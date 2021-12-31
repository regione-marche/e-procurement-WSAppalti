
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per VoceDettaglioOffertaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="VoceDettaglioOffertaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="voce" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoClassificazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="soloSicurezza" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="nonSoggettoRibasso" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="unitaMisura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quantita" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="prezzoUnitario" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="importoUnitario" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

/**
 * 30/07/2019
 * NB: se viene modificata questa classe è necessario rigenerare anche
 *     "WSAsteClient" perchè la classe viene ereditata in WSAsteClient  
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoceDettaglioOffertaType", propOrder = {
    "id",
    "codice",
    "voce",
    "descrizione",
    "tipoClassificazione",
    "soloSicurezza",
    "nonSoggettoRibasso",
    "unitaMisura",
    "quantita",
    "prezzoUnitarioBaseGara",
    "peso",
    "ribassoPercentuale",
    "ribassoPesato",
    "prezzoUnitario",
    "importoUnitario"
})
public class VoceDettaglioOffertaType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected int id;
    protected String codice;
    protected String voce;
    protected String descrizione;
    protected String tipoClassificazione;
    protected boolean soloSicurezza;
    protected boolean nonSoggettoRibasso;
    protected String unitaMisura;
    protected Double quantita;
    protected Double prezzoUnitarioBaseGara;
    protected Double peso;
    protected Double ribassoPercentuale;
    protected Double ribassoPesato;
    protected Double prezzoUnitario;
    protected Double importoUnitario;

    /**
     * Recupera il valore della proprietà id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

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
     * Recupera il valore della proprietà voce.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoce() {
        return voce;
    }

    /**
     * Imposta il valore della proprietà voce.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoce(String value) {
        this.voce = value;
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
     * Recupera il valore della proprietà tipoClassificazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoClassificazione() {
        return tipoClassificazione;
    }

    /**
     * Imposta il valore della proprietà tipoClassificazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoClassificazione(String value) {
        this.tipoClassificazione = value;
    }

    /**
     * Recupera il valore della proprietà soloSicurezza.
     * 
     */
    public boolean isSoloSicurezza() {
        return soloSicurezza;
    }

    /**
     * Imposta il valore della proprietà soloSicurezza.
     * 
     */
    public void setSoloSicurezza(boolean value) {
        this.soloSicurezza = value;
    }

    /**
     * Recupera il valore della proprietà nonSoggettoRibasso.
     * 
     */
    public boolean isNonSoggettoRibasso() {
        return nonSoggettoRibasso;
    }

    /**
     * Imposta il valore della proprietà nonSoggettoRibasso.
     * 
     */
    public void setNonSoggettoRibasso(boolean value) {
        this.nonSoggettoRibasso = value;
    }

    /**
     * Recupera il valore della proprietà unitaMisura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitaMisura() {
        return unitaMisura;
    }

    /**
     * Imposta il valore della proprietà unitaMisura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitaMisura(String value) {
        this.unitaMisura = value;
    }

    /**
     * Recupera il valore della proprietà quantita.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantita() {
        return quantita;
    }

    /**
     * Imposta il valore della proprietà quantita.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantita(Double value) {
        this.quantita = value;
    }
    
    public Double getPrezzoUnitarioBaseGara() {
		return prezzoUnitarioBaseGara;
	}

	public void setPrezzoUnitarioBaseGara(Double prezzoUnitarioBaseGara) {
		this.prezzoUnitarioBaseGara = prezzoUnitarioBaseGara;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getRibassoPercentuale() {
		return ribassoPercentuale;
	}

	public void setRibassoPercentuale(Double ribassoPercentuale) {
		this.ribassoPercentuale = ribassoPercentuale;
	}

	public Double getRibassoPesato() {
		return ribassoPesato;
	}

	public void setRibassoPesato(Double ribassoPesato) {
		this.ribassoPesato = ribassoPesato;
	}

    /**
     * Recupera il valore della proprietà prezzoUnitario.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    /**
     * Imposta il valore della proprietà prezzoUnitario.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPrezzoUnitario(Double value) {
        this.prezzoUnitario = value;
    }

	/**
     * Recupera il valore della proprietà importoUnitario.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoUnitario() {
        return importoUnitario;
    }

    /**
     * Imposta il valore della proprietà importoUnitario.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoUnitario(Double value) {
        this.importoUnitario = value;
    }
    
}
