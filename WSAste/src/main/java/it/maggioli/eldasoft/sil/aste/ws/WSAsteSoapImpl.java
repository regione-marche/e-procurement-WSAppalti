package it.maggioli.eldasoft.sil.aste.ws;


import it.maggioli.eldasoft.sil.aste.bl.AsteManager;
import it.maggioli.eldasoft.sil.aste.bl.AsteManager.NessunRilancioInseritoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
@javax.jws.WebService(		
        serviceName = "WSAsteSoap",
        portName = "WSAsteSOAP",
        targetNamespace = "http://www.eldasoft.it/sil/WSAste/",
//        wsdlLocation = "http://localhost:8080/WSAste/services/WSAsteSOAP?wsdl",	 
        endpointInterface = "it.maggioli.eldasoft.sil.aste.ws.WSAsteSoap"
)      
public class WSAsteSoapImpl implements WSAsteSoap {

	/* logger */
	private static final Logger LOG = LoggerFactory.getLogger(WSAsteSoapImpl.class);
	
	/* managers */
	@Autowired
	private AsteManager asteManager;
	
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Tipi di classifica di un'asta 
	 */
    public static final int TIPOCLASSIFICA_SOLOPROPRIA 				= 1;
    public static final int TIPOCLASSIFICA_PROPRIAEMIGLIOREOFFERTO 	= 2;
    public static final int TIPOCLASSIFICA_GENERALE 				= 3;

	/**
	 * Tipi di classifica di un'asta
	 */
	public static final int TERMINEASTA_NESSUNO					= 0;
	public static final int TERMINEASTA_ASSENZARILANCI 			= 1;
	public static final int TERMINEASTA_SEGUEULTIMORILANCIO 	= 2;
	public static final int TERMINEASTA_DURATAMASSIMA 			= 3;

	/**
	 *
	 */
	public static final int TIPO_OFFERTA_RIBASSI = 1;
	public static final int TIPO_OFFERTA_IMPORTI = 2;

	@Override
	public GetClassificaOutType getClassifica(Integer tipoClassifica,
			String codice, String codiceLotto, String username, String numeroFase) {
		if (LOG.isDebugEnabled())
			LOG.debug("getClassifica(): inizio metodo");

		GetClassificaOutType result = new GetClassificaOutType();
		try {
			List<DettaglioClassificaType> classifica = this.asteManager
				.getClassifica(tipoClassifica, codice, codiceLotto, username, numeroFase);
			
			result.setDettaglio(classifica);
		} catch(Exception e) {
			LOG.error("Errore generico", e);
			result.setErrore("Errore generico: " + e.getMessage());
		}
		
		if (LOG.isDebugEnabled())
			LOG.debug("getClassifica: fine metodo");

		return result;
	}

	@Override
	public GetFasiAstaOutType getFasiAsta(String codice) {
		if (LOG.isDebugEnabled())
			LOG.debug("getFasiAsta(): inizio metodo");

		GetFasiAstaOutType result = new GetFasiAstaOutType();
		try {
			result.setDettaglio( 
					this.asteManager.getFasiAsta(codice));		
		} catch(Exception e) {
			LOG.error("Errore generico", e);
			result.setErrore("Errore generico: " + e.getMessage());
		}
		
		if (LOG.isDebugEnabled())
			LOG.debug("getFasiAsta: fine metodo");

		return result;
	}

	@Override
	public GetDettaglioAstaOutType getDettaglioAsta(String codice,
			String username) {
		if (LOG.isDebugEnabled())
			LOG.debug("getDettaglioAsta(): inizio metodo");

		GetDettaglioAstaOutType result = new GetDettaglioAstaOutType();
		try {
			DettaglioAstaType asta = this.asteManager.getDettaglioAsta(codice, username);
			
			result.setDettaglio(asta); 			
		} catch(Exception e) {
			LOG.error("Errore generico", e);
			result.setErrore("Errore generico: " + e.getMessage());
		}
		
		if (LOG.isDebugEnabled())
			LOG.debug("getDettaglioAsta: fine metodo");

		return result;
	}

	@Override
	public GetElencoRilanciOutType getElencoRilanci(String codice, 
			String codiceLotto, String username, String numeroFase) {
		if (LOG.isDebugEnabled())
			LOG.debug("getElencoRilanci(): inizio metodo");

		GetElencoRilanciOutType result = new GetElencoRilanciOutType();
		try {
			result.setDettaglio( 
				this.asteManager.getElencoRilanci(codice, codiceLotto, username, numeroFase));		
		} catch(Exception e) {
			LOG.error("Errore generico", e);
			result.setErrore("Errore generico: " + e.getMessage());
		}
		
		if (LOG.isDebugEnabled())
			LOG.debug("getElencoRilanci: fine metodo");

		return result;
	}

	@Override
	public InsertRilancioAstaOutType insertRilancioAsta(String codice,
			String codiceLotto, String username, Double offerta, 
			List<VoceDettaglioAstaType> prezziUnitari) {
		if (LOG.isDebugEnabled())
			LOG.debug("insertRilancioAsta(): inizio metodo");

		InsertRilancioAstaOutType result = new InsertRilancioAstaOutType();
		try {
			// inserisci l'offerta del nuovo rilancio...
			result.idRilancio = this.asteManager.insertRilancioAsta(
					codice, 
					codiceLotto, 
					username, 
					offerta,
					prezziUnitari);			
		} catch(NessunRilancioInseritoException e) {
			LOG.error("Rilancio non inserito", e);
			result.setErrore(e.getMessage());
		} catch(Exception e) {
			LOG.error("Errore generico", e);
			result.setErrore("Errore generico: " + e.getMessage());
		}
		
		if (LOG.isDebugEnabled())
			LOG.debug("insertRilancioAsta: fine metodo");

		return result;
	}

	@Override
	public GetElencoTerminiAstaOutType getElencoTerminiAsta() {
		if (LOG.isDebugEnabled())
			LOG.debug("getElencoTerminiAsta(): inizio metodo");

		GetElencoTerminiAstaOutType result = new GetElencoTerminiAstaOutType();
		try {
			LinkedHashMap<String, String> lista = new LinkedHashMap<String, String>();		
			lista.put("1", "Termine asta (in caso di assenza di rilanci)");
			lista.put("2", "Termine asta (seguente ultimo rilancio)");
			lista.put("3", "Termine asta (durata massima)");
			
			result.setElenco(lista);		
		} catch(Exception e) {
			LOG.error("Errore generico", e);
			result.setErrore("Errore generico: " + e.getMessage());
		}
		
//      DA SOSTITUIRE CON 		
//		List<DatoCodificatoType> lista = this.datiCodificatiManager
//			.getTipiTab1("....");
//		String documentoXML = WSOperazioniGeneraliSoapImpl.buildXML(lista);

		if (LOG.isDebugEnabled())
			LOG.debug("getElencoTerminiAsta: fine metodo");

		return result;
	}

	@Override
	public GetElencoTipiClassificaOutType getElencoTipiClassifica() {
		if (LOG.isDebugEnabled())
			LOG.debug("getElencoTipiClassifica(): inizio metodo");

		GetElencoTipiClassificaOutType result = new GetElencoTipiClassificaOutType();
		try {
			LinkedHashMap<String, String> lista = new LinkedHashMap<String, String>();
			lista.put("1", "Solo la propria classifica");
			lista.put("2", "La propria classifica e il miglior valore offerto rispetto alla propria offerta iniziale");
			lista.put("3", "Classifica generale in forma anonima e i valori offerti dai rispettivi concorrenti");

			result.setElenco(lista);		
		} catch(Exception e) {
			LOG.error("Errore generico", e);
			result.setErrore("Errore generico: " + e.getMessage());
		}

//      DA SOSTITUIRE CON 		
//		List<DatoCodificatoType> lista = this.datiCodificatiManager
//			.getTipiTab1("....");
//		String documentoXML = WSOperazioniGeneraliSoapImpl.buildXML(lista);

		if (LOG.isDebugEnabled())
			LOG.debug("getElencoTipiClassifica: fine metodo");

		return result;
	}

	@Override
	public GetPrezziUnitariOutType getPrezziUnitariPrimoRilancio(
			String codice, String codiceLotto, String username) {
		if (LOG.isDebugEnabled())
			LOG.debug("getPrezziUnitariPrimoRilancio(): inizio metodo");

		GetPrezziUnitariOutType result = new GetPrezziUnitariOutType();
		try {
			result.setDettaglio(this.asteManager.getPrezziUnitariPrimoRilancio(codice, codiceLotto, username));		
		} catch(Exception e) {
			LOG.error("Errore generico", e);
			result.setErrore("Errore generico: " + e.getMessage());
		}
		
		if (LOG.isDebugEnabled())
			LOG.debug("getPrezziUnitariPrimoRilancio: fine metodo");

		return result;
	}
		
	@Override
	public GetPrezziUnitariOutType getPrezziUnitariRilancio(
			String codice, String codiceLotto, String username, Long idRilancio) {
		if (LOG.isDebugEnabled())
			LOG.debug("getPrezziUnitariRilancio(): inizio metodo");

		GetPrezziUnitariOutType result = new GetPrezziUnitariOutType();
		try {
			result.setDettaglio(this.asteManager.getPrezziUnitariRilancio(codice, codiceLotto, username, idRilancio));		
		} catch(Exception e) {
			LOG.error("Errore generico", e);
			result.setErrore("Errore generico: " + e.getMessage());
		}
		
		if (LOG.isDebugEnabled())
			LOG.debug("getPrezziUnitariRilancio: fine metodo");

		return result;
	}

}

