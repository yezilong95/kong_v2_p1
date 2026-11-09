package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import defpackage.a6;
import defpackage.c5;
import defpackage.e4;
import defpackage.e5;
import defpackage.h6;
import defpackage.k9;
import defpackage.ko0;
import defpackage.kt0;
import defpackage.o70;
import defpackage.on0;
import defpackage.r5;
import defpackage.rp0;
import defpackage.sp0;
import defpackage.vn0;
import defpackage.y5;
import defpackage.yb0;
import defpackage.z5;
import defpackage.za0;
import defpackage.zb0;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatTextView extends TextView {
    public final e4 b;
    public final y5 c;
    public final e5 d;
    public c5 e;
    public boolean f;
    public o70 g;
    public Future h;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    private c5 getEmojiTextViewHelper() {
        if (this.e == null) {
            this.e = new c5(this);
        }
        return this.e;
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

    public final void f() {
        Future future = this.h;
        if (future == null) {
            return;
        }
        try {
            this.h = null;
            if (future.get() != null) {
                throw new ClassCastException();
            }
            if (Build.VERSION.SDK_INT >= 29) {
                throw null;
            }
            rp0.C(this);
            throw null;
        } catch (InterruptedException | ExecutionException unused) {
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (kt0.b) {
            return super.getAutoSizeMaxTextSize();
        }
        y5 y5Var = this.c;
        if (y5Var != null) {
            return Math.round(y5Var.i.e);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (kt0.b) {
            return super.getAutoSizeMinTextSize();
        }
        y5 y5Var = this.c;
        if (y5Var != null) {
            return Math.round(y5Var.i.d);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (kt0.b) {
            return super.getAutoSizeStepGranularity();
        }
        y5 y5Var = this.c;
        if (y5Var != null) {
            return Math.round(y5Var.i.c);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (kt0.b) {
            return super.getAutoSizeTextAvailableSizes();
        }
        y5 y5Var = this.c;
        return y5Var != null ? y5Var.i.f : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (kt0.b) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        y5 y5Var = this.c;
        if (y5Var != null) {
            return y5Var.i.a;
        }
        return 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return rp0.Z(super.getCustomSelectionActionModeCallback());
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public z5 getSuperCaller() {
        if (this.g == null) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 28) {
                this.g = new a6(this);
            } else if (i >= 26) {
                this.g = new o70(8, this);
            }
        }
        return this.g;
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

    @Override // android.widget.TextView
    public CharSequence getText() {
        f();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        e5 e5Var;
        if (Build.VERSION.SDK_INT >= 28 || (e5Var = this.d) == null) {
            return super.getTextClassifier();
        }
        TextClassifier textClassifier = (TextClassifier) e5Var.d;
        return textClassifier == null ? r5.a((TextView) e5Var.c) : textClassifier;
    }

    public yb0 getTextMetricsParamsCompat() {
        return rp0.C(this);
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.c.getClass();
        y5.h(editorInfo, inputConnectionOnCreateInputConnection, this);
        za0.E(editorInfo, inputConnectionOnCreateInputConnection, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        y5 y5Var = this.c;
        if (y5Var == null || kt0.b) {
            return;
        }
        y5Var.i.a();
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        f();
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        y5 y5Var = this.c;
        if (y5Var == null || kt0.b) {
            return;
        }
        h6 h6Var = y5Var.i;
        if (h6Var.f()) {
            h6Var.a();
        }
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (kt0.b) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.i(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (kt0.b) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.j(iArr, i);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (kt0.b) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.k(i);
        }
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

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        y5 y5Var = this.c;
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

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().k(i);
        } else {
            rp0.V(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().h(i);
        } else {
            rp0.W(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i) {
        k9.g(i);
        if (i != getPaint().getFontMetricsInt(null)) {
            setLineSpacing(i - r0, 1.0f);
        }
    }

    public void setPrecomputedText(zb0 zb0Var) {
        if (Build.VERSION.SDK_INT >= 29) {
            throw null;
        }
        rp0.C(this);
        throw null;
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
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.g(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        e5 e5Var;
        if (Build.VERSION.SDK_INT >= 28 || (e5Var = this.d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            e5Var.d = textClassifier;
        }
    }

    public void setTextFuture(Future<zb0> future) {
        this.h = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(yb0 yb0Var) {
        TextDirectionHeuristic textDirectionHeuristic;
        TextDirectionHeuristic textDirectionHeuristic2 = yb0Var.b;
        TextDirectionHeuristic textDirectionHeuristic3 = TextDirectionHeuristics.FIRSTSTRONG_RTL;
        int i = 1;
        if (textDirectionHeuristic2 != textDirectionHeuristic3 && textDirectionHeuristic2 != (textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR)) {
            if (textDirectionHeuristic2 == TextDirectionHeuristics.ANYRTL_LTR) {
                i = 2;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LTR) {
                i = 3;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.RTL) {
                i = 4;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LOCALE) {
                i = 5;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic) {
                i = 6;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic3) {
                i = 7;
            }
        }
        setTextDirection(i);
        getPaint().set(yb0Var.a);
        on0.e(this, yb0Var.c);
        on0.h(this, yb0Var.d);
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i, float f) {
        boolean z = kt0.b;
        if (z) {
            super.setTextSize(i, f);
            return;
        }
        y5 y5Var = this.c;
        if (y5Var == null || z) {
            return;
        }
        h6 h6Var = y5Var.i;
        if (h6Var.f()) {
            return;
        }
        h6Var.g(i, f);
    }

    @Override // android.widget.TextView
    public final void setTypeface(Typeface typeface, int i) {
        Typeface typefaceCreate;
        if (this.f) {
            return;
        }
        if (typeface == null || i <= 0) {
            typefaceCreate = null;
        } else {
            Context context = getContext();
            rp0 rp0Var = sp0.a;
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null");
            }
            typefaceCreate = Typeface.create(typeface, i);
        }
        this.f = true;
        if (typefaceCreate != null) {
            typeface = typefaceCreate;
        }
        try {
            super.setTypeface(typeface, i);
        } finally {
            this.f = false;
        }
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ko0.a(context);
        this.f = false;
        this.g = null;
        vn0.a(this, getContext());
        e4 e4Var = new e4(this);
        this.b = e4Var;
        e4Var.e(attributeSet, i);
        y5 y5Var = new y5(this);
        this.c = y5Var;
        y5Var.f(attributeSet, i);
        y5Var.b();
        e5 e5Var = new e5(4, false);
        e5Var.c = this;
        this.d = e5Var;
        getEmojiTextViewHelper().b(attributeSet, i);
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? rp0.y(context, i) : null, i2 != 0 ? rp0.y(context, i2) : null, i3 != 0 ? rp0.y(context, i3) : null, i4 != 0 ? rp0.y(context, i4) : null);
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? rp0.y(context, i) : null, i2 != 0 ? rp0.y(context, i2) : null, i3 != 0 ? rp0.y(context, i3) : null, i4 != 0 ? rp0.y(context, i4) : null);
        y5 y5Var = this.c;
        if (y5Var != null) {
            y5Var.b();
        }
    }
}
