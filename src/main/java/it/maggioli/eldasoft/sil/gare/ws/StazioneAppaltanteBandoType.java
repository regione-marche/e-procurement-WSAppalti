
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per StazioneAppaltanteBandoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="StazioneAppaltanteBandoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="denominazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rup" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="puntoOrdinante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puntoIstruttore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StazioneAppaltanteBandoType", propOrder = {
    "codice",
    "denominazione",
    "rup",
    "puntoOrdinante",
    "puntoIstruttore"
})
public class StazioneAppaltanteBandoType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String denominazione;
    @XmlElement(required = true)
    protected String rup;
    protected List<String> puntoOrdinante;
    protected List<String> puntoIstruttore;

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Codice della stazione appaltante    
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
     * Recupera il valore della proprietà denominazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Denominazione stazione appaltante    
     */
    public String getDenominazione() {
        return denominazione;
    }

    /**
     * Imposta il valore della proprietà denominazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazione(String value) {
        this.denominazione = value;
    }

    /**
     * Recupera il valore della proprietà rup.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Cognome Nome del Responsabile procedimento della gara    
     */
    public String getRup() {
        return rup;
    }

    /**
     * Imposta il valore della proprietà rup.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRup(String value) {
        this.rup = value;
    }

	/**
	 * @return the puntoOrdinante
	 */
	public List<String> getPuntoOrdinante() {
		return puntoOrdinante;
	}

	/**
	 * @param puntoOrdinante the puntoOrdinante to set
	 */
	public void setPuntoOrdinante(List<String> puntoOrdinante) {
		this.puntoOrdinante = puntoOrdinante;
	}

	/**
	 * @return the puntoIstruttore
	 */
	public List<String> getPuntoIstruttore() {
		return puntoIstruttore;
	}

	/**
	 * @param puntoIstruttore the puntoIstruttore to set
	 */
	public void setPuntoIstruttore(List<String> puntoIstruttore) {
		this.puntoIstruttore = puntoIstruttore;
	}

}
