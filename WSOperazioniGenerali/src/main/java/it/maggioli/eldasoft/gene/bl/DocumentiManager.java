/*
 * Created on 27/feb/14
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di 
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.gene.bl;

import it.maggioli.eldasoft.gene.bean.ObjectStorageConfiguration;
import it.maggioli.eldasoft.gene.bean.rest.ObjectStorageClient;
import it.maggioli.eldasoft.gene.db.dao.DocumentiDao;
import it.maggioli.eldasoft.gene.ws.FileType;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static it.maggioli.eldasoft.gene.xml.CriptazioneByteAdapter.CIFRA;
import static it.maggioli.eldasoft.gene.xml.CriptazioneByteAdapter.getFactoryCriptazioneByte;

/**
 * Manager per la gestione dei documenti digitali.
 * 
 * @author Stefano.Sabbadin
 */
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DocumentiManager {

	private final DocumentiDao documentiDao;
	private final ObjectStorageClient client;
	private final ObjectStorageConfiguration obs_conf;
	
	public FileType getDocumentoDigitale(final String idProgramma, final long idDocumento, final String tokenRichiedente) {
		val file = documentiDao.getDocumentoDigitale(
				idProgramma
				, idDocumento
				, tokenRichiedente
				, getFactoryCriptazioneByte(tokenRichiedente, CIFRA)
		);
		if (StringUtils.isNotEmpty(file.getBucket_uuid()))
			file.setFile(client.download(file.getBucket_ref(), file.getBucket_uuid()));

		return file;
	}
	
	public String getUsernameDocumentoRiservato(String idProgramma, long idDocumento) {
		return this.documentiDao.getUsernameDocumentoRiservato(idProgramma, idDocumento);
	}
	
}
