package defpackage;

import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class jz0 {
    public final String a;
    public final Map b;
    public final Set c;
    public final Set d;

    public jz0(String str, Map map, Set set, Set set2) {
        t40.n(map, "columns");
        t40.n(set, "foreignKeys");
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
        To view partially-correct add '--show-bad-code' argument
    */
    public static final defpackage.jz0 a(defpackage.ey0 r26, java.lang.String r27) {
        /*
            Method dump skipped, instruction units count: 515
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jz0.a(ey0, java.lang.String):jz0");
    }

    public final boolean equals(Object obj) {
        Set set;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof jz0)) {
            return false;
        }
        jz0 jz0Var = (jz0) obj;
        if (!t40.f(this.a, jz0Var.a) || !t40.f(this.b, jz0Var.b) || !t40.f(this.c, jz0Var.c)) {
            return false;
        }
        Set set2 = this.d;
        if (set2 == null || (set = jz0Var.d) == null) {
            return true;
        }
        return t40.f(set2, set);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "TableInfo{name='" + this.a + "', columns=" + this.b + ", foreignKeys=" + this.c + ", indices=" + this.d + '}';
    }
}
