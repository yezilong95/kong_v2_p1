package io.nats.service;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class PingResponse extends ServiceResponse {
    public static final String TYPE = "io.nats.micro.v1.ping_response";

    public PingResponse(String str, String str2, String str3, Map<String, String> map) {
        super(TYPE, str, str2, str3, map);
    }

    public PingResponse(byte[] bArr) {
        super(TYPE, ServiceResponse.parseMessage(bArr));
    }
}
