
package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per ComunicazioneType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ComunicazioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idComunicazione" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idDestinatario" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="testo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documento" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dataInvio" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dataLettura" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataProtocollo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="numeroProtocollo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bloccaRispondi" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dataInserimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="genere" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="titolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipologia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stazioneAppaltante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComunicazioneType", propOrder = {
    "idComunicazione",
    "idDestinatario",
    "codice",
    "codice2",
    "oggetto",
    "testo",
    "documento",
    "dataInvio",
    "dataLettura",
    "dataProtocollo",
    "numeroProtocollo",
    "bloccaRispondi",
    "dataInserimento",
    "genere",
    "titolo",
    "tipologia",
    "stazioneAppaltante",
    "destinatario",
    "mittente",
    "modello",
    "tipoBusta",
    "dataScadenza",
    "oraScadenza",
    "entita",
    "applicativo"
})
public class ComunicazioneType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected long idComunicazione;
    protected Long idDestinatario;
    @XmlElement(required = true)
    protected String codice;
    protected String codice2;
    protected String oggetto;
    protected String testo;
    protected List<DocumentoAllegatoType> documento;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date dataInvio;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date dataLettura;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date dataProtocollo;
    protected String numeroProtocollo;
    protected boolean bloccaRispondi;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date dataInserimento;
    protected Integer genere;
    protected String titolo;
    protected String tipologia;
    protected String stazioneAppaltante;
	protected String destinatario;
	protected String mittente;
	protected Long modello;
	protected Long tipoBusta;
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataScadenza;
	protected String oraScadenza;
	protected String entita;
	protected String applicativo;

    public long getIdComunicazione() {
        return idComunicazione;
    }

    public void setIdComunicazione(long value) {
        this.idComunicazione = value;
    }

    public Long getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(Long value) {
        this.idDestinatario = value;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String value) {
        this.codice = value;
    }

    public String getCodice2() {
		return codice2;
	}

	public void setCodice2(String codice2) {
		this.codice2 = codice2;
	}

	public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String value) {
        this.oggetto = value;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String value) {
        this.testo = value;
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

    public Date getDataInvio() {
        return dataInvio;
    }

    public void setDataInvio(Date value) {
        this.dataInvio = value;
    }

    public Date getDataLettura() {
        return dataLettura;
    }

    public void setDataLettura(Date value) {
        this.dataLettura = value;
    }

    public Date getDataProtocollo() {
        return dataProtocollo;
    }

    public void setDataProtocollo(Date value) {
        this.dataProtocollo = value;
    }

    public String getNumeroProtocollo() {
        return numeroProtocollo;
    }

    public void setNumeroProtocollo(String value) {
        this.numeroProtocollo = value;
    }

    public boolean isBloccaRispondi() {
        return bloccaRispondi;
    }

    public void setBloccaRispondi(boolean value) {
        this.bloccaRispondi = value;
    }
    
	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Integer getGenere() {
		return genere;
	}

	public void setGenere(Integer genere) {
		this.genere = genere;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getStazioneAppaltante() {
		return stazioneAppaltante;
	}

	public void setStazioneAppaltante(String stazioneAppaltante) {
		this.stazioneAppaltante = stazioneAppaltante;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getMittente() {
		return mittente;
	}

	public void setMittente(String mittente) {
		this.mittente = mittente;
	}

	public Long getModello() {
		return modello;
	}

	public void setModello(Long modello) {
		this.modello = modello;
	}

	public Long getTipoBusta() {
		return tipoBusta;
	}

	public void setTipoBusta(Long tipoBusta) {
		this.tipoBusta = tipoBusta;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getOraScadenza() {
		return oraScadenza;
	}

	public void setOraScadenza(String oraScadenza) {
		this.oraScadenza = oraScadenza;
	}

	public String getEntita() {
		return entita;
	}

	public void setEntita(String entita) {
		this.entita = entita;
	}

	public String getApplicativo() {
		return applicativo;
	}

	public void setApplicativo(String applicativo) {
		this.applicativo = applicativo;
	}	
	
}
