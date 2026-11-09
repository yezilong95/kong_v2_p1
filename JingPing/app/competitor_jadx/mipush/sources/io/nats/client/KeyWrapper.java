package io.nats.client;

import java.security.Key;

/* JADX INFO: loaded from: classes.dex */
abstract class KeyWrapper implements Key {
    @Override // java.security.Key
    public String getAlgorithm() {
        return "EdDSA";
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }
}
