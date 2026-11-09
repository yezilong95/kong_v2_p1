package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import defpackage.bm;
import defpackage.d0;
import defpackage.i6;
import defpackage.jx0;
import defpackage.l21;
import defpackage.l4;
import defpackage.ol0;
import defpackage.t;
import defpackage.u;
import defpackage.w91;
import defpackage.wk;
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
public class BCDSAPrivateKey implements DSAPrivateKey {
    private static final long serialVersionUID = -4677259546958385734L;
    public final BigInteger E;
    public transient DSAParameterSpec F;
    public transient i6 G = new i6(24);

    public BCDSAPrivateKey(ol0 ol0Var) throws IOException {
        yl ylVarG = yl.g(ol0Var.F.F);
        this.E = ((y) ol0Var.i()).q();
        this.F = new DSAParameterSpec(ylVarG.E.p(), ylVarG.F.p(), ylVarG.G.p());
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.F = new DSAParameterSpec((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject());
        this.G = new i6(24);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.F.getP());
        objectOutputStream.writeObject(this.F.getQ());
        objectOutputStream.writeObject(this.F.getG());
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
        d0 d0Var = w91.C;
        BigInteger p = this.F.getP();
        BigInteger q = this.F.getQ();
        BigInteger g = this.F.getG();
        y yVar = new y(p);
        y yVar2 = new y(q);
        y yVar3 = new y(g);
        u uVar = new u(3);
        uVar.c(yVar);
        uVar.c(yVar2);
        uVar.c(yVar3);
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return l21.K(new l4(d0Var, wkVar), new y(getX()));
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

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DSA Private Key [");
        String str = jx0.a;
        BigInteger bigIntegerModPow = getParams().getG().modPow(this.E, getParams().getP());
        stringBuffer.append(bm.a(bigIntegerModPow, getParams()));
        stringBuffer.append("]");
        stringBuffer.append(str);
        stringBuffer.append("            Y: ");
        stringBuffer.append(bigIntegerModPow.toString(16));
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
