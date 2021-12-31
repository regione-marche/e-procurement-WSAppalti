
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DettaglioAvvisoOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DettaglioAvvisoOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="avviso" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DettaglioAvvisoType" minOccurs="0"/>
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
@XmlType(name = "DettaglioAvvisoOutType", propOrder = {
    "avviso",
    "errore"
})
public class DettaglioAvvisoOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected DettaglioAvvisoType avviso;
    protected String errore;

    /**
     * Recupera il valore della proprietà avviso.
     * 
     * @return
     *     possible object is
     *     {@link DettaglioAvvisoType }
     *     
     */
    public DettaglioAvvisoType getAvviso() {
        return avviso;
    }

    /**
     * Imposta il valore della proprietà avviso.
     * 
     * @param value
     *     allowed object is
     *     {@link DettaglioAvvisoType }
     *     
     */
    public void setAvviso(DettaglioAvvisoType value) {
        this.avviso = value;
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
