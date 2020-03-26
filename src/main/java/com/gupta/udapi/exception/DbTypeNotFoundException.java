package com.gupta.udapi.exception;

/**
 * @author Amit
 */
public class DbTypeNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public DbTypeNotFoundException() {super();};

    public DbTypeNotFoundException(Throwable throwable) {super(throwable);}

    public DbTypeNotFoundException(String message) {super(message);}
}
