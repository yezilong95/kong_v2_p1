package androidx.appcompat.widget;

import android.content.Context;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.Toolbar;
import defpackage.de0;
import defpackage.qe0;
import defpackage.rf;
import defpackage.rx0;
import defpackage.zd0;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class j implements qe0 {
    public zd0 E;
    public de0 F;
    public final /* synthetic */ Toolbar G;

    public j(Toolbar toolbar) {
        this.G = toolbar;
    }

    @Override // defpackage.qe0
    public final boolean c(de0 de0Var) {
        Toolbar toolbar = this.G;
        KeyEvent.Callback callback = toolbar.M;
        if (callback instanceof rf) {
            ((rf) callback).e();
        }
        toolbar.removeView(toolbar.M);
        toolbar.removeView(toolbar.L);
        toolbar.M = null;
        ArrayList arrayList = toolbar.l0;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            toolbar.addView((View) arrayList.get(size));
        }
        arrayList.clear();
        this.F = null;
        toolbar.requestLayout();
        de0Var.C = false;
        de0Var.n.p(false);
        toolbar.u();
        return true;
    }

    @Override // defpackage.qe0
    public final void e(Parcelable parcelable) {
    }

    @Override // defpackage.qe0
    public final boolean g(de0 de0Var) {
        Toolbar toolbar = this.G;
        toolbar.c();
        ViewParent parent = toolbar.L.getParent();
        if (parent != toolbar) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar.L);
            }
            toolbar.addView(toolbar.L);
        }
        View actionView = de0Var.getActionView();
        toolbar.M = actionView;
        this.F = de0Var;
        ViewParent parent2 = actionView.getParent();
        if (parent2 != toolbar) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar.M);
            }
            Toolbar.LayoutParams layoutParamsH = Toolbar.h();
            layoutParamsH.a = (toolbar.R & 112) | 8388611;
            layoutParamsH.b = 2;
            toolbar.M.setLayoutParams(layoutParamsH);
            toolbar.addView(toolbar.M);
        }
        for (int childCount = toolbar.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = toolbar.getChildAt(childCount);
            if (((Toolbar.LayoutParams) childAt.getLayoutParams()).b != 2 && childAt != toolbar.E) {
                toolbar.removeViewAt(childCount);
                toolbar.l0.add(childAt);
            }
        }
        toolbar.requestLayout();
        de0Var.C = true;
        de0Var.n.p(false);
        KeyEvent.Callback callback = toolbar.M;
        if (callback instanceof rf) {
            ((rf) callback).b();
        }
        toolbar.u();
        return true;
    }

    @Override // defpackage.qe0
    public final int getId() {
        return 0;
    }

    @Override // defpackage.qe0
    public final void i(Context context, zd0 zd0Var) {
        de0 de0Var;
        zd0 zd0Var2 = this.E;
        if (zd0Var2 != null && (de0Var = this.F) != null) {
            zd0Var2.d(de0Var);
        }
        this.E = zd0Var;
    }

    @Override // defpackage.qe0
    public final void j(boolean z) {
        if (this.F != null) {
            zd0 zd0Var = this.E;
            if (zd0Var != null) {
                int size = zd0Var.f.size();
                for (int i = 0; i < size; i++) {
                    if (this.E.getItem(i) == this.F) {
                        return;
                    }
                }
            }
            c(this.F);
        }
    }

    @Override // defpackage.qe0
    public final boolean k(rx0 rx0Var) {
        return false;
    }

    @Override // defpackage.qe0
    public final boolean l() {
        return false;
    }

    @Override // defpackage.qe0
    public final Parcelable m() {
        return null;
    }

    @Override // defpackage.qe0
    public final void a(zd0 zd0Var, boolean z) {
    }
}
