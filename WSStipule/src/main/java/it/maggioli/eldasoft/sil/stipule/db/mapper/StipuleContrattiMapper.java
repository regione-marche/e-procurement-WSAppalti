package it.maggioli.eldasoft.sil.stipule.db.mapper;

import it.maggioli.eldasoft.sil.stipule.ws.DocumentazioneStipulaContrattiType;
import it.maggioli.eldasoft.sil.stipule.ws.StipulaContrattoType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface StipuleContrattiMapper {
    
    public List<StipulaContrattoType> searchStipuleContratti(
			  @Param("codiceStipula") String codiceStipula
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("stato") Integer stato
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("usernome") String usernome
			, RowBounds bounds
	);
    
    public StipulaContrattoType getDettaglioStipulaContratto(
			  @Param("codiceStipula") String codiceStipula
			, @Param("idstipula") String idStipula
			, @Param("usernome") String usernome
			, @Param("pubblicata") Boolean pubblicata
	);

    public List<DocumentazioneStipulaContrattiType> getDocumentiRichiestiStipulaContratto(
			  @Param("idStipula") String idStipula
	);

    public Integer countStipuleContratti(
			  @Param("codiceStipula") String codiceStipula
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("stato") Integer stato
			, @Param("stazioneAppaltante") String stazioneAppaltante
		 	, @Param("usernome") String username
	);

	public Integer deleteAllegato(
			  @Param("applicativo") String applicativo
			, @Param("id") String idDocumento
	);

    public Integer updateStipula(
			  @Param("codiceStipula") String codStipula
			, @Param("stato") Long stato
    );

	public Integer updateDocStipula(
			  @Param("codiceStipula") String codStipula
			, @Param("stato") Long stato
	);

	public Integer updateNoteDoc(
			  @Param("id") Long idDocStipula
			, @Param("note") String note
	);

}
