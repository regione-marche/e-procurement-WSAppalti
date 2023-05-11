package it.maggioli.eldasoft.sil.gare.ws;

import it.maggioli.eldasoft.gene.xml.DateAdapter;
import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

// dataTermine (data), oraTermine (stringa con ora), dataRettifica (timestamp).
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RettificaGaraType", propOrder = {
    "dataTermine",
    "oraTermine",
    "dataRettifica"
})
public class RettificaGaraType {
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
	protected Date dataTermine;
	protected String oraTermine;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataRettifica;
    
	public Date getDataTermine() {
		return dataTermine;
	}
	public void setDataTermine(Date dataTermine) {
		this.dataTermine = dataTermine;
	}
	public String getOraTermine() {
		return oraTermine;
	}
	public void setOraTermine(String oraTermine) {
		this.oraTermine = oraTermine;
	}
	public Date getDataRettifica() {
		return dataRettifica;
	}
	public void setDataRettifica(Date dataRettifica) {
		this.dataRettifica = dataRettifica;
	}
	
}
