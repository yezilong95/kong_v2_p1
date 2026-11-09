package org.bouncycastle.jce.provider;

import defpackage.d0;
import defpackage.dr;
import defpackage.e0;
import defpackage.g0;
import defpackage.hr;
import defpackage.ir;
import defpackage.j31;
import defpackage.jx0;
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
import defpackage.w91;
import defpackage.xq;
import defpackage.yq;
import defpackage.z;
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

/* JADX INFO: loaded from: classes.dex */
public class JCEECPublicKey implements ECPublicKey, org.bouncycastle.jce.interfaces.ECPublicKey {
    public String E;
    public or F;
    public ECParameterSpec G;
    public boolean H;
    public u10 I;

    public JCEECPublicKey(String str, sr srVar) {
        this.E = str;
        this.F = srVar.G;
        this.G = null;
    }

    public static void a(byte[] bArr, int i, BigInteger bigInteger) {
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

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        xq xqVar;
        byte b;
        sx0 sx0VarG = sx0.g(g0.l((byte[]) objectInputStream.readObject()));
        l4 l4Var = sx0VarG.E;
        boolean zK = l4Var.E.k(rj.c);
        p pVar = sx0VarG.F;
        t tVar = l4Var.F;
        if (zK) {
            this.E = "ECGOST3410";
            try {
                byte[] bArr = ((e0) g0.l(pVar.p())).E;
                byte[] bArr2 = new byte[65];
                bArr2[0] = 4;
                for (int i = 1; i <= 32; i++) {
                    bArr2[i] = bArr[32 - i];
                    bArr2[i + 32] = bArr[64 - i];
                }
                u10 u10VarG = u10.g(tVar);
                this.I = u10VarG;
                hr hrVarR = j31.r(dr.d(u10VarG.E));
                xq xqVar2 = hrVarR.a;
                EllipticCurve ellipticCurveT = t40.t(xqVar2);
                this.F = xqVar2.e(bArr2);
                this.G = new ir(dr.d(this.I.E), ellipticCurveT, t40.w(hrVarR.c), hrVarR.d, hrVarR.e);
            } catch (IOException unused) {
                throw new IllegalArgumentException("error recovering public key");
            }
        } else {
            g0 g0Var = q91.g(tVar).E;
            if (g0Var instanceof d0) {
                d0 d0Var = (d0) g0Var;
                s91 s91VarF = qm.F(d0Var);
                xqVar = s91VarF.F;
                z91.l(s91VarF.J);
                this.G = new ir(qm.v(d0Var), t40.t(xqVar), t40.w(s91VarF.G.g()), s91VarF.H, s91VarF.I);
            } else if (g0Var instanceof z) {
                this.G = null;
                xqVar = ((kb) BouncyCastleProvider.CONFIGURATION).a().a;
            } else {
                s91 s91VarG = s91.g(g0Var);
                xqVar = s91VarG.F;
                z91.l(s91VarG.J);
                this.G = new ECParameterSpec(t40.t(xqVar), t40.w(s91VarG.G.g()), s91VarG.H, s91VarG.I.intValue());
            }
            byte[] bArrP = pVar.p();
            e0 tkVar = new tk(bArrP);
            if (bArrP[0] == 4 && bArrP[1] == bArrP.length - 2 && (((b = bArrP[2]) == 2 || b == 3) && xqVar.i() >= bArrP.length - 3)) {
                try {
                    tkVar = (e0) g0.l(bArrP);
                } catch (IOException unused2) {
                    throw new IllegalArgumentException("error recovering public key");
                }
            }
            byte[] bArrL = z91.l(tkVar.E);
            new tk(bArrL);
            this.F = xqVar.e(bArrL).o();
        }
        this.E = (String) objectInputStream.readObject();
        this.H = objectInputStream.readBoolean();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(getEncoded());
        objectOutputStream.writeObject(this.E);
        objectOutputStream.writeBoolean(this.H);
    }

    public or engineGetQ() {
        return this.F;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof JCEECPublicKey)) {
            return false;
        }
        JCEECPublicKey jCEECPublicKey = (JCEECPublicKey) obj;
        if (!engineGetQ().d(jCEECPublicKey.engineGetQ())) {
            return false;
        }
        ECParameterSpec eCParameterSpec = this.G;
        kr krVarX = eCParameterSpec != null ? t40.x(eCParameterSpec) : ((kb) BouncyCastleProvider.CONFIGURATION).a();
        ECParameterSpec eCParameterSpec2 = jCEECPublicKey.G;
        return krVarX.equals(eCParameterSpec2 != null ? t40.x(eCParameterSpec2) : ((kb) BouncyCastleProvider.CONFIGURATION).a());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.E;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        q91 q91Var;
        sx0 sx0Var;
        if (this.E.equals("ECGOST3410")) {
            t q91Var2 = this.I;
            if (q91Var2 == null) {
                ECParameterSpec eCParameterSpec = this.G;
                if (eCParameterSpec instanceof ir) {
                    q91Var2 = new u10(dr.e(((ir) eCParameterSpec).a), rj.d);
                } else {
                    xq xqVarS = t40.s(eCParameterSpec.getCurve());
                    q91Var2 = new q91(new s91(xqVarS, new u91(t40.u(xqVarS, this.G.getGenerator()), this.H), this.G.getOrder(), BigInteger.valueOf(this.G.getCofactor()), this.G.getCurve().getSeed()));
                }
            }
            or orVar = this.F;
            orVar.b();
            BigInteger bigIntegerH1 = orVar.b.h1();
            BigInteger bigIntegerH12 = this.F.e().h1();
            byte[] bArr = new byte[64];
            a(bArr, 0, bigIntegerH1);
            a(bArr, 32, bigIntegerH12);
            try {
                sx0Var = new sx0(new l4(rj.c, q91Var2), new tk(bArr));
            } catch (IOException unused) {
                return null;
            }
        } else {
            ECParameterSpec eCParameterSpec2 = this.G;
            if (eCParameterSpec2 instanceof ir) {
                d0 d0VarG = qm.G(((ir) eCParameterSpec2).a);
                if (d0VarG == null) {
                    d0VarG = new d0(((ir) this.G).a);
                }
                q91Var = new q91(d0VarG);
            } else if (eCParameterSpec2 == null) {
                q91Var = new q91();
            } else {
                xq xqVarS2 = t40.s(eCParameterSpec2.getCurve());
                q91Var = new q91(new s91(xqVarS2, new u91(t40.u(xqVarS2, this.G.getGenerator()), this.H), this.G.getOrder(), BigInteger.valueOf(this.G.getCofactor()), this.G.getCurve().getSeed()));
            }
            sx0Var = new sx0(new l4(w91.e, q91Var), getQ().h(this.H));
        }
        return l21.M(sx0Var);
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // org.bouncycastle.jce.interfaces.ECPublicKey
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

    @Override // org.bouncycastle.jce.interfaces.ECPublicKey
    public or getQ() {
        return this.G == null ? this.F.o().c() : this.F;
    }

    @Override // java.security.interfaces.ECPublicKey
    public ECPoint getW() {
        return t40.w(this.F);
    }

    public int hashCode() {
        int iHashCode = engineGetQ().hashCode();
        ECParameterSpec eCParameterSpec = this.G;
        return iHashCode ^ (eCParameterSpec != null ? t40.x(eCParameterSpec) : ((kb) BouncyCastleProvider.CONFIGURATION).a()).hashCode();
    }

    public void setPointFormat(String str) {
        this.H = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("EC Public Key");
        String str = jx0.a;
        stringBuffer.append(str);
        stringBuffer.append("            X: ");
        or orVar = this.F;
        orVar.b();
        stringBuffer.append(orVar.b.h1().toString(16));
        stringBuffer.append(str);
        stringBuffer.append("            Y: ");
        stringBuffer.append(this.F.e().h1().toString(16));
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public JCEECPublicKey(String str, sr srVar, kr krVar) {
        this.E = "EC";
        yq yqVar = (yq) srVar.F;
        this.E = str;
        this.F = srVar.G;
        if (krVar != null) {
            this.G = t40.y(t40.t(krVar.a), krVar);
            return;
        }
        z91.l(yqVar.g);
        this.G = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
    }

    public JCEECPublicKey(String str, sr srVar, ECParameterSpec eCParameterSpec) {
        this.E = "EC";
        yq yqVar = (yq) srVar.F;
        this.E = str;
        this.F = srVar.G;
        if (eCParameterSpec != null) {
            this.G = eCParameterSpec;
            return;
        }
        z91.l(yqVar.g);
        this.G = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
    }

    public JCEECPublicKey(String str, tr trVar) {
        this.E = str;
        throw null;
    }

    public JCEECPublicKey(String str, ECPublicKeySpec eCPublicKeySpec) {
        this.E = str;
        ECParameterSpec params = eCPublicKeySpec.getParams();
        this.G = params;
        this.F = t40.v(params, eCPublicKeySpec.getW());
    }

    public JCEECPublicKey(String str, JCEECPublicKey jCEECPublicKey) {
        this.E = str;
        this.F = jCEECPublicKey.F;
        this.G = jCEECPublicKey.G;
        this.H = jCEECPublicKey.H;
        this.I = jCEECPublicKey.I;
    }

    public JCEECPublicKey(ECPublicKey eCPublicKey) {
        this.E = "EC";
        this.E = eCPublicKey.getAlgorithm();
        ECParameterSpec params = eCPublicKey.getParams();
        this.G = params;
        this.F = t40.v(params, eCPublicKey.getW());
    }
}
