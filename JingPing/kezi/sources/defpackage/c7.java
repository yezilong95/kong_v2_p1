package defpackage;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class c7 extends fj0 implements Map {
    public b7 i;

    public c7(fj0 fj0Var) {
        int i = fj0Var.d;
        b(i);
        if (this.d != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                put(fj0Var.h(i2), fj0Var.j(i2));
            }
        } else if (i > 0) {
            System.arraycopy(fj0Var.b, 0, this.b, 0, i);
            System.arraycopy(fj0Var.c, 0, this.c, 0, i << 1);
            this.d = i;
        }
    }

    @Override // java.util.Map
    public final Set entrySet() {
        if (this.i == null) {
            this.i = new b7(0, this);
        }
        b7 b7Var = this.i;
        if (((l40) b7Var.a) == null) {
            b7Var.a = new l40(b7Var, 0);
        }
        return (l40) b7Var.a;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.i == null) {
            this.i = new b7(0, this);
        }
        b7 b7Var = this.i;
        if (((l40) b7Var.b) == null) {
            b7Var.b = new l40(b7Var, 1);
        }
        return (l40) b7Var.b;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        b(map.size() + this.d);
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.i == null) {
            this.i = new b7(0, this);
        }
        b7 b7Var = this.i;
        if (((n40) b7Var.c) == null) {
            b7Var.c = new n40(b7Var);
        }
        return (n40) b7Var.c;
    }
}
