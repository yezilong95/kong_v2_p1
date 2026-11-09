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
public final class db extends ma0 {
    public final /* synthetic */ int q;
    public final /* synthetic */ CoordinatorLayout.Behavior r;

    public /* synthetic */ db(CoordinatorLayout.Behavior behavior, int i) {
        this.q = i;
        this.r = behavior;
    }

    @Override // defpackage.ma0
    public int D(View view) {
        switch (this.q) {
            case 1:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.r;
                return sideSheetBehavior.P + sideSheetBehavior.S;
            default:
                return super.D(view);
        }
    }

    @Override // defpackage.ma0
    public int E() {
        switch (this.q) {
            case 0:
                int i = BottomSheetBehavior.K0;
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) this.r;
                return bottomSheetBehavior.m0 ? bottomSheetBehavior.x0 : bottomSheetBehavior.k0;
            default:
                return super.E();
        }
    }

    @Override // defpackage.ma0
    public final void f0(int i) {
        switch (this.q) {
            case 0:
                if (i == 1) {
                    BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) this.r;
                    if (bottomSheetBehavior.o0) {
                        bottomSheetBehavior.J(1);
                    }
                }
                break;
            default:
                if (i == 1) {
                    SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.r;
                    if (sideSheetBehavior.K) {
                        sideSheetBehavior.x(1);
                    }
                }
                break;
        }
    }

    @Override // defpackage.ma0
    public final void g0(View view, int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        switch (this.q) {
            case 0:
                ((BottomSheetBehavior) this.r).A(i2);
                return;
            default:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.r;
                WeakReference weakReference = sideSheetBehavior.U;
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()) != null) {
                    sideSheetBehavior.E.m0(marginLayoutParams, view.getLeft(), view.getRight());
                    view2.setLayoutParams(marginLayoutParams);
                }
                LinkedHashSet linkedHashSet = sideSheetBehavior.Z;
                if (linkedHashSet.isEmpty()) {
                    return;
                }
                sideSheetBehavior.E.e(i);
                Iterator it = linkedHashSet.iterator();
                if (it.hasNext()) {
                    throw zn.i(it);
                }
                return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e9  */
    @Override // defpackage.ma0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h0(android.view.View r6, float r7, float r8) {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.db.h0(android.view.View, float, float):void");
    }

    @Override // defpackage.ma0
    public final int k(View view, int i) {
        switch (this.q) {
            case 0:
                return view.getLeft();
            default:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.r;
                return s90.m(i, sideSheetBehavior.E.w(), sideSheetBehavior.E.v());
        }
    }

    @Override // defpackage.ma0
    public final int l(View view, int i) {
        switch (this.q) {
            case 0:
                return s90.m(i, ((BottomSheetBehavior) this.r).D(), E());
            default:
                return view.getTop();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0048  */
    @Override // defpackage.ma0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean x0(android.view.View r5, int r6) {
        /*
            r4 = this;
            int r0 = r4.q
            switch(r0) {
                case 0: goto L1c;
                default: goto L5;
            }
        L5:
            androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r6 = r4.r
            com.google.android.material.sidesheet.SideSheetBehavior r6 = (com.google.android.material.sidesheet.SideSheetBehavior) r6
            int r0 = r6.L
            r1 = 0
            r2 = 1
            if (r0 != r2) goto L10
            goto L1b
        L10:
            java.lang.ref.WeakReference r6 = r6.T
            if (r6 == 0) goto L1b
            java.lang.Object r6 = r6.get()
            if (r6 != r5) goto L1b
            r1 = r2
        L1b:
            return r1
        L1c:
            androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r0 = r4.r
            com.google.android.material.bottomsheet.BottomSheetBehavior r0 = (com.google.android.material.bottomsheet.BottomSheetBehavior) r0
            int r1 = r0.p0
            r2 = 1
            if (r1 != r2) goto L26
            goto L56
        L26:
            boolean r3 = r0.G0
            if (r3 == 0) goto L2b
            goto L56
        L2b:
            r3 = 3
            if (r1 != r3) goto L48
            int r1 = r0.E0
            if (r1 != r6) goto L48
            java.lang.ref.WeakReference r6 = r0.A0
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
            java.lang.ref.WeakReference r6 = r0.y0
            if (r6 == 0) goto L56
            java.lang.Object r6 = r6.get()
            if (r6 != r5) goto L56
            goto L57
        L56:
            r2 = 0
        L57:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.db.x0(android.view.View, int):boolean");
    }
}
