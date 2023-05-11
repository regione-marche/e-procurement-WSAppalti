package it.maggioli.eldasoft.sil.aste.db.mapper;

import it.maggioli.eldasoft.sil.aste.ws.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface AsteMapper {

    public List<DettaglioClassificaType> getClassifica(
			  @Param("tipoClassifica") int tipoClassifica
			, @Param("ngara") String nGara
			, @Param("username") String username
			, @Param("numeroFase") String numeroFase
			, @Param("alRialzo") Boolean alRialzo
	);

    public List<DettaglioFaseAstaType> getFasiAsta(
			  @Param("ngara") String nGara
	);

    public DettaglioAstaType getDettaglioAsta(
			  @Param("ngara") String nGara
			  , @Param("dataCorrente") Date now
	);

    public List<DettaglioRilancioType> getElencoRilanci(
			  @Param("ngara") String nGara
			, @Param("username") String username
			, @Param("numeroFase") String numeroFase
	);

    public Integer insertRilancioAsta(
			  @Param("ngara") String nGara
			, @Param("id") Long id
			, @Param("ditta") String ditta
			, @Param("username") String username
			, @Param("numeroRilancio") Integer numeroRilancio
			, @Param("numeroFase") Integer numeroFase
			, @Param("ribasso") Double ribasso
			, @Param("importo") Double importo
			, @Param("dataRilancio") Date dataRilancio
	);

    public Integer updateRilancioAsta(
			  @Param("ngara") String nGara
			, @Param("ditta") String ditta
		  	, @Param("username") String username
		  	, @Param("numeroRilancio") Integer numeroRilancio
			, @Param("numeroFase") Integer numeroFase
			, @Param("ribasso") Double ribasso
			, @Param("importo") Double importo
			, @Param("dataRilancio") Date dataRilancio
			, @Param("id") Long id
	);

    public Integer updateAsta(
			  @Param("ngara") String codice
			, @Param("dataApertura") Date dataApertura
		  	, @Param("dataChiusura") Date dataChiusura
			, @Param("durataMin") Long durataMin
	  		, @Param("durataMax") Long durataMax
		 	, @Param("tempoBase") Long tempoBase
			, @Param("fase") Integer fase
	);

    public List<VoceDettaglioAstaType> getPrezziUnitariPrimoRilancio(
			  @Param("ngara") String nGara
			, @Param("username") String username
	);

    public List<VoceDettaglioAstaType> getPrezziUnitari(
			  @Param("ngara") String ngara
			, @Param("username") String username
			, @Param("idRilancio") Long idRilancio
	);

    public Integer insertPrezziUnitari(
			VoceDettaglioAstaType prezziUnitari
	);

    public String getMandatariaRti(
			  @Param("ngara") String nGara
			, @Param("username") String username
	);

    public Integer getTipoRilancioAsta(
			  @Param("ngara") String nGara
	);

    public Double getTotaleNonSoggettoPrezziUnitari(
			  @Param("ngara") String nGara
	);

}
