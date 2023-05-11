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
package it.maggioli.eldasoft.sil.gare.db.dao.mybatis;

import it.eldasoft.utils.sql.comp.SqlComposer;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.eldasoft.utils.sql.comp.SqlManager;
import it.maggioli.eldasoft.sil.gare.db.dao.ContrattiDao;
import it.maggioli.eldasoft.sil.gare.db.mapper.ContrattiMapper;
import it.maggioli.eldasoft.sil.gare.ws.ContrattoType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static it.maggioli.eldasoft.sil.gare.util.DAOUtils.getWithPercent;
import static it.maggioli.eldasoft.sil.gare.util.DAOUtils.toWithPercentAndUpper;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione dei contratti.
 * 
 * @author Stefano.Sabbadin
 */
@Component
public class SqlMapContrattiDao implements ContrattiDao {

	/** tipologia di DBMS da property */
	@Resource
	private String dbms;
	@Autowired
	private ContrattiMapper contrattiMapper;

	@Override
	public List<ContrattoType> searchContratti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer stato,
			String username) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return contrattiMapper.searchContratti(
				  username
				, stazioneAppaltante
				, composer.getFunzioneUpperCase()
				, toWithPercentAndUpper(oggetto)
				, toWithPercentAndUpper(cig)
				, stato
		);
	}
	@Override
	public ContrattoType getDettaglioContratto(String codice) {
		return contrattiMapper.getDettaglioContratto(codice);
	}
	@Override
	public List<DocumentoAllegatoType> getAllegatiContratto(String codice){
		return contrattiMapper.getAllegatiContratto(codice);
	}

}
