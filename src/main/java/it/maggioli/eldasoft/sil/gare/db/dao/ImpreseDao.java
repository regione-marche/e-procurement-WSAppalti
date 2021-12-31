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
package it.maggioli.eldasoft.sil.gare.db.dao;

import it.eldasoft.sil.portgare.datatypes.AbilitazionePreventivaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.AlboProfessionaleAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.AltriDatiAnagraficiAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.CameraCommercioAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.CassaEdileAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.CassaPrevidenzaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.ContoCorrenteDedicatoAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.DatoAnnuoImpresaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.INAILAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.INPSAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.ISO9001AggiornabileType;
import it.eldasoft.sil.portgare.datatypes.ImpresaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.IndirizzoAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.IndirizzoEstesoAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.IscrizioneElenchiRicostruzioneAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.IscrizioneWhitelistAntimafiaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.RatingLegalitaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.RecapitiAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.ReferenteImpresaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.SOAAggiornabileType;
import it.maggioli.eldasoft.sil.gare.db.vo.PKReferenteImpresa;
import it.maggioli.eldasoft.sil.gare.ws.RisultatoStringaOutType;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * Interfaccia DAO per l'accesso alle informazioni relative alle imprese nel web
 * service per il portale
 * 
 * @author Stefano.Sabbadin
 * 
 */
public interface ImpreseDao {

    /**
     * Estrae la chiave dell'impresa a partire dall'account correlato
     * all'impresa
     * 
     * @param username
     *            username dell'impresa
     * @return PK impresa
     * @throws DataAccessException
     */
    String getPKImpresaByUsername(String username) throws DataAccessException;

    /**
     * Estrae i dati dell'impresa a partire dalla sua chiave
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return dati di dettaglio dell'impresa
     * @throws DataAccessException
     */
    ImpresaAggiornabileType getImpresa(String codiceImpresa)
	    throws DataAccessException;

    /**
     * Estrae la sede legale dell'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return indirizzo dell'impresa
     * @throws DataAccessException
     */
    IndirizzoAggiornabileType getSedeLegaleImpresa(String codiceImpresa)
	    throws DataAccessException;

    /**
     * Estrae i recapiti dell'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return recapiti dell'impresa
     * @throws DataAccessException
     */
    RecapitiAggiornabileType getRecapitiImpresa(String codiceImpresa)
	    throws DataAccessException;

    /**
     * Estrae gli altri indirizzi dell'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return altri indirizzi dell'impresa
     * @throws DataAccessException
     */
    List<IndirizzoEstesoAggiornabileType> getIndirizziImpresa(
	    String codiceImpresa) throws DataAccessException;

    /**
     * Estrae i dati di iscrizione alla camera di commercio per l'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return dati relativi alla camera di commercio
     * @throws DataAccessException
     */
    CameraCommercioAggiornabileType getDatiCCIAAImpresa(String codiceImpresa)
	    throws DataAccessException;

    /**
     * Estrae i dati di iscrizione all'INPS per l'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return dati relativi all'INPS
     * @throws DataAccessException
     */
    INPSAggiornabileType getDatiINPSImpresa(String codiceImpresa)
	    throws DataAccessException;

    /**
     * Estrae i dati di iscrizione all'INAIL per l'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return dati relativi all'INAIL
     * @throws DataAccessException
     */
    INAILAggiornabileType getDatiINAILImpresa(String codiceImpresa)
	    throws DataAccessException;

    /**
     * Estrae i dati di iscrizione alla cassa edile per l'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return dati relativi alla cassa edile
     * @throws DataAccessException
     */
    CassaEdileAggiornabileType getDatiCassaEdileImpresa(String codiceImpresa)
	    throws DataAccessException;

    /**
     * Estrae i dati SOA per l'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return dati SOA dell'impresa
     * @throws DataAccessException
     */
    SOAAggiornabileType getDatiSOAImpresa(String codiceImpresa)
	    throws DataAccessException;

    /**
     * Estrae i dati della certificazione ISO 9001 per l'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return dati ISO 9001 dell'impresa
     * @throws DataAccessException
     */
    ISO9001AggiornabileType getDatiISO9001Impresa(String codiceImpresa)
	    throws DataAccessException;

    /**
     * Estrae i dati sull'iscrizione alla whitelist antimafia
     * @param codiceImpresa
     * @return IscrizioneWhitelistAntimafiaAggiornabileType
     * @throws DataAccessException
     */
    public IscrizioneWhitelistAntimafiaAggiornabileType getDatiIscrizioneWhitelistImpresa(
            String codiceImpresa) throws DataAccessException;

    /**
     * Estra idati dell'iscrizione elenchi ricostruzione (DL 189/2016) per l'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return dati dell'iscrizione elenchi ricostruzione
     * @throws DataAccessException
     */
    public IscrizioneElenchiRicostruzioneAggiornabileType getDatiIscrizioneElenchiRicostruzioneImpresa(
        String codiceImpresa)throws DataAccessException;
    
    /**
     * Estrae i dati del rating di legalità (DL 1/2012) per l'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return dati del rating legale
     * @throws DataAccessException
     */
    public RatingLegalitaAggiornabileType getDatiRatingLegalitaImpresa(
        String codiceImpresa)throws DataAccessException;
    
    /**
     * Estrae i dati del conto corrente dedicato per l'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return dati del conto corrente dedicato
     * @throws DataAccessException
     */
    ContoCorrenteDedicatoAggiornabileType getDatiContoCorrenteDedicatoImpresa(
	    String codiceImpresa) throws DataAccessException;

    /**
     * Estrae i dati dell'abilitazione preventiva per l'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return dati abilitazione preventiva dell'impresa
     * @throws DataAccessException
     */
    AbilitazionePreventivaAggiornabileType getDatiAbilitazionePreventivaImpresa(
	    String codiceImpresa) throws DataAccessException;

    /**
     * Estrae le chiavi degli attuali legali rappresentanti dell'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return lista delle PK dei tecnici con ruolo di attuale legale
     *         rappresentante per l'impresa
     * @throws DataAccessException
     */
    List<PKReferenteImpresa> getPKLegaliRappresentantiImpresa(String codiceImpresa, Date dataPartenzaCessati)
	    throws DataAccessException;

    /**
     * Estrae le chiavi degli attuali direttori tecnici dell'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return lista delle PK dei tecnici con ruolo di attuale direttore tecnico
     *         per l'impresa
     * @throws DataAccessException
     */
    List<PKReferenteImpresa> getPKDirettoriTecniciImpresa(String codiceImpresa, Date dataPartenzaCessati)
	    throws DataAccessException;

    /**
     * Estrae le chiavi degli attuali soggetti con altri incarichi nell'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return lista delle PK dei soggetti con altri incarichi nell'impresa
     * @throws DataAccessException
     */
    List<PKReferenteImpresa> getPKAltreCaricheImpresa(String codiceImpresa, Date dataPartenzaCessati)
	    throws DataAccessException;

    /**
     * Estrae le chiavi degli attuali soggetti collaboratori dell'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @return lista delle PK dei soggetti collaboratori dell'impresa
     * @throws DataAccessException
     */
    List<PKReferenteImpresa> getPKCollaboratoriImpresa(String codiceImpresa, Date dataPartenzaCessati)
	    throws DataAccessException;

    /**
     * Estrae i dati di un referente impresa (tecnico) indipendentemente dal suo
     * incarico
     * 
     * @param codiceReferente
     *            PK tecnico referente
     * @return dati di dettaglio del tecnico referente
     * @throws DataAccessException
     */
    ReferenteImpresaAggiornabileType getDatiReferenteImpresa(
	    String codiceReferente) throws DataAccessException;

    /**
     * Estrae la residenza di un referente impresa (tecnico) indipendentemente
     * dal suo incarico
     * 
     * @param codiceReferente
     *            PK tecnico referente
     * @return residenza del tecnico referente
     * @throws DataAccessException
     */
    IndirizzoAggiornabileType getIndirizzoReferenteImpresa(
	    String codiceReferente) throws DataAccessException;

    /**
     * Estrae i dati della durata dell'incarico di un legale rappresentante
     * dell'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @param codiceReferente
     *            PK tecnico referente
     * @return dati di dettaglio del tecnico referente, valorizzati solo nei
     *         campi della durata incarico
     * @throws DataAccessException
     */
    ReferenteImpresaAggiornabileType getIncaricoLegaleRappresentanteImpresa(
	    String codiceImpresa, PKReferenteImpresa codiceReferente)
	    throws DataAccessException;

    /**
     * Estrae i dati della durata dell'incarico di un direttore tecnico
     * dell'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @param codiceReferente
     *            PK tecnico referente
     * @return dati di dettaglio del tecnico referente, valorizzati solo nei
     *         campi della durata incarico
     * @throws DataAccessException
     */
    ReferenteImpresaAggiornabileType getIncaricoDirettoreTecnicoImpresa(
	    String codiceImpresa, PKReferenteImpresa codiceReferente)
	    throws DataAccessException;

    /**
     * Estrae i dati della durata dell'incarico di un soggetto con altra carica nell'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @param codiceReferente
     *            PK tecnico referente
     * @return dati di dettaglio del tecnico referente, valorizzati solo nei
     *         campi della durata incarico
     * @throws DataAccessException
     */
    ReferenteImpresaAggiornabileType getIncaricoAltraCaricaImpresa(
	    String codiceImpresa, PKReferenteImpresa codiceReferente)
	    throws DataAccessException;

    /**
     * Estrae i dati della durata dell'incarico di un collaboratore dell'impresa
     * 
     * @param codiceImpresa
     *            PK impresa
     * @param codiceReferente
     *            PK tecnico referente
     * @return dati di dettaglio del tecnico referente, valorizzati solo nei
     *         campi della durata incarico
     * @throws DataAccessException
     */
    ReferenteImpresaAggiornabileType getIncaricoCollaboratoreImpresa(
	    String codiceImpresa, PKReferenteImpresa codiceReferente)
	    throws DataAccessException;

    /**
     * Verifica l'esistenza di un'impresa attiva sul portale con lo stesso
     * codice fiscale o partita di quelli in input. La partita IVA viene
     * controllata anche con il campo codice fiscale nel DB in quanto tale
     * informazione pu&ograve; essere sia un codice fiscale che una partita iva.
     * 
     * @param codiceFiscale
     *            codice fiscale da cercare
     * @param partitaIva
     *            partita iva da cercare
     * @return true se esiste almeno un'impresa attiva sul portale e che usa
     *         codice fiscale o partita in input, false altrimenti
     * @throws DataAccessException
     */
    Boolean isImpresaRegistrata(String codiceFiscale, String partitaIva)
	    throws DataAccessException;
    
    /**
     * Restituisce un'impresa del portale con lo stesso
     * codice fiscale o partita, email, pec di quelli in input. 
     * La partita IVA viene controllata anche con il campo codice fiscale 
     * nel DB in quanto tale informazione pu&ograve; essere sia un 
     * codice fiscale che una partita iva.
     * 
     * @param codiceFiscale
     *            codice fiscale da cercare
     * @param partitaIva
     *            partita iva da cercare
     * @return true se esiste almeno un'impresa attiva sul portale e che usa
     *         codice fiscale o partita in input, false altrimenti
     * @throws DataAccessException
     */
    public String getImpresaRegistrata(String codiceFiscale, String partitaIva, String email, String pec) 
    	throws DataAccessException;
    
    /**
     * Ritorna l'elenco dei tipi impresa gestiti per l'inserimento di iscrizioni
     * agli elenchi operatori economici.
     * 
     * @return lista delle tipologie di impresa
     * 
     * @throws DataAccessException
     */
    List<DatoCodificatoType> getElencoTipiImpresaPerIscrizione()
	    throws DataAccessException;

	/**
	 * Ritorna l'elenco dei tipi impresa gestiti per l'inserimento di iscrizioni
	 * agli elenchi operatori economici relativi a ditte individuali.
	 * 
	 * @return lista delle tipologie di impresa
	 * 
	 * @throws DataAccessException
	 */
	List<DatoCodificatoType> getElencoTipiImpresaDittaIndividuale()
			throws DataAccessException;

	/**
	 * Ritorna l'elenco dei tipi impresa gestiti per l'inserimento di iscrizioni
	 * agli elenchi operatori economici relativi a liberi professionisti.
	 * 
	 * @return lista delle tipologie di impresa
	 * 
	 * @throws DataAccessException
	 */
	List<DatoCodificatoType> getElencoTipiImpresaLiberoProfessionista()
			throws DataAccessException;
	
	
	
	/**
	 * Ritorna l'elenco dei tipi impresa gestiti per l'inserimento di iscrizioni
	 * agli elenchi operatori economici relativi a impresa sociale.
	 * 
	 * @return lista delle tipologie di impresa
	 * 
	 * @throws DataAccessException
	 */
	List<DatoCodificatoType> getElencoTipiImpresaSociale()
			throws DataAccessException;	
	
	/**
	 * Ritorna l'elenco dei tipi impresa gestiti per l'inserimento di iscrizioni
	 * agli elenchi operatori economici relativi a consorzi.
	 * 
	 * @return lista delle tipologie di impresa
	 * 
	 * @throws DataAccessException
	 */
	List<DatoCodificatoType> getElencoTipiImpresaConsorzio()
	throws DataAccessException;
	
	/**
	 * Ritorna ulteriori dati anagrafici valorizzabili solo nel caso di libero
	 * professionista.
	 * 
	 * @param codiceImpresa
	 *            PK impresa
	 * @return dati anagrafici ulteriori, valorizzati solo per un libero
	 *         professionista
	 * 
	 * @throws DataAccessException
	 */
	AltriDatiAnagraficiAggiornabileType getAltriDatiLiberoProfessionista(
			String codiceImpresa) throws DataAccessException;
	
	/**
	 * Ritorna i dati dell'albo professionale nel caso di impresa.
	 * 
     * @param codiceReferente
     *            PK tecnico referente
	 * @return dati dell'albo professionale, valorizzati solo per un'impresa
	 * 
	 * @throws DataAccessException
	 */
	AlboProfessionaleAggiornabileType getAlboProfessionaleReferenteImpresa(
			String codiceReferente) throws DataAccessException;
	
	/**
	 * Ritorna i dati dell'albo professionale nel caso di libero
	 * professionista.
	 * 
	 * @param codiceImpresa
	 *            PK impresa
	 * @return dati dell'albo professionale, valorizzati solo per un libero
	 *         professionista
	 * 
	 * @throws DataAccessException
	 */
	AlboProfessionaleAggiornabileType getAlboProfessionaleLiberoProfessionista(
			String codiceImpresa) throws DataAccessException;
	
	/**
	 * Ritorna i dati della cassa previdenza nel caso di impresa.
	 * 
     * @param codiceReferente
     *            PK tecnico referente
	 * @return dati della cassa previdenza, valorizzati solo per un'impresa
	 * 
	 * @throws DataAccessException
	 */
	CassaPrevidenzaAggiornabileType getCassaPrevidenzaReferenteImpresa(
			String codiceReferente) throws DataAccessException;

	/**
	 * Ritorna i dati della cassa previdenza nel caso di libero
	 * professionista.
	 * 
	 * @param codiceImpresa
	 *            PK impresa
	 * @return dati della cassa previdenza, valorizzati solo per un libero
	 *         professionista
	 * 
	 * @throws DataAccessException
	 */
	CassaPrevidenzaAggiornabileType getCassaPrevidenzaLiberoProfessionista(
			String codiceImpresa) throws DataAccessException;

	/**
	 * Ritorna i dati annui dell'impresa.
	 * @param codiceImpresa
	 *            PK impresa
	 * @param anno
	 * anno di riferimento
	 * @return contenitore con i dati annui, null se non disponibili
	 * @throws DataAccessException
	 */
	DatoAnnuoImpresaAggiornabileType getDatiAnnui(
			String codiceImpresa, int anno) throws DataAccessException;

	String getIdImpresa(String username) throws DataAccessException;

}
