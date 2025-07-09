package it.maggioli.eldasoft.gene.util.metric;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Accessors(fluent = true)
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Metric<T> {

    private final MetricOperation operation_set;
    private final T result;

    private boolean with_pretty_print = false;

    public static <T> Metric<T> exec(
        final Function<MetricOperation, T> root_operation
    ) {
        return exec(root_operation, "root_operation", new HashMap<>());
    }

    public static <T> Metric<T> exec(
        final Function<MetricOperation, T> root_operation
        , final String root_operation_type
    ) {
        return exec(root_operation, root_operation_type, new HashMap<>());
    }

    public static <T> Metric<T> exec(
        final Function<MetricOperation, T> root_operation
        , final String root_operation_type
        , final Map<String, ?> metadata
    ) {
        val res = MetricOperation.from_metric(
            root_operation_type
            , metadata
            , root_operation
        );
        return new Metric<>(
            res.left(), res.right()
        );
    }

    public static Metric<Object> exec_void(
            final Consumer<MetricOperation> root_operation
    ) {
        return exec_void(root_operation, "root_operation", new HashMap<>());
    }

    public static Metric<Object> exec_void(
            final Consumer<MetricOperation> root_operation
            , final String root_operation_type
    ) {
        return exec_void(root_operation, root_operation_type, new HashMap<>());
    }

    public static Metric<Object> exec_void(
            final Consumer<MetricOperation> root_operation
            , final String root_operation_type
            , final Map<String, ?> metadata
    ) {
        return exec(
operation -> {
                root_operation.accept(operation);
                return new Object();
            }
            , root_operation_type
            , metadata
        );
    }

    public void with_pretty_print() {
        this.with_pretty_print = true;
    }

    /**
     * Di default viene scritta la versione minificata.
     * @return
     */
    @Override
    public String toString() {
        val result = "Metric {\n  \"Operation\": " + operation_set.toString().replace("\n", "\n  ") + "\n}";
        return with_pretty_print
                ? result
                // Minified version molto semplice e "instabile", fare attenzione a cosa si inserisce sui metadati e su identifier.
                : result.replaceAll("\n[ ]*", "").replaceAll("[ ]*([{},:])[ ]*", "$1");
    }

}
