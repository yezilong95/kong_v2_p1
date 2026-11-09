package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class s5 extends s90 {
    public final /* synthetic */ int w;
    public final /* synthetic */ Object x;

    public /* synthetic */ s5(int i, Object obj) {
        this.w = i;
        this.x = obj;
    }

    @Override // defpackage.j61
    public final void a() {
        Object obj = this.x;
        switch (this.w) {
            case 0:
                c6 c6Var = ((p5) obj).F;
                c6Var.Z.setAlpha(1.0f);
                c6Var.c0.d(null);
                c6Var.c0 = null;
                break;
            case 1:
                c6 c6Var2 = (c6) obj;
                c6Var2.Z.setAlpha(1.0f);
                c6Var2.c0.d(null);
                c6Var2.c0 = null;
                break;
            default:
                i6 i6Var = (i6) obj;
                ((c6) i6Var.G).Z.setVisibility(8);
                c6 c6Var3 = (c6) i6Var.G;
                PopupWindow popupWindow = c6Var3.a0;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (c6Var3.Z.getParent() instanceof View) {
                    View view = (View) c6Var3.Z.getParent();
                    WeakHashMap weakHashMap = f51.a;
                    r41.c(view);
                }
                c6Var3.Z.e();
                c6Var3.c0.d(null);
                c6Var3.c0 = null;
                ViewGroup viewGroup = c6Var3.e0;
                WeakHashMap weakHashMap2 = f51.a;
                r41.c(viewGroup);
                break;
        }
    }

    @Override // defpackage.s90, defpackage.j61
    public void e() {
        Object obj = this.x;
        switch (this.w) {
            case 0:
                ((p5) obj).F.Z.setVisibility(0);
                break;
            case 1:
                c6 c6Var = (c6) obj;
                c6Var.Z.setVisibility(0);
                if (c6Var.Z.getParent() instanceof View) {
                    View view = (View) c6Var.Z.getParent();
                    WeakHashMap weakHashMap = f51.a;
                    r41.c(view);
                }
                break;
        }
    }
}
