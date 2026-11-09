package com.google.android.material.circularreveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.GridLayout;
import defpackage.fd;
import defpackage.gd;
import defpackage.o2;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealGridLayout extends GridLayout implements gd {
    public final o2 b;

    public CircularRevealGridLayout(Context context) {
        this(context, null);
    }

    @Override // defpackage.gd
    public final void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // defpackage.gd
    public final void d() {
        this.b.getClass();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        o2 o2Var = this.b;
        if (o2Var != null) {
            o2Var.f(canvas);
        } else {
            super.draw(canvas);
        }
    }

    @Override // defpackage.gd
    public final void g() {
        this.b.getClass();
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return (Drawable) this.b.f;
    }

    @Override // defpackage.gd
    public int getCircularRevealScrimColor() {
        return ((Paint) this.b.d).getColor();
    }

    @Override // defpackage.gd
    public fd getRevealInfo() {
        return this.b.h();
    }

    @Override // defpackage.gd
    public final boolean h() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public final boolean isOpaque() {
        o2 o2Var = this.b;
        return o2Var != null ? o2Var.k() : super.isOpaque();
    }

    @Override // defpackage.gd
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.b.q(drawable);
    }

    @Override // defpackage.gd
    public void setCircularRevealScrimColor(int i) {
        this.b.r(i);
    }

    @Override // defpackage.gd
    public void setRevealInfo(fd fdVar) {
        this.b.s(fdVar);
    }

    public CircularRevealGridLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new o2(this);
    }
}
