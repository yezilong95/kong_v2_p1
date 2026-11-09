package defpackage;

import android.content.Context;
import android.view.View;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.b;

/* JADX INFO: loaded from: classes.dex */
public final class f3 extends le0 {
    public final /* synthetic */ int l = 0;
    public final /* synthetic */ b m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f3(b bVar, Context context, zd0 zd0Var, View view) {
        super(R$attr.actionOverflowMenuStyle, zd0Var, context, view, true);
        this.m = bVar;
        this.f = 8388613;
        z8 z8Var = bVar.b0;
        this.h = z8Var;
        ie0 ie0Var = this.i;
        if (ie0Var != null) {
            ie0Var.f(z8Var);
        }
    }

    @Override // defpackage.le0
    public final void c() {
        switch (this.l) {
            case 0:
                b bVar = this.m;
                bVar.Y = null;
                bVar.c0 = 0;
                super.c();
                break;
            default:
                b bVar2 = this.m;
                zd0 zd0Var = bVar2.G;
                if (zd0Var != null) {
                    zd0Var.c(true);
                }
                bVar2.X = null;
                super.c();
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f3(b bVar, Context context, rx0 rx0Var, View view) {
        super(R$attr.actionOverflowMenuStyle, rx0Var, context, view, false);
        this.m = bVar;
        if ((rx0Var.A.x & 32) != 32) {
            View view2 = bVar.N;
            this.e = view2 == null ? (View) bVar.L : view2;
        }
        z8 z8Var = bVar.b0;
        this.h = z8Var;
        ie0 ie0Var = this.i;
        if (ie0Var != null) {
            ie0Var.f(z8Var);
        }
    }
}
