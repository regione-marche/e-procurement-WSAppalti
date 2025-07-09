package it.maggioli.eldasoft.gene.util.storage;

import it.maggioli.eldasoft.gene.bean.rest.ObjectStorageClient;
import it.maggioli.eldasoft.gene.util.exception.ObjectStorageException;
import it.maggioli.eldasoft.gene.util.metric.Metric;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Pemette di effettuare upload/delete/insert di file a db e su bucket.
 *
 * In caso la property sia disattivata, verrà mantenuto l'insert/update/delete a db.
 *
 * In caso di eccezioni viene effettuata la rollback dei file su bucket e ovviamente da db perchè viene propagata l'eccezione.
 *
 * E' possibile migliorare le performance, valutare:
 * <ul>
 *  <li>Controllo se i dati del bucket sono necessari (ed inseriti) a monte, in modo da non rischiare di effettuare operazioni per nulla.</li>
 * </ul>
 *
 */
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StorageUploaderBuilder {

    private final ObjectStorageClient client;
    private List<StorageUplodable> operations = new ArrayList<>();

    public static StorageUploaderBuilder create(final ObjectStorageClient client) {
        return new StorageUploaderBuilder(client);
    }
    // Aggiungo una operazione Insert/Update/delete con i relativi allegati
    public StorageUploaderBuilder operation(final StorageUplodable uplodable) {
        this.operations.add(uplodable);
        return this;
    }
    public StorageUploaderBuilder operations(final List<StorageUplodable> uplodables) {
        this.operations.addAll(uplodables);
        return this;
    }

    public void start() {
        val metric = Metric.exec_void(metric_operation -> {
            // Se non ci sono operazioni non faccio nulla
            if (!operations.isEmpty()) {
                // Tenuta per capire chi necessita di rollback in caso di eccezioni
                val executed = new ArrayList<StorageUplodable>(operations.size());

                try {
                    // Gestisco le operazioni (insert/update/delete)
                    operations.forEach(it -> {
                        val metric_data = it.metric_metadata();
                        val map = new HashMap<String, Object>();
                        map.put("number_of_attachment", metric_data.right());
                        metric_operation.exec_void(
                            metric_data.left()
                            , map
                            , op -> {
                                // Eseguo l'operazione
                                it.run().accept(client, op);
                                // Aggiungo l'operazione nella lista delle "Già eseguite"
                                executed.add(it);
                            }
                        );
                    });
                } catch (Exception e) {
                    // Effettuo la rollback dei documenti già gestiti se abilitato il bucket
                    metric_operation.exec_void(
                        "ROLLBACK"
                            , new HashMap<>()
                            , op -> executed.forEach(it -> it.rollback().accept(client, op))
                    );
                    throw new ObjectStorageException(e);
                }

            }
        });
        // Non dovrebbe servire, ma, wrappato in questo if per non rischiare di fargli fare questa elaborazione per nulla (calcolo statistiche)
        if (log.isDebugEnabled())
            log.debug("{}", metric);
    }

}
