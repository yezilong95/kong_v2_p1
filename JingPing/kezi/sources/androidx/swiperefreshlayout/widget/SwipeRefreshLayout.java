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
import defpackage.bh;
import defpackage.cm0;
import defpackage.dm0;
import defpackage.em0;
import defpackage.fm0;
import defpackage.gm0;
import defpackage.j90;
import defpackage.k90;
import defpackage.l90;
import defpackage.lr0;
import defpackage.m90;
import defpackage.n90;
import defpackage.sc;
import defpackage.xr0;
import defpackage.yc;
import defpackage.zc;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements m90, l90, j90 {
    public static final int[] J = {R.attr.enabled};
    public dm0 A;
    public dm0 B;
    public em0 C;
    public em0 D;
    public int E;
    public boolean F;
    public final cm0 G;
    public final dm0 H;
    public final dm0 I;
    public View b;
    public boolean c;
    public final int d;
    public float e;
    public float f;
    public final n90 g;
    public final k90 h;
    public final int[] i;
    public final int[] j;
    public final int[] k;
    public boolean l;
    public final int m;
    public int n;
    public float o;
    public float p;
    public boolean q;
    public int r;
    public final DecelerateInterpolator s;
    public final sc t;
    public int u;
    public int v;
    public final int w;
    public final int x;
    public int y;
    public final zc z;

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    private void setColorViewAlpha(int i) {
        this.t.getBackground().setAlpha(i);
        this.z.setAlpha(i);
    }

    public final boolean a() {
        View view = this.b;
        return view instanceof ListView ? ((ListView) view).canScrollList(-1) : view.canScrollVertically(-1);
    }

    @Override // defpackage.l90
    public final void b(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // defpackage.l90
    public final void c(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    public final void d() {
        if (this.b == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (!childAt.equals(this.t)) {
                    this.b = childAt;
                    return;
                }
            }
        }
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.h.a(f, f2, z);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f, float f2) {
        return this.h.b(f, f2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.h.c(i, i2, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.h.d(i, i2, i3, i4, iArr, 0, null);
    }

    @Override // defpackage.l90
    public final void e(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    @Override // defpackage.m90
    public final void f(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (i5 != 0) {
            return;
        }
        int i6 = iArr[1];
        if (i5 == 0) {
            this.h.d(i, i2, i3, i4, this.j, i5, iArr);
        }
        int i7 = i4 - (iArr[1] - i6);
        if ((i7 == 0 ? i4 + this.j[1] : i7) >= 0 || a()) {
            return;
        }
        float fAbs = this.f + Math.abs(r14);
        this.f = fAbs;
        h(fAbs);
        iArr[1] = iArr[1] + i7;
    }

    public final void g(float f) {
        if (f > this.e) {
            m(true, true);
            return;
        }
        this.c = false;
        zc zcVar = this.z;
        yc ycVar = zcVar.b;
        ycVar.e = 0.0f;
        ycVar.f = 0.0f;
        zcVar.invalidateSelf();
        cm0 cm0Var = new cm0(this, 1);
        this.v = this.n;
        dm0 dm0Var = this.I;
        dm0Var.reset();
        dm0Var.setDuration(200L);
        dm0Var.setInterpolator(this.s);
        sc scVar = this.t;
        scVar.b = cm0Var;
        scVar.clearAnimation();
        this.t.startAnimation(dm0Var);
        zc zcVar2 = this.z;
        yc ycVar2 = zcVar2.b;
        if (ycVar2.n) {
            ycVar2.n = false;
        }
        zcVar2.invalidateSelf();
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        int i3 = this.u;
        return i3 < 0 ? i2 : i2 == i + (-1) ? i3 : i2 >= i3 ? i2 + 1 : i2;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        n90 n90Var = this.g;
        return n90Var.b | n90Var.a;
    }

    public int getProgressCircleDiameter() {
        return this.E;
    }

    public int getProgressViewEndOffset() {
        return this.x;
    }

    public int getProgressViewStartOffset() {
        return this.w;
    }

    public final void h(float f) {
        em0 em0Var;
        em0 em0Var2;
        zc zcVar = this.z;
        yc ycVar = zcVar.b;
        if (!ycVar.n) {
            ycVar.n = true;
        }
        zcVar.invalidateSelf();
        float fMin = Math.min(1.0f, Math.abs(f / this.e));
        float fMax = (((float) Math.max(((double) fMin) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float fAbs = Math.abs(f) - this.e;
        int i = this.y;
        if (i <= 0) {
            i = this.x;
        }
        float f2 = i;
        double dMax = Math.max(0.0f, Math.min(fAbs, f2 * 2.0f) / f2) / 4.0f;
        float fPow = ((float) (dMax - Math.pow(dMax, 2.0d))) * 2.0f;
        int i2 = this.w + ((int) ((f2 * fMin) + (f2 * fPow * 2.0f)));
        if (this.t.getVisibility() != 0) {
            this.t.setVisibility(0);
        }
        this.t.setScaleX(1.0f);
        this.t.setScaleY(1.0f);
        if (f < this.e) {
            if (this.z.b.t > 76 && ((em0Var2 = this.C) == null || !em0Var2.hasStarted() || em0Var2.hasEnded())) {
                em0 em0Var3 = new em0(this, this.z.b.t, 76);
                em0Var3.setDuration(300L);
                sc scVar = this.t;
                scVar.b = null;
                scVar.clearAnimation();
                this.t.startAnimation(em0Var3);
                this.C = em0Var3;
            }
        } else if (this.z.b.t < 255 && ((em0Var = this.D) == null || !em0Var.hasStarted() || em0Var.hasEnded())) {
            em0 em0Var4 = new em0(this, this.z.b.t, 255);
            em0Var4.setDuration(300L);
            sc scVar2 = this.t;
            scVar2.b = null;
            scVar2.clearAnimation();
            this.t.startAnimation(em0Var4);
            this.D = em0Var4;
        }
        zc zcVar2 = this.z;
        float fMin2 = Math.min(0.8f, fMax * 0.8f);
        yc ycVar2 = zcVar2.b;
        ycVar2.e = 0.0f;
        ycVar2.f = fMin2;
        zcVar2.invalidateSelf();
        zc zcVar3 = this.z;
        float fMin3 = Math.min(1.0f, fMax);
        yc ycVar3 = zcVar3.b;
        if (fMin3 != ycVar3.p) {
            ycVar3.p = fMin3;
        }
        zcVar3.invalidateSelf();
        zc zcVar4 = this.z;
        zcVar4.b.g = ((fPow * 2.0f) + ((fMax * 0.4f) - 0.25f)) * 0.5f;
        zcVar4.invalidateSelf();
        setTargetOffsetTopAndBottom(i2 - this.n);
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return this.h.f(0);
    }

    @Override // defpackage.l90
    public final void i(View view, int i, int i2, int i3, int i4, int i5) {
        f(view, i, i2, i3, i4, i5, this.k);
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.h.d;
    }

    @Override // defpackage.l90
    public final boolean j(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            return onStartNestedScroll(view, view2, i);
        }
        return false;
    }

    public final void k(float f) {
        setTargetOffsetTopAndBottom((this.v + ((int) ((this.w - r0) * f))) - this.t.getTop());
    }

    public final void l() {
        this.t.clearAnimation();
        this.z.stop();
        this.t.setVisibility(8);
        setColorViewAlpha(255);
        setTargetOffsetTopAndBottom(this.w - this.n);
        this.n = this.t.getTop();
    }

    public final void m(boolean z, boolean z2) {
        if (this.c != z) {
            d();
            this.c = z;
            cm0 cm0Var = this.G;
            if (!z) {
                dm0 dm0Var = new dm0(this, 1);
                this.B = dm0Var;
                dm0Var.setDuration(150L);
                sc scVar = this.t;
                scVar.b = cm0Var;
                scVar.clearAnimation();
                this.t.startAnimation(this.B);
                return;
            }
            this.v = this.n;
            dm0 dm0Var2 = this.H;
            dm0Var2.reset();
            dm0Var2.setDuration(200L);
            dm0Var2.setInterpolator(this.s);
            if (cm0Var != null) {
                this.t.b = cm0Var;
            }
            this.t.clearAnimation();
            this.t.startAnimation(dm0Var2);
        }
    }

    public final void n(float f) {
        float f2 = this.p;
        float f3 = f - f2;
        float f4 = this.d;
        if (f3 <= f4 || this.q) {
            return;
        }
        this.o = f2 + f4;
        this.q = true;
        this.z.setAlpha(76);
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            r5.d()
            int r0 = r6.getActionMasked()
            boolean r1 = r5.isEnabled()
            r2 = 0
            if (r1 == 0) goto L88
            boolean r1 = r5.a()
            if (r1 != 0) goto L88
            boolean r1 = r5.c
            if (r1 != 0) goto L88
            boolean r1 = r5.l
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
            int r4 = r5.r
            if (r3 != r4) goto L85
            if (r0 != 0) goto L3d
            r2 = r1
        L3d:
            int r6 = r6.getPointerId(r2)
            r5.r = r6
            goto L85
        L44:
            int r0 = r5.r
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
            r5.q = r2
            r5.r = r3
            goto L85
        L64:
            sc r0 = r5.t
            int r0 = r0.getTop()
            int r1 = r5.w
            int r1 = r1 - r0
            r5.setTargetOffsetTopAndBottom(r1)
            int r0 = r6.getPointerId(r2)
            r5.r = r0
            r5.q = r2
            int r0 = r6.findPointerIndex(r0)
            if (r0 >= 0) goto L7f
            goto L88
        L7f:
            float r6 = r6.getY(r0)
            r5.p = r6
        L85:
            boolean r6 = r5.q
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
        if (this.b == null) {
            d();
        }
        View view = this.b;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.t.getMeasuredWidth();
        int measuredHeight2 = this.t.getMeasuredHeight();
        int i5 = measuredWidth / 2;
        int i6 = measuredWidth2 / 2;
        int i7 = this.n;
        this.t.layout(i5 - i6, i7, i5 + i6, measuredHeight2 + i7);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.b == null) {
            d();
        }
        View view = this.b;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        this.t.measure(View.MeasureSpec.makeMeasureSpec(this.E, 1073741824), View.MeasureSpec.makeMeasureSpec(this.E, 1073741824));
        this.u = -1;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) == this.t) {
                this.u = i3;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        return this.h.a(f, f2, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        return this.h.b(f, f2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        if (i2 > 0) {
            float f = this.f;
            if (f > 0.0f) {
                float f2 = i2;
                if (f2 > f) {
                    iArr[1] = (int) f;
                    this.f = 0.0f;
                } else {
                    this.f = f - f2;
                    iArr[1] = i2;
                }
                h(this.f);
            }
        }
        int i3 = i - iArr[0];
        int i4 = i2 - iArr[1];
        int[] iArr2 = this.i;
        if (dispatchNestedPreScroll(i3, i4, iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        f(view, i, i2, i3, i4, 0, this.k);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i) {
        this.g.a = i;
        startNestedScroll(i & 2);
        this.f = 0.0f;
        this.l = true;
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setRefreshing(savedState.b);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.c);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i) {
        return (!isEnabled() || this.c || (i & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        this.g.a = 0;
        this.l = false;
        float f = this.f;
        if (f > 0.0f) {
            g(f);
            this.f = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (isEnabled() && !a() && !this.c && !this.l) {
            if (actionMasked == 0) {
                this.r = motionEvent.getPointerId(0);
                this.q = false;
                return true;
            }
            if (actionMasked == 1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.r);
                if (iFindPointerIndex < 0) {
                    Log.e("SwipeRefreshLayout", "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.q) {
                    float y = (motionEvent.getY(iFindPointerIndex) - this.o) * 0.5f;
                    this.q = false;
                    g(y);
                }
                this.r = -1;
                return false;
            }
            if (actionMasked == 2) {
                int iFindPointerIndex2 = motionEvent.findPointerIndex(this.r);
                if (iFindPointerIndex2 < 0) {
                    Log.e("SwipeRefreshLayout", "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y2 = motionEvent.getY(iFindPointerIndex2);
                n(y2);
                if (this.q) {
                    float f = (y2 - this.o) * 0.5f;
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
                        if (motionEvent.getPointerId(actionIndex) == this.r) {
                            this.r = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
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
                this.r = motionEvent.getPointerId(actionIndex2);
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent;
        View view = this.b;
        if (view != null) {
            WeakHashMap weakHashMap = xr0.a;
            if (!lr0.p(view)) {
                if (this.F || (parent = getParent()) == null) {
                    return;
                }
                parent.requestDisallowInterceptTouchEvent(z);
                return;
            }
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void setAnimationProgress(float f) {
        this.t.setScaleX(f);
        this.t.setScaleY(f);
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        d();
        zc zcVar = this.z;
        yc ycVar = zcVar.b;
        ycVar.i = iArr;
        ycVar.a(0);
        ycVar.a(0);
        zcVar.invalidateSelf();
    }

    public void setColorSchemeResources(int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = bh.a(context, iArr[i]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i) {
        this.e = i;
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
        this.F = z;
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        k90 k90Var = this.h;
        if (k90Var.d) {
            WeakHashMap weakHashMap = xr0.a;
            lr0.z(k90Var.c);
        }
        k90Var.d = z;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i) {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeColor(int i) {
        this.t.setBackgroundColor(i);
    }

    public void setProgressBackgroundColorSchemeResource(int i) {
        setProgressBackgroundColorSchemeColor(bh.a(getContext(), i));
    }

    public void setRefreshing(boolean z) {
        if (!z || this.c == z) {
            m(z, false);
            return;
        }
        this.c = z;
        setTargetOffsetTopAndBottom((this.x + this.w) - this.n);
        cm0 cm0Var = this.G;
        this.t.setVisibility(0);
        this.z.setAlpha(255);
        dm0 dm0Var = new dm0(this, 0);
        this.A = dm0Var;
        dm0Var.setDuration(this.m);
        if (cm0Var != null) {
            this.t.b = cm0Var;
        }
        this.t.clearAnimation();
        this.t.startAnimation(this.A);
    }

    public void setSize(int i) {
        if (i == 0 || i == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i == 0) {
                this.E = (int) (displayMetrics.density * 56.0f);
            } else {
                this.E = (int) (displayMetrics.density * 40.0f);
            }
            this.t.setImageDrawable(null);
            this.z.c(i);
            this.t.setImageDrawable(this.z);
        }
    }

    public void setSlingshotDistance(int i) {
        this.y = i;
    }

    public void setTargetOffsetTopAndBottom(int i) {
        sc scVar = this.t;
        scVar.bringToFront();
        WeakHashMap weakHashMap = xr0.a;
        scVar.offsetTopAndBottom(i);
        this.n = scVar.getTop();
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i) {
        return this.h.g(i, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        this.h.h(0);
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final boolean b;

        public SavedState(Parcelable parcelable, boolean z) {
            super(parcelable);
            this.b = z;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.b ? (byte) 1 : (byte) 0);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != 0;
        }
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = false;
        this.e = -1.0f;
        this.i = new int[2];
        this.j = new int[2];
        this.k = new int[2];
        this.r = -1;
        this.u = -1;
        this.G = new cm0(this, 0);
        this.H = new dm0(this, 2);
        this.I = new dm0(this, 3);
        this.d = ViewConfiguration.get(context).getScaledTouchSlop();
        this.m = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.s = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.E = (int) (displayMetrics.density * 40.0f);
        sc scVar = new sc(getContext());
        float f = scVar.getContext().getResources().getDisplayMetrics().density;
        TypedArray typedArrayObtainStyledAttributes = scVar.getContext().obtainStyledAttributes(R$styleable.SwipeRefreshLayout);
        scVar.c = typedArrayObtainStyledAttributes.getColor(R$styleable.SwipeRefreshLayout_swipeRefreshLayoutProgressSpinnerBackgroundColor, -328966);
        typedArrayObtainStyledAttributes.recycle();
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        WeakHashMap weakHashMap = xr0.a;
        lr0.s(scVar, f * 4.0f);
        shapeDrawable.getPaint().setColor(scVar.c);
        scVar.setBackground(shapeDrawable);
        this.t = scVar;
        zc zcVar = new zc(getContext());
        this.z = zcVar;
        zcVar.c(1);
        this.t.setImageDrawable(this.z);
        this.t.setVisibility(8);
        addView(this.t);
        setChildrenDrawingOrderEnabled(true);
        int i = (int) (displayMetrics.density * 64.0f);
        this.x = i;
        this.e = i;
        this.g = new n90();
        this.h = new k90(this);
        setNestedScrollingEnabled(true);
        int i2 = -this.E;
        this.n = i2;
        this.w = i2;
        k(1.0f);
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, J);
        setEnabled(typedArrayObtainStyledAttributes2.getBoolean(0, true));
        typedArrayObtainStyledAttributes2.recycle();
    }

    public void setOnChildScrollUpCallback(fm0 fm0Var) {
    }

    public void setOnRefreshListener(gm0 gm0Var) {
    }
}
