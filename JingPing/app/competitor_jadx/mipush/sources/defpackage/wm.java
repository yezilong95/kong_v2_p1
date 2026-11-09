package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class wm {
    public static final /* synthetic */ int a = 0;

    static {
        String property;
        int i = yy0.a;
        try {
            property = System.getProperty("kotlinx.coroutines.main.delay");
        } catch (SecurityException unused) {
            property = null;
        }
        if (!(property != null ? Boolean.parseBoolean(property) : false)) {
            vm vmVar = vm.M;
            return;
        }
        in inVar = xo.a;
        u20 u20Var = qb0.a;
        u20 u20Var2 = u20Var.I;
        if (u20Var == null) {
            vm vmVar2 = vm.M;
        }
    }
}
