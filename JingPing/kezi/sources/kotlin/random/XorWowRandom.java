package kotlin.random;

import defpackage.pc0;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class XorWowRandom extends pc0 implements Serializable {
    private static final long serialVersionUID = 0;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;

    public XorWowRandom(int i, int i2, int i3, int i4, int i5, int i6) {
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        this.h = i6;
        if ((i | i2 | i3 | i4 | i5) == 0) {
            throw new IllegalArgumentException("Initial state must have at least one non-zero element.");
        }
        for (int i7 = 0; i7 < 64; i7++) {
            nextInt();
        }
    }

    @Override // defpackage.pc0
    public int nextBits(int i) {
        return ((-i) >> 31) & (nextInt() >>> (32 - i));
    }

    @Override // defpackage.pc0
    public int nextInt() {
        int i = this.c;
        int i2 = i ^ (i >>> 2);
        this.c = this.d;
        this.d = this.e;
        this.e = this.f;
        int i3 = this.g;
        this.f = i3;
        int i4 = ((i2 ^ (i2 << 1)) ^ i3) ^ (i3 << 4);
        this.g = i4;
        int i5 = this.h + 362437;
        this.h = i5;
        return i4 + i5;
    }

    public XorWowRandom(int i, int i2) {
        this(i, i2, 0, 0, ~i, (i << 10) ^ (i2 >>> 4));
    }
}
