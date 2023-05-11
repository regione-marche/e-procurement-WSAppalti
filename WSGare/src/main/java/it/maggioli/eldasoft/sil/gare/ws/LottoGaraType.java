
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per LottoGaraType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="LottoGaraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceGara" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codiceLotto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codiceInterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="esito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoria" type="{http://www.eldasoft.it/sil/WSGareAppalto/}CategoriaGaraType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LottoGaraType", propOrder = {
    "codiceGara",
    "codiceLotto",
    "codiceInterno",
    "oggetto",
    "cig",
    "cup",
    "importo",
    "stato",
    "esito",
    "categoria",
    "costoFisso",
    "soggettiAderenti",
    "progressivoOfferta"
})
public class LottoGaraType
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
    protected double importo;
    protected String stato;
    protected String esito;
    protected List<CategoriaGaraType> categoria;
    protected Integer costoFisso;
    protected List<SoggettoAderenteType> soggettiAderenti;
    protected String progressivoOfferta;

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
     * Recupera il valore della proprietà importo.
     * 
     * @return importo   
     * Importo base d'asta
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
     * Gets the value of the categoria property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoria property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategoria().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CategoriaGaraType }
     * 
     * Categorie solo per gare non a offerta unica
     */
    public List<CategoriaGaraType> getCategoria() {
        if (categoria == null) {
            categoria = new ArrayList<CategoriaGaraType>();
        }
        return this.categoria;
    }

    public void setCategoria(List<CategoriaGaraType> categoria) {
        this.categoria = categoria;
    }

	public Integer getCostoFisso() {
		return costoFisso;
	}

	public void setCostoFisso(Integer costoFisso) {
		this.costoFisso = costoFisso;
	}

	public List<SoggettoAderenteType> getSoggettiAderenti() {
		return soggettiAderenti;
	}

	public void setSoggettiAderenti(List<SoggettoAderenteType> soggettiAderenti) {
		this.soggettiAderenti = soggettiAderenti;
	}

	public String getProgressivoOfferta() {
		return progressivoOfferta;
	}

	public void setProgressivoOfferta(String progressivoOfferta) {
		this.progressivoOfferta = progressivoOfferta;
	}	
	
}
