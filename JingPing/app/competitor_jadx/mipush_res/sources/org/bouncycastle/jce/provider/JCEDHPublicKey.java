package org.bouncycastle.jce.provider;

import defpackage.fl;
import defpackage.l21;
import defpackage.l4;
import defpackage.vj0;
import defpackage.y;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class JCEDHPublicKey implements DHPublicKey {
    static final long serialVersionUID = -216691575254424324L;
    public BigInteger E;
    public DHParameterSpec F;

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.E = (BigInteger) objectInputStream.readObject();
        this.F = new DHParameterSpec((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), objectInputStream.readInt());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(getY());
        objectOutputStream.writeObject(this.F.getP());
        objectOutputStream.writeObject(this.F.getG());
        objectOutputStream.writeInt(this.F.getL());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "DH";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return l21.L(new l4(vj0.c, new fl(this.F.getP(), this.F.getG(), this.F.getL())), new y(this.E));
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // javax.crypto.interfaces.DHKey
    public DHParameterSpec getParams() {
        return this.F;
    }

    @Override // javax.crypto.interfaces.DHPublicKey
    public BigInteger getY() {
        return this.E;
    }
}
