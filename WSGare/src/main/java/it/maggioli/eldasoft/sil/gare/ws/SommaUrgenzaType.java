
package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SommaUrgenzaType", propOrder = {
    "codice",
	"oggetto",
	"dataEsito",
	"dataPubblicazione",
	"dataProcedura",
	"cig",
	"stato",
	"tipoProcedura",
	"importo"	
})
public class SommaUrgenzaType implements Serializable {    
	/**
	 * UID
	 */
	private static final long serialVersionUID = 3396656587877799806L;

	@XmlElement(required = true)
	protected String codice;
	protected String oggetto;
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataEsito;
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataPubblicazione;
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataProcedura;
	protected String cig;
	protected String stato;
	protected String tipoProcedura;
	protected Double importo;
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public String getOggetto() {
		return oggetto;
	}
	
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	
	public Date getDataEsito() {
		return dataEsito;
	}
	
	public void setDataEsito(Date dataEsito) {
		this.dataEsito = dataEsito;
	}
	
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}
	
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	
	public Date getDataProcedura() {
		return dataProcedura;
	}
	
	public void setDataProcedura(Date dataProcedura) {
		this.dataProcedura = dataProcedura;
	}
	
	public String getCig() {
		return cig;
	}
	
	public void setCig(String cig) {
		this.cig = cig;
	}
	
	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public String getTipoProcedura() {
		return tipoProcedura;
	}
	
	public void setTipoProcedura(String tipoProcedura) {
		this.tipoProcedura = tipoProcedura;
	}
	
	public Double getImporto() {
		return importo;
	}
	
	public void setImporto(Double importo) {
		this.importo = importo;
	}
		
}
