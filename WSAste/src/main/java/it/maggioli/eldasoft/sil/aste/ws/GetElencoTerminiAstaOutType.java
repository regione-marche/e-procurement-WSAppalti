package it.maggioli.eldasoft.sil.aste.ws;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetElencoTerminiAstaOutType", propOrder = {
    "elenco",
    "errore"
})
public class GetElencoTerminiAstaOutType implements Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 7016490355954495945L;
	protected Map<String, String> elenco;
	protected String errore;
	
	/**
	 * @return the elenco
	 */
	public Map<String, String> getElenco() {
		return elenco;
	}
	/**
	 * @param elenco the elenco to set
	 */
	public void setElenco(Map<String, String> elenco) {
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
