package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$styleable;
import defpackage.e8;
import defpackage.un0;
import defpackage.xi;

/* JADX INFO: loaded from: classes.dex */
public final class CircularProgressIndicatorSpec extends e8 {
    public int g;
    public int h;
    public int i;

    public CircularProgressIndicatorSpec(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.circularProgressIndicatorStyle);
    }

    @Override // defpackage.e8
    public final void a() {
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CircularProgressIndicatorSpec(Context context, AttributeSet attributeSet, int i) {
        int i2 = CircularProgressIndicator.n;
        super(context, attributeSet, i, i2);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.mtrl_progress_circular_size_medium);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R$dimen.mtrl_progress_circular_inset_medium);
        int[] iArr = R$styleable.CircularProgressIndicator;
        un0.a(context, attributeSet, i, i2);
        un0.b(context, attributeSet, iArr, i, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, i2);
        this.g = Math.max(xi.v(context, typedArrayObtainStyledAttributes, R$styleable.CircularProgressIndicator_indicatorSize, dimensionPixelSize), this.a * 2);
        this.h = xi.v(context, typedArrayObtainStyledAttributes, R$styleable.CircularProgressIndicator_indicatorInset, dimensionPixelSize2);
        this.i = typedArrayObtainStyledAttributes.getInt(R$styleable.CircularProgressIndicator_indicatorDirectionCircular, 0);
        typedArrayObtainStyledAttributes.recycle();
    }
}
