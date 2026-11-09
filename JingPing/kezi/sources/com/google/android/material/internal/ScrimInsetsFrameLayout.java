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
import defpackage.d90;
import defpackage.lr0;
import defpackage.un0;
import defpackage.xr0;
import defpackage.xu0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ScrimInsetsFrameLayout extends FrameLayout {
    public Drawable b;
    public Rect c;
    public final Rect d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;

    public ScrimInsetsFrameLayout(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.c == null || this.b == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        boolean z = this.e;
        Rect rect = this.d;
        if (z) {
            rect.set(0, 0, width, this.c.top);
            this.b.setBounds(rect);
            this.b.draw(canvas);
        }
        if (this.f) {
            rect.set(0, height - this.c.bottom, width, height);
            this.b.setBounds(rect);
            this.b.draw(canvas);
        }
        if (this.g) {
            Rect rect2 = this.c;
            rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
            this.b.setBounds(rect);
            this.b.draw(canvas);
        }
        if (this.h) {
            Rect rect3 = this.c;
            rect.set(width - rect3.right, rect3.top, width, height - rect3.bottom);
            this.b.setBounds(rect);
            this.b.draw(canvas);
        }
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void setDrawBottomInsetForeground(boolean z) {
        this.f = z;
    }

    public void setDrawLeftInsetForeground(boolean z) {
        this.g = z;
    }

    public void setDrawRightInsetForeground(boolean z) {
        this.h = z;
    }

    public void setDrawTopInsetForeground(boolean z) {
        this.e = z;
    }

    public void setScrimInsetForeground(Drawable drawable) {
        this.b = drawable;
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new Rect();
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        TypedArray typedArrayD = un0.d(context, attributeSet, R$styleable.ScrimInsetsFrameLayout, i, R$style.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.b = typedArrayD.getDrawable(R$styleable.ScrimInsetsFrameLayout_insetForeground);
        typedArrayD.recycle();
        setWillNotDraw(true);
        d90 d90Var = new d90((Object) this);
        WeakHashMap weakHashMap = xr0.a;
        lr0.u(this, d90Var);
    }

    public void e(xu0 xu0Var) {
    }
}
