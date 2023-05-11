
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ElencoAvvisiOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ElencoAvvisiOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numAvvisi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="elencoAvvisi" type="{http://www.eldasoft.it/sil/WSGareAppalto/}AvvisoType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ElencoAvvisiOutType", propOrder = {
    "numAvvisi",
    "elencoAvvisi",
    "errore"
})
public class ElencoAvvisiOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected Integer numAvvisi;
    protected List<AvvisoType> elencoAvvisi;
    protected String errore;

    /**
     * Recupera il valore della proprietà numAvvisi.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumAvvisi() {
        return numAvvisi;
    }

    /**
     * Imposta il valore della proprietà numAvvisi.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumAvvisi(Integer value) {
        this.numAvvisi = value;
    }

    /**
     * Gets the value of the elencoAvvisi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elencoAvvisi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElencoAvvisi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AvvisoType }
     * 
     * 
     */
    public List<AvvisoType> getElencoAvvisi() {
        if (elencoAvvisi == null) {
            elencoAvvisi = new ArrayList<AvvisoType>();
        }
        return this.elencoAvvisi;
    }
    
    public void setElencoAvvisi(List<AvvisoType> elencoAvvisi) {
        this.elencoAvvisi = elencoAvvisi;
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
