package defpackage;

import android.view.View;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class w00 {
    public t00 a;
    public String b;
    public final ArrayList c = new ArrayList();

    public final float a(float f) {
        t00 t00Var = this.a;
        nv0 nv0Var = t00Var.f;
        if (nv0Var != null) {
            nv0Var.t(f, t00Var.g);
        } else {
            double[] dArr = t00Var.g;
            dArr[0] = t00Var.e[0];
            dArr[1] = t00Var.b[0];
        }
        return (float) ((t00Var.a.H(f) * t00Var.g[1]) + t00Var.g[0]);
    }

    public final float b(float f) {
        char c;
        t00 t00Var = this.a;
        nv0 nv0Var = t00Var.f;
        if (nv0Var != null) {
            double d = f;
            nv0Var.w(d, t00Var.h);
            t00Var.f.t(d, t00Var.g);
        } else {
            double[] dArr = t00Var.h;
            dArr[0] = 0.0d;
            dArr[1] = 0.0d;
        }
        double d2 = f;
        po poVar = t00Var.a;
        double dH = poVar.H(d2);
        poVar.getClass();
        double d3 = 0.0d;
        double d4 = d2 <= 0.0d ? 1.0E-5d : d2 >= 1.0d ? 0.999999d : d2;
        int iBinarySearch = Arrays.binarySearch((double[]) poVar.d, d4);
        if (iBinarySearch <= 0 && iBinarySearch != 0) {
            int i = -iBinarySearch;
            int i2 = i - 1;
            float[] fArr = (float[]) poVar.c;
            float f2 = fArr[i2];
            int i3 = i - 2;
            float f3 = fArr[i3];
            c = 0;
            double[] dArr2 = (double[]) poVar.d;
            double d5 = dArr2[i2];
            double d6 = dArr2[i3];
            double d7 = ((double) (f2 - f3)) / (d5 - d6);
            d3 = (((double) f3) - (d7 * d6)) + (d4 * d7);
        } else {
            c = 0;
        }
        double dCos = Math.cos(poVar.E(d2) * 6.283185307179586d) * d3 * 6.283185307179586d;
        double[] dArr3 = t00Var.h;
        return (float) ((dCos * t00Var.g[1]) + (dH * dArr3[1]) + dArr3[c]);
    }

    public abstract void c(View view, float f);

    public final String toString() {
        String str = this.b;
        new DecimalFormat("##.##");
        Iterator it = this.c.iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
        return str;
    }
}
