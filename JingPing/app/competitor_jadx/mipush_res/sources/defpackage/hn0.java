package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import androidx.recyclerview.widget.n;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class hn0 {
    public cn0 a;
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

    public abstract boolean a(n nVar, n nVar2, qh0 qh0Var, qh0 qh0Var2);

    public final void c(n nVar) {
        cn0 cn0Var = this.a;
        if (cn0Var != null) {
            boolean z = true;
            nVar.n(true);
            if (nVar.h != null && nVar.i == null) {
                nVar.h = null;
            }
            nVar.i = null;
            if ((nVar.j & 16) != 0) {
                return;
            }
            RecyclerView recyclerView = cn0Var.a;
            recyclerView.c0();
            ou ouVar = recyclerView.I;
            cn0 cn0Var2 = (cn0) ouVar.F;
            RecyclerView recyclerView2 = cn0Var2.a;
            View view = nVar.a;
            int iIndexOfChild = recyclerView2.indexOfChild(view);
            if (iIndexOfChild == -1) {
                ouVar.W(view);
            } else {
                ae aeVar = (ae) ouVar.G;
                if (aeVar.d(iIndexOfChild)) {
                    aeVar.f(iIndexOfChild);
                    ouVar.W(view);
                    cn0Var2.a(iIndexOfChild);
                } else {
                    z = false;
                }
            }
            if (z) {
                n nVarI = RecyclerView.I(view);
                l lVar = recyclerView.F;
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
