/*
 * Created on 15/lug/12
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
import it.maggioli.eldasoft.sil.gare.db.dao.AvvisiDao;
import it.maggioli.eldasoft.sil.gare.db.mapper.AvvisiMapper;
import it.maggioli.eldasoft.sil.gare.ws.AvvisoType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;
import it.maggioli.eldasoft.sil.gare.ws.search.AvvisiTypeSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static it.maggioli.eldasoft.sil.gare.util.DAOUtils.*;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione degli avvisi.
 * 
 * @author Stefano.Sabbadin
 */
@Component
public class SqlMapAvvisiDao implements AvvisiDao {

	/** tipologia di DBMS da property */
	@Resource
	private String dbms;
	@Autowired
	private AvvisiMapper avvisiMapper;

	@Override
	public List<AvvisoType> searchAvvisi(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);

		return avvisiMapper.searchAvvisi(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, BandiManager.toInt(filtri.getTipoAvviso(), "Formato non valido per il tipo avvis")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
	}

	@Override
	public int countAvvisi(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);

		return avvisiMapper.countAvvisi(
				SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, BandiManager.toInt(filtri.getTipoAvviso(), "Formato non valido per il tipo avvis")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
		);
	}

	@Override
	public List<AvvisoType> getElencoAvvisi(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);
		Date today = new Date();

		return avvisiMapper.getElencoAvvisi(
				  today
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, BandiManager.toInt(filtri.getTipoAvviso(), "Formato non valido per il tipo avvis")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
	}
	
	@Override
	public int countElencoAvvisi(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);
		Date today = new Date();

		return avvisiMapper.countElencoAvvisi(
				  today
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, BandiManager.toInt(filtri.getTipoAvviso(), "Formato non valido per il tipo avvis")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
		);
	}
	
	@Override
	public List<AvvisoType> getElencoAvvisiScaduti(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException  {
		SqlComposer composer = SqlManager.getComposer(dbms);
		Date today = new Date();

		return avvisiMapper.getElencoAvvisiScaduti(
				  today
				, SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, BandiManager.toInt(filtri.getTipoAvviso(), "Formato non valido per il tipo avvis")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
				, getBounds(filtri.getIndicePrimoRecord(), filtri.getMaxNumRecord())
		);
	}
	
	@Override
	public int countElencoAvvisiScaduti(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);
		Date today = new Date();

		return avvisiMapper.countElencoAvvisiScaduti(
				  today
				, SqlMapBandiDao.getAnnoMinimoPubblicazione(filtri.getNumAnniPubblicazione())
				, filtri.getStazioneAppaltante()
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(filtri.getOggetto())
				, BandiManager.toInt(filtri.getTipoAvviso(), "Formato non valido per il tipo avvis")
				, filtri.getDataPubblicazioneDa()
				, filtri.getDataPubblicazioneA()
				, filtri.getDataScadenzaDa()
				, filtri.getDataScadenzaA()
				, BandiManager.toInt(filtri.getAltriSoggetti(), "Formato non valido per altri soggetti")
				, filtri.getIsGreen()
				, filtri.getIsRecycle()
				, filtri.getIsPnrr()
		);	
	}
	
	@Override
	public StazioneAppaltanteBandoType getStazioneAppaltanteAvviso(String codice)
			throws DataAccessException {
		return avvisiMapper.getStazioneAppaltanteAvviso(codice);
	}
	
	@Override
	public AvvisoType getAvviso(String codiceGara) throws DataAccessException {
		return avvisiMapper.getAvviso(codiceGara);
	}

}
