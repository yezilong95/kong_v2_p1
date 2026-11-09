package defpackage;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.sidesheet.SideSheetBehavior;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class eb {
    public final /* synthetic */ int a;
    public int b;
    public boolean c;
    public final Runnable d;
    public final /* synthetic */ CoordinatorLayout.Behavior e;

    public eb(SideSheetBehavior sideSheetBehavior) {
        this.a = 1;
        this.e = sideSheetBehavior;
        this.d = new uv0(0, this);
    }

    public final void a(int i) {
        Runnable runnable = this.d;
        CoordinatorLayout.Behavior behavior = this.e;
        switch (this.a) {
            case 0:
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
                WeakReference weakReference = bottomSheetBehavior.y0;
                if (weakReference != null && weakReference.get() != null) {
                    this.b = i;
                    if (!this.c) {
                        WeakHashMap weakHashMap = f51.a;
                        ((View) bottomSheetBehavior.y0.get()).postOnAnimation((f) runnable);
                        this.c = true;
                    }
                    break;
                }
                break;
            default:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) behavior;
                WeakReference weakReference2 = sideSheetBehavior.T;
                if (weakReference2 != null && weakReference2.get() != null) {
                    this.b = i;
                    if (!this.c) {
                        WeakHashMap weakHashMap2 = f51.a;
                        ((View) sideSheetBehavior.T.get()).postOnAnimation((uv0) runnable);
                        this.c = true;
                    }
                    break;
                }
                break;
        }
    }

    public eb(BottomSheetBehavior bottomSheetBehavior) {
        this.a = 0;
        this.e = bottomSheetBehavior;
        this.d = new f(5, this);
    }
}
