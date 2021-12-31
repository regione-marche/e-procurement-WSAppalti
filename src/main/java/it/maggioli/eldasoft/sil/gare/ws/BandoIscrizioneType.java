
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
 * <p>Classe Java per BandoIscrizioneType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="BandoIscrizioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stazioneAppaltante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoElenco" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataPubblicazione" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataInizioIscrizione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="oraInizioIscrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFineIscrizione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="oraFineIscrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fase" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipoClassifica" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="maxNumProdottiPerArticolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="prezziVisibili" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ammesseRTI" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
/**
 * @author luca.sirri
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BandoIscrizioneType", propOrder = {
    "stazioneAppaltante",
    "codice",
    "oggetto",
    "tipoElenco",
    "stato",
    "dataPubblicazione",
    "dataInizioValidita",
    "dataFineValidita",
    "dataInizioIscrizione",
    "oraInizioIscrizione",
    "dataFineIscrizione",
    "oraFineIscrizione",
    "fase",
    "tipoClassifica",
    "maxNumProdottiPerArticolo",
    "prezziVisibili",
    "ammesseRTI", 
    "pubblicaOperatori",
    "coordinatoreSicurezza",
    "dataUltimoAggiornamento"    
})
public class BandoIscrizioneType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String stazioneAppaltante;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String oggetto;
    @XmlElement(required = true)
    protected String tipoElenco;
    @XmlElement(required = true)
    protected String stato;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataPubblicazione;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataInizioValidita;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataFineValidita;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataInizioIscrizione;
    protected String oraInizioIscrizione;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataFineIscrizione;
    protected String oraFineIscrizione;
    protected int fase;
    protected Integer tipoClassifica;
    protected Integer maxNumProdottiPerArticolo;
    protected Boolean prezziVisibili;
    protected Boolean ammesseRTI;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataUltimoAggiornamento;
    protected Boolean pubblicaOperatori;
    protected Boolean coordinatoreSicurezza;
    
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
     * Recupera il valore della proprietà tipoElenco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Descrizione del Tipo di elenco: 
	 *  Lavori, Forniture, Servizi o misto in varie combinazioni     
     */
    public String getTipoElenco() {
        return tipoElenco;
    }

    /**
     * Imposta il valore della proprietà tipoElenco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoElenco(String value) {
        this.tipoElenco = value;
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
     * Recupera il valore della proprietà dataInizioValidita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Imposta il valore della proprietà dataInizioValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataInizioValidita(Date value) {
        this.dataInizioValidita = value;
    }

    /**
     * Recupera il valore della proprietà dataFineValidita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Imposta il valore della proprietà dataFineValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFineValidita(Date value) {
        this.dataFineValidita = value;
    }

    /**
     * Recupera il valore della proprietà dataInizioIscrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDataInizioIscrizione() {
        return dataInizioIscrizione;
    }

    /**
     * Imposta il valore della proprietà dataInizioIscrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataInizioIscrizione(Date value) {
        this.dataInizioIscrizione = value;
    }

    /**
     * Recupera il valore della proprietà oraInizioIscrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOraInizioIscrizione() {
        return oraInizioIscrizione;
    }

    /**
     * Imposta il valore della proprietà oraInizioIscrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOraInizioIscrizione(String value) {
        this.oraInizioIscrizione = value;
    }

    /**
     * Recupera il valore della proprietà dataFineIscrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
	 * Data termine presentazione domanda di partecipazione
     */
    public Date getDataFineIscrizione() {
        return dataFineIscrizione;
    }

    /**
     * Imposta il valore della proprietà dataFineIscrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFineIscrizione(Date value) {
        this.dataFineIscrizione = value;
    }

    /**
     * Recupera il valore della proprietà oraFineIscrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Ora termine presentazione domanda di partecipazione
     */
    public String getOraFineIscrizione() {
        return oraFineIscrizione;
    }

    /**
     * Imposta il valore della proprietà oraFineIscrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOraFineIscrizione(String value) {
        this.oraFineIscrizione = value;
    }

    /**
     * Recupera il valore della proprietà fase.
     * 
     * @return fase   
	 * Fase di iscrizione: 0 - chiusa (fuoridal periodo di validita) 
	 * 					   1 - aperta 
	 * 					   2 - ma ancora valida (dentro il periodo di validita)
     */
    public int getFase() {
        return fase;
    }

    /**
     * Imposta il valore della proprietà fase.
     * 
     */
    public void setFase(int value) {
        this.fase = value;
    }

    /**
     * Recupera il valore della proprietà tipoClassifica.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoClassifica() {
        return tipoClassifica;
    }

    /**
     * Imposta il valore della proprietà tipoClassifica.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoClassifica(Integer value) {
        this.tipoClassifica = value;
    }

    /**
     * Recupera il valore della proprietà maxNumProdottiPerArticolo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxNumProdottiPerArticolo() {
        return maxNumProdottiPerArticolo;
    }

    /**
     * Imposta il valore della proprietà maxNumProdottiPerArticolo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxNumProdottiPerArticolo(Integer value) {
        this.maxNumProdottiPerArticolo = value;
    }

    /**
     * Recupera il valore della proprietà prezziVisibili.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrezziVisibili() {
        return prezziVisibili;
    }

    /**
     * Imposta il valore della proprietà prezziVisibili.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrezziVisibili(Boolean value) {
        this.prezziVisibili = value;
    }

    /**
     * Recupera il valore della proprietà ammesseRTI.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAmmesseRTI() {
        return ammesseRTI;
    }

    /**
     * Imposta il valore della proprietà ammesseRTI.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAmmesseRTI(Boolean value) {
        this.ammesseRTI = value;
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

	/**
	 * Ritorna il valore di pubblicaOperatori per visualizzare l'elenco operatori
	 * @return pubblicaOperatori
	 */
	public Boolean isPubblicaOperatori() {
		return pubblicaOperatori;
	}

	public void setPubblicaOperatori(Boolean pubblicaOperatori) {
		this.pubblicaOperatori = pubblicaOperatori;
	}

	public Boolean getPubblicaOperatori() {
		return pubblicaOperatori;
	}

	public Boolean getCoordinatoreSicurezza() {
		return coordinatoreSicurezza;
	}

	public void setCoordinatoreSicurezza(Boolean coordinatoreSicurezza) {
		this.coordinatoreSicurezza = coordinatoreSicurezza;
	}
	
}
