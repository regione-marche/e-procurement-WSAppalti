
package it.maggioli.eldasoft.gene.ws;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import it.maggioli.eldasoft.gene.xml.DateAdapter;
import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;


/**
 * <p>Java class for DettaglioComunicazioneType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DettaglioComunicazioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicativo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="entita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chiave1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chiave2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chiave3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chiave4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chiave5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idOperatore" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mittente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="testo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoComunicazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comunicazionePubblica" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="dataPubblicazione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataInserimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAggiornamentoStato" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataLettura" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataProtocollo" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="numeroProtocollo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sessionKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DettaglioComunicazioneType", propOrder = {
    "applicativo",
    "id",
    "entita",
    "chiave1",
    "chiave2",
    "chiave3",
    "chiave4",
    "chiave5",
    "idOperatore",
    "mittente",
    "stato",
    "oggetto",
    "testo",
    "tipoComunicazione",
    "comunicazionePubblica",
    "dataPubblicazione",
    "dataInserimento",
    "dataAggiornamentoStato",
    "dataLettura",
    "dataProtocollo",
    "numeroProtocollo",
    "sessionKey",
    "applicativoRisposta",
    "idRisposta",
    "modello",
    "tipoBusta"
})
public class DettaglioComunicazioneType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String applicativo;
    protected Long id;
    protected String entita;
    protected String chiave1;
    protected String chiave2;
    protected String chiave3;
    protected String chiave4;
    protected String chiave5;
    protected Long idOperatore;
    protected String mittente;
    protected String stato;
    protected String oggetto;
    protected String testo;
    protected String tipoComunicazione;
    protected Boolean comunicazionePubblica;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataPubblicazione;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date dataInserimento;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date dataAggiornamentoStato;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date dataLettura;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataProtocollo;
    protected String numeroProtocollo;
    protected String sessionKey;
    protected String applicativoRisposta;
    protected Long idRisposta;
    protected Long modello;				// NULL comunicazione normale, 1 indica un "soccorso istruttorio"
    protected Long tipoBusta;

    public String getApplicativo() {
        return applicativo;
    }

    public void setApplicativo(String value) {
        this.applicativo = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public String getEntita() {
        return entita;
    }

    public void setEntita(String value) {
        this.entita = value;
    }

    public String getChiave1() {
        return chiave1;
    }

    public void setChiave1(String value) {
        this.chiave1 = value;
    }

    public String getChiave2() {
        return chiave2;
    }

    public void setChiave2(String value) {
        this.chiave2 = value;
    }

    public String getChiave3() {
        return chiave3;
    }
    
    public void setChiave3(String value) {
        this.chiave3 = value;
    }

    public String getChiave4() {
        return chiave4;
    }

    public void setChiave4(String value) {
        this.chiave4 = value;
    }

    public String getChiave5() {
        return chiave5;
    }

    public void setChiave5(String value) {
        this.chiave5 = value;
    }

    public Long getIdOperatore() {
        return idOperatore;
    }

    public void setIdOperatore(Long value) {
        this.idOperatore = value;
    }

    public String getMittente() {
        return mittente;
    }

    public void setMittente(String value) {
        this.mittente = value;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String value) {
        this.stato = value;
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

    public String getTipoComunicazione() {
        return tipoComunicazione;
    }

    public void setTipoComunicazione(String value) {
        this.tipoComunicazione = value;
    }

    public Boolean isComunicazionePubblica() {
        return comunicazionePubblica;
    }

    public void setComunicazionePubblica(Boolean value) {
        this.comunicazionePubblica = value;
    }

    public Date getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(Date value) {
        this.dataPubblicazione = value;
    }

    public Date getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(Date value) {
        this.dataInserimento = value;
    }

    public Date getDataAggiornamentoStato() {
        return dataAggiornamentoStato;
    }

    public void setDataAggiornamentoStato(Date value) {
        this.dataAggiornamentoStato = value;
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

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String value) {
        this.sessionKey = value;
    }
    
    public String getApplicativoRisposta() {
        return applicativoRisposta;
    }

    public void setApplicativoRisposta(String value) {
        this.applicativoRisposta = value;
    }

    public Long getIdRisposta() {
        return idRisposta;
    }

    public void setIdRisposta(Long value) {
        this.idRisposta = value;
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
	
}
