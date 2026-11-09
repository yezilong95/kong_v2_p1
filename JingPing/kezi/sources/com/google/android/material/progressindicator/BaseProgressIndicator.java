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
import defpackage.c8;
import defpackage.d8;
import defpackage.e8;
import defpackage.k9;
import defpackage.mk;
import defpackage.nl;
import defpackage.u3;
import defpackage.un0;
import defpackage.vx;
import defpackage.wl;
import defpackage.xr0;
import defpackage.y50;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.WeakHashMap;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseProgressIndicator<S extends e8> extends ProgressBar {
    public static final int m = R$style.Widget_MaterialComponents_ProgressIndicator;
    public final e8 b;
    public int c;
    public final boolean d;
    public final int e;
    public u3 f;
    public boolean g;
    public int h;
    public final c8 i;
    public final c8 j;
    public final d8 k;
    public final d8 l;

    public BaseProgressIndicator(Context context, AttributeSet attributeSet, int i, int i2) {
        super(y50.a(context, attributeSet, i, m), attributeSet, i);
        this.g = false;
        this.h = 4;
        this.i = new c8(this, 0);
        this.j = new c8(this, 1);
        this.k = new d8(this, 0);
        this.l = new d8(this, 1);
        Context context2 = getContext();
        this.b = a(context2, attributeSet);
        TypedArray typedArrayD = un0.d(context2, attributeSet, R$styleable.BaseProgressIndicator, i, i2, new int[0]);
        typedArrayD.getInt(R$styleable.BaseProgressIndicator_showDelay, -1);
        this.e = Math.min(typedArrayD.getInt(R$styleable.BaseProgressIndicator_minHideDelay, -1), CloseFrame.NORMAL);
        typedArrayD.recycle();
        this.f = new u3();
        this.d = true;
    }

    private wl getCurrentDrawingDelegate() {
        if (isIndeterminate()) {
            if (getIndeterminateDrawable() == null) {
                return null;
            }
            return getIndeterminateDrawable().m;
        }
        if (getProgressDrawable() == null) {
            return null;
        }
        return getProgressDrawable().m;
    }

    public abstract e8 a(Context context, AttributeSet attributeSet);

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
            this.c = i;
            this.g = true;
            if (getIndeterminateDrawable().isVisible()) {
                u3 u3Var = this.f;
                ContentResolver contentResolver = getContext().getContentResolver();
                u3Var.getClass();
                if (Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f) != 0.0f) {
                    getIndeterminateDrawable().n.n();
                    return;
                }
            }
            this.k.a(getIndeterminateDrawable());
        }
    }

    public final boolean c() {
        WeakHashMap weakHashMap = xr0.a;
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
        return this.b.f;
    }

    public int[] getIndicatorColor() {
        return this.b.c;
    }

    public int getShowAnimationBehavior() {
        return this.b.e;
    }

    public int getTrackColor() {
        return this.b.d;
    }

    public int getTrackCornerRadius() {
        return this.b.b;
    }

    public int getTrackThickness() {
        return this.b.a;
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
            getIndeterminateDrawable().n.m(this.k);
        }
        mk progressDrawable = getProgressDrawable();
        d8 d8Var = this.l;
        if (progressDrawable != null) {
            mk progressDrawable2 = getProgressDrawable();
            if (progressDrawable2.g == null) {
                progressDrawable2.g = new ArrayList();
            }
            if (!progressDrawable2.g.contains(d8Var)) {
                progressDrawable2.g.add(d8Var);
            }
        }
        if (getIndeterminateDrawable() != null) {
            vx indeterminateDrawable = getIndeterminateDrawable();
            if (indeterminateDrawable.g == null) {
                indeterminateDrawable.g = new ArrayList();
            }
            if (!indeterminateDrawable.g.contains(d8Var)) {
                indeterminateDrawable.g.add(d8Var);
            }
        }
        if (c()) {
            if (this.e > 0) {
                SystemClock.uptimeMillis();
            }
            setVisibility(0);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.j);
        removeCallbacks(this.i);
        ((nl) getCurrentDrawable()).e(false, false, false);
        vx indeterminateDrawable = getIndeterminateDrawable();
        d8 d8Var = this.l;
        if (indeterminateDrawable != null) {
            getIndeterminateDrawable().g(d8Var);
            getIndeterminateDrawable().n.q();
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().g(d8Var);
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
            wl currentDrawingDelegate = getCurrentDrawingDelegate();
            if (currentDrawingDelegate == null) {
                return;
            }
            setMeasuredDimension(currentDrawingDelegate.e() < 0 ? View.getDefaultSize(getSuggestedMinimumWidth(), i) : currentDrawingDelegate.e() + getPaddingLeft() + getPaddingRight(), currentDrawingDelegate.d() < 0 ? View.getDefaultSize(getSuggestedMinimumHeight(), i2) : currentDrawingDelegate.d() + getPaddingTop() + getPaddingBottom());
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        boolean z = i == 0;
        if (this.d) {
            ((nl) getCurrentDrawable()).e(c(), false, z);
        }
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.d) {
            ((nl) getCurrentDrawable()).e(c(), false, false);
        }
    }

    public void setAnimatorDurationScaleProvider(u3 u3Var) {
        this.f = u3Var;
        if (getProgressDrawable() != null) {
            getProgressDrawable().d = u3Var;
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().d = u3Var;
        }
    }

    public void setHideAnimationBehavior(int i) {
        this.b.f = i;
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setIndeterminate(boolean z) {
        try {
            if (z == isIndeterminate()) {
                return;
            }
            nl nlVar = (nl) getCurrentDrawable();
            if (nlVar != null) {
                nlVar.e(false, false, false);
            }
            super.setIndeterminate(z);
            nl nlVar2 = (nl) getCurrentDrawable();
            if (nlVar2 != null) {
                nlVar2.e(c(), false, false);
            }
            if ((nlVar2 instanceof vx) && c()) {
                ((vx) nlVar2).n.o();
            }
            this.g = false;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setIndeterminateDrawable(null);
        } else {
            if (!(drawable instanceof vx)) {
                throw new IllegalArgumentException("Cannot set framework drawable as indeterminate drawable.");
            }
            ((nl) drawable).e(false, false, false);
            super.setIndeterminateDrawable(drawable);
        }
    }

    public void setIndicatorColor(int... iArr) {
        if (iArr.length == 0) {
            iArr = new int[]{k9.p(getContext(), R$attr.colorPrimary, -1)};
        }
        if (Arrays.equals(getIndicatorColor(), iArr)) {
            return;
        }
        this.b.c = iArr;
        getIndeterminateDrawable().n.l();
        invalidate();
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
            if (!(drawable instanceof mk)) {
                throw new IllegalArgumentException("Cannot set framework drawable as progress drawable.");
            }
            mk mkVar = (mk) drawable;
            mkVar.e(false, false, false);
            super.setProgressDrawable(mkVar);
            mkVar.setLevel((int) ((getProgress() / getMax()) * 10000.0f));
        }
    }

    public void setShowAnimationBehavior(int i) {
        this.b.e = i;
        invalidate();
    }

    public void setTrackColor(int i) {
        e8 e8Var = this.b;
        if (e8Var.d != i) {
            e8Var.d = i;
            invalidate();
        }
    }

    public void setTrackCornerRadius(int i) {
        e8 e8Var = this.b;
        if (e8Var.b != i) {
            e8Var.b = Math.min(i, e8Var.a / 2);
        }
    }

    public void setTrackThickness(int i) {
        e8 e8Var = this.b;
        if (e8Var.a != i) {
            e8Var.a = i;
            requestLayout();
        }
    }

    public void setVisibilityAfterHide(int i) {
        if (i != 0 && i != 4 && i != 8) {
            throw new IllegalArgumentException("The component's visibility must be one of VISIBLE, INVISIBLE, and GONE defined in View.");
        }
        this.h = i;
    }

    @Override // android.widget.ProgressBar
    public vx getIndeterminateDrawable() {
        return (vx) super.getIndeterminateDrawable();
    }

    @Override // android.widget.ProgressBar
    public mk getProgressDrawable() {
        return (mk) super.getProgressDrawable();
    }
}
