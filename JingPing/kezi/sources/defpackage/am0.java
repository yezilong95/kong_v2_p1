package defpackage;

import android.view.View;
import android.view.ViewParent;
import com.google.android.material.behavior.SwipeDismissBehavior;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class am0 extends xi {
    public int r;
    public int s = -1;
    public final /* synthetic */ SwipeDismissBehavior t;

    public am0(SwipeDismissBehavior swipeDismissBehavior) {
        this.t = swipeDismissBehavior;
    }

    @Override // defpackage.xi
    public final int C(View view) {
        return view.getWidth();
    }

    @Override // defpackage.xi
    public final void K(View view, int i) {
        this.s = i;
        this.r = view.getLeft();
        ViewParent parent = view.getParent();
        if (parent != null) {
            SwipeDismissBehavior swipeDismissBehavior = this.t;
            swipeDismissBehavior.d = true;
            parent.requestDisallowInterceptTouchEvent(true);
            swipeDismissBehavior.d = false;
        }
    }

    @Override // defpackage.xi
    public final void M(View view, int i, int i2) {
        float width = view.getWidth();
        SwipeDismissBehavior swipeDismissBehavior = this.t;
        float f = width * swipeDismissBehavior.f;
        float width2 = view.getWidth() * swipeDismissBehavior.g;
        float fAbs = Math.abs(i - this.r);
        if (fAbs <= f) {
            view.setAlpha(1.0f);
        } else if (fAbs >= width2) {
            view.setAlpha(0.0f);
        } else {
            view.setAlpha(Math.min(Math.max(0.0f, 1.0f - ((fAbs - f) / (width2 - f))), 1.0f));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0066  */
    @Override // defpackage.xi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void N(android.view.View r9, float r10, float r11) {
        /*
            r8 = this;
            r11 = -1
            r8.s = r11
            int r11 = r9.getWidth()
            r0 = 0
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            com.google.android.material.behavior.SwipeDismissBehavior r2 = r8.t
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L39
            java.util.WeakHashMap r5 = defpackage.xr0.a
            int r5 = r9.getLayoutDirection()
            if (r5 != r3) goto L1a
            r5 = r3
            goto L1b
        L1a:
            r5 = r4
        L1b:
            int r6 = r2.e
            r7 = 2
            if (r6 != r7) goto L21
            goto L52
        L21:
            if (r6 != 0) goto L2d
            if (r5 == 0) goto L2a
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto L66
            goto L52
        L2a:
            if (r1 <= 0) goto L66
            goto L52
        L2d:
            if (r6 != r3) goto L66
            if (r5 == 0) goto L34
            if (r1 <= 0) goto L66
            goto L52
        L34:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto L66
            goto L52
        L39:
            int r1 = r9.getLeft()
            int r5 = r8.r
            int r1 = r1 - r5
            int r5 = r9.getWidth()
            float r5 = (float) r5
            r6 = 1056964608(0x3f000000, float:0.5)
            float r5 = r5 * r6
            int r5 = java.lang.Math.round(r5)
            int r1 = java.lang.Math.abs(r1)
            if (r1 < r5) goto L66
        L52:
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 < 0) goto L61
            int r10 = r9.getLeft()
            int r0 = r8.r
            if (r10 >= r0) goto L5f
            goto L61
        L5f:
            int r0 = r0 + r11
            goto L69
        L61:
            int r10 = r8.r
            int r0 = r10 - r11
            goto L69
        L66:
            int r0 = r8.r
            r3 = r4
        L69:
            cs0 r10 = r2.b
            int r11 = r9.getTop()
            boolean r10 = r10.q(r0, r11)
            if (r10 == 0) goto L7f
            m1 r10 = new m1
            r10.<init>(r2, r9, r3)
            java.util.WeakHashMap r11 = defpackage.xr0.a
            r9.postOnAnimation(r10)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.am0.N(android.view.View, float, float):void");
    }

    @Override // defpackage.xi
    public final boolean S(View view, int i) {
        int i2 = this.s;
        return (i2 == -1 || i2 == i) && this.t.w(view);
    }

    @Override // defpackage.xi
    public final int g(View view, int i) {
        int width;
        int width2;
        int width3;
        WeakHashMap weakHashMap = xr0.a;
        boolean z = view.getLayoutDirection() == 1;
        int i2 = this.t.e;
        if (i2 == 0) {
            if (z) {
                width = this.r - view.getWidth();
                width2 = this.r;
            } else {
                width = this.r;
                width3 = view.getWidth();
                width2 = width3 + width;
            }
        } else if (i2 != 1) {
            width = this.r - view.getWidth();
            width2 = view.getWidth() + this.r;
        } else if (z) {
            width = this.r;
            width3 = view.getWidth();
            width2 = width3 + width;
        } else {
            width = this.r - view.getWidth();
            width2 = this.r;
        }
        return Math.min(Math.max(width, i), width2);
    }

    @Override // defpackage.xi
    public final int h(View view, int i) {
        return view.getTop();
    }

    @Override // defpackage.xi
    public final void L(int i) {
    }
}
