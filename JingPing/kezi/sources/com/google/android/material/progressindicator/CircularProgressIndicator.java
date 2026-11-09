package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import defpackage.e8;
import defpackage.mk;
import defpackage.tc;
import defpackage.vc;
import defpackage.vx;

/* JADX INFO: loaded from: classes.dex */
public final class CircularProgressIndicator extends BaseProgressIndicator<CircularProgressIndicatorSpec> {
    public static final int n = R$style.Widget_MaterialComponents_CircularProgressIndicator;

    public CircularProgressIndicator(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public final e8 a(Context context, AttributeSet attributeSet) {
        return new CircularProgressIndicatorSpec(context, attributeSet);
    }

    public int getIndicatorDirection() {
        return ((CircularProgressIndicatorSpec) this.b).i;
    }

    public int getIndicatorInset() {
        return ((CircularProgressIndicatorSpec) this.b).h;
    }

    public int getIndicatorSize() {
        return ((CircularProgressIndicatorSpec) this.b).g;
    }

    public void setIndicatorDirection(int i) {
        ((CircularProgressIndicatorSpec) this.b).i = i;
        invalidate();
    }

    public void setIndicatorInset(int i) {
        e8 e8Var = this.b;
        if (((CircularProgressIndicatorSpec) e8Var).h != i) {
            ((CircularProgressIndicatorSpec) e8Var).h = i;
            invalidate();
        }
    }

    public void setIndicatorSize(int i) {
        int iMax = Math.max(i, getTrackThickness() * 2);
        e8 e8Var = this.b;
        if (((CircularProgressIndicatorSpec) e8Var).g != iMax) {
            ((CircularProgressIndicatorSpec) e8Var).g = iMax;
            ((CircularProgressIndicatorSpec) e8Var).getClass();
            invalidate();
        }
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackThickness(int i) {
        super.setTrackThickness(i);
        ((CircularProgressIndicatorSpec) this.b).getClass();
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.circularProgressIndicatorStyle);
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, n);
        Context context2 = getContext();
        CircularProgressIndicatorSpec circularProgressIndicatorSpec = (CircularProgressIndicatorSpec) this.b;
        setIndeterminateDrawable(new vx(context2, circularProgressIndicatorSpec, new tc(circularProgressIndicatorSpec), new vc(circularProgressIndicatorSpec)));
        setProgressDrawable(new mk(getContext(), circularProgressIndicatorSpec, new tc(circularProgressIndicatorSpec)));
    }
}
