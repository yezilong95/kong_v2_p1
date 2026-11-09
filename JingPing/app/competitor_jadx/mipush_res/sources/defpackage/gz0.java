package defpackage;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class gz0 {
    public final String a;
    public final String b;
    public final String c;
    public final List d;
    public final List e;

    public gz0(String str, String str2, String str3, List list, List list2) {
        t40.n(list, "columnNames");
        t40.n(list2, "referenceColumnNames");
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = list;
        this.e = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof gz0)) {
            return false;
        }
        gz0 gz0Var = (gz0) obj;
        if (t40.f(this.a, gz0Var.a) && t40.f(this.b, gz0Var.b) && t40.f(this.c, gz0Var.c) && t40.f(this.d, gz0Var.d)) {
            return t40.f(this.e, gz0Var.e);
        }
        return false;
    }

    public final int hashCode() {
        return this.e.hashCode() + ((this.d.hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "ForeignKey{referenceTable='" + this.a + "', onDelete='" + this.b + " +', onUpdate='" + this.c + "', columnNames=" + this.d + ", referenceColumnNames=" + this.e + '}';
    }
}
