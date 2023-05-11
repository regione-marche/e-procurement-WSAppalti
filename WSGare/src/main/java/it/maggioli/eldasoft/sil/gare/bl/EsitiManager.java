/*
 * Created on 8/giu/12
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di 
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.sil.gare.bl;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.gare.db.dao.EsitiDao;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.EsitoType;
import it.maggioli.eldasoft.sil.gare.ws.LottoEsitoType;
import it.maggioli.eldasoft.sil.gare.ws.LottoGaraType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;
import it.maggioli.eldasoft.sil.gare.ws.search.EsitoTypeSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Stefano.Sabbadin
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class EsitiManager {

	private static final Logger logger = LoggerFactory.getLogger(EsitiManager.class);

	@Autowired
	private EsitiDao esitiDao;

	/**
	 * Ritorna l'elenco degli esiti di gara filtrati in base ai parametri in
	 * input.
	 * 
	 * @return lista degli esiti, di tipo EsitoType
	 * @throws SqlComposerException
	 */
	public List<EsitoType> searchEsiti(EsitoTypeSearch filter) throws SqlComposerException {
		return this.esitiDao.searchEsiti(filter);
	}

	public int countEsiti(EsitoTypeSearch filtri) throws SqlComposerException {
		return this.esitiDao.countEsiti(filtri);
	}
	
	/**
	 * Ritorna l'elenco degli esiti di gara.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuta la procedura pubblicata
	 * @return lista degli esiti, di tipo EsitoType
	 * @throws DataAccessException 
	 * @throws SqlComposerException
	 */
	public List<EsitoType> getElencoEsiti(int numAnniPubblicazione) throws DataAccessException, SqlComposerException {
		EsitoTypeSearch filtro = new EsitoTypeSearch();
		filtro.setNumAnniPubblicazione(numAnniPubblicazione);
		filtro.setMaxNumRecord(-1);
		filtro.setIndicePrimoRecord(-1);
		return esitiDao.getElencoEsiti(filtro);
	}
	
	/**
	 * Ritorna l'elenco degli esiti di gara.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuta la procedura pubblicata
	 * @return lista degli esiti, di tipo EsitoType
	 * @throws DataAccessException 
	 * @throws SqlComposerException
	 */
	public List<EsitoType> getElencoEsiti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException {
		return esitiDao.getElencoEsiti(filtri);
	}
	
	public int countElencoEsiti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException {
		return esitiDao.countElencoEsiti(filtri);
	}

	/**
	 * Ritorna l'elenco degli esiti affidamenti di un elenco operatori economici.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuta la procedura pubblicata
	 * @return lista degli esiti, di tipo EsitoType
	 * @throws DataAccessException 
	 * @throws SqlComposerException
	 */
	public List<EsitoType> getElencoEsitiAffidamenti(int numAnniPubblicazione) throws DataAccessException, SqlComposerException {
		EsitoTypeSearch filtri = new EsitoTypeSearch();
		filtri.setNumAnniPubblicazione(numAnniPubblicazione);
		filtri.setIndicePrimoRecord(-1);
		filtri.setMaxNumRecord(-1);
		return esitiDao.getElencoEsitiAffidamenti(filtri);
	}
	
	/**
	 * Ritorna l'elenco degli esiti affidamenti di un elenco operatori economici.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuta la procedura pubblicata
	 * @return lista degli esiti, di tipo EsitoType
	 * @throws SqlComposerException
	 */
	public List<EsitoType> getElencoEsitiAffidamenti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException {
		return esitiDao.getElencoEsitiAffidamenti(filtri);
	}
	
	public int countElencoEsitiAffidamenti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException {
		return esitiDao.countElencoEsitiAffidamenti(filtri);
	}


	public StazioneAppaltanteBandoType getStazioneAppaltanteEsito(String codice) {
		return esitiDao.getStazioneAppaltanteEsito(codice);
	}

	/**
	 * Ritorna il singolo esito a partire dalla sua chiave.
	 * 
	 * @param codiceGara
	 *            codice dell'esito di gara
	 * 
	 * @return esito
	 * @throws SqlComposerException
	 */
	public EsitoType getEsito(String codiceGara) {
		return this.esitiDao.getEsito(codiceGara);
	}

	public List<LottoGaraType> getLottiGaraConEsito(String codiceGara) {
		return this.esitiDao.getLottiGaraConEsito(codiceGara);
	}
	
	public List<DocumentoAllegatoLotto> getDocumentiLottiGaraConEsito(String codiceGara) {
		return this.esitiDao.getDocumentiLottiGaraConEsito(codiceGara);
	}	

	public List<LottoEsitoType> getLottiEsito(String codiceGara) {

		List<LottoEsitoType> listaLottoEsito = this.esitiDao.getLottiEsito(codiceGara);
		boolean aggiudicatariMultipli = this.esitiDao.isAQAggiudicatariMultipli(codiceGara);
		
		for(int i = 0; i < listaLottoEsito.size();i++){
			String codiceLotto = listaLottoEsito.get(i).getCodiceLotto();
			if(aggiudicatariMultipli){	
				listaLottoEsito.get(i).setAggiudicataria(this.esitiDao.getAQAggiudicatariMultipli(codiceLotto));
			}else{
				listaLottoEsito.get(i).setAggiudicataria(this.esitiDao.getAggiudicatariaSingola(codiceLotto));
			}
		}		

		return listaLottoEsito;
	}

	public List<DocumentoAllegatoType> getDocumentiEsitoLotto(
			String codiceGara, String codiceLotto) {
		return this.esitiDao.getDocumentiEsitoLotto(codiceGara, codiceLotto);
	}

}
