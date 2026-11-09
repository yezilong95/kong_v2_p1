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
import defpackage.bh;
import defpackage.u50;
import defpackage.un0;
import defpackage.xi;
import defpackage.xr0;
import defpackage.y50;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialDivider extends View {
    public static final int g = R$style.Widget_MaterialComponents_MaterialDivider;
    public final u50 b;
    public int c;
    public int d;
    public int e;
    public int f;

    public MaterialDivider(Context context) {
        this(context, null);
    }

    public int getDividerColor() {
        return this.d;
    }

    public int getDividerInsetEnd() {
        return this.f;
    }

    public int getDividerInsetStart() {
        return this.e;
    }

    public int getDividerThickness() {
        return this.c;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int width;
        int i;
        super.onDraw(canvas);
        WeakHashMap weakHashMap = xr0.a;
        boolean z = getLayoutDirection() == 1;
        int i2 = z ? this.f : this.e;
        if (z) {
            width = getWidth();
            i = this.e;
        } else {
            width = getWidth();
            i = this.f;
        }
        int i3 = width - i;
        u50 u50Var = this.b;
        u50Var.setBounds(i2, 0, i3, getBottom() - getTop());
        u50Var.draw(canvas);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        int measuredHeight = getMeasuredHeight();
        if (mode == Integer.MIN_VALUE || mode == 0) {
            int i3 = this.c;
            if (i3 > 0 && measuredHeight != i3) {
                measuredHeight = i3;
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
    }

    public void setDividerColor(int i) {
        if (this.d != i) {
            this.d = i;
            this.b.m(ColorStateList.valueOf(i));
            invalidate();
        }
    }

    public void setDividerColorResource(int i) {
        setDividerColor(bh.a(getContext(), i));
    }

    public void setDividerInsetEnd(int i) {
        this.f = i;
    }

    public void setDividerInsetEndResource(int i) {
        setDividerInsetEnd(getContext().getResources().getDimensionPixelOffset(i));
    }

    public void setDividerInsetStart(int i) {
        this.e = i;
    }

    public void setDividerInsetStartResource(int i) {
        setDividerInsetStart(getContext().getResources().getDimensionPixelOffset(i));
    }

    public void setDividerThickness(int i) {
        if (this.c != i) {
            this.c = i;
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
        int i2 = g;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        this.b = new u50();
        TypedArray typedArrayD = un0.d(context2, attributeSet, R$styleable.MaterialDivider, i, i2, new int[0]);
        this.c = typedArrayD.getDimensionPixelSize(R$styleable.MaterialDivider_dividerThickness, getResources().getDimensionPixelSize(R$dimen.material_divider_thickness));
        this.e = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialDivider_dividerInsetStart, 0);
        this.f = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialDivider_dividerInsetEnd, 0);
        setDividerColor(xi.s(context2, typedArrayD, R$styleable.MaterialDivider_dividerColor).getDefaultColor());
        typedArrayD.recycle();
    }
}
