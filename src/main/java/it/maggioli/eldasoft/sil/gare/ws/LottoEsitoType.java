
package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;

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
 * <p>Classe Java per LottoEsitoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="LottoEsitoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceGara" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codiceLotto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codiceInterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importoBaseAsta" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="aggiudicataria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importoAggiudicazione" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="dataAggiudicazione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "LottoEsitoType", propOrder = {
    "codiceGara",
    "codiceLotto",
    "codiceInterno",
    "oggetto",
    "cig",
    "cup",
    "importoBaseAsta",
    "aggiudicataria",
    "importoAggiudicazione",
    "dataAggiudicazione",
    "stato",
    "esito",
    "soggettiAderenti"
})
public class LottoEsitoType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String codiceGara;
    @XmlElement(required = true)
    protected String codiceLotto;
    protected String codiceInterno;
    @XmlElement(required = true)
    protected String oggetto;
    protected String cig;
    protected String cup;
    protected double importoBaseAsta;
    protected List<String> aggiudicataria;
    protected Double importoAggiudicazione;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataAggiudicazione;
    protected String stato;
    protected String esito;
    protected List<SoggettoAderenteType> soggettiAderenti;

    /**
     * Recupera il valore della proprietà codiceGara.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Codice della gara di appartenenza del lotto    
     */
    public String getCodiceGara() {
        return codiceGara;
    }

    /**
     * Imposta il valore della proprietà codiceGara.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceGara(String value) {
        this.codiceGara = value;
    }

    /**
     * Recupera il valore della proprietà codiceLotto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Codice del lotto    
     */
    public String getCodiceLotto() {
        return codiceLotto;
    }

    /**
     * Imposta il valore della proprietà codiceLotto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceLotto(String value) {
        this.codiceLotto = value;
    }

    /**
     * Recupera il valore della proprietà codiceInterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceInterno() {
        return codiceInterno;
    }

    /**
     * Imposta il valore della proprietà codiceInterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceInterno(String value) {
        this.codiceInterno = value;
    }

    /**
     * Recupera il valore della proprietà oggetto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Oggetto del lotto    
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
     * Recupera il valore della proprietà cup.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Codice CUP    
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
     * Recupera il valore della proprietà importoBaseAsta.
     * 
     * @return importoBaseAsta   
     * Importo base d'asta
     */
    public double getImportoBaseAsta() {
        return importoBaseAsta;
    }

    /**
     * Imposta il valore della proprietà importoBaseAsta.
     * 
     */
    public void setImportoBaseAsta(double value) {
        this.importoBaseAsta = value;
    }

    /**
     * Recupera il valore della proprietà aggiudicataria.
     * 
     * @return
     *     possible object is
     *     {@link List<String> }
     * Importo base d'asta    
     */
    public List<String> getAggiudicataria() {
        return aggiudicataria;
    }

    /**
     * Imposta il valore della proprietà aggiudicataria.
     * 
     * @param value
     *     allowed object is
     *     {@link List<String> }
     *     
     */
    public void setAggiudicataria(List<String> value) {
        this.aggiudicataria = value;
    }

    /**
     * Recupera il valore della proprietà importoAggiudicazione.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     * Importo aggiudicazione    
     */
    public Double getImportoAggiudicazione() {
        return importoAggiudicazione;
    }

    /**
     * Imposta il valore della proprietà importoAggiudicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoAggiudicazione(Double value) {
        this.importoAggiudicazione = value;
    }

    /**
     * Recupera il valore della proprietà dataAggiudicazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Data aggiudicazione     
     */
    public Date getDataAggiudicazione() {
        return dataAggiudicazione;
    }

    /**
     * Imposta il valore della proprietà dataAggiudicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataAggiudicazione(Date value) {
        this.dataAggiudicazione = value;
    }

    /**
     * Recupera il valore della proprietà stato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Fase di avanzamento della gara    
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
	 * @return the soggettiAderenti
	 */
	public List<SoggettoAderenteType> getSoggettiAderenti() {
		return soggettiAderenti;
	}

	/**
	 * @param soggettiAderenti the soggettiAderenti to set
	 */
	public void setSoggettiAderenti(List<SoggettoAderenteType> soggettiAderenti) {
		this.soggettiAderenti = soggettiAderenti;
	}
        
}
