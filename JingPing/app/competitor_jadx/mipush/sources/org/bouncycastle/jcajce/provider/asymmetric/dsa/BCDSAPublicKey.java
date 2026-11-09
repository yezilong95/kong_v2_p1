package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import defpackage.am;
import defpackage.bm;
import defpackage.d0;
import defpackage.g0;
import defpackage.jx0;
import defpackage.l21;
import defpackage.l4;
import defpackage.rk;
import defpackage.sx0;
import defpackage.t;
import defpackage.u;
import defpackage.w91;
import defpackage.wk;
import defpackage.y;
import defpackage.yl;
import defpackage.zl;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class BCDSAPublicKey implements DSAPublicKey {
    public static final BigInteger G = BigInteger.valueOf(0);
    private static final long serialVersionUID = 1752452449903495175L;
    public final BigInteger E;
    public transient DSAParameterSpec F;

    public BCDSAPublicKey(sx0 sx0Var) {
        try {
            BigInteger bigIntegerQ = ((y) g0.l(sx0Var.F.r())).q();
            this.E = bigIntegerQ;
            l4 l4Var = sx0Var.E;
            t tVar = l4Var.F;
            zl zlVar = null;
            if (tVar == null || rk.E.k(tVar.b())) {
                this.F = null;
            } else {
                yl ylVarG = yl.g(l4Var.F);
                this.F = new DSAParameterSpec(ylVarG.E.p(), ylVarG.F.p(), ylVarG.G.p());
            }
            DSAParameterSpec dSAParameterSpec = this.F;
            int i = bm.a;
            if (dSAParameterSpec != null) {
                BigInteger p = dSAParameterSpec.getP();
                BigInteger q = dSAParameterSpec.getQ();
                BigInteger g = dSAParameterSpec.getG();
                zlVar = new zl();
                zlVar.E = g;
                zlVar.G = p;
                zlVar.F = q;
            }
            new am(bigIntegerQ, zlVar);
        } catch (IOException unused) {
            throw new IllegalArgumentException("invalid info structure in DSA public key");
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        BigInteger bigInteger = (BigInteger) objectInputStream.readObject();
        zl zlVar = null;
        if (bigInteger.equals(G)) {
            this.F = null;
        } else {
            this.F = new DSAParameterSpec(bigInteger, (BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject());
        }
        BigInteger bigInteger2 = this.E;
        DSAParameterSpec dSAParameterSpec = this.F;
        int i = bm.a;
        if (dSAParameterSpec != null) {
            BigInteger p = dSAParameterSpec.getP();
            BigInteger q = dSAParameterSpec.getQ();
            BigInteger g = dSAParameterSpec.getG();
            zlVar = new zl();
            zlVar.E = g;
            zlVar.G = p;
            zlVar.F = q;
        }
        new am(bigInteger2, zlVar);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        BigInteger g;
        objectOutputStream.defaultWriteObject();
        DSAParameterSpec dSAParameterSpec = this.F;
        if (dSAParameterSpec == null) {
            g = G;
        } else {
            objectOutputStream.writeObject(dSAParameterSpec.getP());
            objectOutputStream.writeObject(this.F.getQ());
            g = this.F.getG();
        }
        objectOutputStream.writeObject(g);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAPublicKey)) {
            return false;
        }
        DSAPublicKey dSAPublicKey = (DSAPublicKey) obj;
        return this.F != null ? getY().equals(dSAPublicKey.getY()) && dSAPublicKey.getParams() != null && getParams().getG().equals(dSAPublicKey.getParams().getG()) && getParams().getP().equals(dSAPublicKey.getParams().getP()) && getParams().getQ().equals(dSAPublicKey.getParams().getQ()) : getY().equals(dSAPublicKey.getY()) && dSAPublicKey.getParams() == null;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "DSA";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        DSAParameterSpec dSAParameterSpec = this.F;
        BigInteger bigInteger = this.E;
        if (dSAParameterSpec == null) {
            return l21.L(new l4(w91.C), new y(bigInteger));
        }
        d0 d0Var = w91.C;
        BigInteger p = dSAParameterSpec.getP();
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
        return l21.L(new l4(d0Var, wkVar), new y(bigInteger));
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
        return this.F != null ? ((getY().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getQ().hashCode() : getY().hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DSA Public Key [");
        String str = jx0.a;
        stringBuffer.append(bm.a(this.E, getParams()));
        stringBuffer.append("]");
        stringBuffer.append(str);
        stringBuffer.append("            Y: ");
        stringBuffer.append(getY().toString(16));
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
