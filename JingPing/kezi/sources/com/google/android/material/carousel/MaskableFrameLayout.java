package com.google.android.material.carousel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import defpackage.ki0;
import defpackage.li0;
import defpackage.r40;
import defpackage.rp0;
import defpackage.t3;
import defpackage.ta0;
import defpackage.vi0;
import defpackage.vk;
import defpackage.wi0;
import defpackage.xi0;
import defpackage.yi0;

/* JADX INFO: loaded from: classes.dex */
public class MaskableFrameLayout extends FrameLayout implements r40, vi0 {
    public static final /* synthetic */ int g = 0;
    public float b;
    public final RectF c;
    public ki0 d;
    public final wi0 e;
    public Boolean f;

    public MaskableFrameLayout(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        wi0 wi0Var = this.e;
        if (wi0Var.b()) {
            Path path = wi0Var.e;
            if (!path.isEmpty()) {
                canvas.save();
                canvas.clipPath(path);
                super.dispatchDraw(canvas);
                canvas.restore();
                return;
            }
        }
        super.dispatchDraw(canvas);
    }

    public RectF getMaskRectF() {
        return this.c;
    }

    @Deprecated
    public float getMaskXPercentage() {
        return this.b;
    }

    public ki0 getShapeAppearanceModel() {
        return this.d;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Boolean bool = this.f;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            wi0 wi0Var = this.e;
            if (zBooleanValue != wi0Var.a) {
                wi0Var.a = zBooleanValue;
                wi0Var.a(this);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        wi0 wi0Var = this.e;
        this.f = Boolean.valueOf(wi0Var.a);
        if (true != wi0Var.a) {
            wi0Var.a = true;
            wi0Var.a(this);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        ki0 ki0Var;
        super.onSizeChanged(i, i2, i3, i4);
        if (getWidth() == 0) {
            return;
        }
        RectF rectF = this.c;
        wi0 wi0Var = this.e;
        wi0Var.d = rectF;
        if (!rectF.isEmpty() && (ki0Var = wi0Var.c) != null) {
            li0.a.a(ki0Var, 1.0f, wi0Var.d, null, wi0Var.e);
        }
        wi0Var.a(this);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        RectF rectF = this.c;
        if (rectF.isEmpty() || motionEvent.getAction() != 0 || rectF.contains(motionEvent.getX(), motionEvent.getY())) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void setForceCompatClipping(boolean z) {
        wi0 wi0Var = this.e;
        if (z != wi0Var.a) {
            wi0Var.a = z;
            wi0Var.a(this);
        }
    }

    @Override // defpackage.r40
    public void setMaskRectF(RectF rectF) {
        ki0 ki0Var;
        RectF rectF2 = this.c;
        rectF2.set(rectF);
        if (getWidth() == 0) {
            return;
        }
        wi0 wi0Var = this.e;
        wi0Var.d = rectF2;
        if (!rectF2.isEmpty() && (ki0Var = wi0Var.c) != null) {
            li0.a.a(ki0Var, 1.0f, wi0Var.d, null, wi0Var.e);
        }
        wi0Var.a(this);
    }

    @Deprecated
    public void setMaskXPercentage(float f) {
        float fM = rp0.m(f, 0.0f, 1.0f);
        if (this.b != fM) {
            this.b = fM;
            float fB = t3.b(0.0f, getWidth() / 2.0f, 0.0f, 1.0f, this.b);
            setMaskRectF(new RectF(fB, 0.0f, getWidth() - fB, getHeight()));
        }
    }

    @Override // defpackage.vi0
    public void setShapeAppearanceModel(ki0 ki0Var) {
        ki0 ki0Var2;
        ki0 ki0VarH = ki0Var.h(new vk(3));
        this.d = ki0VarH;
        wi0 wi0Var = this.e;
        wi0Var.c = ki0VarH;
        if (!wi0Var.d.isEmpty() && (ki0Var2 = wi0Var.c) != null) {
            li0.a.a(ki0Var2, 1.0f, wi0Var.d, null, wi0Var.e);
        }
        wi0Var.a(this);
    }

    public MaskableFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaskableFrameLayout(Context context, AttributeSet attributeSet, int i) {
        wi0 xi0Var;
        super(context, attributeSet, i);
        this.b = 0.0f;
        this.c = new RectF();
        if (Build.VERSION.SDK_INT >= 33) {
            xi0Var = new yi0(this);
        } else {
            xi0Var = new xi0(this);
        }
        this.e = xi0Var;
        this.f = null;
        setShapeAppearanceModel(ki0.c(context, attributeSet, i, 0).a());
    }

    @Override // defpackage.r40
    public void setOnMaskChangedListener(ta0 ta0Var) {
    }
}
