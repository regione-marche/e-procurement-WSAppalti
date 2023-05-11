package it.maggioli.eldasoft.sil.pagopa.db.dao;

import java.util.List;
import java.util.Map;

import it.maggioli.eldasoft.sil.pagopa.entities.PagopaPagamento;
import it.maggioli.eldasoft.sil.pagopa.entities.PagopaRicevuta;
import it.maggioli.eldasoft.sil.pagopa.entities.PagopaRiferimentoProcedura;
import it.maggioli.eldasoft.sil.pagopa.ws.PagamentiFilterInType;
import it.maggioli.eldasoft.sil.pagopa.ws.RiferimentoFilterInType;

/**
 * Interfaccia per la definizione dei metodi di interfaccia verso il DB per PagoPA
 * @author gabriele.nencini
 *
 */
public interface PagoPaDao {
	public Map<java.math.BigDecimal,Long> getCountPagamentiGroupByStato(String codiceImpresa);

	public List<PagopaPagamento> getPagamentiByFiltro(PagamentiFilterInType filtro);
	public Long getPagamentiByFiltroCount(PagamentiFilterInType filtro);
	
	public boolean existsPagamentoById(Long id);
	
	public void insertPagamento(PagopaPagamento pagamento);
	public void updatePagamentoById(PagopaPagamento pagamento);
	
	public PagopaPagamento getPagamentiById(Long id);
	public Map<java.math.BigDecimal,String> getElencoTipiCausalePagamento();
	public Map<java.math.BigDecimal,String> getElencoStatiPagamento();
	public Map<String,String> getMappaturaJppa();
	
	public PagopaRicevuta getRicevutaById(Long id);
	public void insertRicevutaPagamento(PagopaRicevuta ricevuta);
	
	public List<PagopaRiferimentoProcedura> getRiferimentoProceduraByFiltro(RiferimentoFilterInType filtro);
	public PagopaRiferimentoProcedura getRiferimentoProcedura(RiferimentoFilterInType filtro);
}
