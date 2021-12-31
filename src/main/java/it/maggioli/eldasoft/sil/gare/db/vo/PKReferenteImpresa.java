package it.maggioli.eldasoft.sil.gare.db.vo;

import java.io.Serializable;

/**
 * Wrapper della classe per l'estrazione delle chiavi dei referenti di
 * un'impresa.
 * 
 * @author Stefano.Sabbadin
 */
public class PKReferenteImpresa implements Serializable {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 7784671705877481844L;

	/** Chiave del tecnico nell'archivio dei tecnici dell'impresa. */
	private String codice;

	/** Incarico. */
	private Integer incarico;

	/** Eventuale qualifica. */
	private Integer qualifica;
	
	/** Id */
	private Integer id;

	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @param codice
	 *            the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * @return the incarico
	 */
	public Integer getIncarico() {
		return incarico;
	}

	/**
	 * @param incarico
	 *            the incarico to set
	 */
	public void setIncarico(Integer incarico) {
		this.incarico = incarico;
	}

	/**
	 * @return the qualifica
	 */
	public Integer getQualifica() {
		return qualifica;
	}

	/**
	 * @param qualifica
	 *            the qualifica to set
	 */
	public void setQualifica(Integer qualifica) {
		this.qualifica = qualifica;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
}
