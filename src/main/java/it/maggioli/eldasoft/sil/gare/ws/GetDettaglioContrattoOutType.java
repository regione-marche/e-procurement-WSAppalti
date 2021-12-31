
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetDettaglioContrattoOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetDettaglioContrattoOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contratto" type="{http://www.eldasoft.it/sil/WSGareAppalto/}ContrattoType" minOccurs="0"/>
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
@XmlType(name = "GetDettaglioContrattoOutType", propOrder = {
    "contratto",
    "errore"
})
public class GetDettaglioContrattoOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected ContrattoType contratto;
    protected String errore;

    /**
     * Recupera il valore della proprietà contratto.
     * 
     * @return
     *     possible object is
     *     {@link ContrattoType }
     *     
     */
    public ContrattoType getContratto() {
        return contratto;
    }

    /**
     * Imposta il valore della proprietà contratto.
     * 
     * @param value
     *     allowed object is
     *     {@link ContrattoType }
     *     
     */
    public void setContratto(ContrattoType value) {
        this.contratto = value;
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
