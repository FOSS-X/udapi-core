package com.gupta.udapi.exception;

/**
 * @author Amit
 */
public class InvalidEntityJsonException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public InvalidEntityJsonException() {super();};

    public InvalidEntityJsonException(Throwable throwable) {super(throwable);}

    public InvalidEntityJsonException(String message) {super(message);}
}
