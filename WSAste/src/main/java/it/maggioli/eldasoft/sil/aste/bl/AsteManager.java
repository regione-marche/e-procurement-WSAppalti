package it.maggioli.eldasoft.sil.aste.bl;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.gene.bl.GenChiaviManager;
import it.maggioli.eldasoft.sil.aste.db.dao.AsteDao;
import it.maggioli.eldasoft.sil.aste.ws.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AsteManager {

	/** Codice applicazione per l'applicativo di backoffice Appalti. */
	public static final String CODICE_APPLICATIVO_APPALTI = "PG";

	/** Codice applicazione per l'applicativo di frontend portale Appalti. */
	public static final String CODICE_APPLICATIVO_PORTALE = "PA";

	/** 
	 * modalità di estrazione della classifica
	 * 		False classifica dei ribassi
	 * 		True classifica dei rialzi
	 */	 
	public static final boolean CLASSIFICA_AL_RIALZO = false;
	
	private static final Logger logger = LoggerFactory.getLogger(AsteManager.class);

	@Autowired
    private AsteDao asteDao;
	@Autowired
    private GenChiaviManager genChiaviManager;

	/** 
	 * modalita' di estrazione della classifica
	 * 		True classifica dei ribassi
	 * 		False classifica dei rialzi
	 */	 	
	private boolean getClassificaAlRialzo(String codice) {
		boolean alRialzo = false; 
//		GaraType gara = this.bandiDao.getBando(codice);
//		if(gara != null) {
//			if("1".equals(gara.getTipoAggiudicazione())) {
//				// prezzo piu basso
//				classificaAlRibasso = false;
//			} else if("2".equals(gara.getTipoAggiudicazione())) {
//				// off. economicamente + vantaggiosa
//				// ???
//			} else if("3".equals(gara.getTipoAggiudicazione())) {
//				// prezzo piu alto
//				classificaAlRibasso = false;
//			}
//		}
		return alRialzo;
	}
	
	/**
	 * Calcola la data di chiusura dell'asta in base a
	 *  data apertura, durata minima, durata massima e tempo base
	 *
	 *  @param asta l'asta per cui verificare/ricalcolare la data di chiusura
	 *  @param dataRilancio data del nuovo rilancio per cui verificare la data di chiusura 
	 *         se NULL la data di chiusura non viene ricalcolata  
	 *  @return la data di chiusura dell'asta
	 */  
    private Date calcolaDataChiusuraAsta(
    		DettaglioAstaType asta, 
    		Date dataRilancio) {
    	Date dataChiusura = null;
    	if(asta != null) {
    		try {
				long dmax = (asta.getDurataMassima() != null 
					     	? asta.getDurataMassima() : 0) * 60; 
				long dmin = (asta.getDurataMinima() != null 
						    ? asta.getDurataMinima() : 0) * 60;
				long tbase = (asta.getTempoBase() != null 
						    ? asta.getTempoBase() : 0) * 60;
				long apertura = (asta.getDataOraApertura() != null 
				         	? asta.getDataOraApertura().getTime() : 0) / 1000;
				long chiusura = (asta.getDataOraChiusura() != null 
		        			? asta.getDataOraChiusura().getTime() : 0) / 1000;
				
				// chiusura è in secondi...
			    chiusura = chiusura - apertura;
			    
			    // per una nuovo rilancio verifica se posticipare la data di chiusura... 
				if(dataRilancio != null) {
					long dta = (dataRilancio.getTime() / 1000) - apertura;
					if( dta > chiusura - tbase ) {
						chiusura = dta + tbase;
					}
				}
				
				chiusura = Math.min(dmax, Math.max(dmin, chiusura));
				
				dataChiusura = new Date((apertura + chiusura) * 1000);
    		} catch(Exception e) {
    		}
    	}
    	return dataChiusura;
    }
        
    /**
     * Restituisce il tipo di classifica dell'asta 
     * 
     *  @param tipoClassifica tipo di classifica 
     *  @param codice codice della gara per gare a lotto unico
     *  @param codiceLotto codice del lotto per gare a plico unico con offerte distinte
     *  @param username utente loggato
     *  @param numeroFase fase dell'asta
     *        
     *  @return lista di DettaglioClassificaType
     *    
     *  @throws SqlComposerException 
     */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<DettaglioClassificaType> getClassifica(
    		int tipoClassifica, 
    		String codice, 
    		String codiceLotto,
    		String username, 
    		String numeroFase) throws SqlComposerException {
    	
    	List<DettaglioClassificaType> classifica = null;
    	try {
    		List<DettaglioClassificaType> classificaCompleta = null;
    		
    		String mandataria = this.asteDao.getMandatariaRti(codice, codiceLotto, username);
    		
    		int ribcal = this.asteDao.getTipoRilancioAsta(codice);
    		
    		boolean classificaAlRialzo = getClassificaAlRialzo(codice);
    		
	    	if(tipoClassifica == WSAsteSoapImpl.TIPOCLASSIFICA_SOLOPROPRIA) {
	    		// leggi la lista dei rilanci associata all'asta...
	    		classificaCompleta = this.asteDao.getClassifica(
							tipoClassifica, 
							codice,
							codiceLotto,
							null, 
							numeroFase,
							classificaAlRialzo);
	    		
	    		// ...aggiorna l'elenco da restituire...
	    		classifica = new ArrayList<DettaglioClassificaType>();
	    		for(int i = 0; i < classificaCompleta.size(); i++) {
	    			classificaCompleta.get(i).setPosizione(i + 1);
	    			
	    			boolean isUserLoggato = (
		    			(mandataria == null && username.equalsIgnoreCase(classificaCompleta.get(i).getUsername())) ||
		    			(mandataria != null && mandataria.equalsIgnoreCase(classificaCompleta.get(i).getMandataria()))
	    			);
	    			if(isUserLoggato) {
	    				DettaglioClassificaType posizione = 
	    					new DettaglioClassificaType(classificaCompleta.get(i));
	    				posizione.setImporto(null);
						classifica.add(posizione);
	    				break;
	    			}
	    		}
			} else if(tipoClassifica == WSAsteSoapImpl.TIPOCLASSIFICA_PROPRIAEMIGLIOREOFFERTO) {
				// leggi la lista dei rilanci associata all'asta...
				classificaCompleta = this.asteDao.getClassifica(
							tipoClassifica, 
							codice, 
							codiceLotto,
							null, 
							numeroFase,
							classificaAlRialzo);
							
				// ...aggiorna l'elenco da restituire...
				DettaglioClassificaType propria = null;
				DettaglioClassificaType migliore = null;
				for(int i = 0; i < classificaCompleta.size(); i++) {
					classificaCompleta.get(i).setPosizione(i + 1);
					
					// trova l'offerta migliore dell'asta...
					// verifica se l'offerta corrente è migliore dell'ultima trovata... 
					if(migliore == null) {
						migliore = classificaCompleta.get(i);
					}	
					boolean offertaMigliore = false;
					if(ribcal == 1) {
						// offerta con ribasso
						offertaMigliore = Math.abs(classificaCompleta.get(i).getImporto()) > Math.abs(migliore.getImporto()); 
					} else if(ribcal == 2) {
						// offerta con importo
						offertaMigliore = classificaCompleta.get(i).getImporto() < migliore.getImporto();						
					}
					if(offertaMigliore) {
						migliore = classificaCompleta.get(i);
					}
					
					// trova la posizione dell'utente...
					boolean isUserLoggato = (
			    		(mandataria == null && username.equalsIgnoreCase(classificaCompleta.get(i).getUsername())) ||
			    		(mandataria != null && mandataria.equalsIgnoreCase(classificaCompleta.get(i).getMandataria()))
		    		);
					if(isUserLoggato) {
						if(propria == null) {
							propria = classificaCompleta.get(i);
						}
						if(classificaCompleta.get(i).getPosizione() < propria.getPosizione() ) {
							propria = classificaCompleta.get(i);
						}
					}
				}
				
				classifica = new ArrayList<DettaglioClassificaType>();
				classifica.add(propria);	// [0] contiene la posizione dell'utente
				classifica.add(migliore);	// [1] contiene la migliore offerta in assoluto
				
			} else if(tipoClassifica == WSAsteSoapImpl.TIPOCLASSIFICA_GENERALE) {
				// leggi la lista dei rilanci associata all'asta...
				classificaCompleta = this.asteDao.getClassifica(
						tipoClassifica,
						codice,
						codiceLotto,
						null, 
						numeroFase,
						classificaAlRialzo);
				
				// ...aggiorna l'elenco da restituire...
				classifica = new ArrayList<DettaglioClassificaType>();
				for(int i = 0; i < classificaCompleta.size(); i++) {
					classificaCompleta.get(i).setPosizione(i + 1);
					
					DettaglioClassificaType posizione = 
    					new DettaglioClassificaType(classificaCompleta.get(i));
					
					boolean isUserLoggato = (
		    			(mandataria == null && username.equalsIgnoreCase(classificaCompleta.get(i).getUsername())) ||
		    			(mandataria != null && mandataria.equalsIgnoreCase(classificaCompleta.get(i).getMandataria()))
			    	);
					if(!isUserLoggato) {
						// oscura gli utenti diversi dall'utente loggato...
						posizione.setUsername("*******");
						posizione.setMandataria("*******");
						posizione.setRagioneSociale("*******");
					}
					classifica.add(posizione);
				}
			}
		} catch(Exception e) {
    	}		
		return classifica;
    }    
 
    /**
     * Restituisce l'elenco delle fasi d'asta 
     * 
     * @param codice codice/codicelotto della gara/asta
     *         
     * @return List<DettaglioAstaType>
     *   
     * @throws SqlComposerException 
     */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<DettaglioFaseAstaType> getFasiAsta(
    		String codice) throws SqlComposerException {
    	
    	return (List<DettaglioFaseAstaType>) this.asteDao
    		.getFasiAsta(codice);  
    }

    /**
     * Restituisce il dettaglio dell'asta.
     * Se username è valorizzato viene restituito il dettaglio dell'ultimo 
     * rilancio dell'utente, altrimenti l'ultimo rilancio invioato per l'asta. 
     * 
     * @param codice codice/codicelotto della gara per gare a lotto unico    
     *         
     * @return DettaglioAstaType
     *   
     * @throws SqlComposerException 
     */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public DettaglioAstaType getDettaglioAsta(
    		String codice,
    		String username) throws SqlComposerException {
    	
    	DettaglioAstaType asta = (DettaglioAstaType) this.asteDao
    		.getDettaglioAsta(codice);  
    	
    	if(asta != null) {
			// recupera le fasi dell'asta...
			List<DettaglioFaseAstaType> fasi = this.asteDao.getFasiAsta(codice);
    		asta.setFasi(fasi);
    		
    		// imposta la data di chiusura e il termine dell'asta...
    		try {
	    		Date dta = calcolaDataChiusuraAsta(asta, null);
	    		if((asta.getDataOraChiusura() == null) ||
	    		   (dta != null && asta.getDataOraChiusura() != null && 
	    		    dta.compareTo(asta.getDataOraChiusura()) != 0)) {
	    			asta.setDataOraChiusura(dta);
	    			this.asteDao.updateAsta(codice, codice, asta);
	    		}
				asta.setTermineAsta(this.getTipoTerminataAsta(asta));
    		} catch(Exception e) {
    		}
    		
    		// imposta l'ultimo rilancio effettuato...
    		// l'elenco è sempre ordinato per AERILANCI.ID, 
    		// quindi l'ultimo nella lista è l'ultimo rilancio effettuato...
    		List<DettaglioRilancioType> rilanci = this.asteDao
    			.getElencoRilanci(codice, codice, username, null);
    		if(rilanci != null && rilanci.size() > 0) {
	    		asta.setDataUltimoRilancio(rilanci.get(rilanci.size() - 1).getDataRilancio());
	    		asta.setImportoUltimoRilancio(rilanci.get(rilanci.size() - 1).getImporto());
	    		asta.setRibassoUltimoRilancio(rilanci.get(rilanci.size() - 1).getRibasso());
    		}
    		
			// imposta lo stato dell'asta...
			Date now = new Date();
			Date d1 = (asta.getDataOraApertura() != null ? asta.getDataOraApertura() : null);
			Date d2 = (asta.getDataOraChiusura() != null ? asta.getDataOraChiusura() : null);
			boolean attiva = (
				d1 != null && d1.compareTo(now) <= 0 && 
				(d2 == null || (d2 != null && now.compareTo(d2) < 0)) 
			); 
			if(fasi != null && fasi.size() > 0) {
				attiva = false;
				for(int i = 0; i < fasi.size(); i++) {
					d1 = (fasi.get(i).getDataOraApertura() != null ? fasi.get(i).getDataOraApertura() : null);
					d2 = (fasi.get(i).getDataOraChiusura() != null ? fasi.get(i).getDataOraChiusura() : null);
					if(d1.compareTo(now) <= 0 && (d2 == null || (d2 != null && now.compareTo(d2) <= 0))) {
						attiva = true;
					}
				}
			}
			asta.setAttiva(attiva);
    	}
    	
    	return asta;
    }

    /**
     * Restituisce l'elenco dei rilanci di un'asta per un dato utente  
     * 
     * @param codice codice della gara per gare a lotto unico
     * @param codiceLotto codice del lotto per gare a plico unico con offerte distinte
	 * @param username username dell'utente che invia il rilancio
	 * @param numero della fase d'asta
	 * 
	 * @return l'id record del rilancio inviato
	 *  
     * @throws SqlComposerException 
     */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<DettaglioRilancioType> getElencoRilanci(
    		String codice, 
    		String codiceLotto, 
    		String username,
    		String numeroFase) throws SqlComposerException {
    	return this.asteDao.getElencoRilanci(codice, codiceLotto, username, numeroFase);
    }
        
    /**
     * Valuta se il primo rilancio è migliore del secondo rilancio
     * 
	 * @param rilancio1 rilancio da valutare come migliore 
	 * @param rilancio2 rilancio di confronto
	 */
    private boolean isRilancioMigliore(Double rilancio1, Double rilancio2, boolean classificaAlRialzo) {
    	if(rilancio1 == null || rilancio2 == null) {
    		return false;
    	}
    	if( !classificaAlRialzo ) {
    		return (rilancio1.doubleValue() < rilancio2.doubleValue());
        } else 	{
        	return (rilancio1.doubleValue() > rilancio2.doubleValue());
        }
    }
    	
    /**
     * Invia una richiesta di rilancio per un'asta 
     * 
     * @param codice codice della gara per gare a lotto unico         
     * @param codiceLotto codice del lotto per gare a plico unico con offerte distinte
	 * @param username username dell'utente che invia il rilancio
	 * @param importoRibasso importo/ribasso del rilancio
	 * 
	 * @return l'id record del rilancio inviato
	 *  
     * @throws Exception 
     * @throws SqlComposerException 
     */
	@Transactional(propagation = Propagation.REQUIRED)
	public Long insertRilancioAsta(
			String codice, 
			String codiceLotto, 
			String username, 
			Double offerta, 
			List<VoceDettaglioAstaType> prezziUnitari) throws Exception 
	{
		Long id = null;
		try {
			boolean continua = true;

			String codiceGara = (!StringUtils.isEmpty(codiceLotto) ? codiceLotto : codice);
			
			// recupera i dettagli dell'asta...
			DettaglioAstaType asta = this.asteDao.getDettaglioAsta(codiceGara);
			
			if(asta == null) {
				continua = false;
				//throw new Exception("Dettaglio asta non trovato.");
			} 
				
			// verifica se l'asta e' attiva ed il rilancio e' nei termini dell'asta...
			Date dataInserimento = Calendar.getInstance().getTime();;
			Date dataChiusura = null;
			if(continua) {
				dataChiusura = calcolaDataChiusuraAsta(asta, dataInserimento);
				if(dataInserimento.compareTo(asta.getDataOraApertura()) < 0 || dataInserimento.compareTo(dataChiusura) > 0) {
					continua = false;
					//throw new Exception("L'asta non è più attiva.");
				}
			}
			
			if(continua) {
				// inserisci il nuovo rilancio per l'asta...
				// (...e blocca l'eventuale concorrenza su W_GENCHIAVI...)
				Long idRilancio = new Long( this.genChiaviManager.getNextId("AERILANCI") );
				
				// in caso di prezzi unitari, ricalcola il totale
				// e ricrea una nuova lista di prezzi senza le voci 
				// "soloSicurezza" e "nonSoggettoRibasso"
				// che non vanno inserite in AERILPRE
				if(prezziUnitari != null && prezziUnitari.size() > 0) {
					// calcola il totale prezzi unitari...
					double segno = Math.signum(offerta);
					
					if(asta.getTipoOfferta() == WSAsteSoapImpl.TIPO_OFFERTA_IMPORTI) {
						// se e' negativo, rendilo positivo
						offerta = segno * offerta;
					}
				}
				
				// calcola la posizione del nuovo rilancio nella classifica...
				boolean classificaAlRialzo = getClassificaAlRialzo(codice);
				
				List<DettaglioClassificaType> classifica = this.asteDao
					.getClassifica(asta.getTipoClassifica(), 
								   codice,
								   codiceLotto,
								   null,
								   asta.getFase().toString(),
								   classificaAlRialzo);
				
				int newPosizione = classifica.size() + 1;
				int oldPosizione = -1;
				for(int i = 0; i < classifica.size(); i++) {
					// le eventuali posizioni NULL sono da considerare 
					// come ultime in classifica...
					int pos = (classifica.get(i).getPosizione() != null 
							   ? classifica.get(i).getPosizione() 
							   : classifica.size() + 1);
					
					// trova la posizione corrente dell'utente in classifica... 
					if(username.equalsIgnoreCase(classifica.get(i).getUsername())) {
						if(oldPosizione < 0 || pos < oldPosizione) {
							oldPosizione = pos;
						}
					}
					
					// trova la posizione del nuovo rilancio...
					if(isRilancioMigliore(offerta, classifica.get(i).getImporto(), classificaAlRialzo)) {
						newPosizione = Math.min(pos, newPosizione);
					}
				}

				// verifica se un'impresa/consorzio partecipa come rti...
				// e recupera la mandataria...
				String mandataria = this.asteDao.getMandatariaRti(codice, codiceLotto, username);
				
				// e' sempre possibile rilanciare, anche sulla propria posizione!
				// Il rilancio viene inserito se e' migliore del precedente...
				DettaglioRilancioType rilancio = null;
//				if(newPosizione > 0 && newPosizione <= oldPosizione) {
				if(newPosizione > 0) {
					
					// calcola il "numero rilancio" massimo per la ditta...
					String ditta = null;
					int numRilancioMax = 0;
					List<DettaglioRilancioType> rilanci = this.asteDao
							.getElencoRilanci(codice, codiceLotto, username, null);
					for(int i = 0; i < rilanci.size(); i++) {
						int numril = -1;
						if(rilanci.get(i).getNumeroRilancio() != null) { 
							numril = rilanci.get(i).getNumeroRilancio();
						}
						numRilancioMax = Math.max(numRilancioMax, numril);
						ditta = rilanci.get(i).getDitta();
					}
					
					// inserisci il nuovo rilancio...
					rilancio = new DettaglioRilancioType();
					rilancio.setId(idRilancio);
					rilancio.setCodiceGara((codiceLotto != null && !codiceLotto.isEmpty() ? codiceLotto : codice ));
					rilancio.setNumeroFase(asta.getFase());
					if(StringUtils.isNotEmpty(mandataria)) {
						rilancio.setDitta(mandataria);
					} else {
						rilancio.setUsername(username);
					}
					rilancio.setRibasso(asta.getTipoOfferta() == WSAsteSoapImpl.TIPO_OFFERTA_RIBASSI ? offerta : null);
					rilancio.setImporto(asta.getTipoOfferta() == WSAsteSoapImpl.TIPO_OFFERTA_IMPORTI ? offerta : null);					
//					if(asta.getTipoOfferta() == WSAsteSoapImpl.TIPO_OFFERTA_RIBASSI
//					   && (prezziUnitari != null && prezziUnitari.size() > 0)) 
//					{
//						rilancio.setImporto(offerta);	???
//					}
					rilancio.setNumeroRilancio(numRilancioMax + 1);
					rilancio.setDataRilancio(dataInserimento);
					this.asteDao.insertRilancioAsta(codice, codiceLotto, rilancio);
					
					// inserisci i prezzi unitari associati al rilancio...
					// inserisci solo le "voci soggette a ribasso"
					if(prezziUnitari != null && prezziUnitari.size() > 0 && idRilancio > 0) {
						
						// calcola il numero di righe da inserire (solo le voci soggette a ribasso) 
						int count = 0;
						for(int i = 0; i < prezziUnitari.size(); i++) {
							if( !prezziUnitari.get(i).isNonSoggettoRibasso() ) {
								// voci soggette a ribasso
								count++; 
							}
						}
						
						Long idVoce = new Long( genChiaviManager.getNextId("AERILPRE", count) );
						for(int i = 0; i < prezziUnitari.size(); i++) {
							if( !prezziUnitari.get(i).isNonSoggettoRibasso() ) {
								// voci soggette a ribasso
								prezziUnitari.get(i).setAsteId(idVoce + i);
								prezziUnitari.get(i).setAsteCodice(codiceGara);
								prezziUnitari.get(i).setAsteIdLavorazione(new Long(i + 1));
								prezziUnitari.get(i).setAsteDitta(ditta);
							}
						}
						
						// inserisci solo le voci soggette a ribasso, collegate al rilancio
						this.asteDao.insertPrezziUnitari(idRilancio, prezziUnitari); 
					}
				} else {
					// nessun nuovo rilancio inserito
					// (Es: un utente rilancia quando e' già il miglior offerente)
					throw new NessunRilancioInseritoException("Nessun nuovo rilancio inserito.");
				}
				
				// il rilancio e' andato a buon fine...
				// ...aggiorna lo stato dell'asta...
				asta.setDataOraChiusura(dataChiusura);
				asta.setTermineAsta(this.getTipoTerminataAsta(asta));
				if(asta.getTermineAsta() != WSAsteSoapImpl.TERMINEASTA_NESSUNO) {
					// asta conclusa...
				}  
				if(rilancio != null) {
					asta.setDataUltimoRilancio(rilancio.getDataRilancio());	
					asta.setImportoUltimoRilancio(rilancio.getImporto());
					asta.setRibassoUltimoRilancio(rilancio.getRibasso());
				}
				this.asteDao.updateAsta(codice, codiceLotto, asta);
				
				// ...e restituisci l'id del rilancio inserito
				id = idRilancio;
			}
		} catch(Exception e) {
			// rilancio fallito!!!
			throw e;
		}
		return id;
	}
	
	public class NessunRilancioInseritoException extends Exception {
		public NessunRilancioInseritoException(String message) {
			super(message);
		} 
	}
	
	/**
     * Verifica se un'asta è terminata ed in che modo
     * 
     * @param asta dettaglio dell'asta
	 * 
	 * @return 0 se l'asta non è terminata 
	 * 		   1 asta terminata per assenza di rilanci
	 *         2 asta terminata per mancanza di rilanci dopo l'ultimo
	 *         3 asta terminata per superamento della durata massima
	 *         
     * @throws Exception 
     * @throws SqlComposerException 
     */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getTipoTerminataAsta(
			DettaglioAstaType asta) throws Exception {
		int result = WSAsteSoapImpl.TERMINEASTA_NESSUNO;
		try {
			if(asta != null) {
				int dmin = 0;
				if(asta.getDurataMinima() != null) {
					dmin = asta.getDurataMinima().intValue();
				}
				
				int dmax = 0;
				if(asta.getDurataMassima() != null) {
					dmax = asta.getDurataMassima().intValue();
				}
			
				Date dataDurataMin = null;
				Date dataDurataMax = null;
				if(asta.getDataOraApertura() != null) {
					dataDurataMin = DateUtils.addMinutes(asta.getDataOraApertura(), dmin);
					dataDurataMax = DateUtils.addMinutes(asta.getDataOraApertura(), dmax);
				}
				
				Date dataChiusura = asta.getDataOraChiusura();
				Date d = calcolaDataChiusuraAsta(asta, null);
				if((dataChiusura == null) 
				   || (dataChiusura != null && dataChiusura.compareTo(d) < 0)) {
					dataChiusura = d;
				}
	
				Date dataUltimoRilancio = asta.getDataUltimoRilancio();
				Double importoUltimoRilancio = asta.getImportoUltimoRilancio();
			
				// calcola il tipo di chiusura dell'asta...
				if(dataChiusura.compareTo(new Date()) < 0) {
					if(dataDurataMax.compareTo(dataChiusura) <= 0) {
						result = WSAsteSoapImpl.TERMINEASTA_DURATAMASSIMA;
					} else if(dataChiusura != null) {
						if(dataUltimoRilancio == null || importoUltimoRilancio == null) {
							result = WSAsteSoapImpl.TERMINEASTA_ASSENZARILANCI;
						} else if(dataUltimoRilancio != null && importoUltimoRilancio != 0) {
							result = WSAsteSoapImpl.TERMINEASTA_SEGUEULTIMORILANCIO;
						}
					}
				}
			}
		} catch(Exception e) {
			throw e;
		}	
		return result;
	}

	/**
	 * Recupera l'elenco dei prezzi unitari associati al primo rilancio vuoto
	 * dell'asta 
	 * Il primo rilancio d'asta vuoto, viene preparato dal backoffice
	 * 
	 * @throws SqlComposerException
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<VoceDettaglioAstaType> getPrezziUnitariPrimoRilancio(
			String codice, 
			String codiceLotto, 
			String username) throws SqlComposerException {
		return (List<VoceDettaglioAstaType>)this.asteDao
			.getPrezziUnitariPrimoRilancio(codice, codiceLotto, username);
	}
	
	/**
     * Recupera l'elenco dei prezzi unitari associati all'offerta d'asta
     *       
	 * @throws SqlComposerException 
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<VoceDettaglioAstaType> getPrezziUnitariRilancio(
			String codice, 
			String codiceLotto, 
			String username,
			Long idRilancio) throws SqlComposerException {
		return (List<VoceDettaglioAstaType>)this.asteDao
			.getPrezziUnitari(codice, codiceLotto, username, idRilancio); 
	}
	
	/**
     * Inserisce l'elenco dei prezzi unitari associati all'offerta d'asta
     *       
	 * @throws SqlComposerException 
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertPrezziUnitariRilancio(
			Long idRilancio,
			List<VoceDettaglioAstaType> prezziUnitari) throws SqlComposerException {
		this.asteDao.insertPrezziUnitari(idRilancio, prezziUnitari);
	}
	
	/**
     * Aggiorna l'elenco dei prezzi unitari associati all'offerta d'asta
     *       
	 * @throws SqlComposerException 
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updatePrezziUnitariRilancio(
			Long idRilancio,
			List<VoceDettaglioAstaType> prezziUnitari) throws SqlComposerException {
		this.asteDao.updatePrezziUnitari(idRilancio, prezziUnitari);
	}

	/**
	 * Restituisce il totale non soggetto a ribasso di una gara
	 * 
	 * @param codice codice della gara 
	 *  
	 * @return totale non soggetto della gara composto da
	 * 			importo sicurezza, importo non soggetto a ribasso e oneri di progettazione 
	 */
	public double getTotaleNonSoggettoPrezziUnitari(
			String codice) throws SqlComposerException {
		return this.asteDao.getTotaleNonSoggettoPrezziUnitari(codice);
	}

}
