package defpackage;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.e;
import androidx.recyclerview.widget.f;
import androidx.recyclerview.widget.k;

/* JADX INFO: loaded from: classes.dex */
public class fb0 extends od0 {
    public RecyclerView a;
    public final nj0 b = new nj0(this);
    public f c;
    public e d;

    public static int c(View view, en enVar) {
        return ((enVar.c(view) / 2) + enVar.e(view)) - ((enVar.l() / 2) + enVar.k());
    }

    public static View d(k kVar, en enVar) {
        int iV = kVar.v();
        View view = null;
        if (iV == 0) {
            return null;
        }
        int iL = (enVar.l() / 2) + enVar.k();
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < iV; i2++) {
            View viewU = kVar.u(i2);
            int iAbs = Math.abs(((enVar.c(viewU) / 2) + enVar.e(viewU)) - iL);
            if (iAbs < i) {
                view = viewU;
                i = iAbs;
            }
        }
        return view;
    }

    public final void a(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        nj0 nj0Var = this.b;
        if (recyclerView2 != null) {
            recyclerView2.removeOnScrollListener(nj0Var);
            this.a.setOnFlingListener(null);
        }
        this.a = recyclerView;
        if (recyclerView != null) {
            if (recyclerView.getOnFlingListener() != null) {
                throw new IllegalStateException("An instance of OnFlingListener already set.");
            }
            this.a.addOnScrollListener(nj0Var);
            this.a.setOnFlingListener(this);
            new Scroller(this.a.getContext(), new DecelerateInterpolator());
            h();
        }
    }

    public final int[] b(k kVar, View view) {
        int[] iArr = new int[2];
        if (kVar.d()) {
            iArr[0] = c(view, f(kVar));
        } else {
            iArr[0] = 0;
        }
        if (kVar.e()) {
            iArr[1] = c(view, g(kVar));
            return iArr;
        }
        iArr[1] = 0;
        return iArr;
    }

    public View e(k kVar) {
        if (kVar.e()) {
            return d(kVar, g(kVar));
        }
        if (kVar.d()) {
            return d(kVar, f(kVar));
        }
        return null;
    }

    public final en f(k kVar) {
        e eVar = this.d;
        if (eVar == null || ((k) eVar.b) != kVar) {
            this.d = new e(kVar);
        }
        return this.d;
    }

    public final en g(k kVar) {
        f fVar = this.c;
        if (fVar == null || ((k) fVar.b) != kVar) {
            this.c = new f(kVar);
        }
        return this.c;
    }

    public final void h() {
        k layoutManager;
        View viewE;
        RecyclerView recyclerView = this.a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (viewE = e(layoutManager)) == null) {
            return;
        }
        int[] iArrB = b(layoutManager, viewE);
        int i = iArrB[0];
        if (i == 0 && iArrB[1] == 0) {
            return;
        }
        this.a.a0(i, iArrB[1], false);
    }
}
