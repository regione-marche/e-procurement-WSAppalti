
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SommaUrgenzaOutType", propOrder = {
    "numElementiTotali",
    "sommaUrgenza",
    "errore"
})
public class SommaUrgenzaOutType implements Serializable {    
	/**
	 * UID
	 */
	private static final long serialVersionUID = 8097939507269499912L;
	
	protected Integer numElementiTotali;
    protected List<SommaUrgenzaType> sommaUrgenza;
    protected String errore;

    /**
     * Recupera il valore della proprietà numElementiTotali.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumElementiTotali() {
        return numElementiTotali;
    }

    /**
     * Imposta il valore della proprietà numElementiTotali.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumElementiTotali(Integer value) {
        this.numElementiTotali = value;
    }

    /**
     * Gets the value of the comunicazione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comunicazione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComunicazione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComunicazioneType }
     * 
     * 
     */
    public List<SommaUrgenzaType> getSommaUrgenza() {
        if (sommaUrgenza == null) {
        	sommaUrgenza = new ArrayList<SommaUrgenzaType>();
        }
        return this.sommaUrgenza;
    }
    
    public void setSommaUrgenza(List<SommaUrgenzaType> sommaUrgenza) {
        this.sommaUrgenza = sommaUrgenza;
    }

    /**
     * Recupera il valore della proprietà errore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrore() {
        return errore;
    }

    /**
     * Imposta il valore della proprietà errore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrore(String value) {
        this.errore = value;
    }

}
