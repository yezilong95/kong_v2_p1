package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import androidx.recyclerview.widget.n;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class ld0 {
    public gd0 a;
    public ArrayList b;
    public long c;
    public long d;
    public long e;
    public long f;

    public static void b(n nVar) {
        RecyclerView recyclerView;
        int i = nVar.j;
        if (nVar.f() || (i & 4) != 0 || (recyclerView = nVar.r) == null) {
            return;
        }
        recyclerView.F(nVar);
    }

    public abstract boolean a(n nVar, n nVar2, n90 n90Var, n90 n90Var2);

    public final void c(n nVar) {
        gd0 gd0Var = this.a;
        if (gd0Var != null) {
            boolean z = true;
            nVar.n(true);
            if (nVar.h != null && nVar.i == null) {
                nVar.h = null;
            }
            nVar.i = null;
            if ((nVar.j & 16) != 0) {
                return;
            }
            RecyclerView recyclerView = gd0Var.a;
            recyclerView.c0();
            po poVar = recyclerView.f;
            gd0 gd0Var2 = (gd0) poVar.c;
            RecyclerView recyclerView2 = gd0Var2.a;
            View view = nVar.a;
            int iIndexOfChild = recyclerView2.indexOfChild(view);
            if (iIndexOfChild == -1) {
                poVar.R(view);
            } else {
                fc fcVar = (fc) poVar.d;
                if (fcVar.d(iIndexOfChild)) {
                    fcVar.g(iIndexOfChild);
                    poVar.R(view);
                    gd0Var2.h(iIndexOfChild);
                } else {
                    z = false;
                }
            }
            if (z) {
                n nVarI = RecyclerView.I(view);
                l lVar = recyclerView.c;
                lVar.j(nVarI);
                lVar.g(nVarI);
            }
            recyclerView.d0(!z);
            if (z || !nVar.j()) {
                return;
            }
            recyclerView.removeDetachedView(view, false);
        }
    }

    public abstract void d(n nVar);

    public abstract void e();

    public abstract boolean f();
}
