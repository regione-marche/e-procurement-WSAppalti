package it.maggioli.eldasoft.gene.util.metric;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Map;

@EqualsAndHashCode
@Accessors(fluent = true, chain = true)
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class OperationStats {
    private final long totalDuration;
    private final long maxDuration;
    private final double avgDuration;
    private final int totalOperations;
    private final String slowestOperation;
    private final Map<String, Double> operationTypePercentages;
}