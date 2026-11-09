package defpackage;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes.dex */
public final class y70 {
    public long a;
    public TimeInterpolator c = null;
    public int d = 0;
    public int e = 1;
    public long b = 150;

    public y70(long j) {
        this.a = j;
    }

    public final void a(Animator animator) {
        animator.setStartDelay(this.a);
        animator.setDuration(this.b);
        animator.setInterpolator(b());
        if (animator instanceof ValueAnimator) {
            ValueAnimator valueAnimator = (ValueAnimator) animator;
            valueAnimator.setRepeatCount(this.d);
            valueAnimator.setRepeatMode(this.e);
        }
    }

    public final TimeInterpolator b() {
        TimeInterpolator timeInterpolator = this.c;
        return timeInterpolator != null ? timeInterpolator : t3.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof y70)) {
            return false;
        }
        y70 y70Var = (y70) obj;
        if (this.a == y70Var.a && this.b == y70Var.b && this.d == y70Var.d && this.e == y70Var.e) {
            return b().getClass().equals(y70Var.b().getClass());
        }
        return false;
    }

    public final int hashCode() {
        long j = this.a;
        long j2 = this.b;
        return ((((b().getClass().hashCode() + (((((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31)) * 31) + this.d) * 31) + this.e;
    }

    public final String toString() {
        return "\n" + y70.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + this.a + " duration: " + this.b + " interpolator: " + b().getClass() + " repeatCount: " + this.d + " repeatMode: " + this.e + "}\n";
    }
}
