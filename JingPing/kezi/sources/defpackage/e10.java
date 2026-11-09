package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class e10 {
    public final float a;
    public final List b;
    public final int c;
    public final int d;

    public e10(float f, ArrayList arrayList, int i, int i2) {
        this.a = f;
        this.b = Collections.unmodifiableList(arrayList);
        this.c = i;
        this.d = i2;
    }

    public final d10 a() {
        return (d10) this.b.get(this.c);
    }

    public final d10 b() {
        return (d10) this.b.get(0);
    }

    public final d10 c() {
        return (d10) this.b.get(this.d);
    }

    public final d10 d() {
        return (d10) this.b.get(r0.size() - 1);
    }
}
