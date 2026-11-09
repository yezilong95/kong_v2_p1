package kotlin;

import defpackage.oy;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class Pair<A, B> implements Serializable {
    public final Object b;
    public final Object c;

    public Pair(A a, B b) {
        this.b = a;
        this.c = b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Pair copy$default(Pair pair, Object obj, Object obj2, int i, Object obj3) {
        if ((i & 1) != 0) {
            obj = pair.b;
        }
        if ((i & 2) != 0) {
            obj2 = pair.c;
        }
        return pair.copy(obj, obj2);
    }

    public final A component1() {
        return (A) this.b;
    }

    public final B component2() {
        return (B) this.c;
    }

    public final Pair<A, B> copy(A a, B b) {
        return new Pair<>(a, b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return oy.c(this.b, pair.b) && oy.c(this.c, pair.c);
    }

    public final A getFirst() {
        return (A) this.b;
    }

    public final B getSecond() {
        return (B) this.c;
    }

    public int hashCode() {
        Object obj = this.b;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.c;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public String toString() {
        return "(" + this.b + ", " + this.c + ')';
    }
}
