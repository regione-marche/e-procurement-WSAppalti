/*
 * Created on 25/ott/10
 *
 * Copyright (c) EldaSoft S.p.A.
 * Tutti i diritti sono riservati.
 *
 * Questo codice sorgente e' materiale confidenziale di proprieta' di EldaSoft S.p.A.
 * In quanto tale non puo' essere distribuito liberamente ne' utilizzato a meno di 
 * aver prima formalizzato un accordo specifico con EldaSoft.
 */
package it.maggioli.eldasoft.sil.gare.bl;

import it.eldasoft.sil.portgare.datatypes.*;
import it.eldasoft.utils.sql.comp.SqlComposerException;
import it.maggioli.eldasoft.sil.gare.db.dao.ImpreseDao;
import it.maggioli.eldasoft.sil.gare.db.vo.PKReferenteImpresa;
import it.maggioli.eldasoft.sil.gare.ws.VendorRatingType;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;
import org.apache.xmlbeans.XmlBeans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Stefano.Sabbadin
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ImpreseManager {

    private static final Logger logger = LoggerFactory.getLogger(ImpreseManager.class);

	@Autowired
    private ImpreseDao impreseGaraDao;

	public DatiImpresaDocument getDatiImpresa(String token, Date dataPartenzaCessati) {
		// fase di estrazione di tutti i dati richiesti
		String codiceImpresa = this.impreseGaraDao
				.getPKImpresaByUsername(token);

		if (codiceImpresa == null) {
			// si ritorna un documento vuoto solo se il token usato non
			// individua un utente correlato ad un'impresa
			return null;
		}

		// l'impresa esiste sicuramente per cui tutte le seguenti query, essendo
		// sempre sulla tabella IMPR, estraggono dati eventualmente non
		// valorizzati
		ImpresaAggiornabileType impresa = this.impreseGaraDao
				.getImpresa(codiceImpresa);
		IndirizzoAggiornabileType sedeLegale = this.impreseGaraDao
				.getSedeLegaleImpresa(codiceImpresa);
		RecapitiAggiornabileType recapiti = this.impreseGaraDao
				.getRecapitiImpresa(codiceImpresa);
		List<IndirizzoEstesoAggiornabileType> indirizzi = this.impreseGaraDao
				.getIndirizziImpresa(codiceImpresa);
		CameraCommercioAggiornabileType cciiaa = this.impreseGaraDao
				.getDatiCCIAAImpresa(codiceImpresa);
		INPSAggiornabileType inps = this.impreseGaraDao
				.getDatiINPSImpresa(codiceImpresa);
		INAILAggiornabileType inail = this.impreseGaraDao
				.getDatiINAILImpresa(codiceImpresa);
		CassaEdileAggiornabileType cassaEdile = this.impreseGaraDao
				.getDatiCassaEdileImpresa(codiceImpresa);
		SOAAggiornabileType soa = this.impreseGaraDao
				.getDatiSOAImpresa(codiceImpresa);
		ISO9001AggiornabileType iso9001 = this.impreseGaraDao
				.getDatiISO9001Impresa(codiceImpresa);
		IscrizioneWhitelistAntimafiaAggiornabileType iscrizioneWhitelist = this.impreseGaraDao
                .getDatiIscrizioneWhitelistImpresa(codiceImpresa);
		IscrizioneElenchiRicostruzioneAggiornabileType iscrizioneElenchiRicostruzione = this.impreseGaraDao
        		.getDatiIscrizioneElenchiRicostruzioneImpresa(codiceImpresa);
		RatingLegalitaAggiornabileType rating = this.impreseGaraDao
        		.getDatiRatingLegalitaImpresa(codiceImpresa);
		ContoCorrenteDedicatoAggiornabileType ccDedicato = this.impreseGaraDao
				.getDatiContoCorrenteDedicatoImpresa(codiceImpresa);
		AbilitazionePreventivaAggiornabileType abilitazionePreventiva = this.impreseGaraDao
				.getDatiAbilitazionePreventivaImpresa(codiceImpresa);

		impresa.setSedeLegale(sedeLegale);
		impresa.setRecapiti(recapiti);
		impresa.setIndirizzoArray(indirizzi.toArray(new IndirizzoEstesoAggiornabileType[] {}));
		impresa.setCciaa(cciiaa);
		impresa.setInps(inps);
		impresa.setInail(inail);
		impresa.setCassaEdile(cassaEdile);
		impresa.setSoa(soa);
		impresa.setIso9001(iso9001);
		impresa.setIscrizioneWhitelistAntimafia(iscrizioneWhitelist);
		impresa.setIscrizioneElenchiRicostruzione(iscrizioneElenchiRicostruzione);
		impresa.setRatingLegalita(rating);
		impresa.setContoCorrente(ccDedicato);
		impresa.setAbilitazionePreventiva(abilitazionePreventiva);

		List<DatoCodificatoType> elencoTipiImpresa = this.impreseGaraDao
				.getElencoTipiImpresaLiberoProfessionista();
		boolean impresaIndividuale = false;
		for (DatoCodificatoType tipo : elencoTipiImpresa) {
			if (tipo.getCodice().equals(impresa.getTipoImpresa())) {
				impresaIndividuale = true;
				break;
			}
		}

		ReferenteImpresaAggiornabileType datiIncarico = null;
		ReferenteImpresaAggiornabileType[] listaLR = null;
		ReferenteImpresaAggiornabileType[] listaDT = null;
		ReferenteImpresaAggiornabileType[] listaAL = null;
		ReferenteImpresaAggiornabileType[] listaCO = null;
		AltriDatiAnagraficiAggiornabileType altriDati = null;

		if (!impresaIndividuale) {
			// si caricano i diversi tipi di soggeti

			// teoricamente dovrebbe sempre esserci, comunque sia per sicurezza
			// si
			// estraggono i dati solo se esistono, tanto poi la validazione
			// degli
			// oggetti fara' fallire l'invio della risposta
			List<PKReferenteImpresa> pkLegaliRappr = this.impreseGaraDao
					.getPKLegaliRappresentantiImpresa(codiceImpresa,dataPartenzaCessati);
			listaLR = this.setSoggettiImpresa(codiceImpresa, pkLegaliRappr);
			for (int i = 0; i < listaLR.length; i++) {
				datiIncarico = this.impreseGaraDao
						.getIncaricoLegaleRappresentanteImpresa(codiceImpresa,
								pkLegaliRappr.get(i));
				listaLR[i].setDataInizioIncarico(datiIncarico
						.getDataInizioIncarico());
				listaLR[i].setDataFineIncarico(datiIncarico
						.getDataFineIncarico());
				listaLR[i].setQualifica(datiIncarico.getQualifica());
				listaLR[i].setNote(datiIncarico.getNote());
				listaLR[i].setResponsabileDichiarazioni(datiIncarico.getResponsabileDichiarazioni());
				listaLR[i].setSolaLettura(datiIncarico.getDataFineIncarico() != null);
			}

			// i direttori tecnici potrebbero invece non esserci
			List<PKReferenteImpresa> pkDirTecnici = this.impreseGaraDao
					.getPKDirettoriTecniciImpresa(codiceImpresa,dataPartenzaCessati);
			listaDT = this.setSoggettiImpresa(codiceImpresa, pkDirTecnici);
			for (int i = 0; i < listaDT.length; i++) {
				datiIncarico = this.impreseGaraDao
						.getIncaricoDirettoreTecnicoImpresa(codiceImpresa,
								pkDirTecnici.get(i));
				listaDT[i].setDataInizioIncarico(datiIncarico
						.getDataInizioIncarico());
				listaDT[i].setDataFineIncarico(datiIncarico
						.getDataFineIncarico());
				listaDT[i].setQualifica(datiIncarico.getQualifica());
				listaDT[i].setNote(datiIncarico.getNote());
				listaDT[i].setResponsabileDichiarazioni(datiIncarico.getResponsabileDichiarazioni());
				listaDT[i].setSolaLettura(datiIncarico.getDataFineIncarico() != null);
			}

			// gli altri incarichi potrebbero invece non esserci
			List<PKReferenteImpresa> pkAltreCariche = this.impreseGaraDao
					.getPKAltreCaricheImpresa(codiceImpresa,dataPartenzaCessati);
			listaAL = this.setSoggettiImpresa(codiceImpresa, pkAltreCariche);
			for (int i = 0; i < listaAL.length; i++) {
				datiIncarico = this.impreseGaraDao
						.getIncaricoAltraCaricaImpresa(codiceImpresa,
								pkAltreCariche.get(i));
				listaAL[i].setDataInizioIncarico(datiIncarico
						.getDataInizioIncarico());
				listaAL[i].setDataFineIncarico(datiIncarico
						.getDataFineIncarico());
				listaAL[i].setQualifica(datiIncarico.getQualifica());
				listaAL[i].setNote(datiIncarico.getNote());
				listaAL[i].setResponsabileDichiarazioni(datiIncarico.getResponsabileDichiarazioni());
				listaAL[i].setSolaLettura(datiIncarico.getDataFineIncarico() != null);
			}

			// i collaboratori potrebbero invece non esserci
			List<PKReferenteImpresa> pkCollaboratori = this.impreseGaraDao
					.getPKCollaboratoriImpresa(codiceImpresa,dataPartenzaCessati);
			listaCO = this.setSoggettiImpresa(codiceImpresa, pkCollaboratori);
			for (int i = 0; i < listaCO.length; i++) {
				datiIncarico = this.impreseGaraDao
						.getIncaricoCollaboratoreImpresa(codiceImpresa,
								pkCollaboratori.get(i));
				listaCO[i].setDataInizioIncarico(datiIncarico
						.getDataInizioIncarico());
				listaCO[i].setDataFineIncarico(datiIncarico
						.getDataFineIncarico());
				listaCO[i].setQualifica(datiIncarico.getQualifica());
				listaCO[i].setNote(datiIncarico.getNote());
				listaCO[i].setNilResponsabileDichiarazioni();
				listaCO[i].setSolaLettura(datiIncarico.getDataFineIncarico() != null);
			}
		} else {
			// si caricano gli ulteriori dati anagrafici del singolo soggetto
			altriDati = this.impreseGaraDao
					.getAltriDatiLiberoProfessionista(codiceImpresa);

			AlboProfessionaleAggiornabileType albo = this.impreseGaraDao
					.getAlboProfessionaleLiberoProfessionista(codiceImpresa);
			CassaPrevidenzaAggiornabileType cassa = this.impreseGaraDao
					.getCassaPrevidenzaLiberoProfessionista(codiceImpresa);

			altriDati.setAlboProfessionale(albo);
			altriDati.setCassaPrevidenza(cassa);
		}
		
		DatoAnnuoImpresaAggiornabileType[] datiUltimoTriennio = this.getDatiUltimoTriennio(codiceImpresa);

		// fase di composizione del documento xml di risposta con tutti i dati
		// richiesti
		DatiImpresaDocument documento =
				(DatiImpresaDocument)
						XmlBeans.getContextTypeLoader().newInstance(DatiImpresaDocument.type, null);

		DatiImpresaAggiornabiliType datiImpresa = documento.addNewDatiImpresa();
		datiImpresa.setImpresa(impresa);
		if (listaLR != null)
			datiImpresa.setLegaleRappresentanteArray(listaLR);
		if (listaDT != null)
			datiImpresa.setDirettoreTecnicoArray(listaDT);
		if (listaAL != null)
			datiImpresa.setAltraCaricaArray(listaAL);
		if (listaCO != null)
			datiImpresa.setCollaboratoreArray(listaCO);
		if (altriDati != null)
			datiImpresa.getImpresa().setAltriDatiAnagrafici(altriDati);
		datiImpresa.getImpresa().setDatoAnnuoArray(datiUltimoTriennio);

		return documento;
	}

	private DatoAnnuoImpresaAggiornabileType[] getDatiUltimoTriennio(
			String codiceImpresa) {
		DatoAnnuoImpresaAggiornabileType[] risultato = new DatoAnnuoImpresaAggiornabileType[3];
		Calendar c = new GregorianCalendar();
		int anno = c.get(Calendar.YEAR);
		risultato[0] = this.getDatiAnnui(codiceImpresa, anno - 1);
		risultato[1] = this.getDatiAnnui(codiceImpresa, anno - 2);
		risultato[2] = this.getDatiAnnui(codiceImpresa, anno - 3);
		return risultato;
	}

	private DatoAnnuoImpresaAggiornabileType getDatiAnnui(String codiceImpresa,
			int anno) {
		DatoAnnuoImpresaAggiornabileType risultato = this.impreseGaraDao
				.getDatiAnnui(codiceImpresa, anno);
		if (risultato == null) {
			risultato =
					(DatoAnnuoImpresaAggiornabileType)
							XmlBeans.getContextTypeLoader().newInstance(DatoAnnuoImpresaAggiornabileType.type, null);

			risultato.setAnno(anno);
			risultato.setNilDipendenti();
		}
		return risultato;
	}

	/**
	 * Estrae i dati di tutti i soggetti di una tipologia e crea il contenitore
	 * popolato con i dati estratti.
	 * 
	 * @param codiceImpresa
	 *            PK impresa
	 * @param listaPKSoggetti
	 *            PK dei soggetti collegati all'impresa
	 * @return lista di soggetti con i dati di dettaglio per ogni soggetto
	 *         individuato dalle chiavi in input
	 */
	private ReferenteImpresaAggiornabileType[] setSoggettiImpresa(
			String codiceImpresa, List<PKReferenteImpresa> listaPKSoggetti) {
		ReferenteImpresaAggiornabileType[] listaSoggetti = new ReferenteImpresaAggiornabileType[listaPKSoggetti
				.size()];

		ReferenteImpresaAggiornabileType soggetto = null;
		IndirizzoAggiornabileType residenza = null;
		AlboProfessionaleAggiornabileType albo = null;
		CassaPrevidenzaAggiornabileType cassa = null;
		for (int i = 0; i < listaPKSoggetti.size(); i++) {
			soggetto = this.impreseGaraDao
					.getDatiReferenteImpresa(listaPKSoggetti.get(i).getCodice());
			residenza = this.impreseGaraDao
					.getIndirizzoReferenteImpresa(listaPKSoggetti.get(i).getCodice());
			soggetto.setResidenza(residenza);
			albo = this.impreseGaraDao
					.getAlboProfessionaleReferenteImpresa(listaPKSoggetti
							.get(i).getCodice());
			soggetto.setAlboProfessionale(albo);
			cassa = this.impreseGaraDao
					.getCassaPrevidenzaReferenteImpresa(listaPKSoggetti.get(i).getCodice());
			soggetto.setCassaPrevidenza(cassa);

			soggetto.setEsistente(true);
			listaSoggetti[i] = soggetto;
		}
		return listaSoggetti;
	}

	public Boolean isImpresaRegistrata(String codiceFiscale, String partitaIva, boolean isGruppoIva) {
		return this.impreseGaraDao.isImpresaRegistrata(codiceFiscale,
				partitaIva, isGruppoIva);
	}
	
	public String getImpresaRegistrata(
			String codiceFiscale,
			String partitaIva, 
			String email, 
			String pec) 
	{
		return this.impreseGaraDao.getImpresaRegistrata(codiceFiscale, partitaIva, email, pec);
	}

    public List<DatoCodificatoType> getElencoTipiImpresaPerIscrizione() {
    	return this.impreseGaraDao.getElencoTipiImpresaPerIscrizione();
    }

	public List<DatoCodificatoType> getElencoTipiImpresaDittaIndividuale() {
		return this.impreseGaraDao.getElencoTipiImpresaDittaIndividuale();
	}

	public List<DatoCodificatoType> getElencoTipiImpresaLiberoProfessionista() {
		return this.impreseGaraDao.getElencoTipiImpresaLiberoProfessionista();
	}

	public List<DatoCodificatoType> getElencoTipiImpresaSociale() {
		return this.impreseGaraDao.getElencoTipiImpresaSociale();
	}
	
	public List<DatoCodificatoType> getElencoTipiImpresaConsorzio() {
		return this.impreseGaraDao.getElencoTipiImpresaConsorzio();
	}
	
	public String getIdImpresa(String username) {
		return this.impreseGaraDao.getIdImpresa(username);
	}

	public VendorRatingType getVendorRating(String tokenRichiedente, Date data) throws DataAccessException, SqlComposerException {

		VendorRatingType vendorRating = new VendorRatingType();
		VendorRatingType vendorRatingValidita = impreseGaraDao.getVendorRatingValidita(tokenRichiedente, data);
		VendorRatingType vendorRatingSospensione = impreseGaraDao.getVendorRatingSospensione(tokenRichiedente, data);
		if(vendorRatingValidita != null) {
			vendorRating.setIndiceVR(vendorRatingValidita.getIndiceVR());
			vendorRating.setDataInizioValidita(vendorRatingValidita.getDataInizioValidita());
			vendorRating.setDataFineValidita(vendorRatingValidita.getDataFineValidita());
		}
		if(vendorRatingSospensione != null) {
			vendorRating.setDataInizioSospensione(vendorRatingSospensione.getDataInizioSospensione()); //optional
			vendorRating.setDataFineSospensione(vendorRatingSospensione.getDataFineSospensione()); //optional
		}
		return vendorRating;
	}

}
