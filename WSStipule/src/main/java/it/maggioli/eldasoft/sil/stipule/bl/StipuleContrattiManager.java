package it.maggioli.eldasoft.sil.stipule.bl;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.gene.ws.AllegatoComunicazioneType;
import it.maggioli.eldasoft.sil.stipule.db.dao.StipuleContrattiDao;
import it.maggioli.eldasoft.sil.stipule.ws.DocumentazioneStipulaContrattiType;
import it.maggioli.eldasoft.sil.stipule.ws.StipulaContrattoType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Manager per la gestione delle stipule dei contratti.
 * @author 
 */
@Service
public class StipuleContrattiManager {

	private static final Logger logger = LoggerFactory.getLogger(StipuleContrattiManager.class);

	@Autowired
	private StipuleContrattiDao stipuleContrattiDao;
	
	private static final int NUM_GIORNI_COMUNICAZIONI_RICEVUTE = -90;

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<StipulaContrattoType> searchStipuleContratti(
			String codiceStipula,
			String oggetto, 
			Integer stato,
			String stazioneAppaltante,
			String usernome,
			int indicePrimoRecord,
	        int maxNumRecord) throws SqlComposerException 
	{
		Integer intStato = null;
		try {
			if (stato != null && !"".equals(stato))
				intStato = Integer.valueOf(stato);
		} catch (NumberFormatException e) {
			logger.error("Formato non valido per lo stato (" + stato + ")", e);
		}
		return this.stipuleContrattiDao.searchStipuleContratti(
				codiceStipula, 
				oggetto, 
				intStato,
				stazioneAppaltante,
				usernome,
				indicePrimoRecord,
				maxNumRecord);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public StipulaContrattoType getDettaglioStipulaContratto(String codiceStipula, String usernome, boolean pubblicata) {
		StipulaContrattoType contratto = this.stipuleContrattiDao.getDettaglioStipulaContratto(codiceStipula, usernome, pubblicata);
		return contratto; 
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<DocumentazioneStipulaContrattiType> getDocumentiRichiestiStipulaContratto(String idStipula) {
		return this.stipuleContrattiDao.getDocumentiRichiestiStipulaContratto(idStipula);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int countStipuleContratti(String codiceStipula, String oggetto,
			Integer stato, String stazioneAppaltante, String username) throws SqlComposerException {
		return this.stipuleContrattiDao.countStipuleContratti(codiceStipula, oggetto, stato, stazioneAppaltante, username);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public AllegatoComunicazioneType getDocumentoStipula(String idDocumento) {
		return this.stipuleContrattiDao.getDocumentoStipula(idDocumento);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteDocumentoStipula(String idDocumento) {
		this.stipuleContrattiDao.deleteDocumentoStipula(idDocumento);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateStipula(String idStipula, String username) throws DataAccessException {
		StipulaContrattoType contratto = this.stipuleContrattiDao.getDettaglioStipulaContrattoById(idStipula, username);
		if (contratto != null) {
			if (StringUtils.equals(contratto.getStato(), "3"))
				this.stipuleContrattiDao.updateStipula(idStipula, 4L);
			this.stipuleContrattiDao.updateDocsStipula(idStipula, 4L);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void insertAllegato(String nomeFile, Long idDocStipula, byte[] allegato, String note,String firmacheck, Date firmacheckts) {
		this.stipuleContrattiDao.insertAllegato(nomeFile, idDocStipula, allegato, firmacheck, firmacheckts);
		this.stipuleContrattiDao.updateNoteDoc(idDocStipula, note);
	}

}
