package org.bouncycastle.jcajce.provider.asymmetric.ecgost;

import defpackage.a0;
import defpackage.d0;
import defpackage.dr;
import defpackage.e0;
import defpackage.em0;
import defpackage.g0;
import defpackage.hr;
import defpackage.ir;
import defpackage.j31;
import defpackage.kb;
import defpackage.kr;
import defpackage.l21;
import defpackage.l4;
import defpackage.or;
import defpackage.p;
import defpackage.q91;
import defpackage.qm;
import defpackage.rj;
import defpackage.s91;
import defpackage.sr;
import defpackage.sx0;
import defpackage.t;
import defpackage.t40;
import defpackage.tk;
import defpackage.tr;
import defpackage.u10;
import defpackage.u91;
import defpackage.xq;
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
public class BCECGOST3410PublicKey implements ECPublicKey, org.bouncycastle.jce.interfaces.ECPublicKey {
    static final long serialVersionUID = 7026240464295649314L;
    public String E;
    public boolean F;
    public transient sr G;
    public transient ECParameterSpec H;
    public transient a0 I;

    public BCECGOST3410PublicKey(tr trVar, em0 em0Var) {
        this.E = "ECGOST3410";
        throw null;
    }

    public static void b(byte[] bArr, int i, BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length < 32) {
            byte[] bArr2 = new byte[32];
            System.arraycopy(byteArray, 0, bArr2, 32 - byteArray.length, byteArray.length);
            byteArray = bArr2;
        }
        for (int i2 = 0; i2 != 32; i2++) {
            bArr[i + i2] = byteArray[(byteArray.length - 1) - i2];
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        d0 d0VarS;
        objectInputStream.defaultReadObject();
        sx0 sx0VarG = sx0.g(g0.l((byte[]) objectInputStream.readObject()));
        p pVar = sx0VarG.F;
        this.E = "ECGOST3410";
        try {
            byte[] bArr = ((e0) g0.l(pVar.p())).E;
            byte[] bArr2 = new byte[65];
            bArr2[0] = 4;
            for (int i = 1; i <= 32; i++) {
                bArr2[i] = bArr[32 - i];
                bArr2[i + 32] = bArr[64 - i];
            }
            t tVar = sx0VarG.E.F;
            if (tVar instanceof d0) {
                d0VarS = d0.s(tVar);
                this.I = d0VarS;
            } else {
                u10 u10VarG = u10.g(tVar);
                this.I = u10VarG;
                d0VarS = u10VarG.E;
            }
            hr hrVarR = j31.r(dr.d(d0VarS));
            xq xqVar = hrVarR.a;
            EllipticCurve ellipticCurveT = t40.t(xqVar);
            this.G = new sr(xqVar.e(bArr2), qm.y(null, hrVarR));
            this.H = new ir(dr.d(d0VarS), ellipticCurveT, t40.w(hrVarR.c), hrVarR.d, hrVarR.e);
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

    public final t c() {
        if (this.I == null) {
            ECParameterSpec eCParameterSpec = this.H;
            if (eCParameterSpec instanceof ir) {
                this.I = new u10(dr.e(((ir) eCParameterSpec).a), rj.d);
            }
        }
        return this.I;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCECGOST3410PublicKey)) {
            return false;
        }
        BCECGOST3410PublicKey bCECGOST3410PublicKey = (BCECGOST3410PublicKey) obj;
        return this.G.G.d(bCECGOST3410PublicKey.G.G) && a().equals(bCECGOST3410PublicKey.a());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.E;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        t tVarC = c();
        if (tVarC == null) {
            ECParameterSpec eCParameterSpec = this.H;
            if (eCParameterSpec instanceof ir) {
                tVarC = new u10(dr.e(((ir) eCParameterSpec).a), rj.d);
            } else {
                xq xqVarS = t40.s(eCParameterSpec.getCurve());
                tVarC = new q91(new s91(xqVarS, new u91(t40.u(xqVarS, this.H.getGenerator()), this.F), this.H.getOrder(), BigInteger.valueOf(this.H.getCofactor()), this.H.getCurve().getSeed()));
            }
        }
        or orVar = this.G.G;
        orVar.b();
        BigInteger bigIntegerH1 = orVar.b.h1();
        BigInteger bigIntegerH12 = this.G.G.e().h1();
        byte[] bArr = new byte[64];
        b(bArr, 0, bigIntegerH1);
        b(bArr, 32, bigIntegerH12);
        try {
            return l21.M(new sx0(new l4(rj.c, tVarC), new tk(bArr)));
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
        return this.H == null ? this.G.G.o().c() : this.G.G;
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

    public BCECGOST3410PublicKey(String str, sr srVar) {
        this.E = str;
        this.G = srVar;
        this.H = null;
    }

    public BCECGOST3410PublicKey(String str, sr srVar, kr krVar) {
        this.E = "ECGOST3410";
        yq yqVar = (yq) srVar.F;
        this.E = str;
        this.G = srVar;
        if (krVar != null) {
            this.H = t40.y(t40.t(krVar.a), krVar);
            return;
        }
        z91.l(yqVar.g);
        this.H = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
    }

    public BCECGOST3410PublicKey(String str, sr srVar, ECParameterSpec eCParameterSpec) {
        this.E = "ECGOST3410";
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

    public BCECGOST3410PublicKey(ECPublicKey eCPublicKey) {
        this.E = "ECGOST3410";
        this.E = eCPublicKey.getAlgorithm();
        ECParameterSpec params = eCPublicKey.getParams();
        this.H = params;
        this.G = new sr(t40.v(params, eCPublicKey.getW()), t40.b0(null, eCPublicKey.getParams()));
    }

    public BCECGOST3410PublicKey(ECPublicKeySpec eCPublicKeySpec) {
        this.E = "ECGOST3410";
        ECParameterSpec params = eCPublicKeySpec.getParams();
        this.H = params;
        this.G = new sr(t40.v(params, eCPublicKeySpec.getW()), t40.b0(null, eCPublicKeySpec.getParams()));
    }

    public BCECGOST3410PublicKey(BCECGOST3410PublicKey bCECGOST3410PublicKey) {
        this.E = "ECGOST3410";
        this.G = bCECGOST3410PublicKey.G;
        this.H = bCECGOST3410PublicKey.H;
        this.F = bCECGOST3410PublicKey.F;
        this.I = bCECGOST3410PublicKey.I;
    }
}
