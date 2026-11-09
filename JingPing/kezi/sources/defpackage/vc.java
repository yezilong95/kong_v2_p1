package defpackage;

import android.animation.ObjectAnimator;
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public final class vc extends ux {
    public static final int[] l = {0, 1350, 2700, 4050};
    public static final int[] m = {667, 2017, 3367, 4717};
    public static final int[] n = {CloseFrame.NORMAL, 2350, 3700, 5050};
    public static final fb o = new fb(Float.class, "animationFraction", 8);
    public static final fb p = new fb(Float.class, "completeEndFraction", 9);
    public ObjectAnimator d;
    public ObjectAnimator e;
    public final mp f;
    public final CircularProgressIndicatorSpec g;
    public int h;
    public float i;
    public float j;
    public d8 k;

    public vc(CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(1);
        this.h = 0;
        this.k = null;
        this.g = circularProgressIndicatorSpec;
        this.f = new mp(1);
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
        ((int[]) this.c)[0] = k9.j(this.g.c[0], ((vx) this.a).k);
        this.j = 0.0f;
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
        if (((vx) this.a).isVisible()) {
            this.e.start();
        } else {
            a();
        }
    }

    @Override // defpackage.ux
    public final void o() {
        if (this.d == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, o, 0.0f, 1.0f);
            this.d = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(5400L);
            this.d.setInterpolator(null);
            this.d.setRepeatCount(-1);
            this.d.addListener(new uc(this, 0));
        }
        if (this.e == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, p, 0.0f, 1.0f);
            this.e = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(333L);
            this.e.setInterpolator(this.f);
            this.e.addListener(new uc(this, 1));
        }
        this.h = 0;
        ((int[]) this.c)[0] = k9.j(this.g.c[0], ((vx) this.a).k);
        this.j = 0.0f;
        this.d.start();
    }

    @Override // defpackage.ux
    public final void q() {
        this.k = null;
    }
}
