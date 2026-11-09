package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class w70 {
    public final float a;
    public final List b;
    public final int c;
    public final int d;

    public w70(float f, ArrayList arrayList, int i, int i2) {
        this.a = f;
        this.b = Collections.unmodifiableList(arrayList);
        this.c = i;
        this.d = i2;
    }

    public final v70 a() {
        return (v70) this.b.get(this.c);
    }

    public final v70 b() {
        return (v70) this.b.get(0);
    }

    public final v70 c() {
        return (v70) this.b.get(this.d);
    }

    public final v70 d() {
        return (v70) this.b.get(r0.size() - 1);
    }
}
