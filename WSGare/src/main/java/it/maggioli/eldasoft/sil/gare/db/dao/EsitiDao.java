/*
 * Created on 08/giu/12
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
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.EsitoType;
import it.maggioli.eldasoft.sil.gare.ws.LottoEsitoType;
import it.maggioli.eldasoft.sil.gare.ws.LottoGaraType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;
import it.maggioli.eldasoft.sil.gare.ws.search.EsitoTypeSearch;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Interfaccia DAO per l'accesso alle informazioni relative agli esiti nel web
 * service per il portale.
 * 
 * @author Stefano.Sabbadin
 */
public interface EsitiDao {

	/**
	 * Ritorna l'elenco degli esiti di gara filtrati dai parametri in input.
	 * 
	 * @return lista di esiti, di tipo {@link EsitoType}
	 * 
	 * @throws DataAccessException
	 * @throws SqlComposerException
	 */
	List<EsitoType> searchEsiti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException;
	
	int countEsiti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException;

	/**
	 * Ritorna l'elenco degli esiti di gara.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuta la procedura pubblicata
	 * @return lista di esiti, di tipo {@link EsitoType}
	 * 
	 * @throws DataAccessException
	 */
	List<EsitoType> getElencoEsiti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException;
	
	int countElencoEsiti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException;

	/**
	 * Ritorna l'elenco degli esiti affidamenti di un elenco.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuta la procedura pubblicata
	 * @return lista di esiti, di tipo {@link EsitoType}
	 * 
	 * @throws DataAccessException
	 */
	List<EsitoType> getElencoEsitiAffidamenti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException;
	
	int countElencoEsitiAffidamenti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException;

	/**
     * Ritorna i dati della stazione appaltante e del rup a partire dal bando in
     * input
     * 
     * @param codice
     *            codice univoco del bando
     * @return dettaglio della stazione appaltante e nome del rup
     * @throws DataAccessException
     */
	StazioneAppaltanteBandoType getStazioneAppaltanteEsito(String codice)
	    throws DataAccessException;

    /**
     * Ritorna il dettaglio dei dati di un esito di gara individuato dalla
     * chiave in input.
     * 
     * @param codiceGara
     *            codice univoco del bando di gara
     * @return dettaglio dell'esito
     * @throws DataAccessException
     */
	EsitoType getEsito(String codiceGara) throws DataAccessException;

    /**
     * Ritorna l'elenco dei lotti di gara per cui &egrave; stato pubblicato un esito
     * 
     * @param codiceGara
     *            codice univoco del bando di gara
     * @return lista dei lotti, di tipo {@link LottoGaraType}
     * @throws DataAccessException
     */
    List<LottoGaraType> getLottiGaraConEsito(String codiceGara)
	    throws DataAccessException;
    
    /** Ottimizzazione getDettaglioEsito() */
	List<DocumentoAllegatoLotto> getDocumentiLottiGaraConEsito(String codiceGara)
		throws DataAccessException;
    
    /**
     * Ritorna l'elenco dei lotti di gara dell'esito di gara.
     * 
     * @param codiceGara
     *            codice univoco del bando di gara
     * @return lista dei lotti, di tipo {@link LottoEsitoType}
     * @throws DataAccessException
     */
	List<LottoEsitoType> getLottiEsito(String codiceGara) throws DataAccessException;
	
	/**
     * Restituisce un intero che indica se si tratta di un accordo quadro con aggiudicatari multipli
     * 
     * @param codiceGara
     *            codice univoco del bando di gara
     * @return true nel caso in cui si trattassa di accordo quadro con aggiudicatari multipli, false altrimenti
     */
	boolean isAQAggiudicatariMultipli(String codice);
	
	/**
     * Restituisce la lista degli aggiudicatari in caso di accordo quadro con aggiudicatari multipli
     * 
     * @param codiceLotto
     *            codice univoco del lotto di gara
     * @return la lista degli aggiudicatari
     */
	List<String> getAQAggiudicatariMultipli(String codiceLotto);
	
	/**
     * Restituisce l'unica aggiudicataria del lotto di gara
     * 
     * @param codiceLotto          
     *            codice univoco del lotto di gara
     * @return la lista degli aggiudicatari
     */
	List<String> getAggiudicatariaSingola(String codiceLotto);
	
	/**
	 * ...
	 */
	List<DocumentoAllegatoType> getDocumentiEsitoLotto(String codiceGara,
			String codiceLotto);

}
