package it.maggioli.eldasoft.gene.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GenChiaviMapper {

    public Long getNextId(
          @Param("tabella") String tabella
    );
    public Long updateId(
          @Param("incremento") Integer incremento
        , @Param("tabella") String tabella
    );
    public Long getMaxId(
          @Param("chiave") String chiave
        , @Param("tabella") String tabella
        , @Param("condizioniExtra") String condizioniExtra
    );

}
