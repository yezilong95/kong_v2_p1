package org.bouncycastle.jcajce.provider.asymmetric.edec;

import defpackage.as;
import defpackage.cm0;
import defpackage.ds;
import defpackage.es;
import defpackage.l21;
import defpackage.n70;
import defpackage.s8;
import defpackage.s90;
import defpackage.sx0;
import defpackage.z91;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PublicKey;
import java.util.Arrays;
import org.bouncycastle.jcajce.interfaces.EdDSAPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCEdDSAPublicKey implements EdDSAPublicKey {
    static final long serialVersionUID = 1;
    public transient s8 E;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        sx0 sx0VarG = sx0.g((byte[]) objectInputStream.readObject());
        byte[] bArrR = sx0VarG.F.r();
        this.E = es.d.k(sx0VarG.E.E) ? new ds(bArrR) : new as(bArrR);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PublicKey) {
            return Arrays.equals(((PublicKey) obj).getEncoded(), getEncoded());
        }
        return false;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return cm0.c("org.bouncycastle.emulate.oracle") ? "EdDSA" : this.E instanceof ds ? "Ed448" : "Ed25519";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        if (this.E instanceof ds) {
            byte[] bArr = n70.c;
            byte[] bArr2 = new byte[bArr.length + 57];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            ((ds) this.E).a(bArr.length, bArr2);
            return bArr2;
        }
        byte[] bArr3 = n70.d;
        byte[] bArr4 = new byte[bArr3.length + 32];
        System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
        as asVar = (as) this.E;
        l21.D(asVar.F, bArr4, bArr3.length);
        return bArr4;
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // org.bouncycastle.jcajce.interfaces.EdDSAPublicKey
    public byte[] getPointEncoding() {
        s8 s8Var = this.E;
        if (!(s8Var instanceof ds)) {
            return ((as) s8Var).getEncoded();
        }
        byte[] bArr = new byte[57];
        ((ds) s8Var).a(0, bArr);
        return bArr;
    }

    public int hashCode() {
        return z91.E(getEncoded());
    }

    public String toString() {
        return s90.I("Public Key", getAlgorithm(), this.E);
    }
}
