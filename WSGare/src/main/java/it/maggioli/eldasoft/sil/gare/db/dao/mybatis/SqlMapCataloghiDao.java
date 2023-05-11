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

package it.maggioli.eldasoft.sil.gare.db.dao.mybatis;

import it.eldasoft.utils.sql.comp.SqlComposer;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.eldasoft.utils.sql.comp.SqlManager;
import it.maggioli.eldasoft.sil.gare.db.dao.CataloghiDao;
import it.maggioli.eldasoft.sil.gare.db.mapper.CataloghiMapper;
import it.maggioli.eldasoft.sil.gare.ws.ArticoloType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaCatalogoType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.ProdottoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static it.maggioli.eldasoft.sil.gare.util.DAOUtils.*;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione dei cataloghi elettronici.
 * 
 * @author Stefano.Sabbadin
 */
@Component
public class SqlMapCataloghiDao implements CataloghiDao {

	/** tipologia di DBMS da property */
	@Resource
	private String dbms;

	@Autowired
	private CataloghiMapper cataloghiMapper;

	@Override
	public List<ArticoloType> searchArticoli(String codiceCatalogo,
			String username, String codiceCategoria, String codice,
			String tipo, String descrizione, String colore,
			int indicePrimoRecord, int maxNumRecord)
			throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return cataloghiMapper.searchArticoli(
				  username
				, codiceCatalogo
				, codiceCategoria
				, codice
				, tipo
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(descrizione)
				, toWithPercentAndUpper(colore)
				, getBounds(indicePrimoRecord, maxNumRecord)
		);
	}
	@Override
	public Integer countSearchArticoli(String codiceCatalogo, String username,
			String codiceCategoria, String codice, String tipo,
			String descrizione, String colore) throws DataAccessException,
			SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return cataloghiMapper.countSearchArticoli(
				  username
				, codiceCatalogo
				, codiceCategoria
				, codice
				, tipo
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(descrizione)
				, toWithPercentAndUpper(colore)
		);
	}
	@Override
	public ArticoloType getArticolo(long id) throws DataAccessException {
		return cataloghiMapper.getArticolo(id);
	}
	@Override
	public List<DocumentoAllegatoType> getDocumentiArticolo(long id, int tipoDoc)
			throws DataAccessException {
		return cataloghiMapper.getDocumentiArticolo(
				  id
				, tipoDoc
		);
	}
	@Override
	public Long getNumProdottiOEInArticolo(long idArticolo, String username)
			throws DataAccessException {
		return cataloghiMapper.getNumProdottiOEInArticolo(
				  idArticolo
				, username
		);
	}
	@Override
	public Long getNumProdottiAltriOEInArticolo(long idArticolo, String username)
			throws DataAccessException {
		return cataloghiMapper.getNumProdottiAltriOEInArticolo(
				  idArticolo
				, username
		);
	}
	@Override
	public Double getPrezzoMiglioreArticolo(long idArticolo)
			throws DataAccessException {
		return cataloghiMapper.getPrezzoMiglioreArticolo(
				idArticolo
		);
	}
	@Override
	public Boolean isImpresaAbilitataCatalogo(String codiceCatalogo,
			String username) throws DataAccessException {
		return cataloghiMapper.isImpresaAbilitataCatalogo(
				  username
				, codiceCatalogo
		) > 0;
	}
	@Override
	public List<ProdottoType> searchProdotti(String codiceCatalogo, Long idArticolo,
			String username, String terminiRicerca, int indicePrimoRecord, int maxNumRecord) throws SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return cataloghiMapper.searchProdotti(
				  codiceCatalogo
				, "4"
				, username
				, idArticolo
				, getTerminiUtili(terminiRicerca)
				, composer.getFunzioneUpperCase()
				, getBounds(indicePrimoRecord, maxNumRecord)
		);
	}
	@Override
	public Integer countSearchProdotti(String codiceCatalogo, Long idArticolo,
			String username, String terminiRicerca)
			throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);
		return cataloghiMapper.countSearchProdotti(
				codiceCatalogo
				, "4"
				, username
				, idArticolo
				, getTerminiUtili(terminiRicerca)
				, composer.getFunzioneUpperCase()
		);
	}
	@Override
	public List<ProdottoType> advancedSearchProdotti(String codiceCatalogo,
			Long idArticolo, String username, String tipoArticolo,
			String descrizioneArticolo, String coloreArticolo,
			String codiceProdottoFornitore, String nomeCommerciale,
			String descrizioneAggiuntiva, String statoProdotto,
			int indicePrimoRecord, int maxNumRecord) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return cataloghiMapper.advancedSearchProdotti(
				codiceCatalogo
				, null
				, username
				, idArticolo
				, null
				, composer.getFunzioneUpperCase()
				, tipoArticolo
				, toWithPercentAndUpper(descrizioneArticolo)
				, toWithPercentAndUpper(coloreArticolo)
				, toWithPercentAndUpper(codiceProdottoFornitore)
				, toWithPercentAndUpper(nomeCommerciale)
				, toWithPercentAndUpper(descrizioneAggiuntiva)
				, statoProdotto
				, getBounds(indicePrimoRecord, maxNumRecord)
		);
	}
	@Override
	public Integer countAdvancedSearchProdotti(String codiceCatalogo,
			Long idArticolo, String username, String tipoArticolo,
			String descrizioneArticolo, String coloreArticolo,
			String codiceProdottoFornitore, String nomeCommerciale,
			String descrizioneAggiuntiva, String statoProdotto)
			throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return cataloghiMapper.countAdvancedSearchProdotti(
				codiceCatalogo
				, null
				, username
				, idArticolo
				, null
				, composer.getFunzioneUpperCase()
				, tipoArticolo
				, toWithPercentAndUpper(descrizioneArticolo)
				, toWithPercentAndUpper(coloreArticolo)
				, toWithPercentAndUpper(codiceProdottoFornitore)
				, toWithPercentAndUpper(nomeCommerciale)
				, toWithPercentAndUpper(descrizioneAggiuntiva)
				, statoProdotto
		);
	}
	@Override
	public ProdottoType getProdotto(long id) throws DataAccessException {
		return cataloghiMapper.getProdotto(
				id
		);
	}
	@Override
	public List<DocumentoAllegatoType> getAllegatiProdotto(long id,
			int tipoDocumento) throws DataAccessException {
		return cataloghiMapper.getAllegatiProdotto(
				  id
				, tipoDocumento
		);
	}
	@Override
	public List<CategoriaCatalogoType> getCategorieIscritte(
			String codiceCatalogo, String username, String livello1,
			String livello2, String livello3, String livello4)
			throws DataAccessException {
		return cataloghiMapper.getCategorieIscritte(
				codiceCatalogo
				, username
				, livello1
				, livello2
				, livello3
				, livello4
		);
	}
	private List<String> getTerminiUtili(String terminiRicerca) {
		List<String> risultato = new ArrayList<>();
		if (terminiRicerca != null) {
			String[] termini = terminiRicerca.split(" ");
			for (String termine : termini) {
				if (termine.length() >= 3) {
					risultato.add("%" + termine.toUpperCase() + "%" );
				}
			}
		}
		return risultato;
	}

}
