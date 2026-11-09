package defpackage;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.b;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class xw0 extends l3 implements xd0 {
    public Context G;
    public ActionBarContextView H;
    public i6 I;
    public WeakReference J;
    public boolean K;
    public zd0 L;

    @Override // defpackage.l3
    public final void a() {
        if (this.K) {
            return;
        }
        this.K = true;
        this.I.F(this);
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
        return this.L;
    }

    @Override // defpackage.l3
    public final MenuInflater d() {
        return new cy0(this.H.getContext());
    }

    @Override // defpackage.l3
    public final CharSequence e() {
        return this.H.getSubtitle();
    }

    @Override // defpackage.xd0
    public final boolean f(zd0 zd0Var, MenuItem menuItem) {
        return ((tb) this.I.F).d(this, menuItem);
    }

    @Override // defpackage.l3
    public final CharSequence g() {
        return this.H.getTitle();
    }

    @Override // defpackage.l3
    public final void h() {
        this.I.G(this, this.L);
    }

    @Override // defpackage.l3
    public final boolean i() {
        return this.H.W;
    }

    @Override // defpackage.l3
    public final void j(View view) {
        this.H.setCustomView(view);
        this.J = view != null ? new WeakReference(view) : null;
    }

    @Override // defpackage.l3
    public final void k(int i) {
        l(this.G.getString(i));
    }

    @Override // defpackage.l3
    public final void l(CharSequence charSequence) {
        this.H.setSubtitle(charSequence);
    }

    @Override // defpackage.xd0
    public final void m(zd0 zd0Var) {
        h();
        b bVar = this.H.H;
        if (bVar != null) {
            bVar.n();
        }
    }

    @Override // defpackage.l3
    public final void n(int i) {
        o(this.G.getString(i));
    }

    @Override // defpackage.l3
    public final void o(CharSequence charSequence) {
        this.H.setTitle(charSequence);
    }

    @Override // defpackage.l3
    public final void p(boolean z) {
        this.F = z;
        this.H.setTitleOptional(z);
    }
}
