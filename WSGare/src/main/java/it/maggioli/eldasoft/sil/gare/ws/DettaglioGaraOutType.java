
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DettaglioGaraOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DettaglioGaraOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gara" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DettaglioGaraType" minOccurs="0"/>
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
@XmlType(name = "DettaglioGaraOutType", propOrder = {
    "gara",
    "errore"
})
public class DettaglioGaraOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected DettaglioGaraType gara;
    protected String errore;

    /**
     * Recupera il valore della proprietà gara.
     * 
     * @return
     *     possible object is
     *     {@link DettaglioGaraType }
     *     
     */
    public DettaglioGaraType getGara() {
        return gara;
    }

    /**
     * Imposta il valore della proprietà gara.
     * 
     * @param value
     *     allowed object is
     *     {@link DettaglioGaraType }
     *     
     */
    public void setGara(DettaglioGaraType value) {
        this.gara = value;
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
