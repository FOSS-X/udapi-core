package com.gupta.udapi.constants;

/**
 * @author amitkumargupta
 */
public class ConstantStrings {

    public static final String COMMENT_STORAGE_ERROR_MSG = "Could not store comment in database.";
    public static final String ALL_DB_CONFIG_TYPE = "all";

    public static final String[] JWT_FILTER_EXCLUDE_PATHS = {
            "/v1/auth/signup",
            "/v1/auth/login",
            "/test"
            };

    public static final String[] JWT_FILTER_INCLUDE_PATHS = {
            "/**"
    };

    public static final String[] LOG_FILTER_EXCLUDE_PATHS = {};

    public static final String[] LOG_FILTER_INCLUDE_PATHS = {
            "/**"
    };

    public static final String[] DB_FILTER_EXCLUDE_PATHS = {
            "/v1/metadata/db/all"
    };

    public static final String[] DB_FILTER_INCLUDE_PATHS = {
            "/v1/entitySet/**",
            "/v1/entity/**"
    };

    public static final String NO_JWT_IN_HEADER_ERROR_MSG = "No JWT token found in header.";

    public static final String DB_PERSISTENCE_ERROR_MSG = "Error saving to database.";
    public static final String NO_DBTYPE_IN_HEADER_ERROR_MSG = "No Database-Type header found in HTTP headers.";

    /**
     * Need to use String.format() along with this messgae.
     */
    private static final String JWT_PARSE_FAILED_ERROR_MSG = "Could not parse the jwt token: %s. The JWT token is expired / invalid.";

    public static String getJwtParseFailedErrorMsg(String token) {
        return String.format(JWT_PARSE_FAILED_ERROR_MSG, token);
    }

    private static final String LOG_REQUEST_RECEIVED_MSG = "Received [%s] request from: %s";

    public static String getLogRequestReceivedMsg(String method, String remoteAddress) {
        return String.format(LOG_REQUEST_RECEIVED_MSG, method, remoteAddress);
    }
}
