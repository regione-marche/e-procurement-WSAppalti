package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetEspletamentoGaraAccessoDocumentiOutType", propOrder = {
    "documenti",
    "errore"
})
public class GetEspletamentoGaraAccessoDocumentiOutType implements Serializable {	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	protected List<EspletGaraAccessoDocumentiDocumentoType> documenti;
    protected String errore;
    
	public List<EspletGaraAccessoDocumentiDocumentoType> getDocumenti() {
		return documenti;
	}
	
	public void setDocumenti(List<EspletGaraAccessoDocumentiDocumentoType> documenti) {
		this.documenti = documenti;
	}
	
	public String getErrore() {
		return errore;
	}
	
	public void setErrore(String errore) {
		this.errore = errore;
	}
    
}
