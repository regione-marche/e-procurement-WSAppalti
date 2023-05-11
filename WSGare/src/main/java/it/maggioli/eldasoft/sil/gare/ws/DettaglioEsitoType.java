
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DettaglioEsitoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DettaglioEsitoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stazioneAppaltante" type="{http://www.eldasoft.it/sil/WSGareAppalto/}StazioneAppaltanteBandoType"/>
 *         &lt;element name="datiGenerali" type="{http://www.eldasoft.it/sil/WSGareAppalto/}EsitoType"/>
 *         &lt;element name="documento" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lotto" type="{http://www.eldasoft.it/sil/WSGareAppalto/}LottoDettaglioGaraType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DettaglioEsitoType", propOrder = {
    "stazioneAppaltante",
    "datiGenerali",
    "documento",
    "lotto",
    "soggettiAderenti"
})
public class DettaglioEsitoType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected StazioneAppaltanteBandoType stazioneAppaltante;
    @XmlElement(required = true)
    protected EsitoType datiGenerali;
    protected List<DocumentoAllegatoType> documento;
    protected List<LottoDettaglioGaraType> lotto;
    protected List<SoggettoAderenteType> soggettiAderenti;

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
     *     {@link EsitoType }
     *     
     */
    public EsitoType getDatiGenerali() {
        return datiGenerali;
    }

    /**
     * Imposta il valore della proprietà datiGenerali.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoType }
     *     
     */
    public void setDatiGenerali(EsitoType value) {
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
     * Gets the value of the lotto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lotto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLotto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LottoDettaglioGaraType }
     * 
     * 
     */
    public List<LottoDettaglioGaraType> getLotto() {
        if (lotto == null) {
            lotto = new ArrayList<LottoDettaglioGaraType>();
        }
        return this.lotto;
    }

    public void setLotto(List<LottoDettaglioGaraType> lotto) {
        this.lotto = lotto;
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
    
}
