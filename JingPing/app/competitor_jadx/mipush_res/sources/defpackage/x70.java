package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class x70 {
    public final w70 a;
    public final List b;
    public final List c;
    public final float[] d;
    public final float[] e;
    public final float f;
    public final float g;

    public x70(w70 w70Var, ArrayList arrayList, ArrayList arrayList2) {
        this.a = w70Var;
        this.b = Collections.unmodifiableList(arrayList);
        this.c = Collections.unmodifiableList(arrayList2);
        float f = ((w70) arrayList.get(arrayList.size() - 1)).b().a - w70Var.b().a;
        this.f = f;
        float f2 = w70Var.d().a - ((w70) arrayList2.get(arrayList2.size() - 1)).d().a;
        this.g = f2;
        this.d = d(f, arrayList, true);
        this.e = d(f2, arrayList2, false);
    }

    public static float[] d(float f, ArrayList arrayList, boolean z) {
        int size = arrayList.size();
        float[] fArr = new float[size];
        int i = 1;
        while (i < size) {
            int i2 = i - 1;
            w70 w70Var = (w70) arrayList.get(i2);
            w70 w70Var2 = (w70) arrayList.get(i);
            fArr[i] = i == size + (-1) ? 1.0f : fArr[i2] + ((z ? w70Var2.b().a - w70Var.b().a : w70Var.d().a - w70Var2.d().a) / f);
            i++;
        }
        return fArr;
    }

    public static w70 e(w70 w70Var, int i, int i2, float f, int i3, int i4, float f2) {
        ArrayList arrayList = new ArrayList(w70Var.b);
        arrayList.add(i2, (v70) arrayList.remove(i));
        u70 u70Var = new u70(w70Var.a, f2);
        float f3 = f;
        int i5 = 0;
        while (i5 < arrayList.size()) {
            v70 v70Var = (v70) arrayList.get(i5);
            float f4 = v70Var.d;
            u70Var.b((f4 / 2.0f) + f3, v70Var.c, f4, i5 >= i3 && i5 <= i4, v70Var.e, v70Var.f, 0.0f, 0.0f);
            f3 += v70Var.d;
            i5++;
        }
        return u70Var.d();
    }

    public static w70 f(w70 w70Var, float f, float f2, boolean z, float f3) {
        int i;
        List list = w70Var.b;
        ArrayList arrayList = new ArrayList(list);
        float f4 = w70Var.a;
        u70 u70Var = new u70(f4, f2);
        Iterator it = list.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (((v70) it.next()).e) {
                i2++;
            }
        }
        float size = f / (list.size() - i2);
        float f5 = z ? f : 0.0f;
        int i3 = 0;
        while (i3 < arrayList.size()) {
            v70 v70Var = (v70) arrayList.get(i3);
            if (v70Var.e) {
                i = i3;
                u70Var.b(v70Var.b, v70Var.c, v70Var.d, false, true, v70Var.f, 0.0f, 0.0f);
            } else {
                i = i3;
                boolean z2 = i >= w70Var.c && i <= w70Var.d;
                float f6 = v70Var.d - size;
                float fA = qc.a(f6, f4, f3);
                float f7 = (f6 / 2.0f) + f5;
                float f8 = f7 - v70Var.b;
                float f9 = f8;
                if (!z) {
                    f8 = 0.0f;
                }
                if (z) {
                    f9 = 0.0f;
                }
                u70Var.b(f7, fA, f6, z2, false, v70Var.f, f8, f9);
                f5 += f6;
            }
            i3 = i + 1;
        }
        return u70Var.d();
    }

    public final w70 a() {
        return (w70) this.c.get(r0.size() - 1);
    }

    public final w70 b(float f, float f2, float f3) {
        float fB;
        List list;
        float[] fArr;
        float[] fArr2;
        float f4 = this.f;
        float f5 = f2 + f4;
        float f6 = this.g;
        float f7 = f3 - f6;
        float f8 = c().a().g;
        float f9 = a().c().h;
        if (f4 == f8) {
            f5 += f8;
        }
        if (f6 == f9) {
            f7 -= f9;
        }
        if (f < f5) {
            fB = y4.b(1.0f, 0.0f, f2, f5, f);
            list = this.b;
            fArr = this.d;
        } else {
            if (f <= f7) {
                return this.a;
            }
            fB = y4.b(0.0f, 1.0f, f7, f3, f);
            list = this.c;
            fArr = this.e;
        }
        int size = list.size();
        float f10 = fArr[0];
        int i = 1;
        while (true) {
            if (i >= size) {
                fArr2 = new float[]{0.0f, 0.0f, 0.0f};
                break;
            }
            float f11 = fArr[i];
            if (fB <= f11) {
                fArr2 = new float[]{y4.b(0.0f, 1.0f, f10, f11, fB), i - 1, i};
                break;
            }
            i++;
            f10 = f11;
        }
        w70 w70Var = (w70) list.get((int) fArr2[1]);
        w70 w70Var2 = (w70) list.get((int) fArr2[2]);
        float f12 = fArr2[0];
        if (w70Var.a != w70Var2.a) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same item size.");
        }
        List list2 = w70Var.b;
        int size2 = list2.size();
        List list3 = w70Var2.b;
        if (size2 != list3.size()) {
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same number of keylines.");
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list2.size(); i2++) {
            v70 v70Var = (v70) list2.get(i2);
            v70 v70Var2 = (v70) list3.get(i2);
            arrayList.add(new v70(y4.a(v70Var.a, v70Var2.a, f12), y4.a(v70Var.b, v70Var2.b, f12), y4.a(v70Var.c, v70Var2.c, f12), y4.a(v70Var.d, v70Var2.d, f12), false, 0.0f, 0.0f, 0.0f));
        }
        return new w70(w70Var.a, arrayList, y4.c(w70Var.c, f12, w70Var2.c), y4.c(w70Var.d, f12, w70Var2.d));
    }

    public final w70 c() {
        return (w70) this.b.get(r0.size() - 1);
    }
}
