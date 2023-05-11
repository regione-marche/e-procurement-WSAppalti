
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SearchArticoliOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SearchArticoliOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numElementiTotali" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numElementiTotaliFiltrati" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="elencoArticoli" type="{http://www.eldasoft.it/sil/WSGareAppalto/}ArticoloType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="errore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchArticoliOutType", propOrder = {
    "numElementiTotali",
    "numElementiTotaliFiltrati",
    "elencoArticoli",
    "errore"
})
public class SearchArticoliOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected Integer numElementiTotali;
    protected Integer numElementiTotaliFiltrati;
    protected List<ArticoloType> elencoArticoli;
    protected String errore;

    /**
     * Recupera il valore della proprietà numElementiTotali.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumElementiTotali() {
        return numElementiTotali;
    }

    /**
     * Imposta il valore della proprietà numElementiTotali.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumElementiTotali(Integer value) {
        this.numElementiTotali = value;
    }

    /**
     * Recupera il valore della proprietà numElementiTotaliFiltrati.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumElementiTotaliFiltrati() {
        return numElementiTotaliFiltrati;
    }

    /**
     * Imposta il valore della proprietà numElementiTotaliFiltrati.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumElementiTotaliFiltrati(Integer value) {
        this.numElementiTotaliFiltrati = value;
    }

    /**
     * Gets the value of the elencoArticoli property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elencoArticoli property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElencoArticoli().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArticoloType }
     * 
     * 
     */
    public List<ArticoloType> getElencoArticoli() {
        if (elencoArticoli == null) {
            elencoArticoli = new ArrayList<ArticoloType>();
        }
        return this.elencoArticoli;
    }
    
    public void setElencoArticoli(List<ArticoloType> elencoArticoli) {
        this.elencoArticoli = elencoArticoli;
    }

    /**
     * Recupera il valore della proprietà errore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrore() {
        return errore;
    }

    /**
     * Imposta il valore della proprietà errore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrore(String value) {
        this.errore = value;
    }

}
