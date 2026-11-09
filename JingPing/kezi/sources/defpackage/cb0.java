package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class cb0 implements id {
    public final Class b;

    public cb0(Class cls) {
        oy.j(cls, "jClass");
        this.b = cls;
    }

    @Override // defpackage.id
    public final Class a() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof cb0) {
            return oy.c(this.b, ((cb0) obj).b);
        }
        return false;
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return this.b.toString() + " (Kotlin reflection is not available)";
    }
}
