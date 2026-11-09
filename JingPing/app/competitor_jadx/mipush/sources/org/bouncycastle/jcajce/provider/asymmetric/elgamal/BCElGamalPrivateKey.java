package org.bouncycastle.jcajce.provider.asymmetric.elgamal;

import defpackage.d0;
import defpackage.i6;
import defpackage.l4;
import defpackage.oi0;
import defpackage.ol0;
import defpackage.qs;
import defpackage.rs;
import defpackage.t;
import defpackage.y;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Vector;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.jce.interfaces.ElGamalPrivateKey;

/* JADX INFO: loaded from: classes.dex */
public class BCElGamalPrivateKey implements ElGamalPrivateKey, DHPrivateKey {
    static final long serialVersionUID = 4819350091141529678L;
    public transient rs E;
    public transient i6 F;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.E = new rs((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject());
        this.F = new i6(24);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.E.a);
        objectOutputStream.writeObject(this.E.b);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHPrivateKey)) {
            return false;
        }
        DHPrivateKey dHPrivateKey = (DHPrivateKey) obj;
        return getX().equals(dHPrivateKey.getX()) && getParams().getG().equals(dHPrivateKey.getParams().getG()) && getParams().getP().equals(dHPrivateKey.getParams().getP()) && getParams().getL() == dHPrivateKey.getParams().getL();
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "ElGamal";
    }

    public t getBagAttribute(d0 d0Var) {
        return this.F.y(d0Var);
    }

    public Enumeration getBagAttributeKeys() {
        return ((Vector) this.F.G).elements();
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            d0 d0Var = oi0.b;
            rs rsVar = this.E;
            return new ol0(new l4(d0Var, new qs(rsVar.a, rsVar.b, 0)), new y(getX()), null, null).f();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.bouncycastle.jce.interfaces.ElGamalPrivateKey
    public rs getParameters() {
        return this.E;
    }

    @Override // javax.crypto.interfaces.DHKey
    public DHParameterSpec getParams() {
        rs rsVar = this.E;
        return new DHParameterSpec(rsVar.a, rsVar.b);
    }

    @Override // org.bouncycastle.jce.interfaces.ElGamalPrivateKey, javax.crypto.interfaces.DHPrivateKey
    public BigInteger getX() {
        return null;
    }

    public int hashCode() {
        return ((getX().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getL();
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        this.F.N(d0Var, tVar);
    }
}
