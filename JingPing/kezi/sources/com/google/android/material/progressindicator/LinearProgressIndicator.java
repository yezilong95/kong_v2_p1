package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import defpackage.a20;
import defpackage.c20;
import defpackage.e8;
import defpackage.mk;
import defpackage.ux;
import defpackage.vx;
import defpackage.xr0;
import defpackage.z10;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class LinearProgressIndicator extends BaseProgressIndicator<LinearProgressIndicatorSpec> {
    public static final int n = R$style.Widget_MaterialComponents_LinearProgressIndicator;

    public LinearProgressIndicator(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public final e8 a(Context context, AttributeSet attributeSet) {
        return new LinearProgressIndicatorSpec(context, attributeSet);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public final void b(int i) {
        e8 e8Var = this.b;
        if (e8Var != null && ((LinearProgressIndicatorSpec) e8Var).g == 0 && isIndeterminate()) {
            return;
        }
        super.b(i);
    }

    public int getIndeterminateAnimationType() {
        return ((LinearProgressIndicatorSpec) this.b).g;
    }

    public int getIndicatorDirection() {
        return ((LinearProgressIndicatorSpec) this.b).h;
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        e8 e8Var = this.b;
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) e8Var;
        boolean z2 = true;
        if (((LinearProgressIndicatorSpec) e8Var).h != 1) {
            WeakHashMap weakHashMap = xr0.a;
            if ((getLayoutDirection() != 1 || ((LinearProgressIndicatorSpec) e8Var).h != 2) && (getLayoutDirection() != 0 || ((LinearProgressIndicatorSpec) e8Var).h != 3)) {
                z2 = false;
            }
        }
        linearProgressIndicatorSpec.i = z2;
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        int paddingRight = i - (getPaddingRight() + getPaddingLeft());
        int paddingBottom = i2 - (getPaddingBottom() + getPaddingTop());
        vx indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable != null) {
            indeterminateDrawable.setBounds(0, 0, paddingRight, paddingBottom);
        }
        mk progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setBounds(0, 0, paddingRight, paddingBottom);
        }
    }

    public void setIndeterminateAnimationType(int i) {
        e8 e8Var = this.b;
        if (((LinearProgressIndicatorSpec) e8Var).g == i) {
            return;
        }
        if (c() && isIndeterminate()) {
            throw new IllegalStateException("Cannot change indeterminate animation type while the progress indicator is show in indeterminate mode.");
        }
        ((LinearProgressIndicatorSpec) e8Var).g = i;
        ((LinearProgressIndicatorSpec) e8Var).a();
        if (i == 0) {
            vx indeterminateDrawable = getIndeterminateDrawable();
            a20 a20Var = new a20((LinearProgressIndicatorSpec) e8Var);
            indeterminateDrawable.n = a20Var;
            a20Var.a = indeterminateDrawable;
        } else {
            vx indeterminateDrawable2 = getIndeterminateDrawable();
            c20 c20Var = new c20(getContext(), (LinearProgressIndicatorSpec) e8Var);
            indeterminateDrawable2.n = c20Var;
            c20Var.a = indeterminateDrawable2;
        }
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setIndicatorColor(int... iArr) {
        super.setIndicatorColor(iArr);
        ((LinearProgressIndicatorSpec) this.b).a();
    }

    public void setIndicatorDirection(int i) {
        e8 e8Var = this.b;
        ((LinearProgressIndicatorSpec) e8Var).h = i;
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) e8Var;
        boolean z = true;
        if (i != 1) {
            WeakHashMap weakHashMap = xr0.a;
            if ((getLayoutDirection() != 1 || ((LinearProgressIndicatorSpec) e8Var).h != 2) && (getLayoutDirection() != 0 || i != 3)) {
                z = false;
            }
        }
        linearProgressIndicatorSpec.i = z;
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackCornerRadius(int i) {
        super.setTrackCornerRadius(i);
        ((LinearProgressIndicatorSpec) this.b).a();
        invalidate();
    }

    public LinearProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.linearProgressIndicatorStyle);
    }

    public LinearProgressIndicator(Context context, AttributeSet attributeSet, int i) {
        ux c20Var;
        super(context, attributeSet, i, n);
        Context context2 = getContext();
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) this.b;
        z10 z10Var = new z10(linearProgressIndicatorSpec);
        if (linearProgressIndicatorSpec.g == 0) {
            c20Var = new a20(linearProgressIndicatorSpec);
        } else {
            c20Var = new c20(context2, linearProgressIndicatorSpec);
        }
        setIndeterminateDrawable(new vx(context2, linearProgressIndicatorSpec, z10Var, c20Var));
        setProgressDrawable(new mk(getContext(), linearProgressIndicatorSpec, new z10(linearProgressIndicatorSpec)));
    }
}
