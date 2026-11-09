package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class t50 extends Drawable.ConstantState {
    public ki0 a;
    public bn b;
    public ColorStateList c;
    public ColorStateList d;
    public ColorStateList e;
    public PorterDuff.Mode f;
    public Rect g;
    public final float h;
    public float i;
    public float j;
    public int k;
    public float l;
    public float m;
    public int n;
    public int o;
    public int p;
    public final int q;
    public Paint.Style r;

    public t50(ki0 ki0Var) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = PorterDuff.Mode.SRC_IN;
        this.g = null;
        this.h = 1.0f;
        this.i = 1.0f;
        this.k = 255;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = Paint.Style.FILL_AND_STROKE;
        this.a = ki0Var;
        this.b = null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable() {
        u50 u50Var = new u50(this);
        u50Var.f = true;
        return u50Var;
    }

    public t50(t50 t50Var) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = PorterDuff.Mode.SRC_IN;
        this.g = null;
        this.h = 1.0f;
        this.i = 1.0f;
        this.k = 255;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = Paint.Style.FILL_AND_STROKE;
        this.a = t50Var.a;
        this.b = t50Var.b;
        this.j = t50Var.j;
        this.c = t50Var.c;
        this.d = t50Var.d;
        this.f = t50Var.f;
        this.e = t50Var.e;
        this.k = t50Var.k;
        this.h = t50Var.h;
        this.p = t50Var.p;
        this.n = t50Var.n;
        this.i = t50Var.i;
        this.l = t50Var.l;
        this.m = t50Var.m;
        this.o = t50Var.o;
        this.q = t50Var.q;
        this.r = t50Var.r;
        if (t50Var.g != null) {
            this.g = new Rect(t50Var.g);
        }
    }
}
