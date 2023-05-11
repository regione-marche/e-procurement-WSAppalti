
package it.maggioli.eldasoft.sil.stipule.ws;


import it.maggioli.eldasoft.gene.ws.AllegatoComunicazioneType;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllegatoStipulaContrattoOutType", propOrder = {
    "documento",
    "errore"
})
public class GetAllegatoStipulaContrattoOutType
    implements Serializable
{
	private static final long serialVersionUID = -555400993314088453L;
	
	protected AllegatoComunicazioneType documento;
    protected String errore;
    
	public 	AllegatoComunicazioneType getDocumento() {
		return documento;
	}
	
	public void setDocumento(AllegatoComunicazioneType documento) {
		this.documento = documento;
	}
	
	public String getErrore() {
		return errore;
	}
	
	public void setErrore(String errore) {
		this.errore = errore;
	}    
    
}