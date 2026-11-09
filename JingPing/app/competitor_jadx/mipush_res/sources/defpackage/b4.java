package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class b4 {
    public int a;
    public int b;
    public int c;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || b4.class != obj.getClass()) {
                return false;
            }
            b4 b4Var = (b4) obj;
            int i = this.a;
            if (i != b4Var.a) {
                return false;
            }
            if (i != 8 || Math.abs(this.c - this.b) != 1 || this.c != b4Var.b || this.b != b4Var.c) {
                return this.c == b4Var.c && this.b == b4Var.b;
            }
        }
        return true;
    }

    public final int hashCode() {
        return (((this.a * 31) + this.b) * 31) + this.c;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[");
        int i = this.a;
        sb.append(i != 1 ? i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm" : "add");
        sb.append(",s:");
        sb.append(this.b);
        sb.append("c:");
        sb.append(this.c);
        sb.append(",p:null]");
        return sb.toString();
    }
}
