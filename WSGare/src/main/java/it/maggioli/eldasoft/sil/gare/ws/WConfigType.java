package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WConfigType", propOrder = {
	"idprg",
	"chiave",		
	"sezione",
	"descrizione",
	"valore",
	"ciptato"
})
public class WConfigType implements Serializable {    
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	protected String idprg;
	protected String chiave;
	protected String sezione;
	protected String descrizione;
	protected String valore;
	protected String criptato;
	
	public String getIdprg() {
		return idprg;
	}
	
	public void setIdprg(String idprg) {
		this.idprg = idprg;
	}
	
	public String getChiave() {
		return chiave;
	}
	
	public void setChiave(String chiave) {
		this.chiave = chiave;
	}
	
	public String getSezione() {
		return sezione;
	}
	
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getValore() {
		return valore;
	}
	
	public void setValore(String valore) {
		this.valore = valore;
	}

	public String getCriptato() {
		return criptato;
	}

	public void setCriptato(String criptato) {
		this.criptato = criptato;
	}
	
}
