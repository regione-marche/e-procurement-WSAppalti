
package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.sil.aste.ws.DettaglioAstaType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per LottoDettaglioGaraType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="LottoDettaglioGaraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceLotto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codiceInterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documento" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="invito" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requisitoRichiesto" type="{http://www.eldasoft.it/sil/WSGareAppalto/}RequisitoRichiestoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documentoBustaPreQualifica" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentazioneRichiestaType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documentoBustaAmministrativa" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentazioneRichiestaType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documentoBustaTecnica" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentazioneRichiestaType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documentoBustaEconomica" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentazioneRichiestaType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comunicazione" type="{http://www.eldasoft.it/sil/WSGareAppalto/}ComunicazioneType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LottoDettaglioGaraType", propOrder = {
    "codiceLotto",
    "codiceInterno",
    "oggetto",
    "documento",
    "invito",
    "requisitoRichiesto",
    "documentoBustaPreQualifica",
    "documentoBustaAmministrativa",
    "documentoBustaTecnica",
    "documentoBustaEconomica",
    "listaCriteriValutazione",
    "comunicazione",
    "asta",
    "costoFisso",
    "soggettiAderenti"
})
public class LottoDettaglioGaraType
    implements Serializable
{
    private final static long serialVersionUID = 2L;
    
    @XmlElement(required = true)
    protected String codiceLotto;
    protected String codiceInterno;
    @XmlElement(required = true)
    protected String oggetto;
    protected List<DocumentoAllegatoType> documento;
    protected List<DocumentoAllegatoType> invito;
    protected List<RequisitoRichiestoType> requisitoRichiesto;
    protected List<DocumentazioneRichiestaType> documentoBustaPreQualifica;
    protected List<DocumentazioneRichiestaType> documentoBustaAmministrativa;
    protected List<DocumentazioneRichiestaType> documentoBustaTecnica;
    protected List<DocumentazioneRichiestaType> documentoBustaEconomica;
    protected List<CriterioValutazioneOffertaType> listaCriteriValutazione;
    protected List<ComunicazioneType> comunicazione;
    protected DettaglioAstaType asta;
    protected Integer costoFisso;
    protected List<SoggettoAderenteType> soggettiAderenti;
    
	/**
     * Recupera il valore della proprietà codiceLotto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * Codice del lotto     
     */
    public String getCodiceLotto() {
        return codiceLotto;
    }

    /**
     * Imposta il valore della proprietà codiceLotto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceLotto(String value) {
        this.codiceLotto = value;
    }

    /**
     * Recupera il valore della proprietà codiceInterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceInterno() {
        return codiceInterno;
    }

    /**
     * Imposta il valore della proprietà codiceInterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceInterno(String value) {
        this.codiceInterno = value;
    }

    /**
     * Recupera il valore della proprietà oggetto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOggetto() {
        return oggetto;
    }

    /**
     * Imposta il valore della proprietà oggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOggetto(String value) {
        this.oggetto = value;
    }

    /**
     * Gets the value of the documento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoAllegatoType }
     * 
     * Documentazione allegata al lotto
     */
    public List<DocumentoAllegatoType> getDocumento() {
        if (documento == null) {
            documento = new ArrayList<DocumentoAllegatoType>();
        }
        return this.documento;
    }
    
    public void setDocumento(List<DocumentoAllegatoType> documento) {
        this.documento = documento;
    }

    /**
     * Gets the value of the invito property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invito property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvito().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoAllegatoType }
     * 
     * 
     */
    public List<DocumentoAllegatoType> getInvito() {
        if (invito == null) {
            invito = new ArrayList<DocumentoAllegatoType>();
        }
        return this.invito;
    }
    
    public void setInvito(List<DocumentoAllegatoType> invito) {
        this.invito = invito;
    }

    /**
     * Gets the value of the requisitoRichiesto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requisitoRichiesto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequisitoRichiesto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RequisitoRichiestoType }
     * 
     * 
     */
    public List<RequisitoRichiestoType> getRequisitoRichiesto() {
        if (requisitoRichiesto == null) {
            requisitoRichiesto = new ArrayList<RequisitoRichiestoType>();
        }
        return this.requisitoRichiesto;
    }
    
    public void setRequisitoRichiesto(List<RequisitoRichiestoType> requisitoRichiesto) {
        this.requisitoRichiesto = requisitoRichiesto;
    }

    /**
     * Gets the value of the documentoBustaPreQualifica property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentoBustaPreQualifica property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentoBustaPreQualifica().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentazioneRichiestaType }
     * 
     * Documentazione della busta di prequalifica richiesta nel lotto
     */
    public List<DocumentazioneRichiestaType> getDocumentoBustaPreQualifica() {
        if (documentoBustaPreQualifica == null) {
            documentoBustaPreQualifica = new ArrayList<DocumentazioneRichiestaType>();
        }
        return this.documentoBustaPreQualifica;
    }
    
    public void setDocumentoBustaPreQualifica(List<DocumentazioneRichiestaType> documentoBustaPreQualifica) {
        this.documentoBustaPreQualifica = documentoBustaPreQualifica;
    }

    /**
     * Gets the value of the documentoBustaAmministrativa property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentoBustaAmministrativa property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentoBustaAmministrativa().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentazioneRichiestaType }
     * 
     * Documentazione della busta amministrativa richiesta nel lotto
     */
    public List<DocumentazioneRichiestaType> getDocumentoBustaAmministrativa() {
        if (documentoBustaAmministrativa == null) {
            documentoBustaAmministrativa = new ArrayList<DocumentazioneRichiestaType>();
        }
        return this.documentoBustaAmministrativa;
    }
    
    public void setDocumentoBustaAmministrativa(List<DocumentazioneRichiestaType> documentoBustaAmministrativa) {
        this.documentoBustaAmministrativa = documentoBustaAmministrativa;
    }

    /**
     * Gets the value of the documentoBustaTecnica property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentoBustaTecnica property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentoBustaTecnica().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentazioneRichiestaType }
     * 
     * Documentazione della busta tecnica richiesta nel lotto
     */
    public List<DocumentazioneRichiestaType> getDocumentoBustaTecnica() {
        if (documentoBustaTecnica == null) {
            documentoBustaTecnica = new ArrayList<DocumentazioneRichiestaType>();
        }
        return this.documentoBustaTecnica;
    }
    
    public void setDocumentoBustaTecnica(List<DocumentazioneRichiestaType> documentoBustaTecnica) {
        this.documentoBustaTecnica = documentoBustaTecnica;
    }

    /**
     * Gets the value of the documentoBustaEconomica property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentoBustaEconomica property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentoBustaEconomica().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentazioneRichiestaType }
     * 
     *  Documentazione della busta economica richiesta nel lotto
     */
    public List<DocumentazioneRichiestaType> getDocumentoBustaEconomica() {
        if (documentoBustaEconomica == null) {
            documentoBustaEconomica = new ArrayList<DocumentazioneRichiestaType>();
        }
        return this.documentoBustaEconomica;
    }
    
    public void setDocumentoBustaEconomica(List<DocumentazioneRichiestaType> documentoBustaEconomica) {
        this.documentoBustaEconomica = documentoBustaEconomica;
    }
    
    /**
	 * @return the listaCriteriValutazione
	 */
	public List<CriterioValutazioneOffertaType> getListaCriteriValutazione() {
		return listaCriteriValutazione;
	}

	/**
	 * @param listaCriteriValutazione the listaCriteriValutazione to set
	 */
	public void setListaCriteriValutazione(
			List<CriterioValutazioneOffertaType> listaCriteriValutazione) {
		this.listaCriteriValutazione = listaCriteriValutazione;
	}

	/**
     * Gets the value of the comunicazione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comunicazione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComunicazione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComunicazioneType }
     * 
     * 
     */
    public List<ComunicazioneType> getComunicazione() {
        if (comunicazione == null) {
            comunicazione = new ArrayList<ComunicazioneType>();
        }
        return this.comunicazione;
    }
    
    public void setComunicazione(List<ComunicazioneType> comunicazione) {
        this.comunicazione = comunicazione;
    }

    /**
	 * @return the asta
	 */
	public DettaglioAstaType getAsta() {
		return asta;
	}

	/**
	 * @param asta the asta to set
	 */
	public void setAsta(DettaglioAstaType asta) {
		this.asta = asta;
	}

	/**
	 * @return the costoFisso
	 */
	public Integer getCostoFisso() {
		return costoFisso;
	}

	/**
	 * @param costoFisso the costoFisso to set
	 */
	public void setCostoFisso(Integer costoFisso) {
		this.costoFisso = costoFisso;
	}

	/**
	 * @return the soggettiAderenti
	 */
	public List<SoggettoAderenteType> getSoggettiAderenti() {
		return soggettiAderenti;
	}

	/**
	 * @param soggettiAderenti the soggettiAderenti to set
	 */
	public void setSoggettiAderenti(List<SoggettoAderenteType> soggettiAderenti) {
		this.soggettiAderenti = soggettiAderenti;
	}
	
}
