/*
 * Created on 25/ott/10
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di 
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.gene.db.dao;

import java.util.Date;
import java.util.List;

import it.maggioli.eldasoft.gene.ws.AllegatoComunicazioneType;
import it.maggioli.eldasoft.gene.ws.DettaglioComunicazioneType;
import it.maggioli.eldasoft.gene.ws.WSAllegatoType;
import it.maggioli.eldasoft.gene.ws.WSDocumentoType;

import org.springframework.dao.DataAccessException;

/**
 * Interfaccia DAO per la gestione crud delle comunicazioni (W_INVCOM e
 * W_DOCDIG)
 * 
 * @author Stefano.Sabbadin
 */
public interface ComunicazioniDao {

    /**
     * Ritorna il massimo id utilizzato come chiave nelle comunicazioni per
     * l'applicativo in input
     * 
     * @param applicativo
     *            identificativo applicativo
     * @return massimo id utilizzato ed esistente nel db nelle comunicazioni, 0
     *         altrimenti
     * @throws DataAccessException
     */
    Long getMaxIdComunicazione(String applicativo) throws DataAccessException;

    /**
     * Inserisce un'occorrenza nella W_INVCOM
     * 
     * @param comunicazione
     *            comunicazione da inserire
     * @throws DataAccessException
     */
    void insertComunicazione(DettaglioComunicazioneType comunicazione)
	    throws DataAccessException;

    /**
     * Aggiorna un'occorrenza nella W_INVCOM
     * 
     * @param comunicazione
     *            comunicazione da aggiornare
     * @throws DataAccessException
     */
    void updateComunicazione(DettaglioComunicazioneType dettaglioComunicazione)
	    throws DataAccessException;

    /**
     * Ritorna il massimo id utilizzato come chiave nei documenti digitali per
     * l'applicativo in input
     * 
     * @param applicativo
     *            identificativo applicativo
     * @return massimo id utilizzato nei documenti digitali ed esistente nel db,
     *         0 altrimenti
     * @throws DataAccessException
     */
    Long getMaxIdAllegato(String applicativo) throws DataAccessException;

	/**
     * Inserisce un'occorrenza nella W_DOCDIG
     * 
     * @param applicativo
     *            applicativo per cui inserire il documento
     * @param idAllegato
     *            chiave del documento allegato in coppia all'applicativo
     * @param idComunicazione
     *            chiave di collegamento alla comunicazione
     * @param progressivoAllegato
     *            progressivo di allegato per la comunicazione
     * @param allegato
     *            allegato digitale da inserire
     * @throws DataAccessException
     */
    void insertAllegato(String applicativo, Long idAllegato,
	    Long idComunicazione, int progressivoAllegato,
	    AllegatoComunicazioneType allegato) throws DataAccessException;

	/**
	 * Aggiorna un allegato esistente relativo alla comunicazione in input.
	 * 
	 * @param applicativo
	 *            applicativo per cui inserire il documento
	 * @param idAllegato
	 *            chiave del documento allegato in coppia all'applicativo
	 * @param idComunicazione
	 *            chiave di collegamento alla comunicazione
	 * @param progressivoAllegato
	 *            progressivo di allegato per la comunicazione
	 * @param allegato
	 *            allegato digitale da aggiornare
	 */
	void updateAllegato(String applicativo, Long idAllegato,
			Long idComunicazione, int progressivoAllegato,
			AllegatoComunicazioneType allegato);

	/**
	 * Elimina il singolo documento digitale individuato dalla sua chiave.
	 * 
	 * @param applicativo
	 *            applicativo per cui eliminare i documenti
	 * @param idAllegato
	 *            chiave del documento allegato in coppia all'applicativo
	 */
	void deleteAllegato(String applicativo, Long idAllegato);
	
	/**
	 * Elimina la protocollazione del singolo documento digitale individuato dalla sua chiave.
	 * 
	 * @param applicativo
	 *            applicativo per cui eliminare i documenti
	 * @param idAllegato
	 *            chiave del documento allegato in coppia all'applicativo
	 */
	void deleteWSAllegato(String applicativo, Long idAllegato);

    /**
     * Elimina tutti i documenti digitali collegati ad una comunicazione
     * 
     * @param applicativo
     *            applicativo per cui eliminare i documenti
     * @param idComunicazione
     *            chiave di collegamento alla comunicazione
     * @throws DataAccessException
     */
    void deleteAllegati(String applicativo, Long idComunicazione)
	    throws DataAccessException;

	/**
	 * Aggiorna le comunicazioni in input allo stato indicato.
	 * 
	 * @param comunicazioni
	 *            comunicazioni da aggiornare
	 * @param stato
	 *            nuovo stato da assegnare
     * @throws DataAccessException
	 */
	void updateStatoComunicazioni(DettaglioComunicazioneType[] comunicazioni,
			String stato) throws DataAccessException;

	/**
	 * Aggiorna la data lettura del destinatario della comunicazione con il
	 * timestamp corrente.
	 * 
	 * @param applicativo
	 *            id applicativo
	 * @param idComunicazione
	 *            id comunicazione
	 * @param idDestinatario
	 *            id destinatario
	 */
	void updateDataLetturaDestinatarioComunicazione(String applicativo,
			long idComunicazione, long idDestinatario);


	/**
	 * Elimina la testata di una comunicazione.
	 * 
	 * @param applicativo
	 *            id applicativo
	 * @param id
	 *            id comunicazione nell'applicativo
	 * @throws DataAccessException
	 */
	void deleteComunicazione(String applicativo, long id)
			throws DataAccessException;

	/**
     * Estrae i dati di testata di una comunicazione
     * 
     * @param applicativo
     *            id applicativo
     * @param id
     *            id comunicazione nell'applicativo
     * @return dettaglio della comunicazione presente nella tabella W_INVCOM
     * @throws DataAccessException
     */
    DettaglioComunicazioneType getDettaglioComunicazione(String applicativo,
	    long id) throws DataAccessException;

    /**
     * Estrae i documenti allegati di una comunicazione
     * 
     * @param applicativo
     *            id applicativo
     * @param id
     *            id comunicazione nell'applicativo
     * @param idDocumento
     *            id della busta xml o UUID del allegato allegato da scaricare
     *            (NOME_FILE_XXX o UUID)
     *            
     * @return documenti allegati della comunicazione, presenti nella tabella
     *         W_DOCDIG
     * @throws DataAccessException
     */
    List<AllegatoComunicazioneType> getAllegatiComunicazione(
	    String applicativo, long id, String idDocumento) throws DataAccessException;

    /**
     * Estrae l'elenco delle comunicazioni che soddisfano i criteri di ricerca
     * nel bean in input
     * 
     * @param criteriRicerca
     *            criteri di filtro, sulla W_INVCOM
     * @return elenco delle comunicazioni che rispettano i criteri di filtro in
     *         input
     * @throws DataAccessException
     */
    List<DettaglioComunicazioneType> getElencoComunicazioni(
	    DettaglioComunicazioneType criteriRicerca)
	    throws DataAccessException;

    /**
     * Verifica se la comunicazione in input si trova in uno stato di processata
     * dal backoffice
     * 
     * @param applicativo
     *            id applicativo
     * @param id
     *            id comunicazione nell'applicativo
     * @return true se la comunicazione e' stata processata, false altrimenti
     * @throws DataAccessException
     */
    Boolean isComunicazioneProcessata(String applicativo, long id)
	    throws DataAccessException;

    /**
     * Aggiorna una comunicazione attribuendo i dati di protocollazione.
	 * @param applicativo
	 *            id applicativo
	 * @param id
	 *            id comunicazione
	 * @param numeroProtocollo
	 *            numero di protocollo (obbligatorio) da attribuire
	 * @param dataProtocollo
	 *            data protocollo (obbligatoria) da attribuire
	 * @param stato
	 *            eventuale nuovo stato della comunicazione, da valorizzare solo
	 *            se si intende variare
     */
	void updateProtocolloComunicazione(String applicativo, long id,
			String numeroProtocollo, Date dataProtocollo, String stato);

	/**
	 * Inserisce un riferimento documentale.
	 * 
	 * @param idDocumento id del nuovo documento
	 * @param documento dati del documento
	 */
	void insertDocumentoProtocollo(long idDocumento, WSDocumentoType documento);

	/**
	 * Inserisce un allegato protocollato.
	 * 
	 * @param idAllegato id del nuovo allegato
	 * @param allegato dati dell'allegato
	 */
	void insertAllegatoProtocollo(long idDocumento, WSAllegatoType allegato);
	
	/**
	 * Elimina un allegato protocollato.
	 * 
	 * @param entita entita' a cui fa riferimento il documento
	 * @param applicativo applicativo
	 * @param id id dell'allegato
	 */
	void deleteAllegatoProtocollo(String entita, String applicativo, Long id);

	/**
	 * Aggiorna la chiave di sessione di una comunicazione.
	 * 
	 * @param applicativo
	 *            id applicativo
	 * @param id
	 *            id comunicazione
	 * @param sessionKey
	 *            chiave di cifratura
	 * @param stato
	 *            eventuale nuovo stato della comunicazione, da valorizzare solo
	 *            se si intende variare
	 */
	void updateSessionKeyComunicazione(String applicativo,
			long id, String sessionKey, String stato);

}
