package it.maggioli.eldasoft.mybatis.handler;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.*;
import java.util.Calendar;

@Alias("CalendarTypeHandler")
@MappedJdbcTypes(JdbcType.TIMESTAMP)
public class TimestampTypeHandler extends BaseTypeHandler<Calendar>  {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Calendar parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, new Date(parameter.getTimeInMillis()));
    }

    @Override
    public Calendar getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Calendar toReturn = null;

        Timestamp timestamp = rs.getTimestamp(columnName);
        if (timestamp != null) {
            toReturn = Calendar.getInstance();
            toReturn.setTimeInMillis(timestamp.getTime());
        }

        return toReturn;
    }

    @Override
    public Calendar getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Calendar toReturn = null;

        Timestamp timestamp = rs.getTimestamp(columnIndex);
        if (timestamp != null) {
            toReturn = Calendar.getInstance();
            toReturn.setTimeInMillis(timestamp.getTime());
        }

        return toReturn;
    }

    @Override
    public Calendar getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Calendar toReturn = null;

        Timestamp timestamp = cs.getTimestamp(columnIndex);
        if (timestamp != null) {
            toReturn = Calendar.getInstance();
            toReturn.setTimeInMillis(timestamp.getTime());
        }

        return toReturn;
    }

}

