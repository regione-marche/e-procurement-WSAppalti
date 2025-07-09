package it.maggioli.eldasoft.sil.gare.db.vo;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.maggioli.eldasoft.gene.xml.DatetimeAdapter;
import it.maggioli.eldasoft.sil.gare.ws.DeliberaType;


public class DeliberaInfo extends DeliberaType {    
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DatetimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
	protected Date dataPubblicazione;
	
	
	public DeliberaType toDeliberaType() {
		DeliberaType delibera = new DeliberaType(); 
		delibera.setCodice(codice);
		delibera.setStazioneAppaltante(stazioneAppaltante);
		delibera.setOggetto(oggetto);
		delibera.setCig(cig);
		delibera.setDocumenti(documenti);
		return delibera;
	}
	
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	
}
