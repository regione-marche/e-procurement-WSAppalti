
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetDettaglioStazioneAppaltanteOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetDettaglioStazioneAppaltanteOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stazioneAppaltante" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DettaglioStazioneAppaltanteType" minOccurs="0"/>
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
@XmlType(name = "GetDettaglioStazioneAppaltanteOutType", propOrder = {
    "stazioneAppaltante",
    "errore"
})
public class GetDettaglioStazioneAppaltanteOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected DettaglioStazioneAppaltanteType stazioneAppaltante;
    protected String errore;

    /**
     * Recupera il valore della proprietà stazioneAppaltante.
     * 
     * @return
     *     possible object is
     *     {@link DettaglioStazioneAppaltanteType }
     *     
     */
    public DettaglioStazioneAppaltanteType getStazioneAppaltante() {
        return stazioneAppaltante;
    }

    /**
     * Imposta il valore della proprietà stazioneAppaltante.
     * 
     * @param value
     *     allowed object is
     *     {@link DettaglioStazioneAppaltanteType }
     *     
     */
    public void setStazioneAppaltante(DettaglioStazioneAppaltanteType value) {
        this.stazioneAppaltante = value;
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
