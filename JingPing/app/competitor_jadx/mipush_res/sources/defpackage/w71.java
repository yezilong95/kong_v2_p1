package defpackage;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.b;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class w71 extends l3 implements xd0 {
    public final Context G;
    public final zd0 H;
    public i6 I;
    public WeakReference J;
    public final /* synthetic */ x71 K;

    public w71(x71 x71Var, Context context, i6 i6Var) {
        this.K = x71Var;
        this.G = context;
        this.I = i6Var;
        zd0 zd0Var = new zd0(context);
        zd0Var.l = 1;
        this.H = zd0Var;
        zd0Var.e = this;
    }

    @Override // defpackage.l3
    public final void a() {
        x71 x71Var = this.K;
        if (x71Var.i != this) {
            return;
        }
        if (x71Var.p) {
            x71Var.j = this;
            x71Var.k = this.I;
        } else {
            this.I.F(this);
        }
        this.I = null;
        x71Var.a(false);
        ActionBarContextView actionBarContextView = x71Var.f;
        if (actionBarContextView.O == null) {
            actionBarContextView.e();
        }
        x71Var.c.setHideOnContentScrollEnabled(x71Var.u);
        x71Var.i = null;
    }

    @Override // defpackage.l3
    public final View b() {
        WeakReference weakReference = this.J;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // defpackage.l3
    public final zd0 c() {
        return this.H;
    }

    @Override // defpackage.l3
    public final MenuInflater d() {
        return new cy0(this.G);
    }

    @Override // defpackage.l3
    public final CharSequence e() {
        return this.K.f.getSubtitle();
    }

    @Override // defpackage.xd0
    public final boolean f(zd0 zd0Var, MenuItem menuItem) {
        i6 i6Var = this.I;
        if (i6Var != null) {
            return ((tb) i6Var.F).d(this, menuItem);
        }
        return false;
    }

    @Override // defpackage.l3
    public final CharSequence g() {
        return this.K.f.getTitle();
    }

    @Override // defpackage.l3
    public final void h() {
        if (this.K.i != this) {
            return;
        }
        zd0 zd0Var = this.H;
        zd0Var.y();
        try {
            this.I.G(this, zd0Var);
        } finally {
            zd0Var.x();
        }
    }

    @Override // defpackage.l3
    public final boolean i() {
        return this.K.f.W;
    }

    @Override // defpackage.l3
    public final void j(View view) {
        this.K.f.setCustomView(view);
        this.J = new WeakReference(view);
    }

    @Override // defpackage.l3
    public final void k(int i) {
        l(this.K.a.getResources().getString(i));
    }

    @Override // defpackage.l3
    public final void l(CharSequence charSequence) {
        this.K.f.setSubtitle(charSequence);
    }

    @Override // defpackage.xd0
    public final void m(zd0 zd0Var) {
        if (this.I == null) {
            return;
        }
        h();
        b bVar = this.K.f.H;
        if (bVar != null) {
            bVar.n();
        }
    }

    @Override // defpackage.l3
    public final void n(int i) {
        o(this.K.a.getResources().getString(i));
    }

    @Override // defpackage.l3
    public final void o(CharSequence charSequence) {
        this.K.f.setTitle(charSequence);
    }

    @Override // defpackage.l3
    public final void p(boolean z) {
        this.F = z;
        this.K.f.setTitleOptional(z);
    }
}
