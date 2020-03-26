package com.gupta.udapi.exception;

/**
 * @author Amit
 */
public class CannotConnectToDatabaseException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public CannotConnectToDatabaseException() {super();};

    public CannotConnectToDatabaseException(Throwable throwable) {super(throwable);}

    public CannotConnectToDatabaseException(String message) {super(message);}
}
