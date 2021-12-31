
package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per ChiarimentoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ChiarimentoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="domanda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="risposta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChiarimentoType", propOrder = {
    "data",
    "domanda",
    "risposta"
})
public class ChiarimentoType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date data;
    @XmlElement(required = true)
    protected String domanda;
    @XmlElement(required = true)
    protected String risposta;

    /**
     * Recupera il valore della proprietà data.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getData() {
        return data;
    }

    /**
     * Imposta il valore della proprietà data.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setData(Date value) {
        this.data = value;
    }

    /**
     * Recupera il valore della proprietà domanda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomanda() {
        return domanda;
    }

    /**
     * Imposta il valore della proprietà domanda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomanda(String value) {
        this.domanda = value;
    }

    /**
     * Recupera il valore della proprietà risposta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRisposta() {
        return risposta;
    }

    /**
     * Imposta il valore della proprietà risposta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRisposta(String value) {
        this.risposta = value;
    }

}
