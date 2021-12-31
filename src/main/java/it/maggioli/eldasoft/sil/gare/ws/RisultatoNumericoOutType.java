
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per RisultatoNumericoOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RisultatoNumericoOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="valore" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
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
@XmlType(name = "RisultatoNumericoOutType", propOrder = {
    "valore",
    "errore"
})
public class RisultatoNumericoOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected Long valore;
    protected String errore;

    /**
     * Recupera il valore della proprietÓ valore.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValore() {
        return valore;
    }

    /**
     * Imposta il valore della proprietÓ valore.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValore(Long value) {
        this.valore = value;
    }

    /**
     * Recupera il valore della proprietÓ errore.
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
     * Imposta il valore della proprietÓ errore.
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
