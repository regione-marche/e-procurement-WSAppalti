package it.maggioli.eldasoft.gene.db.mapper;

import it.maggioli.eldasoft.gene.ws.FileType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DocumentiMapper {

    public List<FileType> getDocumentoDigitale(
              @Param("id") Long id
            , @Param("prg") String prg
            , @Param("username") String username
    );
    public List<String> getUsernameDocumentoRiservato(
              @Param("id") Long id
            , @Param("prg") String prg
    );

}
