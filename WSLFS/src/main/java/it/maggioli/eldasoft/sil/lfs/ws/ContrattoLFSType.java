package it.maggioli.eldasoft.sil.lfs.ws;

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
 * <p>Classe Java per ContrattoLFSType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ContrattoLFSType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stazioneAppaltante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oggetto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tipoAtto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataAtto" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="numeroRepertorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idProgramma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idComunicazione" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idDestinatario" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="dataLettura" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="allegati" type="tns:DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContrattoLFSType", propOrder = {
	"codice",
	"nappal",
	"oggetto",
	"cig",
	"importo",
	"ngara",
	"codiceSA",
	"stazioneAppaltante",
	"dataContratto",
	"nominativoRespSicImpr",
	"nominativoDirettoreTecni",
	"nominativoRespSicTecni",
	"nominativoDirettoreImpr",
	"numeroRepertorio",
	"fase"
})


public class ContrattoLFSType
    implements Serializable
{
	private static final long serialVersionUID = 4947163176544339619L;
    protected String stazioneAppaltante;
    protected String codiceSA;
    protected String codice;
    protected String nappal;
    protected String oggetto;
    protected String cig;
    protected double importo;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataContratto;
    protected String ngara;
    protected String nominativoRespSicImpr;
    protected String nominativoDirettoreTecni;
    protected String nominativoRespSicTecni;
    protected String nominativoDirettoreImpr;
    protected String numeroRepertorio;
    protected String fase;
    
	public String getStazioneAppaltante() {
		return stazioneAppaltante;
	}
	public void setStazioneAppaltante(String stazioneAppaltante) {
		this.stazioneAppaltante = stazioneAppaltante;
	}
	
	public String getCodiceSA() {
		return codiceSA;
	}
	public void setCodiceSA(String codiceSA) {
		this.codiceSA = codiceSA;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getNappal() {
		return nappal;
	}
	public void setNappal(String nappal) {
		this.nappal = nappal;
	}
	public String getOggetto() {
		return oggetto;
	}
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public Date getDataContratto() {
		return dataContratto;
	}
	public void setDataContratto(Date dataContratto) {
		this.dataContratto = dataContratto;
	}
	public String getCig() {
		return cig;
	}
	public void setCig(String cig) {
		this.cig = cig;
	}
	public String getNgara() {
		return ngara;
	}
	public void setNgara(String ngara) {
		this.ngara = ngara;
	}
	public String getNominativoRespSicImpr() {
		return nominativoRespSicImpr;
	}
	public void setNominativoRespSicImpr(String nominativoRespSicImpr) {
		this.nominativoRespSicImpr = nominativoRespSicImpr;
	}
	public String getNominativoDirettoreTecni() {
		return nominativoDirettoreTecni;
	}
	public void setNominativoDirettoreTecni(String nominativoDirettoreTecni) {
		this.nominativoDirettoreTecni = nominativoDirettoreTecni;
	}
	public String getNominativoRespSicTecni() {
		return nominativoRespSicTecni;
	}
	public void setNominativoRespSicTecni(String nominativoRespSicTecni) {
		this.nominativoRespSicTecni = nominativoRespSicTecni;
	}
	public String getNominativoDirettoreImpr() {
		return nominativoDirettoreImpr;
	}
	public void setNominativoDirettoreImpr(String nominativoDirettoreImpr) {
		this.nominativoDirettoreImpr = nominativoDirettoreImpr;
	}
	public String getNumeroRepertorio() {
		return numeroRepertorio;
	}
	public void setNumeroRepertorio(String numeroRepertorio) {
		this.numeroRepertorio = numeroRepertorio;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
}
