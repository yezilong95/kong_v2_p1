package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ub {
    public static final tb b = new tb();
    public final Object a;

    public /* synthetic */ ub(Object obj) {
        this.a = obj;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ub) {
            return oy.c(this.a, ((ub) obj).a);
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
        if (obj instanceof sb) {
            return ((sb) obj).toString();
        }
        return "Value(" + obj + ')';
    }
}
