package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class el0 extends i60 implements SubMenu {
    public final m60 A;
    public final i60 z;

    public el0(Context context, i60 i60Var, m60 m60Var) {
        super(context);
        this.z = i60Var;
        this.A = m60Var;
    }

    @Override // defpackage.i60
    public final boolean d(m60 m60Var) {
        return this.z.d(m60Var);
    }

    @Override // defpackage.i60
    public final boolean e(i60 i60Var, MenuItem menuItem) {
        return super.e(i60Var, menuItem) || this.z.e(i60Var, menuItem);
    }

    @Override // defpackage.i60
    public final boolean f(m60 m60Var) {
        return this.z.f(m60Var);
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return this.A;
    }

    @Override // defpackage.i60
    public final String j() {
        m60 m60Var = this.A;
        int i = m60Var != null ? m60Var.a : 0;
        if (i == 0) {
            return null;
        }
        return hk.g(i, "android:menu:actionviewstates:");
    }

    @Override // defpackage.i60
    public final i60 k() {
        return this.z.k();
    }

    @Override // defpackage.i60
    public final boolean m() {
        return this.z.m();
    }

    @Override // defpackage.i60
    public final boolean n() {
        return this.z.n();
    }

    @Override // defpackage.i60
    public final boolean o() {
        return this.z.o();
    }

    @Override // defpackage.i60, android.view.Menu
    public final void setGroupDividerEnabled(boolean z) {
        this.z.setGroupDividerEnabled(z);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        w(0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        w(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        w(0, null, 0, null, view);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.A.setIcon(drawable);
        return this;
    }

    @Override // defpackage.i60, android.view.Menu
    public final void setQwertyMode(boolean z) {
        this.z.setQwertyMode(z);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i) {
        w(0, null, i, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i) {
        w(i, null, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i) {
        this.A.setIcon(i);
        return this;
    }
}
