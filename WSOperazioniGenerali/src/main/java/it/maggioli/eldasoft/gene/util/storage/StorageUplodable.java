package it.maggioli.eldasoft.gene.util.storage;

import it.maggioli.eldasoft.gene.bean.rest.ObjectStorageClient;
import it.maggioli.eldasoft.gene.util.Pair;
import it.maggioli.eldasoft.gene.util.metric.MetricOperation;

import java.util.function.BiConsumer;

public interface StorageUplodable {

    BiConsumer<ObjectStorageClient, MetricOperation> rollback();
    BiConsumer<ObjectStorageClient, MetricOperation> run();
    Pair<String, Integer> metric_metadata();

}