package com.google.android.material.slider;

import android.R;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.widget.SeekBar;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.slider.BaseSlider;
import defpackage.bo;
import defpackage.dg;
import defpackage.f51;
import defpackage.fa0;
import defpackage.i01;
import defpackage.ip;
import defpackage.is;
import defpackage.j11;
import defpackage.la0;
import defpackage.ld0;
import defpackage.ma0;
import defpackage.o0;
import defpackage.pd0;
import defpackage.qi;
import defpackage.s90;
import defpackage.sz0;
import defpackage.t40;
import defpackage.vz0;
import defpackage.w9;
import defpackage.wl0;
import defpackage.x9;
import defpackage.xu0;
import defpackage.y4;
import defpackage.z91;
import defpackage.zn;
import defpackage.zu0;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends w9, T extends x9> extends View {
    public static final int Y0 = R$style.Widget_MaterialComponents_Slider;
    public static final int Z0 = R$attr.motionDurationMedium4;
    public static final int a1 = R$attr.motionDurationShort3;
    public static final int b1 = R$attr.motionEasingEmphasizedInterpolator;
    public static final int c1 = R$attr.motionEasingEmphasizedAccelerateInterpolator;
    public int A0;
    public int B0;
    public float C0;
    public float[] D0;
    public final Paint E;
    public boolean E0;
    public final Paint F;
    public int F0;
    public final Paint G;
    public int G0;
    public final Paint H;
    public int H0;
    public final Paint I;
    public boolean I0;
    public final Paint J;
    public boolean J0;
    public final Paint K;
    public ColorStateList K0;
    public final e L;
    public ColorStateList L0;
    public final AccessibilityManager M;
    public ColorStateList M0;
    public d N;
    public ColorStateList N0;
    public final int O;
    public ColorStateList O0;
    public final ArrayList P;
    public final Path P0;
    public final ArrayList Q;
    public final RectF Q0;
    public final ArrayList R;
    public final RectF R0;
    public boolean S;
    public final ld0 S0;
    public ValueAnimator T;
    public Drawable T0;
    public ValueAnimator U;
    public List U0;
    public final int V;
    public float V0;
    public final int W;
    public int W0;
    public final a X0;
    public final int a0;
    public final int b0;
    public final int c0;
    public final int d0;
    public final int e0;
    public final int f0;
    public final int g0;
    public int h0;
    public int i0;
    public int j0;
    public int k0;
    public int l0;
    public int m0;
    public int n0;
    public int o0;
    public int p0;
    public int q0;
    public int r0;
    public int s0;
    public final int t0;
    public float u0;
    public MotionEvent v0;
    public boolean w0;
    public float x0;
    public float y0;
    public ArrayList z0;

    public static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new f();
        public float E;
        public float F;
        public ArrayList G;
        public float H;
        public boolean I;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.E);
            parcel.writeFloat(this.F);
            parcel.writeList(this.G);
            parcel.writeFloat(this.H);
            parcel.writeBooleanArray(new boolean[]{this.I});
        }
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.sliderStyle);
    }

    public final void A() {
        boolean z;
        int iMax = Math.max(this.g0, Math.max(this.j0 + getPaddingBottom() + getPaddingTop(), getPaddingBottom() + getPaddingTop() + this.m0));
        boolean z2 = false;
        if (iMax == this.h0) {
            z = false;
        } else {
            this.h0 = iMax;
            z = true;
        }
        int iMax2 = Math.max(Math.max(Math.max((this.l0 / 2) - this.a0, 0), Math.max((this.j0 - this.b0) / 2, 0)), Math.max(Math.max(this.F0 - this.c0, 0), Math.max(this.G0 - this.d0, 0))) + this.W;
        if (this.k0 != iMax2) {
            this.k0 = iMax2;
            WeakHashMap weakHashMap = f51.a;
            if (isLaidOut()) {
                this.H0 = Math.max(getWidth() - (this.k0 * 2), 0);
                m();
            }
            z2 = true;
        }
        if (z) {
            requestLayout();
        } else if (z2) {
            postInvalidate();
        }
    }

    public final void B() {
        if (this.J0) {
            float f = this.x0;
            float f2 = this.y0;
            if (f >= f2) {
                throw new IllegalStateException("valueFrom(" + this.x0 + ") must be smaller than valueTo(" + this.y0 + ")");
            }
            if (f2 <= f) {
                throw new IllegalStateException("valueTo(" + this.y0 + ") must be greater than valueFrom(" + this.x0 + ")");
            }
            if (this.C0 > 0.0f && !C(f2)) {
                throw new IllegalStateException("The stepSize(" + this.C0 + ") must be 0, or a factor of the valueFrom(" + this.x0 + ")-valueTo(" + this.y0 + ") range");
            }
            for (Float f3 : this.z0) {
                if (f3.floatValue() < this.x0 || f3.floatValue() > this.y0) {
                    throw new IllegalStateException("Slider value(" + f3 + ") must be greater or equal to valueFrom(" + this.x0 + "), and lower or equal to valueTo(" + this.y0 + ")");
                }
                if (this.C0 > 0.0f && !C(f3.floatValue())) {
                    float f4 = this.x0;
                    float f5 = this.C0;
                    throw new IllegalStateException("Value(" + f3 + ") must be equal to valueFrom(" + f4 + ") plus a multiple of stepSize(" + f5 + ") when using stepSize(" + f5 + ")");
                }
            }
            float minSeparation = getMinSeparation();
            if (minSeparation < 0.0f) {
                throw new IllegalStateException("minSeparation(" + minSeparation + ") must be greater or equal to 0");
            }
            float f6 = this.C0;
            if (f6 > 0.0f && minSeparation > 0.0f) {
                if (this.W0 != 1) {
                    throw new IllegalStateException("minSeparation(" + minSeparation + ") cannot be set as a dimension when using stepSize(" + this.C0 + ")");
                }
                if (minSeparation < f6 || !j(minSeparation)) {
                    float f7 = this.C0;
                    throw new IllegalStateException("minSeparation(" + minSeparation + ") must be greater or equal and a multiple of stepSize(" + f7 + ") when using stepSize(" + f7 + ")");
                }
            }
            float f8 = this.C0;
            if (f8 != 0.0f) {
                if (((int) f8) != f8) {
                    Log.w("BaseSlider", "Floating point value used for stepSize(" + f8 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
                }
                float f9 = this.x0;
                if (((int) f9) != f9) {
                    Log.w("BaseSlider", "Floating point value used for valueFrom(" + f9 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
                }
                float f10 = this.y0;
                if (((int) f10) != f10) {
                    Log.w("BaseSlider", "Floating point value used for valueTo(" + f10 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
                }
            }
            this.J0 = false;
        }
    }

    public final boolean C(float f) {
        return j(new BigDecimal(Float.toString(f)).subtract(new BigDecimal(Float.toString(this.x0)), MathContext.DECIMAL64).doubleValue());
    }

    public final float D(float f) {
        return (p(f) * this.H0) + this.k0;
    }

    public final void a(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth == -1 && intrinsicHeight == -1) {
            drawable.setBounds(0, 0, this.l0, this.m0);
        } else {
            float fMax = Math.max(this.l0, this.m0) / Math.max(intrinsicWidth, intrinsicHeight);
            drawable.setBounds(0, 0, (int) (intrinsicWidth * fMax), (int) (intrinsicHeight * fMax));
        }
    }

    public void addOnChangeListener(L l) {
        this.Q.add(l);
    }

    public void addOnSliderTouchListener(T t) {
        this.R.add(t);
    }

    public final int b() {
        int i = this.h0 / 2;
        int i2 = this.i0;
        return i + ((i2 == 1 || i2 == 3) ? ((j11) this.P.get(0)).getIntrinsicHeight() : 0);
    }

    public final ValueAnimator c(boolean z) {
        int iU;
        TimeInterpolator timeInterpolatorV;
        float fFloatValue = z ? 0.0f : 1.0f;
        ValueAnimator valueAnimator = z ? this.U : this.T;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fFloatValue, z ? 1.0f : 0.0f);
        if (z) {
            iU = s90.U(getContext(), Z0, 83);
            timeInterpolatorV = s90.V(getContext(), b1, y4.e);
        } else {
            iU = s90.U(getContext(), a1, 117);
            timeInterpolatorV = s90.V(getContext(), c1, y4.c);
        }
        valueAnimatorOfFloat.setDuration(iU);
        valueAnimatorOfFloat.setInterpolator(timeInterpolatorV);
        valueAnimatorOfFloat.addUpdateListener(new b(this));
        return valueAnimatorOfFloat;
    }

    public final void d(Canvas canvas, int i, int i2, float f, Drawable drawable) {
        canvas.save();
        canvas.translate((this.k0 + ((int) (p(f) * i))) - (drawable.getBounds().width() / 2.0f), i2 - (drawable.getBounds().height() / 2.0f));
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.L.m(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        this.E.setColor(i(this.O0));
        this.F.setColor(i(this.N0));
        this.I.setColor(i(this.M0));
        this.J.setColor(i(this.L0));
        this.K.setColor(i(this.N0));
        for (j11 j11Var : this.P) {
            if (j11Var.isStateful()) {
                j11Var.setState(getDrawableState());
            }
        }
        ld0 ld0Var = this.S0;
        if (ld0Var.isStateful()) {
            ld0Var.setState(getDrawableState());
        }
        Paint paint = this.H;
        paint.setColor(i(this.K0));
        paint.setAlpha(63);
    }

    public final void e() {
        if (!this.S) {
            this.S = true;
            ValueAnimator valueAnimatorC = c(true);
            this.T = valueAnimatorC;
            this.U = null;
            valueAnimatorC.start();
        }
        ArrayList arrayList = this.P;
        Iterator it = arrayList.iterator();
        for (int i = 0; i < this.z0.size() && it.hasNext(); i++) {
            if (i != this.B0) {
                s((j11) it.next(), ((Float) this.z0.get(i)).floatValue());
            }
        }
        if (!it.hasNext()) {
            throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(arrayList.size()), Integer.valueOf(this.z0.size())));
        }
        s((j11) it.next(), ((Float) this.z0.get(this.B0)).floatValue());
    }

    public final void f() {
        if (this.S) {
            this.S = false;
            ValueAnimator valueAnimatorC = c(false);
            this.U = valueAnimatorC;
            this.T = null;
            valueAnimatorC.addListener(new c(this));
            this.U.start();
        }
    }

    public final String g(float f) {
        return String.format(((float) ((int) f)) == f ? "%.0f" : "%.2f", Float.valueOf(f));
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.L.k;
    }

    public float getMinSeparation() {
        return 0.0f;
    }

    public int getThumbRadius() {
        return this.l0 / 2;
    }

    public float getValueFrom() {
        return this.x0;
    }

    public float getValueTo() {
        return this.y0;
    }

    public List<Float> getValues() {
        return new ArrayList(this.z0);
    }

    public final float[] h() {
        float fFloatValue = ((Float) this.z0.get(0)).floatValue();
        ArrayList arrayList = this.z0;
        float fFloatValue2 = ((Float) arrayList.get(arrayList.size() - 1)).floatValue();
        if (this.z0.size() == 1) {
            fFloatValue = this.x0;
        }
        float fP = p(fFloatValue);
        float fP2 = p(fFloatValue2);
        return l() ? new float[]{fP2, fP} : new float[]{fP, fP2};
    }

    public final int i(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    public final boolean j(double d) {
        double dDoubleValue = new BigDecimal(Double.toString(d)).divide(new BigDecimal(Float.toString(this.C0)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(dDoubleValue)) - dDoubleValue) < 1.0E-4d;
    }

    public final boolean k(MotionEvent motionEvent) {
        if (motionEvent.getToolType(0) != 3) {
            for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                ViewGroup viewGroup = (ViewGroup) parent;
                if ((viewGroup.canScrollVertically(1) || viewGroup.canScrollVertically(-1)) && viewGroup.shouldDelayChildPressedState()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean l() {
        WeakHashMap weakHashMap = f51.a;
        return getLayoutDirection() == 1;
    }

    public final void m() {
        if (this.C0 <= 0.0f) {
            return;
        }
        B();
        int iMin = Math.min((int) (((this.y0 - this.x0) / this.C0) + 1.0f), (this.H0 / this.e0) + 1);
        float[] fArr = this.D0;
        if (fArr == null || fArr.length != iMin * 2) {
            this.D0 = new float[iMin * 2];
        }
        float f = this.H0 / (iMin - 1);
        for (int i = 0; i < iMin * 2; i += 2) {
            float[] fArr2 = this.D0;
            fArr2[i] = ((i / 2.0f) * f) + this.k0;
            fArr2[i + 1] = b();
        }
    }

    public final boolean n(int i) {
        int i2 = this.B0;
        long j = ((long) i2) + ((long) i);
        long size = this.z0.size() - 1;
        if (j < 0) {
            j = 0;
        } else if (j > size) {
            j = size;
        }
        int i3 = (int) j;
        this.B0 = i3;
        if (i3 == i2) {
            return false;
        }
        if (this.A0 != -1) {
            this.A0 = i3;
        }
        x();
        postInvalidate();
        return true;
    }

    public final void o(int i) {
        if (l()) {
            i = i == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i;
        }
        n(i);
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnScrollChangedListener(this.X0);
        for (j11 j11Var : this.P) {
            ViewGroup viewGroupQ = fa0.Q(this);
            if (viewGroupQ == null) {
                j11Var.getClass();
            } else {
                j11Var.getClass();
                int[] iArr = new int[2];
                viewGroupQ.getLocationOnScreen(iArr);
                j11Var.o0 = iArr[0];
                viewGroupQ.getWindowVisibleDisplayFrame(j11Var.h0);
                viewGroupQ.addOnLayoutChangeListener(j11Var.g0);
            }
        }
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        d dVar = this.N;
        if (dVar != null) {
            removeCallbacks(dVar);
        }
        this.S = false;
        for (j11 j11Var : this.P) {
            ViewGroup viewGroupQ = fa0.Q(this);
            wl0 wl0Var = viewGroupQ == null ? null : new wl0(viewGroupQ);
            if (wl0Var != null) {
                ((ViewOverlay) wl0Var.E).remove(j11Var);
                ViewGroup viewGroupQ2 = fa0.Q(this);
                if (viewGroupQ2 == null) {
                    j11Var.getClass();
                } else {
                    viewGroupQ2.removeOnLayoutChangeListener(j11Var.g0);
                }
            }
        }
        getViewTreeObserver().removeOnScrollChangedListener(this.X0);
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x0190 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0201  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onDraw(android.graphics.Canvas r21) {
        /*
            Method dump skipped, instruction units count: 784
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        e eVar = this.L;
        if (!z) {
            this.A0 = -1;
            eVar.j(this.B0);
            return;
        }
        if (i == 1) {
            n(Integer.MAX_VALUE);
        } else if (i == 2) {
            n(Integer.MIN_VALUE);
        } else if (i == 17) {
            o(Integer.MAX_VALUE);
        } else if (i == 66) {
            o(Integer.MIN_VALUE);
        }
        eVar.w(this.B0);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    @Override // android.view.View, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onKeyDown(int r14, android.view.KeyEvent r15) {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        this.I0 = false;
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int i3 = this.h0;
        int i4 = this.i0;
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3 + ((i4 == 1 || i4 == 3) ? ((j11) this.P.get(0)).getIntrinsicHeight() : 0), 1073741824));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.x0 = sliderState.E;
        this.y0 = sliderState.F;
        t(sliderState.G);
        this.C0 = sliderState.H;
        if (sliderState.I) {
            requestFocus();
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.E = this.x0;
        sliderState.F = this.y0;
        sliderState.G = new ArrayList(this.z0);
        sliderState.H = this.C0;
        sliderState.I = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        this.H0 = Math.max(i - (this.k0 * 2), 0);
        m();
        x();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0) {
            ViewGroup viewGroupQ = fa0.Q(this);
            wl0 wl0Var = viewGroupQ == null ? null : new wl0(viewGroupQ);
            if (wl0Var == null) {
                return;
            }
            Iterator it = this.P.iterator();
            while (it.hasNext()) {
                ((ViewOverlay) wl0Var.E).remove((j11) it.next());
            }
        }
    }

    public final float p(float f) {
        float f2 = this.x0;
        float f3 = (f - f2) / (this.y0 - f2);
        return l() ? 1.0f - f3 : f3;
    }

    public final void q() {
        Iterator it = this.R.iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
    }

    public boolean r() {
        if (this.A0 != -1) {
            return true;
        }
        float f = this.V0;
        if (l()) {
            f = 1.0f - f;
        }
        float f2 = this.y0;
        float f3 = this.x0;
        float fD = zn.d(f2, f3, f, f3);
        float fD2 = D(fD);
        this.A0 = 0;
        float fAbs = Math.abs(((Float) this.z0.get(0)).floatValue() - fD);
        for (int i = 1; i < this.z0.size(); i++) {
            float fAbs2 = Math.abs(((Float) this.z0.get(i)).floatValue() - fD);
            float fD3 = D(((Float) this.z0.get(i)).floatValue());
            if (Float.compare(fAbs2, fAbs) > 0) {
                break;
            }
            boolean z = !l() ? fD3 - fD2 >= 0.0f : fD3 - fD2 <= 0.0f;
            if (Float.compare(fAbs2, fAbs) < 0) {
                this.A0 = i;
            } else {
                if (Float.compare(fAbs2, fAbs) != 0) {
                    continue;
                } else {
                    if (Math.abs(fD3 - fD2) < this.V) {
                        this.A0 = -1;
                        return false;
                    }
                    if (z) {
                        this.A0 = i;
                    }
                }
            }
            fAbs = fAbs2;
        }
        return this.A0 != -1;
    }

    public void removeOnChangeListener(L l) {
        this.Q.remove(l);
    }

    public void removeOnSliderTouchListener(T t) {
        this.R.remove(t);
    }

    public final void s(j11 j11Var, float f) {
        String strG = g(f);
        if (!TextUtils.equals(j11Var.c0, strG)) {
            j11Var.c0 = strG;
            j11Var.f0.e = true;
            j11Var.invalidateSelf();
        }
        int iP = (this.k0 + ((int) (p(f) * this.H0))) - (j11Var.getIntrinsicWidth() / 2);
        int iB = b() - ((this.m0 / 2) + this.t0);
        j11Var.setBounds(iP, iB - j11Var.getIntrinsicHeight(), j11Var.getIntrinsicWidth() + iP, iB);
        Rect rect = new Rect(j11Var.getBounds());
        bo.c(fa0.Q(this), this, rect);
        j11Var.setBounds(rect);
        ViewGroup viewGroupQ = fa0.Q(this);
        ((ViewOverlay) (viewGroupQ == null ? null : new wl0(viewGroupQ)).E).add(j11Var);
    }

    public void setActiveThumbIndex(int i) {
        this.A0 = i;
    }

    public void setCustomThumbDrawable(Drawable drawable) {
        Drawable drawableNewDrawable = drawable.mutate().getConstantState().newDrawable();
        a(drawableNewDrawable);
        this.T0 = drawableNewDrawable;
        this.U0.clear();
        postInvalidate();
    }

    public void setCustomThumbDrawablesForValues(int... iArr) {
        Drawable[] drawableArr = new Drawable[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            drawableArr[i] = getResources().getDrawable(iArr[i]);
        }
        setCustomThumbDrawablesForValues(drawableArr);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setLayerType(z ? 0 : 2, null);
    }

    public void setFocusedThumbIndex(int i) {
        if (i < 0 || i >= this.z0.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.B0 = i;
        this.L.w(i);
        postInvalidate();
    }

    public void setHaloRadius(int i) {
        if (i == this.n0) {
            return;
        }
        this.n0 = i;
        Drawable background = getBackground();
        if ((getBackground() instanceof RippleDrawable) && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setRadius(this.n0);
        } else {
            postInvalidate();
        }
    }

    public void setHaloTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.K0)) {
            return;
        }
        this.K0 = colorStateList;
        Drawable background = getBackground();
        if ((getBackground() instanceof RippleDrawable) && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setColor(colorStateList);
            return;
        }
        int i = i(colorStateList);
        Paint paint = this.H;
        paint.setColor(i);
        paint.setAlpha(63);
        invalidate();
    }

    public void setLabelBehavior(int i) {
        if (this.i0 != i) {
            this.i0 = i;
            requestLayout();
        }
    }

    public void setSeparationUnit(int i) {
        this.W0 = i;
        this.J0 = true;
        postInvalidate();
    }

    public void setStepSize(float f) {
        if (f >= 0.0f) {
            if (this.C0 != f) {
                this.C0 = f;
                this.J0 = true;
                postInvalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("The stepSize(" + f + ") must be 0, or a factor of the valueFrom(" + this.x0 + ")-valueTo(" + this.y0 + ") range");
    }

    public void setThumbElevation(float f) {
        this.S0.l(f);
    }

    public void setThumbHeight(int i) {
        if (i == this.m0) {
            return;
        }
        this.m0 = i;
        this.S0.setBounds(0, 0, this.l0, i);
        Drawable drawable = this.T0;
        if (drawable != null) {
            a(drawable);
        }
        Iterator it = this.U0.iterator();
        while (it.hasNext()) {
            a((Drawable) it.next());
        }
        A();
    }

    public void setThumbRadius(int i) {
        int i2 = i * 2;
        setThumbWidth(i2);
        setThumbHeight(i2);
    }

    public void setThumbStrokeColor(ColorStateList colorStateList) {
        this.S0.r(colorStateList);
        postInvalidate();
    }

    public void setThumbStrokeWidth(float f) {
        this.S0.s(f);
        postInvalidate();
    }

    public void setThumbTrackGapSize(int i) {
        if (this.o0 == i) {
            return;
        }
        this.o0 = i;
        invalidate();
    }

    public void setThumbWidth(int i) {
        if (i == this.l0) {
            return;
        }
        this.l0 = i;
        is isVar = new is(0);
        is isVar2 = new is(0);
        is isVar3 = new is(0);
        is isVar4 = new is(0);
        float f = this.l0 / 2.0f;
        z91 z91VarN = ma0.n(0);
        xu0.b(z91VarN);
        xu0.b(z91VarN);
        xu0.b(z91VarN);
        xu0.b(z91VarN);
        o0 o0Var = new o0(f);
        o0 o0Var2 = new o0(f);
        o0 o0Var3 = new o0(f);
        o0 o0Var4 = new o0(f);
        zu0 zu0Var = new zu0();
        zu0Var.a = z91VarN;
        zu0Var.b = z91VarN;
        zu0Var.c = z91VarN;
        zu0Var.d = z91VarN;
        zu0Var.e = o0Var;
        zu0Var.f = o0Var2;
        zu0Var.g = o0Var3;
        zu0Var.h = o0Var4;
        zu0Var.i = isVar;
        zu0Var.j = isVar2;
        zu0Var.k = isVar3;
        zu0Var.l = isVar4;
        ld0 ld0Var = this.S0;
        ld0Var.setShapeAppearanceModel(zu0Var);
        ld0Var.setBounds(0, 0, this.l0, this.m0);
        Drawable drawable = this.T0;
        if (drawable != null) {
            a(drawable);
        }
        Iterator it = this.U0.iterator();
        while (it.hasNext()) {
            a((Drawable) it.next());
        }
        A();
    }

    public void setTickActiveRadius(int i) {
        if (this.F0 != i) {
            this.F0 = i;
            this.J.setStrokeWidth(i * 2);
            A();
        }
    }

    public void setTickActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.L0)) {
            return;
        }
        this.L0 = colorStateList;
        this.J.setColor(i(colorStateList));
        invalidate();
    }

    public void setTickInactiveRadius(int i) {
        if (this.G0 != i) {
            this.G0 = i;
            this.I.setStrokeWidth(i * 2);
            A();
        }
    }

    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.M0)) {
            return;
        }
        this.M0 = colorStateList;
        this.I.setColor(i(colorStateList));
        invalidate();
    }

    public void setTrackActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.N0)) {
            return;
        }
        this.N0 = colorStateList;
        this.F.setColor(i(colorStateList));
        this.K.setColor(i(this.N0));
        invalidate();
    }

    public void setTrackHeight(int i) {
        if (this.j0 != i) {
            this.j0 = i;
            this.E.setStrokeWidth(i);
            this.F.setStrokeWidth(this.j0);
            A();
        }
    }

    public void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.O0)) {
            return;
        }
        this.O0 = colorStateList;
        this.E.setColor(i(colorStateList));
        invalidate();
    }

    public void setTrackInsideCornerSize(int i) {
        if (this.s0 == i) {
            return;
        }
        this.s0 = i;
        invalidate();
    }

    public void setTrackStopIndicatorSize(int i) {
        if (this.r0 == i) {
            return;
        }
        this.r0 = i;
        this.K.setStrokeWidth(i);
        invalidate();
    }

    public void setValues(Float... fArr) {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, fArr);
        t(arrayList);
    }

    public final void t(ArrayList arrayList) {
        ViewGroup viewGroupQ;
        int resourceId;
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("At least one value must be set");
        }
        Collections.sort(arrayList);
        if (this.z0.size() == arrayList.size() && this.z0.equals(arrayList)) {
            return;
        }
        this.z0 = arrayList;
        this.J0 = true;
        this.B0 = 0;
        x();
        ArrayList arrayList2 = this.P;
        if (arrayList2.size() > this.z0.size()) {
            List<j11> listSubList = arrayList2.subList(this.z0.size(), arrayList2.size());
            for (j11 j11Var : listSubList) {
                WeakHashMap weakHashMap = f51.a;
                if (isAttachedToWindow()) {
                    ViewGroup viewGroupQ2 = fa0.Q(this);
                    wl0 wl0Var = viewGroupQ2 == null ? null : new wl0(viewGroupQ2);
                    if (wl0Var != null) {
                        ((ViewOverlay) wl0Var.E).remove(j11Var);
                        ViewGroup viewGroupQ3 = fa0.Q(this);
                        if (viewGroupQ3 == null) {
                            j11Var.getClass();
                        } else {
                            viewGroupQ3.removeOnLayoutChangeListener(j11Var.g0);
                        }
                    }
                }
            }
            listSubList.clear();
        }
        while (arrayList2.size() < this.z0.size()) {
            Context context = getContext();
            int i = this.O;
            j11 j11Var2 = new j11(context, i);
            TypedArray typedArrayD = i01.d(j11Var2.d0, null, R$styleable.Tooltip, 0, i, new int[0]);
            Context context2 = j11Var2.d0;
            j11Var2.n0 = context2.getResources().getDimensionPixelSize(R$dimen.mtrl_tooltip_arrowSize);
            boolean z = typedArrayD.getBoolean(R$styleable.Tooltip_showMarker, true);
            j11Var2.m0 = z;
            if (z) {
                xu0 xu0VarG = j11Var2.E.a.g();
                xu0VarG.k = j11Var2.x();
                j11Var2.setShapeAppearanceModel(xu0VarG.a());
            } else {
                j11Var2.n0 = 0;
            }
            CharSequence text = typedArrayD.getText(R$styleable.Tooltip_android_text);
            boolean zEquals = TextUtils.equals(j11Var2.c0, text);
            vz0 vz0Var = j11Var2.f0;
            if (!zEquals) {
                j11Var2.c0 = text;
                vz0Var.e = true;
                j11Var2.invalidateSelf();
            }
            int i2 = R$styleable.Tooltip_android_textAppearance;
            sz0 sz0Var = (!typedArrayD.hasValue(i2) || (resourceId = typedArrayD.getResourceId(i2, 0)) == 0) ? null : new sz0(context2, resourceId);
            if (sz0Var != null && typedArrayD.hasValue(R$styleable.Tooltip_android_textColor)) {
                sz0Var.j = la0.p(context2, typedArrayD, R$styleable.Tooltip_android_textColor);
            }
            vz0Var.c(sz0Var, context2);
            TypedValue typedValueW = s90.W(context2, R$attr.colorOnBackground, j11.class.getCanonicalName());
            int i3 = typedValueW.resourceId;
            int iA = i3 != 0 ? qi.a(context2, i3) : typedValueW.data;
            TypedValue typedValueW2 = s90.W(context2, R.attr.colorBackground, j11.class.getCanonicalName());
            int i4 = typedValueW2.resourceId;
            j11Var2.m(ColorStateList.valueOf(typedArrayD.getColor(R$styleable.Tooltip_backgroundTint, dg.c(dg.e(iA, 153), dg.e(i4 != 0 ? qi.a(context2, i4) : typedValueW2.data, 229)))));
            TypedValue typedValueW3 = s90.W(context2, R$attr.colorSurface, j11.class.getCanonicalName());
            int i5 = typedValueW3.resourceId;
            j11Var2.r(ColorStateList.valueOf(i5 != 0 ? qi.a(context2, i5) : typedValueW3.data));
            j11Var2.i0 = typedArrayD.getDimensionPixelSize(R$styleable.Tooltip_android_padding, 0);
            j11Var2.j0 = typedArrayD.getDimensionPixelSize(R$styleable.Tooltip_android_minWidth, 0);
            j11Var2.k0 = typedArrayD.getDimensionPixelSize(R$styleable.Tooltip_android_minHeight, 0);
            j11Var2.l0 = typedArrayD.getDimensionPixelSize(R$styleable.Tooltip_android_layout_margin, 0);
            typedArrayD.recycle();
            arrayList2.add(j11Var2);
            WeakHashMap weakHashMap2 = f51.a;
            if (isAttachedToWindow() && (viewGroupQ = fa0.Q(this)) != null) {
                int[] iArr = new int[2];
                viewGroupQ.getLocationOnScreen(iArr);
                j11Var2.o0 = iArr[0];
                viewGroupQ.getWindowVisibleDisplayFrame(j11Var2.h0);
                viewGroupQ.addOnLayoutChangeListener(j11Var2.g0);
            }
        }
        int i6 = arrayList2.size() == 1 ? 0 : 1;
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            ((j11) it.next()).s(i6);
        }
        Iterator it2 = this.Q.iterator();
        while (it2.hasNext()) {
            if (it2.next() != null) {
                throw new ClassCastException();
            }
            Iterator it3 = this.z0.iterator();
            if (it3.hasNext()) {
                ((Float) it3.next()).getClass();
                throw null;
            }
        }
        postInvalidate();
    }

    public final boolean u(int i, float f) {
        this.B0 = i;
        if (Math.abs(f - ((Float) this.z0.get(i)).floatValue()) < 1.0E-4d) {
            return false;
        }
        float minSeparation = getMinSeparation();
        if (this.W0 == 0) {
            if (minSeparation == 0.0f) {
                minSeparation = 0.0f;
            } else {
                float f2 = this.x0;
                minSeparation = zn.d(f2, this.y0, (minSeparation - this.k0) / this.H0, f2);
            }
        }
        if (l()) {
            minSeparation = -minSeparation;
        }
        int i2 = i + 1;
        int i3 = i - 1;
        this.z0.set(i, Float.valueOf(s90.l(f, i3 < 0 ? this.x0 : minSeparation + ((Float) this.z0.get(i3)).floatValue(), i2 >= this.z0.size() ? this.y0 : ((Float) this.z0.get(i2)).floatValue() - minSeparation)));
        Iterator it = this.Q.iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            ((Float) this.z0.get(i)).getClass();
            throw null;
        }
        AccessibilityManager accessibilityManager = this.M;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return true;
        }
        d dVar = this.N;
        if (dVar == null) {
            this.N = new d(this);
        } else {
            removeCallbacks(dVar);
        }
        d dVar2 = this.N;
        dVar2.E = i;
        postDelayed(dVar2, 200L);
        return true;
    }

    public final void v() {
        double dRound;
        float f = this.V0;
        float f2 = this.C0;
        if (f2 > 0.0f) {
            int i = (int) ((this.y0 - this.x0) / f2);
            dRound = ((double) Math.round(f * i)) / ((double) i);
        } else {
            dRound = f;
        }
        if (l()) {
            dRound = 1.0d - dRound;
        }
        float f3 = this.y0;
        float f4 = this.x0;
        u(this.A0, (float) ((dRound * ((double) (f3 - f4))) + ((double) f4)));
    }

    public final void w(int i, Rect rect) {
        int iP = this.k0 + ((int) (p(getValues().get(i).floatValue()) * this.H0));
        int iB = b();
        int iMax = Math.max(this.l0 / 2, this.f0 / 2);
        int iMax2 = Math.max(this.m0 / 2, this.f0 / 2);
        rect.set(iP - iMax, iB - iMax2, iP + iMax, iB + iMax2);
    }

    public final void x() {
        if (!(getBackground() instanceof RippleDrawable) || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int iP = (int) ((p(((Float) this.z0.get(this.B0)).floatValue()) * this.H0) + this.k0);
            int iB = b();
            int i = this.n0;
            ip.f(background, iP - i, iB - i, iP + i, iB + i);
        }
    }

    public final void y() {
        int i = this.i0;
        if (i == 0 || i == 1) {
            if (this.A0 == -1 || !isEnabled()) {
                f();
                return;
            } else {
                e();
                return;
            }
        }
        if (i == 2) {
            f();
            return;
        }
        if (i != 3) {
            throw new IllegalArgumentException("Unexpected labelBehavior: " + this.i0);
        }
        if (isEnabled()) {
            Rect rect = new Rect();
            fa0.Q(this).getHitRect(rect);
            if (getLocalVisibleRect(rect)) {
                e();
                return;
            }
        }
        f();
    }

    public final void z(Canvas canvas, Paint paint, RectF rectF, int i) {
        float f;
        float f2 = this.j0 / 2.0f;
        int iW = zn.w(i);
        if (iW == 1) {
            f = this.s0;
        } else if (iW != 2) {
            if (iW == 3) {
                f2 = this.s0;
            }
            f = f2;
        } else {
            f = f2;
            f2 = this.s0;
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        Path path = this.P0;
        path.reset();
        if (rectF.width() >= f2 + f) {
            path.addRoundRect(rectF, new float[]{f2, f2, f, f, f, f, f2, f2}, Path.Direction.CW);
            canvas.drawPath(path, paint);
            return;
        }
        float fMin = Math.min(f2, f);
        float fMax = Math.max(f2, f);
        canvas.save();
        path.addRoundRect(rectF, fMin, fMin, Path.Direction.CW);
        canvas.clipPath(path);
        int iW2 = zn.w(i);
        RectF rectF2 = this.R0;
        if (iW2 == 1) {
            float f3 = rectF.left;
            rectF2.set(f3, rectF.top, (2.0f * fMax) + f3, rectF.bottom);
        } else if (iW2 != 2) {
            rectF2.set(rectF.centerX() - fMax, rectF.top, rectF.centerX() + fMax, rectF.bottom);
        } else {
            float f4 = rectF.right;
            rectF2.set(f4 - (2.0f * fMax), rectF.top, f4, rectF.bottom);
        }
        canvas.drawRoundRect(rectF2, fMax, fMax, paint);
        canvas.restore();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.google.android.material.slider.a] */
    public BaseSlider(Context context, AttributeSet attributeSet, int i) {
        int i2 = Y0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.P = new ArrayList();
        this.Q = new ArrayList();
        this.R = new ArrayList();
        this.S = false;
        this.p0 = -1;
        this.q0 = -1;
        this.w0 = false;
        this.z0 = new ArrayList();
        this.A0 = -1;
        this.B0 = -1;
        this.C0 = 0.0f;
        this.E0 = true;
        this.I0 = false;
        this.P0 = new Path();
        this.Q0 = new RectF();
        this.R0 = new RectF();
        ld0 ld0Var = new ld0();
        this.S0 = ld0Var;
        this.U0 = Collections.EMPTY_LIST;
        this.W0 = 0;
        this.X0 = new ViewTreeObserver.OnScrollChangedListener() { // from class: com.google.android.material.slider.a
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                int i3 = BaseSlider.Y0;
                this.a.y();
            }
        };
        Context context2 = getContext();
        this.E = new Paint();
        this.F = new Paint();
        Paint paint = new Paint(1);
        this.G = paint;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint2 = new Paint(1);
        this.H = paint2;
        paint2.setStyle(style);
        Paint paint3 = new Paint();
        this.I = paint3;
        Paint.Style style2 = Paint.Style.STROKE;
        paint3.setStyle(style2);
        Paint.Cap cap = Paint.Cap.ROUND;
        paint3.setStrokeCap(cap);
        Paint paint4 = new Paint();
        this.J = paint4;
        paint4.setStyle(style2);
        paint4.setStrokeCap(cap);
        Paint paint5 = new Paint();
        this.K = paint5;
        paint5.setStyle(style);
        paint5.setStrokeCap(cap);
        Resources resources = context2.getResources();
        this.g0 = resources.getDimensionPixelSize(R$dimen.mtrl_slider_widget_height);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.mtrl_slider_track_side_padding);
        this.W = dimensionPixelOffset;
        this.k0 = dimensionPixelOffset;
        this.a0 = resources.getDimensionPixelSize(R$dimen.mtrl_slider_thumb_radius);
        this.b0 = resources.getDimensionPixelSize(R$dimen.mtrl_slider_track_height);
        this.c0 = resources.getDimensionPixelSize(R$dimen.mtrl_slider_tick_radius);
        this.d0 = resources.getDimensionPixelSize(R$dimen.mtrl_slider_tick_radius);
        this.e0 = resources.getDimensionPixelSize(R$dimen.mtrl_slider_tick_min_spacing);
        this.t0 = resources.getDimensionPixelSize(R$dimen.mtrl_slider_label_padding);
        int[] iArr = R$styleable.Slider;
        i01.a(context2, attributeSet, i, i2);
        i01.b(context2, attributeSet, iArr, i, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, i2);
        this.O = typedArrayObtainStyledAttributes.getResourceId(R$styleable.Slider_labelStyle, R$style.Widget_MaterialComponents_Tooltip);
        this.x0 = typedArrayObtainStyledAttributes.getFloat(R$styleable.Slider_android_valueFrom, 0.0f);
        this.y0 = typedArrayObtainStyledAttributes.getFloat(R$styleable.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.x0));
        this.C0 = typedArrayObtainStyledAttributes.getFloat(R$styleable.Slider_android_stepSize, 0.0f);
        this.f0 = (int) Math.ceil(typedArrayObtainStyledAttributes.getDimension(R$styleable.Slider_minTouchTargetSize, (float) Math.ceil(fa0.C(getContext(), 48))));
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(R$styleable.Slider_trackColor);
        int i3 = zHasValue ? R$styleable.Slider_trackColor : R$styleable.Slider_trackColorInactive;
        int i4 = zHasValue ? R$styleable.Slider_trackColor : R$styleable.Slider_trackColorActive;
        ColorStateList colorStateListP = la0.p(context2, typedArrayObtainStyledAttributes, i3);
        setTrackInactiveTintList(colorStateListP == null ? t40.Y(context2, R$color.material_slider_inactive_track_color) : colorStateListP);
        ColorStateList colorStateListP2 = la0.p(context2, typedArrayObtainStyledAttributes, i4);
        setTrackActiveTintList(colorStateListP2 == null ? t40.Y(context2, R$color.material_slider_active_track_color) : colorStateListP2);
        ld0Var.m(la0.p(context2, typedArrayObtainStyledAttributes, R$styleable.Slider_thumbColor));
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.Slider_thumbStrokeColor)) {
            setThumbStrokeColor(la0.p(context2, typedArrayObtainStyledAttributes, R$styleable.Slider_thumbStrokeColor));
        }
        setThumbStrokeWidth(typedArrayObtainStyledAttributes.getDimension(R$styleable.Slider_thumbStrokeWidth, 0.0f));
        ColorStateList colorStateListP3 = la0.p(context2, typedArrayObtainStyledAttributes, R$styleable.Slider_haloColor);
        setHaloTintList(colorStateListP3 == null ? t40.Y(context2, R$color.material_slider_halo_color) : colorStateListP3);
        this.E0 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Slider_tickVisible, true);
        boolean zHasValue2 = typedArrayObtainStyledAttributes.hasValue(R$styleable.Slider_tickColor);
        int i5 = zHasValue2 ? R$styleable.Slider_tickColor : R$styleable.Slider_tickColorInactive;
        int i6 = zHasValue2 ? R$styleable.Slider_tickColor : R$styleable.Slider_tickColorActive;
        ColorStateList colorStateListP4 = la0.p(context2, typedArrayObtainStyledAttributes, i5);
        setTickInactiveTintList(colorStateListP4 == null ? t40.Y(context2, R$color.material_slider_inactive_tick_marks_color) : colorStateListP4);
        ColorStateList colorStateListP5 = la0.p(context2, typedArrayObtainStyledAttributes, i6);
        setTickActiveTintList(colorStateListP5 == null ? t40.Y(context2, R$color.material_slider_active_tick_marks_color) : colorStateListP5);
        setThumbTrackGapSize(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_thumbTrackGapSize, 0));
        setTrackStopIndicatorSize(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_trackStopIndicatorSize, 0));
        setTrackInsideCornerSize(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_trackInsideCornerSize, 0));
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_thumbRadius, 0) * 2;
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_thumbWidth, dimensionPixelSize);
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_thumbHeight, dimensionPixelSize);
        setThumbWidth(dimensionPixelSize2);
        setThumbHeight(dimensionPixelSize3);
        setHaloRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_haloRadius, 0));
        setThumbElevation(typedArrayObtainStyledAttributes.getDimension(R$styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_trackHeight, 0));
        setTickActiveRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_tickRadiusActive, this.r0 / 2));
        setTickInactiveRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_tickRadiusInactive, this.r0 / 2));
        setLabelBehavior(typedArrayObtainStyledAttributes.getInt(R$styleable.Slider_labelBehavior, 0));
        if (!typedArrayObtainStyledAttributes.getBoolean(R$styleable.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        typedArrayObtainStyledAttributes.recycle();
        setFocusable(true);
        setClickable(true);
        ld0Var.q(2);
        this.V = ViewConfiguration.get(context2).getScaledTouchSlop();
        e eVar = new e(this);
        this.L = eVar;
        f51.o(this, eVar);
        this.M = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    public void setValues(List<Float> list) {
        t(new ArrayList(list));
    }

    public void setCustomThumbDrawablesForValues(Drawable... drawableArr) {
        this.T0 = null;
        this.U0 = new ArrayList();
        for (Drawable drawable : drawableArr) {
            List list = this.U0;
            Drawable drawableNewDrawable = drawable.mutate().getConstantState().newDrawable();
            a(drawableNewDrawable);
            list.add(drawableNewDrawable);
        }
        postInvalidate();
    }
}
