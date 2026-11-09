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
import defpackage.af0;
import defpackage.cs0;
import defpackage.d1;
import defpackage.e;
import defpackage.f9;
import defpackage.g9;
import defpackage.hk;
import defpackage.ii0;
import defpackage.j0;
import defpackage.jq0;
import defpackage.k10;
import defpackage.ki0;
import defpackage.lr0;
import defpackage.r7;
import defpackage.u50;
import defpackage.w40;
import defpackage.w50;
import defpackage.xi;
import defpackage.xr0;
import defpackage.y0;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SideSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> implements w40 {
    public static final int y = R$string.side_sheet_accessibility_pane_title;
    public static final int z = R$style.Widget_Material3_SideSheet;
    public jq0 b;
    public final u50 c;
    public final ColorStateList d;
    public final ki0 e;
    public final g9 f;
    public final float g;
    public final boolean h;
    public int i;
    public cs0 j;
    public boolean k;
    public final float l;
    public int m;
    public int n;
    public int o;
    public int p;
    public WeakReference q;
    public WeakReference r;
    public final int s;
    public VelocityTracker t;
    public w50 u;
    public int v;
    public final LinkedHashSet w;
    public final f9 x;

    public SideSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new g9(this);
        this.h = true;
        this.i = 5;
        this.l = 0.1f;
        this.s = -1;
        this.w = new LinkedHashSet();
        this.x = new f9(this, 1);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SideSheetBehavior_Layout);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SideSheetBehavior_Layout_backgroundTint)) {
            this.d = xi.s(context, typedArrayObtainStyledAttributes, R$styleable.SideSheetBehavior_Layout_backgroundTint);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SideSheetBehavior_Layout_shapeAppearance)) {
            this.e = ki0.c(context, attributeSet, 0, z).a();
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SideSheetBehavior_Layout_coplanarSiblingViewId)) {
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SideSheetBehavior_Layout_coplanarSiblingViewId, -1);
            this.s = resourceId;
            WeakReference weakReference = this.r;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.r = null;
            WeakReference weakReference2 = this.q;
            if (weakReference2 != null) {
                View view = (View) weakReference2.get();
                if (resourceId != -1) {
                    WeakHashMap weakHashMap = xr0.a;
                    if (view.isLaidOut()) {
                        view.requestLayout();
                    }
                }
            }
        }
        ki0 ki0Var = this.e;
        if (ki0Var != null) {
            u50 u50Var = new u50(ki0Var);
            this.c = u50Var;
            u50Var.k(context);
            ColorStateList colorStateList = this.d;
            if (colorStateList != null) {
                this.c.m(colorStateList);
            } else {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
                this.c.setTint(typedValue.data);
            }
        }
        this.g = typedArrayObtainStyledAttributes.getDimension(R$styleable.SideSheetBehavior_Layout_android_elevation, -1.0f);
        this.h = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SideSheetBehavior_Layout_behavior_draggable, true);
        typedArrayObtainStyledAttributes.recycle();
        ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public final void A() {
        View view;
        WeakReference weakReference = this.q;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        xr0.m(view, 262144);
        xr0.i(view, 0);
        xr0.m(view, 1048576);
        xr0.i(view, 0);
        final int i = 5;
        if (this.i != 5) {
            xr0.n(view, j0.l, null, new y0() { // from class: dj0
                @Override // defpackage.y0
                public final boolean c(View view2) {
                    int i2 = SideSheetBehavior.y;
                    this.b.w(i);
                    return true;
                }
            });
        }
        final int i2 = 3;
        if (this.i != 3) {
            xr0.n(view, j0.j, null, new y0() { // from class: dj0
                @Override // defpackage.y0
                public final boolean c(View view2) {
                    int i22 = SideSheetBehavior.y;
                    this.b.w(i2);
                    return true;
                }
            });
        }
    }

    @Override // defpackage.w40
    public final void a(r7 r7Var) {
        w50 w50Var = this.u;
        if (w50Var == null) {
            return;
        }
        w50Var.f = r7Var;
    }

    @Override // defpackage.w40
    public final void b() {
        final ViewGroup.MarginLayoutParams marginLayoutParams;
        w50 w50Var = this.u;
        if (w50Var == null) {
            return;
        }
        r7 r7Var = w50Var.f;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = null;
        w50Var.f = null;
        int i = 5;
        if (r7Var == null || Build.VERSION.SDK_INT < 34) {
            w(5);
            return;
        }
        jq0 jq0Var = this.b;
        if (jq0Var != null && jq0Var.D() != 0) {
            i = 3;
        }
        d1 d1Var = new d1(9, this);
        WeakReference weakReference = this.r;
        final View view = weakReference != null ? (View) weakReference.get() : null;
        if (view != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams()) != null) {
            final int iT = this.b.t(marginLayoutParams);
            animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: ej0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.a.b.m0(marginLayoutParams, t3.c(iT, valueAnimator.getAnimatedFraction(), 0));
                    view.requestLayout();
                }
            };
        }
        w50Var.c(r7Var, i, d1Var, animatorUpdateListener);
    }

    @Override // defpackage.w40
    public final void c(r7 r7Var) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        w50 w50Var = this.u;
        if (w50Var == null) {
            return;
        }
        jq0 jq0Var = this.b;
        int i = (jq0Var == null || jq0Var.D() == 0) ? 5 : 3;
        if (w50Var.f == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() before updateBackProgress()");
        }
        r7 r7Var2 = w50Var.f;
        w50Var.f = r7Var;
        if (r7Var2 != null) {
            w50Var.d(r7Var.c, i, r7Var.d == 0);
        }
        WeakReference weakReference = this.q;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        View view = (View) this.q.get();
        WeakReference weakReference2 = this.r;
        View view2 = weakReference2 != null ? (View) weakReference2.get() : null;
        if (view2 == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()) == null) {
            return;
        }
        this.b.m0(marginLayoutParams, (int) ((view.getScaleX() * this.m) + this.p));
        view2.requestLayout();
    }

    @Override // defpackage.w40
    public final void d() {
        w50 w50Var = this.u;
        if (w50Var == null) {
            return;
        }
        w50Var.b();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void g(c cVar) {
        this.q = null;
        this.j = null;
        this.u = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void j() {
        this.q = null;
        this.j = null;
        this.u = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        cs0 cs0Var;
        VelocityTracker velocityTracker;
        if ((!view.isShown() && xr0.e(view) == null) || !this.h) {
            this.k = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 && (velocityTracker = this.t) != null) {
            velocityTracker.recycle();
            this.t = null;
        }
        if (this.t == null) {
            this.t = VelocityTracker.obtain();
        }
        this.t.addMovement(motionEvent);
        if (actionMasked == 0) {
            this.v = (int) motionEvent.getX();
        } else if ((actionMasked == 1 || actionMasked == 3) && this.k) {
            this.k = false;
            return false;
        }
        return (this.k || (cs0Var = this.j) == null || !cs0Var.r(motionEvent)) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        View view2;
        View view3;
        int i2;
        View viewFindViewById;
        int iB = 0;
        int i3 = 1;
        u50 u50Var = this.c;
        WeakHashMap weakHashMap = xr0.a;
        if (coordinatorLayout.getFitsSystemWindows() && !view.getFitsSystemWindows()) {
            view.setFitsSystemWindows(true);
        }
        if (this.q == null) {
            this.q = new WeakReference(view);
            this.u = new w50(view);
            if (u50Var != null) {
                view.setBackground(u50Var);
                float fI = this.g;
                if (fI == -1.0f) {
                    fI = lr0.i(view);
                }
                u50Var.l(fI);
            } else {
                ColorStateList colorStateList = this.d;
                if (colorStateList != null) {
                    lr0.q(view, colorStateList);
                }
            }
            int i4 = this.i == 5 ? 4 : 0;
            if (view.getVisibility() != i4) {
                view.setVisibility(i4);
            }
            A();
            if (view.getImportantForAccessibility() == 0) {
                view.setImportantForAccessibility(1);
            }
            if (xr0.e(view) == null) {
                xr0.q(view, view.getResources().getString(y));
            }
        }
        int i5 = Gravity.getAbsoluteGravity(((c) view.getLayoutParams()).c, i) == 3 ? 1 : 0;
        jq0 jq0Var = this.b;
        if (jq0Var == null || jq0Var.D() != i5) {
            ki0 ki0Var = this.e;
            c cVar = null;
            if (i5 == 0) {
                this.b = new k10(this, i3);
                if (ki0Var != null) {
                    WeakReference weakReference = this.q;
                    if (weakReference != null && (view3 = (View) weakReference.get()) != null && (view3.getLayoutParams() instanceof c)) {
                        cVar = (c) view3.getLayoutParams();
                    }
                    if (cVar == null || ((ViewGroup.MarginLayoutParams) cVar).rightMargin <= 0) {
                        ii0 ii0VarG = ki0Var.g();
                        ii0VarG.f = new e(0.0f);
                        ii0VarG.g = new e(0.0f);
                        ki0 ki0VarA = ii0VarG.a();
                        if (u50Var != null) {
                            u50Var.setShapeAppearanceModel(ki0VarA);
                        }
                    }
                }
            } else {
                if (i5 != 1) {
                    throw new IllegalArgumentException(hk.h(i5, "Invalid sheet edge position value: ", ". Must be 0 or 1."));
                }
                this.b = new k10(this, iB);
                if (ki0Var != null) {
                    WeakReference weakReference2 = this.q;
                    if (weakReference2 != null && (view2 = (View) weakReference2.get()) != null && (view2.getLayoutParams() instanceof c)) {
                        cVar = (c) view2.getLayoutParams();
                    }
                    if (cVar == null || ((ViewGroup.MarginLayoutParams) cVar).leftMargin <= 0) {
                        ii0 ii0VarG2 = ki0Var.g();
                        ii0VarG2.e = new e(0.0f);
                        ii0VarG2.h = new e(0.0f);
                        ki0 ki0VarA2 = ii0VarG2.a();
                        if (u50Var != null) {
                            u50Var.setShapeAppearanceModel(ki0VarA2);
                        }
                    }
                }
            }
        }
        if (this.j == null) {
            this.j = new cs0(coordinatorLayout.getContext(), coordinatorLayout, this.x);
        }
        int iB2 = this.b.B(view);
        coordinatorLayout.v(view, i);
        this.n = coordinatorLayout.getWidth();
        this.o = this.b.C(coordinatorLayout);
        this.m = view.getWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        this.p = marginLayoutParams != null ? this.b.a(marginLayoutParams) : 0;
        int i6 = this.i;
        if (i6 == 1 || i6 == 2) {
            iB = iB2 - this.b.B(view);
        } else if (i6 != 3) {
            if (i6 != 5) {
                throw new IllegalStateException("Unexpected value: " + this.i);
            }
            iB = this.b.w();
        }
        view.offsetLeftAndRight(iB);
        if (this.r == null && (i2 = this.s) != -1 && (viewFindViewById = coordinatorLayout.findViewById(i2)) != null) {
            this.r = new WeakReference(viewFindViewById);
        }
        Iterator it = this.w.iterator();
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
        int i = ((SavedState) parcelable).d;
        if (i == 1 || i == 2) {
            i = 5;
        }
        this.i = i;
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
        if (this.i == 1 && actionMasked == 0) {
            return true;
        }
        if (y()) {
            this.j.k(motionEvent);
        }
        if (actionMasked == 0 && (velocityTracker = this.t) != null) {
            velocityTracker.recycle();
            this.t = null;
        }
        if (this.t == null) {
            this.t = VelocityTracker.obtain();
        }
        this.t.addMovement(motionEvent);
        if (y() && actionMasked == 2 && !this.k && y()) {
            float fAbs = Math.abs(this.v - motionEvent.getX());
            cs0 cs0Var = this.j;
            if (fAbs > cs0Var.b) {
                cs0Var.b(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.k;
    }

    public final void w(int i) {
        if (i == 1 || i == 2) {
            throw new IllegalArgumentException(hk.k(new StringBuilder("STATE_"), i == 1 ? "DRAGGING" : "SETTLING", " should not be set externally."));
        }
        WeakReference weakReference = this.q;
        if (weakReference == null || weakReference.get() == null) {
            x(i);
            return;
        }
        View view = (View) this.q.get();
        af0 af0Var = new af0(this, i, 1);
        ViewParent parent = view.getParent();
        if (parent != null && parent.isLayoutRequested()) {
            WeakHashMap weakHashMap = xr0.a;
            if (view.isAttachedToWindow()) {
                view.post(af0Var);
                return;
            }
        }
        af0Var.run();
    }

    public final void x(int i) {
        View view;
        if (this.i == i) {
            return;
        }
        this.i = i;
        WeakReference weakReference = this.q;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        int i2 = this.i == 5 ? 4 : 0;
        if (view.getVisibility() != i2) {
            view.setVisibility(i2);
        }
        Iterator it = this.w.iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
        A();
    }

    public final boolean y() {
        if (this.j != null) {
            return this.h || this.i == 1;
        }
        return false;
    }

    public final void z(View view, int i, boolean z2) {
        int iU;
        if (i == 3) {
            iU = this.b.u();
        } else {
            if (i != 5) {
                throw new IllegalArgumentException(hk.g(i, "Invalid state to get outer edge offset: "));
            }
            iU = this.b.w();
        }
        cs0 cs0Var = this.j;
        if (cs0Var == null || (!z2 ? cs0Var.s(view, iU, view.getTop()) : cs0Var.q(iU, view.getTop()))) {
            x(i);
        } else {
            x(2);
            this.f.a(i);
        }
    }

    public static class SavedState extends androidx.customview.view.AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final int d;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.d);
        }

        public SavedState(SideSheetBehavior sideSheetBehavior) {
            super(AbsSavedState.EMPTY_STATE);
            this.d = sideSheetBehavior.i;
        }
    }

    public SideSheetBehavior() {
        this.f = new g9(this);
        this.h = true;
        this.i = 5;
        this.l = 0.1f;
        this.s = -1;
        this.w = new LinkedHashSet();
        this.x = new f9(this, 1);
    }
}
