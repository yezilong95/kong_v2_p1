package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class wn0 {
    public static final nm a = new nm("NO_THREAD_ELEMENTS");
    public static final pe b = pe.j;
    public static final pe c = pe.k;
    public static final pe d = pe.l;

    public static final void a(nh nhVar, Object obj) {
        if (obj == a) {
            return;
        }
        if (!(obj instanceof zn0)) {
            Object objFold = nhVar.fold(null, c);
            oy.h(objFold, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
            hk.m(objFold);
            throw null;
        }
        zn0 zn0Var = (zn0) obj;
        sh[] shVarArr = zn0Var.b;
        int length = shVarArr.length - 1;
        if (length < 0) {
            return;
        }
        sh shVar = shVarArr[length];
        oy.g(null);
        Object obj2 = zn0Var.a[length];
        throw null;
    }

    public static final Object b(nh nhVar) {
        Object objFold = nhVar.fold(0, b);
        oy.g(objFold);
        return objFold;
    }

    public static final Object c(nh nhVar, Object obj) {
        if (obj == null) {
            obj = b(nhVar);
        }
        if (obj == 0) {
            return a;
        }
        if (obj instanceof Integer) {
            return nhVar.fold(new zn0(((Number) obj).intValue(), nhVar), d);
        }
        hk.m(obj);
        throw null;
    }
}
