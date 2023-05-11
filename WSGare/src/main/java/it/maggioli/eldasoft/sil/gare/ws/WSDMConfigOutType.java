
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSDMConfigOutType", propOrder = {
    "WSDMConfig",
    "errore"
})
public class WSDMConfigOutType implements Serializable {    
    /**
	 * UID
	 */
	private static final long serialVersionUID = 6931363348121281039L;
	
	protected List<WSDMConfigType> WSDMConfig;
    protected String errore;
	
	public List<WSDMConfigType> getWSDMConfig() {
		if (WSDMConfig == null) {
			WSDMConfig = new ArrayList<WSDMConfigType>();
        }
		return WSDMConfig;
	}

	public void setWSDMConfig(List<WSDMConfigType> wSDMConfig) {
		WSDMConfig = wSDMConfig;
	}

	public String getErrore() {
		return errore;
	}
	
	public void setErrore(String errore) {
		this.errore = errore;
	}

}
