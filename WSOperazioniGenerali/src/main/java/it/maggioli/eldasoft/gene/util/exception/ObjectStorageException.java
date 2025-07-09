package it.maggioli.eldasoft.gene.util.exception;

public class ObjectStorageException extends RuntimeException {

    public ObjectStorageException(final String message) {
        super(message);
    }

    public ObjectStorageException(Throwable cause) {
        super(cause);
    }

}
