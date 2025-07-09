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

import it.maggioli.eldasoft.gene.bean.ObjectStorageConfiguration;
import it.maggioli.eldasoft.gene.db.dao.ComunicazioniDao;
import it.maggioli.eldasoft.gene.db.mapper.ComunicazioniMapper;
import it.maggioli.eldasoft.gene.model.UploadInfo;
import it.maggioli.eldasoft.gene.ws.*;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * SqlMap per iBatis
 * 
 * @author Stefano.Sabbadin
 */
@RequiredArgsConstructor
@Component
public class SqlMapComunicazioniDao implements ComunicazioniDao {

	private static final Logger LOG = LoggerFactory.getLogger(SqlMapComunicazioniDao.class);

	private final ComunicazioniMapper comunicazioniMapper;
	private final ObjectStorageConfiguration obs_conf;

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
				, comunicazione.getStazioneAppaltante()
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
				, comunicazione.getStazioneAppaltante()
		);
	}

	@Override
	public void insertAllegato(
			String applicativo
			, Long idAllegato
			, Long idComunicazione
			, int progressivoAllegato
			, AllegatoComunicazioneType allegato
			, UploadInfo bucket_info
	) throws DataAccessException {
		boolean isEmptyStream = (allegato.getFile() == null ||
   			    			     (allegato.getFile() != null && allegato.getFile().length <= 0));

		if(!obs_conf.enabled() && isEmptyStream) {
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
			comunicazioniMapper.insertAllegato(
					applicativo
					, idAllegato
					, "W_INVCOM"
					, idComunicazione
					, progressivoAllegato
					, (!StringUtils.isEmpty(allegato.getUuid()) ? allegato.getUuid() : null)
					, allegato.getCifrato()
					, allegato.getTipo()
					, allegato.getNomeFile()
					, allegato.getDescrizione()
					, allegato.getFile()
					, StringUtils.trimToNull(allegato.getFirmacheck())
					, allegato.getFirmacheckts()
					, bucket_info
			);
		}
	}
	
	@Override
	public void updateAllegato(
			String applicativo
			, Long idAllegato
			, Long idComunicazione
			, int progressivoAllegato
			, AllegatoComunicazioneType allegato
			, final UploadInfo info
	) {
		val uuid = (!StringUtils.isEmpty(allegato.getUuid())	? allegato.getUuid() : null);
		byte[] file = null;
		if(uuid != null && StringUtils.isEmpty(info.uuid())) {
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

		val entita = "W_INVCOM";

		comunicazioniMapper.updateAllegato(
				progressivoAllegato
				, allegato.getTipo()
				, allegato.getNomeFile()
				, allegato.getDescrizione()
				, file
				, uuid
				, allegato.getCifrato()
				, StringUtils.trimToNull(allegato.getFirmacheck())
				, allegato.getFirmacheckts()
				, applicativo
				, idAllegato
				, entita
				, idComunicazione
				, info
				, StringUtils.isNotEmpty(info.uuid())
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
			String applicativo
			, long id
			, String idDocumento
	) throws DataAccessException {
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
	public List<AllegatoComunicazioneType> getAllegatiComunicazioneNoFile(
			String applicativo,
			long id,
			String idDocumento) throws DataAccessException {
		String entita = "W_INVCOM";

		return comunicazioniMapper.getAllegatiComunicazioneNoFile(
				applicativo
				, entita
				, Long.toString(id)
				, idDocumento
		);
	}
	
	@Override
	public List<DettaglioComunicazioneType> getElencoComunicazioni(
			DettaglioComunicazioneType criteriRicerca)
			throws DataAccessException {
		return comunicazioniMapper.getElencoComunicazioni(
				criteriRicerca.getApplicativo()
	    		, criteriRicerca.getId()
	            , criteriRicerca.getEntita()
	            , criteriRicerca.getChiave1()
	            , criteriRicerca.getChiave2()
	            , criteriRicerca.getChiave3()
	            , criteriRicerca.getChiave4()
	            , criteriRicerca.getChiave5()
	            , criteriRicerca.getIdOperatore()
	            , criteriRicerca.getMittente()
	            , criteriRicerca.getStato()
	            , (StringUtils.isNotEmpty(criteriRicerca.getOggetto()) ? "%" + criteriRicerca.getOggetto() + "%" : null)
	            , (StringUtils.isNotEmpty(criteriRicerca.getTesto()) ? "%" + criteriRicerca.getTesto() + "%" : null)
	            , null 		// comunicazionePubblica ??? ==> non esiste in DettaglioComunicazioneType
	            , criteriRicerca.getTipoComunicazione()
	            , criteriRicerca.getDataPubblicazione()
	            , criteriRicerca.getModello()
		);
	}
	
	@Override
	public List<DettaglioComunicazioneType> searchElencoComunicazioni(
			DettaglioComunicazioneType criteriRicerca)
			throws DataAccessException {
		return comunicazioniMapper.searchElencoComunicazioni(
				criteriRicerca.getApplicativo()
	    		, criteriRicerca.getId()
	            , criteriRicerca.getEntita()
	            , (StringUtils.isNotEmpty(criteriRicerca.getChiave1()) ? criteriRicerca.getChiave1() + "%" : null)
	            , (StringUtils.isNotEmpty(criteriRicerca.getChiave2()) ? criteriRicerca.getChiave2() + "%" : null)
	            , (StringUtils.isNotEmpty(criteriRicerca.getChiave3()) ? criteriRicerca.getChiave3() + "%" : null)
	            , (StringUtils.isNotEmpty(criteriRicerca.getChiave4()) ? criteriRicerca.getChiave4() + "%" : null)
	            , (StringUtils.isNotEmpty(criteriRicerca.getChiave5()) ? criteriRicerca.getChiave5() + "%" : null)
	            , criteriRicerca.getIdOperatore()
	            , criteriRicerca.getMittente()
	            , criteriRicerca.getStato()
	            , (StringUtils.isNotEmpty(criteriRicerca.getOggetto()) ? "%" + criteriRicerca.getOggetto() + "%" : null)
	            , (StringUtils.isNotEmpty(criteriRicerca.getTesto()) ? "%" + criteriRicerca.getTesto() + "%" : null)
	            , null 		// comunicazionePubblica ??? ==> non esiste in DettaglioComunicazioneType
	            , criteriRicerca.getTipoComunicazione()
	            , criteriRicerca.getDataPubblicazione()
	            , criteriRicerca.getModello()
		);
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

	@Override
	public String getStatoComunicazione(String usernome, String numGara, String bustaType) {
		return comunicazioniMapper.getStatoComunicazione(usernome, numGara, bustaType);
	}
	@Override
	public String getRichiestaPartecipazione(String username, String ngara) {
		DigoggType richiesta = comunicazioniMapper.getRichiestaPartecipazione(username, ngara);

		return richiesta != null
				? richiesta.getDigogg()
				: null;
	}

}
