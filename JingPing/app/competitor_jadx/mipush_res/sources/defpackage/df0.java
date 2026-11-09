package defpackage;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintAttribute$AttributeType;
import androidx.constraintlayout.widget.c;
import androidx.constraintlayout.widget.d;
import androidx.work.impl.WorkDatabaseVersions;
import io.nats.client.support.NatsConstants;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class df0 implements Comparable {
    public int G;
    public float E = 1.0f;
    public int F = 0;
    public float H = 0.0f;
    public float I = 0.0f;
    public float J = 0.0f;
    public float K = 0.0f;
    public float L = 1.0f;
    public float M = 1.0f;
    public float N = Float.NaN;
    public float O = Float.NaN;
    public float P = 0.0f;
    public float Q = 0.0f;
    public float R = 0.0f;
    public float S = Float.NaN;
    public float T = Float.NaN;
    public final LinkedHashMap U = new LinkedHashMap();

    public static boolean b(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void a(HashMap map, int i) {
        for (String str : map.keySet()) {
            sw0 sw0Var = (sw0) map.get(str);
            str.getClass();
            byte b = -1;
            switch (str.hashCode()) {
                case -1249320806:
                    if (str.equals("rotationX")) {
                        b = 0;
                    }
                    break;
                case -1249320805:
                    if (str.equals("rotationY")) {
                        b = 1;
                    }
                    break;
                case -1225497657:
                    if (str.equals("translationX")) {
                        b = 2;
                    }
                    break;
                case -1225497656:
                    if (str.equals("translationY")) {
                        b = 3;
                    }
                    break;
                case -1225497655:
                    if (str.equals("translationZ")) {
                        b = 4;
                    }
                    break;
                case -1001078227:
                    if (str.equals("progress")) {
                        b = 5;
                    }
                    break;
                case -908189618:
                    if (str.equals("scaleX")) {
                        b = 6;
                    }
                    break;
                case -908189617:
                    if (str.equals("scaleY")) {
                        b = 7;
                    }
                    break;
                case -760884510:
                    if (str.equals("transformPivotX")) {
                        b = 8;
                    }
                    break;
                case -760884509:
                    if (str.equals("transformPivotY")) {
                        b = 9;
                    }
                    break;
                case -40300674:
                    if (str.equals("rotation")) {
                        b = 10;
                    }
                    break;
                case -4379043:
                    if (str.equals("elevation")) {
                        b = 11;
                    }
                    break;
                case 37232917:
                    if (str.equals("transitionPathRotate")) {
                        b = 12;
                    }
                    break;
                case 92909918:
                    if (str.equals("alpha")) {
                        b = NatsConstants.CR;
                    }
                    break;
            }
            switch (b) {
                case 0:
                    sw0Var.b(i, Float.isNaN(this.J) ? 0.0f : this.J);
                    break;
                case 1:
                    sw0Var.b(i, Float.isNaN(this.K) ? 0.0f : this.K);
                    break;
                case 2:
                    sw0Var.b(i, Float.isNaN(this.P) ? 0.0f : this.P);
                    break;
                case 3:
                    sw0Var.b(i, Float.isNaN(this.Q) ? 0.0f : this.Q);
                    break;
                case 4:
                    sw0Var.b(i, Float.isNaN(this.R) ? 0.0f : this.R);
                    break;
                case 5:
                    sw0Var.b(i, Float.isNaN(this.T) ? 0.0f : this.T);
                    break;
                case 6:
                    sw0Var.b(i, Float.isNaN(this.L) ? 1.0f : this.L);
                    break;
                case 7:
                    sw0Var.b(i, Float.isNaN(this.M) ? 1.0f : this.M);
                    break;
                case 8:
                    sw0Var.b(i, Float.isNaN(this.N) ? 0.0f : this.N);
                    break;
                case WorkDatabaseVersions.VERSION_9 /* 9 */:
                    sw0Var.b(i, Float.isNaN(this.O) ? 0.0f : this.O);
                    break;
                case 10:
                    sw0Var.b(i, Float.isNaN(this.I) ? 0.0f : this.I);
                    break;
                case WorkDatabaseVersions.VERSION_11 /* 11 */:
                    sw0Var.b(i, Float.isNaN(this.H) ? 0.0f : this.H);
                    break;
                case WorkDatabaseVersions.VERSION_12 /* 12 */:
                    sw0Var.b(i, Float.isNaN(this.S) ? 0.0f : this.S);
                    break;
                case WorkDatabaseVersions.VERSION_13 /* 13 */:
                    sw0Var.b(i, Float.isNaN(this.E) ? 1.0f : this.E);
                    break;
                default:
                    if (str.startsWith("CUSTOM")) {
                        String str2 = str.split(",")[1];
                        LinkedHashMap linkedHashMap = this.U;
                        if (linkedHashMap.containsKey(str2)) {
                            oh ohVar = (oh) linkedHashMap.get(str2);
                            if (sw0Var instanceof pw0) {
                                ((pw0) sw0Var).f.append(i, ohVar);
                            } else {
                                Log.e("MotionPaths", str + " splineSet not a CustomSet frame = " + i + ", value" + ohVar.b() + sw0Var);
                            }
                        } else {
                            Log.e("MotionPaths", "UNKNOWN customName " + str2);
                        }
                    } else {
                        Log.e("MotionPaths", "UNKNOWN spline ".concat(str));
                    }
                    break;
            }
        }
    }

    public final void c(yh yhVar, d dVar, int i) {
        yhVar.n();
        yhVar.o();
        c cVarG = dVar.g(i);
        vh vhVar = cVarG.b;
        int i2 = vhVar.c;
        this.F = i2;
        int i3 = vhVar.b;
        this.G = i3;
        this.E = (i3 == 0 || i2 != 0) ? vhVar.d : 0.0f;
        wh whVar = cVarG.e;
        boolean z = whVar.l;
        this.H = whVar.m;
        this.I = whVar.b;
        this.J = whVar.c;
        this.K = whVar.d;
        this.L = whVar.e;
        this.M = whVar.f;
        this.N = whVar.g;
        this.O = whVar.h;
        this.P = whVar.i;
        this.Q = whVar.j;
        this.R = whVar.k;
        uh uhVar = cVarG.c;
        vr.d(uhVar.c);
        this.S = uhVar.g;
        this.T = cVarG.b.e;
        for (String str : cVarG.f.keySet()) {
            oh ohVar = (oh) cVarG.f.get(str);
            if (ohVar.b != ConstraintAttribute$AttributeType.STRING_TYPE) {
                this.U.put(str, ohVar);
            }
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        ((df0) obj).getClass();
        return Float.compare(0.0f, 0.0f);
    }
}
