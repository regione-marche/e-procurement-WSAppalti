
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvitoGaraType", propOrder = {
	"codice",
	"codiceLotto",
	"progressivoOfferta",
	"usernome"
})
public class InvitoGaraType implements Serializable {    
	/**
	 * UID
	 */
	private static final long serialVersionUID = 2656852089979209323L;
	
	protected String codice;
	protected String codiceLotto;
	protected String progressivoOfferta;
	protected String usernome;
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCodiceLotto() {
		return codiceLotto;
	}
	
	public void setCodiceLotto(String codiceLotto) {
		this.codiceLotto = codiceLotto;
	}
	
	public String getProgressivoOfferta() {
		return progressivoOfferta;
	}
	
	public void setProgressivoOfferta(String progressivoOfferta) {
		this.progressivoOfferta = progressivoOfferta;
	}
	
	public String getUsernome() {
		return usernome;
	}
	
	public void setUsernome(String usernome) {
		this.usernome = usernome;
	}

}
