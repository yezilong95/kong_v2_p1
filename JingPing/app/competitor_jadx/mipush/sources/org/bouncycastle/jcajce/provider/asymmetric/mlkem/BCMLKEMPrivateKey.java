package org.bouncycastle.jcajce.provider.asymmetric.mlkem;

import defpackage.c30;
import defpackage.gq0;
import defpackage.jx0;
import defpackage.k0;
import defpackage.lb0;
import defpackage.ma0;
import defpackage.mb0;
import defpackage.nb0;
import defpackage.o80;
import defpackage.ob0;
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
import org.bouncycastle.jcajce.interfaces.MLKEMPrivateKey;
import org.bouncycastle.jcajce.interfaces.MLKEMPublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCMLKEMPrivateKey implements MLKEMPrivateKey {
    private static final long serialVersionUID = 1;
    public transient nb0 E;
    public transient String F;
    public transient k0 G;

    public BCMLKEMPrivateKey(nb0 nb0Var) {
        this.E = nb0Var;
        this.F = jx0.e(((mb0) nb0Var.F).E);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        ol0 ol0VarG = ol0.g((byte[]) objectInputStream.readObject());
        this.G = ol0VarG.H;
        nb0 nb0Var = (nb0) ma0.s(ol0VarG);
        this.E = nb0Var;
        this.F = jx0.e(lb0.a(((mb0) nb0Var.F).E).a);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BCMLKEMPrivateKey) {
            return Arrays.equals(this.E.getEncoded(), ((BCMLKEMPrivateKey) obj).E.getEncoded());
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
            return o80.s(this.E, this.G).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.bouncycastle.jcajce.interfaces.MLKEMKey
    public lb0 getParameterSpec() {
        return lb0.a(((mb0) this.E.F).E);
    }

    @Override // org.bouncycastle.jcajce.interfaces.MLKEMPrivateKey
    public MLKEMPublicKey getPublicKey() {
        nb0 nb0Var = this.E;
        return new BCMLKEMPublicKey(new ob0((mb0) nb0Var.F, nb0Var.J, nb0Var.K));
    }

    public int hashCode() {
        return z91.E(this.E.getEncoded());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = jx0.a;
        nb0 nb0Var = this.E;
        byte[] bArrM = z91.m(nb0Var.J, nb0Var.K);
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

    public BCMLKEMPrivateKey(ol0 ol0Var) throws IOException {
        this.G = ol0Var.H;
        nb0 nb0Var = (nb0) ma0.s(ol0Var);
        this.E = nb0Var;
        this.F = jx0.e(lb0.a(((mb0) nb0Var.F).E).a);
    }
}
