package it.maggioli.eldasoft.sil.gare.ws;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperatoriIscrittiOutType", propOrder = {
    "operatori",
    "errore"
})
public class OperatoriIscrittiOutType implements Serializable {	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	protected List<OperatoreIscrittoType> operatori;
    protected String errore;
    
	/**
	 * @return the operatori
	 */
	public List<OperatoreIscrittoType> getOperatori() {
		return operatori;
	}
	/**
	 * @param operatori the operatori to set
	 */
	public void setOperatori(List<OperatoreIscrittoType> operatori) {
		this.operatori = operatori;
	}
	/**
	 * @return the errore
	 */
	public String getErrore() {
		return errore;
	}
	/**
	 * @param errore the errore to set
	 */
	public void setErrore(String errore) {
		this.errore = errore;
	}
    
}
