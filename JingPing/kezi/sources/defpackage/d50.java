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
public final class d50 {
    public final MaterialButton a;
    public ki0 b;
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
    public u50 m;
    public boolean q;
    public RippleDrawable s;
    public int t;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;
    public boolean r = true;

    public d50(MaterialButton materialButton, ki0 ki0Var) {
        this.a = materialButton;
        this.b = ki0Var;
    }

    public final vi0 a() {
        RippleDrawable rippleDrawable = this.s;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.s.getNumberOfLayers() > 2 ? (vi0) this.s.getDrawable(2) : (vi0) this.s.getDrawable(1);
    }

    public final u50 b(boolean z) {
        RippleDrawable rippleDrawable = this.s;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return (u50) ((LayerDrawable) ((InsetDrawable) this.s.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0);
    }

    public final void c(ki0 ki0Var) {
        this.b = ki0Var;
        if (b(false) != null) {
            b(false).setShapeAppearanceModel(ki0Var);
        }
        if (b(true) != null) {
            b(true).setShapeAppearanceModel(ki0Var);
        }
        if (a() != null) {
            a().setShapeAppearanceModel(ki0Var);
        }
    }

    public final void d(int i, int i2) {
        WeakHashMap weakHashMap = xr0.a;
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
        u50 u50Var = new u50(this.b);
        MaterialButton materialButton = this.a;
        u50Var.k(materialButton.getContext());
        hl.h(u50Var, this.j);
        PorterDuff.Mode mode = this.i;
        if (mode != null) {
            hl.i(u50Var, mode);
        }
        float f = this.h;
        ColorStateList colorStateList = this.k;
        u50Var.s(f);
        u50Var.r(colorStateList);
        u50 u50Var2 = new u50(this.b);
        u50Var2.setTint(0);
        float f2 = this.h;
        int iQ = this.n ? k9.q(materialButton, R$attr.colorSurface) : 0;
        u50Var2.s(f2);
        u50Var2.r(ColorStateList.valueOf(iQ));
        u50 u50Var3 = new u50(this.b);
        this.m = u50Var3;
        hl.g(u50Var3, -1);
        RippleDrawable rippleDrawable = new RippleDrawable(lf0.c(this.l), new InsetDrawable((Drawable) new LayerDrawable(new Drawable[]{u50Var2, u50Var}), this.c, this.e, this.d, this.f), this.m);
        this.s = rippleDrawable;
        materialButton.setInternalBackground(rippleDrawable);
        u50 u50VarB = b(false);
        if (u50VarB != null) {
            u50VarB.l(this.t);
            u50VarB.setState(materialButton.getDrawableState());
        }
    }

    public final void f() {
        u50 u50VarB = b(false);
        u50 u50VarB2 = b(true);
        if (u50VarB != null) {
            float f = this.h;
            ColorStateList colorStateList = this.k;
            u50VarB.s(f);
            u50VarB.r(colorStateList);
            if (u50VarB2 != null) {
                float f2 = this.h;
                int iQ = this.n ? k9.q(this.a, R$attr.colorSurface) : 0;
                u50VarB2.s(f2);
                u50VarB2.r(ColorStateList.valueOf(iQ));
            }
        }
    }
}
