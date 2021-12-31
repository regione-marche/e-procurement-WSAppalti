
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per CategoriaImpresaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="CategoriaImpresaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="classificaMinima" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="classificaMassima" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoriaImpresaType", propOrder = {
    "categoria",
    "classificaMinima",
    "classificaMassima",
    "nota"
})
public class CategoriaImpresaType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String categoria;
    protected String classificaMinima;
    protected String classificaMassima;
    protected String nota;

    /**
     * Recupera il valore della proprietà categoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Imposta il valore della proprietà categoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoria(String value) {
        this.categoria = value;
    }

    /**
     * Recupera il valore della proprietà classificaMinima.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificaMinima() {
        return classificaMinima;
    }

    /**
     * Imposta il valore della proprietà classificaMinima.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassificaMinima(String value) {
        this.classificaMinima = value;
    }

    /**
     * Recupera il valore della proprietà classificaMassima.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificaMassima() {
        return classificaMassima;
    }

    /**
     * Imposta il valore della proprietà classificaMassima.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassificaMassima(String value) {
        this.classificaMassima = value;
    }

	/**
	 * @return the nota
	 */
	public String getNota() {
		return nota;
	}

	/**
	 * @param nota the nota to set
	 */
	public void setNote(String nota) {
		this.nota = nota;
	}

}
