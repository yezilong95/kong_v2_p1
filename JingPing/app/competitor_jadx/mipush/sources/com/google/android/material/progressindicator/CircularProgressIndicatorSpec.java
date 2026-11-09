package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$styleable;
import defpackage.aa;
import defpackage.i01;
import defpackage.la0;

/* JADX INFO: loaded from: classes.dex */
public final class CircularProgressIndicatorSpec extends aa {
    public int h;
    public int i;
    public int j;

    public CircularProgressIndicatorSpec(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.circularProgressIndicatorStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CircularProgressIndicatorSpec(Context context, AttributeSet attributeSet, int i) {
        int i2 = CircularProgressIndicator.Q;
        super(context, attributeSet, i, i2);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.mtrl_progress_circular_size_medium);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R$dimen.mtrl_progress_circular_inset_medium);
        int[] iArr = R$styleable.CircularProgressIndicator;
        i01.a(context, attributeSet, i, i2);
        i01.b(context, attributeSet, iArr, i, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, i2);
        this.h = Math.max(la0.r(context, typedArrayObtainStyledAttributes, R$styleable.CircularProgressIndicator_indicatorSize, dimensionPixelSize), this.a * 2);
        this.i = la0.r(context, typedArrayObtainStyledAttributes, R$styleable.CircularProgressIndicator_indicatorInset, dimensionPixelSize2);
        this.j = typedArrayObtainStyledAttributes.getInt(R$styleable.CircularProgressIndicator_indicatorDirectionCircular, 0);
        typedArrayObtainStyledAttributes.recycle();
        a();
    }
}
