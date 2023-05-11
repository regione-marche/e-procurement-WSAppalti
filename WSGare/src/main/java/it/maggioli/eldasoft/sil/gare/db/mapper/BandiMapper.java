package it.maggioli.eldasoft.sil.gare.db.mapper;

import it.maggioli.eldasoft.sil.gare.db.vo.*;
import it.maggioli.eldasoft.sil.gare.ws.*;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.List;

@Mapper
public interface BandiMapper {

    public String getAppaltiVersion();
    
    public List<DatoCodificatoType> getElencoStazioniAppaltanti();
    
    public List<DatoCodificatoType> getElencoStazioniAppaltantiL190();
    
    public List<DatoCodificatoType> getStatiGara();
    
    public List<DatoCodificatoType> getEsitiGara();
    
    public List<GaraType> searchBandi(
			  @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
			, @Param("orderCriteria") String orderCriteria
			, RowBounds bounds
	);
    
    public Integer countBandi(
			  @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
			);

    public List<GaraType> getElencoBandi(
			  @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("garaPrivatistica") Integer garaPrivatistica
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
			, @Param("orderCriteria") String orderCriteria
			, RowBounds bounds
	);
    
    public Integer countElencoBandi(
			  @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("garaPrivatistica") Integer garaPrivatistica
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
	);
    
    public List<GaraType> getElencoBandiScaduti(
			  @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("esito") String esito
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("visualizzaNegoziate") Boolean visualizzaNegoziate
			, @Param("garaPrivatistica") Integer garaPrivatistica
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
			, @Param("orderCriteria") String orderCriteria
			, RowBounds bounds
	);
    
    public Integer countElencoBandiScaduti(
			  @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("esito") String esito
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("visualizzaNegoziate") Boolean visualizzaNegoziate
			, @Param("garaPrivatistica") Integer garaPrivatistica
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
	);
    
    public String getCigBando(
			@Param("codice") String codice
	);
    
    public StazioneAppaltanteBandoType getStazioneAppaltanteBando(
			@Param("codice") String codice
	);
    
    public GaraType getBando(
			@Param("codice") String codice
	);
    
    public List<DocumentoAllegatoType> getDocumentiBandoLotto(
			  @Param("codiceGara") String codiceGara
			, @Param("codiceLotto") String codiceLotto
	);
    
    public List<DocumentoAllegatoType> getDocumentiInvitoLotto(
			  @Param("codiceGara") String codiceGara
			, @Param("codiceLotto") String codiceLotto
	);
    
    public List<DocumentoAllegatoType> getDocumentiInvitoAstaLotto(
			  @Param("codiceGara") String codiceGara
			, @Param("codiceLotto") String codiceLotto
	);
    
    public List<RequisitoRichiestoType> getRequisitiRichiestiBandoLotto(
			  @Param("codiceGara") String codiceGara
			, @Param("codiceLotto") String codiceLotto
	);
    
    public List<RequisitoRichiestoLotto> getRequisitiRichiestiBando(
			  @Param("codiceGara") String codiceGara
	);
    
    public List<LottoGaraType> getLottiGara(
			  @Param("codiceGara") String codiceGara
	);
    
    public List<DocumentoAllegatoLotto> getDocumentiGara(
			  @Param("codiceGara") String codiceGara
	);
    
    public List<LottoGaraType> getLottiGaraPerDomandePartecipazione(
			  @Param("codiceGara") String codiceGara
			, @Param("progressivoOfferta") String progressivoOfferta
	);
    
    public List<LottoGaraType> getLottiGaraPerRichiesteOfferta(
			  @Param("codiceGara") String codiceGara
			, @Param("username") String username
			, @Param("progressivoOfferta") String progressivoOfferta
	);
    
    public List<LottoGaraType> getLottiGaraPlicoUnicoPerRichiesteOfferta(
			  @Param("codiceGara") String codiceGara
			, @Param("username") String username
		    , @Param("progressivoOfferta") String progressivoOfferta
	);
    
    public List<LottoGaraType> getLottiGaraPerRichiesteCheckDocumentazione(
			  @Param("codiceGara") String codiceGara
			, @Param("username") String username
	);
    
    public List<BandoIscrizioneType> getElencoBandiIscrizione(
			  @Param("genere") Integer genere
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("isAttivo") Boolean isAttivo
	);
    
    public StazioneAppaltanteBandoType getStazioneAppaltanteBandoIscrizione(
			  @Param("codice") String codice
	);
    
    public BandoIscrizioneType getBandoIscrizione(
			  @Param("codice") String codice
	);
    
    public List<DocumentoAllegatoType> getDocumentiBandoIscrizione(
			  @Param("codice") String codice
	);
    
    public List<RequisitoRichiestoType> getRequisitiRichiestiBandoIscrizione(
			  @Param("codice") String codice
	);
    
    public List<CategoriaGaraType> getCategorieLotto(
			  @Param("codiceLotto") String codiceLotto
	);
    
    public List<CategoriaBandoIscrizioneType> getCategorieBandoIscrizione(
			  @Param("codice") String codice
			, @Param("livello1") String livello1
			, @Param("livello2") String livello2
			, @Param("livello3") String livello3
			, @Param("livello4") String livello4
			, @Param("filtroCategorie") String filtroCategorie
			, @Param("operatoreUpper") String operatoreUpper

	);
    
    public ImpresaIscrizioneType getImpresaIscrizione(
			  @Param("username") String username
			, @Param("codice") String codice
	);
    
    public List<ComunicazioneType> getComunicazioniRicevute(
			  @Param("username") String username
			, @Param("codice") String codice
			, @Param("dataInvioDa") Date dataInvioDa
			, @Param("dataInvioA") Date dataInvioA
			, @Param("stazioneAppaltante") String stazioneAppaltante
		  	, @Param("modello") String modello
			, @Param("entita") String entita
			, @Param("entita2") String entita2
			, RowBounds bounds
	);
    
    public List<ComunicazioneLotto> getComunicazioniPubblicheRicevute(
			  @Param("applicativo") String applicativo
			, @Param("codice") String codice
			, @Param("codice2") String codice2
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("entita") String entita
	);
    
    public List<ComunicazioneType> getComunicazioniRicevuteLotti(
			  @Param("username") String username
			, @Param("codiciLotto") String[] codiciLotto
			, @Param("dataInvioDa") Date dataInvioDa
			, @Param("dataInvioA") Date dataInvioA
			, @Param("entita") String entita
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("modello") String modello
			, RowBounds bounds
	);
    
    public List<ComunicazioneType> getComunicazioniInviate(
			  @Param("username") String username
			, @Param("codice") String codice
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("entita") String entita
			, RowBounds bounds
	);
    
    public Integer countComunicazioniRicevute(
			  @Param("username") String username
			, @Param("codice") String codice
			, @Param("codice2") String codice2
			, @Param("dataInvioDa") Date dataInvioDa
			, @Param("dataInvioA") Date dataInvioA
			, @Param("soloDaLeggere") Boolean soloDaLeggere
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("modello") String modello
			, @Param("entita") String entita
			, @Param("entita2") String entita2
	);
    
    public Integer countComunicazioniRicevuteLotti(
			  @Param("username") String username
			, @Param("codiciLotto") String[] codiciLotto
			, @Param("dataInvioDa") Date dataInvioDa
			, @Param("dataInvioA") Date dataInvioA
			, @Param("soloDaLeggere") Boolean soloDaLeggere
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("modello") String modello
			, @Param("entita") String entita
	);
    
    public Integer countComunicazioniInviate(
			  @Param("username") String username
			, @Param("codice") String codice
			, @Param("codice2") String codice2
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("entita") String entita
	);
    
    public List<DocumentoAllegatoType> getDocumentiComunicazione(
			  @Param("applicativo") String applicativo
			, @Param("id") Long id
	);
    
    public List<DocumentoAllegatoLotto> getDocumentiComunicazioniPubblicheRicevute(
			  @Param("applicativo") String applicativo
			, @Param("codice") String codice
			, @Param("codice2") String codice2
	);
    
    public ComunicazioneType getComunicazioneRicevuta(
			  @Param("username") String username
			, @Param("id") Long id
			, @Param("idDestinatario") Long idDestinatario
			, @Param("idprg") String idprg
	);
    
    public ComunicazioneType getComunicazioneInviata(
			  @Param("username") String username
			, @Param("id") Long id
			, @Param("idprg") String idprg	//Parametro non utilizzato
	);
    
    public List<InvioType> getElencoInvii(
			  @Param("username") String username
			, @Param("codice") String codice
			, @Param("tipiComunicazione") String[] tipiComunicazione
	);
    
    public Integer getStatoIscrizioneABandoIscrizione(
			  @Param("username") String username
			, @Param("codice") String codice
	);
    
    public List<DocumentazioneRichiestaType> getDocumentiRichiestiBandoGara(
			  @Param("codiceGara") String codiceGara
			, @Param("codiceLotto") String codiceLotto
			, @Param("tipoImpresa") Integer tipoImpresa
			, @Param("busta") Integer busta
	);
    
    public List<DocumentazioneRichiestaLotto> getDocumentiRichiestiBando(
			  @Param("codiceGara") String codiceGara
			, @Param("tipoImpresa") Integer tipoImpresa
	);
    
    public List<DocumentazioneRichiestaType> getDocumentiRichiestiBandoIscrizione(
			  @Param("codice") String codice
			, @Param("tipoImpresa") Integer tipoImpresa
	);
    
    public List<DocumentazioneRichiestaType> getDocumentiRichiestiRinnovoIscrizione(
			  @Param("codice") String codice
			, @Param("tipoImpresa") Integer tipoImpresa
	);
    
    public List<DocumentazioneRichiestaType> getDocumentiRichiestiComunicazione(
			  @Param("idPrg") String idPrg
			, @Param("idComunicazione") Long idComunicazione
	);
    
    public List<DatoCodificatoType> getStatiIscrizioneElencoOperatori();
    
    public List<GaraType> getElencoBandiPerRichiesteOfferta(
			  @Param("username") String username
			, @Param("adesso") Date adesso
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("garaPrivatistica") Integer garaPrivatistica
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
			, @Param("orderCriteria") String orderCriteria
			, RowBounds bounds
	);
    
    public Integer countElencoBandiPerRichiesteOfferta(
			  @Param("username") String username
			, @Param("adesso") Date adesso
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("garaPrivatistica") Integer garaPrivatistica
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
	);
    
    public List<GaraType> getElencoBandiPerRichiesteCheckDocumentazione(
			  @Param("username") String username
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
		 	, @Param("garaPrivatistica") Integer garaPrivatistica
			, @Param("orderCriteria") String orderCriteria
			, RowBounds bounds
	);
    
    public Integer countElencoBandiPerRichiesteCheckDocumentazione(
			  @Param("username") String username
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
			, @Param("garaPrivatistica") Integer garaPrivatistica
	);
    
    public List<GaraType> getElencoBandiPerAsteInCorso(
			  @Param("username") String username
			, @Param("adesso") Date adesso
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("garaPrivatistica") Boolean garaPrivatistica
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
			, @Param("orderCriteria") String orderCriteria
			, RowBounds bounds
	);
    
    public Integer countBandiPerAsteInCorso(
			  @Param("username") String username
			, @Param("adesso") Date adesso
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("garaPrivatistica") Boolean garaPrivatistica
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
	);
    
    public Integer getAbilitazionePartecipazioneGara(
			  @Param("username") String username
			, @Param("codice") String codice
	);
    
    public Integer getAbilitazionePresentazioneOfferta(
			  @Param("username") String username
			, @Param("codice") String codice
	);
    
    public Integer getAbilitazioneCheckDocumentazione(
			  @Param("username") String username
			, @Param("codice") String codice
	);
    
    public TipoPartecipazioneType getTipoPartecipazioneImpresa(
			  @Param("username") String username
			, @Param("codice") String codice
			, @Param("progressivoOfferta") String progressivoOfferta
	);
    
    public List<DatoCodificatoType> getElencoStazioniAppaltantiPerIscrizione(
			  @Param("codice") String codice
	);
    
    public List<CategoriaImpresaType> getCategorieImpresaPerIscrizione(
			  @Param("username") String username
			, @Param("codice") String codice
	);
    
    public List<DatoCodificatoType> getElencoClassificazioniLavoriPerIscrizione();
    
    public List<GaraType> searchBandiPerProcInAggiudicazione(
			  @Param("username") String username
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("esito") String esito
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("garaPrivatistica") Integer garaPrivatistica
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
			, @Param("orderCriteria") String orderCriteria
			, RowBounds bounds
	);
    
    public Integer countBandiPerProcInAggiudicazione(
			  @Param("username") String username
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("esito") String esito
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("garaPrivatistica") Integer garaPrivatistica
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
	);
    
    public List<GaraType> searchBandiConEsito(
			  @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("esito") String esito
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
			, @Param("orderCriteria") String orderCriteria
			, RowBounds bounds
	);
    
    public Integer countBandiConEsito(
			  @Param("annoMinimoPubblicazione") Integer annoMinimoPubblicazione
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("tipoAppalto") Integer tipoAppalto
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("dataScadenzaDa") Date dataScadenzaDa
			, @Param("dataScadenzaA") Date dataScadenzaA
			, @Param("proceduraTelematica") Boolean proceduraTelematica
			, @Param("stato") Integer stato
			, @Param("esito") String esito
			, @Param("altriSoggetti") Integer altriSoggetti
			, @Param("sommaUrgenza") Boolean sommaUrgenza
			, @Param("isGreen") String isGreen
			, @Param("isRecycle") String isRecycle
			, @Param("isPnrr") String isPnrr
			, @Param("codice") String codice
	);
    
    public List<BandoIscrizioneType> searchBandiIscrizione(
			  @Param("username") String username
			, @Param("genere") Integer genere
			, @Param("stato") Integer stato
			, @Param("stazioneAppaltante") String stazioneAppaltante
		  	, @Param("isAttivo") Boolean isAttivo
	);
    
    public Integer isGaraConOffertaTecnica(
			  @Param("codiceGara") String codiceGara
	);
    
    public Integer isImpresaAbilitataRinnovo(
			  @Param("username") String username
			, @Param("codiceBandoIscrizione") String codiceBandoIscrizione
	);
    
    public List<VoceDettaglioOffertaType> getVociDettaglioOfferta(
			  @Param("codice") String codice
	);
    
    public List<VoceDettaglioOffertaType> getVociDettaglioOffertaNoRibasso(
			  @Param("codice") String codice
	);
    
    public List<AttributoAggiuntivoOfferta> getAttributiAggiuntiviOfferta(
			  @Param("codice") String codice
	);
    
    public DettaglioStazioneAppaltanteType getDettaglioStazioneAppaltante(
			  @Param("codice") String codice
	);
    
    public List<DettaglioStazioneAppaltanteType> getStazioniAppaltanti();
    
    public List<String> getPuntiOrdinanti(
			  @Param("codice") String codice
	);
    
    public List<String> getPuntiIstruttori(
			  @Param("codice") String codice
	);
    
    public List<MandanteRTIType> getMandantiRTI(
			  @Param("codiceProcedura") String codiceProcedura
			, @Param("username") String username
			, @Param("progressivoOfferta") String progressivoOfferta
	);
    
    public FascicoloProtocolloType getFascicoloProtocollo(
			  @Param("codice") String codice
	);
    
    public Long getGenere(
			  @Param("codice") String codice
	);
    
    public String getCodiceGaraFromCodiceLotto(
			  @Param("codiceLotto") String codiceLotto
	);
    
    public String getChiavePubblica(
			  @Param("codice") String codice
			, @Param("tipo") String tipo
	);
    
    public Integer isConsorziateEsecutriciPresenti(
			  @Param("tabella") String tabella
			, @Param("username") String username
			, @Param("codiceGara") String codiceGara
	);
    
    public List<DocumentoAllegatoType> getAttiDocumentiBandoGara(
			  @Param("codiceGara") String codiceGara
	);
    
    public Integer isAstaElettronica(
			  @Param("codiceGara") String codiceGara
			, @Param("codiceGara2") String codiceGara2
	);
    
    public Integer isAstaAttiva(
			  @Param("codiceGara") String codiceGara
			, @Param("codiceGara2") String codiceGara2
			, @Param("dataCorrente") Date dataCorrente
	);
    
    public Date getDataInizioAsta(
			  @Param("codiceGara") String codiceGara
			, @Param("codiceGara2") String codiceGara2
	);
    
    public Date getDataInvitoAsta(
			  @Param("codiceGara") String codiceGara
			, @Param("codiceGara2") String codiceGara2
	);
    
    public Integer isInvitataAsta(
			  @Param("codiceGara") String codiceGara
			, @Param("codiceGara2") String codiceGara2
			, @Param("dataCorrente") Date dataCorrente
			, @Param("username") String username
	);
    
    public Long checkAggiornamentoIscrizionePosticipata(
			  @Param("username") String username
			, @Param("codice") String codice
	);

	public List<EspletGaraOperatoreType> getEspletamentoGaraSoccorsiElencoOperatori(
			  @Param("fase") String fase
			, @Param("codice") String codice
			, @Param("codiceLotto") String codiceLotto
			, @Param("username") String username
	);
    
    public List<EspletGaraOperatoreType> getEspletamentoGaraDocAmmElencoOperatori(
			  @Param("codice") String codice
			, @Param("username") String username
	);
    
    public List<EspletGaraOperatoreType> getEspletamentoGaraValTecElencoOperatori(
			  @Param("codice") String codice
			, @Param("username") String username
	);
    
    public List<EspletGaraOperatoreLotto> getEspletamentoGaraValTecElencoOperatoriLotto(
			  @Param("codice") String codice
			, @Param("username") String username
	);
    
    public List<EspletGaraOperatoreType> getEspletamentoGaraOffEcoElencoOperatori(
			  @Param("codice") String codice
			, @Param("username") String username
	);
    
    public List<EspletGaraOperatoreLotto> getEspletamentoGaraOffEcoElencoOperatoriLotto(
			  @Param("codice") String codice
			, @Param("username") String username
	);
    
    public List<EspletGaraOperatoreType> getEspletamentoGaraGraduatoriaElencoOperatori(
			  @Param("codice") String codice
			, @Param("username") String username
		  	, @Param("orderby") String orderby
	);
    
    public List<EspletGaraLottoType> getEspletamentoGaraLotti(
			  @Param("codice") String codice
			, @Param("username") String username
	);
    
    public List<EspletGaraDocumentoType> getEspletamentoGaraDocumenti(
			  @Param("codice") String codice
			, @Param("codiceLotto") String codiceLotto
			, @Param("username") String username
			, @Param("busta") String busta
//			, @Param("partecipaLotto") String partecipaLotto
	);
    
    public List<EspletGaraComponenteRtiType> getEspletamentoGaraComponentiRti(
			  @Param("codiceGara") String codiceGara
			, @Param("username") String username
	);
    
    public Long getFaseGara(
			  @Param("codice") String codice
	);
    
    public List<ComunicazioneType> getNews(
			  @Param("dataInvioDa") Date dataInvioDa
			, @Param("dataInvioA") Date dataInvioA
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, RowBounds bounds
	);
    
    public Integer countNews(
			  @Param("dataInvioDa") Date dataInvioDa
			, @Param("dataInvioA") Date dataInvioA
			, @Param("stazioneAppaltante") String stazioneAppaltante
	);
    
    public List<SoggettoAderenteType> getSoggettiAderenti(
			  @Param("codice") String codice
	);
    
    public List<SoggettoAderenteLotto> getSoggettiAderentiLotti(
			  @Param("codice") String codice
	);
    
    public List<OperatoreIscrittoType> getOperatoriIscritti(
			  @Param("codice") String codice
	);
    
    public List<CategoriaOperatoreIscrittoType> getElencoCategorieOperatoriIscritti(
			  @Param("codice") String codice
	);
    
    public List<RettificaGaraType> getRettifichePresentazioneDomandaOfferta(
			  @Param("codice") String codice
			, @Param("tipter") Integer tipter
	);
    
    public List<CriterioValutazioneOffertaType> getCriteriValutazione(
			  @Param("codice") String codice
			, @Param("tippar") Integer tippar
	);
    
    public List<CriterioValutazioneOffertaLotto> getCriteriValutazioneLotti(
			  @Param("codice") String codice
			, @Param("tippar") Integer tippar
	);
    
    public List<DocumentoAllegatoLotto> checkDocumentiNulli(
			@Param("operatoreWinvcomIdCom") String operatoreWinvcomIdCom
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("codiceGara") String codiceGara
			, @Param("codiceGara2") String codiceGara2
			, @Param("username") String username
			, @Param("tipoBusta") String tipoBusta
	);
    
    public List<DocumentoAllegatoLotto> checkDimensioneDocumenti(
			@Param("operatoreWinvcomIdCom") String operatoreWinvcomIdCom
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("operatoreDataSize") String operatoreDataSize
			, @Param("codiceGara") String codiceGara
			, @Param("codiceGara2") String codiceGara2
			, @Param("username") String username
			, @Param("tipoBusta") String tipoBusta
	);
    
    public List<DeliberaType> getDelibere(
			  @Param("annoInizio") Integer annoInizio
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("tipoAppalto") String tipoAppalto
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("sommaUrgenza") Integer sommaUrgenza
			, RowBounds bounds
	);
    
    public Integer countDelibere(
			  @Param("annoInizio") Integer annoInizio
			, @Param("stazioneAppaltante") String stazioneAppaltante
			, @Param("tipoAppalto") String tipoAppalto
			, @Param("operatoreUpper") String operatoreUpper
			, @Param("oggetto") String oggetto
			, @Param("cig") String cig
			, @Param("dataPubblicazioneDa") Date dataPubblicazioneDa
			, @Param("dataPubblicazioneA") Date dataPubblicazioneA
			, @Param("sommaUrgenza") Integer sommaUrgenza
	);
    
    public List<SommaUrgenzaType> getElencoSommaUrgenza(
//			  String stazioneAppaltante
//			, String oggetto
//			, String tipoAppalto
//			, String cig
//			, Date dataPubblicazioneDa
//			, Date dataPubblicazioneA
//			, Integer indicePrimoRecord
//			, Integer maxNumRecord
			RowBounds bounds
	);
    
    public Integer countElencoSommaUrgenza(
//			  String stazioneAppaltante
//			, String oggetto
//			, String tipoAppalto
//			, String cig
//			, Date dataPubblicazioneDa
//			, Date dataPubblicazioneA
	);
    
    public List<WSDMConfigType> getWSDMConfig(
			  @Param("stazioneAppaltante") String stazioneAppaltante
	);
    
    public List<InvitoGaraType> getElencoInvitiGara(
			  @Param("codiceGara") String codice
			, @Param("username") String username
	);
    
    public Long getNumeroOrdineInvito(
			  @Param("codice") String codice
			, @Param("username") String username
	);
    
    public List<QuestionarioType> getQuestionari(
			  @Param("codice") String codice
			, @Param("tipologia") String tipologia
			, @Param("busta") String busta
	);
    
    public List<ParametroQuestionarioType> getParametriQuestionario();
}
