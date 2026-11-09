package com.google.android.material.timepicker;

import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class b implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ ClockFaceView E;

    public b(ClockFaceView clockFaceView) {
        this.E = clockFaceView;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        ClockFaceView clockFaceView = this.E;
        if (!clockFaceView.isShown()) {
            return true;
        }
        clockFaceView.getViewTreeObserver().removeOnPreDrawListener(this);
        int height = ((clockFaceView.getHeight() / 2) - clockFaceView.a0.H) - clockFaceView.i0;
        if (height != clockFaceView.V) {
            clockFaceView.V = height;
            clockFaceView.o();
            int i = clockFaceView.V;
            ClockHandView clockHandView = clockFaceView.a0;
            clockHandView.P = i;
            clockHandView.invalidate();
        }
        return true;
    }
}
