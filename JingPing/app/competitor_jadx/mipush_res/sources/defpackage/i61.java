package defpackage;

import android.view.View;
import android.view.animation.BaseInterpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class i61 {
    public BaseInterpolator c;
    public s90 d;
    public boolean e;
    public long b = -1;
    public final e11 f = new e11(this);
    public final ArrayList a = new ArrayList();

    public final void a() {
        if (this.e) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((h61) it.next()).b();
            }
            this.e = false;
        }
    }

    public final void b() {
        View view;
        if (this.e) {
            return;
        }
        for (h61 h61Var : this.a) {
            long j = this.b;
            if (j >= 0) {
                h61Var.c(j);
            }
            BaseInterpolator baseInterpolator = this.c;
            if (baseInterpolator != null && (view = (View) h61Var.a.get()) != null) {
                view.animate().setInterpolator(baseInterpolator);
            }
            if (this.d != null) {
                h61Var.d(this.f);
            }
            View view2 = (View) h61Var.a.get();
            if (view2 != null) {
                view2.animate().start();
            }
        }
        this.e = true;
    }
}
