package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CriterioValutazioneOffertaType", propOrder = {
	"codice",
    "descrizione",
    "tipo",
    "formato",
    "valore",
    "valoreMin",
    "valoreMax",
    "listaValori",
    "numeroDecimali"
})
public class CriterioValutazioneOffertaType	implements Serializable {	
    /**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	protected String codice;	 
	@XmlElement(required = true)
    protected String descrizione;
	protected Integer tipo;				// 1=tecnico, 2=economico
    protected Integer formato;			// 1,2,3,4,5,6,50,51,52	    
    protected String valore;
    protected Double valoreMin;
    protected Double valoreMax;
    protected HashMap<String, String> listaValori;
    protected Integer numeroDecimali;

    public CriterioValutazioneOffertaType() {
    }
    
	public CriterioValutazioneOffertaType(CriterioValutazioneOffertaType source) {
		this.codice = source.codice;
		this.descrizione = source.descrizione;
		this.tipo = source.tipo;
		this.formato = source.formato;
		this.valore = source.valore;
		this.valoreMin = source.valoreMin;
		this.valoreMax = source.valoreMax;
		this.listaValori = source.listaValori;
		this.numeroDecimali = source.numeroDecimali;
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
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the formato
	 */
	public Integer getFormato() {
		return formato;
	}
	/**
	 * @param formato the formato to set
	 */
	public void setFormato(Integer formato) {
		this.formato = formato;
	}
	/**
	 * @return the valore
	 */
	public String getValore() {
		return valore;
	}
	/**
	 * @param valore the valore to set
	 */
	public void setValore(String valore) {
		this.valore = valore;
	}		
	/**
	 * @return the valoreMin
	 */
	public Double getValoreMin() {
		return valoreMin;
	}	
	/**
	 * @param valoreMin the valoreMin to set
	 */
	public void setValoreMin(Double valoreMin) {
		this.valoreMin = valoreMin;
	}	
	/**
	 * @return the valoreMax
	 */
	public Double getValoreMax() {
		return valoreMax;
	}
	/**
	 * @param valoreMax the valoreMax to set
	 */
	public void setValoreMax(Double valoreMax) {
		this.valoreMax = valoreMax;
	}
	/**
	 * @return the listaValori
	 */
	public HashMap<String, String> getListaValori() {
		return listaValori;
	}
	/**
	 * @param listaValori the listaValori to set
	 */
	public void setListaValori(HashMap<String, String> listaValori) {
		this.listaValori = listaValori;
	}
	/**
	 * @return the numeroDecimali
	 */
	public Integer getNumeroDecimali() {
		return numeroDecimali;
	}
	/**
	 * @param numeroDecimali the numeroDecimali to set
	 */
	public void setNumeroDecimali(Integer numeroDecimali) {
		this.numeroDecimali = numeroDecimali;
	}	
}
