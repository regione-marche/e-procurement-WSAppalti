/*
 * Created on 25/ott/10
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di 
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.sil.gare.db.dao.ibatis;

import it.eldasoft.sil.portgare.datatypes.AbilitazionePreventivaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.AlboProfessionaleAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.AltriDatiAnagraficiAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.CameraCommercioAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.CassaEdileAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.CassaPrevidenzaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.ContoCorrenteDedicatoAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.DatoAnnuoImpresaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.INAILAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.INPSAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.ISO9001AggiornabileType;
import it.eldasoft.sil.portgare.datatypes.ImpresaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.IndirizzoAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.IndirizzoEstesoAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.IscrizioneElenchiRicostruzioneAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.IscrizioneWhitelistAntimafiaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.RatingLegalitaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.RecapitiAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.ReferenteImpresaAggiornabileType;
import it.eldasoft.sil.portgare.datatypes.SOAAggiornabileType;
import it.maggioli.eldasoft.sil.gare.db.dao.ImpreseDao;
import it.maggioli.eldasoft.sil.gare.db.vo.PKReferenteImpresa;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione delle imprese
 * 
 * @author Stefano.Sabbadin
 */
public class SqlMapImpreseDao extends SqlMapClientDaoSupport implements
	ImpreseDao {

    private static final int INCARICO_LR = 1;
    private static final int INCARICO_DT = 2;
    private static final int INCARICO_AL = 3;
    private static final int INCARICO_CO = 4;

    public String getPKImpresaByUsername(String username)
	    throws DataAccessException {
	return (String) this.getSqlMapClientTemplate().queryForObject(
		"getPKImpresaByUsername", username);
    }

    public ImpresaAggiornabileType getImpresa(String codiceImpresa)
	    throws DataAccessException {
	return (ImpresaAggiornabileType) this.getSqlMapClientTemplate()
		.queryForObject("getImpresa", codiceImpresa);
    }

    public IndirizzoAggiornabileType getSedeLegaleImpresa(String codiceImpresa)
	    throws DataAccessException {
	return (IndirizzoAggiornabileType) this.getSqlMapClientTemplate()
		.queryForObject("getSedeLegaleImpresa", codiceImpresa);
    }

    public RecapitiAggiornabileType getRecapitiImpresa(String codiceImpresa)
	    throws DataAccessException {
	return (RecapitiAggiornabileType) this.getSqlMapClientTemplate()
		.queryForObject("getRecapitiImpresa", codiceImpresa);
    }

    @SuppressWarnings("unchecked")
    public List<IndirizzoEstesoAggiornabileType> getIndirizziImpresa(
	    String codiceImpresa) throws DataAccessException {
	return (List<IndirizzoEstesoAggiornabileType>) this
		.getSqlMapClientTemplate().queryForList("getIndirizziImpresa",
			codiceImpresa);
    }

    public CameraCommercioAggiornabileType getDatiCCIAAImpresa(
	    String codiceImpresa) throws DataAccessException {
	return (CameraCommercioAggiornabileType) this.getSqlMapClientTemplate()
		.queryForObject("getDatiCCIAAImpresa", codiceImpresa);
    }

	public INPSAggiornabileType getDatiINPSImpresa(String codiceImpresa)
			throws DataAccessException {
		return (INPSAggiornabileType) this.getSqlMapClientTemplate()
				.queryForObject("getDatiINPSImpresa", codiceImpresa);
	}

	public INAILAggiornabileType getDatiINAILImpresa(String codiceImpresa)
			throws DataAccessException {
		return (INAILAggiornabileType) this.getSqlMapClientTemplate()
				.queryForObject("getDatiINAILImpresa", codiceImpresa);
	}

	public CassaEdileAggiornabileType getDatiCassaEdileImpresa(
			String codiceImpresa) throws DataAccessException {
		return (CassaEdileAggiornabileType) this.getSqlMapClientTemplate()
				.queryForObject("getDatiCassaEdileImpresa", codiceImpresa);
	}

    public SOAAggiornabileType getDatiSOAImpresa(String codiceImpresa)
	    throws DataAccessException {
	return (SOAAggiornabileType) this.getSqlMapClientTemplate()
		.queryForObject("getDatiSOAImpresa", codiceImpresa);
    }

    public ISO9001AggiornabileType getDatiISO9001Impresa(String codiceImpresa)
	    throws DataAccessException {
	return (ISO9001AggiornabileType) this.getSqlMapClientTemplate()
		.queryForObject("getDatiISO9001Impresa", codiceImpresa);
    }

    @Override
    public IscrizioneWhitelistAntimafiaAggiornabileType getDatiIscrizioneWhitelistImpresa(
        String codiceImpresa)throws DataAccessException {  
        return (IscrizioneWhitelistAntimafiaAggiornabileType) this.getSqlMapClientTemplate()
          .queryForObject("getDatiIscrizioneWhitelistImpresa", codiceImpresa);
    }
    	
    @Override
    public IscrizioneElenchiRicostruzioneAggiornabileType getDatiIscrizioneElenchiRicostruzioneImpresa(
        String codiceImpresa)throws DataAccessException {  
        return (IscrizioneElenchiRicostruzioneAggiornabileType) this.getSqlMapClientTemplate()
          .queryForObject("getDatiIscrizioneElenchiRicostruzioneImpresa", codiceImpresa);
    }
    
    @Override
    public RatingLegalitaAggiornabileType getDatiRatingLegalitaImpresa(
        String codiceImpresa)throws DataAccessException {  
        return (RatingLegalitaAggiornabileType) this.getSqlMapClientTemplate()
          .queryForObject("getDatiRatingLegalitaImpresa", codiceImpresa);
    }
    
	public ContoCorrenteDedicatoAggiornabileType getDatiContoCorrenteDedicatoImpresa(
			String codiceImpresa) throws DataAccessException {
		return (ContoCorrenteDedicatoAggiornabileType) this
		.getSqlMapClientTemplate().queryForObject(
			"getDatiContoCorrenteDedicatoImpresa", codiceImpresa);
	}

    public AbilitazionePreventivaAggiornabileType getDatiAbilitazionePreventivaImpresa(
	    String codiceImpresa) throws DataAccessException {
	return (AbilitazionePreventivaAggiornabileType) this
		.getSqlMapClientTemplate().queryForObject(
			"getDatiAbilitazionePreventivaImpresa", codiceImpresa);
    }

    public List<PKReferenteImpresa> getPKLegaliRappresentantiImpresa(String codiceImpresa, Date dataInizioCessati)
	    throws DataAccessException {
	return getPKAttualiReferentiImpresa(codiceImpresa, INCARICO_LR, dataInizioCessati);
    }

    public List<PKReferenteImpresa> getPKDirettoriTecniciImpresa(String codiceImpresa, Date dataInizioCessati)
	    throws DataAccessException {
	return getPKAttualiReferentiImpresa(codiceImpresa, INCARICO_DT,dataInizioCessati);
    }

	public List<PKReferenteImpresa> getPKAltreCaricheImpresa(String codiceImpresa, Date dataInizioCessati)
			throws DataAccessException {
		return getPKAttualiReferentiImpresa(codiceImpresa, INCARICO_AL,dataInizioCessati);
	}

	public List<PKReferenteImpresa> getPKCollaboratoriImpresa(String codiceImpresa, Date dataInizioCessati)
			throws DataAccessException {
		return getPKAttualiReferentiImpresa(codiceImpresa, INCARICO_CO,dataInizioCessati);
	}

    @SuppressWarnings("unchecked")
    private List<PKReferenteImpresa> getPKAttualiReferentiImpresa(String codiceImpresa,
	    int incarico, Date dataInizioCessati) {
	HashMap<String, Object> hash = new HashMap<String, Object>();
	hash.put("codiceImpresa", codiceImpresa);
	hash.put("incarico", incarico);
	hash.put("dataPartenzaCessati", dataInizioCessati);
	return (List<PKReferenteImpresa>) this.getSqlMapClientTemplate().queryForList(
		"getPKAttualiReferentiImpresa", hash);
    }

    public ReferenteImpresaAggiornabileType getDatiReferenteImpresa(
	    String codiceReferente) throws DataAccessException {
	return (ReferenteImpresaAggiornabileType) this
		.getSqlMapClientTemplate().queryForObject(
			"getDatiReferenteImpresa", codiceReferente);
    }

    public IndirizzoAggiornabileType getIndirizzoReferenteImpresa(
	    String codiceReferente) throws DataAccessException {
	return (IndirizzoAggiornabileType) this
		.getSqlMapClientTemplate()
		.queryForObject("getIndirizzoReferenteImpresa", codiceReferente);
    }

    public ReferenteImpresaAggiornabileType getIncaricoLegaleRappresentanteImpresa(
	    String codiceImpresa, PKReferenteImpresa referente)
	    throws DataAccessException {
	return getIncaricoReferenteImpresa(codiceImpresa, referente);
    }

    public ReferenteImpresaAggiornabileType getIncaricoDirettoreTecnicoImpresa(
	    String codiceImpresa, PKReferenteImpresa referente)
	    throws DataAccessException {
	return getIncaricoReferenteImpresa(codiceImpresa, referente);
    }

    public ReferenteImpresaAggiornabileType getIncaricoAltraCaricaImpresa(
	    String codiceImpresa, PKReferenteImpresa referente)
	    throws DataAccessException {
	return getIncaricoReferenteImpresa(codiceImpresa, referente);
    }

	public ReferenteImpresaAggiornabileType getIncaricoCollaboratoreImpresa(
			String codiceImpresa, PKReferenteImpresa referente)
			throws DataAccessException {
		return getIncaricoReferenteImpresa(codiceImpresa, referente);
	}

    private ReferenteImpresaAggiornabileType getIncaricoReferenteImpresa(
	    String codiceImpresa, PKReferenteImpresa referente)
	    throws DataAccessException {
	HashMap<String, Object> hash = new HashMap<String, Object>();
	hash.put("codiceImpresa", codiceImpresa);
	hash.put("codiceReferente", referente.getCodice());
	hash.put("incarico", referente.getIncarico());
	hash.put("qualifica", referente.getQualifica());
	if(referente.getId() != null) {
		hash.put("id", referente.getId());
	}
	return (ReferenteImpresaAggiornabileType) this
		.getSqlMapClientTemplate().queryForObject(
			"getIncaricoReferenteImpresa", hash);
    }

    public Boolean isImpresaRegistrata(String codiceFiscale, String partitaIva)
	    throws DataAccessException {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("codiceFiscale", codiceFiscale);
		hash.put("partitaIva", partitaIva);
		hash.put("tabella", "IMPR");
		Integer numImprese = (Integer) this.getSqlMapClientTemplate()
			.queryForObject("isImpresaRegistrata", hash);
		return new Boolean(numImprese.intValue() > 0);
    }

    public String getImpresaRegistrata(String codiceFiscale, String partitaIva, String email, String pec) 
		throws DataAccessException 
	{
    	HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("codiceFiscale", codiceFiscale);
		hash.put("partitaIva", partitaIva);
		hash.put("email", email);
		hash.put("pec", pec);
		hash.put("tabella", "IMPR");
		return (String) this.getSqlMapClientTemplate()
			.queryForObject("getImpresaRegistrata", hash);
    }
    
    @SuppressWarnings("unchecked")
    public List<DatoCodificatoType> getElencoTipiImpresaPerIscrizione()
	    throws DataAccessException {
	return (List<DatoCodificatoType>) this.getSqlMapClientTemplate()
		.queryForList("getElencoTipiImpresaPerIscrizione");
    }

	@SuppressWarnings("unchecked")
	public List<DatoCodificatoType> getElencoTipiImpresaDittaIndividuale()
			throws DataAccessException {
		return (List<DatoCodificatoType>) this.getSqlMapClientTemplate()
				.queryForList("getElencoTipiImpresaDittaIndividuale");
	}

	@SuppressWarnings("unchecked")
	public List<DatoCodificatoType> getElencoTipiImpresaLiberoProfessionista()
			throws DataAccessException {
		return (List<DatoCodificatoType>) this.getSqlMapClientTemplate()
				.queryForList("getElencoTipiImpresaLiberoProfessionista");
	}
	
	@SuppressWarnings("unchecked")
	public List<DatoCodificatoType> getElencoTipiImpresaSociale()
			throws DataAccessException {
		return (List<DatoCodificatoType>) this.getSqlMapClientTemplate()
				.queryForList("getElencoTipiImpresaSociale");
	}

	@SuppressWarnings("unchecked")
	public List<DatoCodificatoType> getElencoTipiImpresaConsorzio()
			throws DataAccessException {
		return (List<DatoCodificatoType>) this.getSqlMapClientTemplate()
				.queryForList("getElencoTipiImpresaConsorzio");
	}

	public AltriDatiAnagraficiAggiornabileType getAltriDatiLiberoProfessionista(
			String codiceImpresa) throws DataAccessException {
		return (AltriDatiAnagraficiAggiornabileType) this
		.getSqlMapClientTemplate().queryForObject(
			"getAltriDatiLiberoProfessionista", codiceImpresa);
	}

	public AlboProfessionaleAggiornabileType getAlboProfessionaleReferenteImpresa(
			String codiceReferente) throws DataAccessException {
		return (AlboProfessionaleAggiornabileType) this
		.getSqlMapClientTemplate().queryForObject(
			"getAlboProfessionaleReferenteImpresa", codiceReferente);
	}

	public AlboProfessionaleAggiornabileType getAlboProfessionaleLiberoProfessionista(
			String codiceImpresa) throws DataAccessException {
		return (AlboProfessionaleAggiornabileType) this
		.getSqlMapClientTemplate().queryForObject(
			"getAlboProfessionaleLiberoProfessionista", codiceImpresa);
	}

	public CassaPrevidenzaAggiornabileType getCassaPrevidenzaReferenteImpresa(
			String codiceReferente) throws DataAccessException {
		return (CassaPrevidenzaAggiornabileType) this
		.getSqlMapClientTemplate().queryForObject(
			"getCassaPrevidenzaReferenteImpresa", codiceReferente);
	}

	public CassaPrevidenzaAggiornabileType getCassaPrevidenzaLiberoProfessionista(
			String codiceImpresa) throws DataAccessException {
		return (CassaPrevidenzaAggiornabileType) this
		.getSqlMapClientTemplate().queryForObject(
			"getCassaPrevidenzaLiberoProfessionista", codiceImpresa);
	}

	public DatoAnnuoImpresaAggiornabileType getDatiAnnui(String codiceImpresa,
			int anno) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codiceImpresa", codiceImpresa);
		hash.put("anno", anno);
		return (DatoAnnuoImpresaAggiornabileType) this
				.getSqlMapClientTemplate().queryForObject("getDatiAnnui", hash);
	}

	public String getIdImpresa(String username) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		return (String) this
				.getSqlMapClientTemplate().queryForObject("getIdImpresa", hash);
	}

}
