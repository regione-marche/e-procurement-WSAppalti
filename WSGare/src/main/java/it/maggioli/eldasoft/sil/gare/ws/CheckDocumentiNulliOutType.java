package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckDocumentiNulliOutType", propOrder = {
    "risultato",
    "errore"
})
public class CheckDocumentiNulliOutType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 8048815047262484017L;	
	protected List<DocumentoAllegatoLotto> risultato;
    protected String errore;
    
	public List<DocumentoAllegatoLotto> getRisultato() {
		return risultato;
	}
	
	public void setRisultato(List<DocumentoAllegatoLotto> risultato) {
		this.risultato = risultato;
	}
	
	public String getErrore() {
		return errore;
	}
	
	public void setErrore(String errore) {
		this.errore = errore;
	}
        
}
