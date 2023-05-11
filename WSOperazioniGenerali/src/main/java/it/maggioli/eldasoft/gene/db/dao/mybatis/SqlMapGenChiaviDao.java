/*
 * Created on 07-lug-2006
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.gene.db.dao.mybatis;

import it.maggioli.eldasoft.gene.db.dao.GenChiaviDao;
import it.maggioli.eldasoft.gene.db.mapper.GenChiaviMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con la tabella W_GENCHIAVI tramite iBatis.
 * 
 * @author Luca.Giacomazzo
 */
@Component
public class SqlMapGenChiaviDao implements GenChiaviDao {

	@Autowired
	private GenChiaviMapper genChiaviMapper;

	/**
	 *
	 * @param tabella
	 * @param numIdAllocati
	 * @return
	 */
	@Override
	public long getNextId(String tabella, int numIdAllocati) {
		genChiaviMapper.updateId(
				  numIdAllocati > 0 ? numIdAllocati : 1
				, tabella
		);
		// ultimo id da usare nel ciclo di inserimenti
		Long i = genChiaviMapper.getNextId(tabella);
		// primo id da usare nel ciclo di inserimenti
		return i - numIdAllocati + 1;
	}

	/**
	 *
	 * @param tabella
	 * @param chiave
	 * @param condizioniExtra
	 * @return
	 */
	@Override
	public long getMaxId(String tabella, String chiave, String condizioniExtra) {
		Long maxID = genChiaviMapper.getMaxId(
				  chiave
				, tabella
				, condizioniExtra
		);
		// Se la tabella e' vuota, allora si ritorna zero come maxId, in modo
		// che l'eventuale insert sia fatto con maxId + 1 = 1
		return maxID == null ? 0 : maxID;
	}

}