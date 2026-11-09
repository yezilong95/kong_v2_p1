package defpackage;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class lb0 {
    public final Object a;
    public final Object b;

    public lb0(Object obj, Object obj2) {
        this.a = obj;
        this.b = obj2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof lb0)) {
            return false;
        }
        lb0 lb0Var = (lb0) obj;
        return Objects.equals(lb0Var.a, this.a) && Objects.equals(lb0Var.b, this.b);
    }

    public final int hashCode() {
        Object obj = this.a;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.b;
        return (obj2 != null ? obj2.hashCode() : 0) ^ iHashCode;
    }

    public final String toString() {
        return "Pair{" + this.a + " " + this.b + "}";
    }
}
