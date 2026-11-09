package org.bouncycastle.jcajce.provider.asymmetric.ec;

import defpackage.cm0;
import defpackage.d0;
import defpackage.e0;
import defpackage.em0;
import defpackage.g0;
import defpackage.jr;
import defpackage.kb;
import defpackage.kr;
import defpackage.l21;
import defpackage.l4;
import defpackage.or;
import defpackage.q91;
import defpackage.qm;
import defpackage.s91;
import defpackage.sr;
import defpackage.sx0;
import defpackage.t40;
import defpackage.tk;
import defpackage.tr;
import defpackage.w91;
import defpackage.xq;
import defpackage.yq;
import defpackage.z;
import defpackage.z91;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.util.Arrays;
import java.util.Collections;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class BCECPublicKey implements ECPublicKey, org.bouncycastle.jce.interfaces.ECPublicKey {
    static final long serialVersionUID = 2422789860422731812L;
    public final String E;
    public boolean F;
    public transient sr G;
    public transient ECParameterSpec H;
    public transient em0 I;
    public transient byte[] J;
    public transient boolean K;

    public BCECPublicKey(String str, sr srVar, kr krVar, em0 em0Var) {
        this.E = "EC";
        yq yqVar = (yq) srVar.F;
        this.E = str;
        if (krVar == null) {
            z91.l(yqVar.g);
            this.H = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        } else {
            this.H = t40.y(t40.t(krVar.a), krVar);
        }
        this.G = srVar;
        this.I = em0Var;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        yq yqVar;
        yq yqVar2;
        byte b;
        objectInputStream.defaultReadObject();
        byte[] bArr = (byte[]) objectInputStream.readObject();
        this.I = BouncyCastleProvider.CONFIGURATION;
        sx0 sx0VarG = sx0.g(g0.l(bArr));
        q91 q91VarG = q91.g(sx0VarG.E.F);
        xq xqVarZ = t40.Z(this.I, q91VarG);
        this.H = t40.z(q91VarG, xqVarZ);
        byte[] bArrP = sx0VarG.F.p();
        e0 tkVar = new tk(bArrP);
        if (bArrP[0] == 4 && bArrP[1] == bArrP.length - 2 && (((b = bArrP[2]) == 2 || b == 3) && xqVarZ.i() >= bArrP.length - 3)) {
            try {
                tkVar = (e0) g0.l(bArrP);
            } catch (IOException unused) {
                throw new IllegalArgumentException("error recovering public key");
            }
        }
        byte[] bArrL = z91.l(tkVar.E);
        new tk(bArrL);
        or orVarO = xqVarZ.e(bArrL).o();
        em0 em0Var = this.I;
        g0 g0Var = q91VarG.E;
        if (g0Var instanceof d0) {
            d0 d0VarS = d0.s(g0Var);
            s91 s91VarF = qm.F(d0VarS);
            if (s91VarF == null) {
                s91VarF = (s91) Collections.unmodifiableMap(((kb) em0Var).f).get(d0VarS);
            }
            jr jrVar = new jr(s91VarF.F, s91VarF.G.g(), s91VarF.H, s91VarF.I, z91.l(s91VarF.J));
            jrVar.k = d0VarS;
            yqVar2 = jrVar;
        } else {
            if (g0Var instanceof z) {
                kr krVarA = ((kb) em0Var).a();
                yqVar = new yq(krVarA.a, krVarA.c, krVarA.d, krVarA.e, krVarA.b);
            } else {
                s91 s91VarG = s91.g(g0Var);
                yqVar = new yq(s91VarG.F, s91VarG.G.g(), s91VarG.H, s91VarG.I, z91.l(s91VarG.J));
            }
            yqVar2 = yqVar;
        }
        this.G = new sr(orVarO, yqVar2);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public final kr a() {
        ECParameterSpec eCParameterSpec = this.H;
        return eCParameterSpec != null ? t40.x(eCParameterSpec) : ((kb) this.I).a();
    }

    public boolean equals(Object obj) {
        if (obj instanceof BCECPublicKey) {
            BCECPublicKey bCECPublicKey = (BCECPublicKey) obj;
            return this.G.G.d(bCECPublicKey.G.G) && a().equals(bCECPublicKey.a());
        }
        if (obj instanceof ECPublicKey) {
            return Arrays.equals(getEncoded(), ((ECPublicKey) obj).getEncoded());
        }
        return false;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.E;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        byte[] bArrM;
        boolean zC = cm0.c("org.bouncycastle.ec.enable_pc");
        if (this.J == null || this.K != zC) {
            boolean z = this.F || zC;
            try {
                bArrM = l21.M(new sx0(new l4(w91.e, t40.c0(this.H, z)), this.G.G.h(z)));
            } catch (Exception unused) {
                bArrM = null;
            }
            this.J = bArrM;
            this.K = zC;
        }
        return z91.l(this.J);
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

    @Override // java.security.interfaces.ECPublicKey
    public ECPoint getW() {
        return t40.w(this.G.G);
    }

    public int hashCode() {
        return this.G.G.hashCode() ^ a().hashCode();
    }

    public void setPointFormat(String str) {
        this.F = !"UNCOMPRESSED".equalsIgnoreCase(str);
        this.J = null;
    }

    public String toString() {
        return qm.c0("EC", this.G.G, a());
    }

    public BCECPublicKey(String str, sr srVar, em0 em0Var) {
        this.E = str;
        this.G = srVar;
        this.H = null;
        this.I = em0Var;
    }

    public BCECPublicKey(String str, sr srVar, ECParameterSpec eCParameterSpec, em0 em0Var) {
        this.E = "EC";
        yq yqVar = (yq) srVar.F;
        this.E = str;
        this.G = srVar;
        if (eCParameterSpec == null) {
            z91.l(yqVar.g);
            this.H = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        } else {
            this.H = eCParameterSpec;
        }
        this.I = em0Var;
    }

    public BCECPublicKey(String str, tr trVar, em0 em0Var) {
        this.E = str;
        throw null;
    }

    public BCECPublicKey(String str, ECPublicKeySpec eCPublicKeySpec, em0 em0Var) {
        this.E = str;
        ECParameterSpec params = eCPublicKeySpec.getParams();
        this.H = params;
        this.G = new sr(t40.v(params, eCPublicKeySpec.getW()), t40.b0(em0Var, eCPublicKeySpec.getParams()));
        this.I = em0Var;
    }

    public BCECPublicKey(String str, BCECPublicKey bCECPublicKey) {
        this.E = str;
        this.G = bCECPublicKey.G;
        this.H = bCECPublicKey.H;
        this.F = bCECPublicKey.F;
        this.I = bCECPublicKey.I;
    }

    public BCECPublicKey(ECPublicKey eCPublicKey, em0 em0Var) {
        this.E = "EC";
        this.E = eCPublicKey.getAlgorithm();
        ECParameterSpec params = eCPublicKey.getParams();
        this.H = params;
        this.G = new sr(t40.v(params, eCPublicKey.getW()), t40.b0(em0Var, eCPublicKey.getParams()));
        this.I = em0Var;
    }
}
