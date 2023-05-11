
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetAbilitazioniGaraOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetAbilitazioniGaraOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="abilitazioni" type="{http://www.eldasoft.it/sil/WSGareAppalto/}AbilitazioniGaraType" minOccurs="0"/>
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
@XmlType(name = "GetAbilitazioniGaraOutType", propOrder = {
    "abilitazioni",
    "errore"
})
public class GetAbilitazioniGaraOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected AbilitazioniGaraType abilitazioni;
    protected String errore;

    /**
     * Recupera il valore della proprietà abilitazioni.
     * 
     * @return
     *     possible object is
     *     {@link AbilitazioniGaraType }
     *     
     */
    public AbilitazioniGaraType getAbilitazioni() {
        return abilitazioni;
    }

    /**
     * Imposta il valore della proprietà abilitazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link AbilitazioniGaraType }
     *     
     */
    public void setAbilitazioni(AbilitazioniGaraType value) {
        this.abilitazioni = value;
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
