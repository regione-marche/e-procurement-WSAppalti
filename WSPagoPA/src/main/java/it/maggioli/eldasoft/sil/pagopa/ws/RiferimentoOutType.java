package it.maggioli.eldasoft.sil.pagopa.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RiferimentoOutType", propOrder = {
		"codice",
		"oggetto",
		"sa",
		"cig",
		"usernome"
})
public class RiferimentoOutType implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String codice;
	private String oggetto;
	private String sa;
	private String cig;
	private String usernome;
	
	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * @return the oggetto
	 */
	public String getOggetto() {
		return oggetto;
	}

	/**
	 * @param oggetto the oggetto to set
	 */
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	/**
	 * @return the sa
	 */
	public String getSa() {
		return sa;
	}

	/**
	 * @param sa the sa to set
	 */
	public void setSa(String sa) {
		this.sa = sa;
	}

	/**
	 * @return the cig
	 */
	public String getCig() {
		return cig;
	}

	/**
	 * @param cig the cig to set
	 */
	public void setCig(String cig) {
		this.cig = cig;
	}

	/**
	 * @return the usernome
	 */
	public String getUsernome() {
		return usernome;
	}

	/**
	 * @param usernome the usernome to set
	 */
	public void setUsernome(String usernome) {
		this.usernome = usernome;
	}

}
