package defpackage;

import java.util.concurrent.Executor;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public final class fj extends vo implements Executor {
    public static final fj d = new fj();
    public static final qh e;

    static {
        cq0 cq0Var = cq0.d;
        int i = jm0.a;
        if (64 >= i) {
            i = 64;
        }
        int iI0 = jq0.i0("kotlinx.coroutines.io.parallelism", i, 12);
        cq0Var.getClass();
        if (iI0 < 1) {
            throw new IllegalArgumentException(hk.g(iI0, "Expected positive parallelism level, but got ").toString());
        }
        qh y10Var = cq0Var;
        if (iI0 < bn0.d) {
            if (iI0 < 1) {
                throw new IllegalArgumentException(hk.g(iI0, "Expected positive parallelism level, but got ").toString());
            }
            y10Var = new y10(cq0Var, iI0);
        }
        e = y10Var;
    }

    @Override // defpackage.qh
    public final void C(nh nhVar, Runnable runnable) {
        e.C(nhVar, runnable);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO");
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        t(EmptyCoroutineContext.INSTANCE, runnable);
    }

    @Override // defpackage.qh
    public final void t(nh nhVar, Runnable runnable) {
        e.t(nhVar, runnable);
    }

    @Override // defpackage.qh
    public final String toString() {
        return "Dispatchers.IO";
    }
}
