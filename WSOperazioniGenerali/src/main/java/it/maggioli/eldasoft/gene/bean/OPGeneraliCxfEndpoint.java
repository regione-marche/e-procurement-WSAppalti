package it.maggioli.eldasoft.gene.bean;

import it.maggioli.eldasoft.gene.ws.WSOperazioniGeneraliSoap;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OPGeneraliCxfEndpoint {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WSOperazioniGeneraliSoap wsOperazioniGeneraliSoap;

    @Bean
    public EndpointImpl wsOperazioniGeneraliSoapImpl() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, wsOperazioniGeneraliSoap);
        endpoint.publish("/WSOperazioniGeneraliSOAP");
        endpoint.setAddress("/WSOperazioniGeneraliSOAP");
        endpoint.setTransportId("wsoperazionigeneralisoap");
        return endpoint;
    }

}
