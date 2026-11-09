package defpackage;

import android.graphics.Matrix;
import android.graphics.Path;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class jv0 {
    public float a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;
    public final ArrayList g = new ArrayList();
    public final ArrayList h = new ArrayList();

    public jv0() {
        e(0.0f, 0.0f, 270.0f, 0.0f);
    }

    public final void a(float f, float f2, float f3, float f4, float f5, float f6) {
        fv0 fv0Var = new fv0(f, f2, f3, f4);
        fv0Var.f = f5;
        fv0Var.g = f6;
        this.g.add(fv0Var);
        dv0 dv0Var = new dv0(fv0Var);
        float f7 = f5 + f6;
        boolean z = f6 < 0.0f;
        if (z) {
            f5 = (f5 + 180.0f) % 360.0f;
        }
        float f8 = z ? (180.0f + f7) % 360.0f : f7;
        b(f5);
        this.h.add(dv0Var);
        this.e = f8;
        double d = f7;
        this.c = (((f3 - f) / 2.0f) * ((float) Math.cos(Math.toRadians(d)))) + ((f + f3) * 0.5f);
        this.d = (((f4 - f2) / 2.0f) * ((float) Math.sin(Math.toRadians(d)))) + ((f2 + f4) * 0.5f);
    }

    public final void b(float f) {
        float f2 = this.e;
        if (f2 == f) {
            return;
        }
        float f3 = ((f - f2) + 360.0f) % 360.0f;
        if (f3 > 180.0f) {
            return;
        }
        float f4 = this.c;
        float f5 = this.d;
        fv0 fv0Var = new fv0(f4, f5, f4, f5);
        fv0Var.f = this.e;
        fv0Var.g = f3;
        this.h.add(new dv0(fv0Var));
        this.e = f;
    }

    public final void c(Matrix matrix, Path path) {
        ArrayList arrayList = this.g;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((hv0) arrayList.get(i)).a(matrix, path);
        }
    }

    public final void d(float f, float f2) {
        gv0 gv0Var = new gv0();
        gv0Var.b = f;
        gv0Var.c = f2;
        this.g.add(gv0Var);
        ev0 ev0Var = new ev0(gv0Var, this.c, this.d);
        float fB = ev0Var.b() + 270.0f;
        float fB2 = ev0Var.b() + 270.0f;
        b(fB);
        this.h.add(ev0Var);
        this.e = fB2;
        this.c = f;
        this.d = f2;
    }

    public final void e(float f, float f2, float f3, float f4) {
        this.a = f;
        this.b = f2;
        this.c = f;
        this.d = f2;
        this.e = f3;
        this.f = (f3 + f4) % 360.0f;
        this.g.clear();
        this.h.clear();
    }
}
