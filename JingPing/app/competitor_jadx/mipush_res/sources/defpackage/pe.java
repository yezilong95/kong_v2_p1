package defpackage;

import android.animation.ObjectAnimator;
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class pe extends z5 {
    public static final int[] k = {0, 1350, 2700, 4050};
    public static final int[] l = {667, 2017, 3367, 4717};
    public static final int[] m = {1000, 2350, 3700, 5050};
    public static final yc n = new yc(Float.class, "animationFraction", 8);
    public static final yc o = new yc(Float.class, "completeEndFraction", 9);
    public ObjectAnimator c;
    public ObjectAnimator d;
    public final sv e;
    public final CircularProgressIndicatorSpec f;
    public int g;
    public float h;
    public float i;
    public z9 j;

    public pe(CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(1);
        this.g = 0;
        this.j = null;
        this.f = circularProgressIndicatorSpec;
        this.e = new sv(1);
    }

    @Override // defpackage.z5
    public final void c() {
        ObjectAnimator objectAnimator = this.c;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // defpackage.z5
    public final void j() {
        this.g = 0;
        ((zp) ((ArrayList) this.b).get(0)).c = this.f.c[0];
        this.i = 0.0f;
    }

    @Override // defpackage.z5
    public final void l(z9 z9Var) {
        this.j = z9Var;
    }

    @Override // defpackage.z5
    public final void m() {
        ObjectAnimator objectAnimator = this.d;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        if (((a40) this.a).isVisible()) {
            this.d.start();
        } else {
            c();
        }
    }

    @Override // defpackage.z5
    public final void o() {
        if (this.c == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, n, 0.0f, 1.0f);
            this.c = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(5400L);
            this.c.setInterpolator(null);
            this.c.setRepeatCount(-1);
            this.c.addListener(new oe(this, 0));
        }
        if (this.d == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, o, 0.0f, 1.0f);
            this.d = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(333L);
            this.d.setInterpolator(this.e);
            this.d.addListener(new oe(this, 1));
        }
        this.g = 0;
        ((zp) ((ArrayList) this.b).get(0)).c = this.f.c[0];
        this.i = 0.0f;
        this.c.start();
    }

    @Override // defpackage.z5
    public final void p() {
        this.j = null;
    }
}
