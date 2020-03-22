package com.gupta.udapi.exception;

/**
 * @author Amit
 */
public class UserAlreadyExistsException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException() {super();};

    public UserAlreadyExistsException(Throwable throwable) {super(throwable);}

    public UserAlreadyExistsException(String message) {super(message);}
}
