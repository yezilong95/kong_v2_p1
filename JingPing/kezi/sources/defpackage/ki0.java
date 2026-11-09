package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.google.android.material.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public final class ki0 {
    public static final je0 m = new je0(0.5f);
    public nv0 a = new yf0();
    public nv0 b = new yf0();
    public nv0 c = new yf0();
    public nv0 d = new yf0();
    public kh e = new e(0.0f);
    public kh f = new e(0.0f);
    public kh g = new e(0.0f);
    public kh h = new e(0.0f);
    public qm i = new qm();
    public qm j = new qm();
    public qm k = new qm();
    public qm l = new qm();

    public static ii0 a(Context context, int i, int i2) {
        return b(context, i, i2, new e(0));
    }

    public static ii0 b(Context context, int i, int i2, kh khVar) {
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
            kh khVarE = e(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSize, khVar);
            kh khVarE2 = e(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopLeft, khVarE);
            kh khVarE3 = e(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopRight, khVarE);
            kh khVarE4 = e(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomRight, khVarE);
            kh khVarE5 = e(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomLeft, khVarE);
            ii0 ii0Var = new ii0();
            nv0 nv0VarP = oy.p(i4);
            ii0Var.a = nv0VarP;
            ii0.b(nv0VarP);
            ii0Var.e = khVarE2;
            nv0 nv0VarP2 = oy.p(i5);
            ii0Var.b = nv0VarP2;
            ii0.b(nv0VarP2);
            ii0Var.f = khVarE3;
            nv0 nv0VarP3 = oy.p(i6);
            ii0Var.c = nv0VarP3;
            ii0.b(nv0VarP3);
            ii0Var.g = khVarE4;
            nv0 nv0VarP4 = oy.p(i7);
            ii0Var.d = nv0VarP4;
            ii0.b(nv0VarP4);
            ii0Var.h = khVarE5;
            return ii0Var;
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static ii0 c(Context context, AttributeSet attributeSet, int i, int i2) {
        return d(context, attributeSet, i, i2, new e(0));
    }

    public static ii0 d(Context context, AttributeSet attributeSet, int i, int i2, kh khVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialShape, i, i2);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearanceOverlay, 0);
        typedArrayObtainStyledAttributes.recycle();
        return b(context, resourceId, resourceId2, khVar);
    }

    public static kh e(TypedArray typedArray, int i, kh khVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i);
        if (typedValuePeekValue != null) {
            int i2 = typedValuePeekValue.type;
            if (i2 == 5) {
                return new e(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics()));
            }
            if (i2 == 6) {
                return new je0(typedValuePeekValue.getFraction(1.0f, 1.0f));
            }
        }
        return khVar;
    }

    public final boolean f(RectF rectF) {
        boolean z = this.l.getClass().equals(qm.class) && this.j.getClass().equals(qm.class) && this.i.getClass().equals(qm.class) && this.k.getClass().equals(qm.class);
        float fA = this.e.a(rectF);
        return z && ((this.f.a(rectF) > fA ? 1 : (this.f.a(rectF) == fA ? 0 : -1)) == 0 && (this.h.a(rectF) > fA ? 1 : (this.h.a(rectF) == fA ? 0 : -1)) == 0 && (this.g.a(rectF) > fA ? 1 : (this.g.a(rectF) == fA ? 0 : -1)) == 0) && ((this.b instanceof yf0) && (this.a instanceof yf0) && (this.c instanceof yf0) && (this.d instanceof yf0));
    }

    public final ii0 g() {
        ii0 ii0Var = new ii0();
        ii0Var.a = this.a;
        ii0Var.b = this.b;
        ii0Var.c = this.c;
        ii0Var.d = this.d;
        ii0Var.e = this.e;
        ii0Var.f = this.f;
        ii0Var.g = this.g;
        ii0Var.h = this.h;
        ii0Var.i = this.i;
        ii0Var.j = this.j;
        ii0Var.k = this.k;
        ii0Var.l = this.l;
        return ii0Var;
    }

    public final ki0 h(ji0 ji0Var) {
        ii0 ii0VarG = g();
        ii0VarG.e = ji0Var.a(this.e);
        ii0VarG.f = ji0Var.a(this.f);
        ii0VarG.h = ji0Var.a(this.h);
        ii0VarG.g = ji0Var.a(this.g);
        return ii0VarG.a();
    }
}
