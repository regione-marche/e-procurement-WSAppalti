package it.maggioli.eldasoft.gene.db.mapper;

import it.maggioli.eldasoft.gene.ws.AllegatoComunicazioneType;
import it.maggioli.eldasoft.gene.ws.DettaglioComunicazioneType;
import it.maggioli.eldasoft.gene.ws.WSAllegatoType;
import it.maggioli.eldasoft.gene.ws.WSDocumentoType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ComunicazioniMapper {

    public Long getMaxIdComunicazione(
            @Param("idApplicativo") String idApplicativo
    );
    public int insertComunicazione(
              @Param("applicativo") String applicativo
            , @Param("id") Long id    //Controllare
            , @Param("entita") String entita
            , @Param("chiave1") String chiave1
            , @Param("chiave2") String chiave2
            , @Param("chiave3") String chiave3
            , @Param("chiave4") String chiave4
            , @Param("chiave5") String chiave5
            , @Param("idOperatore") Long idOperatore
            , @Param("dataInserimento") java.util.Date dataInserimento
            , @Param("mittente") String mittente
            , @Param("stato") String stato
            , @Param("oggetto") String oggetto
            , @Param("testo") String testo
            , @Param("comunicazionePubblica") Integer comunicazionePubblica
            , @Param("tipoComunicazione") String tipoComunicazione
            , @Param("dataAggStato") java.util.Date dataAggStato
            , @Param("dataPubblicazione") java.util.Date dataPubblicazione
            , @Param("sessionKey") String sessionKey
            , @Param("applicativoRisposta") String applicativoRisposta
            , @Param("idRisposta") Long idRisposta
            , @Param("modello") Long modello
            , @Param("tipoBusta") Long tipoBusta
    );
    public int updateComunicazione(
            @Param("entita") String entita
            , @Param("chiave1") String chiave1
            , @Param("chiave2") String chiave2
            , @Param("chiave3") String chiave3
            , @Param("chiave4") String chiave4
            , @Param("chiave5") String chiave5
            , @Param("idOperatore") Long idOperatore
            , @Param("mittente") String mittente
            , @Param("stato") String stato
            , @Param("oggetto") String oggetto
            , @Param("testo") String testo
            , @Param("comunicazionePubblica") Integer comunicazionePubblica
            , @Param("tipoComunicazione") String tipoComunicazione
            , @Param("dataAggStato") java.util.Date dataAggStato
            , @Param("dataPubblicazione") java.util.Date dataPubblicazione
            , @Param("sessionKey") String sessionKey
            , @Param("applicativoRisposta") String applicativoRisposta
            , @Param("idRisposta") Long idRisposta
            , @Param("modello") Long modello
            , @Param("tipoBusta") Long tipoBusta
            , @Param("applicativo") String applicativo
            , @Param("id") Long id    //Da controllare
    );
    public int deleteComunicazione(
             @Param("applicativo") String applicativo
            , @Param("idComunicazione") Long idComunicazione
    );
    public Long getMaxIdAllegato(
            @Param("idApplicativo") String idApplicativo
    );
    public int insertAllegato(
            @Param("applicativo") String applicativo
            , @Param("id") Long id
            , @Param("entita") String entita
            , @Param("idComunicazione") Long idComunicazione
            , @Param("progressivo") Integer progressivo
            , @Param("uuid") String uuid
            , @Param("tipo") String tipo
            , @Param("nomeFile") String nomeFile
            , @Param("descrizione") String descrizione
            , @Param("file") byte[] file
            , @Param("firmacheck") String firmacheck
            , @Param("firmacheckts") java.util.Date firmacheckts
    );
    public int updateAllegato(
            @Param("progressivo") Integer progressivo
            , @Param("tipo") String tipo
            , @Param("nomeFile") String nomeFile
            , @Param("descrizione") String descrizione
            , @Param("file") byte[] file
            , @Param("uuid") String uuid
            , @Param("firmacheck") String firmacheck
            , @Param("firmacheckts") java.util.Date firmacheckts
            , @Param("applicativo") String applicativo
            , @Param("id") Long id
            , @Param("entita") String entita
            , @Param("idComunicazione") Long idComunicazione
    );
    public int deleteAllegato(
            @Param("applicativo") String applicativo
            , @Param("id") Long id
    );
    public int deleteWSAllegato(
            @Param("applicativo") String applicativo
            , @Param("id") Long id
    );
    public int deleteAllegati(
            @Param("applicativo") String applicativo
            , @Param("entita") String entita
            , @Param("idComunicazione") String idComunicazione

    );
    public int updateStatoComunicazioni(
            @Param("stato") String stato
            , @Param("comunicazioni") DettaglioComunicazioneType[] comunicazioni
    );
    public int updateDataLetturaDestinatarioComunicazione(
        @Param("dataLettura") java.util.Date dataLettura
        , @Param("applicativo") String applicativo
        , @Param("idComunicazione") Long idComunicazione
        , @Param("idDestinatario") Long idDestinatario
    );
    public DettaglioComunicazioneType getDettaglioComunicazione(
        @Param("applicativo") String applicativo
        , @Param("idComunicazione") Long idComunicazione
    );
    public List<AllegatoComunicazioneType> getAllegatiComunicazione(
            @Param("applicativo") String applicativo
            , @Param("entita") String entita
            , @Param("idComunicazione") String idComunicazione
            , @Param("idDocumento") String idDocumento
    );
    public List<DettaglioComunicazioneType> getElencoComunicazioni(
            DettaglioComunicazioneType criteriRicerca
    );
    public Integer isComunicazioneProcessata(
             @Param("applicativo") String applicativo
            , @Param("idComunicazione") Long idComunicazione
            , @Param("stati") String[] stati
    );
    public int updateProtocolloComunicazione(
            @Param("stato") String stato
            , @Param("numeroProtocollo") String numeroProtocollo
            , @Param("dataProtocollo") java.util.Date dataProtocollo
            , @Param("applicativo") String applicativo
            , @Param("idComunicazione") Long idComunicazione
    );
    public int insertDocumentoProtocollo(
              @Param("id") Long id
            , @Param("dt") WSDocumentoType documentoType
    );
    public int insertAllegatoProtocollo(
            @Param("id") Long id
            , @Param("dt") WSAllegatoType documentoType
    );
    public int deleteAllegatoProtocollo(
    		@Param("entita") String entita
    		, @Param("applicativo") String applicativo
            , @Param("id") Long id
    );
    public int updateSessionKeyComunicazione(
            @Param("stato") String stato
            , @Param("sessionKey") String sessionKey
            , @Param("applicativo") String applicativo
            , @Param("idComunicazione") Long idComunicazione
    );
}
