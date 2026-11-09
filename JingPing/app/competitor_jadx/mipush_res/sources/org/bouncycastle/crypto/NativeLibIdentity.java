package org.bouncycastle.crypto;

import io.nats.client.Nats;

/* JADX INFO: loaded from: classes.dex */
class NativeLibIdentity {
    public static String a() {
        try {
            return getLibIdent();
        } catch (UnsatisfiedLinkError unused) {
            return Nats.CLIENT_LANGUAGE;
        }
    }

    private static native String getBuiltTimeStamp();

    private static native String getLibIdent();
}
