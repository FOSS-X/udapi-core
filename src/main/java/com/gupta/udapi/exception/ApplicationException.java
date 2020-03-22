package com.gupta.udapi.exception;

/**
 * @author amitkumargupta
 */
public class ApplicationException extends RuntimeException {

    private String uiMessage;

    private static final long serialVersionUID = 1L;

    public ApplicationException() {}

    /**
     * @param message
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ApplicationException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(String message, String uiMessage) {
        super(message);
        this.uiMessage = uiMessage;
    }

    public String getUiMessage() {
        return uiMessage;
    }

    public void setUiMessage(String uiMessage) {
        this.uiMessage = uiMessage;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
