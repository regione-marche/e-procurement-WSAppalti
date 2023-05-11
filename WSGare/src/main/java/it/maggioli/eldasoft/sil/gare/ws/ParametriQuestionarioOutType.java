package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParametriQuestionarioOutType", propOrder = {
    "parametri",
    "errore"
})
public class ParametriQuestionarioOutType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -3548816896380869420L;

	protected List<ParametroQuestionarioType> parametri;
    protected String errore;

	public List<ParametroQuestionarioType> getParametri() {
		return parametri;
	}

	public void setParametri(List<ParametroQuestionarioType> parametri) {
		this.parametri = parametri;
	}

	public String getErrore() {
		return errore;
	}
	
	public void setErrore(String errore) {
		this.errore = errore;
	}

}
