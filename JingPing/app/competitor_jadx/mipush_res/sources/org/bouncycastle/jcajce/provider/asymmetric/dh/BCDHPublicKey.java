package org.bouncycastle.jcajce.provider.asymmetric.dh;

import defpackage.cm0;
import defpackage.d0;
import defpackage.dl;
import defpackage.fl;
import defpackage.gl;
import defpackage.hl;
import defpackage.il;
import defpackage.j31;
import defpackage.jx0;
import defpackage.l21;
import defpackage.l4;
import defpackage.n31;
import defpackage.sx0;
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
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class BCDHPublicKey implements DHPublicKey {
    static final long serialVersionUID = -216691575254424324L;
    public final BigInteger E;
    public final transient hl F;
    public transient DHParameterSpec G;
    public transient sx0 H;

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0064, code lost:
    
        if (defpackage.y.o(r3.r(2)).q().compareTo(java.math.BigInteger.valueOf(defpackage.y.o(r3.r(0)).q().bitLength())) > 0) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BCDHPublicKey(defpackage.sx0 r18) {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.dh.BCDHPublicKey.<init>(sx0):void");
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.G = new DHParameterSpec((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), objectInputStream.readInt());
        this.H = null;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.G.getP());
        objectOutputStream.writeObject(this.G.getG());
        objectOutputStream.writeInt(this.G.getL());
    }

    public hl engineGetKeyParameters() {
        return this.F;
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
        return "DH";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        sx0 sx0Var = this.H;
        if (sx0Var != null) {
            return l21.M(sx0Var);
        }
        DHParameterSpec dHParameterSpec = this.G;
        boolean z = dHParameterSpec instanceof dl;
        BigInteger bigInteger = this.E;
        if (z) {
            dl dlVar = (dl) dHParameterSpec;
            if (dlVar.a != null) {
                BigInteger p = dlVar.getP();
                BigInteger g = dlVar.getG();
                int l = dlVar.getL();
                BigInteger bigInteger2 = dlVar.b;
                il ilVar = dlVar.d;
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
                BigInteger bigInteger3 = dlVar.a;
                if (bigInteger3 == null) {
                    throw new IllegalArgumentException("'q' cannot be null");
                }
                y yVar = new y(p);
                y yVar2 = new y(g);
                y yVar3 = new y(bigInteger3);
                y yVar4 = bigInteger2 != null ? new y(bigInteger2) : null;
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
                return l21.L(new l4(d0Var, wkVar), new y(bigInteger));
            }
        }
        return l21.L(new l4(vj0.c, new fl(dHParameterSpec.getP(), this.G.getG(), this.G.getL()).b()), new y(bigInteger));
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // javax.crypto.interfaces.DHKey
    public DHParameterSpec getParams() {
        return this.G;
    }

    @Override // javax.crypto.interfaces.DHPublicKey
    public BigInteger getY() {
        return this.E;
    }

    public int hashCode() {
        return ((getY().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getL();
    }

    public String toString() {
        BigInteger bigInteger = this.E;
        gl glVar = new gl(this.G.getP(), this.G.getG(), 0);
        StringBuffer stringBuffer = new StringBuffer("DH Public Key [");
        String str = jx0.a;
        stringBuffer.append(j31.n(bigInteger, glVar));
        stringBuffer.append("]");
        stringBuffer.append(str);
        stringBuffer.append("             Y: ");
        stringBuffer.append(bigInteger.toString(16));
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
