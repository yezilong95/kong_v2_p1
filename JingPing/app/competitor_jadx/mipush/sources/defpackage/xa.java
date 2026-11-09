package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class xa extends is implements Cloneable {
    public float P;
    public float Q;
    public float R;
    public float S;
    public float T;
    public float U;

    @Override // defpackage.is
    public final void d(float f, float f2, float f3, jv0 jv0Var) {
        float f4;
        float f5;
        float f6 = this.R;
        if (f6 == 0.0f) {
            jv0Var.d(f, 0.0f);
            return;
        }
        float f7 = ((this.Q * 2.0f) + f6) / 2.0f;
        float f8 = f3 * this.P;
        float f9 = f2 + this.T;
        float fD = zn.d(1.0f, f3, f7, this.S * f3);
        if (fD / f7 >= 1.0f) {
            jv0Var.d(f, 0.0f);
            return;
        }
        float f10 = this.U;
        float f11 = f10 * f3;
        boolean z = f10 == -1.0f || Math.abs((f10 * 2.0f) - f6) < 0.1f;
        if (z) {
            f4 = fD;
            f5 = 0.0f;
        } else {
            f5 = 1.75f;
            f4 = 0.0f;
        }
        float f12 = f7 + f8;
        float f13 = f4 + f8;
        float fSqrt = (float) Math.sqrt((f12 * f12) - (f13 * f13));
        float f14 = f9 - fSqrt;
        float f15 = f9 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f13));
        float f16 = (90.0f - degrees) + f5;
        jv0Var.d(f14, 0.0f);
        float f17 = f14 - f8;
        float f18 = f14 + f8;
        float f19 = f8 * 2.0f;
        jv0Var.a(f17, 0.0f, f18, f19, 270.0f, degrees);
        if (z) {
            jv0Var.a(f9 - f7, (-f7) - f4, f9 + f7, f7 - f4, 180.0f - f16, (f16 * 2.0f) - 180.0f);
        } else {
            float f20 = this.Q;
            float f21 = f11 * 2.0f;
            float f22 = f20 + f21;
            float f23 = f9 - f7;
            jv0Var.a(f23, -(f11 + f20), f22 + f23, f20 + f11, 180.0f - f16, ((f16 * 2.0f) - 180.0f) / 2.0f);
            float f24 = f9 + f7;
            float f25 = this.Q;
            jv0Var.d(f24 - ((f25 / 2.0f) + f11), f25 + f11);
            float f26 = this.Q;
            jv0Var.a(f24 - (f21 + f26), -(f11 + f26), f24, f26 + f11, 90.0f, f16 - 90.0f);
        }
        jv0Var.a(f15 - f8, 0.0f, f15 + f8, f19, 270.0f - degrees, degrees);
        jv0Var.d(f, 0.0f);
    }

    public final void p(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.S = f;
    }
}
