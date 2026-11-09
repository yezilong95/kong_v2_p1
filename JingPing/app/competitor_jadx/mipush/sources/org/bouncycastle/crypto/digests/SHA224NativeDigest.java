package org.bouncycastle.crypto.digests;

import defpackage.bq0;
import defpackage.fa0;
import defpackage.po;
import defpackage.vd0;

/* JADX INFO: loaded from: classes.dex */
class SHA224NativeDigest implements po, vd0 {
    public bq0 a;

    public static native void destroy(long j);

    public static native int doFinal(long j, byte[] bArr, int i);

    public static native int encodeFullState(long j, byte[] bArr, int i);

    public static native int getByteLength(long j);

    public static native int getDigestSize(long j);

    public static native long makeNative();

    public static native void reset(long j);

    public static native void restoreFullState(long j, byte[] bArr, int i);

    public static native void update(long j, byte b);

    public static native void update(long j, byte[] bArr, int i, int i2);

    @Override // defpackage.vd0
    public final vd0 a() {
        SHA224NativeDigest sHA224NativeDigest = new SHA224NativeDigest();
        sHA224NativeDigest.a = null;
        long jMakeNative = makeNative();
        sHA224NativeDigest.a = new bq0(jMakeNative, "SHA224", 0);
        reset(sHA224NativeDigest.a.a);
        fa0.R(sHA224NativeDigest);
        org.bouncycastle.crypto.a.a();
        bq0 bq0Var = this.a;
        byte[] bArr = new byte[encodeFullState(bq0Var.a, null, 0)];
        encodeFullState(bq0Var.a, bArr, 0);
        restoreFullState(jMakeNative, bArr, 0);
        return sHA224NativeDigest;
    }

    @Override // defpackage.po
    public final String b() {
        return "SHA-224";
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
        bq0 bq0Var = ((SHA224NativeDigest) vd0Var).a;
        byte[] bArr = new byte[encodeFullState(bq0Var.a, null, 0)];
        encodeFullState(bq0Var.a, bArr, 0);
        restoreFullState(j, bArr, 0);
    }

    public final String toString() {
        return "SHA224[Native]()";
    }
}
