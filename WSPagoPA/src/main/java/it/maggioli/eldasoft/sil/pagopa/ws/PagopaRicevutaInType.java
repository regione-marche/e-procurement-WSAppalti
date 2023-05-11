package it.maggioli.eldasoft.sil.pagopa.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagopaRicevutaInType", propOrder = {
		"id",
		"ricevuta"
})
public class PagopaRicevutaInType {
	@XmlElement(required=true,nillable=false)
	private Long id;
	@XmlElement(required=true,nillable=false)
	private byte[] ricevuta;
	
	public PagopaRicevutaInType id(Long id) {
		this.id = id;
		return this;
	}
	public PagopaRicevutaInType ricevuta(byte[] ricevuta) {
		this.ricevuta = ricevuta;
		return this;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the ricevuta
	 */
	public byte[] getRicevuta() {
		return ricevuta;
	}
	/**
	 * @param ricevuta the ricevuta to set
	 */
	public void setRicevuta(byte[] ricevuta) {
		this.ricevuta = ricevuta;
	}
	
}
