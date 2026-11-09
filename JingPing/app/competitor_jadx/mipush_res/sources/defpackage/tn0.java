package defpackage;

import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.d;
import androidx.recyclerview.widget.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class tn0 {
    public int a = -1;
    public RecyclerView b;
    public k c;
    public boolean d;
    public boolean e;
    public View f;
    public final rn0 g;
    public boolean h;

    public tn0() {
        rn0 rn0Var = new rn0();
        rn0Var.d = -1;
        rn0Var.f = false;
        rn0Var.g = 0;
        rn0Var.a = 0;
        rn0Var.b = 0;
        rn0Var.c = Integer.MIN_VALUE;
        rn0Var.e = null;
        this.g = rn0Var;
    }

    public PointF a(int i) {
        Object obj = this.c;
        if (obj instanceof sn0) {
            return ((sn0) obj).a(i);
        }
        Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + sn0.class.getCanonicalName());
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(int r9, int r10) {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tn0.b(int, int):void");
    }

    public abstract void c(View view, rn0 rn0Var);

    public final void d() {
        if (this.e) {
            this.e = false;
            d dVar = (d) this;
            dVar.p = 0;
            dVar.o = 0;
            dVar.k = null;
            this.b.H0.a = -1;
            this.f = null;
            this.a = -1;
            this.d = false;
            k kVar = this.c;
            if (kVar.e == this) {
                kVar.e = null;
            }
            this.c = null;
            this.b = null;
        }
    }
}
