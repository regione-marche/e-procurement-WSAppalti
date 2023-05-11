package it.maggioli.eldasoft.sil.lfs.db.dao.mybatis;

import it.eldasoft.utils.sql.comp.SqlComposer;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.eldasoft.utils.sql.comp.SqlManager;
import it.maggioli.eldasoft.sil.gare.ws.DocumentoAllegatoType;
import it.maggioli.eldasoft.sil.lfs.db.dao.ContrattiLFSDao;
import it.maggioli.eldasoft.sil.lfs.db.mapper.ContrattiLFSMapper;
import it.maggioli.eldasoft.sil.lfs.ws.ContrattoLFSType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static it.maggioli.eldasoft.sil.gare.util.DAOUtils.getBounds;
import static it.maggioli.eldasoft.sil.gare.util.DAOUtils.getWithPercent;

/**
 * Classe di appoggio per l'esecuzione tramite estensione del framework Spring
 * di interazioni con le tabelle per la gestione dei contratti LFS.
 * 
 * @author ...
 */
@Component
public class SqlMapContrattiLFSDao implements ContrattiLFSDao {

	/** tipologia di DBMS da property */
	@Resource
	private String dbms;
	@Autowired
	private ContrattiLFSMapper contrattiLFSMapper;

	@Override
	public List<ContrattoLFSType> searchContrattiLFS(
			String stazioneAppaltante,
			String oggetto, 
			String cig, 
			String gara,
			String codice,
			String username,
			int indicePrimoRecord,
			int maxNumRecord) throws DataAccessException, SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(this.dbms);

		return contrattiLFSMapper.searchContrattiLFS(
				  username
				, stazioneAppaltante
				, composer.getFunzioneUpperCase()
				, getWithPercent(oggetto != null ? oggetto.toUpperCase() : "")
				, getWithPercent(cig != null ? cig.toUpperCase() : "")
				, getWithPercent(gara != null ? gara.toUpperCase() : "")
				, getWithPercent(codice != null ? codice.toUpperCase() : "")
				, getBounds(indicePrimoRecord, maxNumRecord)
		);
	}

	@Override
	public ContrattoLFSType getDettaglioContrattoLFS(
			String tokenRichiedente,
			String codice, 
			String nappal) {
		return contrattiLFSMapper.getDettaglioContrattoLFS(tokenRichiedente, codice, nappal);
	}
	
	public List<DocumentoAllegatoType> getAllegatiContrattoLFS(String codice) {
		return contrattiLFSMapper.getAllegatiContrattoLFS(codice);
	}

	@Override
	public int countContrattiLFS(
			String stazioneAppaltante, 
			String oggetto,
			String cig, 
			String gara, 
			String codice, 
			String tokenRichiedente) throws DataAccessException, SqlComposerException 
	{
		SqlComposer composer = SqlManager.getComposer(dbms);
		Integer risultato = contrattiLFSMapper.countContrattiLFS(
				tokenRichiedente, stazioneAppaltante, composer.getFunzioneUpperCase(), oggetto, cig, gara, codice
		);
		return risultato;
	}

}
