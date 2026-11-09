package org.bouncycastle.jcajce.provider.asymmetric.slhdsa;

import defpackage.c30;
import defpackage.gm0;
import defpackage.gq0;
import defpackage.hq0;
import defpackage.jq0;
import defpackage.jx0;
import defpackage.lq0;
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
import org.bouncycastle.jcajce.interfaces.SLHDSAPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCSLHDSAPublicKey implements SLHDSAPublicKey {
    private static final long serialVersionUID = 1;
    public transient lq0 E;

    public BCSLHDSAPublicKey(lq0 lq0Var) {
        this.E = lq0Var;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.E = (lq0) gm0.a(sx0.g((byte[]) objectInputStream.readObject()));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BCSLHDSAPublicKey) {
            return Arrays.equals(this.E.getEncoded(), ((BCSLHDSAPublicKey) obj).E.getEncoded());
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
            return s90.s(this.E).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // org.bouncycastle.jcajce.interfaces.SLHDSAKey
    public hq0 getParameterSpec() {
        return hq0.a(((jq0) this.E.F).a);
    }

    @Override // org.bouncycastle.jcajce.interfaces.SLHDSAPublicKey
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

    public BCSLHDSAPublicKey(sx0 sx0Var) throws IOException {
        this.E = (lq0) gm0.a(sx0Var);
    }
}
