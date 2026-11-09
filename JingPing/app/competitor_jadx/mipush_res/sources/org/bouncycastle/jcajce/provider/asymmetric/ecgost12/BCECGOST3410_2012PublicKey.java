package org.bouncycastle.jcajce.provider.asymmetric.ecgost12;

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
import defpackage.rp0;
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
public class BCECGOST3410_2012PublicKey implements ECPublicKey, org.bouncycastle.jce.interfaces.ECPublicKey {
    static final long serialVersionUID = 7026240464295649314L;
    public String E;
    public boolean F;
    public transient sr G;
    public transient ECParameterSpec H;
    public transient u10 I;

    public BCECGOST3410_2012PublicKey(tr trVar, em0 em0Var) {
        this.E = "ECGOST3410-2012";
        throw null;
    }

    public static void b(byte[] bArr, int i, int i2, BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length < i) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(byteArray, 0, bArr2, i - byteArray.length, byteArray.length);
            byteArray = bArr2;
        }
        for (int i3 = 0; i3 != i; i3++) {
            bArr[i2 + i3] = byteArray[(byteArray.length - 1) - i3];
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        sx0 sx0VarG = sx0.g(g0.l((byte[]) objectInputStream.readObject()));
        d0 d0Var = sx0VarG.E.E;
        p pVar = sx0VarG.F;
        this.E = "ECGOST3410-2012";
        try {
            byte[] bArr = ((e0) g0.l(pVar.p())).E;
            int i = d0Var.k(rp0.d) ? 64 : 32;
            int i2 = i * 2;
            byte[] bArr2 = new byte[i2 + 1];
            bArr2[0] = 4;
            for (int i3 = 1; i3 <= i; i3++) {
                bArr2[i3] = bArr[i - i3];
                bArr2[i3 + i] = bArr[i2 - i3];
            }
            u10 u10VarG = u10.g(sx0VarG.E.F);
            this.I = u10VarG;
            hr hrVarR = j31.r(dr.d(u10VarG.E));
            xq xqVar = hrVarR.a;
            EllipticCurve ellipticCurveT = t40.t(xqVar);
            this.G = new sr(xqVar.e(bArr2), qm.y(null, hrVarR));
            this.H = new ir(dr.d(this.I.E), ellipticCurveT, t40.w(hrVarR.c), hrVarR.d, hrVarR.e);
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
        if (!(obj instanceof BCECGOST3410_2012PublicKey)) {
            return false;
        }
        BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey = (BCECGOST3410_2012PublicKey) obj;
        return this.G.G.d(bCECGOST3410_2012PublicKey.G.G) && a().equals(bCECGOST3410_2012PublicKey.a());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.E;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        d0 d0Var;
        int i;
        or orVar = this.G.G;
        orVar.b();
        BigInteger bigIntegerH1 = orVar.b.h1();
        BigInteger bigIntegerH12 = this.G.G.e().h1();
        boolean z = bigIntegerH1.bitLength() > 256;
        t gostParams = getGostParams();
        if (gostParams == null) {
            ECParameterSpec eCParameterSpec = this.H;
            if (eCParameterSpec instanceof ir) {
                d0 d0VarE = dr.e(((ir) eCParameterSpec).a);
                gostParams = z ? new u10(d0VarE, rp0.b) : (d0VarE.k(rp0.f) || d0VarE.k(rp0.g) || d0VarE.k(rp0.h)) ? new u10(d0VarE, null) : new u10(d0VarE, rp0.a);
            } else {
                xq xqVarS = t40.s(eCParameterSpec.getCurve());
                gostParams = new q91(new s91(xqVarS, new u91(t40.u(xqVarS, this.H.getGenerator()), this.F), this.H.getOrder(), BigInteger.valueOf(this.H.getCofactor()), this.H.getCurve().getSeed()));
            }
        }
        int i2 = 64;
        if (z) {
            d0Var = rp0.d;
            i = 64;
            i2 = 128;
        } else {
            d0Var = rp0.c;
            i = 32;
        }
        byte[] bArr = new byte[i2];
        int i3 = i2 / 2;
        b(bArr, i3, 0, bigIntegerH1);
        b(bArr, i3, i, bigIntegerH12);
        try {
            return l21.M(new sx0(new l4(d0Var, gostParams), new tk(bArr)));
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public u10 getGostParams() {
        if (this.I == null && (this.H instanceof ir)) {
            or orVar = this.G.G;
            orVar.b();
            boolean z = orVar.b.h1().bitLength() > 256;
            d0 d0VarE = dr.e(((ir) this.H).a);
            this.I = z ? new u10(d0VarE, rp0.b) : (d0VarE.k(rp0.f) || d0VarE.k(rp0.g) || d0VarE.k(rp0.h)) ? new u10(d0VarE, null) : new u10(d0VarE, rp0.a);
        }
        return this.I;
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

    public BCECGOST3410_2012PublicKey(String str, sr srVar) {
        this.E = str;
        this.G = srVar;
        this.H = null;
    }

    public BCECGOST3410_2012PublicKey(String str, sr srVar, kr krVar) {
        this.E = "ECGOST3410-2012";
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

    public BCECGOST3410_2012PublicKey(String str, sr srVar, ECParameterSpec eCParameterSpec) {
        this.E = "ECGOST3410-2012";
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

    public BCECGOST3410_2012PublicKey(ECPublicKey eCPublicKey) {
        this.E = "ECGOST3410-2012";
        this.E = eCPublicKey.getAlgorithm();
        ECParameterSpec params = eCPublicKey.getParams();
        this.H = params;
        this.G = new sr(t40.v(params, eCPublicKey.getW()), t40.b0(null, eCPublicKey.getParams()));
    }

    public BCECGOST3410_2012PublicKey(ECPublicKeySpec eCPublicKeySpec) {
        this.E = "ECGOST3410-2012";
        ECParameterSpec params = eCPublicKeySpec.getParams();
        this.H = params;
        this.G = new sr(t40.v(params, eCPublicKeySpec.getW()), t40.b0(null, eCPublicKeySpec.getParams()));
    }

    public BCECGOST3410_2012PublicKey(BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey) {
        this.E = "ECGOST3410-2012";
        this.G = bCECGOST3410_2012PublicKey.G;
        this.H = bCECGOST3410_2012PublicKey.H;
        this.F = bCECGOST3410_2012PublicKey.F;
        this.I = bCECGOST3410_2012PublicKey.I;
    }
}
