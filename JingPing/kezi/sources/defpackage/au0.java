package defpackage;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.b;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class au0 extends q1 implements g60 {
    public final Context d;
    public final i60 e;
    public e5 f;
    public WeakReference g;
    public final /* synthetic */ bu0 h;

    public au0(bu0 bu0Var, Context context, e5 e5Var) {
        this.h = bu0Var;
        this.d = context;
        this.f = e5Var;
        i60 i60Var = new i60(context);
        i60Var.l = 1;
        this.e = i60Var;
        i60Var.e = this;
    }

    @Override // defpackage.q1
    public final void a() {
        bu0 bu0Var = this.h;
        if (bu0Var.i != this) {
            return;
        }
        boolean z = bu0Var.p;
        boolean z2 = bu0Var.q;
        if (z || z2) {
            bu0Var.j = this;
            bu0Var.k = this.f;
        } else {
            this.f.G(this);
        }
        this.f = null;
        bu0Var.a(false);
        ActionBarContextView actionBarContextView = bu0Var.f;
        if (actionBarContextView.l == null) {
            actionBarContextView.e();
        }
        bu0Var.c.setHideOnContentScrollEnabled(bu0Var.v);
        bu0Var.i = null;
    }

    @Override // defpackage.q1
    public final View b() {
        WeakReference weakReference = this.g;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // defpackage.q1
    public final i60 c() {
        return this.e;
    }

    @Override // defpackage.q1
    public final MenuInflater d() {
        return new ol0(this.d);
    }

    @Override // defpackage.q1
    public final CharSequence e() {
        return this.h.f.getSubtitle();
    }

    @Override // defpackage.q1
    public final CharSequence f() {
        return this.h.f.getTitle();
    }

    @Override // defpackage.q1
    public final void g() {
        if (this.h.i != this) {
            return;
        }
        i60 i60Var = this.e;
        i60Var.y();
        try {
            this.f.H(this, i60Var);
        } finally {
            i60Var.x();
        }
    }

    @Override // defpackage.q1
    public final boolean h() {
        return this.h.f.t;
    }

    @Override // defpackage.g60
    public final void i(i60 i60Var) {
        if (this.f == null) {
            return;
        }
        g();
        b bVar = this.h.f.e;
        if (bVar != null) {
            bVar.n();
        }
    }

    @Override // defpackage.q1
    public final void j(View view) {
        this.h.f.setCustomView(view);
        this.g = new WeakReference(view);
    }

    @Override // defpackage.q1
    public final void k(int i) {
        l(this.h.a.getResources().getString(i));
    }

    @Override // defpackage.q1
    public final void l(CharSequence charSequence) {
        this.h.f.setSubtitle(charSequence);
    }

    @Override // defpackage.g60
    public final boolean m(i60 i60Var, MenuItem menuItem) {
        e5 e5Var = this.f;
        if (e5Var != null) {
            return ((ba) e5Var.c).e(this, menuItem);
        }
        return false;
    }

    @Override // defpackage.q1
    public final void n(int i) {
        o(this.h.a.getResources().getString(i));
    }

    @Override // defpackage.q1
    public final void o(CharSequence charSequence) {
        this.h.f.setTitle(charSequence);
    }

    @Override // defpackage.q1
    public final void p(boolean z) {
        this.c = z;
        this.h.f.setTitleOptional(z);
    }
}
