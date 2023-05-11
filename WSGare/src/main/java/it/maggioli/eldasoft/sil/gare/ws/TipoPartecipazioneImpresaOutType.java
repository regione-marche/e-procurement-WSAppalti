
package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TipoPartecipazioneImpresaOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TipoPartecipazioneImpresaOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoPartecipazione" type="{http://www.eldasoft.it/sil/WSGareAppalto/}TipoPartecipazioneType" minOccurs="0"/>
 *         &lt;element name="errore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoPartecipazioneImpresaOutType", propOrder = {
    "tipoPartecipazione",
    "errore"
})
public class TipoPartecipazioneImpresaOutType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    protected TipoPartecipazioneType tipoPartecipazione;
    protected String errore;

    public TipoPartecipazioneType getTipoPartecipazione() {
        return tipoPartecipazione;
    }

    public void setTipoPartecipazione(TipoPartecipazioneType value) {
        this.tipoPartecipazione = value;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String value) {
        this.errore = value;
    }

}
