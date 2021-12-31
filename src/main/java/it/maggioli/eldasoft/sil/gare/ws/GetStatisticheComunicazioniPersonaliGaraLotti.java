
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tokenRichiedente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codiceGara" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tokenRichiedente",
    "codiceGara"
})
@XmlRootElement(name = "GetStatisticheComunicazioniPersonaliGaraLotti")
public class GetStatisticheComunicazioniPersonaliGaraLotti
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String tokenRichiedente;
    protected String codiceGara;

    /**
     * Recupera il valore della proprietà tokenRichiedente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTokenRichiedente() {
        return tokenRichiedente;
    }

    /**
     * Imposta il valore della proprietà tokenRichiedente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTokenRichiedente(String value) {
        this.tokenRichiedente = value;
    }

    /**
     * Recupera il valore della proprietà codiceGara.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceGara() {
        return codiceGara;
    }

    /**
     * Imposta il valore della proprietà codiceGara.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceGara(String value) {
        this.codiceGara = value;
    }

}
