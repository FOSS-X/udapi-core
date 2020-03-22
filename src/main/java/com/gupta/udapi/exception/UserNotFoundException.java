package com.gupta.udapi.exception;

/**
 * @author Amit
 */
public class UserNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {super();};

    public UserNotFoundException(Throwable throwable) {super(throwable);}

    public UserNotFoundException(String message) {super(message);}
}
