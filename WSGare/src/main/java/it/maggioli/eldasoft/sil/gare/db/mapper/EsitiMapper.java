package it.maggioli.eldasoft.sil.gare.db.mapper;

import it.maggioli.eldasoft.sil.gare.db.vo.DocumentoAllegatoLotto;
import it.maggioli.eldasoft.sil.gare.ws.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.List;

@Mapper
public interface EsitiMapper {

    public List<EsitoType> searchEsiti(
			  @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("tipoAppalto") Integer tipoAvviso
			, @Param("cig") String cig
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
			, RowBounds bounds
	);

    public Integer countEsiti(
			@Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("tipoAppalto") Integer tipoAvviso
			, @Param("cig") String cig
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
	);
    
    public List<EsitoType> getElencoEsiti(
			  @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("tipoAppalto") Integer tipoAvviso
			, @Param("cig") String cig
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
			, RowBounds bounds
	);
    
    public Integer countElencoEsiti(
			  @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("tipoAppalto") Integer tipoAvviso
			, @Param("cig") String cig
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
	);
    
    public List<EsitoType> getElencoEsitiAffidamenti(
			  @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("tipoAppalto") Integer tipoAvviso
			, @Param("cig") String cig
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
			, RowBounds bounds
	);

    public Integer countElencoEsitiAffidamenti(
			  @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("tipoAppalto") Integer tipoAvviso
			, @Param("cig") String cig
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
	);
    
    public StazioneAppaltanteBandoType getStazioneAppaltanteEsito(
			  @Param("codice") String codice
	);

    public EsitoType getEsito(
			  @Param("codice") String codice
	);

    public List<LottoGaraType> getLottiGaraConEsito(
			  @Param("codiceGara") String codiceGara
	);
    
    public List<DocumentoAllegatoLotto> getDocumentiLottiGaraConEsito(
			  @Param("codiceGara") String codiceGara
	);
    
    public List<LottoEsitoType> getLottiEsito(
			  @Param("codiceGara") String codiceGara
	);
    
    public Integer isAQAggiudicatariMultipli(
			  @Param("codiceGara") String codiceGara
	);
    
    public List<String> getAQAggiudicatariMultipli(
			  @Param("codiceLotto") String codiceLotto
	);
    
    public List<String> getAggiudicatariaSingola(
			  @Param("codiceLotto") String codiceLotto
	);
    
    public List<DocumentoAllegatoType> getDocumentiEsitoLotto(
			  @Param("codiceGara") String codiceGara
			, @Param("codiceLotto") String codiceLotto
	);
    
}
