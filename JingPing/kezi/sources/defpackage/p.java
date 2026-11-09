package defpackage;

import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public abstract class p extends pc0 {
    public abstract Random a();

    @Override // defpackage.pc0
    public final int nextBits(int i) {
        return ((-i) >> 31) & (a().nextInt() >>> (32 - i));
    }

    @Override // defpackage.pc0
    public final boolean nextBoolean() {
        return a().nextBoolean();
    }

    @Override // defpackage.pc0
    public final byte[] nextBytes(byte[] bArr) {
        oy.j(bArr, "array");
        a().nextBytes(bArr);
        return bArr;
    }

    @Override // defpackage.pc0
    public final double nextDouble() {
        return a().nextDouble();
    }

    @Override // defpackage.pc0
    public final float nextFloat() {
        return a().nextFloat();
    }

    @Override // defpackage.pc0
    public final int nextInt() {
        return a().nextInt();
    }

    @Override // defpackage.pc0
    public final long nextLong() {
        return a().nextLong();
    }

    @Override // defpackage.pc0
    public final int nextInt(int i) {
        return a().nextInt(i);
    }
}
