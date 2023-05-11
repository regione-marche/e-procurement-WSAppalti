package it.maggioli.eldasoft.sil.stipule.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StipulaContrattoType", propOrder = {
    "id",
    "codiceGara",
    "contatore",
    "codiceStipula",
    "oggetto",
    "importo",
    "stato",
    "syscon",
    "assegnatario",
    "codicedec",
    "archiviato",
    "dataPubblicazione",
    "allegati",
    "codiceSa",
    "descStato",
    "descSa",
    "descRup"
})
public class StipulaContrattoType
    implements Serializable
{
	private static final long serialVersionUID = 4563664249693158946L;
	
//	@XmlElement(required = true)
    protected long id;
    protected String codiceGara;
    protected String contatore;
    protected String codiceStipula;
    protected String oggetto;
    protected double importo;
    protected String stato;
    protected String syscon;
    protected String assegnatario;
    protected String codicedec;
    protected boolean archiviato;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dataPubblicazione;
    protected List<DocumentoAllegatoType> allegati;
    protected String codiceSa ;
    protected String descStato;
    protected String descSa;
    protected String descRup ;
    
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCodiceGara() {
		return codiceGara;
	}
	
	public void setCodiceGara(String codiceGara) {
		this.codiceGara = codiceGara;
	}
	
	public String getContatore() {
		return contatore;
	}
	
	public void setContatore(String contatore) {
		this.contatore = contatore;
	}
	
	public String getCodiceStipula() {
		return codiceStipula;
	}
	
	public void setCodiceStipula(String codiceStipula) {
		this.codiceStipula = codiceStipula;
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
	
	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public String getSyscon() {
		return syscon;
	}
	
	public void setSyscon(String syscon) {
		this.syscon = syscon;
	}
	
	public String getAssegnatario() {
		return assegnatario;
	}
	
	public void setAssegnatario(String assegnatario) {
		this.assegnatario = assegnatario;
	}
	
	public String getCodicedec() {
		return codicedec;
	}
	
	public void setCodicedec(String codicedec) {
		this.codicedec = codicedec;
	}
	
	public boolean isArchiviato() {
		return archiviato;
	}
	
	public void setArchiviato(boolean archiviato) {
		this.archiviato = archiviato;
	}
	
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}
	
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	
	public List<DocumentoAllegatoType> getAllegati() {
		return allegati;
	}
	
	public void setAllegati(List<DocumentoAllegatoType> allegati) {
		this.allegati = allegati;
	}

	public String getCodiceSa() {
		return codiceSa;
	}

	public void setCodiceSa(String codiceSa) {
		this.codiceSa = codiceSa;
	}

	public String getDescStato() {
		return descStato;
	}

	public void setDescStato(String descStato) {
		this.descStato = descStato;
	}

	public String getDescSa() {
		return descSa;
	}

	public void setDescSa(String descSa) {
		this.descSa = descSa;
	}

	public String getDescRup() {
		return descRup;
	}

	public void setDescRup(String descRup) {
		this.descRup = descRup;
	}

}
