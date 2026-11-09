package com.google.android.material.transformation;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import androidx.coordinatorlayout.widget.c;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import defpackage.di;
import defpackage.i6;
import defpackage.rf0;
import defpackage.sf0;
import defpackage.y4;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    public final Rect G;
    public final RectF H;
    public final RectF I;
    public final int[] J;
    public float K;
    public float L;

    public FabTransformationBehavior() {
        this.G = new Rect();
        this.H = new RectF();
        this.I = new RectF();
        this.J = new int[2];
    }

    public static float B(i6 i6Var, sf0 sf0Var, float f) {
        long j = sf0Var.a;
        sf0 sf0VarF = ((rf0) i6Var.F).f("expansion");
        return y4.a(f, 0.0f, sf0Var.b().getInterpolation((((sf0VarF.a + sf0VarF.b) + 17) - j) / sf0Var.b));
    }

    public static Pair y(float f, float f2, boolean z, i6 i6Var) {
        sf0 sf0VarF;
        sf0 sf0VarF2;
        if (f == 0.0f || f2 == 0.0f) {
            sf0VarF = ((rf0) i6Var.F).f("translationXLinear");
            sf0VarF2 = ((rf0) i6Var.F).f("translationYLinear");
        } else if ((!z || f2 >= 0.0f) && (z || f2 <= 0.0f)) {
            sf0VarF = ((rf0) i6Var.F).f("translationXCurveDownwards");
            sf0VarF2 = ((rf0) i6Var.F).f("translationYCurveDownwards");
        } else {
            sf0VarF = ((rf0) i6Var.F).f("translationXCurveUpwards");
            sf0VarF2 = ((rf0) i6Var.F).f("translationYCurveUpwards");
        }
        return new Pair(sf0VarF, sf0VarF2);
    }

    public final float A(View view, View view2, di diVar) {
        RectF rectF = this.H;
        RectF rectF2 = this.I;
        C(view, rectF);
        rectF.offset(this.K, this.L);
        C(view2, rectF2);
        diVar.getClass();
        return (rectF2.centerY() - rectF.centerY()) + 0.0f;
    }

    public final void C(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.J);
        rectF.offsetTo(r0[0], r0[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    public abstract i6 D(Context context, boolean z);

    @Override // com.google.android.material.transformation.ExpandableBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean f(View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        }
        if (!(view2 instanceof FloatingActionButton)) {
            return false;
        }
        int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
        return expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void g(c cVar) {
        if (cVar.h == 0) {
            cVar.h = 80;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x035e  */
    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.animation.AnimatorSet x(android.view.View r25, android.view.View r26, boolean r27, boolean r28) {
        /*
            Method dump skipped, instruction units count: 970
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.transformation.FabTransformationBehavior.x(android.view.View, android.view.View, boolean, boolean):android.animation.AnimatorSet");
    }

    public final float z(View view, View view2, di diVar) {
        RectF rectF = this.H;
        RectF rectF2 = this.I;
        C(view, rectF);
        rectF.offset(this.K, this.L);
        C(view2, rectF2);
        diVar.getClass();
        return (rectF2.centerX() - rectF.centerX()) + 0.0f;
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.G = new Rect();
        this.H = new RectF();
        this.I = new RectF();
        this.J = new int[2];
    }
}
