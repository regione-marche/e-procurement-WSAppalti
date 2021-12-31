package it.maggioli.eldasoft.sil.gare.db.vo;

import it.maggioli.eldasoft.sil.gare.ws.EspletGaraOperatoreType;

public class EspletGaraOperatoreLotto extends EspletGaraOperatoreType {	
	/**
	 * UID
	 */
	private static final long serialVersionUID = -5727427984303925006L;
	
	protected String codiceLotto;

	public String getCodiceLotto() { return codiceLotto; }
	public void setCodiceLotto(String codiceLotto) { this.codiceLotto = codiceLotto; }
	
	public EspletGaraOperatoreLotto() {	
	}
	
	public EspletGaraOperatoreLotto(EspletGaraOperatoreLotto source) {
		super( (EspletGaraOperatoreType)source );
		this.codiceLotto = source.codiceLotto;
	}

}
