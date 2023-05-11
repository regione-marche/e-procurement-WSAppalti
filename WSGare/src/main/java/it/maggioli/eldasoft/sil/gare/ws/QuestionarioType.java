package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuestionarioType", propOrder = {
	"id",
	"entita",
	"chiave1",
	"chiave2",
	"chiave3",
	"chiave4",
	"titolo",
	"descrizione",
	"tipologia",
	"busta",
	"stato",
	"modello",
	"dataUltimoAggiornamentoModello",
	"dataPubblicazione",
	"oggetto"
})
public class QuestionarioType implements Serializable {
	
	private static final long serialVersionUID = 6757432392758132816L;
	  
	//@XmlElement(required = true)
	protected long id;
	protected String entita;
	protected String chiave1;
	protected String chiave2;
	protected String chiave3;
	protected String chiave4;
	protected String titolo;
	protected String descrizione;
	protected String tipologia;
	protected String busta;
	protected String stato;
	protected String modello;
	@XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataUltimoAggiornamentoModello;
	@XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataPubblicazione;
	protected String oggetto;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEntita() {
		return entita;
	}
	
	public void setEntita(String entita) {
		this.entita = entita;
	}
	
	public String getChiave1() {
		return chiave1;
	}
	
	public void setChiave1(String chiave1) {
		this.chiave1 = chiave1;
	}
	
	public String getChiave2() {
		return chiave2;
	}
	
	public void setChiave2(String chiave2) {
		this.chiave2 = chiave2;
	}
	
	public String getChiave3() {
		return chiave3;
	}
	
	public void setChiave3(String chiave3) {
		this.chiave3 = chiave3;
	}
	
	public String getChiave4() {
		return chiave4;
	}
	
	public void setChiave4(String chiave4) {
		this.chiave4 = chiave4;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getTipologia() {
		return tipologia;
	}
	
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	
	public String getBusta() {
		return busta;
	}
	
	public void setBusta(String busta) {
		this.busta = busta;
	}
	
	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public String getModello() {
		return modello;
	}
	
	public void setModello(String modello) {
		this.modello = modello;
	}
	
	public Date getDataUltimoAggiornamentoModello() {
		return dataUltimoAggiornamentoModello;
	}
	
	public void setDataUltimoAggiornamentoModello(Date dataUltimoAggiornamentoModello) {
		this.dataUltimoAggiornamentoModello = dataUltimoAggiornamentoModello;
	}
	
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}
	
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	
	public String getOggetto() {
		return oggetto;
	}
	
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	
}
