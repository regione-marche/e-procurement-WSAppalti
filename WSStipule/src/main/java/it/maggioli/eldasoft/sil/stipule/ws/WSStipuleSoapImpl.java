package it.maggioli.eldasoft.sil.stipule.ws;


import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.gene.ws.AllegatoComunicazioneType;
import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;
import it.maggioli.eldasoft.sil.stipule.bl.StipuleContrattiManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
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
	        int maxNumRecord)  
	{
		if (LOG.isDebugEnabled())
			LOG.debug("searchStipuleContratti(" + codiceStipula + "," + oggetto	+ "," + stato
					+ "): inizio metodo");

		ElencoStipuleContrattiOutType risultato = new ElencoStipuleContrattiOutType();

		try {
			int count = this.stipuleContrattiManager.countStipuleContratti(
					codiceStipula, 
					oggetto, 
					stato,
					stazioneAppaltante
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
			LOG.error(
					"Errore inaspettato durante l'interazione con la base dati",
					e);
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
		if (LOG.isDebugEnabled())
			LOG.debug("getDettaglioStipulaContratto(" + codiceStipula + "): inizio metodo");

		GetDettaglioStipulaContrattoOutType risultato = new GetDettaglioStipulaContrattoOutType();

		try {
			StipulaContrattoType dettaglio = this.stipuleContrattiManager.getDettaglioStipulaContratto(codiceStipula, usernome, pubblicata);
			risultato.setStipulaContratto(dettaglio);
		} catch (DataAccessException e) {
			LOG.error(
					"Errore inaspettato durante l'interazione con la base dati",
					e);
			risultato.setErrore(e.getMessage());
		}
		LOG.debug("getDettaglioStipulaContratto: fine metodo");

		return risultato;
	}
	
		
	@Override
	public GetDocumentiRichiestiStipulaContrattoOutType getDocumentiRichiestiStipulaContratto(
			String idStipula)
	{
		if (LOG.isDebugEnabled()) {
			LOG.debug("getDocumentiRichiestiStipulaContratto(" + idStipula 
					+ "): inizio metodo");
		}
		
		GetDocumentiRichiestiStipulaContrattoOutType risultato = new GetDocumentiRichiestiStipulaContrattoOutType();

		try {
			List<DocumentazioneStipulaContrattiType> requisiti = this.stipuleContrattiManager
					.getDocumentiRichiestiStipulaContratto(idStipula);
			risultato.setDocumento(requisiti);
		} catch (DataAccessException e) {
			LOG.error(
					"Errore inaspettato durante l'interazione con la base dati",
					e);
			risultato.setErrore(e.getMessage());
		}

		if (LOG.isDebugEnabled())
			LOG.debug("getDocumentiRichiestiStipulaContratto: fine metodo");

		return risultato;
	}


	@Override
	public GetAllegatoStipulaContrattoOutType getDocumentoStipula(String idDocumento)
	{
		if (LOG.isDebugEnabled()) {
			LOG.debug("getDocumentoStipula(" + idDocumento
					+ "): inizio metodo");
		}

		GetAllegatoStipulaContrattoOutType risultato = new GetAllegatoStipulaContrattoOutType();

		try {
			AllegatoComunicazioneType allegato = this.stipuleContrattiManager
					.getDocumentoStipula(idDocumento);
			risultato.setDocumento(allegato);
		} catch (DataAccessException e) {
			LOG.error(
					"Errore inaspettato durante l'interazione con la base dati",
					e);
			risultato.setErrore(e.getMessage());
		}

		if (LOG.isDebugEnabled())
			LOG.debug("getDocumentiRichiestiStipulaContratto: fine metodo");

		return risultato;
	}

	@Override
	public String deleteDocumentoStipula(String idDocumento)
	{
		if (LOG.isDebugEnabled()) {
			LOG.debug("deleteDocumentoStipula(" + idDocumento 
					+ "): inizio metodo");
		}
		
		try {
			this.stipuleContrattiManager
					.deleteDocumentoStipula(idDocumento);
		} catch (DataAccessException e) {
			LOG.error(
					"Errore inaspettato durante l'interazione con la base dati",
					e);
			return e.getMessage();
		}

		if (LOG.isDebugEnabled())
			LOG.debug("deleteDocumentoStipula: fine metodo");
		return null;

	}
	
	@Override
	public String insertAllegato(String nomeFile, Long idDocStipula, byte[] allegato, String note,String firmacheck, 
			@XmlJavaTypeAdapter(DatetimeAdapter.class)
			Date firmacheckts) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("insertAllegato(" + nomeFile + "," + idDocStipula 
					+ "): inizio metodo");
		}
		
		try {
			
			LOG.info("firmacheck: "+firmacheck);
			LOG.info("firmacheckts: "+firmacheckts);
			
			this.stipuleContrattiManager
					.insertAllegato(nomeFile, idDocStipula, allegato, note, firmacheck, firmacheckts);
		} catch (DataAccessException e) {
			LOG.error(
					"Errore inaspettato durante l'interazione con la base dati",
					e);
			return e.getMessage();
		}

		if (LOG.isDebugEnabled())
			LOG.debug("insertAllegato: fine metodo");
		return null;

	}
	
	@Override
	public String updateStipula(String idStipula, String username) throws DataAccessException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("updateStipula(" + idStipula + "," + username 
					+ "): inizio metodo");
		}
		
		try {
			this.stipuleContrattiManager
					.updateStipula(idStipula, username);
		} catch (DataAccessException e) {
			LOG.error(
					"Errore inaspettato durante l'interazione con la base dati",
					e);
			return e.getMessage();
		}

		if (LOG.isDebugEnabled())
			LOG.debug("updateStipula: fine metodo");
		return null;
	}
	
	
	
}

