package defpackage;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.appcompat.widget.ActionMenuView;

/* JADX INFO: loaded from: classes.dex */
public final class hv implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int a;
    public final View b;
    public final View c;
    public final Object d;

    public hv(ActionMenuView actionMenuView, ActionMenuView actionMenuView2) {
        this.a = 0;
        this.b = actionMenuView;
        this.c = actionMenuView2;
        this.d = new float[2];
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.a) {
            case 0:
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                float[] fArr = (float[]) this.d;
                j31.i(fFloatValue, fArr);
                ActionMenuView actionMenuView = (ActionMenuView) this.b;
                if (actionMenuView != null) {
                    actionMenuView.setAlpha(fArr[0]);
                }
                ActionMenuView actionMenuView2 = (ActionMenuView) this.c;
                if (actionMenuView2 != null) {
                    actionMenuView2.setAlpha(fArr[1]);
                }
                break;
            default:
                ((cz0) this.d).c(this.b, this.c, valueAnimator.getAnimatedFraction());
                break;
        }
    }

    public hv(cz0 cz0Var, View view, View view2) {
        this.a = 1;
        this.d = cz0Var;
        this.b = view;
        this.c = view2;
    }
}
