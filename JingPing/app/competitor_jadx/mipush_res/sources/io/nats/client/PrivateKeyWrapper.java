package io.nats.client;

import defpackage.z91;
import defpackage.zr;
import java.security.PrivateKey;

/* JADX INFO: loaded from: classes.dex */
class PrivateKeyWrapper extends KeyWrapper implements PrivateKey {
    final zr privateKey;

    public PrivateKeyWrapper(zr zrVar) {
        this.privateKey = zrVar;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return z91.l(this.privateKey.F);
    }
}
