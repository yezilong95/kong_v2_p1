package defpackage;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.j;
import com.google.android.material.datepicker.l;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;

/* JADX INFO: loaded from: classes.dex */
public final class y2 implements View.OnClickListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ y2(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.E) {
            case 0:
                ((l3) this.F).a();
                break;
            case 1:
                i4 i4Var = (i4) this.F;
                Button button = i4Var.f;
                i4Var.v.obtainMessage(1, i4Var.b).sendToTarget();
                break;
            case 2:
                l lVar = (l) this.F;
                int i = lVar.B0;
                if (i == 2) {
                    lVar.E(1);
                } else if (i == 1) {
                    lVar.E(2);
                }
                break;
            case 3:
                de0 itemData = ((NavigationBarItemView) view).getItemData();
                NavigationBarMenuView navigationBarMenuView = (NavigationBarMenuView) this.F;
                if (!navigationBarMenuView.l0.q(itemData, navigationBarMenuView.k0, 0)) {
                    itemData.setChecked(true);
                }
                break;
            case 4:
                NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) view;
                eh0 eh0Var = (eh0) this.F;
                wg0 wg0Var = eh0Var.I;
                boolean z = true;
                if (wg0Var != null) {
                    wg0Var.e = true;
                }
                de0 itemData2 = navigationMenuItemView.getItemData();
                boolean zQ = eh0Var.G.q(itemData2, eh0Var, 0);
                if (itemData2 != null && itemData2.isCheckable() && zQ) {
                    eh0Var.I.h(itemData2);
                } else {
                    z = false;
                }
                wg0 wg0Var2 = eh0Var.I;
                if (wg0Var2 != null) {
                    wg0Var2.e = false;
                }
                if (z) {
                    eh0Var.j(false);
                }
                break;
            default:
                j jVar = ((Toolbar) this.F).s0;
                de0 de0Var = jVar == null ? null : jVar.F;
                if (de0Var != null) {
                    de0Var.collapseActionView();
                }
                break;
        }
    }
}
