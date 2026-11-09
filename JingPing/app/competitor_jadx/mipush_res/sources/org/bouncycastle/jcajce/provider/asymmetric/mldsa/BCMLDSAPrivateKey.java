package org.bouncycastle.jcajce.provider.asymmetric.mldsa;

import defpackage.c30;
import defpackage.gb0;
import defpackage.gq0;
import defpackage.hb0;
import defpackage.ib0;
import defpackage.jb0;
import defpackage.jx0;
import defpackage.k0;
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
import org.bouncycastle.jcajce.interfaces.MLDSAPrivateKey;
import org.bouncycastle.jcajce.interfaces.MLDSAPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCMLDSAPrivateKey implements MLDSAPrivateKey {
    private static final long serialVersionUID = 1;
    public transient ib0 E;
    public transient String F;
    public transient byte[] G;
    public transient k0 H;

    public BCMLDSAPrivateKey(ib0 ib0Var) {
        this.E = ib0Var;
        this.F = jx0.e(gb0.a(((hb0) ib0Var.F).b).a);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        ol0 ol0VarG = ol0.g((byte[]) objectInputStream.readObject());
        ib0 ib0Var = (ib0) ma0.s(ol0VarG);
        this.H = ol0VarG.H;
        this.E = ib0Var;
        this.F = gb0.a(((hb0) ib0Var.F).b).a.toUpperCase();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BCMLDSAPrivateKey) {
            return Arrays.equals(this.E.getEncoded(), ((BCMLDSAPrivateKey) obj).E.getEncoded());
        }
        return false;
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return this.F;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        if (this.G == null) {
            ib0 ib0Var = this.E;
            k0 k0Var = this.H;
            if (!ib0Var.E) {
                throw new IllegalArgumentException("public key found");
            }
            byte[] bArrF = null;
            try {
                bArrF = o80.s(ib0Var, k0Var).f();
            } catch (Exception unused) {
            }
            this.G = bArrF;
        }
        return z91.l(this.G);
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.bouncycastle.jcajce.interfaces.MLDSAKey
    public gb0 getParameterSpec() {
        return gb0.a(((hb0) this.E.F).b);
    }

    @Override // org.bouncycastle.jcajce.interfaces.MLDSAPrivateKey
    public MLDSAPublicKey getPublicKey() {
        ib0 ib0Var = this.E;
        return new BCMLDSAPublicKey(new jb0((hb0) ib0Var.F, ib0Var.G, ib0Var.M));
    }

    public int hashCode() {
        return z91.E(this.E.getEncoded());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = jx0.a;
        ib0 ib0Var = this.E;
        byte[] bArrM = z91.m(ib0Var.G, ib0Var.M);
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

    public BCMLDSAPrivateKey(ol0 ol0Var) throws IOException {
        ib0 ib0Var = (ib0) ma0.s(ol0Var);
        this.H = ol0Var.H;
        this.E = ib0Var;
        this.F = gb0.a(((hb0) ib0Var.F).b).a.toUpperCase();
    }
}
