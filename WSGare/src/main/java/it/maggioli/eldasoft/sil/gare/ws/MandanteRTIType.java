
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per MandanteRTIType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="MandanteRTIType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ragioneSociale" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipologia" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partitaIVA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quotaPartecipazione" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MandanteRTIType", propOrder = {
    "ragioneSociale",
    "tipologia",
    "ambitoTerritoriale",
    "nazione",
    "codiceFiscale",
    "partitaIVA",
    "idFiscaleEstero",
    "quotaPartecipazione"
})
public class MandanteRTIType
    implements Serializable
{
    private final static long serialVersionUID = 2L;
    
    @XmlElement(required = true)
    protected String ragioneSociale;
    protected Integer tipologia;
    protected String ambitoTerritoriale;
    protected String nazione;
    protected String codiceFiscale;
    protected String partitaIVA;
    protected String idFiscaleEstero;
    protected Double quotaPartecipazione;

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String value) {
        this.ragioneSociale = value;
    }

    public Integer getTipologia() {
        return tipologia;
    }

    public void setTipologia(Integer value) {
        this.tipologia = value;
    }
    
    public String getAmbitoTerritoriale() {
		return ambitoTerritoriale;
	}

	public void setAmbitoTerritoriale(String ambitoTerritoriale) {
		this.ambitoTerritoriale = ambitoTerritoriale;
	}

	public String getNazione() {
        return nazione;
    }

    public void setNazione(String value) {
        this.nazione = value;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    public String getPartitaIVA() {
        return partitaIVA;
    }

    public void setPartitaIVA(String value) {
        this.partitaIVA = value;
    }

	public String getIdFiscaleEstero() {
		return idFiscaleEstero;
	}

	public void setIdFiscaleEstero(String idFiscaleEstero) {
		this.idFiscaleEstero = idFiscaleEstero;
	}

    public Double getQuotaPartecipazione() {
        return quotaPartecipazione;
    }

    public void setQuotaPartecipazione(Double value) {
        this.quotaPartecipazione = value;
    }

}
