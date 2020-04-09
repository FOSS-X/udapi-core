package com.gupta.udapi.exception;

/**
 * @author Amit
 */
public class CreatingEntitySetNotSupportedException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public CreatingEntitySetNotSupportedException() {super();};

    public CreatingEntitySetNotSupportedException(Throwable throwable) {super(throwable);}

    public CreatingEntitySetNotSupportedException(String message) {super(message);}
}
