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
package it.maggioli.eldasoft.gene.bl;

import it.maggioli.eldasoft.gene.db.dao.ComunicazioniDao;
import it.maggioli.eldasoft.gene.ws.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Manager per la gestione delle comunicazioni
 * 
 * @author Stefano.Sabbadin
 */
@Service
public class ComunicazioniManager {

	@Autowired
    private ComunicazioniDao comunicazioniDao;
    @Autowired
    private GenChiaviManager genChiaviManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void insertComunicazione(ComunicazioneType comunicazione) {
		DettaglioComunicazioneType dettaglioComunicazione = comunicazione
			.getDettaglioComunicazione();
		Long id = null;
	
		synchronized (ComunicazioniManager.class) {
		    // estrazione dell'id da utilizzare per l'inserimento
			id = this.comunicazioniDao
			    .getMaxIdComunicazione(dettaglioComunicazione
				    .getApplicativo());
		    dettaglioComunicazione.setId(id + 1);
	
		    // inserimento della testata della comunicazione
		    this.comunicazioniDao.insertComunicazione(dettaglioComunicazione);
		}
		insertAllegatiComunicazione(comunicazione);
    }

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateComunicazione(ComunicazioneType comunicazione) {
		DettaglioComunicazioneType dettaglioComunicazione = comunicazione
				.getDettaglioComunicazione();

		// aggiornamento della testata della comunicazione
		this.comunicazioniDao.updateComunicazione(dettaglioComunicazione);

		// aggiornamento degli allegati
		updateAllegatiComunicazione(comunicazione);
	}

	/**
	 * Aggiorna i documenti allegati. Si agganciano i documenti ricevuti ai
	 * corrispondenti esistenti, si inseriscono eventuali nuovi documenti, si
	 * rimuovono i documenti presenti in w_docdig ma non ricevuti tra gli
	 * allegati.
	 * 
	 * @param comunicazione
	 *            dati della comunicazione da aggiornare
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void updateAllegatiComunicazione(ComunicazioneType comunicazione) {
		DettaglioComunicazioneType dettaglioComunicazione = comunicazione.getDettaglioComunicazione();
		
		List<AllegatoComunicazioneType> allegatiEsistenti = this.comunicazioniDao
				.getAllegatiComunicazione(
						dettaglioComunicazione.getApplicativo(),
						dettaglioComunicazione.getId(),
						null);
		
		// popolamento dati di supporto
		Set<Long> idAllegatiDaRimuovere = new HashSet<>();
		Map<String, Long> hashNomeFileId = new HashMap<>();
		String key;
		for (AllegatoComunicazioneType allegato : allegatiEsistenti) {
			// elenco degli id documenti, dal quale tolgo poi quelli da aggiornare
			idAllegatiDaRimuovere.add(allegato.getId());
			// hash per reperire agevolmente l'id documento dal nome file			
			key = allegato.getNomeFile() + "|" +					
				  //BUG: con la "descrizione" se carico 2 "doc richiesti" con lo stesso nomefile il I viene sovrascritto dal II!!!
			      //(allegato.getDescrizione() != null ? allegato.getDescrizione() : ""); 	
				  (allegato.getUuid() != null ? allegato.getUuid() : "");			
			hashNomeFileId.put(key, allegato.getId());
		}
		
		if (comunicazione.getAllegato() != null) {
			Long idAllegato = null;
			int progressivo = 0;
			for (int i = 0; i < comunicazione.getAllegato().size(); i++) {
				AllegatoComunicazioneType allegato = comunicazione
						.getAllegato().get(i);
							
				key = allegato.getNomeFile() + "|" + 
					  (allegato.getUuid() != null ? allegato.getUuid() : "");

				if (hashNomeFileId.containsKey(key)) {
					// esiste l'allegato nella comunicazione da aggiornare, 
					// mi aggancio allo stesso record e lo aggiorno
					allegato.setId(hashNomeFileId.get(key));
					idAllegatiDaRimuovere.remove(allegato.getId());					
					this.comunicazioniDao.updateAllegato(
							dettaglioComunicazione.getApplicativo(),
							allegato.getId(), 
							dettaglioComunicazione.getId(), 
							++progressivo, 
							allegato);
				} else {
					// si tratta di un nuovo record da allegare, pertanto
					// procedo all'inserimento
					if (idAllegato == null) {
						idAllegato = this.comunicazioniDao
								.getMaxIdAllegato(dettaglioComunicazione
										.getApplicativo());
					}
					this.comunicazioniDao.insertAllegato(
							dettaglioComunicazione.getApplicativo(),
							++idAllegato, 
							dettaglioComunicazione.getId(),
							++progressivo, 
							allegato);
				}
			}
		}
		
		for (Iterator<Long> iterator = idAllegatiDaRimuovere.iterator(); iterator.hasNext();) {
			// tutti i record presenti in precedenza in backoffice e non aggiornati 
			// vanno a questo punto rimossi
			Long idDaRimuovere = (Long) iterator.next();
			this.comunicazioniDao.deleteAllegato(
					dettaglioComunicazione.getApplicativo(),
					idDaRimuovere);
		}
	}

    /**
     * Cicla sugli allegati di una comunicazione e procede con l'inserimento
     * degli stessi
     * 
     * @param comunicazione
     *            comunicazione dalla quale estrarre ed inserire gli allegati
     *            digitali
     */
	@Transactional(propagation = Propagation.REQUIRED)
	private void insertAllegatiComunicazione(ComunicazioneType comunicazione) {
		DettaglioComunicazioneType dettaglioComunicazione = comunicazione
			.getDettaglioComunicazione();
		if (comunicazione.getAllegato() != null) {
		    Long idAllegato = this.comunicazioniDao
			    .getMaxIdAllegato(dettaglioComunicazione.getApplicativo());
		    for (int i = 0; i < comunicazione.getAllegato().size(); i++) {
				this.comunicazioniDao.insertAllegato(
						dettaglioComunicazione.getApplicativo(), 
						++idAllegato, 
						dettaglioComunicazione.getId(), 
						(i + 1), 
						comunicazione.getAllegato().get(i));
		    }
		}
    }

	/**
	 * Aggiorna le comunicazioni in input allo stato indicato.
	 * 
	 * @param comunicazioni
	 *            comunicazioni da aggiornare
	 * @param stato
	 *            nuovo stato da assegnare
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateStatoComunicazioni(
			DettaglioComunicazioneType[] comunicazioni, String stato) {
		this.comunicazioniDao.updateStatoComunicazioni(comunicazioni, stato);
	}

	/**
	 * Aggiorna la data lettura per il destinatario della comunicazione in input.
	 * @param applicativo id applicativo
	 * @param idComunicazione id comunicazione 
	 * @param idDestinatario id destinatario
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateDataLetturaDestinatarioComunicazione(String applicativo,
			long idComunicazione, long idDestinatario) {
		this.comunicazioniDao.updateDataLetturaDestinatarioComunicazione(
				applicativo, idComunicazione, idDestinatario);
	}
	
	/**
	 * Elimina la comunicazione in input.
	 * 
     * @param applicativo
     *            id applicativo
     * @param id
     *            id comunicazione
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteComunicazione(String applicativo, long id) {
		this.comunicazioniDao.deleteComunicazione(applicativo, id);
		this.comunicazioniDao.deleteAllegati(applicativo, id);
	}

    /**
     * Estrae una comunicazione e tutti i suoi allegati a partire dalla chiave.
     * 
     * @param applicativo
     *            id applicativo
     * @param id
     *            id comunicazione
     * @param idDocumento
     *            id della busta xml o UUID del allegato allegato da scaricare
     *            
     * @return comunicazione individuata a partire dalla chiave
     */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ComunicazioneType getComunicazione(String applicativo, long id, String idDocumento) {
		DettaglioComunicazioneType dettaglio = this.comunicazioniDao
			.getDettaglioComunicazione(applicativo, id);
		List<AllegatoComunicazioneType> allegati = this.comunicazioniDao
			.getAllegatiComunicazione(applicativo, id, idDocumento);
	
		ComunicazioneType comunicazione = new ComunicazioneType();
		comunicazione.setDettaglioComunicazione(dettaglio);
		comunicazione.setAllegato(allegati);			

		return comunicazione;
    }

    /**
     * Esegue una ricerca di comunicazione in base ai criteri di filtro in input
     * 
     * @param criteriRicerca
     *            criteri di ricerca per filtrare le comunicazione
     * @return lista delle testate delle comunicazioni che rispettano i criteri
     *         di filtro
     */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<DettaglioComunicazioneType> getElencoComunicazioni(
	    DettaglioComunicazioneType criteriRicerca) {
	return this.comunicazioniDao.getElencoComunicazioni(criteriRicerca);
    }

    /**
     * Verifica se una comunicazione e' stata processata dal backoffice. Ritorna
     * utile per verificare se nel lasso temporale tra la rilettura di una
     * comunicazione e il suo salvataggio delle modifiche viene processata dal
     * backoffice, perche' in tal caso va bloccato l'aggiornamento del record.
     * 
     * @param applicativo
     *            id applicativo
     * @param id
     *            id comunicazione
     * @return true se la comunicazione e' stata processata, false altrimenti
     */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Boolean isComunicazioneProcessata(String applicativo, long id) {
	return this.comunicazioniDao.isComunicazioneProcessata(applicativo, id);
    }

	/**
	 * Aggiorna i dati di protocollazione, eventualmente variando lo stato se
	 * specificato in input, ed associando un documento se indicato.
	 * 
	 * @param applicativo
	 *            id applicativo
	 * @param id
	 *            id comunicazione
	 * @param numeroProtocollo
	 *            numero di protocollo (obbligatorio) da attribuire
	 * @param dataProtocollo
	 *            data protocollo (obbligatoria) da attribuire
	 * @param stato
	 *            eventuale nuovo stato della comunicazione, da valorizzare solo
	 *            se si intende variare
	 * @param documento
	 *            eventuali coordinate del documento da associare al protocollo,
	 *            se necessario
	 * @param allegati
	 *            eventuali allegati da associare al protocollo,
	 *            se necessario
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateProtocolloComunicazione(String applicativo, long id,
			String numeroProtocollo, Date dataProtocollo, String stato,
			WSDocumentoType documento, WSAllegatoType[] allegati) {
		long idDocumento = -1;
		this.comunicazioniDao.updateProtocolloComunicazione(applicativo, id, numeroProtocollo, dataProtocollo, stato);
		if (documento != null) {
			idDocumento = this.genChiaviManager.getNextId("WSDOCUMENTO");
			this.comunicazioniDao.insertDocumentoProtocollo(idDocumento, documento);
		}
		if (allegati != null) {
			long idAllegato = this.genChiaviManager.getNextId("WSALLEGATI", allegati.length);
			for (WSAllegatoType allegato : allegati) {
				if (idDocumento != -1) {
					allegato.setIdDocumento(idDocumento);
				}
				this.comunicazioniDao.insertAllegatoProtocollo(idAllegato++, allegato);	
			}
		}
		
	}

	/**
	 * Elimina un allegato dei dati di protocollazione.
	 * 
	 * @param entita
	 *            entita
	 * @param applicativo
	 *            id applicativo
	 * @param id
	 *            id comunicazione
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAllegatoProtocollo(String entita, String applicativo, long id) {
		this.comunicazioniDao.deleteAllegatoProtocollo(entita, applicativo, id);
	}

	/**
	 * Aggiorna la chiave di sessione per la cifratura dei dati confidenziali
	 * per la comunicazioni in input, eventualmente aggiornando anche lo stato
	 * se valorizzato.
	 * 
	 * @param applicativo
	 *            id applicativo
	 * @param id
	 *            id comunicazione
	 * @param sessionKey
	 *            chiave di cifratura di sessione
	 * @param stato
	 *            eventuale nuovo stato della comunicazione, da valorizzare solo
	 *            se si intende variare
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateSessionKeyComunicazione(String applicativo, long id,
			String sessionKey, String stato) {
		this.comunicazioniDao.updateSessionKeyComunicazione(
				applicativo, id, sessionKey, stato);
	}

}
