
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetDocumentiRichiestiBandoGaraPlicoUnicoOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetDocumentiRichiestiBandoGaraPlicoUnicoOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentoBustaAmministrativa" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentazioneRichiestaType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lotti" type="{http://www.eldasoft.it/sil/WSGareAppalto/}LottoDettaglioGaraType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="errore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDocumentiRichiestiBandoGaraPlicoUnicoOutType", propOrder = {
    "documentoBustaAmministrativa",
    "lotti",
    "errore"
})
public class GetDocumentiRichiestiBandoGaraPlicoUnicoOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected List<DocumentazioneRichiestaType> documentoBustaAmministrativa;
    protected List<LottoDettaglioGaraType> lotti;
    protected String errore;

    /**
     * Gets the value of the documentoBustaAmministrativa property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentoBustaAmministrativa property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentoBustaAmministrativa().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentazioneRichiestaType }
     * 
     * 
     */
    public List<DocumentazioneRichiestaType> getDocumentoBustaAmministrativa() {
        if (documentoBustaAmministrativa == null) {
            documentoBustaAmministrativa = new ArrayList<DocumentazioneRichiestaType>();
        }
        return this.documentoBustaAmministrativa;
    }
    
    public void setDocumentoBustaAmministrativa(List<DocumentazioneRichiestaType> documentoBustaAmministrativa) {
        this.documentoBustaAmministrativa = documentoBustaAmministrativa;
    }

    /**
     * Gets the value of the lotti property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lotti property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLotti().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LottoDettaglioGaraType }
     * 
     * 
     */
    public List<LottoDettaglioGaraType> getLotti() {
        if (lotti == null) {
            lotti = new ArrayList<LottoDettaglioGaraType>();
        }
        return this.lotti;
    }
    
    public void setLotti(List<LottoDettaglioGaraType> lotti) {
        this.lotti = lotti;
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
