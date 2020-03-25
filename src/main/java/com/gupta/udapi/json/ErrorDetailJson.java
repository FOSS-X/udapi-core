package com.gupta.udapi.json;

import java.util.Date;

/**
 * @author amitkumargupta
 */
public class ErrorDetailJson {

    private String errorSubCode;
    private String errorDescription;
    private String message;

    /**
     * @see Used for errors without any description provided
     */
    public ErrorDetailJson(String errorSubCode, String errorDescription) {
        this.errorSubCode = errorSubCode;
        this.errorDescription = errorDescription;
        this.message = "Contact support with the error code " + errorSubCode + ". Occoured at: " + (new Date());
    }

    public String getErrorSubCode() {
        return errorSubCode;
    }

    public void setErrorSubCode(String errorSubCode) {
        this.errorSubCode = errorSubCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorDetailJson{");
        sb.append("errorSubCode='").append(errorSubCode).append('\'');
        sb.append(", errorDescription='").append(errorDescription).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
