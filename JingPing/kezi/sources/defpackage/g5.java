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
public final class g5 extends e5 {
    public final AppCompatSeekBar f;
    public Drawable g;
    public ColorStateList h;
    public PorterDuff.Mode i;
    public boolean j;
    public boolean k;

    public g5(AppCompatSeekBar appCompatSeekBar) {
        super(0, appCompatSeekBar);
        this.h = null;
        this.i = null;
        this.j = false;
        this.k = false;
        this.f = appCompatSeekBar;
    }

    @Override // defpackage.e5
    public final void E(AttributeSet attributeSet, int i) {
        super.E(attributeSet, i);
        AppCompatSeekBar appCompatSeekBar = this.f;
        po poVarN = po.N(appCompatSeekBar.getContext(), attributeSet, R$styleable.AppCompatSeekBar, i);
        xr0.o(appCompatSeekBar, appCompatSeekBar.getContext(), R$styleable.AppCompatSeekBar, attributeSet, (TypedArray) poVarN.d, i);
        Drawable drawableZ = poVarN.z(R$styleable.AppCompatSeekBar_android_thumb);
        if (drawableZ != null) {
            appCompatSeekBar.setThumb(drawableZ);
        }
        Drawable drawableY = poVarN.y(R$styleable.AppCompatSeekBar_tickMark);
        Drawable drawable = this.g;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.g = drawableY;
        if (drawableY != null) {
            drawableY.setCallback(appCompatSeekBar);
            il.b(drawableY, appCompatSeekBar.getLayoutDirection());
            if (drawableY.isStateful()) {
                drawableY.setState(appCompatSeekBar.getDrawableState());
            }
            T();
        }
        appCompatSeekBar.invalidate();
        int i2 = R$styleable.AppCompatSeekBar_tickMarkTintMode;
        TypedArray typedArray = (TypedArray) poVarN.d;
        if (typedArray.hasValue(i2)) {
            this.i = ll.c(typedArray.getInt(R$styleable.AppCompatSeekBar_tickMarkTintMode, -1), this.i);
            this.k = true;
        }
        if (typedArray.hasValue(R$styleable.AppCompatSeekBar_tickMarkTint)) {
            this.h = poVarN.w(R$styleable.AppCompatSeekBar_tickMarkTint);
            this.j = true;
        }
        poVarN.P();
        T();
    }

    public final void T() {
        Drawable drawable = this.g;
        if (drawable != null) {
            if (this.j || this.k) {
                Drawable drawableMutate = drawable.mutate();
                this.g = drawableMutate;
                if (this.j) {
                    hl.h(drawableMutate, this.h);
                }
                if (this.k) {
                    hl.i(this.g, this.i);
                }
                if (this.g.isStateful()) {
                    this.g.setState(this.f.getDrawableState());
                }
            }
        }
    }

    public final void U(Canvas canvas) {
        if (this.g != null) {
            int max = this.f.getMax();
            if (max > 1) {
                int intrinsicWidth = this.g.getIntrinsicWidth();
                int intrinsicHeight = this.g.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.g.setBounds(-i, -i2, i, i2);
                float width = ((r0.getWidth() - r0.getPaddingLeft()) - r0.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(r0.getPaddingLeft(), r0.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.g.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }
}
