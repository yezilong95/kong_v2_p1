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
import defpackage.c7;
import defpackage.f51;
import defpackage.fa0;
import defpackage.h6;
import defpackage.i5;
import defpackage.j01;
import defpackage.k5;
import defpackage.op;
import defpackage.ou;
import defpackage.qm;
import defpackage.w01;
import defpackage.z91;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatCheckedTextView extends CheckedTextView {
    public final k5 E;
    public final i5 F;
    public final c7 G;
    public h6 H;

    public AppCompatCheckedTextView(Context context) {
        this(context, null);
    }

    private h6 getEmojiTextViewHelper() {
        if (this.H == null) {
            this.H = new h6(this);
        }
        return this.H;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        c7 c7Var = this.G;
        if (c7Var != null) {
            c7Var.b();
        }
        i5 i5Var = this.F;
        if (i5Var != null) {
            i5Var.d();
        }
        k5 k5Var = this.E;
        if (k5Var != null) {
            k5Var.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return fa0.O0(super.getCustomSelectionActionModeCallback());
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

    public ColorStateList getSupportCheckMarkTintList() {
        k5 k5Var = this.E;
        if (k5Var != null) {
            return k5Var.a;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCheckMarkTintMode() {
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

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        z91.U(editorInfo, inputConnectionOnCreateInputConnection, this);
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

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(Drawable drawable) {
        super.setCheckMarkDrawable(drawable);
        k5 k5Var = this.E;
        if (k5Var != null) {
            if (k5Var.e) {
                k5Var.e = false;
            } else {
                k5Var.e = true;
                k5Var.b();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        c7 c7Var = this.G;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        c7 c7Var = this.G;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(fa0.P0(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().d(z);
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

    public void setSupportCheckMarkTintList(ColorStateList colorStateList) {
        k5 k5Var = this.E;
        if (k5Var != null) {
            k5Var.a = colorStateList;
            k5Var.c = true;
            k5Var.b();
        }
    }

    public void setSupportCheckMarkTintMode(PorterDuff.Mode mode) {
        k5 k5Var = this.E;
        if (k5Var != null) {
            k5Var.b = mode;
            k5Var.d = true;
            k5Var.b();
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

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        c7 c7Var = this.G;
        if (c7Var != null) {
            c7Var.g(context, i);
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
        w01.a(context);
        j01.a(this, getContext());
        c7 c7Var = new c7(this);
        this.G = c7Var;
        c7Var.f(attributeSet, i);
        c7Var.b();
        i5 i5Var = new i5(this);
        this.F = i5Var;
        i5Var.h(attributeSet, i);
        this.E = new k5(this);
        ou ouVarP = ou.P(getContext(), attributeSet, R$styleable.CheckedTextView, i);
        TypedArray typedArray = (TypedArray) ouVarP.G;
        f51.n(this, getContext(), R$styleable.CheckedTextView, attributeSet, (TypedArray) ouVarP.G, i);
        try {
            if (typedArray.hasValue(R$styleable.CheckedTextView_checkMarkCompat) && (resourceId2 = typedArray.getResourceId(R$styleable.CheckedTextView_checkMarkCompat, 0)) != 0) {
                try {
                    setCheckMarkDrawable(qm.z(getContext(), resourceId2));
                } catch (Resources.NotFoundException unused) {
                    if (typedArray.hasValue(R$styleable.CheckedTextView_android_checkMark)) {
                        setCheckMarkDrawable(qm.z(getContext(), resourceId));
                    }
                }
            } else if (typedArray.hasValue(R$styleable.CheckedTextView_android_checkMark) && (resourceId = typedArray.getResourceId(R$styleable.CheckedTextView_android_checkMark, 0)) != 0) {
                setCheckMarkDrawable(qm.z(getContext(), resourceId));
            }
            if (typedArray.hasValue(R$styleable.CheckedTextView_checkMarkTint)) {
                setCheckMarkTintList(ouVarP.y(R$styleable.CheckedTextView_checkMarkTint));
            }
            if (typedArray.hasValue(R$styleable.CheckedTextView_checkMarkTintMode)) {
                setCheckMarkTintMode(op.c(typedArray.getInt(R$styleable.CheckedTextView_checkMarkTintMode, -1), null));
            }
            ouVarP.S();
            getEmojiTextViewHelper().b(attributeSet, i);
        } catch (Throwable th) {
            ouVarP.S();
            throw th;
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(qm.z(getContext(), i));
    }
}
