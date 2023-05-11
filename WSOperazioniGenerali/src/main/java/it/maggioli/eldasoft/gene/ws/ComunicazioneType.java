
package it.maggioli.eldasoft.gene.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComunicazioneType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComunicazioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dettaglioComunicazione" type="{http://www.eldasoft.it/WSOperazioniGenerali/}DettaglioComunicazioneType"/>
 *         &lt;element name="allegato" type="{http://www.eldasoft.it/WSOperazioniGenerali/}AllegatoComunicazioneType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComunicazioneType", propOrder = {
    "dettaglioComunicazione",
    "allegato"
})
public class ComunicazioneType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected DettaglioComunicazioneType dettaglioComunicazione;
    @XmlElement(nillable = false)
    protected List<AllegatoComunicazioneType> allegato;

    /**
     * Gets the value of the dettaglioComunicazione property.
     * 
     * @return
     *     possible object is
     *     {@link DettaglioComunicazioneType }
     *     
     */
    public DettaglioComunicazioneType getDettaglioComunicazione() {
        return dettaglioComunicazione;
    }

    /**
     * Sets the value of the dettaglioComunicazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link DettaglioComunicazioneType }
     *     
     */
    public void setDettaglioComunicazione(DettaglioComunicazioneType value) {
        this.dettaglioComunicazione = value;
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
     * {@link AllegatoComunicazioneType }
     * 
     * 
     */
    public List<AllegatoComunicazioneType> getAllegato() {
        if (allegato == null) {
            allegato = new ArrayList<AllegatoComunicazioneType>();
        }
        return this.allegato;
    }

    public void setAllegato(List<AllegatoComunicazioneType> allegato) {
        this.allegato = allegato;
    }

}
