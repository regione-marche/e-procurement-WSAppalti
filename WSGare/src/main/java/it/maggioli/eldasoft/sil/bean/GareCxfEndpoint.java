package it.maggioli.eldasoft.sil.bean;

import it.maggioli.eldasoft.sil.gare.ws.WSGareAppaltoSoapImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GareCxfEndpoint {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WSGareAppaltoSoapImpl wsGare;

    @Bean
    public EndpointImpl wsGareAppaltoSOAP() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, wsGare);
        endpoint.publish("/WSGareAppaltoSOAP");
        endpoint.setAddress("/WSGareAppaltoSOAP");
        endpoint.setTransportId("wsgareappaltosoap");
        return endpoint;
    }

}

