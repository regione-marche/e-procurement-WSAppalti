
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetArticoloOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetArticoloOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="articolo" type="{http://www.eldasoft.it/sil/WSGareAppalto/}ArticoloType" minOccurs="0"/>
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
@XmlType(name = "GetArticoloOutType", propOrder = {
    "articolo",
    "errore"
})
public class GetArticoloOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected ArticoloType articolo;
    protected String errore;

    /**
     * Recupera il valore della proprietà articolo.
     * 
     * @return
     *     possible object is
     *     {@link ArticoloType }
     *     
     */
    public ArticoloType getArticolo() {
        return articolo;
    }

    /**
     * Imposta il valore della proprietà articolo.
     * 
     * @param value
     *     allowed object is
     *     {@link ArticoloType }
     *     
     */
    public void setArticolo(ArticoloType value) {
        this.articolo = value;
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
