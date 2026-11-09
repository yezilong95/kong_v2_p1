package defpackage;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class mz0 {
    public static final String a;
    public static final long b;
    public static final int c;
    public static final int d;
    public static final long e;
    public static final is f;
    public static final iq0 g;
    public static final iq0 h;

    static {
        String property;
        int i = yy0.a;
        try {
            property = System.getProperty("kotlinx.coroutines.scheduler.default.name");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            property = "DefaultDispatcher";
        }
        a = property;
        b = fa0.I0("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 1L, Long.MAX_VALUE);
        int i2 = yy0.a;
        if (i2 < 2) {
            i2 = 2;
        }
        c = fa0.J0(i2, 8, "kotlinx.coroutines.scheduler.core.pool.size");
        d = fa0.J0(2097150, 4, "kotlinx.coroutines.scheduler.max.pool.size");
        e = TimeUnit.SECONDS.toNanos(fa0.I0("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 1L, Long.MAX_VALUE));
        f = is.J;
        g = new iq0(0, 2);
        h = new iq0(1, 2);
    }
}
