package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.R$styleable;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class e4 {
    public int a;
    public final Object b;
    public final Object c;
    public Object d;
    public Object e;
    public Object f;

    public e4(View view) {
        this.a = -1;
        this.b = view;
        this.c = a5.a();
    }

    public static e4 b(Context context, int i) {
        k9.f("Cannot create a CalendarItemStyle with a styleResId of 0", i != 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.MaterialCalendarItem);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetLeft, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetTop, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetRight, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList colorStateListS = xi.s(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemFillColor);
        ColorStateList colorStateListS2 = xi.s(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemTextColor);
        ColorStateList colorStateListS3 = xi.s(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MaterialCalendarItem_itemStrokeWidth, 0);
        ki0 ki0VarA = ki0.a(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendarItem_itemShapeAppearance, 0), typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).a();
        typedArrayObtainStyledAttributes.recycle();
        return new e4(colorStateListS, colorStateListS2, colorStateListS3, dimensionPixelSize, ki0VarA, rect);
    }

    public void a() {
        View view = (View) this.b;
        Drawable background = view.getBackground();
        if (background != null) {
            if (((qf) this.d) != null) {
                if (((qf) this.f) == null) {
                    this.f = new qf();
                }
                qf qfVar = (qf) this.f;
                qfVar.c = null;
                qfVar.b = false;
                qfVar.d = null;
                qfVar.a = false;
                WeakHashMap weakHashMap = xr0.a;
                ColorStateList colorStateListG = lr0.g(view);
                if (colorStateListG != null) {
                    qfVar.b = true;
                    qfVar.c = colorStateListG;
                }
                PorterDuff.Mode modeH = lr0.h(view);
                if (modeH != null) {
                    qfVar.a = true;
                    qfVar.d = modeH;
                }
                if (qfVar.b || qfVar.a) {
                    a5.e(background, qfVar, view.getDrawableState());
                    return;
                }
            }
            qf qfVar2 = (qf) this.e;
            if (qfVar2 != null) {
                a5.e(background, qfVar2, view.getDrawableState());
                return;
            }
            qf qfVar3 = (qf) this.d;
            if (qfVar3 != null) {
                a5.e(background, qfVar3, view.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        qf qfVar = (qf) this.e;
        if (qfVar != null) {
            return (ColorStateList) qfVar.c;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        qf qfVar = (qf) this.e;
        if (qfVar != null) {
            return (PorterDuff.Mode) qfVar.d;
        }
        return null;
    }

    public void e(AttributeSet attributeSet, int i) {
        ColorStateList colorStateListF;
        View view = (View) this.b;
        po poVarN = po.N(view.getContext(), attributeSet, androidx.appcompat.R$styleable.ViewBackgroundHelper, i);
        TypedArray typedArray = (TypedArray) poVarN.d;
        View view2 = (View) this.b;
        xr0.o(view2, view2.getContext(), androidx.appcompat.R$styleable.ViewBackgroundHelper, attributeSet, (TypedArray) poVarN.d, i);
        try {
            if (typedArray.hasValue(androidx.appcompat.R$styleable.ViewBackgroundHelper_android_background)) {
                this.a = typedArray.getResourceId(androidx.appcompat.R$styleable.ViewBackgroundHelper_android_background, -1);
                a5 a5Var = (a5) this.c;
                Context context = view.getContext();
                int i2 = this.a;
                synchronized (a5Var) {
                    colorStateListF = a5Var.a.f(context, i2);
                }
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            if (typedArray.hasValue(androidx.appcompat.R$styleable.ViewBackgroundHelper_backgroundTint)) {
                lr0.q(view, poVarN.w(androidx.appcompat.R$styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (typedArray.hasValue(androidx.appcompat.R$styleable.ViewBackgroundHelper_backgroundTintMode)) {
                lr0.r(view, ll.c(typedArray.getInt(androidx.appcompat.R$styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
            poVarN.P();
        } catch (Throwable th) {
            poVarN.P();
            throw th;
        }
    }

    public void f() {
        this.a = -1;
        h(null);
        a();
    }

    public void g(int i) {
        ColorStateList colorStateListF;
        this.a = i;
        a5 a5Var = (a5) this.c;
        if (a5Var != null) {
            Context context = ((View) this.b).getContext();
            synchronized (a5Var) {
                colorStateListF = a5Var.a.f(context, i);
            }
        } else {
            colorStateListF = null;
        }
        h(colorStateListF);
        a();
    }

    public void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (((qf) this.d) == null) {
                this.d = new qf();
            }
            qf qfVar = (qf) this.d;
            qfVar.c = colorStateList;
            qfVar.b = true;
        } else {
            this.d = null;
        }
        a();
    }

    public void i(ColorStateList colorStateList) {
        if (((qf) this.e) == null) {
            this.e = new qf();
        }
        qf qfVar = (qf) this.e;
        qfVar.c = colorStateList;
        qfVar.b = true;
        a();
    }

    public void j(PorterDuff.Mode mode) {
        if (((qf) this.e) == null) {
            this.e = new qf();
        }
        qf qfVar = (qf) this.e;
        qfVar.d = mode;
        qfVar.a = true;
        a();
    }

    public void k(TextView textView) {
        u50 u50Var = new u50();
        u50 u50Var2 = new u50();
        ki0 ki0Var = (ki0) this.f;
        u50Var.setShapeAppearanceModel(ki0Var);
        u50Var2.setShapeAppearanceModel(ki0Var);
        u50Var.m((ColorStateList) this.d);
        u50Var.s(this.a);
        u50Var.r((ColorStateList) this.e);
        ColorStateList colorStateList = (ColorStateList) this.c;
        textView.setTextColor(colorStateList);
        RippleDrawable rippleDrawable = new RippleDrawable(colorStateList.withAlpha(30), u50Var, u50Var2);
        Rect rect = (Rect) this.b;
        InsetDrawable insetDrawable = new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom);
        WeakHashMap weakHashMap = xr0.a;
        textView.setBackground(insetDrawable);
    }

    public e4(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, ki0 ki0Var, Rect rect) {
        k9.g(rect.left);
        k9.g(rect.top);
        k9.g(rect.right);
        k9.g(rect.bottom);
        this.b = rect;
        this.c = colorStateList2;
        this.d = colorStateList;
        this.e = colorStateList3;
        this.a = i;
        this.f = ki0Var;
    }
}
