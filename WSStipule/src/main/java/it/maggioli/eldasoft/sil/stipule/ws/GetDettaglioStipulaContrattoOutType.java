
package it.maggioli.eldasoft.sil.stipule.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDettaglioStipulaContrattoOutType", propOrder = {
    "stipulaContratto",
    "errore"
})
public class GetDettaglioStipulaContrattoOutType
    implements Serializable
{
	private static final long serialVersionUID = 5261797996343095684L;
	
	protected StipulaContrattoType stipulaContratto;
    protected String errore;
    
	public StipulaContrattoType getStipulaContratto() {
		return stipulaContratto;
	}
	
	public void setStipulaContratto(StipulaContrattoType stipulaContratto) {
		this.stipulaContratto = stipulaContratto;
	}
	
	public String getErrore() {
		return errore;
	}
	
	public void setErrore(String errore) {
		this.errore = errore;
	}

}
