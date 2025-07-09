package it.maggioli.eldasoft.gene.util.metric;

import lombok.experimental.UtilityClass;
import lombok.val;
import lombok.var;

import java.util.HashMap;

@UtilityClass
final class MetricStatsCalculator {

    private static final OperationStats EMPTY_STATS = new OperationStats(
            0, 0, 0.0, 0, "", new HashMap<>()
    );

    public static OperationStats calculateStats(final MetricOperation operation) {
        if (operation == null)
            return EMPTY_STATS;

        val directChildren = operation.child_operations();

        // Se non ci sono figli diretti, ritorna statistiche vuote
        if (directChildren.isEmpty())
            return EMPTY_STATS;

        val total_duration = directChildren.stream()
            .map(it -> it.end_time() - it.start_time())
        .reduce(Long::sum)
                .orElse(0L);

        // Calcola statistiche solo per i figli diretti
        long maxDuration = 0;
        var slowestOperationType = "";
        val operationTypeDurations = new HashMap<String, Long>();

        // Calcola statistiche base
        for (MetricOperation child : directChildren) {
            long duration = calculateDuration(child);

            if (duration > maxDuration) {
                maxDuration = duration;
                slowestOperationType = child.identifier();
            }

            operationTypeDurations.merge(child.identifier(), duration, Long::sum);
        }

        // Calcola percentuali per i figli diretti
        val percentages = new HashMap<String, Double>();
        if (total_duration > 0) {
            for (val entry : operationTypeDurations.entrySet()) {
                double percentage = (entry.getValue() * 100.0) / total_duration;
                percentages.put(entry.getKey(), Math.round(percentage * 100.0) / 100.0);
            }
        }

        // Calcola medie e altre statistiche
        int totalOperations = directChildren.size();
        double avgDuration =
                totalOperations > 0
                    ? Math.round((float) total_duration / totalOperations)
                    : 0.0;


        return new OperationStats(
                total_duration,
                maxDuration,
                avgDuration,
                totalOperations,
                slowestOperationType,
                percentages
        );
    }

    private static long calculateDuration(final MetricOperation operation) {
        return (operation.end_time() != null ? operation.end_time() : System.currentTimeMillis())
                - operation.start_time();
    }
}