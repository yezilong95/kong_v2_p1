package org.bouncycastle.jcajce.provider.asymmetric.ecgost12;

import defpackage.d0;
import defpackage.i6;
import defpackage.kb;
import defpackage.kr;
import defpackage.p;
import defpackage.qm;
import defpackage.qr;
import defpackage.rr;
import defpackage.sx0;
import defpackage.t;
import defpackage.t40;
import defpackage.u10;
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
public class BCECGOST3410_2012PrivateKey implements ECPrivateKey, org.bouncycastle.jce.interfaces.ECPrivateKey {
    static final long serialVersionUID = 7245981689601667138L;
    public final String E;
    public boolean F;
    public transient u10 G;
    public transient BigInteger H;
    public transient ECParameterSpec I;
    public transient p J;
    public transient i6 K;

    public BCECGOST3410_2012PrivateKey(rr rrVar) {
        this.E = "ECGOST3410-2012";
        this.K = new i6(24);
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0167  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void readObject(java.io.ObjectInputStream r11) throws java.lang.ClassNotFoundException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 385
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.ecgost12.BCECGOST3410_2012PrivateKey.readObject(java.io.ObjectInputStream):void");
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
        if (!(obj instanceof BCECGOST3410_2012PrivateKey)) {
            return false;
        }
        BCECGOST3410_2012PrivateKey bCECGOST3410_2012PrivateKey = (BCECGOST3410_2012PrivateKey) obj;
        return getD().equals(bCECGOST3410_2012PrivateKey.getD()) && a().equals(bCECGOST3410_2012PrivateKey.a());
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

    /* JADX WARN: Removed duplicated region for block: B:34:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ed  */
    @Override // java.security.Key
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] getEncoded() {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.ecgost12.BCECGOST3410_2012PrivateKey.getEncoded():byte[]");
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

    public BCECGOST3410_2012PrivateKey(String str, qr qrVar) {
        this.E = "ECGOST3410-2012";
        this.K = new i6(24);
        this.E = str;
        this.H = qrVar.G;
        this.I = null;
    }

    public BCECGOST3410_2012PrivateKey(String str, qr qrVar, BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey, kr krVar) {
        ECParameterSpec eCParameterSpec;
        this.E = "ECGOST3410-2012";
        this.K = new i6(24);
        yq yqVar = (yq) qrVar.F;
        this.E = str;
        this.H = qrVar.G;
        if (krVar == null) {
            z91.l(yqVar.g);
            eCParameterSpec = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        } else {
            eCParameterSpec = new ECParameterSpec(t40.t(krVar.a), t40.w(krVar.c), krVar.d, krVar.e.intValue());
        }
        this.I = eCParameterSpec;
        this.G = bCECGOST3410_2012PublicKey.getGostParams();
        this.J = sx0.g(bCECGOST3410_2012PublicKey.getEncoded()).F;
    }

    public BCECGOST3410_2012PrivateKey(String str, qr qrVar, BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey, ECParameterSpec eCParameterSpec) {
        this.E = "ECGOST3410-2012";
        this.K = new i6(24);
        yq yqVar = (yq) qrVar.F;
        this.E = str;
        this.H = qrVar.G;
        if (eCParameterSpec == null) {
            z91.l(yqVar.g);
            this.I = new ECParameterSpec(t40.t(yqVar.f), t40.w(yqVar.h), yqVar.i, yqVar.j.intValue());
        } else {
            this.I = eCParameterSpec;
        }
        this.G = bCECGOST3410_2012PublicKey.getGostParams();
        this.J = sx0.g(bCECGOST3410_2012PublicKey.getEncoded()).F;
    }

    public BCECGOST3410_2012PrivateKey(ECPrivateKey eCPrivateKey) {
        this.E = "ECGOST3410-2012";
        this.K = new i6(24);
        this.H = eCPrivateKey.getS();
        this.E = eCPrivateKey.getAlgorithm();
        this.I = eCPrivateKey.getParams();
    }

    public BCECGOST3410_2012PrivateKey(ECPrivateKeySpec eCPrivateKeySpec) {
        this.E = "ECGOST3410-2012";
        this.K = new i6(24);
        this.H = eCPrivateKeySpec.getS();
        this.I = eCPrivateKeySpec.getParams();
    }

    public BCECGOST3410_2012PrivateKey(BCECGOST3410_2012PrivateKey bCECGOST3410_2012PrivateKey) {
        this.E = "ECGOST3410-2012";
        this.K = new i6(24);
        this.H = bCECGOST3410_2012PrivateKey.H;
        this.I = bCECGOST3410_2012PrivateKey.I;
        this.F = bCECGOST3410_2012PrivateKey.F;
        this.K = bCECGOST3410_2012PrivateKey.K;
        this.J = bCECGOST3410_2012PrivateKey.J;
        this.G = bCECGOST3410_2012PrivateKey.G;
    }
}
