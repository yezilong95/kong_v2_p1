package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import defpackage.d0;
import defpackage.i6;
import defpackage.jx0;
import defpackage.l21;
import defpackage.l4;
import defpackage.mm0;
import defpackage.om0;
import defpackage.pm0;
import defpackage.t;
import defpackage.vj0;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class BCRSAPrivateKey implements RSAPrivateKey {
    public static final BigInteger H = BigInteger.valueOf(0);
    static final long serialVersionUID = 5110188922551353628L;
    public byte[] E;
    public transient l4 F;
    public transient i6 G;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        byte[] encoded;
        objectInputStream.defaultReadObject();
        if (this.E == null) {
            try {
                encoded = BCRSAPublicKey.F.getEncoded();
            } catch (IOException unused) {
                encoded = null;
            }
            this.E = encoded;
        }
        this.F = l4.g(this.E);
        this.G = new i6(24);
        new mm0(true, null, null);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RSAPrivateKey)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) obj;
        return getModulus().equals(rSAPrivateKey.getModulus()) && getPrivateExponent().equals(rSAPrivateKey.getPrivateExponent());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.F.E.k(vj0.b) ? "RSASSA-PSS" : "RSA";
    }

    public t getBagAttribute(d0 d0Var) {
        return this.G.y(d0Var);
    }

    public Enumeration getBagAttributeKeys() {
        return ((Vector) this.G.G).elements();
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        l4 l4Var = this.F;
        BigInteger modulus = getModulus();
        BigInteger bigInteger = H;
        return l21.K(l4Var, new om0(modulus, bigInteger, getPrivateExponent(), bigInteger, bigInteger, bigInteger, bigInteger, bigInteger));
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // java.security.interfaces.RSAKey
    public BigInteger getModulus() {
        return null;
    }

    @Override // java.security.interfaces.RSAPrivateKey
    public BigInteger getPrivateExponent() {
        return null;
    }

    public int hashCode() {
        return getModulus().hashCode() ^ getPrivateExponent().hashCode();
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        this.G.N(d0Var, tVar);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("RSA Private Key [");
        String str = jx0.a;
        stringBuffer.append(pm0.b(getModulus()));
        stringBuffer.append("],[]");
        stringBuffer.append(str);
        stringBuffer.append("            modulus: ");
        stringBuffer.append(getModulus().toString(16));
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
