package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EspletGaraAccessoDocumentiDocumentoType", propOrder = {
    "idProgramma",
    "idDocumento",
    "filename",
    "descrizione",    
    "tipoDocumento",
    "idOrigine",
    "tipologia",
    "stato"
})
public class EspletGaraAccessoDocumentiDocumentoType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	protected String idProgramma;
	protected Long idDocumento;	
	protected String filename;
	protected String descrizione;	
	protected String tipoDocumento;
	protected String idOrigine;
	protected Long tipologia; 
	protected Integer stato;
		
	public String getIdProgramma() {
		return idProgramma;
	}

	public void setIdProgramma(String idProgramma) {
		this.idProgramma = idProgramma;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getIdOrigine() {
		return idOrigine;
	}
	
	public void setIdOrigine(String idOrigine) {
		this.idOrigine = idOrigine;
	}
	
	public Long getTipologia() {
		return tipologia;
	}
	
	public void setTipologia(Long tipologia) {
		this.tipologia = tipologia;
	}
	
	public Integer getStato() {
		return stato;
	}
	
	public void setStato(Integer stato) {
		this.stato = stato;
	}
	
}
