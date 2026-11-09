package org.bouncycastle.crypto.digests;

import defpackage.bq0;
import defpackage.fa0;
import defpackage.vd0;
import defpackage.zq0;
import kotlin.uuid.Uuid;
import org.bouncycastle.crypto.CryptoServicePurpose;

/* JADX INFO: loaded from: classes.dex */
public class SHAKENativeDigest implements zq0 {
    public final bq0 a;
    public final int b;

    public SHAKENativeDigest(int i, CryptoServicePurpose cryptoServicePurpose) {
        this.a = null;
        if (!org.bouncycastle.crypto.a.c("SHA3")) {
            throw new IllegalStateException("no native SHAKE support");
        }
        this.b = i;
        this.a = new bq0(makeNative(i), "SHAKE", 5);
        reset(this.a.a);
        fa0.R(this);
        org.bouncycastle.crypto.a.a();
    }

    public static native void destroy(long j);

    public static native int doFinal(long j, byte[] bArr, int i);

    public static native int doFinal(long j, byte[] bArr, int i, int i2);

    public static native int doOutput(long j, byte[] bArr, int i, int i2);

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
        SHAKENativeDigest sHAKENativeDigest = new SHAKENativeDigest(Uuid.SIZE_BITS, CryptoServicePurpose.ANY);
        bq0 bq0Var = this.a;
        byte[] bArr = new byte[encodeFullState(bq0Var.a, null, 0)];
        encodeFullState(bq0Var.a, bArr, 0);
        restoreFullState(sHAKENativeDigest.a.a, bArr, 0);
        return sHAKENativeDigest;
    }

    @Override // defpackage.po
    public final String b() {
        return "SHAKE" + this.b;
    }

    @Override // defpackage.po
    public final int c() {
        return getDigestSize(this.a.a);
    }

    @Override // defpackage.po
    public final int d(int i, byte[] bArr) {
        return doFinal(this.a.a, bArr, i);
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
        bq0 bq0Var = ((SHAKENativeDigest) vd0Var).a;
        byte[] bArr = new byte[encodeFullState(bq0Var.a, null, 0)];
        encodeFullState(bq0Var.a, bArr, 0);
        restoreFullState(j, bArr, 0);
    }

    @Override // defpackage.zq0
    public final int h(byte[] bArr, int i, int i2) {
        return doFinal(this.a.a, bArr, 0, i2);
    }

    public final String toString() {
        return "SHAKE[Native]()";
    }
}
