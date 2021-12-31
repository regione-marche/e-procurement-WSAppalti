package it.maggioli.eldasoft.sil.gare.db.vo;

import it.maggioli.eldasoft.sil.gare.ws.ComunicazioneType;

public class ComunicazioneLotto extends ComunicazioneType {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	protected String codiceLotto;
	
	public String getCodiceLotto() { return codiceLotto; }
	public void setCodiceLotto(String codiceLotto) {this.codiceLotto = codiceLotto; }

}
