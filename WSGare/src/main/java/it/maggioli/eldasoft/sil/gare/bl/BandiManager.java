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
package it.maggioli.eldasoft.sil.gare.bl;

import it.eldasoft.sil.portgare.datatypes.TipoPartecipazioneDocument;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.gene.bean.rest.ObjectStorageClient;
import it.maggioli.eldasoft.gene.db.dao.ComunicazioniDao;
import it.maggioli.eldasoft.gene.ws.DettaglioComunicazioneType;
import it.maggioli.eldasoft.sil.gare.db.dao.BandiDao;
import it.maggioli.eldasoft.sil.gare.db.dao.ImpreseDao;
import it.maggioli.eldasoft.sil.gare.db.vo.*;
import it.maggioli.eldasoft.sil.gare.ws.*;
import it.maggioli.eldasoft.sil.gare.ws.search.EspletamentoElencoOperatoriSearch;
import it.maggioli.eldasoft.sil.gare.ws.search.GaraTypeSearch;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static it.maggioli.eldasoft.gene.xml.CriptazioneByteAdapter.*;
import static java.lang.String.format;

/**
 * @author Stefano.Sabbadin
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BandiManager {

	public static final Logger logger = LoggerFactory.getLogger(BandiManager.class);
	
	public static final String CODICE_APPLICATIVO_APPALTI 				= "PG";		// codice applicativo Appalti (backoffice)
	public static final String CODICE_APPLICATIVO_PORTALE 				= "PA";		// codice applicativo Portale Appalti (fontoffice)
	public static final String CODICE_APPLICATIVO_LAVORI 				= "PA";		// codice applicativo ...

	/**
	 * Numero di giorni a partire dalla data odierna entro cui le comunicazioni
	 * si intendono ricevute e non archiviate.
	 */
	private static final int NUM_GIORNI_COMUNICAZIONI_RICEVUTE 			= -90;
	private static final int NUM_GIORNI_NEWS 							= -60;
	private static final int GENERE_BANDO_ISCRIZIONE 					= 10;
	
    private final BandiDao bandiDao;
	private final ImpreseDao impreseDao;
	private final ComunicazioniDao comunicazioniDao;
	private final ObjectStorageClient obs_client;

    public List<DatoCodificatoType> getElencoStazioniAppaltanti() {
    	return this.bandiDao.getElencoStazioniAppaltanti();
    }

	public List<DatoCodificatoType> getElencoStazioniAppaltantiL190() {
    	return this.bandiDao.getElencoStazioniAppaltantiL190();
    }

	public List<DatoCodificatoType> getStatiGara() {
    	return this.bandiDao.getStatiGara();
    }

	public List<DatoCodificatoType> getEsitiGara() {
    	return this.bandiDao.getEsitiGara();
    }

	/**
	 * Ritorna la versione dell'applicativo di backoffice
	 * 
	 * @return versione del backoffice
	 * @throws DataAccessException
	 */
	public String getAppaltiVersion() {
		return this.bandiDao.getAppaltiVersion();
	}
	
	public static Integer toInt(String value, String errorMessage) {
		Integer res = null;
		try {
			res = (StringUtils.isNotEmpty(value) ? Integer.valueOf(value) : null);
		} catch (NumberFormatException e) {
			logger.error(errorMessage + " (" + value + ")", e);
		}	
		return res;
	}
	
	public static Long toLong(String value, String errorMessage) {
		Long res = null;
		try {
			res = (StringUtils.isNotEmpty(value) ? Long.valueOf(value) : null);
		} catch (NumberFormatException e) {
			logger.error(errorMessage + " (" + value + ")", e);
		}	
		return res;
	}
	
	public static Integer boolToInt(Boolean value) {
		return (value != null && value ? 1 : null);
	}
	
	/**
	 * Estrae le info da DITG.IDANONIMO inoltre deanonimizza il record del richiedente e offusca il codice fiscale.
	 */
	private void deanonimizeCurrentAndRetrieveComstato(
			final List<EspletGaraOperatoreType> operatori
			, final String username
			, String espletamentoBustaType
			, final String codice
	) {
		deanonimizeCurrent(operatori, username);
		// Imposto un codice fiscale fittizio alle altre ditte
		operatori.stream()
			// Filtro tutte le ditte (tranne la richiedente)
			.filter(it -> StringUtils.isEmpty(it.getCodiceFiscale()))
		// Imposto un codice fittizio
		.forEach(it -> it.setCodiceFiscale("******"));

		if (espletamentoBustaType != null) {
			if (espletamentoBustaType.equals(EspletGaraDocumentoType.ESPLETAMENTO_GARA_BUSTA_AMMINISTRATIVA))
				espletamentoBustaType = "FS11A";
			else if (espletamentoBustaType.equals(EspletGaraDocumentoType.ESPLETAMENTO_GARA_BUSTA_ECONOMICA))
				espletamentoBustaType = "FS11C";
			else
				espletamentoBustaType = "FS11B";

			final String com_busta_type = espletamentoBustaType;
			/**
			 * Recupero il comstato per ogni operatore.
			 */
			operatori.stream()
				.map(it -> {
					// per ogni operatore, crea le coppie <operatore, stato comunicazione associata>
					DettaglioComunicazioneType filtri = new DettaglioComunicazioneType(); 
					filtri.setApplicativo(CODICE_APPLICATIVO_PORTALE);
					filtri.setChiave1(getFactoryCriptazioneByte(it.getUsernome(), DECIFRA));
					filtri.setChiave2(codice);
					filtri.setTipoComunicazione(com_busta_type);
					List<DettaglioComunicazioneType> comunicazioni = comunicazioniDao.getElencoComunicazioni(filtri);
					String stato = null;
					if(comunicazioni != null) 
						stato = comunicazioni.stream()
							.map(DettaglioComunicazioneType::getStato)
							.filter(st -> "5,6,7,13".indexOf(st) >= 0)
							.findFirst()
							.orElse(null);
					return new AbstractMap.SimpleEntry<>(it, stato);
				})	
				.forEach(it -> {
					// per ogni operatore imposta los tato della busta in base allo stato della comunicazione associata
					// <key, value> => <operatore, stato comunicazione associata>
					it.getKey().setRti(false);
					if (it.getValue() == null)
						it.getKey().setStatoBusta(0);
					else if (it.getValue().equals("5") || it.getValue().equals("13"))
						it.getKey().setStatoBusta(1);
					else
						it.getKey().setStatoBusta(2);
				});
		}
	}

	private void deanonimizeCurrent(List<EspletGaraOperatoreType> operatori, String username) {
		String cryptedUsername = getFactoryCriptazioneByte(username, CIFRA);
		// La ditta del richiedente deve essere un chiaro
		operatori.stream()
			.filter(it -> StringUtils.startsWith(it.getUsernome(), cryptedUsername))
			.map(it -> new AbstractMap.SimpleEntry<>(it, impreseDao.getImpresa(impreseDao.getIdImpresa(username))))
		// Metto il CF e la ragione sogiale in chiaro
		.forEach(it -> {
			it.getKey().setCodiceFiscale(it.getValue().getCodiceFiscale());
			it.getKey().setRagioneSociale(format("%s (%s)", it.getKey().getRagioneSociale(), it.getValue().getRagioneSociale()));
		});
	}

	/**
     * Ritorna l'elenco dei bandi di gara filtrati in base ai parametri in input.
     * 
     * @return lista dei bandi, di tipo GaraType
     * @throws SqlComposerException
     */
	public List<GaraType> searchBandi(GaraTypeSearch filtri) throws SqlComposerException {
		return bandiDao.searchBandi(filtri);
	}

	/**
	 * restituisce il numero di occorrenze estratte per i bandi 
	 */
	public int countBandi(GaraTypeSearch filtri) throws SqlComposerException {
		return bandiDao.countBandi(filtri);
	}

//    /**
//     * Ritorna l'elenco dei bandi di gara in corso di validit&agrave;.
//     * 
//     * @return lista dei bandi, di tipo GaraType
//     * @throws SqlComposerException
//     */
//	public List<GaraType> getElencoBandi() {
//		return this.bandiDao.getElencoBandi();
//	}

    /**
     * Ritorna l'elenco dei bandi di gara in corso di validit&agrave;.
     * 
     * @return lista dei bandi, di tipo GaraType
     * @throws DataAccessException
     * @throws SqlComposerException
     */
	public List<GaraType> getElencoBandi(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		return bandiDao.getElencoBandi(filtri);
	}
	
	/**
	 * restituisce il numero di occorrenze estratte per l'elenco bandi 
	 */
	public int countElencoBandi(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		return bandiDao.countElencoBandi(filtri);
	}

//	/**
//	 * Ritorna l'elenco dei bandi di gara scaduti.
//	 *
//	 * @param numAnniPubblicazione
//	 *            numero di anni per cui va mantenuta la procedura pubblicata
//	 * @return lista dei bandi, di tipo GaraType
//	 * @throws SqlComposerException
//	 */
//	public List<GaraType> getElencoBandiScaduti(int numAnniPubblicazione) {
//		return this.bandiDao.getElencoBandiScaduti(numAnniPubblicazione);
//	}
	
	/**
	 * Ritorna l'elenco dei bandi di gara scaduti.
	 * 
	 * @param filtri
	 * @return lista dei bandi, di tipo GaraType
	 * @throws DataAccessException 
	 * @throws SqlComposerException
	 */
	public List<GaraType> getElencoBandiScaduti(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		return bandiDao.getElencoBandiScaduti(filtri);
	}
	public int countElencoBandiScaduti(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
		return bandiDao.countElencoBandiScaduti(filtri);
	}
	
	/**
	 * Ritorna il cig in caso di gara a lotto unico e la lista dei cig in caso
	 * di gara a lotti.
	 * 
	 * @param codice
	 *            codice della gara
	 * @return cig singolo o degli n lotti, in tal caso separati da una virgola
	 */
	public String getCigBando(String codice) {
		return this.bandiDao.getCigBando(codice);
	}

	public StazioneAppaltanteBandoType getStazioneAppaltanteBando(String codice) {
		StazioneAppaltanteBandoType stazione = this.bandiDao
			.getStazioneAppaltanteBando(codice);		
		List<String> ordinanti = this.bandiDao.getPuntiOrdinanti(codice);
		List<String> istruttori = this.bandiDao.getPuntiIstruttori(codice);
		stazione.setPuntoOrdinante(ordinanti);
		stazione.setPuntoIstruttore(istruttori);		
		return stazione;
    }

	public GaraType getBando(String codiceGara) {
    	return this.bandiDao.getBando(codiceGara);
    }
	
	public GaraType getBandoByCig(String cig) {
    	return this.bandiDao.getBandoByCig(cig);
    }

	public List<DocumentoAllegatoType> getDocumentiBandoLotto(
	    String codiceGara, String codiceLotto) {
    	return this.bandiDao.getDocumentiBandoLotto(codiceGara, codiceLotto);
    }

	public List<DocumentoAllegatoType> getDocumentiInvitoLotto(
    	    String codiceGara, String codiceLotto) {
    	return this.bandiDao.getDocumentiInvitoLotto(codiceGara, codiceLotto);
    }

	public List<DocumentoAllegatoType> getDocumentiInvitoAstaLotto(
    	    String codiceGara, String codiceLotto) {
    	return this.bandiDao.getDocumentiInvitoAstaLotto(codiceGara, codiceLotto);
    }

	public List<RequisitoRichiestoType> getRequisitiRichiestiBandoLotto(
	    String codiceGara, String codiceLotto) {
    	return this.bandiDao.getRequisitiRichiestiBandoLotto(codiceGara,
    			codiceLotto);
    }

	public List<RequisitoRichiestoLotto> getRequisitiRichiestiBando(
    	    String codiceGara) {
    	return this.bandiDao.getRequisitiRichiestiBando(codiceGara);
    }

	public List<LottoGaraType> getLottiGara(String codiceGara) {
    	return this.bandiDao.getLottiGara(codiceGara);
    }

	public List<DocumentoAllegatoLotto> getDocumentiGara(String codiceGara) {
    	return this.bandiDao.getDocumentiGara(codiceGara);
    }

	public List<LottoGaraType> getLottiGaraPerDomandePartecipazione(
			String codiceGara, String progressivoOfferta) {
		return this.bandiDao.getLottiGaraPerDomandePartecipazione(codiceGara, progressivoOfferta);
	}

	public List<LottoGaraType> getLottiGaraPerRichiesteOfferta(
			String tokenRichiedente, String codiceGara, String progressivoOfferta) {
		return this.bandiDao.getLottiGaraPerRichiesteOfferta(tokenRichiedente,
				codiceGara, progressivoOfferta);
	}

	public List<LottoGaraType> getLottiGaraPlicoUnicoPerRichiesteOfferta(
			String tokenRichiedente, String codiceGara, String progressivoOfferta) {
		return this.bandiDao.getLottiGaraPlicoUnicoPerRichiesteOfferta(tokenRichiedente,
				codiceGara, progressivoOfferta);
	}

	public List<LottoGaraType> getLottiGaraPerRichiesteCheckDocumentazione(
			String tokenRichiedente, String codiceGara) {
		return this.bandiDao.getLottiGaraPerRichiesteCheckDocumentazione(
				tokenRichiedente, codiceGara);
	}

	public List<CategoriaGaraType> getCategorieLotto(String codiceLotto) {
    	return this.bandiDao.getCategorieLotto(codiceLotto);
    }

	public List<BandoIscrizioneType> searchBandiIscrizione(String token,
														   Integer stato, String stazioneAppaltante, Boolean isAttivo) {
		return this.bandiDao.searchBandiIscrizione(token, GENERE_BANDO_ISCRIZIONE, stato, stazioneAppaltante, isAttivo);
	}

	public List<BandoIscrizioneType> getElencoBandiIscrizione(String stazioneAppaltante, Boolean isAttivo) {
		return this.bandiDao.getElencoBandiIscrizione(GENERE_BANDO_ISCRIZIONE, stazioneAppaltante, isAttivo);
    }

	public StazioneAppaltanteBandoType getStazioneAppaltanteBandoIscrizione(String codice) {
		StazioneAppaltanteBandoType stazione = this.bandiDao
			.getStazioneAppaltanteBandoIscrizione(codice);		
		List<String> ordinanti = this.bandiDao.getPuntiOrdinanti(codice);
		List<String> istruttori = this.bandiDao.getPuntiIstruttori(codice);
		stazione.setPuntoOrdinante(ordinanti);
		stazione.setPuntoIstruttore(istruttori);
		return stazione;
    }

    public BandoIscrizioneType getBandoIscrizione(String codice) {
    	return this.bandiDao.getBandoIscrizione(codice);
    }

    public List<DocumentoAllegatoType> getDocumentiBandoIscrizione(
	    String codice) {
    	return this.bandiDao.getDocumentiBandoIscrizione(codice);
    }

	public List<RequisitoRichiestoType> getRequisitiRichiestiBandoIscrizione(
			String codice) {
		return this.bandiDao.getRequisitiRichiestiBandoIscrizione(codice);
	}

    public List<CategoriaBandoIscrizioneType> getCategorieBandoIscrizione(String codice, String filtroCategorie) throws SqlComposerException {
    	return this.bandiDao.getCategorieBandoIscrizione(codice, filtroCategorie);
    }

	public List<ComunicazioneType> getComunicazioniPubbliche(
			String codice,
			String entita,
			String stazioneAppaltante) 
	{

		List<ComunicazioneType> risultato = this.bandiDao.getComunicazioniRicevute(
				null, 
				codice,
				null, null,
				false,
				entita,
				0, 0, 
				stazioneAppaltante,
				null);
		List<DocumentoAllegatoType> docs = null;
		for (ComunicazioneType comunicazione : risultato) {
			docs = this.bandiDao.getDocumentiComunicazione(comunicazione.getApplicativo(), comunicazione
					.getIdComunicazione());
			comunicazione.setDocumento(docs);
		}
		return risultato;
	}

	public List<ComunicazioneLotto> getLottiComunicazioniPubbliche(
			String codice,
			String entita,
			String stazioneAppaltante) 
	{
		// recupera l'elenco di tutte le comunicazioni associate ai lotti della gara...
		List<ComunicazioneLotto> risultato = this.bandiDao.getComunicazioniPubblicheRicevute(
				CODICE_APPLICATIVO_APPALTI, 
				codice,
				entita,
				stazioneAppaltante);
		
		if(risultato != null) {
			
			// recupera l'elenco di tutti i documenti associati alle comunicazioni...
			List<DocumentoAllegatoLotto> lottiDocumenti = this.bandiDao
				.getDocumentiComunicazioniPubblicheRicevute(CODICE_APPLICATIVO_APPALTI, codice);

			for(int i = 0; i < risultato.size(); i++) {
				ComunicazioneType comunicazione = risultato.get(i);
				
				// aggiungi i documenti alla comunicazione...
				List<DocumentoAllegatoType> docs = comunicazione.getDocumento();
				for(DocumentoAllegatoLotto doc : lottiDocumenti) {
					if( doc.getIdComunicazione() == comunicazione.getIdComunicazione() ) {
						docs.add(doc);
					}
				}
				comunicazione.setDocumento(docs);
			}
		}		
		return risultato;
	}

//	public StatisticheComunicazioniPersonaliType getStatisticheComunicazioniPersonali(
//			String tokenRichiedente, String codice) {
//		StatisticheComunicazioniPersonaliType risultato = new StatisticheComunicazioniPersonaliType();
//		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
//				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
//		risultato.setNumComunicazioniRicevute(this.bandiDao.countComunicazioniRicevute(tokenRichiedente, codice, dataInvioDa, null, false, null));
//		if (risultato.getNumComunicazioniRicevute() > 0) {
//			risultato.setNumComunicazioniRicevuteDaLeggere(this.bandiDao.countComunicazioniRicevute(tokenRichiedente, codice, dataInvioDa, null, true, null));
//		}
//		risultato.setNumComunicazioniArchiviate(this.bandiDao.countComunicazioniRicevute(tokenRichiedente, codice, null, dataInvioDa, false, null));
//		if (risultato.getNumComunicazioniArchiviate() > 0) {
//			risultato.setNumComunicazioniArchiviateDaLeggere(this.bandiDao.countComunicazioniRicevute(tokenRichiedente, codice, null, dataInvioDa, true, null));
//		}
//		risultato.setNumComunicazioniInviate(this.bandiDao.countComunicazioniInviate(tokenRichiedente, codice, null));
//		return risultato;
//	}

	public StatisticheComunicazioniPersonaliType getStatisticheComunicazioniPersonali(
			String tokenRichiedente, 
			String codice, 
			String codice2,
			String entita,
			String stazioneAppaltante) 
	{
		StatisticheComunicazioniPersonaliType risultato = new StatisticheComunicazioniPersonaliType();
		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		String cryptedUsername = tokenRichiedente == null ? tokenRichiedente : getFactoryCriptazioneByte(
				tokenRichiedente, CIFRA
		);
		
		List<StatisticheComunicazioni> statistiche = this.bandiDao.countComunicazioniRicevuteFull(
				tokenRichiedente, 
				codice, 
				codice2,
				dataInvioDa,
				entita,
				stazioneAppaltante,
				cryptedUsername);
		risultato = new StatisticheComunicazioniPersonaliType();
		risultato.setNumComunicazioniRicevute(0);
		risultato.setNumComunicazioniRicevuteDaLeggere(0);
		risultato.setNumComunicazioniArchiviate(0);
		risultato.setNumComunicazioniArchiviateDaLeggere(0);
		risultato.setNumSoccorsiIstruttori(0);
		risultato.setNumSoccorsiIstruttoriDaLeggere(0);
		risultato.setNumComunicazioniInviate(0);
		if(statistiche != null) {
			for(StatisticheComunicazioni s : statistiche) {
				boolean ricevute = (s.getDal() == 1 && s.getSoccorsoNo() == 1) 
								   || (s.getDal() == 1 && s.getSoccorsoNo() == 0 && s.getSoccorsoSi() == 0);
				boolean archiviate = (s.getAl() == 1 && s.getSoccorsoNo() == 1);
				boolean soccorsi = (s.getDal() == 1 && s.getSoccorsoSi() == 1);
				
				if(ricevute) {
					risultato.setNumComunicazioniRicevute(risultato.getNumComunicazioniRicevute() + s.getCount());
					if(s.getDaLeggere() == 1)
						risultato.setNumComunicazioniRicevuteDaLeggere(risultato.getNumComunicazioniRicevuteDaLeggere() + s.getCount());
				}
				if(archiviate) {
					risultato.setNumComunicazioniArchiviate(risultato.getNumComunicazioniArchiviate() + s.getCount());
					if(s.getDaLeggere() == 1)
						risultato.setNumComunicazioniArchiviateDaLeggere(risultato.getNumComunicazioniArchiviateDaLeggere() + s.getCount());
				}
				if(soccorsi) {
					risultato.setNumSoccorsiIstruttori(risultato.getNumSoccorsiIstruttori() + s.getCount());
					if(s.getDaLeggere() == 1)
						risultato.setNumSoccorsiIstruttoriDaLeggere(risultato.getNumSoccorsiIstruttoriDaLeggere() + s.getCount());
				}
			}
		}
		
		risultato.setNumComunicazioniInviate(this.bandiDao.countComunicazioniInviate(
				tokenRichiedente, 
				codice, 
				codice2,
				entita,
				stazioneAppaltante));
		return risultato;
	}

	public StatisticheComunicazioniPersonaliType getStatisticheComunicazioniPersonaliGaraLotti(
			String tokenRichiedente, 
			String codiceGara,
			String entita,
			String stazioneAppaltante) 
	{
		StatisticheComunicazioniPersonaliType risultato = new StatisticheComunicazioniPersonaliType();
		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		
		String[] codiciLotto = getCodiciLotto(codiceGara);
		risultato.setNumComunicazioniRicevute(this.bandiDao.countComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto, 
				dataInvioDa, null, 
				false, 
				entita,
				false, 
				stazioneAppaltante));
		if (risultato.getNumComunicazioniRicevute() > 0) {
			risultato.setNumComunicazioniRicevuteDaLeggere(this.bandiDao.countComunicazioniRicevuteLotti(
					tokenRichiedente, 
					codiciLotto, 
					dataInvioDa, null, 
					false,
					entita,
					true, 
					stazioneAppaltante));
		}
		risultato.setNumComunicazioniArchiviate(this.bandiDao.countComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto, 
				null, dataInvioDa, 
				false, 
				entita,
				false, 
				stazioneAppaltante));
		if (risultato.getNumComunicazioniArchiviate() > 0) {
			risultato.setNumComunicazioniArchiviateDaLeggere(this.bandiDao.countComunicazioniRicevuteLotti(
					tokenRichiedente, 
					codiciLotto, 
					null, dataInvioDa, 
					false,
					entita,
					true, 
					stazioneAppaltante));
		}
		risultato.setNumSoccorsiIstruttori(this.bandiDao.countComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto, 
				dataInvioDa, null, 
				true, 
				entita,
				false, 
				stazioneAppaltante));
		risultato.setNumComunicazioniInviate(this.bandiDao.countComunicazioniInviate(
				tokenRichiedente, 
				codiceGara,
				null,
				entita,
				stazioneAppaltante));
		return risultato;
	}

	public String[] getCodiciLotto(String codiceGara) {
		List<LottoGaraType> lotti = this.bandiDao.getLottiGara(codiceGara);
		String[] codiciLotto = new String[lotti.size()];
		for (int i = 0; i < lotti.size(); i++) {
			codiciLotto[i] = lotti.get(i).getCodiceLotto();
		}
		return codiciLotto;
	}
	
	public List<ComunicazioneType> getComunicazioniRicevute(
			String tokenRichiedente, 
			String codice, 
			boolean soccorsoIstruttorio, 
			String entita,
			int indicePrimoRecord, int maxNumRecord, 
			String stazioneAppaltante) 
	{
		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		String cryptedUsername = tokenRichiedente == null ? tokenRichiedente : getFactoryCriptazioneByte(
				tokenRichiedente, CIFRA
		);
		return this.bandiDao.getComunicazioniRicevute(
				tokenRichiedente, 
				codice,
				dataInvioDa, null,
				soccorsoIstruttorio,
				entita,
				indicePrimoRecord, maxNumRecord, 
				stazioneAppaltante,
				cryptedUsername
		);
	}
	
	public List<ComunicazioneType> getComunicazioniRicevuteGaraLotti(
			String tokenRichiedente, 
			String[] codiciLotto, 
			boolean soccorsoIstruttorio,
			String entita,
			int indicePrimoRecord, int maxNumRecord,
			String stazioneAppString) 
	{
		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		return this.bandiDao.getComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto,
				dataInvioDa, null,
				soccorsoIstruttorio,
				entita,
				indicePrimoRecord, maxNumRecord, 
				stazioneAppString);
	}
	
	public List<ComunicazioneType> getComunicazioniArchiviate(
			String tokenRichiedente, 
			String codice,
			boolean soccorsoIstruttorio,
			String entita,
			int indicePrimoRecord, int maxNumRecord, 
			String stazioneAppaltante) 
	{
		Date dataInvioA = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		String cryptedUsername = tokenRichiedente == null ? tokenRichiedente : getFactoryCriptazioneByte(
				tokenRichiedente, CIFRA
		);
		return this.bandiDao.getComunicazioniRicevute(
				tokenRichiedente, 
				codice,
				null, dataInvioA,
				soccorsoIstruttorio,
				entita,
				indicePrimoRecord, maxNumRecord, 
				stazioneAppaltante,
				cryptedUsername
		);
	}

	public List<ComunicazioneType> getComunicazioniArchiviateGaraLotti(
			String tokenRichiedente, 
			String[] codiciLotto,
			boolean soccorsoIstruttorio,
			String entita,
			int indicePrimoRecord, int maxNumRecord, 
			String stazioneAppString) 
	{
		Date dataInvioA = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		return this.bandiDao.getComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto,
				null, dataInvioA, 
				soccorsoIstruttorio,
				entita,
				indicePrimoRecord, maxNumRecord, 
				stazioneAppString);
	}
	
	public List<ComunicazioneType> getComunicazioniInviate(
			String tokenRichiedente, 
			String codice,
			String entita,
			int indicePrimoRecord, int maxNumRecord, 
			String stazioneAppaltante) 
	{
		return this.bandiDao.getComunicazioniInviate(
				tokenRichiedente, 
				codice,
				entita,
				indicePrimoRecord, maxNumRecord,
				stazioneAppaltante);
	}

	public int countComunicazioniRicevute(
			String tokenRichiedente, 
			String codice, 
			String codice2, 
			boolean soccorsoIstruttorio,
			String entita,
			boolean soloDaLeggere,
			String stazioneAppaltante) 
	{
		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		String cryptedUsername = tokenRichiedente == null ? tokenRichiedente : getFactoryCriptazioneByte(
				tokenRichiedente, CIFRA
		);
		return this.bandiDao.countComunicazioniRicevute(
				tokenRichiedente, 
				codice, 
				codice2,
				dataInvioDa, null,
				soccorsoIstruttorio,
				entita,
				soloDaLeggere, 
				stazioneAppaltante,
				cryptedUsername
		);
	}

	public int countComunicazioniRicevuteGaraLotti(
			String tokenRichiedente, 
			String[] codiciLotto,
			boolean soccorsoIstruttorio,
			String entita,
			boolean soloDaLeggere,
			String stazioneAppaltante) 
	{
		Date dataInvioDa = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		return this.bandiDao.countComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto, 
				dataInvioDa, null,
				soccorsoIstruttorio,
				entita,
				false,
				stazioneAppaltante);
	}

	public int countComunicazioniArchiviate(
			String tokenRichiedente, 
			String codice, 
			String codice2,
			boolean soccorsoIstruttorio,
			String entita,
			boolean soloDaLeggere,
			String stazioneAppaltante) 
	{
		Date dataInvioA = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		String cryptedUsername = tokenRichiedente == null ? tokenRichiedente : getFactoryCriptazioneByte(
				tokenRichiedente, CIFRA
		);
		return this.bandiDao.countComunicazioniRicevute(
				tokenRichiedente, 
				codice,
				codice2,
				null, dataInvioA,
				soccorsoIstruttorio,
				entita,
				soloDaLeggere,
				stazioneAppaltante,
				cryptedUsername
		);
	}

	public int countComunicazioniArchiviateGaraLotti(
			String tokenRichiedente, 
			String[] codiciLotto,
			boolean soccorsoIstruttorio,
			String entita,
			boolean soloDaLeggere,
			String stazioneAppaltante) 
	{
		Date dataInvioA = DateUtils.round(DateUtils.addDays(new Date(),
				NUM_GIORNI_COMUNICAZIONI_RICEVUTE), Calendar.DAY_OF_MONTH);
		return this.bandiDao.countComunicazioniRicevuteLotti(
				tokenRichiedente, 
				codiciLotto, 
				null, dataInvioA,
				soccorsoIstruttorio,
				entita,
				false,
				stazioneAppaltante);
	}

	public int countComunicazioniInviate(
			String tokenRichiedente, 
			String codice,
			String codice2,
			String entita,
			String stazioneAppaltante) 
	{
		return this.bandiDao.countComunicazioniInviate(
				tokenRichiedente, 
				codice,
				codice2,
				entita,
				stazioneAppaltante);
	}

	public ComunicazioneType getComunicazioneRicevuta(
			String tokenRichiedente,
			long idComunicazione,
			long idDestinatario,
			String idprg) 
	{
		ComunicazioneType comunicazione = this.bandiDao
				.getComunicazioneRicevuta(
						tokenRichiedente
						, getFactoryCriptazioneByte(tokenRichiedente, CIFRA)
						, idComunicazione
						, idDestinatario
						, idprg
				);
		if (comunicazione != null) {
			List<DocumentoAllegatoType> docs = this.bandiDao
					.getDocumentiComunicazione(comunicazione.getApplicativo(),
							idComunicazione);
			comunicazione.setDocumento(docs);
		}
		return comunicazione;
	}

	public ComunicazioneType getComunicazioneInviata(String tokenRichiedente,
			long idComunicazione, String idprg) {
		ComunicazioneType comunicazione = this.bandiDao
				.getComunicazioneInviata(tokenRichiedente, idComunicazione, idprg);
		if (comunicazione != null) {
			List<DocumentoAllegatoType> docs = this.bandiDao
					.getDocumentiComunicazione(comunicazione.getApplicativo(),
							idComunicazione);
			comunicazione.setDocumento(docs);
		}
		return comunicazione;
	}

	public List<InvioType> getElencoInvii(String tokenRichiedente,
			String codice, String[] tipiComunicazione) {
		return this.bandiDao.getElencoInvii(tokenRichiedente, codice,
				tipiComunicazione);
	}

	public Integer getStatoIscrizioneABandoIscrizione(String tokenRichiedente,
	    String codice) {
		return this.bandiDao.getStatoIscrizioneABandoIscrizione(
			tokenRichiedente, codice);
    }

	public List<DocumentazioneRichiestaType> getDocumentiRichiestiBandoGara(
			String codiceGara, String codiceLotto, String tipoImpresa, boolean rti, String busta) {
		Integer intTipoImpresa = null;
		try {
		    if (tipoImpresa != null && !"".equals(tipoImpresa))
			intTipoImpresa = Integer.valueOf(tipoImpresa);
		} catch (NumberFormatException e) {
		    logger.error("Formato non valido per il tipo impresa ("
			    + tipoImpresa + ")", e);
		}
		Integer intBusta = null;
		try {
		    if (busta != null && !"".equals(busta))
		    	intBusta = Integer.valueOf(busta);
		} catch (NumberFormatException e) {
		    logger.error("Formato non valido per la busta ("
			    + busta + ")", e);
		}

		return this.bandiDao.getDocumentiRichiestiBandoGara(codiceGara, codiceLotto,
			intTipoImpresa, rti, intBusta);
	}

	public List<DocumentazioneRichiestaLotto> getDocumentiRichiestiBando(
			String codiceGara, String tipoImpresa, boolean rti) {
		Integer intTipoImpresa = null;
		try {
		    if (tipoImpresa != null && !"".equals(tipoImpresa))
			intTipoImpresa = Integer.valueOf(tipoImpresa);
		} catch (NumberFormatException e) {
		    logger.error("Formato non valido per il tipo impresa ("
			    + tipoImpresa + ")", e);
		}

		return this.bandiDao.getDocumentiRichiestiBando(codiceGara, 
				intTipoImpresa, rti);
	}

    public List<DocumentazioneRichiestaType> getDocumentiRichiestiBandoIscrizione(
	    String codice, String tipoImpresa, boolean rti) {
		Integer intTipoImpresa = null;
		try {
		    if (tipoImpresa != null && !"".equals(tipoImpresa))
			intTipoImpresa = Integer.valueOf(tipoImpresa);
		} catch (NumberFormatException e) {
		    logger.error("Formato non valido per il tipo impresa ("
			    + tipoImpresa + ")", e);
		}
	
		return this.bandiDao.getDocumentiRichiestiBandoIscrizione(codice,
			intTipoImpresa, rti);
    }

	public List<DocumentazioneRichiestaType> getDocumentiRichiestiRinnovoIscrizione(
			String codice, String tipoImpresa, boolean rti) {
		Integer intTipoImpresa = null;
		try {
			if (tipoImpresa != null && !"".equals(tipoImpresa))
				intTipoImpresa = Integer.valueOf(tipoImpresa);
		} catch (NumberFormatException e) {
			logger.error("Formato non valido per il tipo impresa ("
					+ tipoImpresa + ")", e);
		}

		return this.bandiDao.getDocumentiRichiestiRinnovoIscrizione(codice,
				intTipoImpresa, rti);
	}

	public List<DocumentazioneRichiestaType> getDocumentiRichiestiComunicazione(
			String idPrg, long idComunicazione) 
	{
		return this.bandiDao.getDocumentiRichiestiComunicazione(idPrg, idComunicazione);
	}

    public List<DatoCodificatoType> getStatiIscrizioneElencoOperatori() {
    	return this.bandiDao.getStatiIscrizioneElencoOperatori();
    }

	public ImpresaIscrizioneType getImpresaIscrizione(
			String username, String codice) throws DataAccessException {
		return this.bandiDao.getImpresaIscrizione(username, codice);
	}

//    public List<GaraType> getElencoBandiPerDomandePartecipazione(
//	    String tokenRichiedente) {
//	return this.bandiDao.getElencoBandiPerDomandePartecipazione(tokenRichiedente);
//    }

    
//    public List<GaraType> getElencoBandiPerRichiesteOfferta(
//	    String tokenRichiedente) {
//		return this.bandiDao.getElencoBandiPerRichiesteOfferta(tokenRichiedente);
//    }

    public List<GaraType> getElencoBandiPerRichiesteOfferta(GaraTypeSearch filtri) throws SqlComposerException {
		filtri.setCryptedUsername(getFactoryCriptazioneByte(filtri.getTokenRichiedente(), CIFRA));
    	return bandiDao.getElencoBandiPerRichiesteOfferta(filtri);
    }
    
    public int countElencoBandiPerRichiesteOfferta(GaraTypeSearch filtri) throws SqlComposerException {
		filtri.setCryptedUsername(getFactoryCriptazioneByte(filtri.getTokenRichiedente(), CIFRA));
		return bandiDao.countElencoBandiPerRichiesteOfferta(filtri);
    }

//    public List<GaraType> getElencoBandiPerRichiesteCheckDocumentazione(
//	    String tokenRichiedente) {
//		return this.bandiDao.getElencoBandiPerRichiesteCheckDocumentazione(tokenRichiedente);
//    }

    public List<GaraType> getElencoBandiPerRichiesteCheckDocumentazione(GaraTypeSearch filtri) throws SqlComposerException {
		return bandiDao.getElencoBandiPerRichiesteCheckDocumentazione(filtri);
	}

    public int countElencoBandiPerRichiesteCheckDocumentazione(GaraTypeSearch filtri) throws SqlComposerException {
		return bandiDao.countElencoBandiPerRichiesteCheckDocumentazione(filtri);
	}

    public List<GaraType> getElencoBandiPerAsteInCorso(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
    	return bandiDao.getElencoBandiPerAsteInCorso(filtri);
    }

    public int countBandiPerAsteInCorso(GaraTypeSearch filtri) throws DataAccessException, SqlComposerException {
    	return bandiDao.countBandiPerAsteInCorso(filtri);
    }

	public AbilitazioniGaraType getAbilitazioniGara(String tokenRichiedente,
			String codice) {
		AbilitazioniGaraType abilitazioni = new AbilitazioniGaraType();
		abilitazioni.setRichPartecipazione(this.bandiDao
				.getAbilitazionePartecipazioneGara(
						tokenRichiedente
						, codice
				)
		);
		abilitazioni.setRichInvioOfferta(this.bandiDao
				.getAbilitazionePresentazioneOfferta(
						tokenRichiedente
						, codice
						, getFactoryCriptazioneByte(tokenRichiedente, CIFRA)
				));
		abilitazioni.setRichComprovaRequisiti(this.bandiDao
				.getAbilitazioneCheckDocumentazione(tokenRichiedente, codice));
		return abilitazioni;
	}

    public TipoPartecipazioneType getTipoPartecipazioneImpresa(
	    String tokenRichiedente, String codice, String progOfferta) {
    	return this.bandiDao.getTipoPartecipazioneImpresa(tokenRichiedente, codice, progOfferta);
    }

    public List<DatoCodificatoType> getElencoStazioniAppaltantiPerIscrizione(
	    String codiceBandoIscrizione) {
    	return bandiDao.getElencoStazioniAppaltantiPerIscrizione(codiceBandoIscrizione);
    }

    public List<CategoriaImpresaType> getCategorieImpresaPerIscrizione(
	    String tokenRichiedente, String codiceBandoIscrizione) {
    	return bandiDao.getCategorieImpresaPerIscrizione(tokenRichiedente, codiceBandoIscrizione);
    }

	public List<DatoCodificatoType> getElencoClassificazioniLavoriPerIscrizione() {
		return bandiDao.getElencoClassificazioniLavoriPerIscrizione();
	}

	public List<GaraType> searchBandiPerProcInAggiudicazione(GaraTypeSearch filtri) throws SqlComposerException {
		filtri.setCryptedUsername(getFactoryCriptazioneByte(filtri.getTokenRichiedente(), CIFRA));
		return bandiDao.searchBandiPerProcInAggiudicazione(filtri);
	}
	
	public int countBandiPerProcInAggiudicazione(GaraTypeSearch filtri) throws SqlComposerException {
		filtri.setCryptedUsername(getFactoryCriptazioneByte(filtri.getTokenRichiedente(), CIFRA));
		return bandiDao.countBandiPerProcInAggiudicazione(filtri);
	}
		
	public List<GaraType> searchBandiConEsito(GaraTypeSearch filtri) throws SqlComposerException {
		//Integer intEsito = toInt(esito, "Formato non valido per l'esito");
		return bandiDao.searchBandiConEsito(filtri);
	}
	
	public int countBandiConEsito(GaraTypeSearch filtri) throws SqlComposerException {
		//Integer intEsito = toInt(esito, "Formato non valido per l'esito");
		return bandiDao.countBandiConEsito(filtri);
	}

	public Boolean isGaraConOffertaTecnica(String codiceGara) {
		return this.bandiDao.isGaraConOffertaTecnica(codiceGara);
	}

	public Boolean isImpresaAbilitataRinnovo(String codiceBandoIscrizione,
			String tokenRichiedente) {
		return this.bandiDao.isImpresaAbilitataRinnovo(codiceBandoIscrizione,
				tokenRichiedente);
	}

	public List<VoceDettaglioOffertaType> getVociDettaglioOfferta(String codice) {
		return this.bandiDao.getVociDettaglioOfferta(codice);
	}

	public List<VoceDettaglioOffertaType> getVociDettaglioOffertaNoRibasso(String codice) {
		return this.bandiDao.getVociDettaglioOffertaNoRibasso(codice);
	}
	
	public List<AttributoAggiuntivoOfferta> getAttributiAggiuntiviOfferta(
			String codice) {
		return this.bandiDao.getAttributiAggiuntiviOfferta(codice);
	}

	public DettaglioStazioneAppaltanteType getDettaglioStazioneAppaltante(
			String codice) {
		return this.bandiDao.getDettaglioStazioneAppaltante(codice);
	}
	
	public List<DettaglioStazioneAppaltanteType> getStazioniAppaltanti() {
		return this.bandiDao.getStazioniAppaltanti();
	}

	public List<String> getPuntiOrdinanti(String codiceGara) {
		return this.bandiDao.getPuntiOrdinanti(codiceGara);
	}

	public List<String> getPuntiIstruttori(String codiceGara) {
		return this.bandiDao.getPuntiIstruttori(codiceGara);
	}

	public List<MandanteRTIType> getMandantiRTI(
			String codiceProcedura,
			String tokenMandataria,
			String progOfferta) 
	{
		return this.bandiDao.getMandantiRTI(codiceProcedura, tokenMandataria, progOfferta);
	}

	public FascicoloProtocolloType getFascicoloProtocollo(String codice) {
		return bandiDao.getFascicoloProtocollo(codice);
	}

	public Long getGenere(String codice) {
		return this.bandiDao.getGenere(codice);
	}

	public String getCodiceGaraFromCodiceLotto(String codiceLotto) {
		return bandiDao.getCodiceGaraFromCodiceLotto(codiceLotto);
	}

	public String getChiavePubblica(String codice, String tipo) {
		return this.bandiDao.getChiavePubblica(codice, tipo);
	}

	public Boolean isConsorziateEsecutriciPresenti(String tokenRichiedente, String codiceGara) {
		return bandiDao.isConsorziateEsecutriciPresenti(tokenRichiedente, codiceGara);
	}
	
	public List<DocumentoAllegatoType> getAttiDocumentiBandoGara(String codiceGara){
		return bandiDao.getAttiDocumentiBandoGara(codiceGara);
	}

	public boolean isAstaElettronica(String codiceGara) {
		return this.bandiDao.isAstaElettronica(codiceGara);
	}
	
	public boolean isAstaAttiva(String codiceGara, Date dataCorrente) {
		return bandiDao.isAstaAttiva(codiceGara, dataCorrente);
	}
	
	public Date getDataInizioAsta(String codiceGara) {
		return this.bandiDao.getDataInizioAsta(codiceGara);
	}
	
	public Date getDataInvitoAsta(String codiceGara) {
		return this.bandiDao.getDataInvitoAsta(codiceGara);
	}
	
	public Boolean isInvitataAsta(String codiceGara, String username) {
		return bandiDao.isInvitataAsta(codiceGara, username);
	}

	public Long checkAggiornamentoIscrizionePosticipata(String tokenRichiedente, String codiceGara) {
		return bandiDao.checkAggiornamentoIscrizionePosticipata(tokenRichiedente, codiceGara);
	}

	public List<EspletGaraOperatoreType> getEspletamentoGaraSoccorsiElencoOperatori(
			String codiceGara, 
			String codiceLotto,
			String fase, 
			String tokenRichiedente) 
	{
		// recupera il singolo operatore o l'elenco degli operatori economici 
		// per la gara...
		List<EspletGaraOperatoreType> elenco = bandiDao
			.getEspletamentoGaraSoccorsiElencoOperatori(
					codiceGara
					, codiceLotto
					, fase
					, tokenRichiedente
					, getFactoryCriptazioneByte(tokenRichiedente, CIFRA)
			);
		return elenco;
	}

	public List<EspletGaraOperatoreType> getEspletamentoGaraDocAmmElencoOperatori(
			EspletamentoElencoOperatoriSearch search
	) {
		// Viene fatto questo giochetto con il search perche' la busta amministrativa non e' a lotti.
		String codiceLottoBackup = search.getCodiceLotto();
		search.setCodiceLotto(null);
		try {
			return getEspletamentoGaraOperatori(
					search
					, bandiDao::getEspletamentoGaraDocAmmElencoOperatori
					, EspletGaraDocumentoType.ESPLETAMENTO_GARA_BUSTA_AMMINISTRATIVA
			);
		} finally {
			search.setCodiceLotto(codiceLottoBackup);
		}
	}

	public List<EspletGaraOperatoreType> getEspletamentoGaraValTecElencoOperatori(
			EspletamentoElencoOperatoriSearch search
	) {
		return getEspletamentoGaraOperatori(
				search
				, bandiDao::getEspletamentoGaraValTecElencoOperatori
				, EspletGaraDocumentoType.ESPLETAMENTO_GARA_BUSTA_TECNICA
		);
	}


	/* Ottimizzazione per gestione dei lotti */
	public List<EspletGaraOperatoreType> getEspletamentoGaraValTecElencoOperatoriLotto(
			String codice
			, String tokenRichiedente
	) {
		return getEspletamentoGaraOperatoriLotto(
				codice
				, tokenRichiedente
				, this::getEspletamentoGaraValTecElencoOperatoriLottoWithCheck
		);
	}

	/**
	 *
	 * @param search criteri di ricerca
	 * @param getElenchi metodo del <i>DAO/Manager</i> che recupera la lista di operatori.<br>
	 *                   I parametri sono: <i>codiceGara</i> e <i>codiceOperatore</i>.
	 * @param espletamentoBustaType tipo di busta tecnica/economica.
	 *                                 Vedi: EspletGaraDocumentoType.ESPLETAMENTO_GARA_BUSTA_TECNICA
	 * @return
	 */
	public List<EspletGaraOperatoreType> getEspletamentoGaraOperatori(
			EspletamentoElencoOperatoriSearch search
			, BiFunction<String, String, List<EspletGaraOperatoreType>> getElenchi
			, String espletamentoBustaType
	) {
		String codiceGara = !StringUtils.isEmpty(search.getCodiceLotto())
				? search.getCodiceLotto()
				: search.getCodice();
		/*
		  Recupera il singolo operatore o l'elenco degli operatori economici
		  per la gara...
		 */
		List<EspletGaraOperatoreType> elenco = getElenchi.apply(
				codiceGara
				, search.getCodiceOperatore()
		);
		/*
		  Se abbiamo gli idanonimi vuol dire che siamo in una gara con concorso di progettazione e siamo ancora nella
		  fase anonima, quindi, avra' i record a database anonimi e dovra' valorizzare il record della ditta
		  corrente.

		  Il controllo viene fatto anche per le fase economica, ma, non e' prevista la fase economica per i concorsi
		  di progettazione.
		 */
		if (elenco != null && !elenco.isEmpty() && elenco.get(0).isAnonimo())
			deanonimizeCurrentAndRetrieveComstato(elenco, search.getUsername(), espletamentoBustaType, search.getCodice());

		// recupera i dati di dettaglio del singolo operatore economico
		if (search.getCodiceOperatore() != null
				&& elenco != null && elenco.size() == 1) {

			// se c'e' un raggruppamento RTI carica i componenti...
			if (elenco.get(0).isRti()) {
				List<EspletGaraComponenteRtiType> componentiRti = this.bandiDao
						.getEspletamentoGaraComponentiRti(codiceGara, search.getCodiceOperatore());
				elenco.get(0).setComponentiRTI(componentiRti);
			}

			// carica i documenti...
			List<EspletGaraDocumentoType> documenti =
					bandiDao.getEspletamentoGaraDocumenti(
							search.getCodice(),
							search.getCodiceLotto(),
							search.getCodiceOperatore(),
							espletamentoBustaType,
							true
					);
			elenco.get(0).setDocumenti(documenti);
		}

		return elenco;

	}

	private List<EspletGaraOperatoreLotto> getEspletamentoGaraValTecElencoOperatoriLottoWithCheck(
			String codice
			, String tokenRichiedente
	) {
		return isGaraConOffertaTecnica(codice)
				? bandiDao.getEspletamentoGaraValTecElencoOperatoriLotto(codice, tokenRichiedente)
				: null;
	}

	public List<EspletGaraOperatoreType> getEspletamentoGaraOffEcoElencoOperatori(
			EspletamentoElencoOperatoriSearch search
	) {
		return getEspletamentoGaraOperatori(
				search
				, bandiDao::getEspletamentoGaraOffEcoElencoOperatori
				, EspletGaraDocumentoType.ESPLETAMENTO_GARA_BUSTA_ECONOMICA
		);
	}

	/**
	 * Funziona sia per la busta economica che per la busta tecnica.
	 *
	 * @param codice Codice della gara.
	 * @param tokenRichiedente Attualmente corrisponde al codice dell'operatore.
	 * @param getLotti Il metodo del DAO/Manager per recuperare i lotti.
	 * @return
	 */
	private List<EspletGaraOperatoreType> getEspletamentoGaraOperatoriLotto(
			String codice
			, String tokenRichiedente
			, BiFunction<String, String, List<EspletGaraOperatoreLotto>> getLotti
	) {
		List<EspletGaraOperatoreType> elenco = null;

		List<EspletGaraOperatoreLotto> lottiElenco = getLotti.apply(codice, tokenRichiedente);
		// crea la lista di lotti-operatore...
		// la e' lista ordinata per (operatore, lotto)...
		if (lottiElenco != null) {
			elenco = new ArrayList<>();

			String lastKey = "";
			for (EspletGaraOperatoreLotto espletGaraOperatoreLotto : lottiElenco) {

				String key = espletGaraOperatoreLotto.getCodiceOperatore();

				EspletGaraOperatoreType operatore = null;
				if (!key.equals(lastKey)) {
					// trovato un nuovo operatore...
					operatore = new EspletGaraOperatoreType(espletGaraOperatoreLotto);
					operatore.setLotti(new ArrayList<>());
					elenco.add(operatore);
					lastKey = key;
				} else
					operatore = elenco.get(elenco.size() - 1);

				// associa all'operatore solo il codiceInterno del lotto
				// gli altri dati non servono per sapere se un dato operatore
				// e' abilitato per un certo lotto...
				EspletGaraLottoType lotto = new EspletGaraLottoType(espletGaraOperatoreLotto);
				
				operatore.getLotti().add(lotto);
			}
		}

		return elenco;
	}

	/* Ottimizzazione per gestione dei lotti */
	public List<EspletGaraOperatoreType> getEspletamentoGaraOffEcoElencoOperatoriLotto(
			String codice
			, String tokenRichiedente
	) {
		return getEspletamentoGaraOperatoriLotto(
				codice
				, tokenRichiedente
				, bandiDao::getEspletamentoGaraOffEcoElencoOperatoriLotto
		);
	}
	
	public List<EspletGaraOperatoreType> getEspletamentoGaraGraduatoriaElencoOperatori(
			EspletamentoElencoOperatoriSearch search
	) {
		// recupera il singolo operatore o l'elenco degli operatori economici 
		// per la gara...
		List<EspletGaraOperatoreType> elenco =
				bandiDao.getEspletamentoGaraGraduatoriaElencoOperatori(
						search.getCodice()
						, search.getCodiceOperatore()
				);
		if (elenco != null && !elenco.isEmpty() && elenco.get(0).isAnonimo())
			deanonimizeCurrentAndRetrieveComstato(elenco, search.getUsername(), null, search.getCodice());

		return elenco;
	}
	
	/**
	 * restituisce l'elenco delle imprese ammesse/classificate per la pubblicazione degli atti
	 */
	private List<EspletGaraOperatoreType> getImpreseAmmesse(String codice, String username) {
		List<EspletGaraOperatoreType> ammesse =
				bandiDao.getEspletamentoGaraAccessoDocumentiElencoOperatori(
					codice 
					, username
					, false
			);
//		// filtra solo le prime 4 classificate dopo l'aggiudicataria
//		if(classificate != null) {
//			int n = Math.min(4, classificate.size());
//			List<EspletGaraOperatoreType> lista = new ArrayList<EspletGaraOperatoreType>();
//			for (int i = 0; i < n; i++)
//				lista.add(classificate.get(i));
//			classificate = lista;
//		}
		return ammesse;
	}
	
	/**
	 * restituisce l'elenco delle aggiudicatarie, classificate e ammesse alla pubblicazione degli atti
	 */
	public List<EspletGaraOperatoreType> getEspletamentoGaraAccessoDocumentiElencoOperatori(
			EspletamentoElencoOperatoriSearch search
	) {
		List<EspletGaraOperatoreType> elenco = new ArrayList<EspletGaraOperatoreType>();
		
		boolean isRichiedenteAggiudicataria = false;
		boolean isRichiedenteClassificata = false;
		//boolean isPrime4Classificate = false;
		boolean isRichiedenteAmmessa = false;

		// verifica se si tratta di un concorso di progettazione...
		// se e' presente il II grado i dati di pubblicazione da presentare sono quelli del secondo grado
		String codiceSecondoGrado = bandiDao.getCodiceSecondoGrado(search.getCodice());
		String codiceDocumenti = search.getCodice();
		search.setCodice( (StringUtils.isNotEmpty(codiceSecondoGrado) ? codiceSecondoGrado : search.getCodice()) );
		
		// recupera l'aggiudicatarie...
		List<EspletGaraOperatoreType> aggiudicatarie = bandiDao.getEspletamentoGaraAccessoDocumentiElencoOperatori(
				search.getCodice()
				, null
				, true
		);
		if (aggiudicatarie != null && aggiudicatarie.size() > 0) {
			if(StringUtils.isNotEmpty(search.getUsername()))
				isRichiedenteAggiudicataria = aggiudicatarie.stream()
												.anyMatch(a -> search.getUsername().equalsIgnoreCase(a.getUsernome()));  
		}

		// recupera le ditte ammesse e classificate dopo l'aggiudicataria
		List<EspletGaraOperatoreType> ammesse = getImpreseAmmesse(
				search.getCodice()
				, null
		);		
		if(ammesse != null && ammesse.size() > 0) {
			EspletGaraOperatoreType richiedente = null;
			if(StringUtils.isNotEmpty(search.getUsername())) 
				richiedente = ammesse.stream()
						.filter(i -> search.getUsername().equalsIgnoreCase(i.getUsernome()))
						.findFirst()
						.orElse(null);
			if(richiedente != null) {
				// codiceAmmissione=0 se ammessa, 1 se la ditta e' classificata
				isRichiedenteAmmessa = "0".equals(richiedente.getCodiceAmmissione());
				isRichiedenteClassificata = "1".equals(richiedente.getCodiceAmmissione());
			}
		}
		
		// se il richiedente e' l'aggiudicataria o una classificata o un'ammessa allora visualizza i dati della pubblicazione
		if(isRichiedenteAggiudicataria || isRichiedenteClassificata || isRichiedenteAmmessa) {
			// aggiungi le aggiudicatarie all'elenco delle imprese in pubblicazione...
			if (aggiudicatarie != null) {
				aggiudicatarie.stream().forEach(a -> elenco.add(a));
			}
				
			// se il richiedente e' una classificata (o l'aggiudicataria) 
			// aggiungi tutte le classificate (prime 4) all'elenco delle imprese in pubblicazione...
			if(isRichiedenteClassificata || isRichiedenteAggiudicataria) {
				ammesse.stream()
					.filter(i -> "1".equals(i.getCodiceAmmissione()))
					.forEach(i -> elenco.add(i));
			}
			
			// recupera i lotti a cui il richiedente ha partecipate ed e' ammesso
			List<String> lottiAmmessiRichiedente = null;
			if(StringUtils.isNotEmpty(search.getUsername())) {
				// NB: la stessa ditta (come mandataria) puo' partecipare con RTI diverse su lotti diversi...
				lottiAmmessiRichiedente = ammesse.stream()
					.filter(i -> search.getUsername().equalsIgnoreCase(i.getUsernome()))
					.map(r -> bandiDao.getEspletamentoGaraAccessoDocumentiLotti(search.getCodice(), r.getCodiceOperatore()))
					//.map(lotti -> lotti.forEach(l -> lottiAmmessiRichiedente.add(l.getLotto())));
					.flatMap(List::stream)					// converti List<List<Obj>> => List<Obj>
					.map(EspletGaraLottoType::getLotto)
					.collect(Collectors.toList());
			}
			
			// aggiorna i dati aggiuntivi di ogni impresa (documenti, lotti, rti)
			for(EspletGaraOperatoreType impresa : elenco) {
				List<EspletGaraAccessoDocumentiDocumentoType> documenti = bandiDao.getEspletamentoGaraAccessoDocumentiAtti(
						codiceDocumenti
						, impresa.getUsernome()
				);
				impresa.setAccessoDocumenti(documenti);

				List<EspletGaraLottoType> lotti = bandiDao.getEspletamentoGaraAccessoDocumentiLotti(
						search.getCodice()
						, impresa.getCodiceOperatore()
				);
				if(lottiAmmessiRichiedente != null && lotti != null) {
					// filtra solo i lotti a cui il richiedente e' stato ammesso
					List<EspletGaraLottoType> tmp = new ArrayList<EspletGaraLottoType>();
					for(EspletGaraLottoType lotto : lotti) 
						if(lottiAmmessiRichiedente.indexOf(lotto.getLotto()) >= 0)
							tmp.add(lotto);
					lotti = tmp;
				} 
				impresa.setLotti(lotti);
				
				if(impresa.isRti()) {
					List<EspletGaraComponenteRtiType> componentiRti = bandiDao.getEspletamentoGaraComponentiRti(
							search.getCodice() 
							, impresa.getCodiceOperatore()
					);
					impresa.setComponentiRTI(componentiRti);
				}
			}
		}
		
		return elenco;
	}

	/**
	 * restituisce i documenti della pubblicazione atti di un'impresa per una data gara 
	 */
	public List<EspletGaraAccessoDocumentiDocumentoType> getEspletamentoGaraAccessoDocumentiAtti(
			String codice
			, String tokenRichiedente
	) {
		List<EspletGaraAccessoDocumentiDocumentoType> documenti = null;  
		
		// verifica se si tratta di un concorso di progettazione...
		// se e' presente il II grado i dati di pubblicazione da presentare sono quelli del secondo grado
		String codiceSecondoGrado = bandiDao.getCodiceSecondoGrado(codice);
		String codiceDocumenti = codice;
		codice = (StringUtils.isNotEmpty(codiceSecondoGrado) ? codiceSecondoGrado : codice);
		
		// recupera l'aggiudicataria
		List<EspletGaraOperatoreType> aggiudicataria =
				bandiDao.getEspletamentoGaraAccessoDocumentiElencoOperatori(
						codice
						, null
						, true
		);
		String usernameAggiudicataria = (aggiudicataria != null && aggiudicataria.size() > 0 
				? aggiudicataria.get(0).getUsernome() 
				: null);

		// carica i documenti 
		boolean visualizza = true;
		if(StringUtils.isEmpty(tokenRichiedente)) {
			// documenti di gara
		} else {
			if(tokenRichiedente.equalsIgnoreCase(usernameAggiudicataria)) {
				// documenti dell'aggiudicataria
			} else {
				// documenti di una ammessa/classificata
				// recupera le ditte ammesse/classificate dopo l'aggiudicataria
				List<EspletGaraOperatoreType> ammesse = getImpreseAmmesse(codice, null);
				if (ammesse != null && ammesse.size() > 0) {
					// codiceAmmissione=0 se ammessa, 1 se la ditta e' classificata 
					visualizza = ammesse.stream()
							.anyMatch(i -> "1".equals(i.getCodiceAmmissione()) && tokenRichiedente.equalsIgnoreCase(i.getUsernome()));
				}
			}
		}
		
		if(visualizza) {
			// recupera i documenti associati ad un'impresa o la documentazione di gara (tokenRichiedente=null ==> documenti di gara)
			documenti = bandiDao.getEspletamentoGaraAccessoDocumentiAtti(
					codiceDocumenti					// in caso di concorso di progettazione i documenti sono sempre nel I grado 
					, tokenRichiedente  
			);
		}
		
		return documenti; 
	}
	
	public Long getFaseGara(String codice) {
		return this.bandiDao.getFaseGara(codice);
	}
	
	public List<ComunicazioneType> getNews(
			int indicePrimoRecord, int maxNumRecord, 
			String stazioneAppaltante) {
		Date dataInvioDa = DateUtils.round(
				DateUtils.addDays(new Date(), NUM_GIORNI_NEWS), Calendar.DAY_OF_MONTH);
		return this.bandiDao.getNews(dataInvioDa, null, indicePrimoRecord, maxNumRecord, stazioneAppaltante);
	}
		
	public int countNews(String stazioneAppaltante) {
		Date dataInvioDa = DateUtils.round(
				DateUtils.addDays(new Date(), NUM_GIORNI_NEWS), Calendar.DAY_OF_MONTH);
		return this.bandiDao.countNews(dataInvioDa, null, stazioneAppaltante);
	}
	
	public List<SoggettoAderenteType> getSoggettiAderenti(String codiceGara) {
		return this.bandiDao.getSoggettiAderenti(codiceGara);
	}

	public List<SoggettoAderenteLotto> getLottiSoggettiAderenti(String codiceGara) {
		return this.bandiDao.getSoggettiAderentiLotti(codiceGara);
	}

	public List<OperatoreIscrittoType> getOperatoriIscritti(String codiceGara) {
		return this.bandiDao.getOperatoriIscritti(codiceGara);
	}
	
	public List<CategoriaOperatoreIscrittoType> getElencoCategorieOperatoriIscritti(String codiceGara) {
		return this.bandiDao.getElencoCategorieOperatoriIscritti(codiceGara);
	}
	
	public List<RettificaGaraType> getRettifichePresentazioneDomanda(
			String codiceGara
			, Date dataScadenza
			, String oraScadenza
	)throws DataAccessException {
		// TIPPER=1 
		return this.bandiDao.getRettifichePresentazioneDomandaOfferta(codiceGara, 1, dataScadenza, oraScadenza);
	}
	
	public List<RettificaGaraType> getRettifichePresentazioneOfferta(
			String codiceGara
			, Date dataScadenza
			, String oraScadenza
	)throws DataAccessException {
		// TIPPER=2
		return this.bandiDao.getRettifichePresentazioneDomandaOfferta(codiceGara, 2, dataScadenza, oraScadenza);
	}
		
	private void aggiornaCriterioValutazione(
			CriterioValutazioneOffertaType criterio, 
			CriterioValutazioneOffertaType valore) {
		// Descrizione							FORMATO	  B.Tec?  B.Eco.? NUMDECI 	G1CRIREG + G1CRIDEF 	
		// (tab A1143)													  (0<=x<=5)	(n record)
		// -----------------------------------------------------------------------------------------------------------
		// Data										1		X	 	 	 
		// Importo									2		X		X		X (2)	eventuale dominio (VALMIN, VALMAX)
		// Lista valori								3		X		X	 			lista coppie (PUNTUALE, PUNTUALE)
		// Testo									4		X	 	 	 
		// Numero intero							5		X		X	 			eventuale dominio (VALMIN, VALMAX)
		// Numero decimale							6		X		X		X		eventuale dominio (VALMIN, VALMAX)
		// Offerta complessiva mediante importo		50	 			X		X		eventuale dominio (VALMIN, VALMAX) 
		// Offerta complessiva mediante ribasso		51	 			X		X		eventuale dominio (VALMIN, VALMAX)
		// Offerta complessiva mediante prz.unitari	52	 			X		X		eventuale dominio (VALMIN, VALMAX)
		// Nessuno									100		
		// -----------------------------------------------------------------------------------------------------------
		if(criterio != null && valore != null) {
			
			criterio.setValore(null);
			
			// 1 = criteri tecnici, 2 = criteri economici
			int tipo = valore.getTipo();
			
			int numDecimali = (valore.getNumeroDecimali() == null 
							   ? 1
							   : Math.min(valore.getNumeroDecimali().intValue(), 5));
			
			Double minval = criterio.getValoreMin();
			if(valore.getValoreMin() != null) {
				minval = (minval == null ? valore.getValoreMin() : minval);
				minval = Math.min(valore.getValoreMin().doubleValue(), minval.doubleValue());
			}
			
			Double maxval = criterio.getValoreMax();
			if(valore.getValoreMax() != null) {
				maxval = (maxval == null ? valore.getValoreMax() : maxval);
				maxval = Math.max(valore.getValoreMax().doubleValue(), maxval.doubleValue());
			}
			
			// utilizza "valore" per configurare il tipo di input del nuovo 
			// criterio...
			try {
				switch (valore.getFormato()) {
				case 1:
					// Data
					if(tipo == 1) {	
						criterio.setValoreMin(null);
						criterio.setValoreMax(null);
					}
					break;
				case 2:
					// Importo
					if(tipo == 1 || tipo == 2) {
						criterio.setValoreMin(minval);
						criterio.setValoreMax(maxval);
						criterio.setNumeroDecimali(numDecimali);
					}
					break;
				case 3:
					// Lista valori
					if(tipo == 1 || tipo == 2) {
						if(criterio.getListaValori() == null) {
							criterio.setListaValori( new LinkedHashMap<String, String>() );
						}
						if(valore.getValore() != null) {
							criterio.getListaValori().put(valore.getValore(), valore.getValore());
						}
						criterio.setValoreMin(null);
						criterio.setValoreMax(null);
					}
					break;
				case 4:
					// Testo
					if(tipo == 1) {
						criterio.setValoreMin(null);
						criterio.setValoreMax(null);
					}
					break;
				case 5:
					// Numero intero
					if(tipo == 1 || tipo == 2) {
						criterio.setValoreMin(minval);
						criterio.setValoreMax(maxval);
						criterio.setNumeroDecimali(0);
					}
					break;
				case 6:
					// Numero decimale
					if(tipo == 1 || tipo == 2) {
						criterio.setValoreMin(minval);
						criterio.setValoreMax(maxval);
						criterio.setNumeroDecimali(numDecimali);
					}
					break;
				case 50:
					// Offerta complessiva mediante importo
					if(tipo == 2) {
						criterio.setValoreMin(minval);
						criterio.setValoreMax(maxval);
						criterio.setNumeroDecimali(numDecimali);
					}
					break;
				case 51:
					// Offerta complessiva mediante ribasso (dominio [0, 100]);
					if(tipo == 2) {
						criterio.setValoreMin( Math.max(0.0, (minval != null ? minval : 0.0)) );
						criterio.setValoreMax( Math.min(100.0, (maxval != null ? maxval : 100.0)) );
						criterio.setNumeroDecimali(numDecimali);
					}
					break;
				case 52:
					// Offerta complessiva mediante prezzi unitari	
					if(tipo == 2) {
						criterio.setValoreMin(minval);
						criterio.setValoreMax(maxval);
						criterio.setNumeroDecimali(numDecimali);
					}
					break;
				case 100:
					// Nessuno
					break;
				}
			} catch(Exception e) {
			}
		}
	}
		
	public List<CriterioValutazioneOffertaType> getCriteriValutazioneOfferta(
			String codiceGara, 
			String tipoBusta) throws DataAccessException {
		
		List<CriterioValutazioneOffertaType> valori = this.bandiDao
			.getCriteriValutazione(codiceGara, (StringUtils.isEmpty(tipoBusta) ? null : tipoBusta));
		
		// ...riorganizza i dati in modo strutturato in base al tipo di dato...
		// (i dati sono ordinati per ngara, tippar, norpar, norpar1, puntuale) 
		List<CriterioValutazioneOffertaType> criteriValutazione = null;
		if(valori != null && valori.size() > 0) {
			criteriValutazione = new ArrayList<CriterioValutazioneOffertaType>();
			
			CriterioValutazioneOffertaType criterio = null;
			String key = "";
			for(int i = 0; i < valori.size(); i++) {
				if ( i == 0 || !key.equalsIgnoreCase(valori.get(i).getCodice()) ) {	
					// crea un nuovo criterio da aggiungere alla lista
					criterio = new CriterioValutazioneOffertaType(valori.get(i));
					criteriValutazione.add(criterio);
					key = valori.get(i).getCodice();
				}
				aggiornaCriterioValutazione(criterio, valori.get(i));
			}
		}
		
		return criteriValutazione;
	}
	
	/* Ottimizzazione getDettaglioGara() */
	public List<CriterioValutazioneOffertaLotto> getCriteriValutazioneOffertaLotti(
			String codiceGara, 
			String tipoBusta) throws DataAccessException {
		
		List<CriterioValutazioneOffertaLotto> valori = this.bandiDao
			.getCriteriValutazioneLotti(codiceGara, (StringUtils.isEmpty(tipoBusta) ? null : tipoBusta));
		
		// ...riorganizza i dati in modo strutturato in base al tipo di dato...
		// (i dati sono ordinati per ngara, tippar, norpar, norpar1, puntuale)
		List<CriterioValutazioneOffertaLotto> criteriValutazione = null;
		if(valori != null && valori.size() > 0) {
			criteriValutazione = new ArrayList<CriterioValutazioneOffertaLotto>();
			
			CriterioValutazioneOffertaLotto criterio = null;
			String key = "";
			for(int i = 0; i < valori.size(); i++) {
				if( i == 0 || !key.equalsIgnoreCase(valori.get(i).getDescrizione()) ) {
					// crea un nuovo criterio da aggiungere alla lista
					criterio = new CriterioValutazioneOffertaLotto(valori.get(i));
					criteriValutazione.add(criterio);
					key = valori.get(i).getDescrizione();
				}
				aggiornaCriterioValutazione(criterio, valori.get(i));
			}
		}
		
		return criteriValutazione;
	}

	public List<DocumentoAllegatoLotto> checkDocumentiNulli(
			final String codiceGara
			, final String username
			, final String tipoBusta
	) throws DataAccessException, SqlComposerException {
		if (obs_client.is_enabled()) {
			val allegati = bandiDao.checkDocumentiLotto(
					codiceGara
					, (StringUtils.isEmpty(username) ? null : username)
					, (StringUtils.isEmpty(tipoBusta) ? null : tipoBusta)
			);
			return allegati.stream()
				.filter(it -> !obs_client.first_chunk_size(it.getBucket_ref(), it.getBucket_uuid()).isPresent())
			.collect(Collectors.toList());
		} else {
			return bandiDao.checkDocumentiNulli(
						codiceGara
						, (StringUtils.isEmpty(username) ? null : username)
						, (StringUtils.isEmpty(tipoBusta) ? null : tipoBusta)
			);
		}
	}
		
	public List<DocumentoAllegatoLotto> checkDimensioneDocumenti(
			String codiceGara, String username, String tipoBusta) throws DataAccessException, SqlComposerException {
		if (obs_client.is_enabled()) {
			val allegati = bandiDao.checkDocumentiLotto(
					codiceGara
					, (StringUtils.isEmpty(username) ? null : username)
					, (StringUtils.isEmpty(tipoBusta) ? null : tipoBusta)
			);
			allegati.forEach(it -> {
				val chunk_size = obs_client.first_chunk_size(it.getBucket_ref(), it.getBucket_uuid());
				it.setDimensione(chunk_size.orElse(0L));
			});
			return allegati;
		} else {
			return bandiDao.checkDimensioneDocumenti(
							codiceGara
							, (StringUtils.isEmpty(username) ? null : username)
							, (StringUtils.isEmpty(tipoBusta) ? null : tipoBusta)
					);
		}
	}

	public List<DeliberaType> getDelibere(
			int annoInizio,
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			String codgara,
			Boolean sommaUrgenza,
			int indicePrimoRecord, 
			int maxNumRecord) throws DataAccessException, SqlComposerException
	{
		return this.bandiDao.getDelibere(
				annoInizio
				, stazioneAppaltante
				, oggetto
				, tipoAppalto
				, cig
				, dataPubblicazioneDa, dataPubblicazioneA
				, codgara
				, sommaUrgenza
				, indicePrimoRecord, maxNumRecord
		);		
	}

	public int countDelibere(
			int annoInizio,
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			String codgara,
			Boolean sommaUrgenza
	) throws DataAccessException, SqlComposerException
	{
		return this.bandiDao.countDelibere(
				annoInizio
				, stazioneAppaltante
				, oggetto
				, tipoAppalto
				, cig
				, dataPubblicazioneDa, dataPubblicazioneA
				, codgara
				, sommaUrgenza
		);
	}

	public DeliberaType getDettaglioDelibera(
			String codgara
	) throws DataAccessException, SqlComposerException
	{
		DeliberaType delibera = this.bandiDao.getDettaglioDelibera(codgara);
		if(delibera != null) {
			delibera.setDocumenti( this.bandiDao.getDocumentiDelibera(codgara, null, null) );
		}
		return delibera;
	}

	public List<SommaUrgenzaType> getElencoSommaUrgenza(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA,
			int indicePrimoRecord, 
			int maxNumRecord) throws DataAccessException, SqlComposerException
	{
		return this.bandiDao.getElencoSommaUrgenza(
				stazioneAppaltante, oggetto, tipoAppalto, cig, dataPubblicazioneDa, dataPubblicazioneA,
				indicePrimoRecord, maxNumRecord);
	}

	public int countElencoSommaUrgenza(
			String stazioneAppaltante,
			String oggetto, 
			String tipoAppalto, 
			String cig, 
			Date dataPubblicazioneDa, 
			Date dataPubblicazioneA) throws DataAccessException, SqlComposerException 
	{
		return this.bandiDao.countElencoSommaUrgenza(
				stazioneAppaltante, oggetto, tipoAppalto, cig, dataPubblicazioneDa, dataPubblicazioneA);
	}

	public List<WSDMConfigType> getWSDMConfig(
			String stazioneAppaltante) throws DataAccessException, SqlComposerException 
	{
		return this.bandiDao.getWSDMConfig(stazioneAppaltante);
	}
	
	public List<InvitoGaraType> getElencoInvitiGara(
			String username, String codice) throws DataAccessException, SqlComposerException 
	{
		return this.bandiDao.getElencoInvitiGara(username, codice);
	}
	
	public Long getNumeroOrdineInvito(
			String username, 
			String codice) throws DataAccessException, SqlComposerException 
	{
		return this.bandiDao.getNumeroOrdineInvito(username, codice);
	}
	
	public List<QuestionarioType> getQuestionari(
			String codice, 
			String tipologia, 
			String busta) throws DataAccessException, SqlComposerException 
	{
		return this.bandiDao.getQuestionari(codice, tipologia, busta);
	}
	
	public List<ParametroQuestionarioType> getParametriQuestionario() throws DataAccessException, SqlComposerException 
	{
		return this.bandiDao.getParametriQuestionario();
	}

	public String getRagioneSocialeAnonima(String username, String ngara) {
		return bandiDao.getRagioneSocialeAnonima(
				getFactoryCriptazioneByte(username, CIFRA)
				, ngara
		);
	}

	public boolean isConcorsoCrypted(String username, String ngara) {
		return bandiDao.isConcorsoCrypted(
				getFactoryCriptazioneByte(username, CIFRA)
				, ngara
		);
	}

	public String getPartecipantiRaggruppamento(String username, String ngara) throws XmlException {
		String result = null;

		String idAnonimo = bandiDao.getIdAnonimo(
				getFactoryCriptazioneByte(username, CIFRA)
				, ngara
		);

		if (StringUtils.isNotEmpty(idAnonimo)) {
			String decrypted = getFactoryCriptazioneByte(idAnonimo, DECIFRA);
			result = comunicazioniDao.getRichiestaPartecipazione(username, decrypted.split("#")[0]);
		}

		return result;
	}

	public String getDenominazioneConcorsoAllegato(String username, String ngara) throws XmlException {
		String result = null;

		String idAnonimo = bandiDao.getIdAnonimo(
				getFactoryCriptazioneByte(username, CIFRA)
				, ngara
		);

		if (StringUtils.isNotEmpty(idAnonimo)) {
			String decrypted = getFactoryCriptazioneByte(idAnonimo, DECIFRA);
			String xml = comunicazioniDao.getRichiestaPartecipazione(username, decrypted.split("#")[0]);


			it.eldasoft.sil.portgare.datatypes.TipoPartecipazioneType partecipazioneType = StringUtils.isNotEmpty(xml)
					? parseTipoPartecipazione(xml).getTipoPartecipazione()
					: null;
			result = partecipazioneType != null && partecipazioneType.getRti() ? partecipazioneType.getDenominazioneRti() : null;
		}

		return result;
	}

	/**
	 * La libreria xmlbean e' buggata, non si puo' referenziare il Factoru all'interno di: TipoPartecipazioneType; perche'
	 * la classe base ha un field chiamato Factory...
	 * @param xml
	 * @return
	 * @throws XmlException
	 */
	private TipoPartecipazioneDocument parseTipoPartecipazione(String xml) throws XmlException {
		return (TipoPartecipazioneDocument) XmlBeans.getContextTypeLoader().parse(xml, TipoPartecipazioneDocument.type, (XmlOptions)null);
	}

	public String getCodiceSecondoGrado(String codice) {
		return bandiDao.getCodiceSecondoGrado(codice);
	}
	
	public boolean canShowRankingFirstGradeCompetition(String ngara) {
		return bandiDao.canShowRankingFirstGradeCompetition(ngara);
	}
	
	/**
	 * restituisce l'elenco delle imprese ausiliarie relative ad un avvalimento
	 * @throws SqlComposerException 
	 * @throws DataAccessException 
	 */
	public List<ImpresaAusiliariaType> getImpreseAusiliarie(String username, String codice, String progressivoOfferta) throws DataAccessException, SqlComposerException {
		return bandiDao.getImpreseAusiliarie(username, codice, progressivoOfferta);
	}
	
}
