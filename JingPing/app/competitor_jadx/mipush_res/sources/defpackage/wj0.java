package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class wj0 implements cf {
    public final Class E;

    public wj0(Class cls) {
        t40.n(cls, "jClass");
        this.E = cls;
    }

    @Override // defpackage.cf
    public final Class a() {
        return this.E;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof wj0) {
            return t40.f(this.E, ((wj0) obj).E);
        }
        return false;
    }

    public final int hashCode() {
        return this.E.hashCode();
    }

    public final String toString() {
        return this.E.toString() + " (Kotlin reflection is not available)";
    }
}
