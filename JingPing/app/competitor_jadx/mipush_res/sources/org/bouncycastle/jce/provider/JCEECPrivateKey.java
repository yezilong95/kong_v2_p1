package org.bouncycastle.jce.provider;

import defpackage.d0;
import defpackage.g0;
import defpackage.i6;
import defpackage.ir;
import defpackage.j0;
import defpackage.jx0;
import defpackage.kb;
import defpackage.kr;
import defpackage.l4;
import defpackage.ol0;
import defpackage.p;
import defpackage.pr;
import defpackage.q91;
import defpackage.qm;
import defpackage.qr;
import defpackage.rj;
import defpackage.rr;
import defpackage.s91;
import defpackage.sx0;
import defpackage.t;
import defpackage.t40;
import defpackage.u91;
import defpackage.w91;
import defpackage.xq;
import defpackage.y;
import defpackage.yq;
import defpackage.z;
import defpackage.z91;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class JCEECPrivateKey implements ECPrivateKey, org.bouncycastle.jce.interfaces.ECPrivateKey {
    public String E;
    public BigInteger F;
    public ECParameterSpec G;
    public boolean H;
    public p I;
    public i6 J;

    public JCEECPrivateKey(String str, qr qrVar) {
        this.E = "EC";
        this.J = new i6(24);
        this.E = str;
        this.F = qrVar.G;
        this.G = null;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        ol0 ol0VarG = ol0.g(g0.l((byte[]) objectInputStream.readObject()));
        g0 g0Var = q91.g(ol0VarG.F.F).E;
        if (g0Var instanceof d0) {
            d0 d0VarS = d0.s(g0Var);
            s91 s91VarF = qm.F(d0VarS);
            if (s91VarF != null) {
                z91.l(s91VarF.J);
                this.G = new ir(qm.v(d0VarS), t40.t(s91VarF.F), t40.w(s91VarF.G.g()), s91VarF.H, s91VarF.I);
            }
        } else if (g0Var instanceof z) {
            this.G = null;
        } else {
            s91 s91VarG = s91.g(g0Var);
            xq xqVar = s91VarG.F;
            z91.l(s91VarG.J);
            this.G = new ECParameterSpec(t40.t(xqVar), t40.w(s91VarG.G.g()), s91VarG.H, s91VarG.I.intValue());
        }
        g0 g0VarI = ol0VarG.i();
        if (g0VarI instanceof y) {
            this.F = y.o(g0VarI).q();
        } else {
            pr prVarG = pr.g(g0VarI);
            this.F = prVarG.h();
            this.I = prVarG.i();
        }
        this.E = (String) objectInputStream.readObject();
        this.H = objectInputStream.readBoolean();
        i6 i6Var = new i6(24);
        this.J = i6Var;
        i6Var.K(objectInputStream);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(getEncoded());
        objectOutputStream.writeObject(this.E);
        objectOutputStream.writeBoolean(this.H);
        this.J.S(objectOutputStream);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof JCEECPrivateKey)) {
            return false;
        }
        JCEECPrivateKey jCEECPrivateKey = (JCEECPrivateKey) obj;
        if (!getD().equals(jCEECPrivateKey.getD())) {
            return false;
        }
        ECParameterSpec eCParameterSpec = this.G;
        kr krVarX = eCParameterSpec != null ? t40.x(eCParameterSpec) : ((kb) BouncyCastleProvider.CONFIGURATION).a();
        ECParameterSpec eCParameterSpec2 = jCEECPrivateKey.G;
        return krVarX.equals(eCParameterSpec2 != null ? t40.x(eCParameterSpec2) : ((kb) BouncyCastleProvider.CONFIGURATION).a());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.E;
    }

    public t getBagAttribute(d0 d0Var) {
        return this.J.y(d0Var);
    }

    public Enumeration getBagAttributeKeys() {
        return ((Vector) this.J.G).elements();
    }

    @Override // org.bouncycastle.jce.interfaces.ECPrivateKey
    public BigInteger getD() {
        return this.F;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        q91 q91Var;
        ECParameterSpec eCParameterSpec = this.G;
        if (eCParameterSpec instanceof ir) {
            d0 d0VarG = qm.G(((ir) eCParameterSpec).a);
            if (d0VarG == null) {
                d0VarG = new d0(((ir) this.G).a);
            }
            q91Var = new q91(d0VarG);
        } else if (eCParameterSpec == null) {
            q91Var = new q91();
        } else {
            xq xqVarS = t40.s(eCParameterSpec.getCurve());
            q91Var = new q91(new s91(xqVarS, new u91(t40.u(xqVarS, this.G.getGenerator()), this.H), this.G.getOrder(), BigInteger.valueOf(this.G.getCofactor()), this.G.getCurve().getSeed()));
        }
        ECParameterSpec eCParameterSpec2 = this.G;
        int iBitLength = eCParameterSpec2 == null ? getS().bitLength() : qm.H(null, eCParameterSpec2.getOrder(), getS());
        try {
            j0 j0Var = (this.I != null ? new pr(iBitLength, getS(), this.I, q91Var) : new pr(iBitLength, getS(), null, q91Var)).E;
            boolean zEquals = this.E.equals("ECGOST3410");
            g0 g0Var = q91Var.E;
            return (zEquals ? new ol0(new l4(rj.c, g0Var), j0Var, null, null) : new ol0(new l4(w91.e, g0Var), j0Var, null, null)).f();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.bouncycastle.jce.interfaces.ECPrivateKey
    public kr getParameters() {
        ECParameterSpec eCParameterSpec = this.G;
        if (eCParameterSpec == null) {
            return null;
        }
        return t40.x(eCParameterSpec);
    }

    @Override // java.security.interfaces.ECKey
    public ECParameterSpec getParams() {
        return this.G;
    }

    @Override // java.security.interfaces.ECPrivateKey
    public BigInteger getS() {
        return this.F;
    }

    public int hashCode() {
        int iHashCode = getD().hashCode();
        ECParameterSpec eCParameterSpec = this.G;
        return iHashCode ^ (eCParameterSpec != null ? t40.x(eCParameterSpec) : ((kb) BouncyCastleProvider.CONFIGURATION).a()).hashCode();
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        this.J.N(d0Var, tVar);
    }

    public void setPointFormat(String str) {
        this.H = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("EC Private Key");
        String str = jx0.a;
        stringBuffer.append(str);
        stringBuffer.append("             S: ");
        stringBuffer.append(this.F.toString(16));
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public JCEECPrivateKey(String str, qr qrVar, JCEECPublicKey jCEECPublicKey, kr krVar) {
        p pVar;
        this.E = "EC";
        this.J = new i6(24);
        this.E = str;
        this.F = qrVar.G;
        if (krVar == null) {
            yq yqVar = (yq) qrVar.F;
            z91.l(yqVar.g);
            this.G = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        } else {
            this.G = new ECParameterSpec(t40.t(krVar.a), t40.w(krVar.c), krVar.d, krVar.e.intValue());
        }
        try {
            pVar = sx0.g(g0.l(jCEECPublicKey.getEncoded())).F;
        } catch (IOException unused) {
            pVar = null;
        }
        this.I = pVar;
    }

    public JCEECPrivateKey(String str, qr qrVar, JCEECPublicKey jCEECPublicKey, ECParameterSpec eCParameterSpec) {
        p pVar;
        this.E = "EC";
        this.J = new i6(24);
        this.E = str;
        this.F = qrVar.G;
        if (eCParameterSpec == null) {
            yq yqVar = (yq) qrVar.F;
            z91.l(yqVar.g);
            eCParameterSpec = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        }
        this.G = eCParameterSpec;
        try {
            pVar = sx0.g(g0.l(jCEECPublicKey.getEncoded())).F;
        } catch (IOException unused) {
            pVar = null;
        }
        this.I = pVar;
    }

    public JCEECPrivateKey(String str, rr rrVar) {
        this.E = "EC";
        this.J = new i6(24);
        this.E = str;
        throw null;
    }

    public JCEECPrivateKey(String str, ECPrivateKeySpec eCPrivateKeySpec) {
        this.E = "EC";
        this.J = new i6(24);
        this.E = str;
        this.F = eCPrivateKeySpec.getS();
        this.G = eCPrivateKeySpec.getParams();
    }

    public JCEECPrivateKey(String str, JCEECPrivateKey jCEECPrivateKey) {
        this.E = "EC";
        this.J = new i6(24);
        this.E = str;
        this.F = jCEECPrivateKey.F;
        this.G = jCEECPrivateKey.G;
        this.H = jCEECPrivateKey.H;
        this.J = jCEECPrivateKey.J;
        this.I = jCEECPrivateKey.I;
    }

    public JCEECPrivateKey(ECPrivateKey eCPrivateKey) {
        this.E = "EC";
        this.J = new i6(24);
        this.F = eCPrivateKey.getS();
        this.E = eCPrivateKey.getAlgorithm();
        this.G = eCPrivateKey.getParams();
    }
}
