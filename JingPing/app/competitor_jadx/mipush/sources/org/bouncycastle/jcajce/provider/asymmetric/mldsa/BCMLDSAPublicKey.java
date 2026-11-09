package org.bouncycastle.jcajce.provider.asymmetric.mldsa;

import defpackage.c30;
import defpackage.gb0;
import defpackage.gm0;
import defpackage.gq0;
import defpackage.hb0;
import defpackage.jb0;
import defpackage.jx0;
import defpackage.s90;
import defpackage.sx0;
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
import org.bouncycastle.jcajce.interfaces.MLDSAPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCMLDSAPublicKey implements MLDSAPublicKey {
    private static final long serialVersionUID = 1;
    public transient jb0 E;
    public transient String F;

    public BCMLDSAPublicKey(jb0 jb0Var) {
        this.E = jb0Var;
        this.F = jx0.e(gb0.a(((hb0) jb0Var.F).b).a);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        jb0 jb0Var = (jb0) gm0.a(sx0.g((byte[]) objectInputStream.readObject()));
        this.E = jb0Var;
        this.F = jx0.e(gb0.a(((hb0) jb0Var.F).b).a);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BCMLDSAPublicKey) {
            return Arrays.equals(this.E.getEncoded(), ((BCMLDSAPublicKey) obj).E.getEncoded());
        }
        return false;
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return this.F;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return s90.s(this.E).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // org.bouncycastle.jcajce.interfaces.MLDSAKey
    public gb0 getParameterSpec() {
        return gb0.a(((hb0) this.E.F).b);
    }

    @Override // org.bouncycastle.jcajce.interfaces.MLDSAPublicKey
    public byte[] getPublicData() {
        return this.E.getEncoded();
    }

    public int hashCode() {
        return z91.E(this.E.getEncoded());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = jx0.a;
        byte[] encoded = this.E.getEncoded();
        sb.append(getAlgorithm());
        sb.append(" Public Key [");
        zq0 sHAKENativeDigest = a.c("SHAKE") ? new SHAKENativeDigest(NatsJetStreamConstants.MAX_PULL_SIZE, CryptoServicePurpose.ANY) : new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
        sHAKENativeDigest.e(encoded, 0, encoded.length);
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
        sb.append(jx0.a(c30.c(encoded, 0, encoded.length)));
        sb.append(str);
        return sb.toString();
    }

    public BCMLDSAPublicKey(sx0 sx0Var) throws IOException {
        jb0 jb0Var = (jb0) gm0.a(sx0Var);
        this.E = jb0Var;
        this.F = jx0.e(gb0.a(((hb0) jb0Var.F).b).a);
    }
}
