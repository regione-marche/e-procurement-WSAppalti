
package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per ProdottoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ProdottoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="codiceCatalogo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idArticolo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="marcaProdottoProduttore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceProdottoProduttore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeCommerciale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceProdottoFornitore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneAggiuntiva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dimensioni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quantitaUMPrezzo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="aliquotaIVA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prezzoUnitario" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="quantitaUMAcquisto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="prezzoUnitarioPerAcquisto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="garanzia" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tempoConsegna" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dataScadenzaOfferta" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="immagine" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" minOccurs="0"/>
 *         &lt;element name="certificazioniRichieste" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="schedeTecniche" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProdottoType", propOrder = {
    "id",
    "codiceCatalogo",
    "idArticolo",
    "marcaProdottoProduttore",
    "codiceProdottoProduttore",
    "nomeCommerciale",
    "codiceProdottoFornitore",
    "descrizioneAggiuntiva",
    "dimensioni",
    "quantitaUMPrezzo",
    "aliquotaIVA",
    "prezzoUnitario",
    "quantitaUMAcquisto",
    "prezzoUnitarioPerAcquisto",
    "garanzia",
    "tempoConsegna",
    "dataScadenzaOfferta",
    "stato",
    "immagine",
    "certificazioniRichieste",
    "schedeTecniche"
})
public class ProdottoType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected long id;
    @XmlElement(required = true)
    protected String codiceCatalogo;
    protected long idArticolo;
    protected String marcaProdottoProduttore;
    protected String codiceProdottoProduttore;
    protected String nomeCommerciale;
    protected String codiceProdottoFornitore;
    protected String descrizioneAggiuntiva;
    protected String dimensioni;
    protected double quantitaUMPrezzo;
    @XmlElement(required = true)
    protected String aliquotaIVA;
    protected double prezzoUnitario;
    protected double quantitaUMAcquisto;
    protected double prezzoUnitarioPerAcquisto;
    protected Integer garanzia;
    protected int tempoConsegna;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataScadenzaOfferta;
    @XmlElement(required = true)
    protected String stato;
    protected DocumentoAllegatoType immagine;
    protected List<DocumentoAllegatoType> certificazioniRichieste;
    protected List<DocumentoAllegatoType> schedeTecniche;

    /**
     * Recupera il valore della propriet� id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Imposta il valore della propriet� id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Recupera il valore della propriet� codiceCatalogo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceCatalogo() {
        return codiceCatalogo;
    }

    /**
     * Imposta il valore della propriet� codiceCatalogo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceCatalogo(String value) {
        this.codiceCatalogo = value;
    }

    /**
     * Recupera il valore della propriet� idArticolo.
     * 
     */
    public long getIdArticolo() {
        return idArticolo;
    }

    /**
     * Imposta il valore della propriet� idArticolo.
     * 
     */
    public void setIdArticolo(long value) {
        this.idArticolo = value;
    }

    /**
     * Recupera il valore della propriet� marcaProdottoProduttore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarcaProdottoProduttore() {
        return marcaProdottoProduttore;
    }

    /**
     * Imposta il valore della propriet� marcaProdottoProduttore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarcaProdottoProduttore(String value) {
        this.marcaProdottoProduttore = value;
    }

    /**
     * Recupera il valore della propriet� codiceProdottoProduttore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceProdottoProduttore() {
        return codiceProdottoProduttore;
    }

    /**
     * Imposta il valore della propriet� codiceProdottoProduttore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceProdottoProduttore(String value) {
        this.codiceProdottoProduttore = value;
    }

    /**
     * Recupera il valore della propriet� nomeCommerciale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeCommerciale() {
        return nomeCommerciale;
    }

    /**
     * Imposta il valore della propriet� nomeCommerciale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeCommerciale(String value) {
        this.nomeCommerciale = value;
    }

    /**
     * Recupera il valore della propriet� codiceProdottoFornitore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceProdottoFornitore() {
        return codiceProdottoFornitore;
    }

    /**
     * Imposta il valore della propriet� codiceProdottoFornitore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceProdottoFornitore(String value) {
        this.codiceProdottoFornitore = value;
    }

    /**
     * Recupera il valore della propriet� descrizioneAggiuntiva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneAggiuntiva() {
        return descrizioneAggiuntiva;
    }

    /**
     * Imposta il valore della propriet� descrizioneAggiuntiva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneAggiuntiva(String value) {
        this.descrizioneAggiuntiva = value;
    }

    /**
     * Recupera il valore della propriet� dimensioni.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDimensioni() {
        return dimensioni;
    }

    /**
     * Imposta il valore della propriet� dimensioni.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDimensioni(String value) {
        this.dimensioni = value;
    }

    /**
     * Recupera il valore della propriet� quantitaUMPrezzo.
     * 
     */
    public double getQuantitaUMPrezzo() {
        return quantitaUMPrezzo;
    }

    /**
     * Imposta il valore della propriet� quantitaUMPrezzo.
     * 
     */
    public void setQuantitaUMPrezzo(double value) {
        this.quantitaUMPrezzo = value;
    }

    /**
     * Recupera il valore della propriet� aliquotaIVA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliquotaIVA() {
        return aliquotaIVA;
    }

    /**
     * Imposta il valore della propriet� aliquotaIVA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliquotaIVA(String value) {
        this.aliquotaIVA = value;
    }

    /**
     * Recupera il valore della propriet� prezzoUnitario.
     * 
     */
    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    /**
     * Imposta il valore della propriet� prezzoUnitario.
     * 
     */
    public void setPrezzoUnitario(double value) {
        this.prezzoUnitario = value;
    }

    /**
     * Recupera il valore della propriet� quantitaUMAcquisto.
     * 
     */
    public double getQuantitaUMAcquisto() {
        return quantitaUMAcquisto;
    }

    /**
     * Imposta il valore della propriet� quantitaUMAcquisto.
     * 
     */
    public void setQuantitaUMAcquisto(double value) {
        this.quantitaUMAcquisto = value;
    }

    /**
     * Recupera il valore della propriet� prezzoUnitarioPerAcquisto.
     * 
     */
    public double getPrezzoUnitarioPerAcquisto() {
        return prezzoUnitarioPerAcquisto;
    }

    /**
     * Imposta il valore della propriet� prezzoUnitarioPerAcquisto.
     * 
     */
    public void setPrezzoUnitarioPerAcquisto(double value) {
        this.prezzoUnitarioPerAcquisto = value;
    }

    /**
     * Recupera il valore della propriet� garanzia.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGaranzia() {
        return garanzia;
    }

    /**
     * Imposta il valore della propriet� garanzia.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGaranzia(Integer value) {
        this.garanzia = value;
    }

    /**
     * Recupera il valore della propriet� tempoConsegna.
     * 
     */
    public int getTempoConsegna() {
        return tempoConsegna;
    }

    /**
     * Imposta il valore della propriet� tempoConsegna.
     * 
     */
    public void setTempoConsegna(int value) {
        this.tempoConsegna = value;
    }

    /**
     * Recupera il valore della propriet� dataScadenzaOfferta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDataScadenzaOfferta() {
        return dataScadenzaOfferta;
    }

    /**
     * Imposta il valore della propriet� dataScadenzaOfferta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataScadenzaOfferta(Date value) {
        this.dataScadenzaOfferta = value;
    }

    /**
     * Recupera il valore della propriet� stato.
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
     * Imposta il valore della propriet� stato.
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
     * Recupera il valore della propriet� immagine.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoAllegatoType }
     *     
     */
    public DocumentoAllegatoType getImmagine() {
        return immagine;
    }

    /**
     * Imposta il valore della propriet� immagine.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoAllegatoType }
     *     
     */
    public void setImmagine(DocumentoAllegatoType value) {
        this.immagine = value;
    }

    /**
     * Gets the value of the certificazioniRichieste property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certificazioniRichieste property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertificazioniRichieste().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoAllegatoType }
     * 
     * 
     */
    public List<DocumentoAllegatoType> getCertificazioniRichieste() {
        if (certificazioniRichieste == null) {
            certificazioniRichieste = new ArrayList<DocumentoAllegatoType>();
        }
        return this.certificazioniRichieste;
    }
    
    public void setCertificazioniRichieste(List<DocumentoAllegatoType> certificazioniRichieste) {
        this.certificazioniRichieste = certificazioniRichieste;
    }

    /**
     * Gets the value of the schedeTecniche property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the schedeTecniche property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSchedeTecniche().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoAllegatoType }
     * 
     * 
     */
    public List<DocumentoAllegatoType> getSchedeTecniche() {
        if (schedeTecniche == null) {
            schedeTecniche = new ArrayList<DocumentoAllegatoType>();
        }
        return this.schedeTecniche;
    }
    
    public void setSchedeTecniche(List<DocumentoAllegatoType> schedeTecniche) {
        this.schedeTecniche = schedeTecniche;
    }

}
