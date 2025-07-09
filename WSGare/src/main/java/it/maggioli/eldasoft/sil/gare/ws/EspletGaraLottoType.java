package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.maggioli.eldasoft.sil.gare.db.vo.EspletGaraOperatoreLotto;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EspletGaraLottoType", propOrder = { 
    "lotto",
    "codiceInterno",
    "oggetto",
    "ammissione",
    "faseGara"
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
    protected Long faseGara;
    
    /**
     * costruttore
     */
    public EspletGaraLottoType() {
    }
    	
    public EspletGaraLottoType(EspletGaraOperatoreLotto source) {
    	lotto = source.getCodiceLotto();
    	codiceInterno = source.getCodiceLotto();
		//oggetto = source.???
		ammissione = source.getAmmissione();
		faseGara = source.getFaseGara();
    }
    
	public String getLotto() {
		return lotto;
	}
	
	public void setLotto(String lotto) {
		this.lotto = lotto;
	}
	
	public String getCodiceInterno() {
		return codiceInterno;
	}
	
	public void setCodiceInterno(String codiceInterno) {
		this.codiceInterno = codiceInterno;
	}
	
	public String getOggetto() {
		return oggetto;
	}
	
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	
	public String getAmmissione() {
		return ammissione;
	}
	
	public void setAmmissione(String ammissione) {
		this.ammissione = ammissione;
	}
	
	public Long getFaseGara() {
		return faseGara;
	}
	
	public void setFaseGara(Long faseGara) {
		this.faseGara = faseGara;
	}
    
}
