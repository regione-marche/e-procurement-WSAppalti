
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DettaglioDeliberaOutType", propOrder = {
    "delibera",
    "errore"
})
public class DettaglioDeliberaOutType implements Serializable {    
    /**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

    protected DeliberaType delibera;
    protected String errore;
    
	public DeliberaType getDelibera() {
		return delibera;
	}
	
	public void setDelibera(DeliberaType delibera) {
		this.delibera = delibera;
	}
	
	public String getErrore() {
		return errore;
	}
	
	public void setErrore(String errore) {
		this.errore = errore;
	}    

}
