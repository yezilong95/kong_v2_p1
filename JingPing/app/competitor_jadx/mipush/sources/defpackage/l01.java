package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class l01 {
    public static final ThreadLocal a = new ThreadLocal();

    public static nu a() {
        ThreadLocal threadLocal = a;
        nu nuVar = (nu) threadLocal.get();
        if (nuVar != null) {
            return nuVar;
        }
        pa paVar = new pa(Thread.currentThread());
        threadLocal.set(paVar);
        return paVar;
    }
}
