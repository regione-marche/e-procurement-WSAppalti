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
package it.maggioli.eldasoft.sil.gare.bl;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.gare.db.dao.BandiDao;
import it.maggioli.eldasoft.sil.gare.db.vo.AttributoAggiuntivoOfferta;
import it.maggioli.eldasoft.sil.gare.db.vo.ComunicazioneLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.CriterioValutazioneOffertaLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentazioneRichiestaLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.EspletGaraOperatoreLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.RequisitoRichiestoLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.SoggettoAderenteLotto;
import it.maggioli.eldasoft.sil.gare.ws.AbilitazioniGaraType;
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
import it.maggioli.eldasoft.sil.gare.ws.StatisticheComunicazioniPersonaliType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;
import it.maggioli.eldasoft.sil.gare.ws.TipoPartecipazioneType;
import it.maggioli.eldasoft.sil.gare.ws.VoceDettaglioOffertaType;
import it.maggioli.eldasoft.sil.gare.ws.WSDMConfigType;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

/**
 * @author Stefano.Sabbadin
 */
public class BandiManager {

	/** Codice applicazione per l'applicativo di backoffice Appalti. */
	public static final String CODICE_APPLICATIVO_APPALTI = "PG";

	/** Codice applicazione per l'applicativo di frontend portale Appalti. */
	public static final String CODICE_APPLICATIVO_PORTALE = "PA";

	/**
	 * Numero di giorni a partire dalla data odierna entro cui le comunicazioni
	 * si intendono ricevute e non archiviate.
	 */
	private static final int NUM_GIORNI_COMUNICAZIONI_RICEVUTE = -90;
	
	private static final int NUM_GIORNI_NEWS = -60;

	private static final int GENERE_BANDO_ISCRIZIONE = 10;

	Logger logger = Logger.getLogger(BandiManager.class);

    private BandiDao bandiDao;

    /**
     * @param bandiDao
     *            bandiDao da settare internamente alla classe.
     */
    public void setBandiDao(BandiDao bandiDao) {
    	this.bandiDao = bandiDao;
    }

    public List<DatoCodificatoType> getElencoStazioniAppaltanti() {
    	return this.bandiDao.getElencoStazioniAppaltanti();
    }
    
    public List<DatoCodificatoType> getElencoStazioniAppaltantiL190() {
    	return this.bandiDao.getElencoStazioniAppaltantiL190();
    }

    public List<DatoCodificatoType> getStatiGara() {
    	return this.bandiDao.getStatiGara();
    }

    public List<DatoCodificatoType> getEsitiGara() {
    	return this.bandiDao.getEsitiGara();
    }

	/**
	 * Ritorna la versione dell'applicativo di backoffice
	 * 
	 * @return versione del backoffice
	 * @throws DataAccessException
	 */
	public String getAppaltiVersion() {
		return this.bandiDao.getAppaltiVersion();
	}
	
	public static Integer toInt(String value, String errorMessage) {
		Integer res = null;
		try {
			if (value != null && !"".equals(value))
				res = Integer.valueOf(value);
		} catch (NumberFormatException e) {
			Logger.getLogger(BandiManager.class).error(errorMessage + " (" + value + ")", e);
		}	
		return res;
	}
	
    /**
     * Ritorna l'elenco dei bandi di gara filtrati in base ai parametri in input.
     * 
     * @return lista dei bandi, di tipo GaraType
     * @throws SqlComposerException
     */
	public List<GaraType> searchBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			String tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			String stato, 
			String altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord) throws SqlComposerException 
	{
		Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per lo stato");
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.bandiDao.searchBandi(
				stazioneAppaltante, 
				oggetto, 
				cig,
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato, 
				intAltriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione,
				indicePrimoRecord, maxNumRecord);
	}

	/**
	 * restituisce il numero di occorrenze estratte per i bandi 
	 */
	public int countBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			String tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			String stato, 
			String altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws SqlComposerException 
	{
		Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per lo stato");
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.bandiDao.countBandi(
				stazioneAppaltante, 
				oggetto, 
				cig,
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato, 
				intAltriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);
	}

//    /**
//     * Ritorna l'elenco dei bandi di gara in corso di validit&agrave;.
//     * 
//     * @return lista dei bandi, di tipo GaraType
//     * @throws SqlComposerException
//     */
//	public List<GaraType> getElencoBandi() {
//		return this.bandiDao.getElencoBandi();
//	}

    /**
     * Ritorna l'elenco dei bandi di gara in corso di validit&agrave;.
     * 
     * @return lista dei bandi, di tipo GaraType
     * @throws DataAccessException 
     * @throws SqlComposerException
     */
	public List<GaraType> getElencoBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			String tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			String stato,
			String altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per il tipo appalto"); 
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per lo stato"); 
		return this.bandiDao.getElencoBandi(
				stazioneAppaltante, 
				oggetto, 
				cig,
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato,
				intAltriSoggetti,
				sommaUrgenza,
				garaPrivatistica,
				indicePrimoRecord, maxNumRecord);
	}
	
	/**
	 * restituisce il numero di occorrenze estratte per l'elenco bandi 
	 */
	public int countElencoBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			String tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			String stato,
			String altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica) throws DataAccessException, SqlComposerException 
	{
		Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per il tipo appalto"); 
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per lo stato"); 
		return this.bandiDao.countElencoBandi(
				stazioneAppaltante, 
				oggetto, 
				cig,
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato,
				intAltriSoggetti,
				sommaUrgenza,
				garaPrivatistica);
	}

//	/**
//	 * Ritorna l'elenco dei bandi di gara scaduti.
//	 *
//	 * @param numAnniPubblicazione
//	 *            numero di anni per cui va mantenuta la procedura pubblicata
//	 * @return lista dei bandi, di tipo GaraType
//	 * @throws SqlComposerException
//	 */
//	public List<GaraType> getElencoBandiScaduti(int numAnniPubblicazione) {
//		return this.bandiDao.getElencoBandiScaduti(numAnniPubblicazione);
//	}
	
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
	 * @param proceduraTelematica
	 * @param stato
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuta la procedura pubblicata
	 * @return lista dei bandi, di tipo GaraType
	 * @throws DataAccessException 
	 * @throws SqlComposerException
	 */	
	public List<GaraType> getElencoBandiScaduti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA,
			Date dataScadenzaDa, Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			String stato, 
			String esito,
			String altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per il tipo appalto"); 
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per lo stato"); 
		return this.bandiDao.getElencoBandiScaduti(
				stazioneAppaltante, 
				oggetto, 
				cig, 
				intTipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato, 
				esito,
				intAltriSoggetti,
				sommaUrgenza,
				garaPrivatistica,
				numAnniPubblicazione,
				indicePrimoRecord, maxNumRecord);
	}
	
	public int countElencoBandiScaduti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA,
			Date dataScadenzaDa, Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			String stato, 
			String esito,
			String altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per il tipo appalto"); 
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per lo stato"); 
		return this.bandiDao.countElencoBandiScaduti(
				stazioneAppaltante, 
				oggetto, 
				cig, 
				intTipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato, 
				esito,
				intAltriSoggetti,
				sommaUrgenza,
				garaPrivatistica,
				numAnniPubblicazione);
	}
	
	/**
	 * Ritorna il cig in caso di gara a lotto unico e la lista dei cig in caso
	 * di gara a lotti.
	 * 
	 * @param codice
	 *            codice della gara
	 * @return cig singolo o degli n lotti, in tal caso separati da una virgola
	 */
	public String getCigBando(String codice) {
		return this.bandiDao.getCigBando(codice);
	}

	public StazioneAppaltanteBandoType getStazioneAppaltanteBando(String codice) {
		StazioneAppaltanteBandoType stazione = this.bandiDao
			.getStazioneAppaltanteBando(codice);		
		List<String> ordinanti = this.bandiDao.getPuntiOrdinanti(codice);
		List<String> istruttori = this.bandiDao.getPuntiIstruttori(codice);
		stazione.setPuntoOrdinante(ordinanti);
		stazione.setPuntoIstruttore(istruttori);		
		return stazione;
    }

    public GaraType getBando(String codiceGara) {
    	return this.bandiDao.getBando(codiceGara);
    }

    public List<DocumentoAllegatoType> getDocumentiBandoLotto(
	    String codiceGara, String codiceLotto) {
    	return this.bandiDao.getDocumentiBandoLotto(codiceGara, codiceLotto);
    }

    public List<DocumentoAllegatoType> getDocumentiInvitoLotto(
    	    String codiceGara, String codiceLotto) {
    	return this.bandiDao.getDocumentiInvitoLotto(codiceGara, codiceLotto);
    }
    
    public List<DocumentoAllegatoType> getDocumentiInvitoAstaLotto(
    	    String codiceGara, String codiceLotto) {
    	return this.bandiDao.getDocumentiInvitoAstaLotto(codiceGara, codiceLotto);
    }
    
    public List<RequisitoRichiestoType> getRequisitiRichiestiBandoLotto(
	    String codiceGara, String codiceLotto) {
    	return this.bandiDao.getRequisitiRichiestiBandoLotto(codiceGara,
    			codiceLotto);
    }
    
    public List<RequisitoRichiestoLotto> getRequisitiRichiestiBando(
    	    String codiceGara) {
    	return this.bandiDao.getRequisitiRichiestiBando(codiceGara);
    }

    public List<LottoGaraType> getLottiGara(String codiceGara) {
    	return this.bandiDao.getLottiGara(codiceGara);
    }
    
    public List<DocumentoAllegatoLotto> getDocumentiGara(String codiceGara) {
    	return this.bandiDao.getDocumentiGara(codiceGara);
    }

	public List<LottoGaraType> getLottiGaraPerDomandePartecipazione(
			String codiceGara) {
		return this.bandiDao.getLottiGaraPerDomandePartecipazione(codiceGara);
	}

	public List<LottoGaraType> getLottiGaraPerRichiesteOfferta(
			String tokenRichiedente, String codiceGara) {
		return this.bandiDao.getLottiGaraPerRichiesteOfferta(tokenRichiedente,
				codiceGara);
	}

	public List<LottoGaraType> getLottiGaraPlicoUnicoPerRichiesteOfferta(
			String tokenRichiedente, String codiceGara) {
		return this.bandiDao.getLottiGaraPlicoUnicoPerRichiesteOfferta(tokenRichiedente,
				codiceGara);
	}

	public List<LottoGaraType> getLottiGaraPerRichiesteCheckDocumentazione(
			String tokenRichiedente, String codiceGara) {
		return this.bandiDao.getLottiGaraPerRichiesteCheckDocumentazione(
				tokenRichiedente, codiceGara);
	}

    public List<CategoriaGaraType> getCategorieLotto(String codiceLotto) {
    	return this.bandiDao.getCategorieLotto(codiceLotto);
    }

	public List<BandoIscrizioneType> searchBandiIscrizione(String token,
			Integer stato, String stazioneAppaltante) {
		return this.bandiDao.searchBandiIscrizione(token, GENERE_BANDO_ISCRIZIONE, stato, stazioneAppaltante);
	}

	public List<BandoIscrizioneType> getElencoBandiIscrizione(String stazioneAppaltante) {
		return this.bandiDao.getElencoBandiIscrizione(GENERE_BANDO_ISCRIZIONE, stazioneAppaltante);
    }

    public StazioneAppaltanteBandoType getStazioneAppaltanteBandoIscrizione(String codice) {
		StazioneAppaltanteBandoType stazione = this.bandiDao
			.getStazioneAppaltanteBandoIscrizione(codice);		
		List<String> ordinanti = this.bandiDao.getPuntiOrdinanti(codice);
		List<String> istruttori = this.bandiDao.getPuntiIstruttori(codice);
		stazione.setPuntoOrdinante(ordinanti);
		stazione.setPuntoIstruttore(istruttori);
		return stazione;
    }

    public BandoIscrizioneType getBandoIscrizione(String codice) {
    	return this.bandiDao.getBandoIscrizione(codice);
    }

    public List<DocumentoAllegatoType> getDocumentiBandoIscrizione(
	    String codice) {
    	return this.bandiDao.getDocumentiBandoIscrizione(codice);
    }

	public List<RequisitoRichiestoType> getRequisitiRichiestiBandoIscrizione(
			String codice) {
		return this.bandiDao.getRequisitiRichiestiBandoIscrizione(codice);
	}

    public List<CategoriaBandoIscrizioneType> getCategorieBandoIscrizione(String codice, String filtroCategorie) throws SqlComposerException {
    	return this.bandiDao.getCategorieBandoIscrizione(codice, filtroCategorie);
    }

	public List<ComunicazioneType> getComunicazioniPubbliche(String codice, 
			String stazioneAppaltante) {
		List<ComunicazioneType> risultato = this.bandiDao.getComunicazioniRicevute(
				null, 
				codice,
				null, null,
				false,
				0, 0, 
				stazioneAppaltante);
		List<DocumentoAllegatoType> docs = null;
		for (ComunicazioneType comunicazione : risultato) {
			docs = this.bandiDao.getDocumentiComunicazione(CODICE_APPLICATIVO_APPALTI, comunicazione
					.getIdComunicazione());
			comunicazione.setDocumento(docs);
		}
		return risultato;
	}

	public List<ComunicazioneLotto> getLottiComunicazioniPubbliche(
			String codice, String stazioneAppaltante) {
		
		// recupera l'elenco di tutte le comunicazioni associate ai lotti della gara...
		List<ComunicazioneLotto> risultato = this.bandiDao.getComunicazioniPubblicheRicevute(
				CODICE_APPLICATIVO_APPALTI, codice, stazioneAppaltante);
		
		if(risultato != null) {
			
			// recupera l'elenco di tutti i documenti associati alle comunicazioni...
			List<DocumentoAllegatoLotto> lottiDocumenti = this.bandiDao
				.getDocumentiComunicazioniPubblicheRicevute(CODICE_APPLICATIVO_APPALTI, codice);

			for(int i = 0; i < risultato.size(); i++) {
				ComunicazioneType comunicazione = risultato.get(i);
				
				// aggiungi i documenti alla comunicazione...
				List<DocumentoAllegatoType> docs = comunicazione.getDocumento();				
				for(DocumentoAllegatoLotto doc : lottiDocumenti) {
					if( doc.getIdComunicazione() == comunicazione.getIdComunicazione() ) {
						docs.add(doc);
					}
				}				
				comunicazione.setDocumento(docs);
			}
		}		
		return risultato;
	}

//	public StatisticheComunicazioniPersonaliType getStatisticheComunicazioniPersonali(
//			String tokenRichiedente, String codice) {
//		StatisticheComunicazioniPersonaliType risultato = new StatisticheComunicazioniPersonaliType();
//		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
//				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
//		risultato.setNumComunicazioniRicevute(this.bandiDao.countComunicazioniRicevute(tokenRichiedente, codice, dataInvioDa, null, false, null));
//		if (risultato.getNumComunicazioniRicevute() > 0) {
//			risultato.setNumComunicazioniRicevuteDaLeggere(this.bandiDao.countComunicazioniRicevute(tokenRichiedente, codice, dataInvioDa, null, true, null));
//		}
//		risultato.setNumComunicazioniArchiviate(this.bandiDao.countComunicazioniRicevute(tokenRichiedente, codice, null, dataInvioDa, false, null));
//		if (risultato.getNumComunicazioniArchiviate() > 0) {
//			risultato.setNumComunicazioniArchiviateDaLeggere(this.bandiDao.countComunicazioniRicevute(tokenRichiedente, codice, null, dataInvioDa, true, null));
//		}
//		risultato.setNumComunicazioniInviate(this.bandiDao.countComunicazioniInviate(tokenRichiedente, codice, null));
//		return risultato;
//	}

	public StatisticheComunicazioniPersonaliType getStatisticheComunicazioniPersonali(
			String tokenRichiedente, 
			String codice, 
			String stazioneAppaltante) 
	{
		StatisticheComunicazioniPersonaliType risultato = new StatisticheComunicazioniPersonaliType();
		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		risultato.setNumComunicazioniRicevute(this.bandiDao.countComunicazioniRicevute(
				tokenRichiedente, 
				codice, 
				dataInvioDa, null,
				false,
				false, 
				stazioneAppaltante));
		if (risultato.getNumComunicazioniRicevute() > 0) {
			risultato.setNumComunicazioniRicevuteDaLeggere(this.bandiDao.countComunicazioniRicevute(
					tokenRichiedente, 
					codice, 
					dataInvioDa, null, 
					false,
					true, 
					stazioneAppaltante));
		}
		risultato.setNumComunicazioniArchiviate(this.bandiDao.countComunicazioniRicevute(
				tokenRichiedente, 
				codice, 
				null, dataInvioDa, 
				false, 
				false, 
				stazioneAppaltante));
		if (risultato.getNumComunicazioniArchiviate() > 0) {
			risultato.setNumComunicazioniArchiviateDaLeggere(this.bandiDao.countComunicazioniRicevute(
					tokenRichiedente, 
					codice, 
					null, dataInvioDa, 
					false, 
					true, 
					stazioneAppaltante));
		}
		risultato.setNumSoccorsiIstruttori(this.bandiDao.countComunicazioniRicevute(
				tokenRichiedente, 
				codice, 
				dataInvioDa, null, 
				true, 
				false, 
				stazioneAppaltante));
		if (risultato.getNumSoccorsiIstruttori() > 0) {
			risultato.setNumSoccorsiIstruttoriDaLeggere(this.bandiDao.countComunicazioniRicevute(
					tokenRichiedente, 
					codice, 
					dataInvioDa, null, 
					true, 
					true, 
					stazioneAppaltante));
		}
		risultato.setNumComunicazioniInviate(this.bandiDao.countComunicazioniInviate(tokenRichiedente, codice, stazioneAppaltante));
		return risultato;
	}

	public StatisticheComunicazioniPersonaliType getStatisticheComunicazioniPersonaliGaraLotti(
			String tokenRichiedente, 
			String codiceGara, 
			String stazioneAppaltante) 
	{
		StatisticheComunicazioniPersonaliType risultato = new StatisticheComunicazioniPersonaliType();
		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		
		String[] codiciLotto = getCodiciLotto(codiceGara);
		risultato.setNumComunicazioniRicevute(this.bandiDao.countComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto, 
				dataInvioDa, null, 
				false, 
				false, 
				stazioneAppaltante));
		if (risultato.getNumComunicazioniRicevute() > 0) {
			risultato.setNumComunicazioniRicevuteDaLeggere(this.bandiDao.countComunicazioniRicevuteLotti(
					tokenRichiedente, 
					codiciLotto, 
					dataInvioDa, null, 
					false, 
					true, 
					stazioneAppaltante));
		}
		risultato.setNumComunicazioniArchiviate(this.bandiDao.countComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto, 
				null, dataInvioDa, 
				false, 
				false, 
				stazioneAppaltante));
		if (risultato.getNumComunicazioniArchiviate() > 0) {
			risultato.setNumComunicazioniArchiviateDaLeggere(this.bandiDao.countComunicazioniRicevuteLotti(
					tokenRichiedente, 
					codiciLotto, 
					null, dataInvioDa, 
					false, 
					true, 
					stazioneAppaltante));
		}
		risultato.setNumSoccorsiIstruttori(this.bandiDao.countComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto, 
				dataInvioDa, null, 
				true, 
				false, 
				stazioneAppaltante));
		risultato.setNumComunicazioniInviate(this.bandiDao.countComunicazioniInviate(tokenRichiedente, codiceGara, stazioneAppaltante));
		return risultato;
	}

	public String[] getCodiciLotto(String codiceGara) {
		List<LottoGaraType> lotti = this.bandiDao.getLottiGara(codiceGara);
		String[] codiciLotto = new String[lotti.size()];
		for (int i = 0; i < lotti.size(); i++) {
			codiciLotto[i] = lotti.get(i).getCodiceLotto();
		}
		return codiciLotto;
	}
	
	public List<ComunicazioneType> getComunicazioniRicevute(
			String tokenRichiedente, 
			String codice, 
			boolean soccorsoIstruttorio, 
			int indicePrimoRecord, int maxNumRecord, 
			String stazioneAppaltante) 
	{
		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		
		return this.bandiDao.getComunicazioniRicevute(
				tokenRichiedente, 
				codice,
				dataInvioDa, null,
				soccorsoIstruttorio,
				indicePrimoRecord, maxNumRecord, 
				stazioneAppaltante);
	}
	
	public List<ComunicazioneType> getComunicazioniRicevuteGaraLotti(
			String tokenRichiedente, String[] codiciLotto, int indicePrimoRecord,
			int maxNumRecord, String stazioneAppString) {
		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		return this.bandiDao.getComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto,
				dataInvioDa, null,
				false,
				indicePrimoRecord, maxNumRecord, 
				stazioneAppString);
	}
	
	public List<ComunicazioneType> getComunicazioniArchiviate(
			String tokenRichiedente, 
			String codice,
			boolean soccorsoIstruttorio,
			int indicePrimoRecord, int maxNumRecord, 
			String stazioneAppaltante) 
	{
		Date dataInvioA = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		return this.bandiDao.getComunicazioniRicevute(
				tokenRichiedente, 
				codice,
				null, dataInvioA,
				soccorsoIstruttorio,
				indicePrimoRecord, maxNumRecord, 
				stazioneAppaltante);
	}

	public List<ComunicazioneType> getComunicazioniArchiviateGaraLotti(
			String tokenRichiedente, 
			String[] codiciLotto,
			boolean soccorsoIstruttorio,
			int indicePrimoRecord, int maxNumRecord, 
			String stazioneAppString) 
	{
		Date dataInvioA = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		return this.bandiDao.getComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto,
				null, dataInvioA, 
				soccorsoIstruttorio,
				indicePrimoRecord, maxNumRecord, 
				stazioneAppString);
	}
	
	public List<ComunicazioneType> getComunicazioniInviate(
			String tokenRichiedente, String codice, int indicePrimoRecord,
			int maxNumRecord, String stazioneAppaltante) {
		return this.bandiDao.getComunicazioniInviate(tokenRichiedente, codice, indicePrimoRecord, maxNumRecord,
				stazioneAppaltante);
	}

	public int countComunicazioniRicevute(
			String tokenRichiedente, 
			String codice, 
			boolean soccorsoIstruttorio,
			boolean soloDaLeggere,
			String stazioneAppaltante) 
	{
		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		return this.bandiDao.countComunicazioniRicevute(
				tokenRichiedente, 
				codice, 
				dataInvioDa, null,
				soccorsoIstruttorio,
				soloDaLeggere, 
				stazioneAppaltante);
	}

	public int countComunicazioniRicevuteGaraLotti(String tokenRichiedente, String[] codiciLotto, boolean soloDaLeggere,
			String stazioneAppaltante) {
		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		return this.bandiDao.countComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto, 
				dataInvioDa, null,
				false,
				false,
				stazioneAppaltante);
	}

	public int countComunicazioniArchiviate(
			String tokenRichiedente, 
			String codice, 
			boolean soccorsoIstruttorio,
			boolean soloDaLeggere,
			String stazioneAppaltante) 
	{
		Date dataInvioA = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		return this.bandiDao.countComunicazioniRicevute(
				tokenRichiedente, 
				codice,
				null, dataInvioA,
				soccorsoIstruttorio,
				soloDaLeggere,
				stazioneAppaltante);
	}

	public int countComunicazioniArchiviateGaraLotti(
			String tokenRichiedente, 
			String[] codiciLotto,
			boolean soccorsoIstruttorio,
			boolean soloDaLeggere,
			String stazioneAppaltante) 
	{
		Date dataInvioA = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		return this.bandiDao.countComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto, 
				null, dataInvioA,
				soccorsoIstruttorio,
				false,
				stazioneAppaltante);
	}

	public int countComunicazioniInviate(String tokenRichiedente, String codice, 
			String stazioneAppaltante) {
		return this.bandiDao.countComunicazioniInviate(tokenRichiedente, codice,
				stazioneAppaltante);
	}

	public ComunicazioneType getComunicazioneRicevuta(
			String tokenRichiedente,
			long idComunicazione,
			long idDestinatario) 
	{
		ComunicazioneType comunicazione = this.bandiDao
				.getComunicazioneRicevuta(tokenRichiedente, idComunicazione, idDestinatario);
		if (comunicazione != null) {
			List<DocumentoAllegatoType> docs = this.bandiDao
					.getDocumentiComunicazione(CODICE_APPLICATIVO_APPALTI,
							idComunicazione);
			comunicazione.setDocumento(docs);
		}
		return comunicazione;
	}

	public ComunicazioneType getComunicazioneInviata(String tokenRichiedente,
			long idComunicazione) {
		ComunicazioneType comunicazione = this.bandiDao
				.getComunicazioneInviata(tokenRichiedente, idComunicazione);
		if (comunicazione != null) {
			List<DocumentoAllegatoType> docs = this.bandiDao
					.getDocumentiComunicazione(CODICE_APPLICATIVO_PORTALE,
							idComunicazione);
			comunicazione.setDocumento(docs);
		}
		return comunicazione;
	}

	public List<InvioType> getElencoInvii(String tokenRichiedente,
			String codice, String[] tipiComunicazione) {
		return this.bandiDao.getElencoInvii(tokenRichiedente, codice,
				tipiComunicazione);
	}

	public Integer getStatoIscrizioneABandoIscrizione(String tokenRichiedente,
	    String codice) {
		return this.bandiDao.getStatoIscrizioneABandoIscrizione(
			tokenRichiedente, codice);
    }

	public List<DocumentazioneRichiestaType> getDocumentiRichiestiBandoGara(
			String codiceGara, String codiceLotto, String tipoImpresa, boolean rti, String busta) {
		Integer intTipoImpresa = null;
		try {
		    if (tipoImpresa != null && !"".equals(tipoImpresa))
			intTipoImpresa = Integer.valueOf(tipoImpresa);
		} catch (NumberFormatException e) {
		    logger.error("Formato non valido per il tipo impresa ("
			    + tipoImpresa + ")", e);
		}
		Integer intBusta = null;
		try {
		    if (busta != null && !"".equals(busta))
		    	intBusta = Integer.valueOf(busta);
		} catch (NumberFormatException e) {
		    logger.error("Formato non valido per la busta ("
			    + busta + ")", e);
		}

		return this.bandiDao.getDocumentiRichiestiBandoGara(codiceGara, codiceLotto,
			intTipoImpresa, rti, intBusta);
	}

	public List<DocumentazioneRichiestaLotto> getDocumentiRichiestiBando(
			String codiceGara, String tipoImpresa, boolean rti) {
		Integer intTipoImpresa = null;
		try {
		    if (tipoImpresa != null && !"".equals(tipoImpresa))
			intTipoImpresa = Integer.valueOf(tipoImpresa);
		} catch (NumberFormatException e) {
		    logger.error("Formato non valido per il tipo impresa ("
			    + tipoImpresa + ")", e);
		}

		return this.bandiDao.getDocumentiRichiestiBando(codiceGara, 
				intTipoImpresa, rti);
	}

    public List<DocumentazioneRichiestaType> getDocumentiRichiestiBandoIscrizione(
	    String codice, String tipoImpresa, boolean rti) {
		Integer intTipoImpresa = null;
		try {
		    if (tipoImpresa != null && !"".equals(tipoImpresa))
			intTipoImpresa = Integer.valueOf(tipoImpresa);
		} catch (NumberFormatException e) {
		    logger.error("Formato non valido per il tipo impresa ("
			    + tipoImpresa + ")", e);
		}
	
		return this.bandiDao.getDocumentiRichiestiBandoIscrizione(codice,
			intTipoImpresa, rti);
    }

	public List<DocumentazioneRichiestaType> getDocumentiRichiestiRinnovoIscrizione(
			String codice, String tipoImpresa, boolean rti) {
		Integer intTipoImpresa = null;
		try {
			if (tipoImpresa != null && !"".equals(tipoImpresa))
				intTipoImpresa = Integer.valueOf(tipoImpresa);
		} catch (NumberFormatException e) {
			logger.error("Formato non valido per il tipo impresa ("
					+ tipoImpresa + ")", e);
		}

		return this.bandiDao.getDocumentiRichiestiRinnovoIscrizione(codice,
				intTipoImpresa, rti);
	}

	public List<DocumentazioneRichiestaType> getDocumentiRichiestiComunicazione(
			String idPrg, long idComunicazione) 
	{
		return this.bandiDao.getDocumentiRichiestiComunicazione(idPrg, idComunicazione);
	}

    public List<DatoCodificatoType> getStatiIscrizioneElencoOperatori() {
    	return this.bandiDao.getStatiIscrizioneElencoOperatori();
    }

	public ImpresaIscrizioneType getImpresaIscrizione(
			String username, String codice) throws DataAccessException {
		return this.bandiDao.getImpresaIscrizione(username, codice);
	}

//    public List<GaraType> getElencoBandiPerDomandePartecipazione(
//	    String tokenRichiedente) {
//	return this.bandiDao.getElencoBandiPerDomandePartecipazione(tokenRichiedente);
//    }

    
//    public List<GaraType> getElencoBandiPerRichiesteOfferta(
//	    String tokenRichiedente) {
//		return this.bandiDao.getElencoBandiPerRichiesteOfferta(tokenRichiedente);
//    }

    public List<GaraType> getElencoBandiPerRichiesteOfferta(
    	    String tokenRichiedente,
    	    String stazioneAppaltante, 
    	    String oggetto,
    		String cig, 
    		String tipoAppalto, 
    		Date dataPubblicazioneDa, Date dataPubblicazioneA, 
    		Date dataScadenzaDa, Date dataScadenzaA,
    		Boolean proceduraTelematica, 
    		String stato,
    		String altriSoggetti,
    		Boolean sommaUrgenza,
    		Integer garaPrivatistica,
			int indicePrimoRecord,
	        int maxNumRecord) throws SqlComposerException 
    {
    	Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per il tipo appalto"); 
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per lo stato"); 
    	return this.bandiDao.getElencoBandiPerRichiesteOfferta(
    			tokenRichiedente, 
    			stazioneAppaltante, 
				oggetto, 
				cig,
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato,
				intAltriSoggetti,
				sommaUrgenza,
				garaPrivatistica,
				indicePrimoRecord, maxNumRecord);
    }
    
    public int countElencoBandiPerRichiesteOfferta(
    	    String tokenRichiedente,
    	    String stazioneAppaltante, 
    	    String oggetto,
    		String cig, 
    		String tipoAppalto, 
    		Date dataPubblicazioneDa, Date dataPubblicazioneA, 
    		Date dataScadenzaDa, Date dataScadenzaA,
    		Boolean proceduraTelematica, 
    		String stato,
    		String altriSoggetti,
    		Boolean sommaUrgenza,
    		Integer garaPrivatistica) throws SqlComposerException 
    {
    	Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per il tipo appalto"); 
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per lo stato"); 
    	return this.bandiDao.countElencoBandiPerRichiesteOfferta(
    			tokenRichiedente, 
    			stazioneAppaltante, 
				oggetto, 
				cig,
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato,
				intAltriSoggetti,
				sommaUrgenza,
				garaPrivatistica);
    }

//    public List<GaraType> getElencoBandiPerRichiesteCheckDocumentazione(
//	    String tokenRichiedente) {
//		return this.bandiDao.getElencoBandiPerRichiesteCheckDocumentazione(tokenRichiedente);
//    }
    
    public List<GaraType> getElencoBandiPerRichiesteCheckDocumentazione(
    	    String tokenRichiedente,
    	    String stazioneAppaltante, 
    	    String oggetto,
    		String cig, 
    		String tipoAppalto, 
    		Date dataPubblicazioneDa, Date dataPubblicazioneA, 
    		Date dataScadenzaDa, Date dataScadenzaA,
    		Boolean proceduraTelematica, 
    		String stato,
    		String altriSoggetti,
    		Boolean sommaUrgenza,
    		int indicePrimoRecord,
	        int maxNumRecord) throws SqlComposerException 
    {
    	Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per il tipo appalto"); 
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per lo stato"); 
		return this.bandiDao.getElencoBandiPerRichiesteCheckDocumentazione(
				tokenRichiedente,
				stazioneAppaltante, 
				oggetto, 
				cig,
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato,
				intAltriSoggetti,
				sommaUrgenza,
				indicePrimoRecord, maxNumRecord);
	}

    public int countElencoBandiPerRichiesteCheckDocumentazione(
    	    String tokenRichiedente,
    	    String stazioneAppaltante, 
    	    String oggetto,
    		String cig, 
    		String tipoAppalto, 
    		Date dataPubblicazioneDa, Date dataPubblicazioneA, 
    		Date dataScadenzaDa, Date dataScadenzaA,
    		Boolean proceduraTelematica, 
    		String stato,
    		String altriSoggetti,
    		Boolean sommaUrgenza) throws SqlComposerException 
    {
    	Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per il tipo appalto"); 
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per lo stato"); 
		return this.bandiDao.countElencoBandiPerRichiesteCheckDocumentazione(
				tokenRichiedente,
				stazioneAppaltante, 
				oggetto, 
				cig,
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato,
				intAltriSoggetti,
				sommaUrgenza);
	}

    public List<GaraType> getElencoBandiPerAsteInCorso(
    		String tokenRichiedente,
    	    String stazioneAppaltante, 
    	    String oggetto,
    		String cig, 
    		String tipoAppalto, 
    		Date dataPubblicazioneDa, Date dataPubblicazioneA, 
    		Date dataScadenzaDa, Date dataScadenzaA,
    		Boolean proceduraTelematica, 
    		String stato,
    		String altriSoggetti,
    		Boolean sommaUrgenza,
    		int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
    {
		Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per lo stato");
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per altri soggetti");
    	return this.bandiDao.getElencoBandiPerAsteInCorso(
    			tokenRichiedente,
				stazioneAppaltante, 
				oggetto, 
				cig,
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato,
				intAltriSoggetti,
				sommaUrgenza,
				indicePrimoRecord, maxNumRecord);
    }

    public int countBandiPerAsteInCorso(
    		String tokenRichiedente,
    	    String stazioneAppaltante, 
    	    String oggetto,
    		String cig, 
    		String tipoAppalto, 
    		Date dataPubblicazioneDa, Date dataPubblicazioneA, 
    		Date dataScadenzaDa, Date dataScadenzaA,
    		Boolean proceduraTelematica, 
    		String stato,
    		String altriSoggetti,
    		Boolean sommaUrgenza) throws DataAccessException, SqlComposerException 
    {
    	Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per lo stato");
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per altri soggetti");
    	return this.bandiDao.countBandiPerAsteInCorso(
    			tokenRichiedente,
				stazioneAppaltante, 
				oggetto, 
				cig,
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato,
				intAltriSoggetti,
				sommaUrgenza);
    }

	public AbilitazioniGaraType getAbilitazioniGara(String tokenRichiedente,
			String codice) {
		AbilitazioniGaraType abilitazioni = new AbilitazioniGaraType();
		abilitazioni.setRichPartecipazione(this.bandiDao
				.getAbilitazionePartecipazioneGara(tokenRichiedente, codice));
		abilitazioni.setRichInvioOfferta(this.bandiDao
				.getAbilitazionePresentazioneOfferta(tokenRichiedente, codice));
		abilitazioni.setRichComprovaRequisiti(this.bandiDao
				.getAbilitazioneCheckDocumentazione(tokenRichiedente, codice));
		return abilitazioni;
	}

    public TipoPartecipazioneType getTipoPartecipazioneImpresa(
	    String tokenRichiedente, String codice, String progOfferta) {
    	return this.bandiDao.getTipoPartecipazioneImpresa(tokenRichiedente, codice, progOfferta);
    }

    public List<DatoCodificatoType> getElencoStazioniAppaltantiPerIscrizione(
	    String codiceBandoIscrizione) {
    	return this.bandiDao.getElencoStazioniAppaltantiPerIscrizione(codiceBandoIscrizione);
    }

    public List<CategoriaImpresaType> getCategorieImpresaPerIscrizione(
	    String tokenRichiedente, String codiceBandoIscrizione) {
    	return this.bandiDao.getCategorieImpresaPerIscrizione(tokenRichiedente, codiceBandoIscrizione);
    }

	public List<DatoCodificatoType> getElencoClassificazioniLavoriPerIscrizione() {
		return this.bandiDao.getElencoClassificazioniLavoriPerIscrizione();
	}

	public List<GaraType> searchBandiPerProcInAggiudicazione(
			String tokenRichiedente,
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String tipoAppalto,
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			Date dataScadenzaDa, 
			Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			String stato, 
			String esito,
			String altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int indicePrimoRecord,
	        int maxNumRecord) throws SqlComposerException 
	{
		Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per il tipo appalto"); 
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per lo stato"); 
		return this.bandiDao.searchBandiPerProcInAggiudicazione(
				tokenRichiedente,
				stazioneAppaltante,
				oggetto, 
				cig, 
				intTipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato, 
				esito,
				intAltriSoggetti,
				sommaUrgenza,
				garaPrivatistica,
				indicePrimoRecord, maxNumRecord);
	}
	
	public int countBandiPerProcInAggiudicazione(
			String tokenRichiedente,
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String tipoAppalto,
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			Date dataScadenzaDa, 
			Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			String stato, 
			String esito,
			String altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica) throws SqlComposerException 
	{
		Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per il tipo appalto"); 
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per lo stato"); 
		return this.bandiDao.countBandiPerProcInAggiudicazione(
				tokenRichiedente,
				stazioneAppaltante,
				oggetto, 
				cig, 
				intTipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato, 
				esito,
				intAltriSoggetti,
				sommaUrgenza,
				garaPrivatistica);
	}
		
	public List<GaraType> searchBandiConEsito(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String tipoAppalto,
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			Date dataScadenzaDa, 
			Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			String stato,
			String esito,
			String altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord) throws SqlComposerException 
	{
		Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per il tipo appalto"); 
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per lo stato"); 
		//Integer intEsito = toInt(esito, "Formato non valido per l'esito"); 
		return this.bandiDao.searchBandiConEsito(
				stazioneAppaltante,
				oggetto, 
				cig, 
				intTipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato,
				esito,
				intAltriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione,
				indicePrimoRecord, maxNumRecord);
	}
	
	public int countBandiConEsito(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String tipoAppalto,
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			Date dataScadenzaDa, 
			Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			String stato,
			String esito,
			String altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws SqlComposerException 
	{
		Integer intTipoAppalto = toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intStato = toInt(stato, "Formato non valido per il tipo appalto"); 
		Integer intAltriSoggetti = toInt(altriSoggetti, "Formato non valido per lo stato"); 
		//Integer intEsito = toInt(esito, "Formato non valido per l'esito"); 
		return this.bandiDao.countBandiConEsito(
				stazioneAppaltante,
				oggetto, 
				cig, 
				intTipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				intStato,
				esito,
				intAltriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);
	}

	public Boolean isGaraConOffertaTecnica(String codiceGara) {
		return this.bandiDao.isGaraConOffertaTecnica(codiceGara);
	}

	public Boolean isImpresaAbilitataRinnovo(String codiceBandoIscrizione,
			String tokenRichiedente) {
		return this.bandiDao.isImpresaAbilitataRinnovo(codiceBandoIscrizione,
				tokenRichiedente);
	}

	public List<VoceDettaglioOffertaType> getVociDettaglioOfferta(String codice) {
		return this.bandiDao.getVociDettaglioOfferta(codice);
	}

	public List<VoceDettaglioOffertaType> getVociDettaglioOffertaNoRibasso(String codice) {
		return this.bandiDao.getVociDettaglioOffertaNoRibasso(codice);
	}
	
	public List<AttributoAggiuntivoOfferta> getAttributiAggiuntiviOfferta(
			String codice) {
		return this.bandiDao.getAttributiAggiuntiviOfferta(codice);
	}

	public DettaglioStazioneAppaltanteType getDettaglioStazioneAppaltante(
			String codice) {
		return this.bandiDao.getDettaglioStazioneAppaltante(codice);
	}
	
	public List<DettaglioStazioneAppaltanteType> getStazioniAppaltanti() {
		return this.bandiDao.getStazioniAppaltanti();
	}

	public List<String> getPuntiOrdinanti(String codiceGara) {
		return this.bandiDao.getPuntiOrdinanti(codiceGara);
	}

	public List<String> getPuntiIstruttori(String codiceGara) {
		return this.bandiDao.getPuntiIstruttori(codiceGara);
	}

	public List<MandanteRTIType> getMandantiRTI(
			String codiceProcedura,
			String tokenMandataria,
			String progOfferta) 
	{
		return this.bandiDao.getMandantiRTI(codiceProcedura, tokenMandataria, progOfferta);
	}

	public FascicoloProtocolloType getFascicoloProtocollo(String codice) {
		return this.bandiDao.getFascicoloProtocollo(codice);
	}

	public Long getGenere(String codice) {
		return this.bandiDao.getGenere(codice);
	}

	public String getCodiceGaraFromCodiceLotto(String codiceLotto) {
		return this.bandiDao.getCodiceGaraFromCodiceLotto(codiceLotto);
	}

	public String getChiavePubblica(String codice, String tipo) {
		return this.bandiDao.getChiavePubblica(codice, tipo);
	}

	public Boolean isConsorziateEsecutriciPresenti(String tokenRichiedente, String codiceGara) {
		return this.bandiDao.isConsorziateEsecutriciPresenti(tokenRichiedente, codiceGara);
	}
	
	public List<DocumentoAllegatoType> getAttiDocumentiBandoGara(String codiceGara){
		return this.bandiDao.getAttiDocumentiBandoGara(codiceGara);
	}

	public boolean isAstaElettronica(String codiceGara) {
		return this.bandiDao.isAstaElettronica(codiceGara);
	}
	
	public boolean isAstaAttiva(String codiceGara, Date dataCorrente) {
		return this.bandiDao.isAstaAttiva(codiceGara, dataCorrente);
	}
	
	public Date getDataInizioAsta(String codiceGara) {
		return this.bandiDao.getDataInizioAsta(codiceGara);
	}
	
	public Date getDataInvitoAsta(String codiceGara) {
		return this.bandiDao.getDataInvitoAsta(codiceGara);
	}
	
	public Boolean isInvitataAsta(String codiceGara, String username) {
		return this.bandiDao.isInvitataAsta(codiceGara, username);
	}

	public Long checkAggiornamentoIscrizionePosticipata(String tokenRichiedente, String codiceGara) {
		return this.bandiDao.checkAggiornamentoIscrizionePosticipata(tokenRichiedente, codiceGara);
	}

	public List<EspletGaraOperatoreType> getEspletamentoGaraSoccorsiElencoOperatori(
			String codiceGara, 
			String codiceLotto,
			String fase, 
			String tokenRichiedente) 
	{
		// recupera il singolo operatore o l'elenco degli operatori economici 
		// per la gara...
		List<EspletGaraOperatoreType> elenco = this.bandiDao
			.getEspletamentoGaraSoccorsiElencoOperatori(codiceGara, codiceLotto, fase, tokenRichiedente);
		return elenco;
	}

	public List<EspletGaraOperatoreType> getEspletamentoGaraDocAmmElencoOperatori(String codiceGara, String tokenRichiedente) {
		// recupera il singolo operatore o l'elenco degli operatori economici 
		// per la gara...
		List<EspletGaraOperatoreType> elenco = this.bandiDao
			.getEspletamentoGaraDocAmmElencoOperatori(codiceGara, tokenRichiedente);
		
		// recupera i dati di dettaglio del singolo operatore economico
		if(tokenRichiedente != null 
		   && elenco != null && elenco.size() == 1) {
			
			// se c'è un raggruppamento RTI carica i componenti...
			if(elenco.get(0).isRti()) {
				List<EspletGaraComponenteRtiType> componentiRti = this.bandiDao
					.getEspletamentoGaraComponentiRti(codiceGara, tokenRichiedente);			
				elenco.get(0).setComponentiRTI(componentiRti);
			}
			
			// se è una gara a lotti, carica i lotti...
			if(true) {
				List<EspletGaraLottoType> lotti = this.bandiDao
					.getEspletamentoGaraLotti(codiceGara, tokenRichiedente);
				elenco.get(0).setLotti(lotti);
			}
			
			// carica i documenti...
			List<EspletGaraDocumentoType> documenti = this.bandiDao
				.getEspletamentoGaraDocumenti(codiceGara, codiceGara, tokenRichiedente, EspletGaraDocumentoType.ESPLETAMENTO_GARA_BUSTA_AMMINISTRATIVA, true);			
			elenco.get(0).setDocumenti(documenti);
		}
		
		return elenco;
	}

	public List<EspletGaraOperatoreType> getEspletamentoGaraValTecElencoOperatori(
			String codice, String codiceLotto, String tokenRichiedente) {
		// recupera il singolo operatore o l'elenco degli operatori economici 
		// per la gara...
		String codiceGara = (!StringUtils.isEmpty(codiceLotto) ? codiceLotto : codice);
		List<EspletGaraOperatoreType> elenco = this.bandiDao
			.getEspletamentoGaraValTecElencoOperatori(codiceGara, tokenRichiedente);
		
		// recupera i dati di dettaglio del singolo operatore economico
		if(tokenRichiedente != null 
		   && elenco != null && elenco.size() == 1) {
			
			// se c'è un raggruppamento RTI carica i componenti...
			if(elenco.get(0).isRti()) {
				List<EspletGaraComponenteRtiType> componentiRti = this.bandiDao
					.getEspletamentoGaraComponentiRti(codiceGara, tokenRichiedente);			
				elenco.get(0).setComponentiRTI(componentiRti);
			}
			
			// carica i documenti...
			List<EspletGaraDocumentoType> documenti = this.bandiDao
				.getEspletamentoGaraDocumenti(
						codice,
						codiceLotto,
						tokenRichiedente, 
						EspletGaraDocumentoType.ESPLETAMENTO_GARA_BUSTA_TECNICA, 
						true);			
			elenco.get(0).setDocumenti(documenti);
		}
		
		return elenco;
	}


	/* Ottimizzazione per gestione dei lotti */
	public List<EspletGaraOperatoreType> getEspletamentoGaraValTecElencoOperatoriLotto(
			String codice, String tokenRichiedente) {
		List<EspletGaraOperatoreType> elenco = null;
		
//		GaraType dettaglio = this.bandiDao.getBando(codice);
				
		// verifica il tipo di gara (OEPV o con offerta tecnica)...
		boolean offertaTec = this.isGaraConOffertaTecnica(codice);
		
		List<EspletGaraOperatoreLotto> lottiElenco = null;
		if(offertaTec) {
			// recupera le coppie (operatore, lotto) per la gara...
			// (FASGAR >= 5)
			lottiElenco = this.bandiDao
				.getEspletamentoGaraValTecElencoOperatoriLotto(codice, tokenRichiedente);
		}

		// crea la lista di lotti-operatore...
		// la è lista ordinata per (operatore, lotto)...
		if(lottiElenco != null) {			
			elenco = new ArrayList<EspletGaraOperatoreType>();
			
			String lastKey = "";
			for(int i = 0; i < lottiElenco.size(); i++) {
							
				String key = lottiElenco.get(i).getCodiceOperatore();
				
				EspletGaraOperatoreType operatore = null;
				if(!key.equals(lastKey)) {
					// trovato un nuovo operatore...
					operatore = new EspletGaraOperatoreType(lottiElenco.get(i));
					operatore.setLotti( new ArrayList<EspletGaraLottoType>() );
					elenco.add(operatore);					
					lastKey = key;
				} else {
					operatore = elenco.get(elenco.size() - 1);
				}
				
				// associa all'operatore solo il codiceInterno del lotto
				// gli altri dati non servono per sapere se un dato operatore 
				// è abilitato pee un certo lotto...
				EspletGaraLottoType lotto = new EspletGaraLottoType();
				lotto.setLotto( lottiElenco.get(i).getCodiceLotto() );
				operatore.getLotti().add(lotto);
			}
		}

		return elenco;
	}

	public List<EspletGaraOperatoreType> getEspletamentoGaraOffEcoElencoOperatori(
			String codice, String codiceLotto, String tokenRichiedente) {
		// recupera il singolo operatore o l'elenco degli operatori economici 
		// per la gara...
		String codiceGara = (!StringUtils.isEmpty(codiceLotto) ? codiceLotto : codice);

		List<EspletGaraOperatoreType> elenco = this.bandiDao
			.getEspletamentoGaraOffEcoElencoOperatori(codiceGara, tokenRichiedente);
		
		// recupera i dati di dettaglio del singolo operatore economico
		if(tokenRichiedente != null 
		   && elenco != null && elenco.size() == 1) {
			
			// se c'è un raggruppamento RTI carica i componenti...
			// se v_ws_apertura_val_tecnica.rti = 1 
			// allora carica l'elenco dei componenti rti 
			// PS: v_ws_apertura_val_tecnica e v_ws_apertura_off_economica
			//     utilizzano le stesse viste/tabelle, quindi  
			//     si utilizza v_ws_apertura_off_economica.rti == 1 come test
			boolean rti = elenco.get(0).isRti();			
			if(rti) {
				List<EspletGaraComponenteRtiType> componentiRti = this.bandiDao
					.getEspletamentoGaraComponentiRti(codiceGara, tokenRichiedente);			
				elenco.get(0).setComponentiRTI(componentiRti);
			}
			
			// carica i documenti...
			List<EspletGaraDocumentoType> documenti = this.bandiDao
				.getEspletamentoGaraDocumenti(
						codice,
						codiceLotto,
						tokenRichiedente, 
						EspletGaraDocumentoType.ESPLETAMENTO_GARA_BUSTA_ECONOMICA, 
						true);			
			elenco.get(0).setDocumenti(documenti);
		}
		
		return elenco;
	}
	
	/* Ottimizzazione per gestione dei lotti */
	public List<EspletGaraOperatoreType> getEspletamentoGaraOffEcoElencoOperatoriLotto(
			String codice, String tokenRichiedente) {		
		List<EspletGaraOperatoreType> elenco = null;
				
//		GaraType dettaglio = this.bandiDao.getBando(codice);
		
		// recupera le coppie (operatore, lotto) per la gara...
		// (FASGAR >= 6)
		List<EspletGaraOperatoreLotto> lottiElenco = this.bandiDao
			.getEspletamentoGaraOffEcoElencoOperatoriLotto(codice, tokenRichiedente);
				
		// crea la lista di lotti-operatore...
		// la è lista ordinata per (operatore, lotto)...
		if(lottiElenco != null) {			
			elenco = new ArrayList<EspletGaraOperatoreType>();
			
			String lastKey = "";
			for(int i = 0; i < lottiElenco.size(); i++) {
							
				String key = lottiElenco.get(i).getCodiceOperatore();
				
				EspletGaraOperatoreType operatore = null;
				if(!key.equals(lastKey)) {
					// trovato un nuovo operatore...
					operatore = new EspletGaraOperatoreType(lottiElenco.get(i));
					operatore.setLotti( new ArrayList<EspletGaraLottoType>() );
					elenco.add(operatore);					
					lastKey = key;
				} else {
					operatore = elenco.get(elenco.size() - 1);
				}
				
				// associa all'operatore solo il codiceInterno del lotto
				// gli altri dati non servono per sapere se un dato operatore 
				// è abilitato pee un certo lotto...
				EspletGaraLottoType lotto = new EspletGaraLottoType();
				lotto.setLotto( lottiElenco.get(i).getCodiceLotto() );
				operatore.getLotti().add(lotto);
			}
		}

		return elenco;
	}	
	

	public List<EspletGaraOperatoreType> getEspletamentoGaraGraduatoriaElencoOperatori(String codiceGara, String tokenRichiedente) {
		// recupera il singolo operatore o l'elenco degli operatori economici 
		// per la gara...
		List<EspletGaraOperatoreType> elenco = this.bandiDao
			.getEspletamentoGaraGraduatoriaElencoOperatori(codiceGara, tokenRichiedente);
		
		return elenco;
	}
	
	public Long getFaseGara(String codice) {
		return this.bandiDao.getFaseGara(codice);
	}
	
	public List<ComunicazioneType> getNews(
			int indicePrimoRecord, int maxNumRecord, 
			String stazioneAppaltante) {
		Date dataInvioDa = DateUtils.round(
				DateUtils.addDays(new Date(), NUM_GIORNI_NEWS), Calendar.DAY_OF_MONTH);		
		return this.bandiDao.getNews(dataInvioDa, null, indicePrimoRecord, maxNumRecord, stazioneAppaltante);
	}
		
	public int countNews(String stazioneAppaltante) {
		Date dataInvioDa = DateUtils.round(
				DateUtils.addDays(new Date(), NUM_GIORNI_NEWS), Calendar.DAY_OF_MONTH);		
		return this.bandiDao.countNews(dataInvioDa, null, stazioneAppaltante);
	}
	
	public List<SoggettoAderenteType> getSoggettiAderenti(String codiceGara) {		
		return this.bandiDao.getSoggettiAderenti(codiceGara);
	}

	public List<SoggettoAderenteLotto> getLottiSoggettiAderenti(String codiceGara) {		
		return this.bandiDao.getSoggettiAderentiLotti(codiceGara);
	}

	public List<OperatoreIscrittoType> getOperatoriIscritti(String codiceGara) {	
		return this.bandiDao.getOperatoriIscritti(codiceGara);
	}
	
	public List<CategoriaOperatoreIscrittoType> getElencoCategorieOperatoriIscritti(String codiceGara) {
		return this.bandiDao.getElencoCategorieOperatoriIscritti(codiceGara);
	}
	
	public List<RettificaGaraType> getRettifichePresentazioneDomanda(String codiceGara)throws DataAccessException {
		// TIPPER=1 
		return this.bandiDao.getRettifichePresentazioneDomandaOfferta(codiceGara, 1);
	}
	
	public List<RettificaGaraType> getRettifichePresentazioneOfferta(String codiceGara)throws DataAccessException {
		// TIPPER=2
		return this.bandiDao.getRettifichePresentazioneDomandaOfferta(codiceGara, 2);
	}
		
	private void aggiornaCriterioValutazione(
			CriterioValutazioneOffertaType criterio, 
			CriterioValutazioneOffertaType valore) {
		// Descrizione							FORMATO	  B.Tec?  B.Eco.? NUMDECI 	G1CRIREG + G1CRIDEF 	
		// (tab A1143)													  (0<=x<=5)	(n record)
		// -----------------------------------------------------------------------------------------------------------
		// Data										1		X	 	 	 
		// Importo									2		X		X		X (2)	eventuale dominio (VALMIN, VALMAX)
		// Lista valori								3		X		X	 			lista coppie (PUNTUALE, PUNTUALE)
		// Testo									4		X	 	 	 
		// Numero intero							5		X		X	 			eventuale dominio (VALMIN, VALMAX)
		// Numero decimale							6		X		X		X		eventuale dominio (VALMIN, VALMAX)
		// Offerta complessiva mediante importo		50	 			X		X		eventuale dominio (VALMIN, VALMAX) 
		// Offerta complessiva mediante ribasso		51	 			X		X		eventuale dominio (VALMIN, VALMAX)
		// Offerta complessiva mediante prz.unitari	52	 			X		X		eventuale dominio (VALMIN, VALMAX)
		// Nessuno									100		
		// -----------------------------------------------------------------------------------------------------------
		if(criterio != null && valore != null) {
			
			criterio.setValore(null);
			
			// 1 = criteri tecnici, 2 = criteri economici
			int tipo = valore.getTipo();
			
			int numDecimali = (valore.getNumeroDecimali() == null 
							   ? 1
							   : Math.min(valore.getNumeroDecimali().intValue(), 5));
			
			Double minval = criterio.getValoreMin();
			if(valore.getValoreMin() != null) {
				minval = (minval == null ? valore.getValoreMin() : minval);
				minval = Math.min(valore.getValoreMin().doubleValue(), minval.doubleValue());
			}
			
			Double maxval = criterio.getValoreMax();
			if(valore.getValoreMax() != null) {
				maxval = (maxval == null ? valore.getValoreMax() : maxval);
				maxval = Math.max(valore.getValoreMax().doubleValue(), maxval.doubleValue());
			}
			
			// utilizza "valore" per configurare il tipo di input del nuovo 
			// criterio...
			try {
				switch (valore.getFormato()) {
				case 1:			
					// Data
					if(tipo == 1) {	
						criterio.setValoreMin(null);
						criterio.setValoreMax(null);
					}
					break;
				case 2:			
					// Importo				
					if(tipo == 1 || tipo == 2) {
						criterio.setValoreMin(minval);
						criterio.setValoreMax(maxval);
						criterio.setNumeroDecimali(2);
					}				
					break;
				case 3:			
					// Lista valori
					if(tipo == 1 || tipo == 2) {
						if(criterio.getListaValori() == null) {
							criterio.setListaValori( new LinkedHashMap<String, String>() );
						}
						if(valore.getValore() != null) {
							criterio.getListaValori().put(valore.getValore(), valore.getValore());
						}
						criterio.setValoreMin(null);
						criterio.setValoreMax(null);
					}
					break;
				case 4:			
					// Testo
					if(tipo == 1) {
						criterio.setValoreMin(null);
						criterio.setValoreMax(null);
					}
					break;
				case 5:			
					// Numero intero
					if(tipo == 1 || tipo == 2) {
						criterio.setValoreMin(minval);
						criterio.setValoreMax(maxval);
						criterio.setNumeroDecimali(0);
					}
					break;
				case 6:			
					// Numero decimale
					if(tipo == 1 || tipo == 2) {
						criterio.setValoreMin(minval);
						criterio.setValoreMax(maxval);
						criterio.setNumeroDecimali(numDecimali);
					}
					break;
				case 50:		
					// Offerta complessiva mediante importo			
					if(tipo == 2) {
						criterio.setValoreMin(minval);
						criterio.setValoreMax(maxval);
						criterio.setNumeroDecimali(numDecimali);
					}
					break;
				case 51:		
					// Offerta complessiva mediante ribasso (dominio [0, 100]);			
					if(tipo == 2) {
						criterio.setValoreMin( Math.max(0.0, (minval != null ? minval : 0.0)) );
						criterio.setValoreMax( Math.min(100.0, (maxval != null ? maxval : 100.0)) );
						criterio.setNumeroDecimali(numDecimali);
					}
					break;
				case 52:		
					// Offerta complessiva mediante prezzi unitari	
					if(tipo == 2) {
						criterio.setValoreMin(minval);
						criterio.setValoreMax(maxval);
						criterio.setNumeroDecimali(numDecimali);
					}
					break;
				case 100: 		
					// Nessuno				
					break;
				}
			} catch(Exception e) {				
			}
		}
	}
		
	public List<CriterioValutazioneOffertaType> getCriteriValutazioneOfferta(
			String codiceGara, 
			String tipoBusta) throws DataAccessException {
		
		List<CriterioValutazioneOffertaType> valori = this.bandiDao
			.getCriteriValutazione(codiceGara, (StringUtils.isEmpty(tipoBusta) ? null : tipoBusta));			
		
		// ...riorganizza i dati in modo strutturato in base al tipo di dato...
		// (i dati sono ordinati per ngara, tippar, norpar, norpar1, puntuale) 
		List<CriterioValutazioneOffertaType> criteriValutazione = null;
		if(valori != null && valori.size() > 0) {
			criteriValutazione = new ArrayList<CriterioValutazioneOffertaType>();
			
			CriterioValutazioneOffertaType criterio = null;
			String key = ""; 		
			for(int i = 0; i < valori.size(); i++) {
				if ( i == 0 || !key.equalsIgnoreCase(valori.get(i).getCodice()) ) {	
					// crea un nuovo criterio da aggiungere alla lista
					criterio = new CriterioValutazioneOffertaType(valori.get(i));
					criteriValutazione.add(criterio);					
					key = valori.get(i).getCodice();
				}						
				aggiornaCriterioValutazione(criterio, valori.get(i));
			}
		}
		
		return criteriValutazione;
	}
	
	/* Ottimizzazione getDettaglioGara() */
	public List<CriterioValutazioneOffertaLotto> getCriteriValutazioneOffertaLotti(
			String codiceGara, 
			String tipoBusta) throws DataAccessException {
		
		List<CriterioValutazioneOffertaLotto> valori = this.bandiDao
			.getCriteriValutazioneLotti(codiceGara, (StringUtils.isEmpty(tipoBusta) ? null : tipoBusta));
		
		// ...riorganizza i dati in modo strutturato in base al tipo di dato...
		// (i dati sono ordinati per ngara, tippar, norpar, norpar1, puntuale)
		List<CriterioValutazioneOffertaLotto> criteriValutazione = null;
		if(valori != null && valori.size() > 0) {
			criteriValutazione = new ArrayList<CriterioValutazioneOffertaLotto>();
			
			CriterioValutazioneOffertaLotto criterio = null;
			String key = ""; 		
			for(int i = 0; i < valori.size(); i++) {
				if( i == 0 || !key.equalsIgnoreCase(valori.get(i).getDescrizione()) ) {
					// crea un nuovo criterio da aggiungere alla lista
					criterio = new CriterioValutazioneOffertaLotto(valori.get(i));
					criteriValutazione.add(criterio);					
					key = valori.get(i).getDescrizione();
				}						
				aggiornaCriterioValutazione(criterio, valori.get(i));
			}
		}
		
		return criteriValutazione;
	}

	public List<DocumentoAllegatoLotto> checkDocumentiNulli(
			String codiceGara, String username, String tipoBusta) throws DataAccessException, SqlComposerException {		
		List<DocumentoAllegatoLotto> documentiNulli = this.bandiDao
			.checkDocumentiNulli(
					codiceGara, 
					(StringUtils.isEmpty(username) ? null : username),
					(StringUtils.isEmpty(tipoBusta) ? null : tipoBusta));	
		
		return documentiNulli;		
	}
		
	public List<DocumentoAllegatoLotto> checkDimensioneDocumenti(
			String codiceGara, String username, String tipoBusta) throws DataAccessException, SqlComposerException {
		List<DocumentoAllegatoLotto> documenti = this.bandiDao
			.checkDimensioneDocumenti(
					codiceGara, 
					(StringUtils.isEmpty(username) ? null : username),
					(StringUtils.isEmpty(tipoBusta) ? null : tipoBusta));
		
		return documenti;
	}

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
			int maxNumRecord) throws DataAccessException, SqlComposerException
	{
		return this.bandiDao.getDelibere(
				annoInizio, stazioneAppaltante, oggetto, tipoAppalto, cig, dataPubblicazioneDa, dataPubblicazioneA, sommaUrgenza, 
				indicePrimoRecord, maxNumRecord);
	}

	public int countDelibere(
			int annoInizio,
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA, 
			Boolean sommaUrgenza) throws DataAccessException, SqlComposerException 
	{
		return this.bandiDao.countDelibere(
				annoInizio, stazioneAppaltante, oggetto, tipoAppalto, cig, dataPubblicazioneDa, dataPubblicazioneA, sommaUrgenza);
	}
	
	public List<SommaUrgenzaType> getElencoSommaUrgenza(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			int indicePrimoRecord, 
			int maxNumRecord) throws DataAccessException, SqlComposerException
	{
		return this.bandiDao.getElencoSommaUrgenza(
				stazioneAppaltante, oggetto, tipoAppalto, cig, dataPubblicazioneDa, dataPubblicazioneA,
				indicePrimoRecord, maxNumRecord);
	}

	public int countElencoSommaUrgenza(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA) throws DataAccessException, SqlComposerException 
	{
		return this.bandiDao.countElencoSommaUrgenza(
				stazioneAppaltante, oggetto, tipoAppalto, cig, dataPubblicazioneDa, dataPubblicazioneA);
	}

	public List<WSDMConfigType> getWSDMConfig(
			String stazioneAppaltante) throws DataAccessException, SqlComposerException 
	{
		return this.bandiDao.getWSDMConfig(stazioneAppaltante);
	}
	
	public List<InvitoGaraType> getElencoInvitiGara(
			String username, String codice) throws DataAccessException, SqlComposerException 
	{
		return this.bandiDao.getElencoInvitiGara(username, codice);
	}
	
	public Long getNumeroOrdineInvito(
			String username, 
			String codice) throws DataAccessException, SqlComposerException 
	{
		return this.bandiDao.getNumeroOrdineInvito(username, codice);
	}
	
}
