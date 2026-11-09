package androidx.recyclerview.widget;

import android.util.SparseArray;
import android.view.View;
import defpackage.b0;
import defpackage.be0;
import defpackage.ld0;
import defpackage.mj;
import defpackage.ql;
import defpackage.rd0;
import defpackage.sd0;
import defpackage.xr0;
import defpackage.zu;
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
    public sd0 g;
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
        be0 be0Var = recyclerView.l0;
        if (be0Var != null) {
            ql qlVar = be0Var.e;
            View view = nVar.a;
            xr0.p(view, qlVar != null ? (b0) ((WeakHashMap) qlVar.f).remove(view) : null);
        }
        if (z) {
            g gVar = recyclerView.l;
            if (gVar != null) {
                gVar.f(nVar);
            }
            if (recyclerView.e0 != null) {
                recyclerView.g.O(nVar);
            }
        }
        nVar.r = null;
        sd0 sd0VarC = c();
        sd0VarC.getClass();
        int i = nVar.f;
        ArrayList arrayList = sd0VarC.a(i).a;
        if (((rd0) sd0VarC.a.get(i)).b <= arrayList.size()) {
            return;
        }
        nVar.m();
        arrayList.add(nVar);
    }

    public final int b(int i) {
        RecyclerView recyclerView = this.h;
        if (i >= 0 && i < recyclerView.e0.b()) {
            return !recyclerView.e0.g ? i : recyclerView.e.g(i, 0);
        }
        throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + recyclerView.e0.b() + recyclerView.y());
    }

    public final sd0 c() {
        if (this.g == null) {
            sd0 sd0Var = new sd0();
            sd0Var.a = new SparseArray();
            sd0Var.b = 0;
            this.g = sd0Var;
        }
        return this.g;
    }

    public final void d() {
        ArrayList arrayList = this.c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            e(size);
        }
        arrayList.clear();
        int[] iArr = RecyclerView.u0;
        zu zuVar = this.h.d0;
        int[] iArr2 = (int[]) zuVar.d;
        if (iArr2 != null) {
            Arrays.fill(iArr2, -1);
        }
        zuVar.c = 0;
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
        if (recyclerView.J == null || nVarI.g()) {
            return;
        }
        recyclerView.J.d(nVarI);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0096, code lost:
    
        r5 = r5 - 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g(androidx.recyclerview.widget.n r12) {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.l.g(androidx.recyclerview.widget.n):void");
    }

    public final void h(View view) {
        ld0 ld0Var;
        n nVarI = RecyclerView.I(view);
        boolean z = (nVarI.j & 12) != 0;
        RecyclerView recyclerView = this.h;
        if (!z && nVarI.k() && (ld0Var = recyclerView.J) != null) {
            mj mjVar = (mj) ld0Var;
            if (nVarI.c().isEmpty() && mjVar.g && !nVarI.f()) {
                if (this.b == null) {
                    this.b = new ArrayList();
                }
                nVarI.n = this;
                nVarI.o = true;
                this.b.add(nVarI);
                return;
            }
        }
        if (nVarI.f() && !nVarI.h() && !recyclerView.l.b) {
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.recyclerview.widget.n i(long r29, int r31) {
        /*
            Method dump skipped, instruction units count: 1411
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.l.i(long, int):androidx.recyclerview.widget.n");
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
        k kVar = this.h.m;
        this.f = this.e + (kVar != null ? kVar.j : 0);
        ArrayList arrayList = this.c;
        for (int size = arrayList.size() - 1; size >= 0 && arrayList.size() > this.f; size--) {
            e(size);
        }
    }
}
