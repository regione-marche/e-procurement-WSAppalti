
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DettaglioBandoIscrizioneOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DettaglioBandoIscrizioneOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bandoIscrizione" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DettaglioBandoIscrizioneType" minOccurs="0"/>
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
@XmlType(name = "DettaglioBandoIscrizioneOutType", propOrder = {
    "bandoIscrizione",
    "errore"
})
public class DettaglioBandoIscrizioneOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected DettaglioBandoIscrizioneType bandoIscrizione;
    protected String errore;

    /**
     * Recupera il valore della proprietà bandoIscrizione.
     * 
     * @return
     *     possible object is
     *     {@link DettaglioBandoIscrizioneType }
     *     
     */
    public DettaglioBandoIscrizioneType getBandoIscrizione() {
        return bandoIscrizione;
    }

    /**
     * Imposta il valore della proprietà bandoIscrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link DettaglioBandoIscrizioneType }
     *     
     */
    public void setBandoIscrizione(DettaglioBandoIscrizioneType value) {
        this.bandoIscrizione = value;
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
