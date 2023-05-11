package it.maggioli.eldasoft.sil.aste.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InsertRilancioAstaOutType", propOrder = {
    "idRilancio",
    "errore"
})
public class InsertRilancioAstaOutType implements Serializable {
	
	private static final long serialVersionUID = -5856752667133369278L;	
	protected Long idRilancio;
	protected String errore;	

	/**
	 * @return the idRilancio
	 */
	public Long getIdRilancio() {
		return idRilancio;
	}
	/**
	 * @param idRilancio the idRilancio to set
	 */
	public void setIdRilancio(Long idRilancio) {
		this.idRilancio = idRilancio;
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
