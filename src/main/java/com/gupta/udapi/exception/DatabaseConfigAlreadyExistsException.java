package com.gupta.udapi.exception;

/**
 * @author Amit
 */
public class DatabaseConfigAlreadyExistsException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public DatabaseConfigAlreadyExistsException() {super();};

    public DatabaseConfigAlreadyExistsException(Throwable throwable) {super(throwable);}

    public DatabaseConfigAlreadyExistsException(String message) {super(message);}
}
