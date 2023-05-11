package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImpresaIscrizioneType", propOrder = {
    "coordinatoreSicurezza",
	"ascesaTorre"
})
public class ImpresaIscrizioneType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//@XmlElement(required = true)
    protected Boolean coordinatoreSicurezza;	// in possesso dei requisiti coordinatore sicurezza
    protected Boolean ascesaTorre;				// requisito ascesa torre
    //protected ... ...;
    //protected ... ...;
	//protected ... ...;

	public Boolean getCoordinatoreSicurezza() {
		return coordinatoreSicurezza;
	}

	public void setCoordinatoreSicurezza(Boolean coordinatoreSicurezza) {
		this.coordinatoreSicurezza = coordinatoreSicurezza;
	}

	public Boolean getAscesaTorre() {
		return ascesaTorre;
	}

	public void setAscesaTorre(Boolean ascesaTorre) {
		this.ascesaTorre = ascesaTorre;
	}
    
}
