package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParametroQuestionarioType", propOrder = {
	"id",
	"chiave",
	"valore"
})
public class ParametroQuestionarioType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 566323325567129093L;
	
	protected long id;
	protected String chiave;
	protected String valore;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getChiave() {
		return chiave;
	}
	
	public void setChiave(String chiave) {
		this.chiave = chiave;
	}
	
	public String getValore() {
		return valore;
	}
	
	public void setValore(String valore) {
		this.valore = valore;
	}	
	
}
