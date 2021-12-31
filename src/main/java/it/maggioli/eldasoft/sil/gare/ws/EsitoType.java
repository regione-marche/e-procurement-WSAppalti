
package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per EsitoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EsitoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stazioneAppaltante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipologia">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="tipoAppalto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="proceduraTelematica" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataPubblicazione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataPubblicazioneBando" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="esito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EsitoType", propOrder = {
    "stazioneAppaltante",
    "codice",
    "oggetto",
    "cig",
    "cup",
    "tipologia",
    "tipoAppalto",
    "proceduraTelematica",
    "stato",
    "dataPubblicazione",
    "dataPubblicazioneBando",
    "esito",
    "accordoQuadro",
    "dataUltimoAggiornamento"
})
public class EsitoType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String stazioneAppaltante;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String oggetto;
    protected String cig;
    protected String cup;
    protected int tipologia;
    @XmlElement(required = true)
    protected String tipoAppalto;
    protected boolean proceduraTelematica; 
    protected String stato;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataPubblicazione;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataPubblicazioneBando;
    protected String esito;
    protected boolean accordoQuadro;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataUltimoAggiornamento;

    /**
     * Recupera il valore della proprietà stazioneAppaltante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStazioneAppaltante() {
        return stazioneAppaltante;
    }

    /**
     * Imposta il valore della proprietà stazioneAppaltante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStazioneAppaltante(String value) {
        this.stazioneAppaltante = value;
    }

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della proprietà oggetto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOggetto() {
        return oggetto;
    }

    /**
     * Imposta il valore della proprietà oggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOggetto(String value) {
        this.oggetto = value;
    }

    /**
     * Recupera il valore della proprietà cig.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCig() {
        return cig;
    }

    /**
     * Imposta il valore della proprietà cig.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCig(String value) {
        this.cig = value;
    }

    /**
     * Recupera il valore della proprietà cup.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCup() {
        return cup;
    }

    /**
     * Imposta il valore della proprietà cup.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCup(String value) {
        this.cup = value;
    }

    /**
     * Recupera il valore della proprietà tipologia.
     * 
     * @return tipologia   
     * Tipologia di gara: 1 - Gara a piu' lotti con offerte distinte 
     *                    2 - Gara a lotto unico 
     *                    3 - Gara a piu' lotti con offerta unica
     */
    public int getTipologia() {
        return tipologia;
    }

    /**
     * Imposta il valore della proprietà tipologia.
     * 
     */
    public void setTipologia(int value) {
        this.tipologia = value;
    }

    /**
     * Recupera il valore della proprietà tipoAppalto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Descrizione del Tipo di appalto: Lavori, Forniture, Servizi    
     */
    public String getTipoAppalto() {
        return tipoAppalto;
    }

    /**
     * Imposta il valore della proprietà tipoAppalto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAppalto(String value) {
        this.tipoAppalto = value;
    }

    /**
     * Recupera il valore della proprietà proceduraTelematica.
     * 
     */
    public boolean isProceduraTelematica() {
        return proceduraTelematica;
    }

    /**
     * Imposta il valore della proprietà proceduraTelematica.
     * 
     */
    public void setProceduraTelematica(boolean value) {
        this.proceduraTelematica = value;
    }

    /**
     * Recupera il valore della proprietà stato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietà stato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStato(String value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della proprietà dataPubblicazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDataPubblicazione() {
        return dataPubblicazione;
    }

    /**
     * Imposta il valore della proprietà dataPubblicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataPubblicazione(Date value) {
        this.dataPubblicazione = value;
    }

    /**
     * Recupera il valore della proprietà dataPubblicazioneBando.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDataPubblicazioneBando() {
        return dataPubblicazioneBando;
    }

    /**
     * Imposta il valore della proprietà dataPubblicazioneBando.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataPubblicazioneBando(Date value) {
        this.dataPubblicazioneBando = value;
    }

    /**
     * Recupera il valore della proprietà esito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsito() {
        return esito;
    }

    /**
     * Imposta il valore della proprietà esito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsito(String value) {
        this.esito = value;
    }
    
    /**
     * Recupera il valore della proprietà accordoQuadro.
     * 
     */
    public boolean isAccordoQuadro() {
        return accordoQuadro;
    }

    /**
     * Imposta il valore della proprietà accordoQuadro.
     * 
     */
    public void setAccordoQuadro(boolean value) {
        this.accordoQuadro = value;
    }

	/**
	 * @return the dataUltimoAggiornamento
	 */
	public Date getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	/**
	 * @param dataUltimoAggiornamento the dataUltimoAggiornamento to set
	 */
	public void setDataUltimoAggiornamento(Date dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}
    
}
