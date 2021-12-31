package it.maggioli.eldasoft.sil.gare.db.vo;

import it.maggioli.eldasoft.sil.gare.ws.RequisitoRichiestoType;

public class RequisitoRichiestoLotto extends RequisitoRichiestoType {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	protected String codiceLotto;

	/**
	 * @return the codiceLotto
	 */
	public String getCodiceLotto() {
		return codiceLotto;
	}

	/**
	 * @param codiceLotto the codiceLotto to set
	 */
	public void setCodiceLotto(String codiceLotto) {
		this.codiceLotto = codiceLotto;
	}	
	
}
