package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class gi extends nv0 {
    public double t;
    public double[] u;

    @Override // defpackage.nv0
    public final double s(double d) {
        return this.u[0];
    }

    @Override // defpackage.nv0
    public final void t(double d, double[] dArr) {
        double[] dArr2 = this.u;
        System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
    }

    @Override // defpackage.nv0
    public final void u(double d, float[] fArr) {
        int i = 0;
        while (true) {
            double[] dArr = this.u;
            if (i >= dArr.length) {
                return;
            }
            fArr[i] = (float) dArr[i];
            i++;
        }
    }

    @Override // defpackage.nv0
    public final double v(double d) {
        return 0.0d;
    }

    @Override // defpackage.nv0
    public final void w(double d, double[] dArr) {
        for (int i = 0; i < this.u.length; i++) {
            dArr[i] = 0.0d;
        }
    }

    @Override // defpackage.nv0
    public final double[] z() {
        return new double[]{this.t};
    }
}
