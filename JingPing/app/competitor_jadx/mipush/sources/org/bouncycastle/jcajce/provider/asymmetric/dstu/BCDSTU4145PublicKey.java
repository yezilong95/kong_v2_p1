package org.bouncycastle.jcajce.provider.asymmetric.dstu;

import defpackage.d0;
import defpackage.dm;
import defpackage.e0;
import defpackage.em;
import defpackage.em0;
import defpackage.fm;
import defpackage.g0;
import defpackage.gm;
import defpackage.hr;
import defpackage.ir;
import defpackage.j0;
import defpackage.kb;
import defpackage.kr;
import defpackage.l21;
import defpackage.l4;
import defpackage.oa;
import defpackage.or;
import defpackage.p;
import defpackage.q91;
import defpackage.qm;
import defpackage.s91;
import defpackage.sr;
import defpackage.sx0;
import defpackage.t;
import defpackage.t40;
import defpackage.tk;
import defpackage.tr;
import defpackage.u21;
import defpackage.u91;
import defpackage.vq;
import defpackage.xq;
import defpackage.y;
import defpackage.yq;
import defpackage.z91;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class BCDSTU4145PublicKey implements ECPublicKey, org.bouncycastle.jce.interfaces.ECPublicKey {
    static final long serialVersionUID = 7026240464295649314L;
    public String E;
    public boolean F;
    public transient sr G;
    public transient ECParameterSpec H;
    public transient gm I;

    public BCDSTU4145PublicKey(tr trVar, em0 em0Var) {
        this.E = "DSTU4145";
        throw null;
    }

    public static void b(byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            bArr[i] = bArr[(bArr.length - 1) - i];
            bArr[(bArr.length - 1) - i] = b;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        kr krVar;
        s91 s91VarG;
        objectInputStream.defaultReadObject();
        sx0 sx0VarG = sx0.g(g0.l((byte[]) objectInputStream.readObject()));
        p pVar = sx0VarG.F;
        this.E = "DSTU4145";
        try {
            byte[] bArr = ((e0) g0.l(pVar.p())).E;
            l4 l4Var = sx0VarG.E;
            d0 d0Var = l4Var.E;
            d0 d0Var2 = u21.a;
            if (d0Var.k(d0Var2)) {
                b(bArr);
            }
            j0 j0VarQ = j0.q(l4Var.F);
            if (j0VarQ.r(0) instanceof y) {
                s91VarG = s91.g(j0VarQ);
                krVar = new kr(s91VarG.F, s91VarG.G.g(), s91VarG.H, s91VarG.I, z91.l(s91VarG.J));
            } else {
                gm gmVarG = gm.g(j0VarQ);
                this.I = gmVarG;
                d0 d0Var3 = gmVarG.E;
                if (d0Var3 != null) {
                    yq yqVarA = fm.a(d0Var3);
                    krVar = new hr(d0Var3.r(), yqVarA.f, yqVarA.h, yqVarA.i, yqVarA.j, z91.l(yqVarA.g));
                } else {
                    em emVar = gmVarG.F;
                    byte[] bArrL = z91.l(emVar.H.E);
                    d0 d0Var4 = l4Var.E;
                    if (d0Var4.k(d0Var2)) {
                        b(bArrL);
                    }
                    dm dmVar = emVar.F;
                    vq vqVar = new vq(dmVar.E, dmVar.F, dmVar.G, dmVar.H, emVar.G.q(), new BigInteger(1, bArrL), null, null);
                    byte[] bArrL2 = z91.l(emVar.J.E);
                    if (d0Var4.k(d0Var2)) {
                        b(bArrL2);
                    }
                    krVar = new kr(vqVar, z91.s(vqVar, bArrL2), emVar.I.q());
                }
                s91VarG = null;
            }
            xq xqVar = krVar.a;
            EllipticCurve ellipticCurveT = t40.t(xqVar);
            if (this.I != null) {
                ECPoint eCPointW = t40.w(krVar.c);
                d0 d0Var5 = this.I.E;
                if (d0Var5 != null) {
                    this.H = new ir(d0Var5.r(), ellipticCurveT, eCPointW, krVar.d, krVar.e);
                } else {
                    this.H = new ECParameterSpec(ellipticCurveT, eCPointW, krVar.d, krVar.e.intValue());
                }
            } else {
                this.H = new ECParameterSpec(t40.t(s91VarG.F), t40.w(s91VarG.G.g()), s91VarG.H, s91VarG.I.intValue());
            }
            this.G = new sr(z91.s(xqVar, bArr), t40.b0(null, this.H));
        } catch (IOException unused) {
            throw new IllegalArgumentException("error recovering public key");
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public final kr a() {
        ECParameterSpec eCParameterSpec = this.H;
        return eCParameterSpec != null ? t40.x(eCParameterSpec) : ((kb) BouncyCastleProvider.CONFIGURATION).a();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCDSTU4145PublicKey)) {
            return false;
        }
        BCDSTU4145PublicKey bCDSTU4145PublicKey = (BCDSTU4145PublicKey) obj;
        return this.G.G.d(bCDSTU4145PublicKey.G.G) && a().equals(bCDSTU4145PublicKey.a());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.E;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        t q91Var = this.I;
        if (q91Var == null) {
            ECParameterSpec eCParameterSpec = this.H;
            if (eCParameterSpec instanceof ir) {
                q91Var = new gm(new d0(((ir) this.H).a));
            } else {
                xq xqVarS = t40.s(eCParameterSpec.getCurve());
                q91Var = new q91(new s91(xqVarS, new u91(t40.u(xqVarS, this.H.getGenerator()), this.F), this.H.getOrder(), BigInteger.valueOf(this.H.getCofactor()), this.H.getCurve().getSeed()));
            }
        }
        or orVarO = this.G.G.o();
        orVarO.b();
        l21 l21Var = orVarO.b;
        byte[] bArrA = oa.a(l21Var.J(), l21Var.h1());
        if (!l21Var.d0()) {
            if (z91.u0(orVarO.e().A(l21Var)).b0()) {
                int length = bArrA.length - 1;
                bArrA[length] = (byte) (bArrA[length] | 1);
            } else {
                int length2 = bArrA.length - 1;
                bArrA[length2] = (byte) (bArrA[length2] & 254);
            }
        }
        try {
            return l21.M(new sx0(new l4(u21.b, q91Var), new tk(bArrA)));
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // org.bouncycastle.jce.interfaces.ECPublicKey
    public kr getParameters() {
        ECParameterSpec eCParameterSpec = this.H;
        if (eCParameterSpec == null) {
            return null;
        }
        return t40.x(eCParameterSpec);
    }

    @Override // java.security.interfaces.ECKey
    public ECParameterSpec getParams() {
        return this.H;
    }

    @Override // org.bouncycastle.jce.interfaces.ECPublicKey
    public or getQ() {
        or orVar = this.G.G;
        return this.H == null ? orVar.o().c() : orVar;
    }

    public byte[] getSbox() {
        gm gmVar = this.I;
        return gmVar != null ? z91.l(gmVar.G) : z91.l(gm.H);
    }

    @Override // java.security.interfaces.ECPublicKey
    public ECPoint getW() {
        return t40.w(this.G.G);
    }

    public int hashCode() {
        return this.G.G.hashCode() ^ a().hashCode();
    }

    public void setPointFormat(String str) {
        this.F = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public String toString() {
        return qm.c0(this.E, this.G.G, a());
    }

    public BCDSTU4145PublicKey(String str, sr srVar) {
        this.E = str;
        this.G = srVar;
        this.H = null;
    }

    public BCDSTU4145PublicKey(String str, sr srVar, kr krVar) {
        this.E = "DSTU4145";
        yq yqVar = (yq) srVar.F;
        this.E = str;
        if (krVar == null) {
            z91.l(yqVar.g);
            this.H = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        } else {
            this.H = t40.y(t40.t(krVar.a), krVar);
        }
        this.G = srVar;
    }

    public BCDSTU4145PublicKey(String str, sr srVar, ECParameterSpec eCParameterSpec) {
        this.E = "DSTU4145";
        yq yqVar = (yq) srVar.F;
        this.E = str;
        this.G = srVar;
        if (eCParameterSpec != null) {
            this.H = eCParameterSpec;
            return;
        }
        z91.l(yqVar.g);
        this.H = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
    }

    public BCDSTU4145PublicKey(ECPublicKeySpec eCPublicKeySpec) {
        this.E = "DSTU4145";
        ECParameterSpec params = eCPublicKeySpec.getParams();
        this.H = params;
        this.G = new sr(t40.v(params, eCPublicKeySpec.getW()), t40.b0(null, this.H));
    }

    public BCDSTU4145PublicKey(BCDSTU4145PublicKey bCDSTU4145PublicKey) {
        this.E = "DSTU4145";
        this.G = bCDSTU4145PublicKey.G;
        this.H = bCDSTU4145PublicKey.H;
        this.F = bCDSTU4145PublicKey.F;
        this.I = bCDSTU4145PublicKey.I;
    }
}
