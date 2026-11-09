package com.google.android.material.divider;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.f51;
import defpackage.i01;
import defpackage.la0;
import defpackage.ld0;
import defpackage.pd0;
import defpackage.qi;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialDivider extends View {
    public static final int J = R$style.Widget_MaterialComponents_MaterialDivider;
    public final ld0 E;
    public int F;
    public int G;
    public int H;
    public int I;

    public MaterialDivider(Context context) {
        this(context, null);
    }

    public int getDividerColor() {
        return this.G;
    }

    public int getDividerInsetEnd() {
        return this.I;
    }

    public int getDividerInsetStart() {
        return this.H;
    }

    public int getDividerThickness() {
        return this.F;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int width;
        int i;
        super.onDraw(canvas);
        WeakHashMap weakHashMap = f51.a;
        boolean z = getLayoutDirection() == 1;
        int i2 = z ? this.I : this.H;
        if (z) {
            width = getWidth();
            i = this.H;
        } else {
            width = getWidth();
            i = this.I;
        }
        int i3 = width - i;
        ld0 ld0Var = this.E;
        ld0Var.setBounds(i2, 0, i3, getBottom() - getTop());
        ld0Var.draw(canvas);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        int measuredHeight = getMeasuredHeight();
        if (mode == Integer.MIN_VALUE || mode == 0) {
            int i3 = this.F;
            if (i3 > 0 && measuredHeight != i3) {
                measuredHeight = i3;
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
    }

    public void setDividerColor(int i) {
        if (this.G != i) {
            this.G = i;
            this.E.m(ColorStateList.valueOf(i));
            invalidate();
        }
    }

    public void setDividerColorResource(int i) {
        setDividerColor(qi.a(getContext(), i));
    }

    public void setDividerInsetEnd(int i) {
        this.I = i;
    }

    public void setDividerInsetEndResource(int i) {
        setDividerInsetEnd(getContext().getResources().getDimensionPixelOffset(i));
    }

    public void setDividerInsetStart(int i) {
        this.H = i;
    }

    public void setDividerInsetStartResource(int i) {
        setDividerInsetStart(getContext().getResources().getDimensionPixelOffset(i));
    }

    public void setDividerThickness(int i) {
        if (this.F != i) {
            this.F = i;
            requestLayout();
        }
    }

    public void setDividerThicknessResource(int i) {
        setDividerThickness(getContext().getResources().getDimensionPixelSize(i));
    }

    public MaterialDivider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialDividerStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialDivider(Context context, AttributeSet attributeSet, int i) {
        int i2 = J;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        this.E = new ld0();
        TypedArray typedArrayD = i01.d(context2, attributeSet, R$styleable.MaterialDivider, i, i2, new int[0]);
        this.F = typedArrayD.getDimensionPixelSize(R$styleable.MaterialDivider_dividerThickness, getResources().getDimensionPixelSize(R$dimen.material_divider_thickness));
        this.H = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialDivider_dividerInsetStart, 0);
        this.I = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialDivider_dividerInsetEnd, 0);
        setDividerColor(la0.p(context2, typedArrayD, R$styleable.MaterialDivider_dividerColor).getDefaultColor());
        typedArrayD.recycle();
    }
}
