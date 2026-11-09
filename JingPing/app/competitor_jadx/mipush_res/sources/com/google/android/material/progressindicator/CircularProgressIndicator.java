package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$drawable;
import com.google.android.material.R$style;
import defpackage.a40;
import defpackage.a41;
import defpackage.aa;
import defpackage.bp0;
import defpackage.fo;
import defpackage.ne;
import defpackage.pe;
import defpackage.vo0;
import defpackage.z31;

/* JADX INFO: loaded from: classes.dex */
public class CircularProgressIndicator extends BaseProgressIndicator<CircularProgressIndicatorSpec> {
    public static final int Q = R$style.Widget_MaterialComponents_CircularProgressIndicator;

    public CircularProgressIndicator(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public final aa a(Context context, AttributeSet attributeSet) {
        return new CircularProgressIndicatorSpec(context, attributeSet);
    }

    public int getIndicatorDirection() {
        return ((CircularProgressIndicatorSpec) this.E).j;
    }

    public int getIndicatorInset() {
        return ((CircularProgressIndicatorSpec) this.E).i;
    }

    public int getIndicatorSize() {
        return ((CircularProgressIndicatorSpec) this.E).h;
    }

    public void setIndicatorDirection(int i) {
        ((CircularProgressIndicatorSpec) this.E).j = i;
        invalidate();
    }

    public void setIndicatorInset(int i) {
        aa aaVar = this.E;
        if (((CircularProgressIndicatorSpec) aaVar).i != i) {
            ((CircularProgressIndicatorSpec) aaVar).i = i;
            invalidate();
        }
    }

    public void setIndicatorSize(int i) {
        int iMax = Math.max(i, getTrackThickness() * 2);
        aa aaVar = this.E;
        if (((CircularProgressIndicatorSpec) aaVar).h != iMax) {
            ((CircularProgressIndicatorSpec) aaVar).h = iMax;
            ((CircularProgressIndicatorSpec) aaVar).a();
            requestLayout();
            invalidate();
        }
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackThickness(int i) {
        super.setTrackThickness(i);
        ((CircularProgressIndicatorSpec) this.E).a();
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.circularProgressIndicatorStyle);
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, Q);
        CircularProgressIndicatorSpec circularProgressIndicatorSpec = (CircularProgressIndicatorSpec) this.E;
        ne neVar = new ne(circularProgressIndicatorSpec);
        Context context2 = getContext();
        a40 a40Var = new a40(context2, circularProgressIndicatorSpec, neVar, new pe(circularProgressIndicatorSpec));
        Resources resources = context2.getResources();
        int i2 = R$drawable.indeterminate_static;
        a41 a41Var = new a41();
        ThreadLocal threadLocal = bp0.a;
        a41Var.E = vo0.a(resources, i2, null);
        new z31(a41Var.E.getConstantState());
        a40Var.R = a41Var;
        setIndeterminateDrawable(a40Var);
        setProgressDrawable(new fo(getContext(), circularProgressIndicatorSpec, neVar));
    }
}
