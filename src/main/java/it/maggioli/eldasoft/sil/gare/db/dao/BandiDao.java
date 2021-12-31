/*
 * Created on 06/lug/10
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
import it.maggioli.eldasoft.sil.gare.db.vo.CriterioValutazioneOffertaLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.AttributoAggiuntivoOfferta;
import it.maggioli.eldasoft.sil.gare.db.vo.ComunicazioneLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentazioneRichiestaLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.EspletGaraOperatoreLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.RequisitoRichiestoLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.SoggettoAderenteLotto;
import it.maggioli.eldasoft.sil.gare.ws.BandoIscrizioneType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaBandoIscrizioneType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaGaraType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaImpresaType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaOperatoreIscrittoType;
import it.maggioli.eldasoft.sil.gare.ws.ComunicazioneType;
import it.maggioli.eldasoft.sil.gare.ws.CriterioValutazioneOffertaType;
import it.maggioli.eldasoft.sil.gare.ws.DeliberaType;
import it.maggioli.eldasoft.sil.gare.ws.DettaglioStazioneAppaltanteType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentazioneRichiestaType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.EspletGaraComponenteRtiType;
import it.maggioli.eldasoft.sil.gare.ws.EspletGaraDocumentoType;
import it.maggioli.eldasoft.sil.gare.ws.EspletGaraLottoType;
import it.maggioli.eldasoft.sil.gare.ws.EspletGaraOperatoreType;
import it.maggioli.eldasoft.sil.gare.ws.FascicoloProtocolloType;
import it.maggioli.eldasoft.sil.gare.ws.GaraType;
import it.maggioli.eldasoft.sil.gare.ws.ImpresaIscrizioneType;
import it.maggioli.eldasoft.sil.gare.ws.InvioType;
import it.maggioli.eldasoft.sil.gare.ws.InvitoGaraType;
import it.maggioli.eldasoft.sil.gare.ws.LottoGaraType;
import it.maggioli.eldasoft.sil.gare.ws.MandanteRTIType;
import it.maggioli.eldasoft.sil.gare.ws.OperatoreIscrittoType;
import it.maggioli.eldasoft.sil.gare.ws.RequisitoRichiestoType;
import it.maggioli.eldasoft.sil.gare.ws.RettificaGaraType;
import it.maggioli.eldasoft.sil.gare.ws.SoggettoAderenteType;
import it.maggioli.eldasoft.sil.gare.ws.SommaUrgenzaType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;
import it.maggioli.eldasoft.sil.gare.ws.TipoPartecipazioneType;
import it.maggioli.eldasoft.sil.gare.ws.VoceDettaglioOffertaType;
import it.maggioli.eldasoft.sil.gare.ws.WSDMConfigType;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;


import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * Interfaccia DAO per l'accesso alle informazioni relative ai bandi nel web
 * service per il portale
 * 
 * @author Stefano.Sabbadin
 * 
 */
public interface BandiDao {

	/**
	 * Ritorna la versione dell'applicativo di backoffice
	 * 
	 * @return versione del backoffice
	 * 
	 * @throws DataAccessException
	 */
	String getAppaltiVersion()
		throws DataAccessException;

	/**
	 * Ritorna l'elenco delle stazioni appaltanti gestite nel backoffice
	 * 
	 * @return lista delle stazioni appaltanti
	 * 
	 * @throws DataAccessException
	 */
	List<DatoCodificatoType> getElencoStazioniAppaltanti()
			throws DataAccessException;
	
	
	/**
	 * Ritorna l'elenco delle stazioni appaltanti gestite nel backoffice 
	 * per la legge 190
	 * 
	 * @return lista delle stazioni appaltanti per la legge 190
	 * 
	 * @throws DataAccessException
	 */
	List<DatoCodificatoType> getElencoStazioniAppaltantiL190()
			throws DataAccessException;

	/**
	 * Ritorna l'elenco degli stati possibili per una gara recuperando il codice
	 * e la descrizione
	 * 
	 * @return lista delle codifiche per lo stato della gara
	 * 
	 * @throws DataAccessException
	 */
	List<DatoCodificatoType> getStatiGara() throws DataAccessException;

	/**
	 * Ritorna l'elenco degli esiti possibili per una gara recuperando il codice
	 * e la descrizione
	 * 
	 * @return lista delle codifiche per lo stato della gara
	 * 
	 * @throws DataAccessException
	 */
	List<DatoCodificatoType> getEsitiGara() throws DataAccessException;

	/**
	 * Ritorna l'elenco dei bandi di gara filtrati dai parametri in input.
	 * 
	 * @return lista dei bandi, di tipo {@link GaraType}
	 * 
	 * @throws DataAccessException
	 * @throws SqlComposerException
	 */
	List<GaraType> searchBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord)
			throws DataAccessException, SqlComposerException;
	
	int countBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione)
			throws DataAccessException, SqlComposerException;

//	/**
//	 * Ritorna l'elenco dei bandi di gara in corso di validit&agrave;.
//	 * 
//	 * @return lista dei bandi, di tipo {@link GaraType}
//	 * 
//	 * @throws DataAccessException
//	 */
//	List<GaraType> getElencoBandi() throws DataAccessException;

	/**
	 * Ritorna l'elenco dei bandi di gara in corso di validit&agrave;.
	 * 
	 * @return lista dei bandi, di tipo {@link GaraType}
	 * 
	 * @throws DataAccessException
	 */
	List<GaraType> getElencoBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int indicePrimoRecord,
	        int maxNumRecord) 
			throws DataAccessException, SqlComposerException;

	int countElencoBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica) 
			throws DataAccessException, SqlComposerException;

//	/**
//	 * Ritorna l'elenco dei bandi di gara scaduti.
//	 * 
//	 * @param numAnniPubblicazione
//	 *            numero di anni per cui va mantenuta la procedura pubblicata
//	 * @return lista dei bandi, di tipo {@link GaraType}
//	 * 
//	 * @throws DataAccessException
//	 */
//	List<GaraType> getElencoBandiScaduti(int numAnniPubblicazione) throws DataAccessException;

	/**
	 * Ritorna l'elenco dei bandi di gara scaduti.
	 * 
	 * @param stazioneAppaltante
	 * @param oggetto
	 * @param cig
	 * @param tipoAppalto
	 * @param dataPubblicazioneDa
	 * @param dataPubblicazioneA
	 * @param dataScadenzaDa
	 * @param dataScadenzaA
	 * @param numAnniPubblicazione
	 * @param proceduraTelematica
	 * @param stato
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuta la procedura pubblicata
	 * @return lista dei bandi, di tipo {@link GaraType}
	 * 
	 * @throws DataAccessException
	 */
	List<GaraType> getElencoBandiScaduti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA,
			Date dataScadenzaDa, Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			Integer stato,
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord) 
			throws DataAccessException, SqlComposerException;
	
	int countElencoBandiScaduti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA,
			Date dataScadenzaDa, Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			Integer stato,
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int numAnniPubblicazione) 
			throws DataAccessException, SqlComposerException;
	
	/**
	 * Ritorna il cig in caso di gara a lotto unico e la lista dei cig in caso
	 * di gara a lotti.
	 * 
	 * @param codice
	 *            codice della gara
	 * @return cig singolo o degli n lotti, in tal caso separati da una virgola
	 */
	String getCigBando(String codice);

	/**
	 * Ritorna i dati della stazione appaltante e del rup a partire dal bando in
	 * input
	 * 
	 * @param codice
	 *            codice univoco del bando
	 * @return dettaglio della stazione appaltante e nome del rup
	 * @throws DataAccessException
	 */
	StazioneAppaltanteBandoType getStazioneAppaltanteBando(String codice)
			throws DataAccessException;

	/**
	 * Ritorna il dettaglio dei dati di un bando di gara individuato dalla
	 * chiave in input
	 * 
	 * @param codiceGara
	 *            codice univoco del bando di gara
	 * @return dettaglio della gara
	 * @throws DataAccessException
	 */
	GaraType getBando(String codiceGara) throws DataAccessException;

	/**
	 * Ritorna l'elenco dei documenti in allegato al bando o al lotto
	 * individuato dai parametri di input
	 * 
	 * @param codiceGara
	 *            codice univoco del bando
	 * @param codiceLotto
	 *            codice univoco del lotto, se si necessita di ottenere i
	 *            documenti del lotto, altrimenti null
	 * @return lista dei documenti, di tipo {@link DocumentoAllegatoType}
	 * @throws DataAccessException
	 */
	List<DocumentoAllegatoType> getDocumentiBandoLotto(String codiceGara,
			String codiceLotto) throws DataAccessException;
	
	/**
	 * Ritorna l'elenco dei documenti di invito allegati al bando o al lotto
	 * individuato dai parametri di input
	 * 
	 * @param codiceGara
	 *            codice univoco del bando
	 * @param codiceLotto
	 *            codice univoco del lotto, se si necessita di ottenere i
	 *            documenti del lotto, altrimenti null
	 * @return lista dei documenti, di tipo {@link DocumentoAllegatoType}
	 * @throws DataAccessException
	 */
	List<DocumentoAllegatoType> getDocumentiInvitoLotto(String codiceGara,
			String codiceLotto) throws DataAccessException;

	/**
	 * Ritorna l'elenco dei documenti di invito allegati all'asta o al lotto
	 * individuato dai parametri di input
	 * 
	 * @param codiceGara
	 *            codice univoco del bando
	 * @param codiceLotto
	 *            codice univoco del lotto, se si necessita di ottenere i
	 *            documenti del lotto, altrimenti null
	 * @return lista dei documenti, di tipo {@link DocumentoAllegatoType}
	 * @throws DataAccessException
	 */
	List<DocumentoAllegatoType> getDocumentiInvitoAstaLotto(String codiceGara,
			String codiceLotto) throws DataAccessException;

	/**
	 * Ritorna la checklist con l'elenco dei requisiti richiesti nel bando o nel
	 * lotto di gara
	 * 
	 * @param codiceGara
	 *            codice univoco del bando
	 * @param codiceLotto
	 *            codice univoco del lotto, se si necessita di ottenere i
	 *            documenti del lotto, altrimenti null
	 * @return lista dei requisiti, di tipo {@link RequisitoRichiestoType}
	 * @throws DataAccessException
	 */
	List<RequisitoRichiestoType> getRequisitiRichiestiBandoLotto(
			String codiceGara, String codiceLotto) throws DataAccessException;
	
	/**
	 * Ottimizzazione gestione lotti in getDettaglioGara(...) 
	 */
	List<RequisitoRichiestoLotto> getRequisitiRichiestiBando(
			String codiceGara) throws DataAccessException;

	/**
	 * Ritorna l'elenco dei lotti di gara
	 * 
	 * @param codiceGara
	 *            codice univoco del bando di gara
	 * @return lista dei lotti, di tipo {@link LottoGaraType}
	 * @throws DataAccessException
	 */
	List<LottoGaraType> getLottiGara(String codiceGara)
			throws DataAccessException;

	/**
	 * Ottimizzazione gestione lotti in getDettaglioGara(...)  
	 */
	List<DocumentoAllegatoLotto> getDocumentiGara(String codiceGara)
			throws DataAccessException;

	/**
	 * Ritorna l'elenco dei lotti di gara per cui presentare una domanda di
	 * partecipazione.
	 * 
	 * @param codiceGara
	 *            codice univoco del bando di gara
	 * @return lista dei lotti, di tipo {@link LottoGaraType}
	 * @throws DataAccessException
	 */
	List<LottoGaraType> getLottiGaraPerDomandePartecipazione(String codiceGara);

	/**
	 * Ritorna l'elenco dei lotti di gara per cui un'impresa e' abilitata a
	 * richiedere l'offerta
	 * 
	 * @param username
	 *            username dell'impresa
	 * @param codiceGara
	 *            codice univoco del bando di gara
	 * @return lista dei lotti, di tipo {@link LottoGaraType}
	 * @throws DataAccessException
	 */
	List<LottoGaraType> getLottiGaraPerRichiesteOfferta(String username,
			String codiceGara) throws DataAccessException;

	/**
	 * Ritorna l'elenco dei lotti di gara per cui un'impresa e' abilitata a
	 * richiedere l'offerta per una gara a plico unico con offerte distinte.
	 * 
	 * @param username
	 *            username dell'impresa
	 * @param codiceGara
	 *            codice univoco del bando di gara
	 * @return lista dei lotti, di tipo {@link LottoGaraType}
	 * @throws DataAccessException
	 */
	List<LottoGaraType> getLottiGaraPlicoUnicoPerRichiesteOfferta(String username,
			String codiceGara) throws DataAccessException;

	/**
	 * Ritorna l'elenco dei lotti di gara per cui un'impresa e' abilitata a
	 * inviare la comprova dei requisiti
	 * 
	 * @param username
	 *            username dell'impresa
	 * @param codiceGara
	 *            codice univoco del bando di gara
	 * @return lista dei lotti, di tipo {@link LottoGaraType}
	 * @throws DataAccessException
	 */
	List<LottoGaraType> getLottiGaraPerRichiesteCheckDocumentazione(
			String username, String codiceGara) throws DataAccessException;

	/**
	 * Ritorna l'elenco dei bandi d'iscrizione o cataloghi a seconda del genere.
	 * 
	 * @param genere
	 *            10=bando d'iscrizione, 20=catalogo
	 * 
	 * @return lista dei bandi, di tipo {@link BandoIscrizioneType}
	 * 
	 * @throws DataAccessException
	 */
	List<BandoIscrizioneType> getElencoBandiIscrizione(int genere, String stazioneAppaltante)
			throws DataAccessException;

	/**
	 * Ritorna i dati della stazione appaltante e del rup a partire dal bando
	 * d'iscrizione in input
	 * 
	 * @param codice
	 *            codice univoco del bando
	 * @return dettaglio della stazione appaltante e nome del rup
	 * @throws DataAccessException
	 */
	StazioneAppaltanteBandoType getStazioneAppaltanteBandoIscrizione(String codice)
			throws DataAccessException;

	/**
	 * Ritorna il dettaglio di un bando d'iscrizione individuato dal parametro
	 * di input
	 * 
	 * @param codice
	 *            codice univoco del bando
	 * @return dettaglio del bando d'iscrizione
	 * @throws DataAccessException
	 */
	BandoIscrizioneType getBandoIscrizione(String codice)
			throws DataAccessException;

	/**
	 * Ritorna l'elenco dei documenti in allegato al bando bando d'iscrizione
	 * individuato dal parametro di input
	 * 
	 * @param codice
	 *            codice univoco del bando
	 * @return lista dei documenti, di tipo {@link DocumentoAllegatoType}
	 * @throws DataAccessException
	 */
	List<DocumentoAllegatoType> getDocumentiBandoIscrizione(String codice)
			throws DataAccessException;

	/**
	 * Ritorna la checklist con l'elenco dei requisiti richiesti per la
	 * presentazione dell'iscrizione all'albo da parte del fornitore o per
	 * l'iscrizione all'elenco
	 * 
	 * @param codice
	 *            codice univoco del bando d'iscrizione
	 * @return lista dei requisiti standard richiesti pi&ugrave; quelli
	 *         dipendenti dalla tipologia impresa, di tipo
	 *         {@link RequisitoRichiestoType}
	 * @throws DataAccessException
	 */
	List<RequisitoRichiestoType> getRequisitiRichiestiBandoIscrizione(
			String codice) throws DataAccessException;

	/**
	 * Ritorna l'elenco delle categorie/prestazioni per il lotto in input
	 * 
	 * @param codiceLotto
	 *            codice univoco del lotto di gara
	 * @return lista delle categorie appartenenti al lotto, di tipo
	 *         {@link CategoriaType}
	 * @throws DataAccessException
	 */
	List<CategoriaGaraType> getCategorieLotto(String codiceLotto)
			throws DataAccessException;

	/**
	 * Ritorna l'elenco delle categorie/prestazioni per il bando d'iscrizione in
	 * input
	 * 
	 * @param codice
	 *            codice univoco del bando d'iscrizione
	 * @param filtroCategorie
	 *            filtro eventuale sulle categorie da selezionare
	 * @return lista delle categorie associate al bando, di tipo
	 *         {@link CategoriaType}
	 * @throws DataAccessException
	 */
	List<CategoriaBandoIscrizioneType> getCategorieBandoIscrizione(
			String codice, String filtroCategorie) throws DataAccessException,
			SqlComposerException;

	/**
	 * Ritorna i dati di un'impresa associata ad un bando di iscrizione
	 * @param username
	 * @param codice
	 * 
	 * @return ImpresaIscrizioneType
	 * @throws DataAccessException
	 */
	public ImpresaIscrizioneType getImpresaIscrizione(
			String username, String codice) throws DataAccessException;

	/**
	 * Ritorna l'elenco paginato delle comunicazioni del bando (di gara o
	 * d'iscrizione) o del lotto di gara, pubbliche o personali a seconda della
	 * valorizzazione dello username, eventualmente di tutte le entit&agrave; se
	 * non si filtra per codice, considerando le comunicazioni filtrate in base
	 * al range di date invio in input.
	 * 
	 * @param username
	 *            username, da usare solo per le comunicazioni personali, null
	 *            altrimenti
	 * @param codice
	 *            eventuale codice univoco del bando o del lotto, o dell'elenco
	 *            o del catalogo
	 * @param dataInvioDa
	 *            data invio a partire da
	 * @param dataInvioA
	 *            data invio fino a
	 * @param indicePrimoRecord
	 * @param maxNumRecord
	 * 
	 * @return lista delle comunicazioni personali, di tipo
	 *         {@link ComunicazioneType}
	 * @throws DataAccessException
	 */
	List<ComunicazioneType> getComunicazioniRicevute(
			String username,
			String codice,
			Date dataInvioDa, Date dataInvioA,
			boolean soccorsoIstruttorio,
			int indicePrimoRecord, int maxNumRecord,
			String stazioneAppaltante);

	/**
	 * Ottimizzazione gestione lotti in getDettaglioGara(...)
	 */
	List<ComunicazioneLotto> getComunicazioniPubblicheRicevute(
			String applicativo, 
			String codice, 
			String stazioneAppaltante);

	/**
	 * Ritorna l'elenco paginato delle comunicazioni ricevute su tutti i lotti di gara.
	 * 
	 * @param username
	 *            username impresa
	 * @param codiciLotto
	 *            codici dei lotti per cui estrarre le comunicazioni
	 * @param dataInvioDa
	 *            data invio a partire da
	 * @param dataInvioA
	 *            data invio fino a
	 * @param indicePrimoRecord
	 * @param maxNumRecord
	 * 
	 * @return lista delle comunicazioni personali, di tipo
	 *         {@link ComunicazioneType}
	 * @throws DataAccessException
	 */
	List<ComunicazioneType> getComunicazioniRicevuteLotti(
			String username,
			String[] codiciLotto,
			Date dataInvioDa, Date dataInvioA,
			boolean soccorsoIstruttorio,
			int indicePrimoRecord, int maxNumRecord,
			String stazioneAppaltante);

	/**
	 * Ritorna l'elenco paginato delle comunicazioni del bando (di gara o
	 * d'iscrizione) o del lotto di gara, inviate dall'operatore economico
	 * all'ente, eventualmente di tutte le entit&agrave; se non si filtra per
	 * codice.
	 * 
	 * @param username
	 *            username impresa inviante le comunicazioni
	 * @param codice
	 *            eventuale codice univoco del bando o del lotto, o dell'elenco
	 *            o del catalogo
	 * @param indicePrimoRecord
	 * @param maxNumRecord
	 * 
	 * @return lista delle comunicazioni personali, di tipo
	 *         {@link ComunicazioneType}
	 * @throws DataAccessException
	 */
	List<ComunicazioneType> getComunicazioniInviate(String username,
			String codice, int indicePrimoRecord, int maxNumRecord,
			String stazioneAppaltante);
	
	/**
	 * Conta il numero di comunicazioni totali ricevute, filtrate per i dati in
	 * input.
	 * 
	 * @param username
	 *            username, da usare solo per le comunicazioni personali, null
	 *            altrimenti
	 * @param codice
	 *            eventuale codice univoco del bando o del lotto, o dell'elenco
	 *            o del catalogo
	 * @param dataInvioDa
	 *            data invio a partire da
	 * @param dataInvioA
	 *            data invio fino a
	 * @param soloDaLeggere
	 *            true se si intende filtrare le sole comunicazioni rimaste da
	 *            leggere, false se si intende recuperarle tutte (lette e da
	 *            leggere)
	 * @return numero di comunicazioni ricevute
	 */
	int countComunicazioniRicevute(
			String username, 
			String codice,
			Date dataInvioDa, Date dataInvioA,
			boolean soccorsoIstruttorio,
			boolean soloDaLeggere,
			String stazioneAppaltante);

	/**
	 * Conta il numero di comunicazioni totali ricevute sui lotti di una gara, filtrate per i dati in
	 * input.
	 * 
	 * @param username
	 *            username, da usare solo per le comunicazioni personali, null
	 *            altrimenti
	 * @param codiciLotto
	 *            codici dei lotti per i quali estrarre le comunicazioni
	 * @param dataInvioDa
	 *            data invio a partire da
	 * @param dataInvioA
	 *            data invio fino a
	 * @param soloDaLeggere
	 *            true se si intende filtrare le sole comunicazioni rimaste da
	 *            leggere, false se si intende recuperarle tutte (lette e da
	 *            leggere)
	 * @return numero di comunicazioni ricevute
	 */
	int countComunicazioniRicevuteLotti(
			String username, 
			String[] codiciLotto, 
			Date dataInvioDa, Date dataInvioA,
			boolean soccorsoIstruttorio,
			boolean soloDaLeggere,
			String stazioneAppaltante);

	/**
	 * Conta il numero di comunicazioni totali inviate, filtrate per i dati in input.
	 * @param username
	 *            username, da usare solo per le comunicazioni personali, null
	 *            altrimenti
	 * @param codice
	 *            eventuale codice univoco del bando o del lotto, o dell'elenco
	 *            o del catalogo
	 * @return numero di comunicazioni ricevute
	 */
	int countComunicazioniInviate(String username, String codice,
			String stazioneAppaltante);
	
	/**
	 * Ritorna l'elenco dei documenti allegati ad una comunicazione.
	 * 
	 * @param applicativo
	 *            "PG" per le comunicazioni ricevute, "PA" per quelle inviate
	 * @param id
	 *            id univoco della comunicazione
	 * 
	 * @return lista dei documenti allegati, di tipo
	 *         {@link DocumentoAllegatoType}
	 * @throws DataAccessException
	 */
	List<DocumentoAllegatoType> getDocumentiComunicazione(String applicativo,
			Long id) throws DataAccessException;
	
	/**
	 * Ottimizzazione gestione lotti in getDettaglioGara(...)
	 */
	List<DocumentoAllegatoLotto> getDocumentiComunicazioniPubblicheRicevute(
			String applicativo,	String codice) throws DataAccessException;

	/**
	 * Ritorna la comunicazione ricevuta a partire dalla sua chiave.
	 * 
	 * @param username
	 *            username
	 * @param id
	 *            id univoco della comunicazione
	 * @param idDestinatario
	 * 			  progressivo destinatario relativo alla comunicazione
	 * @return dettaglio comunicazione
	 */
	ComunicazioneType getComunicazioneRicevuta(String username, Long id, Long idDestinatario);

	/**
	 * Ritorna la comunicazione inviata a partire dalla sua chiave.
	 * 
	 * @param username
	 *            username
	 * @param id
	 *            id univoco della comunicazione
	 * 
	 * @return dettaglio comunicazione
	 */
	ComunicazioneType getComunicazioneInviata(String username, Long id);
	
	/**
	 * Ritorna l'elenco delle comunicazioni/richieste trasmesse dall'operatore
	 * economico in input per una determinata procedura
	 * 
	 * @param username
	 *            username dell'operatore economico
	 * @param codice
	 *            codice procedura (elenco, catalogo)
	 * @param tipiComunicazione
	 *            elenco dei tipi comunicazione da ricercare
	 * @return lista delle comunicazioni che soddisfano i criteri di filtro
	 */
	List<InvioType> getElencoInvii(String username, String codice,
			String[] tipiComunicazione);

	/**
	 * Ritorna lo stato dell'iscrizione ad un elenco operatori economici per
	 * l'impresa in input
	 * 
	 * @param username
	 *            username dell'impresa
	 * @param codice
	 *            codice univoco del bando d'iscrizione
	 * @return <ul>
	 *         <li>null = l'impresa non risulta iscritta</li>
	 *         <li>1 = l'impresa ha un'iscrizione in corso</li>
	 *         <li>2 = l'impresa ha presentato la domanda d'iscrizione</li>
	 *         </ul>
	 * @throws DataAccessException
	 */
	Integer getStatoIscrizioneABandoIscrizione(String username, String codice)
			throws DataAccessException;

	/**
	 * Ritorna l'elenco dei documenti richiesti per la la partecipazione o
	 * l'invio offerta in una gara telematica.
	 * 
	 * @param codiceGara
	 *            codice univoco della gara
	 * @param codiceLotto
	 *            codice univoco del lotto, se si necessita di ottenere i
	 *            documenti del lotto, altrimenti null
	 * @param tipoImpresa
	 *            tipologia dell'impresa che intende iscriversi
	 * @param rti
	 *            l'impresa partecipa come rti
	 * @param busta
	 *            busta da inviare (1=amministrativa, 2=tecnica, 3=economica)
	 * @return lista dei documenti standard richiesti pi&ugrave; quelli
	 *         dipendenti dalla tipologia impresa, di tipo
	 *         {@link DocumentazioneRichiestaType}
	 * @throws DataAccessException
	 */
	List<DocumentazioneRichiestaType> getDocumentiRichiestiBandoGara(
			String codiceGara, String codiceLotto, Integer tipoImpresa,
			boolean rti, Integer busta) throws DataAccessException;

	/**
	 * Ottimizzazione gestione lotti in getDettaglioGara(...)
	 */
	List<DocumentazioneRichiestaLotto> getDocumentiRichiestiBando(
			String codiceGara, Integer tipoImpresa, boolean rti) 
			throws DataAccessException;

	/**
	 * Ritorna l'elenco dei documenti richiesti per la l'iscrizione all'elenco
	 * fornitori
	 * 
	 * @param codice
	 *            codice univoco del bando d'iscrizione
	 * @param tipoImpresa
	 *            tipologia dell'impresa che intende iscriversi
	 * @param rti
	 *            l'impresa partecipa come rti
	 * @return lista dei documenti standard richiesti pi&ugrave; quelli
	 *         dipendenti dalla tipologia impresa, di tipo
	 *         {@link DocumentazioneRichiestaType}
	 * @throws DataAccessException
	 */
	List<DocumentazioneRichiestaType> getDocumentiRichiestiBandoIscrizione(
			String codice, Integer tipoImpresa, boolean rti)
			throws DataAccessException;

	/**
	 * Ritorna l'elenco dei documenti richiesti per il rinnovo all'elenco
	 * fornitori.
	 * 
	 * @param codice
	 *            codice univoco del bando d'iscrizione
	 * @param tipoImpresa
	 *            tipologia dell'impresa che intende iscriversi
	 * @param rti
	 *            l'impresa partecipa come rti
	 * @return lista dei documenti standard richiesti pi&ugrave; quelli
	 *         dipendenti dalla tipologia impresa, di tipo
	 *         {@link DocumentazioneRichiestaType}
	 * @throws DataAccessException
	 */
	List<DocumentazioneRichiestaType> getDocumentiRichiestiRinnovoIscrizione(
			String codice, Integer tipoImpresa, boolean rti)
			throws DataAccessException;
	
	/**
	 * Ritorna l'elenco dei documenti richiesti per una comunicazione (FS12).
 	 * @param idPrg
	 *            id programma "PA", "PG"
	 * @param idComunicazione
	 *            id della comunicazione  
	 */
	public List<DocumentazioneRichiestaType> getDocumentiRichiestiComunicazione(
			String idPrg, long idComunicazione);

	/**
	 * Ritorna l'elenco degli stati possibili per una iscrizione ad elenco
	 * operatori economici
	 * 
	 * @return lista delle codifiche per lo stato dell'iscrizione
	 * 
	 * @throws DataAccessException
	 */
	List<DatoCodificatoType> getStatiIscrizioneElencoOperatori()
			throws DataAccessException;

//	/**
//	 * Ritorna l'elenco delle gare per cui l'impresa in input risulta abilitata
//	 * all'inserimento di domande di partecipazione
//	 * 
//	 * @param username
//	 *            username dell'impresa
//	 * @return lista dei bandi, di tipo {@link GaraType}
//	 */
//	List<GaraType> getElencoBandiPerDomandePartecipazione(String username)
//			throws DataAccessException;

//	/**
//	 * Ritorna l'elenco delle gare per cui l'impresa in input risulta abilitata
//	 * all'inserimento di richieste di invio offerta
//	 * 
//	 * @param username
//	 *            username dell'impresa
//	 * @return lista dei bandi, di tipo {@link GaraType}
//	 */
//	List<GaraType> getElencoBandiPerRichiesteOfferta(String username)
//			throws DataAccessException;

	/**
	 * Ritorna l'elenco delle gare per cui l'impresa in input risulta abilitata
	 * all'inserimento di richieste di invio offerta
	 * 
	 * @param username
	 *            username dell'impresa
	 * @return lista dei bandi, di tipo {@link GaraType}
	 */
	List<GaraType> getElencoBandiPerRichiesteOfferta(
			String username,
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int indicePrimoRecord,
			int maxNumRecord)
			throws DataAccessException, SqlComposerException;
	
	int countElencoBandiPerRichiesteOfferta(
			String username,
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica)
			throws DataAccessException, SqlComposerException;


//	/**
//	 * Ritorna l'elenco delle gare per cui l'impresa in input risulta abilitata
//	 * all'inserimento di richieste di invio documentazione per la comprova dei
//	 * requisiti
//	 * 
//	 * @param username
//	 *            username dell'impresa
//	 * @return lista dei bandi, di tipo {@link GaraType}
//	 */
//	List<GaraType> getElencoBandiPerRichiesteCheckDocumentazione(String username)
//			throws DataAccessException;

	/**
	 * Ritorna l'elenco delle gare per cui l'impresa in input risulta abilitata
	 * all'inserimento di richieste di invio documentazione per la comprova dei
	 * requisiti
	 * 
	 * @param username
	 *            username dell'impresa
	 * @return lista dei bandi, di tipo {@link GaraType}
	 */
	List<GaraType> getElencoBandiPerRichiesteCheckDocumentazione(
			String username,
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int indicePrimoRecord,
	        int maxNumRecord)
			throws DataAccessException, SqlComposerException;
	
	int countElencoBandiPerRichiesteCheckDocumentazione(
			String username,
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza)
			throws DataAccessException, SqlComposerException;

	/**
	 * Ritorna l'elenco delle gare per cui l'impresa in input risulta abilitata
	 * a delle aste in corso
	 * 
	 * @param username
	 *            username dell'impresa
	 * @return lista dei bandi, di tipo {@link GaraType}
	 */
	List<GaraType> getElencoBandiPerAsteInCorso(
			String username,
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int indicePrimoRecord,
	        int maxNumRecord)
			throws DataAccessException, SqlComposerException;

	/**
	 * Ritorna il conteggio gare per cui l'impresa in input risulta abilitata
	 * a delle aste in corso
	 * 
	 * @param username
	 *            username dell'impresa
	 * @param dataPubblicazioneDa 
	 * 			  non viene utilizzato !!!  
	 * @param dataPubblicazioneA  
	 *            non viene utilizzato !!!
	 * @return lista dei bandi, di tipo {@link GaraType}
	 */
	int countBandiPerAsteInCorso(
			String username,
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza)
			throws DataAccessException, SqlComposerException;

	/**
	 * Ritorna l'abilitazione dell'impresa a partecipare al bando di gara
	 * 
	 * @param username
	 *            username dell'impresa
	 * @param codice
	 *            codice univoco del bando
	 * 
	 * @return true se abilitata, false altrimenti
	 * @throws DataAccessException
	 */
	boolean getAbilitazionePartecipazioneGara(String username, String codice)
			throws DataAccessException;

	/**
	 * Ritorna l'abilitazione dell'impresa a presentare l'offerta per il bando
	 * di gara
	 * 
	 * @param username
	 *            username dell'impresa
	 * @param codice
	 *            codice univoco del bando
	 * 
	 * @return true se abilitata, false altrimenti
	 * @throws DataAccessException
	 */
	boolean getAbilitazionePresentazioneOfferta(String username, String codice)
			throws DataAccessException;

	/**
	 * Ritorna l'abilitazione dell'impresa a presentare la documentazione per la
	 * comprova dei requisiti di partecuipazione al bando di gara
	 * 
	 * @param username
	 *            username dell'impresa
	 * @param codice
	 *            codice univoco del bando
	 * 
	 * @return true se abilitata, false altrimenti
	 * @throws DataAccessException
	 */
	boolean getAbilitazioneCheckDocumentazione(String username, String codice)
			throws DataAccessException;

	/**
	 * Ritorna il tipo di partecipazione dell'impresa per il bando di gara
	 * 
	 * @param username
	 *            username dell'impresa
	 * @param codice
	 *            codice univoco del bando
	 * 
	 * @return tipo di partecipazione al bando di gara
	 * @throws DataAccessException
	 */
	TipoPartecipazioneType getTipoPartecipazioneImpresa(
			String username, String codice, String progOfferta) throws DataAccessException;

	/**
	 * Ritorna l'elenco delle stazioni appaltanti abilitate al bando
	 * d'iscrizione
	 * 
	 * @param codice
	 *            codice univoco del bando d'iscrizione
	 * @return lista delle stazioni appaltanti abilitate
	 * 
	 * @throws DataAccessException
	 */
	List<DatoCodificatoType> getElencoStazioniAppaltantiPerIscrizione(
			String codice) throws DataAccessException;

	/**
	 * Ritorna l'elenco delle categorie/prestazioni dell'impresa per il bando
	 * d'iscrizione in input
	 * 
	 * @param username
	 *            username dell'impresa
	 * @param codice
	 *            codice univoco del bando d'iscrizione
	 * @return lista delle categorie dell'impresa associate al bando, di tipo
	 *         {@link CategoriaImpresaType}
	 * @throws DataAccessException
	 */
	List<CategoriaImpresaType> getCategorieImpresaPerIscrizione(
			String username, String codice) throws DataAccessException;

	/**
	 * Ritorna l'elenco delle classificazioni per lavori utilizzabili in fase di
	 * inserimento o aggiornamento di un'iscrizione all'elenco operatori
	 * economici.
	 * 
	 * @return lista delle classificazioni disponibili per lavori
	 * @throws DataAccessException
	 */
	List<DatoCodificatoType> getElencoClassificazioniLavoriPerIscrizione()
			throws DataAccessException;

	/**
	 * Ritorna l'elenco dei bandi di gara relativi a procedure in aggiudicazione
	 * o concluse, filtrati dai parametri in input.
	 * 
	 * @param username
	 *            username dell'impresa
	 * @return lista dei bandi, di tipo {@link GaraType}
	 * 
	 * @throws DataAccessException
	 * @throws SqlComposerException
	 */
	List<GaraType> searchBandiPerProcInAggiudicazione(
			String username,
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA,
			Date dataScadenzaDa, Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int indicePrimoRecord,
	        int maxNumRecord)
			throws DataAccessException, SqlComposerException;
	
	int countBandiPerProcInAggiudicazione(
			String username,
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA,
			Date dataScadenzaDa, Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica) 
			throws DataAccessException, SqlComposerException;

	/**
	 * Ritorna l'elenco dei bandi di gara filtrati dai parametri in input.
	 * 
	 * @return lista dei bandi, di tipo {@link GaraType}
	 * 
	 * @throws DataAccessException
	 * @throws SqlComposerException
	 */
	List<GaraType> searchBandiConEsito(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord)
			throws DataAccessException, SqlComposerException;
	
	int countBandiConEsito(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione)
			throws DataAccessException, SqlComposerException;

	/**
	 * Ritorna l'elenco dei bandi d'iscrizione o cataloghi filtrati dai
	 * parametri in input.
	 * 
	 * @param username
	 *            username dell'impresa
	 * @param genere
	 *            10=bando d'iscrizione, 20=catalogo
	 * @param stato
	 *            eventuale stato su cui filtrare
	 * 
	 * @return lista dei bandi, di tipo {@link BandoIscrizioneType}
	 * 
	 * @throws DataAccessException
	 */
	List<BandoIscrizioneType> searchBandiIscrizione(String username,
			int genere, Integer stato, String stazioneAppaltante) throws DataAccessException;

	/**
	 * Ritorna true se la gara &egrave; OEPV o prevede la busta tecnica, false altrimenti.
	 * 
	 * @param codiceGara
	 *            codice univoco della gara
	 * @return true se la gara &egrave; ad offerta economicamente pi&ugrave;
	 *         vantaggiosa oppure prevede la busta tecnica, false altrimenti
	 * @throws DataAccessException
	 */
	Boolean isGaraConOffertaTecnica(String codiceGara) throws DataAccessException;

	/**
	 * Verifica se un operatore economico risulta abilitato ad effettuare il
	 * rinnovo per un dato elenco o catalogo.
	 * 
	 * @param codiceBandoIscrizione
	 *            codice identificativo del bando d'iscrizione
	 * @param username
	 *            username dell'impresa
	 * @return true se il rinnovo risulta abilitato, false altrimenti
	 * @throws DataAccessException
	 */
	Boolean isImpresaAbilitataRinnovo(String codiceBandoIscrizione,
			String username) throws DataAccessException;

	/**
	 * Ritorna le occorrenze della lista lavorazioni e forniture da presentare
	 * nell'offerta economica della gara.
	 * 
	 * @param codice
	 *            codice univoco del bando
	 * @return lista delle lavorazioni e forniture, se presente
	 */
	List<VoceDettaglioOffertaType> getVociDettaglioOfferta(String codice);

	/**
	 * Ritorna le occorrenze della lista lavorazioni e forniture presenti
	 * nell'offerta economica della gara ma per le quali non va presentata offerta in quanto non soggette a ribasso.
	 * 
	 * @param codice
	 *            codice univoco del bando
	 * @return lista delle lavorazioni e forniture non soggette a ribasso, se presente
	 */
	List<VoceDettaglioOffertaType> getVociDettaglioOffertaNoRibasso(String codice);
	
	/**
	 * Estrae l'elenco degli attributi aggiuntivi per ogni lavorazione o
	 * fornitura da inserire per presentare l'offerta economica.
	 * 
	 * @param codice
	 *            codice univoco del bando
	 * @return eventuale elenco degli attributi in aggiunta da valorizzare in
	 *         ogni lavorazione o fornitura
	 */
	List<AttributoAggiuntivoOfferta> getAttributiAggiuntiviOfferta(
			String codice);

	/**
	 * Ritorna i dati di dettaglio di una stazione appaltante.
	 * 
	 * @param codice
	 *            codice univoco della stazione appaltante
	 * @return dati di dettaglio
	 */
	DettaglioStazioneAppaltanteType getDettaglioStazioneAppaltante(String codice);
	
	/**
	 * Ritorna l'elenco delle stazioni appaltanti.
	 * 
	 * @return lista delle stazioni appaltanti
	 */
	List<DettaglioStazioneAppaltanteType> getStazioniAppaltanti();
		
	/**
	 * Ritorna l'elenco dei punti ordinanti della gara in input.
	 * 
	 * @param codice
	 *            codice univoco del bando
	 * @return lista dei punti ordinanti
	 */
	List<String> getPuntiOrdinanti(String codice);

	/**
	 * Ritorna l'elenco dei punti istruttori della gara in input.
	 * 
	 * @param codice
	 *            codice univoco del bando
	 * @return lista dei punti istruttori
	 */
	List<String> getPuntiIstruttori(String codice);

	/**
	 * Estrae l'elenco delle mandanti definite nella RTI creata per la gara,
	 * l'elenco o il catalogo in input.
	 * 
	 * @param codiceProcedura
	 *            codice della gara, elenco oppure catalogo
	 * @param username
	 *            username dell'impresa mandataria
	 * @param progOfferta
	 * 			  progressivo offerta
	 * @return lista delle eventuali mandanti definite
	 */
	List<MandanteRTIType> getMandantiRTI(
			String codiceProcedura, String username, String progOfferta);

	/**
	 * Estrae l'eventuale fascicolo di protocollazione.
	 * 
	 * @param codice
	 *            codice gara/elenco/catalogo
	 * @return dati identificativi del fascicolo eventuale
	 */
	FascicoloProtocolloType getFascicoloProtocollo(String codice);

	/**
	 * Estrae il genere di una procedura.
	 * 
	 * @param codice
	 *            codice gara/elenco/catalogo
	 * @return genere
	 */
	Long getGenere(String codice);

	/**
	 * Estrae il codice della gara relativa al lotto in input.
	 * 
	 * @param codiceLotto
	 *            codice del lotto
	 * @return codice della gara
	 */
	String getCodiceGaraFromCodiceLotto(String codiceLotto);

	/**
	 * Ritorna la chiave pubblica di cifratura per una gara o lotto di gara
	 * 
	 * @param codice
	 *            codice gara o codice lotto di gara
	 * @param tipo
	 *            codifica indicante la chiave a che busta si riferisce
	 *            <ul>
	 *            <li><b>FS11A</b> = busta amministrativa</li>
	 *            <li><b>FS11B</b> = busta tecnica</li>
	 *            <li><b>FS11C</b> = busta economica</li>
	 *            <li><b>FS10A</b> = busta di prequalifica</li>
	 *            </ul>
	 * @return
	 */
	String getChiavePubblica(String codice, String tipo);


    /**
     * Verifica l'esistenza di un'impresa attiva sul portale delle consorziate esecutrici per una determinata gara.
     * 
     * @param username
     *            username del consorzio
     * @param codiceGara codice della gara
     * @return true se esiste almeno una consorziata esecutrice, false altrimenti
     * @throws DataAccessException
     */
    Boolean isConsorziateEsecutriciPresenti(String username, String codiceGara)
	    throws DataAccessException;
    
    
    /**
     * Ritorna l'elenco degli atti (documenti) in allegato al bando o al lotto 
     * individuato dai parametri di input
	 * 
	 * @param codiceGara
	 *            codice univoco del bando
	 * @param codiceLotto
	 *            codice univoco del lotto, se si necessita di ottenere i
	 *            documenti del lotto, altrimenti null
	 * @return lista degli atti, di tipo {@link DocumentoAllegatoType}
	 * @throws DataAccessException
     */    
	List<DocumentoAllegatoType> getAttiDocumentiBandoGara(String codiceGara);
	
	
    /**
     * Verifica se una gara ha un'asta elettronica.
     * 
     * @return true la gara ha un'asta elettronica
     * @throws DataAccessException
     */
    Boolean isAstaElettronica(String codiceGara)
	    throws DataAccessException;

    
    /**
     * Verifica se una gara ha un'asta elettronica.
     * 
     * @return true la gara ha un'asta elettronica
     * @throws DataAccessException
     */    
	Boolean isAstaAttiva(String codiceGara, Date dataCorrente);

	
	/**
     * Ritorna la data di inizio di un'asta elettronica.
     * 
     * @return la data di inizio dell'asta elettronica
     * @throws DataAccessException
     */
	Date getDataInizioAsta(String codiceGara);

	/**
     * Ritorna la data di invito di un'asta elettronica.
     * 
     * @return la data di invito dell'asta elettronica
     * @throws DataAccessException
     */
	Date getDataInvitoAsta(String codiceGara);		

	/**
     * Ritorna se una gara è abilitata ad un'asta
     * 
     * @param codiceGara 
     * 				codice/codiceLotto della gara
     * @param username 
     * 				username dell'utente loggato (può essere nullo)
     * 
     * @return true la gara ha un'asta abilitata o meno
     * @throws DataAccessException
     */
	public Boolean isInvitataAsta(String codiceGara, String username);

	/**
	 * Estrae l'id della comunicazione relativa ad una data gare e utente 
	 * tra quelle per le quali esiste un aggiornamento di iscrizione 
	 * posticipato (o parzialmente approvato) 
	 * 
	 * @param tokenrichiedente 
	 * 				username dell'impreesa
	 * @param codice 
	 * 				codice della gara/catalogo
	 * @return l'id della comunicazione 
	 * 		
	 */
	Long checkAggiornamentoIscrizionePosticipata(String tokenRichiedente, 
			String codice) throws DataAccessException;

	/**
	 * Estrae l'elenco degli operatori con relativi soccorsi istruttori per una data gara 
	 */
	public List<EspletGaraOperatoreType> getEspletamentoGaraSoccorsiElencoOperatori(
			String codiceGara, String codiceLotto, String fase, String tokenRichiedente) throws DataAccessException;
	
	/**
	 * Estrae l'elenco degli operatori che hanno presentato busta ammistrativa 
	 * per una data gara 
	 */
	public List<EspletGaraOperatoreType> getEspletamentoGaraDocAmmElencoOperatori(
			String codiceGara, String tokenRichiedente) throws DataAccessException;
	
	/**
	 * Estrae l'elenco degli operatori che hanno presentato busta tecnica 
	 * per una data gara 
	 */
	public List<EspletGaraOperatoreType> getEspletamentoGaraValTecElencoOperatori(
			String codiceGara, String tokenRichiedente) throws DataAccessException;
	
	/**
	 * Estrae l'elenco degli operatori che hanno presentato busta tecnica 
	 * per una data gara a lotti (ottimizzazione per lotti)
	 */
	public List<EspletGaraOperatoreLotto> getEspletamentoGaraValTecElencoOperatoriLotto(
			String codiceGara, String tokenRichiedente) throws DataAccessException;
	
	/**
	 * Estrae l'elenco degli operatori che hanno presentato busta economica 
	 * per una data gara  
	 */
	public List<EspletGaraOperatoreType> getEspletamentoGaraOffEcoElencoOperatori(
			String codiceGara, String tokenRichiedente) throws DataAccessException;

	/**
	 * Estrae l'elenco degli operatori che hanno presentato busta economica 
	 * per una data gara a lotti (ottimizzazione per lotti)
	 */
	public List<EspletGaraOperatoreLotto> getEspletamentoGaraOffEcoElencoOperatoriLotto(
			String codiceGara, String tokenRichiedente) throws DataAccessException;

	/**
	 * Estrae l'elenco degli operatori che hanno presentato busta ammistrativa 
	 * per una data gara 
	 */
	public List<EspletGaraOperatoreType> getEspletamentoGaraGraduatoriaElencoOperatori(
			String codiceGara, String tokenRichiedente) throws DataAccessException;
	
	/**
	 * Estrae l'elenco dei lotti relativi ad una busta ammistrativa 
	 * per una data gara 
	 */
	public List<EspletGaraLottoType> getEspletamentoGaraLotti(
			String codiceGara, String tokenRichiedente) throws DataAccessException;

	/**
	 * Estrae l'elenco dei documenti relativi ad una busta ammistrativa 
	 * per una data gara 
	 */
	public List<EspletGaraDocumentoType> getEspletamentoGaraDocumenti(
			String codice, String codiceLotto, String tokenRichiedente, String tipoBusta, boolean partecipaLotto) throws DataAccessException;
		
	/**
	 * Estrae l'elenco dei componenti RTI relativi ad una busta ammistrativa 
	 * per una data gara 
	 */
	public List<EspletGaraComponenteRtiType> getEspletamentoGaraComponentiRti(
			String codiceGara, String tokenRichiedente) throws DataAccessException;

	/**
	 * Restituisce la fase di un gara
	 */
	public Long getFaseGara(String codice); 
	
	/**
	 * Restituisce le comunicazioni pubbliche degli ultimi 60 gg
	 * 
	 * @param dataInvioDa
	 *            data invio a partire da
	 * @param dataInvioA
	 *            data invio fino a            
	 * @param indicePrimoRecord
	 * @param maxNumRecord
	 * 
	 * @return lista delle comunicazioni personali, di tipo
	 *         {@link ComunicazioneType}
	 * @throws DataAccessException
	 */
	public List<ComunicazioneType> getNews(
			Date dataInvioDa, Date dataInvioA, 
			int indicePrimoRecord, int maxNumRecord,
			String stazioneAppaltante) throws DataAccessException;

	/**
	 * Restituisce il conteggio totale delle comunicazioni pubbliche degli ultimi 60 gg 
	 */
	public int countNews(
			Date dataInvioDa, Date dataInvioA, 
			String stazioneAppaltante) throws DataAccessException;

	/**
	 * Restituisce l'elenco dei soggetti aderenti relativi ad una gara
	 * 
	 * @param codice 
	 * 				codice della gara
	 *  
	 */
	public List<SoggettoAderenteType> getSoggettiAderenti(
			String codiceGara) throws DataAccessException;

	/**
	 * Ottimizzazione gestione lotti in getDettaglioGara(...)
	 */
	public List<SoggettoAderenteLotto> getSoggettiAderentiLotti(
			String codiceGara) throws DataAccessException;

	/**
	 * Restituisce l'elenco degli operatori economici abilitati a elenco 
	 */
	public List<OperatoreIscrittoType> getOperatoriIscritti(
			String codiceGara) throws DataAccessException;		

	/**
	 * Restituisce le categorie degli opertori economici abilitati a elenco
	 */
	public List<CategoriaOperatoreIscrittoType> getElencoCategorieOperatoriIscritti(
			String codiceGara) throws DataAccessException;
	
	/**
	 * Ritorna la lista delle rettifiche Delle Presentazioni
	 * @param codiceGara
	 * @return List<RettificaGaraType>
	 * @throws DataAccessException
	 */
	public List<RettificaGaraType> getRettifichePresentazioneDomandaOfferta(
			String codiceGara, Integer tipter) throws DataAccessException;
	
	/**
	 * Ritorna la lista dei criteri di valutazione di un'offerta (1=tecnica, 2=economica)
	 * @param codiceGara 			
	 * @param tipoBusta (BUSTA_TECNICA, BUSTA_ECONOMICA)
	 * 
	 * @return List<CriterioValutazioneOffertaType>
	 * @throws DataAccessException
	 */
	public List<CriterioValutazioneOffertaType> getCriteriValutazione(
			String codiceGara, String tipoBusta) throws DataAccessException;

	/**
	 * Ottimizzazione gestione lotti in getDettaglioGara(...)
	 */
	public List<CriterioValutazioneOffertaLotto> getCriteriValutazioneLotti(
			String codiceGara, String tipoBusta) throws DataAccessException;	

	/**
	 * Ritorna la lista dei documenti con contenuto/stream nullo
	 * @param codiceGara 			
	 * @param username
	 * @param tipoBusta
	 * 
	 * @return List<DocumentoAllegatoType>
	 * @throws DataAccessException
	 */	
	public List<DocumentoAllegatoLotto> checkDocumentiNulli(
			String codiceGara, String username, String tipoBusta) throws DataAccessException, SqlComposerException;

	/**
	 * Ritorna la lista dei documenti per i quali ci sono differenze 
	 * tra la dimensione del file originario e la dimensione sul db 
	 * @param codiceGara 
	 * @param username
	 * @param tipoBusta
	 * 
	 * @return List<DocumentoAllegatoType>
	 * @throws DataAccessException
	 */	
	public List<DocumentoAllegatoLotto> checkDimensioneDocumenti(
			String codiceGara, String username, String tipoBusta) throws DataAccessException, SqlComposerException;


	/**
	 * Ritorna la lista delle delibere a contrarre 
	 * @param annoInizio  
	 * @param stazioneAppaltante 
	 * @param oggetto
	 * @param tipoAppalto
	 * @param cig
	 * @param dataPubblicazioneDa
	 * @param dataPubblicazioneA
	 * @param sommaUrgenza
	 * @param indicePrimoRecord
	 * @param maxNumRecord
	 * 
	 * @return List<DeliberaType>
	 * @throws DataAccessException
	 */
	public List<DeliberaType> getDelibere(
			int annoInizio,
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 			
			Date dataPubblicazioneA,			
			Boolean sommaUrgenza,
			int indicePrimoRecord, 
			int maxNumRecord) throws DataAccessException, SqlComposerException;

	/**
	 * Restituisce il totale delle delibere a contrarre 
	 * @param annoInizio
	 * @param stazioneAppaltante 
	 * @param oggetto
	 * @param tipoAppalto
	 * @param cig
	 * @param dataPubblicazioneDa
	 * @param dataPubblicazioneA
	 * @param sommaUrgenza
	 * @param indicePrimoRecord
	 * @param maxNumRecord
	 * 
	 * @return List<DeliberaType>
	 * @throws DataAccessException
	 */
	public int countDelibere(
			int annoInizio,
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 			
			Date dataPubblicazioneA, 
			Boolean sommaUrgenza) throws DataAccessException, SqlComposerException;
	
	/**
	 * Ritorna la lista delle gare di somma urgenza 
	 * @param stazioneAppaltante 
	 * @param oggetto
	 * @param tipoAppalto
	 * @param cig
	 * @param dataPubblicazioneDa
	 * @param dataPubblicazioneA
	 * @param indicePrimoRecord
	 * @param maxNumRecord
	 * 
	 * @return List<SommaUrgenzaType>
	 * @throws DataAccessException
	 */
	public List<SommaUrgenzaType> getElencoSommaUrgenza(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			int indicePrimoRecord, 
			int maxNumRecord) throws DataAccessException, SqlComposerException;

	/**
	 * Restituisce il totale delle gare di somma urgenza
	 * @param stazioneAppaltante 
	 * @param oggetto
	 * @param tipoAppalto
	 * @param cig
	 * @param dataPubblicazioneDa
	 * @param dataPubblicazioneA
	 * @param indicePrimoRecord
	 * @param maxNumRecord
	 * 
	 * @return count di SommaUrgenzaType
	 * @throws DataAccessException
	 */
	public int countElencoSommaUrgenza(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA) throws DataAccessException, SqlComposerException;

	/**
	 * recupera la configurazione relativa alla SA
	 */
	public List<WSDMConfigType> getWSDMConfig(
			String stazioneAppaltante) throws DataAccessException, SqlComposerException;
	
	/**
	 * recupera l'elenco degli inviti di una gara
	 */
	public List<InvitoGaraType> getElencoInvitiGara(
			String username, String codice) throws DataAccessException, SqlComposerException;

	/**
	 * recupera il numero di ordine dell'invito
	 * @param username
	 * 			username dell'impresa
	 * @param codice
	 * 			codice gara/lotto del bando
	 */
	public Long getNumeroOrdineInvito(
			String username, String codice) throws DataAccessException, SqlComposerException;
	
}
