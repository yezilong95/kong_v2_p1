package kotlin;

import defpackage.t40;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class Triple<A, B, C> implements Serializable {
    public final Object E;
    public final Object F;
    public final Object G;

    public Triple(A a, B b, C c) {
        this.E = a;
        this.F = b;
        this.G = c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Triple copy$default(Triple triple, Object obj, Object obj2, Object obj3, int i, Object obj4) {
        if ((i & 1) != 0) {
            obj = triple.E;
        }
        if ((i & 2) != 0) {
            obj2 = triple.F;
        }
        if ((i & 4) != 0) {
            obj3 = triple.G;
        }
        return triple.copy(obj, obj2, obj3);
    }

    public final A component1() {
        return (A) this.E;
    }

    public final B component2() {
        return (B) this.F;
    }

    public final C component3() {
        return (C) this.G;
    }

    public final Triple<A, B, C> copy(A a, B b, C c) {
        return new Triple<>(a, b, c);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Triple)) {
            return false;
        }
        Triple triple = (Triple) obj;
        return t40.f(this.E, triple.E) && t40.f(this.F, triple.F) && t40.f(this.G, triple.G);
    }

    public final A getFirst() {
        return (A) this.E;
    }

    public final B getSecond() {
        return (B) this.F;
    }

    public final C getThird() {
        return (C) this.G;
    }

    public int hashCode() {
        Object obj = this.E;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.F;
        int iHashCode2 = (iHashCode + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Object obj3 = this.G;
        return iHashCode2 + (obj3 != null ? obj3.hashCode() : 0);
    }

    public String toString() {
        return "(" + this.E + ", " + this.F + ", " + this.G + ')';
    }
}
