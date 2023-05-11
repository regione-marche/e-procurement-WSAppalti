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
package it.maggioli.eldasoft.sil.gare.db.dao.mybatis;

import it.eldasoft.sil.portgare.datatypes.*;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.gare.db.dao.ImpreseDao;
import it.maggioli.eldasoft.sil.gare.db.mapper.ImpreseMapper;
import it.maggioli.eldasoft.sil.gare.db.vo.PKReferenteImpresa;
import it.maggioli.eldasoft.sil.gare.ws.VendorRatingType;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione delle imprese
 * 
 * @author Stefano.Sabbadin
 */
@Component
public class SqlMapImpreseDao implements ImpreseDao {

    private static final int INCARICO_LR = 1;
    private static final int INCARICO_DT = 2;
    private static final int INCARICO_AL = 3;
    private static final int INCARICO_CO = 4;
	
	@Autowired
	private ImpreseMapper impreseMapper;

    @Override
	public String getPKImpresaByUsername(String username)
	    throws DataAccessException {
		return impreseMapper.getPKImpresaByUsername(username);
    }

    @Override
	public ImpresaAggiornabileType getImpresa(String codiceImpresa)
	    throws DataAccessException {
		return impreseMapper.getImpresa(codiceImpresa);
    }

    @Override
	public IndirizzoAggiornabileType getSedeLegaleImpresa(String codiceImpresa)
	    throws DataAccessException {
		return impreseMapper.getSedeLegaleImpresa(codiceImpresa);
    }

    @Override
	public RecapitiAggiornabileType getRecapitiImpresa(String codiceImpresa)
	    throws DataAccessException {
		return impreseMapper.getRecapitiImpresa(codiceImpresa);
    }

    @Override
	public List<IndirizzoEstesoAggiornabileType> getIndirizziImpresa(
	    String codiceImpresa) throws DataAccessException {
		return impreseMapper.getIndirizziImpresa(codiceImpresa);
    }

    @Override
	public CameraCommercioAggiornabileType getDatiCCIAAImpresa(
	    String codiceImpresa) throws DataAccessException {
		return impreseMapper.getDatiCCIAAImpresa(codiceImpresa);
    }

	@Override
	public INPSAggiornabileType getDatiINPSImpresa(String codiceImpresa)
			throws DataAccessException {
		return impreseMapper.getDatiINPSImpresa(codiceImpresa);
	}

	@Override
	public INAILAggiornabileType getDatiINAILImpresa(String codiceImpresa)
			throws DataAccessException {
		return impreseMapper.getDatiINAILImpresa(codiceImpresa);
	}

	@Override
	public CassaEdileAggiornabileType getDatiCassaEdileImpresa(
			String codiceImpresa) throws DataAccessException {
		return impreseMapper.getDatiCassaEdileImpresa(codiceImpresa);
	}

    @Override
	public SOAAggiornabileType getDatiSOAImpresa(String codiceImpresa)
	    throws DataAccessException {
		return impreseMapper.getDatiSOAImpresa(codiceImpresa);
    }

    @Override
	public ISO9001AggiornabileType getDatiISO9001Impresa(String codiceImpresa)
	    throws DataAccessException {
		return impreseMapper.getDatiISO9001Impresa(codiceImpresa);
    }

    @Override
	public IscrizioneWhitelistAntimafiaAggiornabileType getDatiIscrizioneWhitelistImpresa(
        String codiceImpresa)throws DataAccessException {  
        return impreseMapper.getDatiIscrizioneWhitelistImpresa(codiceImpresa);
    }
    	
    @Override
	public IscrizioneElenchiRicostruzioneAggiornabileType getDatiIscrizioneElenchiRicostruzioneImpresa(
        String codiceImpresa)throws DataAccessException {  
        return impreseMapper.getDatiIscrizioneElenchiRicostruzioneImpresa(codiceImpresa);
    }
    
    @Override
	public RatingLegalitaAggiornabileType getDatiRatingLegalitaImpresa(
        String codiceImpresa)throws DataAccessException {  
        return impreseMapper.getDatiRatingLegalitaImpresa(codiceImpresa);
    }
    
	@Override
	public ContoCorrenteDedicatoAggiornabileType getDatiContoCorrenteDedicatoImpresa(
			String codiceImpresa) throws DataAccessException {
		return impreseMapper.getDatiContoCorrenteDedicatoImpresa(codiceImpresa);
	}

    @Override
	public AbilitazionePreventivaAggiornabileType getDatiAbilitazionePreventivaImpresa(
	    String codiceImpresa) throws DataAccessException {
		return impreseMapper.getDatiAbilitazionePreventivaImpresa(codiceImpresa);
    }

    @Override
	public List<PKReferenteImpresa> getPKLegaliRappresentantiImpresa(String codiceImpresa, Date dataInizioCessati)
	    throws DataAccessException {
		return getPKAttualiReferentiImpresa(codiceImpresa, INCARICO_LR, dataInizioCessati);
    }

    @Override
	public List<PKReferenteImpresa> getPKDirettoriTecniciImpresa(String codiceImpresa, Date dataInizioCessati)
	    throws DataAccessException {
		return getPKAttualiReferentiImpresa(codiceImpresa, INCARICO_DT,dataInizioCessati);
    }

	@Override
	public List<PKReferenteImpresa> getPKAltreCaricheImpresa(String codiceImpresa, Date dataInizioCessati)
			throws DataAccessException {
		return getPKAttualiReferentiImpresa(codiceImpresa, INCARICO_AL,dataInizioCessati);
	}

	@Override
	public List<PKReferenteImpresa> getPKCollaboratoriImpresa(String codiceImpresa, Date dataInizioCessati)
			throws DataAccessException {
		return getPKAttualiReferentiImpresa(codiceImpresa, INCARICO_CO,dataInizioCessati);
	}

    @Override
	public ReferenteImpresaAggiornabileType getDatiReferenteImpresa(
	    String codiceReferente) throws DataAccessException {
		return impreseMapper.getDatiReferenteImpresa(codiceReferente);
    }

    @Override
	public IndirizzoAggiornabileType getIndirizzoReferenteImpresa(
	    String codiceReferente) throws DataAccessException {
		return impreseMapper.getIndirizzoReferenteImpresa(codiceReferente);
    }

    @Override
	public ReferenteImpresaAggiornabileType getIncaricoLegaleRappresentanteImpresa(
	    String codiceImpresa, PKReferenteImpresa referente)
	    throws DataAccessException {
		return getIncaricoReferenteImpresa(codiceImpresa, referente);
    }

    @Override
	public ReferenteImpresaAggiornabileType getIncaricoDirettoreTecnicoImpresa(
	    String codiceImpresa, PKReferenteImpresa referente)
	    throws DataAccessException {
		return getIncaricoReferenteImpresa(codiceImpresa, referente);
    }

    @Override
	public ReferenteImpresaAggiornabileType getIncaricoAltraCaricaImpresa(
	    String codiceImpresa, PKReferenteImpresa referente)
	    throws DataAccessException {
		return getIncaricoReferenteImpresa(codiceImpresa, referente);
    }

	@Override
	public ReferenteImpresaAggiornabileType getIncaricoCollaboratoreImpresa(
			String codiceImpresa, PKReferenteImpresa referente)
			throws DataAccessException {
		return getIncaricoReferenteImpresa(codiceImpresa, referente);
	}

    @Override
	public Boolean isImpresaRegistrata(String codiceFiscale, String partitaIva, boolean isGruppoIva)
	    throws DataAccessException {
		return impreseMapper.isImpresaRegistrata(
				"IMPR"
				, codiceFiscale
				, partitaIva
				, isGruppoIva
		) > 0;
    }

    @Override
	public String getImpresaRegistrata(String codiceFiscale, String partitaIva, String email, String pec) 
		throws DataAccessException {
		return impreseMapper.getImpresaRegistrata(
				"IMPR"
				, codiceFiscale
				, partitaIva
				, email
				, pec
		);
    }
    
    @Override
	public List<DatoCodificatoType> getElencoTipiImpresaPerIscrizione()
	    throws DataAccessException {
	return impreseMapper.getElencoTipiImpresaPerIscrizione();
    }

	@Override
	public List<DatoCodificatoType> getElencoTipiImpresaDittaIndividuale()
			throws DataAccessException {
		return impreseMapper.getElencoTipiImpresaDittaIndividuale();
	}

	@Override
	public List<DatoCodificatoType> getElencoTipiImpresaLiberoProfessionista()
			throws DataAccessException {
		return impreseMapper.getElencoTipiImpresaLiberoProfessionista();
	}
	
	@Override
	public List<DatoCodificatoType> getElencoTipiImpresaSociale()
			throws DataAccessException {
		return impreseMapper.getElencoTipiImpresaSociale();
	}

	@Override
	public List<DatoCodificatoType> getElencoTipiImpresaConsorzio()
			throws DataAccessException {
		return impreseMapper.getElencoTipiImpresaConsorzio();
	}

	@Override
	public AltriDatiAnagraficiAggiornabileType getAltriDatiLiberoProfessionista(
			String codiceImpresa) throws DataAccessException {
		return impreseMapper.getAltriDatiLiberoProfessionista(codiceImpresa);
	}

	@Override
	public AlboProfessionaleAggiornabileType getAlboProfessionaleReferenteImpresa(
			String codiceReferente) throws DataAccessException {
		return impreseMapper.getAlboProfessionaleReferenteImpresa(codiceReferente);
	}

	@Override
	public AlboProfessionaleAggiornabileType getAlboProfessionaleLiberoProfessionista(
			String codiceImpresa) throws DataAccessException {
		return impreseMapper.getAlboProfessionaleLiberoProfessionista(codiceImpresa);
	}

	@Override
	public CassaPrevidenzaAggiornabileType getCassaPrevidenzaReferenteImpresa(
			String codiceReferente) throws DataAccessException {
		return impreseMapper.getCassaPrevidenzaReferenteImpresa(codiceReferente);
	}

	@Override
	public CassaPrevidenzaAggiornabileType getCassaPrevidenzaLiberoProfessionista(
			String codiceImpresa) throws DataAccessException {
		return impreseMapper.getCassaPrevidenzaLiberoProfessionista(codiceImpresa);
	}

	@Override
	public DatoAnnuoImpresaAggiornabileType getDatiAnnui(String codiceImpresa,
			int anno) throws DataAccessException {
		return impreseMapper.getDatiAnnui(
				  codiceImpresa
				, anno
		);
	}

	@Override
	public String getIdImpresa(String username) throws DataAccessException {
		return impreseMapper.getIdImpresa(username);
	}

	private ReferenteImpresaAggiornabileType getIncaricoReferenteImpresa(
			String codiceImpresa, PKReferenteImpresa referente)
			throws DataAccessException {
		return impreseMapper.getIncaricoReferenteImpresa(
				codiceImpresa
				, referente.getCodice()
				, referente.getIncarico()
				, referente.getQualifica()
				, referente.getId()
		);
	}

	private List<PKReferenteImpresa> getPKAttualiReferentiImpresa(String codiceImpresa,
																  int incarico, Date dataInizioCessati) {
		return impreseMapper.getPKAttualiReferentiImpresa(
				codiceImpresa
				, incarico
				, dataInizioCessati
		);
	}


	@Override
	public VendorRatingType getVendorRatingValidita(String tokenRichiedente, Date data) throws DataAccessException, SqlComposerException {
		VendorRatingType vendorRatingValidita = impreseMapper.getVendorRatingValidita(tokenRichiedente, data);
		return vendorRatingValidita;
	}

	@Override
	public VendorRatingType getVendorRatingSospensione(String tokenRichiedente, Date data) throws DataAccessException, SqlComposerException {
		VendorRatingType vendorRatingSospensione = impreseMapper.getVendorRatingSospensione(tokenRichiedente, data);
		return vendorRatingSospensione;
	}

}
