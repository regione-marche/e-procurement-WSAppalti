
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DocumentazioneRichiestaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DocumentazioneRichiestaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="obbligatorio" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idfacsimile" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="formato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="generato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idstampa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentazioneRichiestaType", propOrder = {
    "nome",
    "obbligatorio",
    "idfacsimile",
    "nomefilefacsimile",
    "id",
    "formato",
    "generato"
    ,"idstampa"
})
public class DocumentazioneRichiestaType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String nome;
    protected boolean obbligatorio;
    protected Long idfacsimile;
    protected String nomefilefacsimile;
    protected long id;
    protected Integer formato;
    protected Integer generato;
    protected String idstampa;

    /**
     * Recupera il valore della proprietà nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della proprietà nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Recupera il valore della proprietà obbligatorio.
     * 
     */
    public boolean isObbligatorio() {
        return obbligatorio;
    }

    /**
     * Imposta il valore della proprietà obbligatorio.
     * 
     */
    public void setObbligatorio(boolean value) {
        this.obbligatorio = value;
    }

    /**
     * Recupera il valore della proprietà idfacsimile.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *  Identificativo dell'eventuale documento contenente 
     *  il facsimile da allegare alla richiesta   
     */
    public Long getIdfacsimile() {
        return idfacsimile;
    }

    /**
     * Imposta il valore della proprietà idfacsimile.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdfacsimile(Long value) {
        this.idfacsimile = value;
    }
    
    /**
     * Recupera il valore della proprietà nomefilefacsimile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *  Nome dell'eventuale documento contenente 
     *  il facsimile da allegare alla richiesta   
     */
	public String getNomefilefacsimile() {
		return nomefilefacsimile;
	}

    /**
     * Imposta il valore della proprietà nomefilefacsimile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setNomefilefacsimile(String nomefilefacsimile) {
		this.nomefilefacsimile = nomefilefacsimile;
	}

	/**
     * Recupera il valore della proprietà id.
     * 
     * @return id   
     * Id da utilizzare per identificare il documento
     * richiesto in fase di invio al backoffice
     */
    public long getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà formato.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     * Codifica del formato di file richiesto da caricare    
     */
    public Integer getFormato() {
        return formato;
    }

    /**
     * Imposta il valore della proprietà formato.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFormato(Integer value) {
        this.formato = value;
    }

	public Integer getGenerato() {
		return generato;
	}

	public void setGenerato(Integer generato) {
		this.generato = generato;
	}
	
	/**
	 * @return the idstampa
	 */
	public String getIdstampa() {
		return idstampa;
	}

	/**
	 * @param idstampa the idstampa to set
	 */
	public void setIdstampa(String idstampa) {
		this.idstampa = idstampa;
	}
    
}
