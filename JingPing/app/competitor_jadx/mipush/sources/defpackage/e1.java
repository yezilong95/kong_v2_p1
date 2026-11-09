package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class e1 extends z91 {
    @Override // defpackage.z91
    public final void b0(f1 f1Var, f1 f1Var2) {
        f1Var.b = f1Var2;
    }

    @Override // defpackage.z91
    public final void c0(f1 f1Var, Thread thread) {
        f1Var.a = thread;
    }

    @Override // defpackage.z91
    public final boolean i(g1 g1Var, c1 c1Var, c1 c1Var2) {
        synchronized (g1Var) {
            try {
                if (g1Var.b != c1Var) {
                    return false;
                }
                g1Var.b = c1Var2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // defpackage.z91
    public final boolean j(g1 g1Var, Object obj, Object obj2) {
        synchronized (g1Var) {
            try {
                if (g1Var.a != obj) {
                    return false;
                }
                g1Var.a = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // defpackage.z91
    public final boolean k(g1 g1Var, f1 f1Var, f1 f1Var2) {
        synchronized (g1Var) {
            try {
                if (g1Var.c != f1Var) {
                    return false;
                }
                g1Var.c = f1Var2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
