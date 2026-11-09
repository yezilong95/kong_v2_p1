package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$styleable;
import defpackage.aa;
import defpackage.i01;

/* JADX INFO: loaded from: classes.dex */
public final class LinearProgressIndicatorSpec extends aa {
    public int h;
    public int i;
    public boolean j;
    public int k;

    public LinearProgressIndicatorSpec(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.linearProgressIndicatorStyle);
    }

    @Override // defpackage.aa
    public final void a() {
        super.a();
        if (this.k < 0) {
            throw new IllegalArgumentException("Stop indicator size must be >= 0.");
        }
        if (this.h == 0) {
            if (this.b > 0 && this.g == 0) {
                throw new IllegalArgumentException("Rounded corners without gap are not supported in contiguous indeterminate animation.");
            }
            if (this.c.length < 3) {
                throw new IllegalArgumentException("Contiguous indeterminate animation must be used with 3 or more indicator colors.");
            }
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LinearProgressIndicatorSpec(Context context, AttributeSet attributeSet, int i) {
        int i2 = LinearProgressIndicator.Q;
        super(context, attributeSet, i, i2);
        int[] iArr = R$styleable.LinearProgressIndicator;
        int i3 = R$attr.linearProgressIndicatorStyle;
        i01.a(context, attributeSet, i3, i2);
        i01.b(context, attributeSet, iArr, i3, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i3, i2);
        this.h = typedArrayObtainStyledAttributes.getInt(R$styleable.LinearProgressIndicator_indeterminateAnimationType, 1);
        this.i = typedArrayObtainStyledAttributes.getInt(R$styleable.LinearProgressIndicator_indicatorDirectionLinear, 0);
        this.k = Math.min(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.LinearProgressIndicator_trackStopIndicatorSize, 0), this.a);
        typedArrayObtainStyledAttributes.recycle();
        a();
        this.j = this.i == 1;
    }
}
