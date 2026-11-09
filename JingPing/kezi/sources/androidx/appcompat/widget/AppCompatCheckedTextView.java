package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import defpackage.c5;
import defpackage.e4;
import defpackage.g4;
import defpackage.ko0;
import defpackage.ll;
import defpackage.po;
import defpackage.rp0;
import defpackage.vn0;
import defpackage.xr0;
import defpackage.y5;
import defpackage.za0;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatCheckedTextView extends CheckedTextView {
    public final g4 b;
    public final e4 c;
    public final y5 d;
    public c5 e;

    public AppCompatCheckedTextView(Context context) {
        this(context, null);
    }

    private c5 getEmojiTextViewHelper() {
        if (this.e == null) {
            this.e = new c5(this);
        }
        return this.e;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        y5 y5Var = this.d;
        if (y5Var != null) {
            y5Var.b();
        }
        e4 e4Var = this.c;
        if (e4Var != null) {
            e4Var.a();
        }
        g4 g4Var = this.b;
        if (g4Var != null) {
            g4Var.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return rp0.Z(super.getCustomSelectionActionModeCallback());
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

    public ColorStateList getSupportCheckMarkTintList() {
        g4 g4Var = this.b;
        if (g4Var != null) {
            return g4Var.a;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCheckMarkTintMode() {
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

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        za0.E(editorInfo, inputConnectionOnCreateInputConnection, this);
        return inputConnectionOnCreateInputConnection;
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

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(Drawable drawable) {
        super.setCheckMarkDrawable(drawable);
        g4 g4Var = this.b;
        if (g4Var != null) {
            if (g4Var.e) {
                g4Var.e = false;
            } else {
                g4Var.e = true;
                g4Var.b();
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

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(rp0.a0(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().d(z);
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

    public void setSupportCheckMarkTintList(ColorStateList colorStateList) {
        g4 g4Var = this.b;
        if (g4Var != null) {
            g4Var.a = colorStateList;
            g4Var.c = true;
            g4Var.b();
        }
    }

    public void setSupportCheckMarkTintMode(PorterDuff.Mode mode) {
        g4 g4Var = this.b;
        if (g4Var != null) {
            g4Var.b = mode;
            g4Var.d = true;
            g4Var.b();
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

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        y5 y5Var = this.d;
        if (y5Var != null) {
            y5Var.g(context, i);
        }
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.checkedTextViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i) {
        int resourceId;
        int resourceId2;
        super(context, attributeSet, i);
        ko0.a(context);
        vn0.a(this, getContext());
        y5 y5Var = new y5(this);
        this.d = y5Var;
        y5Var.f(attributeSet, i);
        y5Var.b();
        e4 e4Var = new e4(this);
        this.c = e4Var;
        e4Var.e(attributeSet, i);
        this.b = new g4(this);
        po poVarN = po.N(getContext(), attributeSet, R$styleable.CheckedTextView, i);
        TypedArray typedArray = (TypedArray) poVarN.d;
        xr0.o(this, getContext(), R$styleable.CheckedTextView, attributeSet, (TypedArray) poVarN.d, i);
        try {
            if (typedArray.hasValue(R$styleable.CheckedTextView_checkMarkCompat) && (resourceId2 = typedArray.getResourceId(R$styleable.CheckedTextView_checkMarkCompat, 0)) != 0) {
                try {
                    setCheckMarkDrawable(rp0.y(getContext(), resourceId2));
                } catch (Resources.NotFoundException unused) {
                    if (typedArray.hasValue(R$styleable.CheckedTextView_android_checkMark)) {
                        setCheckMarkDrawable(rp0.y(getContext(), resourceId));
                    }
                }
            } else if (typedArray.hasValue(R$styleable.CheckedTextView_android_checkMark) && (resourceId = typedArray.getResourceId(R$styleable.CheckedTextView_android_checkMark, 0)) != 0) {
                setCheckMarkDrawable(rp0.y(getContext(), resourceId));
            }
            if (typedArray.hasValue(R$styleable.CheckedTextView_checkMarkTint)) {
                setCheckMarkTintList(poVarN.w(R$styleable.CheckedTextView_checkMarkTint));
            }
            if (typedArray.hasValue(R$styleable.CheckedTextView_checkMarkTintMode)) {
                setCheckMarkTintMode(ll.c(typedArray.getInt(R$styleable.CheckedTextView_checkMarkTintMode, -1), null));
            }
            poVarN.P();
            getEmojiTextViewHelper().b(attributeSet, i);
        } catch (Throwable th) {
            poVarN.P();
            throw th;
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(rp0.y(getContext(), i));
    }
}
