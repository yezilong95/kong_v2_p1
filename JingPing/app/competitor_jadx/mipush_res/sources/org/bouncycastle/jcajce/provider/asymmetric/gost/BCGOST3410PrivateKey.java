package org.bouncycastle.jcajce.provider.asymmetric.gost;

import defpackage.d0;
import defpackage.i6;
import defpackage.l4;
import defpackage.ol0;
import defpackage.r10;
import defpackage.rj;
import defpackage.s10;
import defpackage.t;
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
import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.jce.interfaces.GOST3410PrivateKey;

/* JADX INFO: loaded from: classes.dex */
public class BCGOST3410PrivateKey implements GOST3410PrivateKey {
    static final long serialVersionUID = 8581661527592305464L;
    public transient r10 E;
    public transient i6 F;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        String str = (String) objectInputStream.readObject();
        if (str != null) {
            this.E = new r10(str, (String) objectInputStream.readObject(), (String) objectInputStream.readObject());
        } else {
            this.E = new r10(new v10((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject()));
            objectInputStream.readObject();
            objectInputStream.readObject();
        }
        this.F = new i6(24);
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
        if (obj instanceof GOST3410PrivateKey) {
            GOST3410PrivateKey gOST3410PrivateKey = (GOST3410PrivateKey) obj;
            if (getX().equals(gOST3410PrivateKey.getX()) && ((r10) getParameters()).a.equals(((r10) gOST3410PrivateKey.getParameters()).a)) {
                String str = ((r10) getParameters()).c;
                String str2 = ((r10) gOST3410PrivateKey.getParameters()).c;
                if (str == str2 ? true : str == null ? false : str.equals(str2)) {
                    String str3 = ((r10) getParameters()).d;
                    String str4 = ((r10) gOST3410PrivateKey.getParameters()).d;
                    if (str3 == str4 ? true : str3 == null ? false : str3.equals(str4)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "GOST3410";
    }

    public t getBagAttribute(d0 d0Var) {
        return this.F.y(d0Var);
    }

    public Enumeration getBagAttributeKeys() {
        return ((Vector) this.F.G).elements();
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        byte[] byteArray = getX().toByteArray();
        byte[] bArr = new byte[byteArray[0] == 0 ? byteArray.length - 1 : byteArray.length];
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = byteArray[(byteArray.length - 1) - i];
        }
        try {
            return new ol0(new l4(rj.b, new u10(new d0(this.E.b), new d0(this.E.c))), new tk(bArr), null, null).f();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.bouncycastle.jce.interfaces.GOST3410PrivateKey
    public t10 getParameters() {
        return this.E;
    }

    @Override // org.bouncycastle.jce.interfaces.GOST3410PrivateKey
    public BigInteger getX() {
        return null;
    }

    public int hashCode() {
        return getX().hashCode() ^ this.E.hashCode();
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        this.F.N(d0Var, tVar);
    }

    public String toString() {
        try {
            v10 v10Var = ((r10) getParameters()).a;
            getX();
            return t40.K0(new s10(v10Var.a, v10Var.b, v10Var.c));
        } catch (InvalidKeyException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
