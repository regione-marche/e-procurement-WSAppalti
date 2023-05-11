package it.maggioli.eldasoft.mybatis.handler;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

@Alias("DataHandlerTypeHandlerCallback")
@MappedJdbcTypes(JdbcType.DATE)
public class DateTypeHandler extends BaseTypeHandler<Calendar>  {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Calendar parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, new Date(parameter.getTimeInMillis()));
    }

    @Override
    public Calendar getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Calendar toReturn = null;

        Date timestamp = rs.getDate(columnName);
        if (timestamp != null) {
            toReturn = Calendar.getInstance();
            toReturn.setTimeInMillis(timestamp.getTime());
        }

        return toReturn;
    }

    @Override
    public Calendar getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Calendar toReturn = null;

        Date timestamp = rs.getDate(columnIndex);
        if (timestamp != null) {
            toReturn = Calendar.getInstance();
            toReturn.setTimeInMillis(timestamp.getTime());
        }

        return toReturn;
    }

    @Override
    public Calendar getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Calendar toReturn = null;

        Date timestamp = cs.getDate(columnIndex);
        if (timestamp != null) {
            toReturn = Calendar.getInstance();
            toReturn.setTimeInMillis(timestamp.getTime());
        }

        return toReturn;
    }

}
