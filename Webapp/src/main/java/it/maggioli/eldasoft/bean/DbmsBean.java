package it.maggioli.eldasoft.bean;

import it.maggioli.eldasoft.util.DBMSSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe che crea il bean: dbms; che contiene il tipo di database
 * In questo modo i moduli inclusi su questa webapp, potranno
 * effettuare l'@Autowired o il @Resource del bean dbms
 */
@Configuration
public class DbmsBean {

    @Autowired
    private DBMSSpecs dbmsSpecs;

    @Bean(name = "dbms")
    public String getDbms() {
        return dbmsSpecs.getInternalDbmsCode();
    }

}