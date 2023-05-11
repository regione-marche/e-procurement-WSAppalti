package it.maggioli.eldasoft.sil.lfs.db.mapper;

import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.lfs.ws.ContrattoLFSType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface ContrattiLFSMapper {

    public List<ContrattoLFSType> searchContrattiLFS(
            @Param("username") String username
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("cig") String cig
            , @Param("gara") String gara
            , @Param("codice") String codice
            , RowBounds bounds
    );
    public Integer countContrattiLFS(
            @Param("username") String username
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("cig") String cig
            , @Param("gara") String gara
            , @Param("codice") String codice
    );
    public ContrattoLFSType getDettaglioContrattoLFS(
            @Param("username") String username
            , @Param("codice") String codice
            , @Param("nappal") String napal
    );
    public String getListaNgaraLFS(
            @Param("clavor") String clavor
            , @Param("numera") String numera
    );
    public String getListaCigLFS(
            @Param("clavor") String clavor
            , @Param("numera") String numera
    );
    public List<DocumentoAllegatoType> getAllegatiContrattoLFS(
            @Param("codice") String codice
    );

}
