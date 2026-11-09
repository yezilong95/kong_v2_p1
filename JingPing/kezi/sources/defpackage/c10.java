package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class c10 {
    public final float a;
    public d10 c;
    public d10 d;
    public final ArrayList b = new ArrayList();
    public int e = -1;
    public int f = -1;
    public float g = 0.0f;

    public c10(float f) {
        this.a = f;
    }

    public final void a(float f, float f2, float f3, boolean z) {
        if (f3 <= 0.0f) {
            return;
        }
        d10 d10Var = new d10(Float.MIN_VALUE, f, f2, f3);
        ArrayList arrayList = this.b;
        if (z) {
            if (this.c == null) {
                this.c = d10Var;
                this.e = arrayList.size();
            }
            if (this.f != -1 && arrayList.size() - this.f > 1) {
                throw new IllegalArgumentException("Keylines marked as focal must be placed next to each other. There cannot be non-focal keylines between focal keylines.");
            }
            if (f3 != this.c.d) {
                throw new IllegalArgumentException("Keylines that are marked as focal must all have the same masked item size.");
            }
            this.d = d10Var;
            this.f = arrayList.size();
        } else {
            if (this.c == null && f3 < this.g) {
                throw new IllegalArgumentException("Keylines before the first focal keyline must be ordered by incrementing masked item size.");
            }
            if (this.d != null && f3 > this.g) {
                throw new IllegalArgumentException("Keylines after the last focal keyline must be ordered by decreasing masked item size.");
            }
        }
        this.g = f3;
        arrayList.add(d10Var);
    }

    public final e10 b() {
        if (this.c == null) {
            throw new IllegalStateException("There must be a keyline marked as focal.");
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            ArrayList arrayList2 = this.b;
            int size = arrayList2.size();
            float f = this.a;
            if (i >= size) {
                return new e10(f, arrayList, this.e, this.f);
            }
            d10 d10Var = (d10) arrayList2.get(i);
            arrayList.add(new d10((i * f) + (this.c.b - (this.e * f)), d10Var.b, d10Var.c, d10Var.d));
            i++;
        }
    }
}
