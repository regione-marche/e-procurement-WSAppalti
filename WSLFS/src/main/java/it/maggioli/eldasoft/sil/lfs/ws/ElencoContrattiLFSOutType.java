package it.maggioli.eldasoft.sil.lfs.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ElencoContrattiLFSOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ElencoContrattiOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="num" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="elenco" type="{http://www.eldasoft.it/sil/WSGareAppalto/}ContrattoType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ElencoContrattiLFSOutType", propOrder = {
    "num",
    "elenco",
    "errore"
})
public class ElencoContrattiLFSOutType
    implements Serializable
{
	private static final long serialVersionUID = -4507273807924545405L;
	protected Integer num;
    protected List<ContrattoLFSType> elenco;
    protected String errore;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer value) {
        this.num = value;
    }

    public List<ContrattoLFSType> getElenco() {
        if (elenco == null) {
            elenco = new ArrayList<ContrattoLFSType>();
        }
        return this.elenco;
    }
    
    public void setElenco(List<ContrattoLFSType> elenco) {
        this.elenco = elenco;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String value) {
        this.errore = value;
    }

}
