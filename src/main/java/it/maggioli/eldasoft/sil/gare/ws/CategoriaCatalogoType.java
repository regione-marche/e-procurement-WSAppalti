
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per CategoriaCatalogoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="CategoriaCatalogoType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.eldasoft.it/sil/WSGareAppalto/}CategoriaBandoIscrizioneType">
 *       &lt;sequence>
 *         &lt;element name="articoli" type="{http://www.eldasoft.it/sil/WSGareAppalto/}ArticoloType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoriaCatalogoType", propOrder = {
    "articoli"
})
public class CategoriaCatalogoType
    extends CategoriaBandoIscrizioneType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected List<ArticoloType> articoli;

    /**
     * Gets the value of the articoli property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the articoli property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArticoli().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArticoloType }
     * 
     * 
     */
    public List<ArticoloType> getArticoli() {
        if (articoli == null) {
            articoli = new ArrayList<ArticoloType>();
        }
        return this.articoli;
    }

    public void setArticoli(List<ArticoloType> articoli) {
        this.articoli = articoli;
    }
}
