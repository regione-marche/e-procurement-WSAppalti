
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TipoPartecipazioneType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TipoPartecipazioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rti" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="denominazioneRti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoPartecipazioneType", propOrder = {
    "rti",
    "denominazioneRti"
})
public class TipoPartecipazioneType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected boolean rti;
    protected String denominazioneRti;

    /**
     * Recupera il valore della proprietà rti.
     * 
     */
    public boolean isRti() {
        return rti;
    }

    /**
     * Imposta il valore della proprietà rti.
     * 
     */
    public void setRti(boolean value) {
        this.rti = value;
    }

    /**
     * Recupera il valore della proprietà denominazioneRti.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazioneRti() {
        return denominazioneRti;
    }

    /**
     * Imposta il valore della proprietà denominazioneRti.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazioneRti(String value) {
        this.denominazioneRti = value;
    }

}
