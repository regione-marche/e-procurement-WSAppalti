
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per CategoriaBandoIscrizioneType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="CategoriaBandoIscrizioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoAppalto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="titolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceLivello1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceLivello2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceLivello3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceLivello4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="livello" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="foglia" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoriaBandoIscrizioneType", propOrder = {
    "codice",
    "descrizione",
    "tipoAppalto",
    "titolo",
    "codiceLivello1",
    "codiceLivello2",
    "codiceLivello3",
    "codiceLivello4",
    "livello",
    "foglia"
})
@XmlSeeAlso({
    CategoriaCatalogoType.class
})
public class CategoriaBandoIscrizioneType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String descrizione;
    @XmlElement(required = true)
    protected String tipoAppalto;
    protected String titolo;
    protected String codiceLivello1;
    protected String codiceLivello2;
    protected String codiceLivello3;
    protected String codiceLivello4;
    protected int livello;
    protected boolean foglia;

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprietà tipoAppalto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Descrizione del Tipo di appalto: Lavori, Forniture, Servizi    
     */
    public String getTipoAppalto() {
        return tipoAppalto;
    }

    /**
     * Imposta il valore della proprietà tipoAppalto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAppalto(String value) {
        this.tipoAppalto = value;
    }

    /**
     * Recupera il valore della proprietà titolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Imposta il valore della proprietà titolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitolo(String value) {
        this.titolo = value;
    }

    /**
     * Recupera il valore della proprietà codiceLivello1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceLivello1() {
        return codiceLivello1;
    }

    /**
     * Imposta il valore della proprietà codiceLivello1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceLivello1(String value) {
        this.codiceLivello1 = value;
    }

    /**
     * Recupera il valore della proprietà codiceLivello2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceLivello2() {
        return codiceLivello2;
    }

    /**
     * Imposta il valore della proprietà codiceLivello2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceLivello2(String value) {
        this.codiceLivello2 = value;
    }

    /**
     * Recupera il valore della proprietà codiceLivello3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceLivello3() {
        return codiceLivello3;
    }

    /**
     * Imposta il valore della proprietà codiceLivello3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceLivello3(String value) {
        this.codiceLivello3 = value;
    }

    /**
     * Recupera il valore della proprietà codiceLivello4.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceLivello4() {
        return codiceLivello4;
    }

    /**
     * Imposta il valore della proprietà codiceLivello4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceLivello4(String value) {
        this.codiceLivello4 = value;
    }

    /**
     * Recupera il valore della proprietà livello.
     * 
     */
    public int getLivello() {
        return livello;
    }

    /**
     * Imposta il valore della proprietà livello.
     * 
     */
    public void setLivello(int value) {
        this.livello = value;
    }

    /**
     * Recupera il valore della proprietà foglia.
     * 
     */
    public boolean isFoglia() {
        return foglia;
    }

    /**
     * Imposta il valore della proprietà foglia.
     * 
     */
    public void setFoglia(boolean value) {
        this.foglia = value;
    }

}
