package it.maggioli.eldasoft.sil.gare.db.mapper;

import it.maggioli.eldasoft.sil.gare.ws.ArticoloType;
import it.maggioli.eldasoft.sil.gare.ws.CategoriaCatalogoType;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.gare.ws.ProdottoType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface CataloghiMapper {
    
    public List<ArticoloType> searchArticoli(
              @Param("username") String username
            , @Param("codiceCatalogo") String codiceCatalogo
            , @Param("codiceCategoria") String codiceCategoria
            , @Param("codice") String codice
            , @Param("tipo") String tipo
			, @Param("operatoreUpper") String operatoreUpper
            , @Param("descrizione") String descrizione
            , @Param("colore") String colore
            , RowBounds bounds
	);
    
    public Integer countSearchArticoli(
              @Param("username") String username
            , @Param("codiceCatalogo") String codiceCatalogo
            , @Param("codiceCategoria") String codiceCategoria
            , @Param("codice") String codice
            , @Param("tipo") String tipo
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("descrizione") String descrizione
            , @Param("colore") String colore
	);
    
    public ArticoloType getArticolo(
			  @Param("id") Long id
	);

    
    public List<DocumentoAllegatoType> getDocumentiArticolo(
              @Param("idartcat") Long id
			, @Param("tipoDoc") Integer tipoDoc
	);

    
    public Long getNumProdottiOEInArticolo(
              @Param("idArticolo") Long idArticolo
			, @Param("username") String username
	);

    
    public Long getNumProdottiAltriOEInArticolo(
              @Param("idArticolo") Long idArticolo
			, @Param("username") String username
	);

    
    public Double getPrezzoMiglioreArticolo(
            @Param("idArticolo") Long idArticolo
	);

    
    public Integer isImpresaAbilitataCatalogo(
              @Param("username") String username
            , @Param("codiceCatalogo") String codiceCatalogo
	);

    
    public List<ProdottoType> searchProdotti(
			  @Param("codiceCatalogo") String codiceCatalogo
            , @Param("stato") String stato
            , @Param("username") String username
            , @Param("idArticolo") Long idArticolo
			, @Param("terminiRicerca") List<String> terminiRicerca
            , @Param("operatoreUpper") String operatoreUpper
            , RowBounds bounds
    );
    
    public Integer countSearchProdotti(
			  @Param("codiceCatalogo") String codiceCatalogo
            , @Param("stato") String stato
            , @Param("username") String username
            , @Param("idArticolo") Long idArticolo
			, @Param("terminiRicerca") List<String> terminiRicerca
            , @Param("operatoreUpper") String operatoreUpper
    );
    
    public List<ProdottoType> advancedSearchProdotti(
			  @Param("codiceCatalogo") String codiceCatalogo
            , @Param("stato") Long stato
			, @Param("username") String username
            , @Param("idArticolo") Long idArticolo
            , @Param("terminiRicerca") List<String> terminiRicerca
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("tipoArticolo") String tipoArticolo
			, @Param("descrizioneArticolo") String descrizioneArticolo
			, @Param("coloreArticolo") String coloreArticolo
			, @Param("codiceProdottoFornitore") String codiceProdottoFornitore
			, @Param("nomeCommerciale") String nomeCommerciale
			, @Param("descrizioneAggiuntiva") String descrizioneAggiuntiva
			, @Param("statoProdotto") String statoProdotto
            , RowBounds bounds
	);
    
    public Integer countAdvancedSearchProdotti(
              @Param("codiceCatalogo") String codiceCatalogo
            , @Param("stato") Long stato
            , @Param("username") String username
            , @Param("idArticolo") Long idArticolo
            , @Param("terminiRicerca") List<String> terminiRicerca
            , @Param("operatoreUpper") String operatoreUpper
            , @Param("tipoArticolo") String tipoArticolo
            , @Param("descrizioneArticolo") String descrizioneArticolo
            , @Param("coloreArticolo") String coloreArticolo
            , @Param("codiceProdottoFornitore") String codiceProdottoFornitore
            , @Param("nomeCommerciale") String nomeCommerciale
            , @Param("descrizioneAggiuntiva") String descrizioneAggiuntiva
            , @Param("statoProdotto") String statoProdotto
    );
    
    public ProdottoType getProdotto(
              @Param("id") Long id
	);

    
    public List<DocumentoAllegatoType> getAllegatiProdotto(
              @Param("idProdotto") Long id
			, @Param("tipoDocumento") Integer tipoDocumento
	);

    
    public List<CategoriaCatalogoType> getCategorieIscritte(
			  @Param("codiceCatalogo") String codiceCatalogo
			, @Param("username") String username
			, @Param("livello1") String livello1
			, @Param("livello2") String livello2
			, @Param("livello3") String livello3
			, @Param("livello4") String livello4
	);
}
