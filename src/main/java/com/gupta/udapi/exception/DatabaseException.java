package com.gupta.udapi.exception;

/**
 * @author Amit
 */
public class DatabaseException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public DatabaseException() {super();};

    public DatabaseException(Throwable throwable) {super(throwable);}

    public DatabaseException(String message) {super(message);}
}
