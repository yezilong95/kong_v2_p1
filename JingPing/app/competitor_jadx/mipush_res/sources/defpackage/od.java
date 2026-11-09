package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class od {
    public static final nd b = new nd();
    public final Object a;

    public /* synthetic */ od(Object obj) {
        this.a = obj;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof od) {
            return t40.f(this.a, ((od) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        Object obj = this.a;
        if (obj instanceof md) {
            return ((md) obj).toString();
        }
        return "Value(" + obj + ')';
    }
}
