package com.google.android.material.circularreveal.cardview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.google.android.material.card.MaterialCardView;
import defpackage.fd;
import defpackage.gd;
import defpackage.o2;

/* JADX INFO: loaded from: classes.dex */
public class CircularRevealCardView extends MaterialCardView implements gd {
    public final o2 q;

    public CircularRevealCardView(Context context) {
        this(context, null);
    }

    @Override // defpackage.gd
    public final void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // defpackage.gd
    public final void d() {
        this.q.getClass();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        o2 o2Var = this.q;
        if (o2Var != null) {
            o2Var.f(canvas);
        } else {
            super.draw(canvas);
        }
    }

    @Override // defpackage.gd
    public final void g() {
        this.q.getClass();
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return (Drawable) this.q.f;
    }

    @Override // defpackage.gd
    public int getCircularRevealScrimColor() {
        return ((Paint) this.q.d).getColor();
    }

    @Override // defpackage.gd
    public fd getRevealInfo() {
        return this.q.h();
    }

    @Override // defpackage.gd
    public final boolean h() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public final boolean isOpaque() {
        o2 o2Var = this.q;
        return o2Var != null ? o2Var.k() : super.isOpaque();
    }

    @Override // defpackage.gd
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.q.q(drawable);
    }

    @Override // defpackage.gd
    public void setCircularRevealScrimColor(int i) {
        this.q.r(i);
    }

    @Override // defpackage.gd
    public void setRevealInfo(fd fdVar) {
        this.q.s(fdVar);
    }

    public CircularRevealCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = new o2(this);
    }
}
