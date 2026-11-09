package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import com.google.android.material.R$attr;
import com.google.android.material.button.MaterialButton;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class tc0 {
    public final MaterialButton a;
    public zu0 b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public PorterDuff.Mode i;
    public ColorStateList j;
    public ColorStateList k;
    public ColorStateList l;
    public ld0 m;
    public boolean q;
    public RippleDrawable s;
    public int t;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;
    public boolean r = true;

    public tc0(MaterialButton materialButton, zu0 zu0Var) {
        this.a = materialButton;
        this.b = zu0Var;
    }

    public final kv0 a() {
        RippleDrawable rippleDrawable = this.s;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.s.getNumberOfLayers() > 2 ? (kv0) this.s.getDrawable(2) : (kv0) this.s.getDrawable(1);
    }

    public final ld0 b(boolean z) {
        RippleDrawable rippleDrawable = this.s;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return (ld0) ((LayerDrawable) ((InsetDrawable) this.s.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0);
    }

    public final void c(zu0 zu0Var) {
        this.b = zu0Var;
        if (b(false) != null) {
            b(false).setShapeAppearanceModel(zu0Var);
        }
        if (b(true) != null) {
            b(true).setShapeAppearanceModel(zu0Var);
        }
        if (a() != null) {
            a().setShapeAppearanceModel(zu0Var);
        }
    }

    public final void d(int i, int i2) {
        WeakHashMap weakHashMap = f51.a;
        MaterialButton materialButton = this.a;
        int paddingStart = materialButton.getPaddingStart();
        int paddingTop = materialButton.getPaddingTop();
        int paddingEnd = materialButton.getPaddingEnd();
        int paddingBottom = materialButton.getPaddingBottom();
        int i3 = this.e;
        int i4 = this.f;
        this.f = i2;
        this.e = i;
        if (!this.o) {
            e();
        }
        materialButton.setPaddingRelative(paddingStart, (paddingTop + i) - i3, paddingEnd, (paddingBottom + i2) - i4);
    }

    public final void e() {
        ld0 ld0Var = new ld0(this.b);
        MaterialButton materialButton = this.a;
        ld0Var.k(materialButton.getContext());
        ip.h(ld0Var, this.j);
        PorterDuff.Mode mode = this.i;
        if (mode != null) {
            ip.i(ld0Var, mode);
        }
        float f = this.h;
        ColorStateList colorStateList = this.k;
        ld0Var.s(f);
        ld0Var.r(colorStateList);
        ld0 ld0Var2 = new ld0(this.b);
        ld0Var2.setTint(0);
        float f2 = this.h;
        int iP = this.n ? fa0.P(materialButton, R$attr.colorSurface) : 0;
        ld0Var2.s(f2);
        ld0Var2.r(ColorStateList.valueOf(iP));
        ld0 ld0Var3 = new ld0(this.b);
        this.m = ld0Var3;
        ip.g(ld0Var3, -1);
        RippleDrawable rippleDrawable = new RippleDrawable(gp0.c(this.l), new InsetDrawable((Drawable) new LayerDrawable(new Drawable[]{ld0Var2, ld0Var}), this.c, this.e, this.d, this.f), this.m);
        this.s = rippleDrawable;
        materialButton.setInternalBackground(rippleDrawable);
        ld0 ld0VarB = b(false);
        if (ld0VarB != null) {
            ld0VarB.l(this.t);
            ld0VarB.setState(materialButton.getDrawableState());
        }
    }

    public final void f() {
        ld0 ld0VarB = b(false);
        ld0 ld0VarB2 = b(true);
        if (ld0VarB != null) {
            float f = this.h;
            ColorStateList colorStateList = this.k;
            ld0VarB.s(f);
            ld0VarB.r(colorStateList);
            if (ld0VarB2 != null) {
                float f2 = this.h;
                int iP = this.n ? fa0.P(this.a, R$attr.colorSurface) : 0;
                ld0VarB2.s(f2);
                ld0VarB2.r(ColorStateList.valueOf(iP));
            }
        }
    }
}
