/*
 * Created on 15/set/14
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di 
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.sil.stipule.db.dao.mybatis;

import it.eldasoft.utils.sql.comp.SqlComposer;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.eldasoft.utils.sql.comp.SqlManager;
import it.maggioli.eldasoft.gene.db.mapper.ComunicazioniMapper;
import it.maggioli.eldasoft.gene.ws.AllegatoComunicazioneType;
import it.maggioli.eldasoft.sil.stipule.db.dao.StipuleContrattiDao;
import it.maggioli.eldasoft.sil.stipule.db.mapper.StipuleContrattiMapper;
import it.maggioli.eldasoft.sil.stipule.ws.DocumentazioneStipulaContrattiType;
import it.maggioli.eldasoft.sil.stipule.ws.StipulaContrattoType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static it.maggioli.eldasoft.sil.gare.util.DAOUtils.*;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione delle stipule dei contratti.
 * 
 * @author
 */
@Component
public class SqlMapStipuleContrattiDao implements StipuleContrattiDao {

	/** tipologia di DBMS da property */
	@Resource
	private String dbms;
	@Autowired
	private StipuleContrattiMapper stipuleContrattiMapper;
	/**
	 * Non posso fare l'Autowired del DAO per motivi di "parametri"
	 */
	@Autowired
	private ComunicazioniMapper comunicazioniMapper;

	@Override
	public List<StipulaContrattoType> searchStipuleContratti(
			String codiceStipula, String oggetto, Integer stato,
			String stazioneAppaltante, String usernome,  int indicePrimoRecord, int maxNumRecord)
			throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(dbms);
		return stipuleContrattiMapper.searchStipuleContratti(
				  codiceStipula
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(oggetto)
				, stato
				, stazioneAppaltante
				, usernome
				, getBounds(indicePrimoRecord, maxNumRecord)
		);
	}

	@Override
	public StipulaContrattoType getDettaglioStipulaContratto(
			String codiceStipula, String usernome, boolean pubblicata) {
		return stipuleContrattiMapper.getDettaglioStipulaContratto(
				  codiceStipula
				, null
				, usernome
				, pubblicata
		);
	}

	@Override
	public StipulaContrattoType getDettaglioStipulaContrattoById(
			String idstipula, String usernome) {
		return stipuleContrattiMapper.getDettaglioStipulaContratto(
				null
				, idstipula
				, usernome
				, false
		);
	}

	@Override
	public List<DocumentazioneStipulaContrattiType> getDocumentiRichiestiStipulaContratto(
			String idStipula) {
		return stipuleContrattiMapper.getDocumentiRichiestiStipulaContratto(
				idStipula
		);
	}

	@Override
	public int countStipuleContratti(String codiceStipula, String oggetto,
									 Integer stato, String stazioneAppaltante, String username)
			throws SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);
		return stipuleContrattiMapper.countStipuleContratti(
				  codiceStipula
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(oggetto)
				, stato
				, stazioneAppaltante
				, username
		);
	}

	@Override
	public void deleteDocumentoStipula(String idDocumento) {
		comunicazioniMapper.deleteAllegato(
				"PG"
				, StringUtils.isNotEmpty(idDocumento) ? Long.parseLong(idDocumento) : null
		);		
		// se esiste elimina l'occorrenza da WSALLEGATI 
		comunicazioniMapper.deleteAllegatoProtocollo(
				"W_DOCDIG"
				, "PG"
				, StringUtils.isNotEmpty(idDocumento) ? Long.parseLong(idDocumento) : null
		);
	}
	
	@Override
	public void updateStipula(String codStipula, Long stato) throws DataAccessException {
		stipuleContrattiMapper.updateStipula(
				  codStipula
				, stato
		);
	}
	
	@Override
	public void updateDocsStipula(String codStipula, Long stato) throws DataAccessException {
		stipuleContrattiMapper.updateDocStipula(
				  codStipula
				, stato
		);
	}

	@Override
	public void updateNoteDoc(Long idDocStipula, String note) {
		stipuleContrattiMapper.updateNoteDoc(
				  idDocStipula
				, note
		);
	}

	@Override
	public void insertAllegato(String nomeFile, Long idDocStipula,
							   byte[] allegato, String firmacheck, Date firmacheckts) throws DataAccessException {
		Long id = comunicazioniMapper.getMaxIdAllegato("PG");
		id = id == null ? 1 : id + 1;

		comunicazioniMapper.insertAllegato(
				"PG"
				, id
				, "G1DOCSTIPULA"
				, idDocStipula
				, null
				, null
				, null
				, nomeFile
				, null
				, allegato
				, firmacheck
				, firmacheckts
		);
	}

	@Override
	public AllegatoComunicazioneType getDocumentoStipula(String idDocumento) {
		List<AllegatoComunicazioneType> results = comunicazioniMapper.getAllegatiComunicazione(
				"PG"
				, "G1DOCSTIPULA"
				, idDocumento
				, null
		);
		return results != null && !results.isEmpty() ? results.get(0) : null;
	}

}
