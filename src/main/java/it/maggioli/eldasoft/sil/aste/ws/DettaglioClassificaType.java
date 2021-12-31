package it.maggioli.eldasoft.sil.aste.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DettaglioClassificaType", propOrder = {
	"id",
	"username",
	"mandataria",
	"ragioneSociale",
	"importo",
	"posizione"
})
public class DettaglioClassificaType implements Serializable {

	private static final long serialVersionUID = 4845128039194573099L;	
	protected Long id;
	protected String username;		  // username	
	protected String mandataria;	  // codice della mandataria in caso di RTI
	protected String ragioneSociale;  // descrizione dello username o della mandataria
	protected Double importo;
	protected Integer posizione;
	
	public DettaglioClassificaType() {}
	
	public DettaglioClassificaType(DettaglioClassificaType obj) {
		this.id = obj.id;
		this.username = obj.username;
		this.mandataria = obj.mandataria;
		this.ragioneSociale = obj.ragioneSociale;		
		this.importo = obj.importo;
		this.posizione = obj.posizione;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the mandataria
	 */
	public String getMandataria() {
		return mandataria;
	}

	/**
	 * @param mandataria the mandataria to set
	 */
	public void setMandataria(String mandataria) {
		this.mandataria = mandataria;
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
	 * @return the importo
	 */
	public Double getImporto() {
		return importo;
	}

	/**
	 * @param importo the importo to set
	 */
	public void setImporto(Double importo) {
		this.importo = importo;
	}

	/**
	 * @return the posizione
	 */
	public Integer getPosizione() {
		return posizione;
	}

	/**
	 * @param posizione the posizione to set
	 */
	public void setPosizione(Integer posizione) {
		this.posizione = posizione;
	}
	
}
