package it.maggioli.eldasoft.sil.pagopa.ws;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "ElencoTipiCausalePagamentoOutType", propOrder = {
    "tipologia",
    "errore"
})
public class ElencoTipiCausalePagamentoOutType implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Map<java.math.BigDecimal, String> tipologia;
	protected String errore;
	/**
	 * @return the tipologia
	 */
	public Map<java.math.BigDecimal, String> getTipologia() {
		return tipologia;
	}
	/**
	 * @param tipologia the tipologia to set
	 */
	public void setTipologia(Map<java.math.BigDecimal, String> tipologia) {
		this.tipologia = tipologia;
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
