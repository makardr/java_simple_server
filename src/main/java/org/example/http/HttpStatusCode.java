package org.example.http;

public enum HttpStatusCode {
    //    Client errors
    CLIENT_ERROR_400_BAD_REQUEST(400, "Bad request"),
    CLIENT_ERROR_401_METHOD_NOT_ALLOWED(401, "Method not allowed"),
    CLIENT_ERROR_414_MAX_LENGTH(414, "URI Too Long"),

    //  Server errors
    SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500, "Internal server error"),
    SERVER_ERROR_501_NOT_IMPLEMENTED(501, "Not implemented"),
    SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported");

    public final int STATUS_CODE;
    public final String MESSAGE;

    HttpStatusCode(int STATUS_CODE, String MESSAGE) {
        this.STATUS_CODE = STATUS_CODE;
        this.MESSAGE = MESSAGE;
    }
}
