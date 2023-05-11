/*
 * Created on 07-lug-2006
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.gene.bl;

import it.maggioli.eldasoft.gene.db.dao.GenChiaviDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Manager che si occupa di gestire le operazioni di business logic per la generazioni delle chiavi come contatore e come sequence.
 *
 * @author Luca.Giacomazzo
 */
@Service
public class GenChiaviManager {

  @Autowired
  private GenChiaviDao genChiaviDao;


  /**
   * Ritorna il prossimo id univoco da utilizzare per un inserimento, sfruttando W_GENCHIAVI.
   *
   * @param tabella
   *        nome della tabella
   * @return id da utilizzare per l'inserimento
   */
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public long getNextId(String tabella) {
    return this.genChiaviDao.getNextId(tabella, 1);
  }

  /**
   * Ritorna il prossimo id univoco da utilizzare <b>per una serie di inserimenti</b>, sfruttando W_GENCHIAVI. L'occorrenza in W_GENCHIAVI
   * verr&agrave; fatta avanzare del numIdAllocati in modo tale da poterli utilizzare liberamente senza effettuare ulteriori richieste di
   * Id.
   *
   * @param tabella
   *        nome della tabella
   * @param numIdAllocati
   *        numero di id da generare
   * @return id da utilizzare per il prossimo inserimento, da incrementare un numero di volte pari a numIdAllocati
   */
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public long getNextId(String tabella, int numIdAllocati) {
    return this.genChiaviDao.getNextId(tabella, numIdAllocati);
  }

  /**
   * Ritorna l'ultimo id utilizzato per inserire nella tabella.
   *
   * @param tabella
   *        nome della tabella
   * @param chiave
   *        campo chiave numerico
   * @return ultimo id inserito, 0 altrimenti
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public long getMaxId(String tabella, String chiave) {
    return this.genChiaviDao.getMaxId(tabella, chiave, null);
  }

  /**
   * Ritorna l'ultimo id utilizzato per inserire nella tabella con l'aggiunta di eventuali condizioni di filtro.
   *
   * @param tabella
   *        nome della tabella
   * @param chiave
   *        campo chiave numerico
   * @return ultimo id inserito, 0 altrimenti
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public long getMaxId(String tabella, String chiave, String condizioniExtra) {
    return this.genChiaviDao.getMaxId(tabella, chiave, condizioniExtra);
  }

}
