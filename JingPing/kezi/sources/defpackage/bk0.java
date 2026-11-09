package defpackage;

import android.util.SparseArray;
import android.view.View;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public final class bk0 extends ek0 {
    public SparseArray f;
    public float[] g;

    @Override // defpackage.ek0
    public final void b(int i, float f) {
        throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
    }

    @Override // defpackage.ek0
    public final void c(View view, float f) {
        this.a.u(f, this.g);
        ((cg) this.f.valueAt(0)).g(view, this.g);
    }

    @Override // defpackage.ek0
    public final void d(int i) {
        SparseArray sparseArray = this.f;
        int size = sparseArray.size();
        int iD = ((cg) sparseArray.valueAt(0)).d();
        double[] dArr = new double[size];
        this.g = new float[iD];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, iD);
        for (int i2 = 0; i2 < size; i2++) {
            int iKeyAt = sparseArray.keyAt(i2);
            cg cgVar = (cg) sparseArray.valueAt(i2);
            dArr[i2] = ((double) iKeyAt) * 0.01d;
            cgVar.c(this.g);
            int i3 = 0;
            while (true) {
                if (i3 < this.g.length) {
                    dArr2[i2][i3] = r7[i3];
                    i3++;
                }
            }
        }
        this.a = nv0.o(i, dArr, dArr2);
    }
}
