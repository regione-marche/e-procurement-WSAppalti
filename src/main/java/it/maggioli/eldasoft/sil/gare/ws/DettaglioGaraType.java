
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DettaglioGaraType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DettaglioGaraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stazioneAppaltante" type="{http://www.eldasoft.it/sil/WSGareAppalto/}StazioneAppaltanteBandoType"/>
 *         &lt;element name="datiGeneraliGara" type="{http://www.eldasoft.it/sil/WSGareAppalto/}GaraType"/>
 *         &lt;element name="documento" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="invito" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requisitoRichiesto" type="{http://www.eldasoft.it/sil/WSGareAppalto/}RequisitoRichiestoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documentoBustaPreQualifica" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentazioneRichiestaType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documentoBustaAmministrativa" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentazioneRichiestaType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documentoBustaTecnica" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentazioneRichiestaType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documentoBustaEconomica" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentazioneRichiestaType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comunicazione" type="{http://www.eldasoft.it/sil/WSGareAppalto/}ComunicazioneType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="chiarimento" type="{http://www.eldasoft.it/sil/WSGareAppalto/}ChiarimentoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lotto" type="{http://www.eldasoft.it/sil/WSGareAppalto/}LottoDettaglioGaraType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DettaglioGaraType", propOrder = {
    "stazioneAppaltante",
    "datiGeneraliGara",
    "documento",
    "invito",
    "requisitoRichiesto",
    "documentoBustaPreQualifica",
    "documentoBustaAmministrativa",
    "documentoBustaTecnica",
    "documentoBustaEconomica",
    "listaCriteriValutazione",
    "comunicazione",
    "chiarimento",
    "lotto",
    "soggettiAderenti"
})
public class DettaglioGaraType implements Serializable
{
    private final static long serialVersionUID = 2L;
    
    @XmlElement(required = true)
    protected StazioneAppaltanteBandoType stazioneAppaltante;
    @XmlElement(required = true)
    protected GaraType datiGeneraliGara;
    protected List<DocumentoAllegatoType> documento;
    protected List<DocumentoAllegatoType> invito;
    protected List<RequisitoRichiestoType> requisitoRichiesto;
    protected List<DocumentazioneRichiestaType> documentoBustaPreQualifica;
    protected List<DocumentazioneRichiestaType> documentoBustaAmministrativa;
    protected List<DocumentazioneRichiestaType> documentoBustaTecnica;
    protected List<DocumentazioneRichiestaType> documentoBustaEconomica;
    protected List<CriterioValutazioneOffertaType> listaCriteriValutazione;
    protected List<ComunicazioneType> comunicazione;
    protected List<ChiarimentoType> chiarimento;
    protected List<LottoDettaglioGaraType> lotto;
    protected List<SoggettoAderenteType> soggettiAderenti;

    /**
     * Recupera il valore della proprietà stazioneAppaltante.
     * 
     * @return
     *     possible object is
     *     {@link StazioneAppaltanteBandoType }
     *     
     */
    public StazioneAppaltanteBandoType getStazioneAppaltante() {
        return stazioneAppaltante;
    }

    /**
     * Imposta il valore della proprietà stazioneAppaltante.
     * 
     * @param value
     *     allowed object is
     *     {@link StazioneAppaltanteBandoType }
     *     
     */
    public void setStazioneAppaltante(StazioneAppaltanteBandoType value) {
        this.stazioneAppaltante = value;
    }

    /**
     * Recupera il valore della proprietà datiGeneraliGara.
     * 
     * @return
     *     possible object is
     *     {@link GaraType }
     *     
     */
    public GaraType getDatiGeneraliGara() {
        return datiGeneraliGara;
    }

    /**
     * Imposta il valore della proprietà datiGeneraliGara.
     * 
     * @param value
     *     allowed object is
     *     {@link GaraType }
     *     
     */
    public void setDatiGeneraliGara(GaraType value) {
        this.datiGeneraliGara = value;
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * Gets the value of the chiarimento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chiarimento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChiarimento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChiarimentoType }
     * 
     * 
     */
    public List<ChiarimentoType> getChiarimento() {
        if (chiarimento == null) {
            chiarimento = new ArrayList<ChiarimentoType>();
        }
        return this.chiarimento;
    }
    
    public void setChiarimento(List<ChiarimentoType> chiarimento) {
        this.chiarimento = chiarimento;
    }

    /**
     * Gets the value of the lotto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lotto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLotto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LottoDettaglioGaraType }
     * 
     * 
     */
    public List<LottoDettaglioGaraType> getLotto() {
        if (lotto == null) {
            lotto = new ArrayList<LottoDettaglioGaraType>();
        }
        return this.lotto;
    }
    
    public void setLotto(List<LottoDettaglioGaraType> lotto) {
        this.lotto = lotto;
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
