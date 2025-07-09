package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.sil.gare.ws.dto.ElencoOperatoriAbilitatiElenco;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElencoOperatoriAbilitatiElenchiOutType", propOrder = {
    "result",
	"totaleOPAbilitati",
    "errore"
})
public class ElencoOperatoriAbilitatiElenchiOutType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 538107507980871229L;
	protected long totaleOPAbilitati;
	protected List<ElencoOperatoriAbilitatiElenco> result;
    protected String errore;

	public List<ElencoOperatoriAbilitatiElenco> getResult() {
		return result;
	}
	public void setResult(List<ElencoOperatoriAbilitatiElenco> result) {
		this.result = result;
	}
	public String getErrore() {
		return errore;
	}
	
	public void setErrore(String errore) {
		this.errore = errore;
	}
	public long getTotaleOPAbilitati() {
		return totaleOPAbilitati;
	}
	public void setTotaleOPAbilitati(long totaleOPAbilitati) {
		this.totaleOPAbilitati = totaleOPAbilitati;
	}

	@Override
	public String toString() {
		return "ElencoOperatoriAbilitatiElenchiOutType{" +
				"result=" + result +
				", errore='" + errore + '\'' +
				'}';
	}

}
