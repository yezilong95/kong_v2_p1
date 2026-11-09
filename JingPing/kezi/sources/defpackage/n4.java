package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class n4 extends za0 {
    public final /* synthetic */ int p;
    public final /* synthetic */ Object q;

    public /* synthetic */ n4(int i, Object obj) {
        this.p = i;
        this.q = obj;
    }

    @Override // defpackage.dt0
    public final void a() {
        Object obj = this.q;
        switch (this.p) {
            case 0:
                x4 x4Var = ((l4) obj).c;
                x4Var.w.setAlpha(1.0f);
                x4Var.z.d(null);
                x4Var.z = null;
                break;
            case 1:
                x4 x4Var2 = (x4) obj;
                x4Var2.w.setAlpha(1.0f);
                x4Var2.z.d(null);
                x4Var2.z = null;
                break;
            default:
                e5 e5Var = (e5) obj;
                ((x4) e5Var.d).w.setVisibility(8);
                x4 x4Var3 = (x4) e5Var.d;
                PopupWindow popupWindow = x4Var3.x;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (x4Var3.w.getParent() instanceof View) {
                    View view = (View) x4Var3.w.getParent();
                    WeakHashMap weakHashMap = xr0.a;
                    jr0.c(view);
                }
                x4Var3.w.e();
                x4Var3.z.d(null);
                x4Var3.z = null;
                ViewGroup viewGroup = x4Var3.B;
                WeakHashMap weakHashMap2 = xr0.a;
                jr0.c(viewGroup);
                break;
        }
    }

    @Override // defpackage.za0, defpackage.dt0
    public void c() {
        Object obj = this.q;
        switch (this.p) {
            case 0:
                ((l4) obj).c.w.setVisibility(0);
                break;
            case 1:
                x4 x4Var = (x4) obj;
                x4Var.w.setVisibility(0);
                if (x4Var.w.getParent() instanceof View) {
                    View view = (View) x4Var.w.getParent();
                    WeakHashMap weakHashMap = xr0.a;
                    jr0.c(view);
                }
                break;
        }
    }
}
