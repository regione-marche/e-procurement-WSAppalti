package it.maggioli.eldasoft.sil.aste.ws;

import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DettaglioAstaType", propOrder = {
	"codice",
	"codiceLotto",
	"dataOraApertura",
	"dataOraChiusura",
	"fase",
	"durataMinima",		
	"durataMassima",
	"tempoBase",
	"tipoClassifica",
	"elettronica",
	"attiva",
	"termineAsta",
	"tipoOfferta",
	"scartoRilancioMinimo",
	"scartoRilancioMassimo",
	"dataUltimoRilancio",	
	"importoUltimoRilancio",
	"ribassoUltimoRilancio",
	"fasi",
	"numDecimaliRibasso"
})
public class DettaglioAstaType implements Serializable {
	
	private static final long serialVersionUID = -4645169543182471445L;
	protected String codice;
	protected String codiceLotto;
	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataOraApertura;         	// dd/MM/yyyy hh:mm:ss
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataOraChiusura;         	// dd/MM/yyyy hh:mm:ss
	protected Integer fase;						// 1..N fasi per ogni gara
	protected Long durataMinima;				// minuti		
	protected Long durataMassima;				// minuti
	protected Long tempoBase;					// minuti
	protected Integer tipoClassifica;			// 0, 1, 2, 3
	protected boolean elettronica;			
	protected boolean attiva;					
	protected Integer termineAsta;				// 0, 1, 2, 3	
	protected Integer tipoOfferta;				// 1=ribasso 2=importo	
	protected Double scartoRilancioMinimo;
	protected Double scartoRilancioMassimo;	
	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataUltimoRilancio;			// dd/MM/yyyy hh:mm:ss
	protected Double importoUltimoRilancio;
	protected Double ribassoUltimoRilancio;		
	protected List<DettaglioFaseAstaType> fasi;
	protected Long numDecimaliRibasso;
	
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCodiceLotto() {
		return codiceLotto;
	}

	public void setCodiceLotto(String codiceLotto) {
		this.codiceLotto = codiceLotto;
	}

	public Date getDataOraApertura() {
		return dataOraApertura;
	}
	
	public void setDataOraApertura(Date dataOraApertura) {
		this.dataOraApertura = dataOraApertura;
	}
	
	public Date getDataOraChiusura() {
		return dataOraChiusura;
	}

	public void setDataOraChiusura(Date dataOraChiusura) {
		this.dataOraChiusura = dataOraChiusura;
	}	
	
	public Integer getFase() {
		return fase;
	}
	
	public void setFase(Integer fase) {
		this.fase = fase;
	}

	/**
	 * Indica la durata minima di un'asta in minuti.
	 * La durata minima è il tempo minimo di chiusura dell'asta in assenza di rilanci.
	 *  
	 * @return the durataMinima
	 */
	public Long getDurataMinima() {
		return durataMinima;
	}
	
	public void setDurataMinima(Long durataMinima) {
		this.durataMinima = durataMinima;
	}
	
	/**
	 * Indica la durata massima di un'asta in minuti.
	 * La durata massima è il tempo massimo entro il quale si possono effettuare 
	 * dei rilanci.
	 * 
	 * @return the durataMassima
	 */
	public Long getDurataMassima() {
		return durataMassima;
	}
	
	public void setDurataMassima(Long durataMassima) {
		this.durataMassima = durataMassima;
	}
	
	/**
 	 * Indica di quanto posticipare il termine di un'asta dall'ultimo rilancio.
	 * Il tempo base fissa il tempo di chiusura di un'asta dall'ultimo rilancio,
	 * ma sempre non oltre la durataMassima.
	 * L'intervallo di validità di un'aste è quindi  
	 * 
	 *   [0, min(durataMassima, max(durataMinima, ultimoRilancio+tempoBase))]
	 *  
	 * @return the tempoBase
	 */
	public Long getTempoBase() {
		return tempoBase;
	}
	
	public void setTempoBase(Long tempoBase) {
		this.tempoBase = tempoBase;
	}
	
	/**
	 * La classifica durante la fase di asta può essere espressa in tre maniere :
	 *  - Solo la propria classifica 
	 *  - La propria classifica e il miglior valore offerto rispetto alla propria offerta iniziale
	 *  - Classifica generale in forma anonima e i valori offerti dai rispettivi concorrenti
	 * 
	 * @return the tipoClassifica
	 */
	public Integer getTipoClassifica() {
		return tipoClassifica;
	}
	
	public void setTipoClassifica(Integer tipoClassifica) {
		this.tipoClassifica = tipoClassifica;
	}

	public boolean getElettronica() {
		return elettronica;
	}

	public void setElettronica(boolean elettronica) {
		this.elettronica = elettronica;
	}

	public boolean getAttiva() {
		return attiva;
	}

	public void setAttiva(boolean attiva) {
		this.attiva = attiva;
	}

	/**
	 * 0 Nessun termine asta
	 * 1 Termine asta (in caso di assenza di rilanci)
	 * 2 Termine asta (seguente ultimo rilancio)
	 * 3 Termine asta (durata massima)
	 * 
	 * @return the termineAsta
	 */
	public Integer getTermineAsta() {
		return termineAsta;
	}
	
	public void setTermineAsta(Integer termineAsta) {
		this.termineAsta = termineAsta;
	}
			
	public Integer getTipoOfferta() {
		return tipoOfferta;
	}

	public void setTipoOfferta(Integer tipoOfferta) {
		this.tipoOfferta = tipoOfferta;
	}

	public Double getScartoRilancioMinimo() {
		return scartoRilancioMinimo;
	}
	
	public void setScartoRilancioMinimo(Double scartoRilancioMinimo) {
		this.scartoRilancioMinimo = scartoRilancioMinimo;
	}
	
	public Double getScartoRilancioMassimo() {
		return scartoRilancioMassimo;
	}
	
	public void setScartoRilancioMassimo(Double scartoRilancioMassimo) {
		this.scartoRilancioMassimo = scartoRilancioMassimo;
	}

	public Date getDataUltimoRilancio() {
		return dataUltimoRilancio;
	}

	public void setDataUltimoRilancio(Date dataUltimoRilancio) {
		this.dataUltimoRilancio = dataUltimoRilancio;
	}

	public Double getImportoUltimoRilancio() {
		return importoUltimoRilancio;
	}

	public void setImportoUltimoRilancio(Double importoUltimoRilancio) {
		this.importoUltimoRilancio = importoUltimoRilancio;
	}

	public Double getRibassoUltimoRilancio() {
		return ribassoUltimoRilancio;
	}

	public void setRibassoUltimoRilancio(Double ribassoUltimoRilancio) {
		this.ribassoUltimoRilancio = ribassoUltimoRilancio;
	}

	public List<DettaglioFaseAstaType> getFasi() {
		return fasi;
	}

	public void setFasi(List<DettaglioFaseAstaType> fasi) {
		this.fasi = fasi;
	}

	public Long getNumDecimaliRibasso() {
		return numDecimaliRibasso;
	}

	public void setNumDecimaliRibasso(Long numDecimaliRibasso) {
		this.numDecimaliRibasso = numDecimaliRibasso;
	}
	
}

