package io.nats.client;

import defpackage.zn;
import io.nats.client.support.Validator;

/* JADX INFO: loaded from: classes.dex */
public class MessageTtl {
    private final String ttlString;

    private MessageTtl(String str) {
        this.ttlString = str;
    }

    public static MessageTtl custom(String str) {
        if (Validator.nullOrEmpty(str)) {
            throw new IllegalArgumentException("Custom value required.");
        }
        return new MessageTtl(str);
    }

    public static MessageTtl never() {
        return new MessageTtl("never");
    }

    public static MessageTtl seconds(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Must be at least 1 second.");
        }
        return new MessageTtl(i + "s");
    }

    public String getTtlString() {
        return this.ttlString;
    }

    public String toString() {
        return zn.o(new StringBuilder("MessageTtl{'"), this.ttlString, "'}");
    }
}
