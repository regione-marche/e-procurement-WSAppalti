package it.maggioli.eldasoft.gene.xml;

import it.eldasoft.utils.sicurezza.CriptazioneException;
import it.eldasoft.utils.sicurezza.FactoryCriptazioneByte;
import it.eldasoft.utils.sicurezza.ICriptazioneByte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter XML per la gestione dei campi di tipo "cifrato" (con FactoryCriptazioneByte) letti/scritti su db
 * 
 * Da utilizzare come annotazione dell'attributo nella classe
 *  
 *   @XmlJavaTypeAdapter(DateAdapter.class)
 *   
 */
public class CriptazioneByteAdapter extends XmlAdapter<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(CriptazioneByteAdapter.class);
	
	public static final boolean DECIFRA 	= true;
	public static final boolean CIFRA 		= false;

	public static String getFactoryCriptazioneByte(String value, boolean decript) {
		String toReturn = value;

		if(toReturn != null) {
			try {
				ICriptazioneByte factory = FactoryCriptazioneByte.getInstance(
						FactoryCriptazioneByte.CODICE_CRIPTAZIONE_LEGACY, 	 
						value.getBytes(),    	
						(decript ? ICriptazioneByte.FORMATO_DATO_CIFRATO: ICriptazioneByte.FORMATO_DATO_NON_CIFRATO)
				);
				toReturn = new String(decript ? factory.getDatoNonCifrato() : factory.getDatoCifrato());
			} catch (CriptazioneException e) {
				toReturn = null;
				logger.error("Errore in CriptazioneByteAdapter", e);
			}
		}

		return toReturn;
	}

	@Override
	public String marshal(String value) throws Exception {
		// chiamata del setter della classe 
		return getFactoryCriptazioneByte(value, DECIFRA);
	}

	@Override
	public String unmarshal(String value) throws Exception {
		// chiamata del getter della classe
		return getFactoryCriptazioneByte(value, CIFRA);		
	}

} 
