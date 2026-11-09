package org.bouncycastle.jce.spec;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import org.bouncycastle.jce.interfaces.IESKey;

/* JADX INFO: loaded from: classes.dex */
public class IEKeySpec implements KeySpec, IESKey {
    public final PublicKey E;
    public final PrivateKey F;

    public IEKeySpec(PrivateKey privateKey, PublicKey publicKey) {
        this.F = privateKey;
        this.E = publicKey;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "IES";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return null;
    }

    @Override // java.security.Key
    public String getFormat() {
        return null;
    }

    @Override // org.bouncycastle.jce.interfaces.IESKey
    public PrivateKey getPrivate() {
        return this.F;
    }

    @Override // org.bouncycastle.jce.interfaces.IESKey
    public PublicKey getPublic() {
        return this.E;
    }
}
