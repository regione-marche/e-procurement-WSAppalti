
package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per DocumentoAllegatoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DocumentoAllegatoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="nomefile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *             &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *           &lt;/sequence>
 *           &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoAllegatoType", propOrder = {
    "descrizione",
    "nomefile",
    "id",
    "url",
    "dataPubblicazione",
    "dimensione",
    "uuid",
    "idStampa",
    "ultbic"
})
public class DocumentoAllegatoType
    implements Serializable
{
    private final static long serialVersionUID = 2L;
    
    @XmlElement(required = true)
    protected String descrizione;
    protected String nomefile;
    protected Long id;
    protected String url;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataPubblicazione;
    protected Long dimensione;
    protected String uuid;
    protected String idStampa;
    protected String ultbic;

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    public String getNomefile() {
        return nomefile;
    }

    public void setNomefile(String value) {
        this.nomefile = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String value) {
        this.url = value;
    }

	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public Long getDimensione() {
		return dimensione;
	}

	public void setDimensione(Long dimensione) {
		this.dimensione = dimensione;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getIdStampa() {
		return idStampa;
	}

	public void setIdStampa(String idStampa) {
		this.idStampa = idStampa;
	}

	public String getUltbic() {
		return ultbic;
	}

	public void setUltbic(String ultbic) {
		this.ultbic = ultbic;
	}	
	
}
