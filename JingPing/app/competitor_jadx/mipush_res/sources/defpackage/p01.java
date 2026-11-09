package defpackage;

import android.util.SparseArray;
import android.view.View;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public final class p01 extends s01 {
    public SparseArray g;
    public SparseArray h;
    public float[] i;
    public float[] j;

    @Override // defpackage.s01
    public final boolean b(float f, long j, z8 z8Var, View view) {
        this.a.k0(f, this.i);
        float[] fArr = this.i;
        float f2 = fArr[fArr.length - 2];
        float f3 = fArr[fArr.length - 1];
        this.f = (float) (((((j - this.e) * 1.0E-9d) * ((double) f2)) + ((double) this.f)) % 1.0d);
        this.e = j;
        float fSin = (float) Math.sin(r0 * 6.2831855f);
        this.d = false;
        int i = 0;
        while (true) {
            float[] fArr2 = this.j;
            if (i >= fArr2.length) {
                break;
            }
            boolean z = this.d;
            float f4 = this.i[i];
            this.d = z | (((double) f4) != 0.0d);
            fArr2[i] = (f4 * fSin) + f3;
            i++;
        }
        ((oh) this.g.valueAt(0)).g(view, this.j);
        if (f2 != 0.0f) {
            this.d = true;
        }
        return this.d;
    }

    @Override // defpackage.s01
    public final void c(int i) {
        SparseArray sparseArray = this.g;
        int size = sparseArray.size();
        int iD = ((oh) sparseArray.valueAt(0)).d();
        double[] dArr = new double[size];
        int i2 = iD + 2;
        this.i = new float[i2];
        this.j = new float[iD];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, i2);
        for (int i3 = 0; i3 < size; i3++) {
            int iKeyAt = sparseArray.keyAt(i3);
            oh ohVar = (oh) sparseArray.valueAt(i3);
            float[] fArr = (float[]) this.h.valueAt(i3);
            dArr[i3] = ((double) iKeyAt) * 0.01d;
            ohVar.c(this.i);
            int i4 = 0;
            while (true) {
                if (i4 < this.i.length) {
                    dArr2[i3][i4] = r10[i4];
                    i4++;
                }
            }
            double[] dArr3 = dArr2[i3];
            dArr3[iD] = fArr[0];
            dArr3[iD + 1] = fArr[1];
        }
        this.a = t40.T(i, dArr, dArr2);
    }
}
