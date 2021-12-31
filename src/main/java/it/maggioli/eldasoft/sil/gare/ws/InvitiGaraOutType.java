
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvitiGaraOutType", propOrder = {
    "elencoInvitiGara",
    "errore"
})
public class InvitiGaraOutType implements Serializable {    
	/**
	 * UID
	 */
	private static final long serialVersionUID = 7644052130513928594L;
	
	protected List<InvitoGaraType> elencoInvitiGara;
    protected String errore;
    
	public List<InvitoGaraType> getElencoInvitiGara() {
		return elencoInvitiGara;
	}
	
	public void setElencoInvitiGara(List<InvitoGaraType> elencoInvitiGara) {
		this.elencoInvitiGara = elencoInvitiGara;
	}
	
	public String getErrore() {
		return errore;
	}
	
	public void setErrore(String errore) {
		this.errore = errore;
	}

}
