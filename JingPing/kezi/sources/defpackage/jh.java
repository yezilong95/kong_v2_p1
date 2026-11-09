package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class jh implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ jh(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        View view;
        Object obj = this.c;
        switch (this.b) {
            case 0:
                ((CoordinatorLayout) obj).u(0);
                break;
            case 1:
                kq kqVar = (kq) obj;
                float rotation = kqVar.v.getRotation();
                if (kqVar.o != rotation) {
                    kqVar.o = rotation;
                    kqVar.q();
                }
                break;
            default:
                WeakHashMap weakHashMap = xr0.a;
                hv hvVar = (hv) obj;
                hvVar.postInvalidateOnAnimation();
                ViewGroup viewGroup = hvVar.b;
                if (viewGroup != null && (view = hvVar.c) != null) {
                    viewGroup.endViewTransition(view);
                    hvVar.b.postInvalidateOnAnimation();
                    hvVar.b = null;
                    hvVar.c = null;
                }
                break;
        }
        return true;
    }
}
