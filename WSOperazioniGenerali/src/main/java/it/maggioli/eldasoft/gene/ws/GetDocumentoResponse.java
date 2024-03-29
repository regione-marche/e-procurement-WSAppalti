
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
 *         &lt;element name="risultato" type="{http://www.eldasoft.it/WSOperazioniGenerali/}DocumentoOutType"/>
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
    "risultato"
})
@XmlRootElement(name = "GetDocumentoResponse")
public class GetDocumentoResponse
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected DocumentoOutType risultato;

    /**
     * Gets the value of the risultato property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoOutType }
     *     
     */
    public DocumentoOutType getRisultato() {
        return risultato;
    }

    /**
     * Sets the value of the risultato property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoOutType }
     *     
     */
    public void setRisultato(DocumentoOutType value) {
        this.risultato = value;
    }

}
