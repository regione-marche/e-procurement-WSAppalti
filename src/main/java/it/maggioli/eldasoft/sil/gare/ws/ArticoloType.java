
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ArticoloType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ArticoloType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="codiceCategoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descrizioneCategoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descrizioneTecnica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="colore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="obbligoImmagine" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="obbligoDescrizioneAggiuntiva" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="obbligoDimensioni" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="obbligoCertificazioni" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="certificazioniRichieste" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="facSimileCertificazioni" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="obbligoSchedaTecnica" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="obbligoGaranzia" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="unitaMisuraDetermPrezzo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numDecimaliDetermPrezzo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitaMisuraAcquisto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numDecimaliAcquisto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="prezzoUnitarioPer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quantitaUnitaAcquisto" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="quantitaMinimaUnitaAcquisto" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="quantitaMassimaUnitaAcquisto" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="tempoMaxConsegna" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitaMisuraTempoConsegna" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prodottoDaVerificare" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArticoloType", propOrder = {
    "id",
    "codiceCategoria",
    "descrizioneCategoria",
    "tipo",
    "codice",
    "descrizione",
    "descrizioneTecnica",
    "colore",
    "obbligoImmagine",
    "obbligoDescrizioneAggiuntiva",
    "obbligoDimensioni",
    "obbligoCertificazioni",
    "certificazioniRichieste",
    "facSimileCertificazioni",
    "obbligoSchedaTecnica",
    "obbligoGaranzia",
    "unitaMisuraDetermPrezzo",
    "numDecimaliDetermPrezzo",
    "unitaMisuraAcquisto",
    "numDecimaliAcquisto",
    "prezzoUnitarioPer",
    "quantitaUnitaAcquisto",
    "quantitaMinimaUnitaAcquisto",
    "quantitaMassimaUnitaAcquisto",
    "tempoMaxConsegna",
    "unitaMisuraTempoConsegna",
    "note",
    "prodottoDaVerificare"
})
public class ArticoloType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected long id;
    @XmlElement(required = true)
    protected String codiceCategoria;
    @XmlElement(required = true)
    protected String descrizioneCategoria;
    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String descrizione;
    protected String descrizioneTecnica;
    protected String colore;
    protected boolean obbligoImmagine;
    protected boolean obbligoDescrizioneAggiuntiva;
    protected boolean obbligoDimensioni;
    protected boolean obbligoCertificazioni;
    protected String certificazioniRichieste;
    protected List<DocumentoAllegatoType> facSimileCertificazioni;
    protected boolean obbligoSchedaTecnica;
    protected boolean obbligoGaranzia;
    @XmlElement(required = true)
    protected String unitaMisuraDetermPrezzo;
    protected int numDecimaliDetermPrezzo;
    @XmlElement(required = true)
    protected String unitaMisuraAcquisto;
    protected int numDecimaliAcquisto;
    @XmlElement(required = true)
    protected String prezzoUnitarioPer;
    protected Double quantitaUnitaAcquisto;
    protected Double quantitaMinimaUnitaAcquisto;
    protected Double quantitaMassimaUnitaAcquisto;
    protected int tempoMaxConsegna;
    @XmlElement(required = true)
    protected String unitaMisuraTempoConsegna;
    protected String note;
    protected boolean prodottoDaVerificare;

    /**
     * Recupera il valore della proprietà id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà codiceCategoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceCategoria() {
        return codiceCategoria;
    }

    /**
     * Imposta il valore della proprietà codiceCategoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceCategoria(String value) {
        this.codiceCategoria = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneCategoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneCategoria() {
        return descrizioneCategoria;
    }

    /**
     * Imposta il valore della proprietà descrizioneCategoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneCategoria(String value) {
        this.descrizioneCategoria = value;
    }

    /**
     * Recupera il valore della proprietà tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietà tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
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
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneTecnica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneTecnica() {
        return descrizioneTecnica;
    }

    /**
     * Imposta il valore della proprietà descrizioneTecnica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneTecnica(String value) {
        this.descrizioneTecnica = value;
    }

    /**
     * Recupera il valore della proprietà colore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColore() {
        return colore;
    }

    /**
     * Imposta il valore della proprietà colore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColore(String value) {
        this.colore = value;
    }

    /**
     * Recupera il valore della proprietà obbligoImmagine.
     * 
     */
    public boolean isObbligoImmagine() {
        return obbligoImmagine;
    }

    /**
     * Imposta il valore della proprietà obbligoImmagine.
     * 
     */
    public void setObbligoImmagine(boolean value) {
        this.obbligoImmagine = value;
    }

    /**
     * Recupera il valore della proprietà obbligoDescrizioneAggiuntiva.
     * 
     */
    public boolean isObbligoDescrizioneAggiuntiva() {
        return obbligoDescrizioneAggiuntiva;
    }

    /**
     * Imposta il valore della proprietà obbligoDescrizioneAggiuntiva.
     * 
     */
    public void setObbligoDescrizioneAggiuntiva(boolean value) {
        this.obbligoDescrizioneAggiuntiva = value;
    }

    /**
     * Recupera il valore della proprietà obbligoDimensioni.
     * 
     */
    public boolean isObbligoDimensioni() {
        return obbligoDimensioni;
    }

    /**
     * Imposta il valore della proprietà obbligoDimensioni.
     * 
     */
    public void setObbligoDimensioni(boolean value) {
        this.obbligoDimensioni = value;
    }

    /**
     * Recupera il valore della proprietà obbligoCertificazioni.
     * 
     */
    public boolean isObbligoCertificazioni() {
        return obbligoCertificazioni;
    }

    /**
     * Imposta il valore della proprietà obbligoCertificazioni.
     * 
     */
    public void setObbligoCertificazioni(boolean value) {
        this.obbligoCertificazioni = value;
    }

    /**
     * Recupera il valore della proprietà certificazioniRichieste.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificazioniRichieste() {
        return certificazioniRichieste;
    }

    /**
     * Imposta il valore della proprietà certificazioniRichieste.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificazioniRichieste(String value) {
        this.certificazioniRichieste = value;
    }

    /**
     * Gets the value of the facSimileCertificazioni property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the facSimileCertificazioni property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFacSimileCertificazioni().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoAllegatoType }
     * 
     * 
     */
    public List<DocumentoAllegatoType> getFacSimileCertificazioni() {
        if (facSimileCertificazioni == null) {
            facSimileCertificazioni = new ArrayList<DocumentoAllegatoType>();
        }
        return this.facSimileCertificazioni;
    }
    
    public void setFacSimileCertificazioni(List<DocumentoAllegatoType> facSimileCertificazioni) {
        this.facSimileCertificazioni = facSimileCertificazioni;
    }

    /**
     * Recupera il valore della proprietà obbligoSchedaTecnica.
     * 
     */
    public boolean isObbligoSchedaTecnica() {
        return obbligoSchedaTecnica;
    }

    /**
     * Imposta il valore della proprietà obbligoSchedaTecnica.
     * 
     */
    public void setObbligoSchedaTecnica(boolean value) {
        this.obbligoSchedaTecnica = value;
    }

    /**
     * Recupera il valore della proprietà obbligoGaranzia.
     * 
     */
    public boolean isObbligoGaranzia() {
        return obbligoGaranzia;
    }

    /**
     * Imposta il valore della proprietà obbligoGaranzia.
     * 
     */
    public void setObbligoGaranzia(boolean value) {
        this.obbligoGaranzia = value;
    }

    /**
     * Recupera il valore della proprietà unitaMisuraDetermPrezzo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitaMisuraDetermPrezzo() {
        return unitaMisuraDetermPrezzo;
    }

    /**
     * Imposta il valore della proprietà unitaMisuraDetermPrezzo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitaMisuraDetermPrezzo(String value) {
        this.unitaMisuraDetermPrezzo = value;
    }

    /**
     * Recupera il valore della proprietà numDecimaliDetermPrezzo.
     * 
     */
    public int getNumDecimaliDetermPrezzo() {
        return numDecimaliDetermPrezzo;
    }

    /**
     * Imposta il valore della proprietà numDecimaliDetermPrezzo.
     * 
     */
    public void setNumDecimaliDetermPrezzo(int value) {
        this.numDecimaliDetermPrezzo = value;
    }

    /**
     * Recupera il valore della proprietà unitaMisuraAcquisto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitaMisuraAcquisto() {
        return unitaMisuraAcquisto;
    }

    /**
     * Imposta il valore della proprietà unitaMisuraAcquisto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitaMisuraAcquisto(String value) {
        this.unitaMisuraAcquisto = value;
    }

    /**
     * Recupera il valore della proprietà numDecimaliAcquisto.
     * 
     */
    public int getNumDecimaliAcquisto() {
        return numDecimaliAcquisto;
    }

    /**
     * Imposta il valore della proprietà numDecimaliAcquisto.
     * 
     */
    public void setNumDecimaliAcquisto(int value) {
        this.numDecimaliAcquisto = value;
    }

    /**
     * Recupera il valore della proprietà prezzoUnitarioPer.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrezzoUnitarioPer() {
        return prezzoUnitarioPer;
    }

    /**
     * Imposta il valore della proprietà prezzoUnitarioPer.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrezzoUnitarioPer(String value) {
        this.prezzoUnitarioPer = value;
    }

    /**
     * Recupera il valore della proprietà quantitaUnitaAcquisto.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantitaUnitaAcquisto() {
        return quantitaUnitaAcquisto;
    }

    /**
     * Imposta il valore della proprietà quantitaUnitaAcquisto.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantitaUnitaAcquisto(Double value) {
        this.quantitaUnitaAcquisto = value;
    }

    /**
     * Recupera il valore della proprietà quantitaMinimaUnitaAcquisto.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantitaMinimaUnitaAcquisto() {
        return quantitaMinimaUnitaAcquisto;
    }

    /**
     * Imposta il valore della proprietà quantitaMinimaUnitaAcquisto.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantitaMinimaUnitaAcquisto(Double value) {
        this.quantitaMinimaUnitaAcquisto = value;
    }

    /**
     * Recupera il valore della proprietà quantitaMassimaUnitaAcquisto.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantitaMassimaUnitaAcquisto() {
        return quantitaMassimaUnitaAcquisto;
    }

    /**
     * Imposta il valore della proprietà quantitaMassimaUnitaAcquisto.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantitaMassimaUnitaAcquisto(Double value) {
        this.quantitaMassimaUnitaAcquisto = value;
    }

    /**
     * Recupera il valore della proprietà tempoMaxConsegna.
     * 
     */
    public int getTempoMaxConsegna() {
        return tempoMaxConsegna;
    }

    /**
     * Imposta il valore della proprietà tempoMaxConsegna.
     * 
     */
    public void setTempoMaxConsegna(int value) {
        this.tempoMaxConsegna = value;
    }

    /**
     * Recupera il valore della proprietà unitaMisuraTempoConsegna.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitaMisuraTempoConsegna() {
        return unitaMisuraTempoConsegna;
    }

    /**
     * Imposta il valore della proprietà unitaMisuraTempoConsegna.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitaMisuraTempoConsegna(String value) {
        this.unitaMisuraTempoConsegna = value;
    }

    /**
     * Recupera il valore della proprietà note.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Imposta il valore della proprietà note.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Recupera il valore della proprietà prodottoDaVerificare.
     * 
     */
    public boolean isProdottoDaVerificare() {
        return prodottoDaVerificare;
    }

    /**
     * Imposta il valore della proprietà prodottoDaVerificare.
     * 
     */
    public void setProdottoDaVerificare(boolean value) {
        this.prodottoDaVerificare = value;
    }

}
