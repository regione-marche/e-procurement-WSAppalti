/*
 * Created on 15/giu/12
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
import it.maggioli.eldasoft.sil.gare.ws.AvvisoType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;
import it.maggioli.eldasoft.sil.gare.ws.search.AvvisiTypeSearch;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Interfaccia DAO per l'accesso alle informazioni relative agli avvisi nel web
 * service per il portale.
 * 
 * @author Stefano.Sabbadin
 */
public interface AvvisiDao {

	/**
	 * Ritorna l'elenco degli esiti di gara filtrati dai parametri in input.
	 * 
	 * @return lista di avvisi, di tipo {@link AvvisoType}
	 * 
	 * @throws DataAccessException
	 * @throws SqlComposerException
	 */
	List<AvvisoType> searchAvvisi(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException;

	int countAvvisi(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException;

	/**
	 * Ritorna l'elenco degli avvisi di gara.
	 * 
	 * @return lista di avvisi, di tipo {@link AvvisoType}
	 * 
	 * @throws DataAccessException
	 */
	List<AvvisoType> getElencoAvvisi(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException;
	
	int countElencoAvvisi(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException;

	/**
	 * Ritorna l'elenco degli avvisi di gara scaduti.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuto pubblicato l'avviso
	 * @return lista di avvisi scaduti, di tipo {@link AvvisoType}
	 * 
	 * @throws DataAccessException
	 */
	List<AvvisoType> getElencoAvvisiScaduti(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException;
	
	int countElencoAvvisiScaduti(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException;
	
    /**
     * Ritorna i dati della stazione appaltante e del rup a partire dal bando in
     * input
     * 
     * @param codice
     *            codice univoco del bando
     * @return dettaglio della stazione appaltante e nome del rup
     * @throws DataAccessException
     */
	StazioneAppaltanteBandoType getStazioneAppaltanteAvviso(String codice)
	    throws DataAccessException;

    /**
     * Ritorna il dettaglio dei dati di un avviso di gara individuato dalla
     * chiave in input.
     * 
     * @param codiceGara
     *            codice univoco del bando di gara
     * @return dettaglio dell'avviso
     * @throws DataAccessException
     */
	AvvisoType getAvviso(String codiceGara) throws DataAccessException;

//	/**
//	 * 
//	 */
//	List<DocumentoAllegatoType> getDocumentiAvvisoLotto(String codiceGara,
//			String codiceLotto);
	
}
