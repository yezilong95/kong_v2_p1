package org.bouncycastle.crypto.digests;

import defpackage.bq0;
import defpackage.fa0;
import defpackage.po;
import defpackage.vd0;
import io.nats.client.support.NatsJetStreamConstants;

/* JADX INFO: loaded from: classes.dex */
public class SHA3NativeDigest implements po, vd0 {
    public bq0 a;
    public int b;

    public static native void destroy(long j);

    public static native int doFinal(long j, byte[] bArr, int i);

    public static native int encodeFullState(long j, byte[] bArr, int i);

    public static native int getByteLength(long j);

    public static native int getDigestSize(long j);

    public static native long makeNative(int i);

    public static native void reset(long j);

    public static native void restoreFullState(long j, byte[] bArr, int i);

    public static native void update(long j, byte b);

    public static native void update(long j, byte[] bArr, int i, int i2);

    @Override // defpackage.vd0
    public final vd0 a() {
        SHA3NativeDigest sHA3NativeDigest = new SHA3NativeDigest();
        sHA3NativeDigest.a = null;
        if (!org.bouncycastle.crypto.a.c("SHA3")) {
            throw new IllegalStateException("no native SHA3 support");
        }
        sHA3NativeDigest.b = NatsJetStreamConstants.MAX_PULL_SIZE;
        long jMakeNative = makeNative(NatsJetStreamConstants.MAX_PULL_SIZE);
        sHA3NativeDigest.a = new bq0(jMakeNative, "SHA3", 3);
        sHA3NativeDigest.i();
        fa0.R(sHA3NativeDigest);
        org.bouncycastle.crypto.a.a();
        bq0 bq0Var = this.a;
        byte[] bArr = new byte[encodeFullState(bq0Var.a, null, 0)];
        encodeFullState(bq0Var.a, bArr, 0);
        restoreFullState(jMakeNative, bArr, 0);
        return sHA3NativeDigest;
    }

    @Override // defpackage.po
    public final String b() {
        return "SHA3-" + this.b;
    }

    @Override // defpackage.po
    public final int c() {
        return getDigestSize(this.a.a);
    }

    @Override // defpackage.po
    public final int d(int i, byte[] bArr) {
        int iDoFinal = doFinal(this.a.a, bArr, i);
        i();
        return iDoFinal;
    }

    @Override // defpackage.po
    public final void e(byte[] bArr, int i, int i2) {
        update(this.a.a, bArr, i, i2);
    }

    @Override // defpackage.po
    public final void f(byte b) {
        update(this.a.a, b);
    }

    @Override // defpackage.vd0
    public final void g(vd0 vd0Var) {
        long j = this.a.a;
        bq0 bq0Var = ((SHA3NativeDigest) vd0Var).a;
        byte[] bArr = new byte[encodeFullState(bq0Var.a, null, 0)];
        encodeFullState(bq0Var.a, bArr, 0);
        restoreFullState(j, bArr, 0);
    }

    public final void i() {
        reset(this.a.a);
    }

    public final String toString() {
        return "SHA3[Native]()";
    }
}
