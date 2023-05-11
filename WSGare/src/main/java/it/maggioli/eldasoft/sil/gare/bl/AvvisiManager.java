/*
 * Created on 8/giu/12
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
import it.maggioli.eldasoft.sil.gare.db.dao.AvvisiDao;
import it.maggioli.eldasoft.sil.gare.ws.AvvisoType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;
import it.maggioli.eldasoft.sil.gare.ws.search.AvvisiTypeSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Stefano.Sabbadin
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AvvisiManager {

	private static final Logger logger = LoggerFactory.getLogger(AvvisiManager.class);

	@Autowired
	private AvvisiDao avvisiDao;

	public List<AvvisoType> searchAvvisi(AvvisiTypeSearch filtri) throws SqlComposerException {
		return avvisiDao.searchAvvisi(filtri);
	}
	
	public int countAvvisi(AvvisiTypeSearch filtri) throws SqlComposerException {
		return avvisiDao.countAvvisi(filtri);
	}

	public List<AvvisoType> getElencoAvvisi() throws DataAccessException, SqlComposerException {
		AvvisiTypeSearch filtri = new AvvisiTypeSearch();
		filtri.setIndicePrimoRecord(-1);
		filtri.setMaxNumRecord(-1);
		return avvisiDao.getElencoAvvisi(filtri);
	}
	
	public List<AvvisoType> getElencoAvvisi(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException {
		return avvisiDao.getElencoAvvisi(filtri);
	}
	
	public int countElencoAvvisi(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException {
		return avvisiDao.countElencoAvvisi(filtri);
	}

	public List<AvvisoType> getElencoAvvisiScaduti(int numAnniPubblicazione) throws DataAccessException, SqlComposerException {
		AvvisiTypeSearch filtri = new AvvisiTypeSearch();
		filtri.setNumAnniPubblicazione(numAnniPubblicazione);
		filtri.setIndicePrimoRecord(-1);
		filtri.setMaxNumRecord(-1);
		return avvisiDao.getElencoAvvisiScaduti(filtri);
	}

	public List<AvvisoType> getElencoAvvisiScaduti(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException {
		return avvisiDao.getElencoAvvisiScaduti(filtri);
	}
	
	public int countElencoAvvisiScaduti(AvvisiTypeSearch filtri) throws DataAccessException, SqlComposerException {
		return avvisiDao.countElencoAvvisiScaduti(filtri);
	}

	public StazioneAppaltanteBandoType getStazioneAppaltanteAvviso(String codice) {
		return this.avvisiDao.getStazioneAppaltanteAvviso(codice);
	}

	public AvvisoType getAvviso(String codiceGara) {
		return this.avvisiDao.getAvviso(codiceGara);
	}

}
