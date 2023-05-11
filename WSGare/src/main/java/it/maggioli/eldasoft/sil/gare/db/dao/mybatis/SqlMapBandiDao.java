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
package it.maggioli.eldasoft.sil.gare.db.dao.mybatis;

import it.eldasoft.utils.sql.comp.SqlComposer;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.eldasoft.utils.sql.comp.SqlManager;
import it.maggioli.eldasoft.sil.gare.bl.BandiManager;
import it.maggioli.eldasoft.sil.gare.db.dao.BandiDao;
import it.maggioli.eldasoft.sil.gare.db.mapper.BandiMapper;
import it.maggioli.eldasoft.sil.gare.db.vo.AttributoAggiuntivoOfferta;
import it.maggioli.eldasoft.sil.gare.db.vo.ComunicazioneLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.CriterioValutazioneOffertaLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentazioneRichiestaLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.EspletGaraOperatoreLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.RequisitoRichiestoLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.SoggettoAderenteLotto;
import it.maggioli.eldasoft.sil.gare.ws.*;
import it.maggioli.eldasoft.sil.gare.ws.search.GaraTypeSearch;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import static it.maggioli.eldasoft.sil.gare.util.DAOUtils.*;


/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione dei bandi
 * 
 * @author Stefano.Sabbadin
 */
@Component
public class SqlMapBandiDao implements BandiDao {

    /** tipologia di DBMS da property */
	@Resource
    private String dbms;
	@Autowired
	private BandiMapper bandiMapper;

    @Override
	public String getAppaltiVersion()
		throws DataAccessException {
		return bandiMapper.getAppaltiVersion();
    }

    @Override
	public List<DatoCodificatoType> getElencoStazioniAppaltanti()
		throws DataAccessException {
		return bandiMapper.getElencoStazioniAppaltanti();
    }
    
    
	@Override
	public List<DatoCodificatoType> getElencoStazioniAppaltantiL190()
		throws DataAccessException {
		return bandiMapper.getElencoStazioniAppaltantiL190();
    }

    
    @Override
	public List<DatoCodificatoType> getStatiGara() throws DataAccessException {
		return bandiMapper.getStatiGara();
    }

    
    @Override
	public List<DatoCodificatoType> getEsitiGara() throws DataAccessException {
		return bandiMapper.getEsitiGara();
    }

	@Override
	public List<GaraType> searchBandi(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);

		return bandiMapper.searchBandi(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per lo stato")
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getSommaUrgenza()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
				, filtri.getOrderCriteria().getSqlCriteriaValue()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
	}

	@Override
	public int countBandi(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.countBandi(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per lo stato")
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getSommaUrgenza()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
		);
	}

	@Override
	public List<GaraType> getElencoBandi(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.getElencoBandi(
				filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per il tipo appalto")
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per lo stato")
				, filtri.getSommaUrgenza()
				, filtri.getGaraPrivatistica()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
				, filtri.getOrderCriteria().getSqlCriteriaValue()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
	}
	
	@Override
	public int countElencoBandi(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.countElencoBandi(
				filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per il tipo appalto")
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per lo stato")
				, filtri.getSommaUrgenza()
				, filtri.getGaraPrivatistica()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
		);
	}

	@Override
	public List<GaraType> getElencoBandiScaduti(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.getElencoBandiScaduti(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per il tipo appalto")
				, filtri.getEsito()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per lo stato")
				, filtri.getSommaUrgenza()
				, filtri.getVisualizzaNegoziate()
				, filtri.getGaraPrivatistica()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
				, filtri.getOrderCriteria().getSqlCriteriaValue()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
	}
	
	@Override
	public int countElencoBandiScaduti(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.countElencoBandiScaduti(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per il tipo appalto")
				, filtri.getEsito()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per lo stato")
				, filtri.getSommaUrgenza()
				, filtri.getVisualizzaNegoziate()
				, filtri.getGaraPrivatistica()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())

		);
	}
	
	/**
	 * Calcola l'anno da utilizzare come confronto con le date di scadenza
	 * pubblicazione di bandi, esiti, avvisi.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuto un bando scaduto
	 * @return anno minimo di pubblicazione per essere visibile oggi
	 */
	public static Integer getAnnoMinimoPubblicazione(int numAnniPubblicazione) {
		// invece di calcolare se:
		//
		// oggi <= data scadenza + numAnniPubblicazione
		//
		// con data scadenza = 31/12/<anno esito o pubblicazione> , si spostano
		// gli anni a sinistra facendo diventare la condizione:
		//
		// oggi - numAnniPubblicazione <= data scadenza
		//
		// (lo si fa usando il solo anno)
		GregorianCalendar dataMinimaPubblicazione = new GregorianCalendar();
		return dataMinimaPubblicazione.get(Calendar.YEAR)
				- numAnniPubblicazione;
	}
	
	@Override
	public String getCigBando(String codice) {
		return bandiMapper.getCigBando(
				codice
		);
	}

	@Override
	public StazioneAppaltanteBandoType getStazioneAppaltanteBando(String codice)
	    throws DataAccessException {
		return bandiMapper.getStazioneAppaltanteBando(
				  codice
		);
    }

    @Override
	public GaraType getBando(String codiceGara) throws DataAccessException {
		return bandiMapper.getBando(
				  codiceGara
		);
    }

    
    @Override
	public List<DocumentoAllegatoType> getDocumentiBandoLotto(
	    String codiceGara, String codiceLotto) throws DataAccessException {
		return bandiMapper.getDocumentiBandoLotto(
				  codiceGara
				, codiceLotto
		);
    }
    
    
    @Override
	public List<DocumentoAllegatoType> getDocumentiInvitoLotto(
	    String codiceGara, String codiceLotto) throws DataAccessException {
		return bandiMapper.getDocumentiInvitoLotto(
				  codiceGara
				, codiceLotto
		);
	}

    
    @Override
	public List<DocumentoAllegatoType> getDocumentiInvitoAstaLotto(
	    String codiceGara, String codiceLotto) throws DataAccessException {
		return bandiMapper.getDocumentiInvitoAstaLotto(
				  codiceGara
				, codiceLotto
		);
    }

    
    @Override
	public List<RequisitoRichiestoType> getRequisitiRichiestiBandoLotto(
	    String codiceGara, String codiceLotto) throws DataAccessException {
		return bandiMapper.getRequisitiRichiestiBandoLotto(
				  codiceGara
				, codiceLotto
		);
    }
    
    /** Ottimizzazione vedi getDettaglioGara */
    
    @Override
	public List<RequisitoRichiestoLotto> getRequisitiRichiestiBando(
	    String codiceGara) throws DataAccessException {
		return bandiMapper.getRequisitiRichiestiBando(
				  codiceGara
		);
    }

    
    @Override
	public List<LottoGaraType> getLottiGara(String codiceGara)
	    throws DataAccessException {
		return bandiMapper.getLottiGara(codiceGara);
    }

    /** Ottimizzazione vedi getDettaglioGara */
    
    @Override
	public List<DocumentoAllegatoLotto> getDocumentiGara(String codiceGara)
	    throws DataAccessException {
		return bandiMapper.getDocumentiGara(codiceGara);
    }

	
	@Override
	public List<LottoGaraType> getLottiGaraPerDomandePartecipazione(
			String codiceGara, String progressivoOfferta) throws DataAccessException {
		return bandiMapper.getLottiGaraPerDomandePartecipazione(
				  codiceGara
				, progressivoOfferta
		);
	}

	
	@Override
	public List<LottoGaraType> getLottiGaraPerRichiesteOfferta(String username,
			String codiceGara, String progressivoOfferta) throws DataAccessException {
		return bandiMapper.getLottiGaraPerRichiesteOfferta(
				  codiceGara
				, username
				, progressivoOfferta
		);
	}

	
	@Override
	public List<LottoGaraType> getLottiGaraPlicoUnicoPerRichiesteOfferta(String username,
			String codiceGara, String progressivoOfferta) throws DataAccessException {
		return bandiMapper.getLottiGaraPlicoUnicoPerRichiesteOfferta(
				  codiceGara
				, username
				, progressivoOfferta
		);
	}

	
	@Override
	public List<LottoGaraType> getLottiGaraPerRichiesteCheckDocumentazione(
			String username, String codiceGara) throws DataAccessException {
		return bandiMapper.getLottiGaraPerRichiesteCheckDocumentazione(
				  codiceGara
				, username
		);
	}

    
    @Override
	public List<CategoriaGaraType> getCategorieLotto(String codiceLotto)
	    throws DataAccessException {
		return bandiMapper.getCategorieLotto(codiceLotto);
    }

    
    @Override
	public List<BandoIscrizioneType> getElencoBandiIscrizione(int genere, String stazioneAppaltante, Boolean isAttivo)
	    throws DataAccessException {
		return bandiMapper.getElencoBandiIscrizione(
				  genere
				, stazioneAppaltante
				, isAttivo == null || isAttivo
		);
    }

    @Override
	public StazioneAppaltanteBandoType getStazioneAppaltanteBandoIscrizione(
	    String codice) throws DataAccessException {
		return bandiMapper.getStazioneAppaltanteBandoIscrizione(
				  codice
		);
    }

    @Override
	public BandoIscrizioneType getBandoIscrizione(String codice)
	    throws DataAccessException {
		return bandiMapper.getBandoIscrizione(
				codice
		);
    }

    
    @Override
	public List<DocumentoAllegatoType> getDocumentiBandoIscrizione(
	    String codice) throws DataAccessException {
		return bandiMapper.getDocumentiBandoIscrizione(
				  codice
		);
    }

	
	@Override
	public List<RequisitoRichiestoType> getRequisitiRichiestiBandoIscrizione(
			String codice) throws DataAccessException {
		return bandiMapper.getRequisitiRichiestiBandoIscrizione(
				  codice
		);
	}

	@Override
	public List<CategoriaBandoIscrizioneType> getCategorieBandoIscrizione(
			String codice, String filtroCategorie) throws DataAccessException, SqlComposerException {
		HashMap<String, String> filtroBase = new HashMap<String, String>();
		filtroBase.put("codice", codice);
		if (!StringUtils.isEmpty(filtroCategorie)) {
			SqlComposer composer = SqlManager.getComposer(this.dbms);
			filtroBase.put("operatoreUpper", composer.getFunzioneUpperCase());
			filtroBase.put("filtroCategorie", "%" + filtroCategorie.toUpperCase() + "%");
		}
		return this.getCategorieBandoIscrizione(filtroBase,  null, null, null, null);
	}

	@Override
	public List<ComunicazioneType> getComunicazioniRicevute(
			String username, 
			String codice,
			Date dataInvioDa, Date dataInvioA,
			boolean soccorsoIstruttorio,
			String entita,
			int indicePrimoRecord, int maxNumRecord,
			String stazioneAppaltante) {
		return bandiMapper.getComunicazioniRicevute(
				  username
				, codice
				, dataInvioDa
				, dataInvioA
				, stazioneAppaltante
				, soccorsoIstruttorio ? "1" : ""
				, entita
				, "GARE".equalsIgnoreCase(entita) ? "TORN" : ""
				, getBounds(indicePrimoRecord, maxNumRecord)
		);
	}
	
	/** Ottimizzazione vedi getDettaglioGara */
	
	@Override
	public List<ComunicazioneLotto> getComunicazioniPubblicheRicevute(
			String applicativo, 
			String codice, 
			String entita,
			String stazioneAppaltante) {
		return bandiMapper.getComunicazioniPubblicheRicevute(
				  applicativo
				, codice
				, "$" + codice
				, stazioneAppaltante
				, entita
		);
	}

	@Override
	public List<ComunicazioneType> getComunicazioniRicevuteLotti(
			String username,
			String[] codiciLotto, 
			Date dataInvioDa, Date dataInvioA,
			boolean soccorsoIstruttorio,
			String entita,
			int indicePrimoRecord, int maxNumRecord,
			String stazioneAppaltante) {
		return bandiMapper.getComunicazioniRicevuteLotti(
				  username
				, codiciLotto
				, dataInvioDa
				, dataInvioA
				, entita
				, stazioneAppaltante
				, soccorsoIstruttorio ? "1" : ""
				, getBounds(indicePrimoRecord, maxNumRecord)
		);
	}
	
	@Override
	public List<ComunicazioneType> getComunicazioniInviate(
			String username,
			String codice, 
			String entita,
			int indicePrimoRecord, int maxNumRecord,
			String stazioneAppaltante) {
		return bandiMapper.getComunicazioniInviate(
				  username
				, codice
				, stazioneAppaltante
				, entita
				, getBounds(indicePrimoRecord, maxNumRecord)
		);
	}	

	@Override
	public int countComunicazioniRicevute(
			String username, 
			String codice, 
			String codice2, 
			Date dataInvioDa, Date dataInvioA, 
			boolean soccorsoIstruttorio,
			String entita,
			boolean soloDaLeggere,
			String stazioneAppaltante) {
		return bandiMapper.countComunicazioniRicevute(
				  username
				, codice
				, codice2
				, dataInvioDa
				, dataInvioA
				, soloDaLeggere
				, stazioneAppaltante
				, soccorsoIstruttorio ? "1" : ""
				, entita
				, "GARE".equalsIgnoreCase(entita) ? "TORN" : ""
		);
	}

	@Override
	public int countComunicazioniRicevuteLotti(
			String username, 
			String[] codiciLotto, 
			Date dataInvioDa, Date dataInvioA,
			boolean soccorsoIstruttorio,
			String entita,
			boolean soloDaLeggere,
			String stazioneAppaltante) {
		return bandiMapper.countComunicazioniRicevuteLotti(
				  username
				, codiciLotto
				, dataInvioDa
				, dataInvioA
				, soloDaLeggere
				, stazioneAppaltante
				, soccorsoIstruttorio ? "1" : ""
				, entita
		);
	}
	
	@Override
	public int countComunicazioniInviate(
			String username, 
			String codice,
			String codice2,
			String entita,
			String stazioneAppaltante) {
		return bandiMapper.countComunicazioniInviate(
				  username
				, codice
				, codice2
				, stazioneAppaltante
				, entita
		);
	}
	
	@Override
	public List<DocumentoAllegatoType> getDocumentiComunicazione(
			String applicativo, Long id) throws DataAccessException {
		return bandiMapper.getDocumentiComunicazione(
				  applicativo
				, id
		);
	}
	
	/** Ottimizzazione vedi getDettaglioGara */
	
	@Override
	public List<DocumentoAllegatoLotto> getDocumentiComunicazioniPubblicheRicevute(
			String applicativo, 
			String codice
			/*,String entita ???*/) throws DataAccessException {
		return bandiMapper.getDocumentiComunicazioniPubblicheRicevute(
				  applicativo
				, codice
				, "$" + codice
		);
	}

	@Override
	public ComunicazioneType getComunicazioneRicevuta(String username, Long id, Long idDestinatario, String idprg) {
		return bandiMapper.getComunicazioneRicevuta(
				  username
				, id, idDestinatario
				, idprg
		);
	}

	@Override
	public ComunicazioneType getComunicazioneInviata(String username, Long id, String idprg) {
		return bandiMapper.getComunicazioneInviata(
				  username
				, id
				, idprg
		);
	}

	
	@Override
	public List<InvioType> getElencoInvii(String username, String codice,
			String[] tipiComunicazione) {
		return bandiMapper.getElencoInvii(
				  username
				, codice
				, tipiComunicazione
		);
	}

    @Override
	public Integer getStatoIscrizioneABandoIscrizione(String username,
	    String codice) throws DataAccessException {
		return bandiMapper.getStatoIscrizioneABandoIscrizione(
				  username
				, codice
		);
    }

	
	@Override
	public List<DocumentazioneRichiestaType> getDocumentiRichiestiBandoGara(
			String codiceGara, String codiceLotto, Integer tipoImpresa,
			boolean rti, Integer busta) throws DataAccessException {
		return bandiMapper.getDocumentiRichiestiBandoGara(
				  codiceGara
				, codiceLotto
				, SqlMapBandiDao.getTipoImpresaPartecipazione(tipoImpresa, rti)
				, busta
		);
	}	
	
	/** Ottimizzazione vedi getDettaglioGara */
	
	@Override
	public List<DocumentazioneRichiestaLotto> getDocumentiRichiestiBando(
			String codiceGara, Integer tipoImpresa, boolean rti) 
			throws DataAccessException {
		return bandiMapper.getDocumentiRichiestiBando(
				  codiceGara
				, SqlMapBandiDao.getTipoImpresaPartecipazione(tipoImpresa, rti)
		);
	}

	@Override
	public List<DocumentazioneRichiestaType> getDocumentiRichiestiBandoIscrizione(
			String codice, Integer tipoImpresa, boolean rti)
			throws DataAccessException {
		return bandiMapper.getDocumentiRichiestiBandoIscrizione(
				  codice
				, SqlMapBandiDao.getTipoImpresaPartecipazione(tipoImpresa, rti)
		);
	}

	
	@Override
	public List<DocumentazioneRichiestaType> getDocumentiRichiestiRinnovoIscrizione(
			String codice, Integer tipoImpresa, boolean rti)
			throws DataAccessException {
		return bandiMapper.getDocumentiRichiestiRinnovoIscrizione(
				codice
				, SqlMapBandiDao.getTipoImpresaPartecipazione(tipoImpresa, rti)
		);
	}
	
	@Override
	public List<DocumentazioneRichiestaType> getDocumentiRichiestiComunicazione(
			String idPrg, long idComunicazione)
			throws DataAccessException {
		return bandiMapper.getDocumentiRichiestiComunicazione(
				  idPrg
				, idComunicazione
		);
	}

    @Override
	public List<DatoCodificatoType> getStatiIscrizioneElencoOperatori()
	    throws DataAccessException {
		return bandiMapper.getStatiIscrizioneElencoOperatori();
    }

    @Override
	public List<GaraType> getElencoBandiPerRichiesteOfferta(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		Date now = new Date();
		return bandiMapper.getElencoBandiPerRichiesteOfferta(
				filtri.getTokenRichiedente()
				, now
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per il tipo appalto")
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per lo stato")
				, filtri.getSommaUrgenza()
				, filtri.getGaraPrivatistica()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
				, filtri.getOrderCriteria().getSqlCriteriaValue()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
    }
    
    @Override
	public int countElencoBandiPerRichiesteOfferta(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		Date now = new Date();
		return bandiMapper.countElencoBandiPerRichiesteOfferta(
				filtri.getTokenRichiedente()
				, now
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per il tipo appalto")
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per lo stato")
				, filtri.getSommaUrgenza()
				, filtri.getGaraPrivatistica()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
		);
    }

    @Override
	public List<GaraType> getElencoBandiPerRichiesteCheckDocumentazione(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.getElencoBandiPerRichiesteCheckDocumentazione(
				  filtri.getTokenRichiedente()
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per il tipo appalto")
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per lo stato")
				, filtri.getSommaUrgenza()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
			  	, filtri.getGaraPrivatistica()
				, filtri.getOrderCriteria().getSqlCriteriaValue()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
    }
    
    @Override
	public int countElencoBandiPerRichiesteCheckDocumentazione(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.countElencoBandiPerRichiesteCheckDocumentazione(
				  filtri.getTokenRichiedente()
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per il tipo appalto")
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per lo stato")
				, filtri.getSommaUrgenza()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
				, filtri.getGaraPrivatistica()
		);
    }

    @Override
	public List<GaraType> getElencoBandiPerAsteInCorso(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		Date now = new Date();
		return bandiMapper.getElencoBandiPerAsteInCorso(
				  filtri.getTokenRichiedente()
				, now
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per lo stato")
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getSommaUrgenza()
				, null
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
				, filtri.getOrderCriteria().getSqlCriteriaValue()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
    }

    @Override
	public int countBandiPerAsteInCorso(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		Date now = new Date();
		return bandiMapper.countBandiPerAsteInCorso(
				filtri.getTokenRichiedente()
				, now
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per lo stato")
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getSommaUrgenza()
				, null
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
		);
    }

	@Override
	public boolean getAbilitazionePartecipazioneGara(String username,
			String codice) throws DataAccessException {
		return bandiMapper.getAbilitazionePartecipazioneGara(
				  username
				, codice
		) == 1;
	}

	@Override
	public boolean getAbilitazionePresentazioneOfferta(String username,
			String codice) throws DataAccessException {
		return bandiMapper.getAbilitazionePresentazioneOfferta(
				  username
				, codice
		) == 1;
	}

	@Override
	public boolean getAbilitazioneCheckDocumentazione(String username,
			String codice) throws DataAccessException {
		return bandiMapper.getAbilitazioneCheckDocumentazione(
				  username
				, codice
		) == 1;
	}

    @Override
	public TipoPartecipazioneType getTipoPartecipazioneImpresa(
    		String username, String codice, String progOfferta) throws DataAccessException {
		TipoPartecipazioneType tipoPartecipazione =
				bandiMapper.getTipoPartecipazioneImpresa(
						username
						, codice
						, progOfferta
				);
		if (tipoPartecipazione != null)
			tipoPartecipazione.setRti(tipoPartecipazione.getDenominazioneRti() != null);

		return tipoPartecipazione;
    }
    
    
    @Override
	public List<DatoCodificatoType> getElencoStazioniAppaltantiPerIscrizione(
	    String codice) throws DataAccessException {
		return bandiMapper.getElencoStazioniAppaltantiPerIscrizione(
				  codice
		);
    }

    
    @Override
	public List<CategoriaImpresaType> getCategorieImpresaPerIscrizione(
	    String username, String codice) throws DataAccessException {
		return bandiMapper.getCategorieImpresaPerIscrizione(
				  username
				, codice
		);
    }

	
	@Override
	public List<DatoCodificatoType> getElencoClassificazioniLavoriPerIscrizione()
			throws DataAccessException {
		return bandiMapper.getElencoClassificazioniLavoriPerIscrizione();
	}

	@Override
	public List<GaraType> searchBandiPerProcInAggiudicazione(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.searchBandiPerProcInAggiudicazione(
				  filtri.getTokenRichiedente()
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per il tipo appalto")
				, filtri.getEsito()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per lo stato")
				, filtri.getSommaUrgenza()
				, filtri.getGaraPrivatistica()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
				, filtri.getOrderCriteria().getSqlCriteriaValue()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
	}
	
	@Override
	public int countBandiPerProcInAggiudicazione(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.countBandiPerProcInAggiudicazione(
				  filtri.getTokenRichiedente()
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per il tipo appalto")
				, filtri.getEsito()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per lo stato")
				, filtri.getSommaUrgenza()
				, filtri.getGaraPrivatistica()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
		);
	}

	@Override
	public List<GaraType> searchBandiConEsito(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.searchBandiConEsito(
				  SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per il tipo appalto")
				, filtri.getEsito()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per lo stato")
				, filtri.getSommaUrgenza()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
				, filtri.getOrderCriteria().getSqlCriteriaValue()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
	}

	@Override
	public int countBandiConEsito(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.countBandiConEsito(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, toWithPercentAndUpper(filtri.getCig())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, filtri.getProceduraTelematica()
				, BandiManager.toInt(filtri.getStato(), "Formato non valido per il tipo appalto")
				, filtri.getEsito()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per lo stato")
				, filtri.getSommaUrgenza()
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, toWithPercentAndUpper(filtri.getCodice())
		);
	}

	@Override
	public List<BandoIscrizioneType> searchBandiIscrizione(String username, int genere,
														   Integer stato, String stazioneAppaltante, Boolean isAttivo) {
		return bandiMapper.searchBandiIscrizione(
			  username
			, genere
			, stato
			, stazioneAppaltante
			, isAttivo == null || isAttivo
		);
	}

	@Override
	public Boolean isGaraConOffertaTecnica(String codiceGara) throws DataAccessException {
		return bandiMapper.isGaraConOffertaTecnica(
				codiceGara
		) > 0;
	}

	@Override
	public Boolean isImpresaAbilitataRinnovo(String codiceBandoIscrizione,
			String username) throws DataAccessException {
		return bandiMapper.isImpresaAbilitataRinnovo(
				  username
				, codiceBandoIscrizione
		) > 0;
	}

	@Override
	public List<VoceDettaglioOffertaType> getVociDettaglioOfferta(String codice) {
		return bandiMapper.getVociDettaglioOfferta(
				  codice
		);
	}

	@Override
	public List<VoceDettaglioOffertaType> getVociDettaglioOffertaNoRibasso(String codice) {
		return bandiMapper.getVociDettaglioOffertaNoRibasso(
				  codice
		);
	}

	@Override
	public List<AttributoAggiuntivoOfferta> getAttributiAggiuntiviOfferta(
			String codice) {
		return bandiMapper.getAttributiAggiuntiviOfferta(
				  codice
		);
	}

	@Override
	public DettaglioStazioneAppaltanteType getDettaglioStazioneAppaltante(
			String codice) {
		return bandiMapper.getDettaglioStazioneAppaltante(
				  codice
		);
	}

	
	@Override
	public List<DettaglioStazioneAppaltanteType> getStazioniAppaltanti() {
		return bandiMapper.getStazioniAppaltanti();
	}

	
	@Override
	public List<String> getPuntiOrdinanti(String codice) {
		return bandiMapper.getPuntiOrdinanti(
				  codice
		);
	}

	
	@Override
	public List<String> getPuntiIstruttori(String codice) {
		return bandiMapper.getPuntiIstruttori(
				  codice
		);
	}

	@Override
	public List<MandanteRTIType> getMandantiRTI(
			String codiceProcedura, String username, String progOfferta) {
		return bandiMapper.getMandantiRTI(
				  codiceProcedura
				, username
				, progOfferta
		);
	}

	@Override
	public FascicoloProtocolloType getFascicoloProtocollo(String codice) {
		return bandiMapper.getFascicoloProtocollo(
				  codice
		);
	}

	@Override
	public Long getGenere(String codice) {
		return bandiMapper.getGenere(
				  codice
		);
	}

	@Override
	public String getCodiceGaraFromCodiceLotto(String codiceLotto) {
		return bandiMapper.getCodiceGaraFromCodiceLotto(
				  codiceLotto
		);
	}

	@Override
	public String getChiavePubblica(String codice, String tipo) {
		return bandiMapper.getChiavePubblica(
				  codice
				, tipo
		);
	}

	@Override
	public Boolean isConsorziateEsecutriciPresenti(String username, String codiceGara)
			throws DataAccessException {
		return bandiMapper.isConsorziateEsecutriciPresenti(
				"IMPR"
				, username
				, codiceGara
		) > 0;
	}

	@Override
	public List<DocumentoAllegatoType> getAttiDocumentiBandoGara(String codiceGara) {
		return bandiMapper.getAttiDocumentiBandoGara(
				  codiceGara
		);
	}
	
	@Override
	public Boolean isAstaElettronica(String codiceGara)
			throws DataAccessException {
		Integer result = bandiMapper.isAstaElettronica(
				  codiceGara
				, "$" + codiceGara
		);
		return result != null && result == 1;
	}

	@Override
	public Boolean isAstaAttiva(String codiceGara, Date dataCorrente) {
		Integer result = bandiMapper.isAstaAttiva(
				  codiceGara
				, "$" + codiceGara
				, dataCorrente
		);
		return result != null && result != 0;
	}
	
	@Override
	public Date getDataInizioAsta(String codiceGara) {
		return bandiMapper.getDataInizioAsta(
				  codiceGara
				, "$" + codiceGara
		);
	}

	@Override
	public Date getDataInvitoAsta(String codiceGara) {
		return bandiMapper.getDataInvitoAsta(
				 codiceGara
				, "$" + codiceGara
		);
	}

	@Override
	public Boolean isInvitataAsta(String codiceGara, String username) {
		Date now = new Date();
		Integer result = bandiMapper.isInvitataAsta(
				  codiceGara
				, "$" + codiceGara
				, now
				, username
		);
		return result != null && result > 0;
	}

	@Override
	public Long checkAggiornamentoIscrizionePosticipata(String tokenRichiedente, 
			String codice) throws DataAccessException {
		return bandiMapper.checkAggiornamentoIscrizionePosticipata(
				  tokenRichiedente
				, codice
		);
	}
	
	@Override
	public List<EspletGaraOperatoreType> getEspletamentoGaraSoccorsiElencoOperatori(
			String codiceGara, 
			String codiceLotto,
			String fase,
			String tokenRichiedente) throws DataAccessException {
		return bandiMapper.getEspletamentoGaraSoccorsiElencoOperatori(
				  fase
				, codiceGara
				, codiceLotto
				, tokenRichiedente
		);
	}

	@Override
	public List<EspletGaraOperatoreType> getEspletamentoGaraDocAmmElencoOperatori(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		return bandiMapper.getEspletamentoGaraDocAmmElencoOperatori(
				  codiceGara
				, tokenRichiedente
		);
	}
	
	
	@Override
	public List<EspletGaraOperatoreType> getEspletamentoGaraValTecElencoOperatori(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		return bandiMapper.getEspletamentoGaraValTecElencoOperatori(
				  codiceGara
				, tokenRichiedente
		);
	}

	@Override
	public List<EspletGaraOperatoreLotto> getEspletamentoGaraValTecElencoOperatoriLotto(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		return bandiMapper.getEspletamentoGaraValTecElencoOperatoriLotto(
				  codiceGara + "%"
				, tokenRichiedente
		);
	}

	@Override
	public List<EspletGaraOperatoreType> getEspletamentoGaraOffEcoElencoOperatori(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		return bandiMapper.getEspletamentoGaraOffEcoElencoOperatori(
				  codiceGara
				, tokenRichiedente
		);
	}

	@Override
	public List<EspletGaraOperatoreLotto> getEspletamentoGaraOffEcoElencoOperatoriLotto(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		return bandiMapper.getEspletamentoGaraOffEcoElencoOperatoriLotto(
				  codiceGara + "%"
				, tokenRichiedente
		);
	}

	
	@Override
	public List<EspletGaraOperatoreType> getEspletamentoGaraGraduatoriaElencoOperatori(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		List<EspletGaraOperatoreType> elenco = bandiMapper.getEspletamentoGaraGraduatoriaElencoOperatori(
				  codiceGara
				, tokenRichiedente
				, null
		);

		// prepara l'ordinamento da applicare all'elenco...
		if(elenco != null && elenco.size() > 0) {
			String rical = elenco.get(0).getTipoRibasso();
			String codmodagg = elenco.get(0).getCodiceModAgg();
			String orderBy = null;
			if("17".equalsIgnoreCase(codmodagg)) {
				// "prezzo piu' basso
				orderBy = "1";
			} else if(!"6".equalsIgnoreCase(codmodagg) && 
				("5".equalsIgnoreCase(codmodagg) || "14".equalsIgnoreCase(codmodagg) || "2".equalsIgnoreCase(rical))) {
				orderBy = "2";
			} else if(!"6".equalsIgnoreCase(codmodagg) &&  "1".equalsIgnoreCase(rical)) {
				orderBy = "3";
			} else {
				// default
				orderBy = "4";
			}
		    elenco = bandiMapper.getEspletamentoGaraGraduatoriaElencoOperatori(
					codiceGara
					, tokenRichiedente
					, orderBy
			);
		}
		
		return elenco;
	}
	
	
	@Override
	public List<EspletGaraLottoType> getEspletamentoGaraLotti(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		return bandiMapper.getEspletamentoGaraLotti(
				  codiceGara
				, tokenRichiedente
		);
	}
	
	
	@Override
	public List<EspletGaraDocumentoType> getEspletamentoGaraDocumenti(
			String codice, String codiceLotto, String tokenRichiedente, String tipoBusta, boolean partecipaLotto) throws DataAccessException {
		return bandiMapper.getEspletamentoGaraDocumenti(
				  codice
				, StringUtils.isEmpty(codiceLotto) ? codice : codiceLotto
				, tokenRichiedente
				, tipoBusta
//				, partecipaLotto ? "1" : "0"
		);
	}

	@Override
	public List<EspletGaraComponenteRtiType> getEspletamentoGaraComponentiRti(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		return bandiMapper.getEspletamentoGaraComponentiRti(
				  codiceGara
				, tokenRichiedente
		);
	}
	
	@Override
	public Long getFaseGara(String codice) {
		return bandiMapper.getFaseGara(
				  codice
		);
	}

	@Override
	public List<ComunicazioneType> getNews(
			Date dataInvioDa, Date dataInvioA, 
			int indicePrimoRecord, int maxNumRecord,
			String stazioneAppaltante) throws DataAccessException {
		return bandiMapper.getNews(
				dataInvioDa
				, dataInvioA
				, stazioneAppaltante
				, getBounds(indicePrimoRecord, maxNumRecord)
		);
	}
	
	@Override
	public int countNews(
			Date dataInvioDa, Date dataInvioA, 
			String stazioneAppaltante) throws DataAccessException {
		return bandiMapper.countNews(
				  dataInvioDa
				, dataInvioA
				, stazioneAppaltante
		);
	}

	@Override
	public List<SoggettoAderenteType> getSoggettiAderenti(
			String codiceGara) throws DataAccessException {		
		return bandiMapper.getSoggettiAderenti(
				  codiceGara
		);
	}
	
	/** Ottimizzazione vedi getDettaglioGara */
	
	@Override
	public List<SoggettoAderenteLotto> getSoggettiAderentiLotti(
			String codiceGara) throws DataAccessException {		
		return bandiMapper.getSoggettiAderentiLotti(
				  codiceGara
		);
	}

	@Override
	public List<OperatoreIscrittoType> getOperatoriIscritti(
			String codiceGara) throws DataAccessException {
		return bandiMapper.getOperatoriIscritti(
				  codiceGara
		);
	}

	@Override
	public List<CategoriaOperatoreIscrittoType> getElencoCategorieOperatoriIscritti(
			String codiceGara) throws DataAccessException {		
		return bandiMapper.getElencoCategorieOperatoriIscritti(
				  codiceGara
		);
	}

	@Override
	public List<RettificaGaraType> getRettifichePresentazioneDomandaOfferta(
			String codiceGara, Integer tipter)throws DataAccessException {	
		return bandiMapper.getRettifichePresentazioneDomandaOfferta(
				  codiceGara
				, tipter
		);
	}
	
	@Override
	public List<CriterioValutazioneOffertaType> getCriteriValutazione(
			String codiceGara, String tipoBusta) throws DataAccessException {
		Integer tippar =  null;
		if(WSGareAppaltoSoapImpl.BUSTA_TECNICA.equals(tipoBusta))
			tippar = 1;
		else if(WSGareAppaltoSoapImpl.BUSTA_ECONOMICA.equals(tipoBusta))
			tippar = 2;

		return bandiMapper.getCriteriValutazione(
				  codiceGara
				, tippar
		);
	}	

	/** Ottimizzazione vedi getDettaglioGara */
	
	@Override
	public List<CriterioValutazioneOffertaLotto> getCriteriValutazioneLotti(
			String codiceGara, String tipoBusta) throws DataAccessException {		
		Integer tippar = null;
		if(WSGareAppaltoSoapImpl.BUSTA_TECNICA.equals(tipoBusta))
			tippar = 1;
		else if(WSGareAppaltoSoapImpl.BUSTA_ECONOMICA.equals(tipoBusta))
			tippar = 2;
		return bandiMapper.getCriteriValutazioneLotti(
				  codiceGara
				, tippar
		);
	}

	@Override
	public ImpresaIscrizioneType getImpresaIscrizione(
			String username, String codice) throws DataAccessException {
		return bandiMapper.getImpresaIscrizione(
				  username
				, codice
		);
	}

	@Override
	public List<DocumentoAllegatoLotto> checkDocumentiNulli(
			String codiceGara, String username, String tipoBusta) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);
		
		return bandiMapper.checkDocumentiNulli(
				operatoreWinvcomIdCom("i.IDCOM")
				, composer.getFunzioneUpperCase()
				, StringUtils.upperCase(codiceGara)
				, "$" + StringUtils.upperCase(codiceGara)
				, username
				, tipoBusta
		);
	}

	@Override
	public List<DocumentoAllegatoLotto> checkDimensioneDocumenti(
			String codiceGara, String username, String tipoBusta) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);
		
		return bandiMapper.checkDimensioneDocumenti(
				  operatoreWinvcomIdCom("i.IDCOM")
				, composer.getFunzioneUpperCase()
				, operatoreDataLenghtOper()
				, StringUtils.upperCase(codiceGara)
				, "$" + StringUtils.upperCase(codiceGara)
				, username
				, tipoBusta
		);
	}

	@Override
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
			int maxNumRecord) throws SqlComposerException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.getDelibere(
				annoInizio
				, stazioneAppaltante
				, tipoAppalto
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(oggetto)
				, toWithPercentAndUpper(cig)
				, dataPubblicazioneDa
				, dataPubblicazioneA
				, Boolean.TRUE.equals(sommaUrgenza) ? 1 : 0
				, getBounds(indicePrimoRecord, maxNumRecord)
		);	}
	
	@Override
	public int countDelibere(
			int annoInizio,
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			Boolean sommaUrgenza) throws SqlComposerException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return bandiMapper.countDelibere(
				  annoInizio
				, stazioneAppaltante
				, tipoAppalto
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(oggetto)
				, toWithPercentAndUpper(cig)
				, dataPubblicazioneDa
				, dataPubblicazioneA
				, Boolean.TRUE.equals(sommaUrgenza) ? 1 : 0
		);
	}

	@Override
	public List<SommaUrgenzaType> getElencoSommaUrgenza(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			int indicePrimoRecord, 
			int maxNumRecord) {
		return bandiMapper.getElencoSommaUrgenza(
//				  stazioneAppaltante
//				, getWithPercent(oggetto)
//				, tipoAppalto
//				, getWithPercent(cig)
//				, dataPubblicazioneDa
//				, dataPubblicazioneA
				getBounds(indicePrimoRecord, maxNumRecord)
		);
	}
	
	@Override
	public int countElencoSommaUrgenza(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA) throws SqlComposerException, SqlComposerException {
		return bandiMapper.countElencoSommaUrgenza(
//				  stazioneAppaltante
//				, getWithPercent(oggetto)
//				, tipoAppalto
//				, getWithPercent(cig)
//				, dataPubblicazioneDa
//				, dataPubblicazioneA
		);
	}

	
	@Override
	public List<WSDMConfigType> getWSDMConfig(
			String stazioneAppaltante) throws DataAccessException, SqlComposerException {
		return bandiMapper.getWSDMConfig(
				stazioneAppaltante
		);
	}
	
	
	@Override
	public List<InvitoGaraType> getElencoInvitiGara(
			String username, String codice) throws DataAccessException, SqlComposerException {
		return bandiMapper.getElencoInvitiGara(
				  codice
				, username
		);
	}
	
	@Override
	public Long getNumeroOrdineInvito(
			String username, String codice) throws DataAccessException, SqlComposerException {
		return bandiMapper.getNumeroOrdineInvito(
				  codice
				, username
		);
	}
		
	@Override
	public List<QuestionarioType> getQuestionari(
			String codice, 
			String tipologia, 
			String busta) throws DataAccessException, SqlComposerException {
		return bandiMapper.getQuestionari(
				  toWithPercentAndUpper(codice)
				, tipologia
				, busta
		);
	}

	@Override
	public List<ParametroQuestionarioType> getParametriQuestionario() throws DataAccessException, SqlComposerException {
		return bandiMapper.getParametriQuestionario();
	}

	private String operatoreWinvcomIdCom(String param) {
		if(SqlManager.DATABASE_SQL_SERVER.equals(this.dbms)) {
			return "convert(varchar, " + param + ")";
		} else if(SqlManager.DATABASE_POSTGRES.equals(this.dbms)) {
			return param;
		} else if(SqlManager.DATABASE_ORACLE.equals(this.dbms)) {
			return "TO_CHAR(" + param + ")";
		} else {
			return "";
		}
	}

	private String operatoreDataLenghtOper() {
		if(SqlManager.DATABASE_SQL_SERVER.equals(this.dbms)) {
			return "datalength";
		} else if(SqlManager.DATABASE_POSTGRES.equals(this.dbms)) {
			return "length";
		} else if(SqlManager.DATABASE_ORACLE.equals(this.dbms)) {
			return "dbms_lob.getlength";
		} else {
			return "";
		}
	}

	/**
	 * Determina il tipo impresa da utilizzare per il reperimento della
	 * documentazione dell'impresa a seconda che partecipi o meno in RTI.
	 *
	 * @param tipoImpresa
	 *            tipologia di impresa
	 * @param rti
	 *            true se partecipa in RTI, false altrimenti
	 * @return il tipo impresa in input se l'impresa non partecipa in RTI, 10 se
	 *         riguarda un soggetto individuale che partecipa in un
	 *         raggruppamento di soggetti, 3 altrimenti
	 */
	private static Integer getTipoImpresaPartecipazione(Integer tipoImpresa,
														boolean rti) {
		Integer risultato = tipoImpresa;
		if (rti) {
			if (tipoImpresa <= 5) {
				risultato = 3;
			} else {
				risultato = 10;
			}
		}
		return risultato;
	}

	private List<CategoriaBandoIscrizioneType> getCategorieBandoIscrizione(
			HashMap<String, String> hash, String livello1, String livello2,
			String livello3, String livello4) {
		List<CategoriaBandoIscrizioneType> risultato = new ArrayList<CategoriaBandoIscrizioneType>();
		List<CategoriaBandoIscrizioneType> lista = bandiMapper.getCategorieBandoIscrizione(
				  hash.get("codice")
				, livello1
				, livello2
				, livello3
				, livello4
				, hash.get("filtroCategorie")
				, hash.get("operatoreUpper")
		);

		for (CategoriaBandoIscrizioneType cat : lista) {
			risultato.add(cat);
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

				List<CategoriaBandoIscrizioneType> listaFigli = this
						.getCategorieBandoIscrizione(hash, catLivello1,
								catLivello2, catLivello3, catLivello4);
				risultato.addAll(listaFigli);
			}
		}
		return risultato;
	}

}
