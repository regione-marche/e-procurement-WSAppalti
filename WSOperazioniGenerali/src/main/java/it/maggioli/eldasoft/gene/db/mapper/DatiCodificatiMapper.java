package it.maggioli.eldasoft.gene.db.mapper;

import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DatiCodificatiMapper {

    List<DatoCodificatoType> getTipiTab1(
              @Param("codice") String codice
    );
    List<DatoCodificatoType> getTipiTab2(
            @Param("codice") String codice
    );
    List<DatoCodificatoType> getTipiTab3(
            @Param("codice") String codice
    );
    List<DatoCodificatoType> getTipiTab4(
            @Param("codice") String codice
    );
    List<DatoCodificatoType> getTipiTab5(
            @Param("codice") String codice
    );
    List<DatoCodificatoType> getNazioniCodificate();

    List<DatoCodificatoType> getFormeGiuridicheCooperative();

    String getNazioniCodificateDGUE(
            @Param("nazione") String nazione
    );

}
