package defpackage;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.work.Configuration;

/* JADX INFO: loaded from: classes.dex */
public final class qe implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int a = 1;
    public final Object b;
    public final Object c;

    public qe(no noVar, View[] viewArr) {
        this.b = noVar;
        this.c = viewArr;
    }

    public static qe a(View... viewArr) {
        return new qe(new no(20), viewArr);
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.a) {
            case 0:
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                te teVar = (te) this.c;
                teVar.getClass();
                se seVar = (se) this.b;
                te.d(fFloatValue, seVar);
                teVar.a(fFloatValue, seVar, false);
                teVar.invalidateSelf();
                break;
            default:
                for (View view : (View[]) this.c) {
                    switch (((no) this.b).E) {
                        case 19:
                            view.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                        case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                            view.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                        case 21:
                            Float f = (Float) valueAnimator.getAnimatedValue();
                            view.setScaleX(f.floatValue());
                            view.setScaleY(f.floatValue());
                            break;
                        default:
                            view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                    }
                }
                break;
        }
    }

    public qe(te teVar, se seVar) {
        this.c = teVar;
        this.b = seVar;
    }
}
