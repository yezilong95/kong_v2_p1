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
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
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
import android.view.accessibility.AccessibilityManager;
import android.widget.SeekBar;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.slider.BaseSlider;
import defpackage.a8;
import defpackage.b8;
import defpackage.bh;
import defpackage.d90;
import defpackage.en0;
import defpackage.hk;
import defpackage.hl;
import defpackage.hn0;
import defpackage.ii0;
import defpackage.je;
import defpackage.jk;
import defpackage.k9;
import defpackage.ki0;
import defpackage.nv0;
import defpackage.oy;
import defpackage.qm;
import defpackage.rp0;
import defpackage.t3;
import defpackage.u50;
import defpackage.un0;
import defpackage.xi;
import defpackage.xo0;
import defpackage.xr0;
import defpackage.y50;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends a8, T extends b8> extends View {
    public static final int j0 = R$style.Widget_MaterialComponents_Slider;
    public static final int k0 = R$attr.motionDurationMedium4;
    public static final int l0 = R$attr.motionDurationShort3;
    public static final int m0 = R$attr.motionEasingEmphasizedInterpolator;
    public static final int n0 = R$attr.motionEasingEmphasizedAccelerateInterpolator;
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public final int F;
    public float G;
    public MotionEvent H;
    public boolean I;
    public float J;
    public float K;
    public ArrayList L;
    public int M;
    public int N;
    public float O;
    public float[] P;
    public boolean Q;
    public int R;
    public int S;
    public int T;
    public boolean U;
    public boolean V;
    public ColorStateList W;
    public ColorStateList a0;
    public final Paint b;
    public ColorStateList b0;
    public final Paint c;
    public ColorStateList c0;
    public final Paint d;
    public ColorStateList d0;
    public final Paint e;
    public final u50 e0;
    public final Paint f;
    public Drawable f0;
    public final Paint g;
    public List g0;
    public final d h;
    public float h0;
    public final AccessibilityManager i;
    public int i0;
    public c j;
    public final int k;
    public final ArrayList l;
    public final ArrayList m;
    public final ArrayList n;
    public boolean o;
    public ValueAnimator p;
    public ValueAnimator q;
    public final int r;
    public final int s;
    public final int t;
    public final int u;
    public final int v;
    public final int w;
    public final int x;
    public final int y;
    public int z;

    public static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new e();
        public float b;
        public float c;
        public ArrayList d;
        public float e;
        public boolean f;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.b);
            parcel.writeFloat(this.c);
            parcel.writeList(this.d);
            parcel.writeFloat(this.e);
            parcel.writeBooleanArray(new boolean[]{this.f});
        }
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.sliderStyle);
    }

    public final void a(Drawable drawable) {
        int i = this.D * 2;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth == -1 && intrinsicHeight == -1) {
            drawable.setBounds(0, 0, i, i);
        } else {
            float fMax = i / Math.max(intrinsicWidth, intrinsicHeight);
            drawable.setBounds(0, 0, (int) (intrinsicWidth * fMax), (int) (intrinsicHeight * fMax));
        }
    }

    public void addOnChangeListener(L l) {
        this.m.add(l);
    }

    public void addOnSliderTouchListener(T t) {
        this.n.add(t);
    }

    public final int b() {
        int i = this.z / 2;
        int i2 = this.A;
        return i + ((i2 == 1 || i2 == 3) ? ((xo0) this.l.get(0)).getIntrinsicHeight() : 0);
    }

    public final ValueAnimator c(boolean z) {
        int iE;
        TimeInterpolator timeInterpolatorF;
        float fFloatValue = z ? 0.0f : 1.0f;
        ValueAnimator valueAnimator = z ? this.q : this.p;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fFloatValue, z ? 1.0f : 0.0f);
        if (z) {
            iE = k9.E(getContext(), k0, 83);
            timeInterpolatorF = k9.F(getContext(), m0, t3.e);
        } else {
            iE = k9.E(getContext(), l0, 117);
            timeInterpolatorF = k9.F(getContext(), n0, t3.c);
        }
        valueAnimatorOfFloat.setDuration(iE);
        valueAnimatorOfFloat.setInterpolator(timeInterpolatorF);
        valueAnimatorOfFloat.addUpdateListener(new a(this));
        return valueAnimatorOfFloat;
    }

    public final void d(Canvas canvas, int i, int i2, float f, Drawable drawable) {
        canvas.save();
        canvas.translate((this.C + ((int) (n(f) * i))) - (drawable.getBounds().width() / 2.0f), i2 - (drawable.getBounds().height() / 2.0f));
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.h.m(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        this.b.setColor(g(this.d0));
        this.c.setColor(g(this.c0));
        this.f.setColor(g(this.b0));
        this.g.setColor(g(this.a0));
        for (xo0 xo0Var : this.l) {
            if (xo0Var.isStateful()) {
                xo0Var.setState(getDrawableState());
            }
        }
        u50 u50Var = this.e0;
        if (u50Var.isStateful()) {
            u50Var.setState(getDrawableState());
        }
        Paint paint = this.e;
        paint.setColor(g(this.W));
        paint.setAlpha(63);
    }

    public final String e(float f) {
        return String.format(((float) ((int) f)) == f ? "%.0f" : "%.2f", Float.valueOf(f));
    }

    public final float[] f() {
        float fFloatValue = ((Float) Collections.max(getValues())).floatValue();
        float fFloatValue2 = ((Float) Collections.min(getValues())).floatValue();
        if (this.L.size() == 1) {
            fFloatValue2 = this.J;
        }
        float fN = n(fFloatValue2);
        float fN2 = n(fFloatValue);
        return j() ? new float[]{fN2, fN} : new float[]{fN, fN2};
    }

    public final int g(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.h.k;
    }

    public float getMinSeparation() {
        return 0.0f;
    }

    public float getValueFrom() {
        return this.J;
    }

    public float getValueTo() {
        return this.K;
    }

    public List<Float> getValues() {
        return new ArrayList(this.L);
    }

    public final boolean h(float f) {
        double dDoubleValue = new BigDecimal(Float.toString(f)).divide(new BigDecimal(Float.toString(this.O)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(dDoubleValue)) - dDoubleValue) < 1.0E-4d;
    }

    public final boolean i(MotionEvent motionEvent) {
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

    public final boolean j() {
        WeakHashMap weakHashMap = xr0.a;
        return getLayoutDirection() == 1;
    }

    public final void k() {
        if (this.O <= 0.0f) {
            return;
        }
        x();
        int iMin = Math.min((int) (((this.K - this.J) / this.O) + 1.0f), (this.T / (this.B * 2)) + 1);
        float[] fArr = this.P;
        if (fArr == null || fArr.length != iMin * 2) {
            this.P = new float[iMin * 2];
        }
        float f = this.T / (iMin - 1);
        for (int i = 0; i < iMin * 2; i += 2) {
            float[] fArr2 = this.P;
            fArr2[i] = ((i / 2.0f) * f) + this.C;
            fArr2[i + 1] = b();
        }
    }

    public final boolean l(int i) {
        int i2 = this.N;
        long j = ((long) i2) + ((long) i);
        long size = this.L.size() - 1;
        if (j < 0) {
            j = 0;
        } else if (j > size) {
            j = size;
        }
        int i3 = (int) j;
        this.N = i3;
        if (i3 == i2) {
            return false;
        }
        if (this.M != -1) {
            this.M = i3;
        }
        v();
        postInvalidate();
        return true;
    }

    public final void m(int i) {
        if (j()) {
            i = i == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i;
        }
        l(i);
    }

    public final float n(float f) {
        float f2 = this.J;
        float f3 = (f - f2) / (this.K - f2);
        return j() ? 1.0f - f3 : f3;
    }

    public final void o() {
        Iterator it = this.n.iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        for (xo0 xo0Var : this.l) {
            ViewGroup viewGroupX = rp0.x(this);
            if (viewGroupX == null) {
                xo0Var.getClass();
            } else {
                xo0Var.getClass();
                int[] iArr = new int[2];
                viewGroupX.getLocationOnScreen(iArr);
                xo0Var.J = iArr[0];
                viewGroupX.getWindowVisibleDisplayFrame(xo0Var.D);
                viewGroupX.addOnLayoutChangeListener(xo0Var.C);
            }
        }
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        c cVar = this.j;
        if (cVar != null) {
            removeCallbacks(cVar);
        }
        this.o = false;
        for (xo0 xo0Var : this.l) {
            ViewGroup viewGroupX = rp0.x(this);
            d90 d90Var = viewGroupX == null ? null : new d90(viewGroupX);
            if (d90Var != null) {
                ((ViewOverlay) d90Var.b).remove(xo0Var);
                ViewGroup viewGroupX2 = rp0.x(this);
                if (viewGroupX2 == null) {
                    xo0Var.getClass();
                } else {
                    viewGroupX2.removeOnLayoutChangeListener(xo0Var.C);
                }
            }
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int i;
        if (this.V) {
            x();
            k();
        }
        super.onDraw(canvas);
        int iB = b();
        int i2 = this.T;
        float[] fArrF = f();
        int i3 = this.C;
        float f = i2;
        float f2 = (fArrF[1] * f) + i3;
        float f3 = i3 + i2;
        Paint paint = this.b;
        if (f2 < f3) {
            float f4 = iB;
            canvas.drawLine(f2, f4, f3, f4, paint);
        }
        float f5 = this.C;
        int i4 = 0;
        float f6 = (fArrF[0] * f) + f5;
        if (f6 > f5) {
            float f7 = iB;
            canvas.drawLine(f5, f7, f6, f7, paint);
        }
        if (((Float) Collections.max(getValues())).floatValue() > this.J) {
            int i5 = this.T;
            float[] fArrF2 = f();
            float f8 = this.C;
            float f9 = i5;
            float f10 = (fArrF2[1] * f9) + f8;
            float f11 = (fArrF2[0] * f9) + f8;
            float f12 = iB;
            canvas.drawLine(f11, f12, f10, f12, this.c);
        }
        if (this.Q && this.O > 0.0f) {
            float[] fArrF3 = f();
            int iRound = Math.round(fArrF3[0] * ((this.P.length / 2) - 1));
            int iRound2 = Math.round(fArrF3[1] * ((this.P.length / 2) - 1));
            float[] fArr = this.P;
            int i6 = iRound * 2;
            Paint paint2 = this.f;
            canvas.drawPoints(fArr, 0, i6, paint2);
            int i7 = iRound2 * 2;
            canvas.drawPoints(this.P, i6, i7 - i6, this.g);
            float[] fArr2 = this.P;
            canvas.drawPoints(fArr2, i7, fArr2.length - i7, paint2);
        }
        if ((this.I || isFocused()) && isEnabled()) {
            int i8 = this.T;
            if (!(getBackground() instanceof RippleDrawable)) {
                int iN = (int) ((n(((Float) this.L.get(this.N)).floatValue()) * i8) + this.C);
                if (Build.VERSION.SDK_INT < 28) {
                    int i9 = this.E;
                    canvas.clipRect(iN - i9, iB - i9, iN + i9, i9 + iB, Region.Op.UNION);
                }
                canvas.drawCircle(iN, iB, this.E, this.e);
            }
        }
        if ((this.M != -1 || this.A == 3) && isEnabled()) {
            if (this.A != 2) {
                if (!this.o) {
                    this.o = true;
                    ValueAnimator valueAnimatorC = c(true);
                    this.p = valueAnimatorC;
                    this.q = null;
                    valueAnimatorC.start();
                }
                ArrayList arrayList = this.l;
                Iterator it = arrayList.iterator();
                for (int i10 = 0; i10 < this.L.size() && it.hasNext(); i10++) {
                    if (i10 != this.N) {
                        q((xo0) it.next(), ((Float) this.L.get(i10)).floatValue());
                    }
                }
                if (!it.hasNext()) {
                    throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(arrayList.size()), Integer.valueOf(this.L.size())));
                }
                q((xo0) it.next(), ((Float) this.L.get(this.N)).floatValue());
            }
        } else if (this.o) {
            this.o = false;
            ValueAnimator valueAnimatorC2 = c(false);
            this.q = valueAnimatorC2;
            this.p = null;
            valueAnimatorC2.addListener(new b(this));
            this.q.start();
        }
        int i11 = this.T;
        while (i4 < this.L.size()) {
            float fFloatValue = ((Float) this.L.get(i4)).floatValue();
            Drawable drawable = this.f0;
            if (drawable != null) {
                i = iB;
                d(canvas, i11, i, fFloatValue, drawable);
            } else {
                i = iB;
                if (i4 < this.g0.size()) {
                    d(canvas, i11, i, fFloatValue, (Drawable) this.g0.get(i4));
                } else {
                    if (!isEnabled()) {
                        canvas.drawCircle((n(fFloatValue) * i11) + this.C, i, this.D, this.d);
                    }
                    d(canvas, i11, i, fFloatValue, this.e0);
                }
            }
            i4++;
            iB = i;
        }
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        d dVar = this.h;
        if (!z) {
            this.M = -1;
            dVar.j(this.N);
            return;
        }
        if (i == 1) {
            l(Integer.MAX_VALUE);
        } else if (i == 2) {
            l(Integer.MIN_VALUE);
        } else if (i == 17) {
            m(Integer.MAX_VALUE);
        } else if (i == 66) {
            m(Integer.MIN_VALUE);
        }
        dVar.w(this.N);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    @Override // android.view.View, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onKeyDown(int r14, android.view.KeyEvent r15) {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        this.U = false;
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int i3 = this.z;
        int i4 = this.A;
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3 + ((i4 == 1 || i4 == 3) ? ((xo0) this.l.get(0)).getIntrinsicHeight() : 0), 1073741824));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.J = sliderState.b;
        this.K = sliderState.c;
        r(sliderState.d);
        this.O = sliderState.e;
        if (sliderState.f) {
            requestFocus();
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.b = this.J;
        sliderState.c = this.K;
        sliderState.d = new ArrayList(this.L);
        sliderState.e = this.O;
        sliderState.f = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        this.T = Math.max(i - (this.C * 2), 0);
        k();
        v();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0) {
            ViewGroup viewGroupX = rp0.x(this);
            d90 d90Var = viewGroupX == null ? null : new d90(viewGroupX);
            if (d90Var == null) {
                return;
            }
            Iterator it = this.l.iterator();
            while (it.hasNext()) {
                ((ViewOverlay) d90Var.b).remove((xo0) it.next());
            }
        }
    }

    public boolean p() {
        if (this.M == -1) {
            float f = this.h0;
            if (j()) {
                f = 1.0f - f;
            }
            float f2 = this.K;
            float f3 = this.J;
            float fD = hk.d(f2, f3, f, f3);
            float fN = (n(fD) * this.T) + this.C;
            this.M = 0;
            float fAbs = Math.abs(((Float) this.L.get(0)).floatValue() - fD);
            for (int i = 1; i < this.L.size(); i++) {
                float fAbs2 = Math.abs(((Float) this.L.get(i)).floatValue() - fD);
                float fN2 = (n(((Float) this.L.get(i)).floatValue()) * this.T) + this.C;
                if (Float.compare(fAbs2, fAbs) > 1) {
                    break;
                }
                boolean z = !j() ? fN2 - fN >= 0.0f : fN2 - fN <= 0.0f;
                if (Float.compare(fAbs2, fAbs) < 0) {
                    this.M = i;
                } else {
                    if (Float.compare(fAbs2, fAbs) != 0) {
                        continue;
                    } else {
                        if (Math.abs(fN2 - fN) < this.r) {
                            this.M = -1;
                            return false;
                        }
                        if (z) {
                            this.M = i;
                        }
                    }
                }
                fAbs = fAbs2;
            }
            if (this.M == -1) {
                return false;
            }
        }
        return true;
    }

    public final void q(xo0 xo0Var, float f) {
        String strE = e(f);
        if (!TextUtils.equals(xo0Var.y, strE)) {
            xo0Var.y = strE;
            xo0Var.B.e = true;
            xo0Var.invalidateSelf();
        }
        int iN = (this.C + ((int) (n(f) * this.T))) - (xo0Var.getIntrinsicWidth() / 2);
        int iB = b() - (this.F + this.D);
        xo0Var.setBounds(iN, iB - xo0Var.getIntrinsicHeight(), xo0Var.getIntrinsicWidth() + iN, iB);
        Rect rect = new Rect(xo0Var.getBounds());
        jk.c(rp0.x(this), this, rect);
        xo0Var.setBounds(rect);
        ViewGroup viewGroupX = rp0.x(this);
        ((ViewOverlay) (viewGroupX == null ? null : new d90(viewGroupX)).b).add(xo0Var);
    }

    public final void r(ArrayList arrayList) {
        ViewGroup viewGroupX;
        int resourceId;
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("At least one value must be set");
        }
        Collections.sort(arrayList);
        if (this.L.size() == arrayList.size() && this.L.equals(arrayList)) {
            return;
        }
        this.L = arrayList;
        this.V = true;
        this.N = 0;
        v();
        ArrayList arrayList2 = this.l;
        if (arrayList2.size() > this.L.size()) {
            List<xo0> listSubList = arrayList2.subList(this.L.size(), arrayList2.size());
            for (xo0 xo0Var : listSubList) {
                WeakHashMap weakHashMap = xr0.a;
                if (isAttachedToWindow()) {
                    ViewGroup viewGroupX2 = rp0.x(this);
                    d90 d90Var = viewGroupX2 == null ? null : new d90(viewGroupX2);
                    if (d90Var != null) {
                        ((ViewOverlay) d90Var.b).remove(xo0Var);
                        ViewGroup viewGroupX3 = rp0.x(this);
                        if (viewGroupX3 == null) {
                            xo0Var.getClass();
                        } else {
                            viewGroupX3.removeOnLayoutChangeListener(xo0Var.C);
                        }
                    }
                }
            }
            listSubList.clear();
        }
        while (arrayList2.size() < this.L.size()) {
            Context context = getContext();
            int i = this.k;
            xo0 xo0Var2 = new xo0(context, i);
            TypedArray typedArrayD = un0.d(xo0Var2.z, null, R$styleable.Tooltip, 0, i, new int[0]);
            Context context2 = xo0Var2.z;
            xo0Var2.I = context2.getResources().getDimensionPixelSize(R$dimen.mtrl_tooltip_arrowSize);
            ii0 ii0VarG = xo0Var2.b.a.g();
            ii0VarG.k = xo0Var2.x();
            xo0Var2.setShapeAppearanceModel(ii0VarG.a());
            CharSequence text = typedArrayD.getText(R$styleable.Tooltip_android_text);
            boolean zEquals = TextUtils.equals(xo0Var2.y, text);
            hn0 hn0Var = xo0Var2.B;
            if (!zEquals) {
                xo0Var2.y = text;
                hn0Var.e = true;
                xo0Var2.invalidateSelf();
            }
            int i2 = R$styleable.Tooltip_android_textAppearance;
            en0 en0Var = (!typedArrayD.hasValue(i2) || (resourceId = typedArrayD.getResourceId(i2, 0)) == 0) ? null : new en0(context2, resourceId);
            if (en0Var != null && typedArrayD.hasValue(R$styleable.Tooltip_android_textColor)) {
                en0Var.j = xi.s(context2, typedArrayD, R$styleable.Tooltip_android_textColor);
            }
            hn0Var.c(en0Var, context2);
            TypedValue typedValueL = nv0.L(context2, R$attr.colorOnBackground, xo0.class.getCanonicalName());
            int i3 = typedValueL.resourceId;
            int iA = i3 != 0 ? bh.a(context2, i3) : typedValueL.data;
            TypedValue typedValueL2 = nv0.L(context2, R.attr.colorBackground, xo0.class.getCanonicalName());
            int i4 = typedValueL2.resourceId;
            xo0Var2.m(ColorStateList.valueOf(typedArrayD.getColor(R$styleable.Tooltip_backgroundTint, je.c(je.e(iA, 153), je.e(i4 != 0 ? bh.a(context2, i4) : typedValueL2.data, 229)))));
            TypedValue typedValueL3 = nv0.L(context2, R$attr.colorSurface, xo0.class.getCanonicalName());
            int i5 = typedValueL3.resourceId;
            xo0Var2.r(ColorStateList.valueOf(i5 != 0 ? bh.a(context2, i5) : typedValueL3.data));
            xo0Var2.E = typedArrayD.getDimensionPixelSize(R$styleable.Tooltip_android_padding, 0);
            xo0Var2.F = typedArrayD.getDimensionPixelSize(R$styleable.Tooltip_android_minWidth, 0);
            xo0Var2.G = typedArrayD.getDimensionPixelSize(R$styleable.Tooltip_android_minHeight, 0);
            xo0Var2.H = typedArrayD.getDimensionPixelSize(R$styleable.Tooltip_android_layout_margin, 0);
            typedArrayD.recycle();
            arrayList2.add(xo0Var2);
            WeakHashMap weakHashMap2 = xr0.a;
            if (isAttachedToWindow() && (viewGroupX = rp0.x(this)) != null) {
                int[] iArr = new int[2];
                viewGroupX.getLocationOnScreen(iArr);
                xo0Var2.J = iArr[0];
                viewGroupX.getWindowVisibleDisplayFrame(xo0Var2.D);
                viewGroupX.addOnLayoutChangeListener(xo0Var2.C);
            }
        }
        int i6 = arrayList2.size() == 1 ? 0 : 1;
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            ((xo0) it.next()).s(i6);
        }
        Iterator it2 = this.m.iterator();
        while (it2.hasNext()) {
            if (it2.next() != null) {
                throw new ClassCastException();
            }
            Iterator it3 = this.L.iterator();
            if (it3.hasNext()) {
                ((Float) it3.next()).getClass();
                throw null;
            }
        }
        postInvalidate();
    }

    public void removeOnChangeListener(L l) {
        this.m.remove(l);
    }

    public void removeOnSliderTouchListener(T t) {
        this.n.remove(t);
    }

    public final boolean s(int i, float f) {
        this.N = i;
        if (Math.abs(f - ((Float) this.L.get(i)).floatValue()) < 1.0E-4d) {
            return false;
        }
        float minSeparation = getMinSeparation();
        if (this.i0 == 0) {
            if (minSeparation == 0.0f) {
                minSeparation = 0.0f;
            } else {
                float f2 = this.J;
                minSeparation = hk.d(f2, this.K, (minSeparation - this.C) / this.T, f2);
            }
        }
        if (j()) {
            minSeparation = -minSeparation;
        }
        int i2 = i + 1;
        int i3 = i - 1;
        this.L.set(i, Float.valueOf(rp0.m(f, i3 < 0 ? this.J : minSeparation + ((Float) this.L.get(i3)).floatValue(), i2 >= this.L.size() ? this.K : ((Float) this.L.get(i2)).floatValue() - minSeparation)));
        Iterator it = this.m.iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            ((Float) this.L.get(i)).getClass();
            throw null;
        }
        AccessibilityManager accessibilityManager = this.i;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return true;
        }
        c cVar = this.j;
        if (cVar == null) {
            this.j = new c(this);
        } else {
            removeCallbacks(cVar);
        }
        c cVar2 = this.j;
        cVar2.b = i;
        postDelayed(cVar2, 200L);
        return true;
    }

    public void setActiveThumbIndex(int i) {
        this.M = i;
    }

    public void setCustomThumbDrawable(Drawable drawable) {
        Drawable drawableNewDrawable = drawable.mutate().getConstantState().newDrawable();
        a(drawableNewDrawable);
        this.f0 = drawableNewDrawable;
        this.g0.clear();
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
        if (i < 0 || i >= this.L.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.N = i;
        this.h.w(i);
        postInvalidate();
    }

    public void setHaloRadius(int i) {
        if (i == this.E) {
            return;
        }
        this.E = i;
        Drawable background = getBackground();
        if ((getBackground() instanceof RippleDrawable) && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setRadius(this.E);
        } else {
            postInvalidate();
        }
    }

    public void setHaloTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.W)) {
            return;
        }
        this.W = colorStateList;
        Drawable background = getBackground();
        if ((getBackground() instanceof RippleDrawable) && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setColor(colorStateList);
            return;
        }
        int iG = g(colorStateList);
        Paint paint = this.e;
        paint.setColor(iG);
        paint.setAlpha(63);
        invalidate();
    }

    public void setLabelBehavior(int i) {
        if (this.A != i) {
            this.A = i;
            requestLayout();
        }
    }

    public void setSeparationUnit(int i) {
        this.i0 = i;
        this.V = true;
        postInvalidate();
    }

    public void setStepSize(float f) {
        if (f >= 0.0f) {
            if (this.O != f) {
                this.O = f;
                this.V = true;
                postInvalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("The stepSize(" + f + ") must be 0, or a factor of the valueFrom(" + this.J + ")-valueTo(" + this.K + ") range");
    }

    public void setThumbElevation(float f) {
        this.e0.l(f);
    }

    public void setThumbRadius(int i) {
        if (i == this.D) {
            return;
        }
        this.D = i;
        qm qmVar = new qm();
        qm qmVar2 = new qm();
        qm qmVar3 = new qm();
        qm qmVar4 = new qm();
        float f = this.D;
        nv0 nv0VarP = oy.p(0);
        ii0.b(nv0VarP);
        ii0.b(nv0VarP);
        ii0.b(nv0VarP);
        ii0.b(nv0VarP);
        defpackage.e eVar = new defpackage.e(f);
        defpackage.e eVar2 = new defpackage.e(f);
        defpackage.e eVar3 = new defpackage.e(f);
        defpackage.e eVar4 = new defpackage.e(f);
        ki0 ki0Var = new ki0();
        ki0Var.a = nv0VarP;
        ki0Var.b = nv0VarP;
        ki0Var.c = nv0VarP;
        ki0Var.d = nv0VarP;
        ki0Var.e = eVar;
        ki0Var.f = eVar2;
        ki0Var.g = eVar3;
        ki0Var.h = eVar4;
        ki0Var.i = qmVar;
        ki0Var.j = qmVar2;
        ki0Var.k = qmVar3;
        ki0Var.l = qmVar4;
        u50 u50Var = this.e0;
        u50Var.setShapeAppearanceModel(ki0Var);
        int i2 = this.D * 2;
        u50Var.setBounds(0, 0, i2, i2);
        Drawable drawable = this.f0;
        if (drawable != null) {
            a(drawable);
        }
        Iterator it = this.g0.iterator();
        while (it.hasNext()) {
            a((Drawable) it.next());
        }
        w();
    }

    public void setThumbStrokeColor(ColorStateList colorStateList) {
        this.e0.r(colorStateList);
        postInvalidate();
    }

    public void setThumbStrokeWidth(float f) {
        this.e0.s(f);
        postInvalidate();
    }

    public void setTickActiveRadius(int i) {
        if (this.R != i) {
            this.R = i;
            this.g.setStrokeWidth(i * 2);
            w();
        }
    }

    public void setTickActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.a0)) {
            return;
        }
        this.a0 = colorStateList;
        this.g.setColor(g(colorStateList));
        invalidate();
    }

    public void setTickInactiveRadius(int i) {
        if (this.S != i) {
            this.S = i;
            this.f.setStrokeWidth(i * 2);
            w();
        }
    }

    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.b0)) {
            return;
        }
        this.b0 = colorStateList;
        this.f.setColor(g(colorStateList));
        invalidate();
    }

    public void setTrackActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.c0)) {
            return;
        }
        this.c0 = colorStateList;
        this.c.setColor(g(colorStateList));
        invalidate();
    }

    public void setTrackHeight(int i) {
        if (this.B != i) {
            this.B = i;
            this.b.setStrokeWidth(i);
            this.c.setStrokeWidth(this.B);
            w();
        }
    }

    public void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.d0)) {
            return;
        }
        this.d0 = colorStateList;
        this.b.setColor(g(colorStateList));
        invalidate();
    }

    public void setValues(Float... fArr) {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, fArr);
        r(arrayList);
    }

    public final void t() {
        double dRound;
        float f = this.h0;
        float f2 = this.O;
        if (f2 > 0.0f) {
            int i = (int) ((this.K - this.J) / f2);
            dRound = ((double) Math.round(f * i)) / ((double) i);
        } else {
            dRound = f;
        }
        if (j()) {
            dRound = 1.0d - dRound;
        }
        float f3 = this.K;
        float f4 = this.J;
        s(this.M, (float) ((dRound * ((double) (f3 - f4))) + ((double) f4)));
    }

    public final void u(int i, Rect rect) {
        int iN = this.C + ((int) (n(getValues().get(i).floatValue()) * this.T));
        int iB = b();
        int i2 = this.D;
        int i3 = this.x;
        if (i2 <= i3) {
            i2 = i3;
        }
        int i4 = i2 / 2;
        rect.set(iN - i4, iB - i4, iN + i4, iB + i4);
    }

    public final void v() {
        if (!(getBackground() instanceof RippleDrawable) || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int iN = (int) ((n(((Float) this.L.get(this.N)).floatValue()) * this.T) + this.C);
            int iB = b();
            int i = this.E;
            hl.f(background, iN - i, iB - i, iN + i, iB + i);
        }
    }

    public final void w() {
        boolean z;
        int iMax = Math.max(this.y, Math.max(this.B + getPaddingBottom() + getPaddingTop(), getPaddingBottom() + getPaddingTop() + (this.D * 2)));
        boolean z2 = false;
        if (iMax == this.z) {
            z = false;
        } else {
            this.z = iMax;
            z = true;
        }
        int iMax2 = Math.max(Math.max(Math.max(this.D - this.t, 0), Math.max((this.B - this.u) / 2, 0)), Math.max(Math.max(this.R - this.v, 0), Math.max(this.S - this.w, 0))) + this.s;
        if (this.C != iMax2) {
            this.C = iMax2;
            WeakHashMap weakHashMap = xr0.a;
            if (isLaidOut()) {
                this.T = Math.max(getWidth() - (this.C * 2), 0);
                k();
            }
            z2 = true;
        }
        if (z) {
            requestLayout();
        } else if (z2) {
            postInvalidate();
        }
    }

    public final void x() {
        if (this.V) {
            float f = this.J;
            float f2 = this.K;
            if (f >= f2) {
                throw new IllegalStateException("valueFrom(" + this.J + ") must be smaller than valueTo(" + this.K + ")");
            }
            if (f2 <= f) {
                throw new IllegalStateException("valueTo(" + this.K + ") must be greater than valueFrom(" + this.J + ")");
            }
            if (this.O > 0.0f && !h(f2 - f)) {
                throw new IllegalStateException("The stepSize(" + this.O + ") must be 0, or a factor of the valueFrom(" + this.J + ")-valueTo(" + this.K + ") range");
            }
            for (Float f3 : this.L) {
                if (f3.floatValue() < this.J || f3.floatValue() > this.K) {
                    throw new IllegalStateException("Slider value(" + f3 + ") must be greater or equal to valueFrom(" + this.J + "), and lower or equal to valueTo(" + this.K + ")");
                }
                if (this.O > 0.0f && !h(f3.floatValue() - this.J)) {
                    float f4 = this.J;
                    float f5 = this.O;
                    throw new IllegalStateException("Value(" + f3 + ") must be equal to valueFrom(" + f4 + ") plus a multiple of stepSize(" + f5 + ") when using stepSize(" + f5 + ")");
                }
            }
            float minSeparation = getMinSeparation();
            if (minSeparation < 0.0f) {
                throw new IllegalStateException("minSeparation(" + minSeparation + ") must be greater or equal to 0");
            }
            float f6 = this.O;
            if (f6 > 0.0f && minSeparation > 0.0f) {
                if (this.i0 != 1) {
                    throw new IllegalStateException("minSeparation(" + minSeparation + ") cannot be set as a dimension when using stepSize(" + this.O + ")");
                }
                if (minSeparation < f6 || !h(minSeparation)) {
                    float f7 = this.O;
                    throw new IllegalStateException("minSeparation(" + minSeparation + ") must be greater or equal and a multiple of stepSize(" + f7 + ") when using stepSize(" + f7 + ")");
                }
            }
            float f8 = this.O;
            if (f8 != 0.0f) {
                if (((int) f8) != f8) {
                    Log.w("BaseSlider", "Floating point value used for stepSize(" + f8 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
                }
                float f9 = this.J;
                if (((int) f9) != f9) {
                    Log.w("BaseSlider", "Floating point value used for valueFrom(" + f9 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
                }
                float f10 = this.K;
                if (((int) f10) != f10) {
                    Log.w("BaseSlider", "Floating point value used for valueTo(" + f10 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
                }
            }
            this.V = false;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BaseSlider(Context context, AttributeSet attributeSet, int i) {
        int i2 = j0;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.l = new ArrayList();
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.o = false;
        this.I = false;
        this.L = new ArrayList();
        this.M = -1;
        this.N = -1;
        this.O = 0.0f;
        this.Q = true;
        this.U = false;
        u50 u50Var = new u50();
        this.e0 = u50Var;
        this.g0 = Collections.EMPTY_LIST;
        this.i0 = 0;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.b = paint;
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        Paint.Cap cap = Paint.Cap.ROUND;
        paint.setStrokeCap(cap);
        Paint paint2 = new Paint();
        this.c = paint2;
        paint2.setStyle(style);
        paint2.setStrokeCap(cap);
        Paint paint3 = new Paint(1);
        this.d = paint3;
        Paint.Style style2 = Paint.Style.FILL;
        paint3.setStyle(style2);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint4 = new Paint(1);
        this.e = paint4;
        paint4.setStyle(style2);
        Paint paint5 = new Paint();
        this.f = paint5;
        paint5.setStyle(style);
        paint5.setStrokeCap(cap);
        Paint paint6 = new Paint();
        this.g = paint6;
        paint6.setStyle(style);
        paint6.setStrokeCap(cap);
        Resources resources = context2.getResources();
        this.y = resources.getDimensionPixelSize(R$dimen.mtrl_slider_widget_height);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.mtrl_slider_track_side_padding);
        this.s = dimensionPixelOffset;
        this.C = dimensionPixelOffset;
        this.t = resources.getDimensionPixelSize(R$dimen.mtrl_slider_thumb_radius);
        this.u = resources.getDimensionPixelSize(R$dimen.mtrl_slider_track_height);
        this.v = resources.getDimensionPixelSize(R$dimen.mtrl_slider_tick_radius);
        this.w = resources.getDimensionPixelSize(R$dimen.mtrl_slider_tick_radius);
        this.F = resources.getDimensionPixelSize(R$dimen.mtrl_slider_label_padding);
        int[] iArr = R$styleable.Slider;
        un0.a(context2, attributeSet, i, i2);
        un0.b(context2, attributeSet, iArr, i, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, i2);
        this.k = typedArrayObtainStyledAttributes.getResourceId(R$styleable.Slider_labelStyle, R$style.Widget_MaterialComponents_Tooltip);
        this.J = typedArrayObtainStyledAttributes.getFloat(R$styleable.Slider_android_valueFrom, 0.0f);
        this.K = typedArrayObtainStyledAttributes.getFloat(R$styleable.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.J));
        this.O = typedArrayObtainStyledAttributes.getFloat(R$styleable.Slider_android_stepSize, 0.0f);
        this.x = (int) Math.ceil(typedArrayObtainStyledAttributes.getDimension(R$styleable.Slider_minTouchTargetSize, (float) Math.ceil(rp0.t(getContext(), 48))));
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(R$styleable.Slider_trackColor);
        int i3 = zHasValue ? R$styleable.Slider_trackColor : R$styleable.Slider_trackColorInactive;
        int i4 = zHasValue ? R$styleable.Slider_trackColor : R$styleable.Slider_trackColorActive;
        ColorStateList colorStateListS = xi.s(context2, typedArrayObtainStyledAttributes, i3);
        setTrackInactiveTintList(colorStateListS == null ? rp0.w(context2, R$color.material_slider_inactive_track_color) : colorStateListS);
        ColorStateList colorStateListS2 = xi.s(context2, typedArrayObtainStyledAttributes, i4);
        setTrackActiveTintList(colorStateListS2 == null ? rp0.w(context2, R$color.material_slider_active_track_color) : colorStateListS2);
        u50Var.m(xi.s(context2, typedArrayObtainStyledAttributes, R$styleable.Slider_thumbColor));
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.Slider_thumbStrokeColor)) {
            setThumbStrokeColor(xi.s(context2, typedArrayObtainStyledAttributes, R$styleable.Slider_thumbStrokeColor));
        }
        setThumbStrokeWidth(typedArrayObtainStyledAttributes.getDimension(R$styleable.Slider_thumbStrokeWidth, 0.0f));
        ColorStateList colorStateListS3 = xi.s(context2, typedArrayObtainStyledAttributes, R$styleable.Slider_haloColor);
        setHaloTintList(colorStateListS3 == null ? rp0.w(context2, R$color.material_slider_halo_color) : colorStateListS3);
        this.Q = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Slider_tickVisible, true);
        boolean zHasValue2 = typedArrayObtainStyledAttributes.hasValue(R$styleable.Slider_tickColor);
        int i5 = zHasValue2 ? R$styleable.Slider_tickColor : R$styleable.Slider_tickColorInactive;
        int i6 = zHasValue2 ? R$styleable.Slider_tickColor : R$styleable.Slider_tickColorActive;
        ColorStateList colorStateListS4 = xi.s(context2, typedArrayObtainStyledAttributes, i5);
        setTickInactiveTintList(colorStateListS4 == null ? rp0.w(context2, R$color.material_slider_inactive_tick_marks_color) : colorStateListS4);
        ColorStateList colorStateListS5 = xi.s(context2, typedArrayObtainStyledAttributes, i6);
        setTickActiveTintList(colorStateListS5 == null ? rp0.w(context2, R$color.material_slider_active_tick_marks_color) : colorStateListS5);
        setThumbRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_thumbRadius, 0));
        setHaloRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_haloRadius, 0));
        setThumbElevation(typedArrayObtainStyledAttributes.getDimension(R$styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_trackHeight, 0));
        setTickActiveRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_tickRadiusActive, 0));
        setTickInactiveRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.Slider_tickRadiusInactive, 0));
        setLabelBehavior(typedArrayObtainStyledAttributes.getInt(R$styleable.Slider_labelBehavior, 0));
        if (!typedArrayObtainStyledAttributes.getBoolean(R$styleable.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        typedArrayObtainStyledAttributes.recycle();
        setFocusable(true);
        setClickable(true);
        u50Var.q(2);
        this.r = ViewConfiguration.get(context2).getScaledTouchSlop();
        d dVar = new d(this);
        this.h = dVar;
        xr0.p(this, dVar);
        this.i = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    public void setValues(List<Float> list) {
        r(new ArrayList(list));
    }

    public void setCustomThumbDrawablesForValues(Drawable... drawableArr) {
        this.f0 = null;
        this.g0 = new ArrayList();
        for (Drawable drawable : drawableArr) {
            List list = this.g0;
            Drawable drawableNewDrawable = drawable.mutate().getConstantState().newDrawable();
            a(drawableNewDrawable);
            list.add(drawableNewDrawable);
        }
        postInvalidate();
    }
}
