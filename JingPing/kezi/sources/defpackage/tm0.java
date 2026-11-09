package defpackage;

import androidx.room.Index$Order;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class tm0 {
    public final String a;
    public final boolean b;
    public final List c;
    public final List d;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Object, java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.ArrayList] */
    public tm0(String str, boolean z, List list, List list2) {
        oy.j(list, "columns");
        oy.j(list2, "orders");
        this.a = str;
        this.b = z;
        this.c = list;
        this.d = list2;
        if (list2.isEmpty()) {
            int size = list.size();
            list2 = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                list2.add(Index$Order.ASC.name());
            }
        }
        this.d = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof tm0) {
            tm0 tm0Var = (tm0) obj;
            if (this.b == tm0Var.b && oy.c(this.c, tm0Var.c) && oy.c(this.d, tm0Var.d)) {
                String str = this.a;
                boolean zJ0 = dl0.j0(str, "index_", false);
                String str2 = tm0Var.a;
                return zJ0 ? dl0.j0(str2, "index_", false) : str.equals(str2);
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.a;
        return this.d.hashCode() + ((this.c.hashCode() + ((((dl0.j0(str, "index_", false) ? -1184239155 : str.hashCode()) * 31) + (this.b ? 1 : 0)) * 31)) * 31);
    }

    public final String toString() {
        return "Index{name='" + this.a + "', unique=" + this.b + ", columns=" + this.c + ", orders=" + this.d + "'}";
    }
}
