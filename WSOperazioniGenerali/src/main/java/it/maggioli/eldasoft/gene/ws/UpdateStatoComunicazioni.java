
package it.maggioli.eldasoft.gene.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comunicazioni" type="{http://www.eldasoft.it/WSOperazioniGenerali/}DettaglioComunicazioneType" maxOccurs="unbounded"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "comunicazioni",
    "stato"
})
@XmlRootElement(name = "UpdateStatoComunicazioni")
public class UpdateStatoComunicazioni
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<DettaglioComunicazioneType> comunicazioni;
    @XmlElement(required = true)
    protected String stato;

    /**
     * Gets the value of the comunicazioni property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comunicazioni property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComunicazioni().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DettaglioComunicazioneType }
     * 
     * 
     */
    public List<DettaglioComunicazioneType> getComunicazioni() {
        if (comunicazioni == null) {
            comunicazioni = new ArrayList<DettaglioComunicazioneType>();
        }
        return this.comunicazioni;
    }

    /**
     * Gets the value of the stato property.
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
     * Sets the value of the stato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStato(String value) {
        this.stato = value;
    }

}
