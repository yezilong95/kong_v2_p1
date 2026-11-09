package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.google.android.material.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public final class zu0 {
    public static final jo0 m = new jo0(0.5f);
    public z91 a = new up0();
    public z91 b = new up0();
    public z91 c = new up0();
    public z91 d = new up0();
    public yi e = new o0(0.0f);
    public yi f = new o0(0.0f);
    public yi g = new o0(0.0f);
    public yi h = new o0(0.0f);
    public is i = new is(0);
    public is j = new is(0);
    public is k = new is(0);
    public is l = new is(0);

    public static xu0 a(Context context, int i, int i2) {
        return b(context, i, i2, new o0(0));
    }

    public static xu0 b(Context context, int i, int i2, yi yiVar) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
        if (i2 != 0) {
            contextThemeWrapper = new ContextThemeWrapper(contextThemeWrapper, i2);
        }
        TypedArray typedArrayObtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(R$styleable.ShapeAppearance);
        try {
            int i3 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamily, 0);
            int i4 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyTopLeft, i3);
            int i5 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyTopRight, i3);
            int i6 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyBottomRight, i3);
            int i7 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyBottomLeft, i3);
            yi yiVarE = e(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSize, yiVar);
            yi yiVarE2 = e(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopLeft, yiVarE);
            yi yiVarE3 = e(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopRight, yiVarE);
            yi yiVarE4 = e(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomRight, yiVarE);
            yi yiVarE5 = e(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomLeft, yiVarE);
            xu0 xu0Var = new xu0();
            z91 z91VarN = ma0.n(i4);
            xu0Var.a = z91VarN;
            xu0.b(z91VarN);
            xu0Var.e = yiVarE2;
            z91 z91VarN2 = ma0.n(i5);
            xu0Var.b = z91VarN2;
            xu0.b(z91VarN2);
            xu0Var.f = yiVarE3;
            z91 z91VarN3 = ma0.n(i6);
            xu0Var.c = z91VarN3;
            xu0.b(z91VarN3);
            xu0Var.g = yiVarE4;
            z91 z91VarN4 = ma0.n(i7);
            xu0Var.d = z91VarN4;
            xu0.b(z91VarN4);
            xu0Var.h = yiVarE5;
            return xu0Var;
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static xu0 c(Context context, AttributeSet attributeSet, int i, int i2) {
        return d(context, attributeSet, i, i2, new o0(0));
    }

    public static xu0 d(Context context, AttributeSet attributeSet, int i, int i2, yi yiVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialShape, i, i2);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearanceOverlay, 0);
        typedArrayObtainStyledAttributes.recycle();
        return b(context, resourceId, resourceId2, yiVar);
    }

    public static yi e(TypedArray typedArray, int i, yi yiVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i);
        if (typedValuePeekValue != null) {
            int i2 = typedValuePeekValue.type;
            if (i2 == 5) {
                return new o0(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics()));
            }
            if (i2 == 6) {
                return new jo0(typedValuePeekValue.getFraction(1.0f, 1.0f));
            }
        }
        return yiVar;
    }

    public final boolean f(RectF rectF) {
        boolean z = this.l.getClass().equals(is.class) && this.j.getClass().equals(is.class) && this.i.getClass().equals(is.class) && this.k.getClass().equals(is.class);
        float fA = this.e.a(rectF);
        return z && ((this.f.a(rectF) > fA ? 1 : (this.f.a(rectF) == fA ? 0 : -1)) == 0 && (this.h.a(rectF) > fA ? 1 : (this.h.a(rectF) == fA ? 0 : -1)) == 0 && (this.g.a(rectF) > fA ? 1 : (this.g.a(rectF) == fA ? 0 : -1)) == 0) && ((this.b instanceof up0) && (this.a instanceof up0) && (this.c instanceof up0) && (this.d instanceof up0));
    }

    public final xu0 g() {
        xu0 xu0Var = new xu0();
        xu0Var.a = this.a;
        xu0Var.b = this.b;
        xu0Var.c = this.c;
        xu0Var.d = this.d;
        xu0Var.e = this.e;
        xu0Var.f = this.f;
        xu0Var.g = this.g;
        xu0Var.h = this.h;
        xu0Var.i = this.i;
        xu0Var.j = this.j;
        xu0Var.k = this.k;
        xu0Var.l = this.l;
        return xu0Var;
    }

    public final zu0 h(yu0 yu0Var) {
        xu0 xu0VarG = g();
        xu0VarG.e = yu0Var.a(this.e);
        xu0VarG.f = yu0Var.a(this.f);
        xu0VarG.h = yu0Var.a(this.h);
        xu0VarG.g = yu0Var.a(this.g);
        return xu0VarG.a();
    }
}
