
package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>Classe Java per AvvisoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AvvisoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stazioneAppaltante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoAvviso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoAppalto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataPubblicazione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataScadenza" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="oraScadenza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AvvisoType", propOrder = {
    "stazioneAppaltante",
    "codice",
    "oggetto",
    "tipoAvviso",
    "tipoAppalto",
    "dataPubblicazione",
    "dataScadenza",
    "oraScadenza",
	"dataUltimoAggiornamento",
    "isGreen",
    "isRecycle",
    "isPnrr"
})
public class AvvisoType implements Serializable 
{
    private final static long serialVersionUID = 2L;
    
    @XmlElement(required = true)
    protected String stazioneAppaltante;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String oggetto;
    @XmlElement(required = true)
    protected String tipoAvviso;
    @XmlElement(required = true)
    protected String tipoAppalto;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataPubblicazione;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataScadenza;
    protected String oraScadenza;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataUltimoAggiornamento;
    protected String isGreen;
    protected String isRecycle;
    protected String isPnrr;

    public String getStazioneAppaltante() {
        return stazioneAppaltante;
    }

    public void setStazioneAppaltante(String value) {
        this.stazioneAppaltante = value;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String value) {
        this.codice = value;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String value) {
        this.oggetto = value;
    }

    public String getTipoAvviso() {
        return tipoAvviso;
    }

    public void setTipoAvviso(String value) {
        this.tipoAvviso = value;
    }

    public String getTipoAppalto() {
        return tipoAppalto;
    }

    public void setTipoAppalto(String value) {
        this.tipoAppalto = value;
    }

    public Date getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(Date value) {
        this.dataPubblicazione = value;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date value) {
        this.dataScadenza = value;
    }

    public String getOraScadenza() {
        return oraScadenza;
    }

    public void setOraScadenza(String value) {
        this.oraScadenza = value;
    }

	public Date getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Date dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

    public String getIsGreen() {
        return isGreen;
    }

    public void setIsGreen(String isGreen) {
        this.isGreen = isGreen;
    }

    public String getIsRecycle() {
        return isRecycle;
    }

    public void setIsRecycle(String isRecycle) {
        this.isRecycle = isRecycle;
    }

	public String getIsPnrr() {
		return isPnrr;
	}

	public void setIsPnrr(String isPnrr) {
		this.isPnrr = isPnrr;
	}
      
}
