package it.maggioli.eldasoft.gene.ws;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "WSDocumentoTypeVerso")
public enum WSDocumentoTypeVerso {
	IN("IN"),
	OUT("OUT");
	
    private final String value;

    WSDocumentoTypeVerso(String value) {
    	this.value = value;
    }
    
    public String value() {
    	return this.value;
    }

}
