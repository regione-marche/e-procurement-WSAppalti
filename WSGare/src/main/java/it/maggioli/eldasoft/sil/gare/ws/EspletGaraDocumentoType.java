package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EspletGaraDocumentoType", propOrder = {
    "busta",
    "posizione", 
    "descrizione",    
    "filename",
    "dataLettura"
})
public class EspletGaraDocumentoType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -4742621555091955070L;
	
	public static final String ESPLETAMENTO_GARA_BUSTA_AMMINISTRATIVA 	= "1";
	public static final String ESPLETAMENTO_GARA_BUSTA_TECNICA 			= "2";
	public static final String ESPLETAMENTO_GARA_BUSTA_ECONOMICA		= "3";
	
	protected String busta;
	protected String posizione; 
	protected String descrizione;
	protected String filename;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataLettura;
	
	/**
	 * @return the busta
	 */
	public String getBusta() {
		return busta;
	}
	/**
	 * @param busta the busta to set
	 */
	public void setBusta(String busta) {
		this.busta = busta;
	}
	/**
	 * @return the posizione
	 */
	public String getPosizione() {
		return posizione;
	}
	/**
	 * @param posizione the posizione to set
	 */
	public void setPosizione(String posizione) {
		this.posizione = posizione;
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
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public Date getDataLettura() {
		return dataLettura;
	}
	
	public void setDataLettura(Date dataLettura) {
		this.dataLettura = dataLettura;
	}

}
