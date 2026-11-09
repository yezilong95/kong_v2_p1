package defpackage;

import android.view.SubMenu;

/* JADX INFO: loaded from: classes.dex */
public final class tg0 extends zd0 {
    @Override // defpackage.zd0, android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        de0 de0VarA = a(i, i2, i3, charSequence);
        gh0 gh0Var = new gh0(this.a, this, de0VarA);
        de0VarA.o = gh0Var;
        gh0Var.setHeaderTitle(de0VarA.e);
        return gh0Var;
    }
}
