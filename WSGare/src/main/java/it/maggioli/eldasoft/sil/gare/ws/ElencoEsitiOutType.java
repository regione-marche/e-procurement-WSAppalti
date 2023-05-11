
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ElencoEsitiOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ElencoEsitiOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numEsiti" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="elencoEsiti" type="{http://www.eldasoft.it/sil/WSGareAppalto/}EsitoType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ElencoEsitiOutType", propOrder = {
    "numEsiti",
    "elencoEsiti",
    "errore"
})
public class ElencoEsitiOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected Integer numEsiti;
    protected List<EsitoType> elencoEsiti;
    protected String errore;

    /**
     * Recupera il valore della proprietà numEsiti.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumEsiti() {
        return numEsiti;
    }

    /**
     * Imposta il valore della proprietà numEsiti.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumEsiti(Integer value) {
        this.numEsiti = value;
    }

    /**
     * Gets the value of the elencoEsiti property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elencoEsiti property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElencoEsiti().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EsitoType }
     * 
     * 
     */
    public List<EsitoType> getElencoEsiti() {
        if (elencoEsiti == null) {
            elencoEsiti = new ArrayList<EsitoType>();
        }
        return this.elencoEsiti;
    }
    
    public void setElencoEsiti(List<EsitoType> elencoEsiti) {
        this.elencoEsiti = elencoEsiti;
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
