package it.maggioli.eldasoft.sil.gare.db.dao;


import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.gare.ws.dto.ElencoOperatoriAbilitatiElenco;
import it.maggioli.eldasoft.sil.gare.ws.search.ElencoOperatoriAbilitatiSearch;
import org.apache.ibatis.cursor.Cursor;

public interface ElenchiDao {

    long countElencoOperatoriAbilitatiElenchi(ElencoOperatoriAbilitatiSearch search, boolean isRer) throws SqlComposerException;
    Cursor<ElencoOperatoriAbilitatiElenco> getElencoOperatoriAbilitatiElenchi(ElencoOperatoriAbilitatiSearch search, boolean isRer) throws SqlComposerException;

}
