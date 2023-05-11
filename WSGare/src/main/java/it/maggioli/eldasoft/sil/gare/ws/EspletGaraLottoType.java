package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EspletGaraLottoType", propOrder = { 
    "lotto",
    "codiceInterno",
    "oggetto",
    "ammissione"
})
public class EspletGaraLottoType implements Serializable {
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 198947056592823476L;

	@XmlElement(required = true)
    protected String lotto;
    protected String codiceInterno;
    protected String oggetto;
    protected String ammissione;
    
	/**
	 * @return the lotto
	 */
	public String getLotto() {
		return lotto;
	}
	/**
	 * @param lotto the lotto to set
	 */
	public void setLotto(String lotto) {
		this.lotto = lotto;
	}
	/**
	 * @return the codiceInterno
	 */
	public String getCodiceInterno() {
		return codiceInterno;
	}
	/**
	 * @param codiceInterno the codiceInterno to set
	 */
	public void setCodiceInterno(String codiceInterno) {
		this.codiceInterno = codiceInterno;
	}
	/**
	 * @return the oggetto
	 */
	public String getOggetto() {
		return oggetto;
	}
	/**
	 * @param oggetto the oggetto to set
	 */
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	/**
	 * @return the ammissione
	 */
	public String getAmmissione() {
		return ammissione;
	}
	/**
	 * @param ammissione the ammissione to set
	 */
	public void setAmmissione(String ammissione) {
		this.ammissione = ammissione;
	}
    
}
