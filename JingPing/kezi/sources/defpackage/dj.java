package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class dj {
    public static final /* synthetic */ int a = 0;

    static {
        String property;
        int i = jm0.a;
        try {
            property = System.getProperty("kotlinx.coroutines.main.delay");
        } catch (SecurityException unused) {
            property = null;
        }
        if (!(property != null ? Boolean.parseBoolean(property) : false)) {
            cj cjVar = cj.j;
            return;
        }
        pj pjVar = al.a;
        nv nvVar = b40.a;
        nv nvVar2 = nvVar.f;
        if (nvVar == null) {
            cj cjVar2 = cj.j;
        }
    }
}
