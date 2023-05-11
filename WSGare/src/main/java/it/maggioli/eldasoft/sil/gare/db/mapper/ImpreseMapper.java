package it.maggioli.eldasoft.sil.gare.db.mapper;

import it.eldasoft.sil.portgare.datatypes.*;
import it.maggioli.eldasoft.sil.gare.db.vo.PKReferenteImpresa;
import it.maggioli.eldasoft.sil.gare.ws.VendorRatingType;
import it.maggioli.eldasoft.wsOperazioniGenerali.daticomuni.DatoCodificatoType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ImpreseMapper {
    
    public String getPKImpresaByUsername(
			  @Param("username") String username
	);
    
    public ImpresaAggiornabileType getImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public IndirizzoAggiornabileType getSedeLegaleImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public RecapitiAggiornabileType getRecapitiImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public List<IndirizzoEstesoAggiornabileType> getIndirizziImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public CameraCommercioAggiornabileType getDatiCCIAAImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public INPSAggiornabileType getDatiINPSImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public INAILAggiornabileType getDatiINAILImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public CassaEdileAggiornabileType getDatiCassaEdileImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public SOAAggiornabileType getDatiSOAImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public ISO9001AggiornabileType getDatiISO9001Impresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public IscrizioneWhitelistAntimafiaAggiornabileType getDatiIscrizioneWhitelistImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public IscrizioneElenchiRicostruzioneAggiornabileType getDatiIscrizioneElenchiRicostruzioneImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public RatingLegalitaAggiornabileType getDatiRatingLegalitaImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public ContoCorrenteDedicatoAggiornabileType getDatiContoCorrenteDedicatoImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public AbilitazionePreventivaAggiornabileType getDatiAbilitazionePreventivaImpresa(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public List<PKReferenteImpresa> getPKLegaliRappresentantiImpresa(
              @Param("codiceImpresa") String codiceImpresa
			, @Param("dataPartenzaCessati") Date dataPartenzaCessati
	);
    
    public List<PKReferenteImpresa> getPKDirettoriTecniciImpresa(
              @Param("codiceImpresa") String codiceImpresa
            , @Param("incarico") Integer incarico
			, @Param("dataPartenzaCessati") Date dataPartenzaCessati
	);
    
    public List<PKReferenteImpresa> getPKAltreCaricheImpresa(
              @Param("codiceImpresa") String codiceImpresa
            , @Param("incarico") Integer incarico
            , @Param("dataPartenzaCessati") Date dataPartenzaCessati
	);
    
    public List<PKReferenteImpresa> getPKCollaboratoriImpresa(
              @Param("codiceImpresa") String codiceImpresa
            , @Param("incarico") Integer incarico
            , @Param("dataPartenzaCessati") Date dataPartenzaCessati
	);
    
    public ReferenteImpresaAggiornabileType getDatiReferenteImpresa(
              @Param("codiceReferente") String codiceReferente
	);
    
    public IndirizzoAggiornabileType getIndirizzoReferenteImpresa(
              @Param("codiceReferente") String codiceReferente
	);
    
    public ReferenteImpresaAggiornabileType getIncaricoLegaleRappresentanteImpresa(
			  @Param("codiceImpresa") String codiceImpresa
            , @Param("codiceReferente") String codiceReferente
            , @Param("incarico") Integer incarico
            , @Param("qualifica") Integer qualifica
            , @Param("id") Integer id
	);
    
    public ReferenteImpresaAggiornabileType getIncaricoDirettoreTecnicoImpresa(
              @Param("codiceImpresa") String codiceImpresa
            , @Param("codiceReferente") String codiceReferente
            , @Param("incarico") Integer incarico
            , @Param("qualifica") Integer qualifica
            , @Param("id") Integer id
	);

	public List<PKReferenteImpresa> getPKAttualiReferentiImpresa(
			  @Param("codiceImpresa") String codiceImpresa
			, @Param("incarico") Integer incarico
			, @Param("dataPartenzaCessati") Date dataPartenzaCessati
	);
    
    public ReferenteImpresaAggiornabileType getIncaricoAltraCaricaImpresa(
              @Param("codiceImpresa") String codiceImpresa
            , @Param("codiceReferente") String codiceReferente
            , @Param("incarico") Integer incarico
            , @Param("qualifica") Integer qualifica
            , @Param("id") Integer id
	);

	public ReferenteImpresaAggiornabileType getIncaricoReferenteImpresa(
			  @Param("codiceImpresa") String codiceImpresa
			, @Param("codiceReferente") String codiceReferente
			, @Param("incarico") Integer incarico
			, @Param("qualifica") Integer qualifica
			, @Param("id") Integer id
	);
    
    public ReferenteImpresaAggiornabileType getIncaricoCollaboratoreImpresa(
			  @Param("codiceImpresa") String codiceImpresa
			, @Param("codiceReferente") String codiceReferente
			, @Param("incarico") Integer incarico
			, @Param("qualifica") Integer qualifica
			, @Param("id") Integer id
	);
    
    public Integer isImpresaRegistrata(
			  @Param("tabella") String tabella
			, @Param("codiceFiscale") String codiceFiscale
			, @Param("partitaIva") String partitaIva
			, @Param("isGruppoIva") boolean isGruppoIva
	);
    
    public String getImpresaRegistrata(
			  @Param("tabella") String tabella
			, @Param("codiceFiscale") String codiceFiscale
			, @Param("partitaIva") String partitaIva
			, @Param("email") String email
			, @Param("pec") String pec
	);
    
    public List<DatoCodificatoType> getElencoTipiImpresaPerIscrizione();
    
    public List<DatoCodificatoType> getElencoTipiImpresaDittaIndividuale();
    
    public List<DatoCodificatoType> getElencoTipiImpresaLiberoProfessionista();
    
    public List<DatoCodificatoType> getElencoTipiImpresaSociale();
    
    public List<DatoCodificatoType> getElencoTipiImpresaConsorzio();
    
    public AltriDatiAnagraficiAggiornabileType getAltriDatiLiberoProfessionista(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public AlboProfessionaleAggiornabileType getAlboProfessionaleReferenteImpresa(
              @Param("codiceReferente") String codiceReferente
	);
    
    public AlboProfessionaleAggiornabileType getAlboProfessionaleLiberoProfessionista(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public CassaPrevidenzaAggiornabileType getCassaPrevidenzaReferenteImpresa(
              @Param("codiceReferente") String codiceReferente
	);
    
    public CassaPrevidenzaAggiornabileType getCassaPrevidenzaLiberoProfessionista(
              @Param("codiceImpresa") String codiceImpresa
	);
    
    public DatoAnnuoImpresaAggiornabileType getDatiAnnui(
              @Param("codiceImpresa") String codiceImpresa
			, @Param("anno") Integer anno
	);
    
    public String getIdImpresa(
              @Param("username") String username
	);


	public VendorRatingType getVendorRatingValidita(
			@Param("tokenRichiedente") String tokenRichiedente
			, @Param("data") Date data
	);
	public VendorRatingType getVendorRatingSospensione(
			@Param("tokenRichiedente") String tokenRichiedente
			, @Param("data") Date data
	);

}
