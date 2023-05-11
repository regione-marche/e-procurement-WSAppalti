package it.maggioli.eldasoft.sil.pagopa.db.dao.mybatis;

import it.maggioli.eldasoft.sil.pagopa.db.dao.PagoPaDao;
import it.maggioli.eldasoft.sil.pagopa.db.mapper.PagoPAMapper;
import it.maggioli.eldasoft.sil.pagopa.entities.PagopaPagamento;
import it.maggioli.eldasoft.sil.pagopa.entities.PagopaRicevuta;
import it.maggioli.eldasoft.sil.pagopa.entities.PagopaRiferimentoProcedura;
import it.maggioli.eldasoft.sil.pagopa.ws.PagamentiFilterInType;
import it.maggioli.eldasoft.sil.pagopa.ws.RiferimentoFilterInType;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Implementazione dei metodi di interfaccia verso il DB per PagoPA
 * @author gabriele.nencini
 *
 */
@Component
public class SqlMapPagoPaDao implements PagoPaDao {

	private static final Logger log = LoggerFactory.getLogger(SqlMapPagoPaDao.class);

	@Autowired
	private PagoPAMapper pagoPAMapper;

	@SuppressWarnings("unchecked")
	@Override
	public Map<BigDecimal, Long> getCountPagamentiGroupByStato(String codiceImpresa) {
		return toLongMap(queryForMap(pagoPAMapper.getCountPagamentiGroupByStato(codiceImpresa),"stato","conteggio"));
	}

	@Override
	public List<PagopaPagamento> getPagamentiByFiltro(PagamentiFilterInType filtro) {
		return pagoPAMapper.getPagamentiByFiltro(
				filtro
				, getBounds(filtro.getOffset(), filtro.getPagesize())
		);
	}

	@Override
	public Long getPagamentiByFiltroCount(PagamentiFilterInType filtro) {
		return pagoPAMapper.getPagamentiByFiltroCount(filtro);
	}

	@Override
	public boolean existsPagamentoById(Long id) {
		return pagoPAMapper.existsPagamentoById(id) > 0;
	}

	@Override
	public void insertPagamento(PagopaPagamento pagamento) {
		pagoPAMapper.insertPagamento(pagamento);
	}

	@Override
	public void updatePagamentoById(PagopaPagamento pagamento) {
		pagoPAMapper.updatePagamentoById(pagamento);
	}

	@Override
	public PagopaPagamento getPagamentiById(Long id) {
		return pagoPAMapper.getPagamentiById(id);
	}
	
	@Override
	public Map<java.math.BigDecimal,String> getElencoTipiCausalePagamento() {
		return queryForMap(pagoPAMapper.getMappaTab1("A1189"),"tab1tip", "tab1desc");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map queryForMap(List<Object> results,String keyProp, String valueProp) {
		Map result = new TreeMap();

		for(Object curr : results) {
			java.util.HashMap<String, Object> record = (java.util.HashMap<String,Object>) curr;
			Object key = getMapValueFromArray(record, keyProp, keyProp.toUpperCase());
			Object value = getMapValueFromArray(record, valueProp, valueProp.toUpperCase());
			if(key != null)
				result.put(key, value);
		}

		return result;
	}

	private Object getMapValueFromArray(Map<String, Object> map, String ... keys) {
		Object toReturn = null;

		if (keys != null && keys.length > 0) {
			int index = 0;
			while (keys.length > index && toReturn == null)
				toReturn = map.get(keys[index++]);
		}

		return toReturn;
	}
	
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	private Map queryForMap() {
		Map result = new HashMap();

		List<Object> results = pagoPAMapper.getMappaTab1("A1189");
		for(Object curr : results) {
			java.util.HashMap<String,Object> record = (java.util.HashMap<String,Object>) curr;

			Object key = getMapValueFromArray(record, "tab1tip", "tab1tip".toUpperCase());
			Object value = getMapValueFromArray(record, "tab1desc", "tab1desc".toUpperCase());

			if(key != null)
				result.put(key, value);
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<java.math.BigDecimal,String> getElencoStatiPagamento(){
		return queryForMap(pagoPAMapper.getMappaTab1("A1190"),"tab1tip", "tab1desc");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,String> getMappaturaJppa(){
		return queryForMap(pagoPAMapper.getMappaTab2Codificato("A1z12"),"codice","descrizione");
	}

	@Override
	public PagopaRicevuta getRicevutaById(Long id) {
		return pagoPAMapper.getRicevutaById(id);
	}
	
	@Override
	public void insertRicevutaPagamento(PagopaRicevuta ricevuta) {
		pagoPAMapper.insertRicevutaPagamento(ricevuta);
	}

	@Override
	public List<PagopaRiferimentoProcedura> getRiferimentoProceduraByFiltro(RiferimentoFilterInType filtro) {
		
		if(StringUtils.isNotEmpty(filtro.getCodice())){
			filtro.setCodice("%"+filtro.getCodice()+"%");
		}
		if(StringUtils.isNotEmpty(filtro.getCig())){
			filtro.setCig("%"+filtro.getCig()+"%");
		}
		if(StringUtils.isNotEmpty(filtro.getOggetto())){
			filtro.setOggetto("%"+filtro.getOggetto()+"%");
		}
		List<PagopaRiferimentoProcedura> list = pagoPAMapper.getRiferimentoProceduraByFiltro(filtro);
//		List<PagopaRiferimentoProcedura> result = new ArrayList<PagopaRiferimentoProcedura>(list.size());
//		for(PagopaRiferimentoProcedura el : list) {
//			if(StringUtils.isEmpty(el.getUsernome()) || StringUtils.equals(el.getUsernome(), filtro.getUsernome())) {
//				result.add(el);
//			}
//		}
		return list;
	}

	@Override
	public PagopaRiferimentoProcedura getRiferimentoProcedura(RiferimentoFilterInType filtro) {
		List<PagopaRiferimentoProcedura> results = pagoPAMapper.getRiferimentoProcedura(filtro);
		for(PagopaRiferimentoProcedura result : results)
			if(StringUtils.isEmpty(result.getUsernome()) || StringUtils.equals(result.getUsernome(), filtro.getUsernome()))
				return result;
		return null;
	}

	public RowBounds getBounds(Long indicePrimoRecord, Long maxNumRecord) {
		return new RowBounds(
				indicePrimoRecord == null ? 0 : indicePrimoRecord.intValue()
				, maxNumRecord == null || maxNumRecord == 0L ? Integer.MAX_VALUE : maxNumRecord.intValue()
		);
	}

	private Map<BigDecimal, Long> toLongMap(Map<Number, Number> queryForMap) {
		return queryForMap.entrySet().stream()
					.collect(Collectors.toMap(this::getBigDecimalFromNumber, this::getLongFromNumber));
	}

	private BigDecimal getBigDecimalFromNumber(Map.Entry<Number, Number> entry) {
		return entry.getKey() != null ? new BigDecimal(entry.getKey().longValue()) : null;
	}
	private Long getLongFromNumber(Map.Entry<Number, Number> entry) {
		return entry.getValue() != null ? entry.getValue().longValue() : null;
	}

}
