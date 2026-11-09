package com.google.android.material.transformation;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import androidx.coordinatorlayout.widget.c;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import defpackage.e5;
import defpackage.t3;
import defpackage.x70;
import defpackage.y70;
import defpackage.yo;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    public final Rect d;
    public final RectF e;
    public final RectF f;
    public final int[] g;
    public float h;
    public float i;

    public FabTransformationBehavior() {
        this.d = new Rect();
        this.e = new RectF();
        this.f = new RectF();
        this.g = new int[2];
    }

    public static float B(e5 e5Var, y70 y70Var, float f) {
        long j = y70Var.a;
        y70 y70VarF = ((x70) e5Var.c).f("expansion");
        return t3.a(f, 0.0f, y70Var.b().getInterpolation((((y70VarF.a + y70VarF.b) + 17) - j) / y70Var.b));
    }

    public static Pair y(float f, float f2, boolean z, e5 e5Var) {
        y70 y70VarF;
        y70 y70VarF2;
        if (f == 0.0f || f2 == 0.0f) {
            y70VarF = ((x70) e5Var.c).f("translationXLinear");
            y70VarF2 = ((x70) e5Var.c).f("translationYLinear");
        } else if ((!z || f2 >= 0.0f) && (z || f2 <= 0.0f)) {
            y70VarF = ((x70) e5Var.c).f("translationXCurveDownwards");
            y70VarF2 = ((x70) e5Var.c).f("translationYCurveDownwards");
        } else {
            y70VarF = ((x70) e5Var.c).f("translationXCurveUpwards");
            y70VarF2 = ((x70) e5Var.c).f("translationYCurveUpwards");
        }
        return new Pair(y70VarF, y70VarF2);
    }

    public final float A(View view, View view2, yo yoVar) {
        RectF rectF = this.e;
        RectF rectF2 = this.f;
        C(view, rectF);
        rectF.offset(this.h, this.i);
        C(view2, rectF2);
        yoVar.getClass();
        return (rectF2.centerY() - rectF.centerY()) + 0.0f;
    }

    public final void C(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.g);
        rectF.offsetTo(r0[0], r0[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    public abstract e5 D(Context context, boolean z);

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.animation.AnimatorSet x(android.view.View r25, android.view.View r26, boolean r27, boolean r28) {
        /*
            Method dump skipped, instruction units count: 970
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.transformation.FabTransformationBehavior.x(android.view.View, android.view.View, boolean, boolean):android.animation.AnimatorSet");
    }

    public final float z(View view, View view2, yo yoVar) {
        RectF rectF = this.e;
        RectF rectF2 = this.f;
        C(view, rectF);
        rectF.offset(this.h, this.i);
        C(view2, rectF2);
        yoVar.getClass();
        return (rectF2.centerX() - rectF.centerX()) + 0.0f;
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new Rect();
        this.e = new RectF();
        this.f = new RectF();
        this.g = new int[2];
    }
}
