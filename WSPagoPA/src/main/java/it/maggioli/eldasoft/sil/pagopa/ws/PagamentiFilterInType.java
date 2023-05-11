package it.maggioli.eldasoft.sil.pagopa.ws;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagamentiFilterInType", propOrder = {
		"codiceimpresa",
		"codicegara",
		"iuv",
		"idDebito",
		"dataScadenzaDa",
		"dataScadenzaA",
		"causale",
		"stato",
		"page",
		"pagesize"
})
public class PagamentiFilterInType implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(required = true)
	private String codiceimpresa;
	private List<String> codicegara;
	private List<String> iuv;
	private List<String> idDebito;
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
	private Date dataScadenzaDa;
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
	private Date dataScadenzaA;
	private List<Integer> causale;
	private List<Integer> stato;
	@XmlElement(required = true)
	private Long page;
	@XmlElement(required = true)
	private Long pagesize;
	/**
	 * @return the page
	 */
	public Long getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Long page) {
		this.page = page;
	}
	/**
	 * @return the pagesize
	 */
	public Long getPagesize() {
		return pagesize;
	}
	/**
	 * @param pagesize the pagesize to set
	 */
	public void setPagesize(Long pagesize) {
		this.pagesize = pagesize;
	}
	/**
	 * @return the codiceimpresa
	 */
	public String getCodiceimpresa() {
		return codiceimpresa;
	}
	/**
	 * @param codiceimpresa the codiceimpresa to set
	 */
	public void setCodiceimpresa(String codiceimpresa) {
		this.codiceimpresa = codiceimpresa;
	}
	/**
	 * @return the iuv
	 */
	public List<String> getIuv() {
		return iuv;
	}
	/**
	 * @param iuv the iuv to set
	 */
	public void setIuv(List<String> iuv) {
		this.iuv = iuv;
	}
	/**
	 * @return the idDebito
	 */
	public List<String> getIdDebito() {
		return idDebito;
	}
	/**
	 * @param idDebito the idDebito to set
	 */
	public void setIdDebito(List<String> idDebito) {
		this.idDebito = idDebito;
	}
	/**
	 * @return the dataScadenzaDa
	 */
	public Date getDataScadenzaDa() {
		return dataScadenzaDa;
	}
	/**
	 * @param dataScadenzaDa the dataScadenzaDa to set
	 */
	public void setDataScadenzaDa(Date dataScadenzaDa) {
		this.dataScadenzaDa = dataScadenzaDa;
	}
	/**
	 * @return the dataScadenzaA
	 */
	public Date getDataScadenzaA() {
		return dataScadenzaA;
	}
	/**
	 * @param dataScadenzaA the dataScadenzaA to set
	 */
	public void setDataScadenzaA(Date dataScadenzaA) {
		this.dataScadenzaA = dataScadenzaA;
	}
	/**
	 * @return the causale
	 */
	public List<Integer> getCausale() {
		return causale;
	}
	/**
	 * @param causale the causale to set
	 */
	public void setCausale(List<Integer> causale) {
		this.causale = causale;
	}
	
	/**
	 * @return the stato
	 */
	public List<Integer> getStato() {
		return stato;
	}
	/**
	 * @param stato the stato to set
	 */
	public void setStato(List<Integer> stato) {
		this.stato = stato;
	}
	/**
	 * @return the codicegara
	 */
	public List<String> getCodicegara() {
		return codicegara;
	}
	/**
	 * @param codicegara the codicegara to set
	 */
	public void setCodicegara(List<String> codicegara) {
		this.codicegara = codicegara;
	}
	@Override
	public String toString() {
		return "PagamentiFilterInType [" + (codiceimpresa != null ? "codiceimpresa=" + codiceimpresa + ", " : "")
				+ (codicegara != null ? "codicegara=" + codicegara + ", " : "")
				+ (iuv != null ? "iuv=" + iuv + ", " : "") + (idDebito != null ? "idDebito=" + idDebito + ", " : "")
				+ (dataScadenzaDa != null ? "dataScadenzaDa=" + dataScadenzaDa + ", " : "")
				+ (dataScadenzaA != null ? "dataScadenzaA=" + dataScadenzaA + ", " : "")
				+ (causale != null ? "causale=" + causale + ", " : "") + (stato != null ? "stato=" + stato : "") + "]";
	}
	
	public long getOffset() {
		return page * pagesize;
	}
	
}
