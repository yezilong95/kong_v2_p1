package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class k01 {
    public static final wy0 a = new wy0("NO_THREAD_ELEMENTS", 0);
    public static final jg b = jg.M;
    public static final jg c = jg.N;
    public static final jg d = jg.O;

    public static final void a(bj bjVar, Object obj) {
        if (obj == a) {
            return;
        }
        if (!(obj instanceof n01)) {
            Object objFold = bjVar.fold(null, c);
            t40.l(objFold, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
            zn.s(objFold);
            throw null;
        }
        n01 n01Var = (n01) obj;
        gj[] gjVarArr = n01Var.b;
        int length = gjVarArr.length - 1;
        if (length < 0) {
            return;
        }
        gj gjVar = gjVarArr[length];
        t40.k(null);
        Object obj2 = n01Var.a[length];
        throw null;
    }

    public static final Object b(bj bjVar) {
        Object objFold = bjVar.fold(0, b);
        t40.k(objFold);
        return objFold;
    }

    public static final Object c(bj bjVar, Object obj) {
        if (obj == null) {
            obj = b(bjVar);
        }
        if (obj == 0) {
            return a;
        }
        if (obj instanceof Integer) {
            return bjVar.fold(new n01(((Number) obj).intValue(), bjVar), d);
        }
        zn.s(obj);
        throw null;
    }
}
