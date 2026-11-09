package defpackage;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintAttribute$AttributeType;
import androidx.constraintlayout.widget.c;
import androidx.constraintlayout.widget.d;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class i70 implements Comparable {
    public int d;
    public float b = 1.0f;
    public int c = 0;
    public float e = 0.0f;
    public float f = 0.0f;
    public float g = 0.0f;
    public float h = 0.0f;
    public float i = 1.0f;
    public float j = 1.0f;
    public float k = Float.NaN;
    public float l = Float.NaN;
    public float m = 0.0f;
    public float n = 0.0f;
    public float o = 0.0f;
    public float p = Float.NaN;
    public float q = Float.NaN;
    public final LinkedHashMap r = new LinkedHashMap();

    public static boolean b(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public final void a(HashMap map, int i) {
        ek0 ek0Var;
        for (String str : map.keySet()) {
            ek0Var = (ek0) map.get(str);
            str.getClass();
            switch (str) {
                case "rotationX":
                    ek0Var.b(i, Float.isNaN(this.g) ? 0.0f : this.g);
                    break;
                case "rotationY":
                    ek0Var.b(i, Float.isNaN(this.h) ? 0.0f : this.h);
                    break;
                case "translationX":
                    ek0Var.b(i, Float.isNaN(this.m) ? 0.0f : this.m);
                    break;
                case "translationY":
                    ek0Var.b(i, Float.isNaN(this.n) ? 0.0f : this.n);
                    break;
                case "translationZ":
                    ek0Var.b(i, Float.isNaN(this.o) ? 0.0f : this.o);
                    break;
                case "progress":
                    ek0Var.b(i, Float.isNaN(this.q) ? 0.0f : this.q);
                    break;
                case "scaleX":
                    ek0Var.b(i, Float.isNaN(this.i) ? 1.0f : this.i);
                    break;
                case "scaleY":
                    ek0Var.b(i, Float.isNaN(this.j) ? 1.0f : this.j);
                    break;
                case "transformPivotX":
                    ek0Var.b(i, Float.isNaN(this.k) ? 0.0f : this.k);
                    break;
                case "transformPivotY":
                    ek0Var.b(i, Float.isNaN(this.l) ? 0.0f : this.l);
                    break;
                case "rotation":
                    ek0Var.b(i, Float.isNaN(this.f) ? 0.0f : this.f);
                    break;
                case "elevation":
                    ek0Var.b(i, Float.isNaN(this.e) ? 0.0f : this.e);
                    break;
                case "transitionPathRotate":
                    ek0Var.b(i, Float.isNaN(this.p) ? 0.0f : this.p);
                    break;
                case "alpha":
                    ek0Var.b(i, Float.isNaN(this.b) ? 1.0f : this.b);
                    break;
                default:
                    if (!str.startsWith("CUSTOM")) {
                        Log.e("MotionPaths", "UNKNOWN spline ".concat(str));
                        break;
                    } else {
                        String str2 = str.split(",")[1];
                        LinkedHashMap linkedHashMap = this.r;
                        if (linkedHashMap.containsKey(str2)) {
                            cg cgVar = (cg) linkedHashMap.get(str2);
                            if (ek0Var instanceof bk0) {
                                ((bk0) ek0Var).f.append(i, cgVar);
                            } else {
                                Log.e("MotionPaths", str + " splineSet not a CustomSet frame = " + i + ", value" + cgVar.b() + ek0Var);
                            }
                        } else {
                            Log.e("MotionPaths", "UNKNOWN customName " + str2);
                        }
                        break;
                    }
                    break;
            }
        }
    }

    public final void c(mg mgVar, d dVar, int i) {
        mgVar.n();
        mgVar.o();
        c cVarG = dVar.g(i);
        jg jgVar = cVarG.b;
        int i2 = jgVar.c;
        this.c = i2;
        int i3 = jgVar.b;
        this.d = i3;
        this.b = (i3 == 0 || i2 != 0) ? jgVar.d : 0.0f;
        kg kgVar = cVarG.e;
        boolean z = kgVar.l;
        this.e = kgVar.m;
        this.f = kgVar.b;
        this.g = kgVar.c;
        this.h = kgVar.d;
        this.i = kgVar.e;
        this.j = kgVar.f;
        this.k = kgVar.g;
        this.l = kgVar.h;
        this.m = kgVar.i;
        this.n = kgVar.j;
        this.o = kgVar.k;
        ig igVar = cVarG.c;
        nm.c(igVar.c);
        this.p = igVar.g;
        this.q = cVarG.b.e;
        for (String str : cVarG.f.keySet()) {
            cg cgVar = (cg) cVarG.f.get(str);
            if (cgVar.b != ConstraintAttribute$AttributeType.STRING_TYPE) {
                this.r.put(str, cgVar);
            }
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        ((i70) obj).getClass();
        return Float.compare(0.0f, 0.0f);
    }
}
