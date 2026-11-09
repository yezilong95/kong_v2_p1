package defpackage;

import android.animation.ValueAnimator;
import android.graphics.Matrix;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class gq implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ float a;
    public final /* synthetic */ float b;
    public final /* synthetic */ float c;
    public final /* synthetic */ float d;
    public final /* synthetic */ float e;
    public final /* synthetic */ float f;
    public final /* synthetic */ float g;
    public final /* synthetic */ Matrix h;
    public final /* synthetic */ kq i;

    public gq(kq kqVar, float f, float f2, float f3, float f4, float f5, float f6, float f7, Matrix matrix) {
        this.i = kqVar;
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = f5;
        this.f = f6;
        this.g = f7;
        this.h = matrix;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        kq kqVar = this.i;
        kqVar.v.setAlpha(t3.b(this.a, this.b, 0.0f, 0.2f, fFloatValue));
        FloatingActionButton floatingActionButton = kqVar.v;
        float f = this.c;
        float f2 = this.d;
        floatingActionButton.setScaleX(t3.a(f, f2, fFloatValue));
        kqVar.v.setScaleY(t3.a(this.e, f2, fFloatValue));
        float f3 = this.f;
        float f4 = this.g;
        kqVar.p = t3.a(f3, f4, fFloatValue);
        float fA = t3.a(f3, f4, fFloatValue);
        Matrix matrix = this.h;
        kqVar.a(fA, matrix);
        kqVar.v.setImageMatrix(matrix);
    }
}
