
package it.maggioli.eldasoft.sil.stipule.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElencoStipuleContrattiOutType", propOrder = {
    "num",
    "elenco",
    "errore"
})
public class ElencoStipuleContrattiOutType
    implements Serializable
{
	private static final long serialVersionUID = 5913670029589211645L;
	
	protected Integer num;
    protected List<StipulaContrattoType> elenco;
    protected String errore;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer value) {
        this.num = value;
    }

    public List<StipulaContrattoType> getElenco() {
        if (elenco == null) {
            elenco = new ArrayList<StipulaContrattoType>();
        }
        return this.elenco;
    }
    
    public void setElenco(List<StipulaContrattoType> elenco) {
        this.elenco = elenco;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String value) {
        this.errore = value;
    }

}
