package org.bouncycastle.jce.provider;

import defpackage.d0;
import defpackage.l21;
import defpackage.l4;
import defpackage.oi0;
import defpackage.qs;
import defpackage.rs;
import defpackage.t;
import defpackage.y;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Enumeration;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.jce.interfaces.ElGamalPrivateKey;

/* JADX INFO: loaded from: classes.dex */
public class JCEElGamalPrivateKey implements ElGamalPrivateKey, DHPrivateKey {
    static final long serialVersionUID = 4819350091141529678L;
    public BigInteger E;
    public rs F;

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.E = (BigInteger) objectInputStream.readObject();
        this.F = new rs((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(getX());
        objectOutputStream.writeObject(this.F.a);
        objectOutputStream.writeObject(this.F.b);
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "ElGamal";
    }

    public t getBagAttribute(d0 d0Var) {
        throw null;
    }

    public Enumeration getBagAttributeKeys() {
        throw null;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        d0 d0Var = oi0.b;
        rs rsVar = this.F;
        return l21.K(new l4(d0Var, new qs(rsVar.a, rsVar.b, 0)), new y(getX()));
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.bouncycastle.jce.interfaces.ElGamalPrivateKey
    public rs getParameters() {
        return this.F;
    }

    @Override // javax.crypto.interfaces.DHKey
    public DHParameterSpec getParams() {
        rs rsVar = this.F;
        return new DHParameterSpec(rsVar.a, rsVar.b);
    }

    @Override // org.bouncycastle.jce.interfaces.ElGamalPrivateKey, javax.crypto.interfaces.DHPrivateKey
    public BigInteger getX() {
        return this.E;
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        throw null;
    }
}
