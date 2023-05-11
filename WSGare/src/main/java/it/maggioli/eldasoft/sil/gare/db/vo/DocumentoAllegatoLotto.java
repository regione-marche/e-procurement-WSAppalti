package it.maggioli.eldasoft.sil.gare.db.vo;

import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.LottoGaraType;

/**
 * Classe utilizzata solo internamente al servizio con lo scopo di minimizzare 
 * le query in caso di lotti distinti 
 *    
 */
public class DocumentoAllegatoLotto  extends DocumentoAllegatoType {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	protected LottoGaraType lotto;
	protected String gruppo;	
	protected long idComunicazione;
	
	public DocumentoAllegatoLotto() {
		this.lotto = new LottoGaraType();
	}

	public String getGruppo() { return gruppo; }	
	public void setGruppo(String gruppo) { this.gruppo = gruppo; }
	
	public long getIdComunicazione() { return idComunicazione; }
	public void setIdComunicazione(long idComunicazione) { this.idComunicazione = idComunicazione; }

	// --- lotto ---	
	public String getCodiceLotto() { return lotto.getCodiceLotto(); }	
	public void setCodiceLotto(String value) { lotto.setCodiceLotto(value); }
	
	public String getCodiceInterno() { return lotto.getCodiceInterno(); }	
	public void setCodiceInterno(String value) { lotto.setCodiceInterno(value); }
	
	public String getOggetto() { return lotto.getOggetto();	}
	public void setOggetto(String value) { lotto.setOggetto(value); }
	
	public Integer getCostoFisso() { return lotto.getCostoFisso(); }
	public void setCostoFisso(Integer value) { lotto.setCostoFisso(value); }
		
}
