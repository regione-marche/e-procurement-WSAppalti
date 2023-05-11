package it.maggioli.eldasoft.sil.pagopa.ws;

import it.maggioli.eldasoft.sil.pagopa.bl.PagoPaManager;
import it.maggioli.eldasoft.sil.pagopa.exception.PagoPaException;
import it.maggioli.eldasoft.sil.pagopa.exception.PagoPaValidationException;
import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service
@WebService(
        serviceName = "WSPagoPASoap",
        portName = "WSPagoPASOAP",
        targetNamespace = "http://www.eldasoft.it/sil/WSPagoPASoap/",
        endpointInterface = "it.maggioli.eldasoft.sil.pagopa.ws.WSPagoPASoap"
)
public class WSPagoPASoapImpl implements WSPagoPASoap {
	private static final Logger logger = LoggerFactory.getLogger(WSPagoPASoapImpl.class);
	@Autowired
	private PagoPaManager pagoPaManager;
	
	@Override
	public it.maggioli.eldasoft.sil.pagopa.ws.StatisticaPagamentiOutType getStatisticaPagamenti(String codiceImpresa)  throws PagoPaException {
		if(StringUtils.isEmpty(codiceImpresa)) throw new PagoPaValidationException("codiceImpresa missing.");
		try {
			logger.info("Calling getStatisticaPagamenti with "+codiceImpresa);
			return pagoPaManager.getStatisticaPagamenti(codiceImpresa);
		} catch (Exception e) {
			logger.error("Errore",e);
			StatisticaPagamentiOutType res = new StatisticaPagamentiOutType();
			res.setErrore(e.getLocalizedMessage());
			return res;
		}
	}
	
	@Override
	public Long saveOrUpdatePagamento(PagamentoInType pagamento) throws PagoPaException {
		return pagoPaManager.insertOrUpdatePagamento(pagamento);
	}
	
	@Override
	public PagamentiPaginationType getListPagamentiByFilter(PagamentiFilterInType filtro) throws PagoPaException {
		return pagoPaManager.getItemsByFilter(filtro);
	}

	@Override
	public PagamentiOutType getPagamentoById(Long id) throws PagoPaException {
		return pagoPaManager.getPagamentiById(id);
	}

	@Override
	public it.maggioli.eldasoft.sil.pagopa.ws.ElencoTipiCausalePagamentoOutType getElencoTipiCausalePagamento() throws PagoPaException {
		return pagoPaManager.getElencoTipiCausalePagamento();
	}
	
	@Override
	public it.maggioli.eldasoft.sil.pagopa.ws.ElencoStatiPagamentoOutType getElencoStatiPagamento()  throws PagoPaException{
		return pagoPaManager.getElencoStatiPagamento();
	}
	
	@Override
	public PagopaRicevutaOutType getRicevutaById(Long id)  throws PagoPaException {
		return pagoPaManager.getRicevutaById(id);
	}
	
	@Override
	public void insertRicevutaPagamento(PagopaRicevutaInType ricevuta) throws PagoPaException {
		pagoPaManager.insertRicevutaPagamento(ricevuta);
	}

	@Override
	public RiferimentoResponseOutType getElencoRiferimenti(RiferimentoFilterInType filtro) throws PagoPaException {
		return pagoPaManager.getElencoRiferimenti(filtro);
	}

	@Override
	public RiferimentoOutType getRiferimentoProcedura(RiferimentoFilterInType filtro) throws PagoPaException {
		return pagoPaManager.getRiferimentoProcedura(filtro);
	}
	
	@Override
	public ElencoTabulatoDueCodificatoOutType getMappaturaJppa() throws PagoPaException{
		return pagoPaManager.getMappaturaJppa();
	}

}
