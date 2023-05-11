package it.maggioli.eldasoft.sil.lfs.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetDettaglioContrattoLFSOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetDettaglioContrattoOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contratto" type="{http://www.eldasoft.it/sil/WSGareAppalto/}ContrattoType" minOccurs="0"/>
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
@XmlType(name = "GetDettaglioContrattoLFSOutType", propOrder = {
    "contratto",
    "errore"
})
public class GetDettaglioContrattoLFSOutType
    implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected ContrattoLFSType contratto;
    protected String errore;

    public ContrattoLFSType getContratto() {
        return contratto;
    }

    public void setContratto(ContrattoLFSType value) {
        this.contratto = value;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String value) {
        this.errore = value;
    }

}
