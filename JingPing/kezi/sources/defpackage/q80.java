package defpackage;

import android.view.SubMenu;

/* JADX INFO: loaded from: classes.dex */
public final class q80 extends i60 {
    @Override // defpackage.i60, android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        m60 m60VarA = a(i, i2, i3, charSequence);
        e90 e90Var = new e90(this.a, this, m60VarA);
        m60VarA.o = e90Var;
        e90Var.setHeaderTitle(m60VarA.e);
        return e90Var;
    }
}
