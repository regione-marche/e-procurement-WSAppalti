package it.maggioli.eldasoft.sil.gare.ws.search;

import it.maggioli.eldasoft.gene.xml.DateAdapter;
import it.maggioli.eldasoft.sil.gare.ws.OrderCriteria;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GaraTypeSearch", propOrder = {
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
        "visualizzaNegoziate",
        "garaPrivatistica",
        "numAnniPubblicazione",
        "isGreen",
        "isRecycle",
        "isPnrr",
        "orderCriteria",
        "codice",
        "indicePrimoRecord",
        "maxNumRecord"
})
public class GaraTypeSearch implements Serializable {

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
    private Boolean visualizzaNegoziate;
    private int garaPrivatistica;
    private int numAnniPubblicazione;
    private int indicePrimoRecord;
    private int maxNumRecord;
    private String isGreen;
    private String isRecycle;
    private String isPnrr;
    private OrderCriteria orderCriteria;

    private  String codice;

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

    public Boolean getVisualizzaNegoziate() {
        return visualizzaNegoziate;
    }

    public void setVisualizzaNegoziate(Boolean visualizzaNegoziate) {
        this.visualizzaNegoziate = visualizzaNegoziate;
    }

    public int getGaraPrivatistica() {
        return garaPrivatistica;
    }

    public void setGaraPrivatistica(int garaPrivatistica) {
        this.garaPrivatistica = garaPrivatistica;
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

    public String getTokenRichiedente() {
        return tokenRichiedente;
    }

    public void setTokenRichiedente(String tokenRichiedente) {
        this.tokenRichiedente = tokenRichiedente;
    }

    public OrderCriteria getOrderCriteria() {
        return orderCriteria;
    }

    public void setOrderCriteria(OrderCriteria orderCriteria) {
        this.orderCriteria = orderCriteria;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }
}
