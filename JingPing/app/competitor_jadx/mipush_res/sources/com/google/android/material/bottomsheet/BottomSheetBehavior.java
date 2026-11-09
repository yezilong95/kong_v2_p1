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
import defpackage.a81;
import defpackage.b81;
import defpackage.bb;
import defpackage.cb;
import defpackage.db;
import defpackage.e2;
import defpackage.eb;
import defpackage.f51;
import defpackage.fa0;
import defpackage.gb;
import defpackage.j4;
import defpackage.k51;
import defpackage.l9;
import defpackage.la0;
import defpackage.ld0;
import defpackage.mc0;
import defpackage.qc0;
import defpackage.sh;
import defpackage.sv;
import defpackage.t41;
import defpackage.x1;
import defpackage.x6;
import defpackage.y1;
import defpackage.y4;
import defpackage.z2;
import defpackage.z71;
import defpackage.zn;
import defpackage.zu0;
import io.nats.client.BaseConsumeOptions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> implements mc0 {
    public static final int K0 = R$style.Widget_Design_BottomSheet_Modal;
    public WeakReference A0;
    public final ArrayList B0;
    public VelocityTracker C0;
    public qc0 D0;
    public final int E;
    public int E0;
    public boolean F;
    public int F0;
    public final float G;
    public boolean G0;
    public final int H;
    public HashMap H0;
    public int I;
    public final SparseIntArray I0;
    public boolean J;
    public final db J0;
    public int K;
    public final int L;
    public final ld0 M;
    public final ColorStateList N;
    public final int O;
    public final int P;
    public int Q;
    public final boolean R;
    public final boolean S;
    public final boolean T;
    public final boolean U;
    public final boolean V;
    public final boolean W;
    public final boolean X;
    public final boolean Y;
    public int Z;
    public int a0;
    public final boolean b0;
    public final zu0 c0;
    public boolean d0;
    public final eb e0;
    public final ValueAnimator f0;
    public final int g0;
    public int h0;
    public int i0;
    public final float j0;
    public int k0;
    public final float l0;
    public boolean m0;
    public boolean n0;
    public final boolean o0;
    public int p0;
    public k51 q0;
    public boolean r0;
    public int s0;
    public boolean t0;
    public final float u0;
    public int v0;
    public int w0;
    public int x0;
    public WeakReference y0;
    public WeakReference z0;

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i;
        super(context, attributeSet);
        int i2 = 0;
        this.E = 0;
        this.F = true;
        this.O = -1;
        this.P = -1;
        this.e0 = new eb(this);
        this.j0 = 0.5f;
        this.l0 = -1.0f;
        this.o0 = true;
        this.p0 = 4;
        this.u0 = 0.1f;
        this.B0 = new ArrayList();
        this.F0 = -1;
        this.I0 = new SparseIntArray();
        this.J0 = new db(this, i2);
        this.L = context.getResources().getDimensionPixelSize(R$dimen.mtrl_min_touch_target_size);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BottomSheetBehavior_Layout);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.BottomSheetBehavior_Layout_backgroundTint)) {
            this.N = la0.p(context, typedArrayObtainStyledAttributes, R$styleable.BottomSheetBehavior_Layout_backgroundTint);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.BottomSheetBehavior_Layout_shapeAppearance)) {
            this.c0 = zu0.c(context, attributeSet, R$attr.bottomSheetStyle, K0).a();
        }
        zu0 zu0Var = this.c0;
        if (zu0Var != null) {
            ld0 ld0Var = new ld0(zu0Var);
            this.M = ld0Var;
            ld0Var.k(context);
            ColorStateList colorStateList = this.N;
            if (colorStateList != null) {
                this.M.m(colorStateList);
            } else {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
                this.M.setTint(typedValue.data);
            }
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(x(), 1.0f);
        this.f0 = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.f0.addUpdateListener(new bb(i2, this));
        this.l0 = typedArrayObtainStyledAttributes.getDimension(R$styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.BottomSheetBehavior_Layout_android_maxWidth)) {
            this.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.BottomSheetBehavior_Layout_android_maxWidth, -1);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.BottomSheetBehavior_Layout_android_maxHeight)) {
            this.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.BottomSheetBehavior_Layout_android_maxHeight, -1);
        }
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(R$styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (typedValuePeekValue == null || (i = typedValuePeekValue.data) != -1) {
            H(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        } else {
            H(i);
        }
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_hideable, false);
        if (this.m0 != z) {
            this.m0 = z;
            if (!z && this.p0 == 5) {
                I(4);
            }
            M();
        }
        this.R = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true);
        if (this.F != z2) {
            this.F = z2;
            if (this.y0 != null) {
                w();
            }
            J((this.F && this.p0 == 6) ? 3 : this.p0);
            O(this.p0, true);
            M();
        }
        this.n0 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false);
        this.o0 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_draggable, true);
        this.E = typedArrayObtainStyledAttributes.getInt(R$styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0);
        float f = typedArrayObtainStyledAttributes.getFloat(R$styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f);
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.j0 = f;
        if (this.y0 != null) {
            this.i0 = (int) ((1.0f - f) * this.x0);
        }
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(R$styleable.BottomSheetBehavior_Layout_behavior_expandedOffset);
        if (typedValuePeekValue2 == null || typedValuePeekValue2.type != 16) {
            int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.BottomSheetBehavior_Layout_behavior_expandedOffset, 0);
            if (dimensionPixelOffset < 0) {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
            this.g0 = dimensionPixelOffset;
            O(this.p0, true);
        } else {
            int i3 = typedValuePeekValue2.data;
            if (i3 < 0) {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
            this.g0 = i3;
            O(this.p0, true);
        }
        this.H = typedArrayObtainStyledAttributes.getInt(R$styleable.BottomSheetBehavior_Layout_behavior_significantVelocityThreshold, BaseConsumeOptions.DEFAULT_MESSAGE_COUNT);
        this.S = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
        this.T = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
        this.U = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
        this.V = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
        this.W = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_marginLeftSystemWindowInsets, false);
        this.X = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_marginRightSystemWindowInsets, false);
        this.Y = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_marginTopSystemWindowInsets, false);
        this.b0 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_shouldRemoveExpandedCorners, true);
        typedArrayObtainStyledAttributes.recycle();
        this.G = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public static View B(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        WeakHashMap weakHashMap = f51.a;
        if (t41.p(view)) {
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
        if (((View) this.y0.get()) != null) {
            ArrayList arrayList = this.B0;
            if (arrayList.isEmpty()) {
                return;
            }
            int i2 = this.k0;
            if (i <= i2 && i2 != D()) {
                D();
            }
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                ((gb) arrayList.get(i3)).getClass();
            }
        }
    }

    public final int D() {
        if (this.F) {
            return this.h0;
        }
        return Math.max(this.g0, this.V ? 0 : this.a0);
    }

    public final int E(int i) {
        if (i == 3) {
            return D();
        }
        if (i == 4) {
            return this.k0;
        }
        if (i == 5) {
            return this.x0;
        }
        if (i == 6) {
            return this.i0;
        }
        throw new IllegalArgumentException(zn.j(i, "Invalid state to get top offset: "));
    }

    public final boolean F() {
        WeakReference weakReference = this.y0;
        if (weakReference != null && weakReference.get() != null) {
            int[] iArr = new int[2];
            ((View) this.y0.get()).getLocationOnScreen(iArr);
            if (iArr[1] == 0) {
                return true;
            }
        }
        return false;
    }

    public final void G(BottomSheetDragHandleView bottomSheetDragHandleView) {
        WeakReference weakReference;
        if (bottomSheetDragHandleView != null || (weakReference = this.z0) == null) {
            this.z0 = new WeakReference(bottomSheetDragHandleView);
            N(bottomSheetDragHandleView, 1);
        } else {
            z((View) weakReference.get(), 1);
            this.z0 = null;
        }
    }

    public final void H(int i) {
        if (i == -1) {
            if (this.J) {
                return;
            } else {
                this.J = true;
            }
        } else {
            if (!this.J && this.I == i) {
                return;
            }
            this.J = false;
            this.I = Math.max(0, i);
        }
        Q();
    }

    public final void I(int i) {
        if (i == 1 || i == 2) {
            throw new IllegalArgumentException(zn.o(new StringBuilder("STATE_"), i == 1 ? "DRAGGING" : "SETTLING", " should not be set externally."));
        }
        if (!this.m0 && i == 5) {
            Log.w("BottomSheetBehavior", "Cannot set state: " + i);
            return;
        }
        int i2 = (i == 6 && this.F && E(i) <= this.h0) ? 3 : i;
        WeakReference weakReference = this.y0;
        if (weakReference == null || weakReference.get() == null) {
            J(i);
            return;
        }
        View view = (View) this.y0.get();
        x6 x6Var = new x6(this, view, i2);
        ViewParent parent = view.getParent();
        if (parent != null && parent.isLayoutRequested()) {
            WeakHashMap weakHashMap = f51.a;
            if (view.isAttachedToWindow()) {
                view.post(x6Var);
                return;
            }
        }
        x6Var.run();
    }

    public final void J(int i) {
        if (this.p0 == i) {
            return;
        }
        this.p0 = i;
        if (i != 4 && i != 3 && i != 6) {
            boolean z = this.m0;
        }
        WeakReference weakReference = this.y0;
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
            ArrayList arrayList = this.B0;
            if (i2 >= arrayList.size()) {
                M();
                return;
            }
            gb gbVar = (gb) arrayList.get(i2);
            gbVar.getClass();
            int i3 = BottomSheetDragHandleView.Q;
            gbVar.a.d(i);
            i2++;
        }
    }

    public final boolean K(View view, float f) {
        if (this.n0) {
            return true;
        }
        if (view.getTop() < this.k0) {
            return false;
        }
        return Math.abs(((f * this.u0) + ((float) view.getTop())) - ((float) this.k0)) / ((float) y()) > 0.5f;
    }

    public final void L(View view, int i, boolean z) {
        int iE = E(i);
        k51 k51Var = this.q0;
        if (k51Var == null || (!z ? k51Var.s(view, view.getLeft(), iE) : k51Var.q(view.getLeft(), iE))) {
            J(i);
            return;
        }
        J(2);
        O(i, true);
        this.e0.a(i);
    }

    public final void M() {
        WeakReference weakReference = this.y0;
        if (weakReference != null) {
            N((View) weakReference.get(), 0);
        }
        WeakReference weakReference2 = this.z0;
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
        if (!this.F && this.p0 != 6) {
            String string = view.getResources().getString(R$string.bottomsheet_action_expand_halfway);
            j4 j4Var = new j4(this, 6);
            ArrayList arrayListF = f51.f(view);
            int i3 = 0;
            while (true) {
                if (i3 >= arrayListF.size()) {
                    int i4 = -1;
                    for (int i5 = 0; i5 < 32 && i4 == -1; i5++) {
                        int i6 = f51.d[i5];
                        boolean z = true;
                        for (int i7 = 0; i7 < arrayListF.size(); i7++) {
                            z &= ((e2) arrayListF.get(i7)).a() != i6;
                        }
                        if (z) {
                            i4 = i6;
                        }
                    }
                    iA = i4;
                } else {
                    if (TextUtils.equals(string, ((AccessibilityNodeInfo.AccessibilityAction) ((e2) arrayListF.get(i3)).a).getLabel())) {
                        iA = ((e2) arrayListF.get(i3)).a();
                        break;
                    }
                    i3++;
                }
            }
            if (iA != -1) {
                i2 = iA;
                e2 e2Var = new e2(null, i2, string, j4Var, null);
                View.AccessibilityDelegate accessibilityDelegateD = f51.d(view);
                y1 y1Var = accessibilityDelegateD == null ? null : accessibilityDelegateD instanceof x1 ? ((x1) accessibilityDelegateD).a : new y1(accessibilityDelegateD);
                if (y1Var == null) {
                    y1Var = new y1();
                }
                f51.o(view, y1Var);
                f51.l(view, e2Var.a());
                f51.f(view).add(e2Var);
                f51.i(view, 0);
            } else {
                i2 = iA;
            }
            this.I0.put(i, i2);
        }
        if (this.m0 && this.p0 != 5) {
            f51.m(view, e2.l, null, new j4(this, 5));
        }
        int i8 = this.p0;
        if (i8 == 3) {
            f51.m(view, e2.k, null, new j4(this, this.F ? 4 : 6));
            return;
        }
        if (i8 == 4) {
            f51.m(view, e2.j, null, new j4(this, this.F ? 3 : 6));
        } else {
            if (i8 != 6) {
                return;
            }
            f51.m(view, e2.k, null, new j4(this, 4));
            f51.m(view, e2.j, null, new j4(this, 3));
        }
    }

    public final void O(int i, boolean z) {
        ld0 ld0Var = this.M;
        ValueAnimator valueAnimator = this.f0;
        if (i == 2) {
            return;
        }
        boolean z2 = this.p0 == 3 && (this.b0 || F());
        if (this.d0 == z2 || ld0Var == null) {
            return;
        }
        this.d0 = z2;
        if (!z || valueAnimator == null) {
            if (valueAnimator != null && valueAnimator.isRunning()) {
                valueAnimator.cancel();
            }
            ld0Var.n(this.d0 ? x() : 1.0f);
            return;
        }
        if (valueAnimator.isRunning()) {
            valueAnimator.reverse();
        } else {
            valueAnimator.setFloatValues(ld0Var.E.i, z2 ? x() : 1.0f);
            valueAnimator.start();
        }
    }

    public final void P(boolean z) {
        WeakReference weakReference = this.y0;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = ((View) weakReference.get()).getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                if (this.H0 != null) {
                    return;
                } else {
                    this.H0 = new HashMap(childCount);
                }
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (childAt != this.y0.get() && z) {
                    this.H0.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                }
            }
            if (z) {
                return;
            }
            this.H0 = null;
        }
    }

    public final void Q() {
        View view;
        if (this.y0 != null) {
            w();
            if (this.p0 != 4 || (view = (View) this.y0.get()) == null) {
                return;
            }
            view.requestLayout();
        }
    }

    @Override // defpackage.mc0
    public final void a() {
        qc0 qc0Var = this.D0;
        if (qc0Var == null) {
            return;
        }
        l9 l9Var = qc0Var.f;
        qc0Var.f = null;
        if (l9Var == null || Build.VERSION.SDK_INT < 34) {
            I(this.m0 ? 5 : 4);
            return;
        }
        boolean z = this.m0;
        int i = qc0Var.d;
        int i2 = qc0Var.c;
        float f = l9Var.c;
        if (!z) {
            AnimatorSet animatorSetB = qc0Var.b();
            animatorSetB.setDuration(y4.c(i2, f, i));
            animatorSetB.start();
            I(4);
            return;
        }
        z2 z2Var = new z2(2, this);
        View view = qc0Var.b;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_Y, view.getScaleY() * view.getHeight());
        objectAnimatorOfFloat.setInterpolator(new sv(1));
        objectAnimatorOfFloat.setDuration(y4.c(i2, f, i));
        objectAnimatorOfFloat.addListener(new z2(8, qc0Var));
        objectAnimatorOfFloat.addListener(z2Var);
        objectAnimatorOfFloat.start();
    }

    @Override // defpackage.mc0
    public final void b(l9 l9Var) {
        qc0 qc0Var = this.D0;
        if (qc0Var == null) {
            return;
        }
        if (qc0Var.f == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() before updateBackProgress()");
        }
        l9 l9Var2 = qc0Var.f;
        qc0Var.f = l9Var;
        if (l9Var2 == null) {
            return;
        }
        qc0Var.c(l9Var.c);
    }

    @Override // defpackage.mc0
    public final void c(l9 l9Var) {
        qc0 qc0Var = this.D0;
        if (qc0Var == null) {
            return;
        }
        qc0Var.f = l9Var;
    }

    @Override // defpackage.mc0
    public final void d() {
        qc0 qc0Var = this.D0;
        if (qc0Var == null || qc0Var.a() == null) {
            return;
        }
        AnimatorSet animatorSetB = qc0Var.b();
        animatorSetB.setDuration(qc0Var.e);
        animatorSetB.start();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void g(c cVar) {
        this.y0 = null;
        this.q0 = null;
        this.D0 = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void j() {
        this.y0 = null;
        this.q0 = null;
        this.D0 = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        int i;
        k51 k51Var;
        if (!view.isShown() || !this.o0) {
            this.r0 = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.E0 = -1;
            this.F0 = -1;
            VelocityTracker velocityTracker = this.C0;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.C0 = null;
            }
        }
        if (this.C0 == null) {
            this.C0 = VelocityTracker.obtain();
        }
        this.C0.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            this.F0 = (int) motionEvent.getY();
            if (this.p0 != 2) {
                WeakReference weakReference = this.A0;
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && coordinatorLayout.t(view2, x, this.F0)) {
                    this.E0 = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.G0 = true;
                }
            }
            this.r0 = this.E0 == -1 && !coordinatorLayout.t(view, x, this.F0);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.G0 = false;
            this.E0 = -1;
            if (this.r0) {
                this.r0 = false;
                return false;
            }
        }
        if (this.r0 || (k51Var = this.q0) == null || !k51Var.r(motionEvent)) {
            WeakReference weakReference2 = this.A0;
            View view3 = weakReference2 != null ? (View) weakReference2.get() : null;
            if (actionMasked != 2 || view3 == null || this.r0 || this.p0 == 1 || coordinatorLayout.t(view3, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.q0 == null || (i = this.F0) == -1 || Math.abs(i - motionEvent.getY()) <= this.q0.b) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        int i2 = this.P;
        ld0 ld0Var = this.M;
        WeakHashMap weakHashMap = f51.a;
        if (coordinatorLayout.getFitsSystemWindows() && !view.getFitsSystemWindows()) {
            view.setFitsSystemWindows(true);
        }
        int i3 = 0;
        if (this.y0 == null) {
            this.K = coordinatorLayout.getResources().getDimensionPixelSize(R$dimen.design_bottom_sheet_peek_height_min);
            int i4 = Build.VERSION.SDK_INT;
            boolean z = (i4 < 29 || this.R || this.J) ? false : true;
            if (this.S || this.T || this.U || this.W || this.X || this.Y || z) {
                fa0.B(view, new cb(this, z));
            }
            sh shVar = new sh(view);
            if (i4 >= 30) {
                view.setWindowInsetsAnimationCallback(new b81(shVar));
            } else {
                PathInterpolator pathInterpolator = a81.e;
                Object tag = view.getTag(R$id.tag_on_apply_window_listener);
                View.OnApplyWindowInsetsListener z71Var = new z71(view, shVar);
                view.setTag(R$id.tag_window_insets_animation_callback, z71Var);
                if (tag == null) {
                    view.setOnApplyWindowInsetsListener(z71Var);
                }
            }
            this.y0 = new WeakReference(view);
            this.D0 = new qc0(view);
            if (ld0Var != null) {
                view.setBackground(ld0Var);
                float fI = this.l0;
                if (fI == -1.0f) {
                    fI = t41.i(view);
                }
                ld0Var.l(fI);
            } else {
                ColorStateList colorStateList = this.N;
                if (colorStateList != null) {
                    t41.q(view, colorStateList);
                }
            }
            M();
            if (view.getImportantForAccessibility() == 0) {
                view.setImportantForAccessibility(1);
            }
        }
        if (this.q0 == null) {
            this.q0 = new k51(coordinatorLayout.getContext(), coordinatorLayout, this.J0);
        }
        int top = view.getTop();
        coordinatorLayout.v(view, i);
        this.w0 = coordinatorLayout.getWidth();
        this.x0 = coordinatorLayout.getHeight();
        int height = view.getHeight();
        this.v0 = height;
        int iMin = this.x0;
        int i5 = iMin - height;
        int i6 = this.a0;
        if (i5 < i6) {
            if (this.V) {
                if (i2 != -1) {
                    iMin = Math.min(iMin, i2);
                }
                this.v0 = iMin;
            } else {
                int iMin2 = iMin - i6;
                if (i2 != -1) {
                    iMin2 = Math.min(iMin2, i2);
                }
                this.v0 = iMin2;
            }
        }
        this.h0 = Math.max(0, this.x0 - this.v0);
        this.i0 = (int) ((1.0f - this.j0) * this.x0);
        w();
        int i7 = this.p0;
        if (i7 == 3) {
            view.offsetTopAndBottom(D());
        } else if (i7 == 6) {
            view.offsetTopAndBottom(this.i0);
        } else if (this.m0 && i7 == 5) {
            view.offsetTopAndBottom(this.x0);
        } else if (i7 == 4) {
            view.offsetTopAndBottom(this.k0);
        } else if (i7 == 1 || i7 == 2) {
            view.offsetTopAndBottom(top - view.getTop());
        }
        O(this.p0, false);
        this.A0 = new WeakReference(B(view));
        while (true) {
            ArrayList arrayList = this.B0;
            if (i3 >= arrayList.size()) {
                return true;
            }
            ((gb) arrayList.get(i3)).getClass();
            i3++;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean m(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(C(i, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, this.O, marginLayoutParams.width), C(i3, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, this.P, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean n(View view) {
        WeakReference weakReference = this.A0;
        return (weakReference == null || view != weakReference.get() || this.p0 == 3) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void o(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr, int i3) {
        boolean z = this.o0;
        if (i3 == 1) {
            return;
        }
        WeakReference weakReference = this.A0;
        if (view2 != (weakReference != null ? (View) weakReference.get() : null)) {
            return;
        }
        int top = view.getTop();
        int i4 = top - i2;
        if (i2 > 0) {
            if (i4 < D()) {
                int iD = top - D();
                iArr[1] = iD;
                WeakHashMap weakHashMap = f51.a;
                view.offsetTopAndBottom(-iD);
                J(3);
            } else {
                if (!z) {
                    return;
                }
                iArr[1] = i2;
                WeakHashMap weakHashMap2 = f51.a;
                view.offsetTopAndBottom(-i2);
                J(1);
            }
        } else if (i2 < 0 && !view2.canScrollVertically(-1)) {
            int i5 = this.k0;
            if (i4 > i5 && !this.m0) {
                int i6 = top - i5;
                iArr[1] = i6;
                WeakHashMap weakHashMap3 = f51.a;
                view.offsetTopAndBottom(-i6);
                J(4);
            } else {
                if (!z) {
                    return;
                }
                iArr[1] = i2;
                WeakHashMap weakHashMap4 = f51.a;
                view.offsetTopAndBottom(-i2);
                J(1);
            }
        }
        A(view.getTop());
        this.s0 = i2;
        this.t0 = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void r(View view, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        int i = this.E;
        if (i != 0) {
            if (i == -1 || (i & 1) == 1) {
                this.I = savedState.H;
            }
            if (i == -1 || (i & 2) == 2) {
                this.F = savedState.I;
            }
            if (i == -1 || (i & 4) == 4) {
                this.m0 = savedState.J;
            }
            if (i == -1 || (i & 8) == 8) {
                this.n0 = savedState.K;
            }
        }
        int i2 = savedState.G;
        if (i2 == 1 || i2 == 2) {
            this.p0 = 4;
        } else {
            this.p0 = i2;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final Parcelable s(View view) {
        AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
        return new SavedState(this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
        this.s0 = 0;
        this.t0 = false;
        return (i & 2) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ae  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
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
            java.lang.ref.WeakReference r3 = r2.A0
            if (r3 == 0) goto Lb5
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto Lb5
            boolean r3 = r2.t0
            if (r3 != 0) goto L1f
            goto Lb5
        L1f:
            int r3 = r2.s0
            r5 = 6
            if (r3 <= 0) goto L34
            boolean r3 = r2.F
            if (r3 == 0) goto L2a
            goto Laf
        L2a:
            int r3 = r4.getTop()
            int r6 = r2.i0
            if (r3 <= r6) goto Laf
            goto Lae
        L34:
            boolean r3 = r2.m0
            if (r3 == 0) goto L55
            android.view.VelocityTracker r3 = r2.C0
            if (r3 != 0) goto L3e
            r3 = 0
            goto L4d
        L3e:
            r6 = 1000(0x3e8, float:1.401E-42)
            float r1 = r2.G
            r3.computeCurrentVelocity(r6, r1)
            android.view.VelocityTracker r3 = r2.C0
            int r6 = r2.E0
            float r3 = r3.getYVelocity(r6)
        L4d:
            boolean r3 = r2.K(r4, r3)
            if (r3 == 0) goto L55
            r0 = 5
            goto Laf
        L55:
            int r3 = r2.s0
            r6 = 4
            if (r3 != 0) goto L93
            int r3 = r4.getTop()
            boolean r1 = r2.F
            if (r1 == 0) goto L74
            int r5 = r2.h0
            int r5 = r3 - r5
            int r5 = java.lang.Math.abs(r5)
            int r1 = r2.k0
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r5 >= r3) goto L97
            goto Laf
        L74:
            int r1 = r2.i0
            if (r3 >= r1) goto L83
            int r6 = r2.k0
            int r6 = r3 - r6
            int r6 = java.lang.Math.abs(r6)
            if (r3 >= r6) goto Lae
            goto Laf
        L83:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.k0
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L97
            goto Lae
        L93:
            boolean r3 = r2.F
            if (r3 == 0) goto L99
        L97:
            r0 = r6
            goto Laf
        L99:
            int r3 = r4.getTop()
            int r0 = r2.i0
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.k0
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L97
        Lae:
            r0 = r5
        Laf:
            r3 = 0
            r2.L(r4, r0, r3)
            r2.t0 = r3
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
        int i = this.p0;
        if (i == 1 && actionMasked == 0) {
            return true;
        }
        k51 k51Var = this.q0;
        if (k51Var != null && (this.o0 || i == 1)) {
            k51Var.k(motionEvent);
        }
        if (actionMasked == 0) {
            this.E0 = -1;
            this.F0 = -1;
            VelocityTracker velocityTracker = this.C0;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.C0 = null;
            }
        }
        if (this.C0 == null) {
            this.C0 = VelocityTracker.obtain();
        }
        this.C0.addMovement(motionEvent);
        if (this.q0 != null && ((this.o0 || this.p0 == 1) && actionMasked == 2 && !this.r0)) {
            float fAbs = Math.abs(this.F0 - motionEvent.getY());
            k51 k51Var2 = this.q0;
            if (fAbs > k51Var2.b) {
                k51Var2.b(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.r0;
    }

    public final void w() {
        int iY = y();
        if (this.F) {
            this.k0 = Math.max(this.x0 - iY, this.h0);
        } else {
            this.k0 = this.x0 - iY;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float x() {
        /*
            r5 = this;
            ld0 r0 = r5.M
            r1 = 0
            if (r0 == 0) goto L6f
            java.lang.ref.WeakReference r0 = r5.y0
            if (r0 == 0) goto L6f
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto L6f
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r0 < r2) goto L6f
            java.lang.ref.WeakReference r0 = r5.y0
            java.lang.Object r0 = r0.get()
            android.view.View r0 = (android.view.View) r0
            boolean r2 = r5.F()
            if (r2 == 0) goto L6f
            android.view.WindowInsets r0 = r0.getRootWindowInsets()
            if (r0 == 0) goto L6f
            ld0 r2 = r5.M
            float r2 = r2.i()
            android.view.RoundedCorner r3 = defpackage.ab.h(r0)
            if (r3 == 0) goto L44
            int r3 = defpackage.ab.c(r3)
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
            ld0 r2 = r5.M
            kd0 r4 = r2.E
            zu0 r4 = r4.a
            yi r4 = r4.f
            android.graphics.RectF r2 = r2.g()
            float r2 = r4.a(r2)
            android.view.RoundedCorner r0 = defpackage.ab.p(r0)
            if (r0 == 0) goto L6a
            int r0 = defpackage.ab.c(r0)
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
        return this.J ? Math.min(Math.max(this.K, this.x0 - ((this.w0 * 9) / 16)), this.v0) + this.Z : (this.R || this.S || (i = this.Q) <= 0) ? this.I + this.Z : Math.max(this.I, i + this.L);
    }

    public final void z(View view, int i) {
        if (view == null) {
            return;
        }
        f51.l(view, 524288);
        f51.i(view, 0);
        f51.l(view, 262144);
        f51.i(view, 0);
        f51.l(view, 1048576);
        f51.i(view, 0);
        SparseIntArray sparseIntArray = this.I0;
        int i2 = sparseIntArray.get(i, -1);
        if (i2 != -1) {
            f51.l(view, i2);
            f51.i(view, 0);
            sparseIntArray.delete(i);
        }
    }

    public static class SavedState extends androidx.customview.view.AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final int G;
        public final int H;
        public final boolean I;
        public final boolean J;
        public final boolean K;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.G = parcel.readInt();
            this.H = parcel.readInt();
            this.I = parcel.readInt() == 1;
            this.J = parcel.readInt() == 1;
            this.K = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.G);
            parcel.writeInt(this.H);
            parcel.writeInt(this.I ? 1 : 0);
            parcel.writeInt(this.J ? 1 : 0);
            parcel.writeInt(this.K ? 1 : 0);
        }

        public SavedState(BottomSheetBehavior bottomSheetBehavior) {
            super(AbsSavedState.EMPTY_STATE);
            this.G = bottomSheetBehavior.p0;
            this.H = bottomSheetBehavior.I;
            this.I = bottomSheetBehavior.F;
            this.J = bottomSheetBehavior.m0;
            this.K = bottomSheetBehavior.n0;
        }
    }

    public BottomSheetBehavior() {
        this.E = 0;
        this.F = true;
        this.O = -1;
        this.P = -1;
        this.e0 = new eb(this);
        this.j0 = 0.5f;
        this.l0 = -1.0f;
        this.o0 = true;
        this.p0 = 4;
        this.u0 = 0.1f;
        this.B0 = new ArrayList();
        this.F0 = -1;
        this.I0 = new SparseIntArray();
        this.J0 = new db(this, 0);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void p(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
    }
}
