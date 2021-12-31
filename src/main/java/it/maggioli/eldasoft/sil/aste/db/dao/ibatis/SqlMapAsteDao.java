package it.maggioli.eldasoft.sil.aste.db.dao.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.aste.db.dao.AsteDao;
import it.maggioli.eldasoft.sil.aste.ws.DettaglioAstaType;
import it.maggioli.eldasoft.sil.aste.ws.DettaglioClassificaType;
import it.maggioli.eldasoft.sil.aste.ws.DettaglioFaseAstaType;
import it.maggioli.eldasoft.sil.aste.ws.DettaglioRilancioType;
import it.maggioli.eldasoft.sil.aste.ws.VoceDettaglioAstaType;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione dei bandi
 * 
 * @author Stefano.Sabbadin
 */
public class SqlMapAsteDao extends SqlMapClientDaoSupport implements AsteDao {

    /** tipologia di DBMS da property */
    private String dbms;

    /**
     * @param dbms
     *            the dbms to set
     */
    public void setDbms(String dbms) {
    	this.dbms = dbms;
    }

   
    @SuppressWarnings("unchecked")
	@Override
	public List<DettaglioClassificaType> getClassifica(
			int tipoClassifica, 
			String codice, 
			String codiceLotto, 
			String username, 
			String numeroFase,
			boolean classificaAlRibasso) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("ngara", (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice));
		hash.put("tipoClassifica", tipoClassifica);
		hash.put("username", username);
		hash.put("numeroFase", numeroFase);		
		if(!classificaAlRibasso) {			
			hash.put("alRialzo", true);
		}
		List<DettaglioClassificaType> elenco = (List<DettaglioClassificaType>) this
			.getSqlMapClientTemplate().queryForList("getClassifica", hash);
		
		// aggiorna le posizioni della classifica...
		if(elenco != null) {
			for(int i = 0; i < elenco.size(); i++) {
				elenco.get(i).setPosizione(i + 1);
			}
		}
		return elenco;		
	}

	@Override
	public List<DettaglioFaseAstaType> getFasiAsta(
			String codice) throws SqlComposerException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("ngara", codice);
		return (List<DettaglioFaseAstaType>) this.getSqlMapClientTemplate()
			.queryForList("getFasiAsta", hash); 
	}

	@Override
	public DettaglioAstaType getDettaglioAsta(
			String codice) throws SqlComposerException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("ngara", codice);
		hash.put("dataCorrente", new Date());		
		return (DettaglioAstaType) this.getSqlMapClientTemplate()
			.queryForObject("getDettaglioAsta", hash);
	}

	@Override
	public void updateAsta(
			String codice, 
			String codiceLotto,
			DettaglioAstaType asta) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("ngara", (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice));
		hash.put("dataApertura", asta.getDataOraApertura());
		hash.put("dataChiusura", asta.getDataOraChiusura());				
		hash.put("fase", asta.getFase());
		hash.put("durataMin", asta.getDurataMinima());		
		hash.put("durataMax", asta.getDurataMassima());
		hash.put("tempoBase", asta.getTempoBase());
		this.getSqlMapClientTemplate().update("updateAsta", hash);		
	}

	@Override
	public List<DettaglioRilancioType> getElencoRilanci(
			String codice, 
			String codiceLotto,
			String username,
			String numeroFase) throws SqlComposerException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("ngara", (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice));
		hash.put("username", username);
		hash.put("numeroFase", numeroFase);
		return (List<DettaglioRilancioType>) this.getSqlMapClientTemplate()
			.queryForList("getElencoRilanci", hash);
	}
	
	@Override
	public void insertRilancioAsta(
			String codice, 
			String codiceLotto, 
			DettaglioRilancioType rilancio) {	  	
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", rilancio.getId());
		hash.put("ngara", (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice));
		if(rilancio.getDitta() != null) {
			hash.put("ditta", rilancio.getDitta());
		} else {
			hash.put("username", rilancio.getUsername());
		}
		hash.put("numeroRilancio", rilancio.getNumeroRilancio());
		hash.put("numeroFase", rilancio.getNumeroFase());		
		hash.put("ribasso", rilancio.getRibasso());
		hash.put("importo", rilancio.getImporto());				
		hash.put("dataRilancio", rilancio.getDataRilancio());
		this.getSqlMapClientTemplate().insert("insertRilancioAsta", hash);		
	}
	
	@Override
	public void updateRilancioAsta(
			long id, 
			DettaglioRilancioType rilancio) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("id", id);
		hash.put("ngara", rilancio.getCodiceGara());
		if(rilancio.getDitta() != null) {
			hash.put("ditta", rilancio.getDitta());
		} else {
			hash.put("username", rilancio.getUsername());
		}
		hash.put("numeroRilancio", rilancio.getNumeroRilancio());
		hash.put("numeroFase", rilancio.getNumeroFase());
		hash.put("ribasso", rilancio.getRibasso());
		hash.put("importo", rilancio.getImporto());				
		hash.put("dataRilancio", rilancio.getDataRilancio());
		this.getSqlMapClientTemplate().update("updateRilancioAsta", hash);
	}

	@Override 
	public List<VoceDettaglioAstaType> getPrezziUnitariPrimoRilancio(
			String codice, 
			String codiceLotto, 
			String username) throws SqlComposerException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("ngara", (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice));
		hash.put("username", username);		
		return (List<VoceDettaglioAstaType>) this.getSqlMapClientTemplate()
			.queryForList("getPrezziUnitariPrimoRilancio", hash);		
	}
	
	@Override
	public List<VoceDettaglioAstaType> getPrezziUnitari(
			String codice, 
			String codiceLotto,
			String username,
			Long idRilancio) throws SqlComposerException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("ngara", (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice));
		hash.put("username", username);
		hash.put("idRilancio", idRilancio);		
		return (List<VoceDettaglioAstaType>) this.getSqlMapClientTemplate()
			.queryForList("getPrezziUnitari", hash);
	}
	
	@Override
	public void insertPrezziUnitari(
			Long idRilancio, 
			List<VoceDettaglioAstaType> prezziUnitari) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		for(int i = 0; i < prezziUnitari.size(); i++) {
			prezziUnitari.get(i).setAsteIdRilancio(idRilancio);	  // <== x sicurezza
		    hash.put("id", prezziUnitari.get(i).getAsteId());
			hash.put("idRilancio", prezziUnitari.get(i).getAsteIdRilancio());
			hash.put("codiceGara", prezziUnitari.get(i).getAsteCodice());			
			hash.put("idLavorazione", prezziUnitari.get(i).getAsteIdLavorazione());			
			hash.put("username", prezziUnitari.get(i).getAsteUsername());
			hash.put("ditta", prezziUnitari.get(i).getAsteDitta());
			hash.put("prezzoUnitario", prezziUnitari.get(i).getAstePrezzoUnitario());
			hash.put("importo", prezziUnitari.get(i).getQuantita() * prezziUnitari.get(i).getAstePrezzoUnitario());					
			this.getSqlMapClientTemplate().insert("insertPrezziUnitari", hash);
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
			String username) throws SqlComposerException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("ngara", (codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice));
		hash.put("username", username);
		return (String) this.getSqlMapClientTemplate()
			.queryForObject("getMandatariaRti", hash);		
	}

	@Override 
	public int getTipoRilancioAsta(
			String codice) throws SqlComposerException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("ngara", codice);
		Integer res = (Integer) this.getSqlMapClientTemplate()
			.queryForObject("getTipoRilancioAsta", hash);
		return (res != null ? res.intValue() : 0);
	}
	
	@Override 
	public double getTotaleNonSoggettoPrezziUnitari(
			String codice) throws SqlComposerException {
		Double res = (Double) this.getSqlMapClientTemplate()
			.queryForObject("getTotaleNonSoggettoPrezziUnitari", codice);
		return (res != null ? res.doubleValue() : 0);
	}
	
}
