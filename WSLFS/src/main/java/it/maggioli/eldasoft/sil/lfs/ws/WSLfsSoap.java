package it.maggioli.eldasoft.sil.lfs.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

@WebService( 
			targetNamespace = "http://www.eldasoft.it/sil/WSLfs/",  
			name = "WSLfsSoap" )
public interface WSLfsSoap {
	
	/**
	 * NB: aggiungere ai metodi l'annotazione per la documentazione
	 * 
	 *     @WSDLDocumentation("..........")
	 * 
	 */
	
	@WSDLDocumentation("Restituisce l'elenco dei contratti LFS.")
	public ElencoContrattiLFSOutType searchContrattiLFS(
			@WebParam(name = "stazioneAppaltante", targetNamespace = "")
			String stazioneAppaltante,
			@WebParam(name = "oggetto", targetNamespace = "")
			String oggetto, 
			@WebParam(name = "cig", targetNamespace = "")
			String cig, 
			@WebParam(name = "gara", targetNamespace = "")
			String gara, 
			@WebParam(name = "codice", targetNamespace = "")
			String codice, 
			@WebParam(name = "tokenRichiedente", targetNamespace = "")
			String tokenRichiedente,
			@WebParam(name = "indicePrimoRecord", targetNamespace = "")
			int indicePrimoRecord, 
			@WebParam(name = "maxNumRecord", targetNamespace = "")
			int maxNumRecord
			
			
	);
		
	@WSDLDocumentation("Restituisce il dettaglio di un contratto LFS.")
	public GetDettaglioContrattoLFSOutType getDettaglioContrattoLFS(
			@WebParam(name = "tokenRichiedente", targetNamespace = "")
	        java.lang.String tokenRichiedente,
			@WebParam(name = "codice", targetNamespace = "")
			String codice,
			@WebParam(name = "nappal", targetNamespace = "")
			String nappal
	);

}
