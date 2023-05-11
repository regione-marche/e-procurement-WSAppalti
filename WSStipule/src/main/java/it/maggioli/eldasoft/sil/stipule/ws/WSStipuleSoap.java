package it.maggioli.eldasoft.sil.stipule.ws;


import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.springframework.dao.DataAccessException;

@WebService( 		
		targetNamespace = "http://www.eldasoft.it/sil/WSStipule/",  
		name = "WSStipuleSoap" )
public interface WSStipuleSoap {

	@WSDLDocumentation("Restituisce l'elenco delle stipule contratti")
	public ElencoStipuleContrattiOutType searchStipuleContratti(
			@WebParam(name = "codiceStipula", targetNamespace = "")
			String codiceStipula,
			@WebParam(name = "oggetto", targetNamespace = "")
			String oggetto, 
			@WebParam(name = "stato", targetNamespace = "")
			Integer stato,
			@WebParam(name = "stazioneAppaltante", targetNamespace = "")
			String stazioneAppaltante,
			@WebParam(name = "usernome", targetNamespace = "")
			String usernome,
	        @WebParam(name = "indicePrimoRecord", targetNamespace = "")
	        int indicePrimoRecord,
	        @WebParam(name = "maxNumRecord", targetNamespace = "")
	        int maxNumRecord);
	

	@WSDLDocumentation("Restituisce il dettaglio di una stipula contratto")
	public GetDettaglioStipulaContrattoOutType getDettaglioStipulaContratto(
			@WebParam(name = "codiceStipula", targetNamespace = "")
			String codiceStipula,
			@WebParam(name = "usernome", targetNamespace = "")
			String usernome,
			@WebParam(name = "pubblicata", targetNamespace = "")
			boolean pubblicata);
	
	@WSDLDocumentation("Estrae l'elenco dei documenti richiesti all'atto della stipula di un contratto.")
	public GetDocumentiRichiestiStipulaContrattoOutType getDocumentiRichiestiStipulaContratto(
			@WebParam(name = "idStipula", targetNamespace = "")
			String idStipula);

	@WSDLDocumentation("Estrae l'elenco dei documenti richiesti all'atto della stipula di un contratto.")
	public GetAllegatoStipulaContrattoOutType getDocumentoStipula(
			@WebParam(name = "idDocumento", targetNamespace = "")
			String idDocumento);

	@WSDLDocumentation("Estrae l'elenco dei documenti richiesti all'atto della stipula di un contratto.")
	public String deleteDocumentoStipula(
			@WebParam(name = "idDocumento", targetNamespace = "")
			String idDocumento);

	@WSDLDocumentation("Inserisce un doc in w_docdig associato alla stipula.")
	String insertAllegato(
			@WebParam(name = "nomeFile", targetNamespace = "")
			String nomeFile,
			@WebParam(name = "idDocStipula", targetNamespace = "")
			Long idDocStipula,
			@WebParam(name = "allegato", targetNamespace = "")
			byte[] allegato,
			@WebParam(name = "note", targetNamespace = "")
			String note	,	
			@WebParam(name = "firmacheck", targetNamespace = "")
			String firmacheck	,	
			@WebParam(name = "firmacheckts", targetNamespace = "")
			Date firmacheckts		
	);

	@WSDLDocumentation("Update di stipula e documenti richiesti in stato 4.")
	String updateStipula(
			@WebParam(name = "idStipula", targetNamespace = "")
			String codStipula,
			@WebParam(name = "username", targetNamespace = "")
			String username)
			throws DataAccessException;
	
}

