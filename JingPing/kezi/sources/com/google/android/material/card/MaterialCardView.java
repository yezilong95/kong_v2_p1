package com.google.android.material.card;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
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
import defpackage.hl;
import defpackage.i50;
import defpackage.k50;
import defpackage.k9;
import defpackage.ki0;
import defpackage.lf0;
import defpackage.oy;
import defpackage.rp0;
import defpackage.u50;
import defpackage.un0;
import defpackage.vi0;
import defpackage.xi;
import defpackage.y50;

/* JADX INFO: loaded from: classes.dex */
public class MaterialCardView extends CardView implements Checkable, vi0 {
    public static final int[] m = {R.attr.state_checkable};
    public static final int[] n = {R.attr.state_checked};
    public static final int[] o = {R$attr.state_dragged};
    public static final int p = R$style.Widget_MaterialComponents_CardView;
    public final k50 i;
    public final boolean j;
    public boolean k;
    public boolean l;

    public MaterialCardView(Context context) {
        this(context, null);
    }

    private RectF getBoundsAsRectF() {
        RectF rectF = new RectF();
        rectF.set(this.i.c.getBounds());
        return rectF;
    }

    public final void c() {
        k50 k50Var;
        RippleDrawable rippleDrawable;
        if (Build.VERSION.SDK_INT <= 26 || (rippleDrawable = (k50Var = this.i).o) == null) {
            return;
        }
        Rect bounds = rippleDrawable.getBounds();
        int i = bounds.bottom;
        k50Var.o.setBounds(bounds.left, bounds.top, bounds.right, i - 1);
        k50Var.o.setBounds(bounds.left, bounds.top, bounds.right, i);
    }

    @Override // androidx.cardview.widget.CardView
    public ColorStateList getCardBackgroundColor() {
        return this.i.c.b.c;
    }

    public ColorStateList getCardForegroundColor() {
        return this.i.d.b.c;
    }

    public float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        return this.i.j;
    }

    public int getCheckedIconGravity() {
        return this.i.g;
    }

    public int getCheckedIconMargin() {
        return this.i.e;
    }

    public int getCheckedIconSize() {
        return this.i.f;
    }

    public ColorStateList getCheckedIconTint() {
        return this.i.l;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        return this.i.b.bottom;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        return this.i.b.left;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        return this.i.b.right;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        return this.i.b.top;
    }

    public float getProgress() {
        return this.i.c.b.i;
    }

    @Override // androidx.cardview.widget.CardView
    public float getRadius() {
        return this.i.c.i();
    }

    public ColorStateList getRippleColor() {
        return this.i.k;
    }

    public ki0 getShapeAppearanceModel() {
        return this.i.m;
    }

    @Deprecated
    public int getStrokeColor() {
        ColorStateList colorStateList = this.i.n;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    public ColorStateList getStrokeColorStateList() {
        return this.i.n;
    }

    public int getStrokeWidth() {
        return this.i.h;
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.k;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oy.O(this, this.i.c);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 3);
        k50 k50Var = this.i;
        if (k50Var != null && k50Var.s) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, m);
        }
        if (this.k) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, n);
        }
        if (this.l) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, o);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.cardview.widget.CardView");
        accessibilityEvent.setChecked(this.k);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.cardview.widget.CardView");
        k50 k50Var = this.i;
        accessibilityNodeInfo.setCheckable(k50Var != null && k50Var.s);
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(this.k);
    }

    @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.i.e(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.j) {
            k50 k50Var = this.i;
            if (!k50Var.r) {
                Log.i("MaterialCardView", "Setting a custom background is not supported.");
                k50Var.r = true;
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        this.i.c.m(colorStateList);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardElevation(float f) {
        super.setCardElevation(f);
        k50 k50Var = this.i;
        k50Var.c.l(k50Var.a.getCardElevation());
    }

    public void setCardForegroundColor(ColorStateList colorStateList) {
        u50 u50Var = this.i.d;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        u50Var.m(colorStateList);
    }

    public void setCheckable(boolean z) {
        this.i.s = z;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.k != z) {
            toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        this.i.g(drawable);
    }

    public void setCheckedIconGravity(int i) {
        k50 k50Var = this.i;
        if (k50Var.g != i) {
            k50Var.g = i;
            MaterialCardView materialCardView = k50Var.a;
            k50Var.e(materialCardView.getMeasuredWidth(), materialCardView.getMeasuredHeight());
        }
    }

    public void setCheckedIconMargin(int i) {
        this.i.e = i;
    }

    public void setCheckedIconMarginResource(int i) {
        if (i != -1) {
            this.i.e = getResources().getDimensionPixelSize(i);
        }
    }

    public void setCheckedIconResource(int i) {
        this.i.g(rp0.y(getContext(), i));
    }

    public void setCheckedIconSize(int i) {
        this.i.f = i;
    }

    public void setCheckedIconSizeResource(int i) {
        if (i != 0) {
            this.i.f = getResources().getDimensionPixelSize(i);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        k50 k50Var = this.i;
        k50Var.l = colorStateList;
        Drawable drawable = k50Var.j;
        if (drawable != null) {
            hl.h(drawable, colorStateList);
        }
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        k50 k50Var = this.i;
        if (k50Var != null) {
            Drawable drawable = k50Var.i;
            MaterialCardView materialCardView = k50Var.a;
            Drawable drawableC = materialCardView.isClickable() ? k50Var.c() : k50Var.d;
            k50Var.i = drawableC;
            if (drawable != drawableC) {
                if (materialCardView.getForeground() instanceof InsetDrawable) {
                    ((InsetDrawable) materialCardView.getForeground()).setDrawable(drawableC);
                } else {
                    materialCardView.setForeground(k50Var.d(drawableC));
                }
            }
        }
    }

    public void setDragged(boolean z) {
        if (this.l != z) {
            this.l = z;
            refreshDrawableState();
            c();
            invalidate();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float f) {
        super.setMaxCardElevation(f);
        this.i.k();
    }

    @Override // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean z) {
        super.setPreventCornerOverlap(z);
        k50 k50Var = this.i;
        k50Var.k();
        k50Var.j();
    }

    public void setProgress(float f) {
        k50 k50Var = this.i;
        k50Var.c.n(f);
        u50 u50Var = k50Var.d;
        if (u50Var != null) {
            u50Var.n(f);
        }
        u50 u50Var2 = k50Var.q;
        if (u50Var2 != null) {
            u50Var2.n(f);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0038  */
    @Override // androidx.cardview.widget.CardView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setRadius(float r3) {
        /*
            r2 = this;
            super.setRadius(r3)
            k50 r0 = r2.i
            ki0 r1 = r0.m
            ii0 r1 = r1.g()
            r1.c(r3)
            ki0 r3 = r1.a()
            r0.h(r3)
            android.graphics.drawable.Drawable r3 = r0.i
            r3.invalidateSelf()
            boolean r3 = r0.i()
            if (r3 != 0) goto L38
            com.google.android.material.card.MaterialCardView r3 = r0.a
            boolean r3 = r3.getPreventCornerOverlap()
            if (r3 == 0) goto L3b
            u50 r3 = r0.c
            t50 r1 = r3.b
            ki0 r1 = r1.a
            android.graphics.RectF r3 = r3.g()
            boolean r3 = r1.f(r3)
            if (r3 != 0) goto L3b
        L38:
            r0.j()
        L3b:
            boolean r3 = r0.i()
            if (r3 == 0) goto L44
            r0.k()
        L44:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.card.MaterialCardView.setRadius(float):void");
    }

    public void setRippleColor(ColorStateList colorStateList) {
        k50 k50Var = this.i;
        k50Var.k = colorStateList;
        int[] iArr = lf0.a;
        RippleDrawable rippleDrawable = k50Var.o;
        if (rippleDrawable != null) {
            rippleDrawable.setColor(colorStateList);
        }
    }

    public void setRippleColorResource(int i) {
        ColorStateList colorStateListW = rp0.w(getContext(), i);
        k50 k50Var = this.i;
        k50Var.k = colorStateListW;
        int[] iArr = lf0.a;
        RippleDrawable rippleDrawable = k50Var.o;
        if (rippleDrawable != null) {
            rippleDrawable.setColor(colorStateListW);
        }
    }

    @Override // defpackage.vi0
    public void setShapeAppearanceModel(ki0 ki0Var) {
        setClipToOutline(ki0Var.f(getBoundsAsRectF()));
        this.i.h(ki0Var);
    }

    public void setStrokeColor(int i) {
        setStrokeColor(ColorStateList.valueOf(i));
    }

    public void setStrokeWidth(int i) {
        k50 k50Var = this.i;
        if (i != k50Var.h) {
            k50Var.h = i;
            u50 u50Var = k50Var.d;
            ColorStateList colorStateList = k50Var.n;
            u50Var.s(i);
            u50Var.r(colorStateList);
        }
        invalidate();
    }

    @Override // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean z) {
        super.setUseCompatPadding(z);
        k50 k50Var = this.i;
        k50Var.k();
        k50Var.j();
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        k50 k50Var = this.i;
        if (k50Var != null && k50Var.s && isEnabled()) {
            this.k = !this.k;
            refreshDrawableState();
            c();
            k50Var.f(this.k, true);
        }
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialCardViewStyle);
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        k50 k50Var = this.i;
        if (k50Var.n != colorStateList) {
            k50Var.n = colorStateList;
            u50 u50Var = k50Var.d;
            u50Var.s(k50Var.h);
            u50Var.r(colorStateList);
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
        int i2 = p;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.k = false;
        this.l = false;
        this.j = true;
        TypedArray typedArrayD = un0.d(getContext(), attributeSet, R$styleable.MaterialCardView, i, i2, new int[0]);
        k50 k50Var = new k50(this, attributeSet, i);
        this.i = k50Var;
        ColorStateList cardBackgroundColor = super.getCardBackgroundColor();
        u50 u50Var = k50Var.c;
        u50Var.m(cardBackgroundColor);
        k50Var.b.set(super.getContentPaddingLeft(), super.getContentPaddingTop(), super.getContentPaddingRight(), super.getContentPaddingBottom());
        k50Var.j();
        MaterialCardView materialCardView = k50Var.a;
        ColorStateList colorStateListS = xi.s(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_strokeColor);
        k50Var.n = colorStateListS;
        if (colorStateListS == null) {
            k50Var.n = ColorStateList.valueOf(-1);
        }
        k50Var.h = typedArrayD.getDimensionPixelSize(R$styleable.MaterialCardView_strokeWidth, 0);
        boolean z = typedArrayD.getBoolean(R$styleable.MaterialCardView_android_checkable, false);
        k50Var.s = z;
        materialCardView.setLongClickable(z);
        k50Var.l = xi.s(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_checkedIconTint);
        k50Var.g(xi.x(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_checkedIcon));
        k50Var.f = typedArrayD.getDimensionPixelSize(R$styleable.MaterialCardView_checkedIconSize, 0);
        k50Var.e = typedArrayD.getDimensionPixelSize(R$styleable.MaterialCardView_checkedIconMargin, 0);
        k50Var.g = typedArrayD.getInteger(R$styleable.MaterialCardView_checkedIconGravity, 8388661);
        ColorStateList colorStateListS2 = xi.s(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_rippleColor);
        k50Var.k = colorStateListS2;
        if (colorStateListS2 == null) {
            k50Var.k = ColorStateList.valueOf(k9.q(materialCardView, R$attr.colorControlHighlight));
        }
        ColorStateList colorStateListS3 = xi.s(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_cardForegroundColor);
        u50 u50Var2 = k50Var.d;
        u50Var2.m(colorStateListS3 == null ? ColorStateList.valueOf(0) : colorStateListS3);
        int[] iArr = lf0.a;
        RippleDrawable rippleDrawable = k50Var.o;
        if (rippleDrawable != null) {
            rippleDrawable.setColor(k50Var.k);
        }
        u50Var.l(materialCardView.getCardElevation());
        float f = k50Var.h;
        ColorStateList colorStateList = k50Var.n;
        u50Var2.s(f);
        u50Var2.r(colorStateList);
        materialCardView.setBackgroundInternal(k50Var.d(u50Var));
        Drawable drawableC = materialCardView.isClickable() ? k50Var.c() : u50Var2;
        k50Var.i = drawableC;
        materialCardView.setForeground(k50Var.d(drawableC));
        typedArrayD.recycle();
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(int i) {
        this.i.c.m(ColorStateList.valueOf(i));
    }

    public void setOnCheckedChangeListener(i50 i50Var) {
    }
}
