package org.bouncycastle.jcajce.provider.asymmetric.gost;

import defpackage.d0;
import defpackage.jx0;
import defpackage.l21;
import defpackage.l4;
import defpackage.r10;
import defpackage.rj;
import defpackage.s10;
import defpackage.sx0;
import defpackage.t10;
import defpackage.t40;
import defpackage.tk;
import defpackage.u10;
import defpackage.v10;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import org.bouncycastle.jce.interfaces.GOST3410PublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCGOST3410PublicKey implements GOST3410PublicKey {
    static final long serialVersionUID = -6251023343619275990L;
    public transient r10 E;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        String str = (String) objectInputStream.readObject();
        if (str != null) {
            this.E = new r10(str, (String) objectInputStream.readObject(), (String) objectInputStream.readObject());
            return;
        }
        this.E = new r10(new v10((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject()));
        objectInputStream.readObject();
        objectInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        String str = this.E.b;
        if (str != null) {
            objectOutputStream.writeObject(str);
            objectOutputStream.writeObject(this.E.c);
            objectOutputStream.writeObject(this.E.d);
        } else {
            objectOutputStream.writeObject(null);
            objectOutputStream.writeObject(this.E.a.a);
            objectOutputStream.writeObject(this.E.a.b);
            objectOutputStream.writeObject(this.E.a.c);
            objectOutputStream.writeObject(this.E.c);
            objectOutputStream.writeObject(this.E.d);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCGOST3410PublicKey)) {
            return false;
        }
        ((BCGOST3410PublicKey) obj).getClass();
        throw null;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "GOST3410";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        sx0 sx0Var;
        byte[] byteArray = getY().toByteArray();
        byte[] bArr = new byte[byteArray[0] == 0 ? byteArray.length - 1 : byteArray.length];
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = byteArray[(byteArray.length - 1) - i];
        }
        try {
            if (this.E.d != null) {
                d0 d0Var = rj.b;
                d0 d0Var2 = new d0(this.E.b);
                d0 d0Var3 = new d0(this.E.c);
                d0 d0Var4 = new d0(this.E.d);
                u10 u10Var = new u10();
                u10Var.E = d0Var2;
                u10Var.F = d0Var3;
                u10Var.G = d0Var4;
                sx0Var = new sx0(new l4(d0Var, u10Var), new tk(bArr));
            } else {
                sx0Var = new sx0(new l4(rj.b, new u10(new d0(this.E.b), new d0(this.E.c))), new tk(bArr));
            }
            return l21.M(sx0Var);
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // org.bouncycastle.jce.interfaces.GOST3410PublicKey
    public t10 getParameters() {
        return this.E;
    }

    @Override // org.bouncycastle.jce.interfaces.GOST3410PublicKey
    public BigInteger getY() {
        return null;
    }

    public int hashCode() {
        throw null;
    }

    public String toString() {
        try {
            v10 v10Var = ((r10) getParameters()).a;
            getY();
            s10 s10Var = new s10(v10Var.a, v10Var.b, v10Var.c);
            String str = jx0.a;
            t40.S(null, s10Var);
            throw null;
        } catch (InvalidKeyException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
