
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ArticoloType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ArticoloType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="codiceCategoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descrizioneCategoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descrizioneTecnica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="colore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="obbligoImmagine" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="obbligoDescrizioneAggiuntiva" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="obbligoDimensioni" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="obbligoCertificazioni" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="certificazioniRichieste" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="facSimileCertificazioni" type="{http://www.eldasoft.it/sil/WSGareAppalto/}DocumentoAllegatoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="obbligoSchedaTecnica" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="obbligoGaranzia" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="unitaMisuraDetermPrezzo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numDecimaliDetermPrezzo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitaMisuraAcquisto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numDecimaliAcquisto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="prezzoUnitarioPer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quantitaUnitaAcquisto" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="quantitaMinimaUnitaAcquisto" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="quantitaMassimaUnitaAcquisto" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="tempoMaxConsegna" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitaMisuraTempoConsegna" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prodottoDaVerificare" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArticoloType", propOrder = {
    "id",
    "codiceCategoria",
    "descrizioneCategoria",
    "tipo",
    "codice",
    "descrizione",
    "descrizioneTecnica",
    "colore",
    "obbligoImmagine",
    "obbligoDescrizioneAggiuntiva",
    "obbligoDimensioni",
    "obbligoCertificazioni",
    "certificazioniRichieste",
    "facSimileCertificazioni",
    "documentiUlteriori",
    "obbligoSchedaTecnica",
    "obbligoGaranzia",
    "unitaMisuraDetermPrezzo",
    "numDecimaliDetermPrezzo",
    "unitaMisuraAcquisto",
    "numDecimaliAcquisto",
    "prezzoUnitarioPer",
    "quantitaUnitaAcquisto",
    "quantitaMinimaUnitaAcquisto",
    "quantitaMassimaUnitaAcquisto",
    "tempoMaxConsegna",
    "unitaMisuraTempoConsegna",
    "note",
    "prodottoDaVerificare"
})
public class ArticoloType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected long id;
    @XmlElement(required = true)
    protected String codiceCategoria;
    @XmlElement(required = true)
    protected String descrizioneCategoria;
    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String descrizione;
    protected String descrizioneTecnica;
    protected String colore;
    protected boolean obbligoImmagine;
    protected boolean obbligoDescrizioneAggiuntiva;
    protected boolean obbligoDimensioni;
    protected boolean obbligoCertificazioni;
    protected String certificazioniRichieste;
    protected List<DocumentoAllegatoType> facSimileCertificazioni;
    protected List<DocumentoAllegatoType> documentiUlteriori;
    protected boolean obbligoSchedaTecnica;
    protected boolean obbligoGaranzia;
    @XmlElement(required = true)
    protected String unitaMisuraDetermPrezzo;
    protected int numDecimaliDetermPrezzo;
    @XmlElement(required = true)
    protected String unitaMisuraAcquisto;
    protected int numDecimaliAcquisto;
    @XmlElement(required = true)
    protected String prezzoUnitarioPer;
    protected Double quantitaUnitaAcquisto;
    protected Double quantitaMinimaUnitaAcquisto;
    protected Double quantitaMassimaUnitaAcquisto;
    protected int tempoMaxConsegna;
    @XmlElement(required = true)
    protected String unitaMisuraTempoConsegna;
    protected String note;
    protected boolean prodottoDaVerificare;

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getCodiceCategoria() {
        return codiceCategoria;
    }

    public void setCodiceCategoria(String value) {
        this.codiceCategoria = value;
    }

    public String getDescrizioneCategoria() {
        return descrizioneCategoria;
    }

    public void setDescrizioneCategoria(String value) {
        this.descrizioneCategoria = value;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String value) {
        this.tipo = value;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String value) {
        this.codice = value;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    public String getDescrizioneTecnica() {
        return descrizioneTecnica;
    }

    public void setDescrizioneTecnica(String value) {
        this.descrizioneTecnica = value;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String value) {
        this.colore = value;
    }

    public boolean isObbligoImmagine() {
        return obbligoImmagine;
    }

    public void setObbligoImmagine(boolean value) {
        this.obbligoImmagine = value;
    }

    public boolean isObbligoDescrizioneAggiuntiva() {
        return obbligoDescrizioneAggiuntiva;
    }

    public void setObbligoDescrizioneAggiuntiva(boolean value) {
        this.obbligoDescrizioneAggiuntiva = value;
    }

    public boolean isObbligoDimensioni() {
        return obbligoDimensioni;
    }

    public void setObbligoDimensioni(boolean value) {
        this.obbligoDimensioni = value;
    }

    public boolean isObbligoCertificazioni() {
        return obbligoCertificazioni;
    }

    public void setObbligoCertificazioni(boolean value) {
        this.obbligoCertificazioni = value;
    }

    public String getCertificazioniRichieste() {
        return certificazioniRichieste;
    }

    public void setCertificazioniRichieste(String value) {
        this.certificazioniRichieste = value;
    }

    /**
     * Gets the value of the facSimileCertificazioni property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the facSimileCertificazioni property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFacSimileCertificazioni().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoAllegatoType }
     * 
     * 
     */
    public List<DocumentoAllegatoType> getFacSimileCertificazioni() {
        if (facSimileCertificazioni == null) {
            facSimileCertificazioni = new ArrayList<DocumentoAllegatoType>();
        }
        return this.facSimileCertificazioni;
    }
    
    public void setFacSimileCertificazioni(List<DocumentoAllegatoType> facSimileCertificazioni) {
        this.facSimileCertificazioni = facSimileCertificazioni;
    }

    public List<DocumentoAllegatoType> getDocumentiUlteriori() {
        if (documentiUlteriori == null) {
        	documentiUlteriori = new ArrayList<DocumentoAllegatoType>();
        }
        return this.documentiUlteriori;
    }
    
    public void setDocumentiUlteriori(List<DocumentoAllegatoType> documentiUlteriori) {
        this.documentiUlteriori = documentiUlteriori;
    }

    public boolean isObbligoSchedaTecnica() {
        return obbligoSchedaTecnica;
    }

    public void setObbligoSchedaTecnica(boolean value) {
        this.obbligoSchedaTecnica = value;
    }

    public boolean isObbligoGaranzia() {
        return obbligoGaranzia;
    }

    public void setObbligoGaranzia(boolean value) {
        this.obbligoGaranzia = value;
    }

    public String getUnitaMisuraDetermPrezzo() {
        return unitaMisuraDetermPrezzo;
    }

    public void setUnitaMisuraDetermPrezzo(String value) {
        this.unitaMisuraDetermPrezzo = value;
    }

    public int getNumDecimaliDetermPrezzo() {
        return numDecimaliDetermPrezzo;
    }

    public void setNumDecimaliDetermPrezzo(int value) {
        this.numDecimaliDetermPrezzo = value;
    }

    public String getUnitaMisuraAcquisto() {
        return unitaMisuraAcquisto;
    }

    public void setUnitaMisuraAcquisto(String value) {
        this.unitaMisuraAcquisto = value;
    }

    public int getNumDecimaliAcquisto() {
        return numDecimaliAcquisto;
    }

    public void setNumDecimaliAcquisto(int value) {
        this.numDecimaliAcquisto = value;
    }

    public String getPrezzoUnitarioPer() {
        return prezzoUnitarioPer;
    }

    public void setPrezzoUnitarioPer(String value) {
        this.prezzoUnitarioPer = value;
    }

    public Double getQuantitaUnitaAcquisto() {
        return quantitaUnitaAcquisto;
    }

    public void setQuantitaUnitaAcquisto(Double value) {
        this.quantitaUnitaAcquisto = value;
    }

    public Double getQuantitaMinimaUnitaAcquisto() {
        return quantitaMinimaUnitaAcquisto;
    }

    public void setQuantitaMinimaUnitaAcquisto(Double value) {
        this.quantitaMinimaUnitaAcquisto = value;
    }

    public Double getQuantitaMassimaUnitaAcquisto() {
        return quantitaMassimaUnitaAcquisto;
    }

    public void setQuantitaMassimaUnitaAcquisto(Double value) {
        this.quantitaMassimaUnitaAcquisto = value;
    }

    public int getTempoMaxConsegna() {
        return tempoMaxConsegna;
    }

    public void setTempoMaxConsegna(int value) {
        this.tempoMaxConsegna = value;
    }

    public String getUnitaMisuraTempoConsegna() {
        return unitaMisuraTempoConsegna;
    }

    public void setUnitaMisuraTempoConsegna(String value) {
        this.unitaMisuraTempoConsegna = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String value) {
        this.note = value;
    }

    public boolean isProdottoDaVerificare() {
        return prodottoDaVerificare;
    }

    public void setProdottoDaVerificare(boolean value) {
        this.prodottoDaVerificare = value;
    }

}
