package defpackage;

import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.d;
import androidx.recyclerview.widget.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class xd0 {
    public int a = -1;
    public RecyclerView b;
    public k c;
    public boolean d;
    public boolean e;
    public View f;
    public final vd0 g;
    public boolean h;

    public xd0() {
        vd0 vd0Var = new vd0();
        vd0Var.d = -1;
        vd0Var.f = false;
        vd0Var.g = 0;
        vd0Var.a = 0;
        vd0Var.b = 0;
        vd0Var.c = Integer.MIN_VALUE;
        vd0Var.e = null;
        this.g = vd0Var;
    }

    public PointF a(int i) {
        Object obj = this.c;
        if (obj instanceof wd0) {
            return ((wd0) obj).a(i);
        }
        Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + wd0.class.getCanonicalName());
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(int r9, int r10) {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xd0.b(int, int):void");
    }

    public abstract void c(View view, vd0 vd0Var);

    public final void d() {
        if (this.e) {
            this.e = false;
            d dVar = (d) this;
            dVar.p = 0;
            dVar.o = 0;
            dVar.k = null;
            this.b.e0.a = -1;
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
