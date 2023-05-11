package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImpresaIscrizioneOutType", propOrder = {
    "impresa",
    "errore"
})
public class ImpresaIscrizioneOutType 
	implements Serializable 
{	
	private static final long serialVersionUID = 1L;
	protected ImpresaIscrizioneType impresa;		
    protected String errore;
    
	public ImpresaIscrizioneType getImpresa() {
		return impresa;
	}

	public void setImpresa(ImpresaIscrizioneType impresa) {
		this.impresa = impresa;
	}

	public String getErrore() {
		return errore;
	}
	
	public void setErrore(String errore) {
		this.errore = errore;
	}
    
    
}

