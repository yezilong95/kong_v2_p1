package kotlin.random;

import defpackage.qm0;
import defpackage.rm0;
import defpackage.t40;
import defpackage.tm;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class Random$Default extends rm0 implements Serializable {
    public Random$Default(tm tmVar) {
    }

    private final Object writeReplace() {
        return qm0.E;
    }

    @Override // defpackage.rm0
    public int nextBits(int i) {
        return rm0.E.nextBits(i);
    }

    @Override // defpackage.rm0
    public boolean nextBoolean() {
        return rm0.E.nextBoolean();
    }

    @Override // defpackage.rm0
    public byte[] nextBytes(byte[] bArr) {
        t40.n(bArr, "array");
        return rm0.E.nextBytes(bArr);
    }

    @Override // defpackage.rm0
    public double nextDouble() {
        return rm0.E.nextDouble();
    }

    @Override // defpackage.rm0
    public float nextFloat() {
        return rm0.E.nextFloat();
    }

    @Override // defpackage.rm0
    public int nextInt() {
        return rm0.E.nextInt();
    }

    @Override // defpackage.rm0
    public long nextLong() {
        return rm0.E.nextLong();
    }

    @Override // defpackage.rm0
    public byte[] nextBytes(int i) {
        return rm0.E.nextBytes(i);
    }

    @Override // defpackage.rm0
    public double nextDouble(double d) {
        return rm0.E.nextDouble(d);
    }

    @Override // defpackage.rm0
    public int nextInt(int i) {
        return rm0.E.nextInt(i);
    }

    @Override // defpackage.rm0
    public long nextLong(long j) {
        return rm0.E.nextLong(j);
    }

    @Override // defpackage.rm0
    public byte[] nextBytes(byte[] bArr, int i, int i2) {
        t40.n(bArr, "array");
        return rm0.E.nextBytes(bArr, i, i2);
    }

    @Override // defpackage.rm0
    public double nextDouble(double d, double d2) {
        return rm0.E.nextDouble(d, d2);
    }

    @Override // defpackage.rm0
    public int nextInt(int i, int i2) {
        return rm0.E.nextInt(i, i2);
    }

    @Override // defpackage.rm0
    public long nextLong(long j, long j2) {
        return rm0.E.nextLong(j, j2);
    }
}
