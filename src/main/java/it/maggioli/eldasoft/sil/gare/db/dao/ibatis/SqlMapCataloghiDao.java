/*
 * Created on 17/dic/2013
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
import it.maggioli.eldasoft.sil.gare.db.dao.CataloghiDao;
import it.maggioli.eldasoft.sil.gare.ws.ArticoloType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaCatalogoType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.ProdottoType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione dei cataloghi elettronici.
 * 
 * @author Stefano.Sabbadin
 */
public class SqlMapCataloghiDao extends SqlMapClientDaoSupport implements
		CataloghiDao {

	/** tipologia di DBMS da property */
	private String dbms;

	/**
	 * @param dbms
	 *            the dbms to set
	 */
	public void setDbms(String dbms) {
		this.dbms = dbms;
	}

	@SuppressWarnings("unchecked")
	public List<ArticoloType> searchArticoli(String codiceCatalogo,
			String username, String codiceCategoria, String codice,
			String tipo, String descrizione, String colore,
			int indicePrimoRecord, int maxNumRecord)
			throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("codiceCatalogo", codiceCatalogo);
		hash.put("username", username);
		hash.put("codiceCategoria", codiceCategoria);
		hash.put("codice", codice);
		hash.put("tipo", tipo);
		if (descrizione != null && descrizione.length() > 0) {
			hash.put("descrizione", "%" + descrizione.toUpperCase() + "%");
		}
		if (colore != null && colore.length() > 0) {
			hash.put("colore", "%" + colore.toUpperCase() + "%");
		}

		List<ArticoloType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<ArticoloType>) this.getSqlMapClientTemplate()
					.queryForList("searchArticoli", hash, indicePrimoRecord,
							maxNumRecord);
		} else {
			risultato = (List<ArticoloType>) this.getSqlMapClientTemplate()
					.queryForList("searchArticoli", hash);
		}
		return risultato;
	}
	
	public Integer countSearchArticoli(String codiceCatalogo, String username,
			String codiceCategoria, String codice, String tipo,
			String descrizione, String colore) throws DataAccessException,
			SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("codiceCatalogo", codiceCatalogo);
		hash.put("username", username);
		hash.put("codiceCategoria", codiceCategoria);
		hash.put("codice", codice);
		hash.put("tipo", tipo);
		if (descrizione != null && descrizione.length() > 0) {
			hash.put("descrizione", "%" + descrizione.toUpperCase() + "%");
		}
		if (colore != null && colore.length() > 0) {
			hash.put("colore", "%" + colore.toUpperCase() + "%");
		}

		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countSearchArticoli", hash);
	}

	public ArticoloType getArticolo(long id) throws DataAccessException {
		return (ArticoloType) this.getSqlMapClientTemplate().queryForObject(
				"getArticolo", id);
	}

	@SuppressWarnings("unchecked")
	public List<DocumentoAllegatoType> getDocumentiArticolo(long id)
			throws DataAccessException {
		return (List<DocumentoAllegatoType>) this.getSqlMapClientTemplate()
				.queryForList("getDocumentiArticolo", id);
	}

	public Long getNumProdottiOEInArticolo(long idArticolo, String username)
			throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("idArticolo", idArticolo);
		hash.put("username", username);
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"getNumProdottiOEInArticolo", hash);
	}

	public Long getNumProdottiAltriOEInArticolo(long idArticolo, String username)
			throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("idArticolo", idArticolo);
		hash.put("username", username);
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"getNumProdottiAltriOEInArticolo", hash);
	}

	public Double getPrezzoMiglioreArticolo(long idArticolo)
			throws DataAccessException {
		return (Double) this.getSqlMapClientTemplate().queryForObject(
				"getPrezzoMiglioreArticolo", idArticolo);
	}

	public Boolean isImpresaAbilitataCatalogo(String codiceCatalogo,
			String username) throws DataAccessException {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("codiceCatalogo", codiceCatalogo);
		hash.put("username", username);
		Integer numImprese = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("isImpresaAbilitataCatalogo", hash);
		return new Boolean(numImprese.intValue() > 0);
	}

	@SuppressWarnings("unchecked")
	public List<ProdottoType> searchProdotti(String codiceCatalogo, Long idArticolo,
			String username, String terminiRicerca, int indicePrimoRecord, int maxNumRecord) throws SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = createHashSearchProdotti(codiceCatalogo,
				idArticolo, username, terminiRicerca, composer);
		List<ProdottoType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<ProdottoType>) this.getSqlMapClientTemplate()
					.queryForList("searchProdotti", hash, indicePrimoRecord,
							maxNumRecord);
		} else {
			risultato = (List<ProdottoType>) this.getSqlMapClientTemplate()
					.queryForList("searchProdotti", hash);
		}
		return risultato;
	}

	private HashMap<String, Object> createHashSearchProdotti(
			String codiceCatalogo, Long idArticolo, String username,
			String terminiRicerca, SqlComposer composer) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("codiceCatalogo", codiceCatalogo);
		hash.put("idArticolo", idArticolo);
		// si cerca per i termini lunghi almeno 3 caratteri		
		List<String> termini = getTerminiUtili(terminiRicerca);
		hash.put("terminiRicerca", termini);
		hash.put("username", username);
		// nella ricerca veloce si considerano i soli prodotti in catalogo
		hash.put("stato", "4");
		return hash;
	}

	public Integer countSearchProdotti(String codiceCatalogo, Long idArticolo,
			String username, String terminiRicerca)
			throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = createHashSearchProdotti(codiceCatalogo,
				idArticolo, username, terminiRicerca, composer);

		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countSearchProdotti", hash);
	}

	private List<String> getTerminiUtili(String terminiRicerca) {
		List<String> risultato = new ArrayList<String>();
		if (terminiRicerca != null) {
			String[] termini = terminiRicerca.split(" ");
			for (int i = 0; i < termini.length; i++) {
				if (termini[i].length() >= 3) {
					risultato.add("%" + termini[i].toUpperCase() + "%");
				}
			}
		}
		return risultato;
	}


	@SuppressWarnings("unchecked")
	public List<ProdottoType> advancedSearchProdotti(String codiceCatalogo,
			Long idArticolo, String username, String tipoArticolo,
			String descrizioneArticolo, String coloreArticolo,
			String codiceProdottoFornitore, String nomeCommerciale,
			String descrizioneAggiuntiva, String statoProdotto,
			int indicePrimoRecord, int maxNumRecord) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = createHashAdvancedSearchProdotti(
				codiceCatalogo, idArticolo, username, tipoArticolo,
				descrizioneArticolo, coloreArticolo, codiceProdottoFornitore,
				nomeCommerciale, descrizioneAggiuntiva, statoProdotto, composer);
		
		List<ProdottoType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<ProdottoType>) this.getSqlMapClientTemplate()
					.queryForList("advancedSearchProdotti", hash, indicePrimoRecord,
							maxNumRecord);
		} else {
			risultato = (List<ProdottoType>) this.getSqlMapClientTemplate()
					.queryForList("advancedSearchProdotti", hash);
		}
		return risultato;
	}

	private HashMap<String, Object> createHashAdvancedSearchProdotti(
			String codiceCatalogo, Long idArticolo, String username,
			String tipoArticolo, String descrizioneArticolo,
			String coloreArticolo, String codiceProdottoFornitore,
			String nomeCommerciale, String descrizioneAggiuntiva,
			String statoProdotto, SqlComposer composer) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("codiceCatalogo", codiceCatalogo);
		hash.put("idArticolo", idArticolo);
		hash.put("username", username);
		if (StringUtils.isNotEmpty(tipoArticolo)) {
			hash.put("tipoArticolo", tipoArticolo);
		}
		if (StringUtils.isNotEmpty(descrizioneArticolo)) {
			hash.put("descrizioneArticolo", "%" + descrizioneArticolo.toUpperCase() + "%");
		}
		if (StringUtils.isNotEmpty(coloreArticolo)) {
			hash.put("coloreArticolo", "%" + coloreArticolo.toUpperCase() + "%");
		}
		if (StringUtils.isNotEmpty(codiceProdottoFornitore)) {
		hash.put("codiceProdottoFornitore", "%" + codiceProdottoFornitore.toUpperCase() + "%");
		}
		if (StringUtils.isNotEmpty(nomeCommerciale)) {
			hash.put("nomeCommerciale", "%" + nomeCommerciale.toUpperCase() + "%");
		}
		if (StringUtils.isNotEmpty(descrizioneAggiuntiva)) {
			hash.put("descrizioneAggiuntiva", "%" + descrizioneAggiuntiva.toUpperCase() + "%");
		}
		hash.put("statoProdotto", statoProdotto);
		return hash;
	}
	
	public Integer countAdvancedSearchProdotti(String codiceCatalogo,
			Long idArticolo, String username, String tipoArticolo,
			String descrizioneArticolo, String coloreArticolo,
			String codiceProdottoFornitore, String nomeCommerciale,
			String descrizioneAggiuntiva, String statoProdotto)
			throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = createHashAdvancedSearchProdotti(
				codiceCatalogo, idArticolo, username, tipoArticolo,
				descrizioneArticolo, coloreArticolo, codiceProdottoFornitore,
				nomeCommerciale, descrizioneAggiuntiva, statoProdotto, composer);

		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countAdvancedSearchProdotti", hash);
	}

	public ProdottoType getProdotto(long id) throws DataAccessException {
		return (ProdottoType) this.getSqlMapClientTemplate().queryForObject(
				"getProdotto", id);
	}

	@SuppressWarnings("unchecked")
	public List<DocumentoAllegatoType> getAllegatiProdotto(long id,
			int tipoDocumento) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("idProdotto", id);
		hash.put("tipoDocumento", tipoDocumento);
		return (List<DocumentoAllegatoType>) this.getSqlMapClientTemplate()
				.queryForList("getAllegatiProdotto", hash);
	}

	@SuppressWarnings("unchecked")
	public List<CategoriaCatalogoType> getCategorieIscritte(
			String codiceCatalogo, String username, String livello1,
			String livello2, String livello3, String livello4)
			throws DataAccessException {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("codiceCatalogo", codiceCatalogo);
		hash.put("username", username);
		hash.put("livello1", livello1);
		hash.put("livello2", livello2);
		hash.put("livello3", livello3);
		hash.put("livello4", livello4);
		return (List<CategoriaCatalogoType>) this.getSqlMapClientTemplate()
				.queryForList("getCategorieIscritte", hash);
	}

}
