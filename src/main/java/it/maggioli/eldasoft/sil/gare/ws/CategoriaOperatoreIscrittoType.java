package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoriaOperatoreIscrittoType", propOrder = {
    "ditta",
    "codice",
    "descrizione",
    "tipoAppalto",
    "classificaMinima",
    "classificaMassima",
    "livello",
    "foglia"
})
public class CategoriaOperatoreIscrittoType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	protected String ditta;
	protected String codice;
	protected String descrizione;
	protected String tipoAppalto;
	protected String classificaMinima;
	protected String classificaMassima;
	protected int livello;
	protected boolean foglia;
	
	/**
	 * @return the ditta
	 */
	public String getDitta() {
		return ditta;
	}
	/**
	 * @param ditta the ditta to set
	 */
	public void setDitta(String ditta) {
		this.ditta = ditta;
	}
	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}
	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}
	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * @return the tipoAppalto
	 */
	public String getTipoAppalto() {
		return tipoAppalto;
	}
	/**
	 * @param tipoAppalto the tipoAppalto to set
	 */
	public void setTipoAppalto(String tipoAppalto) {
		this.tipoAppalto = tipoAppalto;
	}
	/**
	 * @return the classificaMinima
	 */
	public String getClassificaMinima() {
		return classificaMinima;
	}
	/**
	 * @param classificaMinima the classificaMinima to set
	 */
	public void setClassificaMinima(String classificaMinima) {
		this.classificaMinima = classificaMinima;
	}
	/**
	 * @return the classificaMassima
	 */
	public String getClassificaMassima() {
		return classificaMassima;
	}
	/**
	 * @param classificaMassima the classificaMassima to set
	 */
	public void setClassificaMassima(String classificaMassima) {
		this.classificaMassima = classificaMassima;
	}
	/**
	 * @return the livello
	 */
	public int getLivello() {
		return livello;
	}
	/**
	 * @param livello the livello to set
	 */
	public void setLivello(int livello) {
		this.livello = livello;
	}
	/**
	 * @return the foglia
	 */
	public boolean isFoglia() {
		return foglia;
	}
	/**
	 * @param foglia the foglia to set
	 */
	public void setFoglia(boolean foglia) {
		this.foglia = foglia;
	}	
}
