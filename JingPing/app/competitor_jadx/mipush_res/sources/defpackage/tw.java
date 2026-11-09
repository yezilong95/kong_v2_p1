package defpackage;

import android.animation.ValueAnimator;
import android.graphics.Matrix;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class tw implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ float a;
    public final /* synthetic */ float b;
    public final /* synthetic */ float c;
    public final /* synthetic */ float d;
    public final /* synthetic */ float e;
    public final /* synthetic */ float f;
    public final /* synthetic */ float g;
    public final /* synthetic */ Matrix h;
    public final /* synthetic */ xw i;

    public tw(xw xwVar, float f, float f2, float f3, float f4, float f5, float f6, float f7, Matrix matrix) {
        this.i = xwVar;
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
        xw xwVar = this.i;
        xwVar.v.setAlpha(y4.b(this.a, this.b, 0.0f, 0.2f, fFloatValue));
        FloatingActionButton floatingActionButton = xwVar.v;
        float f = this.c;
        float f2 = this.d;
        floatingActionButton.setScaleX(y4.a(f, f2, fFloatValue));
        xwVar.v.setScaleY(y4.a(this.e, f2, fFloatValue));
        float f3 = this.f;
        float f4 = this.g;
        xwVar.p = y4.a(f3, f4, fFloatValue);
        float fA = y4.a(f3, f4, fFloatValue);
        Matrix matrix = this.h;
        xwVar.a(fA, matrix);
        xwVar.v.setImageMatrix(matrix);
    }
}
