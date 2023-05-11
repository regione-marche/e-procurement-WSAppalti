/*
 * Created on 27/feb/14
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di 
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.gene.db.dao.mybatis;

import it.maggioli.eldasoft.gene.db.dao.DocumentiDao;
import it.maggioli.eldasoft.gene.db.mapper.DocumentiMapper;
import it.maggioli.eldasoft.gene.ws.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * SqlMap per iBatis
 * 
 * @author Stefano.Sabbadin
 */
@Component
public class SqlMapDocumentiDao implements DocumentiDao {

	@Autowired
	private DocumentiMapper documentiMapper;
	@Override
	public FileType getDocumentoDigitale(String idProgramma, long idDocumento, String username)
			throws DataAccessException {
		// Idea per limitare la complessità di "v_ws_documenti_digitali"
		// nel caso di gruppo 6,11,12 (inviti) 
		// nel caso di RT la query può restituire più di una riga
		//
		// HINT: recuperare queryforList() e restituire il primo della lista
	
		// NB: non è possibile introdurre nella select DISTINCT a causa di campi blob !!!
//		return (FileType) this.getSqlMapClientTemplate().queryForObject(
//				"getDocumentoDigitale", hash);

		List<FileType> results = documentiMapper.getDocumentoDigitale(
				idDocumento
				, idProgramma
				, username
		);
		return results != null && results.size() > 0 ? results.get(0) : null;
	}
	@Override
	public String getUsernameDocumentoRiservato(String idProgramma, long idDocumento)
		throws DataAccessException {
		List<String> results = documentiMapper.getUsernameDocumentoRiservato(
				idDocumento
				, idProgramma
		);
		return results != null && results.size() > 0 ? results.get(0) : null;
	}

}
