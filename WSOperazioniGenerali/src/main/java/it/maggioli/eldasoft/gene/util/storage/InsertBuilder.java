package it.maggioli.eldasoft.gene.util.storage;

import it.maggioli.eldasoft.gene.bean.rest.ObjectStorageClient;
import it.maggioli.eldasoft.gene.model.UploadInfo;
import it.maggioli.eldasoft.gene.util.Pair;
import it.maggioli.eldasoft.gene.util.exception.ObjectStorageException;
import it.maggioli.eldasoft.gene.util.metric.MetricOperation;
import it.maggioli.eldasoft.gene.ws.AllegatoComunicazioneType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Classe che permette di effettuare l'inserimento di un file su bucket.
 */
@Slf4j
@RequiredArgsConstructor
public final class InsertBuilder implements StorageUplodable {

    // Allegato (contenente il byte con il file), info bucket, indice
    private final TriConsumer<AllegatoComunicazioneType, UploadInfo, Integer> query;
    private List<AllegatoComunicazioneType> attachments = new ArrayList<>();

    private List<UploadInfo> inserted = new ArrayList<>();

    public static InsertBuilder insert_file(final TriConsumer<AllegatoComunicazioneType, UploadInfo, Integer> query) {
        return new InsertBuilder(query);
    }

    public InsertBuilder attachment(final AllegatoComunicazioneType attachment) {
        attachments.add(attachment);
        return this;
    }
    public InsertBuilder attachments(final List<AllegatoComunicazioneType> attachments) {
        this.attachments.addAll(attachments);
        return this;
    }

    @Override
    public Pair<String, Integer> metric_metadata() {
        return Pair.of("INSERT", attachments.size());
    }

    @Override
    public BiConsumer<ObjectStorageClient, MetricOperation> rollback() {
        return (client, operation) -> {
            for (var index = 0; index < inserted.size(); index++)
                rollback(client, index, inserted.get(index), operation);
        };
    }

    private void rollback(final ObjectStorageClient client, final int index, final UploadInfo info, final MetricOperation operation) {
        try {
            if (StringUtils.isNotEmpty(info.uuid()))
                operation.exec_void(
                "INSERT_ROLLBACK_INTO_DELETE_" + index
                    , new HashMap<>()
                    , op -> client.delete_folder(info.bucket_ref(), info.uuid())
                );
        } catch (Exception e) {
            log.error("E' stato rilevato un'errore durante la rollback dell'allegato {}", info, e);
        }
    }

    /**
     * Inserisco il file sul bucket ed eseguo la query a db.
     *
     * In caso l'environment non fosse attiva, esegue semplicemente le query.
     *
     * @return
     */
    @Override
    public BiConsumer<ObjectStorageClient, MetricOperation> run() {
        return (client, metric_operation) -> {
            if (!attachments.isEmpty()) {
                val executed = new ArrayList<UploadInfo>(attachments.size());
                try {
                    for (int index = 0; index < attachments.size(); index++) {
                        val attachment = attachments.get(index);
                        val metric_metadata = new HashMap<String, Object>();
                        metric_metadata.put("file_size", attachment.getFile().length);
                        metric_metadata.put("file_name", attachment.getNomeFile());

                        var info = new UploadInfo(null, null);
                        // SOLO SE E' ABILITATA L'ENVIRONMENT
                        if (client.is_enabled()) {
                            // Non posso procedere con l'inserimento, perchè, dovrei inserire su bucket il file, ma il bucket non è configurato
                            if (!client.is_configured())
                                throw new ObjectStorageException(
                                    "Non è possibile proseguire con l'inserimento degli allegati " +
                                    "perchè si è richiesto l'inserimento di un allegato su bucket," +
                                    "ma il bucket non è stato configurato sul servizio."
                                );
                            info = metric_operation.exec(
                        "INSERT_BUCKET_" + index
                                , metric_metadata
                                // Effettuo l'upload del file
                                , op -> client.full_upload(attachment)
                            );
                            attachment.setFile(null);
                            // Aggiungo alla lista degli allegati elaborati
                            executed.add(info);
                            inserted.add(info);
                        }
                        val temp_info = info;
                        val temp_index = index;
                        metric_operation.exec_void(
                        "INSERT_WDOCDIG_" + index
                            , metric_metadata
                            // Eseguo la query a prescindere
                            , op -> query.accept(attachment, temp_info, temp_index)
                        );
                    }
                } catch (Exception e) {
                    val metric_metadata = new HashMap<String, Object>();
                    metric_metadata.put("to_restore", executed.size());

                    // SOLO SE E' ABILITATA L'ENVIRONMENT
                    // Effettuo la rollback degli allegati già elaborati sul bucket
                    if (client.is_enabled())
                        metric_operation.exec_void(
                            "ROLLBACK_TRIGGERED"
                                , metric_metadata
                                , op -> {
                                    for (var index = 0; index < executed.size(); index++)
                                        rollback(client, index, executed.get(index), op);
                                }
                        );

                    throw new ObjectStorageException(e);
                }
            }
        };
    }

}