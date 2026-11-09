package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import defpackage.jx0;
import defpackage.l21;
import defpackage.l4;
import defpackage.mm0;
import defpackage.pm0;
import defpackage.qs;
import defpackage.rk;
import defpackage.vj0;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCRSAPublicKey implements RSAPublicKey {
    public static final l4 F = new l4(vj0.a, rk.E);
    static final long serialVersionUID = 2675817738516720772L;
    public transient l4 E;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        try {
            this.E = l4.g(objectInputStream.readObject());
        } catch (Exception unused) {
            this.E = F;
        }
        new mm0(false, null, null);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (this.E.equals(F)) {
            return;
        }
        objectOutputStream.writeObject(this.E.getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RSAPublicKey)) {
            return false;
        }
        RSAPublicKey rSAPublicKey = (RSAPublicKey) obj;
        return getModulus().equals(rSAPublicKey.getModulus()) && getPublicExponent().equals(rSAPublicKey.getPublicExponent());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.E.E.k(vj0.b) ? "RSASSA-PSS" : "RSA";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return l21.L(this.E, new qs(getModulus(), getPublicExponent(), 1));
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // java.security.interfaces.RSAKey
    public BigInteger getModulus() {
        return null;
    }

    @Override // java.security.interfaces.RSAPublicKey
    public BigInteger getPublicExponent() {
        return null;
    }

    public int hashCode() {
        return getModulus().hashCode() ^ getPublicExponent().hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("RSA Public Key [");
        String str = jx0.a;
        stringBuffer.append(pm0.b(getModulus()));
        stringBuffer.append("],[");
        stringBuffer.append(pm0.a(getPublicExponent()));
        stringBuffer.append("]");
        stringBuffer.append(str);
        stringBuffer.append("        modulus: ");
        stringBuffer.append(getModulus().toString(16));
        stringBuffer.append(str);
        stringBuffer.append("public exponent: ");
        stringBuffer.append(getPublicExponent().toString(16));
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
