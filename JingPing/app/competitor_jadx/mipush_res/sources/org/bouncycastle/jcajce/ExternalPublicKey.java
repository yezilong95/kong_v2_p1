package org.bouncycastle.jcajce;

import defpackage.a20;
import defpackage.a9;
import defpackage.cv;
import defpackage.l4;
import defpackage.lk;
import defpackage.tk;
import defpackage.u;
import defpackage.ue0;
import defpackage.uk;
import defpackage.wk;
import defpackage.z10;
import defpackage.z91;
import defpackage.zn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ExternalPublicKey implements PublicKey {
    public final l4 E;
    public final byte[] F;

    public ExternalPublicKey(cv cvVar) {
        cvVar.getClass();
        throw null;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "ExternalKey";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            l4 l4Var = new l4(a9.m);
            l4 l4Var2 = this.E;
            byte[] bArr = this.F;
            a20 a20Var = new a20(null);
            byte[] bArrL = z91.l(bArr);
            u uVar = new u();
            uVar.c(a20Var);
            uVar.c(l4Var2);
            uVar.c(new tk(bArrL));
            wk wkVar = new wk(uVar, 0);
            wkVar.H = -1;
            lk lkVar = new lk(wkVar.f(), 0);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            u uVar2 = new u(2);
            uVar2.c(l4Var);
            uVar2.c(lkVar);
            wk wkVar2 = new wk(uVar2, 0);
            wkVar2.H = -1;
            new uk(2, byteArrayOutputStream).K(wkVar2);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("unable to encode composite key: " + e.getMessage());
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public ExternalPublicKey(z10 z10Var, l4 l4Var, byte[] bArr) {
        this.E = l4Var;
        this.F = z91.l(bArr);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ExternalPublicKey(PublicKey publicKey, z10 z10Var, MessageDigest messageDigest) {
        String algorithm = messageDigest.getAlgorithm();
        HashMap map = ue0.a;
        if (map.containsKey(algorithm)) {
            this(z10Var, (l4) map.get(algorithm), messageDigest.digest(publicKey.getEncoded()));
            return;
        }
        throw new IllegalArgumentException(zn.v("unknown digest: ", algorithm));
    }
}
