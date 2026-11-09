package defpackage;

import android.util.Log;
import android.view.View;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class s01 {
    public t40 a;
    public String b;
    public final float[] c;
    public boolean d;
    public long e;
    public float f;

    public s01() {
        this.c = new float[3];
        this.d = false;
        this.f = Float.NaN;
    }

    public final float a(float f, long j, z8 z8Var, View view) {
        float[] fArr = this.c;
        this.a.k0(f, fArr);
        boolean z = true;
        float f2 = fArr[1];
        if (f2 == 0.0f) {
            this.d = false;
            return fArr[2];
        }
        if (Float.isNaN(this.f)) {
            String str = this.b;
            HashMap map = (HashMap) z8Var.F;
            float f3 = Float.NaN;
            if (map.containsKey(view)) {
                HashMap map2 = (HashMap) map.get(view);
                if (map2.containsKey(str)) {
                    float[] fArr2 = (float[]) map2.get(str);
                    if (fArr2.length > 0) {
                        f3 = fArr2[0];
                    }
                }
            }
            this.f = f3;
            if (Float.isNaN(f3)) {
                this.f = 0.0f;
            }
        }
        float f4 = (float) (((((j - this.e) * 1.0E-9d) * ((double) f2)) + ((double) this.f)) % 1.0d);
        this.f = f4;
        String str2 = this.b;
        HashMap map3 = (HashMap) z8Var.F;
        if (map3.containsKey(view)) {
            HashMap map4 = (HashMap) map3.get(view);
            if (map4.containsKey(str2)) {
                float[] fArrCopyOf = (float[]) map4.get(str2);
                if (fArrCopyOf.length <= 0) {
                    fArrCopyOf = Arrays.copyOf(fArrCopyOf, 1);
                }
                fArrCopyOf[0] = f4;
                map4.put(str2, fArrCopyOf);
            } else {
                map4.put(str2, new float[]{f4});
                map3.put(view, map4);
            }
        } else {
            HashMap map5 = new HashMap();
            map5.put(str2, new float[]{f4});
            map3.put(view, map5);
        }
        this.e = j;
        float f5 = fArr[0];
        float fSin = (((float) Math.sin(this.f * 6.2831855f)) * f5) + fArr[2];
        if (f5 == 0.0f && f2 == 0.0f) {
            z = false;
        }
        this.d = z;
        return fSin;
    }

    public abstract boolean b(float f, long j, z8 z8Var, View view);

    public void c(int i) {
        Log.e("SplineSet", "Error no points added to " + this.b);
    }

    public final String toString() {
        String str = this.b;
        new DecimalFormat("##.##");
        return str;
    }
}
