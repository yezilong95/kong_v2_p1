package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class rx0 extends zd0 implements SubMenu {
    public final de0 A;
    public final zd0 z;

    public rx0(Context context, zd0 zd0Var, de0 de0Var) {
        super(context);
        this.z = zd0Var;
        this.A = de0Var;
    }

    @Override // defpackage.zd0
    public final boolean d(de0 de0Var) {
        return this.z.d(de0Var);
    }

    @Override // defpackage.zd0
    public final boolean e(zd0 zd0Var, MenuItem menuItem) {
        return super.e(zd0Var, menuItem) || this.z.e(zd0Var, menuItem);
    }

    @Override // defpackage.zd0
    public final boolean f(de0 de0Var) {
        return this.z.f(de0Var);
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return this.A;
    }

    @Override // defpackage.zd0
    public final String j() {
        de0 de0Var = this.A;
        int i = de0Var != null ? de0Var.a : 0;
        if (i == 0) {
            return null;
        }
        return zn.j(i, "android:menu:actionviewstates:");
    }

    @Override // defpackage.zd0
    public final zd0 k() {
        return this.z.k();
    }

    @Override // defpackage.zd0
    public final boolean m() {
        return this.z.m();
    }

    @Override // defpackage.zd0
    public final boolean n() {
        return this.z.n();
    }

    @Override // defpackage.zd0
    public final boolean o() {
        return this.z.o();
    }

    @Override // defpackage.zd0, android.view.Menu
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

    @Override // defpackage.zd0, android.view.Menu
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
