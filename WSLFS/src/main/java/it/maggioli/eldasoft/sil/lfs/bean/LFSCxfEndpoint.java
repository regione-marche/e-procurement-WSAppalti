package it.maggioli.eldasoft.sil.lfs.bean;

import it.maggioli.eldasoft.sil.lfs.ws.WSLfsSoapImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LFSCxfEndpoint {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WSLfsSoapImpl wsLFSSoap;

    @Bean
    public EndpointImpl wsLFSSoapImpl() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, wsLFSSoap);
        endpoint.publish("/WSLfsSOAP");
        endpoint.setAddress("/WSLfsSOAP");
        endpoint.setTransportId("wslfssoap");
        return endpoint;
    }

}
