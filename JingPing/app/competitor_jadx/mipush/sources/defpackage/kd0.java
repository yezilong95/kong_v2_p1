package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class kd0 extends Drawable.ConstantState {
    public zu0 a;
    public ts b;
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

    public kd0(zu0 zu0Var) {
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
        this.a = zu0Var;
        this.b = null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable() {
        ld0 ld0Var = new ld0(this);
        ld0Var.I = true;
        return ld0Var;
    }

    public kd0(kd0 kd0Var) {
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
        this.a = kd0Var.a;
        this.b = kd0Var.b;
        this.j = kd0Var.j;
        this.c = kd0Var.c;
        this.d = kd0Var.d;
        this.f = kd0Var.f;
        this.e = kd0Var.e;
        this.k = kd0Var.k;
        this.h = kd0Var.h;
        this.p = kd0Var.p;
        this.n = kd0Var.n;
        this.i = kd0Var.i;
        this.l = kd0Var.l;
        this.m = kd0Var.m;
        this.o = kd0Var.o;
        this.q = kd0Var.q;
        this.r = kd0Var.r;
        if (kd0Var.g != null) {
            this.g = new Rect(kd0Var.g);
        }
    }
}
