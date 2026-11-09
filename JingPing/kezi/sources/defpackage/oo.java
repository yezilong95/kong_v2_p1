package defpackage;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public abstract class oo extends qh {
    public long d;
    public boolean e;
    public z6 f;

    public final void E() {
        long j = this.d - 4294967296L;
        this.d = j;
        if (j <= 0 && this.e) {
            J();
        }
    }

    public final void F(zk zkVar) {
        z6 z6Var = this.f;
        if (z6Var == null) {
            z6Var = new z6();
            this.f = z6Var;
        }
        z6Var.addLast(zkVar);
    }

    public abstract Thread G();

    public final void H(boolean z) {
        this.d = (z ? 4294967296L : 1L) + this.d;
        if (z) {
            return;
        }
        this.e = true;
    }

    public final boolean I() throws IllegalAccessException, InvocationTargetException {
        z6 z6Var = this.f;
        if (z6Var == null) {
            return false;
        }
        zk zkVar = (zk) (z6Var.isEmpty() ? null : z6Var.removeFirst());
        if (zkVar == null) {
            return false;
        }
        zkVar.run();
        return true;
    }

    public abstract void J();
}
