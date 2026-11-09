package defpackage;

import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class um0 {
    public final String a;
    public final Map b;
    public final Set c;
    public final Set d;

    public um0(String str, Map map, Set set, Set set2) {
        oy.j(map, "columns");
        oy.j(set, "foreignKeys");
        this.a = str;
        this.b = map;
        this.c = set;
        this.d = set2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x01e0, code lost:
    
        r9 = r10.build();
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01e4, code lost:
    
        r3.close();
     */
    /* JADX WARN: Finally extract failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final defpackage.um0 a(defpackage.ql0 r26, java.lang.String r27) {
        /*
            Method dump skipped, instruction units count: 515
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.um0.a(ql0, java.lang.String):um0");
    }

    public final boolean equals(Object obj) {
        Set set;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof um0)) {
            return false;
        }
        um0 um0Var = (um0) obj;
        if (!oy.c(this.a, um0Var.a) || !oy.c(this.b, um0Var.b) || !oy.c(this.c, um0Var.c)) {
            return false;
        }
        Set set2 = this.d;
        if (set2 == null || (set = um0Var.d) == null) {
            return true;
        }
        return oy.c(set2, set);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "TableInfo{name='" + this.a + "', columns=" + this.b + ", foreignKeys=" + this.c + ", indices=" + this.d + '}';
    }
}
