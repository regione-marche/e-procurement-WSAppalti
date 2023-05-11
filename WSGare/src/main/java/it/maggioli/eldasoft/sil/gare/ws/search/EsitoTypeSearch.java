package it.maggioli.eldasoft.sil.gare.ws.search;

import it.maggioli.eldasoft.gene.xml.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EsitoTypeSearch", propOrder = {
        "tokenRichiedente",
        "stazioneAppaltante",
        "oggetto",
        "cig",
        "tipoAppalto",
        "dataPubblicazioneDa",
        "dataPubblicazioneA",
        "dataScadenzaDa",
        "dataScadenzaA",
        "proceduraTelematica",
        "stato",
        "esito",
        "altriSoggetti",
        "sommaUrgenza",
        "isGreen",
        "isRecycle",
        "isPnrr",
        "numAnniPubblicazione",
        "indicePrimoRecord",
        "maxNumRecord"
})
public class EsitoTypeSearch implements Serializable {

    private String tokenRichiedente;
    private String stazioneAppaltante;
    private String oggetto;
    private String cig;
    private String tipoAppalto;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    private Date dataPubblicazioneDa;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    private Date dataPubblicazioneA;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    private Date dataScadenzaDa;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    private Date dataScadenzaA;
    private Boolean proceduraTelematica;
    private String stato;
    private String esito;
    private String altriSoggetti;
    private Boolean sommaUrgenza;
    private String isGreen;
    private String isRecycle;
    private String isPnrr;
    private int numAnniPubblicazione;
    private int indicePrimoRecord;
    private int maxNumRecord;

    public String getStazioneAppaltante() {
        return stazioneAppaltante;
    }

    public void setStazioneAppaltante(String stazioneAppaltante) {
        this.stazioneAppaltante = stazioneAppaltante;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getCig() {
        return cig;
    }

    public void setCig(String cig) {
        this.cig = cig;
    }

    public String getTipoAppalto() {
        return tipoAppalto;
    }

    public void setTipoAppalto(String tipoAppalto) {
        this.tipoAppalto = tipoAppalto;
    }

    public Date getDataPubblicazioneDa() {
        return dataPubblicazioneDa;
    }

    public void setDataPubblicazioneDa(Date dataPubblicazioneDa) {
        this.dataPubblicazioneDa = dataPubblicazioneDa;
    }

    public Date getDataPubblicazioneA() {
        return dataPubblicazioneA;
    }

    public void setDataPubblicazioneA(Date dataPubblicazioneA) {
        this.dataPubblicazioneA = dataPubblicazioneA;
    }

    public Date getDataScadenzaDa() {
        return dataScadenzaDa;
    }

    public void setDataScadenzaDa(Date dataScadenzaDa) {
        this.dataScadenzaDa = dataScadenzaDa;
    }

    public Date getDataScadenzaA() {
        return dataScadenzaA;
    }

    public void setDataScadenzaA(Date dataScadenzaA) {
        this.dataScadenzaA = dataScadenzaA;
    }

    public Boolean getProceduraTelematica() {
        return proceduraTelematica;
    }

    public void setProceduraTelematica(Boolean proceduraTelematica) {
        this.proceduraTelematica = proceduraTelematica;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getEsito() {
        return esito;
    }

    public void setEsito(String esito) {
        this.esito = esito;
    }

    public String getAltriSoggetti() {
        return altriSoggetti;
    }

    public void setAltriSoggetti(String altriSoggetti) {
        this.altriSoggetti = altriSoggetti;
    }

    public Boolean getSommaUrgenza() {
        return sommaUrgenza;
    }

    public void setSommaUrgenza(Boolean sommaUrgenza) {
        this.sommaUrgenza = sommaUrgenza;
    }

    public int getNumAnniPubblicazione() {
        return numAnniPubblicazione;
    }

    public void setNumAnniPubblicazione(int numAnniPubblicazione) {
        this.numAnniPubblicazione = numAnniPubblicazione;
    }

    public int getIndicePrimoRecord() {
        return indicePrimoRecord;
    }

    public void setIndicePrimoRecord(int indicePrimoRecord) {
        this.indicePrimoRecord = indicePrimoRecord;
    }

    public int getMaxNumRecord() {
        return maxNumRecord;
    }

    public void setMaxNumRecord(int maxNumRecord) {
        this.maxNumRecord = maxNumRecord;
    }

    public String getTokenRichiedente() {
        return tokenRichiedente;
    }

    public void setTokenRichiedente(String tokenRichiedente) {
        this.tokenRichiedente = tokenRichiedente;
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
    
}
