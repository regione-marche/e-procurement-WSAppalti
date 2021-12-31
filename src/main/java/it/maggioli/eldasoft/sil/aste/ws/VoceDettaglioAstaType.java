package it.maggioli.eldasoft.sil.aste.ws;

import it.maggioli.eldasoft.sil.gare.ws.VoceDettaglioOffertaType;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoceDettaglioAstaType", propOrder = {
    "asteId",
    "asteIdRilancio",
    "asteCodice",
    "asteIdLavorazione",
    "asteUsername",
    "asteDitta",
    "astePrezzoUnitario",
    "asteImportoUnitario"		
})
public class VoceDettaglioAstaType extends VoceDettaglioOffertaType   
	implements Serializable {

    /**
	 * UID
	 */
	private static final long serialVersionUID = 483291037959902676L;
	protected Long asteId;				
    protected Long asteIdRilancio;		
    protected String asteCodice;			
    protected Long asteIdLavorazione;		
    protected String asteUsername;		
    protected String asteDitta;
    protected Double astePrezzoUnitario;	
    protected Double asteImportoUnitario;
    
	/**
	 * @return the asteId
	 */
	public Long getAsteId() {
		return asteId;
	}
	/**
	 * @param asteId the asteId to set
	 */
	public void setAsteId(Long asteId) {
		this.asteId = asteId;
	}
	/**
	 * @return the asteIdRilancio
	 */
	public Long getAsteIdRilancio() {
		return asteIdRilancio;
	}
	/**
	 * @param asteIdRilancio the asteIdRilancio to set
	 */
	public void setAsteIdRilancio(Long asteIdRilancio) {
		this.asteIdRilancio = asteIdRilancio;
	}
	/**
	 * @return the asteCodice
	 */
	public String getAsteCodice() {
		return asteCodice;
	}
	/**
	 * @param asteCodice the asteCodice to set
	 */
	public void setAsteCodice(String asteCodice) {
		this.asteCodice = asteCodice;
	}
	/**
	 * @return the asteIdLavorazione
	 */
	public Long getAsteIdLavorazione() {
		return asteIdLavorazione;
	}
	/**
	 * @param asteIdLavorazione the asteIdLavorazione to set
	 */
	public void setAsteIdLavorazione(Long asteIdLavorazione) {
		this.asteIdLavorazione = asteIdLavorazione;
	}
	/**
	 * @return the asteUsername
	 */
	public String getAsteUsername() {
		return asteUsername;
	}
	/**
	 * @param asteUsername the asteUsername to set
	 */
	public void setAsteUsername(String asteUsername) {
		this.asteUsername = asteUsername;
	}	
	/**
	 * @return the asteDitta
	 */
	public String getAsteDitta() {
		return asteDitta;
	}
	/**
	 * @param asteDitta the asteDitta to set
	 */
	public void setAsteDitta(String asteDitta) {
		this.asteDitta = asteDitta;
	}
	/**
	 * @return the astePrezzoUnitario
	 */
	public Double getAstePrezzoUnitario() {
		return astePrezzoUnitario;
	}
	/**
	 * @param astePrezzoUnitario the astePrezzoUnitario to set
	 */
	public void setAstePrezzoUnitario(Double astePrezzoUnitario) {
		this.astePrezzoUnitario = astePrezzoUnitario;
	}
	/**
	 * @return the asteImportoUnitario
	 */
	public Double getAsteImportoUnitario() {
		return asteImportoUnitario;
	}
	/**
	 * @param asteImportoUnitario the asteImportoUnitario to set
	 */
	public void setAsteImportoUnitario(Double asteImportoUnitario) {
		this.asteImportoUnitario = asteImportoUnitario;
	}
    
}
