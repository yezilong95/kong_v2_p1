package defpackage;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.sidesheet.SideSheetBehavior;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class g9 {
    public final /* synthetic */ int a;
    public int b;
    public boolean c;
    public final Runnable d;
    public final /* synthetic */ CoordinatorLayout.Behavior e;

    public g9(SideSheetBehavior sideSheetBehavior) {
        this.a = 1;
        this.e = sideSheetBehavior;
        this.d = new z1(19, this);
    }

    public final void a(int i) {
        Runnable runnable = this.d;
        CoordinatorLayout.Behavior behavior = this.e;
        switch (this.a) {
            case 0:
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
                WeakReference weakReference = bottomSheetBehavior.V;
                if (weakReference != null && weakReference.get() != null) {
                    this.b = i;
                    if (!this.c) {
                        WeakHashMap weakHashMap = xr0.a;
                        ((View) bottomSheetBehavior.V.get()).postOnAnimation((v6) runnable);
                        this.c = true;
                    }
                    break;
                }
                break;
            default:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) behavior;
                WeakReference weakReference2 = sideSheetBehavior.q;
                if (weakReference2 != null && weakReference2.get() != null) {
                    this.b = i;
                    if (!this.c) {
                        WeakHashMap weakHashMap2 = xr0.a;
                        ((View) sideSheetBehavior.q.get()).postOnAnimation((z1) runnable);
                        this.c = true;
                    }
                    break;
                }
                break;
        }
    }

    public g9(BottomSheetBehavior bottomSheetBehavior) {
        this.a = 0;
        this.e = bottomSheetBehavior;
        this.d = new v6(2, this);
    }
}
