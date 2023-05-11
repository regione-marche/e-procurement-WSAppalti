
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
 * <p>Classe Java per EsitoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EsitoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stazioneAppaltante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipologia">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="tipoAppalto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="proceduraTelematica" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataPubblicazione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataPubblicazioneBando" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="esito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EsitoType", propOrder = {
    "stazioneAppaltante",
    "codice",
    "oggetto",
    "cig",
    "cup",
    "tipologia",
    "tipoAppalto",
    "proceduraTelematica",
    "stato",
    "dataPubblicazione",
    "dataPubblicazioneBando",
    "esito",
    "accordoQuadro",
    "dataUltimoAggiornamento",
    "isGreen",
    "isRecycle",
    "isPnrr"
})
public class EsitoType implements Serializable
{
    private final static long serialVersionUID = 2L;
    
    @XmlElement(required = true)
    protected String stazioneAppaltante;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String oggetto;
    protected String cig;
    protected String cup;
    protected int tipologia;
    @XmlElement(required = true)
    protected String tipoAppalto;
    protected boolean proceduraTelematica; 
    protected String stato;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataPubblicazione;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataPubblicazioneBando;
    protected String esito;
    protected boolean accordoQuadro;
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

    public String getCig() {
        return cig;
    }

    public void setCig(String value) {
        this.cig = value;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String value) {
        this.cup = value;
    }

    /**
     * Recupera il valore della proprietà tipologia.
     * 
     * @return tipologia   
     * Tipologia di gara: 1 - Gara a piu' lotti con offerte distinte 
     *                    2 - Gara a lotto unico 
     *                    3 - Gara a piu' lotti con offerta unica
     */
    public int getTipologia() {
        return tipologia;
    }

    public void setTipologia(int value) {
        this.tipologia = value;
    }

    public String getTipoAppalto() {
        return tipoAppalto;
    }

    public void setTipoAppalto(String value) {
        this.tipoAppalto = value;
    }

    public boolean isProceduraTelematica() {
        return proceduraTelematica;
    }

    public void setProceduraTelematica(boolean value) {
        this.proceduraTelematica = value;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String value) {
        this.stato = value;
    }

    public Date getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(Date value) {
        this.dataPubblicazione = value;
    }

    public Date getDataPubblicazioneBando() {
        return dataPubblicazioneBando;
    }

    public void setDataPubblicazioneBando(Date value) {
        this.dataPubblicazioneBando = value;
    }

    public String getEsito() {
        return esito;
    }

    public void setEsito(String value) {
        this.esito = value;
    }
    
    public boolean isAccordoQuadro() {
        return accordoQuadro;
    }

    public void setAccordoQuadro(boolean value) {
        this.accordoQuadro = value;
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
