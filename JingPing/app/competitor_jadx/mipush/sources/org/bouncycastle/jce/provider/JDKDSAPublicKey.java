package org.bouncycastle.jce.provider;

import defpackage.jx0;
import defpackage.l4;
import defpackage.lk;
import defpackage.u;
import defpackage.uk;
import defpackage.w91;
import defpackage.wk;
import defpackage.y;
import defpackage.yl;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class JDKDSAPublicKey implements DSAPublicKey {
    private static final long serialVersionUID = 1752452449903495175L;
    public BigInteger E;
    public DSAParameterSpec F;

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.E = (BigInteger) objectInputStream.readObject();
        this.F = new DSAParameterSpec((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.E);
        objectOutputStream.writeObject(this.F.getP());
        objectOutputStream.writeObject(this.F.getQ());
        objectOutputStream.writeObject(this.F.getG());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAPublicKey)) {
            return false;
        }
        DSAPublicKey dSAPublicKey = (DSAPublicKey) obj;
        return getY().equals(dSAPublicKey.getY()) && getParams().getG().equals(dSAPublicKey.getParams().getG()) && getParams().getP().equals(dSAPublicKey.getParams().getP()) && getParams().getQ().equals(dSAPublicKey.getParams().getQ());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "DSA";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            l4 l4Var = new l4(w91.C, new yl(this.F.getP(), this.F.getQ(), this.F.getG()));
            lk lkVar = new lk(new y(this.E).b().f(), 0);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            u uVar = new u(2);
            uVar.c(l4Var);
            uVar.c(lkVar);
            wk wkVar = new wk(uVar, 0);
            wkVar.H = -1;
            new uk(2, byteArrayOutputStream).K(wkVar);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // java.security.interfaces.DSAKey
    public DSAParams getParams() {
        return this.F;
    }

    @Override // java.security.interfaces.DSAPublicKey
    public BigInteger getY() {
        return this.E;
    }

    public int hashCode() {
        return ((getY().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getQ().hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DSA Public Key");
        String str = jx0.a;
        stringBuffer.append(str);
        stringBuffer.append("            y: ");
        stringBuffer.append(getY().toString(16));
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
