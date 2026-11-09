package defpackage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class k7 extends jo0 {
    public static final ReentrantLock h;
    public static final Condition i;
    public static final long j;
    public static final long k;
    public static k7 l;
    public boolean e;
    public k7 f;
    public long g;

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        h = reentrantLock;
        Condition conditionNewCondition = reentrantLock.newCondition();
        oy.i(conditionNewCondition, "newCondition(...)");
        i = conditionNewCondition;
        long millis = TimeUnit.SECONDS.toMillis(60L);
        j = millis;
        k = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    public final void h() {
        k7 k7Var;
        long j2 = this.c;
        boolean z = this.a;
        if (j2 != 0 || z) {
            ReentrantLock reentrantLock = h;
            reentrantLock.lock();
            try {
                if (this.e) {
                    throw new IllegalStateException("Unbalanced enter/exit");
                }
                this.e = true;
                if (l == null) {
                    l = new k7();
                    h7 h7Var = new h7("Okio Watchdog");
                    h7Var.setDaemon(true);
                    h7Var.start();
                }
                long jNanoTime = System.nanoTime();
                if (j2 != 0 && z) {
                    this.g = Math.min(j2, c() - jNanoTime) + jNanoTime;
                } else if (j2 != 0) {
                    this.g = j2 + jNanoTime;
                } else {
                    if (!z) {
                        throw new AssertionError();
                    }
                    this.g = c();
                }
                long j3 = this.g - jNanoTime;
                k7 k7Var2 = l;
                oy.g(k7Var2);
                while (true) {
                    k7Var = k7Var2.f;
                    if (k7Var == null || j3 < k7Var.g - jNanoTime) {
                        break;
                    } else {
                        k7Var2 = k7Var;
                    }
                }
                this.f = k7Var;
                k7Var2.f = this;
                if (k7Var2 == l) {
                    i.signal();
                }
                reentrantLock.unlock();
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
    }

    public final boolean i() {
        ReentrantLock reentrantLock = h;
        reentrantLock.lock();
        try {
            if (!this.e) {
                return false;
            }
            this.e = false;
            k7 k7Var = l;
            while (k7Var != null) {
                k7 k7Var2 = k7Var.f;
                if (k7Var2 == this) {
                    k7Var.f = this.f;
                    this.f = null;
                    return false;
                }
                k7Var = k7Var2;
            }
            reentrantLock.unlock();
            return true;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void j() {
    }
}
