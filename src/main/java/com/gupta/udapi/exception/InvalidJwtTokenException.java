package com.gupta.udapi.exception;

/**
 * @author amitkumargupta
 */
public class InvalidJwtTokenException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public InvalidJwtTokenException() {super();};

    public InvalidJwtTokenException(Throwable throwable) {super(throwable);}

    public InvalidJwtTokenException(String message) {super(message);}
}
