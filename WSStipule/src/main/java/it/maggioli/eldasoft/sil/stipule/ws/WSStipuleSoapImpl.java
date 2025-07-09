package it.maggioli.eldasoft.sil.stipule.ws;


import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.gene.ws.AllegatoComunicazioneType;
import it.maggioli.eldasoft.sil.stipule.bl.StipuleContrattiManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@javax.jws.WebService(		
        serviceName = "WSStipuleSoap",
        portName = "WSStipuleSOAP",
        targetNamespace = "http://www.eldasoft.it/sil/WSStipule/",
        endpointInterface = "it.maggioli.eldasoft.sil.stipule.ws.WSStipuleSoap"
)      
public class WSStipuleSoapImpl implements WSStipuleSoap {

	/* logger */
	private static final Logger LOG = LoggerFactory.getLogger(WSStipuleSoapImpl.class);
	@Autowired
	private StipuleContrattiManager stipuleContrattiManager;

	@Override
	public ElencoStipuleContrattiOutType searchStipuleContratti(
			String codiceStipula,
			String oggetto, 
			Integer stato,
			String stazioneAppaltante,
			String usernome,
			int indicePrimoRecord,
	        int maxNumRecord) {
		LOG.debug("searchStipuleContratti({}, {}, {}): inizio metodo", codiceStipula, oggetto, stato);

		ElencoStipuleContrattiOutType risultato = new ElencoStipuleContrattiOutType();

		try {
			int count = this.stipuleContrattiManager.countStipuleContratti(
					codiceStipula
					, oggetto
					, stato
					, stazioneAppaltante
					, usernome
			);
			risultato.setNum(count);
			
			List<StipulaContrattoType> lista = this.stipuleContrattiManager.searchStipuleContratti(
					codiceStipula, 
					oggetto, 
					stato,
					stazioneAppaltante,
					usernome,
					indicePrimoRecord,
			        maxNumRecord);
			risultato.setElenco(lista);
		} catch (DataAccessException e) {
			LOG.error("Errore inaspettato durante l'interazione con la base dati", e);
			risultato.setErrore(e.getMessage());
		} catch (SqlComposerException e) {
			LOG.error("DBMS non supportato o non valido", e);
			risultato.setErrore("DBMS non supportato o non valido");
		}

		LOG.debug("searchStipuleContratti: fine metodo");

		return risultato;
	}
	
	@Override
	public GetDettaglioStipulaContrattoOutType getDettaglioStipulaContratto(String codiceStipula, String usernome, boolean pubblicata) {
		LOG.debug("getDettaglioStipulaContratto({}): inizio metodo", codiceStipula);

		GetDettaglioStipulaContrattoOutType risultato = new GetDettaglioStipulaContrattoOutType();

		try {
			StipulaContrattoType dettaglio = this.stipuleContrattiManager.getDettaglioStipulaContratto(codiceStipula, usernome, pubblicata);
			risultato.setStipulaContratto(dettaglio);
		} catch (DataAccessException e) {
			LOG.error("Errore inaspettato durante l'interazione con la base dati", e);
			risultato.setErrore(e.getMessage());
		}
		LOG.debug("getDettaglioStipulaContratto: fine metodo");

		return risultato;
	}
	
		
	@Override
	public GetDocumentiRichiestiStipulaContrattoOutType getDocumentiRichiestiStipulaContratto(String idStipula)	{
		LOG.debug("getDocumentiRichiestiStipulaContratto({}): inizio metodo", idStipula);

		GetDocumentiRichiestiStipulaContrattoOutType risultato = new GetDocumentiRichiestiStipulaContrattoOutType();

		try {
			List<DocumentazioneStipulaContrattiType> requisiti = this.stipuleContrattiManager
					.getDocumentiRichiestiStipulaContratto(idStipula);
			risultato.setDocumento(requisiti);
		} catch (DataAccessException e) {
			LOG.error("Errore inaspettato durante l'interazione con la base dati", e);
			risultato.setErrore(e.getMessage());
		}

		LOG.debug("getDocumentiRichiestiStipulaContratto: fine metodo");

		return risultato;
	}


	@Override
	public GetAllegatoStipulaContrattoOutType getDocumentoStipula(String idDocumento) {
		LOG.debug("getDocumentoStipula({}): inizio metodo",idDocumento);

		GetAllegatoStipulaContrattoOutType risultato = new GetAllegatoStipulaContrattoOutType();

		try {
			AllegatoComunicazioneType allegato = this.stipuleContrattiManager
					.getDocumentoStipula(idDocumento);
			risultato.setDocumento(allegato);
		} catch (DataAccessException e) {
			LOG.error("Errore inaspettato durante l'interazione con la base dati", e);
			risultato.setErrore(e.getMessage());
		}

		LOG.debug("getDocumentiRichiestiStipulaContratto: fine metodo");

		return risultato;
	}

	@Override
	public String deleteDocumentoStipula(String idDocumento) {
		LOG.debug("deleteDocumentoStipula({}): inizio metodo", idDocumento);

		try {
			this.stipuleContrattiManager
					.deleteDocumentoStipula(idDocumento);
		} catch (DataAccessException e) {
			LOG.error("Errore inaspettato durante l'interazione con la base dati", e);
			return e.getMessage();
		}

		LOG.debug("deleteDocumentoStipula: fine metodo");
		return null;

	}
	
	@Override
	public String insertAllegato(
			Long idDocStipula
			, AllegatoComunicazioneType allegato
			, String note
	) {
		LOG.debug("insertAllegato({}, {}, {}): inizio metodo", idDocStipula, allegato.getNomeFile(), note);

		try {
			LOG.debug("firmacheck: {}; firmacheckts: {};", allegato.getFirmacheckts(), allegato.getFirmacheckts());

			stipuleContrattiManager.insertAllegato(idDocStipula, allegato, note);
		} catch (DataAccessException e) {
			LOG.error("Errore inaspettato durante l'interazione con la base dati", e);
			return e.getMessage();
		}

		LOG.debug("insertAllegato: fine metodo");
		return null;
	}
	
	@Override
	public String updateStipula(String idStipula, String username) throws DataAccessException {
		LOG.debug("updateStipula({}, {}): inizio metodo", idStipula, username);

		try {
			this.stipuleContrattiManager.updateStipula(idStipula, username);
		} catch (DataAccessException e) {
			LOG.error("Errore inaspettato durante l'interazione con la base dati", e);
			return e.getMessage();
		}

		LOG.debug("updateStipula: fine metodo");
		return null;
	}
	
	
	
}

