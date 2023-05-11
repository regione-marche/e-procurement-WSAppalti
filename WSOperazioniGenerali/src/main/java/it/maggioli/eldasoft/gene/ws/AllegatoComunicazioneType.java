
package it.maggioli.eldasoft.gene.ws;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;


/**
 * <p>Java class for AllegatoComunicazioneType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AllegatoComunicazioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeFile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="file" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllegatoComunicazioneType", propOrder = {
    "id",
    "tipo",
    "nomeFile",
    "descrizione",
    "file",
    "uuid",
    "modificato",
    "dimensione",
    "firmacheck",
    "firmacheckts"
})
public class AllegatoComunicazioneType
    implements Serializable
{
	private final static long serialVersionUID = 2L;
    protected Long id;
    protected String tipo;
    @XmlElement(required = true)
    protected String nomeFile;
    protected String descrizione;
    @XmlElement(required = true)
    protected byte[] file;
//    @XmlJavaTypeAdapter(DataHandlerAdapter.class)
//    @XmlMimeType("application/octet-stream")
//    protected DataHandler file;
    protected String uuid;
    protected int modificato;
    protected long dimensione;
    protected String firmacheck;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date firmacheckts;

	/**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the nomeFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeFile() {
        return nomeFile;
    }

    /**
     * Sets the value of the nomeFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeFile(String value) {
        this.nomeFile = value;
    }

    /**
     * Gets the value of the descrizione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets the value of the descrizione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Gets the value of the file property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFile() {
        return file;
    }
//    public DataHandler getFile() {
//        return file;
//    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFile(byte[] value) {
        this.file = value;
    }
//    public void setFile(DataHandler value) {
//        this.file = value;
//    }

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the modificato
	 */
	public int getModificato() {
		return modificato;
	}

	/**
	 * @param modificato the modificato to set
	 */
	public void setModificato(int modificato) {
		this.modificato = modificato;
	}

	/**
	 * @return the dimensione
	 */
	public long getDimensione() {
		return dimensione;
	}

	/**
	 * @param dimensione the dimensione to set
	 */
	public void setDimensione(long dimensione) {
		this.dimensione = dimensione;
	}

	/**
	 * @return the firmacheck
	 */
	public String getFirmacheck() {
		return firmacheck;
	}

	/**
	 * @param firmacheck the firmacheck to set
	 */
	public void setFirmacheck(String firmacheck) {
		this.firmacheck = firmacheck;
	}

	/**
	 * @return the firmacheckts
	 */
	public Date getFirmacheckts() {
		return firmacheckts;
	}

	/**
	 * @param firmacheckts the firmacheckts to set
	 */
	public void setFirmacheckts(Date firmacheckts) {
		this.firmacheckts = firmacheckts;
	}
	
}
