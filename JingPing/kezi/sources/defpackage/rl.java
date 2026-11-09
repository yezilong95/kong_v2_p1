package defpackage;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ScrollView;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;

/* JADX INFO: loaded from: classes.dex */
public final class rl extends b0 {
    public final /* synthetic */ int d;

    public /* synthetic */ rl(int i) {
        this.d = i;
    }

    @Override // defpackage.b0
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 4:
                super.c(view, accessibilityEvent);
                NestedScrollView nestedScrollView = (NestedScrollView) view;
                accessibilityEvent.setClassName(ScrollView.class.getName());
                accessibilityEvent.setScrollable(nestedScrollView.getScrollRange() > 0);
                accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
                accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
                accessibilityEvent.setMaxScrollX(nestedScrollView.getScrollX());
                accessibilityEvent.setMaxScrollY(nestedScrollView.getScrollRange());
                break;
            default:
                super.c(view, accessibilityEvent);
                break;
        }
    }

    @Override // defpackage.b0
    public final void d(View view, o0 o0Var) {
        int scrollRange;
        switch (this.d) {
            case 0:
                View.AccessibilityDelegate accessibilityDelegate = this.a;
                AccessibilityNodeInfo accessibilityNodeInfo = o0Var.a;
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                if (!DrawerLayout.i(view)) {
                    accessibilityNodeInfo.setParent(null);
                }
                break;
            case 1:
                View.AccessibilityDelegate accessibilityDelegate2 = this.a;
                AccessibilityNodeInfo accessibilityNodeInfo2 = o0Var.a;
                accessibilityDelegate2.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo2);
                accessibilityNodeInfo2.setCollectionInfo(null);
                break;
            case 2:
                this.a.onInitializeAccessibilityNodeInfo(view, o0Var.a);
                o0Var.l(false);
                break;
            case 3:
                View.AccessibilityDelegate accessibilityDelegate3 = this.a;
                AccessibilityNodeInfo accessibilityNodeInfo3 = o0Var.a;
                accessibilityDelegate3.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo3);
                accessibilityNodeInfo3.setCollectionInfo(null);
                break;
            default:
                this.a.onInitializeAccessibilityNodeInfo(view, o0Var.a);
                NestedScrollView nestedScrollView = (NestedScrollView) view;
                o0Var.i(ScrollView.class.getName());
                if (nestedScrollView.isEnabled() && (scrollRange = nestedScrollView.getScrollRange()) > 0) {
                    o0Var.l(true);
                    if (nestedScrollView.getScrollY() > 0) {
                        o0Var.b(j0.i);
                        o0Var.b(j0.m);
                    }
                    if (nestedScrollView.getScrollY() < scrollRange) {
                        o0Var.b(j0.h);
                        o0Var.b(j0.n);
                    }
                    break;
                }
                break;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0075  */
    @Override // defpackage.b0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean g(android.view.View r5, int r6, android.os.Bundle r7) {
        /*
            r4 = this;
            int r0 = r4.d
            switch(r0) {
                case 4: goto La;
                default: goto L5;
            }
        L5:
            boolean r5 = super.g(r5, r6, r7)
            return r5
        La:
            boolean r7 = super.g(r5, r6, r7)
            r0 = 1
            if (r7 == 0) goto L13
            goto La1
        L13:
            androidx.core.widget.NestedScrollView r5 = (androidx.core.widget.NestedScrollView) r5
            boolean r7 = r5.isEnabled()
            r1 = 0
            if (r7 != 0) goto L1e
            goto La0
        L1e:
            int r7 = r5.getHeight()
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            android.graphics.Matrix r3 = r5.getMatrix()
            boolean r3 = r3.isIdentity()
            if (r3 == 0) goto L3b
            boolean r3 = r5.getGlobalVisibleRect(r2)
            if (r3 == 0) goto L3b
            int r7 = r2.height()
        L3b:
            r2 = 4096(0x1000, float:5.74E-42)
            if (r6 == r2) goto L75
            r2 = 8192(0x2000, float:1.148E-41)
            if (r6 == r2) goto L4e
            r2 = 16908344(0x1020038, float:2.3877386E-38)
            if (r6 == r2) goto L4e
            r2 = 16908346(0x102003a, float:2.3877392E-38)
            if (r6 == r2) goto L75
            goto La0
        L4e:
            int r6 = r5.getPaddingBottom()
            int r7 = r7 - r6
            int r6 = r5.getPaddingTop()
            int r7 = r7 - r6
            int r6 = r5.getScrollY()
            int r6 = r6 - r7
            int r6 = java.lang.Math.max(r6, r1)
            int r7 = r5.getScrollY()
            if (r6 == r7) goto La0
            int r7 = r5.getScrollX()
            int r1 = r1 - r7
            int r7 = r5.getScrollY()
            int r6 = r6 - r7
            r5.u(r1, r6, r0)
            goto La1
        L75:
            int r6 = r5.getPaddingBottom()
            int r7 = r7 - r6
            int r6 = r5.getPaddingTop()
            int r7 = r7 - r6
            int r6 = r5.getScrollY()
            int r6 = r6 + r7
            int r7 = r5.getScrollRange()
            int r6 = java.lang.Math.min(r6, r7)
            int r7 = r5.getScrollY()
            if (r6 == r7) goto La0
            int r7 = r5.getScrollX()
            int r1 = r1 - r7
            int r7 = r5.getScrollY()
            int r6 = r6 - r7
            r5.u(r1, r6, r0)
            goto La1
        La0:
            r0 = r1
        La1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rl.g(android.view.View, int, android.os.Bundle):boolean");
    }
}
