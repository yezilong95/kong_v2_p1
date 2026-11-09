package org.bouncycastle.jcajce.provider.asymmetric.slhdsa;

import defpackage.c30;
import defpackage.d30;
import defpackage.gq0;
import defpackage.hq0;
import defpackage.jq0;
import defpackage.jx0;
import defpackage.k0;
import defpackage.kq0;
import defpackage.lq0;
import defpackage.ma0;
import defpackage.o80;
import defpackage.ol0;
import defpackage.t40;
import defpackage.z91;
import defpackage.zq0;
import io.nats.client.support.NatsJetStreamConstants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.a;
import org.bouncycastle.crypto.digests.SHAKENativeDigest;
import org.bouncycastle.jcajce.interfaces.SLHDSAPrivateKey;
import org.bouncycastle.jcajce.interfaces.SLHDSAPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCSLHDSAPrivateKey implements SLHDSAPrivateKey {
    private static final long serialVersionUID = 1;
    public transient kq0 E;
    public transient k0 F;

    public BCSLHDSAPrivateKey(ol0 ol0Var) throws IOException {
        this.F = ol0Var.H;
        this.E = (kq0) ma0.s(ol0Var);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        ol0 ol0VarG = ol0.g((byte[]) objectInputStream.readObject());
        this.F = ol0VarG.H;
        this.E = (kq0) ma0.s(ol0VarG);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BCSLHDSAPrivateKey) {
            return Arrays.equals(this.E.getEncoded(), ((BCSLHDSAPrivateKey) obj).E.getEncoded());
        }
        return false;
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return "SLH-DSA-".concat(jx0.e(((jq0) this.E.F).a));
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return o80.s(this.E, this.F).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.bouncycastle.jcajce.interfaces.SLHDSAKey
    public hq0 getParameterSpec() {
        return hq0.a(((jq0) this.E.F).a);
    }

    @Override // org.bouncycastle.jcajce.interfaces.SLHDSAPrivateKey
    public SLHDSAPublicKey getPublicKey() {
        kq0 kq0Var = this.E;
        jq0 jq0Var = (jq0) kq0Var.F;
        d30 d30Var = kq0Var.H;
        return new BCSLHDSAPublicKey(new lq0(jq0Var, z91.m(d30Var.a, d30Var.b)));
    }

    public int hashCode() {
        return z91.E(this.E.getEncoded());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = jx0.a;
        d30 d30Var = this.E.H;
        byte[] bArrM = z91.m(d30Var.a, d30Var.b);
        sb.append(getAlgorithm());
        sb.append(" Private Key [");
        zq0 sHAKENativeDigest = a.c("SHAKE") ? new SHAKENativeDigest(NatsJetStreamConstants.MAX_PULL_SIZE, CryptoServicePurpose.ANY) : new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
        sHAKENativeDigest.e(bArrM, 0, bArrM.length);
        byte[] bArr = new byte[20];
        sHAKENativeDigest.h(bArr, 0, 20);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i != 20; i++) {
            if (i > 0) {
                stringBuffer.append(":");
            }
            char[] cArr = t40.e;
            stringBuffer.append(cArr[(bArr[i] >>> 4) & 15]);
            stringBuffer.append(cArr[bArr[i] & 15]);
        }
        sb.append(stringBuffer.toString());
        sb.append("]");
        sb.append(str);
        sb.append("    public data: ");
        sb.append(jx0.a(c30.c(bArrM, 0, bArrM.length)));
        sb.append(str);
        return sb.toString();
    }

    public BCSLHDSAPrivateKey(kq0 kq0Var) {
        this.E = kq0Var;
    }
}
