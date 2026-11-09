package com.google.android.material.circularreveal.cardview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.google.android.material.card.MaterialCardView;
import defpackage.af;
import defpackage.c4;
import defpackage.ze;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealCardView extends MaterialCardView implements af {
    public final c4 T;

    public CircularRevealCardView(Context context) {
        this(context, null);
    }

    @Override // defpackage.af
    public final void d() {
        this.T.getClass();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        c4 c4Var = this.T;
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
        this.T.getClass();
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return (Drawable) this.T.f;
    }

    @Override // defpackage.af
    public int getCircularRevealScrimColor() {
        return ((Paint) this.T.d).getColor();
    }

    @Override // defpackage.af
    public ze getRevealInfo() {
        return this.T.g();
    }

    @Override // defpackage.af
    public final boolean h() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public final boolean isOpaque() {
        c4 c4Var = this.T;
        return c4Var != null ? c4Var.i() : super.isOpaque();
    }

    @Override // defpackage.af
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.T.m(drawable);
    }

    @Override // defpackage.af
    public void setCircularRevealScrimColor(int i) {
        this.T.n(i);
    }

    @Override // defpackage.af
    public void setRevealInfo(ze zeVar) {
        this.T.o(zeVar);
    }

    public CircularRevealCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.T = new c4(this);
    }
}
