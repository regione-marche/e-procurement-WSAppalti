package it.maggioli.eldasoft.gene.xml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DatetimeAdapter extends XmlAdapter<String, Date> {

	/**
	 *  yyyy-MM-dd'T'HH:mm:ss.SSS'Z' => 2016-09-12T14:31:49.150Z
	 *  yyyy-MM-dd'T'HH:mm:ss.SSSZ   => 2016-09-12T14:31:49.150+0200
	 */
	private static final SimpleDateFormat DATETIMEFORMAT;
	
	static {
		DATETIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");		
		
		// WORKAROUND 
		// il client quando riceve questa datetime lo converte in base al 
		// proprio timezone, quindi prima di spedire il valore si riporta 
		// dataora ad offset 00:00
		DATETIMEFORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));			
	}	
    
	@Override
	public String marshal(Date value) throws Exception {
		try {
			String result = DATETIMEFORMAT.format(value); 
	        return result;	        
		} catch(Exception e) {
			return null;
		} 		
	}

	@Override
	public Date unmarshal(String value) throws Exception {
		try {
			Date result = DatatypeConverter.parseDateTime(value).getTime();
			return result;
		} catch(Exception e) {
			return null;
		} 				
	}

}
