package defpackage;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* JADX INFO: loaded from: classes.dex */
public final class xk0 extends z0 {
    @Override // defpackage.z0
    public final Random a() {
        ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
        t40.m(threadLocalRandomCurrent, "current(...)");
        return threadLocalRandomCurrent;
    }

    @Override // defpackage.rm0
    public final double nextDouble(double d) {
        return ThreadLocalRandom.current().nextDouble(d);
    }

    @Override // defpackage.rm0
    public final int nextInt(int i, int i2) {
        return ThreadLocalRandom.current().nextInt(i, i2);
    }

    @Override // defpackage.rm0
    public final long nextLong(long j) {
        return ThreadLocalRandom.current().nextLong(j);
    }

    @Override // defpackage.rm0
    public final long nextLong(long j, long j2) {
        return ThreadLocalRandom.current().nextLong(j, j2);
    }
}
