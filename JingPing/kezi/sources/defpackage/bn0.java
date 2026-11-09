package defpackage;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class bn0 {
    public static final String a;
    public static final long b;
    public static final int c;
    public static final int d;
    public static final long e;
    public static final qm f;
    public static final xm0 g;
    public static final xm0 h;

    static {
        String property;
        int i = jm0.a;
        try {
            property = System.getProperty("kotlinx.coroutines.scheduler.default.name");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            property = "DefaultDispatcher";
        }
        a = property;
        b = jq0.h0("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 1L, Long.MAX_VALUE);
        int i2 = jm0.a;
        if (i2 < 2) {
            i2 = 2;
        }
        c = jq0.i0("kotlinx.coroutines.scheduler.core.pool.size", i2, 8);
        d = jq0.i0("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 4);
        e = TimeUnit.SECONDS.toNanos(jq0.h0("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 1L, Long.MAX_VALUE));
        f = qm.h;
        g = new xm0(0);
        h = new xm0(1);
    }
}
