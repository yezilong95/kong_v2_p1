package defpackage;

import androidx.work.impl.WorkDatabase_Impl;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class vf0 extends b30 {
    public final WorkDatabase_Impl l;
    public final e5 m;
    public final boolean n;
    public final Callable o;
    public final uf0 p;
    public final AtomicBoolean q;
    public final AtomicBoolean r;
    public final AtomicBoolean s;
    public final tf0 t;
    public final tf0 u;

    public vf0(WorkDatabase_Impl workDatabase_Impl, e5 e5Var, boolean z, Callable callable, String[] strArr) {
        oy.j(e5Var, "container");
        this.l = workDatabase_Impl;
        this.m = e5Var;
        this.n = z;
        this.o = callable;
        this.p = new uf0(strArr, this);
        this.q = new AtomicBoolean(true);
        this.r = new AtomicBoolean(false);
        this.s = new AtomicBoolean(false);
        this.t = new tf0(this, 0);
        this.u = new tf0(this, 1);
    }

    @Override // defpackage.b30
    public final void e() {
        e5 e5Var = this.m;
        e5Var.getClass();
        ((Set) e5Var.d).add(this);
        boolean z = this.n;
        WorkDatabase_Impl workDatabase_Impl = this.l;
        (z ? workDatabase_Impl.getTransactionExecutor() : workDatabase_Impl.getQueryExecutor()).execute(this.t);
    }

    @Override // defpackage.b30
    public final void f() {
        e5 e5Var = this.m;
        e5Var.getClass();
        ((Set) e5Var.d).remove(this);
    }
}
