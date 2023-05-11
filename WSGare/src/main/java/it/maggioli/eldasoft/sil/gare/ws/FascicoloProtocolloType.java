
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per FascicoloProtocolloType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FascicoloProtocolloType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="anno" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="classifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceAoo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceUfficio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strutturaCompetente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FascicoloProtocolloType", propOrder = {
    "codice",
    "anno",
    "numero",
    "classifica",
    "codiceAoo",
    "codiceUfficio",
    "strutturaCompetente",
    "riservatezza"
})
public class FascicoloProtocolloType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String codice;
    protected Integer anno;
    protected String numero;
    protected String classifica;
    protected String codiceAoo;
    protected String codiceUfficio;
    protected String strutturaCompetente;
    protected Boolean riservatezza;

    /**
     * Recupera il valore della propriet&agrave; codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della propriet&agrave; codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della propriet&agrave; anno.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnno() {
        return anno;
    }

    /**
     * Imposta il valore della propriet&agrave; anno.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnno(Integer value) {
        this.anno = value;
    }

    /**
     * Recupera il valore della propriet&agrave; numero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Imposta il valore della propriet&agrave; numero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Recupera il valore della propriet&agrave; classifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassifica() {
        return classifica;
    }

    /**
     * Imposta il valore della propriet&agrave; classifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassifica(String value) {
        this.classifica = value;
    }

    /**
     * Recupera il valore della propriet&agrave; codiceAoo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAoo() {
        return codiceAoo;
    }

    /**
     * Imposta il valore della propriet&agrave; codiceAoo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAoo(String value) {
        this.codiceAoo = value;
    }

    /**
     * Recupera il valore della propriet&agrave; codiceUfficio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	/**
     * Imposta il valore della propriet&agrave; codiceUfficio.
     * 
     * @param codiceUfficio
     *     allowed object is
     *     {@link String }
	 */
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	
	/**
     * Imposta il valore della propriet&agrave; strutturaCompetente.
     * 
     * @param strutturaCompetente
     *     allowed object is
     *     {@link String }
	 */
	public String getStrutturaCompetente() {
		return strutturaCompetente;
	}
	
    /**
     * Recupera il valore della propriet&agrave; strutturaCompetente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public void setStrutturaCompetente(String strutturaCompetente) {
		this.strutturaCompetente = strutturaCompetente;
	}

	public Boolean getRiservatezza() {
		return riservatezza;
	}

	public void setRiservatezza(Boolean riservatezza) {
		this.riservatezza = riservatezza;
	}
	
}
