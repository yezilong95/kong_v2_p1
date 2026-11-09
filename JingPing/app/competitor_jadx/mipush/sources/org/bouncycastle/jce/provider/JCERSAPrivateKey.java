package org.bouncycastle.jce.provider;

import defpackage.d0;
import defpackage.i6;
import defpackage.l21;
import defpackage.l4;
import defpackage.om0;
import defpackage.rk;
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
public class JCERSAPrivateKey implements RSAPrivateKey {
    public static final BigInteger H = BigInteger.valueOf(0);
    static final long serialVersionUID = 5110188922551353628L;
    public BigInteger E;
    public BigInteger F;
    public transient i6 G;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        this.E = (BigInteger) objectInputStream.readObject();
        i6 i6Var = new i6(24);
        this.G = i6Var;
        i6Var.K(objectInputStream);
        this.F = (BigInteger) objectInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.E);
        this.G.S(objectOutputStream);
        objectOutputStream.writeObject(this.F);
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
        return "RSA";
    }

    public t getBagAttribute(d0 d0Var) {
        return this.G.y(d0Var);
    }

    public Enumeration getBagAttributeKeys() {
        return ((Vector) this.G.G).elements();
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        l4 l4Var = new l4(vj0.a, rk.E);
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
        return this.E;
    }

    @Override // java.security.interfaces.RSAPrivateKey
    public BigInteger getPrivateExponent() {
        return this.F;
    }

    public int hashCode() {
        return getModulus().hashCode() ^ getPrivateExponent().hashCode();
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        this.G.N(d0Var, tVar);
    }
}
