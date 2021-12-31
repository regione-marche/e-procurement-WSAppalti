
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetRequisitiRichiestiBandoIscrizioneOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetRequisitiRichiestiBandoIscrizioneOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requisito" type="{http://www.eldasoft.it/sil/WSGareAppalto/}RequisitoRichiestoType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "GetRequisitiRichiestiBandoIscrizioneOutType", propOrder = {
    "requisito",
    "errore"
})
public class GetRequisitiRichiestiBandoIscrizioneOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected List<RequisitoRichiestoType> requisito;
    protected String errore;

    /**
     * Gets the value of the requisito property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requisito property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequisito().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RequisitoRichiestoType }
     * 
     * 
     */
    public List<RequisitoRichiestoType> getRequisito() {
        if (requisito == null) {
            requisito = new ArrayList<RequisitoRichiestoType>();
        }
        return this.requisito;
    }

    /**
     * Recupera il valore della proprietà errore.
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
     * Imposta il valore della proprietà errore.
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
