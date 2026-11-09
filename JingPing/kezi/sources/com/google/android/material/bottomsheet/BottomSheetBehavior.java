package com.google.android.material.bottomsheet;

import android.R;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.AbsSavedState;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.PathInterpolator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.c;
import androidx.core.R$id;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.a0;
import defpackage.a50;
import defpackage.b0;
import defpackage.cs0;
import defpackage.d1;
import defpackage.d9;
import defpackage.e9;
import defpackage.eu0;
import defpackage.f9;
import defpackage.fu0;
import defpackage.g9;
import defpackage.gg;
import defpackage.gu0;
import defpackage.hk;
import defpackage.i9;
import defpackage.j0;
import defpackage.ki0;
import defpackage.lr0;
import defpackage.mp;
import defpackage.r7;
import defpackage.rp0;
import defpackage.t3;
import defpackage.t5;
import defpackage.u50;
import defpackage.w2;
import defpackage.w40;
import defpackage.xi;
import defpackage.xr0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> implements w40 {
    public static final int h0 = R$style.Widget_Design_BottomSheet_Modal;
    public boolean A;
    public final g9 B;
    public final ValueAnimator C;
    public final int D;
    public int E;
    public int F;
    public final float G;
    public int H;
    public final float I;
    public boolean J;
    public boolean K;
    public final boolean L;
    public int M;
    public cs0 N;
    public boolean O;
    public int P;
    public boolean Q;
    public final float R;
    public int S;
    public int T;
    public int U;
    public WeakReference V;
    public WeakReference W;
    public WeakReference X;
    public final ArrayList Y;
    public VelocityTracker Z;
    public a50 a0;
    public final int b;
    public int b0;
    public boolean c;
    public int c0;
    public final float d;
    public boolean d0;
    public final int e;
    public HashMap e0;
    public int f;
    public final SparseIntArray f0;
    public boolean g;
    public final f9 g0;
    public int h;
    public final int i;
    public final u50 j;
    public final ColorStateList k;
    public final int l;
    public final int m;
    public int n;
    public final boolean o;
    public final boolean p;
    public final boolean q;
    public final boolean r;
    public final boolean s;
    public final boolean t;
    public final boolean u;
    public final boolean v;
    public int w;
    public int x;
    public final boolean y;
    public final ki0 z;

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i;
        super(context, attributeSet);
        int i2 = 0;
        this.b = 0;
        this.c = true;
        this.l = -1;
        this.m = -1;
        this.B = new g9(this);
        this.G = 0.5f;
        this.I = -1.0f;
        this.L = true;
        this.M = 4;
        this.R = 0.1f;
        this.Y = new ArrayList();
        this.c0 = -1;
        this.f0 = new SparseIntArray();
        this.g0 = new f9(this, i2);
        this.i = context.getResources().getDimensionPixelSize(R$dimen.mtrl_min_touch_target_size);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BottomSheetBehavior_Layout);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.BottomSheetBehavior_Layout_backgroundTint)) {
            this.k = xi.s(context, typedArrayObtainStyledAttributes, R$styleable.BottomSheetBehavior_Layout_backgroundTint);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.BottomSheetBehavior_Layout_shapeAppearance)) {
            this.z = ki0.c(context, attributeSet, R$attr.bottomSheetStyle, h0).a();
        }
        ki0 ki0Var = this.z;
        if (ki0Var != null) {
            u50 u50Var = new u50(ki0Var);
            this.j = u50Var;
            u50Var.k(context);
            ColorStateList colorStateList = this.k;
            if (colorStateList != null) {
                this.j.m(colorStateList);
            } else {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
                this.j.setTint(typedValue.data);
            }
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(x(), 1.0f);
        this.C = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.C.addUpdateListener(new d9(i2, this));
        this.I = typedArrayObtainStyledAttributes.getDimension(R$styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.BottomSheetBehavior_Layout_android_maxWidth)) {
            this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.BottomSheetBehavior_Layout_android_maxWidth, -1);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.BottomSheetBehavior_Layout_android_maxHeight)) {
            this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.BottomSheetBehavior_Layout_android_maxHeight, -1);
        }
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(R$styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (typedValuePeekValue == null || (i = typedValuePeekValue.data) != -1) {
            H(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        } else {
            H(i);
        }
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_hideable, false);
        if (this.J != z) {
            this.J = z;
            if (!z && this.M == 5) {
                I(4);
            }
            M();
        }
        this.o = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true);
        if (this.c != z2) {
            this.c = z2;
            if (this.V != null) {
                w();
            }
            J((this.c && this.M == 6) ? 3 : this.M);
            O(this.M, true);
            M();
        }
        this.K = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false);
        this.L = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_draggable, true);
        this.b = typedArrayObtainStyledAttributes.getInt(R$styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0);
        float f = typedArrayObtainStyledAttributes.getFloat(R$styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f);
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.G = f;
        if (this.V != null) {
            this.F = (int) ((1.0f - f) * this.U);
        }
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(R$styleable.BottomSheetBehavior_Layout_behavior_expandedOffset);
        if (typedValuePeekValue2 == null || typedValuePeekValue2.type != 16) {
            int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.BottomSheetBehavior_Layout_behavior_expandedOffset, 0);
            if (dimensionPixelOffset < 0) {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
            this.D = dimensionPixelOffset;
            O(this.M, true);
        } else {
            int i3 = typedValuePeekValue2.data;
            if (i3 < 0) {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
            this.D = i3;
            O(this.M, true);
        }
        this.e = typedArrayObtainStyledAttributes.getInt(R$styleable.BottomSheetBehavior_Layout_behavior_significantVelocityThreshold, 500);
        this.p = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
        this.q = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
        this.r = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
        this.s = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
        this.t = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_marginLeftSystemWindowInsets, false);
        this.u = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_marginRightSystemWindowInsets, false);
        this.v = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_marginTopSystemWindowInsets, false);
        this.y = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_shouldRemoveExpandedCorners, true);
        typedArrayObtainStyledAttributes.recycle();
        this.d = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public static View B(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        WeakHashMap weakHashMap = xr0.a;
        if (lr0.p(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View viewB = B(viewGroup.getChildAt(i));
            if (viewB != null) {
                return viewB;
            }
        }
        return null;
    }

    public static int C(int i, int i2, int i3, int i4) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, i2, i4);
        if (i3 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), 1073741824);
        }
        if (size != 0) {
            i3 = Math.min(size, i3);
        }
        return View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
    }

    public final void A(int i) {
        if (((View) this.V.get()) != null) {
            ArrayList arrayList = this.Y;
            if (arrayList.isEmpty()) {
                return;
            }
            int i2 = this.H;
            if (i <= i2 && i2 != D()) {
                D();
            }
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                ((i9) arrayList.get(i3)).getClass();
            }
        }
    }

    public final int D() {
        if (this.c) {
            return this.E;
        }
        return Math.max(this.D, this.s ? 0 : this.x);
    }

    public final int E(int i) {
        if (i == 3) {
            return D();
        }
        if (i == 4) {
            return this.H;
        }
        if (i == 5) {
            return this.U;
        }
        if (i == 6) {
            return this.F;
        }
        throw new IllegalArgumentException(hk.g(i, "Invalid state to get top offset: "));
    }

    public final boolean F() {
        WeakReference weakReference = this.V;
        if (weakReference != null && weakReference.get() != null) {
            int[] iArr = new int[2];
            ((View) this.V.get()).getLocationOnScreen(iArr);
            if (iArr[1] == 0) {
                return true;
            }
        }
        return false;
    }

    public final void G(BottomSheetDragHandleView bottomSheetDragHandleView) {
        WeakReference weakReference;
        if (bottomSheetDragHandleView != null || (weakReference = this.W) == null) {
            this.W = new WeakReference(bottomSheetDragHandleView);
            N(bottomSheetDragHandleView, 1);
        } else {
            z((View) weakReference.get(), 1);
            this.W = null;
        }
    }

    public final void H(int i) {
        if (i == -1) {
            if (this.g) {
                return;
            } else {
                this.g = true;
            }
        } else {
            if (!this.g && this.f == i) {
                return;
            }
            this.g = false;
            this.f = Math.max(0, i);
        }
        Q();
    }

    public final void I(int i) {
        if (i == 1 || i == 2) {
            throw new IllegalArgumentException(hk.k(new StringBuilder("STATE_"), i == 1 ? "DRAGGING" : "SETTLING", " should not be set externally."));
        }
        if (!this.J && i == 5) {
            Log.w("BottomSheetBehavior", "Cannot set state: " + i);
            return;
        }
        int i2 = (i == 6 && this.c && E(i) <= this.E) ? 3 : i;
        WeakReference weakReference = this.V;
        if (weakReference == null || weakReference.get() == null) {
            J(i);
            return;
        }
        View view = (View) this.V.get();
        t5 t5Var = new t5(this, view, i2);
        ViewParent parent = view.getParent();
        if (parent != null && parent.isLayoutRequested()) {
            WeakHashMap weakHashMap = xr0.a;
            if (view.isAttachedToWindow()) {
                view.post(t5Var);
                return;
            }
        }
        t5Var.run();
    }

    public final void J(int i) {
        if (this.M == i) {
            return;
        }
        this.M = i;
        if (i != 4 && i != 3 && i != 6) {
            boolean z = this.J;
        }
        WeakReference weakReference = this.V;
        if (weakReference == null || ((View) weakReference.get()) == null) {
            return;
        }
        int i2 = 0;
        if (i == 3) {
            P(true);
        } else if (i == 6 || i == 5 || i == 4) {
            P(false);
        }
        O(i, true);
        while (true) {
            ArrayList arrayList = this.Y;
            if (i2 >= arrayList.size()) {
                M();
                return;
            }
            i9 i9Var = (i9) arrayList.get(i2);
            i9Var.getClass();
            int i3 = BottomSheetDragHandleView.n;
            i9Var.a.d(i);
            i2++;
        }
    }

    public final boolean K(View view, float f) {
        if (this.K) {
            return true;
        }
        if (view.getTop() < this.H) {
            return false;
        }
        return Math.abs(((f * this.R) + ((float) view.getTop())) - ((float) this.H)) / ((float) y()) > 0.5f;
    }

    public final void L(View view, int i, boolean z) {
        int iE = E(i);
        cs0 cs0Var = this.N;
        if (cs0Var == null || (!z ? cs0Var.s(view, view.getLeft(), iE) : cs0Var.q(view.getLeft(), iE))) {
            J(i);
            return;
        }
        J(2);
        O(i, true);
        this.B.a(i);
    }

    public final void M() {
        WeakReference weakReference = this.V;
        if (weakReference != null) {
            N((View) weakReference.get(), 0);
        }
        WeakReference weakReference2 = this.W;
        if (weakReference2 != null) {
            N((View) weakReference2.get(), 1);
        }
    }

    public final void N(View view, int i) {
        int iA;
        int i2;
        if (view == null) {
            return;
        }
        z(view, i);
        if (!this.c && this.M != 6) {
            String string = view.getResources().getString(R$string.bottomsheet_action_expand_halfway);
            w2 w2Var = new w2(this, 6);
            ArrayList arrayListF = xr0.f(view);
            int i3 = 0;
            while (true) {
                if (i3 >= arrayListF.size()) {
                    int i4 = -1;
                    for (int i5 = 0; i5 < 32 && i4 == -1; i5++) {
                        int i6 = xr0.d[i5];
                        boolean z = true;
                        for (int i7 = 0; i7 < arrayListF.size(); i7++) {
                            z &= ((j0) arrayListF.get(i7)).a() != i6;
                        }
                        if (z) {
                            i4 = i6;
                        }
                    }
                    iA = i4;
                } else {
                    if (TextUtils.equals(string, ((AccessibilityNodeInfo.AccessibilityAction) ((j0) arrayListF.get(i3)).a).getLabel())) {
                        iA = ((j0) arrayListF.get(i3)).a();
                        break;
                    }
                    i3++;
                }
            }
            if (iA != -1) {
                i2 = iA;
                j0 j0Var = new j0(null, i2, string, w2Var, null);
                View.AccessibilityDelegate accessibilityDelegateD = xr0.d(view);
                b0 b0Var = accessibilityDelegateD == null ? null : accessibilityDelegateD instanceof a0 ? ((a0) accessibilityDelegateD).a : new b0(accessibilityDelegateD);
                if (b0Var == null) {
                    b0Var = new b0();
                }
                xr0.p(view, b0Var);
                xr0.m(view, j0Var.a());
                xr0.f(view).add(j0Var);
                xr0.i(view, 0);
            } else {
                i2 = iA;
            }
            this.f0.put(i, i2);
        }
        if (this.J && this.M != 5) {
            xr0.n(view, j0.l, null, new w2(this, 5));
        }
        int i8 = this.M;
        if (i8 == 3) {
            xr0.n(view, j0.k, null, new w2(this, this.c ? 4 : 6));
            return;
        }
        if (i8 == 4) {
            xr0.n(view, j0.j, null, new w2(this, this.c ? 3 : 6));
        } else {
            if (i8 != 6) {
                return;
            }
            xr0.n(view, j0.k, null, new w2(this, 4));
            xr0.n(view, j0.j, null, new w2(this, 3));
        }
    }

    public final void O(int i, boolean z) {
        u50 u50Var = this.j;
        ValueAnimator valueAnimator = this.C;
        if (i == 2) {
            return;
        }
        boolean z2 = this.M == 3 && (this.y || F());
        if (this.A == z2 || u50Var == null) {
            return;
        }
        this.A = z2;
        if (!z || valueAnimator == null) {
            if (valueAnimator != null && valueAnimator.isRunning()) {
                valueAnimator.cancel();
            }
            u50Var.n(this.A ? x() : 1.0f);
            return;
        }
        if (valueAnimator.isRunning()) {
            valueAnimator.reverse();
        } else {
            valueAnimator.setFloatValues(u50Var.b.i, z2 ? x() : 1.0f);
            valueAnimator.start();
        }
    }

    public final void P(boolean z) {
        WeakReference weakReference = this.V;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = ((View) weakReference.get()).getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                if (this.e0 != null) {
                    return;
                } else {
                    this.e0 = new HashMap(childCount);
                }
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (childAt != this.V.get() && z) {
                    this.e0.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                }
            }
            if (z) {
                return;
            }
            this.e0 = null;
        }
    }

    public final void Q() {
        View view;
        if (this.V != null) {
            w();
            if (this.M != 4 || (view = (View) this.V.get()) == null) {
                return;
            }
            view.requestLayout();
        }
    }

    @Override // defpackage.w40
    public final void a(r7 r7Var) {
        a50 a50Var = this.a0;
        if (a50Var == null) {
            return;
        }
        a50Var.f = r7Var;
    }

    @Override // defpackage.w40
    public final void b() {
        a50 a50Var = this.a0;
        if (a50Var == null) {
            return;
        }
        r7 r7Var = a50Var.f;
        a50Var.f = null;
        if (r7Var == null || Build.VERSION.SDK_INT < 34) {
            I(this.J ? 5 : 4);
            return;
        }
        boolean z = this.J;
        int i = a50Var.d;
        int i2 = a50Var.c;
        float f = r7Var.c;
        if (!z) {
            AnimatorSet animatorSetB = a50Var.b();
            animatorSetB.setDuration(t3.c(i2, f, i));
            animatorSetB.start();
            I(4);
            return;
        }
        d1 d1Var = new d1(2, this);
        View view = a50Var.b;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_Y, view.getScaleY() * view.getHeight());
        objectAnimatorOfFloat.setInterpolator(new mp(1));
        objectAnimatorOfFloat.setDuration(t3.c(i2, f, i));
        objectAnimatorOfFloat.addListener(new d1(8, a50Var));
        objectAnimatorOfFloat.addListener(d1Var);
        objectAnimatorOfFloat.start();
    }

    @Override // defpackage.w40
    public final void c(r7 r7Var) {
        a50 a50Var = this.a0;
        if (a50Var == null) {
            return;
        }
        if (a50Var.f == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() before updateBackProgress()");
        }
        r7 r7Var2 = a50Var.f;
        a50Var.f = r7Var;
        if (r7Var2 == null) {
            return;
        }
        a50Var.c(r7Var.c);
    }

    @Override // defpackage.w40
    public final void d() {
        a50 a50Var = this.a0;
        if (a50Var == null || a50Var.a() == null) {
            return;
        }
        AnimatorSet animatorSetB = a50Var.b();
        animatorSetB.setDuration(a50Var.e);
        animatorSetB.start();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void g(c cVar) {
        this.V = null;
        this.N = null;
        this.a0 = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void j() {
        this.V = null;
        this.N = null;
        this.a0 = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        int i;
        cs0 cs0Var;
        if (!view.isShown() || !this.L) {
            this.O = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.b0 = -1;
            this.c0 = -1;
            VelocityTracker velocityTracker = this.Z;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.Z = null;
            }
        }
        if (this.Z == null) {
            this.Z = VelocityTracker.obtain();
        }
        this.Z.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            this.c0 = (int) motionEvent.getY();
            if (this.M != 2) {
                WeakReference weakReference = this.X;
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && coordinatorLayout.t(view2, x, this.c0)) {
                    this.b0 = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.d0 = true;
                }
            }
            this.O = this.b0 == -1 && !coordinatorLayout.t(view, x, this.c0);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.d0 = false;
            this.b0 = -1;
            if (this.O) {
                this.O = false;
                return false;
            }
        }
        if (this.O || (cs0Var = this.N) == null || !cs0Var.r(motionEvent)) {
            WeakReference weakReference2 = this.X;
            View view3 = weakReference2 != null ? (View) weakReference2.get() : null;
            if (actionMasked != 2 || view3 == null || this.O || this.M == 1 || coordinatorLayout.t(view3, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.N == null || (i = this.c0) == -1 || Math.abs(i - motionEvent.getY()) <= this.N.b) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        int i2 = this.m;
        u50 u50Var = this.j;
        WeakHashMap weakHashMap = xr0.a;
        if (coordinatorLayout.getFitsSystemWindows() && !view.getFitsSystemWindows()) {
            view.setFitsSystemWindows(true);
        }
        int i3 = 0;
        if (this.V == null) {
            this.h = coordinatorLayout.getResources().getDimensionPixelSize(R$dimen.design_bottom_sheet_peek_height_min);
            int i4 = Build.VERSION.SDK_INT;
            boolean z = (i4 < 29 || this.o || this.g) ? false : true;
            if (this.p || this.q || this.r || this.t || this.u || this.v || z) {
                rp0.s(view, new e9(this, z));
            }
            gg ggVar = new gg(view);
            if (i4 >= 30) {
                view.setWindowInsetsAnimationCallback(new gu0(ggVar));
            } else {
                PathInterpolator pathInterpolator = fu0.e;
                Object tag = view.getTag(R$id.tag_on_apply_window_listener);
                View.OnApplyWindowInsetsListener eu0Var = new eu0(view, ggVar);
                view.setTag(R$id.tag_window_insets_animation_callback, eu0Var);
                if (tag == null) {
                    view.setOnApplyWindowInsetsListener(eu0Var);
                }
            }
            this.V = new WeakReference(view);
            this.a0 = new a50(view);
            if (u50Var != null) {
                view.setBackground(u50Var);
                float fI = this.I;
                if (fI == -1.0f) {
                    fI = lr0.i(view);
                }
                u50Var.l(fI);
            } else {
                ColorStateList colorStateList = this.k;
                if (colorStateList != null) {
                    lr0.q(view, colorStateList);
                }
            }
            M();
            if (view.getImportantForAccessibility() == 0) {
                view.setImportantForAccessibility(1);
            }
        }
        if (this.N == null) {
            this.N = new cs0(coordinatorLayout.getContext(), coordinatorLayout, this.g0);
        }
        int top = view.getTop();
        coordinatorLayout.v(view, i);
        this.T = coordinatorLayout.getWidth();
        this.U = coordinatorLayout.getHeight();
        int height = view.getHeight();
        this.S = height;
        int iMin = this.U;
        int i5 = iMin - height;
        int i6 = this.x;
        if (i5 < i6) {
            if (this.s) {
                if (i2 != -1) {
                    iMin = Math.min(iMin, i2);
                }
                this.S = iMin;
            } else {
                int iMin2 = iMin - i6;
                if (i2 != -1) {
                    iMin2 = Math.min(iMin2, i2);
                }
                this.S = iMin2;
            }
        }
        this.E = Math.max(0, this.U - this.S);
        this.F = (int) ((1.0f - this.G) * this.U);
        w();
        int i7 = this.M;
        if (i7 == 3) {
            view.offsetTopAndBottom(D());
        } else if (i7 == 6) {
            view.offsetTopAndBottom(this.F);
        } else if (this.J && i7 == 5) {
            view.offsetTopAndBottom(this.U);
        } else if (i7 == 4) {
            view.offsetTopAndBottom(this.H);
        } else if (i7 == 1 || i7 == 2) {
            view.offsetTopAndBottom(top - view.getTop());
        }
        O(this.M, false);
        this.X = new WeakReference(B(view));
        while (true) {
            ArrayList arrayList = this.Y;
            if (i3 >= arrayList.size()) {
                return true;
            }
            ((i9) arrayList.get(i3)).getClass();
            i3++;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean m(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(C(i, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, this.l, marginLayoutParams.width), C(i3, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, this.m, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean n(View view) {
        WeakReference weakReference = this.X;
        return (weakReference == null || view != weakReference.get() || this.M == 3) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void o(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr, int i3) {
        boolean z = this.L;
        if (i3 == 1) {
            return;
        }
        WeakReference weakReference = this.X;
        if (view2 != (weakReference != null ? (View) weakReference.get() : null)) {
            return;
        }
        int top = view.getTop();
        int i4 = top - i2;
        if (i2 > 0) {
            if (i4 < D()) {
                int iD = top - D();
                iArr[1] = iD;
                WeakHashMap weakHashMap = xr0.a;
                view.offsetTopAndBottom(-iD);
                J(3);
            } else {
                if (!z) {
                    return;
                }
                iArr[1] = i2;
                WeakHashMap weakHashMap2 = xr0.a;
                view.offsetTopAndBottom(-i2);
                J(1);
            }
        } else if (i2 < 0 && !view2.canScrollVertically(-1)) {
            int i5 = this.H;
            if (i4 > i5 && !this.J) {
                int i6 = top - i5;
                iArr[1] = i6;
                WeakHashMap weakHashMap3 = xr0.a;
                view.offsetTopAndBottom(-i6);
                J(4);
            } else {
                if (!z) {
                    return;
                }
                iArr[1] = i2;
                WeakHashMap weakHashMap4 = xr0.a;
                view.offsetTopAndBottom(-i2);
                J(1);
            }
        }
        A(view.getTop());
        this.P = i2;
        this.Q = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void r(View view, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        int i = this.b;
        if (i != 0) {
            if (i == -1 || (i & 1) == 1) {
                this.f = savedState.e;
            }
            if (i == -1 || (i & 2) == 2) {
                this.c = savedState.f;
            }
            if (i == -1 || (i & 4) == 4) {
                this.J = savedState.g;
            }
            if (i == -1 || (i & 8) == 8) {
                this.K = savedState.h;
            }
        }
        int i2 = savedState.d;
        if (i2 == 1 || i2 == 2) {
            this.M = 4;
        } else {
            this.M = i2;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final Parcelable s(View view) {
        AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
        return new SavedState(this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
        this.P = 0;
        this.Q = false;
        return (i & 2) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ae  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void u(androidx.coordinatorlayout.widget.CoordinatorLayout r3, android.view.View r4, android.view.View r5, int r6) {
        /*
            r2 = this;
            int r3 = r4.getTop()
            int r6 = r2.D()
            r0 = 3
            if (r3 != r6) goto Lf
            r2.J(r0)
            return
        Lf:
            java.lang.ref.WeakReference r3 = r2.X
            if (r3 == 0) goto Lb5
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto Lb5
            boolean r3 = r2.Q
            if (r3 != 0) goto L1f
            goto Lb5
        L1f:
            int r3 = r2.P
            r5 = 6
            if (r3 <= 0) goto L34
            boolean r3 = r2.c
            if (r3 == 0) goto L2a
            goto Laf
        L2a:
            int r3 = r4.getTop()
            int r6 = r2.F
            if (r3 <= r6) goto Laf
            goto Lae
        L34:
            boolean r3 = r2.J
            if (r3 == 0) goto L55
            android.view.VelocityTracker r3 = r2.Z
            if (r3 != 0) goto L3e
            r3 = 0
            goto L4d
        L3e:
            r6 = 1000(0x3e8, float:1.401E-42)
            float r1 = r2.d
            r3.computeCurrentVelocity(r6, r1)
            android.view.VelocityTracker r3 = r2.Z
            int r6 = r2.b0
            float r3 = r3.getYVelocity(r6)
        L4d:
            boolean r3 = r2.K(r4, r3)
            if (r3 == 0) goto L55
            r0 = 5
            goto Laf
        L55:
            int r3 = r2.P
            r6 = 4
            if (r3 != 0) goto L93
            int r3 = r4.getTop()
            boolean r1 = r2.c
            if (r1 == 0) goto L74
            int r5 = r2.E
            int r5 = r3 - r5
            int r5 = java.lang.Math.abs(r5)
            int r1 = r2.H
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r5 >= r3) goto L97
            goto Laf
        L74:
            int r1 = r2.F
            if (r3 >= r1) goto L83
            int r6 = r2.H
            int r6 = r3 - r6
            int r6 = java.lang.Math.abs(r6)
            if (r3 >= r6) goto Lae
            goto Laf
        L83:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.H
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L97
            goto Lae
        L93:
            boolean r3 = r2.c
            if (r3 == 0) goto L99
        L97:
            r0 = r6
            goto Laf
        L99:
            int r3 = r4.getTop()
            int r0 = r2.F
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.H
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L97
        Lae:
            r0 = r5
        Laf:
            r3 = 0
            r2.L(r4, r0, r3)
            r2.Q = r3
        Lb5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.u(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean v(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        int i = this.M;
        if (i == 1 && actionMasked == 0) {
            return true;
        }
        cs0 cs0Var = this.N;
        if (cs0Var != null && (this.L || i == 1)) {
            cs0Var.k(motionEvent);
        }
        if (actionMasked == 0) {
            this.b0 = -1;
            this.c0 = -1;
            VelocityTracker velocityTracker = this.Z;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.Z = null;
            }
        }
        if (this.Z == null) {
            this.Z = VelocityTracker.obtain();
        }
        this.Z.addMovement(motionEvent);
        if (this.N != null && ((this.L || this.M == 1) && actionMasked == 2 && !this.O)) {
            float fAbs = Math.abs(this.c0 - motionEvent.getY());
            cs0 cs0Var2 = this.N;
            if (fAbs > cs0Var2.b) {
                cs0Var2.b(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.O;
    }

    public final void w() {
        int iY = y();
        if (this.c) {
            this.H = Math.max(this.U - iY, this.E);
        } else {
            this.H = this.U - iY;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float x() {
        /*
            r5 = this;
            u50 r0 = r5.j
            r1 = 0
            if (r0 == 0) goto L6f
            java.lang.ref.WeakReference r0 = r5.V
            if (r0 == 0) goto L6f
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto L6f
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r0 < r2) goto L6f
            java.lang.ref.WeakReference r0 = r5.V
            java.lang.Object r0 = r0.get()
            android.view.View r0 = (android.view.View) r0
            boolean r2 = r5.F()
            if (r2 == 0) goto L6f
            android.view.WindowInsets r0 = r0.getRootWindowInsets()
            if (r0 == 0) goto L6f
            u50 r2 = r5.j
            float r2 = r2.i()
            android.view.RoundedCorner r3 = defpackage.c9.h(r0)
            if (r3 == 0) goto L44
            int r3 = defpackage.c9.c(r3)
            float r3 = (float) r3
            int r4 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r4 <= 0) goto L44
            int r4 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r4 <= 0) goto L44
            float r3 = r3 / r2
            goto L45
        L44:
            r3 = r1
        L45:
            u50 r2 = r5.j
            t50 r4 = r2.b
            ki0 r4 = r4.a
            kh r4 = r4.f
            android.graphics.RectF r2 = r2.g()
            float r2 = r4.a(r2)
            android.view.RoundedCorner r0 = defpackage.c9.q(r0)
            if (r0 == 0) goto L6a
            int r0 = defpackage.c9.c(r0)
            float r0 = (float) r0
            int r4 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r4 <= 0) goto L6a
            int r4 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r4 <= 0) goto L6a
            float r1 = r0 / r2
        L6a:
            float r0 = java.lang.Math.max(r3, r1)
            return r0
        L6f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.x():float");
    }

    public final int y() {
        int i;
        return this.g ? Math.min(Math.max(this.h, this.U - ((this.T * 9) / 16)), this.S) + this.w : (this.o || this.p || (i = this.n) <= 0) ? this.f + this.w : Math.max(this.f, i + this.i);
    }

    public final void z(View view, int i) {
        if (view == null) {
            return;
        }
        xr0.m(view, 524288);
        xr0.i(view, 0);
        xr0.m(view, 262144);
        xr0.i(view, 0);
        xr0.m(view, 1048576);
        xr0.i(view, 0);
        SparseIntArray sparseIntArray = this.f0;
        int i2 = sparseIntArray.get(i, -1);
        if (i2 != -1) {
            xr0.m(view, i2);
            xr0.i(view, 0);
            sparseIntArray.delete(i);
        }
    }

    public static class SavedState extends androidx.customview.view.AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final int d;
        public final int e;
        public final boolean f;
        public final boolean g;
        public final boolean h;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt() == 1;
            this.g = parcel.readInt() == 1;
            this.h = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f ? 1 : 0);
            parcel.writeInt(this.g ? 1 : 0);
            parcel.writeInt(this.h ? 1 : 0);
        }

        public SavedState(BottomSheetBehavior bottomSheetBehavior) {
            super(AbsSavedState.EMPTY_STATE);
            this.d = bottomSheetBehavior.M;
            this.e = bottomSheetBehavior.f;
            this.f = bottomSheetBehavior.c;
            this.g = bottomSheetBehavior.J;
            this.h = bottomSheetBehavior.K;
        }
    }

    public BottomSheetBehavior() {
        this.b = 0;
        this.c = true;
        this.l = -1;
        this.m = -1;
        this.B = new g9(this);
        this.G = 0.5f;
        this.I = -1.0f;
        this.L = true;
        this.M = 4;
        this.R = 0.1f;
        this.Y = new ArrayList();
        this.c0 = -1;
        this.f0 = new SparseIntArray();
        this.g0 = new f9(this, 0);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void p(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
    }
}
