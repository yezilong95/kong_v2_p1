package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;
import androidx.appcompat.R$attr;
import defpackage.e4;
import defpackage.e5;
import defpackage.ko0;
import defpackage.po;
import defpackage.rp0;
import defpackage.vn0;
import defpackage.y5;
import defpackage.za0;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatMultiAutoCompleteTextView extends MultiAutoCompleteTextView {
    public static final int[] e = {R.attr.popupBackground};
    public final e4 b;
    public final y5 c;
    public final e5 d;

    public AppCompatMultiAutoCompleteTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.a();
        }
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        e4 e4Var = this.b;
        if (e4Var != null) {
            return e4Var.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e4 e4Var = this.b;
        if (e4Var != null) {
            return e4Var.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.c.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.c.e();
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        za0.E(editorInfo, inputConnectionOnCreateInputConnection, this);
        return this.d.F(inputConnectionOnCreateInputConnection, editorInfo);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.f();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.g(i);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.b();
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(rp0.y(getContext(), i));
    }

    public void setEmojiCompatEnabled(boolean z) {
        this.d.Q(z);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.d.y(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e4 e4Var = this.b;
        if (e4Var != null) {
            e4Var.j(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        y5 y5Var = this.c;
        y5Var.l(colorStateList);
        y5Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        y5 y5Var = this.c;
        y5Var.m(mode);
        y5Var.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.g(context, i);
        }
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ko0.a(context);
        vn0.a(this, getContext());
        po poVarN = po.N(getContext(), attributeSet, e, i);
        if (((TypedArray) poVarN.d).hasValue(0)) {
            setDropDownBackgroundDrawable(poVarN.y(0));
        }
        poVarN.P();
        e4 e4Var = new e4(this);
        this.b = e4Var;
        e4Var.e(attributeSet, i);
        y5 y5Var = new y5(this);
        this.c = y5Var;
        y5Var.f(attributeSet, i);
        y5Var.b();
        e5 e5Var = new e5(this, 3);
        this.d = e5Var;
        e5Var.E(attributeSet, i);
        KeyListener keyListener = getKeyListener();
        if (keyListener instanceof NumberKeyListener) {
            return;
        }
        boolean zIsFocusable = super.isFocusable();
        boolean zIsClickable = super.isClickable();
        boolean zIsLongClickable = super.isLongClickable();
        int inputType = super.getInputType();
        KeyListener keyListenerY = e5Var.y(keyListener);
        if (keyListenerY == keyListener) {
            return;
        }
        super.setKeyListener(keyListenerY);
        super.setRawInputType(inputType);
        super.setFocusable(zIsFocusable);
        super.setClickable(zIsClickable);
        super.setLongClickable(zIsLongClickable);
    }
}
