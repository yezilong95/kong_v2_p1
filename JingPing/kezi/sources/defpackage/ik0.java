package defpackage;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.b;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class ik0 extends q1 implements g60 {
    public Context d;
    public ActionBarContextView e;
    public e5 f;
    public WeakReference g;
    public boolean h;
    public i60 i;

    @Override // defpackage.q1
    public final void a() {
        if (this.h) {
            return;
        }
        this.h = true;
        this.f.G(this);
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
        return this.i;
    }

    @Override // defpackage.q1
    public final MenuInflater d() {
        return new ol0(this.e.getContext());
    }

    @Override // defpackage.q1
    public final CharSequence e() {
        return this.e.getSubtitle();
    }

    @Override // defpackage.q1
    public final CharSequence f() {
        return this.e.getTitle();
    }

    @Override // defpackage.q1
    public final void g() {
        this.f.H(this, this.i);
    }

    @Override // defpackage.q1
    public final boolean h() {
        return this.e.t;
    }

    @Override // defpackage.g60
    public final void i(i60 i60Var) {
        g();
        b bVar = this.e.e;
        if (bVar != null) {
            bVar.n();
        }
    }

    @Override // defpackage.q1
    public final void j(View view) {
        this.e.setCustomView(view);
        this.g = view != null ? new WeakReference(view) : null;
    }

    @Override // defpackage.q1
    public final void k(int i) {
        l(this.d.getString(i));
    }

    @Override // defpackage.q1
    public final void l(CharSequence charSequence) {
        this.e.setSubtitle(charSequence);
    }

    @Override // defpackage.g60
    public final boolean m(i60 i60Var, MenuItem menuItem) {
        return ((ba) this.f.c).e(this, menuItem);
    }

    @Override // defpackage.q1
    public final void n(int i) {
        o(this.d.getString(i));
    }

    @Override // defpackage.q1
    public final void o(CharSequence charSequence) {
        this.e.setTitle(charSequence);
    }

    @Override // defpackage.q1
    public final void p(boolean z) {
        this.c = z;
        this.e.setTitleOptional(z);
    }
}
