
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DettaglioBandoIscrizioneType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DettaglioBandoIscrizioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stazioneAppaltante" type="{http://www.eldasoft.it/sil/WSGareAppalto/}StazioneAppaltanteBandoType"/>
 *         &lt;element name="datiGeneraliBandoIscrizione" type="{http://www.eldasoft.it/sil/WSGareAppalto/}BandoIscrizioneType"/>
 *         &lt;element name="documento" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requisitoRichiesto" type="{http://www.eldasoft.it/sil/WSGareAppalto/}RequisitoRichiestoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comunicazione" type="{http://www.eldasoft.it/sil/WSGareAppalto/}ComunicazioneType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="chiarimento" type="{http://www.eldasoft.it/sil/WSGareAppalto/}ChiarimentoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DettaglioBandoIscrizioneType", propOrder = {
    "stazioneAppaltante",
    "datiGeneraliBandoIscrizione",
    "documento",
    "requisitoRichiesto",
    "comunicazione",
    "chiarimento"
})
public class DettaglioBandoIscrizioneType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected StazioneAppaltanteBandoType stazioneAppaltante;
    @XmlElement(required = true)
    protected BandoIscrizioneType datiGeneraliBandoIscrizione;
    protected List<DocumentoAllegatoType> documento;
    protected List<RequisitoRichiestoType> requisitoRichiesto;
    protected List<ComunicazioneType> comunicazione;
    protected List<ChiarimentoType> chiarimento;

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
     * Recupera il valore della proprietà datiGeneraliBandoIscrizione.
     * 
     * @return
     *     possible object is
     *     {@link BandoIscrizioneType }
     *     
     */
    public BandoIscrizioneType getDatiGeneraliBandoIscrizione() {
        return datiGeneraliBandoIscrizione;
    }

    /**
     * Imposta il valore della proprietà datiGeneraliBandoIscrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link BandoIscrizioneType }
     *     
     */
    public void setDatiGeneraliBandoIscrizione(BandoIscrizioneType value) {
        this.datiGeneraliBandoIscrizione = value;
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
     * Gets the value of the requisitoRichiesto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requisitoRichiesto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequisitoRichiesto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RequisitoRichiestoType }
     * 
     * 
     */
    public List<RequisitoRichiestoType> getRequisitoRichiesto() {
        if (requisitoRichiesto == null) {
            requisitoRichiesto = new ArrayList<RequisitoRichiestoType>();
        }
        return this.requisitoRichiesto;
    }
    
    public void setRequisitoRichiesto(List<RequisitoRichiestoType> requisitoRichiesto) {
        this.requisitoRichiesto = requisitoRichiesto;
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

    /**
     * Gets the value of the chiarimento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chiarimento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChiarimento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChiarimentoType }
     * 
     * 
     */
    public List<ChiarimentoType> getChiarimento() {
        if (chiarimento == null) {
            chiarimento = new ArrayList<ChiarimentoType>();
        }
        return this.chiarimento;
    }
    
    public void setChiarimento(List<ChiarimentoType> chiarimento) {
        this.chiarimento = chiarimento;
    }

}
