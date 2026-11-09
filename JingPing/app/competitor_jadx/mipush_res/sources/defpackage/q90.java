package defpackage;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class q90 implements Map.Entry {
    public q90 E;
    public q90 F;
    public q90 G;
    public q90 H;
    public q90 I;
    public final Object J;
    public Object K;
    public int L;

    public q90() {
        this.J = null;
        this.I = this;
        this.H = this;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.J;
            if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
                Object obj3 = this.K;
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
        return this.K;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.J;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.K;
        return (obj2 != null ? obj2.hashCode() : 0) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.K;
        this.K = obj;
        return obj2;
    }

    public final String toString() {
        return this.J + "=" + this.K;
    }

    public q90(q90 q90Var, Object obj, q90 q90Var2, q90 q90Var3) {
        this.E = q90Var;
        this.J = obj;
        this.L = 1;
        this.H = q90Var2;
        this.I = q90Var3;
        q90Var3.H = this;
        q90Var2.I = this;
    }
}
