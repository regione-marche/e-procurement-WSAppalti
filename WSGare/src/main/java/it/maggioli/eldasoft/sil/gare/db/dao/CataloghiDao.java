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

package it.maggioli.eldasoft.sil.gare.db.dao;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.gare.ws.ArticoloType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaCatalogoType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.ProdottoType;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * Interfaccia DAO per l'accesso alle informazioni relative ai cataloghi
 * elettronici nel web service per il portale.
 * 
 * @author Stefano.Sabbadin
 */
public interface CataloghiDao {

	/**
	 * Ritorna l'elenco degli articoli di un catalogo filtrati dai parametri in
	 * input.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param username
	 *            identificativo richiedente
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
	 * @return lista degli articoli, di tipo {@link ArticoloType}
	 * 
	 * @throws DataAccessException
	 * @throws SqlComposerException
	 */
	List<ArticoloType> searchArticoli(String codiceCatalogo, String username,
			String codiceCategoria, String codice, String tipo,
			String descrizione, String colore, int indicePrimoRecord,
			int maxNumRecord) throws DataAccessException, SqlComposerException;

	/**
	 * Ritorna il numero totale di articoli di un catalogo filtrati dai
	 * parametri in input.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param username
	 *            identificativo richiedente
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
	 * @return numero totale di articoli
	 * 
	 * @throws DataAccessException
	 * @throws SqlComposerException
	 */
	Integer countSearchArticoli(String codiceCatalogo, String username,
			String codiceCategoria, String codice, String tipo,
			String descrizione, String colore) throws DataAccessException,
			SqlComposerException;

	/**
	 * Ritorna l'articolo.
	 * 
	 * @param id
	 *            id articolo
	 * @return dettaglio articolo
	 * @throws DataAccessException
	 */
	ArticoloType getArticolo(long id) throws DataAccessException;

	/**
	 * Ritorna le schede tecniche dell'articolo.
	 * 
	 * @param id
	 *            id articolo
	 * @param tipoDoc
	 * 			  tipo documento 1=fac simile certificazione, 2=altri allegati  
	 * @return lista delle schede tecniche
	 * @throws DataAccessException
	 */
	List<DocumentoAllegatoType> getDocumentiArticolo(long id, int tipoDoc)
			throws DataAccessException;

	/**
	 * Ritorna il numero di prodotti a catalogo o in attesa di verifica di un
	 * operatore economico per un determinato articolo.
	 * 
	 * @param idArticolo
	 *            id articolo
	 * @param username
	 *            username dell'impresa
	 * @return numero di prodotti
	 * @throws DataAccessException
	 */
	Long getNumProdottiOEInArticolo(long idArticolo, String username)
			throws DataAccessException;

	/**
	 * Ritorna il numero di prodotti a catalogo per l'articolo, di altri
	 * operatori economici diverso dall'operatore in input.
	 * 
	 * @param idArticolo
	 *            id articolo
	 * @param username
	 *            username dell'impresa
	 * @return numero di prodotti
	 * @throws DataAccessException
	 */
	Long getNumProdottiAltriOEInArticolo(long idArticolo, String username)
			throws DataAccessException;

	/**
	 * Estrae il prezzo pi&ugrave; basso offerto tra tutti gli operatori
	 * economici per prodotti in catalogo per un dato articolo.
	 * 
	 * @param idArticolo
	 *            id articolo
	 * @return prezzo inferiore
	 * @throws DataAccessException
	 */
	Double getPrezzoMiglioreArticolo(long idArticolo)
			throws DataAccessException;

	/**
	 * Verifica se un operatore economico risulta abilitato per un dato
	 * catalogo.
	 * 
	 * @param codiceCatalogo
	 *            codice identificativo del catalogo
	 * @param username
	 *            username dell'impresa
	 * @return true se l'operatore economico risulta abilitato, false altrimenti
	 * @throws DataAccessException
	 */
	Boolean isImpresaAbilitataCatalogo(String codiceCatalogo, String username)
			throws DataAccessException;

	/**
	 * Ritorna l'elenco dei prodotti di un articolo di catalogo filtrati dai
	 * parametri in input.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param idArticolo
	 *            id univoco dell'articolo
	 * @param username
	 *            identificativo richiedente
	 * @param terminiRicerca
	 *            termini di ricerca da applicare
	 * @param indicePrimoRecord
	 *            indice del primo record da considerare, a partire da 0
	 * @param maxNumRecord
	 *            numero massimo di record da estrarre, 0 per estrarli tutti
	 * 
	 * @return lista dei prodotti, di tipo {@link ProdottoType}
	 * 
	 * @throws DataAccessException
	 * @throws SqlComposerException
	 */
	List<ProdottoType> searchProdotti(String codiceCatalogo, Long idArticolo,
			String username, String terminiRicerca, int indicePrimoRecord, int maxNumRecord) throws DataAccessException,
			SqlComposerException;

	/**
	 * Ritorna il numero totale di prodotti di un articolo in catalogo filtrati dai parametri in
	 * input.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param idArticolo
	 *            id univoco dell'articolo
	 * @param username
	 *            identificativo richiedente
	 * @param terminiRicerca
	 *            termini di ricerca da applicare
	 * 
	 * @return numero totale di prodotti
	 * 
	 * @throws DataAccessException
	 * @throws SqlComposerException
	 */
	Integer countSearchProdotti(String codiceCatalogo, Long idArticolo,
			String username, String terminiRicerca) throws DataAccessException,
			SqlComposerException;

	/**
	 * Ritorna l'elenco dei prodotti di un articolo di catalogo filtrati dai
	 * parametri in input.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param idArticolo
	 *            id univoco dell'articolo
	 * @param username
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
	 * @return lista dei prodotti, di tipo {@link ProdottoType}
	 * 
	 * @throws DataAccessException
	 * @throws SqlComposerException
	 */
	List<ProdottoType> advancedSearchProdotti(String codiceCatalogo,
			Long idArticolo, String username, String tipoArticolo,
			String descrizioneArticolo, String coloreArticolo,
			String codiceProdottoFornitore, String nomeCommerciale,
			String descrizioneAggiuntiva, String statoProdotto,
			int indicePrimoRecord, int maxNumRecord) throws DataAccessException,
			SqlComposerException;

	/**
	 * Ritorna il numero di prodotti filtrati in base ai parametri in input.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param idArticolo
	 *            id univoco dell'articolo
	 * @param username
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
	Integer countAdvancedSearchProdotti(String codiceCatalogo, Long idArticolo,
			String username, String tipoArticolo,
			String descrizioneArticolo, String coloreArticolo,
			String codiceProdottoFornitore, String nomeCommerciale,
			String descrizioneAggiuntiva, String statoProdotto) throws DataAccessException,
			SqlComposerException;

	/**
	 * Ritorna il prodotto.
	 * 
	 * @param id
	 *            id prodotto
	 * @return dettaglio prodotto
	 * @throws DataAccessException
	 */
	ProdottoType getProdotto(long id) throws DataAccessException;

	/**
	 * Estrae i riferimenti agli allegati di un prodotto.
	 * 
	 * @param id
	 *            id prodotto
	 * @param tipoDocumento
	 *            tipologia di documento allegato
	 *            <ul>
	 *            <li><b>1</b>: immagine (un unico file)</li>
	 *            <li><b>2</b>: certificazioni richieste</li>
	 *            <li><b>3</b>: schede tecniche)</li>
	 *            </ul>
	 * @return riferimento ai documenti individuati
	 * @throws DataAccessException
	 */
	List<DocumentoAllegatoType> getAllegatiProdotto(long id, int tipoDocumento)
			throws DataAccessException;

	/**
	 * Estrae le categorie per cui un operatore economico risulta iscritto a
	 * partire dal catalogo in input e considerando i filtri sui livelli. I
	 * filtri sui livelli servono per la gestione di chiamate ricorsive e
	 * ricostruire l'eventuale albero delle categorie.
	 * 
	 * @param codiceCatalogo
	 *            codice del catalogo
	 * @param username
	 *            username dell'impresa
	 * @param livello1
	 *            codice categoria di livello 1 dell'albero, null altrimenti
	 * @param livello2
	 *            codice categoria di livello 2 dell'albero, null altrimenti
	 * @param livello3
	 *            codice categoria di livello 3 dell'albero, null altrimenti
	 * @param livello4
	 *            codice categoria di livello 4 dell'albero, null altrimenti
	 * @return lista delle categorie del catalogo figlie dei livelli indicati,
	 *         per l'impresa iscritta al catalogo
	 * @throws DataAccessException
	 */
	List<CategoriaCatalogoType> getCategorieIscritte(String codiceCatalogo,
			String username, String livello1, String livello2, String livello3,
			String livello4) throws DataAccessException;

}
