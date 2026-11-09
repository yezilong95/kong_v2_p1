package androidx.swiperefreshlayout.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;
import androidx.swiperefreshlayout.R$styleable;
import defpackage.f51;
import defpackage.me;
import defpackage.mh0;
import defpackage.nh0;
import defpackage.oh0;
import defpackage.ph0;
import defpackage.qh0;
import defpackage.qi;
import defpackage.qy0;
import defpackage.ry0;
import defpackage.se;
import defpackage.sy0;
import defpackage.t41;
import defpackage.te;
import defpackage.ty0;
import defpackage.uy0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements ph0, oh0, mh0 {
    public static final int[] p0 = {R.attr.enabled};
    public View E;
    public boolean F;
    public final int G;
    public float H;
    public float I;
    public final qh0 J;
    public final nh0 K;
    public final int[] L;
    public final int[] M;
    public final int[] N;
    public boolean O;
    public final int P;
    public int Q;
    public float R;
    public float S;
    public boolean T;
    public int U;
    public final DecelerateInterpolator V;
    public final me W;
    public int a0;
    public int b0;
    public final int c0;
    public final int d0;
    public int e0;
    public final te f0;
    public ry0 g0;
    public ry0 h0;
    public sy0 i0;
    public sy0 j0;
    public int k0;
    public boolean l0;
    public final qy0 m0;
    public final ry0 n0;
    public final ry0 o0;

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    private void setColorViewAlpha(int i) {
        this.W.getBackground().setAlpha(i);
        this.f0.setAlpha(i);
    }

    @Override // defpackage.oh0
    public final void a(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // defpackage.oh0
    public final void b(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // defpackage.oh0
    public final void c(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    public final boolean d() {
        View view = this.E;
        return view instanceof ListView ? ((ListView) view).canScrollList(-1) : view.canScrollVertically(-1);
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.K.a(f, f2, z);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f, float f2) {
        return this.K.b(f, f2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.K.c(i, i2, 0, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.K.d(i, i2, i3, i4, iArr, 0, null);
    }

    public final void e() {
        if (this.E == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (!childAt.equals(this.W)) {
                    this.E = childAt;
                    return;
                }
            }
        }
    }

    public final void f(float f) {
        if (f > this.H) {
            m(true, true);
            return;
        }
        this.F = false;
        te teVar = this.f0;
        se seVar = teVar.E;
        seVar.e = 0.0f;
        seVar.f = 0.0f;
        teVar.invalidateSelf();
        qy0 qy0Var = new qy0(this, 1);
        this.b0 = this.Q;
        ry0 ry0Var = this.o0;
        ry0Var.reset();
        ry0Var.setDuration(200L);
        ry0Var.setInterpolator(this.V);
        me meVar = this.W;
        meVar.E = qy0Var;
        meVar.clearAnimation();
        this.W.startAnimation(ry0Var);
        te teVar2 = this.f0;
        se seVar2 = teVar2.E;
        if (seVar2.n) {
            seVar2.n = false;
        }
        teVar2.invalidateSelf();
    }

    @Override // defpackage.ph0
    public final void g(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (i5 != 0) {
            return;
        }
        int i6 = iArr[1];
        if (i5 == 0) {
            this.K.d(i, i2, i3, i4, this.M, i5, iArr);
        }
        int i7 = i4 - (iArr[1] - i6);
        if ((i7 == 0 ? i4 + this.M[1] : i7) >= 0 || d()) {
            return;
        }
        float fAbs = this.I + Math.abs(r14);
        this.I = fAbs;
        h(fAbs);
        iArr[1] = iArr[1] + i7;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        int i3 = this.a0;
        return i3 < 0 ? i2 : i2 == i + (-1) ? i3 : i2 >= i3 ? i2 + 1 : i2;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        qh0 qh0Var = this.J;
        return qh0Var.b | qh0Var.a;
    }

    public int getProgressCircleDiameter() {
        return this.k0;
    }

    public int getProgressViewEndOffset() {
        return this.d0;
    }

    public int getProgressViewStartOffset() {
        return this.c0;
    }

    public final void h(float f) {
        sy0 sy0Var;
        sy0 sy0Var2;
        te teVar = this.f0;
        se seVar = teVar.E;
        if (!seVar.n) {
            seVar.n = true;
        }
        teVar.invalidateSelf();
        float fMin = Math.min(1.0f, Math.abs(f / this.H));
        float fMax = (((float) Math.max(((double) fMin) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float fAbs = Math.abs(f) - this.H;
        int i = this.e0;
        if (i <= 0) {
            i = this.d0;
        }
        float f2 = i;
        double dMax = Math.max(0.0f, Math.min(fAbs, f2 * 2.0f) / f2) / 4.0f;
        float fPow = ((float) (dMax - Math.pow(dMax, 2.0d))) * 2.0f;
        int i2 = this.c0 + ((int) ((f2 * fMin) + (f2 * fPow * 2.0f)));
        if (this.W.getVisibility() != 0) {
            this.W.setVisibility(0);
        }
        this.W.setScaleX(1.0f);
        this.W.setScaleY(1.0f);
        if (f < this.H) {
            if (this.f0.E.t > 76 && ((sy0Var2 = this.i0) == null || !sy0Var2.hasStarted() || sy0Var2.hasEnded())) {
                sy0 sy0Var3 = new sy0(this, this.f0.E.t, 76);
                sy0Var3.setDuration(300L);
                me meVar = this.W;
                meVar.E = null;
                meVar.clearAnimation();
                this.W.startAnimation(sy0Var3);
                this.i0 = sy0Var3;
            }
        } else if (this.f0.E.t < 255 && ((sy0Var = this.j0) == null || !sy0Var.hasStarted() || sy0Var.hasEnded())) {
            sy0 sy0Var4 = new sy0(this, this.f0.E.t, 255);
            sy0Var4.setDuration(300L);
            me meVar2 = this.W;
            meVar2.E = null;
            meVar2.clearAnimation();
            this.W.startAnimation(sy0Var4);
            this.j0 = sy0Var4;
        }
        te teVar2 = this.f0;
        float fMin2 = Math.min(0.8f, fMax * 0.8f);
        se seVar2 = teVar2.E;
        seVar2.e = 0.0f;
        seVar2.f = fMin2;
        teVar2.invalidateSelf();
        te teVar3 = this.f0;
        float fMin3 = Math.min(1.0f, fMax);
        se seVar3 = teVar3.E;
        if (fMin3 != seVar3.p) {
            seVar3.p = fMin3;
        }
        teVar3.invalidateSelf();
        te teVar4 = this.f0;
        teVar4.E.g = ((fPow * 2.0f) + ((fMax * 0.4f) - 0.25f)) * 0.5f;
        teVar4.invalidateSelf();
        setTargetOffsetTopAndBottom(i2 - this.Q);
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return this.K.f(0);
    }

    @Override // defpackage.oh0
    public final void i(View view, int i, int i2, int i3, int i4, int i5) {
        g(view, i, i2, i3, i4, i5, this.N);
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.K.d;
    }

    @Override // defpackage.oh0
    public final boolean j(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            return onStartNestedScroll(view, view2, i);
        }
        return false;
    }

    public final void k(float f) {
        setTargetOffsetTopAndBottom((this.b0 + ((int) ((this.c0 - r0) * f))) - this.W.getTop());
    }

    public final void l() {
        this.W.clearAnimation();
        this.f0.stop();
        this.W.setVisibility(8);
        setColorViewAlpha(255);
        setTargetOffsetTopAndBottom(this.c0 - this.Q);
        this.Q = this.W.getTop();
    }

    public final void m(boolean z, boolean z2) {
        if (this.F != z) {
            e();
            this.F = z;
            qy0 qy0Var = this.m0;
            if (!z) {
                ry0 ry0Var = new ry0(this, 1);
                this.h0 = ry0Var;
                ry0Var.setDuration(150L);
                me meVar = this.W;
                meVar.E = qy0Var;
                meVar.clearAnimation();
                this.W.startAnimation(this.h0);
                return;
            }
            this.b0 = this.Q;
            ry0 ry0Var2 = this.n0;
            ry0Var2.reset();
            ry0Var2.setDuration(200L);
            ry0Var2.setInterpolator(this.V);
            if (qy0Var != null) {
                this.W.E = qy0Var;
            }
            this.W.clearAnimation();
            this.W.startAnimation(ry0Var2);
        }
    }

    public final void n(float f) {
        float f2 = this.S;
        float f3 = f - f2;
        float f4 = this.G;
        if (f3 <= f4 || this.T) {
            return;
        }
        this.R = f2 + f4;
        this.T = true;
        this.f0.setAlpha(76);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        l();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x005f  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            r5.e()
            int r0 = r6.getActionMasked()
            boolean r1 = r5.isEnabled()
            r2 = 0
            if (r1 == 0) goto L88
            boolean r1 = r5.d()
            if (r1 != 0) goto L88
            boolean r1 = r5.F
            if (r1 != 0) goto L88
            boolean r1 = r5.O
            if (r1 == 0) goto L1e
            goto L88
        L1e:
            if (r0 == 0) goto L64
            r1 = 1
            r3 = -1
            if (r0 == r1) goto L5f
            r4 = 2
            if (r0 == r4) goto L44
            r4 = 3
            if (r0 == r4) goto L5f
            r3 = 6
            if (r0 == r3) goto L2e
            goto L85
        L2e:
            int r0 = r6.getActionIndex()
            int r3 = r6.getPointerId(r0)
            int r4 = r5.U
            if (r3 != r4) goto L85
            if (r0 != 0) goto L3d
            r2 = r1
        L3d:
            int r6 = r6.getPointerId(r2)
            r5.U = r6
            goto L85
        L44:
            int r0 = r5.U
            if (r0 != r3) goto L50
            java.lang.String r6 = "SwipeRefreshLayout"
            java.lang.String r0 = "Got ACTION_MOVE event but don't have an active pointer id."
            android.util.Log.e(r6, r0)
            return r2
        L50:
            int r0 = r6.findPointerIndex(r0)
            if (r0 >= 0) goto L57
            goto L88
        L57:
            float r6 = r6.getY(r0)
            r5.n(r6)
            goto L85
        L5f:
            r5.T = r2
            r5.U = r3
            goto L85
        L64:
            me r0 = r5.W
            int r0 = r0.getTop()
            int r1 = r5.c0
            int r1 = r1 - r0
            r5.setTargetOffsetTopAndBottom(r1)
            int r0 = r6.getPointerId(r2)
            r5.U = r0
            r5.T = r2
            int r0 = r6.findPointerIndex(r0)
            if (r0 >= 0) goto L7f
            goto L88
        L7f:
            float r6 = r6.getY(r0)
            r5.S = r6
        L85:
            boolean r6 = r5.T
            return r6
        L88:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.swiperefreshlayout.widget.SwipeRefreshLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.E == null) {
            e();
        }
        View view = this.E;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.W.getMeasuredWidth();
        int measuredHeight2 = this.W.getMeasuredHeight();
        int i5 = measuredWidth / 2;
        int i6 = measuredWidth2 / 2;
        int i7 = this.Q;
        this.W.layout(i5 - i6, i7, i5 + i6, measuredHeight2 + i7);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.E == null) {
            e();
        }
        View view = this.E;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        this.W.measure(View.MeasureSpec.makeMeasureSpec(this.k0, 1073741824), View.MeasureSpec.makeMeasureSpec(this.k0, 1073741824));
        this.a0 = -1;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) == this.W) {
                this.a0 = i3;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        return this.K.a(f, f2, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        return this.K.b(f, f2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        if (i2 > 0) {
            float f = this.I;
            if (f > 0.0f) {
                float f2 = i2;
                if (f2 > f) {
                    iArr[1] = (int) f;
                    this.I = 0.0f;
                } else {
                    this.I = f - f2;
                    iArr[1] = i2;
                }
                h(this.I);
            }
        }
        int i3 = i - iArr[0];
        int i4 = i2 - iArr[1];
        int[] iArr2 = this.L;
        if (dispatchNestedPreScroll(i3, i4, iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        g(view, i, i2, i3, i4, 0, this.N);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        this.J.a = i;
        startNestedScroll(i & 2);
        this.I = 0.0f;
        this.O = true;
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setRefreshing(savedState.E);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.F);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        return (!isEnabled() || this.F || (i & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        this.J.a = 0;
        this.O = false;
        float f = this.I;
        if (f > 0.0f) {
            f(f);
            this.I = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (isEnabled() && !d() && !this.F && !this.O) {
            if (actionMasked == 0) {
                this.U = motionEvent.getPointerId(0);
                this.T = false;
                return true;
            }
            if (actionMasked == 1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.U);
                if (iFindPointerIndex < 0) {
                    Log.e("SwipeRefreshLayout", "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.T) {
                    float y = (motionEvent.getY(iFindPointerIndex) - this.R) * 0.5f;
                    this.T = false;
                    f(y);
                }
                this.U = -1;
                return false;
            }
            if (actionMasked == 2) {
                int iFindPointerIndex2 = motionEvent.findPointerIndex(this.U);
                if (iFindPointerIndex2 < 0) {
                    Log.e("SwipeRefreshLayout", "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y2 = motionEvent.getY(iFindPointerIndex2);
                n(y2);
                if (this.T) {
                    float f = (y2 - this.R) * 0.5f;
                    if (f > 0.0f) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        h(f);
                    }
                }
                return true;
            }
            if (actionMasked != 3) {
                if (actionMasked != 5) {
                    if (actionMasked == 6) {
                        int actionIndex = motionEvent.getActionIndex();
                        if (motionEvent.getPointerId(actionIndex) == this.U) {
                            this.U = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                            return true;
                        }
                    }
                    return true;
                }
                int actionIndex2 = motionEvent.getActionIndex();
                if (actionIndex2 < 0) {
                    Log.e("SwipeRefreshLayout", "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                    return false;
                }
                this.U = motionEvent.getPointerId(actionIndex2);
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent;
        View view = this.E;
        if (view != null) {
            WeakHashMap weakHashMap = f51.a;
            if (!t41.p(view)) {
                if (this.l0 || (parent = getParent()) == null) {
                    return;
                }
                parent.requestDisallowInterceptTouchEvent(z);
                return;
            }
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void setAnimationProgress(float f) {
        this.W.setScaleX(f);
        this.W.setScaleY(f);
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        e();
        te teVar = this.f0;
        se seVar = teVar.E;
        seVar.i = iArr;
        seVar.a(0);
        seVar.a(0);
        teVar.invalidateSelf();
    }

    public void setColorSchemeResources(int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = qi.a(context, iArr[i]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i) {
        this.H = i;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            return;
        }
        l();
    }

    @Deprecated
    public void setLegacyRequestDisallowInterceptTouchEventEnabled(boolean z) {
        this.l0 = z;
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        nh0 nh0Var = this.K;
        if (nh0Var.d) {
            WeakHashMap weakHashMap = f51.a;
            t41.z(nh0Var.c);
        }
        nh0Var.d = z;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i) {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeColor(int i) {
        this.W.setBackgroundColor(i);
    }

    public void setProgressBackgroundColorSchemeResource(int i) {
        setProgressBackgroundColorSchemeColor(qi.a(getContext(), i));
    }

    public void setRefreshing(boolean z) {
        if (!z || this.F == z) {
            m(z, false);
            return;
        }
        this.F = z;
        setTargetOffsetTopAndBottom((this.d0 + this.c0) - this.Q);
        qy0 qy0Var = this.m0;
        this.W.setVisibility(0);
        this.f0.setAlpha(255);
        ry0 ry0Var = new ry0(this, 0);
        this.g0 = ry0Var;
        ry0Var.setDuration(this.P);
        if (qy0Var != null) {
            this.W.E = qy0Var;
        }
        this.W.clearAnimation();
        this.W.startAnimation(this.g0);
    }

    public void setSize(int i) {
        if (i == 0 || i == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i == 0) {
                this.k0 = (int) (displayMetrics.density * 56.0f);
            } else {
                this.k0 = (int) (displayMetrics.density * 40.0f);
            }
            this.W.setImageDrawable(null);
            this.f0.c(i);
            this.W.setImageDrawable(this.f0);
        }
    }

    public void setSlingshotDistance(int i) {
        this.e0 = i;
    }

    public void setTargetOffsetTopAndBottom(int i) {
        me meVar = this.W;
        meVar.bringToFront();
        WeakHashMap weakHashMap = f51.a;
        meVar.offsetTopAndBottom(i);
        this.Q = meVar.getTop();
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i) {
        return this.K.g(i, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        this.K.h(0);
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final boolean E;

        public SavedState(Parcelable parcelable, boolean z) {
            super(parcelable);
            this.E = z;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.E ? (byte) 1 : (byte) 0);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.E = parcel.readByte() != 0;
        }
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F = false;
        this.H = -1.0f;
        this.L = new int[2];
        this.M = new int[2];
        this.N = new int[2];
        this.U = -1;
        this.a0 = -1;
        this.m0 = new qy0(this, 0);
        this.n0 = new ry0(this, 2);
        this.o0 = new ry0(this, 3);
        this.G = ViewConfiguration.get(context).getScaledTouchSlop();
        this.P = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.V = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.k0 = (int) (displayMetrics.density * 40.0f);
        me meVar = new me(getContext());
        float f = meVar.getContext().getResources().getDisplayMetrics().density;
        TypedArray typedArrayObtainStyledAttributes = meVar.getContext().obtainStyledAttributes(R$styleable.SwipeRefreshLayout);
        meVar.F = typedArrayObtainStyledAttributes.getColor(R$styleable.SwipeRefreshLayout_swipeRefreshLayoutProgressSpinnerBackgroundColor, -328966);
        typedArrayObtainStyledAttributes.recycle();
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        WeakHashMap weakHashMap = f51.a;
        t41.s(meVar, f * 4.0f);
        shapeDrawable.getPaint().setColor(meVar.F);
        meVar.setBackground(shapeDrawable);
        this.W = meVar;
        te teVar = new te(getContext());
        this.f0 = teVar;
        teVar.c(1);
        this.W.setImageDrawable(this.f0);
        this.W.setVisibility(8);
        addView(this.W);
        setChildrenDrawingOrderEnabled(true);
        int i = (int) (displayMetrics.density * 64.0f);
        this.d0 = i;
        this.H = i;
        this.J = new qh0();
        this.K = new nh0(this);
        setNestedScrollingEnabled(true);
        int i2 = -this.k0;
        this.Q = i2;
        this.c0 = i2;
        k(1.0f);
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, p0);
        setEnabled(typedArrayObtainStyledAttributes2.getBoolean(0, true));
        typedArrayObtainStyledAttributes2.recycle();
    }

    public void setOnChildScrollUpCallback(ty0 ty0Var) {
    }

    public void setOnRefreshListener(uy0 uy0Var) {
    }
}
