package it.maggioli.eldasoft.sil.gare.bl;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.gare.db.dao.ElenchiDao;
import it.maggioli.eldasoft.sil.gare.ws.dto.ElencoOperatoriAbilitatiElenco;
import it.maggioli.eldasoft.sil.gare.ws.search.ElencoOperatoriAbilitatiSearch;
import org.apache.ibatis.cursor.Cursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ElenchiManager {

    private static final Logger log = LoggerFactory.getLogger(ElenchiManager.class);

    @Autowired
    private ElenchiDao elenchiDao;

    public long countElencoOperatoriAbilitatiElenchi(ElencoOperatoriAbilitatiSearch search, boolean isRer) {
        long count = 0;
        try {
            count =  elenchiDao.countElencoOperatoriAbilitatiElenchi(search, isRer);
        } catch (SqlComposerException e) {
            log.error("Error while retrieving: countElencoOperatoriAbilitatiElenchi", e);
        }
        return count;
    }

    public List<ElencoOperatoriAbilitatiElenco> getElencoOperatoriAbilitatiElenchi(ElencoOperatoriAbilitatiSearch search, boolean isRer) {
        List<ElencoOperatoriAbilitatiElenco> output = new ArrayList<>();

        try (Cursor<ElencoOperatoriAbilitatiElenco> returned = elenchiDao.getElencoOperatoriAbilitatiElenchi(search, isRer)) {
            // tabellato A1075 di tab1 per il campo DITG.ABILITAZ 
            // 1  Abilitato
            // 2  Non abilitato
            // 3  Verifica documentazione in corso
            // 4  Docum.conforme (attesa abilitaz.)
            // 5  Revocato
            // 6  Attesa verifica domanda
            // 7  Sospeso
            // 8  Sospeso per iscrizione scaduta
            // 9  Soccorso istruttorio in corso
            // 10 Rinuncia iscrizione
            returned.forEach(it -> {
            	if(it.getAbilitazione().intValue() == 1 || it.getAbilitazione().intValue() == 8)
            		output.add(it);
            });
        } catch (IOException | SqlComposerException e) {
            log.error("Error while retrieving data from the query: getElencoOperatoriAbilitatiElenchi;", e);
        }

        return output;
    }

}
