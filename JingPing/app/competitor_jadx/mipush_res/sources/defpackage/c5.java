package defpackage;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.appbar.AppBarLayout;
import com.mamimobi.videostream.view.PatternLockView;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c5 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ View b;
    public final /* synthetic */ Object c;

    public /* synthetic */ c5(View view, Object obj, int i) {
        this.a = i;
        this.b = view;
        this.c = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        Object obj = this.c;
        View view = this.b;
        switch (this.a) {
            case 0:
                int i = AppBarLayout.g0;
                AppBarLayout appBarLayout = (AppBarLayout) view;
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ((ld0) obj).l(fFloatValue);
                Drawable drawable = appBarLayout.c0;
                if (drawable instanceof ld0) {
                    ((ld0) drawable).l(fFloatValue);
                }
                Iterator it = appBarLayout.V.iterator();
                if (it.hasNext()) {
                    throw zn.i(it);
                }
                return;
            default:
                int i2 = PatternLockView.k0;
                PatternLockView patternLockView = (PatternLockView) view;
                patternLockView.getClass();
                rk0 rk0Var = (rk0) obj;
                rk0Var.a = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                rk0Var.b = true;
                patternLockView.invalidate();
                return;
        }
    }
}
