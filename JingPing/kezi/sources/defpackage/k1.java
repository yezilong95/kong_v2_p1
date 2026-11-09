package defpackage;

import android.content.Context;
import android.view.View;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.b;

/* JADX INFO: loaded from: classes.dex */
public final class k1 extends u60 {
    public final /* synthetic */ int l = 0;
    public final /* synthetic */ b m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k1(b bVar, Context context, i60 i60Var, View view) {
        super(R$attr.actionOverflowMenuStyle, i60Var, context, view, true);
        this.m = bVar;
        this.f = 8388613;
        o70 o70Var = bVar.y;
        this.h = o70Var;
        r60 r60Var = this.i;
        if (r60Var != null) {
            r60Var.h(o70Var);
        }
    }

    @Override // defpackage.u60
    public final void c() {
        switch (this.l) {
            case 0:
                b bVar = this.m;
                bVar.v = null;
                bVar.z = 0;
                super.c();
                break;
            default:
                b bVar2 = this.m;
                i60 i60Var = bVar2.d;
                if (i60Var != null) {
                    i60Var.c(true);
                }
                bVar2.u = null;
                super.c();
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k1(b bVar, Context context, el0 el0Var, View view) {
        super(R$attr.actionOverflowMenuStyle, el0Var, context, view, false);
        this.m = bVar;
        if ((el0Var.A.x & 32) != 32) {
            View view2 = bVar.k;
            this.e = view2 == null ? (View) bVar.i : view2;
        }
        o70 o70Var = bVar.y;
        this.h = o70Var;
        r60 r60Var = this.i;
        if (r60Var != null) {
            r60Var.h(o70Var);
        }
    }
}
