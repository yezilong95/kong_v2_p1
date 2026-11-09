package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.appcompat.R$attr;
import defpackage.c7;
import defpackage.fa0;
import defpackage.h6;
import defpackage.i5;
import defpackage.j01;
import defpackage.l7;
import defpackage.q61;
import defpackage.w01;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatButton extends Button {
    public final i5 E;
    public final c7 F;
    public h6 G;

    public AppCompatButton(Context context) {
        this(context, null);
    }

    private h6 getEmojiTextViewHelper() {
        if (this.G == null) {
            this.G = new h6(this);
        }
        return this.G;
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.d();
        }
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (q61.b) {
            return super.getAutoSizeMaxTextSize();
        }
        c7 c7Var = this.F;
        if (c7Var != null) {
            return Math.round(c7Var.i.e);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (q61.b) {
            return super.getAutoSizeMinTextSize();
        }
        c7 c7Var = this.F;
        if (c7Var != null) {
            return Math.round(c7Var.i.d);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (q61.b) {
            return super.getAutoSizeStepGranularity();
        }
        c7 c7Var = this.F;
        if (c7Var != null) {
            return Math.round(c7Var.i.c);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (q61.b) {
            return super.getAutoSizeTextAvailableSizes();
        }
        c7 c7Var = this.F;
        return c7Var != null ? c7Var.i.f : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (q61.b) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        c7 c7Var = this.F;
        if (c7Var != null) {
            return c7Var.i.a;
        }
        return 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return fa0.O0(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        i5 i5Var = this.E;
        if (i5Var != null) {
            return i5Var.f();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        i5 i5Var = this.E;
        if (i5Var != null) {
            return i5Var.g();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.F.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.F.e();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        c7 c7Var = this.F;
        if (c7Var == null || q61.b) {
            return;
        }
        c7Var.i.a();
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        c7 c7Var = this.F;
        if (c7Var == null || q61.b) {
            return;
        }
        l7 l7Var = c7Var.i;
        if (l7Var.f()) {
            l7Var.a();
        }
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (q61.b) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.i(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (q61.b) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.j(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (q61.b) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.k(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.i();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.j(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(fa0.P0(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().d(z);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    public void setSupportAllCaps(boolean z) {
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.a.setAllCaps(z);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        i5 i5Var = this.E;
        if (i5Var != null) {
            i5Var.m(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        c7 c7Var = this.F;
        c7Var.l(colorStateList);
        c7Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        c7 c7Var = this.F;
        c7Var.m(mode);
        c7Var.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.g(context, i);
        }
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i, float f) {
        boolean z = q61.b;
        if (z) {
            super.setTextSize(i, f);
            return;
        }
        c7 c7Var = this.F;
        if (c7Var == null || z) {
            return;
        }
        l7 l7Var = c7Var.i;
        if (l7Var.f()) {
            return;
        }
        l7Var.g(i, f);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.buttonStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        w01.a(context);
        j01.a(this, getContext());
        i5 i5Var = new i5(this);
        this.E = i5Var;
        i5Var.h(attributeSet, i);
        c7 c7Var = new c7(this);
        this.F = c7Var;
        c7Var.f(attributeSet, i);
        c7Var.b();
        getEmojiTextViewHelper().b(attributeSet, i);
    }
}
