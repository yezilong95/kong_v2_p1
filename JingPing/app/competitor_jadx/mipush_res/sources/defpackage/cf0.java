package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class cf0 extends t40 {
    public double[] A;
    public double[][] B;
    public double[][] C;

    public static double B1(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d2 * d2;
        double d8 = d2 * 6.0d;
        double d9 = 6.0d * d7 * d3;
        double d10 = (d9 + ((d8 * d4) + (((-6.0d) * d7) * d4))) - (d8 * d3);
        double d11 = 3.0d * d;
        return (d * d5) + (((((d11 * d5) * d7) + (((d11 * d6) * d7) + d10)) - (((2.0d * d) * d6) * d2)) - (((4.0d * d) * d5) * d2));
    }

    public static double C1(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d2 * d2;
        double d8 = d7 * d2;
        double d9 = 3.0d * d7;
        double d10 = d8 * 2.0d * d3;
        double d11 = ((d10 + ((d9 * d4) + (((-2.0d) * d8) * d4))) - (d9 * d3)) + d3;
        double d12 = d * d6;
        double d13 = (d12 * d8) + d11;
        double d14 = d * d5;
        return (d14 * d2) + ((((d8 * d14) + d13) - (d12 * d7)) - (((d * 2.0d) * d5) * d7));
    }

    @Override // defpackage.t40
    public final double i0(double d) {
        double[] dArr = this.A;
        int length = dArr.length;
        double d2 = dArr[0];
        double[][] dArr2 = this.B;
        if (d <= d2) {
            return dArr2[0][0];
        }
        int i = length - 1;
        if (d >= dArr[i]) {
            return dArr2[i][0];
        }
        int i2 = 0;
        while (i2 < i) {
            double d3 = dArr[i2];
            if (d == d3) {
                return dArr2[i2][0];
            }
            int i3 = i2 + 1;
            double d4 = dArr[i3];
            if (d < d4) {
                double d5 = d4 - d3;
                double d6 = (d - d3) / d5;
                double d7 = dArr2[i2][0];
                double d8 = dArr2[i3][0];
                double[][] dArr3 = this.C;
                return C1(d5, d6, d7, d8, dArr3[i2][0], dArr3[i3][0]);
            }
            i2 = i3;
        }
        return 0.0d;
    }

    @Override // defpackage.t40
    public final void j0(double d, double[] dArr) {
        double[] dArr2 = this.A;
        int length = dArr2.length;
        double[][] dArr3 = this.B;
        int i = 0;
        int length2 = dArr3[0].length;
        if (d <= dArr2[0]) {
            for (int i2 = 0; i2 < length2; i2++) {
                dArr[i2] = dArr3[0][i2];
            }
            return;
        }
        int i3 = length - 1;
        if (d >= dArr2[i3]) {
            while (i < length2) {
                dArr[i] = dArr3[i3][i];
                i++;
            }
            return;
        }
        int i4 = 0;
        while (i4 < i3) {
            if (d == dArr2[i4]) {
                for (int i5 = 0; i5 < length2; i5++) {
                    dArr[i5] = dArr3[i4][i5];
                }
            }
            int i6 = i4 + 1;
            double d2 = dArr2[i6];
            if (d < d2) {
                double d3 = dArr2[i4];
                double d4 = d2 - d3;
                double d5 = (d - d3) / d4;
                while (i < length2) {
                    double d6 = dArr3[i4][i];
                    double d7 = dArr3[i6][i];
                    double[][] dArr4 = this.C;
                    dArr[i] = C1(d4, d5, d6, d7, dArr4[i4][i], dArr4[i6][i]);
                    i++;
                }
                return;
            }
            i4 = i6;
        }
    }

    @Override // defpackage.t40
    public final void k0(double d, float[] fArr) {
        double[] dArr = this.A;
        int length = dArr.length;
        double[][] dArr2 = this.B;
        int i = 0;
        int length2 = dArr2[0].length;
        if (d <= dArr[0]) {
            for (int i2 = 0; i2 < length2; i2++) {
                fArr[i2] = (float) dArr2[0][i2];
            }
            return;
        }
        int i3 = length - 1;
        if (d >= dArr[i3]) {
            while (i < length2) {
                fArr[i] = (float) dArr2[i3][i];
                i++;
            }
            return;
        }
        int i4 = 0;
        while (i4 < i3) {
            if (d == dArr[i4]) {
                for (int i5 = 0; i5 < length2; i5++) {
                    fArr[i5] = (float) dArr2[i4][i5];
                }
            }
            int i6 = i4 + 1;
            double d2 = dArr[i6];
            if (d < d2) {
                double d3 = dArr[i4];
                double d4 = d2 - d3;
                double d5 = (d - d3) / d4;
                while (i < length2) {
                    double d6 = dArr2[i4][i];
                    double d7 = dArr2[i6][i];
                    double[][] dArr3 = this.C;
                    fArr[i] = (float) C1(d4, d5, d6, d7, dArr3[i4][i], dArr3[i6][i]);
                    i++;
                }
                return;
            }
            i4 = i6;
        }
    }

    @Override // defpackage.t40
    public final double n0(double d) {
        double[] dArr = this.A;
        int length = dArr.length;
        double d2 = dArr[0];
        if (d >= d2) {
            d2 = dArr[length - 1];
            if (d < d2) {
                d2 = d;
            }
        }
        int i = 0;
        while (i < length - 1) {
            int i2 = i + 1;
            double d3 = dArr[i2];
            if (d2 <= d3) {
                double d4 = dArr[i];
                double d5 = d3 - d4;
                double[][] dArr2 = this.B;
                double d6 = dArr2[i][0];
                double d7 = dArr2[i2][0];
                double[][] dArr3 = this.C;
                return B1(d5, (d2 - d4) / d5, d6, d7, dArr3[i][0], dArr3[i2][0]) / d5;
            }
            i = i2;
        }
        return 0.0d;
    }

    @Override // defpackage.t40
    public final void o0(double d, double[] dArr) {
        double[] dArr2 = this.A;
        int length = dArr2.length;
        double[][] dArr3 = this.B;
        int length2 = dArr3[0].length;
        double d2 = dArr2[0];
        if (d > d2) {
            d2 = dArr2[length - 1];
            if (d < d2) {
                d2 = d;
            }
        }
        int i = 0;
        while (i < length - 1) {
            int i2 = i + 1;
            double d3 = dArr2[i2];
            if (d2 <= d3) {
                double d4 = dArr2[i];
                double d5 = d3 - d4;
                double d6 = (d2 - d4) / d5;
                for (int i3 = 0; i3 < length2; i3++) {
                    double d7 = dArr3[i][i3];
                    double d8 = dArr3[i2][i3];
                    double[][] dArr4 = this.C;
                    dArr[i3] = B1(d5, d6, d7, d8, dArr4[i][i3], dArr4[i2][i3]) / d5;
                }
                return;
            }
            i = i2;
        }
    }

    @Override // defpackage.t40
    public final double[] r0() {
        return this.A;
    }
}
