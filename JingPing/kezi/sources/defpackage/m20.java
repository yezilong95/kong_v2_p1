package defpackage;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class m20 implements Map.Entry {
    public m20 b;
    public m20 c;
    public m20 d;
    public m20 e;
    public m20 f;
    public final Object g;
    public Object h;
    public int i;

    public m20() {
        this.g = null;
        this.f = this;
        this.e = this;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.g;
            if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
                Object obj3 = this.h;
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
        return this.h;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.g;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.h;
        return (obj2 != null ? obj2.hashCode() : 0) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.h;
        this.h = obj;
        return obj2;
    }

    public final String toString() {
        return this.g + "=" + this.h;
    }

    public m20(m20 m20Var, Object obj, m20 m20Var2, m20 m20Var3) {
        this.b = m20Var;
        this.g = obj;
        this.i = 1;
        this.e = m20Var2;
        this.f = m20Var3;
        m20Var3.e = this;
        m20Var2.f = this;
    }
}
