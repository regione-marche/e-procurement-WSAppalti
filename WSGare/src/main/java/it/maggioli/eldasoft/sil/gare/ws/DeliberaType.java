
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
@XmlType(name = "DeliberaType", propOrder = {
    "codice",
	"stazioneAppaltante",
	"oggetto",
	"cig",
	"dataPubblicazione",
	"dataAtto",
	"numeroAtto",
	"idDoc",
	"descrizioneDoc",
	"urlDoc",	
	"fileDoc"
})
public class DeliberaType implements Serializable {    
	/**
	 * UID
	 */
	private static final long serialVersionUID = 6773740587814014844L;
	
	@XmlElement(required = true)
	protected String codice;
	protected String stazioneAppaltante;
	protected String oggetto;
	protected String cig;
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataPubblicazione;
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataAtto;
	protected String numeroAtto;
	protected Long idDoc;
	protected String descrizioneDoc;
	protected String urlDoc;	
	protected String fileDoc;
		
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
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
	
	public String getDescrizioneDoc() {
		return descrizioneDoc;
	}
	
	public void setDescrizioneDoc(String descrizioneDoc) {
		this.descrizioneDoc = descrizioneDoc;
	}
	
	public String getUrlDoc() {
		return urlDoc;
	}
	
	public void setUrlDoc(String urlDoc) {
		this.urlDoc = urlDoc;
	}
	
	public String getFileDoc() {
		return fileDoc;
	}
	
	public void setFileDoc(String fileDoc) {
		this.fileDoc = fileDoc;
	}
	
}
