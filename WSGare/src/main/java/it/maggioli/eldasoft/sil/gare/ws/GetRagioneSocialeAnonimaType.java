package it.maggioli.eldasoft.sil.gare.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RagioneSocialeAnonima")
public class GetRagioneSocialeAnonimaType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 4042107187457364197L;
	protected String ragioneSocialeAnonima;
    protected String errore;

	public String getRagioneSocialeAnonima() {
		return ragioneSocialeAnonima;
	}
	public void setRagioneSocialeAnonima(String ragioneSocialeAnonima) {
		this.ragioneSocialeAnonima = ragioneSocialeAnonima;
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
