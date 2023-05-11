
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
 *         &lt;element name="testata" type="{http://www.eldasoft.it/WSOperazioniGenerali/}DettaglioComunicazioneType"/>
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
    "testata"
})
@XmlRootElement(name = "GetElencoComunicazioni")
public class GetElencoComunicazioni
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected DettaglioComunicazioneType testata;

    /**
     * Gets the value of the testata property.
     * 
     * @return
     *     possible object is
     *     {@link DettaglioComunicazioneType }
     *     
     */
    public DettaglioComunicazioneType getTestata() {
        return testata;
    }

    /**
     * Sets the value of the testata property.
     * 
     * @param value
     *     allowed object is
     *     {@link DettaglioComunicazioneType }
     *     
     */
    public void setTestata(DettaglioComunicazioneType value) {
        this.testata = value;
    }

}
