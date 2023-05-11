
package it.maggioli.eldasoft.sil.stipule.ws;


import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDocumentiRichiestiStipulaContrattoOutType", propOrder = {
    "documento",
    "errore"
})
public class GetDocumentiRichiestiStipulaContrattoOutType
    implements Serializable
{
	private static final long serialVersionUID = -555400993314088453L;
	
	protected List<DocumentazioneStipulaContrattiType> documento;
    protected String errore;
    
	public List<DocumentazioneStipulaContrattiType> getDocumento() {
		return documento;
	}
	
	public void setDocumento(List<DocumentazioneStipulaContrattiType> documento) {
		this.documento = documento;
	}
	
	public String getErrore() {
		return errore;
	}
	
	public void setErrore(String errore) {
		this.errore = errore;
	}    
    
}