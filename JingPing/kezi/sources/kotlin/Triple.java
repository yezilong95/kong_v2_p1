package kotlin;

import defpackage.oy;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class Triple<A, B, C> implements Serializable {
    public final Object b;
    public final Object c;
    public final Object d;

    public Triple(A a, B b, C c) {
        this.b = a;
        this.c = b;
        this.d = c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Triple copy$default(Triple triple, Object obj, Object obj2, Object obj3, int i, Object obj4) {
        if ((i & 1) != 0) {
            obj = triple.b;
        }
        if ((i & 2) != 0) {
            obj2 = triple.c;
        }
        if ((i & 4) != 0) {
            obj3 = triple.d;
        }
        return triple.copy(obj, obj2, obj3);
    }

    public final A component1() {
        return (A) this.b;
    }

    public final B component2() {
        return (B) this.c;
    }

    public final C component3() {
        return (C) this.d;
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
        return oy.c(this.b, triple.b) && oy.c(this.c, triple.c) && oy.c(this.d, triple.d);
    }

    public final A getFirst() {
        return (A) this.b;
    }

    public final B getSecond() {
        return (B) this.c;
    }

    public final C getThird() {
        return (C) this.d;
    }

    public int hashCode() {
        Object obj = this.b;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.c;
        int iHashCode2 = (iHashCode + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Object obj3 = this.d;
        return iHashCode2 + (obj3 != null ? obj3.hashCode() : 0);
    }

    public String toString() {
        return "(" + this.b + ", " + this.c + ", " + this.d + ')';
    }
}
