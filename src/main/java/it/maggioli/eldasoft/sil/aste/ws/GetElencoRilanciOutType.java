package it.maggioli.eldasoft.sil.aste.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetElencoRilanciOutType", propOrder = {
    "dettaglio",
    "errore"
})
public class GetElencoRilanciOutType implements Serializable {	
	
	private static final long serialVersionUID = 7959467956607966734L;
	protected List<DettaglioRilancioType> dettaglio;
	protected String errore;
	
	/**
	 * @return the dettaglio
	 */
	public List<DettaglioRilancioType> getDettaglio() {
		return dettaglio;
	}
	/**
	 * @param dettaglio the dettaglio to set
	 */
	public void setDettaglio(List<DettaglioRilancioType> dettaglio) {
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
