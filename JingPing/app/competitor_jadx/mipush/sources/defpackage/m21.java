package defpackage;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class m21 {
    public final Class a;
    public final Type b;
    public final int c;

    public m21(Type type) {
        type.getClass();
        Type typeF = qm.f(type);
        this.b = typeF;
        this.a = qm.I(typeF);
        this.c = typeF.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof m21) {
            return qm.m(this.b, ((m21) obj).b);
        }
        return false;
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return qm.K0(this.b);
    }
}
