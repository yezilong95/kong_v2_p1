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
public final class c1 implements View.OnClickListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ c1(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.b) {
            case 0:
                ((q1) this.c).a();
                break;
            case 1:
                v2 v2Var = (v2) this.c;
                Button button = v2Var.f;
                v2Var.v.obtainMessage(1, v2Var.b).sendToTarget();
                break;
            case 2:
                l lVar = (l) this.c;
                int i = lVar.Y;
                if (i == 2) {
                    lVar.E(1);
                } else if (i == 1) {
                    lVar.E(2);
                }
                break;
            case 3:
                m60 itemData = ((NavigationBarItemView) view).getItemData();
                NavigationBarMenuView navigationBarMenuView = (NavigationBarMenuView) this.c;
                if (!navigationBarMenuView.F.q(itemData, navigationBarMenuView.E, 0)) {
                    itemData.setChecked(true);
                }
                break;
            case 4:
                NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) view;
                b90 b90Var = (b90) this.c;
                t80 t80Var = b90Var.f;
                boolean z = true;
                if (t80Var != null) {
                    t80Var.e = true;
                }
                m60 itemData2 = navigationMenuItemView.getItemData();
                boolean zQ = b90Var.d.q(itemData2, b90Var, 0);
                if (itemData2 != null && itemData2.isCheckable() && zQ) {
                    b90Var.f.h(itemData2);
                } else {
                    z = false;
                }
                t80 t80Var2 = b90Var.f;
                if (t80Var2 != null) {
                    t80Var2.e = false;
                }
                if (z) {
                    b90Var.l(false);
                }
                break;
            default:
                j jVar = ((Toolbar) this.c).M;
                m60 m60Var = jVar == null ? null : jVar.c;
                if (m60Var != null) {
                    m60Var.collapseActionView();
                }
                break;
        }
    }
}
