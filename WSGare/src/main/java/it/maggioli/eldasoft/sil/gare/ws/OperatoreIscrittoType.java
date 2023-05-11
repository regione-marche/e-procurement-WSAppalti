package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperatoreIscrittoType", propOrder = {
    "ditta",
    "denominazione",
    "codiceFiscale",
    "partitaIva",
    "localita",
    "provincia"
})
public class OperatoreIscrittoType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	protected String ditta;
	protected String denominazione;
	protected String codiceFiscale;
	protected String partitaIva;
	protected String localita;
	protected String provincia;
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
	 * @return the denominazione
	 */
	public String getDenominazione() {
		return denominazione;
	}
	/**
	 * @param denominazione the denominazione to set
	 */
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	/**
	 * @return the partitaIva
	 */
	public String getPartitaIva() {
		return partitaIva;
	}
	/**
	 * @param partitaIva the partitaIva to set
	 */
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	/**
	 * @return the localita
	 */
	public String getLocalita() {
		return localita;
	}
	/**
	 * @param localita the localita to set
	 */
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

}
