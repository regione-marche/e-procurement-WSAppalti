package it.maggioli.eldasoft.sil.lfs.ws;

import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.lfs.bl.ContrattiLFSManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@javax.jws.WebService(
      serviceName = "WSLfsSoap",
      portName = "WSLfsSOAP",
      targetNamespace = "http://www.eldasoft.it/sil/WSLfs/",
      endpointInterface = "it.maggioli.eldasoft.sil.lfs.ws.WSLfsSoap"
)                      
public class WSLfsSoapImpl implements WSLfsSoap {

	private static final Logger LOG = LoggerFactory.getLogger(WSLfsSoapImpl.class);

	@Autowired
	private ContrattiLFSManager contrattiLFSManager;

	@Override
	public ElencoContrattiLFSOutType searchContrattiLFS(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String gara, 
			String codice,
			String tokenRichiedente,
			int indicePrimoRecord,
	        int maxNumRecord) {
		if (LOG.isDebugEnabled())
			LOG.debug("searchContrattiLFS(" 
					+ stazioneAppaltante + "," 
					+ oggetto + "," 
					+ cig + "," 
					+ gara + ","  
					+ codice + ","
					+ tokenRichiedente +"): inizio metodo");//

		ElencoContrattiLFSOutType risultato = new ElencoContrattiLFSOutType();
		
		try {
			int count = this.contrattiLFSManager.countContrattiLFS(
					stazioneAppaltante, 
					oggetto, 
					cig, 
					gara,
					codice,
					tokenRichiedente);
			risultato.setNum(count);
			List<ContrattoLFSType> lista = this.contrattiLFSManager.searchContrattiLFS(
					stazioneAppaltante, 
					oggetto, 
					cig, 
					gara,
					codice,
					tokenRichiedente,
					indicePrimoRecord,
			        maxNumRecord);
			
			risultato.setElenco(lista);
			risultato.setNum(count);
		} catch (DataAccessException e) {
			LOG.error("Errore inaspettato durante l'interazione con la base dati",
					  e);
			risultato.setErrore(e.getMessage());
		} catch (SqlComposerException e) {
			LOG.error("DBMS non supportato o non valido", e);
			risultato.setErrore("DBMS non supportato o non valido");
		}

		LOG.debug("searchContrattiLFS: fine metodo");

		return risultato;
	}

	@Override
	public GetDettaglioContrattoLFSOutType getDettaglioContrattoLFS(
			String tokenRichiedente,
			String codice, 
			String nappal) {
		if (LOG.isDebugEnabled())
			LOG.debug("getDettaglioContrattoLFS(" + tokenRichiedente + ", " 
					+ codice + ", " + nappal + "): inizio metodo");

		GetDettaglioContrattoLFSOutType risultato = new GetDettaglioContrattoLFSOutType();
		
		try {
			ContrattoLFSType dettaglio = this.contrattiLFSManager
					.getDettaglioContrattoLFS(tokenRichiedente, codice, nappal);
			risultato.setContratto(dettaglio);
		} catch (DataAccessException e) {
			LOG.error("Errore inaspettato durante l'interazione con la base dati",
					  e);
			risultato.setErrore(e.getMessage());
		} catch (SqlComposerException e) {
			LOG.error("DBMS non supportato o non valido", e);
			risultato.setErrore("DBMS non supportato o non valido");
		}

		LOG.debug("getDettaglioContrattoLFS: fine metodo");

		return risultato;
	}

}