package it.maggioli.eldasoft.sil.aste.db.dao;

import java.util.List;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.aste.ws.DettaglioAstaType;
import it.maggioli.eldasoft.sil.aste.ws.DettaglioClassificaType;
import it.maggioli.eldasoft.sil.aste.ws.DettaglioFaseAstaType;
import it.maggioli.eldasoft.sil.aste.ws.DettaglioRilancioType;
import it.maggioli.eldasoft.sil.aste.ws.VoceDettaglioAstaType;

/**
 * Interfaccia DAO per l'accesso alle informazioni relative ai bandi nel web
 * service per il portale
 * 
 * @author .......
 * 
 */
public interface AsteDao {

    /**
	 * Ritorna il tipo di classifica di un'asta
	 * 
	 * @param tipoClassifica tipo di classifica di un'asta
	 * @param codice codice della gara dell'asta
	 * @param codiceLotto codice del lotto di gara dell'asta
	 * @param username nome utente loggato 
	 * @param numeroFase fase dell'asta
	 * @param classificaAlRibasso 
	 * 		se True estrae la classifica sui ribassi d'offerta
	 * 	    se False estrae la classifica sui rialzi d'offerta
	 * 
	 * @return la lista della classifica
	 */
   	public List<DettaglioClassificaType> getClassifica(int tipoClassifica, String codice, String codiceLotto, String username, String numeroFase, boolean classificaAlRibasso) throws SqlComposerException;

   	/**
	 * Ritorna l'elenco delle fasi di un'asta
	 * 
	 * @param codice codice/codicelotto della gara dell'asta
	 * 
	 * @return elenco delle fasi di un'asta
	 * 
	 * @throws SqlComposerException 
	 */
	public List<DettaglioFaseAstaType> getFasiAsta(String codice) throws SqlComposerException;

	/**
	 * Ritorna il dettaglio di un'asta
	 * 
	 * @param codice codice/codicelotto dell'asta
	 * 
	 * @return il dettaglio di un'asta
	 * 
	 * @throws SqlComposerException 
	 */
    public DettaglioAstaType getDettaglioAsta(String codice) throws SqlComposerException;

    /**
	 * Restituisce l'elenco dei rilanci di un'asta per un dato utente
	 * 
	 * @param codice codice della gara dell'asta
	 * @param codiceLotto codice del lotto di gara dell'asta
	 * @param username nome dell'utente loggato
	 * @param numeroFase fase dell'asta 
	 * 
	 * @return l'elenco dei rilanci dell'asta
	 * 
	 * @throws SqlComposerException 
	 */
    public List<DettaglioRilancioType> getElencoRilanci(String codice, String codiceLotto, String username, String numeroFase) throws SqlComposerException;

    /**
	 * Inserisce un nuovo rilancio in un'asta
	 * 
	 * @param codice codice della gara dell'asta
	 * @param codiceLotto codice del lotto di gara dell'asta
	 * @param rilancio dettaglio del rilancio da inserire
	 * 
	 * @throws SqlComposerException 
	 */
    public void insertRilancioAsta(String codice, String codiceLotto, DettaglioRilancioType rilancio) throws SqlComposerException;
    
    /**
	 * Aggiorna il rilancio esistente in un'asta
	 * 
	 * @param id id del rilancio da aggiornare
	 * @param rilancio dettaglio del rilancio da inserire
	 * 
	 * @throws SqlComposerException 
	 */    
    public void updateRilancioAsta(long id, DettaglioRilancioType rilancio) throws SqlComposerException;

    /**
	 * Aggiorna i dettagli di un'asta
	 * 
	 * @param codice codice della gara dell'asta
	 * @param codice codice del lotto di gara dell'asta
	 * @param asta dettaglio dei dati dell'asta
	 * 
	 * @throws SqlComposerException 
	 */
	public void updateAsta(String codice, String codiceLotto, DettaglioAstaType asta) throws SqlComposerException;

	/**
	 * Recupera l'elenco dei prezzi unitari associati al primo rilancio vuoto
	 * dell'asta
	 * 
	 * @param codice codice della gara dell'asta
	 * @param codiceLotto codice del lotto di gara dell'asta
	 * @param username nome utente associato all'offerta
	 *  
	 * @return lista di VoceDettaglioAstaType 
	 */
	public List<VoceDettaglioAstaType> getPrezziUnitariPrimoRilancio(String codice, String codiceLotto, String username) throws SqlComposerException;
	
	/**
	 * Recupera l'elenco dei prezzi unitari associati all'offerta d'asta
	 * 
	 * @param codice codice della gara dell'asta
	 * @param codiceLotto codice del lotto di gara dell'asta
	 * @param username nome utente associato all'offerta
	 * @param idRilancio id del rilancio del quale cercare i prezzi unitari
	 *  
	 * @return lista di VoceDettaglioOffertaType 
	 */
	public List<VoceDettaglioAstaType> getPrezziUnitari(String codice, String codiceLotto, String username, Long idRilancio) throws SqlComposerException;	

	/**
	 * Inserisci l'elenco dei prezzi uniteri associati all'offerta d'asta
	 * 
	 * @param idRilancio id del rilancio 
	 * @param prezziUnitari lista dei prezzi unitari
	 */
	public void insertPrezziUnitari(Long idRilancio, List<VoceDettaglioAstaType> prezziUnitari) throws SqlComposerException;
	
	/**
	 * Aggiorna l'elenco dei prezzi uniteri associati all'offerta d'asta
	 * 
	 * @param idRilancio id del rilancio
	 * @param prezziUnitari lista dei prezzi unitari
	 */
	public void updatePrezziUnitari(Long idRilancio, List<VoceDettaglioAstaType> prezziUnitari) throws SqlComposerException;	

	/**
	 * Recupera lo username della mandataria di un raggruppamento rti
	 * 
	 * @param codice codice della gara dell'asta
	 * @param codiceLotto codice del lotto di gara dell'asta
	 * @param username nome utente associato all'offerta
	 *  
	 * @return username della ditta mandataria del raggruppamento 
	 */
	public String getMandatariaRti(String codice, String codiceLotto, String username) throws SqlComposerException;
	
	/**
	 * Recupera il tipo di rilancio dell'asta
	 * 
	 * @param codice codice della gara dell'asta
	 *  
	 * @return tipo di rilancio dell'asta 1 = ribasso, 2 = importo 
	 */
	public int getTipoRilancioAsta(String codice) throws SqlComposerException;
	
	/**
	 * Restituisce il totale non soggetto a ribasso di una gara
	 * 
	 * @param codice codice della gara 
	 *  
	 * @return totale non soggetto della gara composto da
	 * 			importo sicurezza, importo non soggetto a ribasso e oneri di progettazione 
	 */
	public double getTotaleNonSoggettoPrezziUnitari(String codice) throws SqlComposerException;

}
