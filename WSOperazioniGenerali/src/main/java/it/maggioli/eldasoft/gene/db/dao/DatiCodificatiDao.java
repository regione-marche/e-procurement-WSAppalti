/*
 * Created on 29/lug/10
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di 
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.gene.db.dao;

import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Interfaccia DAO per l'estrazione dei dati codificati/tabellati
 * 
 * @author Stefano.Sabbadin
 */
public interface DatiCodificatiDao {
	
	  /**
	   * Ritorna l'elenco delle coppie (codice,descrizione) per un determinato tabellato in input su TAB1.
	   * 
	   * @return lista delle codifiche 
	   * 
	   * @throws DataAccessException
	   */
	  List<DatoCodificatoType> getTipiTab1(String codiceTabellato) throws DataAccessException;

	  /**
	   * Ritorna l'elenco delle coppie (codice,descrizione) per un determinato tabellato in input su TAB2, usando TAB2D2 come descrizione.
	   * 
	   * @return lista delle codifiche 
	   * 
	   * @throws DataAccessException
	   */
	  List<DatoCodificatoType> getTipiTab2(String codiceTabellato) throws DataAccessException;

	  /**
	   * Ritorna l'elenco delle coppie (codice,descrizione) per un determinato tabellato in input su TAB3.
	   * 
	   * @return lista delle codifiche
	   * 
	   * @throws DataAccessException
	   */
	  List<DatoCodificatoType> getTipiTab3(String codiceTabellato) throws DataAccessException;
	  
	  /**
	   * Ritorna l'elenco delle coppie (codice,descrizione) per un determinato tabellato in input su TAB5.
	   * 
	   * @return lista delle codifiche
	   * 
	   * @throws DataAccessException
	   */
	  List<DatoCodificatoType> getTipiTab5(String codiceTabellato) throws DataAccessException;

	  /**
	   * Restituisce la lista delle coppie descrizione nazione, codice paese
	   * @return lista delle codifiche
	   * @throws DataAccessException
	   */
	  List<DatoCodificatoType> getNazioniCodificate() throws DataAccessException;

	  List<DatoCodificatoType> getFormeGiuridicheCooperative() throws DataAccessException;
	  String getNazioniCodificateDGUE(String nazione);
	  
}
