package it.maggioli.eldasoft.sil.aste.db.dao.mybatis;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.aste.db.dao.AsteDao;
import it.maggioli.eldasoft.sil.aste.db.mapper.AsteMapper;
import it.maggioli.eldasoft.sil.aste.ws.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione dei bandi
 * 
 * @author Stefano.Sabbadin
 */
@Component
public class SqlMapAsteDao implements AsteDao {

    /** tipologia di DBMS da property */
	@Resource
    private String dbms;
	@Autowired
	private AsteMapper asteMapper;

	@Override
	public List<DettaglioClassificaType> getClassifica(
			int tipoClassifica, 
			String codice, 
			String codiceLotto, 
			String username, 
			String numeroFase,
			boolean classificaAlRialzo) 
	{
		List<DettaglioClassificaType> elenco = asteMapper.getClassifica(
				  tipoClassifica
				, codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice
				, username
				, numeroFase
				, classificaAlRialzo
		);
		
		// aggiorna le posizioni della classifica...
		if(elenco != null)
			for(int i = 0; i < elenco.size(); i++)
				elenco.get(i).setPosizione(i + 1);

		return elenco;
	}

	@Override
	public List<DettaglioFaseAstaType> getFasiAsta(
			String codice) {
		return asteMapper.getFasiAsta(
				  codice
		);
	}

	@Override
	public DettaglioAstaType getDettaglioAsta(
			String codice) {
		Date now = new Date();
		return asteMapper.getDettaglioAsta(
				  codice
				, now
		);
	}

	@Override
	public void updateAsta(
			String codice, 
			String codiceLotto,
			DettaglioAstaType asta) {
		asteMapper.updateAsta(
				  (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice)
				, asta.getDataOraApertura()
				, asta.getDataOraChiusura()
				, asta.getDurataMinima()
				, asta.getDurataMassima()
				, asta.getTempoBase()
				, asta.getFase()
		);
	}

	@Override
	public List<DettaglioRilancioType> getElencoRilanci(
			String codice, 
			String codiceLotto,
			String username,
			String numeroFase) {
		return asteMapper.getElencoRilanci(
				  (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice)
				, username
				, numeroFase
		);
	}
	
	@Override
	public void insertRilancioAsta(
			String codice, 
			String codiceLotto, 
			DettaglioRilancioType rilancio) {	  	
		asteMapper.insertRilancioAsta(
				  (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice)
				, rilancio.getId()
				, rilancio.getDitta()
				, rilancio.getUsername()
				, rilancio.getNumeroRilancio()
				, rilancio.getNumeroFase()
				, rilancio.getRibasso()
				, rilancio.getImporto()
				, rilancio.getDataRilancio()
		);
	}
	
	@Override
	public void updateRilancioAsta(
			long id, 
			DettaglioRilancioType rilancio) {
		String username = StringUtils.isNotEmpty(rilancio.getDitta()) ? null : rilancio.getUsername();
		asteMapper.updateRilancioAsta(
				  rilancio.getCodiceGara()
				, rilancio.getDitta()
				, username
				, rilancio.getNumeroRilancio()
				, rilancio.getNumeroFase()
				, rilancio.getRibasso()
				, rilancio.getImporto()
				, rilancio.getDataRilancio()
				, id
		);
	}

	@Override 
	public List<VoceDettaglioAstaType> getPrezziUnitariPrimoRilancio(
			String codice, 
			String codiceLotto, 
			String username) {
		return asteMapper.getPrezziUnitariPrimoRilancio(
				  (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice)
				, username
		);
	}
	
	@Override
	public List<VoceDettaglioAstaType> getPrezziUnitari(
			String codice, 
			String codiceLotto,
			String username,
			Long idRilancio) {
		return asteMapper.getPrezziUnitari(
				  (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice)
				, username
				, idRilancio
		);
	}
	
	@Override
	public void insertPrezziUnitari(
			Long idRilancio, 
			List<VoceDettaglioAstaType> prezziUnitari) 
	{
		for (VoceDettaglioAstaType voceDettaglioAstaType : prezziUnitari) {
			if( !voceDettaglioAstaType.isNonSoggettoRibasso() ) {
				// solo le voci soggette a ribasso
				voceDettaglioAstaType.setAsteIdRilancio(idRilancio);      // <== per sicurezza
				asteMapper.insertPrezziUnitari(voceDettaglioAstaType);
			}
		}
	}
	
	@Override
	public void updatePrezziUnitari(
			Long idRilancio,
			List<VoceDettaglioAstaType> prezziUnitari) {	
		// SERVE ?! 
	}

	@Override 
	public String getMandatariaRti(
			String codice, 
			String codiceLotto,
			String username) {
		return asteMapper.getMandatariaRti(
				  (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice)
				, username
		);
	}

	@Override 
	public int getTipoRilancioAsta(
			String codice) {
		Integer res = asteMapper.getTipoRilancioAsta(
				  codice
		);
		return (res != null ? res : 0);
	}
	
	@Override 
	public double getTotaleNonSoggettoPrezziUnitari(
			String codice) throws SqlComposerException {
		Double res = asteMapper.getTotaleNonSoggettoPrezziUnitari(
				  codice
		);
		return (res != null ? res : 0);
	}
	
}
