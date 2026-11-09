package androidx.appcompat.widget;

import android.content.Context;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.Toolbar;
import defpackage.el0;
import defpackage.i60;
import defpackage.m60;
import defpackage.wd;
import defpackage.z60;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class j implements z60 {
    public i60 b;
    public m60 c;
    public final /* synthetic */ Toolbar d;

    public j(Toolbar toolbar) {
        this.d = toolbar;
    }

    @Override // defpackage.z60
    public final boolean c() {
        return false;
    }

    @Override // defpackage.z60
    public final boolean d(m60 m60Var) {
        Toolbar toolbar = this.d;
        KeyEvent.Callback callback = toolbar.j;
        if (callback instanceof wd) {
            ((wd) callback).e();
        }
        toolbar.removeView(toolbar.j);
        toolbar.removeView(toolbar.i);
        toolbar.j = null;
        ArrayList arrayList = toolbar.F;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            toolbar.addView((View) arrayList.get(size));
        }
        arrayList.clear();
        this.c = null;
        toolbar.requestLayout();
        m60Var.C = false;
        m60Var.n.p(false);
        toolbar.u();
        return true;
    }

    @Override // defpackage.z60
    public final Parcelable e() {
        return null;
    }

    @Override // defpackage.z60
    public final void g(Parcelable parcelable) {
    }

    @Override // defpackage.z60
    public final int getId() {
        return 0;
    }

    @Override // defpackage.z60
    public final boolean i(m60 m60Var) {
        Toolbar toolbar = this.d;
        toolbar.c();
        ViewParent parent = toolbar.i.getParent();
        if (parent != toolbar) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar.i);
            }
            toolbar.addView(toolbar.i);
        }
        View actionView = m60Var.getActionView();
        toolbar.j = actionView;
        this.c = m60Var;
        ViewParent parent2 = actionView.getParent();
        if (parent2 != toolbar) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar.j);
            }
            Toolbar.LayoutParams layoutParamsH = Toolbar.h();
            layoutParamsH.a = (toolbar.o & 112) | 8388611;
            layoutParamsH.b = 2;
            toolbar.j.setLayoutParams(layoutParamsH);
            toolbar.addView(toolbar.j);
        }
        for (int childCount = toolbar.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = toolbar.getChildAt(childCount);
            if (((Toolbar.LayoutParams) childAt.getLayoutParams()).b != 2 && childAt != toolbar.b) {
                toolbar.removeViewAt(childCount);
                toolbar.F.add(childAt);
            }
        }
        toolbar.requestLayout();
        m60Var.C = true;
        m60Var.n.p(false);
        KeyEvent.Callback callback = toolbar.j;
        if (callback instanceof wd) {
            ((wd) callback).b();
        }
        toolbar.u();
        return true;
    }

    @Override // defpackage.z60
    public final void k(Context context, i60 i60Var) {
        m60 m60Var;
        i60 i60Var2 = this.b;
        if (i60Var2 != null && (m60Var = this.c) != null) {
            i60Var2.d(m60Var);
        }
        this.b = i60Var;
    }

    @Override // defpackage.z60
    public final void l(boolean z) {
        if (this.c != null) {
            i60 i60Var = this.b;
            if (i60Var != null) {
                int size = i60Var.f.size();
                for (int i = 0; i < size; i++) {
                    if (this.b.getItem(i) == this.c) {
                        return;
                    }
                }
            }
            d(this.c);
        }
    }

    @Override // defpackage.z60
    public final boolean m(el0 el0Var) {
        return false;
    }

    @Override // defpackage.z60
    public final void a(i60 i60Var, boolean z) {
    }
}
