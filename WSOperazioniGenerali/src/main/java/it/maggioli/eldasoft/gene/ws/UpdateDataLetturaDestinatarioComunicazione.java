
package it.maggioli.eldasoft.gene.ws;

import java.io.Serializable;
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
 *         &lt;element name="applicativo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idComunicazione" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idDestinatario" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "applicativo",
    "idComunicazione",
    "idDestinatario"
})
@XmlRootElement(name = "UpdateDataLetturaDestinatarioComunicazione")
public class UpdateDataLetturaDestinatarioComunicazione
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String applicativo;
    protected long idComunicazione;
    protected long idDestinatario;

    /**
     * Gets the value of the applicativo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicativo() {
        return applicativo;
    }

    /**
     * Sets the value of the applicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicativo(String value) {
        this.applicativo = value;
    }

    /**
     * Gets the value of the idComunicazione property.
     * 
     */
    public long getIdComunicazione() {
        return idComunicazione;
    }

    /**
     * Sets the value of the idComunicazione property.
     * 
     */
    public void setIdComunicazione(long value) {
        this.idComunicazione = value;
    }

    /**
     * Gets the value of the idDestinatario property.
     * 
     */
    public long getIdDestinatario() {
        return idDestinatario;
    }

    /**
     * Sets the value of the idDestinatario property.
     * 
     */
    public void setIdDestinatario(long value) {
        this.idDestinatario = value;
    }

}
