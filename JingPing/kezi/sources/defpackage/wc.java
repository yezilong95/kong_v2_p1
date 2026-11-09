package defpackage;

import android.animation.ValueAnimator;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class wc implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int a = 1;
    public final Object b;
    public final Object c;

    public wc(vk vkVar, View[] viewArr) {
        this.b = vkVar;
        this.c = viewArr;
    }

    public static wc a(View... viewArr) {
        return new wc(new vk(5), viewArr);
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.a) {
            case 0:
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                zc zcVar = (zc) this.c;
                zcVar.getClass();
                yc ycVar = (yc) this.b;
                zc.d(fFloatValue, ycVar);
                zcVar.a(fFloatValue, ycVar, false);
                zcVar.invalidateSelf();
                break;
            default:
                for (View view : (View[]) this.c) {
                    switch (((vk) this.b).b) {
                        case 4:
                            view.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                        case 5:
                            view.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                        case 6:
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

    public wc(zc zcVar, yc ycVar) {
        this.c = zcVar;
        this.b = ycVar;
    }
}
