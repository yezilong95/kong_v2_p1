package defpackage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.google.android.material.R$anim;
import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class g90 extends z5 {
    public static final int[] k = {533, 567, 850, 750};
    public static final int[] l = {1267, 1000, 333, 0};
    public static final yc m = new yc(Float.class, "animationFraction", 16);
    public ObjectAnimator c;
    public ObjectAnimator d;
    public final Interpolator[] e;
    public final LinearProgressIndicatorSpec f;
    public int g;
    public boolean h;
    public float i;
    public z9 j;

    public g90(Context context, LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(2);
        this.g = 0;
        this.j = null;
        this.f = linearProgressIndicatorSpec;
        this.e = new Interpolator[]{AnimationUtils.loadInterpolator(context, R$anim.linear_indeterminate_line1_head_interpolator), AnimationUtils.loadInterpolator(context, R$anim.linear_indeterminate_line1_tail_interpolator), AnimationUtils.loadInterpolator(context, R$anim.linear_indeterminate_line2_head_interpolator), AnimationUtils.loadInterpolator(context, R$anim.linear_indeterminate_line2_tail_interpolator)};
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
    public final void l(z9 z9Var) {
        this.j = z9Var;
    }

    @Override // defpackage.z5
    public final void m() {
        ObjectAnimator objectAnimator = this.d;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        c();
        if (((a40) this.a).isVisible()) {
            this.d.setFloatValues(this.i, 1.0f);
            this.d.setDuration((long) ((1.0f - this.i) * 1800.0f));
            this.d.start();
        }
    }

    @Override // defpackage.z5
    public final void o() {
        int i = 1;
        int i2 = 0;
        ObjectAnimator objectAnimator = this.c;
        yc ycVar = m;
        if (objectAnimator == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, ycVar, 0.0f, 1.0f);
            this.c = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(1800L);
            this.c.setInterpolator(null);
            this.c.setRepeatCount(-1);
            this.c.addListener(new f90(this, i2));
        }
        if (this.d == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, ycVar, 1.0f);
            this.d = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(1800L);
            this.d.setInterpolator(null);
            this.d.addListener(new f90(this, i));
        }
        q();
        this.c.start();
    }

    @Override // defpackage.z5
    public final void p() {
        this.j = null;
    }

    public final void q() {
        this.g = 0;
        Iterator it = ((ArrayList) this.b).iterator();
        while (it.hasNext()) {
            ((zp) it.next()).c = this.f.c[0];
        }
    }
}
