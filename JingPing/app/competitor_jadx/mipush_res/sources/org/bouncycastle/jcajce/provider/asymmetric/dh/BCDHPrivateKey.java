package org.bouncycastle.jcajce.provider.asymmetric.dh;

import defpackage.cm0;
import defpackage.d0;
import defpackage.dl;
import defpackage.fl;
import defpackage.fp;
import defpackage.gl;
import defpackage.i6;
import defpackage.il;
import defpackage.j0;
import defpackage.j31;
import defpackage.jx0;
import defpackage.l4;
import defpackage.n31;
import defpackage.ol0;
import defpackage.t;
import defpackage.u;
import defpackage.vj0;
import defpackage.w91;
import defpackage.wk;
import defpackage.y;
import defpackage.z91;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Vector;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class BCDHPrivateKey implements DHPrivateKey {
    static final long serialVersionUID = 311058815616901812L;
    public final BigInteger E;
    public transient DHParameterSpec F;
    public transient ol0 G;
    public transient i6 H = new i6(24);

    public BCDHPrivateKey(ol0 ol0Var) throws IOException {
        j0 j0VarQ = j0.q(ol0Var.F.F);
        y yVar = (y) ol0Var.i();
        d0 d0Var = ol0Var.F.E;
        this.G = ol0Var;
        this.E = yVar.q();
        int i = 160;
        if (!d0Var.k(vj0.c)) {
            if (!d0Var.k(w91.D)) {
                throw new IllegalArgumentException("unknown algorithm type: " + d0Var);
            }
            fp fpVar = j0VarQ != null ? new fp(j0.q(j0VarQ)) : null;
            BigInteger bigIntegerP = fpVar.E.p();
            y yVar2 = fpVar.G;
            BigInteger bigIntegerP2 = yVar2.p();
            y yVar3 = fpVar.F;
            BigInteger bigIntegerP3 = yVar3.p();
            y yVar4 = fpVar.H;
            this.F = new dl(0, 0, bigIntegerP, bigIntegerP2, bigIntegerP3, yVar4 != null ? yVar4.p() : null);
            BigInteger bigIntegerP4 = fpVar.E.p();
            yVar3.p();
            yVar2.p();
            if (yVar4 != null) {
                yVar4.p();
            }
            if (160 > bigIntegerP4.bitLength() && !cm0.c("org.bouncycastle.dh.allow_unsafe_p_value")) {
                throw new IllegalArgumentException("unsafe p value so small specific l required");
            }
            return;
        }
        fl flVarG = fl.g(j0VarQ);
        BigInteger bigIntegerH = flVarG.h();
        y yVar5 = flVarG.F;
        y yVar6 = flVarG.E;
        if (bigIntegerH == null) {
            this.F = new DHParameterSpec(yVar6.p(), yVar5.p());
            BigInteger bigIntegerP5 = yVar6.p();
            yVar5.p();
            if (160 > bigIntegerP5.bitLength() && !cm0.c("org.bouncycastle.dh.allow_unsafe_p_value")) {
                throw new IllegalArgumentException("unsafe p value so small specific l required");
            }
            return;
        }
        this.F = new DHParameterSpec(yVar6.p(), yVar5.p(), flVarG.h().intValue());
        BigInteger bigIntegerP6 = yVar6.p();
        yVar5.p();
        int iIntValue = flVarG.h().intValue();
        if (iIntValue != 0 && iIntValue < 160) {
            i = iIntValue;
        }
        if (iIntValue != 0) {
            if (iIntValue > bigIntegerP6.bitLength()) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            }
            if (iIntValue < i) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        if (i > bigIntegerP6.bitLength() && !cm0.c("org.bouncycastle.dh.allow_unsafe_p_value")) {
            throw new IllegalArgumentException("unsafe p value so small specific l required");
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.F = new DHParameterSpec((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), objectInputStream.readInt());
        this.G = null;
        this.H = new i6(24);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.F.getP());
        objectOutputStream.writeObject(this.F.getG());
        objectOutputStream.writeInt(this.F.getL());
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
        return "DH";
    }

    public t getBagAttribute(d0 d0Var) {
        return this.H.y(d0Var);
    }

    public Enumeration getBagAttributeKeys() {
        return ((Vector) this.H.G).elements();
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        ol0 ol0Var;
        try {
            ol0 ol0Var2 = this.G;
            if (ol0Var2 != null) {
                return ol0Var2.f();
            }
            DHParameterSpec dHParameterSpec = this.F;
            if (!(dHParameterSpec instanceof dl) || ((dl) dHParameterSpec).a == null) {
                ol0Var = new ol0(new l4(vj0.c, new fl(dHParameterSpec.getP(), this.F.getG(), this.F.getL()).b()), new y(getX()), null, null);
            } else {
                dl dlVar = (dl) dHParameterSpec;
                BigInteger p = dlVar.getP();
                BigInteger g = dlVar.getG();
                int l = dlVar.getL();
                BigInteger bigInteger = dlVar.b;
                il ilVar = dlVar.d;
                BigInteger bigInteger2 = dlVar.a;
                int i = dlVar.c;
                if (l != 0) {
                    if (l > p.bitLength()) {
                        throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
                    }
                    if (l < i) {
                        throw new IllegalArgumentException("when l value specified, it may not be less than m value");
                    }
                }
                if (i > p.bitLength() && !cm0.c("org.bouncycastle.dh.allow_unsafe_p_value")) {
                    throw new IllegalArgumentException("unsafe p value so small specific l required");
                }
                n31 n31Var = ilVar != null ? new n31(z91.l(ilVar.a), ilVar.b) : null;
                d0 d0Var = w91.D;
                if (g == null) {
                    throw new IllegalArgumentException("'g' cannot be null");
                }
                if (bigInteger2 == null) {
                    throw new IllegalArgumentException("'q' cannot be null");
                }
                y yVar = new y(p);
                y yVar2 = new y(g);
                y yVar3 = new y(bigInteger2);
                y yVar4 = bigInteger != null ? new y(bigInteger) : null;
                u uVar = new u(5);
                uVar.c(yVar);
                uVar.c(yVar2);
                uVar.c(yVar3);
                if (yVar4 != null) {
                    uVar.c(yVar4);
                }
                if (n31Var != null) {
                    uVar.c(n31Var);
                }
                wk wkVar = new wk(uVar, 0);
                wkVar.H = -1;
                ol0Var = new ol0(new l4(d0Var, wkVar), new y(getX()), null, null);
            }
            return ol0Var.f();
        } catch (Exception unused) {
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

    public int hashCode() {
        return ((getX().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getL();
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        this.H.N(d0Var, tVar);
    }

    public String toString() {
        BigInteger bigInteger = this.E;
        gl glVar = new gl(this.F.getP(), this.F.getG(), 0);
        StringBuffer stringBuffer = new StringBuffer("DH Private Key [");
        String str = jx0.a;
        BigInteger bigIntegerModPow = glVar.E.modPow(bigInteger, glVar.F);
        stringBuffer.append(j31.n(bigIntegerModPow, glVar));
        stringBuffer.append("]");
        stringBuffer.append(str);
        stringBuffer.append("              Y: ");
        stringBuffer.append(bigIntegerModPow.toString(16));
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
