package kotlin.random;

import defpackage.rm0;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class XorWowRandom extends rm0 implements Serializable {
    private static final long serialVersionUID = 0;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;

    public XorWowRandom(int i, int i2, int i3, int i4, int i5, int i6) {
        this.F = i;
        this.G = i2;
        this.H = i3;
        this.I = i4;
        this.J = i5;
        this.K = i6;
        if ((i | i2 | i3 | i4 | i5) == 0) {
            throw new IllegalArgumentException("Initial state must have at least one non-zero element.");
        }
        for (int i7 = 0; i7 < 64; i7++) {
            nextInt();
        }
    }

    @Override // defpackage.rm0
    public int nextBits(int i) {
        return ((-i) >> 31) & (nextInt() >>> (32 - i));
    }

    @Override // defpackage.rm0
    public int nextInt() {
        int i = this.F;
        int i2 = i ^ (i >>> 2);
        this.F = this.G;
        this.G = this.H;
        this.H = this.I;
        int i3 = this.J;
        this.I = i3;
        int i4 = ((i2 ^ (i2 << 1)) ^ i3) ^ (i3 << 4);
        this.J = i4;
        int i5 = this.K + 362437;
        this.K = i5;
        return i4 + i5;
    }

    public XorWowRandom(int i, int i2) {
        this(i, i2, 0, 0, ~i, (i << 10) ^ (i2 >>> 4));
    }
}
