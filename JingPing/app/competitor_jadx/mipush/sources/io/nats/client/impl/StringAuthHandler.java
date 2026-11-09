package io.nats.client.impl;

import io.nats.client.AuthHandler;
import io.nats.client.NKey;

/* JADX INFO: loaded from: classes.dex */
class StringAuthHandler implements AuthHandler {
    private final char[] jwt;
    private final char[] nkey;

    public StringAuthHandler(char[] cArr, char[] cArr2) {
        this.jwt = cArr;
        this.nkey = cArr2;
    }

    @Override // io.nats.client.AuthHandler
    public char[] getID() {
        try {
            NKey nKeyFromSeed = NKey.fromSeed(this.nkey);
            char[] publicKey = nKeyFromSeed.getPublicKey();
            nKeyFromSeed.clear();
            return publicKey;
        } catch (Exception e) {
            throw new IllegalStateException("problem getting public key", e);
        }
    }

    @Override // io.nats.client.AuthHandler
    public char[] getJWT() {
        return this.jwt;
    }

    @Override // io.nats.client.AuthHandler
    public byte[] sign(byte[] bArr) {
        try {
            NKey nKeyFromSeed = NKey.fromSeed(this.nkey);
            byte[] bArrSign = nKeyFromSeed.sign(bArr);
            nKeyFromSeed.clear();
            return bArrSign;
        } catch (Exception e) {
            throw new IllegalStateException("problem signing nonce", e);
        }
    }
}
