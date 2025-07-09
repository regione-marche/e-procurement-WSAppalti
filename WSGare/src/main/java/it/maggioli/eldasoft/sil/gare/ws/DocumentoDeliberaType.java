
package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoDeliberaType", propOrder = {
	"codice",	
    "idDoc",
	"fileDoc",
	"descrizioneDoc",
	"dataPubblicazione",
	"dataAtto",
	"numeroAtto",
	"urlDoc"
})
public class DocumentoDeliberaType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(required = true)
	protected Long idDoc;
	protected String fileDoc;
	protected String descrizioneDoc;
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataPubblicazione;
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataAtto;
	protected String numeroAtto;
	protected String urlDoc;
	protected String codice;
	
	public Long getIdDoc() {
		return idDoc;
	}
	
	public void setIdDoc(Long idDoc) {
		this.idDoc = idDoc;
	}
	
	public String getFileDoc() {
		return fileDoc;
	}
	
	public void setFileDoc(String fileDoc) {
		this.fileDoc = fileDoc;
	}
	
	public String getDescrizioneDoc() {
		return descrizioneDoc;
	}
	
	public void setDescrizioneDoc(String descrizioneDoc) {
		this.descrizioneDoc = descrizioneDoc;
	}
	
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}
	
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	
	public Date getDataAtto() {
		return dataAtto;
	}
	
	public void setDataAtto(Date dataAtto) {
		this.dataAtto = dataAtto;
	}
	
	public String getNumeroAtto() {
		return numeroAtto;
	}
	
	public void setNumeroAtto(String numeroAtto) {
		this.numeroAtto = numeroAtto;
	}
	
	public String getUrlDoc() {
		return urlDoc;
	}
	
	public void setUrlDoc(String urlDoc) {
		this.urlDoc = urlDoc;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}	
	
}
