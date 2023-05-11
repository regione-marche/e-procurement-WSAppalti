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
package it.maggioli.eldasoft.sil.gare.db.dao;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.gare.ws.ContrattoType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;

import java.util.List;

/**
 * Interfaccia DAO per l'accesso alle informazioni relative ai contratti nel web
 * service per il portale.
 * 
 * @author Stefano.Sabbadin
 * 
 */
public interface ContrattiDao {

	/**
	 * Ritorna l'elenco dei contratti filtrati in base ai dati in input.
	 * 
	 * @param stazioneAppaltante
	 *            stazione appaltante associata al contratto
	 * @param oggetto
	 *            oggetto della gara
	 * @param cig
	 *            codice cig
	 * @param stato
	 *            stato del contratto
	 * @param username
	 *            username dell'impresa
	 * @return lista dei contratti
	 * @throws SqlComposerException 
	 */
	List<ContrattoType> searchContratti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer stato, 
			String username) throws SqlComposerException;
	
	/**
	 * Estrae il dettaglio di un contratto data la sua chiave.
	 * 
	 * @param codice
	 *            codice univoco del contratto
	 * @return dettaglio del contratto
	 */
	ContrattoType getDettaglioContratto(String codice);

	/**
	 * Estrae gli allegati di un contratto data la sua chiave.
	 * 
	 * @param codice
	 *            codice univoco del contratto
	 * @return lista degli allegati relativi al contratto
	 */
	List<DocumentoAllegatoType> getAllegatiContratto(String codice);
}
