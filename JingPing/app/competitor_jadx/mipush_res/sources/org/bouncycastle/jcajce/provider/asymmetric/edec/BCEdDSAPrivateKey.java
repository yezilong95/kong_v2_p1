package org.bouncycastle.jcajce.provider.asymmetric.edec;

import defpackage.as;
import defpackage.cm0;
import defpackage.cs;
import defpackage.ds;
import defpackage.e0;
import defpackage.es;
import defpackage.ol0;
import defpackage.pl0;
import defpackage.s8;
import defpackage.s90;
import defpackage.z91;
import defpackage.zr;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import org.bouncycastle.jcajce.interfaces.EdDSAPrivateKey;
import org.bouncycastle.jcajce.interfaces.EdDSAPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCEdDSAPrivateKey implements EdDSAPrivateKey {
    static final long serialVersionUID = 1;
    public transient s8 E;
    public transient s8 F;
    public transient int G;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        s8 s8VarA;
        byte[] encoded;
        objectInputStream.defaultReadObject();
        ol0 ol0VarG = ol0.g((byte[]) objectInputStream.readObject());
        byte[] bArr = e0.o(ol0VarG.i()).E;
        if (es.d.k(ol0VarG.F.E)) {
            cs csVar = new cs(bArr);
            this.E = csVar;
            s8VarA = csVar.a();
        } else {
            zr zrVar = new zr(bArr);
            this.E = zrVar;
            s8VarA = zrVar.a();
        }
        this.F = s8VarA;
        s8 s8Var = this.F;
        if (s8Var instanceof ds) {
            encoded = new byte[57];
            ((ds) s8Var).a(0, encoded);
        } else {
            encoded = ((as) s8Var).getEncoded();
        }
        this.G = z91.E(encoded) + (getAlgorithm().hashCode() * 31);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public final ol0 a() {
        try {
            ol0 ol0VarA = pl0.a(this.E, null);
            return new ol0(ol0VarA.F, ol0VarA.i(), null, null);
        } catch (IOException unused) {
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PrivateKey)) {
            return false;
        }
        PrivateKey privateKey = (PrivateKey) obj;
        ol0 ol0VarA = a();
        ol0 ol0VarA2 = privateKey instanceof BCEdDSAPrivateKey ? ((BCEdDSAPrivateKey) privateKey).a() : ol0.g(privateKey.getEncoded());
        if (ol0VarA == null || ol0VarA2 == null) {
            return false;
        }
        try {
            return z91.q(ol0VarA.h().getEncoded(), ol0VarA2.h().getEncoded()) & z91.q(ol0VarA.F.getEncoded(), ol0VarA2.F.getEncoded());
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return cm0.c("org.bouncycastle.emulate.oracle") ? "EdDSA" : this.E instanceof cs ? "Ed448" : "Ed25519";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            ol0 ol0VarA = a();
            if (ol0VarA == null) {
                return null;
            }
            return ol0VarA.getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.bouncycastle.jcajce.interfaces.EdDSAPrivateKey
    public EdDSAPublicKey getPublicKey() {
        s8 s8Var = this.F;
        BCEdDSAPublicKey bCEdDSAPublicKey = new BCEdDSAPublicKey();
        bCEdDSAPublicKey.E = s8Var;
        return bCEdDSAPublicKey;
    }

    public int hashCode() {
        return this.G;
    }

    public String toString() {
        return s90.I("Private Key", getAlgorithm(), this.F);
    }
}
