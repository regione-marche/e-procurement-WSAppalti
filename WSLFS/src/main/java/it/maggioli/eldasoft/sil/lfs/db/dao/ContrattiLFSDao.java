package it.maggioli.eldasoft.sil.lfs.db.dao;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.gare.ws.ContrattoType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.lfs.ws.ContrattoLFSType;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * Interfaccia DAO per l'accesso alle informazioni relative ai contratti LFS nel web
 * service per il portale.
 * 
 * @author ...
 * 
 */
public interface ContrattiLFSDao {

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
	public List<ContrattoLFSType> searchContrattiLFS(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String gara,
			String codice,
			String username,
			int indicePrimoRecord,
			int maxNumRecord) throws SqlComposerException;
	
	/**
	 * Estrae il dettaglio di un contratto data la sua chiave.
	 * 
	 * @param codice
	 *            codice univoco del contratto
	 * @return dettaglio del contratto
	 */
	public ContrattoLFSType getDettaglioContrattoLFS(
			String tokenRichiedente, 
			String codice, 
			String nappal) throws SqlComposerException;

	/**
	 * ... 
	 */
	public int countContrattiLFS(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			String gara, 
			String codice, 
			String tokenRichiedente) throws DataAccessException, SqlComposerException;
	
}
