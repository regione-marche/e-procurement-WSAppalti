
package it.maggioli.eldasoft.gene.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WSAllegatoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSAllegatoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entita" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chiave1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chiave2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chiave3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chiave4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSAllegatoType", propOrder = {
    "entita",
    "chiave1",
    "chiave2",
    "chiave3",
    "chiave4",
    "idDocumento"
})
public class WSAllegatoType
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
    protected long idDocumento;

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

}
