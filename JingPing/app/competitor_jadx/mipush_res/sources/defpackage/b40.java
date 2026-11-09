package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class b40 {
    public final int a;
    public final Object b;

    public b40(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b40)) {
            return false;
        }
        b40 b40Var = (b40) obj;
        return this.a == b40Var.a && t40.f(this.b, b40Var.b);
    }

    public final int hashCode() {
        int iHashCode = Integer.hashCode(this.a) * 31;
        Object obj = this.b;
        return iHashCode + (obj == null ? 0 : obj.hashCode());
    }

    public final String toString() {
        return "IndexedValue(index=" + this.a + ", value=" + this.b + ')';
    }
}
