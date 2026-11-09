package org.bouncycastle.jcajce.provider.asymmetric.ec;

import defpackage.d0;
import defpackage.em0;
import defpackage.g0;
import defpackage.hr;
import defpackage.i6;
import defpackage.jr;
import defpackage.kb;
import defpackage.kr;
import defpackage.l4;
import defpackage.ol0;
import defpackage.p;
import defpackage.pr;
import defpackage.q91;
import defpackage.qm;
import defpackage.qr;
import defpackage.rr;
import defpackage.sx0;
import defpackage.t;
import defpackage.t40;
import defpackage.w91;
import defpackage.y;
import defpackage.yq;
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
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class BCECPrivateKey implements ECPrivateKey, org.bouncycastle.jce.interfaces.ECPrivateKey {
    static final long serialVersionUID = 994553197664784084L;
    public final String E;
    public boolean F;
    public transient BigInteger G;
    public transient ECParameterSpec H;
    public transient em0 I;
    public transient p J;
    public transient ol0 K;
    public transient byte[] L;
    public transient qr M;
    public transient i6 N;

    public BCECPrivateKey(String str, qr qrVar, em0 em0Var) {
        this.E = "EC";
        this.N = new i6(24);
        this.E = str;
        this.G = qrVar.G;
        this.H = null;
        this.I = em0Var;
        this.M = qrVar;
    }

    public static qr a(BCECPrivateKey bCECPrivateKey) {
        String str;
        kr parameters = bCECPrivateKey.getParameters();
        if (parameters == null) {
            parameters = ((kb) BouncyCastleProvider.CONFIGURATION).a();
        }
        return (!(bCECPrivateKey.getParameters() instanceof hr) || (str = ((hr) bCECPrivateKey.getParameters()).f) == null) ? new qr(bCECPrivateKey.getD(), new yq(parameters.a, parameters.c, parameters.d, parameters.e, parameters.b)) : new qr(bCECPrivateKey.getD(), new jr(z91.z(str), parameters.a, parameters.c, parameters.d, parameters.e, parameters.b));
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        byte[] bArr = (byte[]) objectInputStream.readObject();
        this.I = BouncyCastleProvider.CONFIGURATION;
        ol0 ol0VarG = ol0.g(g0.l(bArr));
        q91 q91VarG = q91.g(ol0VarG.F.F);
        this.H = t40.z(q91VarG, t40.Z(this.I, q91VarG));
        g0 g0VarI = ol0VarG.i();
        if (g0VarI instanceof y) {
            this.G = y.o(g0VarI).q();
        } else {
            pr prVarG = pr.g(g0VarI);
            this.G = prVarG.h();
            this.J = prVarG.i();
        }
        this.M = a(this);
        this.N = new i6(24);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public final ol0 b() {
        if (this.K == null) {
            q91 q91VarC0 = t40.c0(this.H, this.F);
            ECParameterSpec eCParameterSpec = this.H;
            int iH = eCParameterSpec == null ? qm.H(this.I, null, getS()) : qm.H(this.I, eCParameterSpec.getOrder(), getS());
            try {
                this.K = new ol0(new l4(w91.e, q91VarC0), this.J != null ? new pr(iH, getS(), this.J, q91VarC0) : new pr(iH, getS(), null, q91VarC0), null, null);
            } catch (IOException unused) {
                return null;
            }
        }
        return this.K;
    }

    public qr engineGetKeyParameters() {
        return this.M;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECPrivateKey) {
            ECPrivateKey eCPrivateKey = (ECPrivateKey) obj;
            ol0 ol0VarB = b();
            ol0 ol0VarB2 = eCPrivateKey instanceof BCECPrivateKey ? ((BCECPrivateKey) eCPrivateKey).b() : ol0.g(eCPrivateKey.getEncoded());
            if (ol0VarB != null && ol0VarB2 != null) {
                try {
                    return z91.q(getS().toByteArray(), eCPrivateKey.getS().toByteArray()) & z91.q(ol0VarB.F.getEncoded(), ol0VarB2.F.getEncoded());
                } catch (IOException unused) {
                }
            }
        }
        return false;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.E;
    }

    public t getBagAttribute(d0 d0Var) {
        return this.N.y(d0Var);
    }

    public Enumeration getBagAttributeKeys() {
        return ((Vector) this.N.G).elements();
    }

    @Override // org.bouncycastle.jce.interfaces.ECPrivateKey
    public BigInteger getD() {
        return this.G;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        if (this.L == null) {
            ol0 ol0VarB = b();
            if (ol0VarB == null) {
                return null;
            }
            try {
                this.L = ol0VarB.f();
            } catch (IOException unused) {
                return null;
            }
        }
        return z91.l(this.L);
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.bouncycastle.jce.interfaces.ECPrivateKey
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

    @Override // java.security.interfaces.ECPrivateKey
    public BigInteger getS() {
        return this.G;
    }

    public int hashCode() {
        int iHashCode = getD().hashCode();
        ECParameterSpec eCParameterSpec = this.H;
        return iHashCode ^ (eCParameterSpec != null ? t40.x(eCParameterSpec) : ((kb) this.I).a()).hashCode();
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        this.N.N(d0Var, tVar);
    }

    public void setPointFormat(String str) {
        this.F = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public String toString() {
        BigInteger bigInteger = this.G;
        ECParameterSpec eCParameterSpec = this.H;
        return qm.b0("EC", bigInteger, eCParameterSpec != null ? t40.x(eCParameterSpec) : ((kb) this.I).a());
    }

    public BCECPrivateKey(String str, qr qrVar, BCECPublicKey bCECPublicKey, kr krVar, em0 em0Var) {
        p pVar;
        this.E = "EC";
        this.N = new i6(24);
        this.E = str;
        this.G = qrVar.G;
        this.I = em0Var;
        this.M = qrVar;
        if (krVar == null) {
            yq yqVar = (yq) qrVar.F;
            z91.l(yqVar.g);
            this.H = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        } else {
            this.H = t40.y(t40.t(krVar.a), krVar);
        }
        try {
            try {
                pVar = sx0.g(g0.l(bCECPublicKey.getEncoded())).F;
            } catch (IOException unused) {
                pVar = null;
            }
            this.J = pVar;
        } catch (Exception unused2) {
            this.J = null;
        }
    }

    public BCECPrivateKey(String str, qr qrVar, BCECPublicKey bCECPublicKey, ECParameterSpec eCParameterSpec, em0 em0Var) {
        p pVar;
        this.E = "EC";
        this.N = new i6(24);
        this.E = str;
        this.G = qrVar.G;
        this.I = em0Var;
        this.M = qrVar;
        if (eCParameterSpec == null) {
            yq yqVar = (yq) qrVar.F;
            z91.l(yqVar.g);
            eCParameterSpec = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        }
        this.H = eCParameterSpec;
        try {
            pVar = sx0.g(g0.l(bCECPublicKey.getEncoded())).F;
        } catch (IOException unused) {
            pVar = null;
        }
        this.J = pVar;
    }

    public BCECPrivateKey(String str, rr rrVar, em0 em0Var) {
        this.E = "EC";
        this.N = new i6(24);
        this.E = str;
        throw null;
    }

    public BCECPrivateKey(String str, ECPrivateKeySpec eCPrivateKeySpec, em0 em0Var) {
        this.E = "EC";
        this.N = new i6(24);
        this.E = str;
        this.G = eCPrivateKeySpec.getS();
        this.H = eCPrivateKeySpec.getParams();
        this.I = em0Var;
        this.M = a(this);
    }

    public BCECPrivateKey(String str, BCECPrivateKey bCECPrivateKey) {
        this.E = "EC";
        this.N = new i6(24);
        this.E = str;
        this.G = bCECPrivateKey.G;
        this.H = bCECPrivateKey.H;
        this.F = bCECPrivateKey.F;
        this.N = bCECPrivateKey.N;
        this.J = bCECPrivateKey.J;
        this.I = bCECPrivateKey.I;
        this.M = bCECPrivateKey.M;
    }

    public BCECPrivateKey(ECPrivateKey eCPrivateKey, em0 em0Var) {
        this.E = "EC";
        this.N = new i6(24);
        this.G = eCPrivateKey.getS();
        this.E = eCPrivateKey.getAlgorithm();
        this.H = eCPrivateKey.getParams();
        this.I = em0Var;
        this.M = a(this);
    }
}
