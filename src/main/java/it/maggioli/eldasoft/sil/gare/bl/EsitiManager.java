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
import it.maggioli.eldasoft.sil.gare.db.dao.EsitiDao;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.EsitoType;
import it.maggioli.eldasoft.sil.gare.ws.LottoEsitoType;
import it.maggioli.eldasoft.sil.gare.ws.LottoGaraType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;

import java.util.Date;
import java.util.List;

import javax.jws.WebParam;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

/**
 * @author Stefano.Sabbadin
 */
public class EsitiManager {

	Logger logger = Logger.getLogger(EsitiManager.class);

	private EsitiDao esitiDao;

	/**
	 * @param esitiDao
	 *            the esitiDao to set
	 */
	public void setEsitiDao(EsitiDao esitiDao) {
		this.esitiDao = esitiDao;
	}

	/**
	 * Ritorna l'elenco degli esiti di gara filtrati in base ai parametri in
	 * input.
	 * 
	 * @return lista degli esiti, di tipo EsitoType
	 * @throws SqlComposerException
	 */
	public List<EsitoType> searchEsiti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			String altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord) throws SqlComposerException 
	{
		Integer intTipoAppalto = BandiManager.toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intAltriSoggetti = BandiManager.toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.esitiDao.searchEsiti(
				stazioneAppaltante, 
				oggetto, 
				cig,
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				proceduraTelematica, 
				intAltriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione,
				indicePrimoRecord, maxNumRecord);
	}

	public int countEsiti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			String altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws SqlComposerException 
	{
		Integer intTipoAppalto = BandiManager.toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intAltriSoggetti = BandiManager.toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.esitiDao.countEsiti(
				stazioneAppaltante, 
				oggetto, 
				cig,
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				proceduraTelematica, 
				intAltriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);
	}
	
	/**
	 * Ritorna l'elenco degli esiti di gara.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuta la procedura pubblicata
	 * @return lista degli esiti, di tipo EsitoType
	 * @throws DataAccessException 
	 * @throws SqlComposerException
	 */
	public List<EsitoType> getElencoEsiti(int numAnniPubblicazione) throws DataAccessException, SqlComposerException {
		return this.esitiDao.getElencoEsiti(
				null, null, null, null, null, null, null, null, null,
				numAnniPubblicazione, -1, -1);
	}
	
	/**
	 * Ritorna l'elenco degli esiti di gara.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuta la procedura pubblicata
	 * @return lista degli esiti, di tipo EsitoType
	 * @throws DataAccessException 
	 * @throws SqlComposerException
	 */
	public List<EsitoType> getElencoEsiti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			String altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione,
			int indicePrimoRecord,
			int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		Integer intTipoAppalto = BandiManager.toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intAltriSoggetti = BandiManager.toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.esitiDao.getElencoEsiti(
				stazioneAppaltante, 
				oggetto, 
				cig, 
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				proceduraTelematica, 
				intAltriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione,
				indicePrimoRecord, maxNumRecord);
	}
	
	public int countElencoEsiti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			String altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		Integer intTipoAppalto = BandiManager.toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intAltriSoggetti = BandiManager.toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.esitiDao.countElencoEsiti(
				stazioneAppaltante, 
				oggetto, 
				cig, 
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				proceduraTelematica, 
				intAltriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);
	}

	/**
	 * Ritorna l'elenco degli esiti affidamenti di un elenco operatori economici.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuta la procedura pubblicata
	 * @return lista degli esiti, di tipo EsitoType
	 * @throws DataAccessException 
	 * @throws SqlComposerException
	 */
	public List<EsitoType> getElencoEsitiAffidamenti(int numAnniPubblicazione) throws DataAccessException, SqlComposerException {
		return this.esitiDao.getElencoEsitiAffidamenti(
				null, null, null, null, null, null, null, null, null,
				numAnniPubblicazione, -1, -1);
	}
	
	/**
	 * Ritorna l'elenco degli esiti affidamenti di un elenco operatori economici.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuta la procedura pubblicata
	 * @return lista degli esiti, di tipo EsitoType
	 * @throws SqlComposerException
	 */
	public List<EsitoType> getElencoEsitiAffidamenti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			String altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione,
			int indicePrimoRecord,
			int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		Integer intTipoAppalto = BandiManager.toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intAltriSoggetti = BandiManager.toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.esitiDao.getElencoEsitiAffidamenti(
				stazioneAppaltante, 
				oggetto, 
				cig, 
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				proceduraTelematica, 
				intAltriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione,
				indicePrimoRecord, maxNumRecord);
	}
	
	public int countElencoEsitiAffidamenti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Boolean proceduraTelematica, 
			String altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		Integer intTipoAppalto = BandiManager.toInt(tipoAppalto, "Formato non valido per il tipo appalto");
		Integer intAltriSoggetti = BandiManager.toInt(altriSoggetti, "Formato non valido per altri soggetti");
		return this.esitiDao.countElencoEsitiAffidamenti(
				stazioneAppaltante, 
				oggetto, 
				cig, 
				intTipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				proceduraTelematica, 
				intAltriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);
	}


	public StazioneAppaltanteBandoType getStazioneAppaltanteEsito(String codice) {
		return this.esitiDao.getStazioneAppaltanteEsito(codice);
	}

	/**
	 * Ritorna il singolo esito a partire dalla sua chiave.
	 * 
	 * @param codiceGara
	 *            codice dell'esito di gara
	 * 
	 * @return esito
	 * @throws SqlComposerException
	 */
	public EsitoType getEsito(String codiceGara) {
		return this.esitiDao.getEsito(codiceGara);
	}

	public List<LottoGaraType> getLottiGaraConEsito(String codiceGara) {
		return this.esitiDao.getLottiGaraConEsito(codiceGara);
	}
	
	public List<DocumentoAllegatoLotto> getDocumentiLottiGaraConEsito(String codiceGara) {
		return this.esitiDao.getDocumentiLottiGaraConEsito(codiceGara);
	}	

	public List<LottoEsitoType> getLottiEsito(String codiceGara) {

		List<LottoEsitoType> listaLottoEsito = this.esitiDao.getLottiEsito(codiceGara);
		boolean aggiudicatariMultipli = this.esitiDao.isAQAggiudicatariMultipli(codiceGara);
		
		for(int i = 0; i < listaLottoEsito.size();i++){
			String codiceLotto = listaLottoEsito.get(i).getCodiceLotto();
			if(aggiudicatariMultipli){	
				listaLottoEsito.get(i).setAggiudicataria(this.esitiDao.getAQAggiudicatariMultipli(codiceLotto));
			}else{
				listaLottoEsito.get(i).setAggiudicataria(this.esitiDao.getAggiudicatariaSingola(codiceLotto));
			}
		}		

		return listaLottoEsito;
	}

	public List<DocumentoAllegatoType> getDocumentiEsitoLotto(
			String codiceGara, String codiceLotto) {
		return this.esitiDao.getDocumentiEsitoLotto(codiceGara, codiceLotto);
	}

}
