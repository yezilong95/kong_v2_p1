package io.nats.client.api;

/* JADX INFO: loaded from: classes.dex */
public enum KeyValueOperation {
    PUT("PUT"),
    DELETE("DEL"),
    PURGE("PURGE");

    private final String headerValue;

    KeyValueOperation(String str) {
        this.headerValue = str;
    }

    public static KeyValueOperation getOrDefault(String str, KeyValueOperation keyValueOperation) {
        KeyValueOperation keyValueOperationInstance = instance(str);
        return keyValueOperationInstance == null ? keyValueOperation : keyValueOperationInstance;
    }

    public static KeyValueOperation instance(String str) {
        KeyValueOperation keyValueOperation = PUT;
        if (keyValueOperation.headerValue.equals(str)) {
            return keyValueOperation;
        }
        KeyValueOperation keyValueOperation2 = DELETE;
        if (keyValueOperation2.headerValue.equals(str)) {
            return keyValueOperation2;
        }
        KeyValueOperation keyValueOperation3 = PURGE;
        if (keyValueOperation3.headerValue.equals(str)) {
            return keyValueOperation3;
        }
        return null;
    }

    public static KeyValueOperation instanceByMarkerReason(String str) {
        if ("Remove".equals(str)) {
            return DELETE;
        }
        if ("MaxAge".equals(str) || "Purge".equals(str)) {
            return PURGE;
        }
        return null;
    }

    public String getHeaderValue() {
        return this.headerValue;
    }
}
