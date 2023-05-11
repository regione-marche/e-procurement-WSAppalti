package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoAderenteType", propOrder = {
    "codiceFiscale",
    "denominazione"
})

public class SoggettoAderenteType implements Serializable {	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	protected String codiceFiscale;
	protected String denominazione;
	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	/**
	 * @return the denominazione
	 */
	public String getDenominazione() {
		return denominazione;
	}
	/**
	 * @param denominazione the denominazione to set
	 */
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
		
}
