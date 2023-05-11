package it.maggioli.eldasoft.sil.aste.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;
import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DettaglioFaseAstaType", propOrder = {
	"id",
	"codice",
	"dataInizio",
	"oraInizio",
	"durataMinima",		
	"durataMassima",
	"tempoBase",
	"dataOraApertura",
	"dataOraChiusura",
	"numeroFase"
})
public class DettaglioFaseAstaType implements Serializable {
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 7694582965777383573L;
	private Long id; 
	private String codice;							// codice/codice lotto gara
	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
	private Date dataInizio;
	private String oraInizio;
	protected Long durataMinima;		
	protected Long durataMassima;
	protected Long tempoBase;
	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	private Date dataOraApertura;
	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	private Date dataOraChiusura;
	private Integer numeroFase;
	
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
	 * @return the datainizio
	 */
	public Date getDataInizio() {
		return dataInizio;
	}
	/**
	 * @param datainizio the datainizio to set
	 */
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	/**
	 * @return the oraInizio
	 */
	public String getOraInizio() {
		return oraInizio;
	}
	/**
	 * @param oraInizio the oraInizio to set
	 */
	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}
	/**
	 * @return the durataMinima
	 */
	public Long getDurataMinima() {
		return durataMinima;
	}
	/**
	 * @param durataMinima the durataMinima to set
	 */
	public void setDurataMinima(Long durataMinima) {
		this.durataMinima = durataMinima;
	}
	/**
	 * @return the durataMassima
	 */
	public Long getDurataMassima() {
		return durataMassima;
	}
	/**
	 * @param durataMassima the durataMassima to set
	 */
	public void setDurataMassima(Long durataMassima) {
		this.durataMassima = durataMassima;
	}
	/**
	 * @return the tempoBase
	 */
	public Long getTempoBase() {
		return tempoBase;
	}
	/**
	 * @param tempoBase the tempoBase to set
	 */
	public void setTempoBase(Long tempoBase) {
		this.tempoBase = tempoBase;
	}
	/**
	 * @return the dataOraApertura
	 */
	public Date getDataOraApertura() {
		return dataOraApertura;
	}
	/**
	 * @param dataOraApertura the dataOraApertura to set
	 */
	public void setDataOraApertura(Date dataOraApertura) {
		this.dataOraApertura = dataOraApertura;
	}
	/**
	 * @return the dataOraChiusura
	 */
	public Date getDataOraChiusura() {
		return dataOraChiusura;
	}
	/**
	 * @param dataOraChiusura the dataOraChiusura to set
	 */
	public void setDataOraChiusura(Date dataOraChiusura) {
		this.dataOraChiusura = dataOraChiusura;
	}
	/**
	 * @return the numeroFase
	 */
	public Integer getNumeroFase() {
		return numeroFase;
	}
	/**
	 * @param numeroFase the numeroFase to set
	 */
	public void setNumeroFase(Integer numeroFase) {
		this.numeroFase = numeroFase;
	}
	
}
