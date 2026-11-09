package com.google.android.material.timepicker;

import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class b implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ ClockFaceView b;

    public b(ClockFaceView clockFaceView) {
        this.b = clockFaceView;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        ClockFaceView clockFaceView = this.b;
        if (!clockFaceView.isShown()) {
            return true;
        }
        clockFaceView.getViewTreeObserver().removeOnPreDrawListener(this);
        int height = ((clockFaceView.getHeight() / 2) - clockFaceView.u.e) - clockFaceView.C;
        if (height != clockFaceView.s) {
            clockFaceView.s = height;
            clockFaceView.o();
            int i = clockFaceView.s;
            ClockHandView clockHandView = clockFaceView.u;
            clockHandView.m = i;
            clockHandView.invalidate();
        }
        return true;
    }
}
