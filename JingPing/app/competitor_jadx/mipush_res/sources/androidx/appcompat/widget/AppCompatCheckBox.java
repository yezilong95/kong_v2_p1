package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.CheckBox;
import androidx.appcompat.R$attr;
import defpackage.c7;
import defpackage.h6;
import defpackage.i5;
import defpackage.j01;
import defpackage.k5;
import defpackage.qm;
import defpackage.w01;
import defpackage.z01;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatCheckBox extends CheckBox implements z01 {
    public final k5 E;
    public final i5 F;
    public final c7 G;
    public h6 H;

    public AppCompatCheckBox(Context context) {
        this(context, null);
    }

    private h6 getEmojiTextViewHelper() {
        if (this.H == null) {
            this.H = new h6(this);
        }
        return this.H;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        i5 i5Var = this.F;
        if (i5Var != null) {
            i5Var.d();
        }
        c7 c7Var = this.G;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        k5 k5Var = this.E;
        if (k5Var != null) {
            k5Var.getClass();
        }
        return compoundPaddingLeft;
    }

    public ColorStateList getSupportBackgroundTintList() {
        i5 i5Var = this.F;
        if (i5Var != null) {
            return i5Var.f();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        i5 i5Var = this.F;
        if (i5Var != null) {
            return i5Var.g();
        }
        return null;
    }

    @Override // defpackage.z01
    public ColorStateList getSupportButtonTintList() {
        k5 k5Var = this.E;
        if (k5Var != null) {
            return k5Var.a;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        k5 k5Var = this.E;
        if (k5Var != null) {
            return k5Var.b;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.G.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.G.e();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        i5 i5Var = this.F;
        if (i5Var != null) {
            i5Var.i();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        i5 i5Var = this.F;
        if (i5Var != null) {
            i5Var.j(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        k5 k5Var = this.E;
        if (k5Var != null) {
            if (k5Var.e) {
                k5Var.e = false;
            } else {
                k5Var.e = true;
                k5Var.a();
            }
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        c7 c7Var = this.G;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        c7 c7Var = this.G;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().d(z);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        i5 i5Var = this.F;
        if (i5Var != null) {
            i5Var.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        i5 i5Var = this.F;
        if (i5Var != null) {
            i5Var.m(mode);
        }
    }

    @Override // defpackage.z01
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        k5 k5Var = this.E;
        if (k5Var != null) {
            k5Var.a = colorStateList;
            k5Var.c = true;
            k5Var.a();
        }
    }

    @Override // defpackage.z01
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        k5 k5Var = this.E;
        if (k5Var != null) {
            k5Var.b = mode;
            k5Var.d = true;
            k5Var.a();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        c7 c7Var = this.G;
        c7Var.l(colorStateList);
        c7Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        c7 c7Var = this.G;
        c7Var.m(mode);
        c7Var.b();
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.checkboxStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        w01.a(context);
        j01.a(this, getContext());
        k5 k5Var = new k5(this);
        this.E = k5Var;
        k5Var.c(attributeSet, i);
        i5 i5Var = new i5(this);
        this.F = i5Var;
        i5Var.h(attributeSet, i);
        c7 c7Var = new c7(this);
        this.G = c7Var;
        c7Var.f(attributeSet, i);
        getEmojiTextViewHelper().b(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(qm.z(getContext(), i));
    }
}
