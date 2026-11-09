package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class fm0 {
    public final /* synthetic */ int a;

    public /* synthetic */ fm0(int i) {
        this.a = i;
    }

    public static jb0 a(hb0 hb0Var, p pVar) {
        try {
            g0 g0VarL = g0.l(pVar.r());
            if (!(g0VarL instanceof j0)) {
                return new jb0(hb0Var, e0.o(g0VarL).E);
            }
            j0 j0VarQ = j0.q(g0VarL);
            return new jb0(hb0Var, e0.o(j0VarQ.r(0)).E, e0.o(j0VarQ.r(1)).E);
        } catch (Exception unused) {
            return new jb0(hb0Var, pVar.r());
        }
    }
}
