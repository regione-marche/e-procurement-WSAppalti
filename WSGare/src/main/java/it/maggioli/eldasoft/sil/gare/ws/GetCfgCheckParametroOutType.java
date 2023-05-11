package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetCfgCheckParametroOutType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetCfgCheckParametroOutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="checkAbilitato" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlType(name = "GetCfgCheckParametroOutType", propOrder = {
    "checkAbilitato",
    "errore"
})
public class GetCfgCheckParametroOutType
    implements Serializable
{
    private final static long serialVersionUID = 2L;
    protected Boolean checkAbilitato;
    protected String errore;

    public Boolean isCheckAbilitato() {
        return checkAbilitato;
    }

    public void setCheckAbilitato(Boolean value) {
        this.checkAbilitato = value;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String value) {
        this.errore = value;
    }


    
}
