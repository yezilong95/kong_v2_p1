package defpackage;

import android.animation.ObjectAnimator;
import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class a20 extends ux {
    public static final fb j = new fb(Float.class, "animationFraction", 15);
    public ObjectAnimator d;
    public final mp e;
    public final LinearProgressIndicatorSpec f;
    public int g;
    public boolean h;
    public float i;

    public a20(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(3);
        this.g = 1;
        this.f = linearProgressIndicatorSpec;
        this.e = new mp(1);
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
        this.h = true;
        this.g = 1;
        Arrays.fill((int[]) this.c, k9.j(this.f.c[0], ((vx) this.a).k));
    }

    @Override // defpackage.ux
    public final void o() {
        if (this.d == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, j, 0.0f, 1.0f);
            this.d = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(333L);
            this.d.setInterpolator(null);
            this.d.setRepeatCount(-1);
            this.d.addListener(new d1(7, this));
        }
        this.h = true;
        this.g = 1;
        Arrays.fill((int[]) this.c, k9.j(this.f.c[0], ((vx) this.a).k));
        this.d.start();
    }

    @Override // defpackage.ux
    public final void n() {
    }

    @Override // defpackage.ux
    public final void q() {
    }

    @Override // defpackage.ux
    public final void m(d8 d8Var) {
    }
}
