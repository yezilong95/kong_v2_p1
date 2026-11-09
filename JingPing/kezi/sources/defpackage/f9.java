package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.sidesheet.SideSheetBehavior;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public final class f9 extends xi {
    public final /* synthetic */ int r;
    public final /* synthetic */ CoordinatorLayout.Behavior s;

    public /* synthetic */ f9(CoordinatorLayout.Behavior behavior, int i) {
        this.r = i;
        this.s = behavior;
    }

    @Override // defpackage.xi
    public int C(View view) {
        switch (this.r) {
            case 1:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.s;
                return sideSheetBehavior.m + sideSheetBehavior.p;
            default:
                return super.C(view);
        }
    }

    @Override // defpackage.xi
    public int D() {
        switch (this.r) {
            case 0:
                int i = BottomSheetBehavior.h0;
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) this.s;
                return bottomSheetBehavior.J ? bottomSheetBehavior.U : bottomSheetBehavior.H;
            default:
                return super.D();
        }
    }

    @Override // defpackage.xi
    public final void L(int i) {
        switch (this.r) {
            case 0:
                if (i == 1) {
                    BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) this.s;
                    if (bottomSheetBehavior.L) {
                        bottomSheetBehavior.J(1);
                    }
                }
                break;
            default:
                if (i == 1) {
                    SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.s;
                    if (sideSheetBehavior.h) {
                        sideSheetBehavior.x(1);
                    }
                }
                break;
        }
    }

    @Override // defpackage.xi
    public final void M(View view, int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        switch (this.r) {
            case 0:
                ((BottomSheetBehavior) this.s).A(i2);
                return;
            default:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.s;
                WeakReference weakReference = sideSheetBehavior.r;
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()) != null) {
                    sideSheetBehavior.b.n0(marginLayoutParams, view.getLeft(), view.getRight());
                    view2.setLayoutParams(marginLayoutParams);
                }
                LinkedHashSet linkedHashSet = sideSheetBehavior.w;
                if (linkedHashSet.isEmpty()) {
                    return;
                }
                sideSheetBehavior.b.b(i);
                Iterator it = linkedHashSet.iterator();
                if (it.hasNext()) {
                    throw hk.e(it);
                }
                return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e9  */
    @Override // defpackage.xi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void N(android.view.View r6, float r7, float r8) {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.f9.N(android.view.View, float, float):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0048  */
    @Override // defpackage.xi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean S(android.view.View r5, int r6) {
        /*
            r4 = this;
            int r0 = r4.r
            switch(r0) {
                case 0: goto L1c;
                default: goto L5;
            }
        L5:
            androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r6 = r4.s
            com.google.android.material.sidesheet.SideSheetBehavior r6 = (com.google.android.material.sidesheet.SideSheetBehavior) r6
            int r0 = r6.i
            r1 = 0
            r2 = 1
            if (r0 != r2) goto L10
            goto L1b
        L10:
            java.lang.ref.WeakReference r6 = r6.q
            if (r6 == 0) goto L1b
            java.lang.Object r6 = r6.get()
            if (r6 != r5) goto L1b
            r1 = r2
        L1b:
            return r1
        L1c:
            androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r0 = r4.s
            com.google.android.material.bottomsheet.BottomSheetBehavior r0 = (com.google.android.material.bottomsheet.BottomSheetBehavior) r0
            int r1 = r0.M
            r2 = 1
            if (r1 != r2) goto L26
            goto L56
        L26:
            boolean r3 = r0.d0
            if (r3 == 0) goto L2b
            goto L56
        L2b:
            r3 = 3
            if (r1 != r3) goto L48
            int r1 = r0.b0
            if (r1 != r6) goto L48
            java.lang.ref.WeakReference r6 = r0.X
            if (r6 == 0) goto L3d
            java.lang.Object r6 = r6.get()
            android.view.View r6 = (android.view.View) r6
            goto L3e
        L3d:
            r6 = 0
        L3e:
            if (r6 == 0) goto L48
            r1 = -1
            boolean r6 = r6.canScrollVertically(r1)
            if (r6 == 0) goto L48
            goto L56
        L48:
            java.lang.System.currentTimeMillis()
            java.lang.ref.WeakReference r6 = r0.V
            if (r6 == 0) goto L56
            java.lang.Object r6 = r6.get()
            if (r6 != r5) goto L56
            goto L57
        L56:
            r2 = 0
        L57:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.f9.S(android.view.View, int):boolean");
    }

    @Override // defpackage.xi
    public final int g(View view, int i) {
        switch (this.r) {
            case 0:
                return view.getLeft();
            default:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.s;
                return rp0.n(i, sideSheetBehavior.b.A(), sideSheetBehavior.b.z());
        }
    }

    @Override // defpackage.xi
    public final int h(View view, int i) {
        switch (this.r) {
            case 0:
                return rp0.n(i, ((BottomSheetBehavior) this.s).D(), D());
            default:
                return view.getTop();
        }
    }
}
