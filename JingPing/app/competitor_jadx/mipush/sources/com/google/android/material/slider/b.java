package com.google.android.material.slider;

import android.animation.ValueAnimator;
import defpackage.f51;
import defpackage.j11;
import defpackage.y4;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class b implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ BaseSlider a;

    public b(BaseSlider baseSlider) {
        this.a = baseSlider;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        BaseSlider baseSlider = this.a;
        for (j11 j11Var : baseSlider.P) {
            j11Var.r0 = 1.2f;
            j11Var.p0 = fFloatValue;
            j11Var.q0 = fFloatValue;
            j11Var.s0 = y4.b(0.0f, 1.0f, 0.19f, 1.0f, fFloatValue);
            j11Var.invalidateSelf();
        }
        WeakHashMap weakHashMap = f51.a;
        baseSlider.postInvalidateOnAnimation();
    }
}
