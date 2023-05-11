package it.maggioli.eldasoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@EnableTransactionManagement
//@MapperScan("it.maggioli.eldasoft.**.mapper")
@ImportResource("classpath:META-INF/cxf/cxf.xml")
@PropertySource("WEB-INF/classes/systemParams.properties")
public class SpringConfiguration {

    @Autowired
    private DataSource dataSource;

    /**
     * L'annotation @EnableTransactionManagement si aspetta questo bean.
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


}
