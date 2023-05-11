package it.maggiolil.eldasoft.gene.bl;

import it.maggioli.eldasoft.gene.bl.DatiCodificatiManager;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;
import it.maggiolil.eldasoft.BaseDBTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
@ContextConfiguration("file:src/test/resources/applicationContext.xml")
public class TestDatiCodificatiManager extends BaseDBTest {

    @Autowired
    private DatiCodificatiManager manager;

    @Test
    public void test00_getNazioniCodificate() {
        System.out.println("Something");
        List<DatoCodificatoType> nazioniCodificate = manager.getNazioniCodificate();
        if (nazioniCodificate != null)
            nazioniCodificate.forEach(it -> {
                System.out.println(it.getCodice() + " " + it.getDescrizione());
            });

    }
    @Test
    public void test01_getTipiTab1() {
        System.out.println("Something");
        List<DatoCodificatoType> nazioniCodificate = manager.getTipiTab1("1007");
        if (nazioniCodificate != null && nazioniCodificate.size() > 0)
            nazioniCodificate.forEach(it -> {
                System.out.println(it.getCodice() + " " + it.getDescrizione());
            });
        else
            System.out.println("Chiamato senza errori");

    }


}
