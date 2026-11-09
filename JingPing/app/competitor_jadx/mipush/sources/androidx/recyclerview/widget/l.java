package androidx.recyclerview.widget;

import android.util.SparseArray;
import android.view.View;
import defpackage.f51;
import defpackage.fn;
import defpackage.hn0;
import defpackage.nn0;
import defpackage.on0;
import defpackage.tp;
import defpackage.w10;
import defpackage.xn0;
import defpackage.y1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class l {
    public final ArrayList a;
    public ArrayList b;
    public final ArrayList c;
    public final List d;
    public int e;
    public int f;
    public on0 g;
    public final /* synthetic */ RecyclerView h;

    public l(RecyclerView recyclerView) {
        this.h = recyclerView;
        ArrayList arrayList = new ArrayList();
        this.a = arrayList;
        this.b = null;
        this.c = new ArrayList();
        this.d = Collections.unmodifiableList(arrayList);
        this.e = 2;
        this.f = 2;
    }

    public final void a(n nVar, boolean z) {
        RecyclerView.i(nVar);
        RecyclerView recyclerView = this.h;
        xn0 xn0Var = recyclerView.O0;
        if (xn0Var != null) {
            tp tpVar = xn0Var.e;
            View view = nVar.a;
            f51.o(view, tpVar != null ? (y1) ((WeakHashMap) tpVar.f).remove(view) : null);
        }
        if (z) {
            g gVar = recyclerView.O;
            if (gVar != null) {
                gVar.f(nVar);
            }
            if (recyclerView.H0 != null) {
                recyclerView.J.f(nVar);
            }
        }
        nVar.r = null;
        on0 on0VarC = c();
        on0VarC.getClass();
        int i = nVar.f;
        ArrayList arrayList = on0VarC.a(i).a;
        if (((nn0) on0VarC.a.get(i)).b <= arrayList.size()) {
            return;
        }
        nVar.m();
        arrayList.add(nVar);
    }

    public final int b(int i) {
        RecyclerView recyclerView = this.h;
        if (i >= 0 && i < recyclerView.H0.b()) {
            return !recyclerView.H0.g ? i : recyclerView.H.f(i, 0);
        }
        throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + recyclerView.H0.b() + recyclerView.y());
    }

    public final on0 c() {
        if (this.g == null) {
            on0 on0Var = new on0();
            on0Var.a = new SparseArray();
            on0Var.b = 0;
            this.g = on0Var;
        }
        return this.g;
    }

    public final void d() {
        ArrayList arrayList = this.c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            e(size);
        }
        arrayList.clear();
        int[] iArr = RecyclerView.X0;
        w10 w10Var = this.h.G0;
        int[] iArr2 = (int[]) w10Var.d;
        if (iArr2 != null) {
            Arrays.fill(iArr2, -1);
        }
        w10Var.c = 0;
    }

    public final void e(int i) {
        ArrayList arrayList = this.c;
        a((n) arrayList.get(i), true);
        arrayList.remove(i);
    }

    public final void f(View view) {
        n nVarI = RecyclerView.I(view);
        boolean zJ = nVarI.j();
        RecyclerView recyclerView = this.h;
        if (zJ) {
            recyclerView.removeDetachedView(view, false);
        }
        if (nVarI.i()) {
            nVarI.n.j(nVarI);
        } else if (nVarI.p()) {
            nVarI.j &= -33;
        }
        g(nVarI);
        if (recyclerView.p0 == null || nVarI.g()) {
            return;
        }
        recyclerView.p0.d(nVarI);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0096, code lost:
    
        r5 = r5 - 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(androidx.recyclerview.widget.n r12) {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.l.g(androidx.recyclerview.widget.n):void");
    }

    public final void h(View view) {
        hn0 hn0Var;
        n nVarI = RecyclerView.I(view);
        boolean z = (nVarI.j & 12) != 0;
        RecyclerView recyclerView = this.h;
        if (!z && nVarI.k() && (hn0Var = recyclerView.p0) != null) {
            fn fnVar = (fn) hn0Var;
            if (nVarI.c().isEmpty() && fnVar.g && !nVarI.f()) {
                if (this.b == null) {
                    this.b = new ArrayList();
                }
                nVarI.n = this;
                nVarI.o = true;
                this.b.add(nVarI);
                return;
            }
        }
        if (nVarI.f() && !nVarI.h() && !recyclerView.O.b) {
            throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + recyclerView.y());
        }
        nVarI.n = this;
        nVarI.o = false;
        this.a.add(nVarI);
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x052c  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0536  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x054c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0550  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0146  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.recyclerview.widget.n i(int r29, long r30) {
        /*
            Method dump skipped, instruction units count: 1411
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.l.i(int, long):androidx.recyclerview.widget.n");
    }

    public final void j(n nVar) {
        if (nVar.o) {
            this.b.remove(nVar);
        } else {
            this.a.remove(nVar);
        }
        nVar.n = null;
        nVar.o = false;
        nVar.j &= -33;
    }

    public final void k() {
        k kVar = this.h.P;
        this.f = this.e + (kVar != null ? kVar.j : 0);
        ArrayList arrayList = this.c;
        for (int size = arrayList.size() - 1; size >= 0 && arrayList.size() > this.f; size--) {
            e(size);
        }
    }
}
