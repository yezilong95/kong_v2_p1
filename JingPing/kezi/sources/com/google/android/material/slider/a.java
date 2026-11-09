package com.google.android.material.slider;

import android.animation.ValueAnimator;
import defpackage.t3;
import defpackage.xo0;
import defpackage.xr0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ BaseSlider a;

    public a(BaseSlider baseSlider) {
        this.a = baseSlider;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        BaseSlider baseSlider = this.a;
        for (xo0 xo0Var : baseSlider.l) {
            xo0Var.M = 1.2f;
            xo0Var.K = fFloatValue;
            xo0Var.L = fFloatValue;
            xo0Var.N = t3.b(0.0f, 1.0f, 0.19f, 1.0f, fFloatValue);
            xo0Var.invalidateSelf();
        }
        WeakHashMap weakHashMap = xr0.a;
        baseSlider.postInvalidateOnAnimation();
    }
}
