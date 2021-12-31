package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EspletGaraOperatoreType", propOrder = {
    "numeroPlico",
    "codiceOperatore",
    "codiceFiscale",
    "ragioneSociale",
    "statoBusta",
    "codiceAmmissione",
    "ammissione",
    "rti",
    "codiceModAgg",
    "punteggioTecnico",
    "punteggioTecnicoRiparametrato",
    "riparametrazioneTecnica",
    "tipoRibasso",
    "importoOfferto",    
    "ribassoOfferto",
    "punteggioEconomico",
    "punteggioEconomicoRiparametrato",
    "riparametrazioneEconomica",
    "punteggioTotale",
    "graduatoria",
    "proceduraInversa",
    "esitoProceduraInversa",
    "componentiRTI",
    "lotti",
    "documenti"
})
public class EspletGaraOperatoreType implements Serializable {
	/**
	 * UID 
	 */
	private static final long serialVersionUID = -9081061560106686887L;

    @XmlElement(required = true)
    protected Integer numeroPlico;
    @XmlElement(required = true)
    protected String codiceOperatore;
    protected String codiceFiscale;    
    protected String ragioneSociale;
    protected Integer statoBusta;  				// { 0=Non presentata | 1=Chiusa | 2=Aperta }
    protected String codiceAmmissione;			
    protected String ammissione;				// descrizione del codice ammissione
    protected boolean rti;
    protected String codiceModAgg;
    protected Double punteggioTecnico;
    protected Double punteggioTecnicoRiparametrato;
    protected Double riparametrazioneTecnica;
    protected String tipoRibasso;			  	// { 1=a importi | 2=a ribassi % }
    protected Double importoOfferto;
	protected Double ribassoOfferto;
	protected Double punteggioEconomico;
	protected Double punteggioEconomicoRiparametrato;
	protected Double riparametrazioneEconomica;
	protected Double punteggioTotale;
	protected String graduatoria;
	protected boolean proceduraInversa;					// { 1 = procedura inversa | 0 = procedura non inversa }
	protected Boolean esitoProceduraInversa;			// { 1 = Idoneo | 0 = Non idoneo }
    protected List<EspletGaraComponenteRtiType> componentiRTI;    
    protected List<EspletGaraLottoType> lotti;
    protected List<EspletGaraDocumentoType> documenti;
    
    public EspletGaraOperatoreType() {
    }
    
	public EspletGaraOperatoreType(EspletGaraOperatoreType source) {
	    this.numeroPlico = source.numeroPlico;
	    this.codiceOperatore = source.codiceOperatore;
	    this.codiceFiscale = source.codiceFiscale;    
	    this.ragioneSociale = source.ragioneSociale;
	    this.statoBusta = source.statoBusta;
	    this.codiceAmmissione = source.codiceAmmissione;
	    this.ammissione = source.ammissione;
	    this.rti = source.rti;
	    this.codiceModAgg = source.codiceModAgg;
	    this.punteggioTecnico = source.punteggioTecnico;
	    this.punteggioTecnicoRiparametrato = source.punteggioEconomicoRiparametrato;
	    this.riparametrazioneTecnica = source.riparametrazioneTecnica;
	    this.tipoRibasso = source.tipoRibasso;
	    this.importoOfferto = source.importoOfferto;
	    this.ribassoOfferto = source.ribassoOfferto;
	    this.punteggioEconomico = source.punteggioEconomico;
	    this.punteggioEconomicoRiparametrato = source.punteggioEconomicoRiparametrato;
	    this.riparametrazioneEconomica = source.riparametrazioneEconomica;
	    this.punteggioTotale = source.punteggioTotale;
	    this.graduatoria = source.graduatoria;
	    this.componentiRTI = source.componentiRTI;
	    this.lotti = source.lotti;
	    this.documenti = source.documenti;
	}

	public Integer getNumeroPlico() {
		return numeroPlico;
	}
	
	public void setNumeroPlico(Integer numeroPlico) {
		this.numeroPlico = numeroPlico;
	}
	
	public String getCodiceOperatore() {
		return codiceOperatore;
	}
	
	public void setCodiceOperatore(String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	
	public Integer getStatoBusta() {
		return statoBusta;
	}
	
	public void setStatoBusta(Integer statoBusta) {
		this.statoBusta = statoBusta;
	}
	
	public String getCodiceAmmissione() {
		return codiceAmmissione;
	}

	public void setCodiceAmmissione(String codiceAmmissione) {
		this.codiceAmmissione = codiceAmmissione;
	}

	public String getAmmissione() {
		return ammissione;
	}
	
	public void setAmmissione(String ammissione) {
		this.ammissione = ammissione;
	}
	
	public boolean isRti() {
		return rti;
	}
	
	public void setRti(boolean rti) {
		this.rti = rti;
	}
	
	public String getCodiceModAgg() {
		return codiceModAgg;
	}
	
	public void setCodiceModAgg(String codiceModAgg) {
		this.codiceModAgg = codiceModAgg;
	}
	
	public Double getPunteggioTecnico() {
		return punteggioTecnico;
	}
	
	public void setPunteggioTecnico(Double punteggioTecnico) {
		this.punteggioTecnico = punteggioTecnico;
	}
	
	public Double getPunteggioTecnicoRiparametrato() {
		return punteggioTecnicoRiparametrato;
	}
	
	public void setPunteggioTecnicoRiparametrato(
			Double punteggioTecnicoRiparametrato) {
		this.punteggioTecnicoRiparametrato = punteggioTecnicoRiparametrato;
	}
	
	public Double getRiparametrazioneTecnica() {
		return riparametrazioneTecnica;
	}
	
	public void setRiparametrazioneTecnica(Double riparametrazioneTecnica) {
		this.riparametrazioneTecnica = riparametrazioneTecnica;
	}
	
	public String getTipoRibasso() {
		return tipoRibasso;
	}
	
	public void setTipoRibasso(String tipoRibasso) {
		this.tipoRibasso = tipoRibasso;
	}
	
	public Double getImportoOfferto() {
		return importoOfferto;
	}
	
	public void setImportoOfferto(Double importoOfferto) {
		this.importoOfferto = importoOfferto;
	}
	
	public Double getRibassoOfferto() {
		return ribassoOfferto;
	}
	
	public void setRibassoOfferto(Double ribassoOfferto) {
		this.ribassoOfferto = ribassoOfferto;
	}
	
	public Double getPunteggioEconomico() {
		return punteggioEconomico;
	}
	
	public void setPunteggioEconomico(Double punteggioEconomico) {
		this.punteggioEconomico = punteggioEconomico;
	}		
	
	public Double getPunteggioEconomicoRiparametrato() {
		return punteggioEconomicoRiparametrato;
	}
	
	public void setPunteggioEconomicoRiparametrato(
			Double punteggioEconomicoRiparametrato) {
		this.punteggioEconomicoRiparametrato = punteggioEconomicoRiparametrato;
	}
		
	public Double getRiparametrazioneEconomica() {
		return riparametrazioneEconomica;
	}
	
	public void setRiparametrazioneEconomica(Double riparametrazioneEconomica) {
		this.riparametrazioneEconomica = riparametrazioneEconomica;
	}
	
	public Double getPunteggioTotale() {
		return punteggioTotale;
	}	
	
	public void setPunteggioTotale(Double punteggioTotale) {
		this.punteggioTotale = punteggioTotale;
	}
	
	public String getGraduatoria() {
		return graduatoria;
	}
	
	public void setGraduatoria(String graduatoria) {
		this.graduatoria = graduatoria;
	}	
	
	public boolean isProceduraInversa() {
		return proceduraInversa;
	}

	public void setProceduraInversa(boolean proceduraInversa) {
		this.proceduraInversa = proceduraInversa;
	}

	public Boolean isEsitoProceduraInversa() {
		return esitoProceduraInversa;
	}

	public void setEsitoProceduraInversa(Boolean esitoProceduraInversa) {
		this.esitoProceduraInversa = esitoProceduraInversa;
	}

	public List<EspletGaraComponenteRtiType> getComponentiRTI() {
		return componentiRTI;
	}
	
	public void setComponentiRTI(List<EspletGaraComponenteRtiType> componentiRTI) {
		this.componentiRTI = componentiRTI;
	}
	
	public List<EspletGaraLottoType> getLotti() {
		return lotti;
	}
	
	public void setLotti(List<EspletGaraLottoType> lotti) {
		this.lotti = lotti;
	}
	
	public List<EspletGaraDocumentoType> getDocumenti() {
		return documenti;
	}
	
	public void setDocumenti(List<EspletGaraDocumentoType> documenti) {
		this.documenti = documenti;
	}

}
