package com.google.android.material.circularreveal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import defpackage.af;
import defpackage.c4;
import defpackage.ze;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealLinearLayout extends LinearLayout implements af {
    public final c4 E;

    public CircularRevealLinearLayout(Context context) {
        this(context, null);
    }

    @Override // defpackage.af
    public final void d() {
        this.E.getClass();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        c4 c4Var = this.E;
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
        this.E.getClass();
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return (Drawable) this.E.f;
    }

    @Override // defpackage.af
    public int getCircularRevealScrimColor() {
        return ((Paint) this.E.d).getColor();
    }

    @Override // defpackage.af
    public ze getRevealInfo() {
        return this.E.g();
    }

    @Override // defpackage.af
    public final boolean h() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public final boolean isOpaque() {
        c4 c4Var = this.E;
        return c4Var != null ? c4Var.i() : super.isOpaque();
    }

    @Override // defpackage.af
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.E.m(drawable);
    }

    @Override // defpackage.af
    public void setCircularRevealScrimColor(int i) {
        this.E.n(i);
    }

    @Override // defpackage.af
    public void setRevealInfo(ze zeVar) {
        this.E.o(zeVar);
    }

    public CircularRevealLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = new c4(this);
    }
}
