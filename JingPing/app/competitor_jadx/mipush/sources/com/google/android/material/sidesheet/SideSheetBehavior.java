package com.google.android.material.sidesheet;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.AbsSavedState;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.c;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.sidesheet.SideSheetBehavior;
import defpackage.db;
import defpackage.e2;
import defpackage.eb;
import defpackage.f51;
import defpackage.k51;
import defpackage.l9;
import defpackage.la0;
import defpackage.ld0;
import defpackage.m80;
import defpackage.mc0;
import defpackage.nd0;
import defpackage.o0;
import defpackage.t41;
import defpackage.u2;
import defpackage.xu0;
import defpackage.z2;
import defpackage.zn;
import defpackage.zo0;
import defpackage.zu0;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SideSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> implements mc0 {
    public static final int b0 = R$string.side_sheet_accessibility_pane_title;
    public static final int c0 = R$style.Widget_Material3_SideSheet;
    public la0 E;
    public final ld0 F;
    public final ColorStateList G;
    public final zu0 H;
    public final eb I;
    public final float J;
    public final boolean K;
    public int L;
    public k51 M;
    public boolean N;
    public final float O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public WeakReference T;
    public WeakReference U;
    public final int V;
    public VelocityTracker W;
    public nd0 X;
    public int Y;
    public final LinkedHashSet Z;
    public final db a0;

    public SideSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.I = new eb(this);
        this.K = true;
        this.L = 5;
        this.O = 0.1f;
        this.V = -1;
        this.Z = new LinkedHashSet();
        this.a0 = new db(this, 1);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SideSheetBehavior_Layout);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SideSheetBehavior_Layout_backgroundTint)) {
            this.G = la0.p(context, typedArrayObtainStyledAttributes, R$styleable.SideSheetBehavior_Layout_backgroundTint);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SideSheetBehavior_Layout_shapeAppearance)) {
            this.H = zu0.c(context, attributeSet, 0, c0).a();
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SideSheetBehavior_Layout_coplanarSiblingViewId)) {
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SideSheetBehavior_Layout_coplanarSiblingViewId, -1);
            this.V = resourceId;
            WeakReference weakReference = this.U;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.U = null;
            WeakReference weakReference2 = this.T;
            if (weakReference2 != null) {
                View view = (View) weakReference2.get();
                if (resourceId != -1) {
                    WeakHashMap weakHashMap = f51.a;
                    if (view.isLaidOut()) {
                        view.requestLayout();
                    }
                }
            }
        }
        zu0 zu0Var = this.H;
        if (zu0Var != null) {
            ld0 ld0Var = new ld0(zu0Var);
            this.F = ld0Var;
            ld0Var.k(context);
            ColorStateList colorStateList = this.G;
            if (colorStateList != null) {
                this.F.m(colorStateList);
            } else {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
                this.F.setTint(typedValue.data);
            }
        }
        this.J = typedArrayObtainStyledAttributes.getDimension(R$styleable.SideSheetBehavior_Layout_android_elevation, -1.0f);
        this.K = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SideSheetBehavior_Layout_behavior_draggable, true);
        typedArrayObtainStyledAttributes.recycle();
        ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public final void A() {
        View view;
        WeakReference weakReference = this.T;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        f51.l(view, 262144);
        f51.i(view, 0);
        f51.l(view, 1048576);
        f51.i(view, 0);
        final int i = 5;
        if (this.L != 5) {
            f51.m(view, e2.l, null, new u2() { // from class: sv0
                @Override // defpackage.u2
                public final boolean b(View view2) {
                    int i2 = SideSheetBehavior.b0;
                    this.E.w(i);
                    return true;
                }
            });
        }
        final int i2 = 3;
        if (this.L != 3) {
            f51.m(view, e2.j, null, new u2() { // from class: sv0
                @Override // defpackage.u2
                public final boolean b(View view2) {
                    int i22 = SideSheetBehavior.b0;
                    this.E.w(i2);
                    return true;
                }
            });
        }
    }

    @Override // defpackage.mc0
    public final void a() {
        final ViewGroup.MarginLayoutParams marginLayoutParams;
        nd0 nd0Var = this.X;
        if (nd0Var == null) {
            return;
        }
        l9 l9Var = nd0Var.f;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = null;
        nd0Var.f = null;
        int i = 5;
        if (l9Var == null || Build.VERSION.SDK_INT < 34) {
            w(5);
            return;
        }
        la0 la0Var = this.E;
        if (la0Var != null && la0Var.G() != 0) {
            i = 3;
        }
        z2 z2Var = new z2(10, this);
        WeakReference weakReference = this.U;
        final View view = weakReference != null ? (View) weakReference.get() : null;
        if (view != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams()) != null) {
            final int iQ = this.E.q(marginLayoutParams);
            animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: tv0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.a.E.l0(marginLayoutParams, y4.c(iQ, valueAnimator.getAnimatedFraction(), 0));
                    view.requestLayout();
                }
            };
        }
        nd0Var.c(l9Var, i, z2Var, animatorUpdateListener);
    }

    @Override // defpackage.mc0
    public final void b(l9 l9Var) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        nd0 nd0Var = this.X;
        if (nd0Var == null) {
            return;
        }
        la0 la0Var = this.E;
        int i = (la0Var == null || la0Var.G() == 0) ? 5 : 3;
        if (nd0Var.f == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() before updateBackProgress()");
        }
        l9 l9Var2 = nd0Var.f;
        nd0Var.f = l9Var;
        if (l9Var2 != null) {
            nd0Var.d(l9Var.c, i, l9Var.d == 0);
        }
        WeakReference weakReference = this.T;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        View view = (View) this.T.get();
        WeakReference weakReference2 = this.U;
        View view2 = weakReference2 != null ? (View) weakReference2.get() : null;
        if (view2 == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()) == null) {
            return;
        }
        this.E.l0(marginLayoutParams, (int) ((view.getScaleX() * this.P) + this.S));
        view2.requestLayout();
    }

    @Override // defpackage.mc0
    public final void c(l9 l9Var) {
        nd0 nd0Var = this.X;
        if (nd0Var == null) {
            return;
        }
        nd0Var.f = l9Var;
    }

    @Override // defpackage.mc0
    public final void d() {
        nd0 nd0Var = this.X;
        if (nd0Var == null) {
            return;
        }
        nd0Var.b();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void g(c cVar) {
        this.T = null;
        this.M = null;
        this.X = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void j() {
        this.T = null;
        this.M = null;
        this.X = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        k51 k51Var;
        VelocityTracker velocityTracker;
        if ((!view.isShown() && f51.e(view) == null) || !this.K) {
            this.N = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 && (velocityTracker = this.W) != null) {
            velocityTracker.recycle();
            this.W = null;
        }
        if (this.W == null) {
            this.W = VelocityTracker.obtain();
        }
        this.W.addMovement(motionEvent);
        if (actionMasked == 0) {
            this.Y = (int) motionEvent.getX();
        } else if ((actionMasked == 1 || actionMasked == 3) && this.N) {
            this.N = false;
            return false;
        }
        return (this.N || (k51Var = this.M) == null || !k51Var.r(motionEvent)) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        View view2;
        View view3;
        int i2;
        View viewFindViewById;
        int iA = 0;
        int i3 = 1;
        ld0 ld0Var = this.F;
        WeakHashMap weakHashMap = f51.a;
        if (coordinatorLayout.getFitsSystemWindows() && !view.getFitsSystemWindows()) {
            view.setFitsSystemWindows(true);
        }
        if (this.T == null) {
            this.T = new WeakReference(view);
            this.X = new nd0(view);
            if (ld0Var != null) {
                view.setBackground(ld0Var);
                float fI = this.J;
                if (fI == -1.0f) {
                    fI = t41.i(view);
                }
                ld0Var.l(fI);
            } else {
                ColorStateList colorStateList = this.G;
                if (colorStateList != null) {
                    t41.q(view, colorStateList);
                }
            }
            int i4 = this.L == 5 ? 4 : 0;
            if (view.getVisibility() != i4) {
                view.setVisibility(i4);
            }
            A();
            if (view.getImportantForAccessibility() == 0) {
                view.setImportantForAccessibility(1);
            }
            if (f51.e(view) == null) {
                f51.p(view, view.getResources().getString(b0));
            }
        }
        int i5 = Gravity.getAbsoluteGravity(((c) view.getLayoutParams()).c, i) == 3 ? 1 : 0;
        la0 la0Var = this.E;
        if (la0Var == null || la0Var.G() != i5) {
            zu0 zu0Var = this.H;
            c cVar = null;
            if (i5 == 0) {
                this.E = new m80(this, i3);
                if (zu0Var != null) {
                    WeakReference weakReference = this.T;
                    if (weakReference != null && (view3 = (View) weakReference.get()) != null && (view3.getLayoutParams() instanceof c)) {
                        cVar = (c) view3.getLayoutParams();
                    }
                    if (cVar == null || ((ViewGroup.MarginLayoutParams) cVar).rightMargin <= 0) {
                        xu0 xu0VarG = zu0Var.g();
                        xu0VarG.f = new o0(0.0f);
                        xu0VarG.g = new o0(0.0f);
                        zu0 zu0VarA = xu0VarG.a();
                        if (ld0Var != null) {
                            ld0Var.setShapeAppearanceModel(zu0VarA);
                        }
                    }
                }
            } else {
                if (i5 != 1) {
                    throw new IllegalArgumentException(zn.k(i5, "Invalid sheet edge position value: ", ". Must be 0 or 1."));
                }
                this.E = new m80(this, iA);
                if (zu0Var != null) {
                    WeakReference weakReference2 = this.T;
                    if (weakReference2 != null && (view2 = (View) weakReference2.get()) != null && (view2.getLayoutParams() instanceof c)) {
                        cVar = (c) view2.getLayoutParams();
                    }
                    if (cVar == null || ((ViewGroup.MarginLayoutParams) cVar).leftMargin <= 0) {
                        xu0 xu0VarG2 = zu0Var.g();
                        xu0VarG2.e = new o0(0.0f);
                        xu0VarG2.h = new o0(0.0f);
                        zu0 zu0VarA2 = xu0VarG2.a();
                        if (ld0Var != null) {
                            ld0Var.setShapeAppearanceModel(zu0VarA2);
                        }
                    }
                }
            }
        }
        if (this.M == null) {
            this.M = new k51(coordinatorLayout.getContext(), coordinatorLayout, this.a0);
        }
        int iA2 = this.E.A(view);
        coordinatorLayout.v(view, i);
        this.Q = coordinatorLayout.getWidth();
        this.R = this.E.E(coordinatorLayout);
        this.P = view.getWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        this.S = marginLayoutParams != null ? this.E.d(marginLayoutParams) : 0;
        int i6 = this.L;
        if (i6 == 1 || i6 == 2) {
            iA = iA2 - this.E.A(view);
        } else if (i6 != 3) {
            if (i6 != 5) {
                throw new IllegalStateException("Unexpected value: " + this.L);
            }
            iA = this.E.u();
        }
        view.offsetLeftAndRight(iA);
        if (this.U == null && (i2 = this.V) != -1 && (viewFindViewById = coordinatorLayout.findViewById(i2)) != null) {
            this.U = new WeakReference(viewFindViewById);
        }
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
        }
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean m(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void r(View view, Parcelable parcelable) {
        int i = ((SavedState) parcelable).G;
        if (i == 1 || i == 2) {
            i = 5;
        }
        this.L = i;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final Parcelable s(View view) {
        AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
        return new SavedState(this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean v(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        VelocityTracker velocityTracker;
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.L == 1 && actionMasked == 0) {
            return true;
        }
        if (y()) {
            this.M.k(motionEvent);
        }
        if (actionMasked == 0 && (velocityTracker = this.W) != null) {
            velocityTracker.recycle();
            this.W = null;
        }
        if (this.W == null) {
            this.W = VelocityTracker.obtain();
        }
        this.W.addMovement(motionEvent);
        if (y() && actionMasked == 2 && !this.N && y()) {
            float fAbs = Math.abs(this.Y - motionEvent.getX());
            k51 k51Var = this.M;
            if (fAbs > k51Var.b) {
                k51Var.b(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.N;
    }

    public final void w(int i) {
        if (i == 1 || i == 2) {
            throw new IllegalArgumentException(zn.o(new StringBuilder("STATE_"), i == 1 ? "DRAGGING" : "SETTLING", " should not be set externally."));
        }
        WeakReference weakReference = this.T;
        if (weakReference == null || weakReference.get() == null) {
            x(i);
            return;
        }
        View view = (View) this.T.get();
        zo0 zo0Var = new zo0(this, i, 1);
        ViewParent parent = view.getParent();
        if (parent != null && parent.isLayoutRequested()) {
            WeakHashMap weakHashMap = f51.a;
            if (view.isAttachedToWindow()) {
                view.post(zo0Var);
                return;
            }
        }
        zo0Var.run();
    }

    public final void x(int i) {
        View view;
        if (this.L == i) {
            return;
        }
        this.L = i;
        WeakReference weakReference = this.T;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        int i2 = this.L == 5 ? 4 : 0;
        if (view.getVisibility() != i2) {
            view.setVisibility(i2);
        }
        Iterator it = this.Z.iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
        A();
    }

    public final boolean y() {
        if (this.M != null) {
            return this.K || this.L == 1;
        }
        return false;
    }

    public final void z(View view, int i, boolean z) {
        int iT;
        if (i == 3) {
            iT = this.E.t();
        } else {
            if (i != 5) {
                throw new IllegalArgumentException(zn.j(i, "Invalid state to get outer edge offset: "));
            }
            iT = this.E.u();
        }
        k51 k51Var = this.M;
        if (k51Var == null || (!z ? k51Var.s(view, iT, view.getTop()) : k51Var.q(iT, view.getTop()))) {
            x(i);
        } else {
            x(2);
            this.I.a(i);
        }
    }

    public static class SavedState extends androidx.customview.view.AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final int G;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.G = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.G);
        }

        public SavedState(SideSheetBehavior sideSheetBehavior) {
            super(AbsSavedState.EMPTY_STATE);
            this.G = sideSheetBehavior.L;
        }
    }

    public SideSheetBehavior() {
        this.I = new eb(this);
        this.K = true;
        this.L = 5;
        this.O = 0.1f;
        this.V = -1;
        this.Z = new LinkedHashSet();
        this.a0 = new db(this, 1);
    }
}
