package defpackage;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class rm0 {
    public final String a;
    public final String b;
    public final String c;
    public final List d;
    public final List e;

    public rm0(String str, String str2, String str3, List list, List list2) {
        oy.j(list, "columnNames");
        oy.j(list2, "referenceColumnNames");
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
        if (!(obj instanceof rm0)) {
            return false;
        }
        rm0 rm0Var = (rm0) obj;
        if (oy.c(this.a, rm0Var.a) && oy.c(this.b, rm0Var.b) && oy.c(this.c, rm0Var.c) && oy.c(this.d, rm0Var.d)) {
            return oy.c(this.e, rm0Var.e);
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
