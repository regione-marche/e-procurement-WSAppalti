
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per AttributoAggiuntivoOffertaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AttributoAggiuntivoOffertaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minExclusive value="1"/>
 *               &lt;maxExclusive value="7"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="formato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valoriAmmessi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="obbligatorio" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttributoAggiuntivoOffertaType", propOrder = {
    "codice",
    "descrizione",
    "tipo",
    "formato",
    "valoriAmmessi",
    "obbligatorio"
})
public class AttributoAggiuntivoOffertaType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String descrizione;
    protected int tipo;
    protected String formato;
    protected String valoriAmmessi;
    protected boolean obbligatorio;

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
     * Recupera il valore della proprietà tipo.
     * 
     * @return tipo   
     *
	 * 1=Data
     * 2=Importo
     * 3=Tabellato
     * 4=Note
     * 5=Numero
     * 6=Flag si/no
     * 7=Stringa
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietà tipo.
     * 
     * @param tipo   
     *
     */
    public void setTipo(int value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprietà formato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormato() {
        return formato;
    }

    /**
     * Imposta il valore della proprietà formato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormato(String value) {
        this.formato = value;
    }

    /**
     * Recupera il valore della proprietà valoriAmmessi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
	 * Anche se non esteticamente bello, si inserisce un dato XML
     * nel formato dei dati comuni eldasoft in analogia ai dati tabellati.
     * 
     * In tal modo si evita di esporre una operazione generica di estrazione
     * tabellati, e si evita di replicare in questo servizio la struttura
     * per contenere tali dati.
     */
    public String getValoriAmmessi() {
        return valoriAmmessi;
    }

    /**
     * Imposta il valore della proprietà valoriAmmessi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
	 * Anche se non esteticamente bello, si inserisce un dato XML
     * nel formato dei dati comuni eldasoft in analogia ai dati tabellati.
     * 
     * In tal modo si evita di esporre una operazione generica di estrazione
     * tabellati, e si evita di replicare in questo servizio la struttura
     * per contenere tali dati.
     */
    public void setValoriAmmessi(String value) {
        this.valoriAmmessi = value;
    }

    /**
     * Recupera il valore della proprietà obbligatorio.
     * 
     */
    public boolean isObbligatorio() {
        return obbligatorio;
    }

    /**
     * Imposta il valore della proprietà obbligatorio.
     * 
     */
    public void setObbligatorio(boolean value) {
        this.obbligatorio = value;
    }

}
