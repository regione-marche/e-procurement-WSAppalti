package it.maggioli.eldasoft.sil.gare.db.vo;

import it.maggioli.eldasoft.sil.gare.ws.SoggettoAderenteType;

public class SoggettoAderenteLotto extends SoggettoAderenteType {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	protected String codiceLotto;

	public String getCodiceLotto() { return codiceLotto; }
	public void setCodiceLotto(String codiceLotto) { this.codiceLotto = codiceLotto; }	

}
