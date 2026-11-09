package com.google.android.material.card;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import androidx.cardview.widget.CardView;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.ad0;
import defpackage.fa0;
import defpackage.gp0;
import defpackage.i01;
import defpackage.ip;
import defpackage.kv0;
import defpackage.la0;
import defpackage.ld0;
import defpackage.ma0;
import defpackage.pd0;
import defpackage.qm;
import defpackage.t40;
import defpackage.yc0;
import defpackage.zu0;

/* JADX INFO: loaded from: classes.dex */
public class MaterialCardView extends CardView implements Checkable, kv0 {
    public static final int[] P = {R.attr.state_checkable};
    public static final int[] Q = {R.attr.state_checked};
    public static final int[] R = {R$attr.state_dragged};
    public static final int S = R$style.Widget_MaterialComponents_CardView;
    public final ad0 L;
    public final boolean M;
    public boolean N;
    public boolean O;

    public MaterialCardView(Context context) {
        this(context, null);
    }

    private RectF getBoundsAsRectF() {
        RectF rectF = new RectF();
        rectF.set(this.L.c.getBounds());
        return rectF;
    }

    public final void b() {
        ad0 ad0Var;
        RippleDrawable rippleDrawable;
        if (Build.VERSION.SDK_INT <= 26 || (rippleDrawable = (ad0Var = this.L).o) == null) {
            return;
        }
        Rect bounds = rippleDrawable.getBounds();
        int i = bounds.bottom;
        ad0Var.o.setBounds(bounds.left, bounds.top, bounds.right, i - 1);
        ad0Var.o.setBounds(bounds.left, bounds.top, bounds.right, i);
    }

    @Override // androidx.cardview.widget.CardView
    public ColorStateList getCardBackgroundColor() {
        return this.L.c.E.c;
    }

    public ColorStateList getCardForegroundColor() {
        return this.L.d.E.c;
    }

    public float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        return this.L.j;
    }

    public int getCheckedIconGravity() {
        return this.L.g;
    }

    public int getCheckedIconMargin() {
        return this.L.e;
    }

    public int getCheckedIconSize() {
        return this.L.f;
    }

    public ColorStateList getCheckedIconTint() {
        return this.L.l;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        return this.L.b.bottom;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        return this.L.b.left;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        return this.L.b.right;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        return this.L.b.top;
    }

    public float getProgress() {
        return this.L.c.E.i;
    }

    @Override // androidx.cardview.widget.CardView
    public float getRadius() {
        return this.L.c.i();
    }

    public ColorStateList getRippleColor() {
        return this.L.k;
    }

    public zu0 getShapeAppearanceModel() {
        return this.L.m;
    }

    @Deprecated
    public int getStrokeColor() {
        ColorStateList colorStateList = this.L.n;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    public ColorStateList getStrokeColorStateList() {
        return this.L.n;
    }

    public int getStrokeWidth() {
        return this.L.h;
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.N;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ad0 ad0Var = this.L;
        ad0Var.k();
        ma0.o0(this, ad0Var.c);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 3);
        ad0 ad0Var = this.L;
        if (ad0Var != null && ad0Var.s) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, P);
        }
        if (this.N) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, Q);
        }
        if (this.O) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, R);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.cardview.widget.CardView");
        accessibilityEvent.setChecked(this.N);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.cardview.widget.CardView");
        ad0 ad0Var = this.L;
        accessibilityNodeInfo.setCheckable(ad0Var != null && ad0Var.s);
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(this.N);
    }

    @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.L.e(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.M) {
            ad0 ad0Var = this.L;
            if (!ad0Var.r) {
                Log.i("MaterialCardView", "Setting a custom background is not supported.");
                ad0Var.r = true;
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        this.L.c.m(colorStateList);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardElevation(float f) {
        super.setCardElevation(f);
        ad0 ad0Var = this.L;
        ad0Var.c.l(ad0Var.a.getCardElevation());
    }

    public void setCardForegroundColor(ColorStateList colorStateList) {
        ld0 ld0Var = this.L.d;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        ld0Var.m(colorStateList);
    }

    public void setCheckable(boolean z) {
        this.L.s = z;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.N != z) {
            toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        this.L.g(drawable);
    }

    public void setCheckedIconGravity(int i) {
        ad0 ad0Var = this.L;
        if (ad0Var.g != i) {
            ad0Var.g = i;
            MaterialCardView materialCardView = ad0Var.a;
            ad0Var.e(materialCardView.getMeasuredWidth(), materialCardView.getMeasuredHeight());
        }
    }

    public void setCheckedIconMargin(int i) {
        this.L.e = i;
    }

    public void setCheckedIconMarginResource(int i) {
        if (i != -1) {
            this.L.e = getResources().getDimensionPixelSize(i);
        }
    }

    public void setCheckedIconResource(int i) {
        this.L.g(qm.z(getContext(), i));
    }

    public void setCheckedIconSize(int i) {
        this.L.f = i;
    }

    public void setCheckedIconSizeResource(int i) {
        if (i != 0) {
            this.L.f = getResources().getDimensionPixelSize(i);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        ad0 ad0Var = this.L;
        ad0Var.l = colorStateList;
        Drawable drawable = ad0Var.j;
        if (drawable != null) {
            ip.h(drawable, colorStateList);
        }
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        ad0 ad0Var = this.L;
        if (ad0Var != null) {
            ad0Var.k();
        }
    }

    public void setDragged(boolean z) {
        if (this.O != z) {
            this.O = z;
            refreshDrawableState();
            b();
            invalidate();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float f) {
        super.setMaxCardElevation(f);
        this.L.m();
    }

    @Override // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean z) {
        super.setPreventCornerOverlap(z);
        ad0 ad0Var = this.L;
        ad0Var.m();
        ad0Var.l();
    }

    public void setProgress(float f) {
        ad0 ad0Var = this.L;
        ad0Var.c.n(f);
        ld0 ld0Var = ad0Var.d;
        if (ld0Var != null) {
            ld0Var.n(f);
        }
        ld0 ld0Var2 = ad0Var.q;
        if (ld0Var2 != null) {
            ld0Var2.n(f);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0038  */
    @Override // androidx.cardview.widget.CardView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setRadius(float r3) {
        /*
            r2 = this;
            super.setRadius(r3)
            ad0 r0 = r2.L
            zu0 r1 = r0.m
            xu0 r1 = r1.g()
            r1.c(r3)
            zu0 r3 = r1.a()
            r0.h(r3)
            android.graphics.drawable.Drawable r3 = r0.i
            r3.invalidateSelf()
            boolean r3 = r0.i()
            if (r3 != 0) goto L38
            com.google.android.material.card.MaterialCardView r3 = r0.a
            boolean r3 = r3.getPreventCornerOverlap()
            if (r3 == 0) goto L3b
            ld0 r3 = r0.c
            kd0 r1 = r3.E
            zu0 r1 = r1.a
            android.graphics.RectF r3 = r3.g()
            boolean r3 = r1.f(r3)
            if (r3 != 0) goto L3b
        L38:
            r0.l()
        L3b:
            boolean r3 = r0.i()
            if (r3 == 0) goto L44
            r0.m()
        L44:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.card.MaterialCardView.setRadius(float):void");
    }

    public void setRippleColor(ColorStateList colorStateList) {
        ad0 ad0Var = this.L;
        ad0Var.k = colorStateList;
        int[] iArr = gp0.a;
        RippleDrawable rippleDrawable = ad0Var.o;
        if (rippleDrawable != null) {
            rippleDrawable.setColor(colorStateList);
        }
    }

    public void setRippleColorResource(int i) {
        ColorStateList colorStateListY = t40.Y(getContext(), i);
        ad0 ad0Var = this.L;
        ad0Var.k = colorStateListY;
        int[] iArr = gp0.a;
        RippleDrawable rippleDrawable = ad0Var.o;
        if (rippleDrawable != null) {
            rippleDrawable.setColor(colorStateListY);
        }
    }

    @Override // defpackage.kv0
    public void setShapeAppearanceModel(zu0 zu0Var) {
        setClipToOutline(zu0Var.f(getBoundsAsRectF()));
        this.L.h(zu0Var);
    }

    public void setStrokeColor(int i) {
        setStrokeColor(ColorStateList.valueOf(i));
    }

    public void setStrokeWidth(int i) {
        ad0 ad0Var = this.L;
        if (i != ad0Var.h) {
            ad0Var.h = i;
            ld0 ld0Var = ad0Var.d;
            ColorStateList colorStateList = ad0Var.n;
            ld0Var.s(i);
            ld0Var.r(colorStateList);
        }
        invalidate();
    }

    @Override // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean z) {
        super.setUseCompatPadding(z);
        ad0 ad0Var = this.L;
        ad0Var.m();
        ad0Var.l();
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        ad0 ad0Var = this.L;
        if (ad0Var != null && ad0Var.s && isEnabled()) {
            this.N = !this.N;
            refreshDrawableState();
            b();
            ad0Var.f(this.N, true);
        }
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialCardViewStyle);
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        ad0 ad0Var = this.L;
        if (ad0Var.n != colorStateList) {
            ad0Var.n = colorStateList;
            ld0 ld0Var = ad0Var.d;
            ld0Var.s(ad0Var.h);
            ld0Var.r(colorStateList);
        }
        invalidate();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        int i2 = S;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.N = false;
        this.O = false;
        this.M = true;
        TypedArray typedArrayD = i01.d(getContext(), attributeSet, R$styleable.MaterialCardView, i, i2, new int[0]);
        ad0 ad0Var = new ad0(this, attributeSet, i);
        this.L = ad0Var;
        ColorStateList cardBackgroundColor = super.getCardBackgroundColor();
        ld0 ld0Var = ad0Var.c;
        ld0Var.m(cardBackgroundColor);
        ad0Var.b.set(super.getContentPaddingLeft(), super.getContentPaddingTop(), super.getContentPaddingRight(), super.getContentPaddingBottom());
        ad0Var.l();
        MaterialCardView materialCardView = ad0Var.a;
        ColorStateList colorStateListP = la0.p(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_strokeColor);
        ad0Var.n = colorStateListP;
        if (colorStateListP == null) {
            ad0Var.n = ColorStateList.valueOf(-1);
        }
        ad0Var.h = typedArrayD.getDimensionPixelSize(R$styleable.MaterialCardView_strokeWidth, 0);
        boolean z = typedArrayD.getBoolean(R$styleable.MaterialCardView_android_checkable, false);
        ad0Var.s = z;
        materialCardView.setLongClickable(z);
        ad0Var.l = la0.p(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_checkedIconTint);
        ad0Var.g(la0.s(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_checkedIcon));
        ad0Var.f = typedArrayD.getDimensionPixelSize(R$styleable.MaterialCardView_checkedIconSize, 0);
        ad0Var.e = typedArrayD.getDimensionPixelSize(R$styleable.MaterialCardView_checkedIconMargin, 0);
        ad0Var.g = typedArrayD.getInteger(R$styleable.MaterialCardView_checkedIconGravity, 8388661);
        ColorStateList colorStateListP2 = la0.p(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_rippleColor);
        ad0Var.k = colorStateListP2;
        if (colorStateListP2 == null) {
            ad0Var.k = ColorStateList.valueOf(fa0.P(materialCardView, R$attr.colorControlHighlight));
        }
        ColorStateList colorStateListP3 = la0.p(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_cardForegroundColor);
        ld0 ld0Var2 = ad0Var.d;
        ld0Var2.m(colorStateListP3 == null ? ColorStateList.valueOf(0) : colorStateListP3);
        int[] iArr = gp0.a;
        RippleDrawable rippleDrawable = ad0Var.o;
        if (rippleDrawable != null) {
            rippleDrawable.setColor(ad0Var.k);
        }
        ld0Var.l(materialCardView.getCardElevation());
        float f = ad0Var.h;
        ColorStateList colorStateList = ad0Var.n;
        ld0Var2.s(f);
        ld0Var2.r(colorStateList);
        materialCardView.setBackgroundInternal(ad0Var.d(ld0Var));
        Drawable drawableC = ad0Var.j() ? ad0Var.c() : ld0Var2;
        ad0Var.i = drawableC;
        materialCardView.setForeground(ad0Var.d(drawableC));
        typedArrayD.recycle();
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(int i) {
        this.L.c.m(ColorStateList.valueOf(i));
    }

    public void setOnCheckedChangeListener(yc0 yc0Var) {
    }
}
