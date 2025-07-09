
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImpresaAusiliariaType", propOrder = {
    "ragioneSociale",
	"tipoImpresa",
	"ambitoTerritoriale",
	"nazione",
	"codiceFiscale",
	"partitaIVA",
	"idFiscaleEstero",
	"avvalimentoPer"
})
public class ImpresaAusiliariaType implements Serializable {    
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	//@XmlElement(required = true)
	protected String ragioneSociale;
	protected String tipoImpresa;
	protected String ambitoTerritoriale;
	protected String nazione;
	protected String codiceFiscale;
	protected String partitaIVA;
	protected String idFiscaleEstero;
	protected String avvalimentoPer;
	
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	
	public String getTipoImpresa() {
		return tipoImpresa;
	}
	
	public void setTipoImpresa(String tipoImpresa) {
		this.tipoImpresa = tipoImpresa;
	}
	
	public String getAmbitoTerritoriale() {
		return ambitoTerritoriale;
	}
	
	public void setAmbitoTerritoriale(String ambitoTerritoriale) {
		this.ambitoTerritoriale = ambitoTerritoriale;
	}
	
	public String getNazione() {
		return nazione;
	}
	
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	
	public String getPartitaIVA() {
		return partitaIVA;
	}
	
	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}
	
	public String getIdFiscaleEstero() {
		return idFiscaleEstero;
	}
	
	public void setIdFiscaleEstero(String idFiscaleEstero) {
		this.idFiscaleEstero = idFiscaleEstero;
	}
	
	public String getAvvalimentoPer() {
		return avvalimentoPer;
	}
	
	public void setAvvalimentoPer(String avvalimentoPer) {
		this.avvalimentoPer = avvalimentoPer;
	}
	
}
