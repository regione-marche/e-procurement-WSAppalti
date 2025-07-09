package it.maggioli.eldasoft.gene.util.metric;

import it.maggioli.eldasoft.gene.util.Pair;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Accessors(fluent = true)
@Getter(AccessLevel.PACKAGE)
public class MetricOperation {

    private final String identifier;
    private final Map<String, ?> metadata;
    private long start_time;
    private Long end_time;
    private List<MetricOperation> child_operations = new ArrayList<>(10);

    private MetricOperation(
        final String operation_type
        , final Map<String, ?> metadata
    ) {
        this.identifier = operation_type;
        this.metadata = metadata;
    }

    static <T> Pair<MetricOperation, T> from_metric(
        final String operation_type
        , final Map<String, ?> metadata
        , final Function<MetricOperation, T> child_operations
    ) {
        val operation = new MetricOperation(
                operation_type, metadata
        );
        val fun_result = child_operations
            .compose(_it -> operation.start())
            .andThen(result -> {
                operation.stop();
                return result;
            })
        .apply(operation);

        return Pair.of(operation, fun_result);
    }

    public <T> T exec(
        final String operation_type
        , final Map<String, ?> metadata
        , final Function<MetricOperation, T> child_operations
    ) {
        val operation = new MetricOperation(
                operation_type, metadata
        );
        val fun_result = child_operations
            .compose(_it -> operation.start())
            .andThen(result -> {
                operation.stop();
                return result;
            })
        .apply(operation);

        this.child_operations.add(operation);

        return fun_result;
    }
    public <T> T exec(
        final Map<String, ?> metadata
        , final Function<MetricOperation, T> child_operations
    ) {
        return exec(UUID.randomUUID().toString(), metadata, child_operations);
    }
    public <T> T exec(
        final Function<MetricOperation, T> child_operations
    ) {
        return exec(UUID.randomUUID().toString(), new HashMap<>(), child_operations);
    }

    public void exec_void(
            final String operation_type
            , final Map<String, ?> metadata
            , final Consumer<MetricOperation> child_operations
    ) {
        exec(
            operation_type
            , metadata
            , operation ->{
                child_operations.accept(operation);
                return new Object();
            }
        );
    }
    public void exec_void(
            final Map<String, ?> metadata
            , final Consumer<MetricOperation> child_operations
    ) {
        exec_void(UUID.randomUUID().toString(), metadata, child_operations);
    }
    public void exec_void(
            final Consumer<MetricOperation> child_operations
    ) {
        exec_void(UUID.randomUUID().toString(), new HashMap<>(), child_operations);
    }

    public MetricOperation add_child(final MetricOperation operation) {
        child_operations.add(operation);
        return this;
    }
    public MetricOperation add_childs(final List<MetricOperation> operations) {
        this.child_operations.addAll(operations);
        return this;
    }
    public MetricOperation start() {
        this.start_time = System.currentTimeMillis();
        return this;
    }
    public MetricOperation stop() {
        this.end_time = System.currentTimeMillis();
        return this;
    }

    @Override
    public String toString() {
        val duration =
                end_time != null
                    ? end_time - start_time
                    : "still running"
                ;
        val stats = MetricStatsCalculator.calculateStats(this);

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"identifier\": \"").append(identifier).append("\",\n");
        sb.append("  \"duration\": \"").append(duration).append("ms\",\n");
        sb.append("  \"metadata\": ").append(map_to_json(metadata));
        if (!child_operations.isEmpty())
            sb.append(",\n");
        else
            sb.append("\n");

        if (!child_operations.isEmpty()) {
            sb.append("  \"statistics\":{\n");
            sb.append("    \"totalOperations\": ").append(stats.totalOperations()).append(",\n");
            sb.append("    \"totalDuration\": \"").append(stats.totalDuration()).append("ms\"\n");
//            sb.append("    avgDuration=").append(stats.avgDuration()).append("ms,\n");
//            sb.append("    maxDuration=").append(stats.maxDuration()).append("ms,\n");
//            sb.append("    \"slowestOperation\": \"").append(stats.slowestOperation()).append("\"\n");
//            sb.append("    operationPercentages={\n");
//            stats.operationTypePercentages().forEach((type, percentage) ->
//                    sb.append("      ").append(type).append(": ").append(percentage).append("%,\n")
//            );
//            sb.append("    }\n");
            sb.append("  },\n");

            sb.append("  \"children\": [\n");
            val childrenString = child_operations.stream()
                    .map(child -> "    " + child.toString().replace("\n", "\n    "))
                    .collect(Collectors.joining(",\n"));
            sb.append(childrenString).append("\n");
            sb.append("  ]\n");
        }

        sb.append("}");
        return sb.toString();
    }

    private String map_to_json(final Map<?, ?> map) {
        if (map == null)
            return "{ }";
        else {
            return String.format(
                "{ %s }"
                , map.entrySet()
                        .stream()
                        .map(it ->
                                String.format("\"%s\": \"%s\"", it.getKey(), it.getValue())
                        )
                    .collect(Collectors.joining(", "))
            );
        }
    }

}
