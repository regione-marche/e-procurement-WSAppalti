
package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;
import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per ContrattoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ContrattoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stazioneAppaltante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tipoAtto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataAtto" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="numeroRepertorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idProgramma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idComunicazione" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idDestinatario" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="dataLettura" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="allegati" type="tns:DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContrattoType", propOrder = {
    "stazioneAppaltante",
    "codice",
    "oggetto",
    "cig",
    "importo",
    "tipoAtto",
    "dataAtto",
    "numeroRepertorio",
    "stato",
    "idProgramma",
    "idDocumento",
    "idComunicazione",
    "idDestinatario",
    "dataLettura",
    "allegati",
    "dataUltimoAggiornamento"
})
public class ContrattoType
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
    protected double importo;
    protected String tipoAtto;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataAtto;
    protected String numeroRepertorio;
    protected String stato;
    @XmlElement(required = true)
    protected String idProgramma;
    protected long idDocumento;
    protected long idComunicazione;
    protected long idDestinatario;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date dataLettura;
    protected List<DocumentoAllegatoType> allegati;
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
     * Denominazione della stazione appaltante    
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
     * Codice interno contratto    
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
     * Oggetto del contratto
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
     * Codice CIG    
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
     * Recupera il valore della proprietà importo.
     * 
     */
    public double getImporto() {
        return importo;
    }

    /**
     * Imposta il valore della proprietà importo.
     * 
     */
    public void setImporto(double value) {
        this.importo = value;
    }

    /**
     * Recupera il valore della proprietà tipoAtto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Tipologia di atto contrattuale    
     */
    public String getTipoAtto() {
        return tipoAtto;
    }

    /**
     * Imposta il valore della proprietà tipoAtto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAtto(String value) {
        this.tipoAtto = value;
    }

    /**
     * Recupera il valore della proprietà dataAtto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Data atto contrattuale    
     */
    public Date getDataAtto() {
        return dataAtto;
    }

    /**
     * Imposta il valore della proprietà dataAtto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataAtto(Date value) {
        this.dataAtto = value;
    }

    /**
     * Recupera il valore della proprietà numeroRepertorio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Numero repertorio    
     */
    public String getNumeroRepertorio() {
        return numeroRepertorio;
    }

    /**
     * Imposta il valore della proprietà numeroRepertorio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRepertorio(String value) {
        this.numeroRepertorio = value;
    }

    /**
     * Recupera il valore della proprietà stato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Stato del contratto
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
     * Recupera il valore della proprietà idProgramma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdProgramma() {
        return idProgramma;
    }

    /**
     * Imposta il valore della proprietà idProgramma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdProgramma(String value) {
        this.idProgramma = value;
    }

    /**
     * Recupera il valore della proprietà idDocumento.
     * 
     * @return idDocumento   
     * Id da utilizzare per richiedere l'apertura del documento
     */
    public long getIdDocumento() {
        return idDocumento;
    }

    /**
     * Imposta il valore della proprietà idDocumento.
     * 
     * @param idDocumento   
	 * Id da utilizzare per richiedere l'apertura del documento
     */
    public void setIdDocumento(long value) {
        this.idDocumento = value;
    }

    /**
     * Recupera il valore della proprietà idComunicazione.
     * 
     */
    public long getIdComunicazione() {
        return idComunicazione;
    }

    /**
     * Imposta il valore della proprietà idComunicazione.
     * 
     */
    public void setIdComunicazione(long value) {
        this.idComunicazione = value;
    }

    /**
     * Recupera il valore della proprietà idDestinatario.
     * 
     */
    public long getIdDestinatario() {
        return idDestinatario;
    }

    /**
     * Imposta il valore della proprietà idDestinatario.
     * 
     */
    public void setIdDestinatario(long value) {
        this.idDestinatario = value;
    }

    /**
     * Recupera il valore della proprietà dataLettura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDataLettura() {
        return dataLettura;
    }

    /**
     * Imposta il valore della proprietà dataLettura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataLettura(Date value) {
        this.dataLettura = value;
    }

    /**
     * Recupera il valore della proprietà allegati.
     * 
     * @return
     *     possible object is
     *     {@link List<DocumentoAllegatoType> }
     *     
     */
    public List<DocumentoAllegatoType> getAllegati() {
        return allegati;
    }

    /**
     * Imposta il valore della proprietà allegati.
     * 
     * @param value
     *     allowed object is
     *     {@link List<DocumentoAllegatoType> }
     *     
     */
    public void setAllegati(List<DocumentoAllegatoType> allegati) {
        this.allegati = allegati;
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
