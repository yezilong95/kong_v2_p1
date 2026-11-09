package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.tn0;

/* JADX INFO: loaded from: classes.dex */
public class d extends tn0 {
    public PointF k;
    public final DisplayMetrics l;
    public float n;
    public final LinearInterpolator i = new LinearInterpolator();
    public final DecelerateInterpolator j = new DecelerateInterpolator();
    public boolean m = false;
    public int o = 0;
    public int p = 0;

    public d(Context context) {
        this.l = context.getResources().getDisplayMetrics();
    }

    public static int e(int i, int i2, int i3, int i4, int i5) {
        if (i5 == -1) {
            return i3 - i;
        }
        if (i5 != 0) {
            if (i5 == 1) {
                return i4 - i2;
            }
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
        int i6 = i3 - i;
        if (i6 > 0) {
            return i6;
        }
        int i7 = i4 - i2;
        if (i7 < 0) {
            return i7;
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0015  */
    @Override // defpackage.tn0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(android.view.View r7, defpackage.rn0 r8) {
        /*
            r6 = this;
            android.graphics.PointF r0 = r6.k
            r1 = 1
            r2 = 0
            r3 = -1
            r4 = 0
            if (r0 == 0) goto L15
            float r0 = r0.x
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 != 0) goto Lf
            goto L15
        Lf:
            if (r0 <= 0) goto L13
            r0 = r1
            goto L16
        L13:
            r0 = r3
            goto L16
        L15:
            r0 = r2
        L16:
            int r0 = r6.f(r7, r0)
            android.graphics.PointF r5 = r6.k
            if (r5 == 0) goto L2a
            float r5 = r5.y
            int r4 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r4 != 0) goto L25
            goto L2a
        L25:
            if (r4 <= 0) goto L29
            r2 = r1
            goto L2a
        L29:
            r2 = r3
        L2a:
            int r7 = r6.g(r7, r2)
            int r2 = r0 * r0
            int r3 = r7 * r7
            int r3 = r3 + r2
            double r2 = (double) r3
            double r2 = java.lang.Math.sqrt(r2)
            int r2 = (int) r2
            int r2 = r6.i(r2)
            double r2 = (double) r2
            r4 = 4599717252057688074(0x3fd57a786c22680a, double:0.3356)
            double r2 = r2 / r4
            double r2 = java.lang.Math.ceil(r2)
            int r2 = (int) r2
            if (r2 <= 0) goto L59
            int r0 = -r0
            int r7 = -r7
            android.view.animation.DecelerateInterpolator r3 = r6.j
            r8.a = r0
            r8.b = r7
            r8.c = r2
            r8.e = r3
            r8.f = r1
        L59:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.d.c(android.view.View, rn0):void");
    }

    public int f(View view, int i) {
        k kVar = this.c;
        if (kVar == null || !kVar.d()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return e((view.getLeft() - ((RecyclerView.LayoutParams) view.getLayoutParams()).b.left) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, view.getRight() + ((RecyclerView.LayoutParams) view.getLayoutParams()).b.right + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, kVar.E(), kVar.n - kVar.F(), i);
    }

    public int g(View view, int i) {
        k kVar = this.c;
        if (kVar == null || !kVar.e()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return e((view.getTop() - ((RecyclerView.LayoutParams) view.getLayoutParams()).b.top) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, view.getBottom() + ((RecyclerView.LayoutParams) view.getLayoutParams()).b.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, kVar.G(), kVar.o - kVar.D(), i);
    }

    public float h(DisplayMetrics displayMetrics) {
        return 25.0f / displayMetrics.densityDpi;
    }

    public int i(int i) {
        float fAbs = Math.abs(i);
        if (!this.m) {
            this.n = h(this.l);
            this.m = true;
        }
        return (int) Math.ceil(fAbs * this.n);
    }
}
