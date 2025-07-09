package it.maggioli.eldasoft.sil.gare.db.dao.mybatis;

import it.eldasoft.utils.sql.comp.SqlComposer;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.eldasoft.utils.sql.comp.SqlManager;
import it.maggioli.eldasoft.sil.gare.db.dao.ElenchiDao;
import it.maggioli.eldasoft.sil.gare.db.mapper.ElenchiMapper;
import it.maggioli.eldasoft.sil.gare.ws.dto.ElencoOperatoriAbilitatiElenco;
import it.maggioli.eldasoft.sil.gare.ws.search.ElencoOperatoriAbilitatiSearch;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static it.maggioli.eldasoft.sil.gare.util.DAOUtils.getBounds;

@Component
public class SqlMapElenchiDao implements ElenchiDao {

    @Autowired
    private ElenchiMapper elenchiMapper;
    @Resource
    private String trimFun;
    @Resource
    private String dbms;


    public long countElencoOperatoriAbilitatiElenchi(ElencoOperatoriAbilitatiSearch search, boolean isRer) throws SqlComposerException {
        SqlComposer composer = SqlManager.getComposer(this.dbms);
        return elenchiMapper.countElencoOperatoriAbilitatiElenchi(
                search
                , isRer
                , trimFun
                , composer.getFunzioneUpperCase()
        );
    }

    public Cursor<ElencoOperatoriAbilitatiElenco> getElencoOperatoriAbilitatiElenchi(ElencoOperatoriAbilitatiSearch search, boolean isRer) throws SqlComposerException {
        SqlComposer composer = SqlManager.getComposer(this.dbms);
        return elenchiMapper.getElencoOperatoriAbilitatiElenchi(
                search
                , isRer
                , trimFun
                , composer.getFunzioneUpperCase()
                , getBounds(search.getIndicePrimoRecord(), search.getMaxNumRecord())
        );
    }

}
