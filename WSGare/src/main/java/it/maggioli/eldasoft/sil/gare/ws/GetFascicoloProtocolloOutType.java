
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetFascicoloProtocolloOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetFascicoloProtocolloOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fascicolo" type="{http://www.eldasoft.it/sil/WSGareAppalto/}FascicoloProtocolloType" minOccurs="0"/>
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
@XmlType(name = "GetFascicoloProtocolloOutType", propOrder = {
    "fascicolo",
    "errore"
})
public class GetFascicoloProtocolloOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected FascicoloProtocolloType fascicolo;
    protected String errore;

    /**
     * Recupera il valore della proprietà fascicolo.
     * 
     * @return
     *     possible object is
     *     {@link FascicoloProtocolloType }
     *     
     */
    public FascicoloProtocolloType getFascicolo() {
        return fascicolo;
    }

    /**
     * Imposta il valore della proprietà fascicolo.
     * 
     * @param value
     *     allowed object is
     *     {@link FascicoloProtocolloType }
     *     
     */
    public void setFascicolo(FascicoloProtocolloType value) {
        this.fascicolo = value;
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
