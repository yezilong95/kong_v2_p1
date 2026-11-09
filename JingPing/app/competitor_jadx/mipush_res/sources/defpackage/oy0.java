package defpackage;

import android.view.View;
import android.view.ViewParent;
import com.google.android.material.behavior.SwipeDismissBehavior;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class oy0 extends ma0 {
    public int q;
    public int r = -1;
    public final /* synthetic */ SwipeDismissBehavior s;

    public oy0(SwipeDismissBehavior swipeDismissBehavior) {
        this.s = swipeDismissBehavior;
    }

    @Override // defpackage.ma0
    public final int D(View view) {
        return view.getWidth();
    }

    @Override // defpackage.ma0
    public final void e0(View view, int i) {
        this.r = i;
        this.q = view.getLeft();
        ViewParent parent = view.getParent();
        if (parent != null) {
            SwipeDismissBehavior swipeDismissBehavior = this.s;
            swipeDismissBehavior.G = true;
            parent.requestDisallowInterceptTouchEvent(true);
            swipeDismissBehavior.G = false;
        }
    }

    @Override // defpackage.ma0
    public final void g0(View view, int i, int i2) {
        float width = view.getWidth();
        SwipeDismissBehavior swipeDismissBehavior = this.s;
        float f = width * swipeDismissBehavior.I;
        float width2 = view.getWidth() * swipeDismissBehavior.J;
        float fAbs = Math.abs(i - this.q);
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
    @Override // defpackage.ma0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h0(android.view.View r9, float r10, float r11) {
        /*
            r8 = this;
            r11 = -1
            r8.r = r11
            int r11 = r9.getWidth()
            r0 = 0
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            com.google.android.material.behavior.SwipeDismissBehavior r2 = r8.s
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L39
            java.util.WeakHashMap r5 = defpackage.f51.a
            int r5 = r9.getLayoutDirection()
            if (r5 != r3) goto L1a
            r5 = r3
            goto L1b
        L1a:
            r5 = r4
        L1b:
            int r6 = r2.H
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
            int r5 = r8.q
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
            int r0 = r8.q
            if (r10 >= r0) goto L5f
            goto L61
        L5f:
            int r0 = r0 + r11
            goto L69
        L61:
            int r10 = r8.q
            int r0 = r10 - r11
            goto L69
        L66:
            int r0 = r8.q
            r3 = r4
        L69:
            k51 r10 = r2.E
            int r11 = r9.getTop()
            boolean r10 = r10.q(r0, r11)
            if (r10 == 0) goto L7f
            h3 r10 = new h3
            r10.<init>(r2, r9, r3)
            java.util.WeakHashMap r11 = defpackage.f51.a
            r9.postOnAnimation(r10)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.oy0.h0(android.view.View, float, float):void");
    }

    @Override // defpackage.ma0
    public final int k(View view, int i) {
        int width;
        int width2;
        int width3;
        WeakHashMap weakHashMap = f51.a;
        boolean z = view.getLayoutDirection() == 1;
        int i2 = this.s.H;
        if (i2 == 0) {
            if (z) {
                width = this.q - view.getWidth();
                width2 = this.q;
            } else {
                width = this.q;
                width3 = view.getWidth();
                width2 = width3 + width;
            }
        } else if (i2 != 1) {
            width = this.q - view.getWidth();
            width2 = view.getWidth() + this.q;
        } else if (z) {
            width = this.q;
            width3 = view.getWidth();
            width2 = width3 + width;
        } else {
            width = this.q - view.getWidth();
            width2 = this.q;
        }
        return Math.min(Math.max(width, i), width2);
    }

    @Override // defpackage.ma0
    public final int l(View view, int i) {
        return view.getTop();
    }

    @Override // defpackage.ma0
    public final boolean x0(View view, int i) {
        int i2 = this.r;
        return (i2 == -1 || i2 == i) && this.s.w(view);
    }

    @Override // defpackage.ma0
    public final void f0(int i) {
    }
}
