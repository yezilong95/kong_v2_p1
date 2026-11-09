package defpackage;

import androidx.constraintlayout.motion.widget.MotionLayout;

/* JADX INFO: loaded from: classes.dex */
public final class l70 extends k70 {
    public float a = 0.0f;
    public float b = 0.0f;
    public float c;
    public final /* synthetic */ MotionLayout d;

    public l70(MotionLayout motionLayout) {
        this.d = motionLayout;
    }

    @Override // defpackage.k70
    public final float a() {
        return this.d.t;
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        float f2 = this.a;
        MotionLayout motionLayout = this.d;
        if (f2 > 0.0f) {
            float f3 = this.c;
            if (f2 / f3 < f) {
                f = f2 / f3;
            }
            motionLayout.t = f2 - (f3 * f);
            return ((f2 * f) - (((f3 * f) * f) / 2.0f)) + this.b;
        }
        float f4 = this.c;
        if ((-f2) / f4 < f) {
            f = (-f2) / f4;
        }
        motionLayout.t = (f4 * f) + f2;
        return (((f4 * f) * f) / 2.0f) + (f2 * f) + this.b;
    }
}
