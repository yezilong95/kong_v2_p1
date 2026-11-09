package defpackage;

import androidx.constraintlayout.widget.ConstraintAttribute$AttributeType;
import androidx.constraintlayout.widget.c;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class s70 implements Comparable {
    public static final String[] o = {"position", "x", "y", "width", "height", "pathRotate"};
    public nm b;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public int c = 0;
    public float j = Float.NaN;
    public int k = -1;
    public final LinkedHashMap l = new LinkedHashMap();
    public double[] m = new double[18];
    public double[] n = new double[18];

    public static boolean b(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public static void d(float f, float f2, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f7 = (float) dArr[i];
            double d = dArr2[i];
            int i2 = iArr[i];
            if (i2 == 1) {
                f3 = f7;
            } else if (i2 == 2) {
                f5 = f7;
            } else if (i2 == 3) {
                f4 = f7;
            } else if (i2 == 4) {
                f6 = f7;
            }
        }
        float f8 = f3 - ((0.0f * f4) / 2.0f);
        float f9 = f5 - ((0.0f * f6) / 2.0f);
        fArr[0] = (((f4 * 1.0f) + f8) * f) + ((1.0f - f) * f8) + 0.0f;
        fArr[1] = (((f6 * 1.0f) + f9) * f2) + ((1.0f - f2) * f9) + 0.0f;
    }

    public final void a(c cVar) {
        this.b = nm.c(cVar.c.c);
        ig igVar = cVar.c;
        this.k = igVar.d;
        this.j = igVar.g;
        this.c = igVar.e;
        float f = cVar.b.e;
        for (String str : cVar.f.keySet()) {
            cg cgVar = (cg) cVar.f.get(str);
            if (cgVar.b != ConstraintAttribute$AttributeType.STRING_TYPE) {
                this.l.put(str, cgVar);
            }
        }
    }

    public final void c(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.f;
        float f2 = this.g;
        float f3 = this.h;
        float f4 = this.i;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f = f5;
            } else if (i3 == 2) {
                f2 = f5;
            } else if (i3 == 3) {
                f3 = f5;
            } else if (i3 == 4) {
                f4 = f5;
            }
        }
        fArr[i] = (f3 / 2.0f) + f + 0.0f;
        fArr[i + 1] = (f4 / 2.0f) + f2 + 0.0f;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Float.compare(this.e, ((s70) obj).e);
    }
}
