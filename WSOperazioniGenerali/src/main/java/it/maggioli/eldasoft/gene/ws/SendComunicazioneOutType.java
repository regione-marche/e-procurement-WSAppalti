
package it.maggioli.eldasoft.gene.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SendComunicazioneOutType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SendComunicazioneOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idComunicazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
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
@XmlType(name = "SendComunicazioneOutType", propOrder = {
    "idComunicazione",
    "errore"
})
public class SendComunicazioneOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected Long idComunicazione;
    protected String errore;

    /**
     * Gets the value of the idComunicazione property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdComunicazione() {
        return idComunicazione;
    }

    /**
     * Sets the value of the idComunicazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdComunicazione(Long value) {
        this.idComunicazione = value;
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
