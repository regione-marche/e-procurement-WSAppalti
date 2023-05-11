package it.maggioli.eldasoft.gene.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DataHandlerAdapter extends XmlAdapter<byte[], DataHandler> {

	@Override
	public byte[] marshal(DataHandler value) throws Exception {
		try {
			byte[] result = null; 			
			ByteArrayOutputStream os;
		    try {
		    	os = new ByteArrayOutputStream();
				value.writeTo(os);
				result = os.toByteArray();
			} catch (IOException e) {
//				throw new SQLException ("Unexpected value " + s + " found where "+YES+" or "+NO+" was expected.");
			}	
			os = null;
	        return result;	        
		} catch(Exception e) {
			return null;
		} 		
	}

	@Override
	public DataHandler unmarshal(byte[] value) throws Exception {
		DataHandler result = null;
		ByteArrayDataSource ds;		
		try {
			ds = new ByteArrayDataSource(value, "*/*");
			result = new DataHandler(ds);			
		} catch(Exception e) {
//			throw new SQLException ("Unexpected value " + s + " found where "+YES+" or "+NO+" was expected.");			
		} 	
		ds = null;
		return result;
	}

}
