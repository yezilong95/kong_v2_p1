package com.google.android.material.progressindicator;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.a40;
import defpackage.aa;
import defpackage.aq;
import defpackage.f51;
import defpackage.fa0;
import defpackage.fo;
import defpackage.i01;
import defpackage.pd0;
import defpackage.qp;
import defpackage.y9;
import defpackage.z4;
import defpackage.z9;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseProgressIndicator<S extends aa> extends ProgressBar {
    public static final int P = R$style.Widget_MaterialComponents_ProgressIndicator;
    public final aa E;
    public int F;
    public final boolean G;
    public final int H;
    public z4 I;
    public boolean J;
    public int K;
    public final y9 L;
    public final y9 M;
    public final z9 N;
    public final z9 O;

    public BaseProgressIndicator(Context context, AttributeSet attributeSet, int i, int i2) {
        super(pd0.a(context, attributeSet, i, P), attributeSet, i);
        this.J = false;
        this.K = 4;
        this.L = new y9(this, 0);
        this.M = new y9(this, 1);
        this.N = new z9(this, 0);
        this.O = new z9(this, 1);
        Context context2 = getContext();
        this.E = a(context2, attributeSet);
        TypedArray typedArrayD = i01.d(context2, attributeSet, R$styleable.BaseProgressIndicator, i, i2, new int[0]);
        typedArrayD.getInt(R$styleable.BaseProgressIndicator_showDelay, -1);
        this.H = Math.min(typedArrayD.getInt(R$styleable.BaseProgressIndicator_minHideDelay, -1), 1000);
        typedArrayD.recycle();
        this.I = new z4();
        this.G = true;
    }

    private aq getCurrentDrawingDelegate() {
        if (isIndeterminate()) {
            if (getIndeterminateDrawable() == null) {
                return null;
            }
            return getIndeterminateDrawable().P;
        }
        if (getProgressDrawable() == null) {
            return null;
        }
        return getProgressDrawable().P;
    }

    public abstract aa a(Context context, AttributeSet attributeSet);

    public void b(int i) {
        if (!isIndeterminate()) {
            super.setProgress(i);
            if (getProgressDrawable() != null) {
                getProgressDrawable().jumpToCurrentState();
                return;
            }
            return;
        }
        if (getProgressDrawable() != null) {
            this.F = i;
            this.J = true;
            if (getIndeterminateDrawable().isVisible()) {
                z4 z4Var = this.I;
                ContentResolver contentResolver = getContext().getContentResolver();
                z4Var.getClass();
                if (Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f) != 0.0f) {
                    getIndeterminateDrawable().Q.m();
                    return;
                }
            }
            this.N.a(getIndeterminateDrawable());
        }
    }

    public final boolean c() {
        WeakHashMap weakHashMap = f51.a;
        if (!isAttachedToWindow() || getWindowVisibility() != 0) {
            return false;
        }
        View view = this;
        while (view.getVisibility() == 0) {
            Object parent = view.getParent();
            if (parent == null) {
                return getWindowVisibility() == 0;
            }
            if (!(parent instanceof View)) {
                return true;
            }
            view = (View) parent;
        }
        return false;
    }

    @Override // android.widget.ProgressBar
    public Drawable getCurrentDrawable() {
        return isIndeterminate() ? getIndeterminateDrawable() : getProgressDrawable();
    }

    public int getHideAnimationBehavior() {
        return this.E.f;
    }

    public int[] getIndicatorColor() {
        return this.E.c;
    }

    public int getIndicatorTrackGapSize() {
        return this.E.g;
    }

    public int getShowAnimationBehavior() {
        return this.E.e;
    }

    public int getTrackColor() {
        return this.E.d;
    }

    public int getTrackCornerRadius() {
        return this.E.b;
    }

    public int getTrackThickness() {
        return this.E.a;
    }

    @Override // android.view.View
    public final void invalidate() {
        super.invalidate();
        if (getCurrentDrawable() != null) {
            getCurrentDrawable().invalidateSelf();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getProgressDrawable() != null && getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().Q.l(this.N);
        }
        fo progressDrawable = getProgressDrawable();
        z9 z9Var = this.O;
        if (progressDrawable != null) {
            fo progressDrawable2 = getProgressDrawable();
            if (progressDrawable2.J == null) {
                progressDrawable2.J = new ArrayList();
            }
            if (!progressDrawable2.J.contains(z9Var)) {
                progressDrawable2.J.add(z9Var);
            }
        }
        if (getIndeterminateDrawable() != null) {
            a40 indeterminateDrawable = getIndeterminateDrawable();
            if (indeterminateDrawable.J == null) {
                indeterminateDrawable.J = new ArrayList();
            }
            if (!indeterminateDrawable.J.contains(z9Var)) {
                indeterminateDrawable.J.add(z9Var);
            }
        }
        if (c()) {
            if (this.H > 0) {
                SystemClock.uptimeMillis();
            }
            setVisibility(0);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.M);
        removeCallbacks(this.L);
        ((qp) getCurrentDrawable()).c(false, false, false);
        a40 indeterminateDrawable = getIndeterminateDrawable();
        z9 z9Var = this.O;
        if (indeterminateDrawable != null) {
            getIndeterminateDrawable().e(z9Var);
            getIndeterminateDrawable().Q.p();
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().e(z9Var);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final synchronized void onDraw(Canvas canvas) {
        try {
            int iSave = canvas.save();
            if (getPaddingLeft() != 0 || getPaddingTop() != 0) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            if (getPaddingRight() != 0 || getPaddingBottom() != 0) {
                canvas.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
            }
            getCurrentDrawable().draw(canvas);
            canvas.restoreToCount(iSave);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final synchronized void onMeasure(int i, int i2) {
        try {
            aq currentDrawingDelegate = getCurrentDrawingDelegate();
            if (currentDrawingDelegate == null) {
                return;
            }
            setMeasuredDimension(currentDrawingDelegate.f() < 0 ? View.getDefaultSize(getSuggestedMinimumWidth(), i) : currentDrawingDelegate.f() + getPaddingLeft() + getPaddingRight(), currentDrawingDelegate.e() < 0 ? View.getDefaultSize(getSuggestedMinimumHeight(), i2) : currentDrawingDelegate.e() + getPaddingTop() + getPaddingBottom());
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        boolean z = i == 0;
        if (this.G) {
            ((qp) getCurrentDrawable()).c(c(), false, z);
        }
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.G) {
            ((qp) getCurrentDrawable()).c(c(), false, false);
        }
    }

    public void setAnimatorDurationScaleProvider(z4 z4Var) {
        this.I = z4Var;
        if (getProgressDrawable() != null) {
            getProgressDrawable().G = z4Var;
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().G = z4Var;
        }
    }

    public void setHideAnimationBehavior(int i) {
        this.E.f = i;
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setIndeterminate(boolean z) {
        try {
            if (z == isIndeterminate()) {
                return;
            }
            qp qpVar = (qp) getCurrentDrawable();
            if (qpVar != null) {
                qpVar.c(false, false, false);
            }
            super.setIndeterminate(z);
            qp qpVar2 = (qp) getCurrentDrawable();
            if (qpVar2 != null) {
                qpVar2.c(c(), false, false);
            }
            if ((qpVar2 instanceof a40) && c()) {
                ((a40) qpVar2).Q.o();
            }
            this.J = false;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setIndeterminateDrawable(null);
        } else {
            if (!(drawable instanceof a40)) {
                throw new IllegalArgumentException("Cannot set framework drawable as indeterminate drawable.");
            }
            ((qp) drawable).c(false, false, false);
            super.setIndeterminateDrawable(drawable);
        }
    }

    public void setIndicatorColor(int... iArr) {
        if (iArr.length == 0) {
            iArr = new int[]{fa0.O(getContext(), R$attr.colorPrimary, -1)};
        }
        if (Arrays.equals(getIndicatorColor(), iArr)) {
            return;
        }
        this.E.c = iArr;
        getIndeterminateDrawable().Q.j();
        invalidate();
    }

    public void setIndicatorTrackGapSize(int i) {
        aa aaVar = this.E;
        if (aaVar.g != i) {
            aaVar.g = i;
            aaVar.a();
            invalidate();
        }
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i) {
        if (isIndeterminate()) {
            return;
        }
        b(i);
    }

    @Override // android.widget.ProgressBar
    public void setProgressDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setProgressDrawable(null);
        } else {
            if (!(drawable instanceof fo)) {
                throw new IllegalArgumentException("Cannot set framework drawable as progress drawable.");
            }
            fo foVar = (fo) drawable;
            foVar.c(false, false, false);
            super.setProgressDrawable(foVar);
            foVar.setLevel((int) ((getProgress() / getMax()) * 10000.0f));
        }
    }

    public void setShowAnimationBehavior(int i) {
        this.E.e = i;
        invalidate();
    }

    public void setTrackColor(int i) {
        aa aaVar = this.E;
        if (aaVar.d != i) {
            aaVar.d = i;
            invalidate();
        }
    }

    public void setTrackCornerRadius(int i) {
        aa aaVar = this.E;
        if (aaVar.b != i) {
            aaVar.b = Math.min(i, aaVar.a / 2);
            invalidate();
        }
    }

    public void setTrackThickness(int i) {
        aa aaVar = this.E;
        if (aaVar.a != i) {
            aaVar.a = i;
            requestLayout();
        }
    }

    public void setVisibilityAfterHide(int i) {
        if (i != 0 && i != 4 && i != 8) {
            throw new IllegalArgumentException("The component's visibility must be one of VISIBLE, INVISIBLE, and GONE defined in View.");
        }
        this.K = i;
    }

    @Override // android.widget.ProgressBar
    public a40 getIndeterminateDrawable() {
        return (a40) super.getIndeterminateDrawable();
    }

    @Override // android.widget.ProgressBar
    public fo getProgressDrawable() {
        return (fo) super.getProgressDrawable();
    }
}
