package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetStazioniAppaltantiOutType", propOrder = {
    "elenco",
    "errore"
})
public class GetStazioniAppaltantiOutType implements Serializable {
    /**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	protected List<DettaglioStazioneAppaltanteType> elenco;
    protected String errore;
	/**
	 * @return the elenco
	 */
	public List<DettaglioStazioneAppaltanteType> getElenco() {
		return elenco;
	}
	/**
	 * @param elenco the elenco to set
	 */
	public void setElenco(List<DettaglioStazioneAppaltanteType> elenco) {
		this.elenco = elenco;
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

