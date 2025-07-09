package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImpreseAusiliarieOutType", propOrder = {
    "numElementiTotali",
    "impreseAusiliarie",
    "errore"
})
public class ImpreseAusiliarieOutType implements Serializable {    
    /**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	protected Integer numElementiTotali;
    protected List<ImpresaAusiliariaType> impreseAusiliarie;
    protected String errore;

    public Integer getNumElementiTotali() {
        return numElementiTotali;
    }

    public void setNumElementiTotali(Integer value) {
        this.numElementiTotali = value;
    }

    public List<ImpresaAusiliariaType> getImpreseAusiliarie() {
        if (impreseAusiliarie == null) {
        	impreseAusiliarie = new ArrayList<ImpresaAusiliariaType>();
        }
        return this.impreseAusiliarie;
    }
    
    public void setImpreseAusiliarie(List<ImpresaAusiliariaType> impreseAusiliarie) {
        this.impreseAusiliarie = impreseAusiliarie;
    }

    public String getErrore() {
        return errore;
    }

    public void setErrore(String value) {
        this.errore = value;
    }

}
