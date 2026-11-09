package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class xn0 {
    public static final ThreadLocal a = new ThreadLocal();

    public static oo a() {
        ThreadLocal threadLocal = a;
        oo ooVar = (oo) threadLocal.get();
        if (ooVar != null) {
            return ooVar;
        }
        q8 q8Var = new q8(Thread.currentThread());
        threadLocal.set(q8Var);
        return q8Var;
    }
}
