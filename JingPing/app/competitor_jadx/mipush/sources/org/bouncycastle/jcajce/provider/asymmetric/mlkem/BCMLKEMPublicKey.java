package org.bouncycastle.jcajce.provider.asymmetric.mlkem;

import defpackage.c30;
import defpackage.gm0;
import defpackage.gq0;
import defpackage.jx0;
import defpackage.lb0;
import defpackage.mb0;
import defpackage.ob0;
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
import org.bouncycastle.jcajce.interfaces.MLKEMPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCMLKEMPublicKey implements MLKEMPublicKey {
    private static final long serialVersionUID = 1;
    public transient ob0 E;
    public transient String F;

    public BCMLKEMPublicKey(ob0 ob0Var) {
        this.E = ob0Var;
        this.F = jx0.e(lb0.a(((mb0) ob0Var.F).E).a);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        ob0 ob0Var = (ob0) gm0.a(sx0.g((byte[]) objectInputStream.readObject()));
        this.E = ob0Var;
        this.F = jx0.e(lb0.a(((mb0) ob0Var.F).E).a);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BCMLKEMPublicKey) {
            return Arrays.equals(getEncoded(), ((BCMLKEMPublicKey) obj).getEncoded());
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

    @Override // org.bouncycastle.jcajce.interfaces.MLKEMKey
    public lb0 getParameterSpec() {
        return lb0.a(((mb0) this.E.F).E);
    }

    @Override // org.bouncycastle.jcajce.interfaces.MLKEMPublicKey
    public byte[] getPublicData() {
        ob0 ob0Var = this.E;
        return z91.m(ob0Var.G, ob0Var.H);
    }

    public int hashCode() {
        return z91.E(getEncoded());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = jx0.a;
        ob0 ob0Var = this.E;
        byte[] bArrM = z91.m(ob0Var.G, ob0Var.H);
        sb.append(getAlgorithm());
        sb.append(" Public Key [");
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

    public BCMLKEMPublicKey(sx0 sx0Var) throws IOException {
        ob0 ob0Var = (ob0) gm0.a(sx0Var);
        this.E = ob0Var;
        this.F = jx0.e(lb0.a(((mb0) ob0Var.F).E).a);
    }
}
