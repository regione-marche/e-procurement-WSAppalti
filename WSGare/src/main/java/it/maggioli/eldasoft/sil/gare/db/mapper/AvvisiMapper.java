package it.maggioli.eldasoft.sil.gare.db.mapper;

import it.maggioli.eldasoft.sil.gare.ws.AvvisoType;
import it.maggioli.eldasoft.sil.gare.ws.StazioneAppaltanteBandoType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import java.util.Date;
import java.util.List;

@Mapper
public interface AvvisiMapper {

    public List<AvvisoType> searchAvvisiGenerali(
    		@Param("dataOdierna") Date today
          , @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
          , @Param("stazioneAppaltante") String stazioneAppaltante
          , @Param("operatoreUpper") String operatoreUpper
          , @Param("oggetto") String oggetto
          , @Param("tipoAvviso") Integer tipoAvviso
          , @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
          , @Param("dataPubblicazioneA") Date dataPubblicazioneA
          , @Param("dataScadenzaDa") Date dataScadenzaDa
          , @Param("dataScadenzaA") Date dataScadenzaA
          , @Param("stato") Integer stato
          , @Param("altriSoggetti") Integer altriSoggetti
          , @Param("isGreen") String isGreen
          , @Param("isRecycle") String isRecycle
          , @Param("isPnrr") String isPnrr
          , @Param("tipoAvvisoGenerale") Integer tipoAvvisoGenerale
          , RowBounds bounds
    );

    public Integer countAvvisiGenerali(
    		@Param("dataOdierna") Date today
          , @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
          , @Param("stazioneAppaltante") String stazioneAppaltante
          , @Param("operatoreUpper") String operatoreUpper
          , @Param("oggetto") String oggetto
          , @Param("tipoAvviso") Integer tipoAvviso
          , @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
          , @Param("dataPubblicazioneA") Date dataPubblicazioneA
          , @Param("dataScadenzaDa") Date dataScadenzaDa
          , @Param("dataScadenzaA") Date dataScadenzaA
          , @Param("stato") Integer stato
          , @Param("altriSoggetti") Integer altriSoggetti
          , @Param("isGreen") String isGreen
          , @Param("isRecycle") String isRecycle
          , @Param("isPnrr") String isPnrr
          , @Param("tipoAvvisoGenerale") Integer tipoAvvisoGenerale
    );
    
    public List<AvvisoType> searchAvvisi(
    		  @Param("dataOdierna") Date today
            , @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("tipoAvviso") Integer tipoAvviso
            , @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
            , @Param("dataPubblicazioneA") Date dataPubblicazioneA
            , @Param("dataScadenzaDa") Date dataScadenzaDa
            , @Param("dataScadenzaA") Date dataScadenzaA
            , @Param("stato") Integer stato
            , @Param("altriSoggetti") Integer altriSoggetti
            , @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
            , @Param("tipoAvvisoGenerale") Integer tipoAvvisoGenerale
            , RowBounds bounds
    );

    public Integer countAvvisi(
    		  @Param("dataOdierna") Date today
            , @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("tipoAvviso") Integer tipoAvviso
            , @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
            , @Param("dataPubblicazioneA") Date dataPubblicazioneA
            , @Param("dataScadenzaDa") Date dataScadenzaDa
            , @Param("dataScadenzaA") Date dataScadenzaA
            , @Param("stato") Integer stato
            , @Param("altriSoggetti") Integer altriSoggetti
            , @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
            , @Param("tipoAvvisoGenerale") Integer tipoAvvisoGenerale
    );

    public List<AvvisoType> getElencoAvvisi(
              @Param("dataOdierna") Date today
            , @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("tipoAvviso") Integer tipoAvviso
            , @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
            , @Param("dataPubblicazioneA") Date dataPubblicazioneA
            , @Param("dataScadenzaDa") Date dataScadenzaDa
            , @Param("dataScadenzaA") Date dataScadenzaA
            , @Param("stato") Integer stato
            , @Param("altriSoggetti") Integer altriSoggetti
            , @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
            , @Param("tipoAvvisoGenerale") Integer tipoAvvisoGenerale
            , RowBounds bounds
    );

    public Integer countElencoAvvisi(
              @Param("dataOdierna") Date today
            , @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("tipoAvviso") Integer tipoAvviso
            , @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
            , @Param("dataPubblicazioneA") Date dataPubblicazioneA
            , @Param("dataScadenzaDa") Date dataScadenzaDa
            , @Param("dataScadenzaA") Date dataScadenzaA
            , @Param("stato") Integer stato
            , @Param("altriSoggetti") Integer altriSoggetti
            , @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
            , @Param("tipoAvvisoGenerale") Integer tipoAvvisoGenerale
    );

    public List<AvvisoType> getElencoAvvisiScaduti(
              @Param("dataOdierna") Date today
            , @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("tipoAvviso") Integer tipoAvviso
            , @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
            , @Param("dataPubblicazioneA") Date dataPubblicazioneA
            , @Param("dataScadenzaDa") Date dataScadenzaDa
            , @Param("dataScadenzaA") Date dataScadenzaA
            , @Param("stato") Integer stato
            , @Param("altriSoggetti") Integer altriSoggetti
            , @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
            , @Param("tipoAvvisoGenerale") Integer tipoAvvisoGenerale
            , RowBounds bounds
    );
    public Integer countElencoAvvisiScaduti(
              @Param("dataOdierna") Date today
            , @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("tipoAvviso") Integer tipoAvviso
            , @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
            , @Param("dataPubblicazioneA") Date dataPubblicazioneA
            , @Param("dataScadenzaDa") Date dataScadenzaDa
            , @Param("dataScadenzaA") Date dataScadenzaA
            , @Param("stato") Integer stato
            , @Param("altriSoggetti") Integer altriSoggetti
            , @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
            , @Param("tipoAvvisoGenerale") Integer tipoAvvisoGenerale
    );

    public StazioneAppaltanteBandoType getStazioneAppaltanteAvviso(
              @Param("codice") String codice
    );

    public AvvisoType getAvviso(
    		@Param("dataOdierna") Date today
  		  	, @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
  		  	, @Param("codice") String codiceGara
    );
    
    public AvvisoType getAvvisoByCig(
    		@Param("dataOdierna") Date today
    		, @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
            , @Param("cig") String cig
    );

}
