package it.maggioli.eldasoft.sil.pagopa.exception;

public class PagoPaException extends Exception {

	private static final long serialVersionUID = 1L;
	public PagoPaException() {
		super("Input Element is missing");
	}
	public PagoPaException(String message) {
        super(message);
    }
}
