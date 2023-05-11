package it.maggioli.eldasoft.sil.gare.db.mapper;

import it.maggioli.eldasoft.sil.gare.ws.ContrattoType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContrattiMapper {
    
    public List<ContrattoType> searchContratti(
              @Param("username") String username
            , @Param("stazioneAppaltante") String stazioneAppaltante
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("oggetto") String oggetto
            , @Param("cig") String cig
            , @Param("stato") Integer stato
    );
    
    public ContrattoType getDettaglioContratto(
              @Param("codice") String codice
    );
    
    public List<DocumentoAllegatoType> getAllegatiContratto(
            @Param("codice") String codice
    );
    
}
