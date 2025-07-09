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

import it.maggioli.eldasoft.gene.bean.rest.ObjectStorageClient;
import it.maggioli.eldasoft.gene.db.dao.ComunicazioniDao;
import it.maggioli.eldasoft.gene.util.Pair;
import it.maggioli.eldasoft.gene.util.storage.StorageUploaderBuilder;
import it.maggioli.eldasoft.gene.ws.*;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static it.maggioli.eldasoft.gene.util.storage.DeleteBuilder.delete_file;
import static it.maggioli.eldasoft.gene.util.storage.InsertBuilder.insert_file;
import static it.maggioli.eldasoft.gene.util.storage.UpdateBuilder.update_file;

/**
 * Manager per la gestione delle comunicazioni
 * 
 * @author Stefano.Sabbadin
 */
@RequiredArgsConstructor
@Service
public class ComunicazioniManager {

    private final ComunicazioniDao comunicazioniDao;
    private final GenChiaviManager genChiaviManager;
	private final ObjectStorageClient obs_client;

	@Transactional(propagation = Propagation.REQUIRED)
	public void insertComunicazione(ComunicazioneType comunicazione) {
		DettaglioComunicazioneType dettaglioComunicazione = comunicazione
			.getDettaglioComunicazione();
		Long id = null;
	
		synchronized (ComunicazioniManager.class) {
		    // estrazione dell'id da utilizzare per l'inserimento
			id = genChiaviManager.getNextId("W_INVCOM_PA");
		    dettaglioComunicazione.setId(id);
	
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
	private void updateAllegatiComunicazione(final ComunicazioneType comunicazione) {
		val dettaglioComunicazione = comunicazione.getDettaglioComunicazione();
		
		val allegatiEsistenti =
				obs_client.is_enabled()
					? comunicazioniDao.getAllegatiComunicazioneNoFile(dettaglioComunicazione.getApplicativo(), dettaglioComunicazione.getId(), null)
					: comunicazioniDao.getAllegatiComunicazione(dettaglioComunicazione.getApplicativo(), dettaglioComunicazione.getId(), null)
				;

		// popolamento dati di supporto
		Map<String, Long> hashNomeFileId = new HashMap<>();

		val allegati_da_rimuovere = new HashMap<Long, AllegatoComunicazioneType>();
		allegati_da_rimuovere.putAll(
			allegatiEsistenti.stream()
					.map(attachment -> {
						// hash per reperire agevolmente l'id documento dal nome file
						val key = attachment.getNomeFile() + "|" +
								//BUG: con la "descrizione" se carico 2 "doc richiesti" con lo stesso nomefile il I viene sovrascritto dal II!!!
								//(allegato.getDescrizione() != null ? allegato.getDescrizione() : "");
								(attachment.getUuid() != null ? attachment.getUuid() : "");
						hashNomeFileId.put(key, attachment.getId());
						return Pair.of(attachment.getId(), attachment);
					}).collect(Collectors.toMap(Pair::left, Pair::right))
		);

		if (comunicazione.getAllegato() != null) {
			// Allegto + progressivo (per mantenere ordinamento precedente)
			val inserts = new ArrayList<Pair<AllegatoComunicazioneType, Integer>>(comunicazione.getAllegato().size());
			val updates = new ArrayList<Pair<AllegatoComunicazioneType, Integer>>(comunicazione.getAllegato().size());
			for (int index = 0; index < comunicazione.getAllegato().size(); index++) {
				val progressivo = index+1;	// Prima era una variabile calcolata così...
				val allegato = comunicazione.getAllegato().get(index);
							
				val key = allegato.getNomeFile() + "|" +
					  (allegato.getUuid() != null ? allegato.getUuid() : "");

				if (hashNomeFileId.containsKey(key)) {
					// esiste l'allegato nella comunicazione da aggiornare, 
					// mi aggancio allo stesso record e lo aggiorno
					allegato.setId(hashNomeFileId.get(key));
					val db_attachment = allegati_da_rimuovere.remove(allegato.getId());
					allegato.setBucket_ref(db_attachment.getBucket_ref());
					allegato.setBucket_uuid(db_attachment.getBucket_uuid());
					updates.add(Pair.of(allegato, progressivo));
				} else {
					// si tratta di un nuovo record da allegare, pertanto
					// procedo all'inserimento
					inserts.add(Pair.of(allegato, progressivo));
				}
			}

			val deletes = new ArrayList<>(allegati_da_rimuovere.values());



			// Potrei farlo all'interno del builder, ma, preferisco non modificare ulteriormente la logica
			val id_allegato =
					!inserts.isEmpty()
						? genChiaviManager.getNextId("W_DOCDIG_PA", inserts.size())
						: -1;

			StorageUploaderBuilder.create(obs_client)
				.operation(
					insert_file(
						(allegato_curr, info, index) -> comunicazioniDao.insertAllegato(
								dettaglioComunicazione.getApplicativo()
								, id_allegato+index.longValue()
								, dettaglioComunicazione.getId()
								, inserts.get(index).right()
								, allegato_curr
								, info
						)
					).attachments(
						inserts.stream()
							.map(Pair::left)
						.collect(Collectors.toList())
					)
				).operation(
					update_file(
						(allegato, info, index) -> comunicazioniDao.updateAllegato(
								dettaglioComunicazione.getApplicativo()
								,allegato.getId()
								, dettaglioComunicazione.getId()
								, updates.get(index).right()
								, allegato
								, info
						)
					).attachments(
						updates.stream()
							.map(Pair::left)
						.collect(Collectors.toList())
					)
				).operation(
					delete_file(
						allegato -> comunicazioniDao.deleteAllegato(
								dettaglioComunicazione.getApplicativo()
								, allegato.getId()
						)
					).attachments(deletes)
				)
			.start();
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
		if (comunicazione.getAllegato() != null && comunicazione.getAllegato().size() > 0) {
			final long idAllegato = genChiaviManager.getNextId("W_DOCDIG_PA", comunicazione.getAllegato().size());
			StorageUploaderBuilder.create(obs_client)
				.operation(
					insert_file(
						(allegato, info, index) ->
							comunicazioniDao.insertAllegato(
									dettaglioComunicazione.getApplicativo()
									, idAllegato+index
									, dettaglioComunicazione.getId()
									, (index + 1)
									, allegato
									, info
							)
					).attachments(comunicazione.getAllegato())
				)
			.start();
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
				applicativo, idComunicazione, idDestinatario
		);
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
		// Concettualmente è sbagliato cancellare la comunicazione prima degli allegati...
		this.comunicazioniDao.deleteComunicazione(applicativo, id);
		val attachments = comunicazioniDao.getAllegatiComunicazioneNoFile(
				applicativo, id, null
		);
		StorageUploaderBuilder.create(obs_client)
			.operation(
				delete_file(
					allegato -> comunicazioniDao.deleteAllegato(
							applicativo
							, allegato.getId()
					)
				).attachments(attachments)
			)
		.start();
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
		val dettaglio = comunicazioniDao.getDettaglioComunicazione(applicativo, id);
		val allegati = comunicazioniDao.getAllegatiComunicazione(applicativo, id, idDocumento);

		if (!allegati.isEmpty())
			allegati
					.stream()
					.filter(attachment -> StringUtils.isNotEmpty(attachment.getBucket_uuid()))
					.forEach(attachment -> {
						// uuid è il discriminante x capire se è un xml generato da portale (nullo = generato)
						// Scarico sia la busta che il documento richiesto
						if (StringUtils.isEmpty(attachment.getUuid()) || StringUtils.equals(attachment.getUuid(), idDocumento)) {
							val content = obs_client.download(attachment.getBucket_ref(), attachment.getBucket_uuid());
							attachment.setFile(content);
							attachment.setDimensione(content.length);
						} else {	// Documento inserito dall'utente
							// Non si effettua il download perchè il download dei file dell'utente verrà fatto direttamente
							// da l'utente stesso quando richiesto
							attachment.setDimensione(
									obs_client.first_chunk_size(
											attachment.getBucket_ref()
											, attachment.getBucket_uuid()
									).orElse(0L)
							);
						}
					});

		val comunicazione = new ComunicazioneType();
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
	    DettaglioComunicazioneType criteriRicerca
	) {
		return this.comunicazioniDao.getElencoComunicazioni(criteriRicerca);
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
	public List<DettaglioComunicazioneType> searchElencoComunicazioni(
	    DettaglioComunicazioneType criteriRicerca
	) {
		return this.comunicazioniDao.searchElencoComunicazioni(criteriRicerca);
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
