
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeliberaType", propOrder = {
    "codice",
	"stazioneAppaltante",
	"oggetto",
	"cig",
	"documenti"
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
	protected List<DocumentoDeliberaType> documenti; 
		
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

	public List<DocumentoDeliberaType> getDocumenti() {
		return documenti;
	}

	public void setDocumenti(List<DocumentoDeliberaType> documenti) {
		this.documenti = documenti;
	}
	
}
