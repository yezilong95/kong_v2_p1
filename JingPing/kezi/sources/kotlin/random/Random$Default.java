package kotlin.random;

import defpackage.aj;
import defpackage.oc0;
import defpackage.oy;
import defpackage.pc0;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class Random$Default extends pc0 implements Serializable {
    public Random$Default(aj ajVar) {
    }

    private final Object writeReplace() {
        return oc0.b;
    }

    @Override // defpackage.pc0
    public int nextBits(int i) {
        return pc0.b.nextBits(i);
    }

    @Override // defpackage.pc0
    public boolean nextBoolean() {
        return pc0.b.nextBoolean();
    }

    @Override // defpackage.pc0
    public byte[] nextBytes(byte[] bArr) {
        oy.j(bArr, "array");
        return pc0.b.nextBytes(bArr);
    }

    @Override // defpackage.pc0
    public double nextDouble() {
        return pc0.b.nextDouble();
    }

    @Override // defpackage.pc0
    public float nextFloat() {
        return pc0.b.nextFloat();
    }

    @Override // defpackage.pc0
    public int nextInt() {
        return pc0.b.nextInt();
    }

    @Override // defpackage.pc0
    public long nextLong() {
        return pc0.b.nextLong();
    }

    @Override // defpackage.pc0
    public byte[] nextBytes(int i) {
        return pc0.b.nextBytes(i);
    }

    @Override // defpackage.pc0
    public double nextDouble(double d) {
        return pc0.b.nextDouble(d);
    }

    @Override // defpackage.pc0
    public int nextInt(int i) {
        return pc0.b.nextInt(i);
    }

    @Override // defpackage.pc0
    public long nextLong(long j) {
        return pc0.b.nextLong(j);
    }

    @Override // defpackage.pc0
    public byte[] nextBytes(byte[] bArr, int i, int i2) {
        oy.j(bArr, "array");
        return pc0.b.nextBytes(bArr, i, i2);
    }

    @Override // defpackage.pc0
    public double nextDouble(double d, double d2) {
        return pc0.b.nextDouble(d, d2);
    }

    @Override // defpackage.pc0
    public int nextInt(int i, int i2) {
        return pc0.b.nextInt(i, i2);
    }

    @Override // defpackage.pc0
    public long nextLong(long j, long j2) {
        return pc0.b.nextLong(j, j2);
    }
}
