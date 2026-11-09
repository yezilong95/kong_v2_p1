package io.nats.client;

import defpackage.as;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
class PublicKeyWrapper extends KeyWrapper implements PublicKey {
    final as publicKey;

    public PublicKeyWrapper(as asVar) {
        this.publicKey = asVar;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return this.publicKey.getEncoded();
    }
}
