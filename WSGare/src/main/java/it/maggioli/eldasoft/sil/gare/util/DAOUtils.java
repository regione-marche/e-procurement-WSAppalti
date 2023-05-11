package it.maggioli.eldasoft.sil.gare.util;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;

public class DAOUtils {

    public static RowBounds getBounds(int indicePrimoRecord, int maxNumRecord) {
        return new RowBounds(
                indicePrimoRecord
                , maxNumRecord == 0 ? Integer.MAX_VALUE : maxNumRecord
        );
    }

    public static String getWithPercent(String value) {
        return StringUtils.isNotEmpty(value) ? "%" + value + "%" : null;
    }
    public static String toUppercaseWithPercent(String value) {
        return getWithPercent(StringUtils.upperCase(value));
    }

//    Metodo utilizzabile per generare stringhe case INSENSITIVE da usare nella clausula like in sql
    public static String toWithPercentAndUpper(String value) {
        return getWithPercent(StringUtils.upperCase(value));
    }
}
