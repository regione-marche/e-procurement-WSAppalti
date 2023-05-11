package it.maggioli.eldasoft.sil.aste.ws;

import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.annotations.WSDLDocumentation;

@WebService( 		
		targetNamespace = "http://www.eldasoft.it/sil/WSAste/",  
		name = "WSAsteSoap" )
//@XmlSeeAlso({ObjectFactory.class})
public interface WSAsteSoap {

   /**
    * Annotazione per la documentazione di un metodo del servizio
    * 
    * 		@WSDLDocumentation("Descrizione del metodo")
    * 
    * mentre per annotare i parametri del metodo si utilizza
    *  
    * 		@WebParam(name = "tipoClassifica", targetNamespace = "")
    *
    * Le seguenti annotazioni danno informazioni relative ai wrapper e alla 
    * risposta del metodo, ma non sono strettamente necessarie:
    *  
    * 		@WebResult(name = "risultato", targetNamespace = "")
    * 		@RequestWrapper(localName = "GetClassifica", targetNamespace = "http://www.eldasoft.it/sil/WSAste/", className = "it.maggioli.eldasoft.sil.aste.ws.GetClassifica")
    * 		@WebMethod(operationName = "GetClassifica", action = "http://www.eldasoft.it/sil/WSAste/GetClassifica")
    * 		@ResponseWrapper(localName = "GetClassificaResponse", targetNamespace = "http://www.eldasoft.it/sil/WSAste/", className = "it.maggioli.eldasoft.sil.aste.ws.GetClassificaResponse")
    * 
    */
	
	@WSDLDocumentation("Richiesta della classifica utenti dell'asta")
	public it.maggioli.eldasoft.sil.aste.ws.GetClassificaOutType getClassifica(
	    @WebParam(name = "tipoClassifica", targetNamespace = "")
	    java.lang.Integer tipoClassifica,
	    @WebParam(name = "codice", targetNamespace = "")
	    java.lang.String codice,
	    @WebParam(name = "codiceLotto", targetNamespace = "")
	    java.lang.String codiceLotto,
	    @WebParam(name = "username", targetNamespace = "")
	    java.lang.String username,
	    @WebParam(name = "numeroFase", targetNamespace = "")
	    java.lang.String numeroFase
	);

	@WSDLDocumentation("Richiesta delle fasi dell'asta")
	public it.maggioli.eldasoft.sil.aste.ws.GetFasiAstaOutType getFasiAsta(
		@WebParam(name = "codice", targetNamespace = "")
	    java.lang.String codice
	);
	
	@WSDLDocumentation("Richiesta del dettaglio dei dati dell'asta")
	public it.maggioli.eldasoft.sil.aste.ws.GetDettaglioAstaOutType getDettaglioAsta(
		@WebParam(name = "codice", targetNamespace = "")
	    java.lang.String codice,
	    @WebParam(name = "username", targetNamespace = "")
	    java.lang.String username
	);

	@WSDLDocumentation("Richiesta d'elenco dei rilanci relativi ad un'asta")
	public it.maggioli.eldasoft.sil.aste.ws.GetElencoRilanciOutType getElencoRilanci(
		@WebParam(name = "codice", targetNamespace = "")
	    java.lang.String codice,
	    @WebParam(name = "codiceLotto", targetNamespace = "")
	    java.lang.String codiceLotto,
	    @WebParam(name = "username", targetNamespace = "")
	    java.lang.String username,
	    @WebParam(name = "numeroFase", targetNamespace = "")
	    java.lang.String numeroFase
	);

	@WSDLDocumentation("Richiesta di inserimento di un rilancio per un'asta")
	public it.maggioli.eldasoft.sil.aste.ws.InsertRilancioAstaOutType insertRilancioAsta(
		@WebParam(name = "codice", targetNamespace = "")
	    java.lang.String codice,
	    @WebParam(name = "codiceLotto", targetNamespace = "")
	    java.lang.String codiceLotto,
	    @WebParam(name = "username", targetNamespace = "")
	    java.lang.String username,
	    @WebParam(name = "offerta", targetNamespace = "")
	    java.lang.Double offerta,
	    @WebParam(name = "prezziUnitari", targetNamespace = "")
	    java.util.List<VoceDettaglioAstaType> prezziUnitari
	);

	@WSDLDocumentation("Richiesta dell'elenco dei tipi di termine asta")
	public GetElencoTerminiAstaOutType getElencoTerminiAsta();
	
	@WSDLDocumentation("Richiesta dell'elenco dei tipi di classifica d'asta")
	public GetElencoTipiClassificaOutType getElencoTipiClassifica();

	@WSDLDocumentation("Richiesta dell'elenco dei prezzi unitari del I rilancio di un'offerta d'asta")
	public GetPrezziUnitariOutType getPrezziUnitariPrimoRilancio(
			@WebParam(name = "codice", targetNamespace = "")
			String codice, 
			@WebParam(name = "codiceLotto", targetNamespace = "")
			String codiceLotto, 
			@WebParam(name = "username", targetNamespace = "")
			String username 
	);

	@WSDLDocumentation("Richiesta dell'elenco dei prezzi unitari di un'offerta d'asta")
	public GetPrezziUnitariOutType getPrezziUnitariRilancio(
			@WebParam(name = "codice", targetNamespace = "")
			String codice,
			@WebParam(name = "codiceLotto", targetNamespace = "")
			String codiceLotto, 
			@WebParam(name = "username", targetNamespace = "")
			String username,
			@WebParam(name = "idRilancio", targetNamespace = "")
			Long idRilancio
	);

}

