package it.maggioli.eldasoft.sil.pagopa.exception;

public class PagoPaPagamentoNotExistsException extends PagoPaException {

	private static final long serialVersionUID = 1L;
	public PagoPaPagamentoNotExistsException() {
		super("Input Element is missing");
	}
	public PagoPaPagamentoNotExistsException(String message) {
        super(message);
    }
}
