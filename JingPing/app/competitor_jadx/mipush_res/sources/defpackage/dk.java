package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class dk extends t40 {
    public double A;
    public double[] B;

    @Override // defpackage.t40
    public final double i0(double d) {
        return this.B[0];
    }

    @Override // defpackage.t40
    public final void j0(double d, double[] dArr) {
        double[] dArr2 = this.B;
        System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
    }

    @Override // defpackage.t40
    public final void k0(double d, float[] fArr) {
        int i = 0;
        while (true) {
            double[] dArr = this.B;
            if (i >= dArr.length) {
                return;
            }
            fArr[i] = (float) dArr[i];
            i++;
        }
    }

    @Override // defpackage.t40
    public final double n0(double d) {
        return 0.0d;
    }

    @Override // defpackage.t40
    public final void o0(double d, double[] dArr) {
        for (int i = 0; i < this.B.length; i++) {
            dArr[i] = 0.0d;
        }
    }

    @Override // defpackage.t40
    public final double[] r0() {
        return new double[]{this.A};
    }
}
