package org.example.http;

public class HttpRequest extends HttpMessage {


    private HttpMethod method;
    private String requestTarget;
    private String httpVersion;

    //    Package level instead of public, all the other classes of this classes can instantiate object of this type directly
    HttpRequest() {
    }

    public HttpMethod getMethod() {
        return method;
    }

    void setMethod(String methodName) throws HttpParsingException {
        for (HttpMethod method: HttpMethod.values()){
            if (methodName.equals(method.name())){
                this.method=method;
                return;
            }
        }
        throw new HttpParsingException(
                HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED
        );
    }
}
