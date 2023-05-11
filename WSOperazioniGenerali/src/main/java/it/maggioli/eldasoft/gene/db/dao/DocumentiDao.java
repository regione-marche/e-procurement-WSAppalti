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
package it.maggioli.eldasoft.gene.db.dao;

import org.springframework.dao.DataAccessException;

import it.maggioli.eldasoft.gene.ws.FileType;

/**
 * Interfaccia DAO per la gestione crud dei documenti digitali (W_DOCDIG)
 * 
 * @author Stefano.Sabbadin
 */
public interface DocumentiDao {

	/**
	 * Ritorna il contenuto del singolo documento, individuato dai parametri di
	 * input.
	 * 
	 * @param idProgramma
	 *            identificativo del programma
	 * @param idDocumento
	 *            identificativo univoco del documento
	 * @param username
	 *            login utente in caso di documento riservato, null in caso di
	 *            documento pubblico
	 * @return documento costituito dal suo contenuto e dal nome del file
	 * @throws DataAccessException
	 */
	FileType getDocumentoDigitale(String idProgramma, long idDocumento, String username)
			throws DataAccessException;

	/**
	 * Ritorna uno username associato ad un documento, individuato dai parametri di
	 * input.
	 * 
	 * @param idProgramma
	 *            identificativo del programma
	 * @param idDocumento
	 *            identificativo univoco del documento
	 * @return documento costituito dal suo contenuto e dal nome del file
	 * @throws DataAccessException
	 */
	String getUsernameDocumentoRiservato(String idProgramma, long idDocumento)
			throws DataAccessException;

}
