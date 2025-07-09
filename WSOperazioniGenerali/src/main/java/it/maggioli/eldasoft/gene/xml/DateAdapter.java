package it.maggioli.eldasoft.gene.xml;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter XML per la gestione dei campi di tipo "Date" letti/scritti su db
 * 
 * Da utilizzare come annotazione dell'attributo nella classe
 *  
 *   @XmlJavaTypeAdapter(DateAdapter.class)
 *   
 */
public class DateAdapter extends XmlAdapter<String, Date> {

	private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public String marshal(Date value) throws Exception {
		try {
			return DATEFORMAT.format(value);
		} catch(Exception e) {
			return null;
		}			
	}

	@Override
	public Date unmarshal(String value) throws Exception {
		try {
			return DatatypeConverter.parseDate(value).getTime();
		} catch(Exception e) {
			return null;
		}
	}

} 