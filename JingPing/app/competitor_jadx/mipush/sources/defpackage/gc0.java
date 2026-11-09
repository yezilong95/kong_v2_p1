package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class gc0 extends is {
    public final float P;

    public gc0(float f) {
        super(0);
        this.P = f - 0.001f;
    }

    @Override // defpackage.is
    public final void d(float f, float f2, float f3, jv0 jv0Var) {
        double d = this.P;
        float fSqrt = (float) ((Math.sqrt(2.0d) * d) / 2.0d);
        float fSqrt2 = (float) Math.sqrt(Math.pow(d, 2.0d) - Math.pow(fSqrt, 2.0d));
        jv0Var.e(f2 - fSqrt, ((float) (-((Math.sqrt(2.0d) * d) - d))) + fSqrt2, 270.0f, 0.0f);
        jv0Var.d(f2, (float) (-((Math.sqrt(2.0d) * d) - d)));
        jv0Var.d(f2 + fSqrt, ((float) (-((Math.sqrt(2.0d) * d) - d))) + fSqrt2);
    }
}
