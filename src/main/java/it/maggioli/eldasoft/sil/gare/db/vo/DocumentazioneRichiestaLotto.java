package it.maggioli.eldasoft.sil.gare.db.vo;

import it.maggioli.eldasoft.sil.gare.ws.DocumentazioneRichiestaType;

/**
 * Classe utilizzata solo internamente al servizio con lo scopo di minimizzare 
 * le query in caso di lotti (vedere getDettaglioGara(...))
 *    
 */
public class DocumentazioneRichiestaLotto extends DocumentazioneRichiestaType {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	protected String codiceLotto;
	protected String tipoBusta;	

	public String getTipoBusta() { return tipoBusta; }
	public void setTipoBusta(String tipoBusta) { this.tipoBusta = tipoBusta; }

	public String getCodiceLotto() { return codiceLotto; }
	public void setCodiceLotto(String codiceLotto) { this.codiceLotto = codiceLotto; }
	
}
