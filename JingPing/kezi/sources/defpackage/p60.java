package defpackage;

import android.view.MenuItem;

/* JADX INFO: loaded from: classes.dex */
public final class p60 implements MenuItem.OnActionExpandListener {
    public final MenuItem.OnActionExpandListener a;
    public final /* synthetic */ q60 b;

    public p60(q60 q60Var, MenuItem.OnActionExpandListener onActionExpandListener) {
        this.b = q60Var;
        this.a = onActionExpandListener;
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public final boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return this.a.onMenuItemActionCollapse(this.b.g(menuItem));
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public final boolean onMenuItemActionExpand(MenuItem menuItem) {
        return this.a.onMenuItemActionExpand(this.b.g(menuItem));
    }
}
