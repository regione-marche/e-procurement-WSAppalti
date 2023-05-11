package it.maggioli.eldasoft.sil.pagopa.bl;

import it.maggioli.eldasoft.gene.bl.GenChiaviManager;
import it.maggioli.eldasoft.sil.pagopa.db.dao.PagoPaDao;
import it.maggioli.eldasoft.sil.pagopa.entities.PagopaPagamento;
import it.maggioli.eldasoft.sil.pagopa.entities.PagopaRicevuta;
import it.maggioli.eldasoft.sil.pagopa.entities.PagopaRiferimentoProcedura;
import it.maggioli.eldasoft.sil.pagopa.exception.PagoPaException;
import it.maggioli.eldasoft.sil.pagopa.exception.PagoPaPagamentoNotExistsException;
import it.maggioli.eldasoft.sil.pagopa.exception.PagoPaValidationException;
import it.maggioli.eldasoft.sil.pagopa.ws.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe per la bl di pagopa
 * @author gabriele.nencini
 *
 */
@Service
public class PagoPaManager {

	private static final Logger logger = LoggerFactory.getLogger(PagoPaManager.class);

	@Autowired
	private PagoPaDao pagoPaDao;
	@Autowired
	private GenChiaviManager genChiaviManager;

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public StatisticaPagamentiOutType getStatisticaPagamenti(String codiceImpresa) {
		logger.info("Calling PagoPaManager.getStatisticaPagamenti with "+codiceImpresa);
		StatisticaPagamentiOutType res = new StatisticaPagamentiOutType();
		res.setStatistica(pagoPaDao.getCountPagamentiGroupByStato(codiceImpresa));
		return res;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Long insertOrUpdatePagamento(PagamentoInType pagamento) throws PagoPaException {
		if(pagamento == null) throw new PagoPaValidationException("Inserire il parametro PagamentoInType.");
		
		PagopaPagamento pag = new PagopaPagamento();
		BeanUtils.copyProperties(pagamento, pag);
		pag.setCausalecod("A1189");
		pag.setStatocod("A1190");
		pag.setCodimp(pagamento.getCodiceimpresa());
		
		if(pagamento.getId()!=null) {
			if(!pagoPaDao.existsPagamentoById(pagamento.getId())) {
				throw new PagoPaPagamentoNotExistsException("Elemento con id["+pagamento.getId()+"] non esistente, impossibile aggiornare.");
			}		
			this.pagoPaDao.updatePagamentoById(pag);
		} else {
			//genera next id
			try {
				long id = genChiaviManager.getNextId("PAGOPA_PAGAMENTI");
				pag.setId(id);
				this.pagoPaDao.insertPagamento(pag);
			} catch (Exception e) {
				throw new PagoPaException(e.getLocalizedMessage());
			}
		}
		return pag.getId();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void insertPagamento(PagamentoInType pagamento) throws PagoPaException{
		if(pagamento == null) throw new PagoPaValidationException("Inserire il parametro PagamentoInType.");
		
		PagopaPagamento pag = new PagopaPagamento();
		BeanUtils.copyProperties(pagamento, pag);
		pag.setCausalecod("A1189");
		pag.setStatocod("A1190");
		pag.setCodimp(pagamento.getCodiceimpresa());
		//genera next id
		try {
			long id = genChiaviManager.getNextId("PAGOPA_PAGAMENTI");
			pag.setId(id);
		} catch (Exception e) {
			throw new PagoPaException(e.getLocalizedMessage());
		}
		this.pagoPaDao.insertPagamento(pag);
	}

	/**
	 * Estrae i dati dopo la validazione del filtro
	 * @param filtro filtro di ricerca  {@link PagamentiFilterInType}
	 * @return restituisce la lista dei pagamenti {@link PagamentiOutType}
	 * @throws PagoPaValidationException in caso la validazione non vada a buon fine
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagamentiPaginationType getItemsByFilter(PagamentiFilterInType filtro) throws PagoPaValidationException {
		this.validaFiltro(filtro);
		logger.info("Calling with "+filtro);
		List<PagopaPagamento> lista = this.pagoPaDao.getPagamentiByFiltro(filtro);
		logger.info("Found "+lista.size() +" elements");
		List<PagamentiOutType> listaOut = new ArrayList<>(lista.size());
		for(PagopaPagamento el : lista) {
			listaOut.add(convertPagopaPagamentoToPagamentiOutType(el));
		}
		PagamentiPaginationType pag = new PagamentiPaginationType();
		pag.setContent(listaOut);
		pag.setPage(filtro.getPage());
		pag.setPagesize(filtro.getPagesize());
		pag.setTotalelements(this.pagoPaDao.getPagamentiByFiltroCount(filtro));
		pag.setTotalpage((long) (int) (pag.getTotalelements() / filtro.getPagesize()));
		if(pag.getTotalpage()<1) {
			pag.setTotalpage(1L);
		}
		return pag;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagamentiOutType getPagamentiById(Long id) throws PagoPaException {
		if(id==null) throw new PagoPaValidationException("Parametro missing.");
		PagopaPagamento pag = this.pagoPaDao.getPagamentiById(id);
		if(pag == null) throw new PagoPaPagamentoNotExistsException("Pagamento con id "+id+" non esistente.");
		return convertPagopaPagamentoToPagamentiOutType(pag);
	}
	
	private void validaFiltro(PagamentiFilterInType filtro) throws PagoPaValidationException {
		if(filtro == null) throw new PagoPaValidationException("PagamentiFilterInType missing");
		if(StringUtils.isBlank(filtro.getCodiceimpresa())) throw new PagoPaValidationException("CodiceImpresa missing");
		if(filtro.getPage()==null || filtro.getPage() < 0) throw new PagoPaValidationException("Page deve essere un numero maggiore o uguale a 0");
		if(filtro.getPagesize()==null || filtro.getPagesize() <= 0) throw new PagoPaValidationException("Pagesize deve essere un numero maggiore di 0");
	}
	
	private PagamentiOutType convertPagopaPagamentoToPagamentiOutType(PagopaPagamento e) {
		PagamentiOutType dto = new PagamentiOutType();
		BeanUtils.copyProperties(e, dto);
		dto.setCodiceimpresa(e.getCodimp());
		return dto;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ElencoTipiCausalePagamentoOutType getElencoTipiCausalePagamento() {
		ElencoTipiCausalePagamentoOutType out = new ElencoTipiCausalePagamentoOutType();
		out.setTipologia(this.pagoPaDao.getElencoTipiCausalePagamento());
		logger.info("getElencoTipiCausalePagamento: "+out.getTipologia().size());
		return out;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ElencoStatiPagamentoOutType getElencoStatiPagamento() {
		ElencoStatiPagamentoOutType out = new ElencoStatiPagamentoOutType();
		out.setTipologia(this.pagoPaDao.getElencoStatiPagamento());
		return out;		
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ElencoTabulatoDueCodificatoOutType getMappaturaJppa() {
		ElencoTabulatoDueCodificatoOutType out = new ElencoTabulatoDueCodificatoOutType();
		out.setTipologia(this.pagoPaDao.getMappaturaJppa());
		return out;		
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagopaRicevutaOutType getRicevutaById(Long id) throws PagoPaException {
		try {
			logger.info("Richiesta ricevuta con id "+id);
			PagopaRicevuta pr = this.pagoPaDao.getRicevutaById(id);
			logger.info("Trovata ricevuta? "+(pr!=null));
			if(pr==null) throw new PagoPaException("Missing rivevuta con id "+id);
			return new PagopaRicevutaOutType().id(pr.getPagopapagamentiid()).ricevuta(pr.getRicevuta());
		} catch (Exception e) {
			throw new PagoPaException(e.getMessage());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void insertRicevutaPagamento(PagopaRicevutaInType ricevuta) throws PagoPaException {
		if(ricevuta == null || ricevuta.getId()==null || ricevuta.getRicevuta() ==null) throw new PagoPaValidationException("PagopaRicevutaInType missing");
		try {
			PagopaRicevuta ric = new PagopaRicevuta();
			ric.setPagopapagamentiid(ricevuta.getId());
			ric.setRicevuta(ricevuta.getRicevuta());
			this.pagoPaDao.insertRicevutaPagamento(ric);
		} catch (Exception e) {
			throw new PagoPaException(e.getMessage());
		}
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public RiferimentoResponseOutType getElencoRiferimenti(RiferimentoFilterInType filtro) throws PagoPaException {
		if(filtro == null || filtro.getUsernome()==null)
			throw new PagoPaValidationException("RiferimentoResponseOutType missing");
		try {
			logger.info("Filtro: " + filtro);
			List<PagopaRiferimentoProcedura> lista = this.pagoPaDao.getRiferimentoProceduraByFiltro(filtro);
			List<RiferimentoOutType> out = new ArrayList<>(lista.size());
			for(PagopaRiferimentoProcedura p : lista) {
				out.add(convertFromPagopaRiferimentoProcedura(p));
			}
			return new RiferimentoResponseOutType().lista(out);
		} catch (Exception e) {
			logger.error("getElencoRiferimenti ",e);
			throw new PagoPaException(e.getMessage());
		}
	}
	
	private RiferimentoOutType convertFromPagopaRiferimentoProcedura(PagopaRiferimentoProcedura p) {
		if(p==null) return null;
		RiferimentoOutType out = new RiferimentoOutType();
		BeanUtils.copyProperties(p, out);
		return out;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public RiferimentoOutType getRiferimentoProcedura(RiferimentoFilterInType filtro) throws PagoPaException {
		try {
			return convertFromPagopaRiferimentoProcedura(this.pagoPaDao.getRiferimentoProcedura(filtro));
		} catch (Exception e) {
			logger.error("getRiferimentoProcedura ",e);
			throw new PagoPaException(e.getMessage());
		}
	}


}
