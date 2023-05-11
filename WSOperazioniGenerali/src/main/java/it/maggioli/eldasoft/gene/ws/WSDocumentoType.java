
package it.maggioli.eldasoft.gene.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSDocumentoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSDocumentoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entita" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chiave1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chiave2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chiave3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chiave4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="annoProtocollo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="numeroProtocollo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="verso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSDocumentoType", propOrder = {
    "entita",
    "chiave1",
    "chiave2",
    "chiave3",
    "chiave4",
    "numeroDocumento",
    "annoProtocollo",
    "numeroProtocollo",
    "verso",
    "oggetto"
})
public class WSDocumentoType
    implements Serializable
{
    private final static long serialVersionUID = 2L;
    
    @XmlElement(required = true)
    protected String entita;
    @XmlElement(required = true)
    protected String chiave1;
    protected String chiave2;
    protected String chiave3;
    protected String chiave4;
    protected String numeroDocumento;
    protected Long annoProtocollo;
    protected String numeroProtocollo;    
    @XmlElement(required = true, type = WSDocumentoTypeVerso.class)
    protected WSDocumentoTypeVerso verso;	     
    protected String oggetto;

    /**
     * Gets the value of the entita property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntita() {
        return entita;
    }

    /**
     * Sets the value of the entita property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntita(String value) {
        this.entita = value;
    }

    /**
     * Gets the value of the chiave1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChiave1() {
        return chiave1;
    }

    /**
     * Sets the value of the chiave1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChiave1(String value) {
        this.chiave1 = value;
    }

    /**
     * Gets the value of the chiave2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChiave2() {
        return chiave2;
    }

    /**
     * Sets the value of the chiave2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChiave2(String value) {
        this.chiave2 = value;
    }

    /**
     * Gets the value of the chiave3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChiave3() {
        return chiave3;
    }

    /**
     * Sets the value of the chiave3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChiave3(String value) {
        this.chiave3 = value;
    }

    /**
     * Gets the value of the chiave4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChiave4() {
        return chiave4;
    }

    /**
     * Sets the value of the chiave4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChiave4(String value) {
        this.chiave4 = value;
    }

    /**
     * Gets the value of the numeroDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Sets the value of the numeroDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDocumento(String value) {
        this.numeroDocumento = value;
    }

    /**
     * Gets the value of the annoProtocollo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAnnoProtocollo() {
        return annoProtocollo;
    }

    /**
     * Sets the value of the annoProtocollo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAnnoProtocollo(Long value) {
        this.annoProtocollo = value;
    }

    /**
     * Gets the value of the numeroProtocollo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroProtocollo() {
        return numeroProtocollo;
    }

    /**
     * Sets the value of the numeroProtocollo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroProtocollo(String value) {
        this.numeroProtocollo = value;
    }

    /**
     * Gets the value of the verso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public WSDocumentoTypeVerso getVerso() {
        return verso;
    }

    /**
     * Sets the value of the verso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerso(WSDocumentoTypeVerso value) {
        this.verso = value;
    }

    /**
     * Gets the value of the oggetto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOggetto() {
        return oggetto;
    }

    /**
     * Sets the value of the oggetto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOggetto(String value) {
        this.oggetto = value;
    }

}
