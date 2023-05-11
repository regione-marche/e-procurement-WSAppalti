/*
 * Created on 08/lug/12
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
import it.maggioli.eldasoft.sil.gare.db.dao.EsitiDao;
import it.maggioli.eldasoft.sil.gare.db.mapper.EsitiMapper;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.EsitoType;
import it.maggioli.eldasoft.sil.gare.ws.LottoEsitoType;
import it.maggioli.eldasoft.sil.gare.ws.LottoGaraType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;
import it.maggioli.eldasoft.sil.gare.ws.search.EsitoTypeSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static it.maggioli.eldasoft.sil.gare.util.DAOUtils.*;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione degli esiti
 * 
 * @author Stefano.Sabbadin
 */
@Component
public class SqlMapEsitiDao implements EsitiDao {

	/** tipologia di DBMS da property */
	@Resource
	private String dbms;

	@Autowired
	private EsitiMapper esitiMapper;

	@Override
	public List<EsitoType> searchEsiti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);

		return esitiMapper.searchEsiti(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, toWithPercentAndUpper(filtri.getCig())
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getProceduraTelematica()
				, filtri.getSommaUrgenza()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
			);
	}
	
	@Override
	public int countEsiti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);

		return esitiMapper.countEsiti(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, toWithPercentAndUpper(filtri.getCig())
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getProceduraTelematica()
				, filtri.getSommaUrgenza()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
		);
	}
	
	@Override
	public List<EsitoType> getElencoEsiti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);

		return esitiMapper.getElencoEsiti(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, toWithPercentAndUpper(filtri.getCig())
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getProceduraTelematica()
				, filtri.getSommaUrgenza()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
	}
	
	@Override
	public int countElencoEsiti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);

		return esitiMapper.countElencoEsiti(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, toWithPercentAndUpper(filtri.getCig())
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getProceduraTelematica()
				, filtri.getSommaUrgenza()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
		);
	}
	
	@Override
	public List<EsitoType> getElencoEsitiAffidamenti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);

		return esitiMapper.getElencoEsitiAffidamenti(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, toWithPercentAndUpper(filtri.getCig())
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getProceduraTelematica()
				, filtri.getSommaUrgenza()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
	}
	
	@Override
	public int countElencoEsitiAffidamenti(EsitoTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);

		return esitiMapper.countElencoEsitiAffidamenti(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, BandiManager.toInt(filtri.getTipoAppalto(), "Formato non valido per il tipo appalto")
				, toWithPercentAndUpper(filtri.getCig())
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getProceduraTelematica()
				, filtri.getSommaUrgenza()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
		);
	}
	
	@Override
	public StazioneAppaltanteBandoType getStazioneAppaltanteEsito(String codice)
			throws DataAccessException {
		return esitiMapper.getStazioneAppaltanteEsito(codice);
	}
	
	@Override
	public EsitoType getEsito(String codiceGara) throws DataAccessException {
		return esitiMapper.getEsito(codiceGara);
	}
	
	@Override
	public List<LottoGaraType> getLottiGaraConEsito(String codiceGara)
			throws DataAccessException {
		return esitiMapper.getLottiGaraConEsito(codiceGara);
	}
	
	/** Ottimizzazione getDettaglioEsito() */
	@Override
	public List<DocumentoAllegatoLotto> getDocumentiLottiGaraConEsito(String codiceGara)
			throws DataAccessException {
		return esitiMapper.getDocumentiLottiGaraConEsito(codiceGara);
	}
	
	@Override
	public boolean isAQAggiudicatariMultipli(String codiceGara)
			throws DataAccessException {
		return esitiMapper.isAQAggiudicatariMultipli(codiceGara) > 0;
	}
	
	@Override
	public List<LottoEsitoType> getLottiEsito(String codiceGara)
			throws DataAccessException {
		return esitiMapper.getLottiEsito(codiceGara);
	}
	
	@Override
	public List<String> getAQAggiudicatariMultipli(String codiceLotto)
			throws DataAccessException {
		return esitiMapper.getAQAggiudicatariMultipli(codiceLotto);
	}
	
	@Override
	public List<String> getAggiudicatariaSingola(String codiceLotto)
			throws DataAccessException {
		return esitiMapper.getAggiudicatariaSingola(codiceLotto);
	}

	@Override
	public List<DocumentoAllegatoType> getDocumentiEsitoLotto(
			String codiceGara, String codiceLotto) throws DataAccessException {
		return esitiMapper.getDocumentiEsitoLotto(codiceGara, codiceLotto);
	}

}
