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
 * Classe che permette di effettuare l'aggiornamento di un file su bucket.
 *
 * Attenzione: L'upload su bucket viene effettuato SOLO se getModificato() != 0
 */
@Slf4j
@RequiredArgsConstructor
public final class UpdateBuilder implements StorageUplodable {

    // Allegato (contenente il byte con il file), indice
    private final TriConsumer<AllegatoComunicazioneType, UploadInfo, Integer> query;
    private List<AllegatoComunicazioneType> attachments = new ArrayList<>();
    private List<UploadInfo> inserted = new ArrayList<>();

    /**
     * Istanzia una classe che permette di effettuare l'aggiornamento di un file su bucket.
     * @param query
     * @return
     */
    public static UpdateBuilder update_file(final TriConsumer<AllegatoComunicazioneType, UploadInfo, Integer> query) {
        return new UpdateBuilder(query);
    }

    public UpdateBuilder attachment(final AllegatoComunicazioneType attachment) {
        attachments.add(attachment);
        return this;
    }
    public UpdateBuilder attachments(final List<AllegatoComunicazioneType> attachments) {
        this.attachments.addAll(attachments);
        return this;
    }

    @Override
    public Pair<String, Integer> metric_metadata() {
        return Pair.of("UPDATE", attachments.size());
    }

    @Override
    public BiConsumer<ObjectStorageClient, MetricOperation> rollback() {
        return (client, operation) -> {
            for (int index = 0; index < inserted.size(); index++)
                rollback(client, Pair.of(attachments.get(index), true), inserted.get(index), operation);
        };
    }

    private void rollback(
        final ObjectStorageClient client
        , final Pair<AllegatoComunicazioneType, Boolean> pair
        , final UploadInfo info
        , final MetricOperation metric_operation
    ) {
        try {
            val attachment = pair.left();
            val upload_succeded = pair.right();
            // Controllo che sia stato effettivamente fatto l'upload
            if (attachment.getModificato() != 0 || StringUtils.isEmpty(attachment.getUuid())) {
                // Se ho fatto anche l'upload del nuovo documento lo cancello
                val metric_metadata = new HashMap<String, Object>();
                metric_metadata.put("file_name", attachment.getNomeFile());
                if (upload_succeded) {
                    metric_operation.exec_void(
                        "ROLLBACK_UPDATE_INTO_DELETE_" + attachment.getId()
                            , metric_metadata
                            , op -> client.delete_folder(info.bucket_ref(), info.uuid())
                    );
                }
                if (StringUtils.isNotEmpty(attachment.getBucket_uuid()))
                    // Faccio la restore del documento cancellato logicamente
                    metric_operation.exec_void(
                        "ROLLBACK_UPDATE_INTO_RESTORE_" + attachment.getId()
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
     * Sostituisco il file con quello nuovo ed eseguo la query a db.
     *
     * In caso l'environment non fosse attiva, esegue semplicemente le query.
     *
     * @return
     */
    @Override
    public BiConsumer<ObjectStorageClient, MetricOperation> run() {
        return (client, operation) -> {
            if (!attachments.isEmpty()) {
                // Allegato per rollback + booleano per capire se è stato fatto l'upload del documento (se false non faccio la delete)
                // Ho bisogno di tenerne traccia localmente, percè, se termino il metodo senza errori, vuol dire che devo fare la rollback di tutti
                val executed = new ArrayList<Pair<AllegatoComunicazioneType, Boolean>>(attachments.size());
                try {
                    for (var index = 0; index < attachments.size(); index++) {
                        val attachment = attachments.get(index);
                        val metric_metadata = new HashMap<String, Object>();
                        metric_metadata.put("file_name", attachment.getNomeFile());

                        var info = new UploadInfo(attachment.getBucket_uuid(), attachment.getBucket_ref());
                        // uuid = null quando il file è generato da portale e quindi, diamo x assunto che è un file che va aggiornato sempre
                        // modifcato == 1 && bucket_uuid != null (vuol dire che devo fare il replace di un file su db
                        if (
                            // File autogenerato (modificare sempre)
                            StringUtils.isEmpty(attachment.getUuid())
                            // File inserito dall'utente
                            || attachment.getModificato() != 0
                        ) {
                            // Solo se prima il file era presente a db
                            if (StringUtils.isNotEmpty(attachment.getBucket_uuid())) {
                                operation.exec_void(
                                        "UPDATE_BUCKET_LOGICAL_DELETE_" + attachment.getId()
                                        , metric_metadata
                                        // Cancello logicamente il vecchio file
                                        , op -> client.logical_delete_folder(attachment.getBucket_ref(), attachment.getBucket_uuid())
                                );
                                executed.add(Pair.of(attachment, false));
                            }

                            metric_metadata.put("file_size", attachment.getFile().length);

                            // Effettuo l'upload su bucket solo se è abilitata la property
                            if (client.is_enabled()) {
                                info = operation.exec(
                                    "UPDATE_BUCKET_UPLOAD_" + attachment.getId()
                                        , metric_metadata
                                        // Faccio l'upload del nuovo file
                                        , op -> client.full_upload(attachment)
                                );
                                // Serve per tenere traccia dei nuovi uuid (rollback)

                                // Se era a già su bucket lo avevo già inserito in elenco
                                if (StringUtils.isNotEmpty(attachment.getBucket_uuid()))
                                    // Cancello il record con il pair a false e visto che l'upload è avvenuto con successo lo imposto a true
                                    executed.remove(executed.size() - 1);

                                executed.add(Pair.of(attachment, true));
                                // Imposto a null così non verrà fatto l'upload dei file su db

                                attachment.setFile(null);
                                inserted.add(info);
                            } else {
                                // Deve essere fatto l'upload su w_docdig
                                // Quindi, inserisco i riferimenti per la rollback, ma, specifico alla query di inserire bucket_uuid/ref a null
                                inserted.add(info);
                                info = new UploadInfo(null, null);
                            }
                            // Impostare campi info a null se client non abilitato
                        }
                        val temp_info = info;
                        val temp_index = index;
                        operation.exec_void(
                            "UPDATE_WDOCDIG_" + attachment.getId()
                                , metric_metadata
                                // Lancio la query a prescindere
                                , op -> query.accept(attachment, temp_info, temp_index)
                        );
                    }
                } catch (Exception e) {
                    val metric_metadata = new HashMap<String, Object>();
                    metric_metadata.put("to_restore", executed.size());

                    operation.exec_void(
                        "ROLLBACK_TRIGGERED"
                            , metric_metadata
                            , op -> {
                                // In caso di eccezione effettuo la rollback dei dati locali
                                for (var index = 0; index < executed.size(); index++)
                                    rollback(
                                        client
                                        , executed.get(index)
                                        , inserted.size() > index ? inserted.get(index) : new UploadInfo()
                                        , op
                                    );
                            }
                    );
                    // Propago l'eccezione per avere la rollback anche del resto + dei dati a db
                    throw new ObjectStorageException(e);
                }
            }
        };
    }
}
