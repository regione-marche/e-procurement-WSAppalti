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
package it.maggioli.eldasoft.gene.db.dao.mybatis;

import it.maggioli.eldasoft.gene.db.dao.DatiCodificatiDao;
import it.maggioli.eldasoft.gene.db.mapper.DatiCodificatiMapper;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * SqlMap per iBatis
 * 
 * @author Stefano.Sabbadin
 */
@Component
public class SqlMapDatiCodificatiDao implements	DatiCodificatiDao {

	@Autowired
	private DatiCodificatiMapper datiCodificatiMapper;

	@Override
	public List<DatoCodificatoType> getTipiTab1(String codiceTabellato) throws DataAccessException {
		return datiCodificatiMapper.getTipiTab1(codiceTabellato);
	}

	@Override
	public List<DatoCodificatoType> getTipiTab2(String codiceTabellato) throws DataAccessException {
		return datiCodificatiMapper.getTipiTab2(codiceTabellato);
	}

	@Override
	public List<DatoCodificatoType> getTipiTab3(String codiceTabellato) throws DataAccessException {
		return datiCodificatiMapper.getTipiTab3(codiceTabellato);
	}

	@Override
	public List<DatoCodificatoType> getTipiTab5(String codiceTabellato) throws DataAccessException {
		return datiCodificatiMapper.getTipiTab5(codiceTabellato);
	}

	@Override
	public List<DatoCodificatoType> getNazioniCodificate() throws DataAccessException {
		return datiCodificatiMapper.getNazioniCodificate();
	}
	@Override
	public List<DatoCodificatoType> getFormeGiuridicheCooperative() throws DataAccessException {
		return datiCodificatiMapper.getFormeGiuridicheCooperative();
	}

	@Override
	public String getNazioniCodificateDGUE(String nazione) {
		return datiCodificatiMapper.getNazioniCodificateDGUE(nazione);
	}

}
