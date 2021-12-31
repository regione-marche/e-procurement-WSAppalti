
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DettaglioAvvisoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DettaglioAvvisoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stazioneAppaltante" type="{http://www.eldasoft.it/sil/WSGareAppalto/}StazioneAppaltanteBandoType"/>
 *         &lt;element name="datiGenerali" type="{http://www.eldasoft.it/sil/WSGareAppalto/}AvvisoType"/>
 *         &lt;element name="documento" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DettaglioAvvisoType", propOrder = {
    "stazioneAppaltante",
    "datiGenerali",
    "documento",
    "soggettiAderenti",
    "comunicazione"
})
public class DettaglioAvvisoType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected StazioneAppaltanteBandoType stazioneAppaltante;
    @XmlElement(required = true)
    protected AvvisoType datiGenerali;
    protected List<DocumentoAllegatoType> documento;
    protected List<SoggettoAderenteType> soggettiAderenti;
    protected List<ComunicazioneType> comunicazione;

    /**
     * Recupera il valore della proprietà stazioneAppaltante.
     * 
     * @return
     *     possible object is
     *     {@link StazioneAppaltanteBandoType }
     *     
     */
    public StazioneAppaltanteBandoType getStazioneAppaltante() {
        return stazioneAppaltante;
    }

    /**
     * Imposta il valore della proprietà stazioneAppaltante.
     * 
     * @param value
     *     allowed object is
     *     {@link StazioneAppaltanteBandoType }
     *     
     */
    public void setStazioneAppaltante(StazioneAppaltanteBandoType value) {
        this.stazioneAppaltante = value;
    }

    /**
     * Recupera il valore della proprietà datiGenerali.
     * 
     * @return
     *     possible object is
     *     {@link AvvisoType }
     *     
     */
    public AvvisoType getDatiGenerali() {
        return datiGenerali;
    }

    /**
     * Imposta il valore della proprietà datiGenerali.
     * 
     * @param value
     *     allowed object is
     *     {@link AvvisoType }
     *     
     */
    public void setDatiGenerali(AvvisoType value) {
        this.datiGenerali = value;
    }

    /**
     * Gets the value of the documento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoAllegatoType }
     * 
     * 
     */
    public List<DocumentoAllegatoType> getDocumento() {
        if (documento == null) {
            documento = new ArrayList<DocumentoAllegatoType>();
        }
        return this.documento;
    }
    
    public void setDocumento(List<DocumentoAllegatoType> documento) {
        this.documento = documento;
    }

	/**
	 * @return the soggettiAderenti
	 */
	public List<SoggettoAderenteType> getSoggettiAderenti() {
		return soggettiAderenti;
	}

	/**
	 * @param soggettiAderenti the soggettiAderenti to set
	 */
	public void setSoggettiAderenti(List<SoggettoAderenteType> soggettiAderenti) {
		this.soggettiAderenti = soggettiAderenti;
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
    public List<ComunicazioneType> getComunicazione() {
        if (comunicazione == null) {
            comunicazione = new ArrayList<ComunicazioneType>();
        }
        return this.comunicazione;
    }
    
    public void setComunicazione(List<ComunicazioneType> comunicazione) {
        this.comunicazione = comunicazione;
    }
}
