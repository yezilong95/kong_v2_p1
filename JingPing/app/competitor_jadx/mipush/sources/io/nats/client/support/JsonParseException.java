package io.nats.client.support;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class JsonParseException extends IOException {
    public JsonParseException(String str) {
        super(str);
    }

    public JsonParseException(String str, Throwable th) {
        super(str, th);
    }

    public JsonParseException(Throwable th) {
        super(th);
    }
}
