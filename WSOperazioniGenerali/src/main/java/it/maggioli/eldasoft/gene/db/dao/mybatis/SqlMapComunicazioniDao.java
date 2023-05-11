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
package it.maggioli.eldasoft.gene.db.dao.mybatis;

import it.maggioli.eldasoft.gene.db.dao.ComunicazioniDao;
import it.maggioli.eldasoft.gene.db.mapper.ComunicazioniMapper;
import it.maggioli.eldasoft.gene.ws.AllegatoComunicazioneType;
import it.maggioli.eldasoft.gene.ws.DettaglioComunicazioneType;
import it.maggioli.eldasoft.gene.ws.WSAllegatoType;
import it.maggioli.eldasoft.gene.ws.WSDocumentoType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * SqlMap per iBatis
 * 
 * @author Stefano.Sabbadin
 */
@Component
public class SqlMapComunicazioniDao implements ComunicazioniDao {

	private static final Logger LOG = LoggerFactory.getLogger(SqlMapComunicazioniDao.class);

	@Autowired
	private ComunicazioniMapper comunicazioniMapper;
	
	/**
	 * converti il valore di comunicazione.ComunicazionePubblica prima di un insertComunicazione o updatecomunicazione 
	 */
	private Integer convertComunicazionePubblica(Boolean value) {
		Integer comPub = null;
		if(value != null) {
			comPub = new Integer( value.booleanValue() ? 1 : 2 );
		}
		return comPub;
	}
	
	@Override
	public Long getMaxIdComunicazione(String applicativo)
			throws DataAccessException {
		Long id = comunicazioniMapper.getMaxIdComunicazione(applicativo);
		return id == null ? 0l : id;
	}
	
	@Override
	public void insertComunicazione(DettaglioComunicazioneType comunicazione)
			throws DataAccessException {
		Date dataAggStato = new Date();
		Integer comunicazionePubblica = convertComunicazionePubblica(comunicazione.isComunicazionePubblica()); 
		Date dataInserimento = dataAggStato;
		
		comunicazioniMapper.insertComunicazione(
				  comunicazione.getApplicativo()
				, comunicazione.getId()
				, comunicazione.getEntita()
				, comunicazione.getChiave1()
				, comunicazione.getChiave2()
				, comunicazione.getChiave3()
				, comunicazione.getChiave4()
				, comunicazione.getChiave5()
				, comunicazione.getIdOperatore()
				, dataInserimento
				, comunicazione.getMittente()
				, comunicazione.getStato()
				, comunicazione.getOggetto()
				, comunicazione.getTesto()
				, comunicazionePubblica
				, comunicazione.getTipoComunicazione()
				, dataAggStato
				, comunicazione.getDataPubblicazione()
				, comunicazione.getSessionKey()
				, comunicazione.getApplicativoRisposta()
				, comunicazione.getIdRisposta()
				, comunicazione.getModello()
				, comunicazione.getTipoBusta()
		);
	}
	
	@Override
	public void updateComunicazione(DettaglioComunicazioneType comunicazione)
	throws DataAccessException {
		Date dataAggStato = new Date();
		Integer comunicazionePubblica = convertComunicazionePubblica(comunicazione.isComunicazionePubblica()); 
		
		comunicazioniMapper.updateComunicazione(
				comunicazione.getEntita()
				, comunicazione.getChiave1()
				, comunicazione.getChiave2()
				, comunicazione.getChiave3()
				, comunicazione.getChiave4()
				, comunicazione.getChiave5()
				, comunicazione.getIdOperatore()
				, comunicazione.getMittente()
				, comunicazione.getStato()
				, comunicazione.getOggetto()
				, comunicazione.getTesto()
				, comunicazionePubblica
				, comunicazione.getTipoComunicazione()
				, dataAggStato
				, comunicazione.getDataPubblicazione()
				, comunicazione.getSessionKey()
				, comunicazione.getApplicativoRisposta()
				, comunicazione.getIdRisposta()
				, comunicazione.getModello()
				, comunicazione.getTipoBusta()
				, comunicazione.getApplicativo()
				, comunicazione.getId()
		);
	}

	@Override
	public Long getMaxIdAllegato(String applicativo) throws DataAccessException {
		Long id = comunicazioniMapper.getMaxIdAllegato(applicativo);
		return id == null ? 0L : id;
	}
	
	@Override
	public void insertAllegato(String applicativo, Long idAllegato,
			Long idComunicazione, int progressivoAllegato,
			AllegatoComunicazioneType allegato) throws DataAccessException {
		boolean isEmptyStream = (allegato.getFile() == null ||
   			    			     (allegato.getFile() != null && allegato.getFile().length <= 0));

		if(isEmptyStream) {
			// 21/01/2019 (bug rilevato a "Chieti")
			// Warning per insert di un allegato senza contenuto!!!
			LOG.warn(
				"Insert attachment with Null stream or no stream in W_DOGDIG_DIGOGG (" +
				"applicativo={}, " +
				"idComunicazione={}, " +
				"id={}, " +
				"nomeFile={}" +
				")", applicativo, idComunicazione, idAllegato, allegato.getNomeFile());
		} else {
			String entita = "W_INVCOM";
			comunicazioniMapper.insertAllegato(
					applicativo
					, idAllegato
					, entita
					, idComunicazione
					, progressivoAllegato
					, (!StringUtils.isEmpty(allegato.getUuid()) ? allegato.getUuid() : null)
					, allegato.getTipo()
					, allegato.getNomeFile()
					, allegato.getDescrizione()
					, allegato.getFile()
					, StringUtils.trimToNull(allegato.getFirmacheck())
					, allegato.getFirmacheckts()
			);
		}
	}
	
	@Override
	public void updateAllegato(
			String applicativo, Long idAllegato, Long idComunicazione, 
			int progressivoAllegato, AllegatoComunicazioneType allegato) 
	{
		String uuid = (!StringUtils.isEmpty(allegato.getUuid())	? allegato.getUuid() : null);
		byte[] file = null;
		if(uuid != null) {
			// 2.0.0
			// in caso di gestione degli allegati fuori dalla busta xml 
			// gestisci l'allegato solo se e' stato modificato
			if(allegato.getModificato() != 0) {
				
				// 21/01/2019 (bug rilevato a "Chieti")
				// Warning per update di un allegato senza contenuto!!!
				// Si evita di aggiornare il contenuto di un allegato nel caso
				// quest'ultimo sia marcato come "modificato" ma non sia presente 
				// il relativo contenuto.
				boolean isEmptyStream = (allegato.getFile() == null ||
						   			    (allegato.getFile() != null && allegato.getFile().length <= 0));
				
				if( !isEmptyStream ) {
					file = allegato.getFile();
				} else {
					LOG.warn(
						"Update attachment with Null stream denied in W_DOGDIG_DIGOGG (" +
						"applicativo={}, " +
						"idComunicazione={}, " +
						"id={}, " +
						"nomeFile={}" +
						")", applicativo, idComunicazione, idAllegato, allegato.getNomeFile()
					);

				}
			}
		} else {
			// 1.14.x
			// i documenti allegati sono all'interno della busta xml
			file = allegato.getFile();
		}

		String entita = "W_INVCOM";

		comunicazioniMapper.updateAllegato(
				progressivoAllegato
				, allegato.getTipo()
				, allegato.getNomeFile()
				, allegato.getDescrizione()
				, file
				, uuid
				, StringUtils.trimToNull(allegato.getFirmacheck())
				, allegato.getFirmacheckts()
				, applicativo
				, idAllegato
				, entita
				, idComunicazione
		);
	}
	
	@Override
	public void deleteAllegato(String applicativo, Long idAllegato) {
		comunicazioniMapper.deleteAllegato(applicativo, idAllegato);
	}
	
	@Override
	public void deleteWSAllegato(String applicativo, Long idAllegato) {
		comunicazioniMapper.deleteWSAllegato(applicativo, idAllegato);
	}
	
	@Override
	public void deleteAllegati(String applicativo, Long idComunicazione)
	throws DataAccessException {
		String entita = "W_INVCOM";

		comunicazioniMapper.deleteAllegati(
				applicativo
				, entita
				, String.valueOf(idComunicazione)
		);
	}
	
	@Override
	public void updateStatoComunicazioni(
			DettaglioComunicazioneType[] comunicazioni, String stato)
			throws DataAccessException {
		comunicazioniMapper.updateStatoComunicazioni(stato, comunicazioni);
	}
	
	@Override
	public void updateDataLetturaDestinatarioComunicazione(String applicativo,
			long idComunicazione, long idDestinatario) {
		Date dataLettura = new Date();
		comunicazioniMapper.updateDataLetturaDestinatarioComunicazione(
				  dataLettura
				, applicativo
				, idComunicazione
				, idDestinatario
		);
	}
	
	@Override
	public void deleteComunicazione(String applicativo, long id)
			throws DataAccessException {
		comunicazioniMapper.deleteComunicazione(applicativo, id);
	}
	
	@Override
	public DettaglioComunicazioneType getDettaglioComunicazione(
			String applicativo, long id) throws DataAccessException {
		return comunicazioniMapper.getDettaglioComunicazione(applicativo, id);
	}
	
	@Override
	public List<AllegatoComunicazioneType> getAllegatiComunicazione(
			String applicativo, 
			long id, 
			String idDocumento) throws DataAccessException {
		String entita = "W_INVCOM";

		List<AllegatoComunicazioneType> allegati =
				comunicazioniMapper.getAllegatiComunicazione(
						  applicativo
						, entita
						, Long.toString(id)
						, idDocumento
				);
		
		// se viene richiesto di caricare tutti gli allegati restituisci 
		// solo le info sugli allegati ma non il contenuto binario...
		// il contenuto binario deve essere richieste in un secondo momento
		// solo se e' necessario scaricare il file!!!
		if(StringUtils.isEmpty(idDocumento)) {
			for(int i = 0; i < allegati.size(); i++) {
				allegati.get(i).setDimensione( (allegati.get(i).getFile() != null 
											    ? allegati.get(i).getFile().length 
											    : 0) );
				
				// dalla 2.0.0 gli allegati vengono scaricati su richiesta e 
				// quindi si spediscono solo le info del file ma non il
				// contenuto!!!
				if( !StringUtils.isEmpty(allegati.get(i).getUuid()) ) {
					allegati.get(i).setFile(null);
				}
			}
		}
		
		return allegati;
	}
	
	@Override
	public List<DettaglioComunicazioneType> getElencoComunicazioni(
			DettaglioComunicazioneType criteriRicerca)
			throws DataAccessException {
		String oggetto = criteriRicerca.getOggetto();
		String testo = criteriRicerca.getTesto();
		if (oggetto != null && oggetto.length() > 0)
			criteriRicerca.setOggetto("%" + oggetto + "%");
		if (testo != null && testo.length() > 0)
			criteriRicerca.setTesto("%" + testo + "%");
		return comunicazioniMapper.getElencoComunicazioni(criteriRicerca);
	}
	
	@Override
	public Boolean isComunicazioneProcessata(String applicativo, long id)
			throws DataAccessException {
		String[] stati = new String[] { "6", "7" };
		Integer numComProcessate =
				comunicazioniMapper.isComunicazioneProcessata(
						  applicativo
						, id
						, stati
				);
		return numComProcessate == 1;
	}
	
	@Override
	public void updateProtocolloComunicazione(String applicativo, long id,
			String numeroProtocollo, Date dataProtocollo, String stato) {
		comunicazioniMapper.updateProtocolloComunicazione(
				  stato
				, numeroProtocollo
				, dataProtocollo
				, applicativo
				, id
		);
	}
	
	@Override
	public void insertDocumentoProtocollo(long idDocumento,
			WSDocumentoType documento) {
		comunicazioniMapper.insertDocumentoProtocollo(
				  idDocumento
				, documento
		);
	}
	
	@Override
	public void insertAllegatoProtocollo(long idAllegato,
			WSAllegatoType allegato) {
		comunicazioniMapper.insertAllegatoProtocollo(
				  idAllegato
				, allegato
		);
	}
	
	@Override
	public void deleteAllegatoProtocollo(String entita, String applicativo, 
			Long id) {
		comunicazioniMapper.deleteAllegatoProtocollo(
				  entita
				, applicativo
				, id
		);
	}
	
	@Override
	public void updateSessionKeyComunicazione(String applicativo, long id,
			String sessionKey, String stato) {
		comunicazioniMapper.updateSessionKeyComunicazione(
				  stato
				, sessionKey
				, applicativo
				, id
		);
	}

}
