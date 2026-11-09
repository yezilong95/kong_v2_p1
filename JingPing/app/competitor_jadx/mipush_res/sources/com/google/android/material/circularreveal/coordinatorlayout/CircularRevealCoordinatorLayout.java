package com.google.android.material.circularreveal.coordinatorlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.af;
import defpackage.c4;
import defpackage.ze;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealCoordinatorLayout extends CoordinatorLayout implements af {
    public final c4 f0;

    public CircularRevealCoordinatorLayout(Context context) {
        this(context, null);
    }

    @Override // defpackage.af
    public final void d() {
        this.f0.getClass();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        c4 c4Var = this.f0;
        if (c4Var != null) {
            c4Var.e(canvas);
        } else {
            super.draw(canvas);
        }
    }

    @Override // defpackage.af
    public final void e(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // defpackage.af
    public final void f() {
        this.f0.getClass();
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return (Drawable) this.f0.f;
    }

    @Override // defpackage.af
    public int getCircularRevealScrimColor() {
        return ((Paint) this.f0.d).getColor();
    }

    @Override // defpackage.af
    public ze getRevealInfo() {
        return this.f0.g();
    }

    @Override // defpackage.af
    public final boolean h() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public final boolean isOpaque() {
        c4 c4Var = this.f0;
        return c4Var != null ? c4Var.i() : super.isOpaque();
    }

    @Override // defpackage.af
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.f0.m(drawable);
    }

    @Override // defpackage.af
    public void setCircularRevealScrimColor(int i) {
        this.f0.n(i);
    }

    @Override // defpackage.af
    public void setRevealInfo(ze zeVar) {
        this.f0.o(zeVar);
    }

    public CircularRevealCoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f0 = new c4(this);
    }
}
