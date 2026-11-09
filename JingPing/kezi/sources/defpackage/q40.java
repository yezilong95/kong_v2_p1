package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class q40 extends qm {
    public final float l;

    public q40(float f) {
        this.l = f - 0.001f;
    }

    @Override // defpackage.qm
    public final void j(float f, float f2, float f3, ui0 ui0Var) {
        double d = this.l;
        float fSqrt = (float) ((Math.sqrt(2.0d) * d) / 2.0d);
        float fSqrt2 = (float) Math.sqrt(Math.pow(d, 2.0d) - Math.pow(fSqrt, 2.0d));
        ui0Var.e(f2 - fSqrt, ((float) (-((Math.sqrt(2.0d) * d) - d))) + fSqrt2, 270.0f, 0.0f);
        ui0Var.d(f2, (float) (-((Math.sqrt(2.0d) * d) - d)));
        ui0Var.d(f2 + fSqrt, ((float) (-((Math.sqrt(2.0d) * d) - d))) + fSqrt2);
    }
}
