package it.maggioli.eldasoft.sil.pagopa.ws;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "StatisticaPagamentiOutType", propOrder = {
    "statistica",
    "errore"
})
public class StatisticaPagamentiOutType implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Map<java.math.BigDecimal, Long> statistica;
	protected String errore;
	/**
	 * @return the statistica
	 */
	public Map<java.math.BigDecimal, Long> getStatistica() {
		return statistica;
	}
	/**
	 * @param statistica the statistica to set
	 */
	public void setStatistica(Map<java.math.BigDecimal, Long> statistica) {
		this.statistica = statistica;
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
