package io.nats.client;

/* JADX INFO: loaded from: classes.dex */
public interface AuthHandler {
    char[] getID();

    char[] getJWT();

    byte[] sign(byte[] bArr);
}
