package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class f10 {
    public final e10 a;
    public final List b;
    public final List c;
    public final float[] d;
    public final float[] e;
    public final float f;
    public final float g;

    public f10(e10 e10Var, ArrayList arrayList, ArrayList arrayList2) {
        this.a = e10Var;
        this.b = Collections.unmodifiableList(arrayList);
        this.c = Collections.unmodifiableList(arrayList2);
        float f = ((e10) arrayList.get(arrayList.size() - 1)).b().a - e10Var.b().a;
        this.f = f;
        float f2 = e10Var.d().a - ((e10) arrayList2.get(arrayList2.size() - 1)).d().a;
        this.g = f2;
        this.d = a(f, arrayList, true);
        this.e = a(f2, arrayList2, false);
    }

    public static float[] a(float f, ArrayList arrayList, boolean z) {
        int size = arrayList.size();
        float[] fArr = new float[size];
        int i = 1;
        while (i < size) {
            int i2 = i - 1;
            e10 e10Var = (e10) arrayList.get(i2);
            e10 e10Var2 = (e10) arrayList.get(i);
            fArr[i] = i == size + (-1) ? 1.0f : fArr[i2] + ((z ? e10Var2.b().a - e10Var.b().a : e10Var.d().a - e10Var2.d().a) / f);
            i++;
        }
        return fArr;
    }

    public static e10 b(e10 e10Var, int i, int i2, float f, int i3, int i4) {
        ArrayList arrayList = new ArrayList(e10Var.b);
        arrayList.add(i2, (d10) arrayList.remove(i));
        c10 c10Var = new c10(e10Var.a);
        int i5 = 0;
        while (i5 < arrayList.size()) {
            d10 d10Var = (d10) arrayList.get(i5);
            float f2 = d10Var.d;
            c10Var.a((f2 / 2.0f) + f, d10Var.c, f2, i5 >= i3 && i5 <= i4);
            f += d10Var.d;
            i5++;
        }
        return c10Var.b();
    }
}
