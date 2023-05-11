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
package it.maggioli.eldasoft.sil.gare.bl;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.gare.db.dao.ContrattiDao;
import it.maggioli.eldasoft.sil.gare.ws.ContrattoType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Manager per la gestione dei contratti.
 * @author Stefano.Sabbadin
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ContrattiManager {

	private static final Logger logger = LoggerFactory.getLogger(ContrattiManager.class);
	@Autowired
	private ContrattiDao contrattiDao;
	
	public List<ContrattoType> searchContratti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String stato,
			String tokenRichiedente) throws SqlComposerException 
	{
		Integer intStato = null;
		try {
			if (stato != null && !"".equals(stato))
				intStato = Integer.valueOf(stato);
		} catch (NumberFormatException e) {
			logger.error("Formato non valido per lo stato (" + stato + ")", e);
		}
		return this.contrattiDao.searchContratti(
				stazioneAppaltante, 
				oggetto, 
				cig, 
				intStato, 
				tokenRichiedente);
	}
	
	public ContrattoType getDettaglioContratto(String codice) {
		ContrattoType contratto = this.contrattiDao.getDettaglioContratto(codice);
		if(contratto != null) {
			List<DocumentoAllegatoType> allegati = this.contrattiDao.getAllegatiContratto(codice);
			contratto.setAllegati(allegati);
		}
		return contratto; 
	}
}
