package defpackage;

import android.view.View;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.appcompat.widget.b;

/* JADX INFO: loaded from: classes.dex */
public final class d3 extends qz {
    public final /* synthetic */ int N;
    public final /* synthetic */ View O;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d3(View view, View view2, int i) {
        super(view2);
        this.N = i;
        this.O = view;
    }

    @Override // defpackage.qz
    public final qv0 b() {
        f3 f3Var;
        switch (this.N) {
            case 0:
                e3 e3Var = ((ActionMenuItemView) this.O).Q;
                if (e3Var == null || (f3Var = ((g3) e3Var).a.Y) == null) {
                    return null;
                }
                return f3Var.a();
            case 1:
                f3 f3Var2 = ((i3) this.O).H.X;
                if (f3Var2 == null) {
                    return null;
                }
                return f3Var2.a();
            default:
                return ((ActivityChooserView) this.O).getListPopupWindow();
        }
    }

    @Override // defpackage.qz
    public final boolean c() {
        qv0 qv0VarB;
        switch (this.N) {
            case 0:
                ActionMenuItemView actionMenuItemView = (ActionMenuItemView) this.O;
                yd0 yd0Var = actionMenuItemView.O;
                return yd0Var != null && yd0Var.a(actionMenuItemView.L) && (qv0VarB = b()) != null && qv0VarB.b();
            case 1:
                ((i3) this.O).H.n();
                return true;
            default:
                ActivityChooserView activityChooserView = (ActivityChooserView) this.O;
                if (activityChooserView.b() || !activityChooserView.O) {
                    return true;
                }
                activityChooserView.E.getClass();
                throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
    }

    @Override // defpackage.qz
    public boolean d() {
        switch (this.N) {
            case 1:
                b bVar = ((i3) this.O).H;
                if (bVar.Z != null) {
                    return false;
                }
                bVar.d();
                return true;
            case 2:
                ((ActivityChooserView) this.O).a();
                return true;
            default:
                return super.d();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d3(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.N = 0;
        this.O = actionMenuItemView;
    }
}
