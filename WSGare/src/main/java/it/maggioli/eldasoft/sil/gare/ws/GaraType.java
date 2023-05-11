
package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;
import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * <p>Classe Java per GaraType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GaraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stazioneAppaltante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipologia">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="busteDistinte" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tipoAppalto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoProcedura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoAggiudicazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="modalitaAggiudicazione" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="importo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="importoNonSoggettoRibasso" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="importoSicurezza" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="importoOneriProgettazione" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="proceduraTelematica" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="offertaTelematica" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tipoRibasso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ammessaOffertaInAumento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="offertaComprensivaSicurezza" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="oneriSoggettiRibasso" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isOffertaPerPermuta" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isOffertaPerCanoneAssistenza" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="numGiorniValiditaOfferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataPubblicazione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataTermine" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="oraTermine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataTerminePresentazioneDomanda" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="oraTerminePresentazioneDomanda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataTerminePresentazioneOfferta" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="oraTerminePresentazioneOfferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEsito" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="esito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iterGara" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GaraType", propOrder = {
    "stazioneAppaltante",
    "codice",
    "oggetto",
    "cig",
    "cup",
    "tipologia",
    "busteDistinte",
    "tipoAppalto",
    "tipoProcedura",
    "tipoAggiudicazione",
    "modalitaAggiudicazione",
    "importo",
    "importoNonSoggettoRibasso",
    "importoSicurezza",
    "richiestaManodopera",
    "modalitaManodopera",
    "importoOneriProgettazione",
    "proceduraTelematica",
    "offertaTelematica",
    "tipoRibasso",
    "ammessaOffertaInAumento",
    "offertaComprensivaSicurezza",
    "oneriSoggettiRibasso",
    "offertaPerPermuta",
    "offertaPerCanoneAssistenza",
    "numGiorniValiditaOfferta",
    "stato",
    "dataPubblicazione",
    "dataTermine",
    "oraTermine",
    "dataTerminePresentazioneDomanda",
    "oraTerminePresentazioneDomanda",
    "dataTerminePresentazioneOfferta",
    "oraTerminePresentazioneOfferta",
    "dataEsito",
    "esito",
    "iterGara",
    "astaElettronica",
    "astaAttiva",
    "dataInvitoAsta",
    "dataInizioAsta",
    "invitataAsta",
    "faseGara",
    "dataUltimoAggiornamento",
    "costoFisso",
    "rettifichePresentazioneDomanda",
    "rettifichePresentazioneOfferta",
    "garaRilancio",
    "proceduraInversa",
    "accordoQuadro",
    "SACodice",
    "SADescrizione",
    "SARup",
    "garaPrivatistica",
    "numDecimaliRibasso",
    "noBustaAmministrativa",
    "tipoOffertaTelematica",
    "visualizzaEspletamento",
    "nascondiImportoBaseGara",
    "nascondiValoriEspletamento",
    "isGreen",
    "isRecycle",
    "isPnrr",
    "orderCriteria",
    "dataTermineRichiestaChiarimenti",
    "dataTermineRispostaOperatori"
})
public class GaraType
    implements Serializable
{
    private final static long serialVersionUID = 2L;
    
    @XmlElement(required = true)
    protected String stazioneAppaltante;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String oggetto;
    protected String cig;
    protected String cup;
    protected int tipologia;
    protected Boolean busteDistinte;
    @XmlElement(required = true)
    protected String tipoAppalto;
    @XmlElement(required = true)
    protected String tipoProcedura;
    protected String tipoAggiudicazione;
    protected Integer modalitaAggiudicazione;
    protected Double importo;
    protected Double importoNonSoggettoRibasso;
    protected Double importoSicurezza;
    protected String richiestaManodopera;
    protected Integer modalitaManodopera;
    protected Double importoOneriProgettazione;
    protected boolean proceduraTelematica;
    protected boolean offertaTelematica;
    protected Integer tipoRibasso;
    protected boolean ammessaOffertaInAumento;
    protected boolean offertaComprensivaSicurezza;
    protected boolean oneriSoggettiRibasso;
    protected boolean offertaPerPermuta;
    protected boolean offertaPerCanoneAssistenza;
    protected String numGiorniValiditaOfferta;
    protected String stato;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataPubblicazione;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataTermine;
    protected String oraTermine;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataTerminePresentazioneDomanda;
    protected String oraTerminePresentazioneDomanda;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataTerminePresentazioneOfferta;
    protected String oraTerminePresentazioneOfferta;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataEsito;
    protected String esito;
    @XmlElement(required = true)
    protected String iterGara;
    protected boolean astaElettronica;    
	protected boolean astaAttiva;
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date dataInvitoAsta;
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date dataInizioAsta;
	protected boolean invitataAsta;
	protected Integer faseGara;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataUltimoAggiornamento;
    protected Integer costoFisso;
    protected List<RettificaGaraType> rettifichePresentazioneDomanda;
    protected List<RettificaGaraType> rettifichePresentazioneOfferta;
    protected boolean garaRilancio;
    protected boolean proceduraInversa;
    protected boolean accordoQuadro;
    protected String SACodice;				// ottimizzazione per getDettaglioGara
    protected String SADescrizione;			// ottimizzazione per getDettaglioGara
    protected String SARup;					// ottimizzazione per getDettaglioGara
    protected Integer garaPrivatistica;
    protected Long numDecimaliRibasso;    
    protected boolean noBustaAmministrativa;
    protected Integer tipoOffertaTelematica;
    protected boolean visualizzaEspletamento;
    protected Integer nascondiImportoBaseGara;
    protected Integer nascondiValoriEspletamento;
    protected String isGreen;
    protected String isRecycle;
    protected String isPnrr;
    protected OrderCriteria orderCriteria;
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataTermineRichiestaChiarimenti;
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataTermineRispostaOperatori;


    public String getStazioneAppaltante() {
        return stazioneAppaltante;
    }

    public void setStazioneAppaltante(String value) {
        this.stazioneAppaltante = value;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String value) {
        this.codice = value;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String value) {
        this.oggetto = value;
    }

    public String getCig() {
        return cig;
    }

    public void setCig(String value) {
        this.cig = value;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String value) {
        this.cup = value;
    }

    /**
     * Recupera il valore della propriet� tipologia.
     * 
     * @return tipologia   
     * Tipologia di gara: 1 - Gara a piu' lotti con offerte distinte 
	 * 					  2 - Gara a lotto unico 
     * 					  3 - Gara a piu' lotti con offerta unica
     */
    public int getTipologia() {
        return tipologia;
    }

    public void setTipologia(int value) {
        this.tipologia = value;
    }

    /**
     * Recupera il valore della propriet� busteDistinte.
     * 
     * @return busteDistinte   
     * Specificato solo per gare a lotti con offerta unica, 
 	 * indica se si inviano buste tecniche ed economiche distinte
     * per lotto
     */
    public Boolean isBusteDistinte() {
        return busteDistinte;
    }

    public void setBusteDistinte(Boolean value) {
        this.busteDistinte = value;
    }

    public String getTipoAppalto() {
        return tipoAppalto;
    }

    public void setTipoAppalto(String value) {
        this.tipoAppalto = value;
    }

    public String getTipoProcedura() {
        return tipoProcedura;
    }

    public void setTipoProcedura(String value) {
        this.tipoProcedura = value;
    }

    public String getTipoAggiudicazione() {
        return tipoAggiudicazione;
    }

    public void setTipoAggiudicazione(String value) {
        this.tipoAggiudicazione = value;
    }

    public Integer getModalitaAggiudicazione() {
        return modalitaAggiudicazione;
    }

    public void setModalitaAggiudicazione(Integer value) {
        this.modalitaAggiudicazione = value;
    }

    public Double getImporto() {
        return importo;
    }

    public void setImporto(Double value) {
        this.importo = value;
    }

    public Double getImportoNonSoggettoRibasso() {
        return importoNonSoggettoRibasso;
    }

    public void setImportoNonSoggettoRibasso(Double value) {
        this.importoNonSoggettoRibasso = value;
    }
    
    public Double getImportoSicurezza() {
        return importoSicurezza;
    }

    public void setImportoSicurezza(Double value) {
        this.importoSicurezza = value;
    }

	public String getRichiestaManodopera() {
		return richiestaManodopera;
	}

	public void setRichiestaManodopera(String richiestaManodopera) {
		this.richiestaManodopera = richiestaManodopera;
	}

    public Double getImportoOneriProgettazione() {
        return importoOneriProgettazione;
    }

    public void setImportoOneriProgettazione(Double value) {
        this.importoOneriProgettazione = value;
    }

    public boolean isProceduraTelematica() {
        return proceduraTelematica;
    }

    public void setProceduraTelematica(boolean value) {
        this.proceduraTelematica = value;
    }

    public boolean isOffertaTelematica() {
        return offertaTelematica;
    }

    public void setOffertaTelematica(boolean value) {
        this.offertaTelematica = value;
    }

    public Integer getTipoRibasso() {
        return tipoRibasso;
    }
    
    public void setTipoRibasso(Integer value) {
        this.tipoRibasso = value;
    }

    public boolean isAmmessaOffertaInAumento() {
        return ammessaOffertaInAumento;
    }

    public void setAmmessaOffertaInAumento(boolean value) {
        this.ammessaOffertaInAumento = value;
    }

    public boolean isOffertaComprensivaSicurezza() {
        return offertaComprensivaSicurezza;
    }

    public void setOffertaComprensivaSicurezza(boolean value) {
        this.offertaComprensivaSicurezza = value;
    }

    public boolean isOneriSoggettiRibasso() {
        return oneriSoggettiRibasso;
    }

    public void setOneriSoggettiRibasso(boolean value) {
        this.oneriSoggettiRibasso = value;
    }

    public String getNumGiorniValiditaOfferta() {
        return numGiorniValiditaOfferta;
    }

    public void setNumGiorniValiditaOfferta(String value) {
        this.numGiorniValiditaOfferta = value;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String value) {
        this.stato = value;
    }

    public Date getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(Date value) {
        this.dataPubblicazione = value;
    }

    public Date getDataTermine() {
        return dataTermine;
    }

    public void setDataTermine(Date value) {
        this.dataTermine = value;
    }

    public String getOraTermine() {
        return oraTermine;
    }

    public void setOraTermine(String value) {
        this.oraTermine = value;
    }

    public Date getDataTerminePresentazioneDomanda() {
        return dataTerminePresentazioneDomanda;
    }

    public void setDataTerminePresentazioneDomanda(Date value) {
        this.dataTerminePresentazioneDomanda = value;
    }

    public String getOraTerminePresentazioneDomanda() {
        return oraTerminePresentazioneDomanda;
    }

    public void setOraTerminePresentazioneDomanda(String value) {
        this.oraTerminePresentazioneDomanda = value;
    }

    public Date getDataTerminePresentazioneOfferta() {
        return dataTerminePresentazioneOfferta;
    }

    public void setDataTerminePresentazioneOfferta(Date value) {
        this.dataTerminePresentazioneOfferta = value;
    }

    public String getOraTerminePresentazioneOfferta() {
        return oraTerminePresentazioneOfferta;
    }

    public void setOraTerminePresentazioneOfferta(String value) {
        this.oraTerminePresentazioneOfferta = value;
    }

    public Date getDataEsito() {
        return dataEsito;
    }

    public void setDataEsito(Date value) {
        this.dataEsito = value;
    }

    public String getEsito() {
        return esito;
    }

    public void setEsito(String value) {
        this.esito = value;
    }

    public String getIterGara() {
        return iterGara;
    }

    public void setIterGara(String value) {
        this.iterGara = value;
    }

	public boolean isOffertaPerPermuta() {
		return offertaPerPermuta;
	}

	public void setOffertaPerPermuta(boolean offertaPerPermuta) {
		this.offertaPerPermuta = offertaPerPermuta;
	}

	public boolean isOffertaPerCanoneAssistenza() {
		return offertaPerCanoneAssistenza;
	}

	public void setOffertaPerCanoneAssistenza(boolean offertaPerCanoneAssistenza) {
		this.offertaPerCanoneAssistenza = offertaPerCanoneAssistenza;
	}

	public boolean isAstaElettronica() {
		return astaElettronica;
	}

	public void setAstaElettronica(boolean astaElettronica) {
		this.astaElettronica = astaElettronica;
	}

	public boolean isAstaAttiva() {
		return astaAttiva;
	}

	public void setAstaAttiva(boolean astaAttiva) {
		this.astaAttiva = astaAttiva;
	}

	public Date getDataInvitoAsta() {
		return dataInvitoAsta;
	}

	public void setDataInvitoAsta(Date dataInvitoAsta) {
		this.dataInvitoAsta = dataInvitoAsta;
	}

	public Date getDataInizioAsta() {
		return dataInizioAsta;
	}

	public void setDataInizioAsta(Date dataInizioAsta) {
		this.dataInizioAsta = dataInizioAsta;
	}

	public boolean isInvitataAsta() {
		return invitataAsta;
	}

	public void setInvitataAsta(boolean invitataAsta) {
		this.invitataAsta = invitataAsta;
	}

	public Integer getFaseGara() {
		return faseGara;
	}

	public void setFaseGara(Integer faseGara) {
		this.faseGara = faseGara;
	}

	public Date getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Date dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public Integer isCostoFisso() {
		return costoFisso;
	}

	public void setCostoFisso(Integer costoFisso) {
		this.costoFisso = costoFisso;
	}

	public List<RettificaGaraType> getRettifichePresentazioneDomanda() {
		return rettifichePresentazioneDomanda;
	}

	public void setRettifichePresentazioneDomanda(
			List<RettificaGaraType> rettifichePresentazioneDomanda) {
		this.rettifichePresentazioneDomanda = rettifichePresentazioneDomanda;
	}

	public List<RettificaGaraType> getRettifichePresentazioneOfferta() {
		return rettifichePresentazioneOfferta;
	}

	public void setRettifichePresentazioneOfferta(
			List<RettificaGaraType> rettifichePresentazioneOfferta) {
		this.rettifichePresentazioneOfferta = rettifichePresentazioneOfferta;
	}

	public boolean isGaraRilancio() {
		return garaRilancio;
	}

	public void setGaraRilancio(boolean garaRilancio) {
		this.garaRilancio = garaRilancio;
	}

	public Integer getModalitaManodopera() {
		return modalitaManodopera;
	}

	public void setModalitaManodopera(Integer modalitaManodopera) {
		this.modalitaManodopera = modalitaManodopera;
	}

	public boolean isProceduraInversa() {
		return proceduraInversa;
	}

	public void setProceduraInversa(boolean proceduraInversa) {
		this.proceduraInversa = proceduraInversa;
	}

	public boolean isAccordoQuadro() {
		return accordoQuadro;
	}

	public void setAccordoQuadro(boolean accordoQuadro) {
		this.accordoQuadro = accordoQuadro;
	}

	public String getSACodice() {
		return SACodice;
	}

	public void setSACodice(String sACodice) {
		SACodice = sACodice;
	}

	public String getSADescrizione() {
		return SADescrizione;
	}

	public void setSADescrizione(String sADescrizione) {
		SADescrizione = sADescrizione;
	}

	public String getSARup() {
		return SARup;
	}

	public void setSARup(String sARup) {
		SARup = sARup;
	}

	public Integer getGaraPrivatistica() {
		return garaPrivatistica;
	}

	public void setGaraPrivatistica(Integer garaPrivatistica) {
		this.garaPrivatistica = garaPrivatistica;
	}

	public Long getNumDecimaliRibasso() {
		return numDecimaliRibasso;
	}

	public void setNumDecimaliRibasso(Long numDecimaliRibasso) {
		this.numDecimaliRibasso = numDecimaliRibasso;
	}

	public boolean isNoBustaAmministrativa() {
		return noBustaAmministrativa;
	}

	public void setNoBustaAmministrativa(boolean noBustaAmministrativa) {
		this.noBustaAmministrativa = noBustaAmministrativa;
	}

	public Integer getTipoOffertaTelematica() {
		return tipoOffertaTelematica;
	}

	public void setTipoOffertaTelematica(Integer tipoOffertaTelematica) {
		this.tipoOffertaTelematica = tipoOffertaTelematica;
	}

	public boolean isVisualizzaEspletamento() {
		return visualizzaEspletamento;
	}

	public void setVisualizzaEspletamento(boolean visualizzaEspletamento) {
		this.visualizzaEspletamento = visualizzaEspletamento;
	}
	
	public Integer setNascondiImportoBaseGara() {
		return nascondiImportoBaseGara;
	}

	public void setNascondiImportoBaseGara(Integer nascondiImportoBaseGara) {
		this.nascondiImportoBaseGara = nascondiImportoBaseGara;
	}
	
	public Integer setNascondiValoriEspletamento() {
		return nascondiValoriEspletamento;
	}

	public void setNascondiValoriEspletamento(Integer nascondiValoriEspletamento) {
		this.nascondiValoriEspletamento = nascondiValoriEspletamento;
	}

    public String getIsGreen() {
        return isGreen;
    }

    public void setIsGreen(String isGreen) {
        this.isGreen = isGreen;
    }

    public String getIsRecycle() {
        return isRecycle;
    }

    public void setIsRecycle(String isRecycle) {
        this.isRecycle = isRecycle;
    }

    public String getIsPnrr() {
		return isPnrr;
	}

	public void setIsPnrr(String isPnrr) {
		this.isPnrr = isPnrr;
	}

	public OrderCriteria getOrderCriteria() {
        return orderCriteria;
    }

    public void setOrderCriteria(OrderCriteria orderCriteria) {
        this.orderCriteria = orderCriteria;
    }
    public Date getDataTermineRichiestaChiarimenti() {
        return dataTermineRichiestaChiarimenti;
    }

    public void setDataTermineRichiestaChiarimenti(Date dataTermineRichiestaChiarimenti) {
        this.dataTermineRichiestaChiarimenti = dataTermineRichiestaChiarimenti;
    }

    public Date getDataTermineRispostaOperatori() {
        return dataTermineRispostaOperatori;
    }

    public void setDataTermineRispostaOperatori(Date dataTermineRispostaOperatori) {
        this.dataTermineRispostaOperatori = dataTermineRispostaOperatori;
    }

}


