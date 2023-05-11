package it.maggioli.eldasoft.sil.pagopa.exception;

public class PagoPaValidationException extends PagoPaException {

	private static final long serialVersionUID = 1L;
	public PagoPaValidationException() {
		super("Input Element is missing");
	}
	public PagoPaValidationException(String message) {
        super(message);
    }
}
