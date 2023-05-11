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
package it.maggioli.eldasoft.gene.db.dao;

/**
 * Interfaccia DAO per l'accesso ai dati della tabella di memorizzazione dei del
 * campo ID_**** delle tabelle relative al DB di backoffice.
 */
public interface GenChiaviDao {

	long getNextId(String tabella, int numIdAllocati);

	public long getMaxId(String tabella, String chiave, String condizioniExtra);
}
