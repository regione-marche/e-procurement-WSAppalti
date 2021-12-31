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
package it.maggioli.eldasoft.sil.gare.db.dao.ibatis;

import it.eldasoft.utils.sql.comp.SqlComposer;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.eldasoft.utils.sql.comp.SqlManager;
import it.maggioli.eldasoft.sil.gare.db.dao.ContrattiDao;
import it.maggioli.eldasoft.sil.gare.ws.ContrattoType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione dei contratti.
 * 
 * @author Stefano.Sabbadin
 */
public class SqlMapContrattiDao extends SqlMapClientDaoSupport implements ContrattiDao {

	/** tipologia di DBMS da property */
	private String dbms;

	/**
	 * @param dbms
	 *            the dbms to set
	 */
	public void setDbms(String dbms) {
		this.dbms = dbms;
	}

	@SuppressWarnings("unchecked")
	public List<ContrattoType> searchContratti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer stato,
			String username) throws DataAccessException, SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		//WE1158
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("stato", stato);
		hash.put("username", username);
		return (List<ContrattoType>) this.getSqlMapClientTemplate()
				.queryForList("searchContratti", hash);
	}

	public ContrattoType getDettaglioContratto(String codice) {
		return (ContrattoType) this.getSqlMapClientTemplate()
		.queryForObject("getDettaglioContratto", codice);
	}
	
	@SuppressWarnings("unchecked")
	public List<DocumentoAllegatoType> getAllegatiContratto(String codice){
		return (List<DocumentoAllegatoType>)this.getSqlMapClientTemplate().queryForList("getAllegatiContratto", codice);
	}
}
