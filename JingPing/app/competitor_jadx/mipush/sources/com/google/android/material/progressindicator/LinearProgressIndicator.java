package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import defpackage.a40;
import defpackage.aa;
import defpackage.d90;
import defpackage.e90;
import defpackage.f51;
import defpackage.fo;
import defpackage.g90;
import defpackage.z5;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class LinearProgressIndicator extends BaseProgressIndicator<LinearProgressIndicatorSpec> {
    public static final int Q = R$style.Widget_MaterialComponents_LinearProgressIndicator;

    public LinearProgressIndicator(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public final aa a(Context context, AttributeSet attributeSet) {
        return new LinearProgressIndicatorSpec(context, attributeSet);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public final void b(int i) {
        aa aaVar = this.E;
        if (aaVar != null && ((LinearProgressIndicatorSpec) aaVar).h == 0 && isIndeterminate()) {
            return;
        }
        super.b(i);
    }

    public int getIndeterminateAnimationType() {
        return ((LinearProgressIndicatorSpec) this.E).h;
    }

    public int getIndicatorDirection() {
        return ((LinearProgressIndicatorSpec) this.E).i;
    }

    public int getTrackStopIndicatorSize() {
        return ((LinearProgressIndicatorSpec) this.E).k;
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        aa aaVar = this.E;
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) aaVar;
        boolean z2 = true;
        if (((LinearProgressIndicatorSpec) aaVar).i != 1) {
            WeakHashMap weakHashMap = f51.a;
            if ((getLayoutDirection() != 1 || ((LinearProgressIndicatorSpec) aaVar).i != 2) && (getLayoutDirection() != 0 || ((LinearProgressIndicatorSpec) aaVar).i != 3)) {
                z2 = false;
            }
        }
        linearProgressIndicatorSpec.j = z2;
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        int paddingRight = i - (getPaddingRight() + getPaddingLeft());
        int paddingBottom = i2 - (getPaddingBottom() + getPaddingTop());
        a40 indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable != null) {
            indeterminateDrawable.setBounds(0, 0, paddingRight, paddingBottom);
        }
        fo progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setBounds(0, 0, paddingRight, paddingBottom);
        }
    }

    public void setIndeterminateAnimationType(int i) {
        aa aaVar = this.E;
        if (((LinearProgressIndicatorSpec) aaVar).h == i) {
            return;
        }
        if (c() && isIndeterminate()) {
            throw new IllegalStateException("Cannot change indeterminate animation type while the progress indicator is show in indeterminate mode.");
        }
        ((LinearProgressIndicatorSpec) aaVar).h = i;
        ((LinearProgressIndicatorSpec) aaVar).a();
        if (i == 0) {
            a40 indeterminateDrawable = getIndeterminateDrawable();
            e90 e90Var = new e90((LinearProgressIndicatorSpec) aaVar);
            indeterminateDrawable.Q = e90Var;
            e90Var.a = indeterminateDrawable;
        } else {
            a40 indeterminateDrawable2 = getIndeterminateDrawable();
            g90 g90Var = new g90(getContext(), (LinearProgressIndicatorSpec) aaVar);
            indeterminateDrawable2.Q = g90Var;
            g90Var.a = indeterminateDrawable2;
        }
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setIndicatorColor(int... iArr) {
        super.setIndicatorColor(iArr);
        ((LinearProgressIndicatorSpec) this.E).a();
    }

    public void setIndicatorDirection(int i) {
        aa aaVar = this.E;
        ((LinearProgressIndicatorSpec) aaVar).i = i;
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) aaVar;
        boolean z = true;
        if (i != 1) {
            WeakHashMap weakHashMap = f51.a;
            if ((getLayoutDirection() != 1 || ((LinearProgressIndicatorSpec) aaVar).i != 2) && (getLayoutDirection() != 0 || i != 3)) {
                z = false;
            }
        }
        linearProgressIndicatorSpec.j = z;
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackCornerRadius(int i) {
        super.setTrackCornerRadius(i);
        ((LinearProgressIndicatorSpec) this.E).a();
        invalidate();
    }

    public void setTrackStopIndicatorSize(int i) {
        aa aaVar = this.E;
        if (((LinearProgressIndicatorSpec) aaVar).k != i) {
            ((LinearProgressIndicatorSpec) aaVar).k = Math.min(i, ((LinearProgressIndicatorSpec) aaVar).a);
            ((LinearProgressIndicatorSpec) aaVar).a();
            invalidate();
        }
    }

    public LinearProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.linearProgressIndicatorStyle);
    }

    public LinearProgressIndicator(Context context, AttributeSet attributeSet, int i) {
        z5 g90Var;
        super(context, attributeSet, i, Q);
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) this.E;
        d90 d90Var = new d90(linearProgressIndicatorSpec);
        d90Var.b = 300.0f;
        Context context2 = getContext();
        if (linearProgressIndicatorSpec.h == 0) {
            g90Var = new e90(linearProgressIndicatorSpec);
        } else {
            g90Var = new g90(context2, linearProgressIndicatorSpec);
        }
        setIndeterminateDrawable(new a40(context2, linearProgressIndicatorSpec, d90Var, g90Var));
        setProgressDrawable(new fo(getContext(), linearProgressIndicatorSpec, d90Var));
    }
}
