package defpackage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.google.android.material.R$anim;
import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public final class c20 extends ux {
    public static final int[] l = {533, 567, 850, 750};
    public static final int[] m = {1267, CloseFrame.NORMAL, 333, 0};
    public static final fb n = new fb(Float.class, "animationFraction", 16);
    public ObjectAnimator d;
    public ObjectAnimator e;
    public final Interpolator[] f;
    public final LinearProgressIndicatorSpec g;
    public int h;
    public boolean i;
    public float j;
    public d8 k;

    public c20(Context context, LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(2);
        this.h = 0;
        this.k = null;
        this.g = linearProgressIndicatorSpec;
        this.f = new Interpolator[]{AnimationUtils.loadInterpolator(context, R$anim.linear_indeterminate_line1_head_interpolator), AnimationUtils.loadInterpolator(context, R$anim.linear_indeterminate_line1_tail_interpolator), AnimationUtils.loadInterpolator(context, R$anim.linear_indeterminate_line2_head_interpolator), AnimationUtils.loadInterpolator(context, R$anim.linear_indeterminate_line2_tail_interpolator)};
    }

    @Override // defpackage.ux
    public final void a() {
        ObjectAnimator objectAnimator = this.d;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // defpackage.ux
    public final void l() {
        this.h = 0;
        int iJ = k9.j(this.g.c[0], ((vx) this.a).k);
        int[] iArr = (int[]) this.c;
        iArr[0] = iJ;
        iArr[1] = iJ;
    }

    @Override // defpackage.ux
    public final void m(d8 d8Var) {
        this.k = d8Var;
    }

    @Override // defpackage.ux
    public final void n() {
        ObjectAnimator objectAnimator = this.e;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        a();
        if (((vx) this.a).isVisible()) {
            this.e.setFloatValues(this.j, 1.0f);
            this.e.setDuration((long) ((1.0f - this.j) * 1800.0f));
            this.e.start();
        }
    }

    @Override // defpackage.ux
    public final void o() {
        ObjectAnimator objectAnimator = this.d;
        fb fbVar = n;
        if (objectAnimator == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, fbVar, 0.0f, 1.0f);
            this.d = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(1800L);
            this.d.setInterpolator(null);
            this.d.setRepeatCount(-1);
            this.d.addListener(new b20(this, 0));
        }
        if (this.e == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, fbVar, 1.0f);
            this.e = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(1800L);
            this.e.setInterpolator(null);
            this.e.addListener(new b20(this, 1));
        }
        this.h = 0;
        int iJ = k9.j(this.g.c[0], ((vx) this.a).k);
        int[] iArr = (int[]) this.c;
        iArr[0] = iJ;
        iArr[1] = iJ;
        this.d.start();
    }

    @Override // defpackage.ux
    public final void q() {
        this.k = null;
    }
}
