package defpackage;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class k8 extends vv0 implements Map {
    public j8 L;

    public k8(vv0 vv0Var) {
        int i = vv0Var.G;
        b(i);
        if (this.G != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                put(vv0Var.h(i2), vv0Var.j(i2));
            }
        } else if (i > 0) {
            System.arraycopy(vv0Var.E, 0, this.E, 0, i);
            System.arraycopy(vv0Var.F, 0, this.F, 0, i << 1);
            this.G = i;
        }
    }

    @Override // java.util.Map
    public final Set entrySet() {
        if (this.L == null) {
            this.L = new j8(0, this);
        }
        j8 j8Var = this.L;
        if (j8Var.a == null) {
            j8Var.a = new ac0(j8Var, 0);
        }
        return j8Var.a;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.L == null) {
            this.L = new j8(0, this);
        }
        j8 j8Var = this.L;
        if (j8Var.b == null) {
            j8Var.b = new ac0(j8Var, 1);
        }
        return j8Var.b;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        b(map.size() + this.G);
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.L == null) {
            this.L = new j8(0, this);
        }
        j8 j8Var = this.L;
        if (j8Var.c == null) {
            j8Var.c = new cc0(j8Var);
        }
        return j8Var.c;
    }
}
