/*
 * Created on 15/lug/12
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
import it.maggioli.eldasoft.sil.gare.db.dao.AvvisiDao;
import it.maggioli.eldasoft.sil.gare.ws.AvvisoType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione degli avvisi.
 * 
 * @author Stefano.Sabbadin
 */
public class SqlMapAvvisiDao extends SqlMapClientDaoSupport implements
		AvvisiDao {

	/** tipologia di DBMS da property */
	private String dbms;

	/**
	 * @param dbms
	 *            the dbms to set
	 */
	public void setDbms(String dbms) {
		this.dbms = dbms;
	}
	
	private HashMap<String, Object> prepareParamsAvvisi(
			String stazioneAppaltante,
			String oggetto, 
			Integer tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Integer altriSoggetti,
			int numAnniPubblicazione) throws SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);
	
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("operatoreUpper", composer.getFunzioneUpperCase());
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAvviso", tipoAvviso);
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("dataScadenzaDa", dataScadenzaDa);
		hash.put("dataScadenzaA", dataScadenzaA);
		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("altriSoggetti", altriSoggetti);
		return hash;
	}
	
	@SuppressWarnings("unchecked")
	public List<AvvisoType> searchAvvisi(
			String stazioneAppaltante,
			String oggetto, 
			Integer tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Integer altriSoggetti,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsAvvisi(
				stazioneAppaltante,
				oggetto, 
				tipoAvviso, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				altriSoggetti,
				numAnniPubblicazione);

		List<AvvisoType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<AvvisoType>) this.getSqlMapClientTemplate().queryForList(
							"searchAvvisi", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<AvvisoType>) this.getSqlMapClientTemplate().queryForList(
							"searchAvvisi", hash);
		}
		return risultato;
	}
	
	public int countAvvisi(
			String stazioneAppaltante,
			String oggetto, 
			Integer tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Integer altriSoggetti,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsAvvisi(
				stazioneAppaltante,
				oggetto, 
				tipoAvviso, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				altriSoggetti,
				numAnniPubblicazione);

		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countAvvisi", hash);
	}

//	@SuppressWarnings("unchecked")
//	public List<AvvisoType> getElencoAvvisi() throws DataAccessException {
//		return (List<AvvisoType>) this.getSqlMapClientTemplate().queryForList(
//				"getElencoAvvisi", new Date());
//	}

	private HashMap<String, Object> prepareParamsElencoAvvisi(
			String stazioneAppaltante,
			String oggetto, 
			Integer tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Integer altriSoggetti) throws SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);
	
		HashMap<String, Object> hash = new HashMap<String, Object>();		
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("operatoreUpper", composer.getFunzioneUpperCase());
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAvviso", tipoAvviso);
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("dataScadenzaDa", dataScadenzaDa);
		hash.put("dataScadenzaA", dataScadenzaA);
//		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("dataOdierna", new Date());
		hash.put("altriSoggetti", altriSoggetti);
		return hash;
	}
		
	@SuppressWarnings("unchecked")
	public List<AvvisoType> getElencoAvvisi(
			String stazioneAppaltante,
			String oggetto, 
			Integer tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Integer altriSoggetti,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoAvvisi(
				stazioneAppaltante,
				oggetto, 
				tipoAvviso, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				altriSoggetti);
		
		List<AvvisoType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<AvvisoType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoAvvisi", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<AvvisoType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoAvvisi", hash);
		}
		return risultato;
	}

	public int countElencoAvvisi(
			String stazioneAppaltante,
			String oggetto, 
			Integer tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Integer altriSoggetti) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoAvvisi(
				stazioneAppaltante,
				oggetto, 
				tipoAvviso, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				altriSoggetti);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countElencoAvvisi", hash);
	}
	
	
//	@SuppressWarnings("unchecked")
//	public List<AvvisoType> getElencoAvvisiScaduti(int numAnniPubblicazione) throws DataAccessException {
//		HashMap<String, Object> hash = new HashMap<String, Object>();
//		hash.put("dataOdierna", new Date());
//		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
//		return (List<AvvisoType>) this.getSqlMapClientTemplate().queryForList(
//				"getElencoAvvisiScaduti", hash);
//	}
	
	private HashMap<String, Object> prepareParamsEencoAvvisiScaduti(
			String stazioneAppaltante,
			String oggetto, 
			Integer tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Integer altriSoggetti,
			int numAnniPubblicazione) throws SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);
		
		HashMap<String, Object> hash = new HashMap<String, Object>();		
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("operatoreUpper", composer.getFunzioneUpperCase());
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAvviso", tipoAvviso);
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("dataScadenzaDa", dataScadenzaDa);
		hash.put("dataScadenzaA", dataScadenzaA);
		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("dataOdierna", new Date());
		hash.put("altriSoggetti", altriSoggetti);
		return hash;
	}

	@SuppressWarnings("unchecked")
	public List<AvvisoType> getElencoAvvisiScaduti(
			String stazioneAppaltante,
			String oggetto, 
			Integer tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Integer altriSoggetti,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsEencoAvvisiScaduti(
				stazioneAppaltante,
				oggetto, 
				tipoAvviso, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				altriSoggetti,
				numAnniPubblicazione);	

		List<AvvisoType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<AvvisoType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoAvvisiScaduti", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<AvvisoType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoAvvisiScaduti", hash);
		}
		return risultato;
	}
	
	public int countElencoAvvisiScaduti(
			String stazioneAppaltante,
			String oggetto, 
			Integer tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Integer altriSoggetti,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsEencoAvvisiScaduti(
				stazioneAppaltante,
				oggetto, 
				tipoAvviso, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				altriSoggetti,
				numAnniPubblicazione);	

		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countElencoAvvisiScaduti", hash);
	}
	
	public StazioneAppaltanteBandoType getStazioneAppaltanteAvviso(String codice)
			throws DataAccessException {
		return (StazioneAppaltanteBandoType) this.getSqlMapClientTemplate()
				.queryForObject("getStazioneAppaltanteAvviso", codice);
	}

	public AvvisoType getAvviso(String codiceGara) throws DataAccessException {
		return (AvvisoType) this.getSqlMapClientTemplate().queryForObject(
				"getAvviso", codiceGara);
	}

//	@SuppressWarnings("unchecked")
//	public List<DocumentoAllegatoType> getDocumentiAvvisoLotto(
//			String codiceGara, String codiceLotto) throws DataAccessException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("codiceGara", codiceGara);
//		map.put("codiceLotto", codiceLotto);
//		return (List<DocumentoAllegatoType>) this.getSqlMapClientTemplate()
//				.queryForList("getDocumentiAvvisoLotto", map);
//	}

}
