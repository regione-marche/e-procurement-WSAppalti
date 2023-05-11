package it.maggioli.eldasoft.sil.pagopa.db.mapper;

import it.maggioli.eldasoft.sil.pagopa.entities.PagopaPagamento;
import it.maggioli.eldasoft.sil.pagopa.entities.PagopaRicevuta;
import it.maggioli.eldasoft.sil.pagopa.entities.PagopaRiferimentoProcedura;
import it.maggioli.eldasoft.sil.pagopa.ws.PagamentiFilterInType;
import it.maggioli.eldasoft.sil.pagopa.ws.RiferimentoFilterInType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface PagoPAMapper {

    public PagopaPagamento getPagamentiById(@Param("id") Long id);
    public Long getPagamentiByFiltroCount(PagamentiFilterInType filterInTypes);
    public List<PagopaPagamento> getPagamentiByFiltro(PagamentiFilterInType filterInType, RowBounds bounds);
    public List<Object> getCountPagamentiGroupByStato(@Param("codimp") String codimp);
    public List<Object> getMappaTab1(@Param("TAB1COD") String tab1cod);
    public List<Object> getMappaTab2Codificato(@Param("TAB2COD") String tab2cod);
    public PagopaRicevuta getRicevutaById(@Param("id") Long id);
    public Long insertRicevutaPagamento(PagopaRicevuta pagopaRicevuta);
    public Long existsPagamentoById(@Param("id") Long id);
    public List<PagopaRiferimentoProcedura> getRiferimentoProceduraByFiltro(RiferimentoFilterInType filterInType);
    public List<PagopaRiferimentoProcedura> getRiferimentoProcedura(RiferimentoFilterInType filterInType);
    public Long insertPagamento(PagopaPagamento pagopaPagamento);
    public Long updatePagamentoById(PagopaPagamento pagopaPagamento);


}
