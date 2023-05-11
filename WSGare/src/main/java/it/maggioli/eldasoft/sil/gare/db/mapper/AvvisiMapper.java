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

    public List<AvvisoType> searchAvvisi(
              @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("tipoAvviso") Integer tipoAvviso
            , @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
            , @Param("dataPubblicazioneA") Date dataPubblicazioneA
            , @Param("dataScadenzaDa") Date dataScadenzaDa
            , @Param("dataScadenzaA") Date dataScadenzaA
            , @Param("altriSoggetti") Integer altriSoggetti
            , @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
            , RowBounds bounds
    );

    public Integer countAvvisi(
              @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("tipoAvviso") Integer tipoAvviso
            , @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
            , @Param("dataPubblicazioneA") Date dataPubblicazioneA
            , @Param("dataScadenzaDa") Date dataScadenzaDa
            , @Param("dataScadenzaA") Date dataScadenzaA
            , @Param("altriSoggetti") Integer altriSoggetti
            , @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
    );

    public List<AvvisoType> getElencoAvvisi(
              @Param("dataOdierna") Date today
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("tipoAvviso") Integer tipoAvviso
            , @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
            , @Param("dataPubblicazioneA") Date dataPubblicazioneA
            , @Param("dataScadenzaDa") Date dataScadenzaDa
            , @Param("dataScadenzaA") Date dataScadenzaA
            , @Param("altriSoggetti") Integer altriSoggetti
            , @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
            , RowBounds bounds
    );

    public Integer countElencoAvvisi(
              @Param("dataOdierna") Date today
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("tipoAvviso") Integer tipoAvviso
            , @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
            , @Param("dataPubblicazioneA") Date dataPubblicazioneA
            , @Param("dataScadenzaDa") Date dataScadenzaDa
            , @Param("dataScadenzaA") Date dataScadenzaA
            , @Param("altriSoggetti") Integer altriSoggetti
            , @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
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
            , @Param("altriSoggetti") Integer altriSoggetti
            , @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
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
            , @Param("altriSoggetti") Integer altriSoggetti
            , @Param("isGreen") String isGreen
            , @Param("isRecycle") String isRecycle
            , @Param("isPnrr") String isPnrr
    );

    public StazioneAppaltanteBandoType getStazioneAppaltanteAvviso(
              @Param("codice") String codice
    );

    public AvvisoType getAvviso(
            @Param("codice") String codiceGara
    );

}
