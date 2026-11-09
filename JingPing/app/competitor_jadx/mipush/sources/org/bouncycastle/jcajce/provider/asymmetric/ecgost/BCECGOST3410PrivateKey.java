package org.bouncycastle.jcajce.provider.asymmetric.ecgost;

import defpackage.d0;
import defpackage.g0;
import defpackage.i6;
import defpackage.ir;
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
import defpackage.tk;
import defpackage.u91;
import defpackage.xq;
import defpackage.yq;
import defpackage.z91;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class BCECGOST3410PrivateKey implements ECPrivateKey, org.bouncycastle.jce.interfaces.ECPrivateKey {
    static final long serialVersionUID = 7245981689601667138L;
    public final String E;
    public boolean F;
    public transient t G;
    public transient BigInteger H;
    public transient ECParameterSpec I;
    public transient p J;
    public transient i6 K;

    public BCECGOST3410PrivateKey(rr rrVar) {
        this.E = "ECGOST3410";
        this.K = new i6(24);
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void readObject(java.io.ObjectInputStream r10) throws java.lang.ClassNotFoundException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.ecgost.BCECGOST3410PrivateKey.readObject(java.io.ObjectInputStream):void");
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public final kr a() {
        ECParameterSpec eCParameterSpec = this.I;
        return eCParameterSpec != null ? t40.x(eCParameterSpec) : ((kb) BouncyCastleProvider.CONFIGURATION).a();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCECGOST3410PrivateKey)) {
            return false;
        }
        BCECGOST3410PrivateKey bCECGOST3410PrivateKey = (BCECGOST3410PrivateKey) obj;
        return getD().equals(bCECGOST3410PrivateKey.getD()) && a().equals(bCECGOST3410PrivateKey.a());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.E;
    }

    public t getBagAttribute(d0 d0Var) {
        return this.K.y(d0Var);
    }

    public Enumeration getBagAttributeKeys() {
        return ((Vector) this.K.G).elements();
    }

    @Override // org.bouncycastle.jce.interfaces.ECPrivateKey
    public BigInteger getD() {
        return this.H;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        q91 q91Var;
        int iH;
        ol0 ol0Var;
        try {
            if (this.G != null) {
                byte[] bArr = new byte[32];
                byte[] byteArray = getS().toByteArray();
                if (byteArray.length < 32) {
                    byte[] bArr2 = new byte[32];
                    System.arraycopy(byteArray, 0, bArr2, 32 - byteArray.length, byteArray.length);
                    byteArray = bArr2;
                }
                for (int i = 0; i != 32; i++) {
                    bArr[i] = byteArray[(byteArray.length - 1) - i];
                }
                ol0Var = new ol0(new l4(rj.c, this.G), new tk(bArr), null, null);
            } else {
                ECParameterSpec eCParameterSpec = this.I;
                if (eCParameterSpec instanceof ir) {
                    d0 d0VarG = qm.G(((ir) eCParameterSpec).a);
                    if (d0VarG == null) {
                        d0VarG = new d0(((ir) this.I).a);
                    }
                    q91Var = new q91(d0VarG);
                    iH = qm.H(BouncyCastleProvider.CONFIGURATION, this.I.getOrder(), getS());
                } else if (eCParameterSpec == null) {
                    q91Var = new q91();
                    iH = qm.H(BouncyCastleProvider.CONFIGURATION, null, getS());
                } else {
                    xq xqVarS = t40.s(eCParameterSpec.getCurve());
                    q91Var = new q91(new s91(xqVarS, new u91(t40.u(xqVarS, this.I.getGenerator()), this.F), this.I.getOrder(), BigInteger.valueOf(this.I.getCofactor()), this.I.getCurve().getSeed()));
                    iH = qm.H(BouncyCastleProvider.CONFIGURATION, this.I.getOrder(), getS());
                }
                ol0Var = new ol0(new l4(rj.c, q91Var.E), (this.J != null ? new pr(iH, getS(), this.J, q91Var) : new pr(iH, getS(), null, q91Var)).E, null, null);
            }
            return ol0Var.f();
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
        ECParameterSpec eCParameterSpec = this.I;
        if (eCParameterSpec == null) {
            return null;
        }
        return t40.x(eCParameterSpec);
    }

    @Override // java.security.interfaces.ECKey
    public ECParameterSpec getParams() {
        return this.I;
    }

    @Override // java.security.interfaces.ECPrivateKey
    public BigInteger getS() {
        return this.H;
    }

    public int hashCode() {
        return getD().hashCode() ^ a().hashCode();
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        this.K.N(d0Var, tVar);
    }

    public void setPointFormat(String str) {
        this.F = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public String toString() {
        return qm.b0(this.E, this.H, a());
    }

    public BCECGOST3410PrivateKey(String str, qr qrVar) {
        this.E = "ECGOST3410";
        this.K = new i6(24);
        this.E = str;
        this.H = qrVar.G;
        this.I = null;
    }

    public BCECGOST3410PrivateKey(String str, qr qrVar, BCECGOST3410PublicKey bCECGOST3410PublicKey, kr krVar) {
        p pVar;
        this.E = "ECGOST3410";
        this.K = new i6(24);
        this.E = str;
        this.H = qrVar.G;
        if (krVar == null) {
            yq yqVar = (yq) qrVar.F;
            z91.l(yqVar.g);
            this.I = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        } else {
            this.I = new ECParameterSpec(t40.t(krVar.a), t40.w(krVar.c), krVar.d, krVar.e.intValue());
        }
        this.G = bCECGOST3410PublicKey.c();
        try {
            pVar = sx0.g(g0.l(bCECGOST3410PublicKey.getEncoded())).F;
        } catch (IOException unused) {
            pVar = null;
        }
        this.J = pVar;
    }

    public BCECGOST3410PrivateKey(String str, qr qrVar, BCECGOST3410PublicKey bCECGOST3410PublicKey, ECParameterSpec eCParameterSpec) {
        p pVar;
        this.E = "ECGOST3410";
        this.K = new i6(24);
        this.E = str;
        this.H = qrVar.G;
        if (eCParameterSpec == null) {
            yq yqVar = (yq) qrVar.F;
            z91.l(yqVar.g);
            eCParameterSpec = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        }
        this.I = eCParameterSpec;
        this.G = bCECGOST3410PublicKey.c();
        try {
            pVar = sx0.g(g0.l(bCECGOST3410PublicKey.getEncoded())).F;
        } catch (IOException unused) {
            pVar = null;
        }
        this.J = pVar;
    }

    public BCECGOST3410PrivateKey(ECPrivateKey eCPrivateKey) {
        this.E = "ECGOST3410";
        this.K = new i6(24);
        this.H = eCPrivateKey.getS();
        this.E = eCPrivateKey.getAlgorithm();
        this.I = eCPrivateKey.getParams();
    }

    public BCECGOST3410PrivateKey(ECPrivateKeySpec eCPrivateKeySpec) {
        this.E = "ECGOST3410";
        this.K = new i6(24);
        this.H = eCPrivateKeySpec.getS();
        this.I = eCPrivateKeySpec.getParams();
    }

    public BCECGOST3410PrivateKey(BCECGOST3410PrivateKey bCECGOST3410PrivateKey) {
        this.E = "ECGOST3410";
        this.K = new i6(24);
        this.H = bCECGOST3410PrivateKey.H;
        this.I = bCECGOST3410PrivateKey.I;
        this.F = bCECGOST3410PrivateKey.F;
        this.K = bCECGOST3410PrivateKey.K;
        this.J = bCECGOST3410PrivateKey.J;
        this.G = bCECGOST3410PrivateKey.G;
    }
}
