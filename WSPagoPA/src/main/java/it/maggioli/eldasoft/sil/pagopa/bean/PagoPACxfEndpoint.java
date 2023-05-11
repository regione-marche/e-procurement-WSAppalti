package it.maggioli.eldasoft.sil.pagopa.bean;

import it.maggioli.eldasoft.sil.pagopa.ws.WSPagoPASoapImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagoPACxfEndpoint {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WSPagoPASoapImpl wsPagoPASoap;

    @Bean
    public EndpointImpl wsPagoPASoapImpl() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, wsPagoPASoap);
        endpoint.publish("/WSPagoPASOAP");
        endpoint.setAddress("/WSPagoPASOAP");
        endpoint.setTransportId("wspagopasoap");
        return endpoint;
    }

}
