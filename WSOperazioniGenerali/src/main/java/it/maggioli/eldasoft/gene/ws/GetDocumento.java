
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
 *         &lt;element name="idProgramma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tokenRichiedente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "idProgramma",
    "idDocumento",
    "tokenRichiedente"
})
@XmlRootElement(name = "GetDocumento")
public class GetDocumento
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String idProgramma;
    protected long idDocumento;
    protected String tokenRichiedente;

    /**
     * Gets the value of the idProgramma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdProgramma() {
        return idProgramma;
    }

    /**
     * Sets the value of the idProgramma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdProgramma(String value) {
        this.idProgramma = value;
    }

    /**
     * Gets the value of the idDocumento property.
     * 
     */
    public long getIdDocumento() {
        return idDocumento;
    }

    /**
     * Sets the value of the idDocumento property.
     * 
     */
    public void setIdDocumento(long value) {
        this.idDocumento = value;
    }

    /**
     * Gets the value of the tokenRichiedente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTokenRichiedente() {
        return tokenRichiedente;
    }

    /**
     * Sets the value of the tokenRichiedente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTokenRichiedente(String value) {
        this.tokenRichiedente = value;
    }

}
