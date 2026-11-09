package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class u70 {
    public final float a;
    public final float b;
    public v70 d;
    public v70 e;
    public final ArrayList c = new ArrayList();
    public int f = -1;
    public int g = -1;
    public float h = 0.0f;
    public int i = -1;

    public u70(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public final void a(float f, float f2, float f3, boolean z, boolean z2) {
        float fAbs;
        float f4 = f3 / 2.0f;
        float f5 = f - f4;
        float f6 = f4 + f;
        float f7 = this.b;
        if (f6 > f7) {
            fAbs = Math.abs(f6 - Math.max(f6 - f3, f7));
        } else {
            fAbs = 0.0f;
            if (f5 < 0.0f) {
                fAbs = Math.abs(f5 - Math.min(f5 + f3, 0.0f));
            }
        }
        b(f, f2, f3, z, z2, fAbs, 0.0f, 0.0f);
    }

    public final void b(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5, float f6) {
        if (f3 <= 0.0f) {
            return;
        }
        ArrayList arrayList = this.c;
        if (z2) {
            if (z) {
                throw new IllegalArgumentException("Anchor keylines cannot be focal.");
            }
            int i = this.i;
            if (i != -1 && i != 0) {
                throw new IllegalArgumentException("Anchor keylines must be either the first or last keyline.");
            }
            this.i = arrayList.size();
        }
        v70 v70Var = new v70(Float.MIN_VALUE, f, f2, f3, z2, f4, f5, f6);
        if (z) {
            if (this.d == null) {
                this.d = v70Var;
                this.f = arrayList.size();
            }
            if (this.g != -1 && arrayList.size() - this.g > 1) {
                throw new IllegalArgumentException("Keylines marked as focal must be placed next to each other. There cannot be non-focal keylines between focal keylines.");
            }
            if (f3 != this.d.d) {
                throw new IllegalArgumentException("Keylines that are marked as focal must all have the same masked item size.");
            }
            this.e = v70Var;
            this.g = arrayList.size();
        } else {
            if (this.d == null && f3 < this.h) {
                throw new IllegalArgumentException("Keylines before the first focal keyline must be ordered by incrementing masked item size.");
            }
            if (this.e != null && f3 > this.h) {
                throw new IllegalArgumentException("Keylines after the last focal keyline must be ordered by decreasing masked item size.");
            }
        }
        this.h = f3;
        arrayList.add(v70Var);
    }

    public final void c(float f, float f2, float f3, int i, boolean z) {
        if (i <= 0 || f3 <= 0.0f) {
            return;
        }
        int i2 = 0;
        while (i2 < i) {
            float f4 = f2;
            float f5 = f3;
            boolean z2 = z;
            a((i2 * f3) + f, f4, f5, z2, false);
            i2++;
            f2 = f4;
            f3 = f5;
            z = z2;
        }
    }

    public final w70 d() {
        if (this.d == null) {
            throw new IllegalStateException("There must be a keyline marked as focal.");
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            ArrayList arrayList2 = this.c;
            int size = arrayList2.size();
            float f = this.a;
            if (i >= size) {
                return new w70(f, arrayList, this.f, this.g);
            }
            v70 v70Var = (v70) arrayList2.get(i);
            arrayList.add(new v70((i * f) + (this.d.b - (this.f * f)), v70Var.b, v70Var.c, v70Var.d, v70Var.e, v70Var.f, v70Var.g, v70Var.h));
            i++;
        }
    }
}
