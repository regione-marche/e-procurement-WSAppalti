package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettiAderentiOutType", propOrder = {
    "soggetti",
    "errore"
})

public class SoggettiAderentiOutType implements Serializable {
    /**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	protected List<SoggettoAderenteType> soggetti;
    protected String errore;
    
	/**
	 * @return the soggetti
	 */
	public List<SoggettoAderenteType> getSoggetti() {
		return soggetti;
	}
	/**
	 * @param soggetti the soggetti to set
	 */
	public void setSoggetti(List<SoggettoAderenteType> soggetti) {
		this.soggetti = soggetti;
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
