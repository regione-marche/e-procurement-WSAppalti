package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategorieOperatoriIscrittiOutType", propOrder = {
    "categorie",
    "errore"
})
public class CategorieOperatoriIscrittiOutType implements Serializable {	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	protected List<CategoriaOperatoreIscrittoType> categorie;
    protected String errore;
    
	/**
	 * @return the categorie
	 */
	public List<CategoriaOperatoreIscrittoType> getCategorie() {
		return categorie;
	}
	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(List<CategoriaOperatoreIscrittoType> categorie) {
		this.categorie = categorie;
	}
	/**
	 * @return the errore
	 */
	public String getErrore() {
		return errore;
	}
	/**
	 * @param errore the errore to set
	 */
	public void setErrore(String errore) {
		this.errore = errore;
	}
     
}
