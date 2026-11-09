package defpackage;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class bb implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ bb(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.a) {
            case 0:
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ld0 ld0Var = ((BottomSheetBehavior) this.b).M;
                if (ld0Var != null) {
                    ld0Var.n(fFloatValue);
                }
                break;
            case 1:
                ((CollapsingToolbarLayout) this.b).setScrimAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
                break;
            case 2:
                ((View) this.b).invalidate();
                break;
            case 3:
                int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
                vv vvVar = (vv) this.b;
                vvVar.c.setAlpha(iFloatValue);
                vvVar.d.setAlpha(iFloatValue);
                vvVar.s.invalidate();
                break;
            case 4:
                ((TabLayout) this.b).scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
                break;
            case 5:
                ((TextInputLayout) this.b).Z0.p(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            default:
                float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                TextView textView = (TextView) this.b;
                textView.setScaleX(fFloatValue2);
                textView.setScaleY(fFloatValue2);
                break;
        }
    }
}
