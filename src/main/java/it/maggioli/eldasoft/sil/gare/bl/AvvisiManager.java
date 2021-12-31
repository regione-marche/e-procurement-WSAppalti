/*
 * Created on 8/giu/12
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
import it.maggioli.eldasoft.sil.gare.db.dao.AvvisiDao;
import it.maggioli.eldasoft.sil.gare.ws.AvvisoType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

/**
 * @author Stefano.Sabbadin
 */
public class AvvisiManager {

	Logger logger = Logger.getLogger(AvvisiManager.class);

	private AvvisiDao avvisiDao;

	/**
	 * @param avvisiDao
	 *            the avvisiDao to set
	 */
	public void setAvvisiDao(AvvisiDao avvisiDao) {
		this.avvisiDao = avvisiDao;
	}

	
	
	public List<AvvisoType> searchAvvisi(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			String altriSoggetti,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord) throws SqlComposerException 
	{
		Integer intTipoAvviso = BandiManager.toInt(tipoAvviso, "Formato non valido per il tipo avvis");
		Integer intAltriSoggetti = BandiManager.toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.avvisiDao.searchAvvisi(
				stazioneAppaltante, 
				oggetto,
				intTipoAvviso, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				intAltriSoggetti,
				numAnniPubblicazione,
				indicePrimoRecord, maxNumRecord);
	}
	
	public int countAvvisi(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			String altriSoggetti,
			int numAnniPubblicazione) throws SqlComposerException 
	{
		Integer intTipoAvviso = BandiManager.toInt(tipoAvviso, "Formato non valido per il tipo avvis");
		Integer intAltriSoggetti = BandiManager.toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.avvisiDao.countAvvisi(
				stazioneAppaltante, 
				oggetto,
				intTipoAvviso, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				intAltriSoggetti,
				numAnniPubblicazione);
	}

	public List<AvvisoType> getElencoAvvisi() throws DataAccessException, SqlComposerException {
		return this.avvisiDao.getElencoAvvisi(
				null, null, null, null, null, null, null, null, -1, -1);
	}
	
	public List<AvvisoType> getElencoAvvisi(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			String altriSoggetti,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		Integer intTipoAvviso = BandiManager.toInt(tipoAvviso, "Formato non valido per il tipo avvis");
		Integer intAltriSoggetti = BandiManager.toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.avvisiDao.getElencoAvvisi(
				stazioneAppaltante, 
				oggetto,
				intTipoAvviso, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA,
				intAltriSoggetti,
				indicePrimoRecord, maxNumRecord);
	}
	
	public int countElencoAvvisi(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			String altriSoggetti) throws DataAccessException, SqlComposerException 
	{
		Integer intTipoAvviso = BandiManager.toInt(tipoAvviso, "Formato non valido per il tipo avvis");
		Integer intAltriSoggetti = BandiManager.toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.avvisiDao.countElencoAvvisi(
				stazioneAppaltante, 
				oggetto,
				intTipoAvviso, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA,
				intAltriSoggetti);
	}

	public List<AvvisoType> getElencoAvvisiScaduti(int numAnniPubblicazione) 
		throws DataAccessException, SqlComposerException {
		return this.avvisiDao.getElencoAvvisiScaduti(
				null, null, null, null, null, null, null, null,
				numAnniPubblicazione, -1, -1);
	}

	public List<AvvisoType> getElencoAvvisiScaduti(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			String altriSoggetti,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		Integer intTipoAvviso = BandiManager.toInt(tipoAvviso, "Formato non valido per il tipo avvis");
		Integer intAltriSoggetti = BandiManager.toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.avvisiDao.getElencoAvvisiScaduti(
				stazioneAppaltante, 
				oggetto,
				intTipoAvviso, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				intAltriSoggetti,
				numAnniPubblicazione,
				indicePrimoRecord, maxNumRecord);
	}
	
	public int countElencoAvvisiScaduti(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAvviso, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			String altriSoggetti,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		Integer intTipoAvviso = BandiManager.toInt(tipoAvviso, "Formato non valido per il tipo avvis");
		Integer intAltriSoggetti = BandiManager.toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.avvisiDao.countElencoAvvisiScaduti(
				stazioneAppaltante, 
				oggetto,
				intTipoAvviso, 
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				intAltriSoggetti,
				numAnniPubblicazione);
	}

	public StazioneAppaltanteBandoType getStazioneAppaltanteAvviso(String codice) {
		return this.avvisiDao.getStazioneAppaltanteAvviso(codice);
	}

	public AvvisoType getAvviso(String codiceGara) {
		return this.avvisiDao.getAvviso(codiceGara);
	}

}
