package it.maggioli.eldasoft.sil.gare.db.vo;

import it.maggioli.eldasoft.sil.gare.ws.AttributoAggiuntivoOffertaType;

/**
 * Wrapper dei dati degli attributi aggiuntivi per estrarre, rispetto alla
 * superclasse, anche il codice tabellato per estrarre i valori ammessi nel
 * dominio.
 * 
 * @author Stefano.Sabbadin
 */
public class AttributoAggiuntivoOfferta extends AttributoAggiuntivoOffertaType {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 354530593306949597L;

	private String tabellato;

	/**
	 * @return the tabellato
	 */
	public String getTabellato() {
		return tabellato;
	}

	/**
	 * @param tabellato
	 *            the tabellato to set
	 */
	public void setTabellato(String tabellato) {
		this.tabellato = tabellato;
	}

}
