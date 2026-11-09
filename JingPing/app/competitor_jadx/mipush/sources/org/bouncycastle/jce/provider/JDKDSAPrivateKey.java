package org.bouncycastle.jce.provider;

import defpackage.d0;
import defpackage.i6;
import defpackage.l4;
import defpackage.ol0;
import defpackage.t;
import defpackage.w91;
import defpackage.y;
import defpackage.yl;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPrivateKey;
import java.security.spec.DSAParameterSpec;
import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class JDKDSAPrivateKey implements DSAPrivateKey {
    private static final long serialVersionUID = -4677259546958385734L;
    public BigInteger E;
    public DSAParameterSpec F;
    public i6 G;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        this.E = (BigInteger) objectInputStream.readObject();
        this.F = new DSAParameterSpec((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject());
        i6 i6Var = new i6(24);
        this.G = i6Var;
        i6Var.K(objectInputStream);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.E);
        objectOutputStream.writeObject(this.F.getP());
        objectOutputStream.writeObject(this.F.getQ());
        objectOutputStream.writeObject(this.F.getG());
        this.G.S(objectOutputStream);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAPrivateKey)) {
            return false;
        }
        DSAPrivateKey dSAPrivateKey = (DSAPrivateKey) obj;
        return getX().equals(dSAPrivateKey.getX()) && getParams().getG().equals(dSAPrivateKey.getParams().getG()) && getParams().getP().equals(dSAPrivateKey.getParams().getP()) && getParams().getQ().equals(dSAPrivateKey.getParams().getQ());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "DSA";
    }

    public t getBagAttribute(d0 d0Var) {
        return this.G.y(d0Var);
    }

    public Enumeration getBagAttributeKeys() {
        return ((Vector) this.G.G).elements();
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new ol0(new l4(w91.C, new yl(this.F.getP(), this.F.getQ(), this.F.getG())), new y(getX()), null, null).f();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // java.security.interfaces.DSAKey
    public DSAParams getParams() {
        return this.F;
    }

    @Override // java.security.interfaces.DSAPrivateKey
    public BigInteger getX() {
        return this.E;
    }

    public int hashCode() {
        return ((getX().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getQ().hashCode();
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        this.G.N(d0Var, tVar);
    }
}
