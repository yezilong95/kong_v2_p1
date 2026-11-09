package com.google.android.material.transformation;

import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import defpackage.z2;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableTransformationBehavior extends ExpandableBehavior {
    public AnimatorSet F;

    public ExpandableTransformationBehavior() {
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior
    public void w(View view, View view2, boolean z, boolean z2) {
        AnimatorSet animatorSet = this.F;
        boolean z3 = animatorSet != null;
        if (z3) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSetX = x(view, view2, z, z3);
        this.F = animatorSetX;
        animatorSetX.addListener(new z2(4, this));
        this.F.start();
        if (z2) {
            return;
        }
        this.F.end();
    }

    public abstract AnimatorSet x(View view, View view2, boolean z, boolean z2);

    public ExpandableTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
