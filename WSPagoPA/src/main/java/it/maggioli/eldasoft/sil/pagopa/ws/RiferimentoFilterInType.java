package it.maggioli.eldasoft.sil.pagopa.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RiferimentoFilterInType", propOrder = {
		"codice",
		"oggetto",
		"sa",
		"cig",
		"usernome",
		"page",
		"pagesize"
})
public class RiferimentoFilterInType implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String codice;
	private String oggetto;
	private String sa;
	private String cig;
	@XmlElement(required = true)
	private String usernome;
	private Integer page;
	private Integer pagesize;
	
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

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the pagesize
	 */
	public Integer getPagesize() {
		return pagesize;
	}

	/**
	 * @param pagesize the pagesize to set
	 */
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public int getOffset() {
		return page * pagesize;
	}
	
	@Override
	public String toString() {
		return "RiferimentoFilterInType [" + (getCodice() != null ? "getCodice()=" + getCodice() + ", " : "")
				+ (getOggetto() != null ? "getOggetto()=" + getOggetto() + ", " : "")
				+ (getSa() != null ? "getSa()=" + getSa() + ", " : "")
				+ (getCig() != null ? "getCig()=" + getCig() + ", " : "")
				+ (getUsernome() != null ? "getUsernome()=" + getUsernome() + ", " : "")
				+ (getPage() != null ? "getPage()=" + getPage() + ", " : "")
				+ (getPagesize() != null ? "getPagesize()=" + getPagesize() + ", " : "")
				+ "]";
	}
	
}
