package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckAggiornamentoIscrizionePosticipataOutType", propOrder = {
    "risultato",
    "errore"
})
public class CheckAggiornamentoIscrizionePosticipataOutType 
	implements Serializable {

    /**
	 * UID
	 */
	private static final long serialVersionUID = 8426657436524008005L;
	protected Long risultato;
    protected String errore;
	/**
	 * @return the risultato
	 */
	public Long getRisultato() {
		return risultato;
	}
	/**
	 * @param risultato the risultato to set
	 */
	public void setRisultato(Long risultato) {
		this.risultato = risultato;
	}
	/**
	 * @return the errore
	 */
	public String getErrore() {
		return errore;
	}
	/**
	 * @param errore the errore to set
	 */
	public void setErrore(String errore) {
		this.errore = errore;
	}
    
}
