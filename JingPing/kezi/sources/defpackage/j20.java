package defpackage;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class j20 implements Map.Entry {
    public j20 b;
    public j20 c;
    public j20 d;
    public j20 e;
    public j20 f;
    public final Object g;
    public final int h;
    public Object i;
    public int j;

    public j20() {
        this.g = null;
        this.h = -1;
        this.f = this;
        this.e = this;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.g;
            if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
                Object obj3 = this.i;
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
        return this.g;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.i;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.g;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.i;
        return (obj2 != null ? obj2.hashCode() : 0) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.i;
        this.i = obj;
        return obj2;
    }

    public final String toString() {
        return this.g + "=" + this.i;
    }

    public j20(j20 j20Var, Object obj, int i, j20 j20Var2, j20 j20Var3) {
        this.b = j20Var;
        this.g = obj;
        this.h = i;
        this.j = 1;
        this.e = j20Var2;
        this.f = j20Var3;
        j20Var3.e = this;
        j20Var2.f = this;
    }
}
