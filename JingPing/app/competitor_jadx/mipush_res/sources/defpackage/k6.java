package defpackage;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.AppCompatSeekBar;

/* JADX INFO: loaded from: classes.dex */
public final class k6 extends i6 {
    public final AppCompatSeekBar I;
    public Drawable J;
    public ColorStateList K;
    public PorterDuff.Mode L;
    public boolean M;
    public boolean N;

    public k6(AppCompatSeekBar appCompatSeekBar) {
        super(0, appCompatSeekBar);
        this.K = null;
        this.L = null;
        this.M = false;
        this.N = false;
        this.I = appCompatSeekBar;
    }

    @Override // defpackage.i6
    public final void D(AttributeSet attributeSet, int i) {
        super.D(attributeSet, i);
        AppCompatSeekBar appCompatSeekBar = this.I;
        ou ouVarP = ou.P(appCompatSeekBar.getContext(), attributeSet, R$styleable.AppCompatSeekBar, i);
        f51.n(appCompatSeekBar, appCompatSeekBar.getContext(), R$styleable.AppCompatSeekBar, attributeSet, (TypedArray) ouVarP.G, i);
        Drawable drawableB = ouVarP.B(R$styleable.AppCompatSeekBar_android_thumb);
        if (drawableB != null) {
            appCompatSeekBar.setThumb(drawableB);
        }
        Drawable drawableA = ouVarP.A(R$styleable.AppCompatSeekBar_tickMark);
        Drawable drawable = this.J;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.J = drawableA;
        if (drawableA != null) {
            drawableA.setCallback(appCompatSeekBar);
            jp.b(drawableA, appCompatSeekBar.getLayoutDirection());
            if (drawableA.isStateful()) {
                drawableA.setState(appCompatSeekBar.getDrawableState());
            }
            T();
        }
        appCompatSeekBar.invalidate();
        int i2 = R$styleable.AppCompatSeekBar_tickMarkTintMode;
        TypedArray typedArray = (TypedArray) ouVarP.G;
        if (typedArray.hasValue(i2)) {
            this.L = op.c(typedArray.getInt(R$styleable.AppCompatSeekBar_tickMarkTintMode, -1), this.L);
            this.N = true;
        }
        if (typedArray.hasValue(R$styleable.AppCompatSeekBar_tickMarkTint)) {
            this.K = ouVarP.y(R$styleable.AppCompatSeekBar_tickMarkTint);
            this.M = true;
        }
        ouVarP.S();
        T();
    }

    public final void T() {
        Drawable drawable = this.J;
        if (drawable != null) {
            if (this.M || this.N) {
                Drawable drawableMutate = drawable.mutate();
                this.J = drawableMutate;
                if (this.M) {
                    ip.h(drawableMutate, this.K);
                }
                if (this.N) {
                    ip.i(this.J, this.L);
                }
                if (this.J.isStateful()) {
                    this.J.setState(this.I.getDrawableState());
                }
            }
        }
    }

    public final void U(Canvas canvas) {
        if (this.J != null) {
            int max = this.I.getMax();
            if (max > 1) {
                int intrinsicWidth = this.J.getIntrinsicWidth();
                int intrinsicHeight = this.J.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.J.setBounds(-i, -i2, i, i2);
                float width = ((r0.getWidth() - r0.getPaddingLeft()) - r0.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(r0.getPaddingLeft(), r0.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.J.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }
}
