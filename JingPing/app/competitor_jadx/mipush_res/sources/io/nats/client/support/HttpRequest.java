package io.nats.client.support;

import io.nats.client.impl.Headers;

/* JADX INFO: loaded from: classes.dex */
public class HttpRequest {
    private String method = "GET";
    private String uri = "/";
    private String version = "1.1";
    private final Headers headers = new Headers();

    public Headers getHeaders() {
        return this.headers;
    }

    public String getMethod() {
        return this.method;
    }

    public String getURI() {
        return this.uri;
    }

    public String getVersion() {
        return this.version;
    }

    public HttpRequest method(String str) {
        if (str == null) {
            throw new IllegalArgumentException("HttpRequest method must be non-null");
        }
        this.method = str.trim().toUpperCase();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.method);
        sb.append(NatsConstants.SPACE);
        sb.append(this.uri);
        sb.append(" HTTP/");
        sb.append(this.version);
        sb.append(NatsConstants.CRLF);
        for (String str : this.headers.keySet()) {
            for (String str2 : this.headers.get(str)) {
                sb.append(str);
                sb.append(": ");
                sb.append(str2);
                sb.append(NatsConstants.CRLF);
            }
        }
        sb.append(NatsConstants.CRLF);
        return sb.toString();
    }

    public HttpRequest uri(String str) {
        if (str == null) {
            throw new IllegalArgumentException("HttpRequest uri must be non-null");
        }
        this.uri = str;
        return this;
    }

    public HttpRequest version(String str) {
        if (str == null) {
            throw new IllegalArgumentException("HttpRequest version must be non-null");
        }
        this.version = str;
        return this;
    }
}
