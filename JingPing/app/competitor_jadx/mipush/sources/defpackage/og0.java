package defpackage;

import android.animation.ValueAnimator;
import com.google.android.material.navigation.NavigationBarItemView;

/* JADX INFO: loaded from: classes.dex */
public final class og0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ float a;
    public final /* synthetic */ NavigationBarItemView b;

    public og0(NavigationBarItemView navigationBarItemView, float f) {
        this.b = navigationBarItemView;
        this.a = f;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        int[] iArr = NavigationBarItemView.n0;
        this.b.e(fFloatValue, this.a);
    }
}
