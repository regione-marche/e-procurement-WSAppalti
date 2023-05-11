package it.maggioli.eldasoft.bean;

import it.maggioli.eldasoft.gene.xml.DataHandlerAdapter;
import it.maggioli.eldasoft.mybatis.factory.MyBatisObjectFactory;
import it.maggioli.eldasoft.mybatis.handler.DateTypeHandler;
import it.maggioli.eldasoft.mybatis.handler.TimestampTypeHandler;
import org.apache.ibatis.session.LocalCacheScope;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Classe che crea il bean col la sessione per MyBatis
 * @author cmantini
 *
 */
@Configuration
public class MyBatis {

    @Autowired
    private org.apache.ibatis.session.Configuration mybatisConf;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setObjectFactory(new MyBatisObjectFactory());
        sqlSessionFactoryBean.setConfiguration(mybatisConf);
        sqlSessionFactoryBean.setTypeAliases(
                  TimestampTypeHandler.class
                , DateTypeHandler.class
                , DataHandlerAdapter.class
        );
        sqlSessionFactoryBean.setDataSource(dataSource);

        return sqlSessionFactoryBean;
    }

    /**
     * Queste due property a true servono in caso debba ritornare un xml con xsi:nil="true"
     */
    @Bean
    public org.apache.ibatis.session.Configuration mybatisConf() {
        org.apache.ibatis.session.Configuration toReturn = new org.apache.ibatis.session.Configuration();

        toReturn.setCallSettersOnNulls(true);
        toReturn.setReturnInstanceForEmptyRow(true);
        toReturn.setLocalCacheScope(LocalCacheScope.STATEMENT);
        toReturn.setCacheEnabled(false);

        return toReturn;
    }

}