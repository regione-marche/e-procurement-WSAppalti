package it.maggioli.eldasoft.sil.pagopa.ws;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagamentiOutType", propOrder = {
		"id",
		"codicegara",
		"codiceimpresa",
		"username",
		"dcreazione",
		"causalecod",
		"causaletip",
		"servizio",
		"importo",
		"iuv",
		"datageniuv",
		"iddebito",
		"datascadenza",
		"datainiziovalidita",
		"datafinevalidita",
		"datapagamento",
		"idrata",
		"statocod",
		"statotip",
		"errore"
})
public class PagamentiOutType {
	private Long id;
	private String codicegara;
	private String codiceimpresa;
	private String username;
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
	private Date dcreazione;
	private String causalecod;
	private Integer causaletip;
	private String servizio;
	private BigDecimal importo;
	private String iuv;
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
	private Date datageniuv;
	private String iddebito;
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
	private Date datascadenza;
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
	private Date datainiziovalidita;
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
	private Date datafinevalidita;
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
	private Date datapagamento;
	private String idrata;
	private String statocod;
	private Integer statotip;
	private String errore;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the codicegara
	 */
	public String getCodicegara() {
		return codicegara;
	}
	/**
	 * @param codicegara the codicegara to set
	 */
	public void setCodicegara(String codicegara) {
		this.codicegara = codicegara;
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
	 * @return the syscon
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param syscon the syscon to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the dcreazione
	 */
	public Date getDcreazione() {
		return dcreazione;
	}
	/**
	 * @param dcreazione the dcreazione to set
	 */
	public void setDcreazione(Date dcreazione) {
		this.dcreazione = dcreazione;
	}
	/**
	 * @return the causalecod
	 */
	public String getCausalecod() {
		return causalecod;
	}
	/**
	 * @param causalecod the causalecod to set
	 */
	public void setCausalecod(String causalecod) {
		this.causalecod = causalecod;
	}
	/**
	 * @return the causaletip
	 */
	public Integer getCausaletip() {
		return causaletip;
	}
	/**
	 * @param causaletip the causaletip to set
	 */
	public void setCausaletip(Integer causaletip) {
		this.causaletip = causaletip;
	}
	/**
	 * @return the servizio
	 */
	public String getServizio() {
		return servizio;
	}
	/**
	 * @param servizio the servizio to set
	 */
	public void setServizio(String servizio) {
		this.servizio = servizio;
	}
	/**
	 * @return the importo
	 */
	public BigDecimal getImporto() {
		return importo;
	}
	/**
	 * @param importo the importo to set
	 */
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}
	/**
	 * @return the iuv
	 */
	public String getIuv() {
		return iuv;
	}
	/**
	 * @param iuv the iuv to set
	 */
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	/**
	 * @return the datageniuv
	 */
	public Date getDatageniuv() {
		return datageniuv;
	}
	/**
	 * @param datageniuv the datageniuv to set
	 */
	public void setDatageniuv(Date datageniuv) {
		this.datageniuv = datageniuv;
	}
	/**
	 * @return the iddebito
	 */
	public String getIddebito() {
		return iddebito;
	}
	/**
	 * @param iddebito the iddebito to set
	 */
	public void setIddebito(String iddebito) {
		this.iddebito = iddebito;
	}
	/**
	 * @return the datascadenza
	 */
	public Date getDatascadenza() {
		return datascadenza;
	}
	/**
	 * @param datascadenza the datascadenza to set
	 */
	public void setDatascadenza(Date datascadenza) {
		this.datascadenza = datascadenza;
	}
	/**
	 * @return the datainiziovalidita
	 */
	public Date getDatainiziovalidita() {
		return datainiziovalidita;
	}
	/**
	 * @param datainiziovalidita the datainiziovalidita to set
	 */
	public void setDatainiziovalidita(Date datainiziovalidita) {
		this.datainiziovalidita = datainiziovalidita;
	}
	/**
	 * @return the datafinevalidita
	 */
	public Date getDatafinevalidita() {
		return datafinevalidita;
	}
	/**
	 * @param datafinevalidita the datafinevalidita to set
	 */
	public void setDatafinevalidita(Date datafinevalidita) {
		this.datafinevalidita = datafinevalidita;
	}
	/**
	 * @return the datapagamento
	 */
	public Date getDatapagamento() {
		return datapagamento;
	}
	/**
	 * @param datapagamento the datapagamento to set
	 */
	public void setDatapagamento(Date datapagamento) {
		this.datapagamento = datapagamento;
	}
	/**
	 * @return the idrata
	 */
	public String getIdrata() {
		return idrata;
	}
	/**
	 * @param idrata the idrata to set
	 */
	public void setIdrata(String idrata) {
		this.idrata = idrata;
	}
	/**
	 * @return the statocod
	 */
	public String getStatocod() {
		return statocod;
	}
	/**
	 * @param statocod the statocod to set
	 */
	public void setStatocod(String statocod) {
		this.statocod = statocod;
	}
	/**
	 * @return the statoTip
	 */
	public Integer getStatotip() {
		return statotip;
	}
	/**
	 * @param statoTip the statoTip to set
	 */
	public void setStatotip(Integer statotip) {
		this.statotip = statotip;
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
