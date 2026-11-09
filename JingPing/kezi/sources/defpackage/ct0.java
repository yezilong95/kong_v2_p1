package defpackage;

import android.view.View;
import android.view.animation.BaseInterpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class ct0 {
    public BaseInterpolator c;
    public za0 d;
    public boolean e;
    public long b = -1;
    public final to0 f = new to0(this);
    public final ArrayList a = new ArrayList();

    public final void a() {
        if (this.e) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((bt0) it.next()).b();
            }
            this.e = false;
        }
    }

    public final void b() {
        View view;
        if (this.e) {
            return;
        }
        for (bt0 bt0Var : this.a) {
            long j = this.b;
            if (j >= 0) {
                bt0Var.c(j);
            }
            BaseInterpolator baseInterpolator = this.c;
            if (baseInterpolator != null && (view = (View) bt0Var.a.get()) != null) {
                view.animate().setInterpolator(baseInterpolator);
            }
            if (this.d != null) {
                bt0Var.d(this.f);
            }
            View view2 = (View) bt0Var.a.get();
            if (view2 != null) {
                view2.animate().start();
            }
        }
        this.e = true;
    }
}
