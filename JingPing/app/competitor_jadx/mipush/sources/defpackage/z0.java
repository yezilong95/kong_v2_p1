package defpackage;

import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public abstract class z0 extends rm0 {
    public abstract Random a();

    @Override // defpackage.rm0
    public final int nextBits(int i) {
        return ((-i) >> 31) & (a().nextInt() >>> (32 - i));
    }

    @Override // defpackage.rm0
    public final boolean nextBoolean() {
        return a().nextBoolean();
    }

    @Override // defpackage.rm0
    public final byte[] nextBytes(byte[] bArr) {
        t40.n(bArr, "array");
        a().nextBytes(bArr);
        return bArr;
    }

    @Override // defpackage.rm0
    public final double nextDouble() {
        return a().nextDouble();
    }

    @Override // defpackage.rm0
    public final float nextFloat() {
        return a().nextFloat();
    }

    @Override // defpackage.rm0
    public final int nextInt() {
        return a().nextInt();
    }

    @Override // defpackage.rm0
    public final long nextLong() {
        return a().nextLong();
    }

    @Override // defpackage.rm0
    public final int nextInt(int i) {
        return a().nextInt(i);
    }
}
