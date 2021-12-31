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
package it.maggioli.eldasoft.sil.gare.bl;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.gare.db.dao.BandiDao;
import it.maggioli.eldasoft.sil.gare.db.dao.CataloghiDao;
import it.maggioli.eldasoft.sil.gare.ws.ArticoloType;
import it.maggioli.eldasoft.sil.gare.ws.BandoIscrizioneType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaCatalogoType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.ProdottoType;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

/**
 * @author Stefano.Sabbadin
 */
public class CataloghiManager {

	private static final int GENERE_CATALOGO = 20;

	Logger logger = Logger.getLogger(CataloghiManager.class);

	private CataloghiDao cataloghiDao;
	private BandiDao bandiDao;

	/**
	 * @param cataloghiDao
	 *            the cataloghiDao to set
	 */
	public void setCataloghiDao(CataloghiDao cataloghiDao) {
		this.cataloghiDao = cataloghiDao;
	}

	/**
	 * @param bandiDao
	 *            the bandiDao to set
	 */
	public void setBandiDao(BandiDao bandiDao) {
		this.bandiDao = bandiDao;
	}

	public List<BandoIscrizioneType> searchCataloghi(String token, Integer stato, 
			String stazioneAppaltante) {
		return this.bandiDao.searchBandiIscrizione(token, GENERE_CATALOGO,
				stato, stazioneAppaltante);
	}

	public List<BandoIscrizioneType> getElencoCataloghi(String stazioneAppaltante) {
		return this.bandiDao.getElencoBandiIscrizione(GENERE_CATALOGO, stazioneAppaltante);
	}

	/**
	 * Ritorna l'elenco degli articoli filtrati in base ai parametri in input.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param tokenRichiedente
	 *            token richiedente
	 * @param codiceCategoria
	 *            eventuale codice categoria
	 * @param codice
	 *            eventuale codice articolo
	 * @param tipo
	 *            eventuale tipo articolo
	 * @param descrizione
	 *            eventuale descrizione
	 * @param colore
	 *            eventuale colore
	 * @param indicePrimoRecord
	 *            indice del primo record da considerare, a partire da 0
	 * @param maxNumRecord
	 *            numero massimo di record da estrarre, 0 per estrarli tutti
	 * 
	 * @return lista articoli, di tipo ArticoloType
	 * @throws SqlComposerException
	 */
	public List<ArticoloType> searchArticoli(String codiceCatalogo,
			String tokenRichiedente, String codiceCategoria, String codice,
			String tipo, String descrizione, String colore,
			int indicePrimoRecord, int maxNumRecord)
			throws SqlComposerException {
		return this.cataloghiDao.searchArticoli(codiceCatalogo,
				tokenRichiedente, codiceCategoria, codice, tipo, descrizione,
				colore, indicePrimoRecord, maxNumRecord);
	}

	/**
	 * Ritorna il numero di articoli filtrati in base ai parametri in input.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param tokenRichiedente
	 *            token richiedente
	 * @param codiceCategoria
	 *            eventuale codice categoria
	 * @param codice
	 *            eventuale codice articolo
	 * @param tipo
	 *            eventuale tipo articolo
	 * @param descrizione
	 *            eventuale descrizione
	 * @param colore
	 *            eventuale colore
	 * 
	 * @return numero di articoli
	 * @throws SqlComposerException
	 */
	public Integer countSearchArticoli(String codiceCatalogo,
			String tokenRichiedente, String codiceCategoria, String codice,
			String tipo, String descrizione, String colore)
			throws SqlComposerException {
		return this.cataloghiDao.countSearchArticoli(codiceCatalogo,
				tokenRichiedente, codiceCategoria, codice, tipo, descrizione, colore);
	}

	/**
	 * Estrae il dettaglio di un articolo.
	 * 
	 * @param id
	 *            id articolo
	 * @return dettaglio
	 */
	public ArticoloType getArticolo(long id) {
		return this.cataloghiDao.getArticolo(id);
	}

	public List<DocumentoAllegatoType> getDocumentiArticolo(long id) {
		return this.cataloghiDao.getDocumentiArticolo(id);
	}

	public Long getNumProdottiOEInArticolo(long idArticolo,
			String tokenRichiedente) {
		return this.cataloghiDao.getNumProdottiOEInArticolo(idArticolo,
				tokenRichiedente);
	}

	public Long getNumProdottiAltriOEInArticolo(long idArticolo,
			String tokenRichiedente) {
		return this.cataloghiDao.getNumProdottiAltriOEInArticolo(idArticolo,
				tokenRichiedente);
	}

	public Double getPrezzoMiglioreArticolo(long idArticolo) {
		return this.cataloghiDao.getPrezzoMiglioreArticolo(idArticolo);
	}

	public Boolean isImpresaAbilitataCatalogo(String codiceCatalogo,
			String tokenRichiedente) {
		return this.cataloghiDao.isImpresaAbilitataCatalogo(codiceCatalogo,
				tokenRichiedente);
	}

	/**
	 * Ritorna l'elenco dei prodotti paginati e filtrati in base ai parametri in input.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param idArticolo
	 *            id univoco dell'articolo
	 * @param tokenRichiedente
	 *            identificativo richiedente
	 * @param terminiRicerca
	 *            termini di ricerca da applicare
	 * @param indicePrimoRecord
	 *            indice del primo record da considerare, a partire da 0
	 * @param maxNumRecord
	 *            numero massimo di record da estrarre, 0 per estrarli tutti
	 * 
	 * @return lista prodotti, di tipo ProdottoType
	 * @throws SqlComposerException 
	 * @throws DataAccessException 
	 */
	public List<ProdottoType> searchProdotti(String codiceCatalogo,
			Long idArticolo, String tokenRichiedente, String terminiRicerca,
			int indicePrimoRecord, int maxNumRecord) throws DataAccessException, SqlComposerException {
		return this.cataloghiDao.searchProdotti(codiceCatalogo, idArticolo,
				tokenRichiedente, terminiRicerca, indicePrimoRecord,
				maxNumRecord);
	}

	/**
	 * Ritorna il numero di prodotti filtrati in base ai parametri in input.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param idArticolo
	 *            id univoco dell'articolo
	 * @param tokenRichiedente
	 *            identificativo richiedente
	 * @param terminiRicerca
	 *            termini di ricerca da applicare
	 * 
	 * @return numero di prodotti
	 * @throws SqlComposerException
	 * @throws DataAccessException
	 */
	public Integer countSearchProdotti(String codiceCatalogo, Long idArticolo,
			String tokenRichiedente, String terminiRicerca)
			throws DataAccessException, SqlComposerException {
		return this.cataloghiDao.countSearchProdotti(codiceCatalogo,
				idArticolo, tokenRichiedente, terminiRicerca);
	}

	/**
	 * Ritorna l'elenco dei prodotti paginati e filtrati in base ai parametri in input.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param idArticolo
	 *            id univoco dell'articolo
	 * @param tokenRichiedente
	 *            identificativo richiedente
	 * @param tipoArticolo
	 *            tipo di articolo, bene o servizio
	 * @param descrizioneArticolo
	 *            descrizione articolo
	 * @param coloreArticolo
	 *            colore articolo
	 * @param codiceProdottoFornitore
	 *            codice prodotto attribuito dal fornitore
	 * @param nomeCommerciale
	 *            nome commerciale del prodotto
	 * @param descrizioneAggiuntiva
	 *            descrizione aggiuntiva del prodotto
	 * @param statoProdotto
	 *            stato del prodotto
	 * @param indicePrimoRecord
	 *            indice del primo record da considerare, a partire da 0
	 * @param maxNumRecord
	 *            numero massimo di record da estrarre, 0 per estrarli tutti
	 * 
	 * @return lista prodotti, di tipo ProdottoType
	 * @throws SqlComposerException 
	 * @throws DataAccessException 
	 */
	public List<ProdottoType> advancedSearchProdotti(String codiceCatalogo,
			Long idArticolo, String tokenRichiedente, String tipoArticolo,
			String descrizioneArticolo, String coloreArticolo,
			String codiceProdottoFornitore, String nomeCommerciale,
			String descrizioneAggiuntiva, String statoProdotto,
			int indicePrimoRecord, int maxNumRecord) throws DataAccessException, SqlComposerException {
		return this.cataloghiDao.advancedSearchProdotti(codiceCatalogo,
				idArticolo, tokenRichiedente, tipoArticolo,
				descrizioneArticolo, coloreArticolo, codiceProdottoFornitore,
				nomeCommerciale, descrizioneAggiuntiva, statoProdotto,indicePrimoRecord, maxNumRecord);
	}

	/**
	 * Ritorna il numero di prodotti filtrati in base ai parametri in input.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param idArticolo
	 *            id univoco dell'articolo
	 * @param tokenRichiedente
	 *            identificativo richiedente
	 * @param tipoArticolo
	 *            tipo di articolo, bene o servizio
	 * @param descrizioneArticolo
	 *            descrizione articolo
	 * @param coloreArticolo
	 *            colore articolo
	 * @param codiceProdottoFornitore
	 *            codice prodotto attribuito dal fornitore
	 * @param nomeCommerciale
	 *            nome commerciale del prodotto
	 * @param descrizioneAggiuntiva
	 *            descrizione aggiuntiva del prodotto
	 * @param statoProdotto
	 *            stato del prodotto
	 * 
	 * @return numero di prodotti
	 * @throws SqlComposerException
	 * @throws DataAccessException
	 */
	public Integer countAdvancedSearchProdotti(String codiceCatalogo,
			Long idArticolo, String tokenRichiedente, String tipoArticolo,
			String descrizioneArticolo, String coloreArticolo,
			String codiceProdottoFornitore, String nomeCommerciale,
			String descrizioneAggiuntiva, String statoProdotto)
			throws DataAccessException, SqlComposerException {
		return this.cataloghiDao.countAdvancedSearchProdotti(codiceCatalogo,
				idArticolo, tokenRichiedente, tipoArticolo,
				descrizioneArticolo, coloreArticolo, codiceProdottoFornitore,
				nomeCommerciale, descrizioneAggiuntiva, statoProdotto);
	}

	/**
	 * Estrae il dettaglio di un prodotto.
	 * 
	 * @param id
	 *            id prodotto
	 * @return dettaglio
	 */
	public ProdottoType getProdotto(long id) {
		return this.cataloghiDao.getProdotto(id);
	}

	/**
	 * Estrae il riferimento all'immagine di un prodotto.
	 * 
	 * @param id
	 *            id prodotto
	 * @return riferimento al documento contenente l'immagine
	 */
	public DocumentoAllegatoType getImmagine(long id) {
		DocumentoAllegatoType risultato = null;
		List<DocumentoAllegatoType> lista = this.cataloghiDao
				.getAllegatiProdotto(id, 1);
		if (lista != null && lista.size() == 1) {
			risultato = lista.get(0);
		}
		return risultato;
	}

	/**
	 * Estrae i riferimenti alle certificazioni del prodotto.
	 * 
	 * @param id
	 *            id prodotto
	 * @return riferimento ai documenti allegati
	 */
	public List<DocumentoAllegatoType> getCertificazioni(long id) {
		return this.cataloghiDao.getAllegatiProdotto(id, 2);
	}

	/**
	 * Estrae i riferimenti alle schede tecniche del prodotto.
	 * 
	 * @param id
	 *            id prodotto
	 * @return riferimento ai documenti allegati
	 */
	public List<DocumentoAllegatoType> getSchedeTecniche(long id) {
		return this.cataloghiDao.getAllegatiProdotto(id, 3);
	}

	public List<CategoriaCatalogoType> getCategorieArticoliOE(
			String codiceCatalogo, String tokenRichiedente)
			throws SqlComposerException {
		return this.getCategorieIscritte(codiceCatalogo, tokenRichiedente,
				null, null, null, null);
	}

	private List<CategoriaCatalogoType> getCategorieIscritte(
			String codiceCatalogo, String tokenRichiedente, String livello1,
			String livello2, String livello3, String livello4)
			throws SqlComposerException {
		List<CategoriaCatalogoType> risultato = new ArrayList<CategoriaCatalogoType>();

		List<CategoriaCatalogoType> lista = this.cataloghiDao
				.getCategorieIscritte(codiceCatalogo, tokenRichiedente,
						livello1, livello2, livello3, livello4);
		for (CategoriaCatalogoType cat : lista) {
			boolean figliVuoti = true;
			if (!cat.isFoglia()) {
				String catLivello1 = livello1;
				String catLivello2 = livello2;
				String catLivello3 = livello3;
				String catLivello4 = livello4;
				if (catLivello1 == null)
					catLivello1 = cat.getCodice();
				else if (catLivello2 == null)
					catLivello2 = cat.getCodice();
				else if (catLivello3 == null)
					catLivello3 = cat.getCodice();
				else if (catLivello4 == null)
					catLivello4 = cat.getCodice();

				// ricorsione
				List<CategoriaCatalogoType> listaFigli = this
						.getCategorieIscritte(codiceCatalogo, tokenRichiedente,
								catLivello1, catLivello2, catLivello3,
								catLivello4);

				// non appena ho figli da includere, prima includo la radice se
				// non l'ho inclusa in precedenza, e quindi accodo i dati
				// estratti
				if (listaFigli.size() > 0) {
					if (figliVuoti) {
						risultato.add(cat);
						figliVuoti = false;
					}
					risultato.addAll(listaFigli);
				}
			}
			// una volta considerato il risultato nel sottoalbero, si considera
			// il nodo verificando l'esistenza di articoli
			List<ArticoloType> listaArticoli = this.cataloghiDao
					.searchArticoli(codiceCatalogo, null, cat.getCodice(), null,
							null, null, null, 0, 0);
			cat.setArticoli(listaArticoli);

			if (figliVuoti) {
				if (listaArticoli.size() > 0) {
					// non e' stato aggiunto il nodo in precedenza, per cui si
					// aggiunge ora
					risultato.add(cat);
				}
			}
		}
		// // caso base di ricorsione: mantengo la foglia solo se ha articoli
		// // oppure mantengo il solo sottoalbero di categorie che contiene
		// // articoli
		// if (risultato.size() == 0) {
		// risultato = null;
		// }

		return risultato;
	}
}
