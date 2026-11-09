package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$styleable;
import defpackage.e8;
import defpackage.un0;

/* JADX INFO: loaded from: classes.dex */
public final class LinearProgressIndicatorSpec extends e8 {
    public int g;
    public int h;
    public boolean i;

    public LinearProgressIndicatorSpec(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.linearProgressIndicatorStyle);
    }

    @Override // defpackage.e8
    public final void a() {
        if (this.g == 0) {
            if (this.b > 0) {
                throw new IllegalArgumentException("Rounded corners are not supported in contiguous indeterminate animation.");
            }
            if (this.c.length < 3) {
                throw new IllegalArgumentException("Contiguous indeterminate animation must be used with 3 or more indicator colors.");
            }
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LinearProgressIndicatorSpec(Context context, AttributeSet attributeSet, int i) {
        int i2 = LinearProgressIndicator.n;
        super(context, attributeSet, i, i2);
        int[] iArr = R$styleable.LinearProgressIndicator;
        int i3 = R$attr.linearProgressIndicatorStyle;
        un0.a(context, attributeSet, i3, i2);
        un0.b(context, attributeSet, iArr, i3, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i3, i2);
        this.g = typedArrayObtainStyledAttributes.getInt(R$styleable.LinearProgressIndicator_indeterminateAnimationType, 1);
        this.h = typedArrayObtainStyledAttributes.getInt(R$styleable.LinearProgressIndicator_indicatorDirectionLinear, 0);
        typedArrayObtainStyledAttributes.recycle();
        a();
        this.i = this.h == 1;
    }
}
