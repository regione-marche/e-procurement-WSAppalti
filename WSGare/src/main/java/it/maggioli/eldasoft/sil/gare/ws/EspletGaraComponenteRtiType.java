package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EspletGaraComponenteRtiType", propOrder = {
    "codice",
    "ragioneSociale", 
    "codiceFiscale",    
    "partitaIva",
    "mandataria"
})
public class EspletGaraComponenteRtiType implements Serializable {
	/**
	 * UID
	 */	
	private static final long serialVersionUID = -8358481377342688315L;
	protected String codice;
	protected String ragioneSociale; 
	protected String codiceFiscale;    
	protected String partitaIva;
	protected Boolean mandataria;
	
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
	 * @return the ragioneSociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	/**
	 * @param ragioneSociale the ragioneSociale to set
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
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
	 * @return the mandataria
	 */
	public Boolean getMandataria() {
		return mandataria;
	}
	/**
	 * @param mandataria the mandataria to set
	 */
	public void setMandataria(Boolean mandataria) {
		this.mandataria = mandataria;
	}
}
