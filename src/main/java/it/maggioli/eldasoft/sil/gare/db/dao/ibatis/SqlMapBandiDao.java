/*
 * Created on 06/lug/10
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di 
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.sil.gare.db.dao.ibatis;

import it.eldasoft.utils.sql.comp.SqlComposer;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.eldasoft.utils.sql.comp.SqlManager;
import it.maggioli.eldasoft.sil.gare.db.dao.BandiDao;
import it.maggioli.eldasoft.sil.gare.db.vo.AttributoAggiuntivoOfferta;
import it.maggioli.eldasoft.sil.gare.db.vo.ComunicazioneLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.CriterioValutazioneOffertaLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentazioneRichiestaLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.EspletGaraOperatoreLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.RequisitoRichiestoLotto;
import it.maggioli.eldasoft.sil.gare.db.vo.SoggettoAderenteLotto;
import it.maggioli.eldasoft.sil.gare.ws.BandoIscrizioneType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaBandoIscrizioneType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaGaraType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaImpresaType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaOperatoreIscrittoType;
import it.maggioli.eldasoft.sil.gare.ws.ComunicazioneType;
import it.maggioli.eldasoft.sil.gare.ws.CriterioValutazioneOffertaType;
import it.maggioli.eldasoft.sil.gare.ws.DeliberaType;
import it.maggioli.eldasoft.sil.gare.ws.DettaglioStazioneAppaltanteType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentazioneRichiestaType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.EspletGaraComponenteRtiType;
import it.maggioli.eldasoft.sil.gare.ws.EspletGaraDocumentoType;
import it.maggioli.eldasoft.sil.gare.ws.EspletGaraLottoType;
import it.maggioli.eldasoft.sil.gare.ws.EspletGaraOperatoreType;
import it.maggioli.eldasoft.sil.gare.ws.FascicoloProtocolloType;
import it.maggioli.eldasoft.sil.gare.ws.GaraType;
import it.maggioli.eldasoft.sil.gare.ws.ImpresaIscrizioneType;
import it.maggioli.eldasoft.sil.gare.ws.InvioType;
import it.maggioli.eldasoft.sil.gare.ws.InvitoGaraType;
import it.maggioli.eldasoft.sil.gare.ws.LottoGaraType;
import it.maggioli.eldasoft.sil.gare.ws.MandanteRTIType;
import it.maggioli.eldasoft.sil.gare.ws.OperatoreIscrittoType;
import it.maggioli.eldasoft.sil.gare.ws.RequisitoRichiestoType;
import it.maggioli.eldasoft.sil.gare.ws.RettificaGaraType;
import it.maggioli.eldasoft.sil.gare.ws.SoggettoAderenteType;
import it.maggioli.eldasoft.sil.gare.ws.SommaUrgenzaType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;
import it.maggioli.eldasoft.sil.gare.ws.TipoPartecipazioneType;
import it.maggioli.eldasoft.sil.gare.ws.VoceDettaglioOffertaType;
import it.maggioli.eldasoft.sil.gare.ws.WSDMConfigType;
import it.maggioli.eldasoft.sil.gare.ws.WSGareAppaltoSoapImpl;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione dei bandi
 * 
 * @author Stefano.Sabbadin
 */
public class SqlMapBandiDao extends SqlMapClientDaoSupport implements BandiDao {

    /** tipologia di DBMS da property */
    private String dbms;

    /**
     * @param dbms
     *            the dbms to set
     */
    public void setDbms(String dbms) {
    	this.dbms = dbms;
    }
    
	private String operatoreWinvcomIdCom(String param) {
		if(SqlManager.DATABASE_SQL_SERVER.equals(this.dbms)) {
			return "convert(varchar, " + param + ")";
		} else if(SqlManager.DATABASE_POSTGRES.equals(this.dbms)) {
			return param;
		} else if(SqlManager.DATABASE_ORACLE.equals(this.dbms)) {
			return "TO_CHAR(" + param + ")";
		} else {
			return "";
		}
	}
	
	private String operatoreDataLenghtOper() {
		if(SqlManager.DATABASE_SQL_SERVER.equals(this.dbms)) {
			return "datalength";
		} else if(SqlManager.DATABASE_POSTGRES.equals(this.dbms)) {
			return "length";
		} else if(SqlManager.DATABASE_ORACLE.equals(this.dbms)) {
			return "dbms_lob.getlength";
		} else {
			return "";
		}
	}

    public String getAppaltiVersion()
		throws DataAccessException {
    	
		return (String) this.getSqlMapClientTemplate()
			.queryForObject("getAppaltiVersion");
    }

    @SuppressWarnings("unchecked")
    public List<DatoCodificatoType> getElencoStazioniAppaltanti()
		throws DataAccessException {
    	
		return (List<DatoCodificatoType>) this.getSqlMapClientTemplate()
			.queryForList("getElencoStazioniAppaltanti");
    }
    
    @SuppressWarnings("unchecked")
	public List<DatoCodificatoType> getElencoStazioniAppaltantiL190()
		throws DataAccessException {
    	
		return (List<DatoCodificatoType>) this.getSqlMapClientTemplate()
			.queryForList("getElencoStazioniAppaltantiL190");
    }

    @SuppressWarnings("unchecked")
    public List<DatoCodificatoType> getStatiGara() throws DataAccessException {
		return (List<DatoCodificatoType>) this.getSqlMapClientTemplate()
			.queryForList("getStatiGara");
    }

    @SuppressWarnings("unchecked")
    public List<DatoCodificatoType> getEsitiGara() throws DataAccessException {
		return (List<DatoCodificatoType>) this.getSqlMapClientTemplate()
			.queryForList("getEsitiGara");
    }

	private HashMap<String, Object> prepareParamsBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		//WE1158
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("dataScadenzaDa", dataScadenzaDa);
		hash.put("dataScadenzaA", dataScadenzaA);
		hash.put("proceduraTelematica", proceduraTelematica);
		hash.put("stato", stato);
		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("altriSoggetti", altriSoggetti);
		hash.put("sommaUrgenza", sommaUrgenza);
		return hash;
	}

	@SuppressWarnings("unchecked")
	public List<GaraType> searchBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsBandi(
				stazioneAppaltante, 
				oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				proceduraTelematica, 
				stato, 
				altriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);

		List<GaraType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"searchBandi", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"searchBandi", hash);
		}		
		return risultato;
	}

	public int countBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato, 
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsBandi(
				stazioneAppaltante, 
				oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				proceduraTelematica, 
				stato, 
				altriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);

		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countBandi", hash);
	}

//	@SuppressWarnings("unchecked")
//	public List<GaraType> getElencoBandi() throws DataAccessException {
//		return (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
//				"getElencoBandi", null);
//	}

	private HashMap<String, Object> prepareParamsElencoBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica) throws SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		//WE1158
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("dataScadenzaDa", dataScadenzaDa);
		hash.put("dataScadenzaA", dataScadenzaA);
		hash.put("proceduraTelematica", proceduraTelematica);
		hash.put("stato", stato);
//		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("altriSoggetti", altriSoggetti);
		hash.put("sommaUrgenza", sommaUrgenza);
		if(garaPrivatistica != null) {
			hash.put("garaPrivatistica", garaPrivatistica);
		}
		return hash;
	}

	@SuppressWarnings("unchecked")
	public List<GaraType> getElencoBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoBandi(
				stazioneAppaltante, 
				oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				proceduraTelematica, 
				stato,
				altriSoggetti,
				sommaUrgenza,
				garaPrivatistica);

		List<GaraType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoBandi", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoBandi", hash);
		}
		return risultato;
	}
	
	@Override
	public int countElencoBandi(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoBandi(
				stazioneAppaltante, 
				oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				proceduraTelematica, 
				stato,
				altriSoggetti,
				sommaUrgenza,
				garaPrivatistica);

		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countElencoBandi", hash);
	}

//	@SuppressWarnings("unchecked")
//	public List<GaraType> getElencoBandiScaduti(int numAnniPubblicazione) throws DataAccessException {
//		return (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
//				"getElencoBandiScaduti", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
//	}

	private HashMap<String, Object> prepareParamsElencoBandiScaduti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			Date dataScadenzaDa, 
			Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int numAnniPubblicazione) throws SqlComposerException
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("dataScadenzaDa", dataScadenzaDa);
		hash.put("dataScadenzaA", dataScadenzaA);
		hash.put("proceduraTelematica", proceduraTelematica);
		hash.put("stato", stato);		
		if (stato != null && stato != 3) {			
			// (3=Conclusa) solo se lo stato è Conclusa c'è un esito
			esito = null;
		}
		hash.put("esito", esito);
		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("altriSoggetti", altriSoggetti);
		hash.put("sommaUrgenza", sommaUrgenza);
		if(garaPrivatistica != null) {
			hash.put("garaPrivatistica", garaPrivatistica);
		}
		return hash;
	}
	
	@SuppressWarnings("unchecked")
	public List<GaraType> getElencoBandiScaduti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA,
			Date dataScadenzaDa, Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoBandiScaduti(
				stazioneAppaltante,
				oggetto, 
				cig, 
				tipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				stato, 
				esito,
				altriSoggetti,
				sommaUrgenza,
				garaPrivatistica,
				numAnniPubblicazione);

		List<GaraType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoBandiScaduti", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoBandiScaduti", hash);
		}
		return risultato;
	}
	
	public int countElencoBandiScaduti(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA,
			Date dataScadenzaDa, Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoBandiScaduti(
				stazioneAppaltante,
				oggetto, 
				cig, 
				tipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				stato, 
				esito,
				altriSoggetti,
				sommaUrgenza,
				garaPrivatistica,
				numAnniPubblicazione);

		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countElencoBandiScaduti", hash);
	}
	
	/**
	 * Calcola l'anno da utilizzare come confronto con le date di scadenza
	 * pubblicazione di bandi, esiti, avvisi.
	 * 
	 * @param numAnniPubblicazione
	 *            numero di anni per cui va mantenuto un bando scaduto
	 * @return anno minimo di pubblicazione per essere visibile oggi
	 */
	public static Integer getAnnoMinimoPubblicazione(int numAnniPubblicazione) {
		// invece di calcolare se:
		//
		// oggi <= data scadenza + numAnniPubblicazione
		//
		// con data scadenza = 31/12/<anno esito o pubblicazione> , si spostano
		// gli anni a sinistra facendo diventare la condizione:
		//
		// oggi - numAnniPubblicazione <= data scadenza
		//
		// (lo si fa usando il solo anno)
		GregorianCalendar dataMinimaPubblicazione = new GregorianCalendar();
		return dataMinimaPubblicazione.get(Calendar.YEAR)
				- numAnniPubblicazione;
	}
	
	public String getCigBando(String codice) {
		return (String) this.getSqlMapClientTemplate().queryForObject(
				"getCigBando", codice);
	}

	public StazioneAppaltanteBandoType getStazioneAppaltanteBando(String codice)
	    throws DataAccessException {
		return (StazioneAppaltanteBandoType) this.getSqlMapClientTemplate()
			.queryForObject("getStazioneAppaltanteBando", codice);
    }

    public GaraType getBando(String codiceGara) throws DataAccessException {
		return (GaraType) this.getSqlMapClientTemplate().queryForObject(
			"getBando", codiceGara);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentoAllegatoType> getDocumentiBandoLotto(
	    String codiceGara, String codiceLotto) throws DataAccessException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("codiceGara", codiceGara);
		map.put("codiceLotto", codiceLotto);
		return (List<DocumentoAllegatoType>) this.getSqlMapClientTemplate()
			.queryForList("getDocumentiBandoLotto", map);
    }
    
    @SuppressWarnings("unchecked")
    public List<DocumentoAllegatoType> getDocumentiInvitoLotto(
	    String codiceGara, String codiceLotto) throws DataAccessException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("codiceGara", codiceGara);
		map.put("codiceLotto", codiceLotto);
		return (List<DocumentoAllegatoType>) this.getSqlMapClientTemplate()
			.queryForList("getDocumentiInvitoLotto", map);
	}

    @SuppressWarnings("unchecked")
    public List<DocumentoAllegatoType> getDocumentiInvitoAstaLotto(
	    String codiceGara, String codiceLotto) throws DataAccessException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("codiceGara", codiceGara);
		map.put("codiceLotto", codiceLotto);
		return (List<DocumentoAllegatoType>) this.getSqlMapClientTemplate()
			.queryForList("getDocumentiInvitoAstaLotto", map);
    }

    @SuppressWarnings("unchecked")
    public List<RequisitoRichiestoType> getRequisitiRichiestiBandoLotto(
	    String codiceGara, String codiceLotto) throws DataAccessException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("codiceGara", codiceGara);
		map.put("codiceLotto", codiceLotto);
		return (List<RequisitoRichiestoType>) this.getSqlMapClientTemplate()
			.queryForList("getRequisitiRichiestiBandoLotto", map);
    }
    
    /** Ottimizzazione vedi getDettaglioGara */
    @SuppressWarnings("unchecked")
    public List<RequisitoRichiestoLotto> getRequisitiRichiestiBando(
	    String codiceGara) throws DataAccessException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("codiceGara", codiceGara);
		return (List<RequisitoRichiestoLotto>) this.getSqlMapClientTemplate()
			.queryForList("getRequisitiRichiestiBando", map);		
    }

    @SuppressWarnings("unchecked")
    public List<LottoGaraType> getLottiGara(String codiceGara)
	    throws DataAccessException {
		return (List<LottoGaraType>) this.getSqlMapClientTemplate()
			.queryForList("getLottiGara", codiceGara);
    }

    /** Ottimizzazione vedi getDettaglioGara */
    @SuppressWarnings("unchecked")
    public List<DocumentoAllegatoLotto> getDocumentiGara(String codiceGara)
	    throws DataAccessException {
		return (List<DocumentoAllegatoLotto>) this.getSqlMapClientTemplate()
			.queryForList("getDocumentiGara", codiceGara);
    }

	@SuppressWarnings("unchecked")
	public List<LottoGaraType> getLottiGaraPerDomandePartecipazione(
			String codiceGara) throws DataAccessException {
		return (List<LottoGaraType>) this.getSqlMapClientTemplate()
				.queryForList("getLottiGaraPerDomandePartecipazione",
						codiceGara);
	}

	@SuppressWarnings("unchecked")
	public List<LottoGaraType> getLottiGaraPerRichiesteOfferta(String username,
			String codiceGara) throws DataAccessException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("codiceGara", codiceGara);
		map.put("username", username);
		return (List<LottoGaraType>) this.getSqlMapClientTemplate()
		.queryForList("getLottiGaraPerRichiesteOfferta", map);
	}

	@SuppressWarnings("unchecked")
	public List<LottoGaraType> getLottiGaraPlicoUnicoPerRichiesteOfferta(String username,
			String codiceGara) throws DataAccessException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("codiceGara", codiceGara);
		map.put("username", username);
		return (List<LottoGaraType>) this.getSqlMapClientTemplate()
		.queryForList("getLottiGaraPlicoUnicoPerRichiesteOfferta", map);
	}

	@SuppressWarnings("unchecked")
	public List<LottoGaraType> getLottiGaraPerRichiesteCheckDocumentazione(
			String username, String codiceGara) throws DataAccessException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("codiceGara", codiceGara);
		map.put("username", username);
		return (List<LottoGaraType>) this.getSqlMapClientTemplate()
		.queryForList("getLottiGaraPerRichiesteCheckDocumentazione", map);
	}

    @SuppressWarnings("unchecked")
    public List<CategoriaGaraType> getCategorieLotto(String codiceLotto)
	    throws DataAccessException {
		return (List<CategoriaGaraType>) this.getSqlMapClientTemplate()
			.queryForList("getCategorieLotto", codiceLotto);
    }

    @SuppressWarnings("unchecked")
    public List<BandoIscrizioneType> getElencoBandiIscrizione(int genere, String stazioneAppaltante)
	    throws DataAccessException {
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("genere", Integer.toString(genere));
		map.put("stazioneAppaltante", stazioneAppaltante);		
		return (List<BandoIscrizioneType>) this.getSqlMapClientTemplate()
			.queryForList("getElencoBandiIscrizione", map);
    }

    public StazioneAppaltanteBandoType getStazioneAppaltanteBandoIscrizione(
	    String codice) throws DataAccessException {
		return (StazioneAppaltanteBandoType) this.getSqlMapClientTemplate()
			.queryForObject("getStazioneAppaltanteBandoIscrizione", codice);
    }

    public BandoIscrizioneType getBandoIscrizione(String codice)
	    throws DataAccessException {
		return (BandoIscrizioneType) this.getSqlMapClientTemplate()
			.queryForObject("getBandoIscrizione", codice);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentoAllegatoType> getDocumentiBandoIscrizione(
	    String codice) throws DataAccessException {
		return (List<DocumentoAllegatoType>) this.getSqlMapClientTemplate()
			.queryForList("getDocumentiBandoIscrizione", codice);
    }

	@SuppressWarnings("unchecked")
	public List<RequisitoRichiestoType> getRequisitiRichiestiBandoIscrizione(
			String codice) throws DataAccessException {
		return (List<RequisitoRichiestoType>) this.getSqlMapClientTemplate()
				.queryForList("getRequisitiRichiestiBandoIscrizione", codice);
	}

	public List<CategoriaBandoIscrizioneType> getCategorieBandoIscrizione(
			String codice, String filtroCategorie) throws DataAccessException, SqlComposerException {
		HashMap<String, String> filtroBase = new HashMap<String, String>();
		filtroBase.put("codice", codice);
		if (!StringUtils.isEmpty(filtroCategorie)) {
			SqlComposer composer = SqlManager.getComposer(this.dbms);
			filtroBase.put("operatoreUpper", composer.getFunzioneUpperCase());
			filtroBase.put("filtroCategorie", "%" + filtroCategorie.toUpperCase() + "%");
		}
		return this.getCategorieBandoIscrizione(filtroBase,  null, null, null, null);
	}

	private List<CategoriaBandoIscrizioneType> getCategorieBandoIscrizione(
			HashMap<String, String> hash, String livello1, String livello2,
			String livello3, String livello4) {
		List<CategoriaBandoIscrizioneType> risultato = new ArrayList<CategoriaBandoIscrizioneType>();
		hash.put("livello1", livello1);
		hash.put("livello2", livello2);
		hash.put("livello3", livello3);
		hash.put("livello4", livello4);
		@SuppressWarnings("unchecked")
		List<CategoriaBandoIscrizioneType> lista = (List<CategoriaBandoIscrizioneType>) this
				.getSqlMapClientTemplate().queryForList(
						"getCategorieBandoIscrizione", hash);

		for (CategoriaBandoIscrizioneType cat : lista) {
			risultato.add(cat);
			if (!cat.isFoglia()) {
				String catLivello1 = livello1;
				String catLivello2 = livello2;
				String catLivello3 = livello3;
				String catLivello4 = livello4;
				if (catLivello1 == null)
					catLivello1 = cat.getCodice();
				else if (catLivello2 == null)
					catLivello2 = cat.getCodice();
				else if (catLivello3 == null)
					catLivello3 = cat.getCodice();
				else if (catLivello4 == null)
					catLivello4 = cat.getCodice();

				List<CategoriaBandoIscrizioneType> listaFigli = this
						.getCategorieBandoIscrizione(hash, catLivello1,
								catLivello2, catLivello3, catLivello4);
				risultato.addAll(listaFigli);
			}
		}
		return risultato;
	}

	@SuppressWarnings("unchecked")
	public List<ComunicazioneType> getComunicazioniRicevute(
			String username, 
			String codice,
			Date dataInvioDa, Date dataInvioA,
			boolean soccorsoIstruttorio,
			int indicePrimoRecord, int maxNumRecord,
			String stazioneAppaltante) 
	{
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codice", codice);
		hash.put("dataInvioDa", dataInvioDa);
		hash.put("dataInvioA", dataInvioA);
		if(soccorsoIstruttorio) {
			hash.put("modello", "1");
		}
		hash.put("stazioneAppaltante", stazioneAppaltante);
		List<ComunicazioneType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<ComunicazioneType>) this.getSqlMapClientTemplate()
					.queryForList("getComunicazioniRicevute", hash, 
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<ComunicazioneType>) this.getSqlMapClientTemplate()
					.queryForList("getComunicazioniRicevute", hash);
		}
		return risultato;
	}
	
	/** Ottimizzazione vedi getDettaglioGara */
	@SuppressWarnings("unchecked")
	public List<ComunicazioneLotto> getComunicazioniPubblicheRicevute(
			String applicativo, String codice, String stazioneAppaltante) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("applicativo", applicativo);
		hash.put("codice", codice);
		hash.put("codice2", "$"+codice);
		hash.put("stazioneAppaltante", stazioneAppaltante);
		List<ComunicazioneLotto> risultato = null;
		risultato = (List<ComunicazioneLotto>) this.getSqlMapClientTemplate()
				.queryForList("getComunicazioniPubblicheRicevute", hash);
		return risultato;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComunicazioneType> getComunicazioniRicevuteLotti(
			String username,
			String[] codiciLotto, 
			Date dataInvioDa, Date dataInvioA,
			boolean soccorsoIstruttorio,
			int indicePrimoRecord, int maxNumRecord,
			String stazioneAppaltante) 
	{
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codiciLotto", codiciLotto);
		hash.put("dataInvioDa", dataInvioDa);
		hash.put("dataInvioA", dataInvioA);
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if(soccorsoIstruttorio) {
			hash.put("modello", "1");
		}
		List<ComunicazioneType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<ComunicazioneType>) this.getSqlMapClientTemplate()
					.queryForList("getComunicazioniRicevuteLotti", hash, indicePrimoRecord,
							maxNumRecord);
		} else {
			risultato = (List<ComunicazioneType>) this.getSqlMapClientTemplate()
					.queryForList("getComunicazioniRicevuteLotti", hash);
		}
		return risultato;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComunicazioneType> getComunicazioniInviate(String username,
			String codice, int indicePrimoRecord, int maxNumRecord,
			String stazioneAppaltante) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codice", codice);
		hash.put("stazioneAppaltante", stazioneAppaltante);
		List<ComunicazioneType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<ComunicazioneType>) this.getSqlMapClientTemplate()
					.queryForList("getComunicazioniInviate", hash, 
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<ComunicazioneType>) this.getSqlMapClientTemplate()
					.queryForList("getComunicazioniInviate", hash);
		}
		return risultato;
	}	

	public int countComunicazioniRicevute(
			String username, 
			String codice, 
			Date dataInvioDa, Date dataInvioA, 
			boolean soccorsoIstruttorio, 
			boolean soloDaLeggere,
			String stazioneAppaltante) 
	{
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codice", codice);
		hash.put("dataInvioDa", dataInvioDa);
		hash.put("dataInvioA", dataInvioA);
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (soloDaLeggere) {
			hash.put("soloDaLeggere", "true");
		}
		if(soccorsoIstruttorio) {
			hash.put("modello", "1");
		}
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countComunicazioniRicevute", hash);
	}

	public int countComunicazioniRicevuteLotti(
			String username, 
			String[] codiciLotto, 
			Date dataInvioDa, Date dataInvioA,
			boolean soccorsoIstruttorio, 
			boolean soloDaLeggere,
			String stazioneAppaltante) 
	{
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codiciLotto", codiciLotto);
		hash.put("dataInvioDa", dataInvioDa);
		hash.put("dataInvioA", dataInvioA);
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (soloDaLeggere) {
			hash.put("soloDaLeggere", "true");
		}
		if(soccorsoIstruttorio) {
			hash.put("modello", "1");
		}
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countComunicazioniRicevuteLotti", hash);
	}
	
	public int countComunicazioniInviate(String username, String codice,
			String stazioneAppaltante) {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("username", username);
		hash.put("codice", codice);
		hash.put("stazioneAppaltante", stazioneAppaltante);
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countComunicazioniInviate", hash);
	}
	
	@SuppressWarnings("unchecked")
	public List<DocumentoAllegatoType> getDocumentiComunicazione(
			String applicativo, Long id) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("applicativo", applicativo);
		hash.put("id", id);
		return (List<DocumentoAllegatoType>) this.getSqlMapClientTemplate()
				.queryForList("getDocumentiComunicazione", hash);
	}
	
	/** Ottimizzazione vedi getDettaglioGara */
	@SuppressWarnings("unchecked")
	public List<DocumentoAllegatoLotto> getDocumentiComunicazioniPubblicheRicevute(
			String applicativo, String codice) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("applicativo", applicativo);
		hash.put("codice", codice);
		hash.put("codice2", "$"+codice);
		return (List<DocumentoAllegatoLotto>) this.getSqlMapClientTemplate()
				.queryForList("getDocumentiComunicazioniPubblicheRicevute", hash);
	}

	public ComunicazioneType getComunicazioneRicevuta(String username, Long id, Long idDestinatario) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("id", id);
		hash.put("idDestinatario", idDestinatario);
		return (ComunicazioneType) this.getSqlMapClientTemplate()
				.queryForObject("getComunicazioneRicevuta", hash);
	}

	public ComunicazioneType getComunicazioneInviata(String username, Long id) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("id", id);
		return (ComunicazioneType) this.getSqlMapClientTemplate()
				.queryForObject("getComunicazioneInviata", hash);
	}

	@SuppressWarnings("unchecked")
	public List<InvioType> getElencoInvii(String username, String codice,
			String[] tipiComunicazione) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codice", codice);
		hash.put("tipiComunicazione", tipiComunicazione);
		return (List<InvioType>) this.getSqlMapClientTemplate()
				.queryForList("getElencoInvii", hash);
	}

    public Integer getStatoIscrizioneABandoIscrizione(String username,
	    String codice) throws DataAccessException {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("username", username);
		hash.put("codice", codice);
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
			"getStatoIscrizioneABandoIscrizione", hash);
    }

	@SuppressWarnings("unchecked")
	public List<DocumentazioneRichiestaType> getDocumentiRichiestiBandoGara(
			String codiceGara, String codiceLotto, Integer tipoImpresa,
			boolean rti, Integer busta) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codiceGara", codiceGara);
		hash.put("codiceLotto", codiceLotto);
		hash.put("busta", busta);
		hash.put("tipoImpresa",
				SqlMapBandiDao.getTipoImpresaPartecipazione(tipoImpresa, rti));
		return (List<DocumentazioneRichiestaType>) this
				.getSqlMapClientTemplate().queryForList(
						"getDocumentiRichiestiBandoGara", hash);
	}	
	
	/** Ottimizzazione vedi getDettaglioGara */
	@SuppressWarnings("unchecked")
	public List<DocumentazioneRichiestaLotto> getDocumentiRichiestiBando(
			String codiceGara, Integer tipoImpresa, boolean rti) 
			throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codiceGara", codiceGara);
		hash.put("tipoImpresa",
				SqlMapBandiDao.getTipoImpresaPartecipazione(tipoImpresa, rti));
		return (List<DocumentazioneRichiestaLotto>) this
				.getSqlMapClientTemplate().queryForList(
						"getDocumentiRichiestiBando", hash);
	}

	/**
	 * Determina il tipo impresa da utilizzare per il reperimento della
	 * documentazione dell'impresa a seconda che partecipi o meno in RTI.
	 * 
	 * @param tipoImpresa
	 *            tipologia di impresa
	 * @param rti
	 *            true se partecipa in RTI, false altrimenti
	 * @return il tipo impresa in input se l'impresa non partecipa in RTI, 10 se
	 *         riguarda un soggetto individuale che partecipa in un
	 *         raggruppamento di soggetti, 3 altrimenti
	 */
	private static Integer getTipoImpresaPartecipazione(Integer tipoImpresa,
			boolean rti) {
		Integer risultato = tipoImpresa;
		if (rti) {
			if (tipoImpresa <= 5) {
				risultato = 3;
			} else {
				risultato = 10;
			}
		}
		return risultato;
	}

	@SuppressWarnings("unchecked")
	public List<DocumentazioneRichiestaType> getDocumentiRichiestiBandoIscrizione(
			String codice, Integer tipoImpresa, boolean rti)
			throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codice);
		hash.put("tipoImpresa",
				SqlMapBandiDao.getTipoImpresaPartecipazione(tipoImpresa, rti));
		return (List<DocumentazioneRichiestaType>) this
				.getSqlMapClientTemplate().queryForList(
						"getDocumentiRichiestiBandoIscrizione", hash);
	}

	@SuppressWarnings("unchecked")
	public List<DocumentazioneRichiestaType> getDocumentiRichiestiRinnovoIscrizione(
			String codice, Integer tipoImpresa, boolean rti)
			throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codice);
		hash.put("tipoImpresa",
				SqlMapBandiDao.getTipoImpresaPartecipazione(tipoImpresa, rti));
		return (List<DocumentazioneRichiestaType>) this
				.getSqlMapClientTemplate().queryForList(
						"getDocumentiRichiestiRinnovoIscrizione", hash);
	}
	
	@SuppressWarnings("unchecked")
	public List<DocumentazioneRichiestaType> getDocumentiRichiestiComunicazione(
			String idPrg, long idComunicazione)
			throws DataAccessException 
	{
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("idPrg", idPrg);
		hash.put("idComunicazione", idComunicazione);
		return (List<DocumentazioneRichiestaType>) this
				.getSqlMapClientTemplate().queryForList(
						"getDocumentiRichiestiComunicazione", hash);
	}

	@SuppressWarnings("unchecked")
    public List<DatoCodificatoType> getStatiIscrizioneElencoOperatori()
	    throws DataAccessException {
		return (List<DatoCodificatoType>) this.getSqlMapClientTemplate()
			.queryForList("getStatiIscrizioneElencoOperatori");
    }

//    @SuppressWarnings("unchecked")
//    public List<GaraType> getElencoBandiPerDomandePartecipazione(String username)
//	    throws DataAccessException {
//	return (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
//		"getElencoBandiPerDomandePartecipazione", username);
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<GaraType> getElencoBandiPerRichiesteOfferta(String username)
//	    throws DataAccessException {
//		HashMap<String, Object> hash = new HashMap<String, Object>();
//		hash.put("username", username);
//		hash.put("adesso", new Date());
//		return (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
//			"getElencoBandiPerRichiesteOfferta", hash);
//    }

	private HashMap<String, Object> prepareParamsElencoBandiPerRichiesteOfferta(
			String username,
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti, 
			Boolean sommaUrgenza,
			Integer garaPrivatistica) throws SqlComposerException
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		//WE1158
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("dataScadenzaDa", dataScadenzaDa);
		hash.put("dataScadenzaA", dataScadenzaA);
		hash.put("proceduraTelematica", proceduraTelematica);
		hash.put("stato", stato);
//		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("username", username);
		hash.put("adesso", new Date());
		hash.put("altriSoggetti", altriSoggetti);
		hash.put("sommaUrgenza", sommaUrgenza);
		if(garaPrivatistica != null) {
			hash.put("garaPrivatistica", garaPrivatistica);
		}
		return hash;
	}

    @SuppressWarnings("unchecked")
    public List<GaraType> getElencoBandiPerRichiesteOfferta(
    		String username,
    		String stazioneAppaltante, 
    		String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti, 
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoBandiPerRichiesteOfferta(
				username,
	    		stazioneAppaltante, 
	    		oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				proceduraTelematica, 
				stato,
				altriSoggetti, 
				sommaUrgenza,
				garaPrivatistica);

		List<GaraType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoBandiPerRichiesteOfferta", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoBandiPerRichiesteOfferta", hash);
		}		
		return risultato;
    }
    
    public int countElencoBandiPerRichiesteOfferta(
    		String username,
    		String stazioneAppaltante, 
    		String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti, 
			Boolean sommaUrgenza,
			Integer garaPrivatistica) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoBandiPerRichiesteOfferta(
				username,
	    		stazioneAppaltante, 
	    		oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				proceduraTelematica, 
				stato,
				altriSoggetti, 
				sommaUrgenza,
				garaPrivatistica);

		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countElencoBandiPerRichiesteOfferta", hash);
    }

//    @SuppressWarnings("unchecked")
//    public List<GaraType> getElencoBandiPerRichiesteCheckDocumentazione(
//	    String username) throws DataAccessException {
//		return (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
//			"getElencoBandiPerRichiesteCheckDocumentazione", username);
//    }
    
    private HashMap<String, Object> prepareParamsElencoBandiPerRichiesteCheckDocumentazione(
    	    String username,
    	    String stazioneAppaltante, 
    		String oggetto,
    		String cig, 
    		Integer tipoAppalto, 
    		Date dataPubblicazioneDa, Date dataPubblicazioneA, 
    		Date dataScadenzaDa, Date dataScadenzaA,
    		Boolean proceduraTelematica, 
    		Integer stato,
    		Integer altriSoggetti,
    		Boolean sommaUrgenza,
    		Integer garaPrivatistica) throws SqlComposerException
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("dataScadenzaDa", dataScadenzaDa);
		hash.put("dataScadenzaA", dataScadenzaA);
		hash.put("proceduraTelematica", proceduraTelematica);
		hash.put("stato", stato);
//    		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("username", username);
		hash.put("adesso", new Date());
		hash.put("altriSoggetti", altriSoggetti);
		hash.put("sommaUrgenza", sommaUrgenza);
		if(garaPrivatistica != null) {
			hash.put("garaPrivatistica", garaPrivatistica);
		}
		return hash;
    }

    @SuppressWarnings("unchecked")
    public List<GaraType> getElencoBandiPerRichiesteCheckDocumentazione(
	    String username,
	    String stazioneAppaltante, 
		String oggetto,
		String cig, 
		Integer tipoAppalto, 
		Date dataPubblicazioneDa, Date dataPubblicazioneA, 
		Date dataScadenzaDa, Date dataScadenzaA,
		Boolean proceduraTelematica, 
		Integer stato,
		Integer altriSoggetti,
		Boolean sommaUrgenza,
		int indicePrimoRecord,
        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoBandiPerRichiesteCheckDocumentazione(
				username,
			    stazioneAppaltante, 
				oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				proceduraTelematica, 
				stato,
				altriSoggetti,
				sommaUrgenza,
				null);
		
		List<GaraType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoBandiPerRichiesteCheckDocumentazione", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoBandiPerRichiesteCheckDocumentazione", hash);
		}		
		return risultato;
    }
    
    public int countElencoBandiPerRichiesteCheckDocumentazione(
	    String username,
	    String stazioneAppaltante, 
		String oggetto,
		String cig, 
		Integer tipoAppalto, 
		Date dataPubblicazioneDa, Date dataPubblicazioneA, 
		Date dataScadenzaDa, Date dataScadenzaA,
		Boolean proceduraTelematica, 
		Integer stato,
		Integer altriSoggetti,
		Boolean sommaUrgenza) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoBandiPerRichiesteCheckDocumentazione(
				username,
			    stazioneAppaltante, 
				oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				proceduraTelematica, 
				stato,
				altriSoggetti,
				sommaUrgenza,
				null);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countElencoBandiPerRichiesteCheckDocumentazione", hash);
    }

    private HashMap<String, Object> prepareParamsElencoBandiPerAsteInCorso(
    		String username,
    		String stazioneAppaltante, 
    		String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica) throws SqlComposerException
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("dataScadenzaDa", dataScadenzaDa);
		hash.put("dataScadenzaA", dataScadenzaA);
		hash.put("proceduraTelematica", proceduraTelematica);
		hash.put("stato", stato);
//		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("username", username);
		hash.put("adesso", new Date());
		hash.put("altriSoggetti", altriSoggetti);
		hash.put("sommaUrgenza", sommaUrgenza);
		if(garaPrivatistica != null) {
			hash.put("garaPrivatistica", garaPrivatistica);
		}
		return hash;
	}
		
    @SuppressWarnings("unchecked")
    public List<GaraType> getElencoBandiPerAsteInCorso(
    		String username,
    		String stazioneAppaltante, 
    		String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsElencoBandiPerAsteInCorso(
				username,
	    		stazioneAppaltante, 
	    		oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				proceduraTelematica, 
				stato,
				altriSoggetti,
				sommaUrgenza,
				null);
		
		List<GaraType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoBandiPerAsteInCorso", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"getElencoBandiPerAsteInCorso", hash);
		}		
		return risultato;
    }

    public int countBandiPerAsteInCorso(
    		String username,
    		String stazioneAppaltante, 
    		String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato,
			Integer altriSoggetti,
			Boolean sommaUrgenza) throws DataAccessException, SqlComposerException 
	{
    	HashMap<String, Object> hash = prepareParamsElencoBandiPerAsteInCorso(
				username,
	    		stazioneAppaltante, 
	    		oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				proceduraTelematica, 
				stato,
				altriSoggetti,
				sommaUrgenza,
				null);

		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countBandiPerAsteInCorso", hash);
    }

	public boolean getAbilitazionePartecipazioneGara(String username,
			String codice) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codice", codice);
		Integer conteggio = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("getAbilitazionePartecipazioneGara", hash);
		return conteggio == 1;
	}

	public boolean getAbilitazionePresentazioneOfferta(String username,
			String codice) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codice", codice);
		Integer conteggio = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("getAbilitazionePresentazioneOfferta", hash);
		return conteggio == 1;
	}

	public boolean getAbilitazioneCheckDocumentazione(String username,
			String codice) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codice", codice);
		Integer conteggio = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("getAbilitazioneCheckDocumentazione", hash);
		return conteggio == 1;
	}

    public TipoPartecipazioneType getTipoPartecipazioneImpresa(
    		String username, String codice, String progOfferta) throws DataAccessException 
    {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codice", codice);
		hash.put("progressivoOfferta", progOfferta);
		TipoPartecipazioneType tipoPartecipazione = (TipoPartecipazioneType) this
			.getSqlMapClientTemplate().queryForObject(
				"getTipoPartecipazioneImpresa", hash);
		if (tipoPartecipazione != null) {
			tipoPartecipazione.setRti(tipoPartecipazione.getDenominazioneRti() != null);
		}
		return tipoPartecipazione;
    }
    
    @SuppressWarnings("unchecked")
    public List<DatoCodificatoType> getElencoStazioniAppaltantiPerIscrizione(
	    String codice) throws DataAccessException {
		return (List<DatoCodificatoType>) this.getSqlMapClientTemplate()
			.queryForList("getElencoStazioniAppaltantiPerIscrizione", codice);
    }

    @SuppressWarnings("unchecked")
    public List<CategoriaImpresaType> getCategorieImpresaPerIscrizione(
	    String username, String codice) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codice", codice);
		return (List<CategoriaImpresaType>) this.getSqlMapClientTemplate()
			.queryForList("getCategorieImpresaPerIscrizione", hash);
    }

	@SuppressWarnings("unchecked")
	public List<DatoCodificatoType> getElencoClassificazioniLavoriPerIscrizione()
			throws DataAccessException {
		return (List<DatoCodificatoType>) this.getSqlMapClientTemplate()
			.queryForList("getElencoClassificazioniLavoriPerIscrizione", null);
	}

	private HashMap<String, Object> prepareParamsBandiPerProcInAggiudicazione(
			String username,
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA,
			Date dataScadenzaDa, Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica) throws SqlComposerException
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("stazioneAppaltante", stazioneAppaltante);
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("dataScadenzaDa", dataScadenzaDa);
		hash.put("dataScadenzaA", dataScadenzaA);
		hash.put("proceduraTelematica", proceduraTelematica);
		hash.put("stato", stato);
		if (stato != null && stato != 3) {
			// (3=Conclusa) solo se lo stato è Conclusa c'è un esito
			esito = null;
		}
		hash.put("esito", esito);
		hash.put("altriSoggetti", altriSoggetti);
		hash.put("sommaUrgenza", sommaUrgenza);
		if(garaPrivatistica != null) {
			hash.put("garaPrivatistica", garaPrivatistica);
		}
		return hash;
	}
	
	@SuppressWarnings("unchecked")
	public List<GaraType> searchBandiPerProcInAggiudicazione(
			String username,
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA,
			Date dataScadenzaDa, Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsBandiPerProcInAggiudicazione(
				username,
				stazioneAppaltante,
				oggetto, 
				cig, 
				tipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				stato, 
				esito,
				altriSoggetti,
				sommaUrgenza,
				garaPrivatistica);
		
		List<GaraType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"searchBandiPerProcInAggiudicazione", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"searchBandiPerProcInAggiudicazione", hash);
		}		
		return risultato;
	}
	
	public int countBandiPerProcInAggiudicazione(
			String username,
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			Integer tipoAppalto,
			Date dataPubblicazioneDa, Date dataPubblicazioneA,
			Date dataScadenzaDa, Date dataScadenzaA, 
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			Integer garaPrivatistica) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsBandiPerProcInAggiudicazione(
				username,
				stazioneAppaltante,
				oggetto, 
				cig, 
				tipoAppalto,
				dataPubblicazioneDa, dataPubblicazioneA,
				dataScadenzaDa, dataScadenzaA, 
				proceduraTelematica, 
				stato, 
				esito,
				altriSoggetti,
				sommaUrgenza,
				garaPrivatistica);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countBandiPerProcInAggiudicazione", hash);
	}

	private HashMap<String, Object> prepareParamsBandiConEsito(			
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws SqlComposerException
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		hash.put("dataScadenzaDa", dataScadenzaDa);
		hash.put("dataScadenzaA", dataScadenzaA);
		hash.put("proceduraTelematica", proceduraTelematica);
		hash.put("stato", stato);
		if (stato != null && stato != 3) {			
			// (3=Conclusa) solo se lo stato è Conclusa c'è un esito
			esito = null;
		}
		hash.put("esito", esito);
		hash.put("annoMinimoPubblicazione", SqlMapBandiDao.getAnnoMinimoPubblicazione(numAnniPubblicazione));
		hash.put("altriSoggetti", altriSoggetti);
		hash.put("sommaUrgenza", sommaUrgenza);
		return hash;
	}
	
	@SuppressWarnings("unchecked")
	public List<GaraType> searchBandiConEsito(			
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione,
			int indicePrimoRecord,
	        int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsBandiConEsito(
				stazioneAppaltante, 
				oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				proceduraTelematica, 
				stato, 
				esito,
				altriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);
		
		List<GaraType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"searchBandiConEsito", hash,
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<GaraType>) this.getSqlMapClientTemplate().queryForList(
							"searchBandiConEsito", hash);
		}		
		return risultato;
	}

	public int countBandiConEsito(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			Integer tipoAppalto, 
			Date dataPubblicazioneDa, Date dataPubblicazioneA, 
			Date dataScadenzaDa, Date dataScadenzaA,
			Boolean proceduraTelematica, 
			Integer stato, 
			String esito,
			Integer altriSoggetti,
			Boolean sommaUrgenza,
			int numAnniPubblicazione) throws DataAccessException, SqlComposerException 
	{
		HashMap<String, Object> hash = prepareParamsBandiConEsito(
				stazioneAppaltante, 
				oggetto,
				cig, 
				tipoAppalto, 
				dataPubblicazioneDa, dataPubblicazioneA, 
				dataScadenzaDa, dataScadenzaA,
				proceduraTelematica, 
				stato, 
				esito,
				altriSoggetti,
				sommaUrgenza,
				numAnniPubblicazione);

		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countBandiConEsito", hash);
	}
	
	@SuppressWarnings("unchecked")
	public List<BandoIscrizioneType> searchBandiIscrizione(String username, int genere,
			Integer stato, String stazioneAppaltante) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("genere", genere);
		hash.put("stato", stato);
		hash.put("stazioneAppaltante", stazioneAppaltante);
		return (List<BandoIscrizioneType>) this.getSqlMapClientTemplate().queryForList(
				"searchBandiIscrizione", hash);
	}

	public Boolean isGaraConOffertaTecnica(String codiceGara) throws DataAccessException {
		Integer numGareOEPV = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("isGaraConOffertaTecnica", codiceGara);
		return new Boolean(numGareOEPV.intValue() > 0);
	}

	public Boolean isImpresaAbilitataRinnovo(String codiceBandoIscrizione,
			String username) throws DataAccessException {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("codiceBandoIscrizione", codiceBandoIscrizione);
		hash.put("username", username);
		Integer numImprese = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("isImpresaAbilitataRinnovo", hash);
		return new Boolean(numImprese.intValue() > 0);
	}

	@SuppressWarnings("unchecked")
	public List<VoceDettaglioOffertaType> getVociDettaglioOfferta(String codice) {
		return (List<VoceDettaglioOffertaType>) this.getSqlMapClientTemplate()
				.queryForList("getVociDettaglioOfferta", codice);
	}

	@SuppressWarnings("unchecked")
	public List<VoceDettaglioOffertaType> getVociDettaglioOffertaNoRibasso(String codice) {
		return (List<VoceDettaglioOffertaType>) this.getSqlMapClientTemplate()
				.queryForList("getVociDettaglioOffertaNoRibasso", codice);
	}

	@SuppressWarnings("unchecked")
	public List<AttributoAggiuntivoOfferta> getAttributiAggiuntiviOfferta(
			String codice) {
		return (List<AttributoAggiuntivoOfferta>) this.getSqlMapClientTemplate()
			.queryForList("getAttributiAggiuntiviOfferta", codice);
	}

	public DettaglioStazioneAppaltanteType getDettaglioStazioneAppaltante(
			String codice) {
		return (DettaglioStazioneAppaltanteType) this.getSqlMapClientTemplate()
				.queryForObject("getDettaglioStazioneAppaltante", codice);
	}

	@SuppressWarnings("unchecked")
	public List<DettaglioStazioneAppaltanteType> getStazioniAppaltanti() {
		return (List<DettaglioStazioneAppaltanteType>) this.getSqlMapClientTemplate()
				.queryForList("getStazioniAppaltanti");
	}

	@SuppressWarnings("unchecked")
	public List<String> getPuntiOrdinanti(String codice) {
		return (List<String>) this.getSqlMapClientTemplate().queryForList(
				"getPuntiOrdinanti", codice);
	}

	@SuppressWarnings("unchecked")
	public List<String> getPuntiIstruttori(String codice) {
		return (List<String>) this.getSqlMapClientTemplate().queryForList(
				"getPuntiIstruttori", codice);
	}

	@SuppressWarnings("unchecked")
	public List<MandanteRTIType> getMandantiRTI(
			String codiceProcedura, String username, String progOfferta) 
	{
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("codiceProcedura", codiceProcedura);
		hash.put("username", username);
		hash.put("progressivoOfferta", progOfferta);
		return (List<MandanteRTIType>) this.getSqlMapClientTemplate()
				.queryForList("getMandantiRTI", hash);
	}

	public FascicoloProtocolloType getFascicoloProtocollo(String codice) {
		return (FascicoloProtocolloType) this.getSqlMapClientTemplate()
				.queryForObject("getFascicoloProtocollo", codice);
	}

	public Long getGenere(String codice) {
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"getGenere", codice);
	}

	public String getCodiceGaraFromCodiceLotto(String codiceLotto) {
		return (String) this.getSqlMapClientTemplate().queryForObject(
				"getCodiceGaraFromCodiceLotto", codiceLotto);
	}

	public String getChiavePubblica(String codice, String tipo) {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("codice", codice);
		hash.put("tipo", tipo);
		return (String) this.getSqlMapClientTemplate().queryForObject(
				"getChiavePubblica", hash);
	}

	public Boolean isConsorziateEsecutriciPresenti(String username, String codiceGara)
			throws DataAccessException {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("username", username);
		hash.put("tabella", "IMPR");
		hash.put("codiceGara", codiceGara);
		Integer numImprese = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("isConsorziateEsecutriciPresenti", hash);
		return new Boolean(numImprese.intValue() > 0);
	}

	@SuppressWarnings("unchecked")
	public List<DocumentoAllegatoType> getAttiDocumentiBandoGara(String codiceGara) {
		return (List<DocumentoAllegatoType>) this.getSqlMapClientTemplate().queryForList(
				"getAttiDocumentiBandoGara", codiceGara);
	}
	
	@Override
	public Boolean isAstaElettronica(String codiceGara)
			throws DataAccessException {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("codiceGara", codiceGara);
		hash.put("codiceGara2", "$"+codiceGara);		
		Integer result = (Integer) this.getSqlMapClientTemplate()
			.queryForObject("isAstaElettronica", hash);		 
		return new Boolean(result != null && result.intValue() == 1);
	}

	@Override
	public Boolean isAstaAttiva(String codiceGara, Date dataCorrente) {
		HashMap<String, Object> hash = new HashMap<String, Object>();		
		hash.put("codiceGara", codiceGara);
		hash.put("codiceGara2", "$"+codiceGara);
		hash.put("dataCorrente", dataCorrente);
		Integer result = (Integer) this.getSqlMapClientTemplate()
			.queryForObject("isAstaAttiva", hash);
		return new Boolean(result != null && result.intValue() != 0);
	}
	
	@Override
	public Date getDataInizioAsta(String codiceGara) {
		HashMap<String, Object> hash = new HashMap<String, Object>();		
		hash.put("codiceGara", codiceGara);
		hash.put("codiceGara2", "$"+codiceGara);
		return (Date) this.getSqlMapClientTemplate()
			.queryForObject("getDataInizioAsta", hash);
	}

	@Override
	public Date getDataInvitoAsta(String codiceGara) {
		HashMap<String, Object> hash = new HashMap<String, Object>();		
		hash.put("codiceGara", codiceGara);
		hash.put("codiceGara2", "$"+codiceGara);
		return (Date) this.getSqlMapClientTemplate()
			.queryForObject("getDataInvitoAsta", hash);
	}

	@Override
	public Boolean isInvitataAsta(String codiceGara, String username) {
		HashMap<String, Object> hash = new HashMap<String, Object>();		
		hash.put("codiceGara", codiceGara);
		hash.put("codiceGara2", "$"+codiceGara);
		hash.put("dataCorrente", new Date());
		if(username != null) {
			hash.put("username", username);
		}		
		Integer result = (Integer) this.getSqlMapClientTemplate()
			.queryForObject("isInvitataAsta", hash);
		return (result != null && result.intValue() > 0);
	}

	@Override
	public Long checkAggiornamentoIscrizionePosticipata(String tokenRichiedente, 
			String codice) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("usernome", tokenRichiedente);
		hash.put("codice", codice);
		return (Long) this.getSqlMapClientTemplate()
			.queryForObject("checkAggiornamentoIscrizionePosticipata", hash);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EspletGaraOperatoreType> getEspletamentoGaraSoccorsiElencoOperatori(
			String codiceGara, 
			String codiceLotto,
			String fase,
			String tokenRichiedente) throws DataAccessException 
	{
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		hash.put("codiceLotto", codiceLotto);
		hash.put("fase", fase);
		hash.put("username", tokenRichiedente);
		return (List<EspletGaraOperatoreType>) this.getSqlMapClientTemplate()
			.queryForList("getEspletamentoGaraSoccorsiElencoOperatori", hash);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EspletGaraOperatoreType> getEspletamentoGaraDocAmmElencoOperatori(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		hash.put("username", tokenRichiedente);		
		return (List<EspletGaraOperatoreType>) this.getSqlMapClientTemplate()
			.queryForList("getEspletamentoGaraDocAmmElencoOperatori", hash);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EspletGaraOperatoreType> getEspletamentoGaraValTecElencoOperatori(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		hash.put("username", tokenRichiedente);		
		return (List<EspletGaraOperatoreType>) this.getSqlMapClientTemplate()
			.queryForList("getEspletamentoGaraValTecElencoOperatori", hash);
	}
	
	@SuppressWarnings("unchecked")
	@Override	
	public List<EspletGaraOperatoreLotto> getEspletamentoGaraValTecElencoOperatoriLotto(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara + "%");
		hash.put("username", tokenRichiedente);		
		return (List<EspletGaraOperatoreLotto>) this.getSqlMapClientTemplate()
			.queryForList("getEspletamentoGaraValTecElencoOperatoriLotto", hash);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EspletGaraOperatoreType> getEspletamentoGaraOffEcoElencoOperatori(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		hash.put("username", tokenRichiedente);		
		return (List<EspletGaraOperatoreType>) this.getSqlMapClientTemplate()
			.queryForList("getEspletamentoGaraOffEcoElencoOperatori", hash);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EspletGaraOperatoreLotto> getEspletamentoGaraOffEcoElencoOperatoriLotto(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara + "%");
		hash.put("username", tokenRichiedente);		
		return (List<EspletGaraOperatoreLotto>) this.getSqlMapClientTemplate()
			.queryForList("getEspletamentoGaraOffEcoElencoOperatoriLotto", hash);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EspletGaraOperatoreType> getEspletamentoGaraGraduatoriaElencoOperatori(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		hash.put("username", tokenRichiedente);
		List<EspletGaraOperatoreType> elenco = (List<EspletGaraOperatoreType>) this.getSqlMapClientTemplate()
			.queryForList("getEspletamentoGaraGraduatoriaElencoOperatori", hash);

		// prepara l'ordinamento da applicare all'elenco... 
		if(elenco != null && elenco.size() > 0) {
			String rical = elenco.get(0).getTipoRibasso();
			String codmodagg = elenco.get(0).getCodiceModAgg();
			if("17".equalsIgnoreCase(codmodagg)) {
				// "prezzo piu' basso
				hash.put("orderby", "1");
			} else if(!"6".equalsIgnoreCase(codmodagg) && 
				("5".equalsIgnoreCase(codmodagg) || "14".equalsIgnoreCase(codmodagg) || "2".equalsIgnoreCase(rical))) {
				hash.put("orderby", "2");
			} else if(!"6".equalsIgnoreCase(codmodagg) &&  "1".equalsIgnoreCase(rical)) {
				hash.put("orderby", "3");
			} else {
				// default
				hash.put("orderby", "4");
			}
		    elenco = (List<EspletGaraOperatoreType>) this.getSqlMapClientTemplate()
				.queryForList("getEspletamentoGaraGraduatoriaElencoOperatori", hash);
		}
		
		return elenco;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EspletGaraLottoType> getEspletamentoGaraLotti(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		hash.put("username", tokenRichiedente);		
		return (List<EspletGaraLottoType>) this.getSqlMapClientTemplate()
			.queryForList("getEspletamentoGaraLotti", hash);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EspletGaraDocumentoType> getEspletamentoGaraDocumenti(
			String codice, String codiceLotto, String tokenRichiedente, String tipoBusta, boolean partecipaLotto) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codice);
		hash.put("codiceLotto", (StringUtils.isEmpty(codiceLotto) ? codice : codiceLotto) );
		hash.put("username", tokenRichiedente);
		hash.put("busta", tipoBusta);
		hash.put("partecipalotto", (partecipaLotto ? "1" : "0"));
		return (List<EspletGaraDocumentoType>) this.getSqlMapClientTemplate()
			.queryForList("getEspletamentoGaraDocumenti", hash);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EspletGaraComponenteRtiType> getEspletamentoGaraComponentiRti(
			String codiceGara, String tokenRichiedente) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		hash.put("username", tokenRichiedente);
		return (List<EspletGaraComponenteRtiType>) this.getSqlMapClientTemplate()
			.queryForList("getEspletamentoGaraComponentiRti", hash);
	}
	
	@Override
	public Long getFaseGara(String codice) {
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"getFaseGara", codice);				
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComunicazioneType> getNews(
			Date dataInvioDa, Date dataInvioA, 
			int indicePrimoRecord, int maxNumRecord,
			String stazioneAppaltante) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("dataInvioDa", dataInvioDa);
		hash.put("dataInvioA", dataInvioA);
		hash.put("stazioneAppaltante", stazioneAppaltante);
		List<ComunicazioneType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<ComunicazioneType>) this.getSqlMapClientTemplate()
					.queryForList("getNews", hash, 
							indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<ComunicazioneType>) this.getSqlMapClientTemplate()
					.queryForList("getNews", hash);
		}
		return risultato;
	}
	
	@Override
	public int countNews(
			Date dataInvioDa, Date dataInvioA, 
			String stazioneAppaltante) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("dataInvioDa", dataInvioDa);
		hash.put("dataInvioA", dataInvioA);
		hash.put("stazioneAppaltante", stazioneAppaltante);
		return (Integer) this.getSqlMapClientTemplate()
			.queryForObject("countNews", hash);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SoggettoAderenteType> getSoggettiAderenti(
			String codiceGara) throws DataAccessException {		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		return (List<SoggettoAderenteType>) this.getSqlMapClientTemplate()
			.queryForList("getSoggettiAderenti", hash);
	}
	
	/** Ottimizzazione vedi getDettaglioGara */
	@SuppressWarnings("unchecked")
	@Override
	public List<SoggettoAderenteLotto> getSoggettiAderentiLotti(
			String codiceGara) throws DataAccessException {		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		return (List<SoggettoAderenteLotto>) this.getSqlMapClientTemplate()
			.queryForList("getSoggettiAderentiLotti", hash);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperatoreIscrittoType> getOperatoriIscritti(
			String codiceGara) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		return (List<OperatoreIscrittoType>) this.getSqlMapClientTemplate()
			.queryForList("getOperatoriIscritti", hash);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaOperatoreIscrittoType> getElencoCategorieOperatoriIscritti(
			String codiceGara) throws DataAccessException {		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		return (List<CategoriaOperatoreIscrittoType>) this.getSqlMapClientTemplate()
			.queryForList("getElencoCategorieOperatoriIscritti", hash);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RettificaGaraType> getRettifichePresentazioneDomandaOfferta(
			String codiceGara, Integer tipter)throws DataAccessException {	
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		hash.put("tipter", tipter);
		return (List<RettificaGaraType>) this.getSqlMapClientTemplate()
			.queryForList("getRettifichePresentazioneDomandaOfferta", hash);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CriterioValutazioneOffertaType> getCriteriValutazione(
			String codiceGara, String tipoBusta) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);
		if(WSGareAppaltoSoapImpl.BUSTA_TECNICA.equals(tipoBusta)) {
			hash.put("tippar", 1);
		} else if(WSGareAppaltoSoapImpl.BUSTA_ECONOMICA.equals(tipoBusta)) {
			hash.put("tippar", 2);
		}
		return (List<CriterioValutazioneOffertaType>) this.getSqlMapClientTemplate()
			.queryForList("getCriteriValutazione", hash);		
	}	

	/** Ottimizzazione vedi getDettaglioGara */
	@SuppressWarnings("unchecked")
	@Override
	public List<CriterioValutazioneOffertaLotto> getCriteriValutazioneLotti(
			String codiceGara, String tipoBusta) throws DataAccessException {		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("codice", codiceGara);		
		if(WSGareAppaltoSoapImpl.BUSTA_TECNICA.equals(tipoBusta)) {
			hash.put("tippar", 1);
		} else if(WSGareAppaltoSoapImpl.BUSTA_ECONOMICA.equals(tipoBusta)) {
			hash.put("tippar", 2);
		}
		return (List<CriterioValutazioneOffertaLotto>) this.getSqlMapClientTemplate()
			.queryForList("getCriteriValutazioneLotti", hash);
	}

	@Override
	public ImpresaIscrizioneType getImpresaIscrizione(
			String username, String codice) throws DataAccessException {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codice", codice);
		return (ImpresaIscrizioneType) this.getSqlMapClientTemplate()
			.queryForObject("getImpresaIscrizione", hash);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentoAllegatoLotto> checkDocumentiNulli(
			String codiceGara, String username, String tipoBusta) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);
		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("operatoreWinvcomIdCom", operatoreWinvcomIdCom("i.IDCOM"));

		hash.put("codice", StringUtils.upperCase(codiceGara) + "%");
		hash.put("codiceGara", StringUtils.upperCase(codiceGara));
		hash.put("codiceGara2", "$" + StringUtils.upperCase(codiceGara));
		hash.put("username", username);
		hash.put("tipoBusta", tipoBusta);
		return (List<DocumentoAllegatoLotto>) this.getSqlMapClientTemplate()
			.queryForList("checkDocumentiNulli", hash);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentoAllegatoLotto> checkDimensioneDocumenti(
			String codiceGara, String username, String tipoBusta) throws DataAccessException, SqlComposerException {
		SqlComposer composer = SqlManager.getComposer(this.dbms);
		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("operatoreDatasize", operatoreDataLenghtOper());
		hash.put("operatoreWinvcomIdCom", operatoreWinvcomIdCom("i.IDCOM"));

		hash.put("codice", StringUtils.upperCase(codiceGara) + "%");
		hash.put("codiceGara", StringUtils.upperCase(codiceGara));
		hash.put("codiceGara2", "$" + StringUtils.upperCase(codiceGara));
		hash.put("username", username);
		hash.put("tipoBusta", tipoBusta);
		return (List<DocumentoAllegatoLotto>) this.getSqlMapClientTemplate()
			.queryForList("checkDimensioneDocumenti", hash);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeliberaType> getDelibere(
			int annoInizio,
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			Boolean sommaUrgenza,
			int indicePrimoRecord, 
			int maxNumRecord) throws SqlComposerException, SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);
		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("annoInizio", annoInizio);
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		if (sommaUrgenza != null) {
			hash.put("sommaUrgenza", (sommaUrgenza ? 1 : 0));
		}
		List<DeliberaType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<DeliberaType>) this.getSqlMapClientTemplate()
					.queryForList("getDelibere", hash, indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<DeliberaType>) this.getSqlMapClientTemplate()
					.queryForList("getDelibere", hash);
		}		
		return risultato;
	}
	
	@Override
	public int countDelibere(
			int annoInizio,
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			Boolean sommaUrgenza) throws SqlComposerException, SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);
		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("annoInizio", annoInizio);
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		if (sommaUrgenza != null) {
			hash.put("sommaUrgenza", (sommaUrgenza ? 1 : 0));
		}
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countDelibere", hash);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SommaUrgenzaType> getElencoSommaUrgenza(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			int indicePrimoRecord, 
			int maxNumRecord) throws SqlComposerException, SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);
		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
		List<SommaUrgenzaType> risultato = null;
		if (indicePrimoRecord >= 0 && maxNumRecord > 0) {
			risultato = (List<SommaUrgenzaType>) this.getSqlMapClientTemplate()
					.queryForList("getElencoSommaUrgenza", hash, indicePrimoRecord, maxNumRecord);
		} else {
			risultato = (List<SommaUrgenzaType>) this.getSqlMapClientTemplate()
					.queryForList("getElencoSommaUrgenza", hash);
		}		
		return risultato;
	}
	
	@Override
	public int countElencoSommaUrgenza(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA) throws SqlComposerException, SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);
		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("operatoreUpper", composer.getFunzioneUpperCase());
		hash.put("stazioneAppaltante", stazioneAppaltante);
		if (oggetto != null && oggetto.length() > 0) {
			hash.put("oggetto", "%" + oggetto.toUpperCase() + "%");
		}
		hash.put("tipoAppalto", tipoAppalto);
		if (cig != null && cig.length() > 0) {
			hash.put("cig", "%" + cig.toUpperCase() + "%");
		}
		hash.put("dataPubblicazioneDa", dataPubblicazioneDa);
		hash.put("dataPubblicazioneA", dataPubblicazioneA);
	
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"countElencoSommaUrgenza", hash);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WSDMConfigType> getWSDMConfig(
			String stazioneAppaltante) throws DataAccessException, SqlComposerException
	{
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("stazioneAppaltante", stazioneAppaltante);

		return (List<WSDMConfigType>) this.getSqlMapClientTemplate()
			.queryForList("getWSDMConfig", hash);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InvitoGaraType> getElencoInvitiGara(
			String username, String codice) throws DataAccessException, SqlComposerException
	{
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codiceGara", codice);

		return (List<InvitoGaraType>) this.getSqlMapClientTemplate()
			.queryForList("getElencoInvitiGara", hash);
	}
	
	public Long getNumeroOrdineInvito(
			String username, String codice) throws DataAccessException, SqlComposerException
	{
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("username", username);
		hash.put("codice", codice);			// codice gara/lotto
		
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"getNumeroOrdineInvito", hash);
	}

}
