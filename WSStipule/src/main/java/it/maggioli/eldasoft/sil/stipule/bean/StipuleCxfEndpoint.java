package it.maggioli.eldasoft.sil.stipule.bean;

import it.maggioli.eldasoft.sil.stipule.ws.WSStipuleSoap;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StipuleCxfEndpoint {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WSStipuleSoap wsStipuleSoap;

    @Bean
    public EndpointImpl wsStipuleSoapImpl() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, wsStipuleSoap);
        endpoint.publish("/WSStipuleSOAP");
        endpoint.setAddress("/WSStipuleSOAP");
        endpoint.setTransportId("wsstipulesoap");
        return endpoint;
    }

}
