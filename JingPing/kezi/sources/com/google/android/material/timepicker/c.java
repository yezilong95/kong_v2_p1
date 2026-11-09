package com.google.android.material.timepicker;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.R$id;
import defpackage.b0;
import defpackage.j0;
import defpackage.n0;
import defpackage.o0;

/* JADX INFO: loaded from: classes.dex */
public final class c extends b0 {
    public final /* synthetic */ ClockFaceView d;

    public c(ClockFaceView clockFaceView) {
        this.d = clockFaceView;
    }

    @Override // defpackage.b0
    public final void d(View view, o0 o0Var) {
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        AccessibilityNodeInfo accessibilityNodeInfo = o0Var.a;
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        int iIntValue = ((Integer) view.getTag(R$id.material_value_index)).intValue();
        if (iIntValue > 0) {
            accessibilityNodeInfo.setTraversalAfter((View) this.d.y.get(iIntValue - 1));
        }
        o0Var.j(n0.a(0, 1, iIntValue, 1, false, view.isSelected()));
        accessibilityNodeInfo.setClickable(true);
        o0Var.b(j0.g);
    }

    @Override // defpackage.b0
    public final boolean g(View view, int i, Bundle bundle) {
        if (i != 16) {
            return super.g(view, i, bundle);
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        ClockFaceView clockFaceView = this.d;
        view.getHitRect(clockFaceView.v);
        float fCenterX = clockFaceView.v.centerX();
        float fCenterY = clockFaceView.v.centerY();
        clockFaceView.u.onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, fCenterX, fCenterY, 0));
        clockFaceView.u.onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 1, fCenterX, fCenterY, 0));
        return true;
    }
}
