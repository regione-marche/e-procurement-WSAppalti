
package it.maggioli.eldasoft.gene.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetComunicazioneOutType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetComunicazioneOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comunicazione" type="{http://www.eldasoft.it/WSOperazioniGenerali/}ComunicazioneType" minOccurs="0"/>
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
@XmlType(name = "GetComunicazioneOutType", propOrder = {
    "comunicazione",
    "errore"
})
public class GetComunicazioneOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected ComunicazioneType comunicazione;
    protected String errore;

    /**
     * Gets the value of the comunicazione property.
     * 
     * @return
     *     possible object is
     *     {@link ComunicazioneType }
     *     
     */
    public ComunicazioneType getComunicazione() {
        return comunicazione;
    }

    /**
     * Sets the value of the comunicazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComunicazioneType }
     *     
     */
    public void setComunicazione(ComunicazioneType value) {
        this.comunicazione = value;
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
