package defpackage;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class n90 implements Map.Entry {
    public n90 E;
    public n90 F;
    public n90 G;
    public n90 H;
    public n90 I;
    public final Object J;
    public final int K;
    public Object L;
    public int M;

    public n90() {
        this.J = null;
        this.K = -1;
        this.I = this;
        this.H = this;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.J;
            if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
                Object obj3 = this.L;
                if (obj3 == null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                } else if (obj3.equals(entry.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.J;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.L;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.J;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.L;
        return (obj2 != null ? obj2.hashCode() : 0) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.L;
        this.L = obj;
        return obj2;
    }

    public final String toString() {
        return this.J + "=" + this.L;
    }

    public n90(n90 n90Var, Object obj, int i, n90 n90Var2, n90 n90Var3) {
        this.E = n90Var;
        this.J = obj;
        this.K = i;
        this.M = 1;
        this.H = n90Var2;
        this.I = n90Var3;
        n90Var3.H = this;
        n90Var2.I = this;
    }
}
