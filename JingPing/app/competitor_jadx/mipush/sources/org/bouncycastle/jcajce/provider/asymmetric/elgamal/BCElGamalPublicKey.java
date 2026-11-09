package org.bouncycastle.jcajce.provider.asymmetric.elgamal;

import defpackage.d0;
import defpackage.oi0;
import defpackage.rs;
import defpackage.y;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.jce.interfaces.ElGamalPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCElGamalPublicKey implements ElGamalPublicKey, DHPublicKey {
    static final long serialVersionUID = 8712728417091216948L;
    public transient rs E;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.E = new rs((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.E.a);
        objectOutputStream.writeObject(this.E.b);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHPublicKey)) {
            return false;
        }
        DHPublicKey dHPublicKey = (DHPublicKey) obj;
        return getY().equals(dHPublicKey.getY()) && getParams().getG().equals(dHPublicKey.getParams().getG()) && getParams().getP().equals(dHPublicKey.getParams().getP()) && getParams().getL() == dHPublicKey.getParams().getL();
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "ElGamal";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        BigInteger bigInteger = null;
        try {
            d0 d0Var = oi0.a;
            rs rsVar = this.E;
            BigInteger bigInteger2 = rsVar.a;
            BigInteger bigInteger3 = rsVar.b;
            new y(bigInteger2);
            new y(bigInteger3);
            bigInteger.toByteArray();
            throw null;
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // org.bouncycastle.jce.interfaces.ElGamalPublicKey
    public rs getParameters() {
        return this.E;
    }

    @Override // javax.crypto.interfaces.DHKey
    public DHParameterSpec getParams() {
        rs rsVar = this.E;
        return new DHParameterSpec(rsVar.a, rsVar.b);
    }

    @Override // org.bouncycastle.jce.interfaces.ElGamalPublicKey, javax.crypto.interfaces.DHPublicKey
    public BigInteger getY() {
        return null;
    }

    public int hashCode() {
        return ((getY().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getL();
    }
}
