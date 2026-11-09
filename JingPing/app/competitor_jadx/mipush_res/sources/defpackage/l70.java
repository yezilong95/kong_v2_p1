package defpackage;

import android.view.View;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class l70 {
    public i70 a;
    public String b;
    public final ArrayList c = new ArrayList();

    public final float a(float f) {
        i70 i70Var = this.a;
        t40 t40Var = i70Var.f;
        if (t40Var != null) {
            t40Var.j0(f, i70Var.g);
        } else {
            double[] dArr = i70Var.g;
            dArr[0] = i70Var.e[0];
            dArr[1] = i70Var.b[0];
        }
        return (float) ((i70Var.a.J(f) * i70Var.g[1]) + i70Var.g[0]);
    }

    public final float b(float f) {
        char c;
        i70 i70Var = this.a;
        t40 t40Var = i70Var.f;
        if (t40Var != null) {
            double d = f;
            t40Var.o0(d, i70Var.h);
            i70Var.f.j0(d, i70Var.g);
        } else {
            double[] dArr = i70Var.h;
            dArr[0] = 0.0d;
            dArr[1] = 0.0d;
        }
        double d2 = f;
        ou ouVar = i70Var.a;
        double dJ = ouVar.J(d2);
        ouVar.getClass();
        double d3 = 0.0d;
        double d4 = d2 <= 0.0d ? 1.0E-5d : d2 >= 1.0d ? 0.999999d : d2;
        int iBinarySearch = Arrays.binarySearch((double[]) ouVar.G, d4);
        if (iBinarySearch <= 0 && iBinarySearch != 0) {
            int i = -iBinarySearch;
            int i2 = i - 1;
            float[] fArr = (float[]) ouVar.F;
            float f2 = fArr[i2];
            int i3 = i - 2;
            float f3 = fArr[i3];
            c = 0;
            double[] dArr2 = (double[]) ouVar.G;
            double d5 = dArr2[i2];
            double d6 = dArr2[i3];
            double d7 = ((double) (f2 - f3)) / (d5 - d6);
            d3 = (((double) f3) - (d7 * d6)) + (d4 * d7);
        } else {
            c = 0;
        }
        double dCos = Math.cos(ouVar.G(d2) * 6.283185307179586d) * d3 * 6.283185307179586d;
        double[] dArr3 = i70Var.h;
        return (float) ((dCos * i70Var.g[1]) + (dJ * dArr3[1]) + dArr3[c]);
    }

    public abstract void c(View view, float f);

    public final String toString() {
        String str = this.b;
        new DecimalFormat("##.##");
        Iterator it = this.c.iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
        return str;
    }
}
