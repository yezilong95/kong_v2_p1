package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.RadioButton;
import androidx.appcompat.R$attr;
import defpackage.c5;
import defpackage.e4;
import defpackage.g4;
import defpackage.ko0;
import defpackage.mo0;
import defpackage.rp0;
import defpackage.vn0;
import defpackage.y5;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatRadioButton extends RadioButton implements mo0 {
    public final g4 b;
    public final e4 c;
    public final y5 d;
    public c5 e;

    public AppCompatRadioButton(Context context) {
        this(context, null);
    }

    private c5 getEmojiTextViewHelper() {
        if (this.e == null) {
            this.e = new c5(this);
        }
        return this.e;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        e4 e4Var = this.c;
        if (e4Var != null) {
            e4Var.a();
        }
        y5 y5Var = this.d;
        if (y5Var != null) {
            y5Var.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        g4 g4Var = this.b;
        if (g4Var != null) {
            g4Var.getClass();
        }
        return compoundPaddingLeft;
    }

    public ColorStateList getSupportBackgroundTintList() {
        e4 e4Var = this.c;
        if (e4Var != null) {
            return e4Var.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e4 e4Var = this.c;
        if (e4Var != null) {
            return e4Var.d();
        }
        return null;
    }

    @Override // defpackage.mo0
    public ColorStateList getSupportButtonTintList() {
        g4 g4Var = this.b;
        if (g4Var != null) {
            return g4Var.a;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        g4 g4Var = this.b;
        if (g4Var != null) {
            return g4Var.b;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.d.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.d.e();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e4 e4Var = this.c;
        if (e4Var != null) {
            e4Var.f();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e4 e4Var = this.c;
        if (e4Var != null) {
            e4Var.g(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        g4 g4Var = this.b;
        if (g4Var != null) {
            if (g4Var.e) {
                g4Var.e = false;
            } else {
                g4Var.e = true;
                g4Var.a();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        y5 y5Var = this.d;
        if (y5Var != null) {
            y5Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        y5 y5Var = this.d;
        if (y5Var != null) {
            y5Var.b();
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
        e4 e4Var = this.c;
        if (e4Var != null) {
            e4Var.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e4 e4Var = this.c;
        if (e4Var != null) {
            e4Var.j(mode);
        }
    }

    @Override // defpackage.mo0
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        g4 g4Var = this.b;
        if (g4Var != null) {
            g4Var.a = colorStateList;
            g4Var.c = true;
            g4Var.a();
        }
    }

    @Override // defpackage.mo0
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        g4 g4Var = this.b;
        if (g4Var != null) {
            g4Var.b = mode;
            g4Var.d = true;
            g4Var.a();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        y5 y5Var = this.d;
        y5Var.l(colorStateList);
        y5Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        y5 y5Var = this.d;
        y5Var.m(mode);
        y5Var.b();
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.radioButtonStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ko0.a(context);
        vn0.a(this, getContext());
        g4 g4Var = new g4(this);
        this.b = g4Var;
        g4Var.c(attributeSet, i);
        e4 e4Var = new e4(this);
        this.c = e4Var;
        e4Var.e(attributeSet, i);
        y5 y5Var = new y5(this);
        this.d = y5Var;
        y5Var.f(attributeSet, i);
        getEmojiTextViewHelper().b(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(rp0.y(getContext(), i));
    }
}
