/*
 * Created on 08/lug/12
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di 
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.sil.gare.db.dao.ibatis;

import it.eldasoft.utils.sql.comp.SqlComposer;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.eldasoft.utils.sql.comp.SqlManager;
import it.maggioli.eldasoft.sil.gare.db.dao.EsitiDao;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.EsitoType;
import it.maggioli.eldasoft.sil.gare.ws.LottoEsitoType;
import it.maggioli.eldasoft.sil.gare.ws.LottoGaraType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione degli esiti
 * 
 * @author Stefano.Sabbadin
 */
public class SqlMapEsitiDao extends SqlMapClientDaoSupport implements EsitiDao {

	/** tipologia di DBMS da property */
	private String dbms;

	/**
	 * @param dbms
	 *            the dbms to set
	 */
	public void setDbms(String dbms) {
		this.dbms = dbms;
	}

	private HashMap<String, Object> prepareParamsEsiti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		//WE1158
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("proceduraTelematica", proceduraTelematica);
		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("sommaUrgenza", sommaUrgenza);
		hash.put("altriSoggetti", altriSoggetti);
		return hash;
	}
		
	@SuppressWarnings("unchecked")
	public List<EsitoType> searchEsiti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione,
			int indicePrimoRecord,
			int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsEsiti(
				stazioneAppaltante, 
				oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, 
				dataPubblicazioneA, proceduraTelematica, 
				altriSoggetti, 
				sommaUrgenza, 
				numAnniPubblicazione);
		
		List<EsitoType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<EsitoType>) this.getSqlMapClientTemplate().queryForList(
							"searchEsiti", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<EsitoType>) this.getSqlMapClientTemplate().queryForList(
							"searchEsiti", hash);
		}
		return risultato;
	}
	
	public int countEsiti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsEsiti(
				stazioneAppaltante, 
				oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, 
				dataPubblicazioneA, proceduraTelematica, 
				altriSoggetti, 
				sommaUrgenza, 
				numAnniPubblicazione);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"coutEsiti", hash);
	}

	private HashMap<String, Object> prepareParamsElencoEsiti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("proceduraTelematica", proceduraTelematica);
		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("sommaUrgenza", sommaUrgenza);
		hash.put("altriSoggetti", altriSoggetti);
		return hash;
	}
		
	@SuppressWarnings("unchecked")
	public List<EsitoType> getElencoEsiti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione,
			int indicePrimoRecord,
			int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoEsiti(
				stazioneAppaltante,
				oggetto, 
				cig, 
				tipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA, 
				proceduraTelematica, 
				altriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);
	
		List<EsitoType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<EsitoType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoEsiti", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<EsitoType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoEsiti", hash);
		}
		return risultato;
	}
	
	public int countElencoEsiti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoEsiti(
				stazioneAppaltante,
				oggetto, 
				cig, 
				tipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA, 
				proceduraTelematica, 
				altriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);

		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countElencoEsiti", hash);
	}

	public HashMap<String, Object> prepareParamsElencoEsitiAffidamenti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("proceduraTelematica", proceduraTelematica);
		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("sommaUrgenza", sommaUrgenza);
		hash.put("altriSoggetti", altriSoggetti);
		return hash;
	}
	
	@SuppressWarnings("unchecked")
	public List<EsitoType> getElencoEsitiAffidamenti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione,
			int indicePrimoRecord,
			int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoEsitiAffidamenti(
				stazioneAppaltante,
				oggetto, 
				cig, 
				tipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA, 
				proceduraTelematica, 
				altriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);
		
		List<EsitoType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<EsitoType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoEsitiAffidamenti", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<EsitoType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoEsitiAffidamenti", hash);
		}
		return risultato;
	}
	
	public int countElencoEsitiAffidamenti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoEsitiAffidamenti(
				stazioneAppaltante,
				oggetto, 
				cig, 
				tipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA, 
				proceduraTelematica, 
				altriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countElencoEsitiAffidamenti", hash);
	}

	public StazioneAppaltanteBandoType getStazioneAppaltanteEsito(String codice)
			throws DataAccessException {
		return (StazioneAppaltanteBandoType) this.getSqlMapClientTemplate()
				.queryForObject("getStazioneAppaltanteEsito", codice);
	}

	public EsitoType getEsito(String codiceGara) throws DataAccessException {
		return (EsitoType) this.getSqlMapClientTemplate().queryForObject(
				"getEsito", codiceGara);
	}

	@SuppressWarnings("unchecked")
	public List<LottoGaraType> getLottiGaraConEsito(String codiceGara)
			throws DataAccessException {
		return (List<LottoGaraType>) this.getSqlMapClientTemplate()
				.queryForList("getLottiGaraConEsito", codiceGara);
	}
	
	/** Ottimizzazione getDettaglioEsito() */
	@SuppressWarnings("unchecked")
	public List<DocumentoAllegatoLotto> getDocumentiLottiGaraConEsito(String codiceGara)
			throws DataAccessException {
		return (List<DocumentoAllegatoLotto>) this.getSqlMapClientTemplate()
				.queryForList("getDocumentiLottiGaraConEsito", codiceGara);
	}
    
	public boolean isAQAggiudicatariMultipli(String codiceGara)
			throws DataAccessException {
		return (((Integer)this.getSqlMapClientTemplate()
				.queryForObject("isAQAggiudicatariMultipli", codiceGara)).intValue()) > 0;
	}
	
	@SuppressWarnings("unchecked")
	public List<LottoEsitoType> getLottiEsito(String codiceGara)
			throws DataAccessException {
		return (List<LottoEsitoType>) this.getSqlMapClientTemplate()
				.queryForList("getLottiEsito", codiceGara);
	}

	@SuppressWarnings("unchecked")
	public List<String> getAQAggiudicatariMultipli(String codiceLotto)
			throws DataAccessException {
		return (List<String>) this.getSqlMapClientTemplate()
				.queryForList("getAQAggiudicatariMultipli", codiceLotto);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAggiudicatariaSingola(String codiceLotto)
			throws DataAccessException {
		return (List<String>) this.getSqlMapClientTemplate()
				.queryForList("getAggiudicatariaSingola",codiceLotto);
	}
	
    @SuppressWarnings("unchecked")
	public List<DocumentoAllegatoType> getDocumentiEsitoLotto(
			String codiceGara, String codiceLotto) throws DataAccessException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("codiceGara", codiceGara);
		map.put("codiceLotto", codiceLotto);
		return (List<DocumentoAllegatoType>) this.getSqlMapClientTemplate()
				.queryForList("getDocumentiEsitoLotto", map);
	}

}
