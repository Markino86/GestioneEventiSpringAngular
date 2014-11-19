package it.cspnet.gestioneeventi.exception;

public class NonConfermatoException extends Exception{

    public NonConfermatoException() {
    }

    public NonConfermatoException(String message) {
        super(message);
    }

    public NonConfermatoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonConfermatoException(Throwable cause) {
        super(cause);
    }

    public NonConfermatoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
