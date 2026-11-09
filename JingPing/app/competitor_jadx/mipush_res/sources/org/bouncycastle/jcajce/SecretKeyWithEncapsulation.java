package org.bouncycastle.jcajce;

import defpackage.z91;
import javax.crypto.SecretKey;

/* JADX INFO: loaded from: classes.dex */
public final class SecretKeyWithEncapsulation implements SecretKey {
    public final SecretKey E;
    public final byte[] F;

    public SecretKeyWithEncapsulation(SecretKey secretKey, byte[] bArr) {
        this.E = secretKey;
        this.F = z91.l(bArr);
    }

    public boolean equals(Object obj) {
        return this.E.equals(obj);
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.E.getAlgorithm();
    }

    public byte[] getEncapsulation() {
        return z91.l(this.F);
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return this.E.getEncoded();
    }

    @Override // java.security.Key
    public String getFormat() {
        return this.E.getFormat();
    }

    public int hashCode() {
        return this.E.hashCode();
    }
}
