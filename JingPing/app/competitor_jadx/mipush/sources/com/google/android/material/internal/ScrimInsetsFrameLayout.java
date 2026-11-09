package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.f51;
import defpackage.i01;
import defpackage.s81;
import defpackage.t41;
import defpackage.wl0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ScrimInsetsFrameLayout extends FrameLayout {
    public Drawable E;
    public Rect F;
    public final Rect G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;

    public ScrimInsetsFrameLayout(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.F == null || this.E == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        boolean z = this.H;
        Rect rect = this.G;
        if (z) {
            rect.set(0, 0, width, this.F.top);
            this.E.setBounds(rect);
            this.E.draw(canvas);
        }
        if (this.I) {
            rect.set(0, height - this.F.bottom, width, height);
            this.E.setBounds(rect);
            this.E.draw(canvas);
        }
        if (this.J) {
            Rect rect2 = this.F;
            rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
            this.E.setBounds(rect);
            this.E.draw(canvas);
        }
        if (this.K) {
            Rect rect3 = this.F;
            rect.set(width - rect3.right, rect3.top, width, height - rect3.bottom);
            this.E.setBounds(rect);
            this.E.draw(canvas);
        }
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.E;
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void setDrawBottomInsetForeground(boolean z) {
        this.I = z;
    }

    public void setDrawLeftInsetForeground(boolean z) {
        this.J = z;
    }

    public void setDrawRightInsetForeground(boolean z) {
        this.K = z;
    }

    public void setDrawTopInsetForeground(boolean z) {
        this.H = z;
    }

    public void setScrimInsetForeground(Drawable drawable) {
        this.E = drawable;
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.G = new Rect();
        this.H = true;
        this.I = true;
        this.J = true;
        this.K = true;
        TypedArray typedArrayD = i01.d(context, attributeSet, R$styleable.ScrimInsetsFrameLayout, i, R$style.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.E = typedArrayD.getDrawable(R$styleable.ScrimInsetsFrameLayout_insetForeground);
        typedArrayD.recycle();
        setWillNotDraw(true);
        wl0 wl0Var = new wl0((Object) this);
        WeakHashMap weakHashMap = f51.a;
        t41.u(this, wl0Var);
    }

    public void e(s81 s81Var) {
    }
}
