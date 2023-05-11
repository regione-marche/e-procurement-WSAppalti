package it.maggioli.eldasoft.sil.aste.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDettaglioAstaOutType", propOrder = {
    "dettaglio",
    "errore"
})
public class GetDettaglioAstaOutType implements Serializable {
	
	private static final long serialVersionUID = -1240622124138886709L;
	protected DettaglioAstaType dettaglio;
	protected String errore;	
	
	/**
	 * @return the dettaglio
	 */
	public DettaglioAstaType getDettaglio() {
		return dettaglio;
	}

	/**
	 * @param dettaglio the dettaglio to set
	 */
	public void setDettaglio(DettaglioAstaType dettaglio) {
		this.dettaglio = dettaglio;
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
