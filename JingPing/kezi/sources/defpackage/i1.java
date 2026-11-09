package defpackage;

import android.view.View;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.appcompat.widget.b;

/* JADX INFO: loaded from: classes.dex */
public final class i1 extends ct {
    public final /* synthetic */ int k;
    public final /* synthetic */ View l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i1(View view, View view2, int i) {
        super(view2);
        this.k = i;
        this.l = view;
    }

    @Override // defpackage.ct
    public final bj0 b() {
        k1 k1Var;
        switch (this.k) {
            case 0:
                j1 j1Var = ((ActionMenuItemView) this.l).n;
                if (j1Var == null || (k1Var = ((l1) j1Var).a.v) == null) {
                    return null;
                }
                return k1Var.a();
            case 1:
                k1 k1Var2 = ((n1) this.l).e.u;
                if (k1Var2 == null) {
                    return null;
                }
                return k1Var2.a();
            default:
                return ((ActivityChooserView) this.l).getListPopupWindow();
        }
    }

    @Override // defpackage.ct
    public final boolean c() {
        bj0 bj0VarB;
        switch (this.k) {
            case 0:
                ActionMenuItemView actionMenuItemView = (ActionMenuItemView) this.l;
                h60 h60Var = actionMenuItemView.l;
                return h60Var != null && h60Var.a(actionMenuItemView.i) && (bj0VarB = b()) != null && bj0VarB.b();
            case 1:
                ((n1) this.l).e.n();
                return true;
            default:
                ActivityChooserView activityChooserView = (ActivityChooserView) this.l;
                if (activityChooserView.b() || !activityChooserView.l) {
                    return true;
                }
                activityChooserView.b.getClass();
                throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
    }

    @Override // defpackage.ct
    public boolean d() {
        switch (this.k) {
            case 1:
                b bVar = ((n1) this.l).e;
                if (bVar.w != null) {
                    return false;
                }
                bVar.f();
                return true;
            case 2:
                ((ActivityChooserView) this.l).a();
                return true;
            default:
                return super.d();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i1(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.k = 0;
        this.l = actionMenuItemView;
    }
}
