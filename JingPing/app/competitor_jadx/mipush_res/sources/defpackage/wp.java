package defpackage;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.internal.ClippableRoundedCornerLayout;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wp implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ wp(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.a) {
            case 0:
                ((DrawerLayout) this.b).setScrimColor(dg.e(-1728053248, y4.c(yp.a, valueAnimator.getAnimatedFraction(), 0)));
                break;
            case 1:
                jq jqVar = (jq) this.b;
                jqVar.getClass();
                jqVar.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            case 2:
                ad0 ad0Var = (ad0) this.b;
                ad0Var.getClass();
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ad0Var.j.setAlpha((int) (255.0f * fFloatValue));
                ad0Var.x = fFloatValue;
                break;
            case 3:
                float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ((ClippableRoundedCornerLayout) this.b).a(r0.getLeft(), r0.getTop(), r0.getRight(), r0.getBottom(), fFloatValue2);
                break;
            case 4:
                float fFloatValue3 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                rp rpVar = (rp) this.b;
                if (rpVar.i != fFloatValue3) {
                    rpVar.i = fFloatValue3;
                    rpVar.invalidateSelf();
                }
                break;
            case 5:
                ((gv) this.b).a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            default:
                ((View) ((x71) ((cw0) this.b).F).d.getParent()).invalidate();
                break;
        }
    }

    public /* synthetic */ wp(cw0 cw0Var, View view) {
        this.a = 6;
        this.b = cw0Var;
    }
}
