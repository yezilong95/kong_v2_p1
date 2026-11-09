package org.bouncycastle.jce.provider;

import defpackage.d0;
import defpackage.l21;
import defpackage.l4;
import defpackage.oi0;
import defpackage.qs;
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
public class JCEElGamalPublicKey implements ElGamalPublicKey, DHPublicKey {
    static final long serialVersionUID = 8712728417091216948L;
    public BigInteger E;
    public rs F;

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.E = (BigInteger) objectInputStream.readObject();
        this.F = new rs((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(getY());
        objectOutputStream.writeObject(this.F.a);
        objectOutputStream.writeObject(this.F.b);
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "ElGamal";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        d0 d0Var = oi0.b;
        rs rsVar = this.F;
        return l21.L(new l4(d0Var, new qs(rsVar.a, rsVar.b, 0)), new y(this.E));
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // org.bouncycastle.jce.interfaces.ElGamalPublicKey
    public rs getParameters() {
        return this.F;
    }

    @Override // javax.crypto.interfaces.DHKey
    public DHParameterSpec getParams() {
        rs rsVar = this.F;
        return new DHParameterSpec(rsVar.a, rsVar.b);
    }

    @Override // org.bouncycastle.jce.interfaces.ElGamalPublicKey, javax.crypto.interfaces.DHPublicKey
    public BigInteger getY() {
        return this.E;
    }
}
