package com.google.android.material.carousel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import defpackage.hc0;
import defpackage.hj0;
import defpackage.kv0;
import defpackage.lv0;
import defpackage.mv0;
import defpackage.no;
import defpackage.nv0;
import defpackage.s90;
import defpackage.y4;
import defpackage.zu0;

/* JADX INFO: loaded from: classes.dex */
public class MaskableFrameLayout extends FrameLayout implements hc0, kv0 {
    public static final /* synthetic */ int J = 0;
    public float E;
    public final RectF F;
    public zu0 G;
    public final lv0 H;
    public Boolean I;

    public MaskableFrameLayout(Context context) {
        this(context, null);
    }

    public final void a() {
        if (this.E != -1.0f) {
            float fB = y4.b(0.0f, getWidth() / 2.0f, 0.0f, 1.0f, this.E);
            setMaskRectF(new RectF(fB, 0.0f, getWidth() - fB, getHeight()));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        lv0 lv0Var = this.H;
        if (lv0Var.b()) {
            Path path = lv0Var.e;
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

    @Override // android.view.View
    public final void getFocusedRect(Rect rect) {
        RectF rectF = this.F;
        rect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }

    public RectF getMaskRectF() {
        return this.F;
    }

    @Deprecated
    public float getMaskXPercentage() {
        return this.E;
    }

    public zu0 getShapeAppearanceModel() {
        return this.G;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Boolean bool = this.I;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            lv0 lv0Var = this.H;
            if (zBooleanValue != lv0Var.a) {
                lv0Var.a = zBooleanValue;
                lv0Var.a(this);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        lv0 lv0Var = this.H;
        this.I = Boolean.valueOf(lv0Var.a);
        if (true != lv0Var.a) {
            lv0Var.a = true;
            lv0Var.a(this);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.E != -1.0f) {
            a();
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        RectF rectF = this.F;
        if (rectF.isEmpty() || motionEvent.getAction() != 0 || rectF.contains(motionEvent.getX(), motionEvent.getY())) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void setForceCompatClipping(boolean z) {
        lv0 lv0Var = this.H;
        if (z != lv0Var.a) {
            lv0Var.a = z;
            lv0Var.a(this);
        }
    }

    @Override // defpackage.hc0
    public void setMaskRectF(RectF rectF) {
        RectF rectF2 = this.F;
        rectF2.set(rectF);
        lv0 lv0Var = this.H;
        lv0Var.d = rectF2;
        lv0Var.c();
        lv0Var.a(this);
    }

    @Deprecated
    public void setMaskXPercentage(float f) {
        float fL = s90.l(f, 0.0f, 1.0f);
        if (this.E != fL) {
            this.E = fL;
            a();
        }
    }

    @Override // defpackage.kv0
    public void setShapeAppearanceModel(zu0 zu0Var) {
        zu0 zu0VarH = zu0Var.h(new no(18));
        this.G = zu0VarH;
        lv0 lv0Var = this.H;
        lv0Var.c = zu0VarH;
        lv0Var.c();
        lv0Var.a(this);
    }

    public MaskableFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaskableFrameLayout(Context context, AttributeSet attributeSet, int i) {
        lv0 mv0Var;
        super(context, attributeSet, i);
        this.E = -1.0f;
        this.F = new RectF();
        if (Build.VERSION.SDK_INT >= 33) {
            mv0Var = new nv0(this);
        } else {
            mv0Var = new mv0(this);
        }
        this.H = mv0Var;
        this.I = null;
        setShapeAppearanceModel(zu0.c(context, attributeSet, i, 0).a());
    }

    @Override // defpackage.hc0
    public void setOnMaskChangedListener(hj0 hj0Var) {
    }
}
