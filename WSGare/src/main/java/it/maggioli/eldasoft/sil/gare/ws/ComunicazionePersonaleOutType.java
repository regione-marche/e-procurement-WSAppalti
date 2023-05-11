
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ComunicazionePersonaleOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ComunicazionePersonaleOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comunicazione" type="{http://www.eldasoft.it/sil/WSGareAppalto/}ComunicazioneType" minOccurs="0"/>
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
@XmlType(name = "ComunicazionePersonaleOutType", propOrder = {
    "comunicazione",
    "errore"
})
public class ComunicazionePersonaleOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected ComunicazioneType comunicazione;
    protected String errore;

    /**
     * Recupera il valore della propriet� comunicazione.
     * 
     * @return
     *     possible object is
     *     {@link ComunicazioneType }
     *     
     */
    public ComunicazioneType getComunicazione() {
        return comunicazione;
    }

    /**
     * Imposta il valore della propriet� comunicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link ComunicazioneType }
     *     
     */
    public void setComunicazione(ComunicazioneType value) {
        this.comunicazione = value;
    }

    /**
     * Recupera il valore della propriet� errore.
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
     * Imposta il valore della propriet� errore.
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
