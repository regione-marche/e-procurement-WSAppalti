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
package it.maggioli.eldasoft.sil.stipule.db.dao;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.gene.ws.AllegatoComunicazioneType;
import it.maggioli.eldasoft.sil.stipule.ws.DocumentazioneStipulaContrattiType;
import it.maggioli.eldasoft.sil.stipule.ws.StipulaContrattoType;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

/**
 * Interfaccia DAO per l'accesso alle informazioni relative alle stipule contratti nel web
 * service per il portale.
 * 
 * @author 
 * 
 */
public interface StipuleContrattiDao {

	/**
	 * Ritorna l'elenco dei contratti filtrati in base ai dati in input.
	 * 
	 * @param codiceStipule
	 * @param oggetto
	 *            oggetto della gara
	 * @param stato
	 *            stato del contratto
	 * @return lista dei contratti
	 * @throws SqlComposerException 
	 */	
	public List<StipulaContrattoType> searchStipuleContratti(
			String codiceStipula,
			String oggetto, 
			Integer stato,
			String stazioneAppaltante,
			String usernome, 
			int indicePrimoRecord,
	        int maxNumRecord) throws SqlComposerException;
	
	/**
	 * Estrae il dettaglio di un contratto data la sua chiave.
	 * 
	 * @param codiceStipula
	 *            codice univoco del contratto
	 * @param usernome 
	 * @return dettaglio del contratto
	 */
	public StipulaContrattoType getDettaglioStipulaContratto(String codiceStipula, String usernome, boolean pubblicata);

	/**
	 * Estrae i documenti "richiesti" di un contratto 
	 * 
	 */
	public List<DocumentazioneStipulaContrattiType> getDocumentiRichiestiStipulaContratto(String idStipula);

	public int countStipuleContratti(String codiceStipula, String oggetto,
									 Integer stato, String stazioneAppaltante, String username) throws SqlComposerException;
	
	public AllegatoComunicazioneType getDocumentoStipula(String idDocStipula);

	public void deleteDocumentoStipula(String idDocumento);

	void updateDocsStipula(String codStipula, Long stato)
			throws DataAccessException;

	void updateStipula(String codStipula, Long stato)
			throws DataAccessException;

	public void updateNoteDoc(Long idDocStipula, String note);
	
	public StipulaContrattoType getDettaglioStipulaContrattoById(String idstipula, String usernome);

	public void insertAllegato(String nomeFile, Long idDocStipula,
							   byte[] allegato,String firmacheck, Date firmacheckts) throws DataAccessException;
	
}
