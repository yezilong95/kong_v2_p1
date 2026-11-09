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
import defpackage.bp;
import defpackage.cp;
import defpackage.dp;
import defpackage.e5;
import defpackage.ep;
import defpackage.fb;
import defpackage.jk;
import defpackage.ki0;
import defpackage.po;
import defpackage.un0;
import defpackage.v3;
import defpackage.x70;
import defpackage.xr0;
import defpackage.y50;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements a {
    public static final int K = R$style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    public static final fb L;
    public static final fb M;
    public static final fb N;
    public static final fb O;
    public final int A;
    public int B;
    public int C;
    public final ExtendedFloatingActionButtonBehavior D;
    public boolean E;
    public boolean F;
    public boolean G;
    public ColorStateList H;
    public int I;
    public int J;
    public int v;
    public final cp w;
    public final cp x;
    public final ep y;
    public final dp z;

    static {
        Class<Float> cls = Float.class;
        L = new fb(cls, "width", 11);
        M = new fb(cls, "height", 12);
        N = new fb(cls, "paddingStart", 13);
        O = new fb(cls, "paddingEnd", 14);
    }

    public ExtendedFloatingActionButton(Context context) {
        this(context, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
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
            cp r2 = r4.x
            goto L22
        Le:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "Unknown strategy type: "
            java.lang.String r5 = defpackage.hk.g(r5, r0)
            r4.<init>(r5)
            throw r4
        L1a:
            cp r2 = r4.w
            goto L22
        L1d:
            dp r2 = r4.z
            goto L22
        L20:
            ep r2 = r4.y
        L22:
            boolean r3 = r2.h()
            if (r3 == 0) goto L29
            return
        L29:
            java.util.WeakHashMap r3 = defpackage.xr0.a
            boolean r3 = r4.isLaidOut()
            if (r3 != 0) goto L45
            int r3 = r4.getVisibility()
            if (r3 == 0) goto L3c
            int r0 = r4.v
            if (r0 != r1) goto L41
            goto L93
        L3c:
            int r3 = r4.v
            if (r3 == r0) goto L41
            goto L93
        L41:
            boolean r0 = r4.G
            if (r0 == 0) goto L93
        L45:
            boolean r0 = r4.isInEditMode()
            if (r0 != 0) goto L93
            if (r5 != r1) goto L68
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            if (r5 == 0) goto L5c
            int r0 = r5.width
            r4.I = r0
            int r5 = r5.height
            r4.J = r5
            goto L68
        L5c:
            int r5 = r4.getWidth()
            r4.I = r5
            int r5 = r4.getHeight()
            r4.J = r5
        L68:
            r5 = 0
            r4.measure(r5, r5)
            android.animation.AnimatorSet r4 = r2.a()
            d1 r5 = new d1
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
        return this.D;
    }

    public int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    public int getCollapsedSize() {
        int i = this.A;
        if (i >= 0) {
            return i;
        }
        WeakHashMap weakHashMap = xr0.a;
        return getIconSize() + (Math.min(getPaddingStart(), getPaddingEnd()) * 2);
    }

    public x70 getExtendMotionSpec() {
        return this.x.f;
    }

    public x70 getHideMotionSpec() {
        return this.z.f;
    }

    public x70 getShowMotionSpec() {
        return this.y.f;
    }

    public x70 getShrinkMotionSpec() {
        return this.w.f;
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.E && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.E = false;
            this.w.g();
        }
    }

    public void setAnimateShowBeforeLayout(boolean z) {
        this.G = z;
    }

    public void setExtendMotionSpec(x70 x70Var) {
        this.x.f = x70Var;
    }

    public void setExtendMotionSpecResource(int i) {
        setExtendMotionSpec(x70.b(getContext(), i));
    }

    public void setExtended(boolean z) {
        if (this.E == z) {
            return;
        }
        cp cpVar = z ? this.x : this.w;
        if (cpVar.h()) {
            return;
        }
        cpVar.g();
    }

    public void setHideMotionSpec(x70 x70Var) {
        this.z.f = x70Var;
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(x70.b(getContext(), i));
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        if (!this.E || this.F) {
            return;
        }
        WeakHashMap weakHashMap = xr0.a;
        this.B = getPaddingStart();
        this.C = getPaddingEnd();
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(i, i2, i3, i4);
        if (!this.E || this.F) {
            return;
        }
        this.B = i;
        this.C = i3;
    }

    public void setShowMotionSpec(x70 x70Var) {
        this.y.f = x70Var;
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(x70.b(getContext(), i));
    }

    public void setShrinkMotionSpec(x70 x70Var) {
        this.w.f = x70Var;
    }

    public void setShrinkMotionSpecResource(int i) {
        setShrinkMotionSpec(x70.b(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setTextColor(int i) {
        super.setTextColor(i);
        this.H = getTextColors();
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.extendedFloatingActionButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = K;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.v = 0;
        v3 v3Var = new v3();
        ep epVar = new ep(this, v3Var);
        this.y = epVar;
        dp dpVar = new dp(this, v3Var);
        this.z = dpVar;
        this.E = true;
        this.F = false;
        this.G = false;
        Context context2 = getContext();
        this.D = new ExtendedFloatingActionButtonBehavior(context2, attributeSet);
        TypedArray typedArrayD = un0.d(context2, attributeSet, R$styleable.ExtendedFloatingActionButton, i, i2, new int[0]);
        x70 x70VarA = x70.a(context2, typedArrayD, R$styleable.ExtendedFloatingActionButton_showMotionSpec);
        x70 x70VarA2 = x70.a(context2, typedArrayD, R$styleable.ExtendedFloatingActionButton_hideMotionSpec);
        x70 x70VarA3 = x70.a(context2, typedArrayD, R$styleable.ExtendedFloatingActionButton_extendMotionSpec);
        x70 x70VarA4 = x70.a(context2, typedArrayD, R$styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        this.A = typedArrayD.getDimensionPixelSize(R$styleable.ExtendedFloatingActionButton_collapsedSize, -1);
        int i3 = typedArrayD.getInt(R$styleable.ExtendedFloatingActionButton_extendStrategy, 1);
        WeakHashMap weakHashMap = xr0.a;
        this.B = getPaddingStart();
        this.C = getPaddingEnd();
        v3 v3Var2 = new v3();
        bp bpVar = new bp(this, 1);
        e5 e5Var = new e5(this, 11, bpVar);
        cp cpVar = new cp(this, v3Var2, i3 != 1 ? i3 != 2 ? new po(this, e5Var, bpVar) : e5Var : bpVar, true);
        this.x = cpVar;
        cp cpVar2 = new cp(this, v3Var2, new bp(this, 0), false);
        this.w = cpVar2;
        epVar.f = x70VarA;
        dpVar.f = x70VarA2;
        cpVar.f = x70VarA3;
        cpVar2.f = x70VarA4;
        typedArrayD.recycle();
        setShapeAppearanceModel(ki0.d(context2, attributeSet, i, i2, ki0.m).a());
        this.H = getTextColors();
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        this.H = getTextColors();
    }

    public static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        public Rect b;
        public final boolean c;
        public final boolean d;

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.c = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
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
            if ((!this.c && !this.d) || cVar.f != appBarLayout.getId()) {
                return false;
            }
            if (this.b == null) {
                this.b = new Rect();
            }
            Rect rect = this.b;
            jk.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                ExtendedFloatingActionButton.e(extendedFloatingActionButton, this.d ? 2 : 1);
            } else {
                ExtendedFloatingActionButton.e(extendedFloatingActionButton, this.d ? 3 : 0);
            }
            return true;
        }

        public final boolean x(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            c cVar = (c) extendedFloatingActionButton.getLayoutParams();
            if ((!this.c && !this.d) || cVar.f != view.getId()) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((c) extendedFloatingActionButton.getLayoutParams())).topMargin) {
                ExtendedFloatingActionButton.e(extendedFloatingActionButton, this.d ? 2 : 1);
            } else {
                ExtendedFloatingActionButton.e(extendedFloatingActionButton, this.d ? 3 : 0);
            }
            return true;
        }

        public ExtendedFloatingActionButtonBehavior() {
            this.c = false;
            this.d = true;
        }
    }
}
