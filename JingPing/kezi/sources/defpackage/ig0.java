package defpackage;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ig0 implements Map.Entry {
    public final Object b;
    public final Object c;
    public ig0 d;
    public ig0 e;

    public ig0(Object obj, Object obj2) {
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ig0)) {
            return false;
        }
        ig0 ig0Var = (ig0) obj;
        return this.b.equals(ig0Var.b) && this.c.equals(ig0Var.c);
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.b;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.c;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.b.hashCode() ^ this.c.hashCode();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public final String toString() {
        return this.b + "=" + this.c;
    }
}
