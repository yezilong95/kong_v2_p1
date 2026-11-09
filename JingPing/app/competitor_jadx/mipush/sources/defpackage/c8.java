package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class c8 extends t40 {
    public final /* synthetic */ int A = 1;
    public double[] B;
    public Object[] C;

    public /* synthetic */ c8() {
    }

    @Override // defpackage.t40
    public final double i0(double d) {
        switch (this.A) {
            case 0:
                b8[] b8VarArr = (b8[]) this.C;
                double d2 = b8VarArr[0].c;
                if (d < d2) {
                    d = d2;
                } else if (d > b8VarArr[b8VarArr.length - 1].d) {
                    d = b8VarArr[b8VarArr.length - 1].d;
                }
                for (int i = 0; i < b8VarArr.length; i++) {
                    b8 b8Var = b8VarArr[i];
                    if (d <= b8Var.d) {
                        if (b8Var.r) {
                            return b8Var.b(d);
                        }
                        b8Var.d(d);
                        b8 b8Var2 = b8VarArr[i];
                        return b8Var2.l + (b8Var2.j * b8Var2.o);
                    }
                }
                return Double.NaN;
            default:
                double[] dArr = this.B;
                int length = dArr.length;
                double d3 = dArr[0];
                double[][] dArr2 = (double[][]) this.C;
                if (d <= d3) {
                    return dArr2[0][0];
                }
                int i2 = length - 1;
                if (d >= dArr[i2]) {
                    return dArr2[i2][0];
                }
                int i3 = 0;
                while (i3 < i2) {
                    double d4 = dArr[i3];
                    if (d == d4) {
                        return dArr2[i3][0];
                    }
                    int i4 = i3 + 1;
                    double d5 = dArr[i4];
                    if (d < d5) {
                        double d6 = (d - d4) / (d5 - d4);
                        return (dArr2[i4][0] * d6) + ((1.0d - d6) * dArr2[i3][0]);
                    }
                    i3 = i4;
                }
                return 0.0d;
        }
    }

    @Override // defpackage.t40
    public final void j0(double d, double[] dArr) {
        switch (this.A) {
            case 0:
                b8[] b8VarArr = (b8[]) this.C;
                double d2 = b8VarArr[0].c;
                if (d < d2) {
                    d = d2;
                }
                if (d > b8VarArr[b8VarArr.length - 1].d) {
                    d = b8VarArr[b8VarArr.length - 1].d;
                }
                for (int i = 0; i < b8VarArr.length; i++) {
                    b8 b8Var = b8VarArr[i];
                    if (d <= b8Var.d) {
                        if (b8Var.r) {
                            dArr[0] = b8Var.b(d);
                            dArr[1] = b8VarArr[i].c(d);
                        } else {
                            b8Var.d(d);
                            b8 b8Var2 = b8VarArr[i];
                            dArr[0] = (b8Var2.j * b8Var2.o) + b8Var2.l;
                            dArr[1] = (b8Var2.k * b8Var2.p) + b8Var2.m;
                        }
                    }
                    break;
                }
                break;
            default:
                double[] dArr2 = this.B;
                int length = dArr2.length;
                double[][] dArr3 = (double[][]) this.C;
                int i2 = 0;
                int length2 = dArr3[0].length;
                if (d <= dArr2[0]) {
                    for (int i3 = 0; i3 < length2; i3++) {
                        dArr[i3] = dArr3[0][i3];
                    }
                } else {
                    int i4 = length - 1;
                    if (d >= dArr2[i4]) {
                        while (i2 < length2) {
                            dArr[i2] = dArr3[i4][i2];
                            i2++;
                        }
                    } else {
                        int i5 = 0;
                        while (i5 < i4) {
                            if (d == dArr2[i5]) {
                                for (int i6 = 0; i6 < length2; i6++) {
                                    dArr[i6] = dArr3[i5][i6];
                                }
                            }
                            int i7 = i5 + 1;
                            double d3 = dArr2[i7];
                            if (d < d3) {
                                double d4 = dArr2[i5];
                                double d5 = (d - d4) / (d3 - d4);
                                while (i2 < length2) {
                                    dArr[i2] = (dArr3[i7][i2] * d5) + ((1.0d - d5) * dArr3[i5][i2]);
                                    i2++;
                                }
                            } else {
                                i5 = i7;
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override // defpackage.t40
    public final void k0(double d, float[] fArr) {
        switch (this.A) {
            case 0:
                b8[] b8VarArr = (b8[]) this.C;
                double d2 = b8VarArr[0].c;
                if (d < d2) {
                    d = d2;
                } else if (d > b8VarArr[b8VarArr.length - 1].d) {
                    d = b8VarArr[b8VarArr.length - 1].d;
                }
                for (int i = 0; i < b8VarArr.length; i++) {
                    b8 b8Var = b8VarArr[i];
                    if (d <= b8Var.d) {
                        if (b8Var.r) {
                            fArr[0] = (float) b8Var.b(d);
                            fArr[1] = (float) b8VarArr[i].c(d);
                        } else {
                            b8Var.d(d);
                            b8 b8Var2 = b8VarArr[i];
                            fArr[0] = (float) ((b8Var2.j * b8Var2.o) + b8Var2.l);
                            fArr[1] = (float) ((b8Var2.k * b8Var2.p) + b8Var2.m);
                        }
                    }
                    break;
                }
                break;
            default:
                double[] dArr = this.B;
                int length = dArr.length;
                double[][] dArr2 = (double[][]) this.C;
                int i2 = 0;
                int length2 = dArr2[0].length;
                if (d <= dArr[0]) {
                    for (int i3 = 0; i3 < length2; i3++) {
                        fArr[i3] = (float) dArr2[0][i3];
                    }
                } else {
                    int i4 = length - 1;
                    if (d >= dArr[i4]) {
                        while (i2 < length2) {
                            fArr[i2] = (float) dArr2[i4][i2];
                            i2++;
                        }
                    } else {
                        int i5 = 0;
                        while (i5 < i4) {
                            if (d == dArr[i5]) {
                                for (int i6 = 0; i6 < length2; i6++) {
                                    fArr[i6] = (float) dArr2[i5][i6];
                                }
                            }
                            int i7 = i5 + 1;
                            double d3 = dArr[i7];
                            if (d < d3) {
                                double d4 = dArr[i5];
                                double d5 = (d - d4) / (d3 - d4);
                                while (i2 < length2) {
                                    fArr[i2] = (float) ((dArr2[i7][i2] * d5) + ((1.0d - d5) * dArr2[i5][i2]));
                                    i2++;
                                }
                            } else {
                                i5 = i7;
                            }
                        }
                    }
                }
                break;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000f A[PHI: r3
      0x000f: PHI (r3v9 double) = (r3v3 double), (r3v5 double) binds: [B:5:0x000d, B:8:0x0017] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // defpackage.t40
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final double n0(double r9) {
        /*
            r8 = this;
            int r0 = r8.A
            switch(r0) {
                case 0: goto L3e;
                default: goto L5;
            }
        L5:
            double[] r0 = r8.B
            int r1 = r0.length
            r2 = 0
            r3 = r0[r2]
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 >= 0) goto L11
        Lf:
            r9 = r3
            goto L1a
        L11:
            int r3 = r1 + (-1)
            r3 = r0[r3]
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 < 0) goto L1a
            goto Lf
        L1a:
            r3 = r2
        L1b:
            int r4 = r1 + (-1)
            if (r3 >= r4) goto L3b
            int r4 = r3 + 1
            r5 = r0[r4]
            int r7 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r7 > 0) goto L39
            r9 = r0[r3]
            double r5 = r5 - r9
            java.lang.Object[] r9 = r8.C
            double[][] r9 = (double[][]) r9
            r10 = r9[r3]
            r0 = r10[r2]
            r9 = r9[r4]
            r2 = r9[r2]
            double r2 = r2 - r0
            double r2 = r2 / r5
            goto L3d
        L39:
            r3 = r4
            goto L1b
        L3b:
            r2 = 0
        L3d:
            return r2
        L3e:
            java.lang.Object[] r0 = r8.C
            b8[] r0 = (defpackage.b8[]) r0
            r1 = 0
            r2 = r0[r1]
            double r2 = r2.c
            int r4 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r4 >= 0) goto L4c
            r9 = r2
        L4c:
            int r2 = r0.length
            int r2 = r2 + (-1)
            r2 = r0[r2]
            double r2 = r2.d
            int r2 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r2 <= 0) goto L5e
            int r9 = r0.length
            int r9 = r9 + (-1)
            r9 = r0[r9]
            double r9 = r9.d
        L5e:
            int r2 = r0.length
            if (r1 >= r2) goto L7d
            r2 = r0[r1]
            double r3 = r2.d
            int r3 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r3 > 0) goto L7a
            boolean r3 = r2.r
            if (r3 == 0) goto L70
            double r9 = r2.l
            goto L7f
        L70:
            r2.d(r9)
            r9 = r0[r1]
            double r9 = r9.a()
            goto L7f
        L7a:
            int r1 = r1 + 1
            goto L5e
        L7d:
            r9 = 9221120237041090560(0x7ff8000000000000, double:NaN)
        L7f:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c8.n0(double):double");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0016 A[PHI: r5
      0x0016: PHI (r5v7 double) = (r5v1 double), (r5v3 double) binds: [B:5:0x0014, B:8:0x001e] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // defpackage.t40
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void o0(double r12, double[] r14) {
        /*
            r11 = this;
            int r0 = r11.A
            switch(r0) {
                case 0: goto L45;
                default: goto L5;
            }
        L5:
            double[] r0 = r11.B
            int r1 = r0.length
            java.lang.Object[] r2 = r11.C
            double[][] r2 = (double[][]) r2
            r3 = 0
            r4 = r2[r3]
            int r4 = r4.length
            r5 = r0[r3]
            int r7 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r7 > 0) goto L18
        L16:
            r12 = r5
            goto L21
        L18:
            int r5 = r1 + (-1)
            r5 = r0[r5]
            int r7 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r7 < 0) goto L21
            goto L16
        L21:
            r5 = r3
        L22:
            int r6 = r1 + (-1)
            if (r5 >= r6) goto L44
            int r6 = r5 + 1
            r7 = r0[r6]
            int r9 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r9 > 0) goto L42
            r12 = r0[r5]
            double r7 = r7 - r12
        L31:
            if (r3 >= r4) goto L44
            r12 = r2[r5]
            r0 = r12[r3]
            r12 = r2[r6]
            r9 = r12[r3]
            double r9 = r9 - r0
            double r9 = r9 / r7
            r14[r3] = r9
            int r3 = r3 + 1
            goto L31
        L42:
            r5 = r6
            goto L22
        L44:
            return
        L45:
            java.lang.Object[] r0 = r11.C
            b8[] r0 = (defpackage.b8[]) r0
            r1 = 0
            r2 = r0[r1]
            double r2 = r2.c
            int r4 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            r5 = 1
            if (r4 >= 0) goto L55
            r12 = r2
            goto L65
        L55:
            int r2 = r0.length
            int r2 = r2 - r5
            r2 = r0[r2]
            double r2 = r2.d
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r2 <= 0) goto L65
            int r12 = r0.length
            int r12 = r12 - r5
            r12 = r0[r12]
            double r12 = r12.d
        L65:
            r2 = r1
        L66:
            int r3 = r0.length
            if (r2 >= r3) goto Lac
            r3 = r0[r2]
            double r6 = r3.d
            int r4 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r4 > 0) goto La9
            boolean r4 = r3.r
            if (r4 == 0) goto L7e
            double r12 = r3.l
            r14[r1] = r12
            double r12 = r3.m
            r14[r5] = r12
            goto Lac
        L7e:
            r3.d(r12)
            r12 = r0[r2]
            double r12 = r12.a()
            r14[r1] = r12
            r12 = r0[r2]
            double r0 = r12.j
            double r2 = r12.p
            double r0 = r0 * r2
            double r2 = r12.k
            double r2 = -r2
            double r6 = r12.o
            double r2 = r2 * r6
            double r6 = r12.n
            double r0 = java.lang.Math.hypot(r0, r2)
            double r6 = r6 / r0
            boolean r12 = r12.q
            if (r12 == 0) goto La4
            double r12 = -r2
            double r12 = r12 * r6
            goto La6
        La4:
            double r12 = r2 * r6
        La6:
            r14[r5] = r12
            goto Lac
        La9:
            int r2 = r2 + 1
            goto L66
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c8.o0(double, double[]):void");
    }

    @Override // defpackage.t40
    public final double[] r0() {
        switch (this.A) {
        }
        return this.B;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARN: Type inference failed for: r0v0, types: [c8, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public c8(int[] r29, double[] r30, double[][] r31) {
        /*
            Method dump skipped, instruction units count: 396
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c8.<init>(int[], double[], double[][]):void");
    }
}
