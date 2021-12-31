package it.maggioli.eldasoft.sil.aste.ws;

import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DettaglioRilancioType", propOrder = {
	"id",
	"codiceGara",
	"username",
	"ditta",
	"numeroRilancio",
	"numeroFase",
	"ribasso",
	"importo",
	"dataRilancio"
})
public class DettaglioRilancioType implements Serializable {

	/**
	 * UID 
	 */
	private static final long serialVersionUID = -6806461096254006393L;	
	protected Long id;
	protected String codiceGara;
	protected String username;
	protected String ditta;
	protected Integer numeroRilancio;
	protected Integer numeroFase;
	protected Double ribasso;
	protected Double importo;	
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataRilancio;
	
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
	 * @return the codiceGara
	 */
	public String getCodiceGara() {
		return codiceGara;
	}
	/**
	 * @param codiceGara the codiceGara to set
	 */
	public void setCodiceGara(String codiceGara) {
		this.codiceGara = codiceGara;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}	
	/**
	 * @return the ditta
	 */
	public String getDitta() {
		return ditta;
	}
	/**
	 * @param ditta the ditta to set
	 */
	public void setDitta(String ditta) {
		this.ditta = ditta;
	}
	/**
	 * @return the numeroRilancio
	 */
	public Integer getNumeroRilancio() {
		return numeroRilancio;
	}
	/**
	 * @param numeroRilancio the numeroRilancio to set
	 */
	public void setNumeroRilancio(Integer numeroRilancio) {
		this.numeroRilancio = numeroRilancio;
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
	/**
	 * @return the ribasso
	 */
	public Double getRibasso() {
		return ribasso;
	}
	/**
	 * @param ribasso the ribasso to set
	 */
	public void setRibasso(Double ribasso) {
		this.ribasso = ribasso;
	}
	/**
	 * @return the importo
	 */
	public Double getImporto() {
		return importo;
	}
	/**
	 * @param importo the importo to set
	 */
	public void setImporto(Double importo) {
		this.importo = importo;
	}
	/**
	 * @return the dataRilancio
	 */
	public Date getDataRilancio() {
		return dataRilancio;
	}
	/**
	 * @param dataRilancio the dataRilancio to set
	 */
	public void setDataRilancio(Date dataRilancio) {
		this.dataRilancio = dataRilancio;
	}
	
}
