/*
 * Created on 15/mag/18
 *
 * Copyright (c) Maggioli S.p.A. - Divisione ELDASOFT
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di Maggioli S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di
 * aver prima formalizzato un accordo specifico con Maggioli.
 */
package it.maggioli.eldasoft.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe contenente le specifiche del DBMS in uso.
 * E' la versione "moderna" della DBMSSpecs nelle eldasoft-utils
 * E' uguale alla DBMSSpecs in WSBandiEsitiAvvisi
 * 
 * @author Stefano.Sabbadin
 */
@Component
public class DBMSSpecs {

	/** Costante che individua la tipologia di database Oracle. */
	public static final String DATABASE_ORACLE = "ORA";

	/** Costante che individua la tipologia di database Microsoft SQL Server. */
	public static final String DATABASE_SQL_SERVER = "MSQ";

	/** Costante che individua la tipologia di database PostgreSQL. */
	public static final String DATABASE_POSTGRES = "POS";

	/** Costante che individua la tipologia di database IBM DB2. */
	public static final String DATABASE_DB2 = "DB2";

	/**
	 * Datasource di connessione al DB.
	 */
	@Autowired
	private DataSource dataSource;

	/** Nome commerciale del DBMS utilizzato. */
	private String productName;

	/** Versione del DBMS utilizzato. */
	private String productVersion;

	/** Codifica interna del DBMS. */
	private String internalDbmsCode;

	/**
	 * Dopo che spring abbia valorizzato la variable dataSource, imposto le altre variabili dipendenti dal datasource.
	 * @throws SQLException
	 */
	@PostConstruct
	public void initFields() throws SQLException {
		try (Connection c = dataSource.getConnection()) {
			this.productName = c.getMetaData().getDatabaseProductName();
			this.productVersion = c.getMetaData().getDatabaseProductVersion();
			if ("Oracle".equalsIgnoreCase(productName)) {
				this.internalDbmsCode = DBMSSpecs.DATABASE_ORACLE;
			} else if ("Microsoft SQL Server".equalsIgnoreCase(productName)) {
				this.internalDbmsCode = DBMSSpecs.DATABASE_SQL_SERVER;
			} else if ("PostgreSQL".equalsIgnoreCase(productName)) {
				this.internalDbmsCode = DBMSSpecs.DATABASE_POSTGRES;
			} else if (StringUtils.startsWithIgnoreCase(productName, "DB2/")) {
				this.internalDbmsCode = DBMSSpecs.DATABASE_DB2;
			}
		}
	}
	
	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @return Ritorna productName.
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @return Ritorna productVersion.
	 */
	public String getProductVersion() {
		return productVersion;
	}

	/**
	 * @return Ritorna internalDbmsCode.
	 */
	public String getInternalDbmsCode() {
		return internalDbmsCode;
	}

}