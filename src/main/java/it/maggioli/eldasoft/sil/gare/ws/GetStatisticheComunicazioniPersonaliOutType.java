
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetStatisticheComunicazioniPersonaliOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetStatisticheComunicazioniPersonaliOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statistiche" type="{http://www.eldasoft.it/sil/WSGareAppalto/}StatisticheComunicazioniPersonaliType" minOccurs="0"/>
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
@XmlType(name = "GetStatisticheComunicazioniPersonaliOutType", propOrder = {
    "statistiche",
    "errore"
})
public class GetStatisticheComunicazioniPersonaliOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected StatisticheComunicazioniPersonaliType statistiche;
    protected String errore;

    /**
     * Recupera il valore della proprietà statistiche.
     * 
     * @return
     *     possible object is
     *     {@link StatisticheComunicazioniPersonaliType }
     *     
     */
    public StatisticheComunicazioniPersonaliType getStatistiche() {
        return statistiche;
    }

    /**
     * Imposta il valore della proprietà statistiche.
     * 
     * @param value
     *     allowed object is
     *     {@link StatisticheComunicazioniPersonaliType }
     *     
     */
    public void setStatistiche(StatisticheComunicazioniPersonaliType value) {
        this.statistiche = value;
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
