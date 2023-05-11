/*
 * Created on 29/lug/10
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di 
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.gene.bl;

import it.maggioli.eldasoft.gene.db.dao.DatiCodificatiDao;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Manager per la gestione dei dati codificati
 * 
 * @author Stefano.Sabbadin
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DatiCodificatiManager {

	@Autowired
	private DatiCodificatiDao datiCodificatiDao;
	
	public List<DatoCodificatoType> getTipiTab1(String codiceTabellato) {
		return datiCodificatiDao.getTipiTab1(codiceTabellato);
	}

	public List<DatoCodificatoType> getTipiTab2(String codiceTabellato) {
		return datiCodificatiDao.getTipiTab2(codiceTabellato);
	}

	public List<DatoCodificatoType> getTipiTab3(String codiceTabellato) {
		return datiCodificatiDao.getTipiTab3(codiceTabellato);
	}
	
	public List<DatoCodificatoType> getTipiTab5(String codiceTabellato) {
		return datiCodificatiDao.getTipiTab5(codiceTabellato);
	}
	
	public List<DatoCodificatoType> getNazioniCodificate() {
		return datiCodificatiDao.getNazioniCodificate();
	}

	public List<DatoCodificatoType> getFormeGiuridicheCooperative() {
		return datiCodificatiDao.getFormeGiuridicheCooperative();
	}
	public String getNazioniCodificateDGUE(String nazione) {
		return datiCodificatiDao.getNazioniCodificateDGUE(nazione);
	}

}
