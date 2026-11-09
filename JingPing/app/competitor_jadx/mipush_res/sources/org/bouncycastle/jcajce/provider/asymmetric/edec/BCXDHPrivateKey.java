package org.bouncycastle.jcajce.provider.asymmetric.edec;

import defpackage.cm0;
import defpackage.e0;
import defpackage.es;
import defpackage.j91;
import defpackage.k91;
import defpackage.l91;
import defpackage.m91;
import defpackage.ol0;
import defpackage.pl0;
import defpackage.s8;
import defpackage.s90;
import defpackage.z91;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import org.bouncycastle.jcajce.interfaces.XDHPrivateKey;
import org.bouncycastle.jcajce.interfaces.XDHPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCXDHPrivateKey implements XDHPrivateKey {
    static final long serialVersionUID = 1;
    public transient s8 E;
    public transient s8 F;
    public transient int G;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        s8 s8VarA;
        objectInputStream.defaultReadObject();
        ol0 ol0VarG = ol0.g((byte[]) objectInputStream.readObject());
        int length = ol0VarG.G.E.length;
        byte[] bArr = (length == 32 || length == 56) ? ol0VarG.h().E : e0.o(ol0VarG.i()).E;
        if (es.b.k(ol0VarG.F.E)) {
            l91 l91Var = new l91(bArr);
            this.E = l91Var;
            s8VarA = l91Var.a();
        } else {
            j91 j91Var = new j91(bArr);
            this.E = j91Var;
            s8VarA = j91Var.a();
        }
        this.F = s8VarA;
        s8 s8Var = this.F;
        this.G = z91.E(s8Var instanceof m91 ? z91.l(((m91) s8Var).F) : z91.l(((k91) s8Var).F)) + (getAlgorithm().hashCode() * 31);
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
        ol0 ol0VarA2 = privateKey instanceof BCXDHPrivateKey ? ((BCXDHPrivateKey) privateKey).a() : ol0.g(privateKey.getEncoded());
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
        return cm0.c("org.bouncycastle.emulate.oracle") ? "XDH" : this.E instanceof l91 ? "X448" : "X25519";
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

    @Override // org.bouncycastle.jcajce.interfaces.XDHPrivateKey
    public XDHPublicKey getPublicKey() {
        s8 s8Var = this.F;
        BCXDHPublicKey bCXDHPublicKey = new BCXDHPublicKey();
        bCXDHPublicKey.E = s8Var;
        return bCXDHPublicKey;
    }

    public int hashCode() {
        return this.G;
    }

    public String toString() {
        return s90.I("Private Key", getAlgorithm(), this.F);
    }
}
