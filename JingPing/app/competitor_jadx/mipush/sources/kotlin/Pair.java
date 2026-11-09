package kotlin;

import defpackage.t40;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class Pair<A, B> implements Serializable {
    public final Object E;
    public final Object F;

    public Pair(A a, B b) {
        this.E = a;
        this.F = b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Pair copy$default(Pair pair, Object obj, Object obj2, int i, Object obj3) {
        if ((i & 1) != 0) {
            obj = pair.E;
        }
        if ((i & 2) != 0) {
            obj2 = pair.F;
        }
        return pair.copy(obj, obj2);
    }

    public final A component1() {
        return (A) this.E;
    }

    public final B component2() {
        return (B) this.F;
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
        return t40.f(this.E, pair.E) && t40.f(this.F, pair.F);
    }

    public final A getFirst() {
        return (A) this.E;
    }

    public final B getSecond() {
        return (B) this.F;
    }

    public int hashCode() {
        Object obj = this.E;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.F;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public String toString() {
        return "(" + this.E + ", " + this.F + ')';
    }
}
