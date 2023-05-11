
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.maggioli.eldasoft.gene.xml.DateAdapter;


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
    "importoUnitario",
    "quantitaOfferta",
    "dataConsegnaRichiesta",    
    "dataConsegnaOfferta",
    "tipo",
    "note"
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
    protected Double quantitaOfferta;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataConsegnaRichiesta;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataConsegnaOfferta;
    protected String tipo;
    protected String note;

    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String value) {
        this.codice = value;
    }

    public String getVoce() {
        return voce;
    }

    public void setVoce(String value) {
        this.voce = value;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    public String getTipoClassificazione() {
        return tipoClassificazione;
    }

    public void setTipoClassificazione(String value) {
        this.tipoClassificazione = value;
    }

    public boolean isSoloSicurezza() {
        return soloSicurezza;
    }

    public void setSoloSicurezza(boolean value) {
        this.soloSicurezza = value;
    }

    public boolean isNonSoggettoRibasso() {
        return nonSoggettoRibasso;
    }

    public void setNonSoggettoRibasso(boolean value) {
        this.nonSoggettoRibasso = value;
    }

    public String getUnitaMisura() {
        return unitaMisura;
    }

    public void setUnitaMisura(String value) {
        this.unitaMisura = value;
    }

    public Double getQuantita() {
        return quantita;
    }

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

    public Double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    public void setPrezzoUnitario(Double value) {
        this.prezzoUnitario = value;
    }

    public Double getImportoUnitario() {
        return importoUnitario;
    }

    public void setImportoUnitario(Double value) {
        this.importoUnitario = value;
    }
    
	public Double getQuantitaOfferta() {
		return quantitaOfferta;
	}

	public void setQuantitaOfferta(Double quantitaOfferta) {
		this.quantitaOfferta = quantitaOfferta;
	}

	public Date getDataConsegnaRichiesta() {
		return dataConsegnaRichiesta;
	}

	public void setDataConsegnaRichiesta(Date dataConsegnaRichiesta) {
		this.dataConsegnaRichiesta = dataConsegnaRichiesta;
	}

	public Date getDataConsegnaOfferta() {
		return dataConsegnaOfferta;
	}

	public void setDataConsegnaOfferta(Date dataConsegnaOfferta) {
		this.dataConsegnaOfferta = dataConsegnaOfferta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
