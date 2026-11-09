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
import defpackage.c01;
import defpackage.c7;
import defpackage.d7;
import defpackage.e7;
import defpackage.fa0;
import defpackage.gl0;
import defpackage.h6;
import defpackage.hl0;
import defpackage.i5;
import defpackage.i6;
import defpackage.j01;
import defpackage.l7;
import defpackage.la0;
import defpackage.ma0;
import defpackage.n21;
import defpackage.p50;
import defpackage.q61;
import defpackage.qm;
import defpackage.v6;
import defpackage.w01;
import defpackage.z91;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatTextView extends TextView {
    public final i5 E;
    public final c7 F;
    public final i6 G;
    public h6 H;
    public boolean I;
    public p50 J;
    public Future K;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    private h6 getEmojiTextViewHelper() {
        if (this.H == null) {
            this.H = new h6(this);
        }
        return this.H;
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

    public final void f() {
        Future future = this.K;
        if (future == null) {
            return;
        }
        try {
            this.K = null;
            if (future.get() != null) {
                throw new ClassCastException();
            }
            if (Build.VERSION.SDK_INT >= 29) {
                throw null;
            }
            fa0.S(this);
            throw null;
        } catch (InterruptedException | ExecutionException unused) {
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

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public d7 getSuperCaller() {
        if (this.J == null) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 28) {
                this.J = new e7(this);
            } else if (i >= 26) {
                this.J = new p50(7, this);
            }
        }
        return this.J;
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

    @Override // android.widget.TextView
    public CharSequence getText() {
        f();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        i6 i6Var;
        if (Build.VERSION.SDK_INT >= 28 || (i6Var = this.G) == null) {
            return super.getTextClassifier();
        }
        TextClassifier textClassifier = (TextClassifier) i6Var.G;
        return textClassifier == null ? v6.a((TextView) i6Var.F) : textClassifier;
    }

    public gl0 getTextMetricsParamsCompat() {
        return fa0.S(this);
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.F.getClass();
        c7.h(editorInfo, inputConnectionOnCreateInputConnection, this);
        z91.U(editorInfo, inputConnectionOnCreateInputConnection, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        c7 c7Var = this.F;
        if (c7Var == null || q61.b) {
            return;
        }
        c7Var.i.a();
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        f();
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
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
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        c7 c7Var = this.F;
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

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().k(i);
        } else {
            fa0.t0(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().d(i);
        } else {
            fa0.u0(this, i);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i) {
        la0.g(i);
        if (i != getPaint().getFontMetricsInt(null)) {
            setLineSpacing(i - r0, 1.0f);
        }
    }

    public void setPrecomputedText(hl0 hl0Var) {
        if (Build.VERSION.SDK_INT >= 29) {
            throw null;
        }
        fa0.S(this);
        throw null;
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
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.g(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        i6 i6Var;
        if (Build.VERSION.SDK_INT >= 28 || (i6Var = this.G) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            i6Var.G = textClassifier;
        }
    }

    public void setTextFuture(Future<hl0> future) {
        this.K = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(gl0 gl0Var) {
        TextDirectionHeuristic textDirectionHeuristic;
        TextDirectionHeuristic textDirectionHeuristic2 = gl0Var.b;
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
        getPaint().set(gl0Var.a);
        c01.e(this, gl0Var.c);
        c01.h(this, gl0Var.d);
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

    @Override // android.widget.TextView
    public final void setTypeface(Typeface typeface, int i) {
        Typeface typefaceCreate;
        if (this.I) {
            return;
        }
        if (typeface == null || i <= 0) {
            typefaceCreate = null;
        } else {
            Context context = getContext();
            ma0 ma0Var = n21.a;
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null");
            }
            typefaceCreate = Typeface.create(typeface, i);
        }
        this.I = true;
        if (typefaceCreate != null) {
            typeface = typefaceCreate;
        }
        try {
            super.setTypeface(typeface, i);
        } finally {
            this.I = false;
        }
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        w01.a(context);
        this.I = false;
        this.J = null;
        j01.a(this, getContext());
        i5 i5Var = new i5(this);
        this.E = i5Var;
        i5Var.h(attributeSet, i);
        c7 c7Var = new c7(this);
        this.F = c7Var;
        c7Var.f(attributeSet, i);
        c7Var.b();
        i6 i6Var = new i6(6, false);
        i6Var.F = this;
        this.G = i6Var;
        getEmojiTextViewHelper().b(attributeSet, i);
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? qm.z(context, i) : null, i2 != 0 ? qm.z(context, i2) : null, i3 != 0 ? qm.z(context, i3) : null, i4 != 0 ? qm.z(context, i4) : null);
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? qm.z(context, i) : null, i2 != 0 ? qm.z(context, i2) : null, i3 != 0 ? qm.z(context, i3) : null, i4 != 0 ? qm.z(context, i4) : null);
        c7 c7Var = this.F;
        if (c7Var != null) {
            c7Var.b();
        }
    }
}
