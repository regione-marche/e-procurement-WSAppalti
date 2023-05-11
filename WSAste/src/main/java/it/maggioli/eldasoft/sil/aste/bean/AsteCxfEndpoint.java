package it.maggioli.eldasoft.sil.aste.bean;

import it.maggioli.eldasoft.sil.aste.ws.WSAsteSoap;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsteCxfEndpoint {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WSAsteSoap wsAsteSoap;

    @Bean
    public EndpointImpl wsAsteSoapImpl() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, wsAsteSoap);
        endpoint.publish("/WSAsteSOAP");
        endpoint.setAddress("/WSAsteSOAP");
        endpoint.setTransportId("wsastesoap");
        return endpoint;
    }

}
