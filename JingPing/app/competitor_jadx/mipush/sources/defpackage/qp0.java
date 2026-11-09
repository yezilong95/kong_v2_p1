package defpackage;

import androidx.work.impl.WorkDatabase_Impl;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class qp0 extends ea0 {
    public final WorkDatabase_Impl l;
    public final i6 m;
    public final boolean n;
    public final Callable o;
    public final pp0 p;
    public final AtomicBoolean q;
    public final AtomicBoolean r;
    public final AtomicBoolean s;
    public final op0 t;
    public final op0 u;

    public qp0(WorkDatabase_Impl workDatabase_Impl, i6 i6Var, boolean z, Callable callable, String[] strArr) {
        t40.n(i6Var, "container");
        this.l = workDatabase_Impl;
        this.m = i6Var;
        this.n = z;
        this.o = callable;
        this.p = new pp0(strArr, this);
        this.q = new AtomicBoolean(true);
        this.r = new AtomicBoolean(false);
        this.s = new AtomicBoolean(false);
        this.t = new op0(this, 0);
        this.u = new op0(this, 1);
    }

    @Override // defpackage.ea0
    public final void d() {
        i6 i6Var = this.m;
        i6Var.getClass();
        ((Set) i6Var.G).add(this);
        boolean z = this.n;
        WorkDatabase_Impl workDatabase_Impl = this.l;
        (z ? workDatabase_Impl.getTransactionExecutor() : workDatabase_Impl.getQueryExecutor()).execute(this.t);
    }

    @Override // defpackage.ea0
    public final void e() {
        i6 i6Var = this.m;
        i6Var.getClass();
        ((Set) i6Var.G).remove(this);
    }
}
