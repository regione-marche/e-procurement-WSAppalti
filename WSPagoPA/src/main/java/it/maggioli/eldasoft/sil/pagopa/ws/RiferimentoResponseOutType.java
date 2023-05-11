package it.maggioli.eldasoft.sil.pagopa.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RiferimentoResponseOutType", propOrder = {
		"lista"
})
public class RiferimentoResponseOutType implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private List<RiferimentoOutType> lista;
	/**
	 * @return the lista
	 */
	public List<RiferimentoOutType> getLista() {
		return lista;
	}
	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<RiferimentoOutType> lista) {
		this.lista = lista;
	}
	
	public RiferimentoResponseOutType lista(List<RiferimentoOutType> lista) {
		this.lista = lista;
		return this;
	}

}
