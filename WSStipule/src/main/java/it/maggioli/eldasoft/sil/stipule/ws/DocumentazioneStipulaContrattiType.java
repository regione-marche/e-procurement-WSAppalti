package it.maggioli.eldasoft.sil.stipule.ws;

import java.io.Serializable;
import java.util.Date;

import it.maggioli.eldasoft.gene.xml.DateAdapter;
import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentazioneStipulaContrattiType", propOrder = {
    "id",
    "idStipula",
    "ordine",
    "fase",
    "titolo",
    "descrizione",
    "note",
    "visibilita",
    "stato",
    "obbligatorio",
    "iddocdig",
    "nomeFile",
    "formato",
    "firmacheck",
    "firmacheckts"
})
public class DocumentazioneStipulaContrattiType
    implements Serializable
{
	private static final long serialVersionUID = 7822885271874325714L;
	
//	@XmlElement(required = true)
	protected long id;
	protected Long idStipula;
    protected String ordine;
    protected Integer fase;
    protected String titolo;
    protected String descrizione;
    protected String note;
    protected Integer visibilita;
    protected Integer stato;
    protected String obbligatorio;
    protected Integer iddocdig;
    protected String nomeFile;
    protected Integer formato;
    protected String firmacheck;
	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date firmacheckts;
    
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Long getIdStipula() {
		return idStipula;
	}
	
	public void setIdStipula(Long idStipula) {
		this.idStipula = idStipula;
	}
	
	public String getOrdine() {
		return ordine;
	}
	
	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}
	
	public Integer getFase() {
		return fase;
	}
	
	public void setFase(Integer fase) {
		this.fase = fase;
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
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public Integer getStato() {
		return stato;
	}
	
	public void setStato(Integer stato) {
		this.stato = stato;
	}

	public Integer getVisibilita() {
		return visibilita;
	}

	public void setVisibilita(Integer visibilita) {
		this.visibilita = visibilita;
	}

	public String getObbligatorio() {
		return obbligatorio;
	}

	public void setObbligatorio(String obbligatorio) {
		this.obbligatorio = obbligatorio;
	}

	public Integer getIddocdig() {
		return iddocdig;
	}

	public void setIddocdig(Integer iddocdig) {
		this.iddocdig = iddocdig;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public Integer getFormato() {
		return formato;
	}

	public void setFormato(Integer formato) {
		this.formato = formato;
	}

	/**
	 * @return the firmacheck
	 */
	public String getFirmacheck() {
		return firmacheck;
	}

	/**
	 * @param firmacheck the firmacheck to set
	 */
	public void setFirmacheck(String firmacheck) {
		this.firmacheck = firmacheck;
	}

	/**
	 * @return the firmacheckts
	 */
	public Date getFirmacheckts() {
		return firmacheckts;
	}

	/**
	 * @param firmacheckts the firmacheckts to set
	 */
	public void setFirmacheckts(Date firmacheckts) {
		this.firmacheckts = firmacheckts;
	}
	
}
