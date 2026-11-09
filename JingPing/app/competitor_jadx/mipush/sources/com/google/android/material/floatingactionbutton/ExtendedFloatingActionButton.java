package com.google.android.material.floatingactionbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.a;
import androidx.coordinatorlayout.widget.c;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import defpackage.bo;
import defpackage.f51;
import defpackage.i01;
import defpackage.i6;
import defpackage.ou;
import defpackage.p50;
import defpackage.pd0;
import defpackage.rf0;
import defpackage.xu;
import defpackage.yc;
import defpackage.yu;
import defpackage.z8;
import defpackage.zu;
import defpackage.zu0;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements a {
    public static final int q0 = R$style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    public static final yc r0 = new yc(Float.class, "width", 11);
    public static final yc s0 = new yc(Float.class, "height", 12);
    public static final yc t0 = new yc(Float.class, "paddingStart", 13);
    public static final yc u0 = new yc(Float.class, "paddingEnd", 14);
    public int b0;
    public final xu c0;
    public final xu d0;
    public final zu e0;
    public final yu f0;
    public final int g0;
    public int h0;
    public int i0;
    public final ExtendedFloatingActionButtonBehavior j0;
    public boolean k0;
    public boolean l0;
    public boolean m0;
    public ColorStateList n0;
    public int o0;
    public int p0;

    public ExtendedFloatingActionButton(Context context) {
        this(context, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void e(com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r4, int r5) {
        /*
            r0 = 1
            r1 = 2
            if (r5 == 0) goto L20
            if (r5 == r0) goto L1d
            if (r5 == r1) goto L1a
            r2 = 3
            if (r5 != r2) goto Le
            xu r2 = r4.d0
            goto L22
        Le:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "Unknown strategy type: "
            java.lang.String r5 = defpackage.zn.j(r5, r0)
            r4.<init>(r5)
            throw r4
        L1a:
            xu r2 = r4.c0
            goto L22
        L1d:
            yu r2 = r4.f0
            goto L22
        L20:
            zu r2 = r4.e0
        L22:
            boolean r3 = r2.h()
            if (r3 == 0) goto L29
            return
        L29:
            java.util.WeakHashMap r3 = defpackage.f51.a
            boolean r3 = r4.isLaidOut()
            if (r3 != 0) goto L45
            int r3 = r4.getVisibility()
            if (r3 == 0) goto L3c
            int r0 = r4.b0
            if (r0 != r1) goto L41
            goto L93
        L3c:
            int r3 = r4.b0
            if (r3 == r0) goto L41
            goto L93
        L41:
            boolean r0 = r4.m0
            if (r0 == 0) goto L93
        L45:
            boolean r0 = r4.isInEditMode()
            if (r0 != 0) goto L93
            if (r5 != r1) goto L68
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            if (r5 == 0) goto L5c
            int r0 = r5.width
            r4.o0 = r0
            int r5 = r5.height
            r4.p0 = r5
            goto L68
        L5c:
            int r5 = r4.getWidth()
            r4.o0 = r5
            int r5 = r4.getHeight()
            r4.p0 = r5
        L68:
            r5 = 0
            r4.measure(r5, r5)
            android.animation.AnimatorSet r4 = r2.a()
            z2 r5 = new z2
            r0 = 5
            r5.<init>(r0, r2)
            r4.addListener(r5)
            java.util.ArrayList r5 = r2.c
            java.util.Iterator r5 = r5.iterator()
        L7f:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L8f
            java.lang.Object r0 = r5.next()
            android.animation.Animator$AnimatorListener r0 = (android.animation.Animator.AnimatorListener) r0
            r4.addListener(r0)
            goto L7f
        L8f:
            r4.start()
            return
        L93:
            r2.g()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.e(com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton, int):void");
    }

    public final void f(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
    }

    @Override // androidx.coordinatorlayout.widget.a
    public CoordinatorLayout.Behavior<ExtendedFloatingActionButton> getBehavior() {
        return this.j0;
    }

    public int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    public int getCollapsedSize() {
        int i = this.g0;
        if (i >= 0) {
            return i;
        }
        WeakHashMap weakHashMap = f51.a;
        return getIconSize() + (Math.min(getPaddingStart(), getPaddingEnd()) * 2);
    }

    public rf0 getExtendMotionSpec() {
        return this.d0.f;
    }

    public rf0 getHideMotionSpec() {
        return this.f0.f;
    }

    public rf0 getShowMotionSpec() {
        return this.e0.f;
    }

    public rf0 getShrinkMotionSpec() {
        return this.c0.f;
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.k0 && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.k0 = false;
            this.c0.g();
        }
    }

    public void setAnimateShowBeforeLayout(boolean z) {
        this.m0 = z;
    }

    public void setExtendMotionSpec(rf0 rf0Var) {
        this.d0.f = rf0Var;
    }

    public void setExtendMotionSpecResource(int i) {
        setExtendMotionSpec(rf0.b(getContext(), i));
    }

    public void setExtended(boolean z) {
        if (this.k0 == z) {
            return;
        }
        xu xuVar = z ? this.d0 : this.c0;
        if (xuVar.h()) {
            return;
        }
        xuVar.g();
    }

    public void setHideMotionSpec(rf0 rf0Var) {
        this.f0.f = rf0Var;
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(rf0.b(getContext(), i));
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        if (!this.k0 || this.l0) {
            return;
        }
        WeakHashMap weakHashMap = f51.a;
        this.h0 = getPaddingStart();
        this.i0 = getPaddingEnd();
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(i, i2, i3, i4);
        if (!this.k0 || this.l0) {
            return;
        }
        this.h0 = i;
        this.i0 = i3;
    }

    public void setShowMotionSpec(rf0 rf0Var) {
        this.e0.f = rf0Var;
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(rf0.b(getContext(), i));
    }

    public void setShrinkMotionSpec(rf0 rf0Var) {
        this.c0.f = rf0Var;
    }

    public void setShrinkMotionSpecResource(int i) {
        setShrinkMotionSpec(rf0.b(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setTextColor(int i) {
        super.setTextColor(i);
        this.n0 = getTextColors();
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.extendedFloatingActionButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = q0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.b0 = 0;
        p50 p50Var = new p50(6, false);
        zu zuVar = new zu(this, p50Var);
        this.e0 = zuVar;
        yu yuVar = new yu(this, p50Var);
        this.f0 = yuVar;
        this.k0 = true;
        this.l0 = false;
        this.m0 = false;
        Context context2 = getContext();
        this.j0 = new ExtendedFloatingActionButtonBehavior(context2, attributeSet);
        TypedArray typedArrayD = i01.d(context2, attributeSet, R$styleable.ExtendedFloatingActionButton, i, i2, new int[0]);
        rf0 rf0VarA = rf0.a(context2, typedArrayD, R$styleable.ExtendedFloatingActionButton_showMotionSpec);
        rf0 rf0VarA2 = rf0.a(context2, typedArrayD, R$styleable.ExtendedFloatingActionButton_hideMotionSpec);
        rf0 rf0VarA3 = rf0.a(context2, typedArrayD, R$styleable.ExtendedFloatingActionButton_extendMotionSpec);
        rf0 rf0VarA4 = rf0.a(context2, typedArrayD, R$styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        this.g0 = typedArrayD.getDimensionPixelSize(R$styleable.ExtendedFloatingActionButton_collapsedSize, -1);
        int i3 = typedArrayD.getInt(R$styleable.ExtendedFloatingActionButton_extendStrategy, 1);
        WeakHashMap weakHashMap = f51.a;
        this.h0 = getPaddingStart();
        this.i0 = getPaddingEnd();
        p50 p50Var2 = new p50(6, false);
        z8 z8Var = new z8(19, this);
        i6 i6Var = new i6(this, 13, z8Var);
        xu xuVar = new xu(this, p50Var2, i3 != 1 ? i3 != 2 ? new ou(this, i6Var, z8Var) : i6Var : z8Var, true);
        this.d0 = xuVar;
        xu xuVar2 = new xu(this, p50Var2, new p50(20, this), false);
        this.c0 = xuVar2;
        zuVar.f = rf0VarA;
        yuVar.f = rf0VarA2;
        xuVar.f = rf0VarA3;
        xuVar2.f = rf0VarA4;
        typedArrayD.recycle();
        setShapeAppearanceModel(zu0.d(context2, attributeSet, i, i2, zu0.m).a());
        this.n0 = getTextColors();
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        this.n0 = getTextColors();
    }

    public static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        public Rect E;
        public final boolean F;
        public final boolean G;

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.F = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.G = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ boolean e(Rect rect, View view) {
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void g(c cVar) {
            if (cVar.h == 0) {
                cVar.h = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            if (view2 instanceof AppBarLayout) {
                w(coordinatorLayout, (AppBarLayout) view2, extendedFloatingActionButton);
            } else {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                if (layoutParams instanceof c ? ((c) layoutParams).a instanceof BottomSheetBehavior : false) {
                    x(view2, extendedFloatingActionButton);
                }
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            ArrayList arrayListO = coordinatorLayout.o(extendedFloatingActionButton);
            int size = arrayListO.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view2 = (View) arrayListO.get(i2);
                if (!(view2 instanceof AppBarLayout)) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if ((layoutParams instanceof c ? ((c) layoutParams).a instanceof BottomSheetBehavior : false) && x(view2, extendedFloatingActionButton)) {
                        break;
                    }
                } else {
                    if (w(coordinatorLayout, (AppBarLayout) view2, extendedFloatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.v(extendedFloatingActionButton, i);
            return true;
        }

        public final boolean w(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            c cVar = (c) extendedFloatingActionButton.getLayoutParams();
            if ((!this.F && !this.G) || cVar.f != appBarLayout.getId()) {
                return false;
            }
            if (this.E == null) {
                this.E = new Rect();
            }
            Rect rect = this.E;
            bo.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                ExtendedFloatingActionButton.e(extendedFloatingActionButton, this.G ? 2 : 1);
            } else {
                ExtendedFloatingActionButton.e(extendedFloatingActionButton, this.G ? 3 : 0);
            }
            return true;
        }

        public final boolean x(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            c cVar = (c) extendedFloatingActionButton.getLayoutParams();
            if ((!this.F && !this.G) || cVar.f != view.getId()) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((c) extendedFloatingActionButton.getLayoutParams())).topMargin) {
                ExtendedFloatingActionButton.e(extendedFloatingActionButton, this.G ? 2 : 1);
            } else {
                ExtendedFloatingActionButton.e(extendedFloatingActionButton, this.G ? 3 : 0);
            }
            return true;
        }

        public ExtendedFloatingActionButtonBehavior() {
            this.F = false;
            this.G = true;
        }
    }
}
