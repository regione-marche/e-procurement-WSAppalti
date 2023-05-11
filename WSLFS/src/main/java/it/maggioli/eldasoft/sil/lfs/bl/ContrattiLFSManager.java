package it.maggioli.eldasoft.sil.lfs.bl;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.lfs.db.dao.ContrattiLFSDao;
import it.maggioli.eldasoft.sil.lfs.ws.ContrattoLFSType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Manager per la gestione dei contratti LFS.
 * @author ...
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ContrattiLFSManager {

//	private static final Logger logger = LoggerFactory.getLogger(ContrattiLFSManager.class);

	@Autowired
	private ContrattiLFSDao contrattiLFSDao;
	
	/**
	 * ... 
	 */
	public List<ContrattoLFSType> searchContrattiLFS(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String gara,
			String codice,
			String tokenRichiedente,
			int indicePrimoRecord,
	        int maxNumRecord) throws SqlComposerException {
		List<ContrattoLFSType> lista = this.contrattiLFSDao.searchContrattiLFS(
				stazioneAppaltante, 
				oggetto, 
				cig, 
				gara,
				codice,
				tokenRichiedente,
				indicePrimoRecord,
				maxNumRecord);
		
		return lista;
	}
	
	public int countContrattiLFS(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String gara,
			String codice,
			String tokenRichiedente) throws SqlComposerException {
		return contrattiLFSDao.countContrattiLFS(
				stazioneAppaltante, 
				oggetto, 
				cig, 
				gara,
				codice,
				tokenRichiedente);
		
	}	
		
	/**
	 * ... 
	 */
	public ContrattoLFSType getDettaglioContrattoLFS(
			String tokenRichiedente,
			String codice, 
			String nappal) throws SqlComposerException {
		return contrattiLFSDao.getDettaglioContrattoLFS(tokenRichiedente, codice, nappal);
	}
}
