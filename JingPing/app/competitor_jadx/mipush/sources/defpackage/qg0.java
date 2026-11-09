package defpackage;

import android.content.Context;
import android.view.SubMenu;

/* JADX INFO: loaded from: classes.dex */
public final class qg0 extends zd0 {
    public final int A;
    public final Class z;

    public qg0(Context context, Class cls, int i) {
        super(context);
        this.z = cls;
        this.A = i;
    }

    @Override // defpackage.zd0
    public final de0 a(int i, int i2, int i3, CharSequence charSequence) {
        int size = this.f.size() + 1;
        int i4 = this.A;
        if (size <= i4) {
            y();
            de0 de0VarA = super.a(i, i2, i3, charSequence);
            de0VarA.f(true);
            x();
            return de0VarA;
        }
        String simpleName = this.z.getSimpleName();
        StringBuilder sb = new StringBuilder("Maximum number of items supported by ");
        sb.append(simpleName);
        sb.append(" is ");
        sb.append(i4);
        sb.append(". Limit can be checked with ");
        throw new IllegalArgumentException(zn.o(sb, simpleName, "#getMaxItemCount()"));
    }

    @Override // defpackage.zd0, android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        throw new UnsupportedOperationException(this.z.getSimpleName().concat(" does not support submenus"));
    }
}
