package it.maggioli.eldasoft.sil.pagopa.ws;

import it.maggioli.eldasoft.sil.pagopa.exception.PagoPaException;
import org.apache.cxf.annotations.WSDLDocumentation;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService(
		targetNamespace = "http://www.eldasoft.it/sil/WSPagoPASoap/",  
		name = "WSPagoPASoap" )
public interface WSPagoPASoap {
	
	/**
	 * Restituisce il conteggio dei pagamenti raggruppati per stato e filtrati per codice impresa che abbiano lo iuv popolato
	 * @param codiceImpresa il codice impresa da filtrare
	 * @return l'oggetto contente i dati
	 * @throws PagoPaException
	 */
	@WSDLDocumentation("Restituisce il conteggio dei pagamenti raggruppati per stato e filtrati per codice impresa che abbiano lo iuv popolato")
	public it.maggioli.eldasoft.sil.pagopa.ws.StatisticaPagamentiOutType getStatisticaPagamenti(
			@WebParam(name = "codiceImpresa", targetNamespace = "")
			@XmlElement(required=true,nillable=false)
			String codiceImpresa)  throws PagoPaException;
	
	/**
	 * Salva o aggiorna un pagamento
	 * @param pagamento il pagamento da inserire o aggiornare
	 * @throws PagoPaException
	 */
	public Long saveOrUpdatePagamento(
			@WebParam(name = "pagamento", targetNamespace = "")
			@XmlElement(required=true,nillable=false)
			PagamentoInType pagamento) throws PagoPaException;
	
	/**
	 * Restituisce la lista dei pagamenti filtrati secondo il parametro
	 * @param filtro il filtro con i dati da applicare
	 * @return la lista dei pagamenti
	 * @throws PagoPaException in caso di errori di validazione o altri errori
	 */
	@WSDLDocumentation("Restituisce la lista pginata dei pagamenti filtrati secondo il parametro")
	public PagamentiPaginationType getListPagamentiByFilter(
			@WebParam(name = "filtro", targetNamespace = "")
			@XmlElement(required=true,nillable=false)
			PagamentiFilterInType filtro) throws PagoPaException;

	/**
	 * Restituisce il pagamento richiesto
	 * @param id l'id del pagamento
	 * @return il pagamento richieso
	 * @throws PagoPaException in caso non sia presente il pagamento richiesto
	 */
	@WSDLDocumentation("Restituisce la lista dei pagamenti filtrati secondo il parametro")
	public PagamentiOutType getPagamentoById(
			@WebParam(name = "id", targetNamespace = "")
			@XmlElement(required=true,nillable=false)
			Long id) throws PagoPaException;
	
	/**
	 * Restituisce l'elenco dei tipi causale pagamento.
	 * @return l'oggetto contenente i dati
	 * @throws PagoPaException
	 */
	@WSDLDocumentation("Restituisce l'elenco dei tipi causale pagamento.")
	public ElencoTipiCausalePagamentoOutType getElencoTipiCausalePagamento()  throws PagoPaException;
	
	/**
	 * Restituisce l'elenco dei tipi pagamento.
	 * @return l'oggetto contenente i dati
	 * @throws PagoPaException
	 */
	@WSDLDocumentation("Restituisce l'elenco degli stati del pagamento.")
	public ElencoStatiPagamentoOutType getElencoStatiPagamento()  throws PagoPaException;
	
	/**
	 * Restituisce la ricevuta
	 * @return l'oggetto contenente i dati
	 * @throws PagoPaException
	 */
	@WSDLDocumentation("Restituisce la ricevuta.")
	public PagopaRicevutaOutType getRicevutaById(
			@WebParam(name = "id", targetNamespace = "")
			@XmlElement(required=true,nillable=false)
			Long id)  throws PagoPaException;
	/**
	 * Inserisce una ricevuta a DB
	 * @param ricevuta la ricevuta in ingresso
	 * @throws PagoPaException
	 */
	@WSDLDocumentation("Inserisce la ricevuta nella base dati.")
	public void insertRicevutaPagamento(
			@WebParam(name = "ricevuta", targetNamespace = "")
			@XmlElement(required=true,nillable=false)
			PagopaRicevutaInType ricevuta) throws PagoPaException;
	
	
	@WSDLDocumentation("Si ottiene la lista di riferimenti procedure.")
	public RiferimentoResponseOutType getElencoRiferimenti(
			@WebParam(name = "filtro", targetNamespace = "")
			@XmlElement(required=true,nillable=false)
			RiferimentoFilterInType filtro) throws PagoPaException;
	
	@WSDLDocumentation("Si ottiene la singola riferimento procedura")
	public RiferimentoOutType getRiferimentoProcedura(
			@WebParam(name = "filtro", targetNamespace = "")
			@XmlElement(required=true,nillable=false)
			RiferimentoFilterInType filtro) throws PagoPaException;
	
	@WSDLDocumentation("Si ottiene la mappatura della causale pagamento rispetto alla configurazione jppa")
	public ElencoTabulatoDueCodificatoOutType getMappaturaJppa() throws PagoPaException;
	
}
