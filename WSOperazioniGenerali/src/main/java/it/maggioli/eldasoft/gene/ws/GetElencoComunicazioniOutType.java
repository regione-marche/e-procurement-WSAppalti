
package it.maggioli.eldasoft.gene.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetElencoComunicazioniOutType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetElencoComunicazioniOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comunicazione" type="{http://www.eldasoft.it/WSOperazioniGenerali/}DettaglioComunicazioneType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "GetElencoComunicazioniOutType", propOrder = {
    "comunicazione",
    "errore"
})
public class GetElencoComunicazioniOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(nillable = false)
    protected List<DettaglioComunicazioneType> comunicazione;
    protected String errore;

    /**
     * Gets the value of the comunicazione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comunicazione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComunicazione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DettaglioComunicazioneType }
     * 
     * 
     */
    public List<DettaglioComunicazioneType> getComunicazione() {
        if (comunicazione == null) {
            comunicazione = new ArrayList<DettaglioComunicazioneType>();
        }
        return this.comunicazione;
    }

    public void setComunicazione(List<DettaglioComunicazioneType> comunicazione) {
        this.comunicazione = comunicazione;
    }

    /**
     * Gets the value of the errore property.
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
     * Sets the value of the errore property.
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
