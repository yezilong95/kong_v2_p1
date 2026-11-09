package defpackage;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.internal.ClippableRoundedCornerLayout;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class tl implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ tl(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.a) {
            case 0:
                ((DrawerLayout) this.b).setScrimColor(je.e(-1728053248, t3.c(vl.a, valueAnimator.getAnimatedFraction(), 0)));
                break;
            case 1:
                gm gmVar = (gm) this.b;
                gmVar.getClass();
                gmVar.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            case 2:
                k50 k50Var = (k50) this.b;
                k50Var.getClass();
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                k50Var.j.setAlpha((int) (255.0f * fFloatValue));
                k50Var.x = fFloatValue;
                break;
            case 3:
                float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ((ClippableRoundedCornerLayout) this.b).a(r0.getLeft(), r0.getTop(), r0.getRight(), r0.getBottom(), fFloatValue2);
                break;
            case 4:
                float fFloatValue3 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ol olVar = (ol) this.b;
                if (olVar.i != fFloatValue3) {
                    olVar.i = fFloatValue3;
                    olVar.invalidateSelf();
                }
                break;
            case 5:
                ((jp) this.b).a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            default:
                ((View) ((bu0) ((d90) this.b).b).d.getParent()).invalidate();
                break;
        }
    }

    public /* synthetic */ tl(d90 d90Var, View view) {
        this.a = 6;
        this.b = d90Var;
    }
}
