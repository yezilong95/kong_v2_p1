package org.bouncycastle.jce.provider;

import defpackage.d0;
import defpackage.fl;
import defpackage.l4;
import defpackage.ol0;
import defpackage.t;
import defpackage.vj0;
import defpackage.y;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Enumeration;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class JCEDHPrivateKey implements DHPrivateKey {
    static final long serialVersionUID = 311058815616901812L;
    public BigInteger E;
    public DHParameterSpec F;

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.E = (BigInteger) objectInputStream.readObject();
        this.F = new DHParameterSpec((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), objectInputStream.readInt());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(getX());
        objectOutputStream.writeObject(this.F.getP());
        objectOutputStream.writeObject(this.F.getG());
        objectOutputStream.writeInt(this.F.getL());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "DH";
    }

    public t getBagAttribute(d0 d0Var) {
        throw null;
    }

    public Enumeration getBagAttributeKeys() {
        throw null;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new ol0(new l4(vj0.c, new fl(this.F.getP(), this.F.getG(), this.F.getL())), new y(getX()), null, null).f();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // javax.crypto.interfaces.DHKey
    public DHParameterSpec getParams() {
        return this.F;
    }

    @Override // javax.crypto.interfaces.DHPrivateKey
    public BigInteger getX() {
        return this.E;
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        throw null;
    }
}
