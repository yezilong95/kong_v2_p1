package defpackage;

import android.animation.ObjectAnimator;
import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class e90 extends z5 {
    public static final yc i = new yc(Float.class, "animationFraction", 15);
    public ObjectAnimator c;
    public final sv d;
    public final LinearProgressIndicatorSpec e;
    public int f;
    public boolean g;
    public float h;

    public e90(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(3);
        this.f = 1;
        this.e = linearProgressIndicatorSpec;
        this.d = new sv(1);
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
        q();
    }

    @Override // defpackage.z5
    public final void o() {
        if (this.c == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, i, 0.0f, 1.0f);
            this.c = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(333L);
            this.c.setInterpolator(null);
            this.c.setRepeatCount(-1);
            this.c.addListener(new z2(7, this));
        }
        q();
        this.c.start();
    }

    public final void q() {
        this.g = true;
        this.f = 1;
        for (zp zpVar : (ArrayList) this.b) {
            LinearProgressIndicatorSpec linearProgressIndicatorSpec = this.e;
            zpVar.c = linearProgressIndicatorSpec.c[0];
            zpVar.d = linearProgressIndicatorSpec.g / 2;
        }
    }

    @Override // defpackage.z5
    public final void m() {
    }

    @Override // defpackage.z5
    public final void p() {
    }

    @Override // defpackage.z5
    public final void l(z9 z9Var) {
    }
}
