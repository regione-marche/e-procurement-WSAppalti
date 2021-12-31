package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckDimensioneDocumentiOutType", propOrder = {
    "risultato",
    "errore"
})
public class CheckDimensioneDocumentiOutType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -2913181709162273461L;
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
