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
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Classe che permette di effettuare la cancellazione di un file su bucket.
 */
@Slf4j
@RequiredArgsConstructor
public final class DeleteBuilder implements StorageUplodable {

    private final Consumer<AllegatoComunicazioneType> query;
    private List<AllegatoComunicazioneType> attachments = new ArrayList<>();

    public static DeleteBuilder delete_file(final Consumer<AllegatoComunicazioneType> query) {
        return new DeleteBuilder(query);
    }

    public DeleteBuilder attachment(final AllegatoComunicazioneType attachment) {
        attachments.add(attachment);
        return this;
    }
    public DeleteBuilder attachments(final List<AllegatoComunicazioneType> attachments) {
        this.attachments.addAll(attachments);
        return this;
    }

    @Override
    public Pair<String, Integer> metric_metadata() {
        return Pair.of("DELETE", attachments.size());
    }

    @Override
    public BiConsumer<ObjectStorageClient, MetricOperation> rollback() {
        return (client, operation) -> {
            attachments.forEach(it -> rollback(client, it, operation));
        };
    }
    private void rollback(final ObjectStorageClient client, final AllegatoComunicazioneType attachment, final MetricOperation operation) {
        try {
            if (StringUtils.isNotEmpty(attachment.getBucket_uuid())) {
                val metric_metadata = new HashMap<String, Object>();
                metric_metadata.put("file_name", attachment.getNomeFile());

                operation.exec_void(
                        "ROLLBACK_DELETE_INTO_RESTORE_" + attachment.getId()
                        , metric_metadata
                        , op -> client.logical_restore_folder(attachment.getBucket_ref(), attachment.getBucket_uuid())
                );
            }
        } catch (Exception e) {
            log.error(
                "E' stato rilevato un'errore durante la rollback dell'allegato {}"
                , new UploadInfo()
                , e
            );
        }
    }

    /**
     * Cancello il file dal bucket ed eseguo la query di cancellazione
     *
     * In caso l'environment non fosse attiva, esegue semplicemente le query.
     *
     * @return
     */
    @Override
    public BiConsumer<ObjectStorageClient, MetricOperation> run() {
        return (client, metric_operation) -> {
            if (!attachments.isEmpty()) {
                val executed = new ArrayList<AllegatoComunicazioneType>(attachments.size());
                try {
                    attachments.forEach(it -> {
                        val metric_metadata = new HashMap<String, Object>();
//                        metric_metadata.put("file_size", it.getFile().length);
                        metric_metadata.put("file_name", it.getNomeFile());

                        // SOLO SE IL FILE ERA GIA' STATO INSERITO SU BUCKET
                        if (StringUtils.isNotEmpty(it.getBucket_uuid())) {
                            // Non posso procedere con la cancellazion, perchè, dovrei cancellare da bucket il file, ma il bucket non è configurato
                            if (!client.is_configured())
                                throw new ObjectStorageException(
                                    "Non è possibile proseguire con la cancellazione degli allegati " +
                                    "perchè si è richiesta la cancellazione di un allegato su bucket," +
                                    "ma il bucket non è stato configurato sul servizio."
                                );

                            metric_operation.exec_void(
                            "DELETE_" + it.getId()
                                , metric_metadata
                                // Cancello logicamente il file così in caso di rollback riesco a recuperarlo
                                , op -> client.logical_delete_folder(it.getBucket_ref(), it.getBucket_uuid())
                            );
                            // Aggiungo alla lista degli allegati elaborati
                            executed.add(it);
                        }
                        metric_operation.exec_void(
                        "DELETE_WDOCDIG_" + it.getId()
                            , metric_metadata
                            // Eseguo la query a prescindere
                            , op -> query.accept(it)
                        );
                    });
                } catch (Exception e) {
                    val metric_metadata = new HashMap<String, Object>();
                    metric_metadata.put("to_restore", executed.size());

                    metric_operation.exec_void(
                        "ROLLBACK_TRIGGERED"
                            , metric_metadata
                            , op -> {
                                // Effettuo la rollback degli allegati già elaborati sul bucket
                                executed
                                    .stream()
                                    // Necessitano di rollback solo le operazioni che hanno effettuato una cancellazione su bucket
                                    // e quindi solo dei file che erano su bucket
                                    .filter(it -> StringUtils.isNotEmpty(it.getBucket_uuid()))
                                // Effettuo la rollback (restore del file)
                                .forEach(it -> rollback(client, it, op));
                            }
                    );

                    throw new ObjectStorageException(e);
                }
            }
        };
    }
}
