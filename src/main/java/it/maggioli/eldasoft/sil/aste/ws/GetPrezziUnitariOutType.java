package it.maggioli.eldasoft.sil.aste.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPrezziUnitariOutType", propOrder = {
    "dettaglio",
    "errore"
})
public class GetPrezziUnitariOutType implements Serializable {

	/**
	 * UID 
	 */
	private static final long serialVersionUID = 3130465543423310937L;
	protected List<VoceDettaglioAstaType> dettaglio;
	protected String errore;
	
	/**
	 * @return the dettaglio
	 */
	public List<VoceDettaglioAstaType> getDettaglio() {
		return dettaglio;
	}
	/**
	 * @param dettaglio the dettaglio to set
	 */
	public void setDettaglio(List<VoceDettaglioAstaType> dettaglio) {
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
