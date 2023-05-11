
package it.maggioli.eldasoft.gene.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;


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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="numeroProtocollo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataProtocollo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documento" type="{http://www.eldasoft.it/WSOperazioniGenerali/}WSDocumentoType" minOccurs="0"/>
 *         &lt;element name="allegato" type="{http://www.eldasoft.it/WSOperazioniGenerali/}WSAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
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
    "id",
    "numeroProtocollo",
    "dataProtocollo",
    "stato",
    "documento",
    "allegato"
})
@XmlRootElement(name = "ProtocollaComunicazione")
public class ProtocollaComunicazione
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String applicativo;
    protected long id;
    @XmlElement(required = true)
    protected String numeroProtocollo;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date dataProtocollo;
    protected String stato;
    protected WSDocumentoType documento;
    protected List<WSAllegatoType> allegato;

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
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
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
     * Gets the value of the dataProtocollo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDataProtocollo() {
        return dataProtocollo;
    }

    /**
     * Sets the value of the dataProtocollo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataProtocollo(Date value) {
        this.dataProtocollo = value;
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

    /**
     * Gets the value of the documento property.
     * 
     * @return
     *     possible object is
     *     {@link WSDocumentoType }
     *     
     */
    public WSDocumentoType getDocumento() {
        return documento;
    }

    /**
     * Sets the value of the documento property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSDocumentoType }
     *     
     */
    public void setDocumento(WSDocumentoType value) {
        this.documento = value;
    }

    /**
     * Gets the value of the allegato property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allegato property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllegato().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WSAllegatoType }
     * 
     * 
     */
    public List<WSAllegatoType> getAllegato() {
        if (allegato == null) {
            allegato = new ArrayList<WSAllegatoType>();
        }
        return this.allegato;
    }

}
