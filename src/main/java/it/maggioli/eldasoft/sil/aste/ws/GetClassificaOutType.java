package it.maggioli.eldasoft.sil.aste.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetClassificaOutType", propOrder = {
    "dettaglio",
    "errore"
})
public class GetClassificaOutType implements Serializable {

	private static final long serialVersionUID = -7566555906249882629L;
	protected List<DettaglioClassificaType> dettaglio;
	protected String errore;
	
	/**
	 * @return the dettaglio
	 */
	public List<DettaglioClassificaType> getDettaglio() {
		return dettaglio;
	}

	/**
	 * @param dettaglio the dettaglio to set
	 */
	public void setDettaglio(List<DettaglioClassificaType> dettaglio) {
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

	
