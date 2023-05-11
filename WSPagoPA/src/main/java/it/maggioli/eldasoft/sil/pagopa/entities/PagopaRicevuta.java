package it.maggioli.eldasoft.sil.pagopa.entities;

/**
 * Mappatura della tabella pagopa_ricevuta
 * @author gabriele.nencini
 *
 */
public class PagopaRicevuta {
	private Long pagopapagamentiid;
	private byte[] ricevuta;
	/**
	 * @return the pagopapagamentiid
	 */
	public Long getPagopapagamentiid() {
		return pagopapagamentiid;
	}
	/**
	 * @param pagopapagamentiid the pagopapagamentiid to set
	 */
	public void setPagopapagamentiid(Long pagopapagamentiid) {
		this.pagopapagamentiid = pagopapagamentiid;
	}
	/**
	 * @return the ricevuta
	 */
	public byte[] getRicevuta() {
		return ricevuta;
	}
	/**
	 * @param ricevuta the ricevuta to set
	 */
	public void setRicevuta(byte[] ricevuta) {
		this.ricevuta = ricevuta;
	}
	
}
