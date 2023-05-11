package it.maggioli.eldasoft.sil.gare.ws;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum
OrderCriteria {
    DATA_SCADENZA_DESC("dattermine DESC, oratermine DESC"),
    DATA_SCADENZA_ASC("dattermine ASC, oratermine ASC"),
    DATA_PUBBLICAZIONE_ASC("datpub ASC"),
    DATA_PUBBLICAZIONE_DESC("datpub DESC"),
    IMPORTO_ASC("importo ASC"),
    IMPORTO_DESC("importo DESC");

    OrderCriteria(String sqlCriteriaValue) {
        this.sqlCriteriaValue = sqlCriteriaValue;
    }
    private String sqlCriteriaValue;
    private static final List<OrderCriteria> ivalues = Collections.unmodifiableList(Arrays.asList(values()));


    public String getSqlCriteriaValue() {
        return sqlCriteriaValue;
    }

    public static OrderCriteria getOrdinal(Integer ordinal) {
        return ivalues.get(ordinal);
    }
}