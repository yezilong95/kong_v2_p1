package com.google.android.material.circularreveal.coordinatorlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.fd;
import defpackage.gd;
import defpackage.o2;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealCoordinatorLayout extends CoordinatorLayout implements gd {
    public final o2 z;

    public CircularRevealCoordinatorLayout(Context context) {
        this(context, null);
    }

    @Override // defpackage.gd
    public final void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // defpackage.gd
    public final void d() {
        this.z.getClass();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        o2 o2Var = this.z;
        if (o2Var != null) {
            o2Var.f(canvas);
        } else {
            super.draw(canvas);
        }
    }

    @Override // defpackage.gd
    public final void g() {
        this.z.getClass();
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return (Drawable) this.z.f;
    }

    @Override // defpackage.gd
    public int getCircularRevealScrimColor() {
        return ((Paint) this.z.d).getColor();
    }

    @Override // defpackage.gd
    public fd getRevealInfo() {
        return this.z.h();
    }

    @Override // defpackage.gd
    public final boolean h() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public final boolean isOpaque() {
        o2 o2Var = this.z;
        return o2Var != null ? o2Var.k() : super.isOpaque();
    }

    @Override // defpackage.gd
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.z.q(drawable);
    }

    @Override // defpackage.gd
    public void setCircularRevealScrimColor(int i) {
        this.z.r(i);
    }

    @Override // defpackage.gd
    public void setRevealInfo(fd fdVar) {
        this.z.s(fdVar);
    }

    public CircularRevealCoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.z = new o2(this);
    }
}
