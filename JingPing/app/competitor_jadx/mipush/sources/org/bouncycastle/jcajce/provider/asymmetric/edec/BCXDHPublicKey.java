package org.bouncycastle.jcajce.provider.asymmetric.edec;

import defpackage.cm0;
import defpackage.es;
import defpackage.k91;
import defpackage.m91;
import defpackage.n70;
import defpackage.s8;
import defpackage.s90;
import defpackage.sx0;
import defpackage.z91;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.PublicKey;
import java.util.Arrays;
import org.bouncycastle.jcajce.interfaces.XDHPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCXDHPublicKey implements XDHPublicKey {
    static final long serialVersionUID = 1;
    public transient s8 E;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        sx0 sx0VarG = sx0.g((byte[]) objectInputStream.readObject());
        byte[] bArrR = sx0VarG.F.r();
        if (es.b.k(sx0VarG.E.E)) {
            if (bArrR.length != 56) {
                throw new IllegalArgumentException("'buf' must have length 56");
            }
            this.E = new m91(bArrR);
        } else {
            if (bArrR.length != 32) {
                throw new IllegalArgumentException("'buf' must have length 32");
            }
            this.E = new k91(bArrR);
        }
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
        return cm0.c("org.bouncycastle.emulate.oracle") ? "XDH" : this.E instanceof m91 ? "X448" : "X25519";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        if (this.E instanceof m91) {
            byte[] bArr = n70.a;
            byte[] bArr2 = new byte[bArr.length + 56];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            m91 m91Var = (m91) this.E;
            System.arraycopy(m91Var.F, 0, bArr2, bArr.length, 56);
            return bArr2;
        }
        byte[] bArr3 = n70.b;
        byte[] bArr4 = new byte[bArr3.length + 32];
        System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
        k91 k91Var = (k91) this.E;
        System.arraycopy(k91Var.F, 0, bArr4, bArr3.length, 32);
        return bArr4;
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // org.bouncycastle.jcajce.interfaces.XDHPublicKey
    public BigInteger getU() {
        byte[] uEncoding = getUEncoding();
        if (uEncoding != null) {
            int length = uEncoding.length - 1;
            for (int i = 0; i < length; i++) {
                byte b = uEncoding[i];
                uEncoding[i] = uEncoding[length];
                uEncoding[length] = b;
                length--;
            }
        }
        return new BigInteger(1, uEncoding);
    }

    @Override // org.bouncycastle.jcajce.interfaces.XDHPublicKey
    public byte[] getUEncoding() {
        s8 s8Var = this.E;
        return s8Var instanceof m91 ? z91.l(((m91) s8Var).F) : z91.l(((k91) s8Var).F);
    }

    public int hashCode() {
        return z91.E(getEncoded());
    }

    public String toString() {
        return s90.I("Public Key", getAlgorithm(), this.E);
    }
}
