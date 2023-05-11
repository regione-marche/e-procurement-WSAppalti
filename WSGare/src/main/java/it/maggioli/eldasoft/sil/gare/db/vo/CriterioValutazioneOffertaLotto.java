package it.maggioli.eldasoft.sil.gare.db.vo;

import it.maggioli.eldasoft.sil.gare.ws.CriterioValutazioneOffertaType;

public class CriterioValutazioneOffertaLotto extends CriterioValutazioneOffertaType {	
	/**
	 * UID
	 */
	private static final long serialVersionUID = -5692141046332818591L;
	
	protected String codiceLotto;

	public String getCodiceLotto() { return codiceLotto; }
	public void setCodiceLotto(String codiceLotto) { this.codiceLotto = codiceLotto; }
	
	public CriterioValutazioneOffertaLotto() {	
	}
	
	public CriterioValutazioneOffertaLotto(CriterioValutazioneOffertaLotto source) {
		super( (CriterioValutazioneOffertaType)source );
		this.codiceLotto = source.codiceLotto;
	}

}
