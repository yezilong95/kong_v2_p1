package defpackage;

import androidx.room.Index$Order;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class iz0 {
    public final String a;
    public final boolean b;
    public final List c;
    public final List d;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Object, java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.ArrayList] */
    public iz0(String str, boolean z, List list, List list2) {
        t40.n(list, "columns");
        t40.n(list2, "orders");
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
        if (!(obj instanceof iz0)) {
            return false;
        }
        iz0 iz0Var = (iz0) obj;
        if (this.b != iz0Var.b || !t40.f(this.c, iz0Var.c) || !t40.f(this.d, iz0Var.d)) {
            return false;
        }
        String str = this.a;
        boolean zStartsWith = str.startsWith("index_");
        String str2 = iz0Var.a;
        return zStartsWith ? str2.startsWith("index_") : str.equals(str2);
    }

    public final int hashCode() {
        String str = this.a;
        return this.d.hashCode() + ((this.c.hashCode() + ((((str.startsWith("index_") ? -1184239155 : str.hashCode()) * 31) + (this.b ? 1 : 0)) * 31)) * 31);
    }

    public final String toString() {
        return "Index{name='" + this.a + "', unique=" + this.b + ", columns=" + this.c + ", orders=" + this.d + "'}";
    }
}
