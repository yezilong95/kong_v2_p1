package defpackage;

import java.util.concurrent.Executor;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public final class ym extends su implements Executor {
    public static final ym G = new ym();
    public static final ej H;

    static {
        z21 z21Var = z21.G;
        int i = yy0.a;
        if (64 >= i) {
            i = 64;
        }
        int iJ0 = fa0.J0(i, 12, "kotlinx.coroutines.io.parallelism");
        z21Var.getClass();
        if (iJ0 < 1) {
            throw new IllegalArgumentException(zn.j(iJ0, "Expected positive parallelism level, but got ").toString());
        }
        ej b90Var = z21Var;
        if (iJ0 < mz0.d) {
            if (iJ0 < 1) {
                throw new IllegalArgumentException(zn.j(iJ0, "Expected positive parallelism level, but got ").toString());
            }
            b90Var = new b90(z21Var, iJ0);
        }
        H = b90Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO");
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        j(EmptyCoroutineContext.INSTANCE, runnable);
    }

    @Override // defpackage.ej
    public final void j(bj bjVar, Runnable runnable) {
        H.j(bjVar, runnable);
    }

    @Override // defpackage.ej
    public final void l(bj bjVar, Runnable runnable) {
        H.l(bjVar, runnable);
    }

    @Override // defpackage.ej
    public final String toString() {
        return "Dispatchers.IO";
    }
}
