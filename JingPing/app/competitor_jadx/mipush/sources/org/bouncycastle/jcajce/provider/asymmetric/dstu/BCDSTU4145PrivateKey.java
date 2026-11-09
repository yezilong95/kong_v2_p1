package org.bouncycastle.jcajce.provider.asymmetric.dstu;

import defpackage.d0;
import defpackage.dm;
import defpackage.em;
import defpackage.fm;
import defpackage.g0;
import defpackage.gm;
import defpackage.hr;
import defpackage.i6;
import defpackage.ir;
import defpackage.j0;
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
import defpackage.s91;
import defpackage.sx0;
import defpackage.t;
import defpackage.t40;
import defpackage.u21;
import defpackage.vq;
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
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class BCDSTU4145PrivateKey implements ECPrivateKey, org.bouncycastle.jce.interfaces.ECPrivateKey {
    static final long serialVersionUID = 7245981689601667138L;
    public final String E;
    public boolean F;
    public transient BigInteger G;
    public transient ECParameterSpec H;
    public transient p I;
    public transient i6 J;

    public BCDSTU4145PrivateKey(rr rrVar) {
        this.E = "DSTU4145";
        this.J = new i6(24);
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
        objectInputStream.defaultReadObject();
        ol0 ol0VarG = ol0.g(g0.l((byte[]) objectInputStream.readObject()));
        q91 q91VarG = q91.g(ol0VarG.F.F);
        g0 g0Var = q91VarG.E;
        if (g0Var instanceof d0) {
            d0 d0VarS = d0.s(g0Var);
            s91 s91VarF = qm.F(d0VarS);
            if (s91VarF == null) {
                yq yqVarA = fm.a(d0VarS);
                xq xqVar = yqVarA.f;
                z91.l(yqVarA.g);
                this.H = new ir(d0VarS.r(), t40.t(xqVar), t40.w(yqVarA.h), yqVarA.i, yqVarA.j);
            } else {
                z91.l(s91VarF.J);
                this.H = new ir(qm.v(d0VarS), t40.t(s91VarF.F), t40.w(s91VarF.G.g()), s91VarF.H, s91VarF.I);
            }
        } else if (g0Var instanceof z) {
            this.H = null;
        } else {
            j0 j0VarQ = j0.q(g0Var);
            if (j0VarQ.r(0) instanceof y) {
                s91 s91VarG = s91.g(q91VarG.E);
                xq xqVar2 = s91VarG.F;
                z91.l(s91VarG.J);
                this.H = new ECParameterSpec(t40.t(xqVar2), t40.w(s91VarG.G.g()), s91VarG.H, s91VarG.I.intValue());
            } else {
                gm gmVarG = gm.g(j0VarQ);
                d0 d0Var = gmVarG.E;
                if (d0Var != null) {
                    yq yqVarA2 = fm.a(d0Var);
                    String strR = d0Var.r();
                    xq xqVar3 = yqVarA2.f;
                    byte[] bArrL = z91.l(yqVarA2.g);
                    krVar = new hr(strR, xqVar3, yqVarA2.h, yqVarA2.i, yqVarA2.j, bArrL);
                } else {
                    em emVar = gmVarG.F;
                    byte[] bArrL2 = z91.l(emVar.H.E);
                    l4 l4Var = ol0VarG.F;
                    d0 d0Var2 = l4Var.E;
                    d0 d0Var3 = u21.a;
                    if (d0Var2.k(d0Var3)) {
                        b(bArrL2);
                    }
                    dm dmVar = emVar.F;
                    int i = dmVar.E;
                    BigInteger bigIntegerQ = emVar.G.q();
                    BigInteger bigInteger = new BigInteger(1, bArrL2);
                    vq vqVar = new vq(i, dmVar.F, dmVar.G, dmVar.H, bigIntegerQ, bigInteger, null, null);
                    byte[] bArrL3 = z91.l(emVar.J.E);
                    if (l4Var.E.k(d0Var3)) {
                        b(bArrL3);
                    }
                    krVar = new kr(vqVar, z91.s(vqVar, bArrL3), emVar.I.q());
                }
                this.H = new ECParameterSpec(t40.t(krVar.a), t40.w(krVar.c), krVar.d, krVar.e.intValue());
            }
        }
        g0 g0VarI = ol0VarG.i();
        if (g0VarI instanceof y) {
            this.G = y.o(g0VarI).q();
        } else {
            pr prVarG = pr.g(g0VarI);
            this.G = prVarG.h();
            this.I = prVarG.i();
        }
        this.J = new i6(24);
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
        if (!(obj instanceof BCDSTU4145PrivateKey)) {
            return false;
        }
        BCDSTU4145PrivateKey bCDSTU4145PrivateKey = (BCDSTU4145PrivateKey) obj;
        return getD().equals(bCDSTU4145PrivateKey.getD()) && a().equals(bCDSTU4145PrivateKey.a());
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
        return this.G;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00aa A[Catch: IOException -> 0x00c8, TRY_ENTER, TryCatch #0 {IOException -> 0x00c8, blocks: (B:16:0x009c, B:19:0x00aa, B:21:0x00c3, B:20:0x00b7), top: B:24:0x009c }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b7 A[Catch: IOException -> 0x00c8, TryCatch #0 {IOException -> 0x00c8, blocks: (B:16:0x009c, B:19:0x00aa, B:21:0x00c3, B:20:0x00b7), top: B:24:0x009c }] */
    @Override // java.security.Key
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] getEncoded() {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.dstu.BCDSTU4145PrivateKey.getEncoded():byte[]");
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
        return getD().hashCode() ^ a().hashCode();
    }

    public void setBagAttribute(d0 d0Var, t tVar) {
        this.J.N(d0Var, tVar);
    }

    public void setPointFormat(String str) {
        this.F = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public String toString() {
        return qm.b0(this.E, this.G, a());
    }

    public BCDSTU4145PrivateKey(String str, qr qrVar) {
        this.E = "DSTU4145";
        this.J = new i6(24);
        this.E = str;
        this.G = qrVar.G;
        this.H = null;
    }

    public BCDSTU4145PrivateKey(String str, qr qrVar, BCDSTU4145PublicKey bCDSTU4145PublicKey, kr krVar) {
        ECParameterSpec eCParameterSpec;
        p pVar;
        this.E = "DSTU4145";
        this.J = new i6(24);
        yq yqVar = (yq) qrVar.F;
        this.E = str;
        this.G = qrVar.G;
        if (krVar == null) {
            z91.l(yqVar.g);
            eCParameterSpec = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        } else {
            eCParameterSpec = new ECParameterSpec(t40.t(krVar.a), t40.w(krVar.c), krVar.d, krVar.e.intValue());
        }
        this.H = eCParameterSpec;
        try {
            pVar = sx0.g(g0.l(bCDSTU4145PublicKey.getEncoded())).F;
        } catch (IOException unused) {
            pVar = null;
        }
        this.I = pVar;
    }

    public BCDSTU4145PrivateKey(String str, qr qrVar, BCDSTU4145PublicKey bCDSTU4145PublicKey, ECParameterSpec eCParameterSpec) {
        p pVar;
        this.E = "DSTU4145";
        this.J = new i6(24);
        yq yqVar = (yq) qrVar.F;
        this.E = str;
        this.G = qrVar.G;
        if (eCParameterSpec == null) {
            z91.l(yqVar.g);
            this.H = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        } else {
            this.H = eCParameterSpec;
        }
        try {
            pVar = sx0.g(g0.l(bCDSTU4145PublicKey.getEncoded())).F;
        } catch (IOException unused) {
            pVar = null;
        }
        this.I = pVar;
    }

    public BCDSTU4145PrivateKey(ECPrivateKey eCPrivateKey) {
        this.E = "DSTU4145";
        this.J = new i6(24);
        this.G = eCPrivateKey.getS();
        this.E = eCPrivateKey.getAlgorithm();
        this.H = eCPrivateKey.getParams();
    }

    public BCDSTU4145PrivateKey(ECPrivateKeySpec eCPrivateKeySpec) {
        this.E = "DSTU4145";
        this.J = new i6(24);
        this.G = eCPrivateKeySpec.getS();
        this.H = eCPrivateKeySpec.getParams();
    }

    public BCDSTU4145PrivateKey(BCDSTU4145PrivateKey bCDSTU4145PrivateKey) {
        this.E = "DSTU4145";
        this.J = new i6(24);
        this.G = bCDSTU4145PrivateKey.G;
        this.H = bCDSTU4145PrivateKey.H;
        this.F = bCDSTU4145PrivateKey.F;
        this.J = bCDSTU4145PrivateKey.J;
        this.I = bCDSTU4145PrivateKey.I;
    }
}
