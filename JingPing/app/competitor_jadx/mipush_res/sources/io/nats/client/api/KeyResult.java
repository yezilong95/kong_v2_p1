package io.nats.client.api;

/* JADX INFO: loaded from: classes.dex */
public class KeyResult {
    private final Exception e;
    private final String key;

    public KeyResult() {
        this.key = null;
        this.e = null;
    }

    public Exception getException() {
        return this.e;
    }

    public String getKey() {
        return this.key;
    }

    public boolean isDone() {
        return this.key == null;
    }

    public boolean isException() {
        return this.e != null;
    }

    public boolean isKey() {
        return this.key != null;
    }

    public KeyResult(String str) {
        this.key = str;
        this.e = null;
    }

    public KeyResult(Exception exc) {
        this.key = null;
        this.e = exc;
    }
}
