package defpackage;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* JADX INFO: loaded from: classes.dex */
public final class tb0 extends p {
    @Override // defpackage.p
    public final Random a() {
        ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
        oy.i(threadLocalRandomCurrent, "current(...)");
        return threadLocalRandomCurrent;
    }

    @Override // defpackage.pc0
    public final double nextDouble(double d) {
        return ThreadLocalRandom.current().nextDouble(d);
    }

    @Override // defpackage.pc0
    public final int nextInt(int i, int i2) {
        return ThreadLocalRandom.current().nextInt(i, i2);
    }

    @Override // defpackage.pc0
    public final long nextLong(long j) {
        return ThreadLocalRandom.current().nextLong(j);
    }

    @Override // defpackage.pc0
    public final long nextLong(long j, long j2) {
        return ThreadLocalRandom.current().nextLong(j, j2);
    }
}
